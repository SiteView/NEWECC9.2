package SiteView.ecc.dialog;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import siteview.windows.forms.ImageHelper;
import SiteView.ecc.Activator;
import SiteView.ecc.Control.GroupTreeContentProvider;
import SiteView.ecc.Control.GroupTreeLabelProvider;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import system.Collections.ICollection;
import system.Collections.IEnumerator;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class MonitorSetUp extends Dialog {
	private String title = "批量修改监测器";

	private Table table;

	private TableItem tableItem;

	private TableViewer tableViewer;

	private Tree tree;

	private Text text_1;
	
	private Text text;

	private Combo combo;

	private Combo combo_1;

	private Button btnCheckButton;

	public MonitorSetUp(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		newShell.setSize(870, 500);
		newShell.setLocation(220, 120);
		newShell.setText(title);
		super.configureShell(newShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout());

		SashForm sashForm = new SashForm(composite, SWT.NONE);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setVisible(true);
		composite_1.setLayout(new FillLayout());

		TreeViewer treeViewer = new TreeViewer(composite_1, SWT.BORDER | SWT.CHECK | SWT.V_SCROLL);
		tree = treeViewer.getTree();
		tree.setBackground(EccTreeControl.color);
		tree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = (TreeItem) e.item;
				if (item.getChecked()) {
					SelectParent(item);
					SelectChild(item);
					Set<String> set = new HashSet<String>();
					Set<String> set3 = selectAllId(item,set);
					createTableItem(set3);	
				}else{
					DeletChild(item);
					if (!item.getText().equals("SiteViewEcc9.2")) {
						Set<String> set = new HashSet<String>();
						Set<String> set2 = selectAllId(item,set);
						if(item.getParentItem().getText().equals("SiteViewEcc9.2")){
							for (int i=0;i<item.getParentItem().getItems().length;i++) {
								if(item.getParentItem().getItems()[i].getChecked()){
									break;
								}
								if(i==item.getParentItem().getItems().length-1){
									DeletParent(item);
									
								}
							}
						}
						for (String string : set2) {
							TableItem[] ti = table.getItems();
							for (TableItem tableItem : ti) {
								if(((BusinessObject)tableItem.getData()).get_RecId().equals(string)){
									tableItem.dispose();
								}
							}
						}
					}
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		treeViewer.setContentProvider(new GroupTreeContentProvider());
		treeViewer.setLabelProvider(new GroupTreeLabelProvider());
		treeViewer.setInput(SiteViewData.CreatTreeData());
		treeViewer.expandToLevel(2);

		SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);

		tableViewer = new TableViewer(sashForm_1, SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table = tableViewer.getTable();
		table.setBackground(EccTreeControl.color);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				tableItem = (TableItem) e.item;

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		TableColumn tableName = new TableColumn(table, SWT.NONE);
		tableName.setWidth(160);
		tableName.setText("\u540D\u79F0");

		TableColumn tableFrequency = new TableColumn(table, SWT.NONE);
		tableFrequency.setWidth(160);
		tableFrequency.setText("\u76D1\u6D4B\u9891\u7387");

		TableColumn tableConditions = new TableColumn(table, SWT.NONE);
		tableConditions.setWidth(160);
		tableConditions.setText("\u9600\u503C");

		TableColumn tableState = new TableColumn(table, SWT.NONE);
		tableState.setWidth(160);
		tableState.setText("\u72B6\u6001");

		TabFolder tabFolder = new TabFolder(sashForm_1, SWT.NONE);

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("\u57FA\u7840\u4FE1\u606F");

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_2);
		composite_2.setLayout(new GridLayout(5, false));
		composite_2.setBackground(EccTreeControl.color);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);
		new Label(composite_2, SWT.NONE);

		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setText("\u76D1\u6D4B\u5668\u9891\u7387 *\uFF1A");
		label_1.setBackground(EccTreeControl.color);

		text = new Text(composite_2, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});

		combo = new Combo(composite_2, SWT.READ_ONLY);
		combo.add("Second");
		combo.add("Minute");
		combo.add("Hour");
		combo.add("Day");
		combo.select(0);

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("\u62A5\u8B66\u6761\u4EF6");

		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_3);
		composite_3.setLayout(new FormLayout());
		composite_3.setBackground(EccTreeControl.color);

		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText("\u9519\u8BEF\u6821\u9A8C");

		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_2.setControl(composite_4);
		composite_4.setLayout(new GridLayout(5, false));
		composite_4.setBackground(EccTreeControl.color);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);

		Label lblNewLabel_1 = new Label(composite_4, SWT.NONE);
		lblNewLabel_1.setText("\u9519\u8BEF\u6821\u9A8C\uFF1A");
		lblNewLabel_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));

		btnCheckButton = new Button(composite_4, SWT.CHECK);
		btnCheckButton.setText("\u76D1\u6D4B\u5668\u9519\u8BEF\u6821\u9A8C");
		btnCheckButton.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);
		new Label(composite_4, SWT.NONE);

		Label label = new Label(composite_4, SWT.NONE);
		label.setText("\u9519\u8BEF\u6821\u9A8C\u9891\u7387\uFF1A");
		label.setBackground(EccTreeControl.color);

		text_1 = new Text(composite_4, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1,
				1));
		text_1.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});

		combo_1 = new Combo(composite_4, SWT.READ_ONLY);
		combo_1.add("Second");
		combo_1.add("Minute");
		combo_1.add("Hour");
		combo_1.add("Day");
		combo_1.select(0);

		sashForm_1.setWeights(new int[] { 1, 1 });
		sashForm.setWeights(new int[] { 1, 3 });
		return composite;
	}

	// 創建表格項
	public void createTableItem(Set<String> set1) {
		for (String s1 : set1) {
			BusinessObject bo1 = EccTreeControl.CreateBo("RecId",s1, "Ecc");
			BusinessObject bodyn = EccTreeControl.CreateBo("monitorid",s1, "EccDyn");
			String[] data = new String[4];
			if (bodyn == null) {
				data[0] = bo1.GetField("title").get_NativeValue()
						.toString();
				String frequency = bo1.GetField("frequency")
						.get_NativeValue().toString();
				frequency = frequency.substring(0,
						frequency.lastIndexOf("."));
				String timeUnitSelf = bo1.GetField("timeUnitSelf")
					.get_NativeValue().toString();
				data[1] = frequency + " " + timeUnitSelf;
				data[2] = "測試條件";
				data[3] = "no data";
			} else {
				data[0] = bo1.GetField("title").get_NativeValue()
						.toString();
				String frequency = bo1.GetField("frequency")
						.get_NativeValue().toString();
				frequency = frequency.substring(0,
						frequency.lastIndexOf("."));
				String timeUnitSelf = bo1.GetField("timeUnitSelf")
						.get_NativeValue().toString();
				data[1] = frequency + " " + timeUnitSelf;
				data[2] = "測試條件";
				data[3] = bodyn.GetField("category").get_NativeValue()
						.toString();
			}
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setData(bo1);
			tableItem.setText(data);
		}
	}

	// 获取所选项的包括孩子的所有监测器id
	public Set<String> selectAllId(TreeItem item,Set<String> set) {
		if(item.getData() instanceof MonitorModle){
			String s = ((MonitorModle)item.getData()).getBo().get_RecId();
			set.add(s);
		}
		if(item.getItems().length!=0){
			TreeItem[] treeitem = item.getItems();
			for (TreeItem treeItem2 : treeitem) {
				selectAllId(treeItem2,set);
			}
			
		}
		return set;
	}

	// 勾选项取消时，取消勾选项的孩子
	protected void DeletChild(TreeItem item) {
		if (item.getItemCount() > 0) {
			for (TreeItem t : item.getItems()) {
				t.setChecked(false);
				DeletChild(t);
			}
		}
	}

	// 勾选项取消时，取消勾选项的父亲
	private void DeletParent(TreeItem item) {
		if (item.getParent() != null && !item.getText().equals("SiteViewEcc9.2")) {
			TreeItem treeItem = item.getParentItem();
			treeItem.setChecked(false);
			DeletParent(treeItem);
		}
	}

	// 勾选某项时同时勾选他的父亲项
	private void SelectParent(TreeItem item) {
		if (item.getParent() != null && !item.getText().equals("SiteViewEcc9.2")) {
			TreeItem treeItem = item.getParentItem();
			treeItem.setChecked(true);
			SelectParent(treeItem);
		}
	}

	// 勾选某项时同时勾选他的孩子项
	protected void SelectChild(TreeItem item) {
		if (item.getItemCount() > 0) {
			for (TreeItem t : item.getItems()) {
				t.setChecked(true);
				SelectChild(t);
			}
		}
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "确定",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", true);
		Button finishButton = createButton(parent, IDialogConstants.FINISH_ID,
				"应用", true);
		Button helpButton = createButton(parent, IDialogConstants.HELP_ID,
				"帮助", true);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			BusinessObject bo = (BusinessObject) tableItem.getData();
			bo = SiteView.ecc.views.EccTreeControl.CreateBo("RecId",
					bo.get_RecId(), "Ecc."
							+ bo.GetField("EccType").get_NativeValue()
									.toString());
			if (text.getText() != "") {
				bo.GetField("frequency").SetValue(
						new SiteviewValue(text.getText()));
				bo.GetField("timeUnitSelf").SetValue(
						new SiteviewValue(combo.getText()));
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			}
			if (text_1.getText() != "") {
				bo.GetField("verifyerror").SetValue(
						new SiteviewValue(btnCheckButton.getSelection()));
				bo.GetField("verifyErrorFrequency").SetValue(
						new SiteviewValue(text_1.getText()));
				bo.GetField("ErrorFrequency").SetValue(
						new SiteviewValue(combo_1.getText()));
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			}
			this.close();
		} else if (buttonId == IDialogConstants.FINISH_ID) {
			BusinessObject bo = (BusinessObject) tableItem.getData();
			bo = SiteView.ecc.views.EccTreeControl.CreateBo("RecId",
					bo.get_RecId(), "Ecc."
							+ bo.GetField("EccType").get_NativeValue()
									.toString());
			if (text.getText() != "") {
				bo.GetField("frequency").SetValue(
						new SiteviewValue(Double.parseDouble(text.getText())));
				bo.GetField("timeUnitSelf").SetValue(
						new SiteviewValue(combo.getText()));
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				tableItem.setText(1, text.getText() + " " + combo.getText());
			}
			if (text_1.getText() != "") {
				bo.GetField("verifyerror").SetValue(
						new SiteviewValue(btnCheckButton.getSelection()));
				bo.GetField("verifyErrorFrequency").SetValue(
						new SiteviewValue(text_1.getText()));
				bo.GetField("ErrorFrequency").SetValue(
						new SiteviewValue(combo_1.getText()));
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			}
		} else if (buttonId == IDialogConstants.HELP_ID) {

		} else {
			this.close();
		}
	}

}
