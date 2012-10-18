package SiteView.ecc.dialog;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
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
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

import SiteView.ecc.editors.AbsoluteTime;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import Siteview.Windows.Forms.MessageBox;

public class AddTimeQuantum extends Dialog{
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
	private String title="添加时间段任务计划";
	private Text text_1;
	private Text text_2;
	public BusinessObject bo;
	public Button btnCheckButton;
	public Button btnCheckButton_1;
	public Button btnCheckButton_2;
	public Button btnCheckButton_3;
	public Button btnCheckButton_4;
	public Button btnCheckButton_5;
	public Button btnCheckButton_6;
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
	public AddTimeQuantum(Shell parentShell) {
		super(parentShell);
		
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(420, 315);
		newShell.setLocation(450, 175);
		newShell.setText(title);
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent){
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		TabFolder tabFolder=new TabFolder(composite, SWT.NONE);
		
		TabItem basicItem=new TabItem(tabFolder, SWT.NONE);
		basicItem.setText("基本");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		basicItem.setControl(composite_1);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBackground(EccTreeControl.color);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		lblNewLabel.setBounds(23, 11, 107, 15);
		lblNewLabel.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*:");
		
		text_2 = new Text(composite_1, SWT.BORDER);//任务计划名称
		text_2.setBounds(145, 9, 201, 18);
		
		lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(33, 35, 54, 16);
		lblNewLabel_1.setText("星期日");
		
		btnCheckButton = new Button(composite_1, SWT.CHECK);
		btnCheckButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton.setBounds(93, 32, 45, 16);
		btnCheckButton.setText("\u5141\u8BB8");
		
		Label lblNewLabel_8 = new Label(composite_1, SWT.NONE);
		lblNewLabel_8.setBackground(EccTreeControl.color);
		lblNewLabel_8.setBounds(155, 35, 18, 12);
		lblNewLabel_8.setText("\u4ECE");

		final DateTime startTime = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime.setLocation(179, 33);
		startTime.setSize(79, 15);
		startTime.setTime(00, 00, 00);
//		startTimeStr=startTime.getHours() + ":"
//				+ startTime.getMinutes() + ":" + startTime.getSeconds();
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
//            startTimeStr=startTime.getHours() + ":"
//    				+ startTime.getMinutes() + ":" + startTime.getSeconds();
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
		
		Label lblNewLabel_9 = new Label(composite_1, SWT.NONE);
		lblNewLabel_9.setBackground(EccTreeControl.color);
		lblNewLabel_9.setBounds(271, 35, 18, 12);
		lblNewLabel_9.setText("\u5230");

		final DateTime startTime_1 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_1.setLocation(295, 33);
		startTime_1.setSize(79, 15);
		startTime_1.setTime(23, 59, 00);
//		startTimeStr_1=startTime_1.getHours() + ":"
//				+ startTime_1.getMinutes() + ":" + startTime_1.getSeconds();
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
//				startTimeStr_1=startTime_1.getHours() + ":"
//						+ startTime_1.getMinutes() + ":" + startTime_1.getSeconds();
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

		lblNewLabel_2 = new Label(composite_1, SWT.NONE);
	    lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    lblNewLabel_2.setBounds(33, 57, 54, 16);
	    lblNewLabel_2.setText("星期一");
	    
		btnCheckButton_1 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_1.setBounds(93, 55, 45, 16);
		btnCheckButton_1.setText("\u5141\u8BB8");

		Label lblNewLabel_10 = new Label(composite_1, SWT.NONE);
		lblNewLabel_10.setBackground(EccTreeControl.color);
		lblNewLabel_10.setBounds(155, 57, 18, 12);
		lblNewLabel_10.setText("\u4ECE");

		final DateTime startTime_2 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_2.setLocation(179, 54);
		startTime_2.setSize(79, 15);
		startTime_2.setTime(00, 00, 00);
//		startTimeStr_2=startTime_2.getHours() + ":"
//				+ startTime_2.getMinutes() + ":" + startTime_2.getSeconds();
		if(startTime_2.getHours()<10){
			startTimeStr_2="0"+startTime_2.getHours()+":";
		}else{
			startTimeStr_2=startTime_2.getHours()+":";
		}
		if(startTime_2.getMinutes()<10){
			startTimeStr_2+="0"+startTime_2.getMinutes()+":";
		}else{
			startTimeStr_2+=startTime_2.getMinutes()+":";
		}
		if(startTime_2.getSeconds()<10){
			startTimeStr_2+="0"+startTime_2.getMinutes();
		}else{
			startTimeStr_2+=startTime_2.getMinutes()+"";
		}
		startTime_2.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_2=startTime_2.getHours() + ":"
//						+ startTime_2.getMinutes() + ":" + startTime_2.getSeconds();
				if(startTime_2.getHours()<10){
					startTimeStr_2="0"+startTime_2.getHours()+":";
				}else{
					startTimeStr_2=startTime_2.getHours()+":";
				}
				if(startTime_2.getMinutes()<10){
					startTimeStr_2+="0"+startTime_2.getMinutes()+":";
				}else{
					startTimeStr_2+=startTime_2.getMinutes()+":";
				}
				if(startTime_2.getSeconds()<10){
					startTimeStr_2+="0"+startTime_2.getMinutes();
				}else{
					startTimeStr_2+=startTime_2.getMinutes()+"";
				}	
				
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});

		Label lblNewLabel_11 = new Label(composite_1, SWT.NONE);
		lblNewLabel_11.setBackground(EccTreeControl.color);
		lblNewLabel_11.setBounds(271, 57, 18, 12);
		lblNewLabel_11.setText("\u5230");
		
		final DateTime startTime_3 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_3.setLocation(295, 54);
		startTime_3.setSize(79, 15);
		startTime_3.setTime(23, 59, 00);
