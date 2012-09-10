package SiteView.ecc.data;

import java.util.ArrayList;
import java.util.List;
import system.DateTime;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.Modle.DetailModel;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class DutyDetailInfor {
	public static List<DetailModel> list=null;
	public static  List<DetailModel> getDutyDetailInfor(){
		ICollection icoll=null;
		IEnumerator ienum=null;
		list=new ArrayList<DetailModel>();
		icoll=FileTools.getBussCollection("DutyDetail");
		ienum=icoll.GetEnumerator();
		if(ienum!=null){
			while(ienum.MoveNext()){
				BusinessObject bo=(BusinessObject) ienum.get_Current();
				String receiveAlarmpPhone=bo.GetField("ReceiveAlarmpPhone").get_NativeValue().toString();//�õ����ո澯�ֻ������ֶζ�Ӧ��ֵ
				System.out.println("receiveAlarmpPhone:"+receiveAlarmpPhone);
				String receiveAlarmEmail=bo.GetField("ReceiveAlarmEmail").get_NativeValue().toString();//�õ����ո澯�����ֶζ�Ӧ��ֵ
				System.out.println("receiveAlarmEmail:"+receiveAlarmEmail);
				String createdDateTime=bo.GetField("CreatedDateTime").get_NativeValue().toString();//�õ������ֶζ�Ӧ��ֵ
				System.out.println("createdDateTime:"+createdDateTime);
				String startTime=bo.GetField("StartTime").get_NativeValue().toString();//�õ���ʼ�����ֶζ�Ӧ��ֵ
				System.out.println("startTime:"+startTime);
				String endTime=bo.GetField("EndTime").get_NativeValue().toString();//�õ��������ڶ�Ӧ��ֵ
				System.out.println("endTime:"+endTime);
				
				DetailModel detail=new DetailModel(bo);
				detail.setReceiveAlarmpPhone(receiveAlarmpPhone);
				detail.setReceiveAlarmEmail(receiveAlarmEmail);
				detail.setCreatedDateTime(createdDateTime);
				detail.setStartTime(startTime);
				detail.setEndTime(endTime);
				list.add(detail);
			}
		}
		
		return list;
		
	}
}
