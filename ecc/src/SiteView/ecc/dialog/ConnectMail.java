package SiteView.ecc.dialog;

import java.util.regex.Pattern;

import my.util.email.MailSenderInfo;
import my.util.email.SimpleMailSender;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;

import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;

public class ConnectMail extends Dialog{
	private Text text;
	private Text text_1;
	private BusinessObject bo;

	public ConnectMail(Shell parentShell) {
		super(parentShell);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public ConnectMail(Shell parentShell,BusinessObject bo){
		super(parentShell);
		this.bo=bo;
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(300, 300);
		newShell.setLocation(450, 175);
		newShell.setText("Email测试");
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(EccTreeControl.color);
		composite.setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(EccTreeControl.color);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		fd_lblNewLabel.right = new FormAttachment(100, -10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("\u90AE\u4EF6\u63A5\u6536\u5730\u5740\uFF08\u591A\u4E2A\u90AE\u4EF6\u4EE5\u9017\u53F7\u9694\u5F00\uFF09");
		
		text = new Text(composite, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(lblNewLabel, 0, SWT.RIGHT);
		fd_text.left = new FormAttachment(0, 10);
		text.setLayoutData(fd_text);
		
		Label label = new Label(composite, SWT.NONE);
		fd_text.bottom = new FormAttachment(100, -174);
		label.setBackground(EccTreeControl.color);
		FormData fd_label = new FormData();
		fd_label.left = new FormAttachment(0, 10);
		label.setLayoutData(fd_label);
		label.setText("\u90AE\u4EF6\u5185\u5BB9\uFF1A");
		
		text_1 = new Text(composite, SWT.BORDER | SWT.WRAP);
		fd_label.bottom = new FormAttachment(text_1, -6);
		FormData fd_text_1 = new FormData();
		fd_text_1.right = new FormAttachment(lblNewLabel, 0, SWT.RIGHT);
		fd_text_1.left = new FormAttachment(0, 10);
		fd_text_1.top = new FormAttachment(0, 82);
		fd_text_1.bottom = new FormAttachment(100, -16);
		text_1.setLayoutData(fd_text_1);
		
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
			if(text.getText()==null||text.getText().equals("")){
				MessageDialog.openInformation(new Shell(), "提示", "请填写Email");
				return;
			}else if(!regex.matcher(text.getText()).matches()){
				MessageDialog.openInformation(new Shell(), "提示", "Email格式不对");
				return;
			}else if(text_1.getText()==null||text_1.getText().equals("")){
				MessageDialog.openInformation(new Shell(), "提示", "请填写邮件内容");
				return;
			}
			 MailSenderInfo mailInfo = new MailSenderInfo();    
		     mailInfo.setMailServerHost(bo.GetField("SendServer").get_NativeValue().toString());    
		     mailInfo.setMailServerPort("25");    
		     mailInfo.setValidate(true);    
		     mailInfo.setUserName(bo.GetField("MailUserName").get_NativeValue().toString());    
		     mailInfo.setPassword(bo.GetField("MailPwd").get_NativeValue().toString());//您的邮箱密码    
		     mailInfo.setFromAddress(bo.GetField("MailAddress").get_NativeValue().toString());    
		     mailInfo.setToAddress(text.getText());    
		     mailInfo.setSubject("邮件测试");    
		     mailInfo.setContent(text_1.getText());    
		        //这个类主要来发送邮件   
		     SimpleMailSender sms = new SimpleMailSender();   
		         //sms.sendTextMail(mailInfo);//发送文体格式    
		     sms.sendHtmlMail(mailInfo);//发送html格式   
		}else{
			this.close();
		}
	}
}
