/*
 * 
 * Created on 2005-3-7 1:20:16
 *
 * PingMonitor.java
 *
 * History:
 *
 */
package COM.dragonflow.StandardMonitor;

/**
 * Comment for <code>PingMonitor</code>
 * 
 * @author 
 * @version 0.0
 *
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;
import java.util.Vector;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import sun.misc.Perf.GetPerfAction;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import system.Xml.XmlElement;

import SiteViewMain.SiteViewSupport;
import Siteview.Operators;
import Siteview.QueryInfoToGet;
import Siteview.SearchCriteria;
import Siteview.SiteviewQuery;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;

import COM.dragonflow.HTTP.HTTPRequest;
import COM.dragonflow.Page.CGI;
import COM.dragonflow.Properties.*;
import COM.dragonflow.SiteView.*;
import COM.dragonflow.SiteViewException.SiteViewException;
import COM.dragonflow.Utils.FileTools;
import COM.dragonflow.Utils.I18N;
import COM.dragonflow.Utils.TextUtils;
import jgl.Array;
import jgl.HashMap;

public class ConfigDownLoadUploadMonitor extends ServerMonitor {
	static CommandTextProperty pServiceName;
	static StringProperty pHost;// 所请求服务器IP
//	static StringProperty pConfigName;// 用户保存配置文件的全路径
	static StringProperty puName;//用户
	static StringProperty pPwd; // 所请求服务器的密码
	static StringProperty pSuperName; // 所请求服务器的超级用户名
	static StringProperty pSuperPwd; // 所请求服务器的超级密码
	static StringProperty pName;
	static StringProperty pIp;
	static StringProperty pTimeout;
	static StringProperty pSize;
	static StringProperty pStatus;
	static StringProperty pRoundTripTime;
	static StringProperty pPacketsSent;
	static StringProperty pPacketsReceived;
	static StringProperty pPercentGood;
	static StringProperty pPercentFailed;
	static StringProperty pConfigFileName;//配置文件名称
	static int kInitialPacketCount = 1;
	static int kRetryPacketCount = 3;
	File f1;
	public static String ConfigURL=null;//文件比较位置
	public static String ConfigAllURl=null;//存储位置
//	boolean success=false;

	public ConfigDownLoadUploadMonitor() {
	}

	public String getHostname() {
		return getProperty(pHost);
	}

	
	public String getTestURL() {
		return "/SiteView/cgi/go.exe/SiteView?page=ping&host="
				+ getProperty(pHost)
				+ "&group="
				+ HTTPRequest.encodeString(I18N
						.toDefaultEncoding(getProperty(pGroupID)));
	}
  
	public static boolean getSuccecc(){
		ConfigFileListDownLoad cdlf= new ConfigFileListDownLoad();
		boolean success=cdlf.isSuccess();
		 return success;
	}
	public static String  getUrl(String url,String groupName,String host, 
			String ConfigFileName, String serviceName){
		File f= new File(ConfigCopy.getTempUrl());
		f.mkdirs();
		ConfigURL= ConfigCopy.getTempUrl()+"/"+ConfigFileName;
		ConfigAllURl=url+"/"+groupName+"/"+serviceName+"/"+host+"/"+ConfigFileName;
		return null;
	}
	/**
	 * 监听事件
	 */
	protected boolean update() {
	   
		boolean flag = false;
		
		String host = getProperty(pHost);
		// String configName = getProperty(pConfigName);
		String url = ConfigCopy.getURL();
		String pwd = getProperty(pPwd);
		String superName = getProperty(pSuperName);
		String superPwd = getProperty(pSuperPwd);
		String serviceName = getProperty(pServiceName);
		String ConfigFileName = getProperty(pConfigFileName);
		String UserName=getProperty(puName);
		String group = "";
		String groupName = "";
		BusinessObject businessObj=FileTools.CreateBo("ServerAddress", host, "RemoteMachine");
		if(businessObj!=null){
			group = businessObj.GetField("Groups").get_NativeValue().toString();
			serviceName = businessObj.GetField("EpuipmentsTypes").get_NativeValue().toString();
			UserName=businessObj.GetField("UserName").get_NativeValue().toString();
			pwd = businessObj.GetField("EquipmentPassWord").get_NativeValue().toString();
			superName = businessObj.GetField("AuthorityName").get_NativeValue().toString();
			superPwd = businessObj.GetField("AuthorityPwd").get_NativeValue().toString();
		}
		BusinessObject Obj=FileTools.CreateBo("RecId", group, "EccGroup");
		if(Obj!=null){
			groupName = Obj.GetField("GroupName").get_NativeValue().toString();
		}
		File file = new File(url);
		if (file.listFiles() == null) {
			try {
				ConfigCopy.testClone();
			} catch (InvalidRemoteException e) {
				e.printStackTrace();
			} catch (TransportException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (GitAPIException e) {
				e.printStackTrace();
			}

		} else {
			try {
				ConfigCopy.testpull();
			} catch (Exception e) {
				System.err.println("Git pull Execption");;
			}
		}
		
	//flag为false 代表没有更新
			if (flag) {
				
				SiteviewQuery siteviewquery = new SiteviewQuery();
				siteviewquery.AddBusObQuery("ConfigFileManagemant", QueryInfoToGet.All);
				SearchCriteria sqlbuilder = siteviewquery.get_CriteriaBuilder();
				XmlElement xmlelement1 = sqlbuilder.FieldAndValueExpression("ConfigName",Operators.Equals,ConfigFileName);
				XmlElement xmlelement2 = sqlbuilder.FieldAndValueExpression("EquipmentAddress",Operators.Equals,host);
				XmlElement sqlxmlelment = sqlbuilder.AndExpressions(new XmlElement[]{xmlelement1,xmlelement2});
				siteviewquery.set_BusObSearchCriteria(sqlxmlelment);
				ICollection busCollection = FileTools.getApi().get_BusObService().get_SimpleQueryResolver().ResolveQueryToBusObList(siteviewquery);
				
				if(busCollection!=null){
				IEnumerator ienumbus = busCollection.GetEnumerator();
				while(ienumbus.MoveNext()){
					BusinessObject busob = (BusinessObject)ienumbus.get_Current();
					busob.DeleteObject(FileTools.getApi());
				}
				File file2 = new File(url + "/" + groupName + "/"
						+ serviceName + "/" + host + "/" + ConfigFileName);
				FileInputStream in = null;
				long filesize = 0;
				try {
					in = new FileInputStream(file2);
					filesize = in.available();
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
						String RecId = UUID.randomUUID().toString().replace("-", "");
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date date = new Date();
						String date1 = dateFormat.format(date);
						BusinessObject bb=FileTools.getApi_1().get_BusObService().Create("ConfigFileManagemant");
//						String URL = ConfigCopy.getURL();
						bb.GetField("URL").SetValue(new SiteviewValue(url));
                        bb.GetField("RecId").SetValue(new
								 SiteviewValue(RecId));
						bb.GetField("Groups").SetValue(new
								 SiteviewValue(group));
						bb.GetField("GroupName").SetValue(new
								 SiteviewValue(groupName));
						bb.GetField("EquipmentType").SetValue(new
								 SiteviewValue(serviceName));
						bb.GetField("EquipmentAddress").SetValue(new
								 SiteviewValue(host));
						bb.GetField("ConfigName").SetValue(new
								 SiteviewValue(ConfigFileName));
						bb.GetField("ConfigFileSize").SetValue(new
								 SiteviewValue(filesize));
						bb.GetField("updateTime").SetValue(new
								 SiteviewValue(date1));
						bb.SaveObject(FileTools.getApi(), true, true);
						try {
							System.out.println("提交数据");
							ConfigCopy.testpush();//提交数据			
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
					}
			int timeout = getPropertyAsInteger(pTimeout);
			int size = getPropertyAsInteger(pSize);
			int initialPacketCount = getSettingAsLong("_pingPacketsInitial",
					kInitialPacketCount);
			int packetRetry = getSettingAsLong("_pingPacketsRetry",
					kRetryPacketCount);
			int ai[] = Platform.ping(host, timeout, initialPacketCount, size,
					this);
			if (ai[1] != initialPacketCount && packetRetry != 0) {
				initialPacketCount = packetRetry;
				ai = Platform.ping(host, timeout, initialPacketCount, size,
						this);
			}
			int packetReceived = ai[1];
			int j1 = ai[0];
			currentStatus = "ConfigDownLoadUpLoadMonitor analyzing results...";
			boolean success=getSuccecc();//判断telnet是否连接上
			if (stillActive()) {
				synchronized (this) {
					 if (!success) {
					 packetReceived = -1;
					 }else{
						 packetReceived=1;
					 }
					if (packetReceived > 0) {
						setProperty(pStatus, "ok");
						float f = (float) j1 / (float) packetReceived;
						if (f < 10F
								&& getSetting("_singleDigitMillis").length() <= 0) {
							f = 10F;
						}
						String s1=ConfigFileListDownLoad.description;
						System.out.println("s1=="+s1);
							
					
						setProperty(pPacketsReceived, packetReceived);
						setProperty(pPercentGood,TextUtils.floatToString(((float) packetReceived / (float) initialPacketCount) * 100F,0));
						setProperty(pPercentFailed,TextUtils.floatToString((((float) initialPacketCount - (float) packetReceived) / (float) initialPacketCount) * 100F,0));
//						setProperty(pRoundTripTime, f);
//						setProperty(pMeasurement,
//								getMeasurement(pRoundTripTime, 200L));
						setProperty(pStateString, s1);
						
					} else {
						setProperty(pPacketsReceived, 0);
						setProperty(pPercentGood, 0);
						setProperty(pPercentFailed, 0);
						setProperty(pMeasurement, 0);
//						setProperty(pRoundTripTime, "n/a");
						setProperty(pStatus, "failed");
						if (j1 == Platform.PING_COMMAND_FAILED) {
							setProperty(pNoData, "n/a");
							setProperty(pStateString,
									"数据不可用下载失败");
						} else {
							setProperty(pStateString, "未连接上");
						}
					}
				}
			
	}
		return true;
		
	}

	public Array getLogProperties() {
		Array array = super.getLogProperties();
		array.add(pStatus);
//		array.add(pRoundTripTime);
//		array.add(pPercentGood);
		array.add(pStateString);
		return array;
	}

	/**
	 * 
	 * 输入字段验证
	 */
	public String verify(StringProperty stringproperty, String s,
			HTTPRequest httprequest, HashMap hashmap) {

		if (stringproperty == pHost) {
			if (s.length() == 0) {
				hashmap.put(stringproperty, stringproperty.getLabel()
						+ " missing");
			} else if (TextUtils.hasSpaces(s)) {
				hashmap.put(stringproperty, "no spaces are allowed");
			}
			return s;
		}
		if (stringproperty == pTimeout) {
			if (TextUtils.digits(s) != s.length()) {
				hashmap.put(stringproperty, "time out must be a number");
			} else if (TextUtils.toInt(s) < 100) {
				hashmap.put(stringproperty,
						"time out must be greater than 100 (remember, the timeout is in milliseconds)");
			}
			return s;
		}
		if (stringproperty == pSize) {
			if (TextUtils.digits(s) != s.length()) {
				hashmap.put(stringproperty,
						"packet size must be a positive number");
			} else if (TextUtils.toInt(s) < 1 || TextUtils.toInt(s) > 60000) {
				hashmap.put(stringproperty,
						"packet size must be a number between 1 and 60000");
			}
			return s;
		} else {
			return super.verify(stringproperty, s, httprequest, hashmap);
		}
	}

	public String getProperty(StringProperty stringproperty)
			throws NullPointerException {
		if (stringproperty == pDiagnosticText) {
			if (getProperty(pCategory).equals("good")) {
				return "";
			} else {
				return DIAGNOSTIC_HEADER
						+ diagnosticTraceRoute(getProperty(pHost)) + "";
			}
		} else {
			return super.getProperty(stringproperty);
		}
	}

	static {
		// 设备名称
		pServiceName = new CommandTextProperty("_service", true);
		pServiceName.setWindowsPlatforms();
		pServiceName.setDisplayText("Service", "");
		pServiceName.setParameterOptions(true, 1, false);
		pHost = new StringProperty("_hostname", "", "host name");
	
		pHost.setParameterOptions(true, 2, false);
		puName= new StringProperty("_uName","","user name");
       puName.setParameterOptions(true, 3, false);
		pPwd = new StringProperty("_pPwd", "", "pPwd");
		
		pPwd.setParameterOptions(true, 4, false);

		pSuperName = new StringProperty("_pSuperName", "", "pSuperName");
		
		pSuperName.setParameterOptions(true, 5, false);

		pSuperPwd = new StringProperty("_pSuperPwd", "", "pSuperPwd");
		
		pSuperPwd.setParameterOptions(true, 6, false);
		
		pConfigFileName= new StringProperty("_pConfigFileName", "", "pConfigFileName");
		pConfigFileName.setParameterOptions(true, 7, false);

		pTimeout = new NumericProperty("_timeout", "5000", "milliseconds");
		pTimeout.setDisplayText("Timeout",
				"the time out per packet, in milliseconds, to wait for ping replies\n");
		pTimeout.setParameterOptions(true, 8, true);
		pSize = new NumericProperty("_packetSize", "32", "bytes");
		pSize.setDisplayText("Size", "the size, in bytes, of the ping message");
		pSize.setParameterOptions(true, 9, true);
//		pRoundTripTime = new NumericProperty("roundTripTime", "0",
//				"milliseconds");
//		
//		pRoundTripTime.setLabel("round trip time");
//		pRoundTripTime.setStateOptions(1);
		pStatus = new StringProperty("status", "no data");
		pPercentGood = new PercentProperty("percentGood");
		pPercentGood.setLabel("% packets good");
		pPercentGood.setStateOptions(3);
		pPercentFailed = new PercentProperty("percentFailed");
		pPercentFailed.setLabel("% packets failed");
		pPacketsSent = new NumericProperty("packetsSent");
		pPacketsSent.setLabel("packets sent");
		pPacketsReceived = new NumericProperty("packetsReceived");
		pPacketsReceived.setLabel("packets received");
		StringProperty astringproperty[] = { pServiceName, pHost, pPwd,
				pSuperName, pSuperPwd,pConfigFileName, pTimeout, pSize,
				pStatus, pPacketsSent, pPacketsReceived,
				pPercentGood, pPercentFailed };
//		StringProperty astringproperty[] = { pServiceName, pHost, pPwd,
//				pSuperName, pSuperPwd,pConfigFileName, pTimeout, pSize,
//				pStatus, pRoundTripTime, pPacketsSent, pPacketsReceived,
//			pPercentGood, pPercentFailed };
		addProperties("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				astringproperty);
		addClassElement("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				Rule.stringToClassifier("percentGood == 0\terror", true));
//		addClassElement("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
//				Rule.stringToClassifier("roundTripTime == n/a\terror"));
		addClassElement("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				Rule.stringToClassifier("percentGood < 100\twarning", true));
		addClassElement("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				Rule.stringToClassifier("percentGood == 100\tgood", true));
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				"description", "Pings a device on the network.");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				"help", "PingMon.htm");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				"title", "ConfigDownloadUploadMonitor");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				"class", "ConfigPingMonitor");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				"target", "_hostname");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				"toolName", "config");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				"toolDescription", "Performs a roundtrip test on the network.");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				"topazName", "server");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigDownLoadUploadMonitor",
				"topazType", "System Resources");
	}
}
