package SiteView.ecc.dialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.eclipse.swt.widgets.DateTime;

import SiteView.ecc.Control.DutyDetailContentProvider;
import SiteView.ecc.Control.DutyDetailLabelProvider;
import SiteView.ecc.Modle.DetailModel;
import SiteView.ecc.data.DutyDetailInfor;
import SiteView.ecc.editors.TableDuty;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class AddDutyDetail extends Dialog{
	private Button applyButton;
	private Button closeButton;
	public Text text;
	public Text text_1;
	public Combo combo;
	public DateTime startTime;
	public DateTime endTime;
	public Calendar startcal;
	public String type;
    public BusinessObject bo1;
    public static BusinessObject bo;
    public String startTimeStr = "";	
    public String endTimeStr = "";
    public Map<String,BusinessObject> sub=new HashMap<String,BusinessObject>();
    public String strID;
	public AddDutyDetail(Shell shell,String type,BusinessObject bo1) {
		super(shell);
		this.type=type;
		this.bo1=bo1;
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
		text.setBounds(176, 24, 145, 18);//第一个文本输入框
		
		Label lblNewLabel_1 = new Label(g, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(20, 52, 122, 18);
		lblNewLabel_1.setText("\u63A5\u6536\u544A\u8B66\u4FE1\u606F\u90AE\u7BB1");
		
		text_1 = new Text(g, SWT.BORDER);
		text_1.setBounds(176, 52, 145, 18);//第二个文本输入框
		
		Label lblNewLabel_2 = new Label(g, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_2.setBounds(20, 85, 122, 18);
		lblNewLabel_2.setText("\u65E5\u671F");
		
		combo = new Combo(g, SWT.NONE);//第三个文本输入框
		combo.setSize(145, 20);
		combo.setLocation(176, 85);
		if("day".equals(type)){
			combo.add("");
		}
		if("day of week".equals(type)){
				combo.add("星期一");
				combo.add("星期二");
				combo.add("星期三");
				combo.add("星期四");
				combo.add("星期五");
				combo.add("星期六");
				combo.add("星期日");
		}
		if("day of month".equals(type)){
			for (int i = 1; i <= 31; i++) {
				combo.add(""+i); //循环添加选项
			}
		}
		
		
		Label lblNewLabel_3 = new Label(g, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_3.setBounds(20, 123, 122, 18);
		lblNewLabel_3.setText("\u5F00\u59CB\u65F6\u95F4");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date startDateTime  = new Date();
		Date endDateTime  = new Date();
		startcal = Calendar.getInstance();
		startcal.setTime(startDateTime);
		startTime = new DateTime(g, SWT.TIME
				| SWT.SHORT);
		startTime.setLocation(176, 120);//第四个文本输入框
		startTime.setSize(79, 21);
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
		
		Label lblNewLabel_4 = new Label(g, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		lblNewLabel_4.setBounds(20, 154, 122, 18);
		lblNewLabel_4.setText("\u7ED3\u675F\u65F6\u95F4");
		
		Calendar endcal = Calendar.getInstance();
		endcal.setTime(endDateTime);
	    endTime = new DateTime(g, SWT.TIME
				| SWT.SHORT);
		endTime.setLocation(176, 151);//第五个文本输入框
		endTime.setSize(79, 21);
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
	public void createButtonsForButtonBar(Composite parent) {//设置保存和取消两个按钮
		applyButton = createButton(parent, IDialogConstants.OK_ID, "保存",true);
		closeButton=createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
    }
	public void buttonPressed(int buttonId){
	    strID=bo1.get_Id();
		if(buttonId==IDialogConstants.OK_ID){
		    bo = ConnectionBroker.get_SiteviewApi()//得到数据库表
					.get_BusObService().Create("DutyDetail");
			bo.GetField("ReceiveAlarmpPhone").SetValue(//得到第一个文本框里的数据
					new SiteviewValue(text.getText()));
			bo.GetField("ReceiveAlarmEmail").SetValue(//得到第二个文本框里的数据
					new SiteviewValue(text_1.getText()));
			bo.GetField("Week").SetValue(//得到第三个文本框里的数据
					new SiteviewValue(combo.getText()));
			bo.GetField("StartTime").SetValue(//得到第四个文本框里的数据
					new SiteviewValue(startTimeStr));
			bo.GetField("EndTime").SetValue(//得到第五个文本框里的数据
					new SiteviewValue(endTimeStr));
			bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//将数据存储到数据
			
			sub.put(strID, bo);	
			
//			TableDuty.TableViewer1.setContentProvider(new DutyDetailContentProvider());
//			TableDuty.TableViewer1.setLabelProvider(new DutyDetailLabelProvider());
//			TableDuty.TableViewer1.setInput(DutyDetailInfor.getDutyDetailInfor(sub,strID));
            
//			DetailModel detailModel=new DetailModel(bo);
//			detailModel.setReceiveAlarmpPhone(text.getText());
//			detailModel.setReceiveAlarmEmail(text_1.getText());
//			detailModel.setWeek((combo.getText()));
//			detailModel.setStartTime(startTimeStr);
//			detailModel.setEndTime(endTimeStr);
//			List list=(List) TableDuty.TableViewer1.getInput();
//		    list.add(detailModel);
//			TableDuty.TableViewer1.setInput(detailModel);
//			TableDuty.TableViewer1.refresh();
					}
		this.close();
	}
	
	public static BusinessObject getBo() {
		return bo;
	}
	public static void setBo(BusinessObject bo) {
		AddDutyDetail.bo = bo;
	}
	
}
