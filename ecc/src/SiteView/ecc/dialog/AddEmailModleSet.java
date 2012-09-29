package SiteView.ecc.dialog;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import Siteview.Api.BusinessObject;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class AddEmailModleSet extends Dialog{
public String name;
public BusinessObject bo;
public static ListViewer listViewer;
private Button subButton;
private Button cancelButton;
private Button cancelButton_1;
private Button cancelButton_2;
private Text text;
private Text text_1;
private Label lblNewLabel_2;
private Text text_2;
	public AddEmailModleSet(Shell parentShell, String name,BusinessObject bo) {
		super(parentShell);
		this.name = name;
		this.bo=bo;
	}
	protected void configureShell(Shell newShell){
		newShell.setSize(500, 350);
		newShell.setLocation(200, 175);
		if("��Ϣģ������".equals(name)){
			newShell.setText("��Ϣģ������");
		}else if("Web��Ϣģ������".equals(name)){
			newShell.setText("Web��Ϣģ������");
		}
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent){
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		
		Group grpEmail = new Group(sashForm, SWT.NONE);
		if("��Ϣģ������".equals(name)){
			grpEmail.setText("����ģ��ϵ��:");
			grpEmail.setLayout(new FillLayout());
			listViewer = new ListViewer(grpEmail, SWT.BORDER | SWT.V_SCROLL);
		}else if("Web��Ϣģ������".equals(name)){
			grpEmail.setText("Web��Ϣģ��ϵ��:");
			grpEmail.setLayout(new FillLayout());
			listViewer = new ListViewer(grpEmail, SWT.BORDER | SWT.V_SCROLL);
		}
			
			Group grpEmail_1 = new Group(sashForm, SWT.NONE);
			if("��Ϣģ������".equals(name)){
				grpEmail_1.setText("����ģ������");
				
				Label lblNewLabel = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
				lblNewLabel.setBounds(42, 23, 83, 18);
				lblNewLabel.setText("\u77ED\u4FE1\u6807\u9898*\uFF1A");
				
				text = new Text(grpEmail_1, SWT.BORDER);
				text.setBounds(42, 48, 233, 18);
				
				Label lblNewLabel_1 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_1.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
				lblNewLabel_1.setBounds(42, 80, 83, 18);
				lblNewLabel_1.setText("\u77ED\u4FE1\u5185\u5BB9:");
				
				text_1 = new Text(grpEmail_1, SWT.BORDER);
				text_1.setBounds(42, 104, 233, 74);
				
				lblNewLabel_2 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_2.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
				lblNewLabel_2.setBounds(42, 196, 83, 18);
				lblNewLabel_2.setText("\u6A21\u677F\u6807\u9898*:");
				
				text_2 = new Text(grpEmail_1, SWT.BORDER);
				text_2.setBounds(42, 220, 233, 18);
				
			}else if("Web��Ϣģ������".equals(name)){
				grpEmail_1.setText("Web��Ϣģ������");
				
				Label lblNewLabel = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
				lblNewLabel.setBounds(42, 23, 90, 18);
				lblNewLabel.setText("Web����ģ��*:");
				
				text = new Text(grpEmail_1, SWT.BORDER);
				text.setBounds(42, 48, 233, 18);
				
				Label lblNewLabel_1 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_1.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
				lblNewLabel_1.setBounds(42, 80, 83, 18);
				lblNewLabel_1.setText("��������:");
				
				text_1 = new Text(grpEmail_1, SWT.BORDER);
				text_1.setBounds(42, 104, 233, 74);
				
				lblNewLabel_2 = new Label(grpEmail_1, SWT.NONE);
				lblNewLabel_2.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
				lblNewLabel_2.setBounds(42, 196, 83, 18);
				lblNewLabel_2.setText("ģ�����*:");
				
				text_2 = new Text(grpEmail_1, SWT.BORDER);
				text_2.setBounds(42, 220, 233, 18);
			}
			
			sashForm.setWeights(new int[] {97, 344});
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
}