//		startTimeStr_3=startTime_3.getHours() + ":"
//				+ startTime_3.getMinutes() + ":" + startTime_3.getSeconds();
		if(startTime_3.getHours()<10){
			startTimeStr_3="0"+startTime_3.getHours()+":";
		}else{
			startTimeStr_3=startTime_3.getHours()+":";
		}
		if(startTime_3.getMinutes()<10){
			startTimeStr_3+="0"+startTime_3.getMinutes()+":";
		}else{
			startTimeStr_3+=startTime_3.getMinutes()+":";
		}
		if(startTime_3.getSeconds()<10){
			startTimeStr_3+="0"+startTime_3.getMinutes();
		}else{
			startTimeStr_3+=startTime_3.getMinutes()+"";
		}
		startTime_3.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_3=startTime_3.getHours() + ":"
//						+ startTime_3.getMinutes() + ":" + startTime_3.getSeconds();
				if(startTime_3.getHours()<10){
					startTimeStr_3="0"+startTime_3.getHours()+":";
				}else{
					startTimeStr_3=startTime_3.getHours()+":";
				}
				if(startTime_3.getMinutes()<10){
					startTimeStr_3+="0"+startTime_3.getMinutes()+":";
				}else{
					startTimeStr_3+=startTime_3.getMinutes()+":";
				}
				if(startTime_3.getSeconds()<10){
					startTimeStr_3+="0"+startTime_3.getMinutes();
				}else{
					startTimeStr_3+=startTime_3.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		lblNewLabel_3 = new Label(composite_1, SWT.NONE);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_3.setBounds(33, 79, 54, 16);
		lblNewLabel_3.setText("星期二");

		btnCheckButton_2 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_2.setBounds(93, 77, 45, 16);
		btnCheckButton_2.setText("\u5141\u8BB8");

		Label lblNewLabel_12 = new Label(composite_1, SWT.NONE);
		lblNewLabel_12.setBackground(EccTreeControl.color);
		lblNewLabel_12.setBounds(155, 79, 18, 12);
		lblNewLabel_12.setText("\u4ECE");

		final DateTime startTime_4 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_4.setLocation(179, 75);
		startTime_4.setSize(79, 15);
		startTime_4.setTime(00, 00, 00);
//		startTimeStr_4=startTime_4.getHours() + ":"
//				+ startTime_4.getMinutes() + ":" + startTime_4.getSeconds();
		if(startTime_4.getHours()<10){
			startTimeStr_4="0"+startTime_4.getHours()+":";
		}else{
			startTimeStr_4=startTime_4.getHours()+":";
		}
		if(startTime_4.getMinutes()<10){
			startTimeStr_4+="0"+startTime_4.getMinutes()+":";
		}else{
			startTimeStr_4+=startTime_4.getMinutes()+":";
		}
		if(startTime_4.getSeconds()<10){
			startTimeStr_4+="0"+startTime_4.getMinutes();
		}else{
			startTimeStr_4+=startTime_4.getMinutes()+"";
		}
		startTime_4.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_4=startTime_4.getHours() + ":"
//						+ startTime_4.getMinutes() + ":" + startTime_4.getSeconds();
				if(startTime_4.getHours()<10){
					startTimeStr_4="0"+startTime_4.getHours()+":";
				}else{
					startTimeStr_4=startTime_4.getHours()+":";
				}
				if(startTime_4.getMinutes()<10){
					startTimeStr_4+="0"+startTime_4.getMinutes()+":";
				}else{
					startTimeStr_4+=startTime_4.getMinutes()+":";
				}
				if(startTime_4.getSeconds()<10){
					startTimeStr_4+="0"+startTime_4.getMinutes();
				}else{
					startTimeStr_4+=startTime_4.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});

		Label lblNewLabel_13 = new Label(composite_1, SWT.NONE);
		lblNewLabel_13.setBackground(EccTreeControl.color);
		lblNewLabel_13.setBounds(271, 79, 18, 12);
		lblNewLabel_13.setText("\u5230");

		final DateTime startTime_5 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_5.setLocation(295, 75);
		startTime_5.setSize(79, 15);
		startTime_5.setTime(23, 59, 00);
