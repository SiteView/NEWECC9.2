package SiteView.ecc.data;

import java.util.ArrayList;
import java.util.List;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;

public class TableDutyInfor {
	public static List<TableModle> list=null;
	public static  List<TableModle> getTableDutyInfor(){
		list=new ArrayList<TableModle>();
		ICollection icoll=null;
		IEnumerator ienum=null;
		icoll=FileTools.getBussCollection("EccDutyTable");
		ienum=icoll.GetEnumerator();
		if(ienum!=null){
			while(ienum.MoveNext()){
				BusinessObject bo=(BusinessObject) ienum.get_Current();
				 String dutyTableName = bo.GetField("DutyTableName").get_NativeValue().toString();//得到值班表名字段对应的值
				 String dutyTableDec=bo.GetField("DutyTableDec").get_NativeValue().toString();//得到描述字段对应的值
				 String dutyTableType=bo.GetField("DutyTableType").get_NativeValue().toString();//得到类型字段对应的值
				 TableModle table=new TableModle(bo);
				 table.setDutyTableName(dutyTableName);
				 table.setDutyTableType(dutyTableType);
				 table.setDutyTableDec(dutyTableDec);
				 list.add(table);
			}
		}
			        return list;
		
	}
}
