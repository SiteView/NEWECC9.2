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
	public static  List<DetailModel> getDutyDetailweekInfor(){
		list=new ArrayList<DetailModel>();
		ICollection icoll=null;
		IEnumerator ienum=null;
		icoll=FileTools.getBussCollection("DutyDetail");
		ienum=icoll.GetEnumerator();
		if(ienum!=null){
			while(ienum.MoveNext()){
				BusinessObject bo=(BusinessObject) ienum.get_Current();
				String receiveAlarmpPhone= bo.GetField("ReceiveAlarmpPhone").get_NativeValue().toString();//得到接收告警手机号码字段对应的值
				String receiveAlarmEmail=bo.GetField("ReceiveAlarmEmail").get_NativeValue().toString();//得到接收告警邮箱字段对应的值	
				String DateTime=bo.GetField("Week").get_NativeValue().toString();//得到日期字段对应的值		
				String startTime=bo.GetField("StartTime").get_NativeValue().toString();//得到开始日期字段对应的值
				String endTime=bo.GetField("EndTime").get_NativeValue().toString();//得到结束日期对应的值
				
				if(DateTime.contentEquals("星期一")||DateTime.contentEquals("星期二")||DateTime.contentEquals("星期三")||DateTime.contentEquals("星期四")||DateTime.contentEquals("星期五")
						||DateTime.contentEquals("星期六")||DateTime.contentEquals("星期日")){
					DetailModel detail=new DetailModel(bo);
					detail.setReceiveAlarmpPhone(receiveAlarmpPhone);
					detail.setReceiveAlarmEmail(receiveAlarmEmail);
					detail.setWeek(DateTime);
					detail.setStartTime(startTime);
					detail.setEndTime(endTime);
					list.add(detail);
				}
			}
		}
		return list;
	}
	public static  List<DetailModel> getDutyDetaildayInfor(){
		list=new ArrayList<DetailModel>();
		ICollection icoll=null;
		IEnumerator ienum=null;
		icoll=FileTools.getBussCollection("DutyDetail");
		ienum=icoll.GetEnumerator();
		if(ienum!=null){
			while(ienum.MoveNext()){
				BusinessObject bo=(BusinessObject) ienum.get_Current();
				String receiveAlarmpPhone=bo.GetField("ReceiveAlarmpPhone").get_NativeValue().toString();//得到接收告警手机号码字段对应的值
				String receiveAlarmEmail=bo.GetField("ReceiveAlarmEmail").get_NativeValue().toString();//得到接收告警邮箱字段对应的值	
				String DateTime=bo.GetField("Week").get_NativeValue().toString();//得到日期字段对应的值		
				String startTime=bo.GetField("StartTime").get_NativeValue().toString();//得到开始日期字段对应的值
				String endTime=bo.GetField("EndTime").get_NativeValue().toString();//得到结束日期对应的值
				
				if("".equals(DateTime)){
					DetailModel detail=new DetailModel(bo);
					detail.setReceiveAlarmpPhone(receiveAlarmpPhone);
					detail.setReceiveAlarmEmail(receiveAlarmEmail);
					detail.setWeek(DateTime);
					detail.setStartTime(startTime);
					detail.setEndTime(endTime);
					list.add(detail);
				}
			}
		}
		return list;
	}
	public static  List<DetailModel> getDutyDetailmonthInfor(){
		list=new ArrayList<DetailModel>();
		ICollection icoll=null;
		IEnumerator ienum=null;
		icoll=FileTools.getBussCollection("DutyDetail");
		ienum=icoll.GetEnumerator();
		if(ienum!=null){
			while(ienum.MoveNext()){
				BusinessObject bo=(BusinessObject) ienum.get_Current();
				String receiveAlarmpPhone=bo.GetField("ReceiveAlarmpPhone").get_NativeValue().toString();//得到接收告警手机号码字段对应的值
				String receiveAlarmEmail=bo.GetField("ReceiveAlarmEmail").get_NativeValue().toString();//得到接收告警邮箱字段对应的值	
				String DateTime=bo.GetField("Week").get_NativeValue().toString();//得到日期字段对应的值		
				String startTime=bo.GetField("StartTime").get_NativeValue().toString();//得到开始日期字段对应的值
				String endTime=bo.GetField("EndTime").get_NativeValue().toString();//得到结束日期对应的值
				
				for(int i=1;i<=31;i++){
					String str=""+i;
					if(str.equals(DateTime)){
						DetailModel detail=new DetailModel(bo);
						detail.setReceiveAlarmpPhone(receiveAlarmpPhone);
						detail.setReceiveAlarmEmail(receiveAlarmEmail);
						detail.setWeek(DateTime);
						detail.setStartTime(startTime);
						detail.setEndTime(endTime);
						list.add(detail);
					}
				}
				
			}
		}
		return list;
	}
}
