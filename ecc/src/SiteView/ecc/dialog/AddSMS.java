package SiteView.ecc.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Combo;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

import SiteView.ecc.tools.FileTools;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class AddSMS extends Dialog {
	private Text text;
	private Text text_1;
	public static List<BusinessObject> messagemodels=AddEmailAlarmRule.messagemodels;
	Button button;
	Combo combo;
	Combo combo_1;
	Combo combo_2;
	Combo combo_3;

	public AddSMS(Shell parentShell) {
		super(parentShell);
	}
	
	protected void configureShell(Shell newShell) {
		newShell.setSize(450, 320);
		newShell.setLocation(400, 175);
		newShell.setText("����ֻ���������");
		super.configureShell(newShell);
	}
	
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite, SWT.NONE);
		group.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		group.setText("\u624B\u673A\u53F7\u7801\u4FE1\u606F");
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group.setLayout(null);
		
		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(70, 20, 90, 18);
		label.setText("\u540D\u79F0*\uFF1A");
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(70, 50, 90, 18);
		label_1.setText("\u63A5\u6536\u624B\u673A\u53F7\u7801*\uFF1A");
		
		text = new Text(group, SWT.BORDER);
		text.setBounds(170, 20, 200, 18);
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(170, 50, 200, 18);
		text_1.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});
		
		button = new Button(group, SWT.CHECK);
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button.setBounds(170, 80, 90, 16);
		button.setText("\u7981\u6B62");
		
		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(70, 110, 90, 20);
		lblNewLabel.setText("\u6A21\u677F\u7C7B\u578B\uFF1A");
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(70, 140, 90, 20);
		label_2.setText("\u4FE1\u606F\u6A21\u677F*\uFF1A");
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(70, 170, 90, 20);
		label_3.setText("\u4EFB\u52A1\u8BA1\u5212\u7C7B\u578B\uFF1A");
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(70, 200, 90, 20);
		label_4.setText("\u4EFB\u52A1\u8BA1\u5212*\uFF1A");
		
		combo = new Combo(group, SWT.NONE);
		combo.setBounds(170, 110, 200, 20);
		combo.add("feixin");
		combo.select(0);
		
		combo_1 = new Combo(group, SWT.NONE);
		combo_1.setBounds(170, 140, 200, 20);
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
			combo_1.add(messagemodels.get(i).GetField("MailModle")
					.get_NativeValue().toString());
		}
		combo_1.select(0);
		
		combo_2 = new Combo(group, SWT.NONE);
		combo_2.setBounds(170, 170, 200, 20);
		combo_2.add("����ʱ������ƻ�");
		combo_2.add("ʱ�������ƻ�");
		combo_2.add("���ʱ������ƻ�");
		combo_2.select(0);
		
		combo_3 = new Combo(group, SWT.NONE);
		combo_3.setBounds(170, 200, 200, 20);
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "����",true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,"ȡ��", true);
	}
	
	protected void buttonPressed(int buttonId) {
		if(buttonId==IDialogConstants.OK_ID){
			if(text.getText()==null||text.getText().equals("")){
				MessageDialog.openInformation(new Shell(), "��ʾ", "����д����");
				return;
			}else if(text_1.getText()==null||text_1.getText().equals("")){
				MessageDialog.openInformation(new Shell(), "��ʾ", "����д�ֻ�����");
				return;
			}else if(text_1.getText().length()>11){
				MessageDialog.openInformation(new Shell(), "��ʾ", "��д���ֻ����벻����");
				return;
			}
			BusinessObject bo = null;
//			if(SMS!=null){
				
//			}else{
				bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("EccSMS");
//			}
				bo.GetField("SetName").SetValue(new SiteviewValue(text.getText()));
				bo.GetField("IsStop").SetValue(new SiteviewValue(button.getSelection()));
				bo.GetField("MobliePhone").SetValue(new SiteviewValue(text_1.getText()));
				bo.GetField("MouldType").SetValue(new SiteviewValue(combo.getText()));
				bo.GetField("MouldId").SetValue(new SiteviewValue(""));
				bo.GetField("TaskPlanType").SetValue(new SiteviewValue(combo_2.getText()));
				bo.GetField("TaskPlanId").SetValue(new SiteviewValue(""));
				bo.GetField("SMSType").SetValue(new SiteviewValue("receive"));
		}else{
			this.close();
		}
	}
}
