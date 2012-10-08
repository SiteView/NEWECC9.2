package SiteView.ecc.dialog;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import SiteView.ecc.tools.FileTools;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class AddSMSModleSet extends Dialog{
public String name;
public BusinessObject bo;
public static ListViewer listViewer;
private Text text;
private Text text_1;
private Label lblNewLabel_2;
private Text text_2;
String type;
public static List<BusinessObject> messagemodels = AddEmailAlarmRule.messagemodels;
	public AddSMSModleSet(Shell parentShell, String name,BusinessObject bo) {
		super(parentShell);
		this.name = name;
		this.bo=bo;
	}
	protected void configureShell(Shell newShell){
		newShell.setSize(500, 350);
		newShell.setLocation(200, 175);
		if("短信模板设置".equals(name)){
			newShell.setText("短信模板设置");
		}else if("Web短信模板设置".equals(name)){
			newShell.setText("Web短信模板设置");
		}
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent){
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Group grpEmail = new Group(sashForm, SWT.NONE);
		grpEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		if("短信模板设置".equals(name)){
			grpEmail.setText("短信模板系列:");
			grpEmail.setLayout(new FillLayout());
			listViewer = new ListViewer(grpEmail, SWT.BORDER | SWT.V_SCROLL);
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
				listViewer.add(messagemodels.get(i).GetField("MailModle").get_NativeValue().toString());
				listViewer.setData(messagemodels.get(i).GetField("MailModle").get_NativeValue().toString(), messagemodels.get(i));
			}
			listViewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent event) {
					IStructuredSelection selection = (IStructuredSelection)event.getSelection();
					 for(Iterator iterator = selection.iterator(); iterator.hasNext(); ) {
				          type=iterator.next().toString();
				        }
					text.setText(((BusinessObject) listViewer.getData(type)).GetField("MailTitle").get_NativeValue().toString());
					text_1.setText(((BusinessObject) listViewer.getData(type)).GetField("MailContent").get_NativeValue().toString());
				}
			});
		}else if("Web短信模板设置".equals(name)){
			grpEmail.setText("Web短信模板系列:");
			grpEmail.setLayout(new FillLayout());
			listViewer = new ListViewer(grpEmail, SWT.BORDER | SWT.V_SCROLL);
		}
			
			Group grpEmail_1 = new Group(sashForm, SWT.NONE);
			grpEmail_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			if("短信模板设置".equals(name)){
				grpEmail_1.setText("短信模板设置");
				
				Label lblNewLabel = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel.setBounds(42, 23, 83, 18);
				lblNewLabel.setText("\u77ED\u4FE1\u6807\u9898*\uFF1A");
				lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				
				text = new Text(grpEmail_1, SWT.BORDER);
				text.setBounds(42, 48, 233, 18);
				
				Label lblNewLabel_1 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel_1.setBounds(42, 80, 83, 18);
				lblNewLabel_1.setText("\u77ED\u4FE1\u5185\u5BB9:");
				lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				
				text_1 = new Text(grpEmail_1, SWT.BORDER | SWT.WRAP);
				text_1.setBounds(42, 104, 233, 74);
				
				lblNewLabel_2 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel_2.setBounds(42, 196, 83, 18);
				lblNewLabel_2.setText("\u6A21\u677F\u6807\u9898*:");
				lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				
				text_2 = new Text(grpEmail_1, SWT.BORDER);
				text_2.setBounds(42, 220, 233, 18);
				
			}else if("Web短信模板设置".equals(name)){
				grpEmail_1.setText("Web短信模板设置");
				
				Label lblNewLabel = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel.setBounds(42, 23, 90, 18);
				lblNewLabel.setText("Web发送模板*:");
				lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				
				text = new Text(grpEmail_1, SWT.BORDER);
				text.setBounds(42, 48, 233, 18);
				
				Label lblNewLabel_1 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel_1.setBounds(42, 80, 83, 18);
				lblNewLabel_1.setText("短信内容:");
				lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				
				text_1 = new Text(grpEmail_1, SWT.BORDER);
				text_1.setBounds(42, 104, 233, 74);
				
				lblNewLabel_2 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel_2.setBounds(42, 196, 83, 18);
				lblNewLabel_2.setText("模板标题*:");
				lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				
				text_2 = new Text(grpEmail_1, SWT.BORDER);
				text_2.setBounds(42, 220, 233, 18);
			}
			
			sashForm.setWeights(new int[] {97, 344});
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "添加",true);
		Button cancelButton = createButton(parent, IDialogConstants.ABORT_ID,"删除", true);
		Button cancelButton_1 = createButton(parent, IDialogConstants.BACK_ID,"更新", true);
		Button cancelButton_2 = createButton(parent, IDialogConstants.CANCEL_ID,"关闭", true);
	}
	
	protected void buttonPressed(int buttonId) {
		if(buttonId==IDialogConstants.OK_ID){
			ICollection ico=FileTools.getBussCollection("EccMailModle");
			IEnumerator ienum=ico.GetEnumerator();
			while(ienum.MoveNext()){
				BusinessObject businessObject=(BusinessObject)ienum.get_Current();
				String ModleType=businessObject.GetField("ModleType").get_NativeValue().toString();
				String MailModle=businessObject.GetField("MailModle").get_NativeValue().toString();
				if("SMS".equals(ModleType)&&text_2.getText().equals(MailModle)){//判断添加的元素是否已经存在
	            	MessageDialog.openInformation(null, "提示", "此模板名已存在,请换一个模板名!");
	            	return;
				}
				if("SMS".equals(ModleType)&&text_2.getText().equals("")){//判断添加的元素是否已经存在
	            	MessageDialog.openInformation(null, "提示", "模板名称不能为空,请重新输入!");
	            	return;
				}
			}
			BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("EccMailModle");
			bo.GetField("MailTitle").SetValue(new SiteviewValue(text.getText()));
			bo.GetField("MailContent").SetValue(new SiteviewValue(text_1.getText()));
			bo.GetField("MailModle").SetValue(new SiteviewValue(text_2.getText()));
			bo.GetField("ModleType").SetValue(new SiteviewValue("SMS"));
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			listViewer.add(text_2.getText());
			listViewer.setData(text_2.getText(), bo);
		}else if(buttonId==IDialogConstants.ABORT_ID){
			BusinessObject bo = (BusinessObject)listViewer.getData(type);
			bo.DeleteObject(ConnectionBroker.get_SiteviewApi());
			listViewer.remove(bo.GetField("MailModle").get_NativeValue().toString());
		}else if(buttonId==IDialogConstants.BACK_ID){
			
		}else{
			this.close();
		}
	}
}
