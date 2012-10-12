package SiteView.ecc.editors;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;

public class AlarmLog extends EditorPart{
	public static String ID = "SiteView.ecc.editors.AlarmLog";
	private Table table;
	public AlarmLog() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		this.setInput(input);
		this.setSite(site);
		this.setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label = new Label(composite, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u67E5\u8BE2\u6761\u4EF6");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_1.setBounds(0, 10, 66, 12);
		lblNewLabel_1.setText(" \u62A5\u8B66\u540D\u79F0\uFF1A");
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_2.setBounds(0, 33, 78, 12);
		lblNewLabel_2.setText(" \u62A5\u8B66\u63A5\u6536\u4EBA\uFF1A");
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_1.setBounds(0, 51, 90, 12);
		label_1.setText(" \u62A5\u8B66\u5F00\u59CB\u65F6\u95F4\uFF1A");
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_2.setBounds(0, 69, 104, 12);
		label_2.setText(" \u62A5\u8B66\u7ED3\u675F\u65F6\u95F4\uFF1A");
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_3.setBounds(0, 96, 78, 12);
		label_3.setText(" \u62A5\u8B66\u7C7B\u578B\uFF1A");
		
		final Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setBounds(195, 2, 181, 20);
		combo.add("全部");
		ICollection ico=FileTools.getBussCollection("EccAlarmRule");
		IEnumerator ien=ico.GetEnumerator();
		Set<String> set_AlarmName = new HashSet<String>();
		Set<String> set_AlarmReceiver = new HashSet<String>();
		while(ien.MoveNext()){
			BusinessObject bo = (BusinessObject)ien.get_Current();
			set_AlarmName.add(bo.GetField("AlarmName").get_NativeValue().toString());
			set_AlarmReceiver.add(bo.GetField("Other").get_NativeValue().toString());
		}
		Iterator iter_Name = set_AlarmName.iterator();
		while(iter_Name.hasNext()){
			combo.add(iter_Name.next().toString());
		}
		combo.select(0);
		
		ICollection ico2=FileTools.getBussCollection("MailType","receiver","EccMail");
		IEnumerator ien2=ico2.GetEnumerator();
		while(ien2.MoveNext()){
			BusinessObject bo = (BusinessObject)ien2.get_Current();
			set_AlarmReceiver.add(bo.GetField("MailAddress").get_NativeValue().toString());
		}
		
		ICollection ico3=FileTools.getBussCollection("SMSType","receive","EccSMS");
		IEnumerator ien3=ico3.GetEnumerator();
		while(ien3.MoveNext()){
			BusinessObject bo = (BusinessObject)ien3.get_Current();
			set_AlarmReceiver.add(new BigDecimal((Double)bo.GetField("MobliePhone").get_NativeValue()).toPlainString());
		}
		
		ICollection ico4=FileTools.getBussCollection("DutyDetail");
		IEnumerator ien4=ico4.GetEnumerator();
		while(ien4.MoveNext()){
			BusinessObject bo = (BusinessObject)ien4.get_Current();
			set_AlarmReceiver.add(new BigDecimal((Double)bo.GetField("ReceiveAlarmpPhone").get_NativeValue()).toPlainString());
			set_AlarmReceiver.add(bo.GetField("ReceiveAlarmEmail").get_NativeValue().toString());
		}
		
		final Combo combo_1 = new Combo(composite_1, SWT.NONE);
		combo_1.setBounds(195, 25, 181, 20);
		combo_1.add("全部");
		Iterator iter_Receiver = set_AlarmReceiver.iterator();
		while(iter_Receiver.hasNext()){
			combo_1.add(iter_Receiver.next().toString());
		}
		combo_1.select(0);
		
		final DateTime dateTime = new DateTime(composite_1, SWT.BORDER);
		dateTime.setBounds(195, 51, 90, 20);
		
		final DateTime dateTime_1 = new DateTime(composite_1, SWT.BORDER);
		dateTime_1.setBounds(195, 69, 90, 20);
		
		final Combo combo_2 = new Combo(composite_1, SWT.READ_ONLY);
		combo_2.setBounds(195, 93, 181, 20);
		combo_2.add("全部");
		combo_2.add("邮件报警");
		combo_2.add("短信报警");
		combo_2.add("脚本报警");
		combo_2.add("声音报警");
		combo_2.select(0);
		
		final DateTime dateTime_2 = new DateTime(composite_1, SWT.TIME);
		dateTime_2.setBounds(286, 51, 90, 20);
		
		final DateTime dateTime_3 = new DateTime(composite_1, SWT.TIME);
		dateTime_3.setBounds(286, 69, 90, 20);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				TableItem[] itemArr = table.getItems();
				for(int i=0;i<itemArr.length;i++){
					itemArr[i].dispose();
				}
				Map<String, Object> map = new HashMap<String, Object>();
				String alarmName = combo.getItem(combo.getSelectionIndex());
				String receiverAddress = combo_1.getItem(combo_1.getSelectionIndex());
				String startTime = dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+" "+dateTime_2.getHours()+":"+dateTime_2.getMinutes()+":"+dateTime_2.getSeconds();
				String endTime = dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-"+dateTime_1.getDay()+" "+dateTime_3.getHours()+":"+dateTime_3.getMinutes()+":"+dateTime_3.getSeconds();
				String[] arr = {"全部","email","SMS","script","sound"};
				String alarmType = arr[combo_2.getSelectionIndex()];
				if(!alarmName.equals("全部")){
					map.put("AlarmName", alarmName);
				}
				if(!receiverAddress.equals("全部")){
					map.put("ReceiverAddress", receiverAddress);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					startTime = sdf.format(sdf.parse(startTime));
					endTime = sdf.format(sdf.parse(endTime));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				map.put("startTime", startTime);
				map.put("endTime", endTime);
				if(!alarmType.equals("全部")){
					map.put("AlarmType", alarmType);
				}
				ICollection ic = null;
				if(map.size()==2){
					map.put("LastModBy", null);
					ic = FileTools.getLog2(map, "EccAlarmLog");
				}else{
					ic = FileTools.getLog(map, "EccAlarmLog");
				}
				IEnumerator ieable = ic.GetEnumerator();
				while(ieable.MoveNext()){
					TableItem item = new TableItem(table, SWT.NONE);
					BusinessObject bo = (BusinessObject)ieable.get_Current();
					item.setText(0, bo.GetField("CreatedDateTime").get_NativeValue().toString());
					item.setText(1, bo.GetField("AlarmName").get_NativeValue().toString());
					item.setText(2, bo.GetField("AlarmGroup").get_NativeValue().toString());
					item.setText(3, bo.GetField("AlarmMonitor").get_NativeValue().toString());
					item.setText(4, bo.GetField("AlarmType").get_NativeValue().toString());
					item.setText(5, bo.GetField("ReceiverAddress").get_NativeValue().toString());
					item.setText(6, bo.GetField("AlarmStatus").get_NativeValue().toString());
				}
			}
			public void mouseDown(MouseEvent e) {
			}
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		btnNewButton.setBounds(195, 119, 53, 22);
		btnNewButton.setText("查询");
		btnNewButton.setCursor(new Cursor(Display.getDefault(), SWT.CURSOR_HAND));
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setText("\u62A5\u8B66\u65E5\u5FD7\u5217\u8868");
		
		table = new Table(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(90);
		tblclmnNewColumn.setText("报警时间");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(90);
		tblclmnNewColumn_1.setText("报警名称");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(90);
		tblclmnNewColumn_2.setText("报警组");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(90);
		tblclmnNewColumn_3.setText("报警监测器");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_4.setWidth(90);
		tblclmnNewColumn_4.setText("报警类型");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_5.setWidth(90);
		tblclmnNewColumn_5.setText("接收人");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_6.setWidth(90);
		tblclmnNewColumn_6.setText("报警状态");
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		sashForm.setWeights(new int[] {13, 140, 27, 282, 0});
		// TODO Auto-generated method stub
		
	}
	public void setFocus() {
	}
}
