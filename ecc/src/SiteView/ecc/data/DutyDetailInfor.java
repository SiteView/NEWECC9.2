package SiteView.ecc.data;

import java.util.ArrayList;
import java.util.List;
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
	//public static BusinessObject bo;
	public DutyDetailInfor(){
	}
	public static  List<DetailModel> getDutyDetailInfor(BusinessObject bo){
//		ICollection icoll=null;
//		IEnumerator ienum=null;
//		list=new ArrayList<DetailModel>();
//		icoll=FileTools.getBussCollection("DutyDetail");
//		ienum=icoll.GetEnumerator();
//		if(ienum!=null){
//			while(ienum.MoveNext()){
//				    BusinessObject bo=(BusinessObject) ienum.get_Current();
					String receiveAlarmpPhone=bo.GetField("ReceiveAlarmpPhone").get_NativeValue().toString();//�õ����ո澯�ֻ������ֶζ�Ӧ��ֵ
					String receiveAlarmEmail=bo.GetField("ReceiveAlarmEmail").get_NativeValue().toString();//�õ����ո澯�����ֶζ�Ӧ��ֵ
					String DateTime=bo.GetField("Week").get_NativeValue().toString();//�õ������ֶζ�Ӧ��ֵ
					String startTime=bo.GetField("StartTime").get_NativeValue().toString();//�õ���ʼ�����ֶζ�Ӧ��ֵ
					String endTime=bo.GetField("EndTime").get_NativeValue().toString();//�õ��������ڶ�Ӧ��ֵ
					
					
					DetailModel detail=new DetailModel(bo);
					detail.setReceiveAlarmpPhone(receiveAlarmpPhone);
					detail.setReceiveAlarmEmail(receiveAlarmEmail);
					detail.setWeek(DateTime);
					detail.setStartTime(startTime);
					detail.setEndTime(endTime);
					list.add(detail);
//			}
//		}
		
		return list;
		
	}
	
}
