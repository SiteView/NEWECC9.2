package SiteView.ecc.data;

import java.util.List;
import system.DateTime;
import SiteView.ecc.Modle.DetailModel;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class DutyDetailInfor {
	public static List<DetailModel> list=null;
	public static  List<DetailModel> getDutyDetailInfor(){
		BusinessObject bo = ConnectionBroker.get_SiteviewApi()//得到数据库表
				.get_BusObService().Create("DutyDetail");
		if(bo!=null){
		String receiveAlarmpPhone=bo.GetField("ReceiveAlarmpPhone").get_NativeValue().toString();//得到接收告警手机号码字段对应的值
		String receiveAlarmEmail=bo.GetField("ReceiveAlarmEmail").get_NativeValue().toString();//得到接收告警邮箱字段对应的值
		DateTime createdDateTime=bo.GetField("CreatedDateTime").get_Value().ToDateTime();//得到日期字段对应的值
		DateTime startTime=bo.GetField("StartTime").get_Value().ToDateTime();//得到开始日期字段对应的值
		DateTime endTime=bo.GetField("EndTime").get_Value().ToDateTime();//得到结束日期对应的值
		
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