//		startTimeStr_5=startTime_5.getHours() + ":"
//				+ startTime_5.getMinutes() + ":" + startTime_5.getSeconds();
		if(startTime_5.getHours()<10){
			startTimeStr_5="0"+startTime_5.getHours()+":";
		}else{
			startTimeStr_5=startTime_5.getHours()+":";
		}
		if(startTime_5.getMinutes()<10){
			startTimeStr_5+="0"+startTime_5.getMinutes()+":";
		}else{
			startTimeStr_5+=startTime_5.getMinutes()+":";
		}
		if(startTime_5.getSeconds()<10){
			startTimeStr_5+="0"+startTime_5.getMinutes();
		}else{
			startTimeStr_5+=startTime_5.getMinutes()+"";
		}
		startTime_5.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_5=startTime_5.getHours() + ":"
//						+ startTime_5.getMinutes() + ":" + startTime_5.getSeconds();
				if(startTime_5.getHours()<10){
					startTimeStr_5="0"+startTime_5.getHours()+":";
				}else{
					startTimeStr_5=startTime_5.getHours()+":";
				}
				if(startTime_5.getMinutes()<10){
					startTimeStr_5+="0"+startTime_5.getMinutes()+":";
				}else{
					startTimeStr_5+=startTime_5.getMinutes()+":";
				}
				if(startTime_5.getSeconds()<10){
					startTimeStr_5+="0"+startTime_5.getMinutes();
				}else{
					startTimeStr_5+=startTime_5.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});

		lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setBounds(33, 101, 54, 16);
		lblNewLabel_4.setText("星期三");
		
		btnCheckButton_3 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_3.setBounds(93, 99, 45, 16);
		btnCheckButton_3.setText("\u5141\u8BB8");

		Label lblNewLabel_14 = new Label(composite_1, SWT.NONE);
		lblNewLabel_14.setBackground(EccTreeControl.color);
		lblNewLabel_14.setBounds(155, 101, 18, 12);
		lblNewLabel_14.setText("\u4ECE");

		final DateTime startTime_6 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_6.setLocation(179, 96);
		startTime_6.setSize(79, 15);
		startTime_6.setTime(00, 00, 00);
