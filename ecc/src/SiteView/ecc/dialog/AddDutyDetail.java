package SiteView.ecc.dialog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;


import SiteView.ecc.Modle.DetailModel;
import SiteView.ecc.data.DutyDetailInfor;
import SiteView.ecc.editors.TableDuty;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
/**
 * 
 * @author Administrator
 * 添加、修改 值班详细情况
 *
 */
public class AddDutyDetail extends Dialog{
	//控件
	private Button applyButton;
	private Button closeButton;
    private Group group;
    private Composite composite;
    private Composite composite_1;
    private Composite composite_2;
    private Composite composite_3;
    private DateTime dateTime;
    private DateTime dateTime_1;
	public Text text;
	public Text text_1;
	public Combo combo;
    private Label label_4;
    //数据
    private String dutyid;
	private BusinessObject bo1;
    private String type;
    private DetailModel detaModel;
	public AddDutyDetail(Shell shell,String type,BusinessObject bo1,DetailModel dm,String id) {
		super(shell);
		this.type=type;
		this.bo1=bo1;
		this.detaModel=dm;
		this.dutyid=id;
	}
	protected void configureShell(Shell newShell) {
		 super.configureShell(newShell);
		 newShell.setSize(350,300);
		 newShell.setLocation(200,100);
		 newShell.setText("添加值班表详细信息");
	}
	protected Control createDialogArea(Composite parent){
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		Composite container = (Composite) super.createDialogArea(parent);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		group = new Group(container, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		group.setText("\u8BE6\u7EC6\u60C5\u51B5");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(group, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Label label = new Label(composite, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label.setBounds(10, 10, 141, 19);
		label.setText(" \u63A5\u6536\u544A\u8B66\u77ED\u4FE1\u624B\u673A\u53F7\u7801 *:");
		
		text= new Text(composite, SWT.BORDER);
		text.setBounds(180, 10, 141, 18);
		
		composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_1.setBounds(10, 10, 141, 12);
		label_1.setText(" \u63A5\u6536\u544A\u8B66\u4FE1\u606F\u90AE\u7BB1 *\uFF1A ");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(180, 10, 141, 18);
		
		if(type.startsWith("星期")){ 
			composite=new Composite(sashForm, SWT.NONE);
			composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
			
			label_4 = new Label(composite, SWT.NONE);
			label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
			label_4.setBounds(10, 10, 141, 12);
			label_4.setText("\u661F    \u671F\uFF1A");
			
			combo = new Combo(composite, SWT.NONE);
			combo.setBounds(180, 10, 141, 20);
			combo.add("星期一");
			combo.add("星期二");
			combo.add("星期三");
			combo.add("星期四");
			combo.add("星期五");
			combo.add("星期六");
			combo.add("星期日");
			combo.select(0);
		}else if(!type.equals("")){
			composite=new Composite(sashForm, SWT.NONE);
			composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
			
			label_4 = new Label(composite, SWT.NONE);
			label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
			label_4.setBounds(10, 10, 141, 12);
			label_4.setText("\u65E5    \u671F\uFF1A");
			
			combo = new Combo(composite, SWT.NONE);
			combo.setBounds(180, 10, 141, 20);
			for(int i=1;i<32;i++){
				combo.add(i+"");
			}
			combo.select(0);
		}
		
		composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_2.setBounds(10, 10, 141, 12);
		label_2.setText(" \u5F00 \u59CB \u65F6 \u95F4\uFF1A");
		
		dateTime = new DateTime(composite_2, SWT.TIME);
		dateTime.setBounds(180, 10, 141, 20);
		composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Label label_3 = new Label(composite_3, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_3.setBounds(10, 10, 141, 12);
		label_3.setText(" \u7ED3 \u675F \u65F6 \u95F4\uFF1A ");
		
		dateTime_1 = new DateTime(composite_3, SWT.TIME);
		dateTime_1.setBounds(180, 10, 141, 20);
		if(bo1==null){
			dateTime.setTime(9, 00, 00);
			dateTime_1.setTime(18, 00, 00);	
		}else{
			String s=bo1.GetField("StartTime").get_NativeValue().toString();
			s=s.substring(11);
			int h=Integer.parseInt(s.substring(0,s.indexOf(":")));
			int m=Integer.parseInt(s.substring(s.indexOf(":")+1,s.lastIndexOf(":")));
			int mm=Integer.parseInt(s.substring(s.lastIndexOf(":")+1));
			dateTime.setTime(h, m, mm);
			String s1=bo1.GetField("EndTime").get_NativeValue().toString();
			s1=s1.substring(11);
			h=Integer.parseInt(s1.substring(0,s1.indexOf(":")));
			m=Integer.parseInt(s1.substring(s1.indexOf(":")+1,s1.lastIndexOf(":")));
			mm=Integer.parseInt(s1.substring(s1.lastIndexOf(":")+1));
			dateTime_1.setTime(h, m, mm);
			BigDecimal  big=new BigDecimal(bo1.GetField("ReceiveAlarmpPhone").get_NativeValue().toString());
			text.setText(big.toString());
			text_1.setText(bo1.GetField("ReceiveAlarmEmail").get_NativeValue().toString());
			if(combo!=null){
				combo.setText(bo1.GetField("Week").get_NativeValue().toString());
			}
		}
		if(!type.equals("")){
			sashForm.setWeights(new int[] {1, 1, 1, 1,1});
		}else{
			sashForm.setWeights(new int[] {1, 1, 1, 1});
		}
		return container;
	}
	public void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));//设置保存和取消两个按钮
		applyButton = createButton(parent, IDialogConstants.OK_ID, "保存",true);
		closeButton=createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
    }
	public void buttonPressed(int buttonId){
		if(buttonId==IDialogConstants.OK_ID){
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			if(bo1==null){
				  bo1 = ConnectionBroker.get_SiteviewApi().get_BusObService().Create("DutyDetail");//得到数据库表
			}
		   if(text.getText()==null||text.getText().equals("")||text.getText().length()<11){
			   MessageDialog.openInformation(new Shell(), "提示", "请输入正确的手机号码");
			   return;
		   }else{
			   bo1.GetField("ReceiveAlarmpPhone").SetValue(new SiteviewValue(text.getText())); //得到第一个文本框里的数据
		   }
			if(text_1.getText()==null||text_1.getText().equals("")||!regex.matcher(text_1.getText()).matches()){
				MessageDialog.openInformation(new Shell(), "提示", "请输入正确Email");
				return;
			}else{
				bo1.GetField("ReceiveAlarmEmail").SetValue(new SiteviewValue(text_1.getText()));//得到第二个文本框里的数据
			}
			if(combo!=null&&combo.getText()!=null&&!combo.getText().equals("")){
				bo1.GetField("Week").SetValue(new SiteviewValue(combo.getText()));//得到第三个文本框里的数据
			}
			
			String startTimeStr="";
			String endTimeStr="";
			if(dateTime.getHours()<10){
				startTimeStr="0"+dateTime.getHours()+":";
			}else{
				startTimeStr=dateTime.getHours()+":";
			}
			if(dateTime.getMinutes()<10){
				startTimeStr+="0"+dateTime.getMinutes()+":";
			}else{
				startTimeStr+=dateTime.getMinutes()+":";
			}
			if(dateTime.getSeconds()<10){
				startTimeStr+="0"+dateTime.getMinutes();
			}else{
				startTimeStr+=dateTime.getMinutes()+"";
			}
			if(dateTime_1.getHours()<10){
				endTimeStr="0"+dateTime_1.getHours()+":";
			}else{
				endTimeStr=dateTime_1.getHours()+":";
			}
			if(dateTime_1.getMinutes()<10){
				endTimeStr+="0"+dateTime_1.getMinutes()+":";
			}else{
				endTimeStr+=dateTime_1.getMinutes()+":";
			}
			if(dateTime_1.getSeconds()<10){
				endTimeStr+="0"+dateTime_1.getMinutes();
			}else{
				endTimeStr+=dateTime_1.getMinutes()+"";
			}
			
			bo1.GetField("StartTime").SetValue(new SiteviewValue(startTimeStr));//得到第四个文本框里的数据
			bo1.GetField("EndTime").SetValue(new SiteviewValue(endTimeStr));//得到第五个文本框里的数据
			if(dutyid!=null){
				bo1.GetField("DutyId").SetValue(new SiteviewValue(dutyid));
			}
			bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true,true);//将数据存储到数据
			
			if(detaModel!=null){
				DutyDetailInfor.list.remove(detaModel);
			}
			detaModel=new DetailModel(bo1);
			detaModel.setReceiveAlarmpPhone(text.getText());
			detaModel.setReceiveAlarmEmail(text_1.getText());
			if(combo!=null){
				detaModel.setWeek((combo.getText()));
			}
			detaModel.setStartTime(startTimeStr);
			detaModel.setEndTime(endTimeStr);
			
			DutyDetailInfor.list.add(0,detaModel);
			TableDuty.TableViewer1.setInput(DutyDetailInfor.list);
			TableDuty.TableViewer1.refresh();
		}
		this.close();
	}
}
