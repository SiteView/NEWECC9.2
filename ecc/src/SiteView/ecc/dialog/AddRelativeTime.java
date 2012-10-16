package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.editors.AbsoluteTime;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import Siteview.Windows.Forms.MessageBox;

public class AddRelativeTime extends Dialog {
	private Table table;
	private Text text;
	private Text text_1;
    public BusinessObject bo;
    public String permission;
    public String permission_1;
    public String permission_2;
    public String permission_3;
    public String permission_4;
    public String permission_5;
    public String permission_6;
    public String permission_7;
    public String permission_8;
    public String permission_9;
    public String permission_10;
    public String permission_11;
    public String permission_12;
    public String permission_13;
    public String permission_14;
    public String permission_15;
    public String permission_16;
    public String permission_17;
    public String permission_18;
    public String permission_19;
    public String permission_20;
    public String permission_21;
    public String permission_22;
    public String permission_23;
    public String permission_24;
    public String permission_25;
    public String permission_26;
    public String permission_27;
    public String permission_28;
    public String permission_29;
    public String permission_30;
    public String permission_31;
    public String permission_32;
    public String permission_33;
    public String permission_34;
    public String permission_35;
    public String permission_36;
    public String permission_37;
    public String permission_38;
    public String permission_39;
    public String permission_40;
    public String permission_41;
    public String permission_42;
    public String permission_43;
    public String permission_44;
    public String permission_45;
    public String permission_46;
    public String permission_47;
    public String permission_48;
    public String permission_49;
    public String permission_50;
    public String permission_51;
    public String permission_52;
    public String permission_53;
    public String permission_54;
    public String permission_55;
    public String permission_56;
    public String permission_57;
    public String permission_58;
    public String permission_59;
    public String permission_60;
    public String permission_61;
    public String permission_62;
    public String permission_63;
    public String permission_64;
    public String permission_65;
    public String permission_66;
    public String permission_67;
    public String permission_68;
    public String permission_69;
    public String permission_70;
    public String permission_71;
    public String permission_72;
    public String permission_73;
    public String permission_74;
    public String permission_75;
    public String permission_76;
    public String permission_77;
    public String permission_78;
    public String permission_79;
    public String permission_80;
    public String permission_81;
    public String permission_82;
    public String permission_83;
    public String permission_84;
    public String permission_85;
    public String permission_86;
    public String permission_87;
    public String permission_88;
    public String permission_89;
    public String permission_90;
    public String permission_91;
    public String permission_92;
    public String permission_93;
    public String permission_94;
    public String permission_95;
    public String permission_96;
    public String permission_97;
    public String permission_98;
    public String permission_99;
    public String permission_100;
    public String permission_101;
    public String permission_102;
    public String permission_103;
    public String permission_104;
    public String permission_105;
    public String permission_106;
    public String permission_107;
    public String permission_108;
    public String permission_109;
    public String permission_110;
    public String permission_111;
    public String permission_112;
    public String permission_113;
    public String permission_114;
    public String permission_115;
    public String permission_116;
    public String permission_117;
    public String permission_118;
    public String permission_119;
    public String permission_120;
    public String permission_121;
    public String permission_122;
    public String permission_123;
    public String permission_124;
    public String permission_125;
    public String permission_126;
    public String permission_127;
    public String permission_128;
    public String permission_129;
    public String permission_130;
    public String permission_131;
    public String permission_132;
    public String permission_133;
    public String permission_134;
    public String permission_135;
    public String permission_136;
    public String permission_137;
    public String permission_138;
    public String permission_139;
    public String permission_140;
    public String permission_141;
    public String permission_142;
    public String permission_143;
    public String permission_144;
    public String permission_145;
    public String permission_146;
    public String permission_147;
    public String permission_148;
    public String permission_149;
    public String permission_150;
    public String permission_151;
    public String permission_152;
    public String permission_153;
    public String permission_154;
    public String permission_155;
    public String permission_156;
    public String permission_157;
    public String permission_158;
    public String permission_159;
    public String permission_160;
    public String permission_161;
    public String permission_162;
    public String permission_163;
    public String permission_164;
    public String permission_165;
    public String permission_166;
    public String permission_167;
    public Button check;
    public TableItem item1;
    public TableItem item2;
    public TableItem item3;
    public TableItem item4;
    public TableItem item5;
    public TableItem item6;
    public TableItem item7;
    public TableViewer tableViewer ;
    private CheckboxTableViewer ctv; //新增的语句
	public AddRelativeTime(Shell parentShell) {
		super(parentShell);
	}
	
	protected void configureShell(Shell newShell) {
		newShell.setSize(810, 480);
		newShell.setLocation(200, 175);
		newShell.setText("添加相对时间计划");
		super.configureShell(newShell);
	}
	
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(EccTreeControl.color);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		sashForm.setBackground(EccTreeControl.color);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(EccTreeControl.color);
		lblNewLabel_1.setBounds(10, 5, 100, 18);
		lblNewLabel_1.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*\uFF1A");
		
		text_1 = new Text(composite_1, SWT.BORDER);//任务计划名称
		text_1.setBounds(115, 5, 150, 18);
		
		tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setBackground(EccTreeControl.color);

		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(80);
		tblclmnNewColumn.setText("星期");
		
		for (int i = 0; i < 24; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setWidth(30);
			column.setText(i+"");
			
		}
		
