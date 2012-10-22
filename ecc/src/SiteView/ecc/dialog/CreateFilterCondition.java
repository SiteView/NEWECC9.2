package SiteView.ecc.dialog;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;


public class CreateFilterCondition extends Dialog {
	public Shell parentShell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Combo combo;
	private Combo combo_1;
	private Combo combo_2;
	private Combo combo_3;
	private Button button;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	private Button button_4;
	private String monitorId="";
	private String machineId="";
	private String groupId="";
	private String monitorType;
	private String dialogTitle;
	
	protected void configureShell(Shell newShell) {
		newShell.setSize(480, 480);
		newShell.setLocation(200, 175);
		newShell.setText(dialogTitle);
		super.configureShell(newShell);
	}
	public Text getText_1() {
		return text_1;
	}
	public void setText_1(Text text_1) {
		this.text_1 = text_1;
	}
	public Text getText_2() {
		return text_2;
	}
	public void setText_2(Text text_2) {
		this.text_2 = text_2;
	}
	public Text getText_3() {
		return text_3;
	}
	public void setText_3(Text text_3) {
		this.text_3 = text_3;
	}
	public Text getText_4() {
		return text_4;
	}
	public void setText_4(Text text_4) {
		this.text_4 = text_4;
	}
	public String getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getMonitorType() {
		return monitorType;
	}
	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}
	public CreateFilterCondition(Shell parentShell,String name) {
		super(parentShell);
		this.parentShell=parentShell;
		if("编辑".equals(name)){
			name = name + "筛选条件";
		}
		this.dialogTitle = name;
	}
	private MonitorSelect createMonitorSelect(String name){
		return new MonitorSelect(null,name,this);
	}
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite=(Composite)super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		
		Group group = new Group(sashForm, SWT.NONE);
		group.setText("自定义信息");
		
		Label label = new Label(group, SWT.CENTER);
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label.setBounds(39, 39, 78, 18);
		label.setText("\u7B5B\u9009\u540D\u79F0*:");
		
		text = new Text(group, SWT.NONE);
		text.setBounds(123, 36, 193, 16);
		
		button = new Button(group, SWT.RADIO);
		button.setBounds(46, 77, 45, 16);
		button.setText("\u6DFB\u52A0");
		
		button_1 = new Button(group, SWT.RADIO);
		button_1.setBounds(97, 77, 45, 16);
		button_1.setText("\u7F16\u8F91");
		
		Group group_1 = new Group(sashForm, SWT.NONE);
		group_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		group_1.setText("条件设置");
		
		Label label_1 = new Label(group_1, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_1.setBounds(49, 29, 83, 12);
		label_1.setText("\u68C0\u6D4B\u5668\u540D\u79F0:");
		
		text_1 = new Text(group_1, SWT.BORDER);
		text_1.setBounds(136, 23, 212, 20);
		text_1.setEditable(false);
		text_1.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				String s = e.text;
				if(s!=""&&s!=null){
					button_3.setEnabled(false);
					button_4.setEnabled(false);
				}
			}
		});
		
		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_2.setBounds(52, 60, 54, 19);
		label_2.setText("\u6240\u5C5E\u8BBE\u5907:");
		
		text_2 = new Text(group_1, SWT.BORDER);
		text_2.setBounds(136, 54, 212, 20);
		text_2.setEditable(false);
		text_2.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				String s = e.text;
				if(s!=""&&s!=null){
					button_4.setEnabled(false);
				}
			}
		});
		
		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_3.setBounds(49, 92, 57, 12);
		label_3.setText("\u6240\u5C5E\u7EC4:");
		
		text_3 = new Text(group_1, SWT.BORDER);
		text_3.setBounds(136, 85, 212, 20);
		text_3.setEditable(false);
		
		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_4.setBounds(49, 122, 54, 12);
		label_4.setText("\u63CF\u8FF0:");
		
		text_4 = new Text(group_1, SWT.BORDER);
		text_4.setBounds(136, 116, 212, 20);
		
		button_2 = new Button(group_1, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("宋体",1, SWT.NORMAL));
		button_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		button_2.setBounds(354, 23, 30, 25);
		button_2.setText("监测器选择器");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Button but = (Button)e.getSource();
				String name = but.getText();
				MonitorSelect ms = createMonitorSelect(name);
				ms.open();
			}
		});
		
		button_3 = new Button(group_1, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("宋体", 1, SWT.NORMAL));
		button_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		button_3.setText("设备选择器");
		button_3.setBounds(354, 54, 30, 25);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Button but = (Button)e.getSource();
				String name = but.getText();
				MonitorSelect ms = createMonitorSelect(name);
				ms.open();
			}
		});
		
		button_4 = new Button(group_1, SWT.NONE);
		button_4.setFont(SWTResourceManager.getFont("宋体", 1, SWT.NORMAL));
		button_4.setText("组选择器");
		button_4.setBounds(354, 85, 30, 25);
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Button but = (Button)e.getSource();
				String name = but.getText();
				MonitorSelect ms = createMonitorSelect(name);
				ms.open();
			}
		});
		
		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_5.setBounds(49, 152, 66, 12);
		label_5.setText("\u663E\u793A/\u9690\u85CF:");
		
		combo = new Combo(group_1, SWT.READ_ONLY);
		combo.setBounds(136, 147, 212, 20);
		String[] items = {"显示所有","显示错误","显示正常","显示无状态","显示禁止监测器","隐藏错误","隐藏正常","隐藏无状态","隐藏禁止监测器"};
		combo.setItems(items);
		combo.select(0);
		
		combo_1 = new Combo(group_1, SWT.READ_ONLY);
		combo_1.setBounds(136, 173, 212, 20);
		ICollection ico=FileTools.getBussCollection("Ecc");
		IEnumerator ien=ico.GetEnumerator();
		Set<String> set = new HashSet<String>();
		while(ien.MoveNext()){
			BusinessObject bo = (BusinessObject)ien.get_Current();
			set.add(bo.GetField("EccType").get_NativeValue().toString());
		}
		String[] a = new String[set.size()+1];
		a = set.toArray(a);
		a[a.length-1] = "所有类型";
		combo_1.setItems(a);
		combo_1.select(a.length-1);
		
		combo_2 = new Combo(group_1, SWT.READ_ONLY);
		combo_2.setBounds(136, 199, 212, 20);
		String[] items_2 = {"状态","组","名称","描述","设备"};
		combo_2.setItems(items_2);
		combo_2.select(0);
		
		combo_3 = new Combo(group_1, SWT.READ_ONLY);
		combo_3.setEnabled(false);
		combo_3.setBounds(136, 225, 212, 20);
		String[] items_3 = {"手动刷新","自动刷新"};
		combo_3.setItems(items_3);
		combo_3.select(0);
		
		Label label_6 = new Label(group_1, SWT.READ_ONLY);
		label_6.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_6.setBounds(49, 178, 83, 12);
		label_6.setText("\u68C0\u6D4B\u5668\u7C7B\u578B:");
		
		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_7.setBounds(49, 203, 54, 12);
		label_7.setText("\u6392\u5E8F:");
		
		Label label_8 = new Label(group_1, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_8.setBounds(49, 230, 66, 12);
		label_8.setText("\u5237\u65B0\u8BBE\u7F6E:");
		if("创建筛选条件".equals(dialogTitle)){
			button_1.setEnabled(false);
		}else{
			button.setEnabled(false);
		}
		sashForm.setWeights(new int[] {124, 275});
		return composite;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button  button1= createButton(parent, IDialogConstants.OK_ID, "确定", true);
		Button  button2= createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
		
	}
	
	protected void buttonPressed(int buttonId) {
		if(buttonId==IDialogConstants.OK_ID){
			String recId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
			String createDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String conditionName = text.getText().trim();
			String GroupId = this.groupId;
			String MachineId = this.machineId;
			String MonitorId = this.monitorId;
			String monitorType = combo_1.getItem(combo_1.getSelectionIndex());
			String isVisible = combo.getItem(combo.getSelectionIndex());
			String description = text_4.getText().trim();
			String sortMethod = combo_3.getItem(combo_3.getSelectionIndex());
			
			BusinessObject bo=ConnectionBroker.get_SiteviewApi().get_BusObService().Create("EccFilter");
//			bo.s
		}else{
			this.close();
		}
	}
}
