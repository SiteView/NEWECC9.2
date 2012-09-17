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
					String receiveAlarmpPhone=bo.GetField("ReceiveAlarmpPhone").get_NativeValue().toString();//得到接收告警手机号码字段对应的值
					String receiveAlarmEmail=bo.GetField("ReceiveAlarmEmail").get_NativeValue().toString();//得到接收告警邮箱字段对应的值
					String DateTime=bo.GetField("Week").get_NativeValue().toString();//得到日期字段对应的值
					String startTime=bo.GetField("StartTime").get_NativeValue().toString();//得到开始日期字段对应的值
					String endTime=bo.GetField("EndTime").get_NativeValue().toString();//得到结束日期对应的值
					
					
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
