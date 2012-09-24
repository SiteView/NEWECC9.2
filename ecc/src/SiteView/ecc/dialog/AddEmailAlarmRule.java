package SiteView.ecc.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Group;

import SiteView.ecc.Control.GroupTreeContentProvider;
import SiteView.ecc.Control.GroupTreeLabelProvider;
import SiteView.ecc.Modle.AlarmRuleInfo;
import SiteView.ecc.Modle.EmailModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.data.TableDutyInfor;
import SiteView.ecc.editors.AlarmRule;
import SiteView.ecc.editors.EmailSetUp;
import SiteView.ecc.tools.FileTools;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class AddEmailAlarmRule extends Dialog {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private static Text text_4;
	private static Text text_5;
	private static Text text_6;
	private static Text text_8;
	private static Text text_9;
	private static Text text_10;
	private static Text text_13;
	public static List<EmailModle> list = EmailSetUp.list;
	public static List<BusinessObject> mailmodels = MailModleSetUp.modles;
	public static List<TableModle> duty = TableDutyInfor.list;
	public static List<MachineModle> service;
	public static List<BusinessObject> messagemodels;
	private String name;
	Combo combo;// 报警事件
	Button button;// 连续发送按钮
	Button button_1;// 只发一次
	Button btnRadioButton;// 选择发送
	Tree tree;

	public AddEmailAlarmRule(Shell parentShell, String name) {
		super(parentShell);
		this.name = name;
	}

	protected void configureShell(Shell newShell) {
		newShell.setSize(650, 600);
		newShell.setLocation(280, 100);
		if (name == "email") {
			newShell.setText("添加Email报警");
		} else if (name == "SMS") {
			newShell.setText("添加短信报警");
		} else if (name == "script") {
			newShell.setText("添加脚本报警");
		} else if (name == "sound") {
			newShell.setText("添加声音报警");
		}
		super.configureShell(newShell);
	}

	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));

		Group group = new Group(sashForm, SWT.NONE);
		group.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		group.setText("\u9009\u62E9\u62A5\u8B66\u8303\u56F4");
		group.setLayout(new FillLayout());
		TreeViewer treeViewer = new TreeViewer(group, SWT.CHECK);
		tree = treeViewer.getTree();
		tree.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		tree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem item=(TreeItem) e.item;
				if(item.getChecked()){
					SelectChild(item);
					SelectParent(item);
				}else{
					DeletChild(item);
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
		sashForm_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));

		if (name == "email") {
			createEmailGroup(sashForm_1);
		} else if (name == "SMS") {
			createMessageGroup(sashForm_1);
		} else if (name == "script") {
			createScriptGroup(sashForm_1);
		} else if (name == "sound") {
			createSoundGroup(sashForm_1);
		}

		Group group_1 = new Group(sashForm_1, SWT.NONE);
		group_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		group_1.setText("\u53D1\u9001\u6761\u4EF6");

		Label label = new Label(group_1, SWT.NONE);
		label.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label.setBounds(10, 24, 54, 12);
		label.setText("\u62A5\u8B66\u4E8B\u4EF6");

		combo = new Combo(group_1, SWT.NONE);
		combo.setBounds(70, 21, 86, 20);
		combo.add("危险");
		combo.add("错误");
		combo.select(0);

		button = new Button(group_1, SWT.RADIO);
		button.setBounds(10, 52, 160, 16);
		button.setText("\u8FDE\u7EED\u4E0D\u65AD\u53D1\u9001\u8B66\u544A");
		button.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));

		Label label_1 = new Label(group_1, SWT.NONE);
		label_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_1.setBounds(10, 74, 78, 12);
		label_1.setText("\u603B\u662F\u53D1\u9001,\u4ECE\u7B2C");

		text = new Text(group_1, SWT.BORDER);
		text.setBounds(90, 71, 43, 18);
		text.setText("1");
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});

		Label lblNewLabel = new Label(group_1, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setBounds(136, 74, 218, 12);
		lblNewLabel
				.setText("\u6B21\u7B26\u5408\u62A5\u8B66\u53D1\u9001\u6761\u4EF6\u5F00\u59CB\u53D1\u9001\u62A5\u8B66 ");

		button_1 = new Button(group_1, SWT.RADIO);
		button_1.setBounds(10, 117, 213, 16);
		button_1.setText("\u62A5\u8B66\u53EA\u53D1\u9001\u4E00\u6B21");
		button_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));

		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_2.setBounds(10, 139, 86, 12);
		label_2.setText("\u53D1\u9001\u4E00\u6B21\uFF0C\u5F53\u7B2C");

		text_1 = new Text(group_1, SWT.BORDER);
		text_1.setBounds(97, 136, 36, 18);
		text_1.setText("1");
		text_1.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});

		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_3.setBounds(136, 139, 218, 12);
		label_3.setText("\u6B21\u7B26\u5408\u62A5\u8B66\u53D1\u9001\u6761\u4EF6\u65F6\u53D1\u9001\u62A5\u8B66");

		btnRadioButton = new Button(group_1, SWT.RADIO);
		btnRadioButton.setBounds(10, 175, 213, 16);
		btnRadioButton.setText("\u9009\u62E9\u6027\u53D1\u9001\u8B66\u62A5");
		btnRadioButton.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnRadioButton.setSelection(true);

		Label lblNewLabel_1 = new Label(group_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_1.setBounds(10, 197, 30, 12);
		lblNewLabel_1.setText("\u5F53\u7B2C ");

		text_2 = new Text(group_1, SWT.BORDER);
		text_2.setBounds(40, 194, 48, 18);
		text_2.setText("2");
		text_2.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});

		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_4.setBounds(90, 197, 210, 12);
		label_4.setText("\u6B21\u7B26\u5408\u62A5\u8B66\u53D1\u9001\u6761\u4EF6\u65F6\u53CA\u5176\u4EE5\u540E\u6BCF\u91CD\u590D ");

		text_3 = new Text(group_1, SWT.BORDER);
		text_3.setBounds(307, 194, 43, 18);
		text_3.setText("3");
		text_3.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});

		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_5.setBounds(354, 197, 127, 12);
		label_5.setText("\u6B21\u65F6\u53D1\u9001\u62A5\u8B66");
		sashForm_1.setWeights(new int[] { 130, 115 });
		sashForm.setWeights(new int[] { 130, 311 });
		treeViewer.setContentProvider(new GroupTreeContentProvider());
		treeViewer.setLabelProvider(new GroupTreeLabelProvider());
		treeViewer.setInput(SiteViewData.CreatTreeData());
		return composite;
	}
	
	//选中某个子节点把父节点也选中
	private void SelectParent(TreeItem item) {
		if (item.getParent() != null && !item.getText().equals("SiteViewEcc9.2")) {
			TreeItem treeItem = item.getParentItem();
			treeItem.setChecked(true);
			SelectParent(treeItem);
		}
	}

	//选中某个节点若还有子节点则选中子节点
	protected void SelectChild(TreeItem item) {
		if (item.getItemCount() > 0) {
			for (TreeItem t : item.getItems()) {
				t.setChecked(true);
				SelectChild(t);
			}
		}
	}

	//取消某个节点时取消下一级的子节点
	protected void DeletChild(TreeItem item) {
		if (item.getItemCount() > 0) {
			for (TreeItem t : item.getItems()) {
				t.setChecked(false);
				DeletChild(t);
			}
		}
	}
	
