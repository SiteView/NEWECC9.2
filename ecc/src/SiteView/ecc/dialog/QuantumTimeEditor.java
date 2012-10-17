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
	public Label lblNewLabel_1;
	public Label lblNewLabel_2;
	public Label lblNewLabel_3;
	public Label lblNewLabel_4;
	public Label lblNewLabel_5;
	public Label lblNewLabel_6;
	public Label lblNewLabel_7;
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
	private Text text_2;
	public Button btnCheckButton;
	public Button btnCheckButton_1;
	public Button btnCheckButton_2;
	public Button btnCheckButton_3;
	public Button btnCheckButton_4;
	public Button btnCheckButton_5;
	public Button btnCheckButton_6;
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
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		lblNewLabel.setBounds(26, 0, 107, 15);
		lblNewLabel.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*:");
		
		text_2 = new Text(composite_1, SWT.BORDER);//任务计划名称
		text_2.setBounds(140, 0, 201, 18);
		text_2.setText(TaskName);
		text_2.setEnabled(false);
		
		lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(26, 30, 54, 16);
		lblNewLabel_1.setText("星期日");
		
		btnCheckButton = new Button(composite_1, SWT.CHECK);
		btnCheckButton.setBackground(EccTreeControl.color);
		btnCheckButton.setBounds(113, 30, 45, 16);
		btnCheckButton.setText("\u5141\u8BB8");
		if(lblNewLabel_1.getText().equals(date)&&permission.equals("允许")){
			btnCheckButton.setSelection(true);
		}else if(lblNewLabel_1.getText().equals(date)&&permission.equals("禁止")){
			btnCheckButton.setSelection(false);
		}

		Label lblNewLabel_8 = new Label(composite_1, SWT.NONE);
		lblNewLabel_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_8.setBounds(167, 34, 18, 12);
		lblNewLabel_8.setText("\u4ECE");

		final DateTime startTime = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime.setLocation(191, 31);
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

		Label lblNewLabel_9 = new Label(composite_1, SWT.NONE);
		lblNewLabel_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_9.setBounds(281, 34, 18, 12);
		lblNewLabel_9.setText("\u5230");

		final DateTime startTime_1 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_1.setLocation(312, 31);
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

		lblNewLabel_2 = new Label(composite_1, SWT.NONE);
	    lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    lblNewLabel_2.setBounds(26, 54, 54, 16);
	    lblNewLabel_2.setText("星期一");

		btnCheckButton_1 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_1.setBackground(EccTreeControl.color);
		btnCheckButton_1.setBounds(113, 52, 45, 16);
		btnCheckButton_1.setText("\u5141\u8BB8");
		if(lblNewLabel_2.getText().equals(date_1)&&permission_1.equals("允许")){
			btnCheckButton_1.setSelection(true);
		}else if(lblNewLabel_2.getText().equals(date_1)&&permission_1.equals("禁止")){
			btnCheckButton_1.setSelection(false);
		}

		Label lblNewLabel_10 = new Label(composite_1, SWT.NONE);
		lblNewLabel_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_10.setBounds(167, 56, 18, 12);
		lblNewLabel_10.setText("\u4ECE");

		final DateTime startTime_2 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_2.setLocation(191, 53);
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

		Label lblNewLabel_11 = new Label(composite_1, SWT.NONE);
		lblNewLabel_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_11.setBounds(281, 56, 18, 12);
		lblNewLabel_11.setText("\u5230");

		final DateTime startTime_3 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_3.setLocation(312, 53);
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

		lblNewLabel_3 = new Label(composite_1, SWT.NONE);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_3.setBounds(26, 76, 54, 16);
		lblNewLabel_3.setText("星期二");

		btnCheckButton_2 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_2.setBackground(EccTreeControl.color);
		btnCheckButton_2.setBounds(113, 74, 45, 16);
		btnCheckButton_2.setText("\u5141\u8BB8");
		if(lblNewLabel_3.getText().equals(date_2)&&permission_2.equals("允许")){
			btnCheckButton_2.setSelection(true);
		}else if(lblNewLabel_3.getText().equals(date_2)&&permission_2.equals("禁止")){
			btnCheckButton_2.setSelection(false);
		}

		Label lblNewLabel_12 = new Label(composite_1, SWT.NONE);
		lblNewLabel_12.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_12.setBounds(167, 76, 18, 12);
		lblNewLabel_12.setText("\u4ECE");

		final DateTime startTime_4 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_4.setLocation(191, 74);
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

		Label lblNewLabel_13 = new Label(composite_1, SWT.NONE);
		lblNewLabel_13.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_13.setBounds(281, 76, 18, 12);
		lblNewLabel_13.setText("\u5230");

		final DateTime startTime_5 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_5.setLocation(312, 74);
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
	
		lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setBounds(26, 98, 54, 16);
		lblNewLabel_4.setText("星期三");

		btnCheckButton_3 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_3.setBackground(EccTreeControl.color);
		btnCheckButton_3.setBounds(113, 96, 45, 16);
		btnCheckButton_3.setText("\u5141\u8BB8");
		if(lblNewLabel_4.getText().equals(date_3)&&permission_3.equals("允许")){
			btnCheckButton_3.setSelection(true);
		}else if(lblNewLabel_4.getText().equals(date_3)&&permission_3.equals("禁止")){
			btnCheckButton_3.setSelection(false);
		}

		Label lblNewLabel_14 = new Label(composite_1, SWT.NONE);
		lblNewLabel_14.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_14.setBounds(167, 98, 18, 12);
		lblNewLabel_14.setText("\u4ECE");

		final DateTime startTime_6 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_6.setLocation(191, 95);
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

		Label lblNewLabel_18 = new Label(composite_1, SWT.NONE);
		lblNewLabel_18.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_18.setBounds(281, 98, 18, 12);
		lblNewLabel_18.setText("\u5230");

		final DateTime startTime_7 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_7.setLocation(312, 95);
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

		lblNewLabel_5 = new Label(composite_1, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_5.setBounds(26, 120, 54, 16);
		lblNewLabel_5.setText("星期四");

		btnCheckButton_4 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_4.setBounds(113, 118, 45, 16);
		btnCheckButton_4.setText("\u5141\u8BB8 ");
		if(lblNewLabel_5.getText().equals(date_4)&&permission_4.equals("允许")){
			btnCheckButton_4.setSelection(true);
		}else if(lblNewLabel_5.getText().equals(date_4)&&permission_4.equals("禁止")){
			btnCheckButton_4.setSelection(false);
		}

		Label lblNewLabel_15 = new Label(composite_1, SWT.NONE);
		lblNewLabel_15.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_15.setBounds(167, 120, 18, 12);
		lblNewLabel_15.setText("\u4ECE");

		final DateTime startTime_8 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_8.setLocation(191, 116);
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

		Label lblNewLabel_19 = new Label(composite_1, SWT.NONE);
		lblNewLabel_19.setBackground(EccTreeControl.color);
		lblNewLabel_19.setBounds(281, 120, 18, 12);
		lblNewLabel_19.setText("\u5230");

		final DateTime startTime_9 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_9.setLocation(312, 116);
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
			
		lblNewLabel_6 = new Label(composite_1, SWT.NONE);
		lblNewLabel_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_6.setBounds(26, 139, 54, 12);
		lblNewLabel_6.setText("星期五");

		btnCheckButton_5 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_5.setBackground(EccTreeControl.color);
		btnCheckButton_5.setBounds(113, 158, 45, 16);
		btnCheckButton_5.setText("\u5141\u8BB8");
		if(lblNewLabel_6.getText().equals(date_5)&&permission_5.equals("允许")){
			btnCheckButton_5.setSelection(true);
		}else if(lblNewLabel_6.getText().equals(date_5)&&permission_5.equals("禁止")){
			btnCheckButton_5.setSelection(false);
		}

		Label lblNewLabel_16 = new Label(composite_1, SWT.NONE);
		lblNewLabel_16.setBackground(EccTreeControl.color);
		lblNewLabel_16.setBounds(167, 160, 18, 12);
		lblNewLabel_16.setText("\u4ECE");
		
		final DateTime startTime_10 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_10.setLocation(191, 159);
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

		Label lblNewLabel_20 = new Label(composite_1, SWT.NONE);
		lblNewLabel_20.setBackground(EccTreeControl.color);
		lblNewLabel_20.setBounds(281, 138, 18, 12);
		lblNewLabel_20.setText("\u5230");

		final DateTime startTime_11 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_11.setLocation(312, 137);
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

		lblNewLabel_7 = new Label(composite_1, SWT.NONE);
		lblNewLabel_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_7.setBounds(26, 160, 54, 16);
		lblNewLabel_7.setText("星期六");
		
		btnCheckButton_6 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_6.setBackground(EccTreeControl.color);
		btnCheckButton_6.setBounds(113, 140, 45, 16);
		btnCheckButton_6.setText("\u5141\u8BB8");
		if(lblNewLabel_7.getText().equals(date_6)&&permission_6.equals("允许")){
			btnCheckButton_6.setSelection(true);
		}else if(lblNewLabel_7.getText().equals(date_6)&&permission_6.equals("禁止")){
			btnCheckButton_6.setSelection(false);
		}

		Label lblNewLabel_17 = new Label(composite_1, SWT.NONE);
		lblNewLabel_17.setBackground(EccTreeControl.color);
		lblNewLabel_17.setBounds(167, 139, 18, 12);
		lblNewLabel_17.setText("\u4ECE");

		final DateTime startTime_12 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_12.setLocation(191, 136);
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

		 Label lblNewLabel_21 = new Label(composite_1, SWT.NONE);
		 lblNewLabel_21.setBackground(EccTreeControl.color);
		 lblNewLabel_21.setBounds(281, 160, 18, 12);
		 lblNewLabel_21.setText("\u5230");

		final DateTime startTime_13 = new DateTime(composite_1, SWT.TIME
					| SWT.SHORT);
			startTime_13.setLocation(312, 159);
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
					new SiteviewValue(lblNewLabel_1.getText()+":"+permission+";"+lblNewLabel_2.getText()+":"+permission_1+";"+
							lblNewLabel_3.getText()+":"+permission_2+";"+lblNewLabel_4.getText()+":"+permission_3+";"+
							lblNewLabel_5.getText()+":"+permission_4+";"+lblNewLabel_6.getText()+":"+permission_5+";"+
							lblNewLabel_7.getText()+":"+permission_6));
			bo.GetField("StatrtTime").SetValue(//更新开始时间的数据
					new SiteviewValue(lblNewLabel_1.getText()+","+startTimeStr+";"+lblNewLabel_2.getText()+","+startTimeStr_2+";"+
							lblNewLabel_3.getText()+","+startTimeStr_4+";"+lblNewLabel_4.getText()+","+startTimeStr_6+";"+
							lblNewLabel_5.getText()+","+startTimeStr_8+";"+lblNewLabel_6.getText()+","+startTimeStr_10+";"+
							lblNewLabel_7.getText()+","+startTimeStr_12));
			bo.GetField("EndTime").SetValue(new SiteviewValue(lblNewLabel_1.getText()+","+startTimeStr_1+";"+lblNewLabel_2.getText()+","+startTimeStr_3+";"+
					lblNewLabel_3.getText()+","+startTimeStr_5+";"+lblNewLabel_4.getText()+","+startTimeStr_7+";"+
					lblNewLabel_5.getText()+","+startTimeStr_9+";"+lblNewLabel_6.getText()+","+startTimeStr_11+";"+
					lblNewLabel_7.getText()+","+startTimeStr_13));//更新结束时间的数据
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//将修改后的数据存储到数据库
			item.setText(1, text_1.getText());
		}
		this.close();
	}
}
