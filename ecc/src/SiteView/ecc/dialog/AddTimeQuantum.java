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
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import Siteview.Windows.Forms.MessageBox;

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
	private String title="���ʱ�������ƻ�";
	private Text text_1;
	private Table table;
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
	public TableItem tableItem;
	public TableItem tableItem_1;
	public TableItem tableItem_2;
	public TableItem tableItem_3;
	public TableItem tableItem_4;
	public TableItem tableItem_5;
	public TableItem tableItem_6;
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
		basicItem.setText("����");
		
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
		lblNewLabel.setFont(SWTResourceManager.getFont("����", 10, SWT.NORMAL));
		lblNewLabel.setBounds(26, 0, 107, 15);
		lblNewLabel.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*:");
		
		text_2 = new Text(composite_3, SWT.BORDER);//����ƻ�����
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
		
		tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(0,"������");
		TableEditor editor = new TableEditor(table);
		btnCheckButton = new Button(table, SWT.CHECK);
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
		startTime = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime.setLocation(191, 32);
		startTime.setSize(79, 15);
		startTime.setTime(00, 00, 00);
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
		startTime_1.setTime(23, 59, 00);
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
		tableItem_1.setText(0,"����һ");
		TableEditor editor_5 = new TableEditor(table);
		btnCheckButton_1 = new Button(table, SWT.CHECK);
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
		startTime_2 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_2.setLocation(191, 50);
		startTime_2.setSize(79, 15);
		startTime_2.setTime(00, 00, 00);
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
		startTime_3.setTime(23, 59, 00);
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
		tableItem_2.setText(0,"���ڶ�");
		TableEditor editor_10 = new TableEditor(table);
		btnCheckButton_2 = new Button(table, SWT.CHECK);
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
		startTime_4 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_4.setLocation(191, 68);
		startTime_4.setSize(79, 15);
		startTime_4.setTime(00, 00, 00);
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
		startTime_5.setTime(23, 59, 00);
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
		tableItem_3.setText(0,"������");
		TableEditor editor_15 = new TableEditor(table);
		btnCheckButton_3 = new Button(table, SWT.CHECK);
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
		startTime_6 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_6.setLocation(191, 86);
		startTime_6.setSize(79, 15);
		startTime_6.setTime(00, 00, 00);
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
		startTime_7.setTime(23, 59, 00);
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
		tableItem_4.setText(0,"������");
		TableEditor editor_20 = new TableEditor(table);
		btnCheckButton_4 = new Button(table, SWT.CHECK);
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
		startTime_8 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_8.setLocation(191, 104);
		startTime_8.setSize(79, 15);
		startTime_8.setTime(00, 00, 00);
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
		lblNewLabel_19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_19.setBounds(276, 107, 18, 12);
		lblNewLabel_19.setText("\u5230");
		editor_23.minimumWidth = lblNewLabel_19.getSize ().x;
		editor_23.setEditor(lblNewLabel_19, tableItem_4, 4); 
		TableEditor editor_24 = new TableEditor(table);
		startTime_9 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_9.setLocation(295, 104);
		startTime_9.setSize(79, 15);
		startTime_9.setTime(23, 59, 00);
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
		tableItem_5.setText(0,"������");
		TableEditor editor_25 = new TableEditor(table);
		btnCheckButton_5 = new Button(table, SWT.CHECK);
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
		startTime_10 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_10.setLocation(191, 122);
		startTime_10.setSize(79, 15);
		startTime_10.setTime(00, 00, 00);
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
		lblNewLabel_20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_20.setBounds(276, 125, 18, 12);
		lblNewLabel_20.setText("\u5230");
		editor_28.minimumWidth = lblNewLabel_20.getSize ().x;
		editor_28.setEditor(lblNewLabel_20, tableItem_5, 4); 
		TableEditor editor_29 = new TableEditor(table);
		startTime_11 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_11.setLocation(295, 122);
		startTime_11.setSize(79, 15);
		startTime_11.setTime(23, 59, 00);
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
		tableItem_6.setText(0,"������");
		TableEditor editor_30 = new TableEditor(table);
		btnCheckButton_6 = new Button(table, SWT.CHECK);
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
		startTime_12 = new DateTime(table, SWT.TIME
				| SWT.SHORT);
		startTime_12.setLocation(191, 140);
		startTime_12.setSize(79, 15);
		startTime_12.setTime(00, 00, 00);
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
		 lblNewLabel_21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		 lblNewLabel_21.setBounds(276, 143, 18, 12);
		 lblNewLabel_21.setText("\u5230");
		 editor_33.minimumWidth = lblNewLabel_21.getSize ().x;
		 editor_33.setEditor(lblNewLabel_21, tableItem_6, 4);
		 TableEditor editor_34 = new TableEditor(table);
			startTime_13 = new DateTime(table, SWT.TIME
					| SWT.SHORT);
			startTime_13.setLocation(295, 140);
			startTime_13.setSize(79, 15);
			startTime_13.setTime(23, 59, 00);
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
		describeItem.setText("����");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		describeItem.setControl(composite_2);
		
		Label lblNewLabel_22 = new Label(composite_2, SWT.HORIZONTAL);
		lblNewLabel_22.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_22.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
		lblNewLabel_22.setAlignment(SWT.CENTER);
		lblNewLabel_22.setBounds(0, 0, 54, 199);
		lblNewLabel_22.setText("\u63CF\u8FF0:");
		
		text_1 = new Text(composite_2, SWT.BORDER);//����
		text_1.setBounds(56, 0, 370, 199);
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "����",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"ȡ��", true);
	}
	protected void buttonPressed(int buttonId) {
		L1:	if(buttonId==IDialogConstants.OK_ID){
			if(btnCheckButton.getSelection()){
				permission="����";
			}else{
				permission="��ֹ";
			}
			
			if(btnCheckButton_1.getSelection()){
				permission_1="����";
			}else{
				permission_1="��ֹ";
			}
			
			if(btnCheckButton_2.getSelection()){
				permission_2="����";
			}else{
				permission_2="��ֹ";
			}
			
			if(btnCheckButton_3.getSelection()){
				permission_3="����";
			}else{
				permission_3="��ֹ";
			}
			
			if(btnCheckButton_4.getSelection()){
				permission_4="����";
			}else{
				permission_4="��ֹ";
			}
			
			if(btnCheckButton_5.getSelection()){
				permission_5="����";
			}else{
				permission_5="��ֹ";
			}
			
			if(btnCheckButton_6.getSelection()){
				permission_6="����";
			}else{
				permission_6="��ֹ";
			}
			
			ICollection ico=FileTools.getBussCollection("EccTaskPlan");
			IEnumerator ienum=ico.GetEnumerator();
			while(ienum.MoveNext()){
				BusinessObject businessObject=(BusinessObject)ienum.get_Current();
				if(businessObject!=null){
					String taskName=businessObject.GetField("TaskName").get_NativeValue().toString();
					String model=businessObject.GetField("Model").get_NativeValue().toString();
					if(text_2.getText().equals(taskName)&&"ʱ��μƻ�".equals(model)){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("����ƻ������Ѵ���!", "��ʾ", SWT.OK);
						break L1;
					}
					if(text_2.getText().isEmpty()){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("����ƻ����Ʋ���Ϊ��!", "��ʾ", SWT.OK);
						break L1;
					} 
				}
			}

			bo = ConnectionBroker.get_SiteviewApi()//�õ����ݿ��
					.get_BusObService().Create("EccTaskPlan");
			bo.GetField("TaskName").SetValue(
					new SiteviewValue(text_2.getText()));//����ƻ�����
			bo.GetField("Instruction").SetValue(
					new SiteviewValue(text_1.getText()));//����
			bo.GetField("Model").SetValue(
					new SiteviewValue("ʱ��μƻ�"));
			bo.GetField("StatrtTime").SetValue(new SiteviewValue(tableItem.getText(0)+","+startTimeStr+";"+tableItem_1.getText(0)+","+startTimeStr_2+";"+
					tableItem_2.getText(0)+","+startTimeStr_4+";"+tableItem_3.getText(0)+","+startTimeStr_6+";"+
					tableItem_4.getText(0)+","+startTimeStr_8+";"+tableItem_5.getText(0)+","+startTimeStr_10+";"+
					tableItem_6.getText(0)+","+startTimeStr_12));//��ʼʱ��
			bo.GetField("StatrtTime").SetValue(new SiteviewValue(tableItem.getText(0)+","+startTimeStr_1+";"+tableItem_1.getText(0)+","+startTimeStr_3+";"+
					tableItem_2.getText(0)+","+startTimeStr_5+";"+tableItem_3.getText(0)+","+startTimeStr_7+";"+
					tableItem_4.getText(0)+","+startTimeStr_9+";"+tableItem_5.getText(0)+","+startTimeStr_11+";"+
					tableItem_6.getText(0)+","+startTimeStr_13));//����ʱ��
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//�����ݴ洢������
			
			AbsoluteTime.addQuantumData();//ˢ�±�
		}
		this.close();
	
	}
}
