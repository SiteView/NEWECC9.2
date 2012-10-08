package SiteView.ecc.data;

import java.util.ArrayList;
import java.util.List;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.Modle.AbsoluteTimeProjectModel;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;

public class AbsoluteTimeInfor {
	public static List<AbsoluteTimeProjectModel> list=null;
	public static  List<AbsoluteTimeProjectModel> getTableDutyInfor(){
		list=new ArrayList<AbsoluteTimeProjectModel>();
		ICollection icoll=null;
		IEnumerator ienum=null;
		icoll=FileTools.getBussCollection("EccDutyTable");
		ienum=icoll.GetEnumerator();
		if(ienum!=null){
			while(ienum.MoveNext()){
				BusinessObject bo=(BusinessObject) ienum.get_Current();
				
//				String dutyTableName = bo.GetField("DutyTableName").get_NativeValue().toString();//�õ�ֵ������ֶζ�Ӧ��ֵ
//				 System.out.println("dutyTableName"+dutyTableName);
//				 String dutyTableDec=bo.GetField("DutyTableDec").get_NativeValue().toString();//�õ������ֶζ�Ӧ��ֵ
//				 System.out.println("DutyTableDec"+dutyTableDec);
//				 String dutyTableType=bo.GetField("DutyTableType").get_NativeValue().toString();//�õ������ֶζ�Ӧ��ֵ
//				 System.out.println("dutyTableType"+dutyTableType);
				
//				TableModle table=new TableModle(bo);
//				table.setDutyTableName(dutyTableName);
//				table.setDutyTableType(dutyTableType);
//				table.setDutyTableDec(dutyTableDec);
//				list.add(table);
			}
		}
		return list;
	}
}