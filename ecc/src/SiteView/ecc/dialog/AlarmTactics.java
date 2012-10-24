package SiteView.ecc.dialog;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import Siteview.Windows.Forms.MessageBox;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class AlarmTactics extends Dialog{
	public static Table table;
    public TableItem tableItem;
  //  public static Set<String> set=new HashSet<String>();
    public static String TacticName;
	public AlarmTactics(Shell parentShell) {
		super(parentShell);
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(900, 900);
		newShell.setLocation(200, 105);
		newShell.setText("报警策略");
		super.configureShell(newShell);
	}
	
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		
		Label lblNewLabel = new Label(sashForm, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setText("\u544A\u8B66\u7B56\u7565");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		Button button = new Button(composite_1, SWT.NONE);//添加
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddAlarmTactics addAlarm=new AddAlarmTactics(new Shell());
				addAlarm.open();
			}
		});
		button.setBounds(0, 0, 72, 22);
		button.setText("\u6DFB\u52A0");
		
		
		Button button_1 = new Button(composite_1, SWT.NONE);//编辑
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (tableItem==null) {
					MessageBox messageBox = new MessageBox();
					messageBox.Show("请选择要编辑的策略!", "提示", SWT.OK);
				}else{
					String name=tableItem.getText(0);
					//System.out.println("name:"+name);
					AddAlarmTactics addAlarm=new AddAlarmTactics(new Shell(),name,tableItem);
					addAlarm.open();
				}
			}
		});
		button_1.setBounds(81, 0, 72, 22);
		button_1.setText("\u7F16\u8F91");
		
		Button button_2 = new Button(composite_1, SWT.NONE);//删除
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (tableItem==null) {
					MessageBox messageBox = new MessageBox();
					messageBox.Show("你还没有选定想删除的策略名称!", "提示", SWT.OK);
				}else{
					if(!(tableItem.isDisposed())){
						ICollection ico = FileTools.getBussCollection("TacticName",
								tableItem.getText(0), "EccAlarmTactic");
						IEnumerator ien = ico.GetEnumerator();
						while (ien.MoveNext()) {
							((BusinessObject) ien.get_Current()).DeleteObject(ConnectionBroker.get_SiteviewApi());// 删除数据库数据
							tableItem.dispose();// 删除表单数据	
					}
					}
				}
			}
		});
		button_2.setBounds(159, 0, 72, 22);
		button_2.setText("\u5220\u9664");
		
		Button button_3 = new Button(composite_1, SWT.NONE);//刷新
		button_3.setBounds(241, 0, 72, 22);
		button_3.setText("\u5237\u65B0");
		button_3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e){
				table.update();
			}
		});
		
		Label lblNewLabel_1 = new Label(sashForm, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setText("\u544A\u8B66\u7B56\u7565\u5217\u8868");
		
		table = new Table(sashForm, SWT.FULL_SELECTION|SWT.CENTER|SWT.CHECK);
		table.setHeaderVisible(true);
		table.addSelectionListener(new SelectionListener(){ 
			public void widgetSelected(SelectionEvent e) {
				tableItem = (TableItem) e.item;
				tableItem.setChecked(true);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				tableItem.setChecked(false);
			}
		});
		
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		cursor.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				for (TableItem ta : table.getItems()) {
					if (!ta.equals(tableItem)) {
						ta.setChecked(false);
					}
				}
			}
			
			public void mouseDown(MouseEvent e) {
				
			}
			
			public void mouseDoubleClick(MouseEvent e) {
				
			}
		});
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(443);
		tableColumn.setText("名称");
		
		createTable();
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(EccTreeControl.color);
		sashForm.setWeights(new int[] {3, 6, 5, 144, 54});
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "保存",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", true);
	}
	public static void createTable(){//创建表格信息
		for (TableItem item : table.getItems()) {
			item.dispose();
		}
		ICollection icoll = FileTools.getBussCollection("EccAlarmTactic");
		IEnumerator ienum = icoll.GetEnumerator();
		if (ienum != null){
			while(ienum.MoveNext()){
				BusinessObject bo = (BusinessObject) ienum.get_Current();
				if(bo!=null){
				TacticName = bo.GetField("TacticName").get_NativeValue()
							.toString();
				//set.add(TacticName);
				TableItem tableItem_1 = new TableItem(table, SWT.NONE);
				tableItem_1.setText(0, TacticName);
					}
				}
			}
//		for(String a:set){
//			TableItem tableItem_1 = new TableItem(table, SWT.NONE);
//			tableItem_1.setText(0, a);
//		}
	}
}
