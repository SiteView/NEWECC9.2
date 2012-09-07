package SiteView.ecc.data;

import java.util.List;
import system.DateTime;
import SiteView.ecc.Modle.DetailModel;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class DutyDetailInfor {
	public static List<DetailModel> list=null;
	public static  List<DetailModel> getDutyDetailInfor(){
		BusinessObject bo = ConnectionBroker.get_SiteviewApi()//�õ����ݿ��
				.get_BusObService().Create("DutyDetail");
		if(bo!=null){
		String receiveAlarmpPhone=bo.GetField("ReceiveAlarmpPhone").get_NativeValue().toString();//�õ����ո澯�ֻ������ֶζ�Ӧ��ֵ
		String receiveAlarmEmail=bo.GetField("ReceiveAlarmEmail").get_NativeValue().toString();//�õ����ո澯�����ֶζ�Ӧ��ֵ
		DateTime createdDateTime=bo.GetField("CreatedDateTime").get_Value().ToDateTime();//�õ������ֶζ�Ӧ��ֵ
		DateTime startTime=bo.GetField("StartTime").get_Value().ToDateTime();//�õ���ʼ�����ֶζ�Ӧ��ֵ
		DateTime endTime=bo.GetField("EndTime").get_Value().ToDateTime();//�õ��������ڶ�Ӧ��ֵ
		
		DetailModel detail=new DetailModel();
		detail.setReceiveAlarmpPhone(receiveAlarmpPhone);
		detail.setReceiveAlarmEmail(receiveAlarmEmail);
		detail.setCreatedDateTime(createdDateTime);
		detail.setStartTime(startTime);
		detail.setEndTime(endTime);
		list.add(detail);
		}
		return list;
		
	}
}