//	//取消所选节点的父节点
//	private void DeleteParent(TreeItem item) {
//		if (item.getParent() != null && !item.getText().equals("SiteViewEcc9.2")) {
//			TreeItem treeItem = item.getParentItem();
//			treeItem.setChecked(false);
//			SelectParent(treeItem);
//		}
//	}

	// 设置email报警
	public static void createEmailGroup(SashForm sashForm) {
		Group group = new Group(sashForm, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group.setText("Email\u8BBE\u7F6E");

		Label name = new Label(group, SWT.NONE);
		name.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		name.setBounds(35, 20, 95, 18);
		name.setText("\u62A5\u8B66\u540D\u79F0*\uFF1A");

		text_4 = new Text(group, SWT.BORDER);
		text_4.setBounds(135, 20, 200, 18);

		Label receiveAddress = new Label(group, SWT.NONE);
		receiveAddress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		receiveAddress.setBounds(35, 48, 95, 18);
		receiveAddress.setText("\u90AE\u4EF6\u63A5\u6536\u5730\u5740*\uFF1A");

		Combo combo = new Combo(group, SWT.NONE);
		combo.setBounds(135, 48, 200, 18);
		combo.add("其他");
		if (list == null) {
			list = new ArrayList();
			ICollection ic = FileTools.getBussCollection("MailType",
					"receiver", "EccMail");
			IEnumerator ien = ic.GetEnumerator();
			while (ien.MoveNext()) {
				BusinessObject bo = (BusinessObject) ien.get_Current();
				EmailModle m = new EmailModle(bo);
				list.add(m);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			BusinessObject bo = list.get(i).getBo();
			combo.add(bo.GetField("SetName").get_NativeValue().toString());
		}
		combo.select(0);

		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(35, 76, 95, 18);
		label.setText("\u5176\u4ED6\u90AE\u4EF6\u5730\u5740\uFF1A");

		text_6 = new Text(group, SWT.BORDER);
		text_6.setBounds(135, 76, 200, 18);

		Label lblEmail = new Label(group, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBounds(35, 104, 95, 18);
		lblEmail.setText("Email\u6A21\u677F*\uFF1A");

		Combo combo_1 = new Combo(group, SWT.NONE);
		combo_1.setBounds(135, 104, 200, 18);
		if (mailmodels == null) {
			mailmodels = new ArrayList<BusinessObject>();
			ICollection ico = FileTools.getBussCollection("ModleType", "email",
					"EccMailModle");
			IEnumerator ie = ico.GetEnumerator();
			while (ie.MoveNext()) {
				mailmodels.add((BusinessObject) ie.get_Current());
			}
		}
		for (int i = 0; i < mailmodels.size(); i++) {
			combo_1.add(mailmodels.get(i).GetField("MailTitle")
					.get_NativeValue().toString());
		}
		combo_1.select(0);

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(35, 132, 95, 18);
		label_1.setText("\u5347\u7EA7\u6B21\u6570\uFF1A");

		text_8 = new Text(group, SWT.BORDER);
		text_8.setBounds(135, 132, 200, 18);

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(35, 160, 95, 18);
		label_2.setText("\u5347\u7EA7\u63A5\u6536\u4EBA\u5730\u5740\uFF1A");

		text_9 = new Text(group, SWT.BORDER);
		text_9.setBounds(135, 160, 200, 18);

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setEnabled(true);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(35, 188, 95, 18);
		label_3.setText("\u505C\u6B62\u6B21\u6570\uFF1A");

		text_10 = new Text(group, SWT.BORDER);
		text_10.setBounds(135, 188, 200, 18);

		Label lblNewLabel_2 = new Label(group, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(35, 216, 95, 18);
		lblNewLabel_2.setText("\u503C\u73ED\u62A5\u8B66\u5217\u8868\uFF1A");

		Combo combo_2 = new Combo(group, SWT.NONE);
		combo_2.setBounds(135, 216, 200, 18);
		combo_2.add("空");
		if (duty == null) {
			duty = new ArrayList<TableModle>();
			ICollection ico = FileTools.getBussCollection("EccDutyTable");
			IEnumerator ie = ico.GetEnumerator();
			while (ie.MoveNext()) {
				BusinessObject bo = (BusinessObject) ie.get_Current();
				TableModle table = new TableModle(bo);
				duty.add(table);
			}
		}
		for (int i = 0; i < duty.size(); i++) {
			combo_2.add(duty.get(i).getBo().GetField("DutyTableName")
					.get_NativeValue().toString());
		}
		combo_2.select(0);

		Label label_4 = new Label(group, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(35, 244, 95, 18);
		label_4.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");

		Combo combo_3 = new Combo(group, SWT.NONE);
		combo_3.setBounds(135, 244, 200, 18);
	}

	// 设置短信报警
	public static void createMessageGroup(SashForm sashForm) {
		Group group = new Group(sashForm, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group.setText("\u8BBE\u7F6E\u77ED\u4FE1\u62A5\u8B66");

		Label name = new Label(group, SWT.NONE);
		name.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		name.setBounds(35, 20, 95, 18);
		name.setText("\u62A5\u8B66\u540D\u79F0*\uFF1A");

		text_4 = new Text(group, SWT.BORDER);
		text_4.setBounds(135, 20, 200, 18);

		Label receiveAddress = new Label(group, SWT.NONE);
		receiveAddress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		receiveAddress.setBounds(35, 45, 95, 18);
		receiveAddress
				.setText("\u62A5\u8B66\u63A5\u6536\u624B\u673A\u53F7*\uFF1A");

		Combo combo = new Combo(group, SWT.NONE);
		combo.setBounds(135, 45, 200, 18);

		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(35, 70, 95, 18);
		label.setText("\u5176\u4ED6\u624B\u673A\u53F7\uFF1A");

		text_6 = new Text(group, SWT.BORDER);
		text_6.setBounds(135, 70, 200, 18);

		Label label_5 = new Label(group, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(35, 95, 95, 18);
		label_5.setText("\u53D1\u9001\u65B9\u5F0F*\uFF1A");

		Combo combo_1 = new Combo(group, SWT.NONE);
		combo_1.setBounds(135, 95, 200, 18);

		Label lblEmail = new Label(group, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBounds(35, 120, 95, 18);
		lblEmail.setText("\u77ED\u4FE1\u6A21\u677F*\uFF1A");

		Combo combo_2 = new Combo(group, SWT.NONE);
		combo_2.setBounds(135, 120, 200, 18);
		if (messagemodels == null) {
			messagemodels = new ArrayList<BusinessObject>();
			ICollection ico = FileTools.getBussCollection("ModleType", "SMS",
					"EccMailModle");
			IEnumerator ie = ico.GetEnumerator();
			while (ie.MoveNext()) {
				messagemodels.add((BusinessObject) ie.get_Current());
			}
		}
		for (int i = 0; i < messagemodels.size(); i++) {
			combo_2.add(messagemodels.get(i).GetField("MailTitle")
					.get_NativeValue().toString());
		}
		combo_2.select(0);

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(35, 145, 95, 18);
		label_1.setText("\u5347\u7EA7\u6B21\u6570\uFF1A");

		text_8 = new Text(group, SWT.BORDER);
		text_8.setBounds(135, 145, 200, 18);

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(35, 170, 95, 18);
		label_2.setText("\u5347\u7EA7\u63A5\u6536\u4EBA\u5730\u5740\uFF1A");

		text_9 = new Text(group, SWT.BORDER);
		text_9.setBounds(135, 170, 200, 18);

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setEnabled(true);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(35, 195, 95, 18);
		label_3.setText("\u505C\u6B62\u6B21\u6570\uFF1A");

		text_10 = new Text(group, SWT.BORDER);
		text_10.setBounds(135, 195, 200, 18);

		Label lblNewLabel_2 = new Label(group, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(35, 220, 95, 18);
		lblNewLabel_2.setText("\u503C\u73ED\u62A5\u8B66\u5217\u8868\uFF1A");

		Combo combo_3 = new Combo(group, SWT.NONE);
		combo_3.setBounds(135, 220, 200, 18);

		Label label_4 = new Label(group, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(35, 245, 95, 18);
		label_4.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");

		Combo combo_4 = new Combo(group, SWT.NONE);
		combo_4.setBounds(135, 245, 200, 18);
	}

	// 设置腳本报警
	public static void createScriptGroup(SashForm sashForm) {
		Group group = new Group(sashForm, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group.setText("\u8BBE\u7F6E\u811A\u672C\u62A5\u8B66");

		Label name = new Label(group, SWT.NONE);
		name.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		name.setBounds(35, 20, 95, 20);
		name.setText("\u62A5\u8B66\u540D\u79F0*\uFF1A");

		text_4 = new Text(group, SWT.BORDER);
		text_4.setBounds(135, 20, 200, 20);

		Label receiveAddress = new Label(group, SWT.NONE);
		receiveAddress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		receiveAddress.setBounds(35, 60, 95, 20);
		receiveAddress.setText("\u9009\u62E9\u670D\u52A1\u5668*\uFF1A");

		Combo combo = new Combo(group, SWT.NONE);
		combo.setBounds(135, 60, 200, 20);
		combo.add("127.0.0.1");
		if (service == null) {
			service = new ArrayList<MachineModle>();
			ICollection iCollection = FileTools
					.getBussCollection("RemoteMachine");
			IEnumerator ie = iCollection.GetEnumerator();
			while (ie.MoveNext()) {
				BusinessObject bo = (BusinessObject) ie.get_Current();
				MachineModle model = new MachineModle(bo);
				service.add(model);
			}
		}
		for (int i = 0; i < service.size(); i++) {
			combo.add(service.get(i).getBo().GetField("ServerAddress")
					.get_NativeValue().toString());
		}
		combo.select(0);

		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(35, 100, 95, 20);
		label.setText("\u9009\u62E9\u811A\u672C*\uFF1A");

		Combo combo_1 = new Combo(group, SWT.NONE);
		combo_1.setBounds(135, 100, 200, 20);
		combo_1.add("PlayRemoteSound");
		combo_1.add("Reboot");
		combo_1.add("RestartIIS");
		combo_1.add("RestartService");
		combo_1.add("SendMessage");
		combo_1.add("Shutdown");
		combo_1.select(0);

		Label label_5 = new Label(group, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(35, 140, 95, 20);
		label_5.setText("\u9644\u52A0\u53C2\u6570\uFF1A");

		text_13 = new Text(group, SWT.BORDER);
		text_13.setBounds(135, 140, 200, 20);

		Label lblEmail = new Label(group, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBounds(35, 180, 95, 20);
		lblEmail.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");

		Combo combo_2 = new Combo(group, SWT.NONE);
		combo_2.setBounds(135, 180, 200, 20);

	}

	// 设置声音报警
	public static void createSoundGroup(SashForm sashForm) {
		Group group = new Group(sashForm, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group.setText("\u8BBE\u7F6E\u58F0\u97F3\u62A5\u8B66");

		Label name = new Label(group, SWT.NONE);
		name.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		name.setBounds(35, 20, 95, 20);
		name.setText("\u62A5\u8B66\u540D\u79F0*\uFF1A");

		text_4 = new Text(group, SWT.BORDER);
		text_4.setBounds(135, 20, 200, 20);

		Label receiveAddress = new Label(group, SWT.NONE);
		receiveAddress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		receiveAddress.setBounds(35, 60, 95, 20);
		receiveAddress.setText("\u670D\u52A1\u5668\u540D*\uFF1A");

		text_5 = new Text(group, SWT.BORDER);
		text_5.setBounds(135, 60, 200, 20);

		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(35, 100, 95, 20);
		label.setText("\u767B\u5F55\u540D*\uFF1A");

		text_6 = new Text(group, SWT.BORDER);
		text_6.setBounds(135, 100, 200, 20);

		Label label_5 = new Label(group, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(35, 140, 95, 20);
		label_5.setText("\u767B\u5F55\u5BC6\u7801\uFF1A");

		text_13 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_13.setBounds(135, 140, 200, 20);

		Label lblEmail = new Label(group, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBounds(35, 180, 95, 20);
		lblEmail.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");

		Combo combo = new Combo(group, SWT.NONE);
		combo.setBounds(135, 180, 200, 20);

	}

	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "保存",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", true);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			BusinessObject bo = ConnectionBroker.get_SiteviewApi()
					.get_BusObService().Create("EccAlarmRule");
			if (name.equals("email")) {
				bo.GetField("AlarmType").SetValue(new SiteviewValue("email"));
			} else if (name.equals("SMS")) {
				bo.GetField("AlarmType").SetValue(new SiteviewValue("SMS"));
			} else if (name.equals("script")) {
				bo.GetField("AlarmType").SetValue(new SiteviewValue("script"));
			} else if (name.equals("sound")) {
				bo.GetField("AlarmType").SetValue(new SiteviewValue("sound"));
			}
			bo.GetField("AlarmEvent").SetValue(
					new SiteviewValue(combo.getText()));
			if (button.getSelection()) {
				bo.GetField("AlarmRule")
						.SetValue(new SiteviewValue("continue"));
				bo.GetField("StartCount").SetValue(
						new SiteviewValue(text.getText()));
			} else if (button_1.getSelection()) {
				bo.GetField("AlarmRule").SetValue(new SiteviewValue("once"));
				bo.GetField("StartCount").SetValue(
						new SiteviewValue(text_1.getText()));
			} else if (btnRadioButton.getSelection()) {
				bo.GetField("AlarmRule").SetValue(new SiteviewValue("select"));
				bo.GetField("StartCount").SetValue(
						new SiteviewValue(text_2.getText()));
				bo.GetField("RepeatCount").SetValue(
						new SiteviewValue(text_3.getText()));
			}
			bo.GetField("RuleStatus").SetValue(new SiteviewValue(true));
			TreeItem[] item = tree.getItems();
			for (TreeItem treeItem : item) {
				if(treeItem.getChecked()){
					if(treeItem.getData() instanceof MonitorModle){
						((MonitorModle)treeItem.getData()).getBo().GetField("").get_NativeValue().toString();
					}
				}
			}
			bo.GetField("MonitorId").SetValue(
					new SiteviewValue("567156dghfghfjh761671857"));
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			AlarmRuleInfo ar = new AlarmRuleInfo(bo);
			AlarmRule.list.add(ar);
			AlarmRule.disposeTableItem();
			AlarmRule.createTableItem();
			this.close();
		} else {
			this.close();
		}
	}
}
