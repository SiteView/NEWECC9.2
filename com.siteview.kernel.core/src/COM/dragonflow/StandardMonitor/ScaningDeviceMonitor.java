package COM.dragonflow.StandardMonitor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jgl.Array;
import jgl.HashMap;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import COM.dragonflow.HTTP.HTTPRequest;
import COM.dragonflow.Properties.PercentProperty;
import COM.dragonflow.Properties.StringProperty;
import COM.dragonflow.SiteView.AtomicMonitor;
import COM.dragonflow.SiteView.Rule;
import COM.dragonflow.SiteViewException.SiteViewException;
import COM.dragonflow.Utils.FileTools;
import COM.dragonflow.Utils.I18N;
import Siteview.Operators;
import Siteview.QueryInfoToGet;
import Siteview.SiteviewQuery;
import Siteview.SiteviewValue;
import Siteview.UpdateResult;
import Siteview.Api.BusinessObject;
import Siteview.Api.ISiteviewApi;
import Siteview.Api.SiteviewApi;

import com.siteview.assets.Autoscaningdevice;
import com.siteview.assets.ScanDeviceHandler;


public class ScaningDeviceMonitor extends AtomicMonitor {
	
	private ISiteviewApi currentAPI;
	
	static StringProperty RecId;
	static StringProperty assetstype;
	static StringProperty common;
	static StringProperty IPrange;
	static StringProperty pcIPrange;
	static StringProperty scancommand;
	static StringProperty scannumber;
	static StringProperty scantime;
	static StringProperty pStatus;

	// ɨ�跽ʽ:scantype,ɨ��ʱ��:scandatetime,ɨ�����豸��:totaldevice,�����豸��:newdevice,����豸��:changedevice,�����豸��Ϣ:newinfo,����豸��Ϣ:changeinfo
	static StringProperty scantype;
	static StringProperty scandatetime;
	static StringProperty totaldevice;
	static StringProperty newdevice;
	static StringProperty changedevice;
	static StringProperty newinfo;
	static StringProperty changeinfo;

