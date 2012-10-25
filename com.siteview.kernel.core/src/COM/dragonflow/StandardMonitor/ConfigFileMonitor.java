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

import SiteViewMain.SiteViewSupport;

import COM.dragonflow.HTTP.HTTPRequest;
import COM.dragonflow.Page.CGI;
import COM.dragonflow.Properties.*;
import COM.dragonflow.SiteView.*;
import COM.dragonflow.SiteViewException.SiteViewException;
import COM.dragonflow.Utils.I18N;
import COM.dragonflow.Utils.TextUtils;
import COM.dragonflow.itsm.data.JDBCForSQL;
import jgl.Array;
import jgl.HashMap;

public class ConfigFileMonitor extends ServerMonitor {
	static CommandTextProperty pServiceName;
	static StringProperty pHost;// 所请求服务器IP
	static StringProperty pConfigName;// 用户保存配置文件的全路径
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
	static int kInitialPacketCount = 1;
	static int kRetryPacketCount = 3;
	static StringProperty pCommand;
	File f1;

	public ConfigFileMonitor() {
	}

	public String getHostname() {
		return getProperty(pHost);
	}

	public String getConfigName() {
		return getProperty(pConfigName);
	}

	public String getTestURL() {
		return "/SiteView/cgi/go.exe/SiteView?page=ping&host="
				+ getProperty(pHost)
				+ "&group="
				+ HTTPRequest.encodeString(I18N
						.toDefaultEncoding(getProperty(pGroupID)));
	}

