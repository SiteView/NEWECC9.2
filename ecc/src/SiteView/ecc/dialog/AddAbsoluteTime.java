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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import swing2swt.layout.BorderLayout;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import SiteView.ecc.editors.AbsoluteTime;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import Siteview.Windows.Forms.MessageBox;

public class AddAbsoluteTime extends Dialog{
	public BusinessObject bo;
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
	private String title="添加绝对时间任务计划";
	private Text text;
	private Text text1;
	public String permission;
	public String permission_1;
	public String permission_2;
	public String permission_3;
	public String permission_4;
	public String permission_5;
	public String permission_6;
	public Label lblNewLabel_1;
	public Label lblNewLabel_2;
	public Label lblNewLabel_3;
	public Label lblNewLabel_4;
	public Label lblNewLabel_5;
	public Label lblNewLabel_6;
	public Label lblNewLabel_7;
	public AddAbsoluteTime(Shell parentShell) {
		super(parentShell);
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
		text = new Text(composite_1, SWT.BORDER);
		text.setLocation(137, 0);
		text.setSize(177, 18);
		
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
		
		btnCheckButton= new Button(composite_2, SWT.CHECK);
		btnCheckButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton.setBounds(114, 8, 54, 16);
		btnCheckButton.setText("\u5141\u8BB8");
		
		final DateTime startTime = new DateTime(composite_2, SWT.TIME
		| SWT.SHORT);
        startTime.setLocation(205, 10);
        startTime.setSize(79, 15);
        if(startTime.getHours()<10){
			startTimeStr="0"+startTime.getHours()+":";
		}else{
			startTimeStr=startTime.getHours()+":";
		}
		if(startTime.getMinutes()<10){
			startTimeStr+="0"+startTime.getMinutes()+":";
		}else{
			startTimeStr+=startTime.getMinutes()+":";
		}
		if(startTime.getSeconds()<10){
			startTimeStr+="0"+startTime.getMinutes();
		}else{
			startTimeStr+=startTime.getMinutes()+"";
		}
		
          startTime.addSelectionListener(new SelectionListener() {
	    public void widgetSelected(SelectionEvent e) {
	    	if(startTime.getHours()<10){
				startTimeStr="0"+startTime.getHours()+":";
			}else{
				startTimeStr=startTime.getHours()+":";
			}
			if(startTime.getMinutes()<10){
				startTimeStr+="0"+startTime.getMinutes()+":";
			}else{
				startTimeStr+=startTime.getMinutes()+":";
			}
			if(startTime.getSeconds()<10){
				startTimeStr+="0"+startTime.getMinutes();
			}else{
				startTimeStr+=startTime.getMinutes()+"";
			}
	           }
	   public void widgetDefaultSelected(SelectionEvent e) {
		
	           }
               });
		
		
      lblNewLabel_2 = new Label(composite_2, SWT.NONE);
      lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
      lblNewLabel_2.setBounds(20, 32, 54, 16);
      lblNewLabel_2.setText("星期一");

	  btnCheckButton_1 = new Button(composite_2, SWT.CHECK);
	  btnCheckButton_1.setBounds(114, 30, 45, 16);
	  btnCheckButton_1.setBackground(EccTreeControl.color);
	  btnCheckButton_1.setText("\u5141\u8BB8");

	  final DateTime startTime_1 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
	  startTime_1.setLocation(205, 31);
	  startTime_1.setSize(79, 15);
	  if(startTime_1.getHours()<10){
		  startTimeStr_1="0"+startTime_1.getHours()+":";
		}else{
			startTimeStr_1=startTime_1.getHours()+":";
		}
		if(startTime_1.getMinutes()<10){
			startTimeStr_1+="0"+startTime_1.getMinutes()+":";
		}else{
			startTimeStr_1+=startTime_1.getMinutes()+":";
		}
		if(startTime_1.getSeconds()<10){
			startTimeStr_1+="0"+startTime_1.getMinutes();
		}else{
			startTimeStr_1+=startTime_1.getMinutes()+"";
		}
	  startTime_1.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				 if(startTime_1.getHours()<10){
					  startTimeStr_1="0"+startTime_1.getHours()+":";
					}else{
						startTimeStr_1=startTime_1.getHours()+":";
					}
					if(startTime_1.getMinutes()<10){
						startTimeStr_1+="0"+startTime_1.getMinutes()+":";
					}else{
						startTimeStr_1+=startTime_1.getMinutes()+":";
					}
					if(startTime_1.getSeconds()<10){
						startTimeStr_1+="0"+startTime_1.getMinutes();
					}else{
						startTimeStr_1+=startTime_1.getMinutes()+"";
					}
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
		btnCheckButton_2.setBounds(114, 52, 45, 16);
		btnCheckButton_2.setText("\u5141\u8BB8");

		final DateTime startTime_2 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_2.setLocation(205, 52);
		startTime_2.setSize(79, 15);
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
		btnCheckButton_3.setBounds(114, 74, 45, 16);
		btnCheckButton_3.setText("\u5141\u8BB8");

		final DateTime startTime_3 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_3.setLocation(205, 73);
		startTime_3.setSize(79, 15);
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
		button.setBounds(114, 96, 45, 16);

		final DateTime startTime_4 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_4.setLocation(205, 94);
		startTime_4.setSize(79, 15);
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
		btnCheckButton_4.setBounds(114, 118, 45, 16);
		btnCheckButton_4.setText("\u5141\u8BB8");

		final DateTime startTime_5 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_5.setLocation(205, 115);
		startTime_5.setSize(79, 15);
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
		btnCheckButton_5.setBounds(114, 140, 45, 16);
		btnCheckButton_5.setText("\u5141\u8BB8");

		final DateTime startTime_6 = new DateTime(composite_2, SWT.TIME
				| SWT.SHORT);
		startTime_6.setLocation(205, 136);
		startTime_6.setSize(79, 15);
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
		
		text1=new Text(composite2, SWT.WRAP | SWT.BORDER);
		text1.setLayoutData(BorderLayout.CENTER);
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "保存",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", true);
	}
	
	
	protected void buttonPressed(int buttonId) {
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
			
			
			ICollection ico=FileTools.getBussCollection("EccTaskPlan");
			IEnumerator ienum=ico.GetEnumerator();
			while(ienum.MoveNext()){
				BusinessObject businessObject=(BusinessObject)ienum.get_Current();
				if(businessObject!=null){
					String taskName=businessObject.GetField("TaskName").get_NativeValue().toString();
					String model=businessObject.GetField("Model").get_NativeValue().toString();
					if(text.getText().equals(taskName)&&"绝对时间任务计划".equals(model)){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("任务计划名称已存在!", "提示", SWT.OK);
						return;
					}
					if(text.getText().equals(taskName)){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("该任务计划名称已在别的任务计划中存在!", "提示", SWT.OK);
						return;
					}
					if(text.getText().isEmpty()){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("任务计划名称不能为空!", "提示", SWT.OK);
						return;
					} 
				}
			}
			
			bo = ConnectionBroker.get_SiteviewApi()//得到数据库表
					.get_BusObService().Create("EccTaskPlan");
			bo.GetField("TaskName").SetValue(
					new SiteviewValue(text.getText()));
			bo.GetField("Instruction").SetValue(
					new SiteviewValue(text1.getText()));
			bo.GetField("Model").SetValue(
					new SiteviewValue("绝对时间任务计划"));//类型
			bo.GetField("StatrtTime").SetValue(//得到开始时间的数据
					new SiteviewValue(lblNewLabel_1.getText()+","+startTimeStr+";"+lblNewLabel_2.getText()+","+startTimeStr_1+";"+lblNewLabel_3.getText()+","+startTimeStr_2+";"
							+lblNewLabel_4.getText()+","+startTimeStr_3+";"+lblNewLabel_5.getText()+","+startTimeStr_4+";"+
							lblNewLabel_6.getText()+","+startTimeStr_5+";"+lblNewLabel_7.getText()+","+startTimeStr_6));
			bo.GetField("Status").SetValue(//得到是否允许的数据
					new SiteviewValue(lblNewLabel_1.getText()+":"+permission+";"+lblNewLabel_2.getText()+":"+permission_1+";"+
							lblNewLabel_3.getText()+":"+permission_2+";"+lblNewLabel_4.getText()+":"+permission_3+";"+
							lblNewLabel_5.getText()+":"+permission_4+";"+lblNewLabel_6.getText()+":"+permission_5+";"+
							lblNewLabel_7.getText()+":"+permission_6));
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//将数据存储到数据
			
			AbsoluteTime.addAbsoluteData();//刷新表单
		}
		this.close();

	}
}
	
