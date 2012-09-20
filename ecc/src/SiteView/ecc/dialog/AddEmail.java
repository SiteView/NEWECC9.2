package SiteView.ecc.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import my.util.email.MailSenderInfo;
import my.util.email.SimpleMailSender;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import SiteView.ecc.Modle.EmailModle;
import SiteView.ecc.editors.EmailSetUp;
import SiteView.ecc.tools.FileTools;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class AddEmail extends Dialog{
	private Text text_2;
	private Text text_3;
	private Combo text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	Button button;
	private EmailModle email;
	/**
	 * @wbp.parser.constructor
	 */
	public AddEmail(Shell parentShell) {
		super(parentShell);
	}
	public AddEmail(Shell parentShell,EmailModle email) {
		super(parentShell);
		this.email=email;
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(450, 420);
		newShell.setLocation(400, 175);
		newShell.setText("Email测试");
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent) {
		if(MailModleSetUp.modles==null){
			MailModleSetUp.modles=new ArrayList<BusinessObject>();
			ICollection ico=FileTools.getBussCollection("EccMailModle");
			IEnumerator ie=ico.GetEnumerator();
			while(ie.MoveNext()){
				MailModleSetUp.modles.add((BusinessObject)ie.get_Current());
			}
		}
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Group group = new Group(sashForm, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		group.setText("\u57FA\u672C\u5C5E\u6027");
		
		Label label = new Label(group, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label.setBounds(10, 20, 83, 12);
		label.setText("\u540D\u79F0\uFF1A");
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setBounds(113, 14, 307, 18);
		
		Label lblemail = new Label(group, SWT.NONE);
		lblemail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblemail.setBounds(10, 48, 97, 12);
		lblemail.setText("\u63A5\u6536email\u5730\u5740\uFF1A");
		
		text_3 = new Text(group, SWT.BORDER);
		text_3.setBounds(113, 45, 307, 18);
		
		button = new Button(group, SWT.CHECK);
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		button.setBounds(113, 67, 51, 16);
		button.setText("\u7981\u6B62");
		
		Group group_1 = new Group(sashForm, SWT.NONE);
		group_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		group_1.setText("\u9AD8\u7EA7\u5C5E\u6027");
		
		Label lblEmail = new Label(group_1, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblEmail.setBounds(10, 21, 93, 12);
		lblEmail.setText("email\u6A21\u677F\uFF1A");
		
		text_4 = new Combo(group_1, SWT.NONE);
		text_4.setBounds(109, 15, 304, 18);
		for(BusinessObject bo:MailModleSetUp.modles){
			text_4.add(bo.GetField("MailTitle").get_NativeValue().toString());
		}
		
		Label label_1 = new Label(group_1, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_1.setBounds(10, 53, 93, 12);
		label_1.setText("\u4EFB\u52A1\u8BA1\u5212\u7C7B\u578B\uFF1A");
		
		text_5 = new Text(group_1, SWT.BORDER);
		text_5.setBounds(109, 50, 304, 18);
		
		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_2.setBounds(10, 84, 76, 12);
		label_2.setText("\u4EFB\u52A1\u8BA1\u5212\uFF1A");
		
		text_6 = new Text(group_1, SWT.BORDER);
		text_6.setBounds(109, 81, 304, 18);
		
		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_3.setBounds(10, 133, 93, 12);
		label_3.setText("\u63CF\u8FF0\uFF1A");
		
		text_7 = new Text(group_1, SWT.BORDER | SWT.WRAP);
		text_7.setBounds(109, 106, 304, 130);
		sashForm.setWeights(new int[] {63, 165});
		if(email!=null){
			BusinessObject bo=email.getBo();
			button.setSelection(!(Boolean)(bo.GetField("IsStop").get_NativeValue()));
			text_2.setText(bo.GetField("SetName").get_NativeValue().toString());
			text_3.setText(bo.GetField("MailAddress").get_NativeValue().toString());
			text_4.setText(bo.GetField("MailModle").get_NativeValue().toString());
			text_5.setText(bo.GetField("MailScheduleType").get_NativeValue().toString());
			text_6.setText(bo.GetField("MailSchedule").get_NativeValue().toString());
			text_7.setText(bo.GetField("Description").get_NativeValue().toString());
		}
		
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "保存",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", true);
	}
	protected void buttonPressed(int buttonId) {
		 String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		 Pattern regex = Pattern.compile(check); 
		if(buttonId==IDialogConstants.OK_ID){
			if(text_2.getText()==null||text_2.getText().equals("")){
				MessageDialog.openInformation(new Shell(), "提示", "请填写名称");
				return;
			}else if(text_3.getText()==null||text_3.getText().equals("")){
				MessageDialog.openInformation(new Shell(), "提示", "请填写Email");
				return;
			}else if(!regex.matcher(text_3.getText()).matches()){
				MessageDialog.openInformation(new Shell(), "提示", "Email格式不对");
				return;
			}
			
			BusinessObject bo=null;
			if(email!=null){
				bo=email.getBo();
			}else{
				bo=ConnectionBroker.get_SiteviewApi().get_BusObService().Create("EccMail");
			}
			bo.GetField("IsStop").SetValue(new SiteviewValue(!button.getSelection()));
			bo.GetField("MailType").SetValue(new SiteviewValue("receiver"));
			bo.GetField("SetName").SetValue(new SiteviewValue(text_2.getText()));
			bo.GetField("MailAddress").SetValue(new SiteviewValue(text_3.getText()));
			bo.GetField("MailModle").SetValue(new SiteviewValue(text_4.getText()));
			bo.GetField("MailScheduleType").SetValue(new SiteviewValue(text_5.getText()));
			bo.GetField("MailSchedule").SetValue(new SiteviewValue(text_6.getText()));
			bo.GetField("Description").SetValue(new SiteviewValue(text_7.getText()));
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), false, true);
			if(email!=null){
				email.setBo(bo);
				EmailSetUp.tableViewer.update(email, new String[]{"bo"});
			}else{
				email=new EmailModle(bo);
				EmailSetUp.tableViewer.insert(email, 0);
			}
			EmailSetUp.list=(List<EmailModle>) EmailSetUp.tableViewer.getInput();
			EmailSetUp.tableViewer.refresh();
		}
		this.close();
	}
}
