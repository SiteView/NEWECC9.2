package SiteView.ecc.dialog;

import java.util.Calendar;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.DateTime;

public class AddDutyDetail extends Dialog{
	private Button applyButton;
	private Button closeButton;
	private Text text;
	private Text text_1;
	private Combo combo;
	public AddDutyDetail(Shell parent) {
		super(parent);
	}
	protected void configureShell(Shell newShell) {
		 super.configureShell(newShell);
		 newShell.setSize(350,300);
		 newShell.setLocation(200,100);
		 newShell.setText("添加值班表详细信息");
	}
	protected Control createDialogArea(Composite parent){
		Composite container = (Composite) super.createDialogArea(parent);
		Group g=new Group(container,SWT.NONE);
		GridData gd_g = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_g.heightHint = 208;
		gd_g.widthHint = 415;
		g.setLayoutData(gd_g);
		g.setText("详细信息");
		
		Label lblNewLabel = new Label(g, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel.setBounds(20, 24, 122, 18);
		lblNewLabel.setText("\u63A5\u6536\u544A\u8B66\u77ED\u4FE1\u624B\u673A\u53F7\u7801");
		
		text = new Text(g, SWT.BORDER);
		text.setBounds(176, 24, 145, 18);
		
		Label lblNewLabel_1 = new Label(g, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(20, 52, 122, 18);
		lblNewLabel_1.setText("\u63A5\u6536\u544A\u8B66\u4FE1\u606F\u90AE\u7BB1");
		
		text_1 = new Text(g, SWT.BORDER);
		text_1.setBounds(176, 52, 145, 18);
		
		Label lblNewLabel_2 = new Label(g, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_2.setBounds(20, 85, 122, 18);
		lblNewLabel_2.setText("\u65E5\u671F");
		
		combo = new Combo(g, SWT.NONE);
		combo.setSize(145, 20);
		combo.setLocation(176, 85);
		for (int i = 1; i <= 31; i++) {
			combo.add(""+i); //循环添加选项
		}
		
		Label lblNewLabel_3 = new Label(g, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_3.setBounds(20, 123, 122, 18);
		lblNewLabel_3.setText("\u5F00\u59CB\u65F6\u95F4");
		
		Calendar startcal = Calendar.getInstance();
		final DateTime startTime = new DateTime(g, SWT.TIME
				| SWT.SHORT);
		startTime.setLocation(176, 120);
		startTime.setSize(79, 21);
		FormData fd_startTime = new FormData();
		startTime.setLayoutData(fd_startTime);
		startTime.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime.setMinutes(startcal.get(Calendar.MINUTE));
		startTime.setSeconds(startcal.get(Calendar.SECOND));
		
		Label lblNewLabel_4 = new Label(g, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_4.setBounds(20, 154, 122, 18);
		lblNewLabel_4.setText("\u7ED3\u675F\u65F6\u95F4");
		
		Calendar endcal = Calendar.getInstance();
		final DateTime endTime = new DateTime(g, SWT.TIME
				| SWT.SHORT);
		endTime.setLocation(176, 151);
		endTime.setSize(79, 21);
		endTime.setHours(endcal.get(Calendar.HOUR_OF_DAY));
		endTime.setMinutes(endcal.get(Calendar.MINUTE));
		endTime.setSeconds(endcal.get(Calendar.SECOND));
		return container;
	}
	protected void createButtonsForButtonBar(Composite parent) {//设置保存和取消两个按钮
		applyButton = createButton(parent, IDialogConstants.OK_ID, "保存",true);
		closeButton=createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
    }
	protected void buttonPressed(int buttonId){
		if(buttonId==IDialogConstants.OK_ID){
			
		}
	}
}
