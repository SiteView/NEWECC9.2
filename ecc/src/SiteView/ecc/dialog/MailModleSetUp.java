package SiteView.ecc.dialog;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import system.Web.UI.WebControls.ListItem;
import system.Windows.Forms.ListView;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class MailModleSetUp extends Dialog{
	private Text text;
	private Text text_1;
	private Text text_2;
	private Button subButton;
	private Button cancelButton;
	private Button cancelButton_1;
	private Button cancelButton_2;
	private Button cancelButton_3;
	public static java.util.List<BusinessObject> modles=null;
	public static ArrayList<EmailModle> list1=null;
	public static ListViewer listViewer;
    public Object bb;
    public Shell parentShell;
    public Group grpEmail_1;
    private Group group;
    private Text text_3;
	public MailModleSetUp(Shell parentShell) {
		super(parentShell);
		this.parentShell=parentShell;
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(620, 480);
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
		
	    listViewer = new ListViewer(grpEmail, SWT.BORDER | SWT.V_SCROLL);
		list1=new ArrayList<EmailModle>();
		
		for(BusinessObject bo:modles){
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
			}
		}
		
		listViewer.addDoubleClickListener(new IDoubleClickListener() {//双击事件:选中左边列表中的元素右边显示相应的信息
			public void doubleClick(DoubleClickEvent event) {
				 ISelection selection = listViewer.getSelection();
			     bb = ((IStructuredSelection)selection).getFirstElement();//得到列表框中当前选中的元素
			     ICollection ico=FileTools.getBussCollection("EccMailModle");
			     IEnumerator ienum=ico.GetEnumerator();
			     while(ienum.MoveNext()){
			    	 BusinessObject bj=(BusinessObject)ienum.get_Current();
			    	 if(bj!=null){
			    		 String MailModle=bj.GetField("MailModle").get_NativeValue().toString();
			    		 String ModleType=bj.GetField("ModleType").get_NativeValue().toString();
			    		 if(bb.equals(MailModle)&&"email".equals(ModleType)){
						     text.setText(bj.GetField("MailTitle").get_NativeValue().toString());
						     text_1.setText(bj.GetField("MailContent").get_NativeValue().toString());
			    		 }
			    	 }
			     }

			}
		});
		grpEmail_1 = new Group(sashForm, SWT.NONE);
		grpEmail_1.setText("Email\u6A21\u677F\u8BBE\u7F6E");
		
		Label label = new Label(grpEmail_1, SWT.NONE);//邮件标题
		label.setBounds(10, 36, 54, 12);
		label.setText("\u90AE\u4EF6\u6807\u9898\uFF1A");
		
		text = new Text(grpEmail_1, SWT.BORDER);
		text.setBounds(70, 30, 344, 18);
		
		
		Label label_1 = new Label(grpEmail_1, SWT.NONE);//邮件内容
		label_1.setBounds(10, 67, 54, 12);
		label_1.setText("\u90AE\u4EF6\u5185\u5BB9\uFF1A");
		
		text_1 = new Text(grpEmail_1, SWT.BORDER | SWT.WRAP);
		text_1.setLocation(70, 64);
		text_1.setSize(344, 128);
		
		
		Label label_2 = new Label(grpEmail_1, SWT.NONE);//邮件模板
		label_2.setBounds(10, 218, 60, 12);
		label_2.setText("\u90AE\u4EF6\u6A21\u677F\uFF1A");
		
		text_2 = new Text(grpEmail_1, SWT.BORDER);
		text_2.setBounds(70, 215, 344, 18);
		
		sashForm.setWeights(new int[] {147, 403});
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		 subButton = createButton(parent, IDialogConstants.OK_ID, "添加",
				true);
		 cancelButton = createButton(parent, IDialogConstants.ABORT_ID,
				"删除", true);
		 cancelButton_1 = createButton(parent, IDialogConstants.BACK_ID,
				"更新", true);
		 cancelButton_2 = createButton(parent, IDialogConstants.CANCEL_ID,
				"关闭", true);
		 cancelButton_3 = createButton(parent, IDialogConstants.CLOSE_ID,
				"系统变量说明", true);
	}
	protected void buttonPressed(int buttonId){
		L1:if(buttonId==IDialogConstants.OK_ID){//添加
			ICollection ico=FileTools.getBussCollection("EccMailModle");
			IEnumerator ienum=ico.GetEnumerator();
			while(ienum.MoveNext()){
				BusinessObject businessObject=(BusinessObject)ienum.get_Current();
				String ModleType=businessObject.GetField("ModleType").get_NativeValue().toString();
				String MailModle=businessObject.GetField("MailModle").get_NativeValue().toString();
				if("email".equals(ModleType)&&text_2.getText().equals(MailModle)){//判断添加的元素是否已经存在
	            	MessageDialog.openInformation(parentShell, "提示", "此模板名已存在,请换一个模板名!");
	            	break L1;
				}
				if("email".equals(ModleType)&&text_2.getText().equals("")){//判断添加的元素是否已经存在
	            	MessageDialog.openInformation(parentShell, "提示", "模板名称不能为空,请重新输入!");
	            	break L1;
				}
			}
			BusinessObject obj=ConnectionBroker.get_SiteviewApi()//得到对应的数据库表
					.get_BusObService().Create("EccMailModle");
			obj.GetField("MailTitle").SetValue(new SiteviewValue(text.getText()));//邮件标题
			obj.GetField("MailContent").SetValue(new SiteviewValue(text_1.getText()));//邮件内容
			obj.GetField("MailModle").SetValue(new SiteviewValue(text_2.getText()));//邮件模板
			obj.GetField("ModleType").SetValue(new SiteviewValue("email"));
			obj.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//将添加的元素的数据存储到数据库中
			listViewer.add(obj.GetField("MailModle").get_NativeValue().toString());//在列表框中添加元素
		}
	
		L2:if(buttonId==IDialogConstants.ABORT_ID){//删除
			if("Default".equals(bb)||"SelfDefine".equals(bb)||"LogTemplate".equals(bb)){//判断是不是自定义模板
				MessageDialog.openInformation(parentShell, "提示", "系统自定义模板不能被删除!");
				break L2;
			 }
				     ICollection ico=FileTools.getBussCollection("EccMailModle");
				     IEnumerator ienum=ico.GetEnumerator();
				     while(ienum.MoveNext()){
				    	 BusinessObject bv=(BusinessObject)ienum.get_Current();
				    	 if(bv!=null){
				    		 String MailModle=bv.GetField("MailModle").get_NativeValue().toString();
				    		 String ModleType=bv.GetField("ModleType").get_NativeValue().toString();
				    		 if(bb.equals(MailModle)&&"email".equals(ModleType)){
				    			 listViewer.remove(bb);//移除列表框中当前选中的元素
				    			 bv.DeleteObject(ConnectionBroker.get_SiteviewApi());//在数据库中把当前选中的元素的数据删除
				    		 }
				    	 }
				     }
		}
		
		L3:if(buttonId==IDialogConstants.BACK_ID){//更新即修改功能键
			if("Default".equals(bb)||"SelfDefine".equals(bb)||"LogTemplate".equals(bb)){//判断是不是自定义模板
				MessageDialog.openInformation(parentShell, "提示", "系统自定义模板,不能够被更新!");
				break L3;
			 }
			if(listViewer.getSelection().isEmpty()){
				MessageDialog.openInformation(parentShell, "提示", "请选择要更新的邮件模板!");
				break L3;
			}
			ICollection ico=FileTools.getBussCollection("EccMailModle");
		     IEnumerator ienum=ico.GetEnumerator();
		     while(ienum.MoveNext()){
		    	 BusinessObject bv=(BusinessObject)ienum.get_Current();
		    	 if(bv!=null){
		    		 String MailModle=bv.GetField("MailModle").get_NativeValue().toString();
		    		 String ModleType=bv.GetField("ModleType").get_NativeValue().toString();
		    		 if(bb.equals(MailModle)&&"email".equals(ModleType)){
		    			 bv.GetField("MailTitle").SetValue(new SiteviewValue(text.getText()));//得到更新后的邮件标题
		    			 bv.GetField("MailContent").SetValue(new SiteviewValue(text_1.getText()));//得到更新后的邮件内容
		    			 bv.SaveObject(ConnectionBroker.get_SiteviewApi(), true,//将修改后的数据存储到数据库
									true);
		    			 MessageDialog.openInformation(parentShell, "提示", "更新成功!");
		    		 }
		    	 }
		}
		}
		
		if(buttonId==IDialogConstants.CANCEL_ID){//关闭
			this.close();
		}
		L4:if(cancelButton_3.getText().equals("系统变量说明")&&buttonId==IDialogConstants.CLOSE_ID){//系统变量说明
			group = new Group(grpEmail_1, SWT.NONE);
			group.setBounds(10, 254, 404, 141);
			group.setText("\u8BF4\u660E\u5217\u8868");
			
			text_3 = new Text(group, SWT.BORDER|SWT.WRAP);
			text_3.setBounds(0, 10, 404, 130);
			text_3.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
			text_3.setText("注意：系统模板不可以删除和修改你可以拷贝参数，请不要随意输入“@”和两个“@”间的参数变量,可以参考系统模板进行添加。参数变量如下： @FullPathGroup@ :监测点所在设备所在组的全部路径名称@Status@监测器点的状态 @AllGroup@ :监测点所在设备所在组名称@Group@监测点所在组下 @Device@ :监测点所在设备的名称@Monitor@ :监测点名称@MonitorDstr@ :监测器描述@MonitorAlertDes@ :监测点报警描述，在监测点高级设置中设置 @Time@ : 报警时间");
			text_3.setEnabled(false);
			
			cancelButton_3.setText("隐藏");
			break L4;
		}
		if(cancelButton_3.getText().equals("隐藏")&&buttonId==IDialogConstants.CLOSE_ID){//隐藏
			System.out.println("chenggong");
			group.dispose();
			text_3.dispose();
			cancelButton_3.setText("系统变量说明");
		}
	}
}
