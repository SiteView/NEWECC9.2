package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableColumn;
import SiteView.ecc.tools.FileTools;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import swing2swt.layout.BorderLayout;
import system.Collections.ICollection;
import system.Collections.IEnumerator;


public class AbsoluteTimeEditor extends Dialog{
	private String title="编辑绝对时间任务计划";
	public TableItem tableItem;
	public TableItem tableItem_1;
	public TableItem tableItem_2;
	public TableItem tableItem_3;
	public TableItem tableItem_4;
	public TableItem tableItem_5;
	public TableItem tableItem_6;
	public Button btnCheckButton;
	public Button btnCheckButton_1;
	public Button btnCheckButton_2;
	public Button btnCheckButton_3;
	public Button button;
	public Button btnCheckButton_4;
	public Button btnCheckButton_5;
	public String startTimeStr = "";	
	public String startTimeStr_1 = "";	
	public String startTimeStr_2 = "";	
	public String startTimeStr_3 = "";	
	public String startTimeStr_4 = "";	
	public String startTimeStr_5 = "";	
	public String startTimeStr_6 = "";	
	private Text text;
	private Text text1;
	private Table table;
	private String name;
	private TableItem item;
	public BusinessObject bo;
	public String TaskName;
	public String Instruction;
	public String StatrtTime;
	public String Status;
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
	public String time;
	public String time_1;
	public String time_2;
	public String time_3;
	public String time_4;
	public String time_5;
	public String time_6;
	public DateTime startTime;
	public DateTime startTime_1;
	public DateTime startTime_2;
	public DateTime startTime_3;
	public DateTime startTime_4;
	public DateTime startTime_5;
	public DateTime startTime_6;
	public AbsoluteTimeEditor(Shell shell,String name,TableItem item) {
		super(shell);
		this.name=name;
		this.item=item;
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(350, 320);
		newShell.setLocation(450, 175);
		newShell.setText(title);
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		TabFolder tabFolder=new TabFolder(composite, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		TabItem basicItem=new TabItem(tabFolder, SWT.NONE);
		basicItem.setText("基本");
		
		Composite composite1 = new Composite(tabFolder, SWT.NONE);
		composite1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		basicItem.setControl(composite1);
		
		SashForm sashForm = new SashForm(composite1, SWT.VERTICAL);
		sashForm.setSize(445, 211);
		sashForm.setLocation(0, 0);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		ICollection ico=FileTools.getBussCollection("TaskName",name, "EccTaskPlan");
		IEnumerator ien=ico.GetEnumerator();
		while(ien.MoveNext()){
			bo=(BusinessObject)ien.get_Current();
			if(bo!=null){
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
		
		text = new Text(composite_1, SWT.BORDER);//任务计划名称
		text.setLocation(137, 0);
		text.setSize(177, 18);
		text.setText(TaskName);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		lblNewLabel.setBounds(10, 2, 102, 18);
		lblNewLabel.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*:");
		
		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setLinesVisible(false);
		table.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
			event.height=25;
			}
		});
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		
	    tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(0, "星期日");
			TableEditor editor = new TableEditor(table);
			btnCheckButton = new Button(table, SWT.CHECK);
			btnCheckButton.setBounds(130, 31, 45, 16);
			btnCheckButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			btnCheckButton.setText("\u5141\u8BB8");
			if(tableItem.getText(0).equals(date)&&permission.equals("允许")){
				btnCheckButton.setSelection(true);
			}else if(tableItem.getText(0).equals(date)&&permission.equals("禁止")){
				btnCheckButton.setSelection(false);
			}
			editor.minimumWidth = btnCheckButton.getSize ().x;
			editor.setEditor(btnCheckButton, tableItem, 1);
			
			TableEditor editor_1 = new TableEditor(table);
			startTime = new DateTime(table, SWT.TIME
					| SWT.SHORT);
			startTime.setLocation(181, 30);
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
			editor_1.minimumWidth = startTime.getSize ().x;
			editor_1.setEditor(startTime, tableItem, 2);
		
		
		tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(0, "星期一");
		TableEditor editor_2 = new TableEditor(table);
		btnCheckButton_1 = new Button(table, SWT.CHECK);
		btnCheckButton_1.setBounds(130, 53, 45, 16);
		btnCheckButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_1.setText("\u5141\u8BB8");
		if(tableItem_1.getText(0).equals(date_1)&&permission_1.equals("允许")){
			btnCheckButton_1.setSelection(true);
		}else if(tableItem_1.getText(0).equals(date_1)&&permission_1.equals("禁止")){
			btnCheckButton_1.setSelection(false);
		}
		editor_2.minimumWidth = btnCheckButton_1.getSize ().x;
		editor_2.setEditor(btnCheckButton_1, tableItem_1, 1);
		
		TableEditor editor_3 = new TableEditor(table);
		startTime_1 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_1.setLocation(181, 52);
		startTime_1.setSize(79, 15);
		int h1=Integer.parseInt(time_1.substring(0, time_1.indexOf(":")));
		int m1=Integer.parseInt(time_1.substring(time_1.indexOf(":")+1,time_1.lastIndexOf(":")));
		int mm1=Integer.parseInt(time_1.substring(time_1.lastIndexOf(":")+1));
		startTime_1.setTime(h1, m1, mm1);
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
		editor_3.minimumWidth = startTime_1.getSize ().x;
		editor_3.setEditor(startTime_1, tableItem_1, 2);
		
		tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(0,"星期二 ");
		TableEditor editor_4 = new TableEditor(table);
		btnCheckButton_2 = new Button(table, SWT.CHECK);
		btnCheckButton_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_2.setBounds(130, 73, 45, 16);
		btnCheckButton_2.setText("\u5141\u8BB8");
		if(tableItem_2.getText(0).equals(date_2)&&permission_2.equals("允许")){
			btnCheckButton_2.setSelection(true);
		}else if(tableItem_2.getText(0).equals(date_2)&&permission_2.equals("禁止")){
			btnCheckButton_2.setSelection(false);
		}
		editor_4.minimumWidth = btnCheckButton_2.getSize ().x;
		editor_4.setEditor(btnCheckButton_2, tableItem_2, 1);
		
		TableEditor editor_5 = new TableEditor(table);
		startTime_2 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_2.setLocation(181, 72);
		startTime_2.setSize(79, 15);
		int h2=Integer.parseInt(time_2.substring(0, time_2.indexOf(":")));
		int m2=Integer.parseInt(time_2.substring(time_2.indexOf(":")+1,time_2.lastIndexOf(":")));
		int mm2=Integer.parseInt(time_2.substring(time_2.lastIndexOf(":")+1));
		startTime_2.setTime(h2, m2, mm2);
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
		 editor_5.minimumWidth = startTime_2.getSize ().x;
		 editor_5.setEditor(startTime_2, tableItem_2, 2);
		 
		tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(0,"星期三 ");
		TableEditor editor_6 = new TableEditor(table);
		btnCheckButton_3 = new Button(table, SWT.CHECK);
		btnCheckButton_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_3.setBounds(130, 95, 45, 16);
		btnCheckButton_3.setText("\u5141\u8BB8");
		if(tableItem_3.getText(0).equals(date_3)&&permission_3.equals("允许")){
			btnCheckButton_3.setSelection(true);
		}else if(tableItem_3.getText(0).equals(date_3)&&permission_3.equals("禁止")){
			btnCheckButton_3.setSelection(false);
		}
		editor_6.minimumWidth = btnCheckButton_3.getSize ().x;
		editor_6.setEditor(btnCheckButton_3, tableItem_3, 1);
		
		TableEditor editor_7 = new TableEditor(table);
		startTime_3 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_3.setLocation(181, 93);
		startTime_3.setSize(79, 15);
		int h3=Integer.parseInt(time_3.substring(0, time_3.indexOf(":")));
		int m3=Integer.parseInt(time_3.substring(time_3.indexOf(":")+1,time_3.lastIndexOf(":")));
		int mm3=Integer.parseInt(time_3.substring(time_3.lastIndexOf(":")+1));
		startTime_3.setTime(h3, m3, mm3);
		startTimeStr_3=startTime_3.getHours() + ":"
				+ startTime_3.getMinutes() + ":" + startTime_3.getSeconds();
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
		editor_7.minimumWidth = startTime_3.getSize ().x;
		editor_7.setEditor(startTime_3, tableItem_3, 2);
		
		tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setText(0,"星期四 ");
		TableEditor editor_8 = new TableEditor(table);
		button = new Button(table, SWT.CHECK);
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button.setText("\u5141\u8BB8");
		button.setBounds(130, 116, 45, 16);
		if(tableItem_4.getText(0).equals(date_4)&&permission_4.equals("允许")){
			button.setSelection(true);
		}else if(tableItem_4.getText(0).equals(date_4)&&permission_4.equals("禁止")){
			button.setSelection(false);
		}
		editor_8.minimumWidth = button.getSize ().x;
		editor_8.setEditor(button, tableItem_4, 1);
		
		TableEditor editor_9 = new TableEditor(table);
		startTime_4 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_4.setLocation(181, 115);
		startTime_4.setSize(79, 15);
		int h4=Integer.parseInt(time_4.substring(0, time_4.indexOf(":")));
		int m4=Integer.parseInt(time_4.substring(time_4.indexOf(":")+1,time_4.lastIndexOf(":")));
		int mm4=Integer.parseInt(time_4.substring(time_4.lastIndexOf(":")+1));
		startTime_4.setTime(h4, m4, mm4);
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
		 editor_9.minimumWidth = startTime_4.getSize ().x;
		 editor_9.setEditor(startTime_4, tableItem_4, 2);
		 
		tableItem_5 = new TableItem(table, SWT.NONE);
		tableItem_5.setText(0,"星期五");
		TableEditor editor_10 = new TableEditor(table);
		btnCheckButton_4 = new Button(table, SWT.CHECK);
		btnCheckButton_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_4.setBounds(130, 138, 45, 16);
		btnCheckButton_4.setText("\u5141\u8BB8");
		if(tableItem_5.getText(0).equals(date_5)&&permission_5.equals("允许")){
			btnCheckButton_4.setSelection(true);
		}else if(tableItem_5.getText(0).equals(date_5)&&permission_5.equals("禁止")){
			btnCheckButton_4.setSelection(false);
		}
		editor_10.minimumWidth = btnCheckButton_4.getSize ().x;
		editor_10.setEditor(btnCheckButton_4, tableItem_5, 1);
		
		TableEditor editor_11 = new TableEditor(table);
		startTime_5 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_5.setLocation(181, 136);
		startTime_5.setSize(79, 15);
		int h5=Integer.parseInt(time_5.substring(0, time_5.indexOf(":")));
		int m5=Integer.parseInt(time_5.substring(time_5.indexOf(":")+1,time_5.lastIndexOf(":")));
		int mm5=Integer.parseInt(time_5.substring(time_5.lastIndexOf(":")+1));
		startTime_5.setTime(h5, m5, mm5);
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
		editor_11.minimumWidth = startTime_5.getSize ().x;
		editor_11.setEditor(startTime_5, tableItem_5, 2); 
		
		tableItem_6 = new TableItem(table, SWT.NONE);
		tableItem_6.setText(0,"星期六");
		TableEditor editor_12 = new TableEditor(table);
		btnCheckButton_5 = new Button(table, SWT.CHECK);
		btnCheckButton_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_5.setBounds(130, 160, 45, 16);
		btnCheckButton_5.setText("\u5141\u8BB8");
		if(tableItem_6.getText(0).equals(date_6)&&permission_6.equals("允许")){
			btnCheckButton_5.setSelection(true);
		}else if(tableItem_6.getText(0).equals(date_6)&&permission_6.equals("禁止")){
			btnCheckButton_5.setSelection(false);
		}
		editor_12.minimumWidth = btnCheckButton_5.getSize ().x;
		editor_12.setEditor(btnCheckButton_5, tableItem_6, 1); 
		
		TableEditor editor_13 = new TableEditor(table);
		startTime_6 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_6.setLocation(181, 158);
		startTime_6.setSize(79, 15);
		int h6=Integer.parseInt(time_6.substring(0, time_6.indexOf(":")));
		int m6=Integer.parseInt(time_6.substring(time_6.indexOf(":")+1,time_6.lastIndexOf(":")));
		int mm6=Integer.parseInt(time_6.substring(time_6.lastIndexOf(":")+1));
		startTime_6.setTime(h6, m6, mm6);
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
		 editor_13.minimumWidth = startTime_6.getSize ().x;
		 editor_13.setEditor(startTime_6, tableItem_6, 2);  
		 
		sashForm.setWeights(new int[] {18, 190});
		 
		TabItem describeItem=new TabItem(tabFolder, SWT.NONE);
		describeItem.setText("描述");
		
		Composite composite2 = new Composite(tabFolder, SWT.NONE);
		composite2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		describeItem.setControl(composite2);
		composite2.setLayout(new BorderLayout(0, 0));
		
		Label label = new Label(composite2, SWT.HORIZONTAL);
		label.setAlignment(SWT.CENTER);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(0, 0, 54, 199);
		label.setLayoutData(BorderLayout.WEST);
		label.setText("\u63CF\u8FF0\uFF1A");
		
		text1=new Text(composite2, SWT.WRAP | SWT.BORDER);//描述
		text1.setLayoutData(BorderLayout.CENTER);
		text1.setText(Instruction);
		
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
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
			
			if(button.getSelection()){
				permission_4="允许";
			}else{
				permission_4="禁止";
			}
			
			if(btnCheckButton_4.getSelection()){
				permission_5="允许";
			}else{
				permission_5="禁止";
			}
			
			if(btnCheckButton_5.getSelection()){
				permission_6="允许";
			}else{
				permission_6="禁止";
			}
			bo.GetField("TaskName").SetValue(new SiteviewValue(text.getText()));//更新任务计划名称
			bo.GetField("Instruction").SetValue(new SiteviewValue(text1.getText()));//更新描述 
			bo.GetField("Status").SetValue(//更新是否允许的数据
					new SiteviewValue(tableItem.getText(0)+":"+permission+";"+tableItem_1.getText(0)+":"+permission_1+";"+
							tableItem_2.getText(0)+":"+permission_2+";"+tableItem_3.getText(0)+":"+permission_3+";"+
							tableItem_4.getText(0)+":"+permission_4+";"+tableItem_5.getText(0)+":"+permission_5+";"+
							tableItem_6.getText(0)+":"+permission_6));
			bo.GetField("StatrtTime").SetValue(//更新开始时间的数据
					new SiteviewValue(tableItem.getText(0)+","+startTimeStr+";"+tableItem_1.getText(0)+","+startTimeStr_1+";"+tableItem_2.getText(0)+","+startTimeStr_2+";"
							+tableItem_3.getText(0)+","+startTimeStr_3+";"+tableItem_4.getText(0)+","+startTimeStr_4+";"+
							tableItem_5.getText(0)+","+startTimeStr_5+";"+tableItem_6.getText(0)+","+startTimeStr_6));
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,//将修改后的数据存储到数据库
					true); 
			
			item.setText(0, text.getText());
			item.setText(1, text1.getText());
		}
		this.close();
	}
}
