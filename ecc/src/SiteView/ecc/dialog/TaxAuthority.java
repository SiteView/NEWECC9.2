package SiteView.ecc.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import SiteView.ecc.Control.EccTreeComparer;
import SiteView.ecc.Control.EccTreeContentProvider;
import SiteView.ecc.Control.EccTreeLabelProvider;
import SiteView.ecc.Modle.AlarmModle;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorSetUpModel;
import SiteView.ecc.Modle.SetUpModle;
import SiteView.ecc.Modle.StatementsModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.data.UserInfor;
import SiteView.ecc.editors.UserManager;
import SiteView.ecc.tools.FileTools;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class TaxAuthority extends Dialog {
	private String title = "用户授权";
	List<BusinessObject> list;
	private Map<String, BusinessObject> userPermission;
	private Map<String, BusinessObject> functionPermission;
	TreeItem item;
	TreeItem treeItem;
	TreeItem treeItem1;
	Combo combo;
	Button btnCheckButton;
	Button applyButton;
	Button giveupButton;
	Button closeButton;
	Button but1;
	Button but2;
	Button but3;
	Button but4;
	Button but5;
	Button but6;
	Button but7;
	Button but9;
	Button but10;
	Button but11;
	Button but12;
	Button but13;
	Button but14;
	Button but15;
	Button but16;
	Button but17;
	Button but18;
	Button but19;
	Tree tree;
	Composite composite_2;
	Composite composite_5;
	Composite composite_6;
	Composite composite_7;
	Composite composite_8;
	StackLayout stackLayOut = new StackLayout();
	boolean[] flag = new boolean[4];

	public TaxAuthority(Shell parent) {
		super(parent);
	}

	protected void configureShell(Shell newShell) {
		newShell.setSize(600, 500);
		newShell.setLocation(250, 150);
		newShell.setText(title);
		super.configureShell(newShell);
	}

	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout());

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		Composite composite_1 = new Composite(sashForm, SWT.VIRTUAL);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(new FillLayout());

		SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
		sashForm_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		btnCheckButton = new Button(composite_3, SWT.CHECK);
		btnCheckButton.setBounds(0, 0, 51, 20);
		btnCheckButton.setText("\u5168\u9009");// 全选
		btnCheckButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		btnCheckButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (btnCheckButton.getSelection()) {
					TreeItem[] items = tree.getItems();
					for (TreeItem treeItem : items) {
						treeItem.setChecked(true);
						selectTree(treeItem);
					}
				} else {
					TreeItem[] items = tree.getItems();
					for (TreeItem treeItem : items) {
						treeItem.setChecked(false);
						removeTree(treeItem);
					}
				}

			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		combo = new Combo(composite_3, SWT.NONE);
		for (int i = 0; i < UserInfor.list.size(); i++) {
			String s = UserInfor.list.get(i).getLogname();
			combo.add(s);
			if (s.equals( ((SiteView.ecc.Modle.UserModle)UserManager.tableItem
					.getData()).getLogname())) {
				combo.select(i);
			}
		}
		combo.setBounds(112, 0, 92, 20);
		list = getPermissions("UserId", combo.getText());
		combo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				removeAllTree(tree.getItems());
				list = getPermissions("UserId", combo.getText());
				createTree();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Label label = new Label(composite_3, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(70, 4, 36, 12);
		label.setText("\u7528\u6237\uFF1A");

		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_4.setVisible(true);
		composite_4.setLayout(new FillLayout());

		sashForm_1.setWeights(new int[] { 11, 217 });
		
		composite_8 =new Composite(sashForm, SWT.NONE);
		composite_8.setLayout(stackLayOut);
		stackLayOut.topControl = createEccComposite(composite_8);
	
		TreeViewer treeViewer = new TreeViewer(composite_4, SWT.BORDER | SWT.CHECK);
		tree = treeViewer.getTree();
		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				item = (TreeItem) e.item;
				if(item.getChecked()){
					SelectChild(item);
					SelectParent(item);
				}else{
					DeletChild(item);
				}
				if(item.getText().equals("SiteViewEcc9.2")){
					flag[0] = true;
					flag[1] = false;
					flag[2] = false;
					flag[3] = false;
					stackLayOut.topControl = createEccComposite(composite_8);
					Authority();
					if(userPermission.get("SE")!=null){
						but1.setSelection((Boolean) userPermission.get("SE").GetField("AddGroup").get_NativeValue());						
					}
				}else if(item.getText().equals("报警规则")){
					stackLayOut.topControl = createAlarmComposite(composite_8,"报警规则操作权限","添加报警规则","编辑报警规则","删除报警规则");
				}else if(item.getText().equals("报警策略")){
					stackLayOut.topControl = createTacticsComposite(composite_8,"报警策略操作权限","添加报警策略","编辑报警策略","删除报警策略");
				}else if(item.getData() instanceof GroupModle){
					flag[0] = false;
					flag[1] = true;
					flag[2] = false;
					flag[3] = true;
					stackLayOut.topControl = createEccComposite(composite_8); 
					Authority();
					if(userPermission.get(((GroupModle)item.getData()).getBo().get_RecId())!=null){
						String macid = ((GroupModle)item.getData()).getBo().get_RecId();
						but2.setSelection((Boolean) userPermission.get(macid)
								.GetField("AddGroup").get_NativeValue());
						but3.setSelection((Boolean) userPermission.get(macid)
								.GetField("AddMachine").get_NativeValue());
						but4.setSelection((Boolean) userPermission.get(macid)
								.GetField("EditGroup").get_NativeValue());
						but5.setSelection((Boolean) userPermission.get(macid)
								.GetField("DeleteGroup").get_NativeValue());
						but6.setSelection((Boolean) userPermission.get(macid)
								.GetField("AddMonitor").get_NativeValue());
						but11.setSelection((Boolean) userPermission.get(macid)
								.GetField("EditMonitor").get_NativeValue());
						but12.setSelection((Boolean) userPermission.get(macid)
								.GetField("DeleteMonitor")
								.get_NativeValue());
					}
				}else if(item.getData() instanceof MachineModle){
					flag[0] = false;
					flag[1] = false;
					flag[2] = true;
					flag[3] = true;
					stackLayOut.topControl = createEccComposite(composite_8);
					Authority();
					String macid = ((MachineModle)item.getData()).getBo().get_RecId();
					if(userPermission.get(macid)!=null){
						but7.setSelection((Boolean) userPermission.get(macid).GetField("AddMonitor").get_NativeValue());
						but9.setSelection((Boolean) userPermission.get(macid).GetField("EditMachine").get_NativeValue());
						but10.setSelection((Boolean) userPermission.get(macid).GetField("DeleteMachine").get_NativeValue());
						but11.setSelection((Boolean) userPermission.get(macid).GetField("EditMonitor").get_NativeValue());
						but12.setSelection((Boolean) userPermission.get(macid).GetField("DeleteMonitor").get_NativeValue());
					}
				}else{
					stackLayOut.topControl = createComposite(composite_8);
				}
				composite_8.layout();
		}
		public void widgetDefaultSelected(SelectionEvent e) {
			}	
		});
		treeViewer.setContentProvider(new EccTreeContentProvider());
		treeViewer.setLabelProvider(new EccTreeLabelProvider());
		SiteViewData s = new SiteViewData();
		treeViewer.setInput(s.getData());
		treeViewer.expandAll();
		treeViewer.setComparer(new EccTreeComparer());
		createTree();

		sashForm.setWeights(new int[] { 3, 2 });
		return composite;
	}
	
	
	public void createTree(){
		if(list.size()!=0){
			TreeItem[] item = tree.getItems();
			for (BusinessObject list1 : list) {
				if(list1.GetField("ButtonType").get_NativeValue().toString().equals("false")){					
					String id = list1.GetField("PermissionsId").get_NativeValue().toString();
					for (TreeItem treeItem : item) {
						if(treeItem.getText().equals("SiteViewEcc9.2") && id.equals("SE")){
							treeItem.setChecked(true);
							for (BusinessObject list2 : list) {								
								createSEChaild(treeItem.getItems(),list2.GetField("PermissionsId").get_NativeValue().toString());
							}
						}else if(treeItem.getData() instanceof AlarmModle && id.equals("Alarm")){
							treeItem.setChecked(true);
							for (BusinessObject list2 : list) {								
								createAlarmChaild(treeItem.getItems(),list2.GetField("PermissionsId").get_NativeValue().toString());
							}
						}else if(treeItem.getData() instanceof MonitorSetUpModel && id.equals("Monitor")){
							treeItem.setChecked(true);
						}else if(treeItem.getData() instanceof SetUpModle && id.equals("Set")){
							treeItem.setChecked(true);
							for (BusinessObject list2 : list) {								
								createSetChaild(treeItem.getItems(),list2.GetField("PermissionsId").get_NativeValue().toString());
							}
						}else if(treeItem.getData() instanceof StatementsModle && id.equals("Report")){
							treeItem.setChecked(true);
							for (BusinessObject list2 : list) {								
								createReporChaild(treeItem.getItems(),list2.GetField("PermissionsId").get_NativeValue().toString());
							}
						}
					}
				}
			}
		}
	}
	
	public void removeAllTree(TreeItem[] items){
		for (TreeItem item :items) {
			item.setChecked(false);
			if(item.getItemCount()>0){
				removeAllTree(item.getItems());
			}
		}
	}
	
	public void createSEChaild(TreeItem[] item,String id){
		for (TreeItem treeItem : item) {
			if(treeItem.getData() instanceof GroupModle){
				String id1 = ((GroupModle)treeItem.getData()).getBo().get_RecId();
				if(id1.equals(id)){
					treeItem.setChecked(true);
				}
				
			}else if(treeItem.getData() instanceof MachineModle){
				String id1 = ((MachineModle)treeItem.getData()).getBo().get_RecId();
				if(id1.equals(id)){
					treeItem.setChecked(true);
				}
			}
			if(treeItem.getItemCount()>0){
				createSEChaild(treeItem.getItems(),id);
			}
		}
	}
	
	public void createSetChaild(TreeItem[] item,String id){
		for (TreeItem treeItem : item) {
			if(treeItem.getText().equals("邮件设置") && id.equals("Email")){
				treeItem.setChecked(true);
			}else if(treeItem.getText().equals("短信设置") && id.equals("SMS")){
				treeItem.setChecked(true);
			}else if(treeItem.getText().equals("值班表设置") && id.equals("Duty")){
				treeItem.setChecked(true);
			}else if(treeItem.getText().equals("用户管理") && id.equals("User")){
				treeItem.setChecked(true);
			}else if(treeItem.getText().equals("任务计划") && id.equals("Task")){
				treeItem.setChecked(true);
				for (TreeItem treeItem1 : treeItem.getItems()) {
					for (BusinessObject list1 : list) {
						String id1 = list1.GetField("PermissionsId").get_NativeValue().toString();
						if(treeItem1.getText().equals("绝对时间任务计划") && id1.equals("Absolute")){
							treeItem1.setChecked(true);
						}else if(treeItem1.getText().equals("时间段计划") && id1.equals("Quantum")){
							treeItem1.setChecked(true);
						}else if(treeItem1.getText().equals("相对时间计划") && id1.equals("Relative")){
							treeItem1.setChecked(true);
						}
					}
				}
			}
		}
	}
	
	public void createAlarmChaild(TreeItem[] item,String id){
		for (TreeItem treeItem : item) {
			if(treeItem.getText().equals("报警规则") && id.equals("AlarmRule")){
				treeItem.setChecked(true);
			}else if(treeItem.getText().equals("报警策略") && id.equals("AlarmTactics")){
				treeItem.setChecked(true);	
			}else if(treeItem.getText().equals("报警日志") && id.equals("AlarmLog")){
				treeItem.setChecked(true);
			}
		}
	}
	
	public void createReporChaild(TreeItem[] item,String id){
		for (TreeItem treeItem : item) {
			if(treeItem.getText().equals("趋势报告") && id.equals("Trend")){
				treeItem.setChecked(true);
			}else if(treeItem.getText().equals("对比报告") && id.equals("Ratio")){
				treeItem.setChecked(true);
			}else if(treeItem.getText().equals("时段对比报告") && id.equals("TimeRatio")){
				treeItem.setChecked(true);
			}else if(treeItem.getText().equals("状态统计报告") && id.equals("Status")){
				treeItem.setChecked(true);
			}
		}
	}
	
	//对应的空白面板
	public Composite createComposite(Composite composite){
		Composite composite_8 = new Composite(composite,SWT.BORDER);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		return composite_8;
	}
	
	//报警规则对应的右边面板
	public Composite createAlarmComposite(Composite composite,String name1,String name2,String name3,String name4){
		Composite composite_8 = new Composite(composite,SWT.BORDER);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Label label= new Label(composite_8, SWT.NONE);
		label.setText(name1);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 10, 240, 15);
		but14 = new Button(composite_8, SWT.CHECK);
		but14.setText(name2);
		but14.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but14.setBounds(10, 30, 93, 16);
		
		but15 = new Button(composite_8, SWT.CHECK);
		but15.setText(name3);
		but15.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but15.setBounds(10, 65, 93, 16);
		
		but16 = new Button(composite_8, SWT.CHECK);
		but16.setText(name4);
		but16.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but16.setBounds(10, 100, 93, 16);
		return composite_8;
	}
	
	//报警策略对应的右边面板
	public Composite createTacticsComposite(Composite composite,String name1,String name2,String name3,String name4){
		Composite composite_8 = new Composite(composite,SWT.BORDER);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Label label= new Label(composite_8, SWT.NONE);
		label.setText(name1);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 10, 240, 15);
		but17 = new Button(composite_8, SWT.CHECK);
		but17.setText(name2);
		but17.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but17.setBounds(10, 30, 93, 16);
		
		but18 = new Button(composite_8, SWT.CHECK);
		but18.setText(name3);
		but18.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but18.setBounds(10, 65, 93, 16);
		
		but19 = new Button(composite_8, SWT.CHECK);
		but19.setText(name4);
		but19.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but19.setBounds(10, 100, 93, 16);
		return composite_8;
	}

	//siteviewecc9.2对应的右边面板
	public Composite createEccComposite(Composite composite){
		Composite composite_8 = new Composite(composite,SWT.BORDER);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_2 = new SashForm(composite_8, SWT.VERTICAL);
		sashForm_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		composite_2 = new Composite(sashForm_2, SWT.BORDER);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setToolTipText("SE权限");
		composite_2.setLayout(new FormLayout());

		but1 = new Button(composite_2, SWT.CHECK);
		FormData fd_btnCheckButton_1 = new FormData();
		fd_btnCheckButton_1.top = new FormAttachment(0, 10);
		fd_btnCheckButton_1.left = new FormAttachment(0, 10);
		but1.setLayoutData(fd_btnCheckButton_1);
		but1.setText("\u6DFB\u52A0\u7EC4");
		but1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		composite_5 = new Composite(sashForm_2, SWT.BORDER);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_5.setToolTipText("组权限");

		but2 = new Button(composite_5, SWT.CHECK);
		but2.setBounds(10, 10, 93, 16);
		but2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but2.setText("\u6DFB\u52A0\u5B50\u7EC4");

		but3 = new Button(composite_5, SWT.CHECK);
		but3.setBounds(10, 40, 93, 16);
		but3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but3.setText("\u6DFB\u52A0\u8BBE\u5907");

		but4 = new Button(composite_5, SWT.CHECK);
		but4.setBounds(10, 69, 93, 16);
		but4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but4.setText("\u7F16\u8F91\u7EC4");

		but5 = new Button(composite_5, SWT.CHECK);
		but5.setBounds(10, 101, 93, 16);
		but5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but5.setText("\u5220\u9664\u7EC4");

		but6 = new Button(composite_5, SWT.CHECK);
		but6.setBounds(10, 131, 93, 16);
		but6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but6.setText("\u6DFB\u52A0\u76D1\u6D4B\u5668");

		composite_6 = new Composite(sashForm_2, SWT.BORDER);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		but7 = new Button(composite_6, SWT.CHECK);
		but7.setBounds(10, 10, 93, 16);
		but7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but7.setText("\u6DFB\u52A0\u76D1\u6D4B\u5668");

		but9 = new Button(composite_6, SWT.CHECK);
		but9.setBounds(10, 40, 93, 16);
		but9.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but9.setText("\u7F16\u8F91\u8BBE\u5907");

		but10 = new Button(composite_6, SWT.CHECK);
		but10.setBounds(10, 70, 93, 16);
		but10.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but10.setText("\u5220\u9664\u8BBE\u5907");

		composite_7 = new Composite(sashForm_2, SWT.BORDER);
		composite_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_7.setToolTipText("监测器权限");

		but11 = new Button(composite_7, SWT.CHECK);
		but11.setBounds(10, 10, 93, 16);
		but11.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but11.setText("\u7F16\u8F91\u76D1\u6D4B\u5668");

		but12 = new Button(composite_7, SWT.CHECK);
		but12.setBounds(10, 44, 93, 16);
		but12.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but12.setText("\u5220\u9664\u76D1\u6D4B\u5668");

		but13 = new Button(composite_7, SWT.CHECK);
		but13.setBounds(10, 77, 93, 16);
		but13.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		but13.setText("\u5237\u65B0\u76D1\u6D4B\u5668");
		sashForm_2.setWeights(new int[] { 40, 163, 110, 110 });
		return composite_8;
	}
	// 点击全选按钮的时候选中
	private void selectTree(TreeItem treeItem) {
		TreeItem[] treeitem1 = treeItem.getItems();
		for (TreeItem treeItem2 : treeitem1) {
			treeItem2.setChecked(true);
			selectTree(treeItem2);
		}
	}

	// 取消全选按钮的时候取消全选
	private void removeTree(TreeItem treeItem) {
		TreeItem[] treeitem1 = treeItem.getItems();
		for (TreeItem treeItem2 : treeitem1) {
			treeItem2.setChecked(false);
			removeTree(treeItem2);
		}
	}
	
	private void Authority() {
		for (Control c : composite_2.getChildren()) {
			c.setEnabled(flag[0]);
		}
		for (Control c : composite_5.getChildren()) {
			c.setEnabled(flag[1]);
		}
		for (Control c : composite_6.getChildren()) {
			c.setEnabled(flag[2]);
		}
		for (Control c : composite_7.getChildren()) {
			c.setEnabled(flag[3]);
		}
	}

	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		applyButton = createButton(parent, IDialogConstants.OK_ID,
				"授予勾选节点", true);
		giveupButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"关闭", false);
		closeButton = createButton(parent, IDialogConstants.CLOSE_ID,
				"授予勾选功能", true);
	}

	private void SelectParent(TreeItem item) {
		if (item.getParentItem() != null) {
			TreeItem treeItem = item.getParentItem();
			treeItem.setChecked(true);
			SelectParent(treeItem);
		}
	}

	protected void SelectChild(TreeItem item) {
		if (item.getItemCount() > 0) {
			for (TreeItem t : item.getItems()) {
				t.setChecked(true);
				SelectChild(t);
			}
		}
	}

	protected void DeletChild(TreeItem item) {
		if (item.getItemCount() > 0) {
			for (TreeItem t : item.getItems()) {
				t.setChecked(false);
				DeletChild(t);
			}
		}
	}

	protected void buttonPressed(int buttonId) {
		if(buttonId == IDialogConstants.OK_ID){
			applyButton.setEnabled(false);
			TreeItem[] items = tree.getItems();
			for (TreeItem treeItem : items) {
				if(treeItem.getChecked()){
					if(treeItem.getText().equals("SiteViewEcc9.2")){
						if(functionPermission.get("SE")!=null){
							BusinessObject bo1 = functionPermission.get("SE");
							bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo1.GetField("PermissionsType").SetValue(new SiteviewValue("SE"));						
							bo1.GetField("PermissionsId").SetValue(new SiteviewValue("SE"));
							bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}else{
							BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
							bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo.GetField("PermissionsType").SetValue(new SiteviewValue("SE"));						
							bo.GetField("PermissionsId").SetValue(new SiteviewValue("SE"));
							bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);							
						}
						createSEChaild(treeItem.getItems());
					}else if(treeItem.getData() instanceof AlarmModle){
						if(functionPermission.get("Alarm")!=null){
							BusinessObject bo1 = functionPermission.get("Alarm");
							bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Alarm"));	
							bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Alarm"));
							bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}else{
							BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
							bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo.GetField("PermissionsType").SetValue(new SiteviewValue("Alarm"));	
							bo.GetField("PermissionsId").SetValue(new SiteviewValue("Alarm"));
							bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);							
						}
						createAlarmChaild(treeItem.getItems());
					}else if(treeItem.getData() instanceof MonitorSetUpModel){
						if(functionPermission.get("Monitor")!=null){
							BusinessObject bo1 = functionPermission.get("Monitor");
							bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Monitor"));	
							bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Monitor"));
							bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}else{							
							BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
							bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo.GetField("PermissionsType").SetValue(new SiteviewValue("Monitor"));	
							bo.GetField("PermissionsId").SetValue(new SiteviewValue("Monitor"));
							bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}
					}else if(treeItem.getData() instanceof StatementsModle){
						if(functionPermission.get("Report")!=null){
							BusinessObject bo1 = functionPermission.get("Report");
							bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Report"));	
							bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Report"));	
							bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}else{							
							BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
							bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo.GetField("PermissionsType").SetValue(new SiteviewValue("Report"));	
							bo.GetField("PermissionsId").SetValue(new SiteviewValue("Report"));	
							bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}
						createReporChaild(treeItem.getItems());
					}else if(treeItem.getData() instanceof SetUpModle){
						if(functionPermission.get("Set")!=null){
							BusinessObject bo1 = functionPermission.get("Set");
							bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Set"));	
							bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Set"));
							bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}else{							
							BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
							bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
							bo.GetField("PermissionsType").SetValue(new SiteviewValue("Set"));	
							bo.GetField("PermissionsId").SetValue(new SiteviewValue("Set"));
							bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}
						createSetChaild(treeItem.getItems());
					}
				}else{
					if(treeItem.getText().equals("SiteViewEcc9.2")){
						if(functionPermission.get("SE")!=null){
							BusinessObject bo1 = functionPermission.get("SE");
							bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
						}
						createSEChaild(treeItem.getItems());
					}else if(treeItem.getData() instanceof AlarmModle){
						if(functionPermission.get("Alarm")!=null){
							BusinessObject bo1 = functionPermission.get("Alarm");
							bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
						}
						createAlarmChaild(treeItem.getItems());
					}else if(treeItem.getData() instanceof MonitorSetUpModel){
						if(functionPermission.get("Monitor")!=null){
							BusinessObject bo1 = functionPermission.get("Monitor");
							bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
						}
					}else if(treeItem.getData() instanceof StatementsModle){
						if(functionPermission.get("Report")!=null){
							BusinessObject bo1 = functionPermission.get("Report");
							bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
						}
						createReporChaild(treeItem.getItems());
					}else if(treeItem.getData() instanceof SetUpModle){
						if(functionPermission.get("Set")!=null){
							BusinessObject bo1 = functionPermission.get("Set");
							bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
						}
						createSetChaild(treeItem.getItems());
					}
				}
			}
			applyButton.setEnabled(true);
			this.close();
		}else if(buttonId ==IDialogConstants.CLOSE_ID){
			closeButton.setEnabled(false);
			TreeItem[] items = tree.getItems();
			for (TreeItem treeItem : items) {
				if(treeItem.getData() instanceof AlarmModle){
					if(treeItem.getChecked()){
						if(userPermission.get("Alarm")!=null){
							BusinessObject bo1 = userPermission.get("Alarm");
							bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo1.GetField("ButtonType").SetValue(new SiteviewValue(true));
							bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Alarm"));	
							bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Alarm"));
							bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}else{							
							BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
							bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo.GetField("ButtonType").SetValue(new SiteviewValue(true));
							bo.GetField("PermissionsType").SetValue(new SiteviewValue("Alarm"));	
							bo.GetField("PermissionsId").SetValue(new SiteviewValue("Alarm"));
							bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}
						createFunctionAlarm(treeItem.getItems());
					}else{
						if(userPermission.get("Alarm")!=null){
							BusinessObject bo1 = userPermission.get("Alarm");
							bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
						}
						createFunctionAlarm(treeItem.getItems());
					}
				}else if(treeItem.getText().equals("SiteViewEcc9.2")){
					if(treeItem.getChecked()){
						if(userPermission.get("SE")!=null){
							BusinessObject bo1 = userPermission.get("SE");
							bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo1.GetField("ButtonType").SetValue(new SiteviewValue(true));
							bo1.GetField("PermissionsType").SetValue(new SiteviewValue("SE"));	
							bo1.GetField("PermissionsId").SetValue(new SiteviewValue("SE"));
							bo1.GetField("AddGroup").SetValue(new SiteviewValue(but1.getSelection()));
							bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}else{							
							BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
							bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo.GetField("ButtonType").SetValue(new SiteviewValue(true));
							bo.GetField("PermissionsType").SetValue(new SiteviewValue("SE"));	
							bo.GetField("PermissionsId").SetValue(new SiteviewValue("SE"));
							bo.GetField("AddGroup").SetValue(new SiteviewValue(but1.getSelection()));
							bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						}
						createFunctionSE(treeItem.getItems());
					}else{
						if(userPermission.get("SE")!=null){
							BusinessObject bo1 = userPermission.get("SE");
							bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
						}
						createFunctionSE(treeItem.getItems());
					}
				}
			}
			closeButton.setEnabled(true);
			this.close();
		}else{
			this.close();
		}
	}
	//SiteViewEcc9.2的功能子项
	public void createFunctionSE(TreeItem[] item1){
		for (TreeItem treeItem : item1) {
			if(treeItem.getChecked()){
				if(treeItem.getData() instanceof GroupModle){
					if(userPermission.get(((GroupModle)treeItem.getData()).getBo().get_RecId())!=null){
						BusinessObject bo1 = userPermission.get(((GroupModle)treeItem.getData()).getBo().get_RecId());
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(true));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Group"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue(((GroupModle)treeItem.getData()).getBo().get_RecId()));
						bo1.GetField("AddGroup").SetValue(new SiteviewValue(but2.getSelection()));
						bo1.GetField("AddMachine").SetValue(new SiteviewValue(but3.getSelection()));
						bo1.GetField("EditGroup").SetValue(new SiteviewValue(but4.getSelection()));
						bo1.GetField("DeleteGroup").SetValue(new SiteviewValue(but5.getSelection()));
						bo1.GetField("AddMonitor").SetValue(new SiteviewValue(but6.getSelection()));
						if(item.getData() instanceof GroupModle){
							bo1.GetField("EditMonitor").SetValue(new SiteviewValue(but11.getSelection()));
							bo1.GetField("DeleteMonitor").SetValue(new SiteviewValue(but12.getSelection()));
						}else{
							bo1.GetField("EditMonitor").SetValue(new SiteviewValue(false));
							bo1.GetField("DeleteMonitor").SetValue(new SiteviewValue(false));						
						}
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(true));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Group"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue(((GroupModle)treeItem.getData()).getBo().get_RecId()));
						bo.GetField("AddGroup").SetValue(new SiteviewValue(but2.getSelection()));
						bo.GetField("AddMachine").SetValue(new SiteviewValue(but3.getSelection()));
						bo.GetField("EditGroup").SetValue(new SiteviewValue(but4.getSelection()));
						bo.GetField("DeleteGroup").SetValue(new SiteviewValue(but5.getSelection()));
						bo.GetField("AddMonitor").SetValue(new SiteviewValue(but6.getSelection()));
						if(item.getData() instanceof GroupModle){
							bo.GetField("EditMonitor").SetValue(new SiteviewValue(but11.getSelection()));
							bo.GetField("DeleteMonitor").SetValue(new SiteviewValue(but12.getSelection()));
						}else{
							bo.GetField("EditMonitor").SetValue(new SiteviewValue(false));
							bo.GetField("DeleteMonitor").SetValue(new SiteviewValue(false));						
						}
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getData() instanceof MachineModle){
					if(userPermission.get(((MachineModle)treeItem.getData()).getBo().get_RecId())!=null){
						BusinessObject bo1 = userPermission.get(((MachineModle)treeItem.getData()).getBo().get_RecId());
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(true));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Machine"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue(((MachineModle)treeItem.getData()).getBo().get_RecId()));	
						bo1.GetField("AddMonitor").SetValue(new SiteviewValue(but7.getSelection()));
						bo1.GetField("EditMachine").SetValue(new SiteviewValue(but9.getSelection()));
						bo1.GetField("DeleteMachine").SetValue(new SiteviewValue(but10.getSelection()));
						if(item.getData() instanceof MachineModle){
							bo1.GetField("EditMonitor").SetValue(new SiteviewValue(but11.getSelection()));
							bo1.GetField("DeleteMonitor").SetValue(new SiteviewValue(but12.getSelection()));
						}else{
							bo1.GetField("EditMonitor").SetValue(new SiteviewValue(false));
							bo1.GetField("DeleteMonitor").SetValue(new SiteviewValue(false));						
						}
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(true));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Machine"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue(((MachineModle)treeItem.getData()).getBo().get_RecId()));	
						bo.GetField("AddMonitor").SetValue(new SiteviewValue(but7.getSelection()));
						bo.GetField("EditMachine").SetValue(new SiteviewValue(but9.getSelection()));
						bo.GetField("DeleteMachine").SetValue(new SiteviewValue(but10.getSelection()));
						if(item.getData() instanceof MachineModle){
							bo.GetField("EditMonitor").SetValue(new SiteviewValue(but11.getSelection()));
							bo.GetField("DeleteMonitor").SetValue(new SiteviewValue(but12.getSelection()));
						}else{
							bo.GetField("EditMonitor").SetValue(new SiteviewValue(false));
							bo.GetField("DeleteMonitor").SetValue(new SiteviewValue(false));						
						}
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}
			}else{
				if(treeItem.getData() instanceof GroupModle){
					if(userPermission.get(((GroupModle)treeItem.getData()).getBo().get_RecId())!=null){
						BusinessObject bo1 = userPermission.get(((GroupModle)treeItem.getData()).getBo().get_RecId());
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getData() instanceof MachineModle){
					if(userPermission.get(((MachineModle)treeItem.getData()).getBo().get_RecId())!=null){
						BusinessObject bo1 = userPermission.get(((MachineModle)treeItem.getData()).getBo().get_RecId());
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}
			}
			if(treeItem.getItemCount()>0){
				createFunctionSE(treeItem.getItems());
			}
		}
	}
	//报警的功能子项
	public void createFunctionAlarm(TreeItem[] item){
		for (TreeItem treeItem : item) {
			if(treeItem.getText().equals("报警规则")){
				if(treeItem.getChecked()){
					if(userPermission.get("AlarmRule")!=null){
						BusinessObject bo1 = userPermission.get("AlarmRule");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(true));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmRule"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmRule"));
						bo1.GetField("AddAlarmRule").SetValue(new SiteviewValue(but14.getSelection()));
						bo1.GetField("EditAlarmRule").SetValue(new SiteviewValue(but15.getSelection()));
						bo1.GetField("DeleteAlarmRule").SetValue(new SiteviewValue(but16.getSelection()));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(true));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmRule"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmRule"));
						bo.GetField("AddAlarmRule").SetValue(new SiteviewValue(but14.getSelection()));
						bo.GetField("EditAlarmRule").SetValue(new SiteviewValue(but15.getSelection()));
						bo.GetField("DeleteAlarmRule").SetValue(new SiteviewValue(but16.getSelection()));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else{
					if(userPermission.get("AlarmRule")!=null){
						BusinessObject bo1 = userPermission.get("AlarmRule");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}
			}else if(treeItem.getText().equals("报警策略")){
				if(treeItem.getChecked()){
					if(userPermission.get("AlarmTactics")!=null){
						BusinessObject bo1 = userPermission.get("AlarmTactics");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(true));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmTactics"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmTactics"));
						bo1.GetField("AddAlarmTactics").SetValue(new SiteviewValue(but17.getSelection()));
						bo1.GetField("EditAlarmTactics").SetValue(new SiteviewValue(but18.getSelection()));
						bo1.GetField("DeleteAlarmTactics").SetValue(new SiteviewValue(but19.getSelection()));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(true));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmTactics"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmTactics"));
						bo.GetField("AddAlarmTactics").SetValue(new SiteviewValue(but17.getSelection()));
						bo.GetField("EditAlarmTactics").SetValue(new SiteviewValue(but18.getSelection()));
						bo.GetField("DeleteAlarmTactics").SetValue(new SiteviewValue(but19.getSelection()));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else{
					if(userPermission.get("AlarmTactics")!=null){
						BusinessObject bo1 = userPermission.get("AlarmTactics");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}
			}
		}
	}
	//SiteViewEcc9.2的子项
	public void createSEChaild(TreeItem[] item){
		for (TreeItem treeItem : item) {
			if(treeItem.getChecked()){
				if(treeItem.getData() instanceof GroupModle){
					if(functionPermission.get(((GroupModle)treeItem.getData()).getBo().get_RecId())!=null){
						BusinessObject bo1 = functionPermission.get(((GroupModle)treeItem.getData()).getBo().get_RecId());
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Group"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue(((GroupModle)treeItem.getData()).getBo().get_RecId()));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Group"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue(((GroupModle)treeItem.getData()).getBo().get_RecId()));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getData() instanceof MachineModle){
					if(functionPermission.get(((MachineModle)treeItem.getData()).getBo().get_RecId())!=null){
						BusinessObject bo1 = functionPermission.get(((MachineModle)treeItem.getData()).getBo().get_RecId());
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Machine"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue(((MachineModle)treeItem.getData()).getBo().get_RecId()));	
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Machine"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue(((MachineModle)treeItem.getData()).getBo().get_RecId()));	
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}
			}else{
				if(treeItem.getData() instanceof GroupModle){
					if(functionPermission.get(((GroupModle)treeItem.getData()).getBo().get_RecId())!=null){
						BusinessObject bo1 = functionPermission.get(((GroupModle)treeItem.getData()).getBo().get_RecId());
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getData() instanceof MachineModle){
					if(functionPermission.get(((MachineModle)treeItem.getData()).getBo().get_RecId())!=null){
						BusinessObject bo1 = functionPermission.get(((MachineModle)treeItem.getData()).getBo().get_RecId());
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}
			}
			if(treeItem.getItemCount()>0){
				createSEChaild(treeItem.getItems());
			}
		}
	}
	//设置的子项
	public void createSetChaild(TreeItem[] item){
		for (TreeItem treeItem : item) {
			if(treeItem.getChecked()){
				if(treeItem.getText().equals("邮件设置")){
					if(functionPermission.get("Email")!=null){
						BusinessObject bo1 = functionPermission.get("Email");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Email"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Email"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Email"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Email"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getText().equals("短信设置")){
					if(functionPermission.get("SMS")!=null){
						BusinessObject bo1 = functionPermission.get("SMS");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("SMS"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("SMS"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("SMS"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("SMS"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getText().equals("值班表设置")){
					if(functionPermission.get("Duty")!=null){
						BusinessObject bo1 = functionPermission.get("Duty");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Duty"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Duty"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Duty"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Duty"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getText().equals("用户管理")){
					if(functionPermission.get("User")!=null){
						BusinessObject bo1 = functionPermission.get("User");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("User"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("User"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("User"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("User"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getText().equals("任务计划")){
					if(functionPermission.get("Task")!=null){
						BusinessObject bo1 = functionPermission.get("Task");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Task"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Task"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Task"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Task"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
					for (TreeItem treeItem2 : treeItem.getItems()) {
						if(treeItem2.getChecked()){
							if(treeItem2.getText().equals("绝对时间任务计划")){
								if(functionPermission.get("Absolute")!=null){
									BusinessObject bo2 = functionPermission.get("Absolute");
									bo2.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
									bo2.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
									bo2.GetField("ButtonType").SetValue(new SiteviewValue(false));
									bo2.GetField("PermissionsType").SetValue(new SiteviewValue("Absolute"));	
									bo2.GetField("PermissionsId").SetValue(new SiteviewValue("Absolute"));
									bo2.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
								}else{									
									BusinessObject bo1 = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
									bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
									bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
									bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
									bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Absolute"));	
									bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Absolute"));
									bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
								}
							}else if(treeItem2.getText().equals("时间段计划")){
								if(functionPermission.get("Quantum")!=null){
									BusinessObject bo2 = functionPermission.get("Quantum");
									bo2.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
									bo2.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
									bo2.GetField("ButtonType").SetValue(new SiteviewValue(false));
									bo2.GetField("PermissionsType").SetValue(new SiteviewValue("Quantum"));	
									bo2.GetField("PermissionsId").SetValue(new SiteviewValue("Quantum"));
									bo2.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
								}else{									
									BusinessObject bo1 = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
									bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
									bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
									bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
									bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Quantum"));	
									bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Quantum"));
									bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
								}
							}else if(treeItem2.getText().equals("相对时间计划")){
								if(functionPermission.get("Relative")!=null){
									BusinessObject bo2 = functionPermission.get("Relative");
									bo2.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
									bo2.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
									bo2.GetField("ButtonType").SetValue(new SiteviewValue(false));
									bo2.GetField("PermissionsType").SetValue(new SiteviewValue("Relative"));	
									bo2.GetField("PermissionsId").SetValue(new SiteviewValue("Relative"));
									bo2.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
								}else{									
									BusinessObject bo1 = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
									bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
									bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
									bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
									bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Relative"));	
									bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Relative"));
									bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
								}
							}
						}
					}
				}
				
			}else{
				if(treeItem.getText().equals("邮件设置")){
					if(functionPermission.get("Email")!=null){
						BusinessObject bo1 = functionPermission.get("Email");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getText().equals("短信设置")){
					if(functionPermission.get("SMS")!=null){
						BusinessObject bo1 = functionPermission.get("SMS");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getText().equals("值班表设置")){
					if(functionPermission.get("Duty")!=null){
						BusinessObject bo1 = functionPermission.get("Duty");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getText().equals("用户管理")){
					if(functionPermission.get("User")!=null){
						BusinessObject bo1 = functionPermission.get("User");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getText().equals("任务计划")){
					if(functionPermission.get("Task")!=null){
						BusinessObject bo1 = functionPermission.get("Task");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
					for (TreeItem treeItem2 : treeItem.getItems()) {
						if(treeItem2.getChecked()){
							if(treeItem2.getText().equals("绝对时间任务计划")){
								if(functionPermission.get("Absolute")!=null){
									BusinessObject bo2 = functionPermission.get("Absolute");
									bo2.DeleteObject(ConnectionBroker.get_SiteviewApi());
								}
							}else if(treeItem2.getText().equals("时间段计划")){
								if(functionPermission.get("Quantum")!=null){
									BusinessObject bo2 = functionPermission.get("Quantum");
									bo2.DeleteObject(ConnectionBroker.get_SiteviewApi());
								}
							}else if(treeItem2.getText().equals("相对时间计划")){
								if(functionPermission.get("Relative")!=null){
									BusinessObject bo2 = functionPermission.get("Relative");
									bo2.DeleteObject(ConnectionBroker.get_SiteviewApi());
								}
							}
						}
					}
				}
			}
		}
	}
	//报警的子项
	public void createAlarmChaild(TreeItem[] item){
		for (TreeItem treeItem : item) {
			if(treeItem.getChecked()){
				if(treeItem.getText().equals("报警规则")){
					if(functionPermission.get("AlarmRule")!=null){
						BusinessObject bo1 = functionPermission.get("AlarmRule");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmRule"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmRule"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmRule"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmRule"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getText().equals("报警策略")){
					if(functionPermission.get("AlarmTactics")!=null){
						BusinessObject bo1 = functionPermission.get("AlarmTactics");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmTactics"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmTactics"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmTactics"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmTactics"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getText().equals("报警日志")){
					if(functionPermission.get("AlarmLog")!=null){
						BusinessObject bo1 = functionPermission.get("AlarmLog");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmLog"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmLog"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmLog"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmLog"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}
			}else{
				if(treeItem.getText().equals("报警规则")){
					if(functionPermission.get("AlarmRule")!=null){
						BusinessObject bo1 = functionPermission.get("AlarmRule");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getText().equals("报警策略")){
					if(functionPermission.get("AlarmTactics")!=null){
						BusinessObject bo1 = functionPermission.get("AlarmTactics");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getText().equals("报警日志")){
					if(functionPermission.get("AlarmLog")!=null){
						BusinessObject bo1 = functionPermission.get("AlarmLog");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}
			}
			
		}
	}
	//报告的子项
	public void createReporChaild(TreeItem[] item){
		for (TreeItem treeItem : item) {
			if(treeItem.getChecked()){
				if(treeItem.getText().equals("趋势报告")){
					if(functionPermission.get("Trend")!=null){
						BusinessObject bo1 = functionPermission.get("Trend");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Trend"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Trend"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Trend"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Trend"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getText().equals("对比报告")){
					if(functionPermission.get("Ratio")!=null){
						BusinessObject bo1 = functionPermission.get("Ratio");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Ratio"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Ratio"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Ratio"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Ratio"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getText().equals("时段对比报告")){
					if(functionPermission.get("TimeRatio")!=null){
						BusinessObject bo1 = functionPermission.get("TimeRatio");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("TimeRatio"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("TimeRatio"));
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("TimeRatio"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("TimeRatio"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}else if(treeItem.getText().equals("状态统计报告")){
					if(functionPermission.get("Status")!=null){
						BusinessObject bo1 = functionPermission.get("Status");
						bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo1.GetField("PermissionsType").SetValue(new SiteviewValue("Status"));	
						bo1.GetField("PermissionsId").SetValue(new SiteviewValue("Status"));	
						bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else{						
						BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
						bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
						bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
						bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Status"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Status"));	
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
				}
			}else{
				if(treeItem.getText().equals("趋势报告")){
					if(functionPermission.get("Trend")!=null){
						BusinessObject bo1 = functionPermission.get("Trend");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getText().equals("对比报告")){
					if(functionPermission.get("Ratio")!=null){
						BusinessObject bo1 = functionPermission.get("Ratio");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getText().equals("时段对比报告")){
					if(functionPermission.get("TimeRatio")!=null){
						BusinessObject bo1 = functionPermission.get("TimeRatio");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}else if(treeItem.getText().equals("状态统计报告")){
					if(functionPermission.get("Status")!=null){
						BusinessObject bo1 = functionPermission.get("Status");
						bo1.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
				}
			}
			
		}
	}
	
	public List<BusinessObject> getPermissions(String key, String vlue) {
		ICollection icollection = FileTools.getBussCollection(key, vlue,
				"Permissions");
		List<BusinessObject> bos = new ArrayList<BusinessObject>();
		IEnumerator iEnumerator = icollection.GetEnumerator();
		userPermission = new HashMap<String, BusinessObject>();
		functionPermission = new HashMap<String, BusinessObject>();
		while (iEnumerator.MoveNext()) {
			BusinessObject ob = (BusinessObject) iEnumerator.get_Current();
			if(ob.GetField("ButtonType").get_NativeValue().toString().equals("true")){
				userPermission.put(ob.GetField("PermissionsId").get_NativeValue().toString(), ob);				
			}else{
				functionPermission.put(ob.GetField("PermissionsId").get_NativeValue().toString(), ob);
			}
			bos.add(ob);
		}
		return bos;
	}
}