//		startTimeStr_6=startTime_6.getHours() + ":"
//				+ startTime_6.getMinutes() + ":" + startTime_6.getSeconds();
		if(startTime_6.getHours()<10){
			startTimeStr_6="0"+startTime_6.getHours()+":";
		}else{
			startTimeStr_6=startTime_6.getHours()+":";
		}
		if(startTime_6.getMinutes()<10){
			startTimeStr_6+="0"+startTime_6.getMinutes()+":";
		}else{
			startTimeStr_6+=startTime_6.getMinutes()+":";
		}
		if(startTime_6.getSeconds()<10){
			startTimeStr_6+="0"+startTime_6.getMinutes();
		}else{
			startTimeStr_6+=startTime_6.getMinutes()+"";
		}
		startTime_6.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_6=startTime_6.getHours() + ":"
//						+ startTime_6.getMinutes() + ":" + startTime_6.getSeconds();
				if(startTime_6.getHours()<10){
					startTimeStr_6="0"+startTime_6.getHours()+":";
				}else{
					startTimeStr_6=startTime_6.getHours()+":";
				}
				if(startTime_6.getMinutes()<10){
					startTimeStr_6+="0"+startTime_6.getMinutes()+":";
				}else{
					startTimeStr_6+=startTime_6.getMinutes()+":";
				}
				if(startTime_6.getSeconds()<10){
					startTimeStr_6+="0"+startTime_6.getMinutes();
				}else{
					startTimeStr_6+=startTime_6.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Label lblNewLabel_18 = new Label(composite_1, SWT.NONE);
		lblNewLabel_18.setBackground(EccTreeControl.color);
		lblNewLabel_18.setBounds(271, 101, 18, 12);
		lblNewLabel_18.setText("\u5230");

		final DateTime startTime_7 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_7.setLocation(295, 96);
		startTime_7.setSize(79, 15);
		startTime_7.setTime(23, 59, 00);
//		startTimeStr_7=startTime_7.getHours() + ":"
//				+ startTime_7.getMinutes() + ":" + startTime_7.getSeconds();
		if(startTime_7.getHours()<10){
			startTimeStr_7="0"+startTime_7.getHours()+":";
		}else{
			startTimeStr_7=startTime_7.getHours()+":";
		}
		if(startTime_7.getMinutes()<10){
			startTimeStr_7+="0"+startTime_7.getMinutes()+":";
		}else{
			startTimeStr_7+=startTime_7.getMinutes()+":";
		}
		if(startTime_7.getSeconds()<10){
			startTimeStr_7+="0"+startTime_7.getMinutes();
		}else{
			startTimeStr_7=startTime_7.getMinutes()+"";
		}
		startTime_7.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_7=startTime_7.getHours() + ":"
//						+ startTime_7.getMinutes() + ":" + startTime_7.getSeconds();
				if(startTime_7.getHours()<10){
					startTimeStr_7="0"+startTime_7.getHours()+":";
				}else{
					startTimeStr_7=startTime_7.getHours()+":";
				}
				if(startTime_7.getMinutes()<10){
					startTimeStr_7+="0"+startTime_7.getMinutes()+":";
				}else{
					startTimeStr_7+=startTime_7.getMinutes()+":";
				}
				if(startTime_7.getSeconds()<10){
					startTimeStr_7+="0"+startTime_7.getMinutes();
				}else{
					startTimeStr_7=startTime_7.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
	
		lblNewLabel_5 = new Label(composite_1, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_5.setBounds(33, 123, 54, 16);
		lblNewLabel_5.setText("星期四");

		btnCheckButton_4 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_4.setBackground(EccTreeControl.color);
		btnCheckButton_4.setBounds(93, 121, 45, 16);
		btnCheckButton_4.setText("\u5141\u8BB8 ");

		Label lblNewLabel_15 = new Label(composite_1, SWT.NONE);
		lblNewLabel_15.setBackground(EccTreeControl.color);
		lblNewLabel_15.setBounds(155, 123, 18, 12);
		lblNewLabel_15.setText("\u4ECE");

		final DateTime startTime_8 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_8.setLocation(179, 117);
		startTime_8.setSize(79, 15);
		startTime_8.setTime(00, 00, 00);
//		startTimeStr_8=startTime_8.getHours() + ":"
//				+ startTime_8.getMinutes() + ":" + startTime_8.getSeconds();
		if(startTime_8.getHours()<10){
			startTimeStr_8="0"+startTime_8.getHours()+":";
		}else{
			startTimeStr_8=startTime_8.getHours()+":";
		}
		if(startTime_8.getMinutes()<10){
			startTimeStr_8+="0"+startTime_8.getMinutes()+":";
		}else{
			startTimeStr_8+=startTime_8.getMinutes()+":";
		}
		if(startTime_8.getSeconds()<10){
			startTimeStr_8+="0"+startTime_8.getMinutes();
		}else{
			startTimeStr_8+=startTime_8.getMinutes()+"";
		}
		startTime_8.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_8=startTime_8.getHours() + ":"
//						+ startTime_8.getMinutes() + ":" + startTime_8.getSeconds();
				if(startTime_8.getHours()<10){
					startTimeStr_8="0"+startTime_8.getHours()+":";
				}else{
					startTimeStr_8=startTime_8.getHours()+":";
				}
				if(startTime_8.getMinutes()<10){
					startTimeStr_8+="0"+startTime_8.getMinutes()+":";
				}else{
					startTimeStr_8+=startTime_8.getMinutes()+":";
				}
				if(startTime_8.getSeconds()<10){
					startTimeStr_8+="0"+startTime_8.getMinutes();
				}else{
					startTimeStr_8+=startTime_8.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Label lblNewLabel_19 = new Label(composite_1, SWT.NONE);
		lblNewLabel_19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_19.setBounds(271, 123, 18, 12);
		lblNewLabel_19.setText("\u5230");

		final DateTime startTime_9 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_9.setLocation(295, 117);
		startTime_9.setSize(79, 15);
		startTime_9.setTime(23, 59, 00);
//		startTimeStr_9=startTime_9.getHours() + ":"
//				+ startTime_9.getMinutes() + ":" + startTime_9.getSeconds();
		if(startTime_9.getHours()<10){
			startTimeStr_9="0"+startTime_9.getHours()+":";
		}else{
			startTimeStr_9=startTime_9.getHours()+":";
		}
		if(startTime_9.getMinutes()<10){
			startTimeStr_9+="0"+startTime_9.getMinutes()+":";
		}else{
			startTimeStr_9+=startTime_9.getMinutes()+":";
		}
		if(startTime_9.getSeconds()<10){
			startTimeStr_9+="0"+startTime_9.getMinutes();
		}else{
			startTimeStr_9=startTime_9.getMinutes()+"";
		}
		startTime_9.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_9=startTime_9.getHours() + ":"
//						+ startTime_9.getMinutes() + ":" + startTime_9.getSeconds();
				if(startTime_9.getHours()<10){
					startTimeStr_9="0"+startTime_9.getHours()+":";
				}else{
					startTimeStr_9=startTime_9.getHours()+":";
				}
				if(startTime_9.getMinutes()<10){
					startTimeStr_9+="0"+startTime_9.getMinutes()+":";
				}else{
					startTimeStr_9+=startTime_9.getMinutes()+":";
				}
				if(startTime_9.getSeconds()<10){
					startTimeStr_9+="0"+startTime_9.getMinutes();
				}else{
					startTimeStr_9=startTime_9.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});
	
		lblNewLabel_6 = new Label(composite_1, SWT.NONE);
		lblNewLabel_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_6.setBounds(33, 145, 54, 12);
		lblNewLabel_6.setText("星期五");

		btnCheckButton_5 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_5.setBounds(93, 143, 45, 16);
		btnCheckButton_5.setText("\u5141\u8BB8");

		Label lblNewLabel_16 = new Label(composite_1, SWT.NONE);
		lblNewLabel_16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_16.setBounds(155, 145, 18, 12);
		lblNewLabel_16.setText("\u4ECE");

		final DateTime startTime_10 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_10.setLocation(179, 142);
		startTime_10.setSize(79, 15);
		startTime_10.setTime(00, 00, 00);
//		startTimeStr_10=startTime_10.getHours() + ":"
//				+ startTime_10.getMinutes() + ":" + startTime_10.getSeconds();
		if(startTime_10.getHours()<10){
			startTimeStr_10="0"+startTime_10.getHours()+":";
		}else{
			startTimeStr_10=startTime_10.getHours()+":";
		}
		if(startTime_10.getMinutes()<10){
			startTimeStr_10+="0"+startTime_10.getMinutes()+":";
		}else{
			startTimeStr_10+=startTime_10.getMinutes()+":";
		}
		if(startTime_10.getSeconds()<10){
			startTimeStr_10+="0"+startTime_10.getMinutes();
		}else{
			startTimeStr_10+=startTime_10.getMinutes()+"";
		}
		startTime_10.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_10=startTime_10.getHours() + ":"
//						+ startTime_10.getMinutes() + ":" + startTime_10.getSeconds();
				if(startTime_10.getHours()<10){
					startTimeStr_10="0"+startTime_10.getHours()+":";
				}else{
					startTimeStr_10=startTime_10.getHours()+":";
				}
				if(startTime_10.getMinutes()<10){
					startTimeStr_10+="0"+startTime_10.getMinutes()+":";
				}else{
					startTimeStr_10+=startTime_10.getMinutes()+":";
				}
				if(startTime_10.getSeconds()<10){
					startTimeStr_10+="0"+startTime_10.getMinutes();
				}else{
					startTimeStr_10+=startTime_10.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});

		Label lblNewLabel_20 = new Label(composite_1, SWT.NONE);
		lblNewLabel_20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_20.setBounds(271, 167, 18, 12);
		lblNewLabel_20.setText("\u5230");

		final DateTime startTime_11 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_11.setLocation(295, 164);
		startTime_11.setSize(79, 15);
		startTime_11.setTime(23, 59, 00);
//		startTimeStr_11=startTime_11.getHours() + ":"
//				+ startTime_11.getMinutes() + ":" + startTime_11.getSeconds();
		if(startTime_11.getHours()<10){
			startTimeStr_11="0"+startTime_11.getHours()+":";
		}else{
			startTimeStr_11=startTime_11.getHours()+":";
		}
		if(startTime_11.getMinutes()<10){
			startTimeStr_11+="0"+startTime_11.getMinutes()+":";
		}else{
			startTimeStr_11+=startTime_11.getMinutes()+":";
		}
		if(startTime_11.getSeconds()<10){
			startTimeStr_11+="0"+startTime_11.getMinutes();
		}else{
			startTimeStr_11=startTime_11.getMinutes()+"";
		}
		startTime_11.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_11=startTime_11.getHours() + ":"
//						+ startTime_11.getMinutes() + ":" + startTime_11.getSeconds();
				if(startTime_11.getHours()<10){
					startTimeStr_11="0"+startTime_11.getHours()+":";
				}else{
					startTimeStr_11=startTime_11.getHours()+":";
				}
				if(startTime_11.getMinutes()<10){
					startTimeStr_11+="0"+startTime_11.getMinutes()+":";
				}else{
					startTimeStr_11+=startTime_11.getMinutes()+":";
				}
				if(startTime_11.getSeconds()<10){
					startTimeStr_11+="0"+startTime_11.getMinutes();
				}else{
					startTimeStr_11=startTime_11.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});
	
		lblNewLabel_7 = new Label(composite_1, SWT.NONE);
		lblNewLabel_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_7.setBounds(33, 167, 54, 16);
		lblNewLabel_7.setText("星期六");

		btnCheckButton_6 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_6.setBounds(93, 165, 45, 16);
		btnCheckButton_6.setText("\u5141\u8BB8");

		Label lblNewLabel_17 = new Label(composite_1, SWT.NONE);
		lblNewLabel_17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_17.setBounds(155, 167, 18, 12);
		lblNewLabel_17.setText("\u4ECE");

		final DateTime startTime_12 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_12.setLocation(179, 164);
		startTime_12.setSize(79, 15);
		startTime_12.setTime(00, 00, 00);
//		startTimeStr_12=startTime_12.getHours() + ":"
//				+ startTime_12.getMinutes() + ":" + startTime_12.getSeconds();
		if(startTime_12.getHours()<10){
			startTimeStr_12="0"+startTime_12.getHours()+":";
		}else{
			startTimeStr_12=startTime_12.getHours()+":";
		}
		if(startTime_12.getMinutes()<10){
			startTimeStr_12+="0"+startTime_12.getMinutes()+":";
		}else{
			startTimeStr_12+=startTime_12.getMinutes()+":";
		}
		if(startTime_12.getSeconds()<10){
			startTimeStr_12+="0"+startTime_12.getMinutes();
		}else{
			startTimeStr_12+=startTime_12.getMinutes()+"";
		}
		startTime_12.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
//				startTimeStr_12=startTime_12.getHours() + ":"
//						+ startTime_12.getMinutes() + ":" + startTime_12.getSeconds();
				if(startTime_12.getHours()<10){
					startTimeStr_12="0"+startTime_12.getHours()+":";
				}else{
					startTimeStr_12=startTime_12.getHours()+":";
				}
				if(startTime_12.getMinutes()<10){
					startTimeStr_12+="0"+startTime_12.getMinutes()+":";
				}else{
					startTimeStr_12+=startTime_12.getMinutes()+":";
				}
				if(startTime_12.getSeconds()<10){
					startTimeStr_12+="0"+startTime_12.getMinutes();
				}else{
					startTimeStr_12+=startTime_12.getMinutes()+"";
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});

		 Label lblNewLabel_21 = new Label(composite_1, SWT.NONE);
		 lblNewLabel_21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		 lblNewLabel_21.setBounds(271, 145, 18, 12);
		 lblNewLabel_21.setText("\u5230");

		 final DateTime startTime_13 = new DateTime(composite_1, SWT.TIME
					| SWT.SHORT);
			startTime_13.setLocation(295, 142);
			startTime_13.setSize(79, 15);
			startTime_13.setTime(23, 59, 00);
