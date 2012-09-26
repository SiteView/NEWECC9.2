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
	private Group group;
    private Text text_3;
	public static java.util.List<BusinessObject> modles=null;
	public static ListViewer listViewer;
    public Object bb;
    public Shell parentShell;
    public Group grpEmail_1;
	public MailModleSetUp(Shell parentShell) {
		super(parentShell);
		this.parentShell=parentShell;
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(620, 480);
		newShell.setLocation(200, 175);
		newShell.setText("Emailģ������");
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
		
		for(BusinessObject bo:modles){
			String ModleType=bo.GetField("ModleType").get_NativeValue().toString();
			if("email".equals(ModleType)){
				listViewer.add(bo.GetField("MailModle").get_NativeValue().toString());
			}
		}
		listViewer.addSelectionChangedListener(new ISelectionChangedListener() {//�����¼�:ѡ������б��е�Ԫ���ұ���ʾ��Ӧ����Ϣ
			public void selectionChanged(SelectionChangedEvent event) {
				 ISelection selection = listViewer.getSelection();
			     bb = ((IStructuredSelection)selection).getFirstElement();//�õ��б���е�ǰѡ�е�Ԫ��
			     //System.out.println(bb);
			     if(bb!=null){//��ֹɾ����ʱ��bbΪnull�����쳣
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
							     if(!(text_2.getText().isEmpty())){
							    	 text_2.setText("");
							     }
				    		 }
				    	 }
				     }
	    		 }
			}
		});

		grpEmail_1 = new Group(sashForm, SWT.NONE);
		grpEmail_1.setText("Email\u6A21\u677F\u8BBE\u7F6E");
		
		Label label = new Label(grpEmail_1, SWT.NONE);//�ʼ�����
		label.setBounds(10, 36, 54, 12);
		label.setText("\u90AE\u4EF6\u6807\u9898\uFF1A");
		
		text = new Text(grpEmail_1, SWT.BORDER);
		text.setBounds(70, 30, 344, 18);
		
		
		Label label_1 = new Label(grpEmail_1, SWT.NONE);//�ʼ�����
		label_1.setBounds(10, 67, 54, 12);
		label_1.setText("\u90AE\u4EF6\u5185\u5BB9\uFF1A");
		
		text_1 = new Text(grpEmail_1, SWT.BORDER | SWT.WRAP);
		text_1.setLocation(70, 64);
		text_1.setSize(344, 128);
		
		
		Label label_2 = new Label(grpEmail_1, SWT.NONE);//�ʼ�ģ��
		label_2.setBounds(10, 218, 60, 12);
		label_2.setText("\u90AE\u4EF6\u6A21\u677F\uFF1A");
		
		text_2 = new Text(grpEmail_1, SWT.BORDER);
		text_2.setBounds(70, 215, 344, 18);
		
		group = new Group(grpEmail_1, SWT.NONE);
		group.setBounds(10, 252, 404, 143);
		group.setText("\u8BF4\u660E\u5217\u8868");
		
		text_3 = new Text(group, SWT.BORDER|SWT.WRAP);
		text_3.setBounds(0, 10, 404, 130);
		text_3.setFont(SWTResourceManager.getFont("����", 10, SWT.NORMAL));
		text_3.setText("ע�⣺ϵͳģ�岻����ɾ�����޸�����Կ����������벻Ҫ�������롰@����������@����Ĳ�������,���Բο�ϵͳģ�������ӡ������������£� @FullPathGroup@ :���������豸�������ȫ��·������@Status@��������״̬ @AllGroup@ :���������豸����������@Group@������������ @Device@ :���������豸������@Monitor@ :��������@MonitorDstr@ :���������@MonitorAlertDes@ :���㱨���������ڼ���߼����������� @Time@ : ����ʱ��");
		text_3.setEnabled(false);
		
		sashForm.setWeights(new int[] {147, 403});
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		 subButton = createButton(parent, IDialogConstants.OK_ID, "���",
				true);
		 cancelButton = createButton(parent, IDialogConstants.ABORT_ID,
				"ɾ��", true);
		 cancelButton_1 = createButton(parent, IDialogConstants.BACK_ID,
				"����", true);
		 cancelButton_2 = createButton(parent, IDialogConstants.CANCEL_ID,
				"�ر�", true);
	}
	protected void buttonPressed(int buttonId){
		L1:if(buttonId==IDialogConstants.OK_ID){//���
			ICollection ico=FileTools.getBussCollection("EccMailModle");
			IEnumerator ienum=ico.GetEnumerator();
			while(ienum.MoveNext()){
				BusinessObject businessObject=(BusinessObject)ienum.get_Current();
				String ModleType=businessObject.GetField("ModleType").get_NativeValue().toString();
				String MailModle=businessObject.GetField("MailModle").get_NativeValue().toString();
				if("email".equals(ModleType)&&text_2.getText().equals(MailModle)){//�ж���ӵ�Ԫ���Ƿ��Ѿ�����
	            	MessageDialog.openInformation(parentShell, "��ʾ", "��ģ�����Ѵ���,�뻻һ��ģ����!");
	            	break L1;
				}
				if("email".equals(ModleType)&&text_2.getText().equals("")){//�ж���ӵ�Ԫ���Ƿ��Ѿ�����
	            	MessageDialog.openInformation(parentShell, "��ʾ", "ģ�����Ʋ���Ϊ��,����������!");
	            	break L1;
				}
			}
			BusinessObject obj=ConnectionBroker.get_SiteviewApi()//�õ���Ӧ�����ݿ��
					.get_BusObService().Create("EccMailModle");
			obj.GetField("MailTitle").SetValue(new SiteviewValue(text.getText()));//�ʼ�����
			obj.GetField("MailContent").SetValue(new SiteviewValue(text_1.getText()));//�ʼ�����
			obj.GetField("MailModle").SetValue(new SiteviewValue(text_2.getText()));//�ʼ�ģ��
			obj.GetField("ModleType").SetValue(new SiteviewValue("email"));
			obj.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//����ӵ�Ԫ�ص����ݴ洢�����ݿ���
			listViewer.add(obj.GetField("MailModle").get_NativeValue().toString());//���б�������Ԫ��
			
			if(!(text_2.getText().isEmpty())){
				text_2.setText("");
			}
		}
	L2:if(buttonId==IDialogConstants.ABORT_ID){//ɾ��
		if("Default".equals(bb)||"SelfDefine".equals(bb)||"LogTemplate".equals(bb)){//�ж��ǲ����Զ���ģ��
			MessageDialog.openInformation(parentShell, "��ʾ", "ϵͳ�Զ���ģ�岻�ܱ�ɾ��!");
			break L2;
		 }
		
			ICollection ico=FileTools.getBussCollection("EccMailModle");
		     IEnumerator ienum=ico.GetEnumerator();
		     while(ienum.MoveNext()){
		    	 BusinessObject bv=(BusinessObject)ienum.get_Current();
		    	 if(bv!=null){
		    		 String MailModle=bv.GetField("MailModle").get_NativeValue().toString();
		    		 String ModleType=bv.GetField("ModleType").get_NativeValue().toString();
		    		 if(bb!=null){//��ֹɾ����ʱ��bbΪnull�����쳣
		    		 if(bb.equals(MailModle)&&"email".equals(ModleType)){
		    			 listViewer.remove(bb);//�Ƴ��б���е�ǰѡ�е�Ԫ��
		    			 bv.DeleteObject(ConnectionBroker.get_SiteviewApi());//�����ݿ��аѵ�ǰѡ�е�Ԫ�ص�����ɾ��
		    		 }
		    		 }
		    	 }
		     }
		
			     
	}
	
	L3:if(buttonId==IDialogConstants.BACK_ID){//���¼��޸Ĺ��ܼ�
		if("Default".equals(bb)||"SelfDefine".equals(bb)||"LogTemplate".equals(bb)){//�ж��ǲ����Զ���ģ��
			MessageDialog.openInformation(parentShell, "��ʾ", "ϵͳ�Զ���ģ��,���ܹ�������!");
			break L3;
		 }
		if(listViewer.getSelection().isEmpty()){
			MessageDialog.openInformation(parentShell, "��ʾ", "��ѡ��Ҫ���µ��ʼ�ģ��!");
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
	    			 bv.GetField("MailTitle").SetValue(new SiteviewValue(text.getText()));//�õ����º���ʼ�����
	    			 bv.GetField("MailContent").SetValue(new SiteviewValue(text_1.getText()));//�õ����º���ʼ�����
	    			 bv.SaveObject(ConnectionBroker.get_SiteviewApi(), true,//���޸ĺ�����ݴ洢�����ݿ�
								true);
	    			 MessageDialog.openInformation(parentShell, "��ʾ", "���³ɹ�!");
	    		 }
	    	 }
	}
	}
		if(buttonId==IDialogConstants.CANCEL_ID){//�ر�
			this.close();
		}
	}
}
