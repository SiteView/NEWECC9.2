package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class QuantumTimeEditor extends Dialog{
	private String title="编辑时间段任务计划";
	public DateTime startTime;
	public DateTime startTime_1;
	public DateTime startTime_2;
	public DateTime startTime_3;
	public DateTime startTime_4;
	public DateTime startTime_5;
	public DateTime startTime_6;
	public DateTime startTime_7;
	public DateTime startTime_8;
	public DateTime startTime_9;
	public DateTime startTime_10;
	public DateTime startTime_11;
	public DateTime startTime_12;
	public DateTime startTime_13;
	public String startTimeStr = "";	
	public String startTimeStr_1 = "";	
	public String startTimeStr_2 = "";	
	public String startTimeStr_3 = "";	
	public String startTimeStr_4 = "";	
	public String startTimeStr_5 = "";	
	public String startTimeStr_6 = "";	
	public String startTimeStr_7 = "";	
	public String startTimeStr_8 = "";	
	public String startTimeStr_9 = "";	
	public String startTimeStr_10 = "";	
	public String startTimeStr_11 = "";	
	public String startTimeStr_12 = "";	
	public String startTimeStr_13 = "";	
	private Text text_1;
	private Table table;
	private Text text_2;
	public Button btnCheckButton;
	public Button btnCheckButton_1;
	public Button btnCheckButton_2;
	public Button btnCheckButton_3;
	public Button btnCheckButton_4;
	public Button btnCheckButton_5;
	public Button btnCheckButton_6;
	public TableItem tableItem;
	public TableItem tableItem_1;
	public TableItem tableItem_2;
	public TableItem tableItem_3;
	public TableItem tableItem_4;
	public TableItem tableItem_5;
	public TableItem tableItem_6;
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
	private String name;
	private TableItem item;
	public BusinessObject bo;
	public String TaskName;
	public String Instruction;
	public String StatrtTime;
	public String EndTime;
	public String Status;
	public String time;
	public String time_1;
	public String time_2;
	public String time_3;
	public String time_4;
	public String time_5;
	public String time_6;
	public String time_7;
	public String time_8;
	public String time_9;
	public String time_10;
	public String time_11;
	public String time_12;
	public String time_13;
	public QuantumTimeEditor(Shell shell,String name,TableItem item) {
		super(shell);
		this.name=name;
		this.item=item;
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(420, 315);
		newShell.setLocation(450, 175);
		newShell.setText(title);
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent){
		parent.setBackground(EccTreeControl.color);
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(EccTreeControl.color);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		TabFolder tabFolder=new TabFolder(composite, SWT.NONE);
		
		TabItem basicItem=new TabItem(tabFolder, SWT.NONE);
		basicItem.setText("基本");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		basicItem.setControl(composite_1);
		
		SashForm sashForm = new SashForm(composite_1, SWT.VERTICAL);
		sashForm.setBackground(EccTreeControl.color);
		sashForm.setSize(436, 214);
		sashForm.setLocation(0, 0);
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(EccTreeControl.color);
		
		ICollection ico=FileTools.getBussCollection("TaskName",name, "EccTaskPlan");
		IEnumerator ien=ico.GetEnumerator();
		while(ien.MoveNext()){
			bo=(BusinessObject)ien.get_Current();
			if(bo!=null&&bo.GetField("Model").get_NativeValue().toString().equals("时间段计划")){
				TaskName=bo.GetField("TaskName").get_NativeValue()
						.toString();
				Instruction=bo.GetField("Instruction").get_NativeValue()
						.toString();
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
				
				EndTime=bo.GetField("EndTime").get_NativeValue()
						.toString();
				String []s3=EndTime.split(";");
				String []c1=s3[0].split(",");
				time_7=c1[1];
				String []c2=s3[1].split(",");
				time_8=c2[1];
				String []c3=s3[2].split(",");
				time_9=c3[1];
				String []c4=s3[3].split(",");
				time_10=c4[1];
				String []c5=s3[4].split(",");
				time_11=c5[1];
				String []c6=s3[5].split(",");
				time_12=c6[1];
				String []c7=s3[6].split(",");
				time_13=c7[1];
				
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
			}
		}
		
		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		lblNewLabel.setBounds(26, 0, 107, 15);
		lblNewLabel.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*:");
		
		text_2 = new Text(composite_3, SWT.BORDER);//任务计划名称
		text_2.setBounds(140, 0, 201, 18);
		text_2.setText(TaskName);
		text_2.setEnabled(false);
		
		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(EccTreeControl.color);
		table.setLinesVisible(false);
		table.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				event.height=25;
			}
		});
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(60);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(90);
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(25);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(25);
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		
		tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(0,"星期日");
		TableEditor editor = new TableEditor(table);
		btnCheckButton = new Button(table, SWT.CHECK);
		btnCheckButton.setBackground(EccTreeControl.color);
		btnCheckButton.setBounds(113, 33, 45, 16);
		btnCheckButton.setText("\u5141\u8BB8");
		if(tableItem.getText(0).equals(date)&&permission.equals("允许")){
			btnCheckButton.setSelection(true);
		}else if(tableItem.getText(0).equals(date)&&permission.equals("禁止")){
			btnCheckButton.setSelection(false);
		}
		editor.minimumWidth = btnCheckButton.getSize ().x;
		editor.setEditor(btnCheckButton, tableItem, 1);
		TableEditor editor_1 = new TableEditor(table);
		Label lblNewLabel_8 = new Label(table, SWT.NONE);
		lblNewLabel_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_8.setBounds(172, 34, 18, 12);
		lblNewLabel_8.setText("\u4ECE");
		editor_1.minimumWidth = lblNewLabel_8.getSize ().x;
		editor_1.setEditor(lblNewLabel_8, tableItem, 2);
		TableEditor editor_2 = new TableEditor(table);
		startTime = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime.setLocation(191, 32);
		startTime.setSize(79, 15);
		int h=Integer.parseInt(time.substring(0, time.indexOf(":")));
		int m=Integer.parseInt(time.substring(time.indexOf(":")+1,time.lastIndexOf(":")));
		int mm=Integer.parseInt(time.substring(time.lastIndexOf(":")+1));
		startTime.setTime(h, m, mm);
		startTimeStr=startTime.getHours() + ":"
				+ startTime.getMinutes() + ":" + startTime.getSeconds();
		startTime.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
            startTimeStr=startTime.getHours() + ":"
    				+ startTime.getMinutes() + ":" + startTime.getSeconds();
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		editor_2.minimumWidth = startTime.getSize ().x;
		editor_2.setEditor(startTime, tableItem, 3);
		TableEditor editor_3 = new TableEditor(table);
		Label lblNewLabel_9 = new Label(table, SWT.NONE);
		lblNewLabel_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_9.setBounds(276, 34, 18, 12);
		lblNewLabel_9.setText("\u5230");
		editor_3.minimumWidth = lblNewLabel_9.getSize ().x;
		editor_3.setEditor(lblNewLabel_9, tableItem, 4);
		TableEditor editor_4 = new TableEditor(table);
		startTime_1 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_1.setLocation(295, 32);
		startTime_1.setSize(79, 15);
		int h7=Integer.parseInt(time_7.substring(0, time_7.indexOf(":")));
		int m7=Integer.parseInt(time_7.substring(time_7.indexOf(":")+1,time_7.lastIndexOf(":")));
		int mm7=Integer.parseInt(time_7.substring(time_7.lastIndexOf(":")+1));
		startTime_1.setTime(h7, m7, mm7);
		startTimeStr_1=startTime_1.getHours() + ":"
				+ startTime_1.getMinutes() + ":" + startTime_1.getSeconds();
		startTime_1.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_1=startTime_1.getHours() + ":"
						+ startTime_1.getMinutes() + ":" + startTime_1.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_4.minimumWidth = startTime_1.getSize ().x;
		editor_4.setEditor(startTime_1, tableItem, 5); 
		
		tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(0,"星期一");
		TableEditor editor_5 = new TableEditor(table);
		btnCheckButton_1 = new Button(table, SWT.CHECK);
		btnCheckButton_1.setBackground(EccTreeControl.color);
		btnCheckButton_1.setBounds(113, 51, 45, 16);
		btnCheckButton_1.setText("\u5141\u8BB8");
		if(tableItem_1.getText(0).equals(date_1)&&permission_1.equals("允许")){
			btnCheckButton_1.setSelection(true);
		}else if(tableItem_1.getText(0).equals(date_1)&&permission_1.equals("禁止")){
			btnCheckButton_1.setSelection(false);
		}
		editor_5.minimumWidth = btnCheckButton_1.getSize ().x;
		editor_5.setEditor(btnCheckButton_1, tableItem_1, 1);
		TableEditor editor_6 = new TableEditor(table);
		Label lblNewLabel_10 = new Label(table, SWT.NONE);
		lblNewLabel_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_10.setBounds(172, 52, 18, 12);
		lblNewLabel_10.setText("\u4ECE");
		editor_6.minimumWidth = lblNewLabel_10.getSize ().x;
		editor_6.setEditor(lblNewLabel_10, tableItem_1, 2);
		TableEditor editor_7 = new TableEditor(table);
		startTime_2 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_2.setLocation(191, 50);
		startTime_2.setSize(79, 15);
		int h1=Integer.parseInt(time_1.substring(0, time_1.indexOf(":")));
		int m1=Integer.parseInt(time_1.substring(time_1.indexOf(":")+1,time_1.lastIndexOf(":")));
		int mm1=Integer.parseInt(time_1.substring(time_1.lastIndexOf(":")+1));
		startTime_2.setTime(h1, m1, mm1);
		startTimeStr_2=startTime_2.getHours() + ":"
				+ startTime_2.getMinutes() + ":" + startTime_2.getSeconds();
		startTime_2.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_2=startTime_2.getHours() + ":"
						+ startTime_2.getMinutes() + ":" + startTime_2.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});
		 editor_7.minimumWidth = startTime_2.getSize ().x;
		 editor_7.setEditor(startTime_2, tableItem_1, 3);
		TableEditor editor_8 = new TableEditor(table);
		Label lblNewLabel_11 = new Label(table, SWT.NONE);
		lblNewLabel_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_11.setBounds(276, 52, 18, 12);
		lblNewLabel_11.setText("\u5230");
		editor_8.minimumWidth = lblNewLabel_11.getSize ().x;
		editor_8.setEditor(lblNewLabel_11, tableItem_1, 4);
		TableEditor editor_9 = new TableEditor(table);
		startTime_3 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_3.setLocation(295, 50);
		startTime_3.setSize(79, 15);
		int h8=Integer.parseInt(time_8.substring(0, time_8.indexOf(":")));
		int m8=Integer.parseInt(time_8.substring(time_8.indexOf(":")+1,time_8.lastIndexOf(":")));
		int mm8=Integer.parseInt(time_8.substring(time_8.lastIndexOf(":")+1));
		startTime_3.setTime(h8, m8, mm8);
		startTimeStr_3=startTime_3.getHours() + ":"
				+ startTime_3.getMinutes() + ":" + startTime_3.getSeconds();
		startTime_3.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_3=startTime_3.getHours() + ":"
						+ startTime_3.getMinutes() + ":" + startTime_3.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		 editor_9.minimumWidth = startTime_3.getSize ().x;
		 editor_9.setEditor(startTime_3, tableItem_1, 5); 
		
		tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(0,"星期二");
		TableEditor editor_10 = new TableEditor(table);
		btnCheckButton_2 = new Button(table, SWT.CHECK);
		btnCheckButton_2.setBackground(EccTreeControl.color);
		btnCheckButton_2.setBounds(113, 69, 45, 16);
		btnCheckButton_2.setText("\u5141\u8BB8");
		if(tableItem_2.getText(0).equals(date_2)&&permission_2.equals("允许")){
			btnCheckButton_2.setSelection(true);
		}else if(tableItem_2.getText(0).equals(date_2)&&permission_2.equals("禁止")){
			btnCheckButton_2.setSelection(false);
		}
		editor_10.minimumWidth = btnCheckButton_2.getSize ().x;
		editor_10.setEditor(btnCheckButton_2, tableItem_2, 1); 
		TableEditor editor_11 = new TableEditor(table);	
		Label lblNewLabel_12 = new Label(table, SWT.NONE);
		lblNewLabel_12.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_12.setBounds(172, 70, 18, 12);
		lblNewLabel_12.setText("\u4ECE");
		editor_11.minimumWidth = lblNewLabel_12.getSize ().x;
		editor_11.setEditor(lblNewLabel_12, tableItem_2, 2); 
		TableEditor editor_12 = new TableEditor(table);
		startTime_4 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_4.setLocation(191, 68);
		startTime_4.setSize(79, 15);
		int h2=Integer.parseInt(time_2.substring(0, time_2.indexOf(":")));
		int m2=Integer.parseInt(time_2.substring(time_2.indexOf(":")+1,time_2.lastIndexOf(":")));
		int mm2=Integer.parseInt(time_2.substring(time_2.lastIndexOf(":")+1));
		startTime_4.setTime(h2, m2, mm2);
		startTimeStr_4=startTime_4.getHours() + ":"
				+ startTime_4.getMinutes() + ":" + startTime_4.getSeconds();
		startTime_4.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_4=startTime_4.getHours() + ":"
						+ startTime_4.getMinutes() + ":" + startTime_4.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});
		editor_12.minimumWidth = startTime_4.getSize ().x;
		editor_12.setEditor(startTime_4, tableItem_2, 3); 
		TableEditor editor_13 = new TableEditor(table);
		Label lblNewLabel_13 = new Label(table, SWT.NONE);
		lblNewLabel_13.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_13.setBounds(276, 71, 18, 12);
		lblNewLabel_13.setText("\u5230");
		editor_13.minimumWidth = lblNewLabel_13.getSize ().x;
		editor_13.setEditor(lblNewLabel_13, tableItem_2, 4); 
		TableEditor editor_14 = new TableEditor(table);
		startTime_5 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_5.setLocation(295, 68);
		startTime_5.setSize(79, 15);
		int h9=Integer.parseInt(time_9.substring(0, time_9.indexOf(":")));
		int m9=Integer.parseInt(time_9.substring(time_9.indexOf(":")+1,time_9.lastIndexOf(":")));
		int mm9=Integer.parseInt(time_9.substring(time_9.lastIndexOf(":")+1));
		startTime_5.setTime(h9, m9, mm9);
		startTimeStr_5=startTime_5.getHours() + ":"
				+ startTime_5.getMinutes() + ":" + startTime_5.getSeconds();
		startTime_5.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_5=startTime_5.getHours() + ":"
						+ startTime_5.getMinutes() + ":" + startTime_5.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		editor_14.minimumWidth = startTime_5.getSize ().x;
		editor_14.setEditor(startTime_5, tableItem_2, 5); 
		
		tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(0,"星期三");
		TableEditor editor_15 = new TableEditor(table);
		btnCheckButton_3 = new Button(table, SWT.CHECK);
		btnCheckButton_3.setBackground(EccTreeControl.color);
		btnCheckButton_3.setBounds(113, 87, 45, 16);
		btnCheckButton_3.setText("\u5141\u8BB8");
		if(tableItem_3.getText(0).equals(date_3)&&permission_3.equals("允许")){
			btnCheckButton_3.setSelection(true);
		}else if(tableItem_3.getText(0).equals(date_3)&&permission_3.equals("禁止")){
			btnCheckButton_3.setSelection(false);
		}
		editor_15.minimumWidth = btnCheckButton_3.getSize ().x;
		editor_15.setEditor(btnCheckButton_3, tableItem_3, 1); 
		TableEditor editor_16 = new TableEditor(table);
		Label lblNewLabel_14 = new Label(table, SWT.NONE);
		lblNewLabel_14.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_14.setBounds(172, 88, 18, 12);
		lblNewLabel_14.setText("\u4ECE");
		editor_16.minimumWidth = lblNewLabel_14.getSize ().x;
		editor_16.setEditor(lblNewLabel_14, tableItem_3, 2); 
		TableEditor editor_17 = new TableEditor(table);
		startTime_6 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_6.setLocation(191, 86);
		startTime_6.setSize(79, 15);
		int h3=Integer.parseInt(time_3.substring(0, time_3.indexOf(":")));
		int m3=Integer.parseInt(time_3.substring(time_3.indexOf(":")+1,time_3.lastIndexOf(":")));
		int mm3=Integer.parseInt(time_3.substring(time_3.lastIndexOf(":")+1));
		startTime_6.setTime(h3, m3, mm3);
		startTimeStr_6=startTime_6.getHours() + ":"
				+ startTime_6.getMinutes() + ":" + startTime_6.getSeconds();
		startTime_6.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_6=startTime_6.getHours() + ":"
						+ startTime_6.getMinutes() + ":" + startTime_6.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_17.minimumWidth = startTime_6.getSize ().x;
		editor_17.setEditor(startTime_6, tableItem_3, 3); 
		TableEditor editor_18 = new TableEditor(table);
		Label lblNewLabel_18 = new Label(table, SWT.NONE);
		lblNewLabel_18.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_18.setBounds(276, 89, 18, 12);
		lblNewLabel_18.setText("\u5230");
		editor_18.minimumWidth = lblNewLabel_18.getSize ().x;
		editor_18.setEditor(lblNewLabel_18, tableItem_3, 4); 
		TableEditor editor_19 = new TableEditor(table);
		startTime_7 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_7.setLocation(295, 86);
		startTime_7.setSize(79, 15);
		int h10=Integer.parseInt(time_10.substring(0, time_10.indexOf(":")));
		int m10=Integer.parseInt(time_10.substring(time_10.indexOf(":")+1,time_10.lastIndexOf(":")));
		int mm10=Integer.parseInt(time_10.substring(time_10.lastIndexOf(":")+1));
		startTime_7.setTime(h10, m10, mm10);
		startTimeStr_7=startTime_7.getHours() + ":"
				+ startTime_7.getMinutes() + ":" + startTime_7.getSeconds();
		startTime_7.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_7=startTime_7.getHours() + ":"
						+ startTime_7.getMinutes() + ":" + startTime_7.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		editor_19.minimumWidth = startTime_7.getSize ().x;
		editor_19.setEditor(startTime_7, tableItem_3, 5); 
		
		
		tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setText(0,"星期四");
		TableEditor editor_20 = new TableEditor(table);
		btnCheckButton_4 = new Button(table, SWT.CHECK);
		btnCheckButton_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_4.setBounds(113, 105, 45, 16);
		btnCheckButton_4.setText("\u5141\u8BB8 ");
		if(tableItem_4.getText(0).equals(date_4)&&permission_4.equals("允许")){
			btnCheckButton_4.setSelection(true);
		}else if(tableItem_4.getText(0).equals(date_4)&&permission_4.equals("禁止")){
			btnCheckButton_4.setSelection(false);
		}
		editor_20.minimumWidth = btnCheckButton_4.getSize ().x;
		editor_20.setEditor(btnCheckButton_4, tableItem_4, 1); 
		TableEditor editor_21 = new TableEditor(table);
		Label lblNewLabel_15 = new Label(table, SWT.NONE);
		lblNewLabel_15.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_15.setBounds(172, 107, 18, 12);
		lblNewLabel_15.setText("\u4ECE");
		editor_21.minimumWidth = lblNewLabel_15.getSize ().x;
		editor_21.setEditor(lblNewLabel_15, tableItem_4, 2); 
		TableEditor editor_22 = new TableEditor(table);
		startTime_8 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_8.setLocation(191, 104);
		startTime_8.setSize(79, 15);
		int h4=Integer.parseInt(time_4.substring(0, time_4.indexOf(":")));
		int m4=Integer.parseInt(time_4.substring(time_4.indexOf(":")+1,time_4.lastIndexOf(":")));
		int mm4=Integer.parseInt(time_4.substring(time_4.lastIndexOf(":")+1));
		startTime_8.setTime(h4, m4, mm4);
		startTimeStr_8=startTime_8.getHours() + ":"
				+ startTime_8.getMinutes() + ":" + startTime_8.getSeconds();
		startTime_8.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_8=startTime_8.getHours() + ":"
						+ startTime_8.getMinutes() + ":" + startTime_8.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_22.minimumWidth = startTime_8.getSize ().x;
		editor_22.setEditor(startTime_8, tableItem_4, 3); 
		TableEditor editor_23 = new TableEditor(table);
		Label lblNewLabel_19 = new Label(table, SWT.NONE);
		lblNewLabel_19.setBackground(EccTreeControl.color);
		lblNewLabel_19.setBounds(276, 107, 18, 12);
		lblNewLabel_19.setText("\u5230");
		editor_23.minimumWidth = lblNewLabel_19.getSize ().x;
		editor_23.setEditor(lblNewLabel_19, tableItem_4, 4); 
		TableEditor editor_24 = new TableEditor(table);
		startTime_9 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_9.setLocation(295, 104);
		startTime_9.setSize(79, 15);
		int h11=Integer.parseInt(time_11.substring(0, time_11.indexOf(":")));
		int m11=Integer.parseInt(time_11.substring(time_11.indexOf(":")+1,time_11.lastIndexOf(":")));
		int mm11=Integer.parseInt(time_11.substring(time_11.lastIndexOf(":")+1));
		startTime_9.setTime(h11, m11, mm11);
		startTimeStr_9=startTime_9.getHours() + ":"
				+ startTime_9.getMinutes() + ":" + startTime_9.getSeconds();
		startTime_9.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_9=startTime_9.getHours() + ":"
						+ startTime_9.getMinutes() + ":" + startTime_9.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});
		editor_24.minimumWidth = startTime_9.getSize ().x;
		editor_24.setEditor(startTime_9, tableItem_4, 5); 
		
		
		
		tableItem_5 = new TableItem(table, SWT.NONE);
		tableItem_5.setText(0,"星期五");
		TableEditor editor_25 = new TableEditor(table);
		btnCheckButton_5 = new Button(table, SWT.CHECK);
		btnCheckButton_5.setBackground(EccTreeControl.color);
		btnCheckButton_5.setBounds(113, 123, 45, 16);
		btnCheckButton_5.setText("\u5141\u8BB8");
		if(tableItem_5.getText(0).equals(date_5)&&permission_5.equals("允许")){
			btnCheckButton_5.setSelection(true);
		}else if(tableItem_5.getText(0).equals(date_5)&&permission_5.equals("禁止")){
			btnCheckButton_5.setSelection(false);
		}
		editor_25.minimumWidth = btnCheckButton_5.getSize ().x;
		editor_25.setEditor(btnCheckButton_5, tableItem_5, 1); 
		TableEditor editor_26 = new TableEditor(table);
		Label lblNewLabel_16 = new Label(table, SWT.NONE);
		lblNewLabel_16.setBackground(EccTreeControl.color);
		lblNewLabel_16.setBounds(172, 125, 18, 12);
		lblNewLabel_16.setText("\u4ECE");
		editor_26.minimumWidth = lblNewLabel_16.getSize ().x;
		editor_26.setEditor(lblNewLabel_16, tableItem_5, 2); 
		TableEditor editor_27 = new TableEditor(table);
		startTime_10 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_10.setLocation(191, 122);
		startTime_10.setSize(79, 15);
		int h5=Integer.parseInt(time_5.substring(0, time_5.indexOf(":")));
		int m5=Integer.parseInt(time_5.substring(time_5.indexOf(":")+1,time_5.lastIndexOf(":")));
		int mm5=Integer.parseInt(time_5.substring(time_5.lastIndexOf(":")+1));
		startTime_10.setTime(h5, m5, mm5);
		startTimeStr_10=startTime_10.getHours() + ":"
				+ startTime_10.getMinutes() + ":" + startTime_10.getSeconds();
		startTime_10.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_10=startTime_10.getHours() + ":"
						+ startTime_10.getMinutes() + ":" + startTime_10.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});
		editor_27.minimumWidth = startTime_10.getSize ().x;
		editor_27.setEditor(startTime_10, tableItem_5, 3); 
		TableEditor editor_28 = new TableEditor(table);
		Label lblNewLabel_20 = new Label(table, SWT.NONE);
		lblNewLabel_20.setBackground(EccTreeControl.color);
		lblNewLabel_20.setBounds(276, 125, 18, 12);
		lblNewLabel_20.setText("\u5230");
		editor_28.minimumWidth = lblNewLabel_20.getSize ().x;
		editor_28.setEditor(lblNewLabel_20, tableItem_5, 4); 
		TableEditor editor_29 = new TableEditor(table);
		startTime_11 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_11.setLocation(295, 122);
		startTime_11.setSize(79, 15);
		int h12=Integer.parseInt(time_12.substring(0, time_12.indexOf(":")));
		int m12=Integer.parseInt(time_12.substring(time_12.indexOf(":")+1,time_12.lastIndexOf(":")));
		int mm12=Integer.parseInt(time_12.substring(time_12.lastIndexOf(":")+1));
		startTime_11.setTime(h12, m12, mm12);
		startTimeStr_11=startTime_11.getHours() + ":"
				+ startTime_11.getMinutes() + ":" + startTime_11.getSeconds();
		startTime_11.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_11=startTime_11.getHours() + ":"
						+ startTime_11.getMinutes() + ":" + startTime_11.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});
		editor_29.minimumWidth = startTime_11.getSize ().x;
		editor_29.setEditor(startTime_11, tableItem_5, 5); 
		
		tableItem_6 = new TableItem(table, SWT.NONE);
		tableItem_6.setText(0,"星期六");
		TableEditor editor_30 = new TableEditor(table);
		btnCheckButton_6 = new Button(table, SWT.CHECK);
		btnCheckButton_6.setBackground(EccTreeControl.color);
		btnCheckButton_6.setBounds(113, 141, 45, 16);
		btnCheckButton_6.setText("\u5141\u8BB8");
		if(tableItem_6.getText(0).equals(date_6)&&permission_6.equals("允许")){
			btnCheckButton_6.setSelection(true);
		}else if(tableItem_6.getText(0).equals(date_6)&&permission_6.equals("禁止")){
			btnCheckButton_6.setSelection(false);
		}
		editor_30.minimumWidth = btnCheckButton_6.getSize ().x;
		editor_30.setEditor(btnCheckButton_6, tableItem_6, 1); 
		TableEditor editor_31 = new TableEditor(table);
		Label lblNewLabel_17 = new Label(table, SWT.NONE);
		lblNewLabel_17.setBackground(EccTreeControl.color);
		lblNewLabel_17.setBounds(172, 143, 18, 12);
		lblNewLabel_17.setText("\u4ECE");
		editor_31.minimumWidth = lblNewLabel_17.getSize ().x;
		editor_31.setEditor(lblNewLabel_17, tableItem_6, 2);
		TableEditor editor_32 = new TableEditor(table);
		startTime_12 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_12.setLocation(191, 140);
		startTime_12.setSize(79, 15);
		int h6=Integer.parseInt(time_6.substring(0, time_6.indexOf(":")));
		int m6=Integer.parseInt(time_6.substring(time_6.indexOf(":")+1,time_6.lastIndexOf(":")));
		int mm6=Integer.parseInt(time_6.substring(time_6.lastIndexOf(":")+1));
		startTime_12.setTime(h6, m6, mm6);
		startTimeStr_12=startTime_12.getHours() + ":"
				+ startTime_12.getMinutes() + ":" + startTime_12.getSeconds();
		startTime_12.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				startTimeStr_12=startTime_12.getHours() + ":"
						+ startTime_12.getMinutes() + ":" + startTime_12.getSeconds();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});
		 editor_32.minimumWidth = startTime_12.getSize ().x;
		 editor_32.setEditor(startTime_12, tableItem_6, 3);
		 TableEditor editor_33 = new TableEditor(table);
		 Label lblNewLabel_21 = new Label(table, SWT.NONE);
		 lblNewLabel_21.setBackground(EccTreeControl.color);
		 lblNewLabel_21.setBounds(276, 143, 18, 12);
		 lblNewLabel_21.setText("\u5230");
		 editor_33.minimumWidth = lblNewLabel_21.getSize ().x;
		 editor_33.setEditor(lblNewLabel_21, tableItem_6, 4);
		 TableEditor editor_34 = new TableEditor(table);
			startTime_13 = new DateTime(table, SWT.TIME
					| SWT.SHORT);
			startTime_13.setLocation(295, 140);
			startTime_13.setSize(79, 15);
			int h13=Integer.parseInt(time_13.substring(0, time_13.indexOf(":")));
			int m13=Integer.parseInt(time_13.substring(time_13.indexOf(":")+1,time_13.lastIndexOf(":")));
			int mm13=Integer.parseInt(time_13.substring(time_13.lastIndexOf(":")+1));
			startTime_13.setTime(h13, m13, mm13);
			startTimeStr_13=startTime_13.getHours() + ":"
					+ startTime_13.getMinutes() + ":" + startTime_13.getSeconds();
			startTime_13.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					startTimeStr_13=startTime_13.getHours() + ":"
							+ startTime_13.getMinutes() + ":" + startTime_13.getSeconds();
				}
				public void widgetDefaultSelected(SelectionEvent e) {
				
				}
			});
			 editor_34.minimumWidth = startTime_13.getSize ().x;
			 editor_34.setEditor(startTime_13, tableItem_6, 5);
			 
		sashForm.setWeights(new int[] {25, 171});
			
		TabItem describeItem=new TabItem(tabFolder, SWT.NONE);
		describeItem.setText("描述");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		describeItem.setControl(composite_2);
		
		Label lblNewLabel_22 = new Label(composite_2, SWT.HORIZONTAL);
		lblNewLabel_22.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_22.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_22.setAlignment(SWT.CENTER);
		lblNewLabel_22.setBounds(0, 0, 54, 199);
		lblNewLabel_22.setText("\u63CF\u8FF0:");
		
		text_1 = new Text(composite_2, SWT.BORDER);//描述
		text_1.setBounds(56, 0, 370, 199);
		text_1.setText(Instruction);
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "保存",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", true);
	}
	protected void buttonPressed(int buttonId){
		if(buttonId==IDialogConstants.OK_ID){
			if(btnCheckButton.getSelection()){
				permission="允许";
			}else{
				permission="禁止";
			}
			
			if(btnCheckButton_1.getSelection()){
				permission_1="允许";
			}else{
				permission_1="禁止";
			}
			
			if(btnCheckButton_2.getSelection()){
				permission_2="允许";
			}else{
				permission_2="禁止";
			}
			
			if(btnCheckButton_3.getSelection()){
				permission_3="允许";
			}else{
				permission_3="禁止";
			}
			
			if(btnCheckButton_4.getSelection()){
				permission_4="允许";
			}else{
				permission_4="禁止";
			}
			
			if(btnCheckButton_5.getSelection()){
				permission_5="允许";
			}else{
				permission_5="禁止";
			}
			
			if(btnCheckButton_6.getSelection()){
				permission_6="允许";
			}else{
				permission_6="禁止";
			}
			
			bo.GetField("Instruction").SetValue(new SiteviewValue(text_1.getText()));//更新描述 
			bo.GetField("Status").SetValue(//更新是否允许的数据
					new SiteviewValue(tableItem.getText(0)+":"+permission+";"+tableItem_1.getText(0)+":"+permission_1+";"+
							tableItem_2.getText(0)+":"+permission_2+";"+tableItem_3.getText(0)+":"+permission_3+";"+
							tableItem_4.getText(0)+":"+permission_4+";"+tableItem_5.getText(0)+":"+permission_5+";"+
							tableItem_6.getText(0)+":"+permission_6));
			bo.GetField("StatrtTime").SetValue(//更新开始时间的数据
					new SiteviewValue(tableItem.getText(0)+","+startTimeStr+";"+tableItem_1.getText(0)+","+startTimeStr_2+";"+
							tableItem_2.getText(0)+","+startTimeStr_4+";"+tableItem_3.getText(0)+","+startTimeStr_6+";"+
							tableItem_4.getText(0)+","+startTimeStr_8+";"+tableItem_5.getText(0)+","+startTimeStr_10+";"+
							tableItem_6.getText(0)+","+startTimeStr_12));
			bo.GetField("EndTime").SetValue(new SiteviewValue(tableItem.getText(0)+","+startTimeStr_1+";"+tableItem_1.getText(0)+","+startTimeStr_3+";"+
					tableItem_2.getText(0)+","+startTimeStr_5+";"+tableItem_3.getText(0)+","+startTimeStr_7+";"+
					tableItem_4.getText(0)+","+startTimeStr_9+";"+tableItem_5.getText(0)+","+startTimeStr_11+";"+
					tableItem_6.getText(0)+","+startTimeStr_13));//更新结束时间的数据
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//将修改后的数据存储到数据库
			item.setText(1, text_1.getText());
		}
		this.close();
	}
}