	/**
	 * 按钮事件
	 */
	protected boolean update() {
		boolean flag = false;
		String host = getProperty(pHost);

		// String configName = getProperty(pConfigName);
		String configName = "F:/CfrConfigFile";
		String pwd = getProperty(pPwd);
		String superName = getProperty(pSuperName);
		String superPwd = getProperty(pSuperPwd);
		String serviceName = getProperty(pServiceName);
		String command = getProperty(pCommand);
		String group = "";
		String groupName = "";
		// JDBC 查询数据
		Connection conn = JDBCForSQL.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select r.* , e.GroupName from dbo.RemoteMachine as r , dbo.EccGroup as e where ServerAddress='"
				+ host + "'  and e.RecId=r.groups";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				group = rs.getString("Groups");
				serviceName = rs.getString("EpuipmentsTypes");
				pwd = rs.getString("PasswordMachine");
				superName = rs.getString("AuthorityName");
				superPwd = rs.getString("AuthorityPwd");
				groupName = rs.getString("Groupname");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		File file = new File(configName);
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
						e.printStackTrace();
					}
			}

		

		if ("cisco".equals(serviceName)) {
			flag = ConfigCopy.ConfigCisco(host, configName, pwd, superName,
					superPwd, command, groupName, serviceName);
		} else if ("huawei".equals(serviceName)) {
			flag = ConfigCopy.ConfigHuaWei(host, configName, pwd, superName,
					superPwd, command, groupName, serviceName);
		} else {
			return false;
		}
		if (flag) {

			File file2 = new File(configName + "/" + groupName + "/"
					+ serviceName + "/" + host + "/"+"config.txt");
			FileInputStream in = null;
			long filesize = 0;
			try {
				in = new FileInputStream(file2);
				filesize = in.available();
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// long file2Size = file2.getFreeSpace();
			String RecId = UUID.randomUUID().toString().replace("-", "");
			 SimpleDateFormat dateFormat = new SimpleDateFormat(
			 "yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			 String date1 = dateFormat.format(date);
//			String date1 = date.toString();
//			System.out.println(date1);
			StringBuilder addsql = new StringBuilder(
					"insert into dbo.ConfigFileManagemant(RecId,Groups,GroupName,EquipmentType,EquipmentAddress,ConfigName,ConfigFileSize,updateTime) values(");
			addsql.append("'");
			addsql.append(RecId);
			addsql.append("','");
			addsql.append(group);
			addsql.append("','");
			addsql.append(groupName);
			addsql.append("','");
			addsql.append(serviceName);
			addsql.append("','");
			addsql.append(host);
			addsql.append("','");
			addsql.append("config.txt");
			addsql.append("','");
			addsql.append(filesize);
			addsql.append("','");
			addsql.append(date1);
			addsql.append("')");
//			System.out.println(addsql);
			try {
				int a = st.executeUpdate(addsql.toString());
				if (a > 0) {
					 System.out.println("本地库跟新");
					try {
						ConfigCopy.testpush();
						 System.out.println("提交到服务器成功");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		int timeout = getPropertyAsInteger(pTimeout);
		int size = getPropertyAsInteger(pSize);
		int initialPacketCount = getSettingAsLong("_pingPacketsInitial",
				kInitialPacketCount);
		int packetRetry = getSettingAsLong("_pingPacketsRetry",
				kRetryPacketCount);
		int ai[] = Platform.ping(host, timeout, initialPacketCount, size, this);
		if (ai[1] != initialPacketCount && packetRetry != 0) {
			initialPacketCount = packetRetry;
			ai = Platform.ping(host, timeout, initialPacketCount, size, this);
		}
		int packetReceived = ai[1];
		int j1 = ai[0];
		currentStatus = "ConfigPingMonitor analyzing results...";
		if (stillActive()) {
			synchronized (this) {
				if (flag == false) {
					packetReceived = -1;
				}
				if (packetReceived > 0) {
					setProperty(pStatus, "ok");
					float f = (float) j1 / (float) packetReceived;
					if (f < 10F
							&& getSetting("_singleDigitMillis").length() <= 0) {
						f = 10F;
					}
					String s1;
					if (getSetting("_singleDigitMillis").length() <= 0) {
						s1 = TextUtils.floatToString(f / 1000F, 2) + " sec";
					} else {
						s1 = TextUtils.floatToString(f / 1000F, 3) + " sec";
					}
					if (packetReceived != initialPacketCount) {
						int k1 = initialPacketCount - packetReceived;
						s1 = k1 + " out of " + initialPacketCount
								+ " missing, " + s1;
					}
					setProperty(pPacketsReceived, packetReceived);
					setProperty(
							pPercentGood,
							TextUtils
									.floatToString(
											((float) packetReceived / (float) initialPacketCount) * 100F,
											0));
					setProperty(
							pPercentFailed,
							TextUtils
									.floatToString(
											(((float) initialPacketCount - (float) packetReceived) / (float) initialPacketCount) * 100F,
											0));
					setProperty(pRoundTripTime, f);
					setProperty(pMeasurement,
							getMeasurement(pRoundTripTime, 200L));
					setProperty(pStateString, s1);
				} else {
					setProperty(pPacketsReceived, 0);
					setProperty(pPercentGood, 0);
					setProperty(pPercentFailed, 0);
					setProperty(pMeasurement, 0);
					setProperty(pRoundTripTime, "n/a");
					setProperty(pStatus, "failed");
					if (j1 == Platform.PING_COMMAND_FAILED) {
						setProperty(pNoData, "n/a");
						setProperty(pStateString,
								"Data Unavailable command failed to execute");
					} else {
						setProperty(pStateString, "failed");
					}
				}
			}
		}
		return true;
	}

	public Array getLogProperties() {
		Array array = super.getLogProperties();
		array.add(pStatus);
		array.add(pRoundTripTime);
		array.add(pPercentGood);
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
		pHost.setDisplayText(
				"Host IP",
				"the IP address or host name that will be pinged (examples: 206.168.191.21 or demo."
						+ Platform.exampleDomain + ")");
		pHost.setParameterOptions(true, 2, false);

		pPwd = new StringProperty("_pPwd", "", "pPwd");
		pPwd.setDisplayText("Password", "the User login server password"
				+ Platform.exampleDomain + ")");

		pPwd.setParameterOptions(true, 3, false);

		pSuperName = new StringProperty("_pSuperName", "", "pSuperName");
		pSuperName.setDisplayText("Super Name",
				"the User login server super user name"
						+ Platform.exampleDomain + ")");

		pSuperName.setParameterOptions(true, 4, false);

		pSuperPwd = new StringProperty("_pSuperPwd", "", "pSuperPwd");
		pSuperPwd.setDisplayText("Super Password",
				"the User login server super user password"
						+ Platform.exampleDomain + ")");

		pSuperPwd.setParameterOptions(true, 5, false);

		pCommand = new StringProperty("_pCommand", "", "pSuperPwd");
		pCommand.setDisplayText("Super Password", "the open configfile command"
				+ Platform.exampleDomain + ")");

		pCommand.setParameterOptions(true, 6, false);

		pConfigName = new StringProperty("_configname", "", "config name");
		pConfigName.setDisplayText("Config Name",
				"The Users download configuration files full path  "
						+ Platform.exampleDomain + ")");

		pConfigName.setParameterOptions(true, 7, false);

		pTimeout = new NumericProperty("_timeout", "5000", "milliseconds");
		pTimeout.setDisplayText("Timeout",
				"the time out per packet, in milliseconds, to wait for ping replies\n");
		pTimeout.setParameterOptions(true, 8, true);
		pSize = new NumericProperty("_packetSize", "32", "bytes");
		pSize.setDisplayText("Size", "the size, in bytes, of the ping message");
		pSize.setParameterOptions(true, 9, true);
		pRoundTripTime = new NumericProperty("roundTripTime", "0",
				"milliseconds");
		pRoundTripTime.setLabel("round trip time");
		pRoundTripTime.setStateOptions(1);
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
				pSuperName, pSuperPwd, pConfigName, pCommand, pTimeout, pSize,
				pStatus, pRoundTripTime, pPacketsSent, pPacketsReceived,
				pPercentGood, pPercentFailed };
		addProperties("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				astringproperty);
		addClassElement("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				Rule.stringToClassifier("percentGood == 0\terror", true));
		addClassElement("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				Rule.stringToClassifier("roundTripTime == n/a\terror"));
		addClassElement("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				Rule.stringToClassifier("percentGood < 100\twarning", true));
		addClassElement("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				Rule.stringToClassifier("percentGood == 100\tgood", true));
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				"description", "Pings a device on the network.");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				"help", "PingMon.htm");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				"title", "ConfigFileMonitor");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				"class", "ConfigPingMonitor");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				"target", "_hostname");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				"toolName", "config");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				"toolDescription", "Performs a roundtrip test on the network.");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				"topazName", "server");
		setClassProperty("COM.dragonflow.StandardMonitor.ConfigFileMonitor",
				"topazType", "System Resources");
	}
}
