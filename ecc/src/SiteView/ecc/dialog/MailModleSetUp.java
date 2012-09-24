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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import SiteView.ecc.Modle.EmailModle;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import system.Web.UI.WebControls.ListItem;
import system.Windows.Forms.ListView;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;

public class MailModleSetUp extends Dialog{
	private Text text;
	private Text text_1;
	private Text text_2;
	public static java.util.List<BusinessObject> modles=null;
	public static ArrayList<EmailModle> list1=null;
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
		if(modles==null){
			modles=new ArrayList<BusinessObject>();
			ICollection ico=FileTools.getBussCollection("EccMailModle");
			IEnumerator ie=ico.GetEnumerator();
			while(ie.MoveNext()){
				modles.add((BusinessObject)ie.get_Current());
			}
		}
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		
		Group grpEmail = new Group(sashForm, SWT.NONE);
		grpEmail.setText("Email\u6A21\u677F\u5217\u8868");
		grpEmail.setLayout(new FillLayout());
		
		final ListViewer listViewer = new ListViewer(grpEmail, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		list.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		list1=new ArrayList<EmailModle>();
		
		for(BusinessObject bo:modles){
			if(this.bo==null){
				this.bo=bo;
			}
			String ModleType=bo.GetField("ModleType").get_NativeValue().toString();
			if("email".equals(ModleType)){
				listViewer.add(bo.GetField("MailModle").get_NativeValue().toString());
			
				String MailTitle=bo.GetField("MailTitle").get_NativeValue().toString();
				String MailContent=bo.GetField("MailContent").get_NativeValue().toString();
				String MailModle=bo.GetField("MailModle").get_NativeValue().toString();
				
				EmailModle em=new EmailModle(bo);
				em.setMailTitle(MailTitle);
				em.setMailContent(MailContent);
				em.setMailModle(MailModle);
				
				list1.add(em);
			    //listViewer.setInput(list1);
			}
		}
		
		listViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				 ISelection selection = listViewer.getSelection();
			     Object bb = ((IStructuredSelection)selection).getFirstElement();
               
//			     text.setText(bo.GetField("MailTitle").get_NativeValue().toString());
//			     text_1.setText(bo.GetField("MailContent").get_NativeValue().toString());
			}
		});
		Group grpEmail_1 = new Group(sashForm, SWT.NONE);
		grpEmail_1.setText("Email\u6A21\u677F\u8BBE\u7F6E");
		
		Label label = new Label(grpEmail_1, SWT.NONE);//邮件标题
		label.setBounds(10, 36, 54, 12);
		label.setText("\u90AE\u4EF6\u6807\u9898\uFF1A");
		
		text = new Text(grpEmail_1, SWT.BORDER);
		text.setBounds(70, 30, 344, 18);
		
		
		Label label_1 = new Label(grpEmail_1, SWT.NONE);//邮件内容
		label_1.setBounds(10, 80, 54, 12);
		label_1.setText("\u90AE\u4EF6\u5185\u5BB9\uFF1A");
		
		text_1 = new Text(grpEmail_1, SWT.BORDER | SWT.WRAP);
		text_1.setBounds(70, 74, 344, 128);
		
		
		Label label_2 = new Label(grpEmail_1, SWT.NONE);//邮件模板
		label_2.setBounds(10, 231, 60, 12);
		label_2.setText("\u90AE\u4EF6\u6A21\u677F\uFF1A");
		
		text_2 = new Text(grpEmail_1, SWT.BORDER);
		text_2.setBounds(70, 225, 344, 18);
	
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
