package SiteView.ecc.Modle;

import Siteview.Api.BusinessObject;
import system.DateTime;

public class DetailModel {
	public String ReceiveAlarmpPhone;
	public String ReceiveAlarmEmail;
	public String Week;
	public String StartTime;
	public String EndTime;
	public BusinessObject bo;
	public BusinessObject getBo() {
		return bo;
	}
	public void setBo(BusinessObject bo) {
		this.bo = bo;
	}
	public DetailModel(){
		
	}
	public DetailModel(BusinessObject bo) {
		super();
		this.bo = bo;
	}
	
	
	public DetailModel(String receiveAlarmpPhone, String receiveAlarmEmail,
			String week, String startTime, String endTime,
		    BusinessObject bo) {
		super();
		ReceiveAlarmpPhone = receiveAlarmpPhone;
		ReceiveAlarmEmail = receiveAlarmEmail;
		Week = week;
		StartTime = startTime;
		EndTime = endTime;
		this.bo = bo;
	}
	public String getWeek() {
		return Week;
	}
	public void setWeek(String week) {
		Week = week;
	}
	public String getReceiveAlarmpPhone() {
		return ReceiveAlarmpPhone;
	}

	public void setReceiveAlarmpPhone(String receiveAlarmpPhone) {
		ReceiveAlarmpPhone = receiveAlarmpPhone;
	}

	public String getReceiveAlarmEmail() {
		return ReceiveAlarmEmail;
	}

	public void setReceiveAlarmEmail(String receiveAlarmEmail) {
		ReceiveAlarmEmail = receiveAlarmEmail;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

}
