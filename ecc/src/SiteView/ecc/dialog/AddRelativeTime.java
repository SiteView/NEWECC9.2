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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AddRelativeTime extends Dialog {
	private Table table;
	private Text text;
	private Text text_1;
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
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(10, 5, 100, 18);
		lblNewLabel_1.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0*\uFF1A");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(115, 5, 150, 18);
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(80);
		tblclmnNewColumn.setText("星期");
		
		for (int i = 0; i < 24; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setWidth(30);
			column.setText(i+"");
		}
		
		TableItem item1 = new TableItem(table, SWT.NONE);
		item1.setText(0, "星期日");
		Image image = new Image(null, 15, 30);
		item1.setImage(image);
		for (int i = 0; i < 24; i++) {
			TableEditor editor = new TableEditor(table);
			final Button check = new Button(table, SWT.CHECK);
			check.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			check.pack();
			editor.minimumWidth = check.getSize ().x;
			editor.minimumHeight = check.getSize ().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(check, item1, i+1);
		}
		
		TableItem item2 = new TableItem(table, SWT.NONE);
		item2.setText(0, "星期一");
		for (int i = 0; i < 24; i++) {
			TableEditor editor = new TableEditor(table);
			final Button check = new Button(table, SWT.CHECK);
			check.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			check.pack();
			editor.minimumWidth = check.getSize ().x;
			editor.minimumHeight = check.getSize ().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(check, item2, i+1);
		}
		
		TableItem item3 = new TableItem(table, SWT.NONE);
		item3.setText(0, "星期二");
		for (int i = 0; i < 24; i++) {
			TableEditor editor = new TableEditor(table);
			final Button check = new Button(table, SWT.CHECK);
			check.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			check.pack();
			editor.minimumWidth = check.getSize ().x;
			editor.minimumHeight = check.getSize ().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(check, item3, i+1);
		}
		
		TableItem item4 = new TableItem(table, SWT.NONE);
		item4.setText(0, "星期三");
		for (int i = 0; i < 24; i++) {
			TableEditor editor = new TableEditor(table);
			final Button check = new Button(table, SWT.CHECK);
			check.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			check.pack();
			editor.minimumWidth = check.getSize ().x;
			editor.minimumHeight = check.getSize ().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(check, item4, i+1);
		}
		
		TableItem item5 = new TableItem(table, SWT.NONE);
		item5.setText(0, "星期四");
		for (int i = 0; i < 24; i++) {
			TableEditor editor = new TableEditor(table);
			final Button check = new Button(table, SWT.CHECK);
			check.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			check.pack();
			editor.minimumWidth = check.getSize ().x;
			editor.minimumHeight = check.getSize ().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(check, item5, i+1);
		}
		
		TableItem item6 = new TableItem(table, SWT.NONE);
		item6.setText(0, "星期五");
		for (int i = 0; i < 24; i++) {
			TableEditor editor = new TableEditor(table);
			final Button check = new Button(table, SWT.CHECK);
			check.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			check.pack();
			editor.minimumWidth = check.getSize ().x;
			editor.minimumHeight = check.getSize ().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(check, item6, i+1);
		}
		
		TableItem item7 = new TableItem(table, SWT.NONE);
		item7.setText(0, "星期六");
		for (int i = 0; i < 24; i++) {
			TableEditor editor = new TableEditor(table);
			final Button check = new Button(table, SWT.CHECK);
			check.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			check.pack();
			editor.minimumWidth = check.getSize ().x;
			editor.minimumHeight = check.getSize ().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(check, item7, i+1);
		}
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 10, 40, 12);
		lblNewLabel.setText("\u5907\u6CE8\uFF1A");
		
		text = new Text(composite_2, SWT.WRAP | SWT.BORDER);
		text.setBounds(55, 10, 350, 100);
		sashForm.setWeights(new int[] {1, 10, 5});
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button button = createButton(parent, IDialogConstants.OK_ID, "确定",true);
		Button button1 = createButton(parent, IDialogConstants.CANCEL_ID, "取消",true);
	}
	
	protected void buttonPressed(int buttonId) {
		if(buttonId==IDialogConstants.OK_ID){
			
		}else{
			this.close();
		}
	}
}