//			startTimeStr_13=startTime_13.getHours() + ":"
//					+ startTime_13.getMinutes() + ":" + startTime_13.getSeconds();
			if(startTime_13.getHours()<10){
				startTimeStr_13="0"+startTime_13.getHours()+":";
			}else{
				startTimeStr_13=startTime_13.getHours()+":";
			}
			if(startTime_13.getMinutes()<10){
				startTimeStr_13+="0"+startTime_13.getMinutes()+":";
			}else{
				startTimeStr_13+=startTime_13.getMinutes()+":";
			}
			if(startTime_13.getSeconds()<10){
				startTimeStr_13+="0"+startTime_13.getMinutes();
			}else{
				startTimeStr_13=startTime_13.getMinutes()+"";
			}
			startTime_13.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
//					startTimeStr_13=startTime_13.getHours() + ":"
//							+ startTime_13.getMinutes() + ":" + startTime_13.getSeconds();
					if(startTime_13.getHours()<10){
						startTimeStr_13="0"+startTime_13.getHours()+":";
					}else{
						startTimeStr_13=startTime_13.getHours()+":";
					}
					if(startTime_13.getMinutes()<10){
						startTimeStr_13+="0"+startTime_13.getMinutes()+":";
					}else{
						startTimeStr_13+=startTime_13.getMinutes()+":";
					}
					if(startTime_13.getSeconds()<10){
						startTimeStr_13+="0"+startTime_13.getMinutes();
					}else{
						startTimeStr_13=startTime_13.getMinutes()+"";
					}
				}
				public void widgetDefaultSelected(SelectionEvent e) {
				
				}
			});
			
		TabItem describeItem=new TabItem(tabFolder, SWT.NONE);
		describeItem.setText("描述");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		composite_2.setBackground(EccTreeControl.color);
		describeItem.setControl(composite_2);
		
		Label lblNewLabel_22 = new Label(composite_2, SWT.HORIZONTAL);
		lblNewLabel_22.setBackground(EccTreeControl.color);
		lblNewLabel_22.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_22.setAlignment(SWT.CENTER);
		lblNewLabel_22.setBounds(0, 0, 54, 199);
		lblNewLabel_22.setText("\u63CF\u8FF0:");
		
		text_1 = new Text(composite_2, SWT.BORDER);//描述
		text_1.setBounds(56, 0, 370, 199);
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
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
			
			ICollection ico=FileTools.getBussCollection("EccTaskPlan");
			IEnumerator ienum=ico.GetEnumerator();
			while(ienum.MoveNext()){
				BusinessObject businessObject=(BusinessObject)ienum.get_Current();
				if(businessObject!=null){
					String taskName=businessObject.GetField("TaskName").get_NativeValue().toString();
					String model=businessObject.GetField("Model").get_NativeValue().toString();
					if(text_2.getText().equals(taskName)&&"时间段计划".equals(model)){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("任务计划名称已存在!", "提示", SWT.OK);
						return;
					}
					if(text_2.getText().equals(taskName)){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("该任务计划名称已在别的任务计划中存在!", "提示", SWT.OK);
						return;
					}
					if(text_2.getText().isEmpty()){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("任务计划名称不能为空!", "提示", SWT.OK);
						return;
					} 
				}
			}

			bo = ConnectionBroker.get_SiteviewApi()//得到数据库表
					.get_BusObService().Create("EccTaskPlan");
			bo.GetField("TaskName").SetValue(
					new SiteviewValue(text_2.getText()));//任务计划名称
			bo.GetField("Instruction").SetValue(
					new SiteviewValue(text_1.getText()));//描述
			bo.GetField("Model").SetValue(
					new SiteviewValue("时间段计划"));//类型
			bo.GetField("Status").SetValue(//得到是否允许的数据
					new SiteviewValue(lblNewLabel_1.getText()+":"+permission+";"+lblNewLabel_2.getText()+":"+permission_1+";"+
							lblNewLabel_3.getText()+":"+permission_2+";"+lblNewLabel_4.getText()+":"+permission_3+";"+
							lblNewLabel_5.getText()+":"+permission_4+";"+lblNewLabel_6.getText()+":"+permission_5+";"+
							lblNewLabel_7.getText()+":"+permission_6));
			bo.GetField("StatrtTime").SetValue(new SiteviewValue(lblNewLabel_1.getText()+","+startTimeStr+";"+lblNewLabel_2.getText()+","+startTimeStr_2+";"+
					lblNewLabel_3.getText()+","+startTimeStr_4+";"+lblNewLabel_4.getText()+","+startTimeStr_6+";"+
					lblNewLabel_5.getText()+","+startTimeStr_8+";"+lblNewLabel_6.getText()+","+startTimeStr_10+";"+
					lblNewLabel_7.getText()+","+startTimeStr_12));//开始时间
			bo.GetField("EndTime").SetValue(new SiteviewValue(lblNewLabel_1.getText()+","+startTimeStr_1+";"+lblNewLabel_2.getText()+","+startTimeStr_3+";"+
					lblNewLabel_3.getText()+","+startTimeStr_5+";"+lblNewLabel_4.getText()+","+startTimeStr_7+";"+
					lblNewLabel_5.getText()+","+startTimeStr_9+";"+lblNewLabel_6.getText()+","+startTimeStr_11+";"+
					lblNewLabel_7.getText()+","+startTimeStr_13));//结束时间
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//将数据存储到数据
			
			AbsoluteTime.addQuantumData();//刷新表单
		}
		this.close();
	
	}
}
