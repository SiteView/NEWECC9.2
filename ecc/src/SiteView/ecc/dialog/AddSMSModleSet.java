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
import SiteView.ecc.view.EccTreeControl;
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
private Text text_3;
	public AddSMSModleSet(Shell parentShell, String name,BusinessObject bo) {
		super(parentShell);
		this.name = name;
		this.bo=bo;
	}
	protected void configureShell(Shell newShell){
		newShell.setSize(500, 550);
		newShell.setLocation(400, 125);
		if("短信模板设置".equals(name)){
			newShell.setText("短信模板设置");
		}else if("Web短信模板设置".equals(name)){
			newShell.setText("Web短信模板设置");
		}
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent){
		parent.setBackground(EccTreeControl.color);
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(EccTreeControl.color);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setBackground(EccTreeControl.color);
		
		Group grpEmail = new Group(sashForm, SWT.NONE);
		grpEmail.setBackground(EccTreeControl.color);
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
			
			SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);
			sashForm_1.setBackground(EccTreeControl.color);
			
			Group grpEmail_1 = new Group(sashForm_1, SWT.NONE);
			grpEmail_1.setBackground(EccTreeControl.color);
			
			Group group = new Group(sashForm_1, SWT.NONE);
			group.setBackground(EccTreeControl.color);
			group.setLayout(new FillLayout(SWT.HORIZONTAL));
			
			sashForm_1.setWeights(new int[] {3, 2});
			if("短信模板设置".equals(name)){
				grpEmail_1.setText("短信模板设置");
				
				Label lblNewLabel = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel.setBounds(42, 23, 83, 18);
				lblNewLabel.setText("\u77ED\u4FE1\u6807\u9898*\uFF1A");
				lblNewLabel.setBackground(EccTreeControl.color);
				
				text = new Text(grpEmail_1, SWT.BORDER);
				text.setBounds(42, 48, 233, 18);
				
				Label lblNewLabel_1 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel_1.setBounds(42, 80, 83, 18);
				lblNewLabel_1.setText("\u77ED\u4FE1\u5185\u5BB9:");
				lblNewLabel_1.setBackground(EccTreeControl.color);
				
				text_1 = new Text(grpEmail_1, SWT.BORDER | SWT.WRAP);
				text_1.setBounds(42, 104, 233, 74);
				
				lblNewLabel_2 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel_2.setBounds(42, 196, 83, 18);
				lblNewLabel_2.setText("\u6A21\u677F\u6807\u9898*:");
				lblNewLabel_2.setBackground(EccTreeControl.color);
				
				text_2 = new Text(grpEmail_1, SWT.BORDER);
				text_2.setBounds(42, 220, 233, 18);
				
				group.setText("说明列表");
				text_3 = new Text(group, SWT.BORDER |SWT.WRAP);
				text_3.setText("注意：系统模板不可以删除和修改你可以拷贝参数，" +
						"请不要随意输入“@”和两个“@”间的参数变量,可以参考系统模板进行添加。" +
						"参数变量如下：@FullPathGroup@ :监测点所在设备所在组的全部路径名称" +
						"@Status@监测器点的状态@AllGroup@ :监测点所在设备所在组名称" +
						"@Group@监测点所在组下@Device@ :监测点所在设备的名称@Monitor@ :" +
						"监测点名称@MonitorDstr@ :监测点报警描述，在监测点高级设置中设置@Time@ : 报警时间");
				text_3.setBackground(EccTreeControl.color);
				text_3.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
				text_3.setEditable(false);
				
			}else if("Web短信模板设置".equals(name)){
				grpEmail_1.setText("Web短信模板设置");
				
				Label lblNewLabel = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel.setBounds(42, 23, 90, 18);
				lblNewLabel.setText("Web发送模板*:");
				lblNewLabel.setBackground(EccTreeControl.color);
				
				text = new Text(grpEmail_1, SWT.BORDER);
				text.setBounds(42, 48, 233, 18);
				
				Label lblNewLabel_1 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel_1.setBounds(42, 80, 83, 18);
				lblNewLabel_1.setText("短信内容:");
				lblNewLabel_1.setBackground(EccTreeControl.color);
				
				text_1 = new Text(grpEmail_1, SWT.BORDER);
				text_1.setBounds(42, 104, 233, 74);
				
				lblNewLabel_2 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
				lblNewLabel_2.setBounds(42, 196, 83, 18);
				lblNewLabel_2.setText("模板标题*:");
				lblNewLabel_2.setBackground(EccTreeControl.color);
				
				text_2 = new Text(grpEmail_1, SWT.BORDER);
				text_2.setBounds(42, 220, 233, 18);
				
				group.setText("说明列表");
				text_3 = new Text(group, SWT.BORDER |SWT.WRAP);
				text_3.setText("注意：系统模板不可以删除和修改你可以拷贝参数，" +
						"请不要随意输入“@”和两个“@”间的参数变量,可以参考系统模板进行添加。" +
						"Web模板参数变量如下：@UserName@ : 表示用户名 @Pwd@ : 表示密码 " +
						"@Phone@ : 表示电话号码 @Content@ ： 短信表示内容短信内容参数如下：" +
						"@FullPathGroup@ :监测点所在设备所在组的全部路径名称@Status" +
						"@监测器点的状态@AllGroup@ :监测点所在设备所在组名称@Group" +
						"@监测点所在组下@Device@ :监测点所在设备的名称@monitor@ :" +
						"监测点名称@MonitorDes@ :监测点报警描述，在监测点高级设置中设置@Time@ : " +
						"报警时间@Status@ :监测点状态 : 危险、正常、错误@Log@ :日志内容");
				text_3.setBackground(EccTreeControl.color);
				text_3.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
				text_3.setEditable(false);
			}
			
			sashForm.setWeights(new int[] {97, 220});
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(EccTreeControl.color);
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
