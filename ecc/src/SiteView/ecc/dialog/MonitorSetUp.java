package SiteView.ecc.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
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
import SiteView.ecc.Action.PingTypeAction;
import SiteView.ecc.Control.GroupTreeContentProvider;
import SiteView.ecc.Control.GroupTreeLabelProvider;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.tools.Config;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import system.Collections.ICollection;
import system.Collections.IEnumerator;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class MonitorSetUp extends Dialog {
	private String title = "批量修改监测器";

	public static Table table;

	private TableItem tableItem;

	private TableViewer tableViewer;
	
	private TreeViewer treeViewer;
	
	private Object item1;
	
	private TreeItem item;
	
	private Map<TreeItem, Set<String>> map =new HashMap<TreeItem, Set<String>>();

	public static Tree tree;

	private Text text_1;
	
	private Text text;

	private Combo combo;

	private Combo combo_1;
	
	public static Combo combo_2;

	private Button btnCheckButton;
	
	public static Text text_2;
	
	public static Text text_3;
	
	public static Text text_4;
	
	public TabFolder tabFolder;
	
	public static List list;
	
	public static List list1;
	
	public static List list2;
	
	Button subButton ;

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

		treeViewer = new TreeViewer(composite_1, SWT.BORDER | SWT.CHECK | SWT.V_SCROLL);
		tree = treeViewer.getTree();
		tree.setBackground(EccTreeControl.color);
		tree.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if(e.button==1){
					TreeItem item=tree.getItem(new Point(e.x,e.y));
					if (item.getChecked()) {
						SelectParent(item);
						SelectChild(item);
						Set<String> set = new HashSet<String>();
						Set<String> set3 = selectAllId(item,set);
						TableItem[] item1=table.getItems();
						if(item1.length!=0){
							for (String str : set3) {
								for(int i=0;i<item1.length;i++){
									if(((BusinessObject)item1[i].getData()).get_RecId().equals(str)){
										break;
									}
									if(i==item1.length-1){
										createOne(str);
									}
								}
							}
						}else{
							createTableItem(set3);
						}
						Set<String> set1 = new HashSet<String>();
						Set<String> set4 = selectAllType(item,set1);
						for (String string : set4) {
							if(combo_2.getItemCount()>0){
								String[] string2=combo_2.getItems();
								for (int i=0;i<string2.length;i++) {
									if(string2[i].equals(string)){
										break;
									}
									if(i==string2.length-1){
										combo_2.add(string);
										combo_2.setEnabled(true);
									}
								}
							}else{
								combo_2.add(string);
								combo_2.setEnabled(true);
							}
						}
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
							String[] str=combo_2.getItems();
							for (String string : str) {
								TableItem[] ti = table.getItems();
								if(ti.length!=0){									
									for (int i=0;i<ti.length;i++) {
										if(((BusinessObject)ti[i].getData()).GetField("EccType").get_NativeValue().toString().equals(string)){
											break;
										}
										if(i==ti.length-1){
											combo_2.remove(string);
										}
									}
								}else{
									combo_2.removeAll();
								}
							}
						}
					}
				}
			}
		});
		treeViewer.setContentProvider(new GroupTreeContentProvider());
		treeViewer.setLabelProvider(new GroupTreeLabelProvider());
		treeViewer.setInput(SiteViewData.CreatTreeData());
		treeViewer.expandAll();
		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				item=(TreeItem)e.item;
				item1=e.item.getData();
			}
		});
		selectTree(tree.getItems());
		createContextMenu(parent);

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

		tabFolder = new TabFolder(sashForm_1, SWT.NONE);

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
		composite_3.setBackground(EccTreeControl.color);
		
		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_2.setBackground(EccTreeControl.color);
		label_2.setBounds(20, 10, 70, 18);
		label_2.setText("\u76D1\u6D4B\u5668\u7C7B\u578B\uFF1A");
		
		combo_2 = new Combo(composite_3, SWT.READ_ONLY);
		combo_2.setBounds(100, 10, 200, 18);
		
		Label label_3 = new Label(composite_3, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_3.setBackground(EccTreeControl.color);
		label_3.setBounds(20, 35, 70, 18);
		label_3.setText("\u9519\u8BEF\uFF1A");
		
		text_2 = new Text(composite_3, SWT.WRAP | SWT.BORDER |SWT.V_SCROLL);
		text_2.setBounds(100, 35, 200, 45);
		text_2.setEditable(false);
		
		Button button1=new Button(composite_3, SWT.NONE);
		button1.setBounds(305, 55, 25, 25);
		button1.setText("...");
		button1.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(combo_2.getText().equals("")){
					MessageDialog.openInformation(new Shell(), "提示", "请选择监听器！");
				}else{					
					WarningCondition wc=new WarningCondition(null,"error",combo_2.getText());
					wc.open();
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Label label_4 = new Label(composite_3, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_4.setBackground(EccTreeControl.color);
		label_4.setBounds(20, 85, 70, 18);
		label_4.setText("\u8B66\u544A\uFF1A");
		
		text_3 = new Text(composite_3, SWT.WRAP | SWT.BORDER |SWT.V_SCROLL);
		text_3.setBounds(100, 85, 200, 45);
		text_3.setEditable(false);
		
		Button button2=new Button(composite_3, SWT.NONE);
		button2.setBounds(305, 105, 25, 25);
		button2.setText("...");
		button2.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(combo_2.getText().equals("")){
					MessageDialog.openInformation(new Shell(), "提示", "请选择监听器！");
				}else{					
					WarningCondition wc=new WarningCondition(null,"alarm",combo_2.getText());
					wc.open();
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Label lblNewLabel = new Label(composite_3, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		lblNewLabel.setBackground(EccTreeControl.color);
		lblNewLabel.setBounds(20, 135, 70, 18);
		lblNewLabel.setText("\u6B63\u5E38\uFF1A");
		
		text_4 = new Text(composite_3, SWT.WRAP | SWT.BORDER |SWT.V_SCROLL);
		text_4.setBounds(100, 135, 200, 45);
		text_4.setEditable(false);
		
		Button button3=new Button(composite_3, SWT.NONE);
		button3.setBounds(305, 155, 25, 25);
		button3.setText("...");
		button3.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(combo_2.getText().equals("")){
					MessageDialog.openInformation(new Shell(), "提示", "请选择监听器！");
				}else{					
					WarningCondition wc=new WarningCondition(null,"normal",combo_2.getText());
					wc.open();
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

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
	
	private void createContextMenu(Composite parent) {// 添加菜单
		MenuManager mgr = new MenuManager();
		mgr.setRemoveAllWhenShown(true);
		mgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer
						.getSelection();
				Object element = selection.getFirstElement();
				item1 = element;
				if (element instanceof GroupModle) {
					Set<String> setType = map.get(item);
					fillContextMenu1(manager,setType);
				} else if (element instanceof MachineModle) {
					Set<String> setType = map.get(item);
					fillContextMenu1(manager,setType);
				}else if(element instanceof SiteViewEcc){
					Set<String> setType = map.get(item);
					fillContextMenu1(manager,setType);
				}
			}
		});
		Menu menu = mgr.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);

	}
	
	public void fillContextMenu1(IMenuManager manager,Set<String> set){
		MenuManager menu = new MenuManager("批量选择");
		manager.add(menu);
		for (String string : set) {			
			menu.add(new PingTypeAction(string,item));
		}
	}
	
	public void selectTree(TreeItem[] item){
		for (TreeItem treeItem : item) {			
			Set<String> set = new HashSet<String>();
			Set<String> set1 = selectAllType(treeItem, set);
			map.put(treeItem, set1);
			if(treeItem.getItemCount()>0){
				selectTree(treeItem.getItems());
			}
		}
	}

	// 創建表格項
	public static void createTableItem(Set<String> set1) {
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
	
	//对表格添加一行数据
	public void createOne(String s1){
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

	
	// 获取所选项的包括孩子的所有监测器所属的类型
	public static Set<String> selectAllType(TreeItem item,Set<String> set) {
		if(item.getData() instanceof MonitorModle){
			String s = ((MonitorModle)item.getData()).getBo().GetField("EccType").get_NativeValue().toString();
			set.add(s);
		}
		if(item.getItems().length!=0){
			TreeItem[] treeitem = item.getItems();
			for (TreeItem treeItem2 : treeitem) {
				selectAllType(treeItem2,set);
			}
		}
		return set;
	}
		
	// 获取所选项的包括孩子的所有监测器id
	public static Set<String> selectAllId(TreeItem item,Set<String> set) {
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
	public static void SelectParent(TreeItem item) {
		if (item.getParent() != null && !item.getText().equals("SiteViewEcc9.2")) {
			TreeItem treeItem = item.getParentItem();
			treeItem.setChecked(true);
			SelectParent(treeItem);
		}
	}

	// 勾选某项时同时勾选他的孩子项
	private void SelectChild(TreeItem item) {
		if (item.getItemCount() > 0) {
			for (TreeItem t : item.getItems()) {
				t.setChecked(true);
				SelectChild(t);
			}
		}
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		subButton = createButton(parent, IDialogConstants.OK_ID, "确定",
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
			if(tabFolder.getSelectionIndex()==0){
				if(tableItem==null){
					MessageDialog.openInformation(new Shell(), "提示", "没有选择修改项或者没有选择监测器！");
					return;
				}
				if(text.getText().equals("")){
					MessageDialog.openInformation(new Shell(), "提示","修改值不能为空");
					return;
				}
				BusinessObject bo = (BusinessObject) tableItem.getData();
				bo =FileTools.CreateBo("RecId",
						bo.get_RecId(), "Ecc."
								+ bo.GetField("EccType").get_NativeValue().toString());
				bo.GetField("frequency").SetValue(
						new SiteviewValue(text.getText()));
				bo.GetField("timeUnitSelf").SetValue(
						new SiteviewValue(combo.getText()));
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			}else if(tabFolder.getSelectionIndex()==1){
				if(text_2.getText().equals("")&&text_3.getText().equals("")&&text_4.getText().equals("")){
					MessageDialog.openInformation(new Shell(), "提示", "阀值没有进行修改！");
					return;
				}
				subButton.setEnabled(false);
				TableItem[] item=table.getItems();
				if(!text_2.getText().equals("")){
					for (TableItem tableItem : item) {
						if(((BusinessObject)tableItem.getData()).GetField("EccType").get_NativeValue().toString().equals(combo_2.getText())){
							for (int i=0;i<list.size();i++) {
								String[] str=(String[]) list.get(i);
								BusinessObject bo=((BusinessObject)tableItem.getData()).GetRelationship(combo_2.getText()+"ContainsAlarm").CreateNewObject(true);
								bo.GetField("AlarmStatus").SetValue(new SiteviewValue("error"));
								bo.GetField("Operator").SetValue(new SiteviewValue(str[1]));
								bo.GetField("AlramValue").SetValue(new SiteviewValue(str[2]));
								if(str[3].equals("与")){									
									bo.GetField("isAnd").SetValue(new SiteviewValue(true));
								}else{
									bo.GetField("isAnd").SetValue(new SiteviewValue(false));
								}
								String path = FileTools.getRealPath("\\files\\MonitorReturnValveReference.properties");
								String name = Config.getReturnStr(path, "Ecc."+combo_2.getText());
								bo.GetField(name).SetValue(new SiteviewValue(WarningCondition.map.get(str[0])));
								bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
							}
						}
					}
				}
				if(!text_3.getText().equals("")){
					for (TableItem tableItem : item) {
						if(((BusinessObject)tableItem.getData()).GetField("EccType").get_NativeValue().toString().equals(combo_2.getText())){
							for (int i=0;i<list1.size();i++) {
								String[] str=(String[]) list1.get(i);
								BusinessObject bo=((BusinessObject)tableItem.getData()).GetRelationship(combo_2.getText()+"ContainsAlarm").CreateNewObject(true);
								bo.GetField("AlarmStatus").SetValue(new SiteviewValue("warning"));
								bo.GetField("Operator").SetValue(new SiteviewValue(str[1]));
								bo.GetField("AlramValue").SetValue(new SiteviewValue(str[2]));
								if(str[3].equals("与")){									
									bo.GetField("isAnd").SetValue(new SiteviewValue(true));
								}else{
									bo.GetField("isAnd").SetValue(new SiteviewValue(false));
								}
								String path = FileTools.getRealPath("\\files\\MonitorReturnValveReference.properties");
								String name = Config.getReturnStr(path, "Ecc."+combo_2.getText());
								bo.GetField(name).SetValue(new SiteviewValue(WarningCondition.map.get(str[0])));
								bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
							}
						}
					}
				}
				if(!text_4.getText().equals("")){
					for (TableItem tableItem : item) {
						if(((BusinessObject)tableItem.getData()).GetField("EccType").get_NativeValue().toString().equals(combo_2.getText())){
							for (int i=0;i<list2.size();i++) {
								String[] str=(String[]) list2.get(i);
								BusinessObject bo=((BusinessObject)tableItem.getData()).GetRelationship(combo_2.getText()+"ContainsAlarm").CreateNewObject(true);
								bo.GetField("AlarmStatus").SetValue(new SiteviewValue("good"));
								bo.GetField("Operator").SetValue(new SiteviewValue(str[1]));
								bo.GetField("AlramValue").SetValue(new SiteviewValue(str[2]));
								if(str[3].equals("与")){									
									bo.GetField("isAnd").SetValue(new SiteviewValue(true));
								}else{
									bo.GetField("isAnd").SetValue(new SiteviewValue(false));
								}
								String path = FileTools.getRealPath("\\files\\MonitorReturnValveReference.properties");
								String name = Config.getReturnStr(path, "Ecc."+combo_2.getText());
								bo.GetField(name).SetValue(new SiteviewValue(WarningCondition.map.get(str[0])));
								bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
							}
						}
					}
				}
			}else if(tabFolder.getSelectionIndex()==2){
				if(tableItem==null){
					MessageDialog.openInformation(new Shell(), "提示", "没有选择修改项或者没有选择监测器！");
					return;
				}
				if(text.getText().equals("")){
					MessageDialog.openInformation(new Shell(), "提示","修改值不能为空");
					return;
				}
				BusinessObject bo = (BusinessObject) tableItem.getData();
				bo =FileTools.CreateBo("RecId",
						bo.get_RecId(), "Ecc."
								+ bo.GetField("EccType").get_NativeValue().toString());
				bo.GetField("verifyerror").SetValue(
						new SiteviewValue(btnCheckButton.getSelection()));
				bo.GetField("verifyErrorFrequency").SetValue(
						new SiteviewValue(text_1.getText()));
				bo.GetField("ErrorFrequency").SetValue(
						new SiteviewValue(combo_1.getText()));
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			}
			subButton.setEnabled(true);
		this.close();
		} else if (buttonId == IDialogConstants.FINISH_ID) {
			BusinessObject bo = (BusinessObject) tableItem.getData();
			bo = FileTools.CreateBo("RecId",
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
