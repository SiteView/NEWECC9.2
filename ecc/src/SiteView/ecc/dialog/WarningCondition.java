package SiteView.ecc.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;

import SiteView.ecc.tools.Config;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
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
	public TableItem item;
	private Text text;
	public List<String> list = new ArrayList<String>();
	public static Map<String, String> map=new HashMap<String, String>();

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
			map.put(bo.GetField("Item").get_NativeValue().toString(), bo.GetField("SaveItem").get_NativeValue().toString());
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
		
		final Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setBounds(75, 10, 170, 20);
		for (String str1 : list) {
			combo.add(str1);
		}
		
		final Combo combo_1 = new Combo(composite_1, SWT.READ_ONLY);
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
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});
		
		final Button button = new Button(composite_1, SWT.RADIO);
		button.setBounds(20, 36, 30, 16);
		button.setText("\u4E0E");
		button.setBackground(EccTreeControl.color);
		
		final Button button_1 = new Button(composite_1, SWT.RADIO);
		button_1.setBounds(55, 36, 30, 16);
		button_1.setText("\u6216");
		button_1.setBackground(EccTreeControl.color);
		
		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setBounds(350, 34, 72, 22);
		button_2.setText("\u6DFB\u52A0");
		button_2.setBackground(EccTreeControl.color);
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().equals("")||combo_1.getText().equals("")||text.getText().equals("")){
					MessageDialog.openInformation(new Shell(), "提示", "项目表达式不能为空！");
					return;
				}
				TableItem[] item1=table.getItems();
				TableItem item=new TableItem(table, SWT.NONE);
				String date[]=new String[4];
				if(item1.length==0){
					date[0]=combo.getText();
					date[1]=combo_1.getText();
					date[2]=text.getText();
					date[3]="";
				}else{
					date[0]=combo.getText();
					date[1]=combo_1.getText();
					date[2]=text.getText();
					if(button.getSelection()){
						date[3]=button.getText();						
					}else if(button_1.getSelection()){
						date[3]=button_1.getText();
					}
				}
				item.setText(date);
			}
		});
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				item=(TableItem)e.item;
			}
		});
		
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
		if(string.equals("error")){
			if(MonitorSetUp.list!=null&&(!MonitorSetUp.text_2.getText().equals(""))){
				for (int i=0;i<MonitorSetUp.list.size();i++) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText((String[])MonitorSetUp.list.get(i));
				}
			}
		}else if(string.equals("alarm")){
			if(MonitorSetUp.list1!=null&&(!MonitorSetUp.text_3.getText().equals(""))){
				for (int i=0;i<MonitorSetUp.list1.size();i++) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText((String[])MonitorSetUp.list1.get(i));
				}
			}
		}else if(string.equals("normal")){
			if(MonitorSetUp.list2!=null&&(!MonitorSetUp.text_4.getText().equals(""))){
				for (int i=0;i<MonitorSetUp.list2.size();i++) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText((String[])MonitorSetUp.list2.get(i));
				}
			}
		}
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Button buttondel=createButton(parent, IDialogConstants.DETAILS_ID, "删除", true);
		Button buttoncancel=createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
		Button buttonok=createButton(parent, IDialogConstants.OK_ID, "确定", true);
	}
	
	protected void buttonPressed(int buttonId) {
		if(buttonId==IDialogConstants.OK_ID){
			if(string.equals("error")){
				MonitorSetUp.list=new ArrayList();
				TableItem[] item=table.getItems();
				String str="";
				for (int i=item.length-1;i>=0;i--) {
					str=str+"["+item[i].getText(0)+item[i].getText(1)+item[i].getText(2)+"]"+item[i].getText(3);
					String[] array=new String[4];
					array[0]=item[i].getText(0);
					array[1]=item[i].getText(1);
					array[2]=item[i].getText(2);
					array[3]=item[i].getText(3);
					MonitorSetUp.list.add(array);
				}
				MonitorSetUp.text_2.setText(str);
				MonitorSetUp.combo_2.setEnabled(false);
			}else if(string.equals("alarm")){
				MonitorSetUp.list1=new ArrayList();
				TableItem[] item=table.getItems();
				String str="";
				for (int i=item.length-1;i>=0;i--) {
					str=str+"["+item[i].getText(0)+item[i].getText(1)+item[i].getText(2)+"]"+item[i].getText(3);
					String[] array=new String[4];
					array[0]=item[i].getText(0);
					array[1]=item[i].getText(1);
					array[2]=item[i].getText(2);
					array[3]=item[i].getText(3);
					MonitorSetUp.list1.add(array);
				}
				MonitorSetUp.text_3.setText(str);
				MonitorSetUp.combo_2.setEnabled(false);
			}else if(string.equals("normal")){
				MonitorSetUp.list2=new ArrayList();
				TableItem[] item=table.getItems();
				String str="";
				for (int i=item.length-1;i>=0;i--) {
					str=str+"["+item[i].getText(0)+item[i].getText(1)+item[i].getText(2)+"]"+item[i].getText(3);
					String[] array=new String[4];
					array[0]=item[i].getText(0);
					array[1]=item[i].getText(1);
					array[2]=item[i].getText(2);
					array[3]=item[i].getText(3);
					MonitorSetUp.list2.add(array);
				}
				MonitorSetUp.text_4.setText(str);
				MonitorSetUp.combo_2.setEnabled(false);
			}
			
			this.close();
		}else if(buttonId==IDialogConstants.DETAILS_ID){
			if(item==null||item.isDisposed()){
				MessageDialog.openInformation(new Shell(), "提示", "请选择需要删除的项！");
				return;
			}
			if(!item.isDisposed()){
				item.dispose();
			}
		}else{
			this.close();
		}
	}
}
