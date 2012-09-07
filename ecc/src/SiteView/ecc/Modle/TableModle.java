package SiteView.ecc.Modle;

public class TableModle{
public String DutyTableName;
public String DutyTableType;
public String DutyTableDec;


public TableModle(){
	
}


public String getDutyTableName() {
	return DutyTableName;
}
public void setDutyTableName(String dutyTableName) {
	DutyTableName = dutyTableName;
}
public String getDutyTableType() {
	return DutyTableType;
}
public void setDutyTableType(String dutyTableType) {
	DutyTableType = dutyTableType;
}
public String getDutyTableDec() {
	return DutyTableDec;
}
public void setDutyTableDec(String dutyTableDec) {
	DutyTableDec = dutyTableDec;
}
public TableModle(String dutyTableName, String dutyTableType,
		String dutyTableDetail) {
	super();
	DutyTableName = dutyTableName;
	DutyTableType = dutyTableType;
	DutyTableDec = dutyTableDetail;
	
	
}

}
