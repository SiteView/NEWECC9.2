package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import swing2swt.layout.BorderLayout;
import system.Collections.ICollection;
import system.Collections.IEnumerator;


public class AbsoluteTimeEditor extends Dialog{
	private String title="编辑绝对时间任务计划";
	public Label lblNewLabel_1;
	public Label lblNewLabel_2;
	public Label lblNewLabel_3;
	public Label lblNewLabel_4;
	public Label lblNewLabel_5;
	public Label lblNewLabel_6;
	public Label lblNewLabel_7;
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
		parent.setBackground(EccTreeControl.color);
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		TabFolder tabFolder=new TabFolder(composite, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		TabItem basicItem=new TabItem(tabFolder, SWT.NONE);
		basicItem.setText("基本");
		
		Composite composite1 = new Composite(tabFolder, SWT.NONE);
		composite1.setBackground(EccTreeControl.color);
		basicItem.setControl(composite1);
		
		SashForm sashForm = new SashForm(composite1, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		sashForm.setSize(445, 211);
		sashForm.setLocation(0, 0);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		
		ICollection ico=FileTools.getBussCollection("TaskName",name, "EccTaskPlan");
		IEnumerator ien=ico.GetEnumerator();
		while(ien.MoveNext()){
			bo=(BusinessObject)ien.get_Current();
			if(bo!=null&&bo.GetField("Model").get_NativeValue().toString().equals("绝对时间任务计划")){
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
		text.setEnabled(false);

		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		lblNewLabel.setBounds(10, 2, 102, 18);
		lblNewLabel.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*:");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(20, 10, 54, 16);
		lblNewLabel_1.setText("星期日");
		
		btnCheckButton = new Button(composite_2, SWT.CHECK);
		btnCheckButton.setBounds(102, 8, 45, 16);
		btnCheckButton.setBackground(EccTreeControl.color);
		btnCheckButton.setText("\u5141\u8BB8");
		if(lblNewLabel_1.getText().equals(date)&&permission.equals("允许")){
				btnCheckButton.setSelection(true);
		}else if(lblNewLabel_1.getText().equals(date)&&permission.equals("禁止")){
				btnCheckButton.setSelection(false);
			}
			
		final DateTime startTime = new DateTime(composite_2, SWT.TIME
					| SWT.SHORT);
			startTime.setLocation(164, 10);
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
			
		
		
		lblNewLabel_2 = new Label(composite_2, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(20, 32, 54, 16);
		lblNewLabel_2.setText("星期一");
		      
		btnCheckButton_1 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_1.setBounds(102, 30, 45, 16);
		btnCheckButton_1.setBackground(EccTreeControl.color);
		btnCheckButton_1.setText("\u5141\u8BB8");
		if(lblNewLabel_2.getText().equals(date_1)&&permission_1.equals("允许")){
			btnCheckButton_1.setSelection(true);
		}else if(lblNewLabel_2.getText().equals(date_1)&&permission_1.equals("禁止")){
			btnCheckButton_1.setSelection(false);
		}
		
		final DateTime startTime_1 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_1.setLocation(164, 31);
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
		
		lblNewLabel_3 = new Label(composite_2, SWT.NONE);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_3.setBounds(20, 54, 54, 16);
		lblNewLabel_3.setText("星期二");
		
		btnCheckButton_2 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_2.setBackground(EccTreeControl.color);
		btnCheckButton_2.setBounds(102, 51, 45, 16);
		btnCheckButton_2.setText("\u5141\u8BB8");
		if(lblNewLabel_3.getText().equals(date_2)&&permission_2.equals("允许")){
			btnCheckButton_2.setSelection(true);
		}else if(lblNewLabel_3.getText().equals(date_2)&&permission_2.equals("禁止")){
			btnCheckButton_2.setSelection(false);
		}
		
		final DateTime startTime_2 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_2.setLocation(164, 52);
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
		 
		 
		lblNewLabel_4 = new Label(composite_2, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setBounds(20, 76, 54, 16);
		lblNewLabel_4.setText("星期三");
		
		btnCheckButton_3 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_3.setBackground(EccTreeControl.color);
		btnCheckButton_3.setBounds(102, 71, 45, 16);
		btnCheckButton_3.setText("\u5141\u8BB8");
		if(lblNewLabel_4.getText().equals(date_3)&&permission_3.equals("允许")){
			btnCheckButton_3.setSelection(true);
		}else if(lblNewLabel_4.getText().equals(date_3)&&permission_3.equals("禁止")){
			btnCheckButton_3.setSelection(false);
		}
		
		final DateTime startTime_3 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_3.setLocation(164, 73);
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
		
		lblNewLabel_5 = new Label(composite_2, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_5.setBounds(20, 98, 54, 16);
		lblNewLabel_5.setText("星期四");
		
		button = new Button(composite_2, SWT.CHECK);
		button.setBackground(EccTreeControl.color);
		button.setText("\u5141\u8BB8");
		button.setBounds(102, 93, 45, 16);
		if(lblNewLabel_5.getText().equals(date_4)&&permission_4.equals("允许")){
			button.setSelection(true);
		}else if(lblNewLabel_5.getText().equals(date_4)&&permission_4.equals("禁止")){
			button.setSelection(false);
		}
		
		final DateTime startTime_4 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_4.setLocation(164, 94);
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
		 
		lblNewLabel_6 = new Label(composite_2, SWT.NONE);
		lblNewLabel_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_6.setBounds(20, 120, 54, 12);
		lblNewLabel_6.setText("星期五");
		btnCheckButton_4 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_4.setBackground(EccTreeControl.color);
		btnCheckButton_4.setBounds(102, 115, 45, 16);
		btnCheckButton_4.setText("\u5141\u8BB8");
		if(lblNewLabel_6.getText().equals(date_5)&&permission_5.equals("允许")){
			btnCheckButton_4.setSelection(true);
		}else if(lblNewLabel_6.getText().equals(date_5)&&permission_5.equals("禁止")){
			btnCheckButton_4.setSelection(false);
		}
		
		final DateTime startTime_5 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_5.setLocation(164, 115);
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
		
		lblNewLabel_7 = new Label(composite_2, SWT.NONE);
		lblNewLabel_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_7.setBounds(20, 142, 54, 16);
		lblNewLabel_7.setText("星期六");
		
		btnCheckButton_5 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_5.setBackground(EccTreeControl.color);
		btnCheckButton_5.setBounds(102, 137, 45, 16);
		btnCheckButton_5.setText("\u5141\u8BB8");
		if(lblNewLabel_7.getText().equals(date_6)&&permission_6.equals("允许")){
			btnCheckButton_5.setSelection(true);
		}else if(lblNewLabel_7.getText().equals(date_6)&&permission_6.equals("禁止")){
			btnCheckButton_5.setSelection(false);
		}
		
		final DateTime startTime_6 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_6.setLocation(164, 136);
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
		 
		sashForm.setWeights(new int[] {1,8});
		 
		TabItem describeItem=new TabItem(tabFolder, SWT.NONE);
		describeItem.setText("描述");
		
		Composite composite2 = new Composite(tabFolder, SWT.NONE);
		composite2.setBackground(EccTreeControl.color);
		describeItem.setControl(composite2);
		composite2.setLayout(new BorderLayout(0, 0));
		
		Label label = new Label(composite2, SWT.HORIZONTAL);
		label.setAlignment(SWT.CENTER);
		label.setBackground(EccTreeControl.color);
		label.setBounds(0, 0, 54, 199);
		label.setLayoutData(BorderLayout.WEST);
		label.setText("\u63CF\u8FF0\uFF1A");
		
		text1=new Text(composite2, SWT.WRAP | SWT.BORDER);//描述
		text1.setLayoutData(BorderLayout.CENTER);
		text1.setText(Instruction);
		
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
			
			bo.GetField("Instruction").SetValue(new SiteviewValue(text1.getText()));//更新描述 
			bo.GetField("Status").SetValue(//更新是否允许的数据
					new SiteviewValue(lblNewLabel_1.getText()+":"+permission+";"+lblNewLabel_2.getText()+":"+permission_1+";"+
							lblNewLabel_3.getText()+":"+permission_2+";"+lblNewLabel_4.getText()+":"+permission_3+";"+
							lblNewLabel_5.getText()+":"+permission_4+";"+lblNewLabel_6.getText()+":"+permission_5+";"+
							lblNewLabel_7.getText()+":"+permission_6));
			bo.GetField("StatrtTime").SetValue(//更新开始时间的数据
					new SiteviewValue(lblNewLabel_1.getText()+","+startTimeStr+";"+lblNewLabel_2.getText()+","+startTimeStr_1+";"+lblNewLabel_3.getText()+","+startTimeStr_2+";"
							+lblNewLabel_4.getText()+","+startTimeStr_3+";"+lblNewLabel_5.getText()+","+startTimeStr_4+";"+
							lblNewLabel_6.getText()+","+startTimeStr_5+";"+lblNewLabel_7.getText()+","+startTimeStr_6));
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,//将修改后的数据存储到数据库
					true); 
			
			item.setText(1, text1.getText());//更新列表
		}
		this.close();
	}
}
