package SiteView.ecc.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.data.TableDutyInfor;
import SiteView.ecc.editors.TableDuty;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class AddTableDuty extends Dialog{
	private Text text;
	private Text text_1;
	public static Combo combo;
	private Button applyButton;
	private Button closeButton;
	public Shell parent;
	public AddTableDuty(Shell parent) {
		super(parent);
		this.parent=parent;
	}
	@Override
	protected void configureShell(Shell newShell) {
		 super.configureShell(newShell);
		 newShell.setSize(350,300);
		 newShell.setLocation(200,100);
		 newShell.setText("���ֵ�����Ϣ");
	}
	@Override
	public Control createDialogArea(Composite parent){
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		Composite container = (Composite) super.createDialogArea(parent);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
         Group g=new Group(container,SWT.NONE);
         g.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
         GridData gd_g = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
         gd_g.widthHint = 416;
         gd_g.heightHint = 222;
         g.setLayoutData(gd_g);
         g.setText("ֵ���");
         g.setBounds(10, 20, 500, 600);
         
         Label lblNewLabel = new Label(g, SWT.NONE);
         lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
         lblNewLabel.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
         lblNewLabel.setText("\u503C\u73ED\u8868\u540D\u79F0* :");
         lblNewLabel.setBounds(29, 42, 99, 22);
         
         text = new Text(g, SWT.BORDER);//ֵ�������
         text.setBounds(145, 42, 156, 18);
        
         Label lblNewLabel_1 = new Label(g, SWT.NONE);
         lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
         lblNewLabel_1.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
         lblNewLabel_1.setBounds(26, 82, 98, 22);
         lblNewLabel_1.setText("\u503C\u73ED\u8868\u63CF\u8FF0* :");
         
         text_1 = new Text(g, SWT.BORDER);//ֵ�������
         text_1.setBounds(145, 80, 156, 18);
         
   
         Label lblNewLabel_2 = new Label(g, SWT.NONE);
         lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
         lblNewLabel_2.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
         lblNewLabel_2.setBounds(29, 122, 98, 22);
         lblNewLabel_2.setText("\u503C\u73ED\u7C7B\u578B :");
         
         combo = new Combo(g, SWT.READ_ONLY);//ֵ�������
         combo.setBounds(145, 120, 156, 18);
         combo.add("day"); //ѭ�����ѡ��
         combo.add("day of week");
         combo.add("day of month");
         combo.select(0);
		 return container;
	}
	protected void createButtonsForButtonBar(Composite parent) {//���ñ����ȡ��������ť
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		applyButton = createButton(parent, IDialogConstants.OK_ID, "����",true);
		closeButton=createButton(parent, IDialogConstants.CANCEL_ID, "ȡ��", true);
    }
	protected void buttonPressed(int buttonId){
		if(buttonId==IDialogConstants.OK_ID){
			BusinessObject bo = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("EccDutyTable");//�õ����ݿ��
			if(text.getText().trim().equals("")||text.getText()==null){
				MessageDialog.openInformation(new Shell(), "��ʾ", "������ֵ�������");
				return;
			}
			if(text_1.getText().trim().equals("")||text_1.getText()==null){
				MessageDialog.openInformation(new Shell(), "��ʾ", "������ֵ�������");
				return;
			}
			bo.GetField("DutyTableName").SetValue(new SiteviewValue(text.getText()));//�õ���һ���ı����������
			bo.GetField("DutyTableDec").SetValue(new SiteviewValue(text_1.getText()));//�õ��ڶ����ı����������
			bo.GetField("DutyTableType").SetValue(new SiteviewValue(combo.getText()));//�õ��������ı����������
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,true);//�����ݴ洢�����ݿ�
			TableModle tableModle=new TableModle(bo);
			tableModle.setDutyTableDec(text_1.getText());
			tableModle.setDutyTableName(text.getText());
			tableModle.setDutyTableType(combo.getText());
			TableDutyInfor.list.add(tableModle);
			TableDuty.TableViewer.setInput(TableDutyInfor.list);
			TableDuty.TableViewer.refresh();
		}
		this.close();
	}
}