		item1 = new TableItem(table, SWT.NONE);
		item1.setText(0, "星期日");
		Image image = new Image(null, 15, 30);
		item1.setImage(image);
		TableEditor editor_0 = new TableEditor(table);
		final Button check_0 = new Button(table, SWT.CHECK);
		check_0.setBackground(EccTreeControl.color);
		check_0.pack();
		check_0.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission="true";
			}
		});
		permission="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_0.setSelection(true);
					}
				}else{
					 check_0.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_0.minimumWidth = check_0.getSize ().x;
		editor_0.setEditor(check_0, item1, 1);
		
		
		TableEditor editor_1 = new TableEditor(table);
		final Button check_1 = new Button(table, SWT.CHECK);
		check_1.setBackground(EccTreeControl.color);
		check_1.pack();
		check_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_1="true";
			}
		});
		permission_1="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_1.setSelection(true);
					}
				}else{
					check_1.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_1.minimumWidth = check_1.getSize ().x;
		editor_1.setEditor(check_1, item1, 2);
		
		TableEditor editor_2 = new TableEditor(table);
		final Button check_2 = new Button(table, SWT.CHECK);
		check_2.setBackground(EccTreeControl.color);
		check_2.pack();
		check_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_2="true";
			}
		});
		permission_2="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_2.setSelection(true);
					}
				}else{
					check_2.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_2.minimumWidth = check_2.getSize ().x;
		editor_2.setEditor(check_2, item1, 3);	
		
		TableEditor editor_3 = new TableEditor(table);
		final Button check_3 = new Button(table, SWT.CHECK);
		check_3.setBackground(EccTreeControl.color);
		check_3.pack();
		check_3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_3="true";
			}
		});
		permission_3="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_3.setSelection(true);
					}
				}else{
					check_3.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_3.minimumWidth = check_3.getSize ().x;
		editor_3.setEditor(check_3, item1, 4);	
		
		TableEditor editor_4 = new TableEditor(table);
		final Button check_4 = new Button(table, SWT.CHECK);
		check_4.setBackground(EccTreeControl.color);
		check_4.pack();
		check_4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_4="true";
			}
		});
		permission_4="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_4.setSelection(true);
					}
				}else{
					check_4.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_4.minimumWidth = check_4.getSize ().x;
		editor_4.setEditor(check_4, item1, 5);	
		
		TableEditor editor_5 = new TableEditor(table);
		final Button check_5 = new Button(table, SWT.CHECK);
		check_5.setBackground(EccTreeControl.color);
		check_5.pack();
		check_5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_5="true";
			}
		});
		permission_5="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_5.setSelection(true);
					}
				}else{
					check_5.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_5.minimumWidth = check_5.getSize ().x;
		editor_5.setEditor(check_5, item1, 6);	
		
		TableEditor editor_6 = new TableEditor(table);
		final Button check_6 = new Button(table, SWT.CHECK);
		check_6.setBackground(EccTreeControl.color);
		check_6.pack();
		check_6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_6="true";
			}
		});
		permission_6="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_6.setSelection(true);
					}
				}else{
					check_6.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_6.minimumWidth = check_6.getSize ().x;
		editor_6.setEditor(check_6, item1, 7);	
		
		TableEditor editor_7 = new TableEditor(table);
		final Button check_7 = new Button(table, SWT.CHECK);
		check_7.setBackground(EccTreeControl.color);
		check_7.pack();
		check_7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_7="true";
			}
		});
		permission_7="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_7.setSelection(true);
					}
				}else{
					check_7.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_7.minimumWidth = check_7.getSize ().x;
		editor_7.setEditor(check_7, item1, 8);	
		
		TableEditor editor_8 = new TableEditor(table);
		final Button check_8 = new Button(table, SWT.CHECK);
		check_8.setBackground(EccTreeControl.color);
		check_8.pack();
		check_8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_8="true";
			}
		});
		permission_8="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_8.setSelection(true);
					}
				}else{
					check_8.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_8.minimumWidth = check_8.getSize ().x;
		editor_8.setEditor(check_8, item1, 9);	
		
		TableEditor editor_9 = new TableEditor(table);
		final Button check_9 = new Button(table, SWT.CHECK);
		check_9.setBackground(EccTreeControl.color);
		check_9.pack();
		check_9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_9="true";
			}
		});
		permission_9="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_9.setSelection(true);
					}
				}else{
					check_9.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_9.minimumWidth = check_9.getSize ().x;
		editor_9.setEditor(check_9, item1, 10);	
		
		TableEditor editor_10 = new TableEditor(table);
		final Button check_10 = new Button(table, SWT.CHECK);
		check_10.setBackground(EccTreeControl.color);
		check_10.pack();
		check_10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_10="true";
			}
		});
		permission_10="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_10.setSelection(true);
					}
				}else{
					check_10.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_10.minimumWidth = check_10.getSize ().x;
		editor_10.setEditor(check_10, item1, 11);	
		
		TableEditor editor_11 = new TableEditor(table);
		final Button check_11 = new Button(table, SWT.CHECK);
		check_11.setBackground(EccTreeControl.color);
		check_11.pack();
		check_11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_11="true";
			}
		});
		permission_11="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_11.setSelection(true);
					}
				}else{
					check_11.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_11.minimumWidth = check_11.getSize ().x;
		editor_11.setEditor(check_11, item1, 12);	
		
		TableEditor editor_12 = new TableEditor(table);
		final Button check_12 = new Button(table, SWT.CHECK);
		check_12.setBackground(EccTreeControl.color);
		check_12.pack();
		check_12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_12="true";
			}
		});
		permission_12="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_12.setSelection(true);
					}
				}else{
					check_12.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_12.minimumWidth = check_12.getSize ().x;
		editor_12.setEditor(check_12, item1, 13);	
		
		TableEditor editor_13 = new TableEditor(table);
		final Button check_13 = new Button(table, SWT.CHECK);
		check_13.setBackground(EccTreeControl.color);
		check_13.pack();
		check_13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_13="true";
			}
		});
		permission_13="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_13.setSelection(true);
					}
				}else{
					check_13.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_13.minimumWidth = check_13.getSize ().x;
		editor_13.setEditor(check_13, item1, 14);	
		
		TableEditor editor_14 = new TableEditor(table);
		final Button check_14 = new Button(table, SWT.CHECK);
		check_14.setBackground(EccTreeControl.color);
		check_14.pack();
		check_14.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_14="true";
			}
		});
		permission_14="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_14.setSelection(true);
					}
				}else{
					check_14.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_14.minimumWidth = check_14.getSize ().x;
		editor_14.setEditor(check_14, item1, 15);	
		
		TableEditor editor_15 = new TableEditor(table);
		final Button check_15 = new Button(table, SWT.CHECK);
		check_15.setBackground(EccTreeControl.color);
		check_15.pack();
		check_15.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_15="true";
			}
		});
		permission_15="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_15.setSelection(true);
					}
				}else{
					check_15.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_15.minimumWidth = check_15.getSize ().x;
		editor_15.setEditor(check_15, item1, 16);	
		
		TableEditor editor_16 = new TableEditor(table);
		final Button check_16 = new Button(table, SWT.CHECK);
		check_16.setBackground(EccTreeControl.color);
		check_16.pack();
		check_16.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_16="true";
			}
		});
		permission_16="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_16.setSelection(true);
					}
				}else{
					check_16.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_16.minimumWidth = check_16.getSize ().x;
		editor_16.setEditor(check_16, item1, 17);	
		
		TableEditor editor_17 = new TableEditor(table);
		final Button check_17 = new Button(table, SWT.CHECK);
		check_17.setBackground(EccTreeControl.color);
		check_17.pack();
		check_17.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_17="true";
			}
		});
		permission_17="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_17.setSelection(true);
					}
				}else{
					check_17.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_17.minimumWidth = check_17.getSize ().x;
		editor_17.setEditor(check_17, item1, 18);	
		
		TableEditor editor_18 = new TableEditor(table);
		final Button check_18 = new Button(table, SWT.CHECK);
		check_18.setBackground(EccTreeControl.color);
		check_18.pack();
		check_18.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_18="true";
			}
		});
		permission_18="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_18.setSelection(true);
					}
				}else{
					check_18.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_18.minimumWidth = check_18.getSize ().x;
		editor_18.setEditor(check_18, item1, 19);
		
		TableEditor editor_19 = new TableEditor(table);
		final Button check_19 = new Button(table, SWT.CHECK);
		check_19.setBackground(EccTreeControl.color);
		check_19.pack();
		check_19.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_19="true";
			}
		});
		permission_19="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_19.setSelection(true);
					}
				}else{
					check_19.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_19.minimumWidth = check_19.getSize ().x;
		editor_19.setEditor(check_19, item1, 20);	
		
		TableEditor editor_20 = new TableEditor(table);
		final Button check_20 = new Button(table, SWT.CHECK);
		check_20.setBackground(EccTreeControl.color);
		check_20.pack();
		check_20.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_20="true";
			}
		});
		permission_20="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_20.setSelection(true);
					}
				}else{
					check_20.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_20.minimumWidth = check_20.getSize ().x;
		editor_20.setEditor(check_20, item1, 21);	
		
		TableEditor editor_21 = new TableEditor(table);
		final Button check_21 = new Button(table, SWT.CHECK);
		check_21.setBackground(EccTreeControl.color);
		check_21.pack();
		check_21.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_21="true";
			}
		});
		permission_21="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_21.setSelection(true);
					}
				}else{
					check_21.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_21.minimumWidth = check_21.getSize ().x;
		editor_21.setEditor(check_21, item1, 22);	
		
		TableEditor editor_22 = new TableEditor(table);
		final Button check_22 = new Button(table, SWT.CHECK);
		check_22.setBackground(EccTreeControl.color);
		check_22.pack();
		check_22.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_22="true";
			}
		});
		permission_22="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_22.setSelection(true);
					}
				}else{
					check_22.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_22.minimumWidth = check_22.getSize ().x;
		editor_22.setEditor(check_22, item1, 23);	
		
		TableEditor editor_23 = new TableEditor(table);
		final Button check_23 = new Button(table, SWT.CHECK);
		check_23.setBackground(EccTreeControl.color);
		check_23.pack();
		check_23.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_23="true";
			}
		});
		permission_23="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期日")){
                        check_23.setSelection(true);
					}
				}else{
					check_23.setSelection(false);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_23.minimumWidth = check_23.getSize ().x;
		editor_23.setEditor(check_23, item1, 24);	
		
		
		item2 = new TableItem(table, SWT.NONE);
		item2.setText(0, "星期一");
		TableEditor editor_a1 = new TableEditor(table);
		final Button check_a1 = new Button(table, SWT.CHECK);
		check_a1.setBackground(EccTreeControl.color);
		check_a1.pack();
		check_a1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_24="true";
				
			}
		});
		permission_24="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a1.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a1.minimumWidth = check_a1.getSize ().x;
		editor_a1.setEditor(check_a1, item2, 1);
		
		TableEditor editor_a2 = new TableEditor(table);
		final Button check_a2 = new Button(table, SWT.CHECK);
		
		check_a2.setBackground(EccTreeControl.color);
		check_a2.pack();
		check_a2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_25="true";
			}
		});
		permission_25="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a2.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a2.minimumWidth = check_a2.getSize ().x;
		editor_a2.setEditor(check_a2, item2, 2);
		
		TableEditor editor_a3 = new TableEditor(table);
		final Button check_a3 = new Button(table, SWT.CHECK);
		check_a3.setBackground(EccTreeControl.color);
		check_a3.pack();
		check_a3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_26="true";
			}
		});
		permission_26="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a3.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a3.minimumWidth = check_a3.getSize ().x;
		editor_a3.setEditor(check_a3, item2, 3);
		
		TableEditor editor_a4 = new TableEditor(table);
		final Button check_a4 = new Button(table, SWT.CHECK);
		check_a4.setBackground(EccTreeControl.color);
		check_a4.pack();
		check_a4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_27="true";
			}
		});
		permission_27="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a4.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a4.minimumWidth = check_a4.getSize ().x;
		editor_a4.setEditor(check_a4, item2, 4);
		
		TableEditor editor_a5 = new TableEditor(table);
		final Button check_a5 = new Button(table, SWT.CHECK);
		check_a5.setBackground(EccTreeControl.color);
		check_a5.pack();
		check_a5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_28="true";
			}
		});
		permission_28="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a5.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a5.minimumWidth = check_a5.getSize ().x;
		editor_a5.setEditor(check_a5, item2, 5);
		
		TableEditor editor_a6 = new TableEditor(table);
		final Button check_a6 = new Button(table, SWT.CHECK);
		check_a6.setBackground(EccTreeControl.color);
		check_a6.pack();
		check_a6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_29="true";
			}
		});
		permission_29="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a6.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a6.minimumWidth = check_a6.getSize ().x;
		editor_a6.setEditor(check_a6, item2, 6);
		
		TableEditor editor_a7 = new TableEditor(table);
		final Button check_a7 = new Button(table, SWT.CHECK);
		check_a7.setBackground(EccTreeControl.color);
		check_a7.pack();
		check_a7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_30="true";
			}
		});
		permission_30="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a7.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a7.minimumWidth = check_a7.getSize ().x;
		editor_a7.setEditor(check_a7, item2, 7);
		
		TableEditor editor_a8 = new TableEditor(table);
		final Button check_a8 = new Button(table, SWT.CHECK);
		check_a8.setBackground(EccTreeControl.color);
		check_a8.pack();
		check_a8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_31="true";
			}
		});
		permission_31="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a8.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a8.minimumWidth = check_a8.getSize ().x;
		editor_a8.setEditor(check_a8, item2, 8);
		
		TableEditor editor_a9 = new TableEditor(table);
		final Button check_a9 = new Button(table, SWT.CHECK);
		check_a9.setBackground(EccTreeControl.color);
		check_a9.pack();
		check_a9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_32="true";
			}
		});
		permission_32="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a9.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a9.minimumWidth = check_a9.getSize ().x;
		editor_a9.setEditor(check_a9, item2, 9);
		
		TableEditor editor_a10 = new TableEditor(table);
		final Button check_a10 = new Button(table, SWT.CHECK);
		check_a10.setBackground(EccTreeControl.color);
		check_a10.pack();
		check_a10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_33="true";
			}
		});
		permission_33="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a10.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a10.minimumWidth = check_a10.getSize ().x;
		editor_a10.setEditor(check_a10, item2, 10);
		
		TableEditor editor_a11 = new TableEditor(table);
		final Button check_a11 = new Button(table, SWT.CHECK);
		check_a11.setBackground(EccTreeControl.color);
		check_a11.pack();
		check_a11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_34="true";
			}
		});
		permission_34="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a11.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a11.minimumWidth = check_a11.getSize ().x;
		editor_a11.setEditor(check_a11, item2, 11);
		
		TableEditor editor_a12 = new TableEditor(table);
		final Button check_a12 = new Button(table, SWT.CHECK);
		check_a12.setBackground(EccTreeControl.color);
		check_a12.pack();
		check_a12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_35="true";
			}
		});
		permission_35="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a12.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a12.minimumWidth = check_a12.getSize ().x;
		editor_a12.setEditor(check_a12, item2, 12);
		
		TableEditor editor_a13 = new TableEditor(table);
		final Button check_a13 = new Button(table, SWT.CHECK);
		check_a13.setBackground(EccTreeControl.color);
		check_a13.pack();
		check_a13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_36="true";
			}
		});
		permission_36="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a13.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a13.minimumWidth = check_a13.getSize ().x;
		editor_a13.setEditor(check_a13, item2, 13);
		
		TableEditor editor_a14 = new TableEditor(table);
		final Button check_a14 = new Button(table, SWT.CHECK);
		check_a14.setBackground(EccTreeControl.color);
		check_a14.pack();
		check_a14.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_37="true";
			}
		});
		permission_37="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a14.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a14.minimumWidth = check_a14.getSize ().x;
		editor_a14.setEditor(check_a14, item2, 14);
		
		TableEditor editor_a15 = new TableEditor(table);
		final Button check_a15 = new Button(table, SWT.CHECK);
		check_a15.setBackground(EccTreeControl.color);
		check_a15.pack();
		check_a15.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_38="true";
			}
		});
		permission_38="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a15.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a15.minimumWidth = check_a15.getSize ().x;
		editor_a15.setEditor(check_a15, item2, 15);
		
		TableEditor editor_a16 = new TableEditor(table);
		final Button check_a16 = new Button(table, SWT.CHECK);
		check_a16.setBackground(EccTreeControl.color);
		check_a16.pack();
		check_a16.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_39="true";
			}
		});
		permission_39="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a16.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a16.minimumWidth = check_a16.getSize ().x;
		editor_a16.setEditor(check_a16, item2, 16);
		
		TableEditor editor_a17 = new TableEditor(table);
		final Button check_a17 = new Button(table, SWT.CHECK);
		check_a17.setBackground(EccTreeControl.color);
		check_a17.pack();
		check_a17.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_40="true";
			}
		});
		permission_40="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a17.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a17.minimumWidth = check_a17.getSize ().x;
		editor_a17.setEditor(check_a17, item2, 17);
		
		TableEditor editor_a18 = new TableEditor(table);
		final Button check_a18 = new Button(table, SWT.CHECK);
		check_a18.setBackground(EccTreeControl.color);
		check_a18.pack();
		check_a18.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_41="true";
			}
		});
		permission_41="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a18.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a18.minimumWidth = check_a18.getSize ().x;
		editor_a18.setEditor(check_a18, item2, 18);
		
		TableEditor editor_a19 = new TableEditor(table);
		final Button check_a19 = new Button(table, SWT.CHECK);
		check_a19.setBackground(EccTreeControl.color);
		check_a19.pack();
		check_a19.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_42="true";
			}
		});
		permission_42="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a19.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a19.minimumWidth = check_a19.getSize ().x;
		editor_a19.setEditor(check_a19, item2, 19);
		
		TableEditor editor_a20 = new TableEditor(table);
		final Button check_a20 = new Button(table, SWT.CHECK);
		check_a20.setBackground(EccTreeControl.color);
		check_a20.pack();
		check_a20.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_43="true";
			}
		});
		permission_43="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a20.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a20.minimumWidth = check_a20.getSize ().x;
		editor_a20.setEditor(check_a20, item2, 20);
		
		TableEditor editor_a21 = new TableEditor(table);
		final Button check_a21 = new Button(table, SWT.CHECK);
		check_a21.setBackground(EccTreeControl.color);
		check_a21.pack();
		check_a21.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_44="true";
			}
		});
		permission_44="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a21.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a21.minimumWidth = check_a21.getSize ().x;
		editor_a21.setEditor(check_a21, item2, 21);
		
		TableEditor editor_a22 = new TableEditor(table);
		final Button check_a22 = new Button(table, SWT.CHECK);
		check_a22.setBackground(EccTreeControl.color);
		check_a22.pack();
		check_a22.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_45="true";
			}
		});
		permission_45="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a22.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a22.minimumWidth = check_a22.getSize ().x;
		editor_a22.setEditor(check_a22, item2, 22);
		
		TableEditor editor_a23 = new TableEditor(table);
		final Button check_a23 = new Button(table, SWT.CHECK);
		check_a23.setBackground(EccTreeControl.color);
		check_a23.pack();
		check_a23.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_46="true";
			}
		});
		permission_46="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a23.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a23.minimumWidth = check_a23.getSize ().x;
		editor_a23.setEditor(check_a23, item2, 23);
		
		TableEditor editor_a24 = new TableEditor(table);
		final Button check_a24 = new Button(table, SWT.CHECK);
		check_a24.setBackground(EccTreeControl.color);
		check_a24.pack();
		check_a24.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_47="true";
			}
		});
		permission_47="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期一")){
						check_a24.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_a24.minimumWidth = check_a24.getSize ().x;
		editor_a24.setEditor(check_a24, item2, 24);
		
		
		item3 = new TableItem(table, SWT.NONE);
		item3.setText(0, "星期二");
		TableEditor editor_b1 = new TableEditor(table);
		final Button check_b1 = new Button(table, SWT.CHECK);
		check_b1.setBackground(EccTreeControl.color);
		check_b1.pack();
		check_b1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_48="true";
			}
		});
		permission_48="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b1.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b1.minimumWidth = check_b1.getSize ().x;
		editor_b1.setEditor(check_b1, item3, 1);
		
		TableEditor editor_b2 = new TableEditor(table);
		final Button check_b2 = new Button(table, SWT.CHECK);
		check_b2.setBackground(EccTreeControl.color);
		check_b2.pack();
		check_b2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_49="true";
			}
		});
		permission_49="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b2.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b2.minimumWidth = check_b2.getSize ().x;
		editor_b2.setEditor(check_b2, item3, 2);
		
		TableEditor editor_b3 = new TableEditor(table);
		final Button check_b3 = new Button(table, SWT.CHECK);
		check_b3.setBackground(EccTreeControl.color);
		check_b3.pack();
		check_b3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_50="true";
			}
		});
		permission_50="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b3.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b3.minimumWidth = check_b3.getSize ().x;
		editor_b3.setEditor(check_b3, item3, 3);
		
		TableEditor editor_b4 = new TableEditor(table);
		final Button check_b4 = new Button(table, SWT.CHECK);
		check_b4.setBackground(EccTreeControl.color);
		check_b4.pack();
		check_b4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_51="true";
			}
		});
		permission_51="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b4.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b4.minimumWidth = check_b4.getSize ().x;
		editor_b4.setEditor(check_b4, item3, 4);
		
		TableEditor editor_b5 = new TableEditor(table);
		final Button check_b5 = new Button(table, SWT.CHECK);
		check_b5.setBackground(EccTreeControl.color);
		check_b5.pack();
		check_b5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_52="true";
			}
		});
		permission_52="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b5.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b5.minimumWidth = check_b5.getSize ().x;
		editor_b5.setEditor(check_b5, item3, 5);
		
		TableEditor editor_b6 = new TableEditor(table);
		final Button check_b6 = new Button(table, SWT.CHECK);
		check_b6.setBackground(EccTreeControl.color);
		check_b6.pack();
		check_b6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_53="true";
			}
		});
		permission_53="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b6.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b6.minimumWidth = check_b6.getSize ().x;
		editor_b6.setEditor(check_b6, item3, 6);
		
		TableEditor editor_b7 = new TableEditor(table);
		final Button check_b7 = new Button(table, SWT.CHECK);
		check_b7.setBackground(EccTreeControl.color);
		check_b7.pack();
		check_b7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_54="true";
			}
		});
		permission_54="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b7.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b7.minimumWidth = check_b7.getSize ().x;
		editor_b7.setEditor(check_b7, item3, 7);
		
		TableEditor editor_b8 = new TableEditor(table);
		final Button check_b8 = new Button(table, SWT.CHECK);
		check_b8.setBackground(EccTreeControl.color);
		check_b8.pack();
		check_b8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_55="true";
			}
		});
		permission_55="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b8.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b8.minimumWidth = check_b8.getSize ().x;
		editor_b8.setEditor(check_b8, item3, 8);
		
		TableEditor editor_b9 = new TableEditor(table);
		final Button check_b9 = new Button(table, SWT.CHECK);
		check_b9.setBackground(EccTreeControl.color);
		check_b9.pack();
		check_b9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_56="true";
			}
		});
		permission_56="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b9.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b9.minimumWidth = check_b9.getSize ().x;
		editor_b9.setEditor(check_b9, item3, 9);
		
		TableEditor editor_b10 = new TableEditor(table);
		final Button check_b10 = new Button(table, SWT.CHECK);
		check_b10.setBackground(EccTreeControl.color);
		check_b10.pack();
		check_b10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_57="true";
			}
		});
		permission_57="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b10.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b10.minimumWidth = check_b10.getSize ().x;
		editor_b10.setEditor(check_b10, item3, 10);
		
		TableEditor editor_b11 = new TableEditor(table);
		final Button check_b11 = new Button(table, SWT.CHECK);
		check_b11.setBackground(EccTreeControl.color);
		check_b11.pack();
		check_b11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_58="true";
			}
		});
		permission_58="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b11.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b11.minimumWidth = check_b11.getSize ().x;
		editor_b11.setEditor(check_b11, item3, 11);
		
		TableEditor editor_b12 = new TableEditor(table);
		final Button check_b12 = new Button(table, SWT.CHECK);
		check_b12.setBackground(EccTreeControl.color);
		check_b12.pack();
		check_b12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_59="true";
			}
		});
		permission_59="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b12.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b12.minimumWidth = check_b12.getSize ().x;
		editor_b12.setEditor(check_b12, item3, 12);
		
		TableEditor editor_b13 = new TableEditor(table);
		final Button check_b13 = new Button(table, SWT.CHECK);
		check_b13.setBackground(EccTreeControl.color);
		check_b13.pack();
		check_b13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_60="true";
			}
		});
		permission_60="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b13.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b13.minimumWidth = check_b13.getSize ().x;
		editor_b13.setEditor(check_b13, item3, 13);
		
		TableEditor editor_b14 = new TableEditor(table);
		final Button check_b14 = new Button(table, SWT.CHECK);
		check_b14.setBackground(EccTreeControl.color);
		check_b14.pack();
		check_b14.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_61="true";
			}
		});
		permission_61="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b14.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b14.minimumWidth = check_b14.getSize ().x;
		editor_b14.setEditor(check_b14, item3, 14);
		
		TableEditor editor_b15 = new TableEditor(table);
		final Button check_b15 = new Button(table, SWT.CHECK);
		check_b15.setBackground(EccTreeControl.color);
		check_b15.pack();
		check_b15.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_62="true";
			}
		});
		permission_62="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b15.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b15.minimumWidth = check_b15.getSize ().x;
		editor_b15.setEditor(check_b15, item3, 15);
		
		TableEditor editor_b16 = new TableEditor(table);
		final Button check_b16 = new Button(table, SWT.CHECK);
		check_b16.setBackground(EccTreeControl.color);
		check_b16.pack();
		check_b16.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_63="true";
			}
		});
		permission_63="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b16.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b16.minimumWidth = check_b16.getSize ().x;
		editor_b16.setEditor(check_b16, item3, 16);
		
		TableEditor editor_b17 = new TableEditor(table);
		final Button check_b17 = new Button(table, SWT.CHECK);
		check_b17.setBackground(EccTreeControl.color);
		check_b17.pack();
		check_b17.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_64="true";
			}
		});
		permission_64="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b17.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b17.minimumWidth = check_b17.getSize ().x;
		editor_b17.setEditor(check_b17, item3, 17);
		
		TableEditor editor_b18 = new TableEditor(table);
		final Button check_b18 = new Button(table, SWT.CHECK);
		check_b18.setBackground(EccTreeControl.color);
		check_b18.pack();
		check_b18.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_65="true";
			}
		});
		permission_65="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b18.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b18.minimumWidth = check_b18.getSize ().x;
		editor_b18.setEditor(check_b18, item3, 18);
		
		TableEditor editor_b19 = new TableEditor(table);
		final Button check_b19 = new Button(table, SWT.CHECK);
		check_b19.setBackground(EccTreeControl.color);
		check_b19.pack();
		check_b19.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_66="true";
			}
		});
		permission_66="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b19.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b19.minimumWidth = check_b19.getSize ().x;
		editor_b19.setEditor(check_b19, item3, 19);
		
		TableEditor editor_b20 = new TableEditor(table);
		final Button check_b20 = new Button(table, SWT.CHECK);
		check_b20.setBackground(EccTreeControl.color);
		check_b20.pack();
		check_b20.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_67="true";
			}
		});
		permission_67="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b20.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b20.minimumWidth = check_b20.getSize ().x;
		editor_b20.setEditor(check_b20, item3, 20);
		
		TableEditor editor_b21 = new TableEditor(table);
		final Button check_b21 = new Button(table, SWT.CHECK);
		check_b21.setBackground(EccTreeControl.color);
		check_b21.pack();
		check_b21.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_68="true";
			}
		});
		permission_68="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b21.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b21.minimumWidth = check_b21.getSize ().x;
		editor_b21.setEditor(check_b21, item3, 21);
		
		TableEditor editor_b22 = new TableEditor(table);
		final Button check_b22 = new Button(table, SWT.CHECK);
		check_b22.setBackground(EccTreeControl.color);
		check_b22.pack();
		check_b22.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_69="true";
			}
		});
		permission_69="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b22.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b22.minimumWidth = check_b22.getSize ().x;
		editor_b22.setEditor(check_b22, item3, 22);
		
		TableEditor editor_b23 = new TableEditor(table);
		final Button check_b23 = new Button(table, SWT.CHECK);
		check_b23.setBackground(EccTreeControl.color);
		check_b23.pack();
		check_b23.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_70="true";
			}
		});
		permission_70="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b23.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b23.minimumWidth = check_b23.getSize ().x;
		editor_b23.setEditor(check_b23, item3, 23);
		
		TableEditor editor_b24 = new TableEditor(table);
		final Button check_b24 = new Button(table, SWT.CHECK);
		check_b24.setBackground(EccTreeControl.color);
		check_b24.pack();
		check_b24.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_71="true";
			}
		});
		permission_71="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期二")){
						check_b24.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_b24.minimumWidth = check_b24.getSize ().x;
		editor_b24.setEditor(check_b24, item3, 24);
		
		
		item4 = new TableItem(table, SWT.NONE);
		item4.setText(0, "星期三");
		TableEditor editor_c1 = new TableEditor(table);
		final Button check_c1 = new Button(table, SWT.CHECK);
		check_c1.setBackground(EccTreeControl.color);
		check_c1.pack();
		check_c1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_72="true";
			}
		});
		permission_72="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c1.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c1.minimumWidth = check_c1.getSize ().x;
		editor_c1.setEditor(check_c1, item4, 1);
		
		TableEditor editor_c2 = new TableEditor(table);
		final Button check_c2 = new Button(table, SWT.CHECK);
		check_c2.setBackground(EccTreeControl.color);
		check_c2.pack();
		check_c2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_73="true";
			}
		});
		permission_73="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c2.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c2.minimumWidth = check_c2.getSize ().x;
		editor_c2.setEditor(check_c2, item4, 2);
		
		TableEditor editor_c3 = new TableEditor(table);
		final Button check_c3 = new Button(table, SWT.CHECK);
		check_c3.setBackground(EccTreeControl.color);
		check_c3.pack();
		check_c3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_74="true";
			}
		});
		permission_74="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c3.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c3.minimumWidth = check_c3.getSize ().x;
		editor_c3.setEditor(check_c3, item4, 3);
		
		TableEditor editor_c4 = new TableEditor(table);
		final Button check_c4 = new Button(table, SWT.CHECK);
		check_c4.setBackground(EccTreeControl.color);
		check_c4.pack();
		check_c4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_75="true";
			}
		});
		permission_75="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c4.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c4.minimumWidth = check_c4.getSize ().x;
		editor_c4.setEditor(check_c4, item4, 4);
		
		TableEditor editor_c5 = new TableEditor(table);
		final Button check_c5 = new Button(table, SWT.CHECK);
		check_c5.setBackground(EccTreeControl.color);
		check_c5.pack();
		check_c5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_76="true";
			}
		});
		permission_76="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c5.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c5.minimumWidth = check_c5.getSize ().x;
		editor_c5.setEditor(check_c5, item4, 5);
		
		TableEditor editor_c6 = new TableEditor(table);
		final Button check_c6 = new Button(table, SWT.CHECK);
		check_c6.setBackground(EccTreeControl.color);
		check_c6.pack();
		check_c6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_77="true";
			}
		});
		permission_77="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c6.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c6.minimumWidth = check_c6.getSize ().x;
		editor_c6.setEditor(check_c6, item4, 6);
		
		TableEditor editor_c7 = new TableEditor(table);
		final Button check_c7 = new Button(table, SWT.CHECK);
		check_c7.setBackground(EccTreeControl.color);
		check_c7.pack();
		check_c7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_78="true";
			}
		});
		permission_78="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c7.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c7.minimumWidth = check_c7.getSize ().x;
		editor_c7.setEditor(check_c7, item4, 7);
		
		TableEditor editor_c8 = new TableEditor(table);
		final Button check_c8 = new Button(table, SWT.CHECK);
		check_c8.setBackground(EccTreeControl.color);
		check_c8.pack();
		check_c8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_79="true";
			}
		});
		permission_79="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c8.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c8.minimumWidth = check_c8.getSize ().x;
		editor_c8.setEditor(check_c8, item4, 8);
		
		TableEditor editor_c9 = new TableEditor(table);
		final Button check_c9 = new Button(table, SWT.CHECK);
		check_c9.setBackground(EccTreeControl.color);
		check_c9.pack();
		check_c9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_80="true";
			}
		});
		permission_80="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c9.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c9.minimumWidth = check_c9.getSize ().x;
		editor_c9.setEditor(check_c9, item4, 9);
		
		TableEditor editor_c10 = new TableEditor(table);
		final Button check_c10 = new Button(table, SWT.CHECK);
		check_c10.setBackground(EccTreeControl.color);
		check_c10.pack();
		check_c10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_81="true";
			}
		});
		permission_81="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c10.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c10.minimumWidth = check_c10.getSize ().x;
		editor_c10.setEditor(check_c10, item4, 10);
		
		TableEditor editor_c11 = new TableEditor(table);
		final Button check_c11 = new Button(table, SWT.CHECK);
		check_c11.setBackground(EccTreeControl.color);
		check_c11.pack();
		check_c11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_82="true";
			}
		});
		permission_82="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c11.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c11.minimumWidth = check_c11.getSize ().x;
		editor_c11.setEditor(check_c11, item4, 11);
		
		TableEditor editor_c12 = new TableEditor(table);
		final Button check_c12 = new Button(table, SWT.CHECK);
		check_c12.setBackground(EccTreeControl.color);
		check_c12.pack();
		check_c12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_83="true";
			}
		});
		permission_83="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c12.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c12.minimumWidth = check_c12.getSize ().x;
		editor_c12.setEditor(check_c12, item4, 12);
		
		TableEditor editor_c13 = new TableEditor(table);
		final Button check_c13 = new Button(table, SWT.CHECK);
		check_c13.setBackground(EccTreeControl.color);
		check_c13.pack();
		check_c13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_84="true";
			}
		});
		permission_84="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c13.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c13.minimumWidth = check_c13.getSize ().x;
		editor_c13.setEditor(check_c13, item4, 13);
		
		TableEditor editor_c14 = new TableEditor(table);
		final Button check_c14 = new Button(table, SWT.CHECK);
		check_c14.setBackground(EccTreeControl.color);
		check_c14.pack();
		check_c14.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_85="true";
			}
		});
		permission_85="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c14.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c14.minimumWidth = check_c14.getSize ().x;
		editor_c14.setEditor(check_c14, item4, 14);
		
		TableEditor editor_c15 = new TableEditor(table);
		final Button check_c15 = new Button(table, SWT.CHECK);
		check_c15.setBackground(EccTreeControl.color);
		check_c15.pack();
		check_c15.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_86="true";
			}
		});
		permission_86="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c15.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c15.minimumWidth = check_c15.getSize ().x;
		editor_c15.setEditor(check_c15, item4, 15);
		
		TableEditor editor_c16 = new TableEditor(table);
		final Button check_c16 = new Button(table, SWT.CHECK);
		check_c16.setBackground(EccTreeControl.color);
		check_c16.pack();
		check_c16.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_87="true";
			}
		});
		permission_87="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c16.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c16.minimumWidth = check_c16.getSize ().x;
		editor_c16.setEditor(check_c16, item4, 16);
		
		TableEditor editor_c17 = new TableEditor(table);
		final Button check_c17 = new Button(table, SWT.CHECK);
		check_c17.setBackground(EccTreeControl.color);
		check_c17.pack();
		check_c17.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_88="true";
			}
		});
		permission_88="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c17.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c17.minimumWidth = check_c17.getSize ().x;
		editor_c17.setEditor(check_c17, item4, 17);
		
		TableEditor editor_c18 = new TableEditor(table);
		final Button check_c18 = new Button(table, SWT.CHECK);
		check_c18.setBackground(EccTreeControl.color);
		check_c18.pack();
		check_c18.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_89="true";
			}
		});
		permission_89="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c18.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c18.minimumWidth = check_c18.getSize ().x;
		editor_c18.setEditor(check_c18, item4, 18);
		
		TableEditor editor_c19 = new TableEditor(table);
		final Button check_c19 = new Button(table, SWT.CHECK);
		check_c19.setBackground(EccTreeControl.color);
		check_c19.pack();
		check_c19.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_90="true";
			}
		});
		permission_90="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c19.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c19.minimumWidth = check_c19.getSize ().x;
		editor_c19.setEditor(check_c19, item4, 19);
		
		TableEditor editor_c20 = new TableEditor(table);
		final Button check_c20 = new Button(table, SWT.CHECK);
		check_c20.setBackground(EccTreeControl.color);
		check_c20.pack();
		check_c20.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_91="true";
			}
		});
		permission_91="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c20.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c20.minimumWidth = check_c20.getSize ().x;
		editor_c20.setEditor(check_c20, item4, 20);
		
		TableEditor editor_c21 = new TableEditor(table);
		final Button check_c21 = new Button(table, SWT.CHECK);
		check_c21.setBackground(EccTreeControl.color);
		check_c21.pack();
		check_c21.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_92="true";
			}
		});
		permission_92="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c21.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c21.minimumWidth = check_c21.getSize ().x;
		editor_c21.setEditor(check_c21, item4, 21);
		
		TableEditor editor_c22 = new TableEditor(table);
		final Button check_c22 = new Button(table, SWT.CHECK);
		check_c22.setBackground(EccTreeControl.color);
		check_c22.pack();
		check_c22.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_93="true";
			}
		});
		permission_93="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c22.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c22.minimumWidth = check_c22.getSize ().x;
		editor_c22.setEditor(check_c22, item4, 22);
		
		TableEditor editor_c23 = new TableEditor(table);
		final Button check_c23 = new Button(table, SWT.CHECK);
		check_c23.setBackground(EccTreeControl.color);
		check_c23.pack();
		check_c23.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_94="true";
			}
		});
		permission_94="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c23.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c23.minimumWidth = check_c23.getSize ().x;
		editor_c23.setEditor(check_c23, item4, 23);
		
		TableEditor editor_c24 = new TableEditor(table);
		final Button check_c24 = new Button(table, SWT.CHECK);
		check_c24.setBackground(EccTreeControl.color);
		check_c24.pack();
		check_c24.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_95="true";
			}
		});
		permission_95="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期三")){
						check_c24.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_c24.minimumWidth = check_c24.getSize ().x;
		editor_c24.setEditor(check_c24, item4, 24);
		
		
		item5 = new TableItem(table, SWT.NONE);
		item5.setText(0, "星期四");
		TableEditor editor_d1 = new TableEditor(table);
		final Button check_d1 = new Button(table, SWT.CHECK);
		check_d1.setBackground(EccTreeControl.color);
		check_d1.pack();
		check_d1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_96="true";
			}
		});
		permission_96="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d1.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d1.minimumWidth = check_d1.getSize ().x;
		editor_d1.setEditor(check_d1, item5, 1);
		
		TableEditor editor_d2 = new TableEditor(table);
		final Button check_d2 = new Button(table, SWT.CHECK);
		check_d2.setBackground(EccTreeControl.color);
		check_d2.pack();
		check_d2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_97="true";
			}
		});
		permission_97="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d2.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d2.minimumWidth = check_d2.getSize ().x;
		editor_d2.setEditor(check_d2, item5, 2);
		
		TableEditor editor_d3 = new TableEditor(table);
		final Button check_d3 = new Button(table, SWT.CHECK);
		check_d3.setBackground(EccTreeControl.color);
		check_d3.pack();
		check_d3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_98="true";
			}
		});
		permission_98="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d3.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d3.minimumWidth = check_d3.getSize ().x;
		editor_d3.setEditor(check_d3, item5, 3);
		
		TableEditor editor_d4 = new TableEditor(table);
		final Button check_d4 = new Button(table, SWT.CHECK);
		check_d4.setBackground(EccTreeControl.color);
		check_d4.pack();
		check_d4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_99="true";
			}
		});
		permission_99="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d4.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d4.minimumWidth = check_d4.getSize ().x;
		editor_d4.setEditor(check_d4, item5, 4);
		
		TableEditor editor_d5 = new TableEditor(table);
		final Button check_d5 = new Button(table, SWT.CHECK);
		check_d5.setBackground(EccTreeControl.color);
		check_d5.pack();
		check_d5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_100="true";
			}
		});
		permission_100="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d5.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d5.minimumWidth = check_d5.getSize ().x;
		editor_d5.setEditor(check_d5, item5, 5);
		
		TableEditor editor_d6 = new TableEditor(table);
		final Button check_d6 = new Button(table, SWT.CHECK);
		check_d6.setBackground(EccTreeControl.color);
		check_d6.pack();
		check_d6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_101="true";
			}
		});
		permission_101="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d6.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d6.minimumWidth = check_d6.getSize ().x;
		editor_d6.setEditor(check_d6, item5, 6);
		
		TableEditor editor_d7 = new TableEditor(table);
		final Button check_d7 = new Button(table, SWT.CHECK);
		check_d7.setBackground(EccTreeControl.color);
		check_d7.pack();
		check_d7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_102="true";
			}
		});
		permission_102="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d7.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d7.minimumWidth = check_d7.getSize ().x;
		editor_d7.setEditor(check_d7, item5, 7);
		
		TableEditor editor_d8 = new TableEditor(table);
		final Button check_d8 = new Button(table, SWT.CHECK);
		check_d8.setBackground(EccTreeControl.color);
		check_d8.pack();
		check_d8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_103="true";
			}
		});
		permission_103="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d8.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d8.minimumWidth = check_d8.getSize ().x;
		editor_d8.setEditor(check_d8, item5, 8);
		
		TableEditor editor_d9 = new TableEditor(table);
		final Button check_d9 = new Button(table, SWT.CHECK);
		check_d9.setBackground(EccTreeControl.color);
		check_d9.pack();
		check_d9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_104="true";
			}
		});
		permission_104="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d9.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d9.minimumWidth = check_d9.getSize ().x;
		editor_d9.setEditor(check_d9, item5, 9);
		
		TableEditor editor_d10 = new TableEditor(table);
		final Button check_d10 = new Button(table, SWT.CHECK);
		check_d10.setBackground(EccTreeControl.color);
		check_d10.pack();
		check_d10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_105="true";
			}
		});
		permission_105="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d10.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d10.minimumWidth = check_d10.getSize ().x;
		editor_d10.setEditor(check_d10, item5, 10);
		
		TableEditor editor_d11 = new TableEditor(table);
		final Button check_d11 = new Button(table, SWT.CHECK);
		check_d11.setBackground(EccTreeControl.color);
		check_d11.pack();
		check_d11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_106="true";
			}
		});
		permission_106="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d11.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d11.minimumWidth = check_d11.getSize ().x;
		editor_d11.setEditor(check_d11, item5, 11);
		
		TableEditor editor_d12 = new TableEditor(table);
		final Button check_d12 = new Button(table, SWT.CHECK);
		check_d12.setBackground(EccTreeControl.color);
		check_d12.pack();
		check_d12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_107="true";
			}
		});
		permission_107="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d12.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d12.minimumWidth = check_c12.getSize ().x;
		editor_d12.setEditor(check_d12, item5, 12);
		
		TableEditor editor_d13 = new TableEditor(table);
		final Button check_d13 = new Button(table, SWT.CHECK);
		check_d13.setBackground(EccTreeControl.color);
		check_d13.pack();
		check_d13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_108="true";
			}
		});
		permission_108="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d13.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d13.minimumWidth = check_d13.getSize ().x;
		editor_d13.setEditor(check_d13, item5, 13);
		
		TableEditor editor_d14 = new TableEditor(table);
		final Button check_d14 = new Button(table, SWT.CHECK);
		check_d14.setBackground(EccTreeControl.color);
		check_d14.pack();
		check_d14.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_109="true";
			}
		});
		permission_109="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d14.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d14.minimumWidth = check_d14.getSize ().x;
		editor_d14.setEditor(check_d14, item5, 14);
		
		TableEditor editor_d15 = new TableEditor(table);
		final Button check_d15 = new Button(table, SWT.CHECK);
		check_d15.setBackground(EccTreeControl.color);
		check_d15.pack();
		check_d15.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_110="true";
			}
		});
		permission_110="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d15.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d15.minimumWidth = check_d15.getSize ().x;
		editor_d15.setEditor(check_d15, item5, 15);
		
		TableEditor editor_d16 = new TableEditor(table);
		final Button check_d16 = new Button(table, SWT.CHECK);
		check_d16.setBackground(EccTreeControl.color);
		check_d16.pack();
		check_d16.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_111="true";
			}
		});
		permission_111="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d16.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d16.minimumWidth = check_d16.getSize ().x;
		editor_d16.setEditor(check_d16, item5, 16);
		
		TableEditor editor_d17 = new TableEditor(table);
		final Button check_d17 = new Button(table, SWT.CHECK);
		check_d17.setBackground(EccTreeControl.color);
		check_d17.pack();
		check_d17.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_112="true";
			}
		});
		permission_112="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d17.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d17.minimumWidth = check_d17.getSize ().x;
		editor_d17.setEditor(check_d17, item5, 17);
		
		TableEditor editor_d18 = new TableEditor(table);
		final Button check_d18 = new Button(table, SWT.CHECK);
		check_d18.setBackground(EccTreeControl.color);
		check_d18.pack();
		check_d18.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_113="true";
			}
		});
		permission_113="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d18.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d18.minimumWidth = check_d18.getSize ().x;
		editor_d18.setEditor(check_d18, item5, 18);
		
		TableEditor editor_d19 = new TableEditor(table);
		final Button check_d19 = new Button(table, SWT.CHECK);
		check_d19.setBackground(EccTreeControl.color);
		check_d19.pack();
		check_d19.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_114="true";
			}
		});
		permission_114="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d19.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d19.minimumWidth = check_d19.getSize ().x;
		editor_d19.setEditor(check_d19, item5, 19);
		
		TableEditor editor_d20 = new TableEditor(table);
		final Button check_d20 = new Button(table, SWT.CHECK);
		check_d20.setBackground(EccTreeControl.color);
		check_d20.pack();
		check_d20.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_115="true";
			}
		});
		permission_115="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d20.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d20.minimumWidth = check_d20.getSize ().x;
		editor_d20.setEditor(check_d20, item5, 20);
		
		TableEditor editor_d21 = new TableEditor(table);
		final Button check_d21 = new Button(table, SWT.CHECK);
		check_d21.setBackground(EccTreeControl.color);
		check_d21.pack();
		check_d21.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_116="true";
			}
		});
		permission_116="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d21.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d21.minimumWidth = check_d21.getSize ().x;
		editor_d21.setEditor(check_d21, item5, 21);
		
		TableEditor editor_d22 = new TableEditor(table);
		final Button check_d22 = new Button(table, SWT.CHECK);
		check_d22.setBackground(EccTreeControl.color);
		check_d22.pack();
		check_d22.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_117="true";
			}
		});
		permission_117="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d22.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d22.minimumWidth = check_d22.getSize ().x;
		editor_d22.setEditor(check_d22, item5, 22);
		
		TableEditor editor_d23 = new TableEditor(table);
		final Button check_d23 = new Button(table, SWT.CHECK);
		check_d23.setBackground(EccTreeControl.color);
		check_d23.pack();
		check_d23.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_118="true";
			}
		});
		permission_118="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d23.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d23.minimumWidth = check_d23.getSize ().x;
		editor_d23.setEditor(check_d23, item5, 23);
		
		TableEditor editor_d24 = new TableEditor(table);
		final Button check_d24 = new Button(table, SWT.CHECK);
		check_d24.setBackground(EccTreeControl.color);
		check_d24.pack();
		check_d24.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_119="true";
			}
		});
		permission_119="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期四")){
						check_d24.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_d24.minimumWidth = check_d24.getSize ().x;
		editor_d24.setEditor(check_d24, item5, 24);
		
		
		
		item6 = new TableItem(table, SWT.NONE);
		item6.setText(0, "星期五");
		TableEditor editor_e1 = new TableEditor(table);
		final Button check_e1 = new Button(table, SWT.CHECK);
		check_e1.setBackground(EccTreeControl.color);
		check_e1.pack();
		check_e1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_120="true";
			}
		});
		permission_120="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e1.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e1.minimumWidth = check_e1.getSize ().x;
		editor_e1.setEditor(check_e1, item6, 1);
		
		TableEditor editor_e2 = new TableEditor(table);
		final Button check_e2 = new Button(table, SWT.CHECK);
		check_e2.setBackground(EccTreeControl.color);
		check_e2.pack();
		check_e2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_121="true";
			}
		});
		permission_121="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e2.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e2.minimumWidth = check_e2.getSize ().x;
		editor_e2.setEditor(check_e2, item6, 2);
		
		TableEditor editor_e3 = new TableEditor(table);
		final Button check_e3 = new Button(table, SWT.CHECK);
		check_e3.setBackground(EccTreeControl.color);
		check_e3.pack();
		check_e3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_122="true";
			}
		});
		permission_122="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e3.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e3.minimumWidth = check_e3.getSize ().x;
		editor_e3.setEditor(check_e3, item6, 3);
		
		TableEditor editor_e4 = new TableEditor(table);
		final Button check_e4 = new Button(table, SWT.CHECK);
		check_e4.setBackground(EccTreeControl.color);
		check_e4.pack();
		check_e4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_123="true";
			}
		});
		permission_123="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e4.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e4.minimumWidth = check_e4.getSize ().x;
		editor_e4.setEditor(check_e4, item6, 4);
		
		TableEditor editor_e5 = new TableEditor(table);
		final Button check_e5 = new Button(table, SWT.CHECK);
		check_e5.setBackground(EccTreeControl.color);
		check_e5.pack();
		check_e5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_124="true";
			}
		});
		permission_124="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e5.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e5.minimumWidth = check_e5.getSize ().x;
		editor_e5.setEditor(check_e5, item6, 5);
		
		TableEditor editor_e6 = new TableEditor(table);
		final Button check_e6 = new Button(table, SWT.CHECK);
		check_e6.setBackground(EccTreeControl.color);
		check_e6.pack();
		check_e6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_125="true";
			}
		});
		permission_125="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e6.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e6.minimumWidth = check_e6.getSize ().x;
		editor_e6.setEditor(check_e6, item6, 6);
		
		TableEditor editor_e7 = new TableEditor(table);
		final Button check_e7 = new Button(table, SWT.CHECK);
		check_e7.setBackground(EccTreeControl.color);
		check_e7.pack();
		check_e7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_126="true";
			}
		});
		permission_126="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e7.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e7.minimumWidth = check_e7.getSize ().x;
		editor_e7.setEditor(check_e7, item6, 7);
		
		TableEditor editor_e8 = new TableEditor(table);
		final Button check_e8 = new Button(table, SWT.CHECK);
		check_e8.setBackground(EccTreeControl.color);
		check_e8.pack();
		check_e8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_127="true";
			}
		});
		permission_127="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e8.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e8.minimumWidth = check_e8.getSize ().x;
		editor_e8.setEditor(check_e8, item6, 8);
		
		TableEditor editor_e9 = new TableEditor(table);
		final Button check_e9 = new Button(table, SWT.CHECK);
		check_e9.setBackground(EccTreeControl.color);
		check_e9.pack();
		check_e9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_128="true";
			}
		});
		permission_128="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e9.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e9.minimumWidth = check_e9.getSize ().x;
		editor_e9.setEditor(check_e9, item6, 9);
		
		TableEditor editor_e10 = new TableEditor(table);
		final Button check_e10 = new Button(table, SWT.CHECK);
		check_e10.setBackground(EccTreeControl.color);
		check_e10.pack();
		check_e10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_129="true";
			}
		});
		permission_129="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e10.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e10.minimumWidth = check_e10.getSize ().x;
		editor_e10.setEditor(check_e10, item6, 10);
		
		TableEditor editor_e11 = new TableEditor(table);
		final Button check_e11 = new Button(table, SWT.CHECK);
		check_e11.setBackground(EccTreeControl.color);
		check_e11.pack();
		check_e11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_130="true";
			}
		});
		permission_130="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e11.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e11.minimumWidth = check_e11.getSize ().x;
		editor_e11.setEditor(check_e11, item6, 11);
		
		TableEditor editor_e12 = new TableEditor(table);
		final Button check_e12 = new Button(table, SWT.CHECK);
		check_e12.setBackground(EccTreeControl.color);
		check_e12.pack();
		check_e12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_131="true";
			}
		});
		permission_131="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e12.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e12.minimumWidth = check_e12.getSize ().x;
		editor_e12.setEditor(check_e12, item6, 12);
		
		TableEditor editor_e13 = new TableEditor(table);
		final Button check_e13 = new Button(table, SWT.CHECK);
		check_e13.setBackground(EccTreeControl.color);
		check_e13.pack();
		check_e13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_132="true";
			}
		});
		permission_132="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e13.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e13.minimumWidth = check_e13.getSize ().x;
		editor_e13.setEditor(check_e13, item6, 13);
		
		TableEditor editor_e14 = new TableEditor(table);
		final Button check_e14 = new Button(table, SWT.CHECK);
		check_e14.setBackground(EccTreeControl.color);
		check_e14.pack();
		check_e14.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_133="true";
			}
		});
		permission_133="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e14.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e14.minimumWidth = check_e14.getSize ().x;
		editor_e14.setEditor(check_e14, item6, 14);
		
		TableEditor editor_e15 = new TableEditor(table);
		final Button check_e15 = new Button(table, SWT.CHECK);
		check_e15.setBackground(EccTreeControl.color);
		check_e15.pack();
		check_e15.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_134="true";
			}
		});
		permission_134="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e15.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e15.minimumWidth = check_e15.getSize ().x;
		editor_e15.setEditor(check_e15, item6, 15);
		
		TableEditor editor_e16 = new TableEditor(table);
		final Button check_e16 = new Button(table, SWT.CHECK);
		check_e16.setBackground(EccTreeControl.color);
		check_e16.pack();
		check_e16.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_135="true";
			}
		});
		permission_135="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e16.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e16.minimumWidth = check_e16.getSize ().x;
		editor_e16.setEditor(check_e16, item6, 16);
		
		TableEditor editor_e17 = new TableEditor(table);
		final Button check_e17 = new Button(table, SWT.CHECK);
		check_e17.setBackground(EccTreeControl.color);
		check_e17.pack();
		check_e17.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_136="true";
			}
		});
		permission_136="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e17.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e17.minimumWidth = check_e17.getSize ().x;
		editor_e17.setEditor(check_e17, item6, 17);
		
		TableEditor editor_e18 = new TableEditor(table);
		final Button check_e18 = new Button(table, SWT.CHECK);
		check_e18.setBackground(EccTreeControl.color);
		check_e18.pack();
		check_e18.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_137="true";
			}
		});
		permission_137="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e18.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e18.minimumWidth = check_e18.getSize ().x;
		editor_e18.setEditor(check_e18, item6, 18);
		
		TableEditor editor_e19 = new TableEditor(table);
		final Button check_e19 = new Button(table, SWT.CHECK);
		check_e19.setBackground(EccTreeControl.color);
		check_e19.pack();
		check_e19.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_138="true";
			}
		});
		permission_138="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e19.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e19.minimumWidth = check_e19.getSize ().x;
		editor_e19.setEditor(check_e19, item6, 19);
		
		TableEditor editor_e20 = new TableEditor(table);
		final Button check_e20 = new Button(table, SWT.CHECK);
		check_e20.setBackground(EccTreeControl.color);
		check_e20.pack();
		check_e20.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_139="true";
			}
		});
		permission_139="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e20.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e20.minimumWidth = check_e20.getSize ().x;
		editor_e20.setEditor(check_e20, item6, 20);
		
		TableEditor editor_e21 = new TableEditor(table);
		final Button check_e21 = new Button(table, SWT.CHECK);
		check_e21.setBackground(EccTreeControl.color);
		check_e21.pack();
		check_e21.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_140="true";
			}
		});
		permission_140="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e21.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e21.minimumWidth = check_e21.getSize ().x;
		editor_e21.setEditor(check_e21, item6, 21);
		
		TableEditor editor_e22 = new TableEditor(table);
		final Button check_e22 = new Button(table, SWT.CHECK);
		check_e22.setBackground(EccTreeControl.color);
		check_e22.pack();
		check_e22.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_141="true";
			}
		});
		permission_141="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e22.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e22.minimumWidth = check_e22.getSize ().x;
		editor_e22.setEditor(check_e22, item6, 22);
		
		TableEditor editor_e23 = new TableEditor(table);
		final Button check_e23 = new Button(table, SWT.CHECK);
		check_e23.setBackground(EccTreeControl.color);
		check_e23.pack();
		check_e23.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_142="true";
			}
		});
		permission_142="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e23.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e23.minimumWidth = check_e23.getSize ().x;
		editor_e23.setEditor(check_e23, item6, 23);
		
		TableEditor editor_e24 = new TableEditor(table);
		final Button check_e24 = new Button(table, SWT.CHECK);
		check_e24.setBackground(EccTreeControl.color);
		check_e24.pack();
		check_e24.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_143="true";
			}
		});
		permission_143="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期五")){
						check_e24.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_e24.minimumWidth = check_e24.getSize ().x;
		editor_e24.setEditor(check_e24, item6, 24);
		
		
		item7 = new TableItem(table, SWT.NONE);
		item7.setText(0, "星期六");
		TableEditor editor_f1 = new TableEditor(table);
		final Button check_f1 = new Button(table, SWT.CHECK);
		check_f1.setBackground(EccTreeControl.color);
		check_f1.pack();
		check_f1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_144="true";
			}
		});
		permission_144="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f1.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f1.minimumWidth = check_f1.getSize ().x;
		editor_f1.setEditor(check_f1, item7, 1);
		
		TableEditor editor_f2 = new TableEditor(table);
		final Button check_f2 = new Button(table, SWT.CHECK);
		check_f2.setBackground(EccTreeControl.color);
		check_f2.pack();
		check_f2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_145="true";
			}
		});
		permission_145="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f2.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f2.minimumWidth = check_f2.getSize ().x;
		editor_f2.setEditor(check_f2, item7, 2);
		
		TableEditor editor_f3 = new TableEditor(table);
		final Button check_f3 = new Button(table, SWT.CHECK);
		check_f3.setBackground(EccTreeControl.color);
		check_f3.pack();
		check_f3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_146="true";
			}
		});
		permission_146="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f3.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f3.minimumWidth = check_f3.getSize ().x;
		editor_f3.setEditor(check_f3, item7, 3);
		
		TableEditor editor_f4 = new TableEditor(table);
		final Button check_f4 = new Button(table, SWT.CHECK);
		check_f4.setBackground(EccTreeControl.color);
		check_f4.pack();
		check_f4.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_147="true";
			}
		});
		permission_147="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f4.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f4.minimumWidth = check_f4.getSize ().x;
		editor_f4.setEditor(check_f4, item7, 4);
		
		TableEditor editor_f5 = new TableEditor(table);
		final Button check_f5 = new Button(table, SWT.CHECK);
		check_f5.setBackground(EccTreeControl.color);
		check_f5.pack();
		check_f5.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_148="true";
			}
		});
		permission_148="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f5.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f5.minimumWidth = check_f5.getSize ().x;
		editor_f5.setEditor(check_f5, item7, 5);
		
		TableEditor editor_f6 = new TableEditor(table);
		final Button check_f6 = new Button(table, SWT.CHECK);
		check_f6.setBackground(EccTreeControl.color);
		check_f6.pack();
		check_f6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_149="true";
			}
		});
		permission_149="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f6.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f6.minimumWidth = check_f6.getSize ().x;
		editor_f6.setEditor(check_f6, item7, 6);
		
		TableEditor editor_f7 = new TableEditor(table);
		final Button check_f7 = new Button(table, SWT.CHECK);
		check_f7.setBackground(EccTreeControl.color);
		check_f7.pack();
		check_f7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_150="true";
			}
		});
		permission_150="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f7.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f7.minimumWidth = check_f7.getSize ().x;
		editor_f7.setEditor(check_f7, item7, 7);
		
		TableEditor editor_f8 = new TableEditor(table);
		final Button check_f8 = new Button(table, SWT.CHECK);
		check_f8.setBackground(EccTreeControl.color);
		check_f8.pack();
		check_f8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_151="true";
			}
		});
		permission_151="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f8.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f8.minimumWidth = check_f8.getSize ().x;
		editor_f8.setEditor(check_f8, item7, 8);
		
		TableEditor editor_f9 = new TableEditor(table);
		final Button check_f9 = new Button(table, SWT.CHECK);
		check_f9.setBackground(EccTreeControl.color);
		check_f9.pack();
		check_f9.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_152="true";
			}
		});
		permission_152="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f9.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f9.minimumWidth = check_f9.getSize ().x;
		editor_f9.setEditor(check_f9, item7, 9);
		
		TableEditor editor_f10 = new TableEditor(table);
		final Button check_f10 = new Button(table, SWT.CHECK);
		check_f10.setBackground(EccTreeControl.color);
		check_f10.pack();
		check_f10.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_153="true";
			}
		});
		permission_153="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f10.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f10.minimumWidth = check_f10.getSize ().x;
		editor_f10.setEditor(check_f10, item7, 10);
		
		TableEditor editor_f11 = new TableEditor(table);
		final Button check_f11 = new Button(table, SWT.CHECK);
		check_f11.setBackground(EccTreeControl.color);
		check_f11.pack();
		check_f11.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_154="true";
			}
		});
		permission_154="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f11.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f11.minimumWidth = check_f11.getSize ().x;
		editor_f11.setEditor(check_f11, item7, 11);
		
		TableEditor editor_f12 = new TableEditor(table);
		final Button check_f12 = new Button(table, SWT.CHECK);
		check_f12.setBackground(EccTreeControl.color);
		check_f12.pack();
		check_f12.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_155="true";
			}
		});
		permission_155="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f12.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f12.minimumWidth = check_f12.getSize ().x;
		editor_f12.setEditor(check_f12, item7, 12);
		
		TableEditor editor_f13 = new TableEditor(table);
		final Button check_f13 = new Button(table, SWT.CHECK);
		check_f13.setBackground(EccTreeControl.color);
		check_f13.pack();
		check_f13.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_156="true";
			}
		});
		permission_156="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f13.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f13.minimumWidth = check_f13.getSize ().x;
		editor_f13.setEditor(check_f13, item7, 13);
		
		TableEditor editor_f14 = new TableEditor(table);
		final Button check_f14 = new Button(table, SWT.CHECK);
		check_f14.setBackground(EccTreeControl.color);
		check_f14.pack();
		check_f14.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_157="true";
			}
		});
		permission_157="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f14.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f14.minimumWidth = check_f14.getSize ().x;
		editor_f14.setEditor(check_f14, item7, 14);
		
		TableEditor editor_f15 = new TableEditor(table);
		final Button check_f15 = new Button(table, SWT.CHECK);
		check_f15.setBackground(EccTreeControl.color);
		check_f15.pack();
		check_f15.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_158="true";
			}
		});
		permission_158="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f15.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f15.minimumWidth = check_f15.getSize ().x;
		editor_f15.setEditor(check_f15, item7, 15);
		
		TableEditor editor_f16 = new TableEditor(table);
		final Button check_f16 = new Button(table, SWT.CHECK);
		check_f16.setBackground(EccTreeControl.color);
		check_f16.pack();
		check_f16.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_159="true";
			}
		});
		permission_159="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f16.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f16.minimumWidth = check_f16.getSize ().x;
		editor_f16.setEditor(check_f16, item7, 16);
		
		TableEditor editor_f17 = new TableEditor(table);
		final Button check_f17 = new Button(table, SWT.CHECK);
		check_f17.setBackground(EccTreeControl.color);
		check_f17.pack();
		check_f17.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_160="true";
			}
		});
		permission_160="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f17.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f17.minimumWidth = check_f17.getSize ().x;
		editor_f17.setEditor(check_f17, item7, 17);
		
		TableEditor editor_f18 = new TableEditor(table);
		final Button check_f18 = new Button(table, SWT.CHECK);
		check_f18.setBackground(EccTreeControl.color);
		check_f18.pack();
		check_f18.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_161="true";
			}
		});
		permission_161="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f18.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f18.minimumWidth = check_f18.getSize ().x;
		editor_f18.setEditor(check_f18, item7, 18);
		
		TableEditor editor_f19 = new TableEditor(table);
		final Button check_f19 = new Button(table, SWT.CHECK);
		check_f19.setBackground(EccTreeControl.color);
		check_f19.pack();
		check_f19.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_162="true";
			}
		});
		permission_162="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f19.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f19.minimumWidth = check_f19.getSize ().x;
		editor_f19.setEditor(check_f19, item7, 19);
		
		TableEditor editor_f20 = new TableEditor(table);
		final Button check_f20 = new Button(table, SWT.CHECK);
		check_f20.setBackground(EccTreeControl.color);
		check_f20.pack();
		check_f20.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_163="true";
			}
		});
		permission_163="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f20.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f20.minimumWidth = check_f20.getSize ().x;
		editor_f20.setEditor(check_f20, item7, 20);
		
		TableEditor editor_f21 = new TableEditor(table);
		final Button check_f21 = new Button(table, SWT.CHECK);
		check_f21.setBackground(EccTreeControl.color);
		check_f21.pack();
		check_f21.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_164="true";
			}
		});
		permission_164="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f21.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f21.minimumWidth = check_f21.getSize ().x;
		editor_f21.setEditor(check_f21, item7, 21);
		
		TableEditor editor_f22 = new TableEditor(table);
		final Button check_f22 = new Button(table, SWT.CHECK);
		check_f22.setBackground(EccTreeControl.color);
		check_f22.pack();
		check_f22.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_165="true";
			}
		});
		permission_165="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f22.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f22.minimumWidth = check_f22.getSize ().x;
		editor_f22.setEditor(check_f22, item7, 22);
		
		TableEditor editor_f23 = new TableEditor(table);
		final Button check_f23 = new Button(table, SWT.CHECK);
		check_f23.setBackground(EccTreeControl.color);
		check_f23.pack();
		check_f23.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_166="true";
			}
		});
		permission_166="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f23.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f23.minimumWidth = check_f23.getSize ().x;
		editor_f23.setEditor(check_f23, item7, 23);
		
		TableEditor editor_f24 = new TableEditor(table);
		final Button check_f24 = new Button(table, SWT.CHECK);
		check_f24.setBackground(EccTreeControl.color);
		check_f24.pack();
		check_f24.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				permission_167="true";
			}
		});
		permission_167="false";
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem=(TableItem)e.item;
				if(tableItem.getChecked()){
					if(tableItem.getText(0).equals("星期六")){
						check_f24.setSelection(true);
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		editor_f24.minimumWidth = check_f24.getSize ().x;
		editor_f24.setEditor(check_f24, item7, 24);
		
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(EccTreeControl.color);
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBackground(EccTreeControl.color);
		lblNewLabel.setBounds(10, 10, 40, 12);
		lblNewLabel.setText("\u5907\u6CE8\uFF1A");
		
		text = new Text(composite_2, SWT.WRAP | SWT.BORDER);//备注
		text.setBounds(55, 10, 350, 100);
		sashForm.setWeights(new int[] {1, 10, 5});
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Button button = createButton(parent, IDialogConstants.OK_ID, "确定",true);
		Button button1 = createButton(parent, IDialogConstants.CANCEL_ID, "取消",true);
	}
	protected void buttonPressed(int buttonId) {
	L1:	if(buttonId==IDialogConstants.OK_ID){
			ICollection ico=FileTools.getBussCollection("EccTaskPlan");
			IEnumerator ienum=ico.GetEnumerator();
			while(ienum.MoveNext()){
				BusinessObject businessObject=(BusinessObject)ienum.get_Current();
				if(businessObject!=null){
					String taskName=businessObject.GetField("TaskName").get_NativeValue().toString();
					String model=businessObject.GetField("Model").get_NativeValue().toString();
					if(text_1.getText().equals(taskName)&&"相对时间计划".equals(model)){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("任务计划名称已存在!", "提示", SWT.OK);
						break L1;
					}
					if(text_1.getText().equals(taskName)){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("该任务计划名称已在别的任务计划中存在!", "提示", SWT.OK);
						break L1;
					}
					if(text_1.getText().isEmpty()){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("任务计划名称不能为空!", "提示", SWT.OK);
						break L1;
					} 
				}
			}
			
			bo = ConnectionBroker.get_SiteviewApi()//得到数据库表
					.get_BusObService().Create("EccTaskPlan");
			bo.GetField("TaskName").SetValue(
					new SiteviewValue(text_1.getText()));
			bo.GetField("Instruction").SetValue(
					new SiteviewValue(text.getText()));
			bo.GetField("Model").SetValue(
					new SiteviewValue("相对时间计划"));//类型
			bo.GetField("Status").SetValue(//得到是否允许的数据
					new SiteviewValue(item1.getText(0)+":"+permission+","+permission_1+","+permission_2+","+permission_3+","+permission_4+","+permission_5+","+permission_6+","
							+permission_7+","+permission_8+","+permission_9+","+permission_10+","+permission_11+","+permission_12+","+permission_13+","+permission_14+","+permission_15+","
							+permission_16+","+permission_17+","+permission_18+","+permission_19+","+permission_20+","+permission_21+","+permission_22+","+permission_23+";"
							+item2.getText(0)+":"+permission_24+","+permission_25+","+permission_26+","+permission_27+","+permission_28+","+permission_29+","+permission_30+","
							+permission_31+","+permission_32+","+permission_33+","+permission_34+","+permission_35+","+permission_36+","+permission_37+","+permission_38+","+permission_39+","
							+permission_40+","+permission_41+","+permission_42+","+permission_43+","+permission_44+","+permission_45+","+permission_46+","+permission_47+";"
							+item3.getText(0)+":"+permission_48+","+permission_49+","+permission_50+","+permission_51+","+permission_52+","+permission_53+","+permission_54+","
							+permission_55+","+permission_56+","+permission_57+","+permission_58+","+permission_59+","+permission_60+","+permission_61+","+permission_62+","+permission_63+","
							+permission_64+","+permission_65+","+permission_66+","+permission_67+","+permission_68+","+permission_69+","+permission_70+","+permission_71+";"
							+item4.getText(0)+":"+permission_72+","+permission_73+","+permission_74+","+permission_75+","+permission_76+","+permission_77+","+permission_78+","
							+permission_79+","+permission_80+","+permission_81+","+permission_82+","+permission_83+","+permission_84+","+permission_85+","+permission_86+","+permission_87+","
							+permission_88+","+permission_89+","+permission_90+","+permission_91+","+permission_92+","+permission_93+","+permission_94+","+permission_95+";"
							+item5.getText(0)+":"+permission_96+","+permission_97+","+permission_98+","+permission_99+","+permission_100+","+permission_101+","+permission_102+","
							+permission_103+","+permission_104+","+permission_105+","+permission_106+","+permission_107+","+permission_108+","+permission_109+","+permission_110+","+permission_111+","
							+permission_112+","+permission_113+","+permission_114+","+permission_115+","+permission_116+","+permission_117+","+permission_118+","+permission_119+";"
							+item6.getText(0)+":"+permission_120+","+permission_121+","+permission_122+","+permission_123+","+permission_124+","+permission_125+","+permission_126+","
							+permission_127+","+permission_128+","+permission_129+","+permission_130+","+permission_131+","+permission_132+","+permission_133+","+permission_134+","+permission_135+","
							+permission_136+","+permission_137+","+permission_138+","+permission_139+","+permission_140+","+permission_141+","+permission_142+","+permission_143+";"
							+item7.getText(0)+":"+permission_144+","+permission_145+","+permission_146+","+permission_147+","+permission_148+","+permission_149+","+permission_150+","
							+permission_151+","+permission_152+","+permission_153+","+permission_154+","+permission_155+","+permission_156+","+permission_157+","+permission_158+","+permission_159+","
							+permission_160+","+permission_161+","+permission_162+","+permission_163+","+permission_164+","+permission_165+","+permission_166+","+permission_167));
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//将数据存储到数据
			
			AbsoluteTime.addRelativeData();//刷新表单
			
		}
	   this.close();
		
	}
}
