package SiteView.ecc.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;

import SiteView.ecc.tools.Config;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TableColumn;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class WarningCondition extends Dialog {
	public String string;
	public String select;
	private Table table;
	private Text text;
	public List<String> list = new ArrayList<String>();

	public WarningCondition(Shell parentShell,String string,String select) {
		super(parentShell);
		this.string=string;
		this.select=select;
	}
	
	protected void configureShell(Shell newShell) {
		if(string.equals("error")){
			newShell.setText("编辑错误条件");
		}else if(string.equals("alarm")){
			newShell.setText("编辑警告条件");
		}else if(string.equals("normal")){
			newShell.setText("编辑正常条件");
		}
		newShell.setSize(450, 250);
		newShell.setLocation(400, 250);
		super.configureShell(newShell);
	}
	
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		String path = FileTools.getRealPath("\\files\\MonitorReturnValveReference.properties");
		String name = Config.getReturnStr(path, "Ecc."+select);
		ICollection ic = FileTools.getBussCollection(name);
		IEnumerator ien=ic.GetEnumerator();
		while(ien.MoveNext()){
			BusinessObject bo=(BusinessObject) ien.get_Current();
			list.add(bo.GetField("Item").get_NativeValue().toString());
		}
		Composite composite = (Composite)super.createDialogArea(parent);
		composite.setBackground(EccTreeControl.color);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		sashForm.setBackground(EccTreeControl.color);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label.setBackground(EccTreeControl.color);
		label.setBounds(10, 10, 54, 20);
		label.setText("\u6761\u4EF6");
		
		Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setBounds(75, 10, 170, 20);
		for (String str1 : list) {
			combo.add(str1);
		}
		combo.select(0);
		
		Combo combo_1 = new Combo(composite_1, SWT.READ_ONLY);
		combo_1.setBounds(250, 10, 90, 20);
		combo_1.add("==");
		combo_1.add("!=");
		combo_1.add(">=");
		combo_1.add(">");
		combo_1.add("<=");
		combo_1.add("<");
		combo_1.add("contains");
		combo_1.add("!contains");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBounds(345, 10, 70, 20);
		
		Button button = new Button(composite_1, SWT.RADIO);
		button.setBounds(20, 36, 30, 16);
		button.setText("\u4E0E");
		button.setBackground(EccTreeControl.color);
		
		Button button_1 = new Button(composite_1, SWT.RADIO);
		button_1.setBounds(55, 36, 30, 16);
		button_1.setText("\u6216");
		button_1.setBackground(EccTreeControl.color);
		
		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setBounds(350, 34, 72, 22);
		button_2.setText("\u6DFB\u52A0");
		button_2.setBackground(EccTreeControl.color);
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u9879\u76EE");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("\u64CD\u4F5C\u7B26");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u503C");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u5173\u7CFB");
		sashForm.setWeights(new int[] {1, 2});
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Button buttondel=createButton(parent, IDialogConstants.DETAILS_ID, "删除", true);
		Button buttoncancel=createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
		Button buttonok=createButton(parent, IDialogConstants.OK_ID, "确定", true);
	}
}
