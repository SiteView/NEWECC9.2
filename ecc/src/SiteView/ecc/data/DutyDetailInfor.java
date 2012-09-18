package SiteView.ecc.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import system.DateTime;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.Modle.DetailModel;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.dialog.AddDutyDetail;
import SiteView.ecc.editors.TableDuty;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class DutyDetailInfor {
	public static List<DetailModel> list=null;
	public DutyDetailInfor(){
	}
	public static  List<DetailModel> getDutyDetailInfor(Map<String,BusinessObject> sub,String strID){
		list=new ArrayList<DetailModel>();
		            System.out.println("strID:"+strID);
		            BusinessObject bb= sub.get(strID);
		            System.out.println("bb:"+bb.get_RecId());
					String receiveAlarmpPhone=bb.GetField("ReceiveAlarmpPhone").get_NativeValue().toString();//�õ����ո澯�ֻ������ֶζ�Ӧ��ֵ
					System.out.println("receiveAlarmpPhone:"+receiveAlarmpPhone);
					String receiveAlarmEmail=bb.GetField("ReceiveAlarmEmail").get_NativeValue().toString();//�õ����ո澯�����ֶζ�Ӧ��ֵ	
					System.out.println("receiveAlarmEmail:"+receiveAlarmEmail);
					String DateTime=bb.GetField("Week").get_NativeValue().toString();//�õ������ֶζ�Ӧ��ֵ		
					System.out.println("DateTime:"+DateTime);
					String startTime=bb.GetField("StartTime").get_NativeValue().toString();//�õ���ʼ�����ֶζ�Ӧ��ֵ
					System.out.println("startTime:"+startTime);
					String endTime=bb.GetField("EndTime").get_NativeValue().toString();//�õ��������ڶ�Ӧ��ֵ
					System.out.println("endTime:"+endTime);
					
					DetailModel detail=new DetailModel(bb);
					detail.setReceiveAlarmpPhone(receiveAlarmpPhone);
					detail.setReceiveAlarmEmail(receiveAlarmEmail);
					detail.setWeek(DateTime);
					detail.setStartTime(startTime);
					detail.setEndTime(endTime);
					list.add(detail);
		return list;
		
	}
	
}
