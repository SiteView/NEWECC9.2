package SiteView.ecc.Modle;

import java.util.ArrayList;
import java.util.List;

public class StatementsModle {
	public String name="报表";
	public List list=new ArrayList();
	public StatementsModle(){
		list.add(new TrendReportModle("趋势报告"));
		list.add(new TrendReportModle("对比报告"));
		list.add(new TrendReportModle("时段对比报告"));
		list.add(new TrendReportModle("状态统计报告"));
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
