package SiteView.ecc.dialog;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class MailModleSetUp extends Dialog{
	private Text text;
	private Text text_1;
	private Text text_2;
	private BusinessObject bo;

	public MailModleSetUp(Shell parentShell) {
		super(parentShell);
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(600, 350);
		newShell.setLocation(200, 175);
		newShell.setText("Email模版设置");
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent) {
		java.util.List<BusinessObject> modles=new ArrayList<BusinessObject>();
		ICollection ico=FileTools.getBussCollection("EccMailModle");
		IEnumerator ie=ico.GetEnumerator();
		while(ie.MoveNext()){
			modles.add((BusinessObject)ie.get_Current());
		}
		
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		
		Group grpEmail = new Group(sashForm, SWT.NONE);
		grpEmail.setText("Email\u6A21\u677F\u5217\u8868");
		grpEmail.setLayout(new FillLayout());
		List list=new List(grpEmail, SWT.NONE);
		list.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		for(BusinessObject bo:modles){
			if(this.bo==null){
				this.bo=bo;
			}
			list.add(bo.GetField("MailModle").get_NativeValue().toString());
			list.setData(bo);
		}
		list.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
//		list.addSelectionListener(new SelectionListener() {
//			public void widgetSelected(SelectionEvent e) {
//				bo=(BusinessObject) e.item.getData();
//				text.setText(bo.GetField("MailTitle").get_NativeValue().toString());
//				text_1.setText(bo.GetField("MailContent").get_NativeValue().toString());
//				text_2.setText(bo.GetField("MailModle").get_NativeValue().toString());
//			}
//			public void widgetDefaultSelected(SelectionEvent e) {}
//		});
		Group grpEmail_1 = new Group(sashForm, SWT.NONE);
		grpEmail_1.setText("Email\u6A21\u677F\u8BBE\u7F6E");
		
		Label label = new Label(grpEmail_1, SWT.NONE);
		label.setBounds(10, 36, 54, 12);
		label.setText("\u90AE\u4EF6\u6807\u9898\uFF1A");
		
		text = new Text(grpEmail_1, SWT.BORDER);
		text.setBounds(70, 30, 344, 18);
		text.setText(bo.GetField("MailTitle").get_NativeValue().toString());
		
		Label label_1 = new Label(grpEmail_1, SWT.NONE);
		label_1.setBounds(10, 80, 54, 12);
		label_1.setText("\u90AE\u4EF6\u5185\u5BB9\uFF1A");
		
		text_1 = new Text(grpEmail_1, SWT.BORDER | SWT.WRAP);
		text_1.setBounds(70, 74, 344, 128);
		text_1.setText(bo.GetField("MailContent").get_NativeValue().toString());
		
		Label label_2 = new Label(grpEmail_1, SWT.NONE);
		label_2.setBounds(10, 231, 60, 12);
		label_2.setText("\u90AE\u4EF6\u6A21\u677F\uFF1A");
		
		text_2 = new Text(grpEmail_1, SWT.BORDER);
		text_2.setBounds(70, 225, 344, 18);
		text_2.setText(bo.GetField("MailModle").get_NativeValue().toString());
		sashForm.setWeights(new int[] {100, 341});
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "添加",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.ABORT_ID,
				"删除", true);
		Button cancelButton_1 = createButton(parent, IDialogConstants.BACK_ID,
				"更新", true);
		Button cancelButton_2 = createButton(parent, IDialogConstants.CANCEL_ID,
				"关闭", true);
		Button cancelButton_3 = createButton(parent, IDialogConstants.CLOSE_ID,
				"系统变量", true);
	}
}
