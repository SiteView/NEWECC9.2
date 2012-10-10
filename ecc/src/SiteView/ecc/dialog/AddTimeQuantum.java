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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

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
	private Text text_1;
	private Table table;
	private Text text_2;
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
		
		SashForm sashForm = new SashForm(composite_1, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		sashForm.setSize(436, 214);
		sashForm.setLocation(0, 0);
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		lblNewLabel.setBounds(26, 0, 107, 15);
		lblNewLabel.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*:");
		
		text_2 = new Text(composite_3, SWT.BORDER);
		text_2.setBounds(140, 0, 201, 18);
		
		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
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
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(0,"星期日");
		TableEditor editor = new TableEditor(table);
		Button btnCheckButton = new Button(table, SWT.CHECK);
		btnCheckButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton.setBounds(113, 33, 45, 16);
		btnCheckButton.setText("\u5141\u8BB8");
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
		Date startDateTime  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime);
		startTime = new DateTime(table, SWT.TIME
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
		Date startDateTime_1  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_1);
		startTime_1 = new DateTime(table, SWT.TIME
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
		editor_4.minimumWidth = startTime_1.getSize ().x;
		editor_4.setEditor(startTime_1, tableItem, 5); 
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(0,"星期一");
		TableEditor editor_5 = new TableEditor(table);
		Button btnCheckButton_1 = new Button(table, SWT.CHECK);
		btnCheckButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_1.setBounds(113, 51, 45, 16);
		btnCheckButton_1.setText("\u5141\u8BB8");
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
		Date startDateTime_2  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_2);
		startTime_2 = new DateTime(table, SWT.TIME
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
		Date startDateTime_3  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_3);
		startTime_3 = new DateTime(table, SWT.TIME
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
		 editor_9.minimumWidth = startTime_3.getSize ().x;
		 editor_9.setEditor(startTime_3, tableItem_1, 5); 
		
		TableItem tableItem_2 = new TableItem(table, SWT.NONE);
		tableItem_2.setText(0,"星期二");
		TableEditor editor_10 = new TableEditor(table);
		Button btnCheckButton_2 = new Button(table, SWT.CHECK);
		btnCheckButton_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_2.setBounds(113, 69, 45, 16);
		btnCheckButton_2.setText("\u5141\u8BB8");
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
		Date startDateTime_4  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_4);
		startTime_4 = new DateTime(table, SWT.TIME
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
		Date startDateTime_5  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_5);
		startTime_5 = new DateTime(table, SWT.TIME
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
		editor_14.minimumWidth = startTime_5.getSize ().x;
		editor_14.setEditor(startTime_5, tableItem_2, 5); 
		
		TableItem tableItem_3 = new TableItem(table, SWT.NONE);
		tableItem_3.setText(0,"星期三");
		TableEditor editor_15 = new TableEditor(table);
		Button btnCheckButton_3 = new Button(table, SWT.CHECK);
		btnCheckButton_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_3.setBounds(113, 87, 45, 16);
		btnCheckButton_3.setText("\u5141\u8BB8");
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
		Date startDateTime_6  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_6);
		startTime_6 = new DateTime(table, SWT.TIME
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
		Date startDateTime_7  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_7);
		startTime_7 = new DateTime(table, SWT.TIME
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
		editor_19.minimumWidth = startTime_7.getSize ().x;
		editor_19.setEditor(startTime_7, tableItem_3, 5); 
		
		
		TableItem tableItem_4 = new TableItem(table, SWT.NONE);
		tableItem_4.setText(0,"星期四");
		TableEditor editor_20 = new TableEditor(table);
		Button btnCheckButton_4 = new Button(table, SWT.CHECK);
		btnCheckButton_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_4.setBounds(113, 105, 45, 16);
		btnCheckButton_4.setText("\u5141\u8BB8 ");
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
		Date startDateTime_8  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_8);
		startTime_8 = new DateTime(table, SWT.TIME
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
		editor_22.minimumWidth = startTime_8.getSize ().x;
		editor_22.setEditor(startTime_8, tableItem_4, 3); 
		TableEditor editor_23 = new TableEditor(table);
		Label lblNewLabel_19 = new Label(table, SWT.NONE);
		lblNewLabel_19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_19.setBounds(276, 107, 18, 12);
		lblNewLabel_19.setText("\u5230");
		editor_23.minimumWidth = lblNewLabel_19.getSize ().x;
		editor_23.setEditor(lblNewLabel_19, tableItem_4, 4); 
		TableEditor editor_24 = new TableEditor(table);
		Date startDateTime_9  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_9);
		startTime_9 = new DateTime(table, SWT.TIME
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
		editor_24.minimumWidth = startTime_9.getSize ().x;
		editor_24.setEditor(startTime_9, tableItem_4, 5); 
		
		
		
		TableItem tableItem_5 = new TableItem(table, SWT.NONE);
		tableItem_5.setText(0,"星期五");
		TableEditor editor_25 = new TableEditor(table);
		Button btnCheckButton_5 = new Button(table, SWT.CHECK);
		btnCheckButton_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_5.setBounds(113, 123, 45, 16);
		btnCheckButton_5.setText("\u5141\u8BB8");
		editor_25.minimumWidth = btnCheckButton_5.getSize ().x;
		editor_25.setEditor(btnCheckButton_5, tableItem_5, 1); 
		TableEditor editor_26 = new TableEditor(table);
		Label lblNewLabel_16 = new Label(table, SWT.NONE);
		lblNewLabel_16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_16.setBounds(172, 125, 18, 12);
		lblNewLabel_16.setText("\u4ECE");
		editor_26.minimumWidth = lblNewLabel_16.getSize ().x;
		editor_26.setEditor(lblNewLabel_16, tableItem_5, 2); 
		TableEditor editor_27 = new TableEditor(table);
		Date startDateTime_10  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_10);
		startTime_10 = new DateTime(table, SWT.TIME
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
		editor_27.minimumWidth = startTime_10.getSize ().x;
		editor_27.setEditor(startTime_10, tableItem_5, 3); 
		TableEditor editor_28 = new TableEditor(table);
		Label lblNewLabel_20 = new Label(table, SWT.NONE);
		lblNewLabel_20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_20.setBounds(276, 125, 18, 12);
		lblNewLabel_20.setText("\u5230");
		editor_28.minimumWidth = lblNewLabel_20.getSize ().x;
		editor_28.setEditor(lblNewLabel_20, tableItem_5, 4); 
		TableEditor editor_29 = new TableEditor(table);
		Date startDateTime_11  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_11);
		startTime_11 = new DateTime(table, SWT.TIME
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
		editor_29.minimumWidth = startTime_11.getSize ().x;
		editor_29.setEditor(startTime_11, tableItem_5, 5); 
		
		TableItem tableItem_6 = new TableItem(table, SWT.NONE);
		tableItem_6.setText(0,"星期六");
		TableEditor editor_30 = new TableEditor(table);
		Button btnCheckButton_6 = new Button(table, SWT.CHECK);
		btnCheckButton_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton_6.setBounds(113, 141, 45, 16);
		btnCheckButton_6.setText("\u5141\u8BB8");
		editor_30.minimumWidth = btnCheckButton_6.getSize ().x;
		editor_30.setEditor(btnCheckButton_6, tableItem_6, 1); 
		TableEditor editor_31 = new TableEditor(table);
		Label lblNewLabel_17 = new Label(table, SWT.NONE);
		lblNewLabel_17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_17.setBounds(172, 143, 18, 12);
		lblNewLabel_17.setText("\u4ECE");
		editor_31.minimumWidth = lblNewLabel_17.getSize ().x;
		editor_31.setEditor(lblNewLabel_17, tableItem_6, 2);
		TableEditor editor_32 = new TableEditor(table);
		Date startDateTime_12  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_12);
		startTime_12 = new DateTime(table, SWT.TIME
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
		 editor_32.minimumWidth = startTime_12.getSize ().x;
		 editor_32.setEditor(startTime_12, tableItem_6, 3);
		 TableEditor editor_33 = new TableEditor(table);
		 Label lblNewLabel_21 = new Label(table, SWT.NONE);
		 lblNewLabel_21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		 lblNewLabel_21.setBounds(276, 143, 18, 12);
		 lblNewLabel_21.setText("\u5230");
		 editor_33.minimumWidth = lblNewLabel_21.getSize ().x;
		 editor_33.setEditor(lblNewLabel_21, tableItem_6, 4);
		 TableEditor editor_34 = new TableEditor(table);
		    Date startDateTime_13  = new Date();
			startcal = Calendar.getInstance();
			startcal.setTime(startDateTime_13);
			startTime_13 = new DateTime(table, SWT.TIME
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
		
		text_1 = new Text(composite_2, SWT.BORDER);
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
			
		}else{
			this.close();
		}
	}
}
