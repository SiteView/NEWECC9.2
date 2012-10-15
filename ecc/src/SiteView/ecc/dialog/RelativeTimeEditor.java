package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;

public class RelativeTimeEditor extends Dialog{
	private String title="编辑相对任务计划";
	private Table table;
	private Text text;
	private Text text_1;
	private String name;
	private BusinessObject bo;
	public TableItem item1;
    public TableItem item2;
    public TableItem item3;
    public TableItem item4;
    public TableItem item5;
    public TableItem item6;
    public TableItem item7;
    public String TaskName;
    public String Instruction;
    public String Status;
    public String date_0;
    public String []c1;
	public RelativeTimeEditor(Shell parentShell,String name) {
		super(parentShell);
		this.name=name;
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(810, 480);
		newShell.setLocation(200, 175);
		newShell.setText(title);
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		ICollection ico=FileTools.getBussCollection("TaskName",name, "EccTaskPlan");
		IEnumerator ien=ico.GetEnumerator();
		while(ien.MoveNext()){
			bo=(BusinessObject)ien.get_Current();
			if(bo!=null&&bo.GetField("Model").get_NativeValue().toString().equals("相对时间计划")){
				TaskName=bo.GetField("TaskName").get_NativeValue()
						.toString();
				Instruction=bo.GetField("Instruction").get_NativeValue()
						.toString();
				Status=bo.GetField("Status").get_NativeValue()
						.toString();
				String []a1=Status.split(";");
				
				String []b1=a1[0].split(":");
				date_0=b1[0];//星期日
				System.out.println(b1[1]);
				c1=b1[1].split(",");
				
			}
		}
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(10, 5, 100, 18);
		lblNewLabel_1.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*\uFF1A");
		
		text_1 = new Text(composite_1, SWT.BORDER);//任务计划名称
		text_1.setBounds(115, 5, 150, 18);
		text_1.setText(TaskName);
		text_1.setEnabled(false);
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
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
		Button check_0 = new Button(table, SWT.CHECK);
		check_0.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_0.pack();
        if(item1.getText(0).equals(date_0)&&c1[0].equals("true")){
        	check_0.setSelection(true);
		}else if(item1.getText(0).equals(date_0)&&c1[0].equals("false")){
			check_0.setSelection(false);
		}
		editor_0.minimumWidth = check_0.getSize ().x;
		editor_0.setEditor(check_0, item1, 1);
		
		TableEditor editor_1 = new TableEditor(table);
		Button check_1 = new Button(table, SWT.CHECK);
		check_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_1.pack();
		editor_1.minimumWidth = check_1.getSize ().x;
		editor_1.setEditor(check_1, item1, 2);
		
		TableEditor editor_2 = new TableEditor(table);
		Button check_2 = new Button(table, SWT.CHECK);
		check_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_2.pack();
		editor_2.minimumWidth = check_2.getSize ().x;
		editor_2.setEditor(check_2, item1, 3);	
		
		TableEditor editor_3 = new TableEditor(table);
		Button check_3 = new Button(table, SWT.CHECK);
		check_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_3.pack();
		editor_3.minimumWidth = check_3.getSize ().x;
		editor_3.setEditor(check_3, item1, 4);	
		
		TableEditor editor_4 = new TableEditor(table);
		Button check_4 = new Button(table, SWT.CHECK);
		check_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_4.pack();
		editor_4.minimumWidth = check_4.getSize ().x;
		editor_4.setEditor(check_4, item1, 5);	
		
		TableEditor editor_5 = new TableEditor(table);
		Button check_5 = new Button(table, SWT.CHECK);
		check_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_5.pack();
		editor_5.minimumWidth = check_5.getSize ().x;
		editor_5.setEditor(check_5, item1, 6);	
		
		TableEditor editor_6 = new TableEditor(table);
		Button check_6 = new Button(table, SWT.CHECK);
		check_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_6.pack();
		editor_6.minimumWidth = check_6.getSize ().x;
		editor_6.setEditor(check_6, item1, 7);	
		
		TableEditor editor_7 = new TableEditor(table);
		Button check_7 = new Button(table, SWT.CHECK);
		check_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_7.pack();
		editor_7.minimumWidth = check_7.getSize ().x;
		editor_7.setEditor(check_7, item1, 8);	
		
		TableEditor editor_8 = new TableEditor(table);
		Button check_8 = new Button(table, SWT.CHECK);
		check_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_8.pack();
		editor_8.minimumWidth = check_8.getSize ().x;
		editor_8.setEditor(check_8, item1, 9);	
		
		TableEditor editor_9 = new TableEditor(table);
		Button check_9 = new Button(table, SWT.CHECK);
		check_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_9.pack();
		editor_9.minimumWidth = check_9.getSize ().x;
		editor_9.setEditor(check_9, item1, 10);	
		
		TableEditor editor_10 = new TableEditor(table);
		Button check_10 = new Button(table, SWT.CHECK);
		check_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_10.pack();
		editor_10.minimumWidth = check_10.getSize ().x;
		editor_10.setEditor(check_10, item1, 11);	
		
		TableEditor editor_11 = new TableEditor(table);
		Button check_11 = new Button(table, SWT.CHECK);
		check_11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_11.pack();
		editor_11.minimumWidth = check_11.getSize ().x;
		editor_11.setEditor(check_11, item1, 12);	
		
		TableEditor editor_12 = new TableEditor(table);
		Button check_12 = new Button(table, SWT.CHECK);
		check_12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_12.pack();
		editor_12.minimumWidth = check_12.getSize ().x;
		editor_12.setEditor(check_12, item1, 13);	
		
		TableEditor editor_13 = new TableEditor(table);
		Button check_13 = new Button(table, SWT.CHECK);
		check_13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_13.pack();
		editor_13.minimumWidth = check_13.getSize ().x;
		editor_13.setEditor(check_13, item1, 14);	
		
		TableEditor editor_14 = new TableEditor(table);
		Button check_14 = new Button(table, SWT.CHECK);
		check_14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_14.pack();
		editor_14.minimumWidth = check_14.getSize ().x;
		editor_14.setEditor(check_14, item1, 15);	
		
		TableEditor editor_15 = new TableEditor(table);
		Button check_15 = new Button(table, SWT.CHECK);
		check_15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_15.pack();
		editor_15.minimumWidth = check_15.getSize ().x;
		editor_15.setEditor(check_15, item1, 16);	
		
		TableEditor editor_16 = new TableEditor(table);
		Button check_16 = new Button(table, SWT.CHECK);
		check_16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_16.pack();
		editor_16.minimumWidth = check_16.getSize ().x;
		editor_16.setEditor(check_16, item1, 17);	
		
		TableEditor editor_17 = new TableEditor(table);
		Button check_17 = new Button(table, SWT.CHECK);
		check_17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_17.pack();
		editor_17.minimumWidth = check_17.getSize ().x;
		editor_17.setEditor(check_17, item1, 18);	
		
		TableEditor editor_18 = new TableEditor(table);
		Button check_18 = new Button(table, SWT.CHECK);
		check_18.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_18.pack();
		editor_18.minimumWidth = check_18.getSize ().x;
		editor_18.setEditor(check_18, item1, 19);
		
		TableEditor editor_19 = new TableEditor(table);
		Button check_19 = new Button(table, SWT.CHECK);
		check_19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_19.pack();
		editor_19.minimumWidth = check_19.getSize ().x;
		editor_19.setEditor(check_19, item1, 20);	
		
		TableEditor editor_20 = new TableEditor(table);
		Button check_20 = new Button(table, SWT.CHECK);
		check_20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_20.pack();
		editor_20.minimumWidth = check_20.getSize ().x;
		editor_20.setEditor(check_20, item1, 21);	
		
		TableEditor editor_21 = new TableEditor(table);
		Button check_21 = new Button(table, SWT.CHECK);
		check_21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_21.pack();
		editor_21.minimumWidth = check_21.getSize ().x;
		editor_21.setEditor(check_21, item1, 22);	
		
		TableEditor editor_22 = new TableEditor(table);
		Button check_22 = new Button(table, SWT.CHECK);
		check_22.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_22.pack();
		editor_22.minimumWidth = check_22.getSize ().x;
		editor_22.setEditor(check_22, item1, 23);	
		
		TableEditor editor_23 = new TableEditor(table);
		Button check_23 = new Button(table, SWT.CHECK);
		check_23.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_23.pack();
		editor_23.minimumWidth = check_23.getSize ().x;
		editor_23.setEditor(check_23, item1, 24);	
		
		
		item2 = new TableItem(table, SWT.NONE);
		item2.setText(0, "星期一");
		TableEditor editor_a1 = new TableEditor(table);
		Button check_a1 = new Button(table, SWT.CHECK);
		check_a1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a1.pack();
		editor_a1.minimumWidth = check_a1.getSize ().x;
		editor_a1.setEditor(check_a1, item2, 1);
		
		TableEditor editor_a2 = new TableEditor(table);
		Button check_a2 = new Button(table, SWT.CHECK);
		
		check_a2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a2.pack();
		editor_a2.minimumWidth = check_a2.getSize ().x;
		editor_a2.setEditor(check_a2, item2, 2);
		
		TableEditor editor_a3 = new TableEditor(table);
		Button check_a3 = new Button(table, SWT.CHECK);
		check_a3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a3.pack();
		editor_a3.minimumWidth = check_a3.getSize ().x;
		editor_a3.setEditor(check_a3, item2, 3);
		
		TableEditor editor_a4 = new TableEditor(table);
		Button check_a4 = new Button(table, SWT.CHECK);
		check_a4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a4.pack();
		editor_a4.minimumWidth = check_a4.getSize ().x;
		editor_a4.setEditor(check_a4, item2, 4);
		
		TableEditor editor_a5 = new TableEditor(table);
		Button check_a5 = new Button(table, SWT.CHECK);
		check_a5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a5.pack();
		editor_a5.minimumWidth = check_a5.getSize ().x;
		editor_a5.setEditor(check_a5, item2, 5);
		
		TableEditor editor_a6 = new TableEditor(table);
		Button check_a6 = new Button(table, SWT.CHECK);
		check_a6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a6.pack();
		editor_a6.minimumWidth = check_a6.getSize ().x;
		editor_a6.setEditor(check_a6, item2, 6);
		
		TableEditor editor_a7 = new TableEditor(table);
		Button check_a7 = new Button(table, SWT.CHECK);
		check_a7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a7.pack();
		editor_a7.minimumWidth = check_a7.getSize ().x;
		editor_a7.setEditor(check_a7, item2, 7);
		
		TableEditor editor_a8 = new TableEditor(table);
		Button check_a8 = new Button(table, SWT.CHECK);
		check_a8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a8.pack();
		editor_a8.minimumWidth = check_a8.getSize ().x;
		editor_a8.setEditor(check_a8, item2, 8);
		
		TableEditor editor_a9 = new TableEditor(table);
		Button check_a9 = new Button(table, SWT.CHECK);
		check_a9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a9.pack();
		editor_a9.minimumWidth = check_a9.getSize ().x;
		editor_a9.setEditor(check_a9, item2, 9);
		
		TableEditor editor_a10 = new TableEditor(table);
		Button check_a10 = new Button(table, SWT.CHECK);
		check_a10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a10.pack();
		editor_a10.minimumWidth = check_a10.getSize ().x;
		editor_a10.setEditor(check_a10, item2, 10);
		
		TableEditor editor_a11 = new TableEditor(table);
		Button check_a11 = new Button(table, SWT.CHECK);
		check_a11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a11.pack();
		editor_a11.minimumWidth = check_a11.getSize ().x;
		editor_a11.setEditor(check_a11, item2, 11);
		
		TableEditor editor_a12 = new TableEditor(table);
		Button check_a12 = new Button(table, SWT.CHECK);
		check_a12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a12.pack();
		editor_a12.minimumWidth = check_a12.getSize ().x;
		editor_a12.setEditor(check_a12, item2, 12);
		
		TableEditor editor_a13 = new TableEditor(table);
		Button check_a13 = new Button(table, SWT.CHECK);
		check_a13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a13.pack();
		editor_a13.minimumWidth = check_a13.getSize ().x;
		editor_a13.setEditor(check_a13, item2, 13);
		
		TableEditor editor_a14 = new TableEditor(table);
		Button check_a14 = new Button(table, SWT.CHECK);
		check_a14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a14.pack();
		editor_a14.minimumWidth = check_a14.getSize ().x;
		editor_a14.setEditor(check_a14, item2, 14);
		
		TableEditor editor_a15 = new TableEditor(table);
		Button check_a15 = new Button(table, SWT.CHECK);
		check_a15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a15.pack();
		editor_a15.minimumWidth = check_a15.getSize ().x;
		editor_a15.setEditor(check_a15, item2, 15);
		
		TableEditor editor_a16 = new TableEditor(table);
		Button check_a16 = new Button(table, SWT.CHECK);
		check_a16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a16.pack();
		editor_a16.minimumWidth = check_a16.getSize ().x;
		editor_a16.setEditor(check_a16, item2, 16);
		
		TableEditor editor_a17 = new TableEditor(table);
		Button check_a17 = new Button(table, SWT.CHECK);
		check_a17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a17.pack();
		editor_a17.minimumWidth = check_a17.getSize ().x;
		editor_a17.setEditor(check_a17, item2, 17);
		
		TableEditor editor_a18 = new TableEditor(table);
		Button check_a18 = new Button(table, SWT.CHECK);
		check_a18.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a18.pack();
		editor_a18.minimumWidth = check_a18.getSize ().x;
		editor_a18.setEditor(check_a18, item2, 18);
		
		TableEditor editor_a19 = new TableEditor(table);
		Button check_a19 = new Button(table, SWT.CHECK);
		check_a19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a19.pack();
		editor_a19.minimumWidth = check_a19.getSize ().x;
		editor_a19.setEditor(check_a19, item2, 19);
		
		TableEditor editor_a20 = new TableEditor(table);
		Button check_a20 = new Button(table, SWT.CHECK);
		check_a20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a20.pack();
		editor_a20.minimumWidth = check_a20.getSize ().x;
		editor_a20.setEditor(check_a20, item2, 20);
		
		TableEditor editor_a21 = new TableEditor(table);
		Button check_a21 = new Button(table, SWT.CHECK);
		check_a21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a21.pack();
		editor_a21.minimumWidth = check_a21.getSize ().x;
		editor_a21.setEditor(check_a21, item2, 21);
		
		TableEditor editor_a22 = new TableEditor(table);
		Button check_a22 = new Button(table, SWT.CHECK);
		check_a22.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a22.pack();
		editor_a22.minimumWidth = check_a22.getSize ().x;
		editor_a22.setEditor(check_a22, item2, 22);
		
		TableEditor editor_a23 = new TableEditor(table);
		Button check_a23 = new Button(table, SWT.CHECK);
		check_a23.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a23.pack();
		editor_a23.minimumWidth = check_a23.getSize ().x;
		editor_a23.setEditor(check_a23, item2, 23);
		
		TableEditor editor_a24 = new TableEditor(table);
		Button check_a24 = new Button(table, SWT.CHECK);
		check_a24.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_a24.pack();
		editor_a24.minimumWidth = check_a24.getSize ().x;
		editor_a24.setEditor(check_a24, item2, 24);
		
		
		item3 = new TableItem(table, SWT.NONE);
		item3.setText(0, "星期二");
		TableEditor editor_b1 = new TableEditor(table);
		Button check_b1 = new Button(table, SWT.CHECK);
		check_b1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b1.pack();
		editor_b1.minimumWidth = check_b1.getSize ().x;
		editor_b1.setEditor(check_b1, item3, 1);
		
		TableEditor editor_b2 = new TableEditor(table);
		Button check_b2 = new Button(table, SWT.CHECK);
		check_b2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b2.pack();
		editor_b2.minimumWidth = check_b2.getSize ().x;
		editor_b2.setEditor(check_b2, item3, 2);
		
		TableEditor editor_b3 = new TableEditor(table);
		Button check_b3 = new Button(table, SWT.CHECK);
		check_b3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b3.pack();
		editor_b3.minimumWidth = check_b3.getSize ().x;
		editor_b3.setEditor(check_b3, item3, 3);
		
		TableEditor editor_b4 = new TableEditor(table);
		Button check_b4 = new Button(table, SWT.CHECK);
		check_b4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b4.pack();
		editor_b4.minimumWidth = check_b4.getSize ().x;
		editor_b4.setEditor(check_b4, item3, 4);
		
		TableEditor editor_b5 = new TableEditor(table);
		Button check_b5 = new Button(table, SWT.CHECK);
		check_b5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b5.pack();
		editor_b5.minimumWidth = check_b5.getSize ().x;
		editor_b5.setEditor(check_b5, item3, 5);
		
		TableEditor editor_b6 = new TableEditor(table);
		Button check_b6 = new Button(table, SWT.CHECK);
		check_b6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b6.pack();
		editor_b6.minimumWidth = check_b6.getSize ().x;
		editor_b6.setEditor(check_b6, item3, 6);
		
		TableEditor editor_b7 = new TableEditor(table);
		Button check_b7 = new Button(table, SWT.CHECK);
		check_b7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b7.pack();
		editor_b7.minimumWidth = check_b7.getSize ().x;
		editor_b7.setEditor(check_b7, item3, 7);
		
		TableEditor editor_b8 = new TableEditor(table);
		Button check_b8 = new Button(table, SWT.CHECK);
		check_b8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b8.pack();
		editor_b8.minimumWidth = check_b8.getSize ().x;
		editor_b8.setEditor(check_b8, item3, 8);
		
		TableEditor editor_b9 = new TableEditor(table);
		Button check_b9 = new Button(table, SWT.CHECK);
		check_b9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b9.pack();
		editor_b9.minimumWidth = check_b9.getSize ().x;
		editor_b9.setEditor(check_b9, item3, 9);
		
		TableEditor editor_b10 = new TableEditor(table);
		Button check_b10 = new Button(table, SWT.CHECK);
		check_b10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b10.pack();
		editor_b10.minimumWidth = check_b10.getSize ().x;
		editor_b10.setEditor(check_b10, item3, 10);
		
		TableEditor editor_b11 = new TableEditor(table);
		Button check_b11 = new Button(table, SWT.CHECK);
		check_b11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b11.pack();
		editor_b11.minimumWidth = check_b11.getSize ().x;
		editor_b11.setEditor(check_b11, item3, 11);
		
		TableEditor editor_b12 = new TableEditor(table);
		Button check_b12 = new Button(table, SWT.CHECK);
		check_b12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b12.pack();
		editor_b12.minimumWidth = check_b12.getSize ().x;
		editor_b12.setEditor(check_b12, item3, 12);
		
		TableEditor editor_b13 = new TableEditor(table);
		Button check_b13 = new Button(table, SWT.CHECK);
		check_b13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b13.pack();
		editor_b13.minimumWidth = check_b13.getSize ().x;
		editor_b13.setEditor(check_b13, item3, 13);
		
		TableEditor editor_b14 = new TableEditor(table);
		Button check_b14 = new Button(table, SWT.CHECK);
		check_b14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b14.pack();
		editor_b14.minimumWidth = check_b14.getSize ().x;
		editor_b14.setEditor(check_b14, item3, 14);
		
		TableEditor editor_b15 = new TableEditor(table);
		Button check_b15 = new Button(table, SWT.CHECK);
		check_b15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b15.pack();
		editor_b15.minimumWidth = check_b15.getSize ().x;
		editor_b15.setEditor(check_b15, item3, 15);
		
		TableEditor editor_b16 = new TableEditor(table);
		Button check_b16 = new Button(table, SWT.CHECK);
		check_b16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b16.pack();
		editor_b16.minimumWidth = check_b16.getSize ().x;
		editor_b16.setEditor(check_b16, item3, 16);
		
		TableEditor editor_b17 = new TableEditor(table);
		Button check_b17 = new Button(table, SWT.CHECK);
		check_b17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b17.pack();
		editor_b17.minimumWidth = check_b17.getSize ().x;
		editor_b17.setEditor(check_b17, item3, 17);
		
		TableEditor editor_b18 = new TableEditor(table);
		Button check_b18 = new Button(table, SWT.CHECK);
		check_b18.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b18.pack();
		editor_b18.minimumWidth = check_b18.getSize ().x;
		editor_b18.setEditor(check_b18, item3, 18);
		
		TableEditor editor_b19 = new TableEditor(table);
		Button check_b19 = new Button(table, SWT.CHECK);
		check_b19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b19.pack();
		editor_b19.minimumWidth = check_b19.getSize ().x;
		editor_b19.setEditor(check_b19, item3, 19);
		
		TableEditor editor_b20 = new TableEditor(table);
		Button check_b20 = new Button(table, SWT.CHECK);
		check_b20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b20.pack();
		editor_b20.minimumWidth = check_b20.getSize ().x;
		editor_b20.setEditor(check_b20, item3, 20);
		
		TableEditor editor_b21 = new TableEditor(table);
		Button check_b21 = new Button(table, SWT.CHECK);
		check_b21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b21.pack();
		editor_b21.minimumWidth = check_b21.getSize ().x;
		editor_b21.setEditor(check_b21, item3, 21);
		
		TableEditor editor_b22 = new TableEditor(table);
		Button check_b22 = new Button(table, SWT.CHECK);
		check_b22.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b22.pack();
		editor_b22.minimumWidth = check_b22.getSize ().x;
		editor_b22.setEditor(check_b22, item3, 22);
		
		TableEditor editor_b23 = new TableEditor(table);
		Button check_b23 = new Button(table, SWT.CHECK);
		check_b23.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b23.pack();
		editor_b23.minimumWidth = check_b23.getSize ().x;
		editor_b23.setEditor(check_b23, item3, 23);
		
		TableEditor editor_b24 = new TableEditor(table);
		Button check_b24 = new Button(table, SWT.CHECK);
		check_b24.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_b24.pack();
		editor_b24.minimumWidth = check_b24.getSize ().x;
		editor_b24.setEditor(check_b24, item3, 24);
		
		
		item4 = new TableItem(table, SWT.NONE);
		item4.setText(0, "星期三");
		TableEditor editor_c1 = new TableEditor(table);
		final Button check_c1 = new Button(table, SWT.CHECK);
		check_c1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c1.pack();
		editor_c1.minimumWidth = check_c1.getSize ().x;
		editor_c1.setEditor(check_c1, item4, 1);
		
		TableEditor editor_c2 = new TableEditor(table);
		Button check_c2 = new Button(table, SWT.CHECK);
		check_c2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c2.pack();
		editor_c2.minimumWidth = check_c2.getSize ().x;
		editor_c2.setEditor(check_c2, item4, 2);
		
		TableEditor editor_c3 = new TableEditor(table);
		Button check_c3 = new Button(table, SWT.CHECK);
		check_c3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c3.pack();
		editor_c3.minimumWidth = check_c3.getSize ().x;
		editor_c3.setEditor(check_c3, item4, 3);
		
		TableEditor editor_c4 = new TableEditor(table);
		Button check_c4 = new Button(table, SWT.CHECK);
		check_c4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c4.pack();
		editor_c4.minimumWidth = check_c4.getSize ().x;
		editor_c4.setEditor(check_c4, item4, 4);
		
		TableEditor editor_c5 = new TableEditor(table);
		Button check_c5 = new Button(table, SWT.CHECK);
		check_c5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c5.pack();
		editor_c5.minimumWidth = check_c5.getSize ().x;
		editor_c5.setEditor(check_c5, item4, 5);
		
		TableEditor editor_c6 = new TableEditor(table);
		Button check_c6 = new Button(table, SWT.CHECK);
		check_c6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c6.pack();
		editor_c6.minimumWidth = check_c6.getSize ().x;
		editor_c6.setEditor(check_c6, item4, 6);
		
		TableEditor editor_c7 = new TableEditor(table);
		Button check_c7 = new Button(table, SWT.CHECK);
		check_c7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c7.pack();
		editor_c7.minimumWidth = check_c7.getSize ().x;
		editor_c7.setEditor(check_c7, item4, 7);
		
		TableEditor editor_c8 = new TableEditor(table);
		Button check_c8 = new Button(table, SWT.CHECK);
		check_c8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c8.pack();
		editor_c8.minimumWidth = check_c8.getSize ().x;
		editor_c8.setEditor(check_c8, item4, 8);
		
		TableEditor editor_c9 = new TableEditor(table);
		Button check_c9 = new Button(table, SWT.CHECK);
		check_c9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c9.pack();
		editor_c9.minimumWidth = check_c9.getSize ().x;
		editor_c9.setEditor(check_c9, item4, 9);
		
		TableEditor editor_c10 = new TableEditor(table);
		Button check_c10 = new Button(table, SWT.CHECK);
		check_c10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c10.pack();
		editor_c10.minimumWidth = check_c10.getSize ().x;
		editor_c10.setEditor(check_c10, item4, 10);
		
		TableEditor editor_c11 = new TableEditor(table);
		Button check_c11 = new Button(table, SWT.CHECK);
		check_c11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c11.pack();
		editor_c11.minimumWidth = check_c11.getSize ().x;
		editor_c11.setEditor(check_c11, item4, 11);
		
		TableEditor editor_c12 = new TableEditor(table);
		Button check_c12 = new Button(table, SWT.CHECK);
		check_c12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c12.pack();
		editor_c12.minimumWidth = check_c12.getSize ().x;
		editor_c12.setEditor(check_c12, item4, 12);
		
		TableEditor editor_c13 = new TableEditor(table);
		Button check_c13 = new Button(table, SWT.CHECK);
		check_c13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c13.pack();
		editor_c13.minimumWidth = check_c13.getSize ().x;
		editor_c13.setEditor(check_c13, item4, 13);
		
		TableEditor editor_c14 = new TableEditor(table);
		Button check_c14 = new Button(table, SWT.CHECK);
		check_c14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c14.pack();
		editor_c14.minimumWidth = check_c14.getSize ().x;
		editor_c14.setEditor(check_c14, item4, 14);
		
		TableEditor editor_c15 = new TableEditor(table);
		Button check_c15 = new Button(table, SWT.CHECK);
		check_c15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c15.pack();
		editor_c15.minimumWidth = check_c15.getSize ().x;
		editor_c15.setEditor(check_c15, item4, 15);
		
		TableEditor editor_c16 = new TableEditor(table);
		Button check_c16 = new Button(table, SWT.CHECK);
		check_c16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c16.pack();
		editor_c16.minimumWidth = check_c16.getSize ().x;
		editor_c16.setEditor(check_c16, item4, 16);
		
		TableEditor editor_c17 = new TableEditor(table);
		Button check_c17 = new Button(table, SWT.CHECK);
		check_c17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c17.pack();
		editor_c17.minimumWidth = check_c17.getSize ().x;
		editor_c17.setEditor(check_c17, item4, 17);
		
		TableEditor editor_c18 = new TableEditor(table);
		Button check_c18 = new Button(table, SWT.CHECK);
		check_c18.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c18.pack();
		editor_c18.minimumWidth = check_c18.getSize ().x;
		editor_c18.setEditor(check_c18, item4, 18);
		
		TableEditor editor_c19 = new TableEditor(table);
		Button check_c19 = new Button(table, SWT.CHECK);
		check_c19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c19.pack();
		editor_c19.minimumWidth = check_c19.getSize ().x;
		editor_c19.setEditor(check_c19, item4, 19);
		
		TableEditor editor_c20 = new TableEditor(table);
		Button check_c20 = new Button(table, SWT.CHECK);
		check_c20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c20.pack();
		editor_c20.minimumWidth = check_c20.getSize ().x;
		editor_c20.setEditor(check_c20, item4, 20);
		
		TableEditor editor_c21 = new TableEditor(table);
		Button check_c21 = new Button(table, SWT.CHECK);
		check_c21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c21.pack();
		editor_c21.minimumWidth = check_c21.getSize ().x;
		editor_c21.setEditor(check_c21, item4, 21);
		
		TableEditor editor_c22 = new TableEditor(table);
		Button check_c22 = new Button(table, SWT.CHECK);
		check_c22.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c22.pack();
		editor_c22.minimumWidth = check_c22.getSize ().x;
		editor_c22.setEditor(check_c22, item4, 22);
		
		TableEditor editor_c23 = new TableEditor(table);
		Button check_c23 = new Button(table, SWT.CHECK);
		check_c23.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c23.pack();
		editor_c23.minimumWidth = check_c23.getSize ().x;
		editor_c23.setEditor(check_c23, item4, 23);
		
		TableEditor editor_c24 = new TableEditor(table);
		Button check_c24 = new Button(table, SWT.CHECK);
		check_c24.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_c24.pack();
		editor_c24.minimumWidth = check_c24.getSize ().x;
		editor_c24.setEditor(check_c24, item4, 24);
		
		
		item5 = new TableItem(table, SWT.NONE);
		item5.setText(0, "星期四");
		TableEditor editor_d1 = new TableEditor(table);
		final Button check_d1 = new Button(table, SWT.CHECK);
		check_d1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d1.pack();
		editor_d1.minimumWidth = check_d1.getSize ().x;
		editor_d1.setEditor(check_d1, item5, 1);
		
		TableEditor editor_d2 = new TableEditor(table);
		Button check_d2 = new Button(table, SWT.CHECK);
		check_d2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d2.pack();
		editor_d2.minimumWidth = check_d2.getSize ().x;
		editor_d2.setEditor(check_d2, item5, 2);
		
		TableEditor editor_d3 = new TableEditor(table);
		Button check_d3 = new Button(table, SWT.CHECK);
		check_d3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d3.pack();
		editor_d3.minimumWidth = check_d3.getSize ().x;
		editor_d3.setEditor(check_d3, item5, 3);
		
		TableEditor editor_d4 = new TableEditor(table);
		Button check_d4 = new Button(table, SWT.CHECK);
		check_d4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d4.pack();
		editor_d4.minimumWidth = check_d4.getSize ().x;
		editor_d4.setEditor(check_d4, item5, 4);
		
		TableEditor editor_d5 = new TableEditor(table);
		Button check_d5 = new Button(table, SWT.CHECK);
		check_d5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d5.pack();
		editor_d5.minimumWidth = check_d5.getSize ().x;
		editor_d5.setEditor(check_d5, item5, 5);
		
		TableEditor editor_d6 = new TableEditor(table);
		Button check_d6 = new Button(table, SWT.CHECK);
		check_d6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d6.pack();
		editor_d6.minimumWidth = check_d6.getSize ().x;
		editor_d6.setEditor(check_d6, item5, 6);
		
		TableEditor editor_d7 = new TableEditor(table);
		Button check_d7 = new Button(table, SWT.CHECK);
		check_d7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d7.pack();
		editor_d7.minimumWidth = check_d7.getSize ().x;
		editor_d7.setEditor(check_d7, item5, 7);
		
		TableEditor editor_d8 = new TableEditor(table);
		Button check_d8 = new Button(table, SWT.CHECK);
		check_d8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d8.pack();
		editor_d8.minimumWidth = check_d8.getSize ().x;
		editor_d8.setEditor(check_d8, item5, 8);
		
		TableEditor editor_d9 = new TableEditor(table);
		Button check_d9 = new Button(table, SWT.CHECK);
		check_d9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d9.pack();
		editor_d9.minimumWidth = check_d9.getSize ().x;
		editor_d9.setEditor(check_d9, item5, 9);
		
		TableEditor editor_d10 = new TableEditor(table);
		Button check_d10 = new Button(table, SWT.CHECK);
		check_d10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d10.pack();
		editor_d10.minimumWidth = check_d10.getSize ().x;
		editor_d10.setEditor(check_d10, item5, 10);
		
		TableEditor editor_d11 = new TableEditor(table);
		Button check_d11 = new Button(table, SWT.CHECK);
		check_d11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d11.pack();
		editor_d11.minimumWidth = check_d11.getSize ().x;
		editor_d11.setEditor(check_d11, item5, 11);
		
		TableEditor editor_d12 = new TableEditor(table);
		Button check_d12 = new Button(table, SWT.CHECK);
		check_d12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d12.pack();
		editor_d12.minimumWidth = check_c12.getSize ().x;
		editor_d12.setEditor(check_d12, item5, 12);
		
		TableEditor editor_d13 = new TableEditor(table);
		Button check_d13 = new Button(table, SWT.CHECK);
		check_d13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d13.pack();
		editor_d13.minimumWidth = check_d13.getSize ().x;
		editor_d13.setEditor(check_d13, item5, 13);
		
		TableEditor editor_d14 = new TableEditor(table);
		Button check_d14 = new Button(table, SWT.CHECK);
		check_d14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d14.pack();
		editor_d14.minimumWidth = check_d14.getSize ().x;
		editor_d14.setEditor(check_d14, item5, 14);
		
		TableEditor editor_d15 = new TableEditor(table);
		Button check_d15 = new Button(table, SWT.CHECK);
		check_d15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d15.pack();
		editor_d15.minimumWidth = check_d15.getSize ().x;
		editor_d15.setEditor(check_d15, item5, 15);
		
		TableEditor editor_d16 = new TableEditor(table);
		Button check_d16 = new Button(table, SWT.CHECK);
		check_d16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d16.pack();
		editor_d16.minimumWidth = check_d16.getSize ().x;
		editor_d16.setEditor(check_d16, item5, 16);
		
		TableEditor editor_d17 = new TableEditor(table);
		Button check_d17 = new Button(table, SWT.CHECK);
		check_d17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d17.pack();
		editor_d17.minimumWidth = check_d17.getSize ().x;
		editor_d17.setEditor(check_d17, item5, 17);
		
		TableEditor editor_d18 = new TableEditor(table);
		Button check_d18 = new Button(table, SWT.CHECK);
		check_d18.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d18.pack();
		editor_d18.minimumWidth = check_d18.getSize ().x;
		editor_d18.setEditor(check_d18, item5, 18);
		
		TableEditor editor_d19 = new TableEditor(table);
		Button check_d19 = new Button(table, SWT.CHECK);
		check_d19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d19.pack();
		editor_d19.minimumWidth = check_d19.getSize ().x;
		editor_d19.setEditor(check_d19, item5, 19);
		
		TableEditor editor_d20 = new TableEditor(table);
		Button check_d20 = new Button(table, SWT.CHECK);
		check_d20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d20.pack();
		editor_d20.minimumWidth = check_d20.getSize ().x;
		editor_d20.setEditor(check_d20, item5, 20);
		
		TableEditor editor_d21 = new TableEditor(table);
		Button check_d21 = new Button(table, SWT.CHECK);
		check_d21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d21.pack();
		editor_d21.minimumWidth = check_d21.getSize ().x;
		editor_d21.setEditor(check_d21, item5, 21);
		
		TableEditor editor_d22 = new TableEditor(table);
		Button check_d22 = new Button(table, SWT.CHECK);
		check_d22.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d22.pack();
		editor_d22.minimumWidth = check_d22.getSize ().x;
		editor_d22.setEditor(check_d22, item5, 22);
		
		TableEditor editor_d23 = new TableEditor(table);
		Button check_d23 = new Button(table, SWT.CHECK);
		check_d23.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d23.pack();
		editor_d23.minimumWidth = check_d23.getSize ().x;
		editor_d23.setEditor(check_d23, item5, 23);
		
		TableEditor editor_d24 = new TableEditor(table);
		Button check_d24 = new Button(table, SWT.CHECK);
		check_d24.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_d24.pack();
		editor_d24.minimumWidth = check_d24.getSize ().x;
		editor_d24.setEditor(check_d24, item5, 24);
		
		
		
		item6 = new TableItem(table, SWT.NONE);
		item6.setText(0, "星期五");
		TableEditor editor_e1 = new TableEditor(table);
		final Button check_e1 = new Button(table, SWT.CHECK);
		check_e1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e1.pack();
		editor_e1.minimumWidth = check_e1.getSize ().x;
		editor_e1.setEditor(check_e1, item6, 1);
		
		TableEditor editor_e2 = new TableEditor(table);
		final Button check_e2 = new Button(table, SWT.CHECK);
		check_e2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e2.pack();
		editor_e2.minimumWidth = check_e2.getSize ().x;
		editor_e2.setEditor(check_e2, item6, 2);
		
		TableEditor editor_e3 = new TableEditor(table);
		final Button check_e3 = new Button(table, SWT.CHECK);
		check_e3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e3.pack();
		editor_e3.minimumWidth = check_e3.getSize ().x;
		editor_e3.setEditor(check_e3, item6, 3);
		
		TableEditor editor_e4 = new TableEditor(table);
		final Button check_e4 = new Button(table, SWT.CHECK);
		check_e4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e4.pack();
		editor_e4.minimumWidth = check_e4.getSize ().x;
		editor_e4.setEditor(check_e4, item6, 4);
		
		TableEditor editor_e5 = new TableEditor(table);
		final Button check_e5 = new Button(table, SWT.CHECK);
		check_e5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e5.pack();
		editor_e5.minimumWidth = check_e5.getSize ().x;
		editor_e5.setEditor(check_e5, item6, 5);
		
		TableEditor editor_e6 = new TableEditor(table);
		final Button check_e6 = new Button(table, SWT.CHECK);
		check_e6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e6.pack();
		editor_e6.minimumWidth = check_e6.getSize ().x;
		editor_e6.setEditor(check_e6, item6, 6);
		
		TableEditor editor_e7 = new TableEditor(table);
		final Button check_e7 = new Button(table, SWT.CHECK);
		check_e7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e7.pack();
		editor_e7.minimumWidth = check_e7.getSize ().x;
		editor_e7.setEditor(check_e7, item6, 7);
		
		TableEditor editor_e8 = new TableEditor(table);
		final Button check_e8 = new Button(table, SWT.CHECK);
		check_e8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e8.pack();
		editor_e8.minimumWidth = check_e8.getSize ().x;
		editor_e8.setEditor(check_e8, item6, 8);
		
		TableEditor editor_e9 = new TableEditor(table);
		final Button check_e9 = new Button(table, SWT.CHECK);
		check_e9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e9.pack();
		editor_e9.minimumWidth = check_e9.getSize ().x;
		editor_e9.setEditor(check_e9, item6, 9);
		
		TableEditor editor_e10 = new TableEditor(table);
		final Button check_e10 = new Button(table, SWT.CHECK);
		check_e10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e10.pack();
		editor_e10.minimumWidth = check_e10.getSize ().x;
		editor_e10.setEditor(check_e10, item6, 10);
		
		TableEditor editor_e11 = new TableEditor(table);
		final Button check_e11 = new Button(table, SWT.CHECK);
		check_e11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e11.pack();
		editor_e11.minimumWidth = check_e11.getSize ().x;
		editor_e11.setEditor(check_e11, item6, 11);
		
		TableEditor editor_e12 = new TableEditor(table);
		final Button check_e12 = new Button(table, SWT.CHECK);
		check_e12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e12.pack();
		editor_e12.minimumWidth = check_e12.getSize ().x;
		editor_e12.setEditor(check_e12, item6, 12);
		
		TableEditor editor_e13 = new TableEditor(table);
		final Button check_e13 = new Button(table, SWT.CHECK);
		check_e13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e13.pack();
		editor_e13.minimumWidth = check_e13.getSize ().x;
		editor_e13.setEditor(check_e13, item6, 13);
		
		TableEditor editor_e14 = new TableEditor(table);
		final Button check_e14 = new Button(table, SWT.CHECK);
		check_e14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e14.pack();
		editor_e14.minimumWidth = check_e14.getSize ().x;
		editor_e14.setEditor(check_e14, item6, 14);
		
		TableEditor editor_e15 = new TableEditor(table);
		final Button check_e15 = new Button(table, SWT.CHECK);
		check_e15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e15.pack();
		editor_e15.minimumWidth = check_e15.getSize ().x;
		editor_e15.setEditor(check_e15, item6, 15);
		
		TableEditor editor_e16 = new TableEditor(table);
		final Button check_e16 = new Button(table, SWT.CHECK);
		check_e16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e16.pack();
		editor_e16.minimumWidth = check_e16.getSize ().x;
		editor_e16.setEditor(check_e16, item6, 16);
		
		TableEditor editor_e17 = new TableEditor(table);
		final Button check_e17 = new Button(table, SWT.CHECK);
		check_e17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e17.pack();
		editor_e17.minimumWidth = check_e17.getSize ().x;
		editor_e17.setEditor(check_e17, item6, 17);
		
		TableEditor editor_e18 = new TableEditor(table);
		final Button check_e18 = new Button(table, SWT.CHECK);
		check_e18.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e18.pack();
		editor_e18.minimumWidth = check_e18.getSize ().x;
		editor_e18.setEditor(check_e18, item6, 18);
		
		TableEditor editor_e19 = new TableEditor(table);
		final Button check_e19 = new Button(table, SWT.CHECK);
		check_e19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e19.pack();
		editor_e19.minimumWidth = check_e19.getSize ().x;
		editor_e19.setEditor(check_e19, item6, 19);
		
		TableEditor editor_e20 = new TableEditor(table);
		final Button check_e20 = new Button(table, SWT.CHECK);
		check_e20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e20.pack();
		editor_e20.minimumWidth = check_e20.getSize ().x;
		editor_e20.setEditor(check_e20, item6, 20);
		
		TableEditor editor_e21 = new TableEditor(table);
		final Button check_e21 = new Button(table, SWT.CHECK);
		check_e21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e21.pack();
		editor_e21.minimumWidth = check_e21.getSize ().x;
		editor_e21.setEditor(check_e21, item6, 21);
		
		TableEditor editor_e22 = new TableEditor(table);
		final Button check_e22 = new Button(table, SWT.CHECK);
		check_e22.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e22.pack();
		editor_e22.minimumWidth = check_e22.getSize ().x;
		editor_e22.setEditor(check_e22, item6, 22);
		
		TableEditor editor_e23 = new TableEditor(table);
		final Button check_e23 = new Button(table, SWT.CHECK);
		check_e23.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e23.pack();
		editor_e23.minimumWidth = check_e23.getSize ().x;
		editor_e23.setEditor(check_e23, item6, 23);
		
		TableEditor editor_e24 = new TableEditor(table);
		final Button check_e24 = new Button(table, SWT.CHECK);
		check_e24.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_e24.pack();
		editor_e24.minimumWidth = check_e24.getSize ().x;
		editor_e24.setEditor(check_e24, item6, 24);
		
		
		item7 = new TableItem(table, SWT.NONE);
		item7.setText(0, "星期六");
		TableEditor editor_f1 = new TableEditor(table);
		final Button check_f1 = new Button(table, SWT.CHECK);
		check_f1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f1.pack();
		editor_f1.minimumWidth = check_f1.getSize ().x;
		editor_f1.setEditor(check_f1, item7, 1);
		
		TableEditor editor_f2 = new TableEditor(table);
		final Button check_f2 = new Button(table, SWT.CHECK);
		check_f2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f2.pack();
		editor_f2.minimumWidth = check_f2.getSize ().x;
		editor_f2.setEditor(check_f2, item7, 2);
		
		TableEditor editor_f3 = new TableEditor(table);
		final Button check_f3 = new Button(table, SWT.CHECK);
		check_f3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f3.pack();
		editor_f3.minimumWidth = check_f3.getSize ().x;
		editor_f3.setEditor(check_f3, item7, 3);
		
		TableEditor editor_f4 = new TableEditor(table);
		final Button check_f4 = new Button(table, SWT.CHECK);
		check_f4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f4.pack();
		editor_f4.minimumWidth = check_f4.getSize ().x;
		editor_f4.setEditor(check_f4, item7, 4);
		
		TableEditor editor_f5 = new TableEditor(table);
		final Button check_f5 = new Button(table, SWT.CHECK);
		check_f5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f5.pack();
		editor_f5.minimumWidth = check_f5.getSize ().x;
		editor_f5.setEditor(check_f5, item7, 5);
		
		TableEditor editor_f6 = new TableEditor(table);
		final Button check_f6 = new Button(table, SWT.CHECK);
		check_f6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f6.pack();
		editor_f6.minimumWidth = check_f6.getSize ().x;
		editor_f6.setEditor(check_f6, item7, 6);
		
		TableEditor editor_f7 = new TableEditor(table);
		final Button check_f7 = new Button(table, SWT.CHECK);
		check_f7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f7.pack();
		editor_f7.minimumWidth = check_f7.getSize ().x;
		editor_f7.setEditor(check_f7, item7, 7);
		
		TableEditor editor_f8 = new TableEditor(table);
		final Button check_f8 = new Button(table, SWT.CHECK);
		check_f8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f8.pack();
		editor_f8.minimumWidth = check_f8.getSize ().x;
		editor_f8.setEditor(check_f8, item7, 8);
		
		TableEditor editor_f9 = new TableEditor(table);
		final Button check_f9 = new Button(table, SWT.CHECK);
		check_f9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f9.pack();
		editor_f9.minimumWidth = check_f9.getSize ().x;
		editor_f9.setEditor(check_f9, item7, 9);
		
		TableEditor editor_f10 = new TableEditor(table);
		final Button check_f10 = new Button(table, SWT.CHECK);
		check_f10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f10.pack();
		editor_f10.minimumWidth = check_f10.getSize ().x;
		editor_f10.setEditor(check_f10, item7, 10);
		
		TableEditor editor_f11 = new TableEditor(table);
		final Button check_f11 = new Button(table, SWT.CHECK);
		check_f11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f11.pack();
		editor_f11.minimumWidth = check_f11.getSize ().x;
		editor_f11.setEditor(check_f11, item7, 11);
		
		TableEditor editor_f12 = new TableEditor(table);
		final Button check_f12 = new Button(table, SWT.CHECK);
		check_f12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f12.pack();
		editor_f12.minimumWidth = check_f12.getSize ().x;
		editor_f12.setEditor(check_f12, item7, 12);
		
		TableEditor editor_f13 = new TableEditor(table);
		final Button check_f13 = new Button(table, SWT.CHECK);
		check_f13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f13.pack();
		editor_f13.minimumWidth = check_f13.getSize ().x;
		editor_f13.setEditor(check_f13, item7, 13);
		
		TableEditor editor_f14 = new TableEditor(table);
		final Button check_f14 = new Button(table, SWT.CHECK);
		check_f14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f14.pack();
		editor_f14.minimumWidth = check_f14.getSize ().x;
		editor_f14.setEditor(check_f14, item7, 14);
		
		TableEditor editor_f15 = new TableEditor(table);
		final Button check_f15 = new Button(table, SWT.CHECK);
		check_f15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f15.pack();
		editor_f15.minimumWidth = check_f15.getSize ().x;
		editor_f15.setEditor(check_f15, item7, 15);
		
		TableEditor editor_f16 = new TableEditor(table);
		final Button check_f16 = new Button(table, SWT.CHECK);
		check_f16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f16.pack();
		editor_f16.minimumWidth = check_f16.getSize ().x;
		editor_f16.setEditor(check_f16, item7, 16);
		
		TableEditor editor_f17 = new TableEditor(table);
		final Button check_f17 = new Button(table, SWT.CHECK);
		check_f17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f17.pack();
		editor_f17.minimumWidth = check_f17.getSize ().x;
		editor_f17.setEditor(check_f17, item7, 17);
		
		TableEditor editor_f18 = new TableEditor(table);
		final Button check_f18 = new Button(table, SWT.CHECK);
		check_f18.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f18.pack();
		editor_f18.minimumWidth = check_f18.getSize ().x;
		editor_f18.setEditor(check_f18, item7, 18);
		
		TableEditor editor_f19 = new TableEditor(table);
		final Button check_f19 = new Button(table, SWT.CHECK);
		check_f19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f19.pack();
		editor_f19.minimumWidth = check_f19.getSize ().x;
		editor_f19.setEditor(check_f19, item7, 19);
		
		TableEditor editor_f20 = new TableEditor(table);
		final Button check_f20 = new Button(table, SWT.CHECK);
		check_f20.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f20.pack();
		editor_f20.minimumWidth = check_f20.getSize ().x;
		editor_f20.setEditor(check_f20, item7, 20);
		
		TableEditor editor_f21 = new TableEditor(table);
		final Button check_f21 = new Button(table, SWT.CHECK);
		check_f21.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f21.pack();
		editor_f21.minimumWidth = check_f21.getSize ().x;
		editor_f21.setEditor(check_f21, item7, 21);
		
		TableEditor editor_f22 = new TableEditor(table);
		final Button check_f22 = new Button(table, SWT.CHECK);
		check_f22.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f22.pack();
		editor_f22.minimumWidth = check_f22.getSize ().x;
		editor_f22.setEditor(check_f22, item7, 22);
		
		TableEditor editor_f23 = new TableEditor(table);
		final Button check_f23 = new Button(table, SWT.CHECK);
		check_f23.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f23.pack();
		editor_f23.minimumWidth = check_f23.getSize ().x;
		editor_f23.setEditor(check_f23, item7, 23);
		
		TableEditor editor_f24 = new TableEditor(table);
		final Button check_f24 = new Button(table, SWT.CHECK);
		check_f24.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		check_f24.pack();
		editor_f24.minimumWidth = check_f24.getSize ().x;
		editor_f24.setEditor(check_f24, item7, 24);
		
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 10, 40, 12);
		lblNewLabel.setText("\u5907\u6CE8\uFF1A");
		
		text = new Text(composite_2, SWT.WRAP | SWT.BORDER);//备注
		text.setBounds(55, 10, 350, 100);
		text.setText(Instruction);
		
		sashForm.setWeights(new int[] {1, 10, 5});
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button button = createButton(parent, IDialogConstants.OK_ID, "确定",true);
		Button button1 = createButton(parent, IDialogConstants.CANCEL_ID, "取消",true);
	}
	protected void buttonPressed(int buttonId){
		if(buttonId==IDialogConstants.OK_ID){
			
		}
		this.close();
	}
}