	public ScaningDeviceMonitor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean update() throws SiteViewException {
		// TODO Auto-generated method stub

		System.out.println("ִ��ɨ�迪ʼ..." + getProperty(RecId) + "~~~"
				+ getProperty(assetstype) + "~~~" + getProperty(common) + "~~~"
				+ getProperty(IPrange) + "~~~" + getProperty(pcIPrange) + "~~~"
				+ getProperty(scancommand) + "~~~" + getProperty(scannumber)
				+ "~~~" + getProperty(scantime) + "~~~");

		synchronized (this) {
			
			if("ȫ���˿�ɨ��".equals(getProperty(assetstype))&&getProperty(IPrange).length()>0){
				
				try {
					List<String> listIp=new ArrayList<String>();
					List<String> listpcIp=new ArrayList<String>();
					List<String> listcommand=new ArrayList<String>();
					//IP��Χ
					String[] tableip=getProperty(IPrange).split(";");
					if(tableip!=null&&tableip.length>0){
						for(String str:tableip){
							String ips=str.replace("[", "");
							ips=ips.replace("]","");
							String[] taip=ips.split(",");
							if(taip.length>=2){
								listIp.add(taip[0]+"-"+taip[1]);
							}
						}
					}
					//�ų�IP��Χ
					String[] tablepcip=getProperty(pcIPrange).split(";");
					if(tablepcip!=null&&tablepcip.length>0){
						for(String str:tablepcip){
							String ips=str.replace("[", "");
							ips=ips.replace("]","");
							if(ips.length()>0){
								String[] tapcip=ips.split("~");
								listpcIp.add(tapcip[0]+"-"+tapcip[1]);
							}
						}
					}
					
					//����
					String AllScancommand=getProperty(scancommand);
					String[] valueList=AllScancommand.trim().split("]");
			    	if(valueList!=null){
				    	for(String str:valueList){
				    		listcommand.add(str.replace("[", ""));
				    	}
			    	}
					
			    	 //���API
			    	if(currentAPI==null){
			    		
			    		currentAPI=FileTools.getApi();
						
			    	}
			    	
					//ִ��ɨ�� ����
			    	ScanDeviceHandler scaning=new ScanDeviceHandler(currentAPI,listIp,listpcIp,listcommand);
			    	scaning.doScanDevice();
			    	
					setProperty(pStatus, "�ɹ�");
					setProperty(pMeasurement,"try");
					setProperty(scantype, getProperty(assetstype) );
					setProperty(scandatetime,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					setProperty(totaldevice,scaning.getTotalDeviceNum());
					setProperty(newdevice, scaning.getNewDeviceNum());
					setProperty(changedevice, scaning.getUpdateDeviceNum());
					setProperty(newinfo, scaning.getNewDeviceInfo());
					setProperty(changeinfo,scaning.getUpdateDeviceInfo());
					
					
				} catch (Exception e) {
					setProperty(pStatus, "ʧ��");
					setProperty(pMeasurement,"try");
					e.printStackTrace();
				}
		    	
			}else{
				Autoscaningdevice scaning=new Autoscaningdevice();
				setProperty(pStatus, "�ɹ�");
				setProperty(pMeasurement,"try");
				setProperty(scantype, getProperty(assetstype) );
				setProperty(scandatetime,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				setProperty(totaldevice,scaning.getTotal() );
				setProperty(newdevice, scaning.getNewdevice());
				setProperty(changedevice, scaning.getChangedevice());
				setProperty(newinfo, scaning.getNewinfo());
				setProperty(changeinfo, scaning.getChangeinfo());
			}
//			setProperty(pStatus, "�ɹ�");
//			setProperty(pMeasurement,"try");
//			setProperty(scantype, getProperty(assetstype) );
//			setProperty(scandatetime,new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
//			setProperty(totaldevice,1 );
//			setProperty(newdevice,1);
//			setProperty(changedevice, 1);
//			setProperty(newinfo, 1);
//			setProperty(changeinfo, 1);
//			getAllradwin(getProperty(RecId));
		}
//		
	
		
	
		//
		return true;
	}

	public Array getLogProperties() {
		Array array = super.getLogProperties();
		array.add(pStatus);
		array.add(scantype);
		array.add(scandatetime);
		array.add(totaldevice);
		array.add(newdevice);
		array.add(changedevice);
		array.add(newinfo);
		array.add(changeinfo);
		return array;
	}

	public String getHostname() {
		return getProperty(getProperty(assetstype));
	}

	public String getTestURL() {
		return "/SiteView/cgi/go.exe/SiteView?page=ping&host="
				+ getProperty(assetstype)
				+ "&group="
				+ HTTPRequest.encodeString(I18N
						.toDefaultEncoding(getProperty(pGroupID)));
	}

	public String verify(StringProperty stringproperty, String s,
			HTTPRequest httprequest, HashMap hashmap) {
		if (stringproperty == pStatus) {
			if (s.length() == 0) {
				hashmap.put(stringproperty, stringproperty.getLabel()
						+ " failing");
			} else if (s.equals("ok")) {
				hashmap.put(stringproperty, "ok");
			} else {
				hashmap.put(stringproperty, "no");
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
						+ diagnosticTraceRoute(getProperty(pStatus)) + "";
			}
		} else {
			return super.getProperty(stringproperty);
		}
	}

	
	

	public BusinessObject getAllradwin(String RecId) {
		
		 //���API
    	if(currentAPI==null){
    		currentAPI=FileTools.getApi();
			
    	}
		
		// ȡ���е�RADWIN �豸
		SiteviewQuery SiteViewQuery = new SiteviewQuery();
		SiteViewQuery.AddBusObQuery("Ecc.ScaningDevice", QueryInfoToGet.All);
		SiteViewQuery.set_BusObSearchCriteria(SiteViewQuery
				.get_CriteriaBuilder().FieldAndValueExpression("RecId",
						Operators.Equals, RecId));
		ICollection radwinico = currentAPI.get_BusObService()
				.get_SimpleQueryResolver()
				.ResolveQueryToBusObList(SiteViewQuery);
		System.out.println("ScaningDevice ���� ~~~~~~~   " + radwinico.get_Count());

		if (radwinico != null) {
			IEnumerator radwinit = radwinico.GetEnumerator();
			while (radwinit.MoveNext()) {
				BusinessObject ScaningDevice = (BusinessObject) radwinit
						.get_Current();
				if (ScaningDevice != null) {
					ScaningDevice.GetField("lastscandatetime").SetValue(new SiteviewValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		    		UpdateResult result=ScaningDevice.SaveObject(currentAPI, false, true);
		    		if(result.get_SaveSuccess()){
		    			System.out.println("�ɹ�");
		    		}else {
		    			System.out.println("�޸�ʧ��~~~"+result.get_ErrorMessages());
		    		}
					return ScaningDevice;
				}

			}
		}
		return null;
	}
	
	
	
	
	/*
	 * ��������
	 */
	static {
		System.out.println("�������ݿ�ʼ...");

		String classname = "COM.dragonflow.StandardMonitor.ScaningDeviceMonitor";

		RecId = new StringProperty("_scanID");
		assetstype = new StringProperty("_assetstype");
		common = new StringProperty("_common");
		IPrange = new StringProperty("_IPrange");
		pcIPrange = new StringProperty("_pcIPrange");
		scancommand = new StringProperty("_scancommand");
		scannumber = new StringProperty("_scannumber");

		scantime = new StringProperty("_scantime");
		pStatus = new PercentProperty("status");
		pStatus.setLabel("% packets good");
		pStatus.setStateOptions(1);

		scantype = new StringProperty("scantype");
		scandatetime = new StringProperty("scandatetime");
		totaldevice = new StringProperty("totaldevice");
		newdevice = new StringProperty("newdevice");
		changedevice = new StringProperty("changedevice");
		newinfo = new StringProperty("newinfo");
		changeinfo = new StringProperty("changeinfo");

		StringProperty astringproperty[] = { RecId, assetstype, common,
				IPrange, pcIPrange, scancommand, scannumber, scantime, pStatus };

		addProperties(classname, astringproperty);

		addClassElement(classname,
				Rule.stringToClassifier("status == '�ɹ�'\tgood"));
		addClassElement(classname,
				Rule.stringToClassifier("status == '�澯'\twarning"));
		addClassElement(classname,
				Rule.stringToClassifier("status == 'ʧ��'\terror"));

		setClassProperty(classname, "description",
				"Pings a device on the network.");
		// setClassProperty(classname, "help", "PingMon.htm");
		setClassProperty(classname, "title", "ScaningDevice");
		setClassProperty(classname, "class", "ScaningDeviceMonitor");
		setClassProperty(classname, "target", "_hostname");
		setClassProperty(classname, "toolName", "ScaningDevice");
		setClassProperty(classname, "toolDescription",
				"Performs a roundtrip test on the network.");
		setClassProperty(classname, "topazName", "ScaningDevice");
		setClassProperty(classname, "topazType", "System Resources");

	}
}
