package SiteView.ecc.dialog;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import COM.dragonflow.Properties.Fetion;

public class ConnectSMS extends Dialog {
	private Text text;
	String phone;
	String pwd;

	public ConnectSMS(Shell parentShell,String phone,String pwd) {
		super(parentShell);
		this.phone = phone;
		this.pwd = pwd;
	}
	
	protected void configureShell(Shell newShell) {
		newShell.setText("短信测试");
		newShell.setLocation(450, 150);
		newShell.setSize(350, 110);
		super.configureShell(newShell);
	}
	
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite =(Composite)super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(null);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 8, 160, 18);
		lblNewLabel.setText("\u8F93\u5165\u63A5\u6536\u4FE1\u606F\u7684\u624B\u673A\u53F7\u7801\uFF1A");
		
		text = new Text(composite, SWT.BORDER);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.setBounds(175, 8, 160, 18);
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button button = createButton(parent, IDialogConstants.OK_ID, "发送", true);
		Button button1 = createButton(parent, IDialogConstants.CANCEL_ID, "关闭", true);
	}
	
	protected void buttonPressed(int buttonId) {
		if(buttonId == IDialogConstants.OK_ID){
			try {
				Fetion.sendMsg(phone, pwd, text.getText(), "测试成功");
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.close();
		}else{
			this.close();
		}
	}
}
