package SiteView.ecc.dialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AddTimeQuantum extends Dialog{
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
	public Calendar startcal;
	public SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	private String title="添加时间段任务计划";
	private Text text;
	public AddTimeQuantum(Shell parentShell) {
		super(parentShell);
		
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(420, 300);
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
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		basicItem.setControl(composite_1);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		lblNewLabel.setBounds(10, 10, 92, 19);
		lblNewLabel.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*:");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBounds(113, 10, 272, 18);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_1.setBounds(20, 35, 54, 12);
		lblNewLabel_1.setText("\u661F\u671F\u65E5");
		
		Button btnCheckButton = new Button(composite_1, SWT.CHECK);
		btnCheckButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton.setBounds(113, 33, 45, 16);
		btnCheckButton.setText("\u5141\u8BB8");
		
		Label lblNewLabel_8 = new Label(composite_1, SWT.NONE);
		lblNewLabel_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_8.setBounds(172, 34, 18, 12);
		lblNewLabel_8.setText("\u4ECE");
		
		Date startDateTime  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime);
		startTime = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime.setLocation(191, 32);
		startTime.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 00);
		startcal.set(Calendar.MINUTE, 0);
		startcal.set(Calendar.SECOND, 0);
		
		startTime.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime.setMinutes(startcal.get(Calendar.MINUTE));
		startTime.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr=startTime.getHours() + ":"
				+ startTime.getMinutes() + ":" + startTime.getSeconds();
		 try {
				startDateTime = sdf.parse(startTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		startTimeStr = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		Label lblNewLabel_9 = new Label(composite_1, SWT.NONE);
		lblNewLabel_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_9.setBounds(276, 34, 18, 12);
		lblNewLabel_9.setText("\u5230");
		
		Date startDateTime_1  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_1);
		startTime_1 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_1.setLocation(295, 32);
		startTime_1.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 23);
		startcal.set(Calendar.MINUTE, 59);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_1.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_1.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_1.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_1=startTime_1.getHours() + ":"
				+ startTime_1.getMinutes() + ":" + startTime_1.getSeconds();
		 try {
			 startDateTime_1 = sdf.parse(startTimeStr_1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_1 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_2.setBounds(20, 53, 54, 12);
		lblNewLabel_2.setText("\u661F\u671F\u4E00");
		
		Button btnCheckButton_1 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_1.setBounds(113, 51, 45, 16);
		btnCheckButton_1.setText("\u5141\u8BB8");
		
		Label lblNewLabel_10 = new Label(composite_1, SWT.NONE);
		lblNewLabel_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_10.setBounds(172, 52, 18, 12);
		lblNewLabel_10.setText("\u4ECE");
		
		Date startDateTime_2  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_2);
		startTime_2 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_2.setLocation(191, 50);
		startTime_2.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 00);
		startcal.set(Calendar.MINUTE, 0);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_2.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_2.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_2.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_2=startTime_2.getHours() + ":"
				+ startTime_2.getMinutes() + ":" + startTime_2.getSeconds();
		 try {
			 startDateTime_2 = sdf.parse(startTimeStr_2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_2 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime()); 
		
		Label lblNewLabel_11 = new Label(composite_1, SWT.NONE);
		lblNewLabel_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_11.setBounds(276, 52, 18, 12);
		lblNewLabel_11.setText("\u5230");
		
		Date startDateTime_3  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_3);
		startTime_3 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_3.setLocation(295, 50);
		startTime_3.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 23);
		startcal.set(Calendar.MINUTE, 59);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_3.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_3.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_3.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_3=startTime_3.getHours() + ":"
				+ startTime_3.getMinutes() + ":" + startTime_3.getSeconds();
		 try {
			 startDateTime_3 = sdf.parse(startTimeStr_3);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_3 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime()); 
		 
		Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_3.setBounds(20, 71, 54, 12);
		lblNewLabel_3.setText("\u661F\u671F\u4E8C");
		
		Button btnCheckButton_2 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_2.setBounds(113, 69, 45, 16);
		btnCheckButton_2.setText("\u5141\u8BB8");
		
		Label lblNewLabel_12 = new Label(composite_1, SWT.NONE);
		lblNewLabel_12.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_12.setBounds(172, 70, 18, 12);
		lblNewLabel_12.setText("\u4ECE");
		
		Date startDateTime_4  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_4);
		startTime_4 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_4.setLocation(191, 68);
		startTime_4.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 00);
		startcal.set(Calendar.MINUTE, 0);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_4.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_4.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_4.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_4=startTime_4.getHours() + ":"
				+ startTime_4.getMinutes() + ":" + startTime_4.getSeconds();
		 try {
			 startDateTime_4 = sdf.parse(startTimeStr_4);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_4 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime()); 
		
		Label lblNewLabel_13 = new Label(composite_1, SWT.NONE);
		lblNewLabel_13.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_13.setBounds(276, 71, 18, 12);
		lblNewLabel_13.setText("\u5230");
		
		Date startDateTime_5  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_5);
		startTime_5 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_5.setLocation(295, 68);
		startTime_5.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 23);
		startcal.set(Calendar.MINUTE, 59);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_5.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_5.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_5.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_5=startTime_5.getHours() + ":"
				+ startTime_5.getMinutes() + ":" + startTime_5.getSeconds();
		 try {
			 startDateTime_5 = sdf.parse(startTimeStr_5);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_5 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime()); 
		
		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_4.setBounds(20, 89, 54, 12);
		lblNewLabel_4.setText("\u661F\u671F\u4E09");
		
		Button btnCheckButton_3 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_3.setBounds(113, 87, 45, 16);
		btnCheckButton_3.setText("\u5141\u8BB8");
		
		Label lblNewLabel_14 = new Label(composite_1, SWT.NONE);
		lblNewLabel_14.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_14.setBounds(172, 88, 18, 12);
		lblNewLabel_14.setText("\u4ECE");
		
		Date startDateTime_6  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_6);
		startTime_6 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_6.setLocation(191, 86);
		startTime_6.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 00);
		startcal.set(Calendar.MINUTE, 0);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_6.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_6.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_6.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_6=startTime_6.getHours() + ":"
				+ startTime_6.getMinutes() + ":" + startTime_6.getSeconds();
		 try {
			 startDateTime_6 = sdf.parse(startTimeStr_6);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_6 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime()); 
		
		Label lblNewLabel_18 = new Label(composite_1, SWT.NONE);
		lblNewLabel_18.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_18.setBounds(276, 89, 18, 12);
		lblNewLabel_18.setText("\u5230");
		
		Date startDateTime_7  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_7);
		startTime_7 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_7.setLocation(295, 86);
		startTime_7.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 23);
		startcal.set(Calendar.MINUTE, 59);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_7.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_7.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_7.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_7=startTime_7.getHours() + ":"
				+ startTime_7.getMinutes() + ":" + startTime_7.getSeconds();
		 try {
			 startDateTime_7 = sdf.parse(startTimeStr_7);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_7 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		Label lblNewLabel_5 = new Label(composite_1, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_5.setBounds(20, 107, 54, 12);
		lblNewLabel_5.setText("\u661F\u671F\u56DB");
		
		Button btnCheckButton_4 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_4.setBounds(113, 105, 45, 16);
		btnCheckButton_4.setText("\u5141\u8BB8 ");
		
		Label lblNewLabel_15 = new Label(composite_1, SWT.NONE);
		lblNewLabel_15.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_15.setBounds(172, 107, 18, 12);
		lblNewLabel_15.setText("\u4ECE");
		
		Date startDateTime_8  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_8);
		startTime_8 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_8.setLocation(191, 104);
		startTime_8.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 00);
		startcal.set(Calendar.MINUTE, 0);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_8.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_8.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_8.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_8=startTime_8.getHours() + ":"
				+ startTime_8.getMinutes() + ":" + startTime_8.getSeconds();
		 try {
			 startDateTime_8 = sdf.parse(startTimeStr_8);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_8 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		Label lblNewLabel_19 = new Label(composite_1, SWT.NONE);
		lblNewLabel_19.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_19.setBounds(276, 107, 18, 12);
		lblNewLabel_19.setText("\u5230");
		
		Date startDateTime_9  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_9);
		startTime_9 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_9.setLocation(295, 104);
		startTime_9.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 23);
		startcal.set(Calendar.MINUTE, 59);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_9.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_9.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_9.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_9=startTime_9.getHours() + ":"
				+ startTime_9.getMinutes() + ":" + startTime_9.getSeconds();
		 try {
			 startDateTime_9 = sdf.parse(startTimeStr_9);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_9 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		Label lblNewLabel_6 = new Label(composite_1, SWT.NONE);
		lblNewLabel_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_6.setBounds(20, 125, 54, 12);
		lblNewLabel_6.setText("\u661F\u671F\u4E94");
		
		Button btnCheckButton_5 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_5.setBounds(113, 123, 45, 16);
		btnCheckButton_5.setText("\u5141\u8BB8");
		
		Label lblNewLabel_16 = new Label(composite_1, SWT.NONE);
		lblNewLabel_16.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_16.setBounds(172, 125, 18, 12);
		lblNewLabel_16.setText("\u4ECE");
		
		Date startDateTime_10  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_10);
		startTime_10 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_10.setLocation(191, 122);
		startTime_10.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 00);
		startcal.set(Calendar.MINUTE, 0);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_10.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_10.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_10.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_10=startTime_10.getHours() + ":"
				+ startTime_10.getMinutes() + ":" + startTime_10.getSeconds();
		 try {
			 startDateTime_10 = sdf.parse(startTimeStr_10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_10 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		Label lblNewLabel_20 = new Label(composite_1, SWT.NONE);
		lblNewLabel_20.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_20.setBounds(276, 125, 18, 12);
		lblNewLabel_20.setText("\u5230");
		
		Date startDateTime_11  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_11);
		startTime_11 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_11.setLocation(295, 122);
		startTime_11.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 23);
		startcal.set(Calendar.MINUTE, 59);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_11.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_11.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_11.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_11=startTime_11.getHours() + ":"
				+ startTime_11.getMinutes() + ":" + startTime_11.getSeconds();
		 try {
			 startDateTime_11 = sdf.parse(startTimeStr_11);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_11 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		Label lblNewLabel_7 = new Label(composite_1, SWT.NONE);
		lblNewLabel_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_7.setBounds(20, 143, 54, 12);
		lblNewLabel_7.setText("\u661F\u671F\u516D");
		
		Button btnCheckButton_6 = new Button(composite_1, SWT.CHECK);
		btnCheckButton_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_6.setBounds(113, 141, 45, 16);
		btnCheckButton_6.setText("\u5141\u8BB8");
		
		Label lblNewLabel_17 = new Label(composite_1, SWT.NONE);
		lblNewLabel_17.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_17.setBounds(172, 143, 18, 12);
		lblNewLabel_17.setText("\u4ECE");
		
		Date startDateTime_12  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_12);
		startTime_12 = new DateTime(composite_1, SWT.TIME
				| SWT.SHORT);
		startTime_12.setLocation(191, 140);
		startTime_12.setSize(79, 15);
		
		startcal.set(Calendar.HOUR_OF_DAY, 00);
		startcal.set(Calendar.MINUTE, 0);
		startcal.set(Calendar.SECOND, 0);
		
		startTime_12.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_12.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_12.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_12=startTime_12.getHours() + ":"
				+ startTime_12.getMinutes() + ":" + startTime_12.getSeconds();
		 try {
			 startDateTime_12 = sdf.parse(startTimeStr_12);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_12 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		 Label lblNewLabel_21 = new Label(composite_1, SWT.NONE);
		 lblNewLabel_21.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		 lblNewLabel_21.setBounds(276, 143, 18, 12);
		 lblNewLabel_21.setText("\u5230");
		 
		 Date startDateTime_13  = new Date();
			startcal = Calendar.getInstance();
			startcal.setTime(startDateTime_13);
			startTime_13 = new DateTime(composite_1, SWT.TIME
					| SWT.SHORT);
			startTime_13.setLocation(295, 140);
			startTime_13.setSize(79, 15);
			
			startcal.set(Calendar.HOUR_OF_DAY, 23);
			startcal.set(Calendar.MINUTE, 59);
			startcal.set(Calendar.SECOND, 0);
			
			startTime_13.setHours(startcal.get(Calendar.HOUR_OF_DAY));
			startTime_13.setMinutes(startcal.get(Calendar.MINUTE));
			startTime_13.setSeconds(startcal.get(Calendar.SECOND));
			startTimeStr_13=startTime_13.getHours() + ":"
					+ startTime_13.getMinutes() + ":" + startTime_13.getSeconds();
			 try {
				 startDateTime_13 = sdf.parse(startTimeStr_13);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			 startTimeStr_13 = new SimpleDateFormat("HH:mm:ss")
			.format(startcal.getTime());
			
		TabItem describeItem=new TabItem(tabFolder, SWT.NONE);
		describeItem.setText("描述");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		describeItem.setControl(composite_2);
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
			
		}else{
			this.close();
		}
	}
}
