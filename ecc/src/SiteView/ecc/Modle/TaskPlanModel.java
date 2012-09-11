package SiteView.ecc.Modle;

import java.util.ArrayList;
import java.util.List;

public class TaskPlanModel {
	public String name="任务计划";
	
	public List list=new ArrayList();
	
	public TaskPlanModel(List<Object> list){
		super();
		this.list=list;
		
	}
	
	public TaskPlanModel(){
		list.add(new AbsoluteTimeModel());
		list.add(new TimeQuantumModel());
		list.add(new RelativeTimeModel());
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
