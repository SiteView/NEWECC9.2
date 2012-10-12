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

import SiteView.ecc.Activator;
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
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;

import siteview.windows.forms.ImageHelper;
import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class TaxAuthority extends Dialog {
	private String title = "用户授权";
	List<BusinessObject> bos;
	private Map<String, BusinessObject> userPermission;
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
		getPermissions("UserId", combo.getText());
//		combo.addSelectionListener(new SelectionListener() {
//
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				getPermissions("UserId", combo.getText());
////				createTreeItem();
//			}
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent e) {
//			}
//		});

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
				}else if(item.getData() instanceof MachineModle){
					flag[0] = false;
					flag[1] = false;
					flag[2] = true;
					flag[3] = true;
					stackLayOut.topControl = createEccComposite(composite_8);
					Authority();;
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
		treeViewer.expandToLevel(10);
		treeViewer.setComparer(new EccTreeComparer());

		sashForm.setWeights(new int[] { 3, 2 });
		return composite;
	}
	
	public Composite createComposite(Composite composite){
		Composite composite_8 = new Composite(composite,SWT.BORDER);
		composite_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		return composite_8;
	}
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
					BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
					bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
					bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
					bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
					if(treeItem.getText().equals("SiteViewEcc9.2")){
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("SE"));						
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("SE"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						if(treeItem.getItemCount()>0){
							createSEChaild(treeItem.getItems());
						}
					}else if(treeItem.getData() instanceof AlarmModle){
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Alarm"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Alarm"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						if(treeItem.getItemCount()>0){
							createAlarmChaild(treeItem.getItems());
						}
					}else if(treeItem.getData() instanceof MonitorSetUpModel){
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Monitor"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Monitor"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}else if(treeItem.getData() instanceof StatementsModle){
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Report"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Report"));	
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						if(treeItem.getItemCount()>0){
							createReporChaild(treeItem.getItems());
						}
					}else if(treeItem.getData() instanceof SetUpModle){
						bo.GetField("PermissionsType").SetValue(new SiteviewValue("Set"));	
						bo.GetField("PermissionsId").SetValue(new SiteviewValue("Set"));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						if(treeItem.getItemCount()>0){
							createSetChaild(treeItem.getItems());
						}
					}
				}
			}
			applyButton.setEnabled(true);
		}else if(buttonId ==IDialogConstants.CLOSE_ID){
			
		}else{
			this.close();
		}
	}
	
	//SiteViewEcc9.2的子项
	public void createSEChaild(TreeItem[] item){
		for (TreeItem treeItem : item) {
			if(treeItem.getChecked()){
				BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
				bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
				bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
				bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
				if(treeItem.getData() instanceof GroupModle){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("Group"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue(((GroupModle)treeItem.getData()).getBo().get_RecId()));
				}else if(treeItem.getData() instanceof MachineModle){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("Machine"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue(((MachineModle)treeItem.getData()).getBo().get_RecId()));	
				}
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				if(treeItem.getItemCount()>0){
					createSEChaild(treeItem.getItems());
				}
			}
			
		}
	}
	
	//设置的子项
	public void createSetChaild(TreeItem[] item){
		for (TreeItem treeItem : item) {
			if(treeItem.getChecked()){
				BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
				bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
				bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
				bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
				if(treeItem.getText().equals("邮件设置")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("Email"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("Email"));
					bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				}else if(treeItem.getText().equals("短信设置")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("SMS"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("SMS"));
					bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				}else if(treeItem.getText().equals("值班表设置")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("Duty"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("Duty"));
					bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				}else if(treeItem.getText().equals("用户管理")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("User"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("User"));
					bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				}else if(treeItem.getText().equals("任务计划")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("Task"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("Task"));
					bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					for (TreeItem treeItem2 : treeItem.getItems()) {
						if(treeItem2.getChecked()){
							BusinessObject bo1 = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
							bo1.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
							bo1.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
							bo1.GetField("ButtonType").SetValue(new SiteviewValue(false));
							if(treeItem2.getText().equals("绝对时间任务计划")){
								bo.GetField("PermissionsType").SetValue(new SiteviewValue("Absolute"));	
								bo.GetField("PermissionsId").SetValue(new SiteviewValue("Absolute"));
							}else if(treeItem2.getText().equals("时间段计划")){
								bo.GetField("PermissionsType").SetValue(new SiteviewValue("Quantum"));	
								bo.GetField("PermissionsId").SetValue(new SiteviewValue("Quantum"));
							}else if(treeItem2.getText().equals("相对时间计划")){
								bo.GetField("PermissionsType").SetValue(new SiteviewValue("Relative"));	
								bo.GetField("PermissionsId").SetValue(new SiteviewValue("Relative"));
							}
							bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
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
				BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
				bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
				bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
				bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
				if(treeItem.getText().equals("报警规则")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmRule"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmRule"));
				}else if(treeItem.getText().equals("报警策略")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmTactics"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmTactics"));	
				}else if(treeItem.getText().equals("报警日志")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("AlarmLog"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("AlarmLog"));	
				}
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			}
			
		}
	}
	
	//报告的子项
	public void createReporChaild(TreeItem[] item){
		for (TreeItem treeItem : item) {
			if(treeItem.getChecked()){
				BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("Permissions");
				bo.GetField("UserId").SetValue(new SiteviewValue(combo.getText()));
				bo.GetField("SelectPerimissions").SetValue(new SiteviewValue(true));
				bo.GetField("ButtonType").SetValue(new SiteviewValue(false));
				if(treeItem.getText().equals("趋势报告")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("Trend"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("Trend"));
				}else if(treeItem.getText().equals("对比报告")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("Ratio"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("Ratio"));	
				}else if(treeItem.getText().equals("时段对比报告")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("TimeRatio"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("TimeRatio"));	
				}else if(treeItem.getText().equals("状态统计报告")){
					bo.GetField("PermissionsType").SetValue(new SiteviewValue("Status"));	
					bo.GetField("PermissionsId").SetValue(new SiteviewValue("Status"));	
				}
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			}
			
		}
	}
	
	public List<BusinessObject> getPermissions(String key, String vlue) {
		ICollection icollection = FileTools.getBussCollection(key, vlue,
				"Permissions");
		List<BusinessObject> bos = new ArrayList<BusinessObject>();
		IEnumerator iEnumerator = icollection.GetEnumerator();
		userPermission = new HashMap<String, BusinessObject>();
		while (iEnumerator.MoveNext()) {
			BusinessObject ob = (BusinessObject) iEnumerator.get_Current();
			userPermission.put(ob.GetField("PermissionsId").get_NativeValue()
					.toString(), ob);
			bos.add(ob);
		}
		return bos;
	}
}
