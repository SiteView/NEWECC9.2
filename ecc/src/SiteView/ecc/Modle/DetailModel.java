package SiteView.ecc.Modle;

import Siteview.Api.BusinessObject;
import system.DateTime;

public class DetailModel {
	public String ReceiveAlarmpPhone;
	public String ReceiveAlarmEmail;
	public String CreatedDateTime;
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
			String createdDateTime, String startTime, String endTime,
			BusinessObject bo) {
		super();
		ReceiveAlarmpPhone = receiveAlarmpPhone;
		ReceiveAlarmEmail = receiveAlarmEmail;
		CreatedDateTime = createdDateTime;
		StartTime = startTime;
		EndTime = endTime;
		this.bo = bo;
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
	public String getCreatedDateTime() {
		return CreatedDateTime;
	}
	public void setCreatedDateTime(String createdDateTime) {
		CreatedDateTime = createdDateTime;
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
