package SiteView.ecc.Modle;

import java.util.ArrayList;
import java.util.List;

public class StatementsModle {
	public String name="����";
	public List list=new ArrayList();
	public StatementsModle(){
		list.add(new TrendReportModle("���Ʊ���"));
		list.add(new TrendReportModle("�Աȱ���"));
		list.add(new TrendReportModle("ʱ�ζԱȱ���"));
		list.add(new TrendReportModle("״̬ͳ�Ʊ���"));
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
