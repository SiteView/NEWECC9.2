package SiteView.ecc.dialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
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
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.Modle.SMSModel;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.data.TableDutyInfor;
import SiteView.ecc.editors.AlarmRule;
import SiteView.ecc.editors.EmailSetUp;
import SiteView.ecc.editors.MessageSetUp;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

import siteview.windows.forms.SwtImageConverter;
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
	static Combo combo_1;
	static Combo combo_2;
	static Combo combo_3;
	static Combo combo_4;
	static Combo combo_5;
	Button button;// 连续发送按钮
	Button button_1;// 只发一次
	Button btnRadioButton;// 选择发送
	Tree tree;
	TreeViewer treeViewer;
	List<String> monitorid = new ArrayList<String>();
	Map<String, TreeItem> allid = new HashMap<String, TreeItem>();
	List<String> alarmname;
	static BusinessObject bo=null;
	public static List<SMSModel> list1 = MessageSetUp.list;
	/**
	 * @wbp.parser.constructor
	 */
	public AddEmailAlarmRule(Shell parentShell, String name,BusinessObject bo) {
		super(parentShell);
		this.name = name;
		this.bo=bo;
		alarmname = getAlarmName();
//		getAll(tree.getItems());
	}

	protected void configureShell(Shell newShell) {
		newShell.setSize(650, 600);
		newShell.setLocation(280, 100);
		if (name.equals("email")) {
			newShell.setText("添加Email报警");
		} else if (name.equals("SMS")) {
			newShell.setText("添加短信报警");
		} else if (name.equals("script")) {
			newShell.setText("添加脚本报警");
		} else if (name.equals("sound")) {
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
		treeViewer = new TreeViewer(group,SWT.CHECK);
		tree = treeViewer.getTree();
		tree.setBackground(EccTreeControl.color);
		tree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem item=(TreeItem) e.item;
				if(item.getChecked()){
					if(bo!=null){
						SelectParent(item);
					}else{
						SelectChild(item);
						SelectParent(item);	
					}
				}else{
					DeletChild(item);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
		sashForm_1.setBackground(EccTreeControl.color);

		if (name.equals("email")) {
			createEmailGroup(sashForm_1);
		} else if (name.equals("SMS")) {
			createMessageGroup(sashForm_1);
		} else if (name.equals("script")) {
			createScriptGroup(sashForm_1);
		} else if (name.equals("sound")) {
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
		if(bo!=null){
			if(bo.GetField("AlarmEvent").get_NativeValue().toString().equals("error")){
				combo.setText("错误");				
			}else{
				combo.setText("危险");
			}
		}

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
		if(bo!=null&&bo.GetField("AlarmRule").get_NativeValue().toString().equals("continue")){
			text.setText(bo.GetField("StartCount").get_NativeValue().toString());
		}

		Label lblNewLabel = new Label(group_1, SWT.NONE);
		lblNewLabel.setBackground(EccTreeControl.color);
		lblNewLabel.setBounds(136, 74, 218, 12);
		lblNewLabel.setText("\u6B21\u7B26\u5408\u62A5\u8B66\u53D1\u9001\u6761\u4EF6\u5F00\u59CB\u53D1\u9001\u62A5\u8B66 ");

		button_1 = new Button(group_1, SWT.RADIO);
		button_1.setBounds(10, 117, 213, 16);
		button_1.setText("\u62A5\u8B66\u53EA\u53D1\u9001\u4E00\u6B21");
		button_1.setBackground(EccTreeControl.color);

		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setBackground(EccTreeControl.color);
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
		if(bo!=null&&bo.GetField("AlarmRule").get_NativeValue().toString().equals("once")){
			text_1.setText(bo.GetField("StartCount").get_NativeValue().toString());
		}

		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setBackground(EccTreeControl.color);
		label_3.setBounds(136, 139, 218, 12);
		label_3.setText("\u6B21\u7B26\u5408\u62A5\u8B66\u53D1\u9001\u6761\u4EF6\u65F6\u53D1\u9001\u62A5\u8B66");

		btnRadioButton = new Button(group_1, SWT.RADIO);
		btnRadioButton.setBounds(10, 175, 213, 16);
		btnRadioButton.setText("\u9009\u62E9\u6027\u53D1\u9001\u8B66\u62A5");
		btnRadioButton.setBackground(EccTreeControl.color);
		btnRadioButton.setSelection(true);

		Label lblNewLabel_1 = new Label(group_1, SWT.NONE);
		lblNewLabel_1.setBackground(EccTreeControl.color);
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
		if(bo!=null&&bo.GetField("AlarmRule").get_NativeValue().toString().equals("select")){
			text_2.setText(bo.GetField("StartCount").get_NativeValue().toString());
			text_3.setText(bo.GetField("RepeatCount").get_NativeValue().toString());
		}

		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setBackground(EccTreeControl.color);
		label_5.setBounds(354, 197, 127, 12);
		label_5.setText("\u6B21\u65F6\u53D1\u9001\u62A5\u8B66");
		sashForm_1.setWeights(new int[] { 130, 115 });
		sashForm.setWeights(new int[] { 130, 311 });
		treeViewer.setContentProvider(new GroupTreeContentProvider());
		treeViewer.setLabelProvider(new GroupTreeLabelProvider());
		treeViewer.setInput(SiteViewData.CreatTreeData());
		treeViewer.expandToLevel(10);
		if(bo!=null){
			for (TreeItem item : tree.getItems()) {
				TreeItem[] treeItem = item.getItems();
				get(treeItem);
			}
			String name = bo.GetField("AlarmName").get_NativeValue().toString();
			ICollection ico = FileTools.getBussCollection("AlarmName",name,"EccAlarmRule");
			IEnumerator ie = ico.GetEnumerator();
			while (ie.MoveNext()) {
				String id = ((BusinessObject)ie.get_Current()).GetField("MonitorId").get_NativeValue().toString();
				if(allid.get(id)!=null){
					TreeItem item = allid.get(id);
					SelectParent(item);
					item.setChecked(true);
				}
			}
		}
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
		if(bo!=null){
			text_4.setText(bo.GetField("AlarmName").get_NativeValue().toString());
		}

		Label receiveAddress = new Label(group, SWT.NONE);
		receiveAddress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		receiveAddress.setBounds(35, 48, 95, 18);
		receiveAddress.setText("\u90AE\u4EF6\u63A5\u6536\u5730\u5740*\uFF1A");

		combo_4 = new Combo(group, SWT.NONE);
		combo_4.setBounds(135, 48, 200, 18);
		combo_4.add("其他");
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
			combo_4.add(bo.GetField("SetName").get_NativeValue().toString());
		}
		combo_4.select(0);
		if(bo!=null){
			if(bo.GetField("Address").get_NativeValue().toString().equals("其他")){
				combo_4.setText(bo.GetField("Address").get_NativeValue().toString());
			}else{				
				combo_4.setText(EccTreeControl.CreateBo("RecId", bo.GetField("Address").get_NativeValue().toString(), "EccMail").GetField("SetName").get_NativeValue().toString());
			}
		}

		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(35, 76, 95, 18);
		label.setText("\u5176\u4ED6\u90AE\u4EF6\u5730\u5740\uFF1A");

		text_6 = new Text(group, SWT.BORDER);
		text_6.setBounds(135, 76, 200, 18);
		if(bo!=null){
			text_6.setText(bo.GetField("Other").get_NativeValue().toString());
		}

		Label lblEmail = new Label(group, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBounds(35, 104, 95, 18);
		lblEmail.setText("Email\u6A21\u677F*\uFF1A");

		combo_1 = new Combo(group, SWT.NONE);
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
			combo_1.add(mailmodels.get(i).GetField("MailModle")
					.get_NativeValue().toString());
		}
		combo_1.select(0);
		if(bo!=null){
			String ModleId = bo.GetField("ModleId").get_NativeValue().toString();
			combo_1.setText(EccTreeControl.CreateBo("RecId", ModleId, "EccMailModle").GetField("MailModle").get_NativeValue().toString());
		}

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(35, 132, 95, 18);
		label_1.setText("\u5347\u7EA7\u6B21\u6570\uFF1A");

		text_8 = new Text(group, SWT.BORDER);
		text_8.setBounds(135, 132, 200, 18);
		if(bo!=null){			
			text_8.setText(bo.GetField("PromotionCount").get_NativeValue().toString());
		}

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(35, 160, 95, 18);
		label_2.setText("\u5347\u7EA7\u63A5\u6536\u4EBA\u5730\u5740\uFF1A");

		text_9 = new Text(group, SWT.BORDER);
		text_9.setBounds(135, 160, 200, 18);
		if(bo!=null){
			text_9.setText(bo.GetField("PromotionAddress").get_NativeValue().toString());
		}

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setEnabled(true);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(35, 188, 95, 18);
		label_3.setText("\u505C\u6B62\u6B21\u6570\uFF1A");

		text_10 = new Text(group, SWT.BORDER);
		text_10.setBounds(135, 188, 200, 18);
		if(bo!=null){
			text_10.setText(bo.GetField("StopCount").get_NativeValue().toString());
		}

		Label lblNewLabel_2 = new Label(group, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(35, 216, 95, 18);
		lblNewLabel_2.setText("\u503C\u73ED\u62A5\u8B66\u5217\u8868\uFF1A");

		combo_2 = new Combo(group, SWT.NONE);
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
		if(bo!=null){
			String dutyid = bo.GetField("DutyId").get_NativeValue().toString();
			combo_2.setText(EccTreeControl.CreateBo("RecId", dutyid, "EccDutyTable").GetField("DutyTableName").get_NativeValue().toString());
		}

		Label label_4 = new Label(group, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(35, 244, 95, 18);
		label_4.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");

		combo_3 = new Combo(group, SWT.NONE);
		combo_3.setBounds(135, 244, 200, 18);
		combo_3.add("空");
		combo_3.select(0);
		if(bo!=null){
			combo_3.setText(bo.GetField("AlarmTactful").get_NativeValue().toString());
		}
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
		if(bo!=null){
			text_4.setText(bo.GetField("AlarmName").get_NativeValue().toString());
		}

		Label receiveAddress = new Label(group, SWT.NONE);
		receiveAddress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		receiveAddress.setBounds(35, 45, 95, 18);
		receiveAddress
				.setText("\u62A5\u8B66\u63A5\u6536\u624B\u673A\u53F7*\uFF1A");

		combo_4 = new Combo(group, SWT.NONE);
		combo_4.setBounds(135, 45, 200, 18);
		combo_4.add("其他");
		combo_4.select(0);
		if(bo!=null){
			combo_4.setText(bo.GetField("Address").get_NativeValue().toString());
		}
		if(list1==null){
			list1 = new ArrayList<SMSModel>();
			ICollection ic=FileTools.getBussCollection("SMSType", "receive", "EccSMS");
			IEnumerator ien=ic.GetEnumerator();
			while(ien.MoveNext()){
				BusinessObject bo=(BusinessObject) ien.get_Current();
				SMSModel sms=new SMSModel(bo);
				list1.add(sms);
			}
		}
		for (SMSModel sms : list1) {
			BigDecimal big = new BigDecimal(sms.getBo().GetField("MobliePhone").get_NativeValue().toString());
			combo_4.add(big.toPlainString());
		}
		

		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(35, 70, 95, 18);
		label.setText("\u5176\u4ED6\u624B\u673A\u53F7\uFF1A");

		text_6 = new Text(group, SWT.BORDER);
		text_6.setBounds(135, 70, 200, 18);
		if(bo!=null){
			text_6.setText(bo.GetField("Other").get_NativeValue().toString());
		}

		Label label_5 = new Label(group, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(35, 95, 95, 18);
		label_5.setText("\u53D1\u9001\u65B9\u5F0F*\uFF1A");

		combo_1 = new Combo(group, SWT.NONE);
		combo_1.setBounds(135, 95, 200, 18);

		Label lblEmail = new Label(group, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBounds(35, 120, 95, 18);
		lblEmail.setText("\u77ED\u4FE1\u6A21\u677F*\uFF1A");

		combo_2 = new Combo(group, SWT.NONE);
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
			combo_2.add(messagemodels.get(i).GetField("MailModle")
					.get_NativeValue().toString());
		}
		combo_2.select(0);
		if(bo!=null){
			String ModleId = bo.GetField("ModleId").get_NativeValue().toString();
			combo_2.setText(EccTreeControl.CreateBo("RecId", ModleId, "EccMailModle").GetField("MailModle").get_NativeValue().toString());
		}

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(35, 145, 95, 18);
		label_1.setText("\u5347\u7EA7\u6B21\u6570\uFF1A");

		text_8 = new Text(group, SWT.BORDER);
		text_8.setBounds(135, 145, 200, 18);
		if(bo!=null){
			text_8.setText(bo.GetField("PromotionCount").get_NativeValue().toString());
		}

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(35, 170, 95, 18);
		label_2.setText("\u5347\u7EA7\u63A5\u6536\u4EBA\u5730\u5740\uFF1A");

		text_9 = new Text(group, SWT.BORDER);
		text_9.setBounds(135, 170, 200, 18);
		if(bo!=null){
			text_9.setText(bo.GetField("PromotionAddress").get_NativeValue().toString());
		}

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setEnabled(true);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(35, 195, 95, 18);
		label_3.setText("\u505C\u6B62\u6B21\u6570\uFF1A");

		text_10 = new Text(group, SWT.BORDER);
		text_10.setBounds(135, 195, 200, 18);
		if(bo!=null){
			text_10.setText(bo.GetField("StopCount").get_NativeValue().toString());
		}

		Label lblNewLabel_2 = new Label(group, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(35, 220, 95, 18);
		lblNewLabel_2.setText("\u503C\u73ED\u62A5\u8B66\u5217\u8868\uFF1A");

		combo_3 = new Combo(group, SWT.NONE);
		combo_3.setBounds(135, 220, 200, 18);
		combo_3.add("空");
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
			combo_3.add(duty.get(i).getBo().GetField("DutyTableName")
					.get_NativeValue().toString());
		}
		combo_3.select(0);
		if(bo!=null){
			String dutyid = bo.GetField("DutyId").get_NativeValue().toString();
			combo_3.setText(EccTreeControl.CreateBo("RecId", dutyid, "EccDutyTable").GetField("DutyTableName").get_NativeValue().toString());
		}

		Label label_4 = new Label(group, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(35, 245, 95, 18);
		label_4.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");

		combo_5 = new Combo(group, SWT.NONE);
		combo_5.setBounds(135, 245, 200, 18);
		combo_5.add("空");
		combo_5.select(0);
		if(bo!=null){
			combo_5.setText(bo.GetField("AlarmTactful").get_NativeValue().toString());
		}
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
		if(bo!=null){
			text_4.setText(bo.GetField("AlarmName").get_NativeValue().toString());
		}

		Label receiveAddress = new Label(group, SWT.NONE);
		receiveAddress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		receiveAddress.setBounds(35, 60, 95, 20);
		receiveAddress.setText("\u9009\u62E9\u670D\u52A1\u5668*\uFF1A");

		combo_4 = new Combo(group, SWT.NONE);
		combo_4.setBounds(135, 60, 200, 20);
		combo_4.add("127.0.0.1");
		if (service == null) {
			service = new ArrayList<MachineModle>();
			ICollection iCollection = FileTools.getBussCollection("RemoteMachine");
			IEnumerator ie = iCollection.GetEnumerator();
			while (ie.MoveNext()) {
				BusinessObject bo = (BusinessObject) ie.get_Current();
				MachineModle model = new MachineModle(bo);
				service.add(model);
			}
		}
		for (int i = 0; i < service.size(); i++) {
			combo_4.add(service.get(i).getBo().GetField("ServerAddress")
					.get_NativeValue().toString());
		}
		combo_4.select(0);
		if(bo!=null){
			combo_4.setText(bo.GetField("Service").get_NativeValue().toString());
		}

		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(35, 100, 95, 20);
		label.setText("\u9009\u62E9\u811A\u672C*\uFF1A");

		combo_1 = new Combo(group, SWT.NONE);
		combo_1.setBounds(135, 100, 200, 20);
		combo_1.add("PlayRemoteSound");
		combo_1.add("Reboot");
		combo_1.add("RestartIIS");
		combo_1.add("RestartService");
		combo_1.add("SendMessage");
		combo_1.add("Shutdown");
		combo_1.select(0);
		if(bo!=null){
			combo_1.setText(bo.GetField("SelectScript").get_NativeValue().toString());
		}

		Label label_5 = new Label(group, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(35, 140, 95, 20);
		label_5.setText("\u9644\u52A0\u53C2\u6570\uFF1A");

		text_13 = new Text(group, SWT.BORDER);
		text_13.setBounds(135, 140, 200, 20);
		if(bo!=null){
			text_13.setText(bo.GetField("AddParameter").get_NativeValue().toString());
		}

		Label lblEmail = new Label(group, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBounds(35, 180, 95, 20);
		lblEmail.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");

		combo_2 = new Combo(group, SWT.NONE);
		combo_2.setBounds(135, 180, 200, 20);
		combo_2.add("空");
		combo_2.select(0);
		if(bo!=null){
			combo_2.setText(bo.GetField("AlarmTactful").get_NativeValue().toString());
		}

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
		if(bo!=null){
			text_4.setText(bo.GetField("AlarmName").get_NativeValue().toString());
		}

		Label receiveAddress = new Label(group, SWT.NONE);
		receiveAddress.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		receiveAddress.setBounds(35, 60, 95, 20);
		receiveAddress.setText("\u670D\u52A1\u5668\u540D*\uFF1A");

		text_5 = new Text(group, SWT.BORDER);
		text_5.setBounds(135, 60, 200, 20);
		text_5.setText("127.0.0.1");
		if(bo!=null){
			text_5.setText(bo.GetField("Service").get_NativeValue().toString());
		}

		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(35, 100, 95, 20);
		label.setText("\u767B\u5F55\u540D*\uFF1A");

		text_6 = new Text(group, SWT.BORDER);
		text_6.setBounds(135, 100, 200, 20);
		text_6.setText("administrator");
		if(bo!=null){
			text_6.setText(bo.GetField("LoginName").get_NativeValue().toString());
		}

		Label label_5 = new Label(group, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_5.setBounds(35, 140, 95, 20);
		label_5.setText("\u767B\u5F55\u5BC6\u7801\uFF1A");

		text_13 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text_13.setBounds(135, 140, 200, 20);
		if(bo!=null){
			text_13.setText(bo.GetField("LoginPassword").get_NativeValue().toString());
		}

		Label lblEmail = new Label(group, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBounds(35, 180, 95, 20);
		lblEmail.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");

		combo_4 = new Combo(group, SWT.NONE);
		combo_4.setBounds(135, 180, 200, 20);
		combo_4.add("空");
		combo_4.select(0);
		if(bo!=null){
			combo_4.setText(bo.GetField("AlarmTactful").get_NativeValue().toString());
		}

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
			if(bo!=null){
				TreeItem[] item = tree.getItems();
				List<String> mid = getSelect(item);
				if(text_4.getText().equals("")){
					MessageDialog.openInformation(new Shell(), "提示", "报警名称不能为空！");
					return;
				}else if(mid.size()==0){
					MessageDialog.openInformation(new Shell(), "提示", "请选择监听器！");
					return;
				}else{
//					ICollection ico1 = FileTools.getBussCollection("AlarmName",bo.GetField("AlarmName").get_NativeValue().toString(),"EccAlarmRule");
//					IEnumerator ie1 = ico1.GetEnumerator();
//					while (ie1.MoveNext()) {
//						((BusinessObject)ie1.get_Current()).DeleteObject(ConnectionBroker.get_SiteviewApi());
//					}
					for (String string : mid) {
						BusinessObject bo = EccTreeControl.CreateBo("MonitorId", string, "EccAlarmRule");
						if (name.equals("email")) {
							String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
							Pattern regex = Pattern.compile(check); 
							 if(text_6.getText().equals("")&&combo_4.getText().equals("其他")){
									MessageDialog.openInformation(new Shell(), "提示", "报警邮件接收地址不能为空！");
									return;
								}else if(!text_6.getText().equals("")&&!regex.matcher(text_6.getText()).matches()){
									 MessageDialog.openInformation(new Shell(), "提示", "Email格式不对！");
									 return;
								}else if(!text_9.getText().equals("")&&!regex.matcher(text_9.getText()).matches()){
									 MessageDialog.openInformation(new Shell(), "提示", "Email格式不对！");
									 return;
								}
						    bo.GetField("AlarmType").SetValue(new SiteviewValue("email"));
							bo.GetField("AlarmName").SetValue(new SiteviewValue(text_4.getText()));
							if(!combo_4.getText().equals("其他")){								
								bo.GetField("Address").SetValue(new SiteviewValue(EccTreeControl.CreateBo("SetName", combo_4.getText(), "EccMail").get_RecId()));
							}else{
								bo.GetField("Address").SetValue(new SiteviewValue(combo_4.getText()));
							}
							bo.GetField("Other").SetValue(new SiteviewValue(text_6.getText()));
							ICollection ico = FileTools.getBussCollection("MailModle",combo_1.getText(),"EccMailModle");
							IEnumerator ie = ico.GetEnumerator();
							while (ie.MoveNext()) {
								String type = ((BusinessObject)ie.get_Current()).GetField("ModleType").get_NativeValue().toString();
								if(type.equals("email")){
									String ModleId = ((BusinessObject)ie.get_Current()).get_RecId();
									bo.GetField("ModleId").SetValue(new SiteviewValue(ModleId));
								}
							}
							bo.GetField("PromotionCount").SetValue(new SiteviewValue(text_8.getText()));
							bo.GetField("PromotionAddress").SetValue(new SiteviewValue(text_9.getText()));
							bo.GetField("StopCount").SetValue(new SiteviewValue(text_10.getText()));
							if(combo_2.getText().equals("空")){
								bo.GetField("DutyId").SetValue(new SiteviewValue("空"));
							}else{
								String DutyId = EccTreeControl.CreateBo("DutyTableName",combo_2.getText(), "EccDutyTable").get_RecId();
								bo.GetField("DutyId").SetValue(new SiteviewValue(DutyId));								
							}
							bo.GetField("AlarmTactful").SetValue(new SiteviewValue(combo_3.getText()));
						} else if (name.equals("SMS")) {
							if(combo_4.getText().equals("其他")&&text_6.getText().equals("")){
								 MessageDialog.openInformation(new Shell(), "提示", "接收手机不能为空！");
								 return;
							}
							bo.GetField("AlarmType").SetValue(new SiteviewValue("SMS"));
							bo.GetField("AlarmName").SetValue(new SiteviewValue(text_4.getText()));
							bo.GetField("Address").SetValue(new SiteviewValue(combo_4.getText()));
							bo.GetField("Other").SetValue(new SiteviewValue(text_6.getText()));
							ICollection ico = FileTools.getBussCollection("MailModle",combo_2.getText(),"EccMailModle");
							IEnumerator ie = ico.GetEnumerator();
							while (ie.MoveNext()) {
								String type = ((BusinessObject)ie.get_Current()).GetField("ModleType").get_NativeValue().toString();
								if(type.equals("SMS")){
									String ModleId = ((BusinessObject)ie.get_Current()).get_RecId();
									bo.GetField("ModleId").SetValue(new SiteviewValue(ModleId));
								}
							}
							bo.GetField("PromotionCount").SetValue(new SiteviewValue(text_8.getText()));
							bo.GetField("PromotionAddress").SetValue(new SiteviewValue(text_9.getText()));
							bo.GetField("StopCount").SetValue(new SiteviewValue(text_10.getText()));
							if(combo_3.getText().equals("空")){
								bo.GetField("DutyId").SetValue(new SiteviewValue("空"));
							}else{
								String DutyId = EccTreeControl.CreateBo("DutyTableName",combo_3.getText(), "EccDutyTable").get_RecId();
								bo.GetField("DutyId").SetValue(new SiteviewValue(DutyId));								
							}
							bo.GetField("AlarmTactful").SetValue(new SiteviewValue(combo_5.getText()));
						} else if (name.equals("script")) {
							bo.GetField("AlarmType").SetValue(new SiteviewValue("script"));
							bo.GetField("AlarmName").SetValue(new SiteviewValue(text_4.getText()));
							bo.GetField("Service").SetValue(new SiteviewValue(combo_4.getText()));
							bo.GetField("SelectScript").SetValue(new SiteviewValue(combo_1.getText()));
							bo.GetField("AddParameter").SetValue(new SiteviewValue(text_13.getText()));
							bo.GetField("AlarmTactful").SetValue(new SiteviewValue(combo_2.getText()));
						} else if (name.equals("sound")) {
							bo.GetField("AlarmType").SetValue(new SiteviewValue("sound"));
							bo.GetField("AlarmName").SetValue(new SiteviewValue(text_4.getText()));
							bo.GetField("Service").SetValue(new SiteviewValue(text_5.getText()));
							bo.GetField("LoginName").SetValue(new SiteviewValue(text_6.getText()));
							bo.GetField("LoginPassword").SetValue(new SiteviewValue(text_13.getText()));
							bo.GetField("AlarmTactful").SetValue(new SiteviewValue(combo_4.getText()));
						}
						if(combo.getText().equals("危险")){				
							bo.GetField("AlarmEvent").SetValue(new SiteviewValue("warning"));
						}else if(combo.getText().equals("错误")){
							bo.GetField("AlarmEvent").SetValue(new SiteviewValue("error"));
						}
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
						bo.GetField("MonitorId").SetValue(new SiteviewValue(string));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					}
					AlarmRule.getData();
					AlarmRule.disposeTableItem();
					AlarmRule.createTableItem();
				}
			}else{
				for (String string : alarmname) {
					if(string.equals(text_4.getText())){
						MessageDialog.openInformation(new Shell(), "提示", "该报警名称已存在！");
						return;
					}
				}
				TreeItem[] item = tree.getItems();
				List<String> mid = getSelect(item);
				if(text_4.getText().equals("")){
					MessageDialog.openInformation(new Shell(), "提示", "报警名称不能为空！");
					return;
				}else if(mid.size()==0){
					MessageDialog.openInformation(new Shell(), "提示", "请选择监听器！");
					return;
				}
					for (String string : mid) {	
						BusinessObject bo = ConnectionBroker.get_SiteviewApi()
								.get_BusObService().Create("EccAlarmRule");
						if (name.equals("email")) {
							String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
							Pattern regex = Pattern.compile(check); 
							 if(text_6.getText().equals("")&&combo_4.getText().equals("其他")){
									MessageDialog.openInformation(new Shell(), "提示", "报警邮件接收地址不能为空！");
									return;
								}else if(!text_6.getText().equals("")&&!regex.matcher(text_6.getText()).matches()){
									 MessageDialog.openInformation(new Shell(), "提示", "Email格式不对！");
									 return;
								}else if(!text_9.getText().equals("")&&!regex.matcher(text_9.getText()).matches()){
									 MessageDialog.openInformation(new Shell(), "提示", "Email格式不对！");
									 return;
								}
							bo.GetField("AlarmType").SetValue(new SiteviewValue("email"));
							bo.GetField("AlarmName").SetValue(new SiteviewValue(text_4.getText()));
							if(!combo_4.getText().equals("其他")){								
								bo.GetField("Address").SetValue(new SiteviewValue(EccTreeControl.CreateBo("SetName", combo_4.getText(), "EccMail").get_RecId()));
							}else{
								bo.GetField("Address").SetValue(new SiteviewValue(combo_4.getText()));
							}
							bo.GetField("Other").SetValue(new SiteviewValue(text_6.getText()));
							ICollection ico = FileTools.getBussCollection("MailModle",combo_1.getText(),"EccMailModle");
							IEnumerator ie = ico.GetEnumerator();
							while (ie.MoveNext()) {
								String type = ((BusinessObject)ie.get_Current()).GetField("ModleType").get_NativeValue().toString();
								if(type.equals("email")){
									String ModleId = ((BusinessObject)ie.get_Current()).get_RecId();
									bo.GetField("ModleId").SetValue(new SiteviewValue(ModleId));
								}
							}
							bo.GetField("PromotionCount").SetValue(new SiteviewValue(text_8.getText()));
							bo.GetField("PromotionAddress").SetValue(new SiteviewValue(text_9.getText()));
							bo.GetField("StopCount").SetValue(new SiteviewValue(text_10.getText()));
							if(combo_2.getText().equals("空")){
								bo.GetField("DutyId").SetValue(new SiteviewValue("空"));
							}else{
								String DutyId = EccTreeControl.CreateBo("DutyTableName",combo_2.getText(), "EccDutyTable").get_RecId();
								bo.GetField("DutyId").SetValue(new SiteviewValue(DutyId));								
							}
							bo.GetField("AlarmTactful").SetValue(new SiteviewValue(combo_3.getText()));
						} else if (name.equals("SMS")) {
							if(combo_4.getText().equals("其他")&&text_6.getText().equals("")){
								 MessageDialog.openInformation(new Shell(), "提示", "接收手机不能为空！");
								 return;
							}
							bo.GetField("AlarmType").SetValue(new SiteviewValue("SMS"));
							bo.GetField("AlarmName").SetValue(new SiteviewValue(text_4.getText()));
							bo.GetField("Address").SetValue(new SiteviewValue(combo_4.getText()));
							bo.GetField("Other").SetValue(new SiteviewValue(text_6.getText()));
							ICollection ico = FileTools.getBussCollection("MailModle",combo_2.getText(),"EccMailModle");
							IEnumerator ie = ico.GetEnumerator();
							while (ie.MoveNext()) {
								String type = ((BusinessObject)ie.get_Current()).GetField("ModleType").get_NativeValue().toString();
								if(type.equals("SMS")){
									String ModleId = ((BusinessObject)ie.get_Current()).get_RecId();
									bo.GetField("ModleId").SetValue(new SiteviewValue(ModleId));
								}
							}
							bo.GetField("PromotionCount").SetValue(new SiteviewValue(text_8.getText()));
							bo.GetField("PromotionAddress").SetValue(new SiteviewValue(text_9.getText()));
							bo.GetField("StopCount").SetValue(new SiteviewValue(text_10.getText()));
							if(combo_3.getText().equals("空")){
								bo.GetField("DutyId").SetValue(new SiteviewValue("空"));
							}else{
								String DutyId = EccTreeControl.CreateBo("DutyTableName",combo_3.getText(), "EccDutyTable").get_RecId();
								bo.GetField("DutyId").SetValue(new SiteviewValue(DutyId));								
							}
							bo.GetField("AlarmTactful").SetValue(new SiteviewValue(combo_5.getText()));
						} else if (name.equals("script")) {
							bo.GetField("AlarmType").SetValue(new SiteviewValue("script"));
							bo.GetField("AlarmName").SetValue(new SiteviewValue(text_4.getText()));
							bo.GetField("Service").SetValue(new SiteviewValue(combo_4.getText()));
							bo.GetField("SelectScript").SetValue(new SiteviewValue(combo_1.getText()));
							bo.GetField("AddParameter").SetValue(new SiteviewValue(text_13.getText()));
							bo.GetField("AlarmTactful").SetValue(new SiteviewValue(combo_2.getText()));
						} else if (name.equals("sound")) {
							bo.GetField("AlarmType").SetValue(new SiteviewValue("sound"));
							bo.GetField("AlarmName").SetValue(new SiteviewValue(text_4.getText()));
							bo.GetField("Service").SetValue(new SiteviewValue(text_5.getText()));
							bo.GetField("LoginName").SetValue(new SiteviewValue(text_6.getText()));
							bo.GetField("LoginPassword").SetValue(new SiteviewValue(text_13.getText()));
							bo.GetField("AlarmTactful").SetValue(new SiteviewValue(combo_4.getText()));
						}
						if(combo.getText().equals("危险")){				
							bo.GetField("AlarmEvent").SetValue(new SiteviewValue("warning"));
						}else if(combo.getText().equals("错误")){
							bo.GetField("AlarmEvent").SetValue(new SiteviewValue("error"));
						}
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
						bo.GetField("MonitorId").SetValue(new SiteviewValue(string));
						bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
						AlarmRuleInfo ar = new AlarmRuleInfo(bo);
						AlarmRule.list.add(ar);
					}
					AlarmRule.disposeTableItem();
					AlarmRule.createTableItem();
			}
			this.close();
		} else {
			this.close();
		}
	}
	
	//得到选中的監視器id
	public List<String> getSelect(TreeItem[] item){
		for (TreeItem treeItem : item) {
			if(treeItem.getChecked()){
				if(treeItem.getData() instanceof MonitorModle){
					String id = ((MonitorModle)treeItem.getData()).getBo().get_RecId().toString();
					monitorid.add(id);
				}else if(treeItem.getItemCount()>0){
					TreeItem[] item1 = treeItem.getItems();
					getSelect(item1);
				}
			}
		}
		return monitorid;
	}
	
	//得到全部的監視器id
		public Map<String,TreeItem> get(TreeItem[] item){
			for (TreeItem treeItem : item) {
					if(treeItem.getData() instanceof MonitorModle){
						String id = ((MonitorModle)treeItem.getData()).getBo().get_RecId().toString();
						allid.put(id, treeItem);
					}else if(treeItem.getItemCount()>0){
						TreeItem[] item1 = treeItem.getItems();
						get(item1);
					}
			}
			return allid;
		}
	
	//获取报警表中所有已存在的报警名称
	public List<String> getAlarmName(){
		List<AlarmRuleInfo> alarmlist = AlarmRule.list;
		List<String> alarmname = new ArrayList<String>();
		for (int i = 0; i < alarmlist.size(); i++) {
			String name = alarmlist.get(i).getBo().GetField("AlarmName").get_NativeValue().toString();
			alarmname.add(name);
		}
		return alarmname;
	}
}
