package SiteView.ecc.dialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import swing2swt.layout.BorderLayout;

public class AddAbsoluteTime extends Dialog{
	public DateTime startTime;
	public DateTime startTime_1;
	public DateTime startTime_2;
	public DateTime startTime_3;
	public DateTime startTime_4;
	public DateTime startTime_5;
	public DateTime startTime_6;
	public String startTimeStr = "";	
	public String startTimeStr_1 = "";	
	public String startTimeStr_2 = "";	
	public String startTimeStr_3 = "";	
	public String startTimeStr_4 = "";	
	public String startTimeStr_5 = "";	
	public String startTimeStr_6 = "";	
	public SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	public Calendar startcal;
	private String title="添加绝对时间任务计划";
	private Text text;

	public AddAbsoluteTime(Shell parentShell) {
		super(parentShell);
	}
	
	protected void configureShell(Shell newShell) {
		newShell.setSize(350, 310);
		newShell.setLocation(450, 175);
		newShell.setText(title);
		super.configureShell(newShell);
	}
	
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		TabFolder tabFolder=new TabFolder(composite, SWT.NONE);
		
		TabItem basicItem=new TabItem(tabFolder, SWT.NONE);
		basicItem.setText("基本");
		
		Composite composite1 = new Composite(tabFolder, SWT.NONE);
		composite1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		basicItem.setControl(composite1);
		
