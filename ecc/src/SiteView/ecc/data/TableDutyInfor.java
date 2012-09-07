package SiteView.ecc.data;

import java.util.ArrayList;
import java.util.List;
import SiteView.ecc.Modle.TableModle;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class TableDutyInfor {
	public static List<TableModle> list=null;
	public static  List<TableModle> getTableDutyInfor(){
		list=new ArrayList<TableModle>();
			BusinessObject bo = ConnectionBroker.get_SiteviewApi()//�õ����ݿ��
						.get_BusObService().Create("EccDuty");
			if(bo!=null){
				String dutyTableName = bo.GetField("DutyTableName").get_NativeValue().toString();//�õ�ֵ������ֶζ�Ӧ��ֵ
				 String dutyTableDec=bo.GetField("DutyTableDec").get_NativeValue().toString();//�õ������ֶζ�Ӧ��ֵ
				 String dutyTableType=bo.GetField("DutyTableType").get_NativeValue().toString();//�õ������ֶζ�Ӧ��ֵ
					TableModle table=new TableModle();
					table.setDutyTableName(dutyTableName);
					table.setDutyTableType(dutyTableType);
					table.setDutyTableDec(dutyTableDec);
					list.add(table);
			
			}
			return list;
		
	}
}
