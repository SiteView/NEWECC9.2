package SiteView.ecc.dialog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import SiteView.ecc.data.DutyDetailInfor;
import SiteView.ecc.editors.TableDuty;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

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
public Date startDateTime;
private Text text;
private Text text_1;
private Combo combo;
private Combo combo_1;
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
		newShell.setText("�༭ֵ�����ϸ��Ϣ");
	}
	protected Control createDialogArea(Composite parent){
		Composite container = (Composite) super.createDialogArea(parent);
		Group g=new Group(container,SWT.NONE);
		GridData gd_g = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_g.heightHint = 198;
		gd_g.widthHint = 418;
		g.setLayoutData(gd_g);
		g.setText("��ϸ��Ϣ");
		
		Label lblNewLabel = new Label(g, SWT.NONE);//�ֻ�����
		lblNewLabel.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
		lblNewLabel.setText("\u63A5\u6536\u544A\u8B66\u77ED\u4FE1\u624B\u673A\u53F7\u7801:");
		lblNewLabel.setBounds(30, 25, 164, 20);
		
		BigDecimal  big=new BigDecimal(bv.GetField("ReceiveAlarmpPhone").get_NativeValue().toString());
		text = new Text(g, SWT.BORDER);
		text.setBounds(197, 24, 181, 18);
		text.setText(big.toPlainString());
		
		Label lblNewLabel_1 = new Label(g, SWT.NONE);//����
		lblNewLabel_1.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(30, 56, 164, 19);
		lblNewLabel_1.setText("\u63A5\u6536\u544A\u8B66\u4FE1\u606F\u90AE\u7BB1:");
		
		text_1 = new Text(g, SWT.BORDER);
		text_1.setBounds(197, 57, 181, 20);
		text_1.setText(bv.GetField("ReceiveAlarmEmail").get_NativeValue().toString());
		
		if(bv.GetField("Week").get_NativeValue().toString().contentEquals("")){
			Label lblNewLabel_3 = new Label(g, SWT.NONE);//��ʼʱ��
			lblNewLabel_3.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
			lblNewLabel_3.setBounds(30, 92, 164, 19);
			lblNewLabel_3.setText("\u5F00\u59CB\u65F6\u95F4:");
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			startcal = Calendar.getInstance();
			startTime = new DateTime(g, SWT.TIME
					| SWT.SHORT);
			startTime.setLocation(197, 92);//��ʼʱ���ı������
			startTime.setSize(79, 18);
			
			startcal.set(Calendar.HOUR_OF_DAY,9);
			startcal.set(Calendar.MINUTE, 0);
			startcal.set(Calendar.SECOND, 0);
			
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
		    startcal.setTime(startDateTime);
			startTimeStr = new SimpleDateFormat("HH:mm:ss")
			.format(startcal.getTime());
			
			Label lblNewLabel_4 = new Label(g, SWT.NONE);//����ʱ��
			lblNewLabel_4.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
			lblNewLabel_4.setBounds(30, 122, 164, 19);
			lblNewLabel_4.setText("\u7ED3\u675F\u65F6\u95F4:");
			
			Date endDateTime  = new Date();
			Calendar endcal = Calendar.getInstance();
			endcal.setTime(endDateTime);
		    endTime = new DateTime(g, SWT.TIME
					| SWT.SHORT);
		    endTime.setLocation(197, 123);//����ʱ���ı������
			endTime.setSize(79, 18);
			
			endcal.set(Calendar.HOUR_OF_DAY, 18);
			endcal.set(Calendar.MINUTE, 0);
			endcal.set(Calendar.SECOND, 0);
			
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
		}
		
		if(bv.GetField("Week").get_NativeValue().toString().contentEquals("����һ")||bv.GetField("Week").get_NativeValue().toString().contentEquals("���ڶ�")
				||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")
				||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")
				||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")){
			Label lblNewLabel_2 = new Label(g, SWT.NONE);//����
			lblNewLabel_2.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
			lblNewLabel_2.setBounds(30, 90, 164, 19);
			lblNewLabel_2.setText("����:");
			
		    combo = new Combo(g, SWT.NONE);
			combo.setBounds(197, 90, 87, 20);
			combo.setText(bv.GetField("Week").get_NativeValue().toString());
			combo.add("����һ");
			combo.add("���ڶ�");
			combo.add("������");
			combo.add("������");
			combo.add("������");
			combo.add("������");
			combo.add("������");
			
			
			Label lblNewLabel_3 = new Label(g, SWT.NONE);//��ʼʱ��
			lblNewLabel_3.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
			lblNewLabel_3.setBounds(30, 124, 164, 19);
			lblNewLabel_3.setText("\u5F00\u59CB\u65F6\u95F4:");
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			Date startDateTime  = new Date();
			Date endDateTime  = new Date();
			startcal = Calendar.getInstance();
			startcal.setTime(startDateTime);
			startTime = new DateTime(g, SWT.TIME
					| SWT.SHORT);
			startTime.setLocation(197, 122);//��ʼʱ���ı������
			startTime.setSize(79, 18);
			
			startcal.set(Calendar.HOUR_OF_DAY,9);
			startcal.set(Calendar.MINUTE, 0);
			startcal.set(Calendar.SECOND, 0);
			
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
			
			Label lblNewLabel_4 = new Label(g, SWT.NONE);//����ʱ��
			lblNewLabel_4.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
			lblNewLabel_4.setBounds(30, 156, 164, 19);
			lblNewLabel_4.setText("\u7ED3\u675F\u65F6\u95F4:");
			
			Calendar endcal = Calendar.getInstance();
			endcal.setTime(endDateTime);
		    endTime = new DateTime(g, SWT.TIME
					| SWT.SHORT);
		    endTime.setLocation(197, 154);//����ʱ���ı������
			endTime.setSize(79, 18);
			
			endcal.set(Calendar.HOUR_OF_DAY, 18);
			endcal.set(Calendar.MINUTE, 0);
			endcal.set(Calendar.SECOND, 0);
			
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
		}
		for(int i=1;i<=31;i++){
			String str=""+i;
			if(bv.GetField("Week").get_NativeValue().toString().contentEquals(str)){
				Label lblNewLabel_2 = new Label(g, SWT.NONE);//����
				lblNewLabel_2.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
				lblNewLabel_2.setBounds(30, 90, 164, 19);
				lblNewLabel_2.setText("����:");
				
				combo_1 = new Combo(g, SWT.NONE);
				combo_1.setBounds(197, 90, 87, 20);
				combo_1.setText(bv.GetField("Week").get_NativeValue().toString());
				for (int i1=1; i1 <= 31; i1++) {
					combo_1.add(""+i1); //ѭ�����ѡ��
				}
				
				Label lblNewLabel_3 = new Label(g, SWT.NONE);//��ʼʱ��
				lblNewLabel_3.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
				lblNewLabel_3.setBounds(30, 124, 164, 19);
				lblNewLabel_3.setText("\u5F00\u59CB\u65F6\u95F4:");
				
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				Date startDateTime  = new Date();
				Date endDateTime  = new Date();
				startcal = Calendar.getInstance();
				startcal.setTime(startDateTime);
				startTime = new DateTime(g, SWT.TIME
						| SWT.SHORT);
				startTime.setLocation(197, 122);//��ʼʱ���ı������
				startTime.setSize(79, 18);
				
				startcal.set(Calendar.HOUR_OF_DAY,9);
				startcal.set(Calendar.MINUTE, 0);
				startcal.set(Calendar.SECOND, 0);
				
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
				
				Label lblNewLabel_4 = new Label(g, SWT.NONE);//����ʱ��
				lblNewLabel_4.setFont(SWTResourceManager.getFont("����", 11, SWT.NORMAL));
				lblNewLabel_4.setBounds(30, 156, 164, 19);
				lblNewLabel_4.setText("\u7ED3\u675F\u65F6\u95F4:");
				
				Calendar endcal = Calendar.getInstance();
				endcal.setTime(endDateTime);
			    endTime = new DateTime(g, SWT.TIME
						| SWT.SHORT);
			    endTime.setLocation(197, 154);//����ʱ���ı������
				endTime.setSize(79, 18);
				
				endcal.set(Calendar.HOUR_OF_DAY, 18);
				endcal.set(Calendar.MINUTE, 0);
				endcal.set(Calendar.SECOND, 0);
				
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
			}
		}
		
		return container;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		applyButton = createButton(parent, IDialogConstants.OK_ID, "����",true);
		closeButton=createButton(parent, IDialogConstants.CANCEL_ID, "ȡ��", true);
	}
	protected void buttonPressed(int buttonId){
		if(bv.GetField("Week").get_NativeValue().toString().contentEquals("")){
			if(buttonId==IDialogConstants.OK_ID){
				BigDecimal  big=new BigDecimal(text.getText());
				bv.GetField("ReceiveAlarmpPhone").SetValue(new SiteviewValue(text.getText()));//�ֻ������޸�
				dm.setReceiveAlarmpPhone(big.toPlainString());
				
				bv.GetField("ReceiveAlarmEmail").SetValue(new SiteviewValue(text_1.getText()));//�����޸�
				dm.setReceiveAlarmEmail(text_1.getText());
				
				bv.SaveObject(ConnectionBroker.get_SiteviewApi(), true,//���޸ĺ�����ݴ洢�����ݿ�
						true);
				TableDuty.TableViewer1.setInput(DutyDetailInfor.list);
				TableDuty.TableViewer1.refresh();//�����ϵ���������Ӧ�ĸ���
			}
		}
		if(bv.GetField("Week").get_NativeValue().toString().contentEquals("����һ")||bv.GetField("Week").get_NativeValue().toString().contentEquals("���ڶ�")
				||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")
				||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")
				||bv.GetField("Week").get_NativeValue().toString().contentEquals("������")){
			if(buttonId==IDialogConstants.OK_ID){
				BigDecimal  big=new BigDecimal(text.getText());
				bv.GetField("ReceiveAlarmpPhone").SetValue(new SiteviewValue(text.getText()));//�ֻ������޸�
				dm.setReceiveAlarmpPhone(big.toPlainString());
				
				bv.GetField("ReceiveAlarmEmail").SetValue(new SiteviewValue(text_1.getText()));//�����޸�
				dm.setReceiveAlarmEmail(text_1.getText());
				
			    bv.GetField("Week").SetValue(new SiteviewValue(combo.getText()));//�����޸�
				dm.setWeek(combo.getText());
				
			
			bv.SaveObject(ConnectionBroker.get_SiteviewApi(), true,//���޸ĺ�����ݴ洢�����ݿ�
					true);
			TableDuty.TableViewer1.setInput(DutyDetailInfor.list);
			TableDuty.TableViewer1.refresh();//�����ϵ���������Ӧ�ĸ���
		}
		}
			for(int i=1;i<=31;i++){//month������
				String str=""+i;
				if(bv.GetField("Week").get_NativeValue().toString().contentEquals(str)){
					if(buttonId==IDialogConstants.OK_ID){
						BigDecimal  big=new BigDecimal(text.getText());
						bv.GetField("ReceiveAlarmpPhone").SetValue(new SiteviewValue(text.getText()));//�ֻ������޸�
						dm.setReceiveAlarmpPhone(big.toPlainString());
						
						bv.GetField("ReceiveAlarmEmail").SetValue(new SiteviewValue(text_1.getText()));//�����޸�
						dm.setReceiveAlarmEmail(text_1.getText());
						
					    bv.GetField("Week").SetValue(new SiteviewValue(combo_1.getText()));//�����޸�
						dm.setWeek(combo_1.getText());
						
					
					bv.SaveObject(ConnectionBroker.get_SiteviewApi(), true,//���޸ĺ�����ݴ洢�����ݿ�
							true);
					TableDuty.TableViewer1.setInput(DutyDetailInfor.list);
					TableDuty.TableViewer1.refresh();//�����ϵ���������Ӧ�ĸ���
				}
				}
			}
		
			
	
		this.close();
	}
}
