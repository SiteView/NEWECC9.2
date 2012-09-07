package SiteView.ecc.Modle;

import java.util.ArrayList;
import java.util.List;

public class AlarmModle {
	private String name;
	private List list;
	public AlarmModle(){
		this.name="±¨¾¯";
		list=new ArrayList();
		list.add(new AlarmRuleModle());
		list.add(new AlarmTacticsModle());
		list.add(new AlarmLogModle());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
}
