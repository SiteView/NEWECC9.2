package SiteView.ecc.dialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import SiteView.ecc.Modle.DetailModel;
import Siteview.Api.BusinessObject;

import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class DutyDetailEditor extends Dialog{
private Button applyButton;
private Button closeButton;	
public BusinessObject bv;
public DetailModel dm;
public DateTime startTime;
public DateTime endTime;
public Calendar startcal;
public String startTimeStr = "";	
public String endTimeStr = "";
private Text text;
private Text text_1;
private Text text_2;
private Text text_3;
private Text text_4;
	public DutyDetailEditor(Shell parent,BusinessObject bv,DetailModel dm) {
		super(parent);
		this.bv=bv;
		this.dm=dm;
	}
	protected void configureShell(Shell newShell){
		super.configureShell(newShell);
		newShell.setSize(500,300);
		newShell.setLocation(200,100);
		newShell.setText("编辑值班表详细信息");
	}
	protected Control createDialogArea(Composite parent){
		Composite container = (Composite) super.createDialogArea(parent);
		Group g=new Group(container,SWT.NONE);
		GridData gd_g = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_g.heightHint = 198;
		gd_g.widthHint = 418;
		g.setLayoutData(gd_g);
		g.setText("详细信息");
		
		Label lblNewLabel = new Label(g, SWT.NONE);//手机号码
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel.setText("\u63A5\u6536\u544A\u8B66\u77ED\u4FE1\u624B\u673A\u53F7\u7801:");
		lblNewLabel.setBounds(30, 25, 164, 19);
		
		text = new Text(g, SWT.BORDER);
		text.setBounds(197, 24, 181, 18);
		
		Label lblNewLabel_1 = new Label(g, SWT.NONE);//邮箱
		lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(30, 56, 164, 19);
		lblNewLabel_1.setText("\u63A5\u6536\u544A\u8B66\u4FE1\u606F\u90AE\u7BB1:");
		
		text_1 = new Text(g, SWT.BORDER);
		text_1.setBounds(197, 57, 181, 18);
		
		if(bv.GetField("Week").get_NativeValue().toString().contentEquals("")){
			
		}
		Label lblNewLabel_2 = new Label(g, SWT.NONE);//日期
		lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_2.setBounds(30, 90, 164, 19);
		lblNewLabel_2.setText("\u65E5\u671F:");
		
		text_2 = new Text(g, SWT.BORDER);
		text_2.setBounds(197, 90, 181, 18);
		
		Label lblNewLabel_3 = new Label(g, SWT.NONE);//开始时间
		lblNewLabel_3.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_3.setBounds(30, 124, 164, 19);
		lblNewLabel_3.setText("\u5F00\u59CB\u65F6\u95F4:");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date startDateTime  = new Date();
		Date endDateTime  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime);
		startTime = new DateTime(g, SWT.TIME
				| SWT.SHORT);
		startTime.setLocation(197, 122);//开始时间文本输入框
		startTime.setSize(79, 18);
		FormData fd_startTime = new FormData();
		startTime.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime.setMinutes(startcal.get(Calendar.MINUTE));
		startTime.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr=startTime.getHours() + ":"
				+ startTime.getMinutes() + ":" + startTime.getSeconds();
		 try {
				startDateTime = sdf.parse(startTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		startTimeStr = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		Label lblNewLabel_4 = new Label(g, SWT.NONE);//结束时间
		lblNewLabel_4.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_4.setBounds(30, 156, 164, 19);
		lblNewLabel_4.setText("\u7ED3\u675F\u65F6\u95F4:");
		
		Calendar endcal = Calendar.getInstance();
		endcal.setTime(endDateTime);
	    endTime = new DateTime(g, SWT.TIME
				| SWT.SHORT);
	    endTime.setLocation(197, 154);//结束时间文本输入框
		endTime.setSize(79, 18);
		endTime.setHours(endcal.get(Calendar.HOUR_OF_DAY));
		endTime.setMinutes(endcal.get(Calendar.MINUTE));
		endTime.setSeconds(endcal.get(Calendar.SECOND));
		endTimeStr=endTime.getHours() + ":"
				+ endTime.getMinutes() + ":" + endTime.getSeconds();
		try {
			endDateTime = sdf.parse(endTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		endTimeStr = new SimpleDateFormat("HH:mm:ss")
		.format(endcal.getTime());
		return container;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		applyButton = createButton(parent, IDialogConstants.OK_ID, "保存",true);
		closeButton=createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
	}
}
