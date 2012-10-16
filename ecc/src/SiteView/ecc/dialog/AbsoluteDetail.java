package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class AbsoluteDetail extends Dialog{
	private Table table;
    private String name;
    private BusinessObject bo;
    private String Status;
    public String date;
	public String permission;
	public String date_1;
	public String permission_1;
	public String date_2;
	public String permission_2;
	public String date_3;
	public String permission_3;
	public String date_4;
	public String permission_4;
	public String date_5;
	public String permission_5;
	public String date_6;
	public String permission_6;
	public String StatrtTime;
	public String time;
	public String time_1;
	public String time_2;
	public String time_3;
	public String time_4;
	public String time_5;
	public String time_6;
	public AbsoluteDetail(Shell parentShell,String name) {
		super(parentShell);
		this.name=name;
		
	}
	protected void configureShell(Shell newShell){
		newShell.setSize(300, 200);
		newShell.setLocation(350, 175);
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ICollection ico=FileTools.getBussCollection("TaskName",name, "EccTaskPlan");
		IEnumerator ien=ico.GetEnumerator();
		while(ien.MoveNext()){
			bo=(BusinessObject)ien.get_Current();
			if(bo!=null&&bo.GetField("Model").get_NativeValue().toString().equals("绝对时间任务计划")){
				Status=bo.GetField("Status").get_NativeValue()
						.toString();
				String []s1=Status.split(";");
                String []a1=s1[0].split(":");
                date=a1[0];//星期日
                permission=a1[1];
                String []a2=s1[1].split(":");
                date_1=a2[0];//星期一
                permission_1=a2[1];
                String []a3=s1[2].split(":");
                date_2=a3[0];//星期二
                permission_2=a3[1];
                String []a4=s1[3].split(":");
                date_3=a4[0];//星期三
                permission_3=a4[1];
                String []a5=s1[4].split(":");
                date_4=a5[0];//星期四
                permission_4=a5[1];
                String []a6=s1[5].split(":");
                date_5=a6[0];//星期五
                permission_5=a6[1];
                String []a7=s1[6].split(":");
                date_6=a7[0];//星期六
                permission_6=a7[1];
                
                StatrtTime=bo.GetField("StatrtTime").get_NativeValue()
        				.toString();
        				String []s2=StatrtTime.split(";");
        				String []b1=s2[0].split(",");
        				time=b1[1];
        				String []b2=s2[1].split(",");
        				time_1=b2[1];
        				String []b3=s2[2].split(",");
        				time_2=b3[1];
        				String []b4=s2[3].split(",");
        				time_3=b4[1];
        				String []b5=s2[4].split(",");
        				time_4=b5[1];
        				String []b6=s2[5].split(",");
        				time_5=b6[1];
        				String []b7=s2[6].split(",");
        				time_6=b7[1];
			}
		}
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("计划");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("状态");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("开始");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(0, "星期日");
		if(tableItem.getText(0).equals(date)&&permission.equals("允许")){
			tableItem.setText(1, "允许");
		}else if(tableItem.getText(0).equals(date)&&permission.equals("禁止")){
			tableItem.setText(1, "禁止");
		}
		if(tableItem.getText(0).equals(date)){
			tableItem.setText(2,time );
		}
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(0, "星期一");
		if(tableItem_1.getText(0).equals(date_1)&&permission_1.equals("允许")){
			tableItem_1.setText(1, "允许");
		}else if(tableItem_1.getText(0).equals(date_1)&&permission_1.equals("禁止")){
			tableItem_1.setText(1, "禁止");
		}
		if(tableItem_1.getText(0).equals(date_1)){
			tableItem_1.setText(2,time_1 );
		}
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(0, "星期二");
		tableItem_2.setText(1, permission_2);
		tableItem_2.setText(2,time_2 );
		
		
		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(0, "星期三");
		tableItem_3.setText(1, permission_3);
		tableItem_3.setText(2, time_3);
		
		TableItem tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setText(0, "星期四");
		tableItem_4.setText(1, permission_4);
		tableItem_4.setText(2, time_4);
		
		TableItem tableItem_5 = new TableItem(table, SWT.NONE);
		tableItem_5.setText(0, "星期五");
		if(tableItem_5.getText(0).equals(date_5)&&permission_5.equals("允许")){
			tableItem_5.setText(1, "允许");
		}else if(tableItem_5.getText(0).equals(date_5)&&permission_5.equals("禁止")){
			tableItem_5.setText(1, "禁止");
		}
		if(tableItem_5.getText(0).equals(date_5)){
			tableItem_5.setText(2,time_5 );
		}
		
		TableItem tableItem_6 = new TableItem(table, SWT.NONE);
		tableItem_6.setText(0, "星期六");
		if(tableItem_6.getText(0).equals(date_6)&&permission_6.equals("允许")){
			tableItem_6.setText(1, "允许");
		}else if(tableItem_6.getText(0).equals(date_6)&&permission_6.equals("禁止")){
			tableItem_6.setText(1, "禁止");
		}
		if(tableItem_6.getText(0).equals(date_6)){
			tableItem_6.setText(2,time_6 );
		}
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(EccTreeControl.color);
    }

}
