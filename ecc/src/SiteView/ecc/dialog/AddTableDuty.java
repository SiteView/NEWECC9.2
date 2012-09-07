package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
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

import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class AddTableDuty extends Dialog{
	private Text text;
	private Text text_1;
	private Combo combo;
	private Button applyButton;
	private Button closeButton;
	public AddTableDuty(Shell parent) {
		super(parent);
	}
	@Override
	protected void configureShell(Shell newShell) {
		 super.configureShell(newShell);
		 newShell.setSize(350,300);
		 newShell.setLocation(200,100);
		 newShell.setText("添加值班表信息");
	}
	@Override
	protected Control createDialogArea(Composite parent){
		Composite container = (Composite) super.createDialogArea(parent);
         Group g=new Group(container,SWT.NONE);
         GridData gd_g = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
         gd_g.widthHint = 416;
         gd_g.heightHint = 222;
         g.setLayoutData(gd_g);
         g.setText("值班表");
         g.setBounds(10, 20, 500, 600);
         
         Label lblNewLabel = new Label(g, SWT.NONE);
         lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
         lblNewLabel.setText("\u503C\u73ED\u8868\u540D\u79F0* :");
         lblNewLabel.setBounds(29, 42, 99, 22);
         
         text = new Text(g, SWT.BORDER);//第一个文本输入框
         text.setBounds(145, 42, 156, 18);
   
         
         Label lblNewLabel_1 = new Label(g, SWT.NONE);
         lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
         lblNewLabel_1.setBounds(26, 82, 98, 22);
         lblNewLabel_1.setText("\u503C\u73ED\u8868\u63CF\u8FF0* :");
         
         text_1 = new Text(g, SWT.BORDER);//第二个文本输入框
         text_1.setBounds(145, 80, 156, 18);
   
         Label lblNewLabel_2 = new Label(g, SWT.NONE);
         lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
         lblNewLabel_2.setBounds(29, 122, 98, 22);
         lblNewLabel_2.setText("\u503C\u73ED\u7C7B\u578B :");
         
         combo = new Combo(g, SWT.NONE);//第三个文本输入框
         combo.setBounds(145, 120, 156, 18);
         combo.add("day"); //循环添加选项
         combo.add("day of week");
         combo.add("day of month");
   
        			
		 return container;

		
	}
	protected void createButtonsForButtonBar(Composite parent) {//设置保存和取消两个按钮
		applyButton = createButton(parent, IDialogConstants.OK_ID, "保存",true);
		closeButton=createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
    }
	protected void buttonPressed(int buttonId){
		if(buttonId==IDialogConstants.OK_ID){
			BusinessObject bo = ConnectionBroker.get_SiteviewApi()//得到数据库表
					.get_BusObService().Create("EccDuty");
			bo.GetField("DutyTableName").SetValue(//得到第一个文本框里的数据
					new SiteviewValue(text.getText()));
			bo.GetField("DutyTableDec").SetValue(//得到第二个文本框里的数据
					new SiteviewValue(text_1.getText()));
			bo.GetField("DutyTableDec").SetValue(//得到第三个文本框里的数据
					new SiteviewValue(combo.getText()));
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);
		}
		this.close();
	}
}
