package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;


public class CreateFilterCondition extends Dialog {
	public Shell parentShell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	public CreateFilterCondition(Shell parentShell) {
		super(parentShell);
		this.parentShell=parentShell;
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(480, 480);
		newShell.setLocation(200, 175);
		newShell.setText("创建筛选条件");
		super.configureShell(newShell);
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
		label.setBounds(39, 40, 78, 18);
		label.setText("\u7B5B\u9009\u540D\u79F0*:");
		
		text = new Text(group, SWT.NONE);
		text.setBounds(123, 35, 193, 20);
		
		Button button = new Button(group, SWT.RADIO);
		button.setBounds(46, 77, 45, 16);
		button.setText("\u6DFB\u52A0");
		
		Button button_1 = new Button(group, SWT.RADIO);
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
		
		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_2.setBounds(52, 60, 54, 19);
		label_2.setText("\u6240\u5C5E\u8BBE\u5907:");
		
		text_2 = new Text(group_1, SWT.BORDER);
		text_2.setBounds(136, 54, 212, 20);
		
		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_3.setBounds(49, 92, 57, 12);
		label_3.setText("\u6240\u5C5E\u7EC4:");
		
		text_3 = new Text(group_1, SWT.BORDER);
		text_3.setBounds(136, 85, 212, 20);
		
		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_4.setBounds(49, 122, 54, 12);
		label_4.setText("\u63CF\u8FF0:");
		
		text_4 = new Text(group_1, SWT.BORDER);
		text_4.setBounds(136, 116, 212, 20);
		
		Button button_2 = new Button(group_1, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("宋体", 5, SWT.NORMAL));
		button_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		button_2.setBounds(354, 23, 30, 25);
		button_2.setText("监测器名称");
		button_2.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				Button but = (Button)e.getSource();
				String name = but.getText();
				MonitorSelect ms = new MonitorSelect(null,name);
				ms.open();
			}
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		Button button_3 = new Button(group_1, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("宋体", 5, SWT.NORMAL));
		button_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		button_3.setText("所属设备");
		button_3.setBounds(354, 54, 30, 25);
		button_3.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				Button but = (Button)e.getSource();
				String name = but.getText();
				MonitorSelect ms = new MonitorSelect(null,name);
				ms.open();
			}
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		Button button_4 = new Button(group_1, SWT.NONE);
		button_4.setFont(SWTResourceManager.getFont("宋体", 5, SWT.NORMAL));
		button_4.setText("所属组");
		button_4.setBounds(354, 85, 30, 25);
		button_4.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				Button but = (Button)e.getSource();
				String name = but.getText();
				MonitorSelect ms = new MonitorSelect(null,name);
				ms.open();
			}
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		label_5.setBounds(49, 152, 66, 12);
		label_5.setText("\u663E\u793A/\u9690\u85CF:");
		
		Combo combo = new Combo(group_1, SWT.NONE);
		combo.setBounds(136, 147, 212, 20);
		
		Combo combo_1 = new Combo(group_1, SWT.NONE);
		combo_1.setBounds(136, 173, 212, 20);
		
		Combo combo_2 = new Combo(group_1, SWT.NONE);
		combo_2.setBounds(136, 199, 212, 20);
		
		Combo combo_3 = new Combo(group_1, SWT.NONE);
		combo_3.setEnabled(false);
		combo_3.setBounds(136, 225, 212, 20);
		
		Label label_6 = new Label(group_1, SWT.NONE);
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
			
		}else{
			this.close();
		}
	}
}
