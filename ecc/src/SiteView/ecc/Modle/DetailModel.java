package SiteView.ecc.Modle;

import system.DateTime;

public class DetailModel {
	public String ReceiveAlarmpPhone;
	public String ReceiveAlarmEmail;
	public DateTime CreatedDateTime;
	public DateTime StartTime;
	public DateTime EndTime;
	public DetailModel(){
		
	}
	public DetailModel(String receiveAlarmpPhone,String receiveAlarmEmail,
		DateTime createdDateTime,DateTime startTime,DateTime endTime){
		super();

		ReceiveAlarmpPhone=receiveAlarmpPhone;
		ReceiveAlarmEmail=receiveAlarmEmail;
		CreatedDateTime=createdDateTime;
		StartTime=startTime;
		EndTime=endTime;
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

	public DateTime getCreatedDateTime() {
		return CreatedDateTime;
	}

	public void setCreatedDateTime(DateTime createdDateTime) {
		CreatedDateTime = createdDateTime;
	}

	public DateTime getStartTime() {
		return StartTime;
	}

	public void setStartTime(DateTime startTime) {
		StartTime = startTime;
	}

	public DateTime getEndTime() {
		return EndTime;
	}

	public void setEndTime(DateTime endTime) {
		EndTime = endTime;
	}

}
