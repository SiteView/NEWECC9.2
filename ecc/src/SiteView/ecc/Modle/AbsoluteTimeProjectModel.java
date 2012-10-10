package SiteView.ecc.Modle;

import Siteview.Api.BusinessObject;

public class AbsoluteTimeProjectModel {
public String TaskName;
public String Instruction;
public String Status;
public String Model;
public String StatrtTime;
public String EndTime;
public BusinessObject bo;
public AbsoluteTimeProjectModel() {
	super();
}

public AbsoluteTimeProjectModel(BusinessObject bo) {
	super();
	this.bo = bo;
}

public AbsoluteTimeProjectModel(String taskName, String instruction,
		String status, String model, String statrtTime, String endTime,
		BusinessObject bo) {
	super();
	TaskName = taskName;
	Instruction = instruction;
	Status = status;
	Model = model;
	StatrtTime = statrtTime;
	EndTime = endTime;
	this.bo = bo;
}

public String getTaskName() {
	return TaskName;
}

public void setTaskName(String taskName) {
	TaskName = taskName;
}

public String getInstruction() {
	return Instruction;
}

public void setInstruction(String instruction) {
	Instruction = instruction;
}

public String getStatus() {
	return Status;
}

public void setStatus(String status) {
	Status = status;
}

public String getModel() {
	return Model;
}

public void setModel(String model) {
	Model = model;
}

public String getStatrtTime() {
	return StatrtTime;
}

public void setStatrtTime(String statrtTime) {
	StatrtTime = statrtTime;
}

public String getEndTime() {
	return EndTime;
}

public void setEndTime(String endTime) {
	EndTime = endTime;
}

public BusinessObject getBo() {
	return bo;
}

public void setBo(BusinessObject bo) {
	this.bo = bo;
}

}