		Label lblNewLabel = new Label(composite1, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel.setBounds(10, 10, 114, 17);
		lblNewLabel.setText("\u4EFB\u52A1\u8BA1\u5212\u540D\u79F0\uFF1A");
		
		text = new Text(composite1, SWT.BORDER);
		text.setBounds(130, 10, 185, 18);
		
		Label lblNewLabel_1 = new Label(composite1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_1.setAlignment(SWT.CENTER);
		lblNewLabel_1.setText("\u661F\u671F\u65E5");
		lblNewLabel_1.setBounds(27, 33, 54, 12);
		
		Button btnCheckButton = new Button(composite1, SWT.CHECK);
		btnCheckButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton.setBounds(130, 31, 45, 16);
		btnCheckButton.setText("\u5141\u8BB8");
		btnCheckButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("勾选");
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Date startDateTime  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime);
		startTime = new DateTime(composite1, SWT.TIME
				| SWT.SHORT);
		startTime.setLocation(181, 30);
		startTime.setSize(79, 15);
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
		
		Label lblNewLabel_2 = new Label(composite1, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_2.setAlignment(SWT.CENTER);
		lblNewLabel_2.setBounds(27, 55, 54, 12);
		lblNewLabel_2.setText("\u661F\u671F\u4E00");
		
		Button btnCheckButton_1 = new Button(composite1, SWT.CHECK);
		btnCheckButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_1.setBounds(130, 53, 54, 16);
		btnCheckButton_1.setText("\u5141\u8BB8");
		
		Date startDateTime_1  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_1);
		startTime_1 = new DateTime(composite1, SWT.TIME
				| SWT.SHORT);
		startTime_1.setLocation(181, 52);
		startTime_1.setSize(79, 15);
		startTime_1.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_1.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_1.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_1=startTime_1.getHours() + ":"
				+ startTime_1.getMinutes() + ":" + startTime_1.getSeconds();
		 try {
			 startDateTime_1 = sdf.parse(startTimeStr_1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_1 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		
		Label lblNewLabel_3 = new Label(composite1, SWT.NONE);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_3.setAlignment(SWT.CENTER);
		lblNewLabel_3.setBounds(27, 75, 54, 12);
		lblNewLabel_3.setText("\u661F\u671F\u4E8C");
		
		Button btnCheckButton_2 = new Button(composite1, SWT.CHECK);
		btnCheckButton_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_2.setBounds(130, 73, 45, 16);
		btnCheckButton_2.setText("\u5141\u8BB8");
		
		Date startDateTime_2  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_2);
		startTime_2 = new DateTime(composite1, SWT.TIME
				| SWT.SHORT);
		startTime_2.setLocation(181, 72);
		startTime_2.setSize(79, 15);
		startTime_2.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_2.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_2.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_2=startTime_2.getHours() + ":"
				+ startTime_2.getMinutes() + ":" + startTime_2.getSeconds();
		 try {
			 startDateTime_2 = sdf.parse(startTimeStr_2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_2 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime()); 
		 
		Label lblNewLabel_4 = new Label(composite1, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_4.setAlignment(SWT.CENTER);
		lblNewLabel_4.setBounds(27, 96, 54, 12);
		lblNewLabel_4.setText("\u661F\u671F\u4E09");
		
		Button btnCheckButton_3 = new Button(composite1, SWT.CHECK);
		btnCheckButton_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_3.setBounds(130, 95, 45, 16);
		btnCheckButton_3.setText("\u5141\u8BB8");
		
		Date startDateTime_3  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_3);
		startTime_3 = new DateTime(composite1, SWT.TIME
				| SWT.SHORT);
		startTime_3.setLocation(181, 93);
		startTime_3.setSize(79, 15);
		startTime_3.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_3.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_3.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_3=startTime_3.getHours() + ":"
				+ startTime_3.getMinutes() + ":" + startTime_3.getSeconds();
		 try {
			 startDateTime_3 = sdf.parse(startTimeStr_3);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_3 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime()); 
		 
		Label lblNewLabel_5 = new Label(composite1, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_5.setAlignment(SWT.CENTER);
		lblNewLabel_5.setBounds(27, 118, 54, 12);
		lblNewLabel_5.setText("\u661F\u671F\u56DB");
		
		Button button = new Button(composite1, SWT.CHECK);
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		button.setText("\u5141\u8BB8");
		button.setBounds(130, 116, 45, 16);
		
		Date startDateTime_4  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_4);
		startTime_4 = new DateTime(composite1, SWT.TIME
				| SWT.SHORT);
		startTime_4.setLocation(181, 115);
		startTime_4.setSize(79, 15);
		startTime_4.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_4.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_4.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_4=startTime_4.getHours() + ":"
				+ startTime_4.getMinutes() + ":" + startTime_4.getSeconds();
		 try {
			 startDateTime_4 = sdf.parse(startTimeStr_4);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_4 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime()); 
		 
		Label lblNewLabel_6 = new Label(composite1, SWT.NONE);
		lblNewLabel_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_6.setAlignment(SWT.CENTER);
		lblNewLabel_6.setBounds(27, 139, 54, 12);
		lblNewLabel_6.setText("\u661F\u671F\u4E94");
		
		Button btnCheckButton_4 = new Button(composite1, SWT.CHECK);
		btnCheckButton_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_4.setBounds(130, 138, 45, 16);
		btnCheckButton_4.setText("\u5141\u8BB8");
		
		Date startDateTime_5  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_5);
		startTime_5 = new DateTime(composite1, SWT.TIME
				| SWT.SHORT);
		startTime_5.setLocation(181, 136);
		startTime_5.setSize(79, 15);
		startTime_5.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_5.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_5.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_5=startTime_5.getHours() + ":"
				+ startTime_5.getMinutes() + ":" + startTime_5.getSeconds();
		 try {
			 startDateTime_5 = sdf.parse(startTimeStr_5);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_5 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		 
		Label lblNewLabel_7 = new Label(composite1, SWT.NONE);
		lblNewLabel_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_7.setAlignment(SWT.CENTER);
		lblNewLabel_7.setBounds(27, 161, 54, 12);
		lblNewLabel_7.setText("\u661F\u671F\u516D");
		
		Button btnCheckButton_5 = new Button(composite1, SWT.CHECK);
		btnCheckButton_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		btnCheckButton_5.setBounds(130, 160, 45, 16);
		btnCheckButton_5.setText("\u5141\u8BB8");
		
		Date startDateTime_6  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime_6);
		startTime_6 = new DateTime(composite1, SWT.TIME
				| SWT.SHORT);
		startTime_6.setLocation(181, 158);
		startTime_6.setSize(79, 15);
		startTime_6.setHours(startcal.get(Calendar.HOUR_OF_DAY));
		startTime_6.setMinutes(startcal.get(Calendar.MINUTE));
		startTime_6.setSeconds(startcal.get(Calendar.SECOND));
		startTimeStr_6=startTime_6.getHours() + ":"
				+ startTime_6.getMinutes() + ":" + startTime_6.getSeconds();
		 try {
			 startDateTime_6 = sdf.parse(startTimeStr_6);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 startTimeStr_6 = new SimpleDateFormat("HH:mm:ss")
		.format(startcal.getTime());
		 
		TabItem describeItem=new TabItem(tabFolder, SWT.NONE);
		describeItem.setText("描述");
		
		Composite composite2 = new Composite(tabFolder, SWT.NONE);
		composite2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		describeItem.setControl(composite2);
		composite2.setLayout(new BorderLayout(0, 0));
		
		Label label = new Label(composite2, SWT.HORIZONTAL);
		label.setAlignment(SWT.CENTER);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(0, 0, 54, 199);
		label.setLayoutData(BorderLayout.WEST);
		label.setText("\u63CF\u8FF0\uFF1A");
		
		Text text1=new Text(composite2, SWT.WRAP | SWT.BORDER);
		text1.setLayoutData(BorderLayout.CENTER);
		return composite;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "保存",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", true);
	}
	
	
	protected void buttonPressed(int buttonId) {
		if(buttonId==IDialogConstants.OK_ID){
			
		}else{
			this.close();
		}
	}
}
