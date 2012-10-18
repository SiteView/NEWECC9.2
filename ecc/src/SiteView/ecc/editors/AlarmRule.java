package SiteView.ecc.editors;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;

import siteview.windows.forms.ImageHelper;
import system.Collections.ICollection;
import system.Collections.IEnumerable;
import system.Collections.IEnumerator;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.AlarmRuleInfo;
import SiteView.ecc.Modle.SMSModel;
import SiteView.ecc.dialog.AddEmailAlarmRule;
import SiteView.ecc.tab.views.MonitorLogTabView;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class AlarmRule extends EditorPart {
	public static Table table;
	private Table table_1;
	public static final String ID="SiteView.ecc.editors.AlarmRule";
	private Action emailAction;//邮件报警
	private Action messageAction;//短信报警
	private Action scriptAction;//脚本报警
	private Action soundAction;//声音报警
	public Menu popmenu;
	TableItem tableItem;
	public static List<AlarmRuleInfo> list;
	public AlarmRule() {
		createAction();
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
		this.setSite(site);
		this.setInput(input);
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
			getData();
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setBackground(EccTreeControl.color);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(EccTreeControl.color);
		
		Button button = new Button(composite, SWT.NONE);
		button.setBounds(0, 10, 59, 22);
		button.setText("\u6DFB\u52A0");
		final MenuManager pm = new MenuManager();
		pm.setRemoveAllWhenShown(true);
		popmenu = pm.createContextMenu(button);
		pm.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				manager.add(emailAction);				
				manager.add(messageAction);				
				manager.add(scriptAction);				
				manager.add(soundAction);				
			}
		});
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				popmenu.setVisible(true);
			}
		});
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setBounds(75, 10, 59, 22);
		button_1.setText("\u5220\u9664");
		button_1.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(!(tableItem.isDisposed())){
					BusinessObject bo1 = (BusinessObject) tableItem.getData();
					ICollection ic=FileTools.getBussCollection("AlarmName", bo1.GetField("AlarmName").get_NativeValue().toString(), "EccAlarmRule");
					IEnumerator ien=ic.GetEnumerator();
					while(ien.MoveNext()){
						BusinessObject bo=(BusinessObject) ien.get_Current();
						bo.DeleteObject(ConnectionBroker.get_SiteviewApi());
					}
					tableItem.dispose();
				}
				
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setBounds(152, 10, 72, 22);
		button_2.setText("\u5141\u8BB8");
		button_2.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				BusinessObject bo1 = (BusinessObject) tableItem.getData();
				ICollection ic=FileTools.getBussCollection("AlarmName", bo1.GetField("AlarmName").get_NativeValue().toString(), "EccAlarmRule");
				IEnumerator ien=ic.GetEnumerator();
				while(ien.MoveNext()){
					BusinessObject bo=(BusinessObject) ien.get_Current();
					bo.GetField("RuleStatus").SetValue(new SiteviewValue(true));
					bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				}
				tableItem.setImage(3,ImageHelper.LoadImage(Activator.PLUGIN_ID,"Image/promiss.bmp"));
				tableItem.setText(3, "启动中");
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.setBounds(242, 10, 72, 22);
		button_3.setText("\u7981\u6B62");
		button_3.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				BusinessObject bo1 = (BusinessObject) tableItem.getData();
				ICollection ic=FileTools.getBussCollection("AlarmName", bo1.GetField("AlarmName").get_NativeValue().toString(), "EccAlarmRule");
				IEnumerator ien=ic.GetEnumerator();
				while(ien.MoveNext()){
					BusinessObject bo=(BusinessObject) ien.get_Current();
					bo.GetField("RuleStatus").SetValue(new SiteviewValue(false));
					bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				}
				tableItem.setImage(3,ImageHelper.LoadImage(Activator.PLUGIN_ID,"Image/stop.bmp"));
				tableItem.setText(3, "禁止");
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		Button button_4 = new Button(composite, SWT.NONE);
		button_4.setBounds(330, 10, 72, 22);
		button_4.setText("\u5237\u65B0");
		
		Label lblNewLabel = new Label(sashForm, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setText("\u62A5\u8B66\u89C4\u5219\u5217\u8868");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		table.setBackground(EccTreeControl.color);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem[] item = table.getItems(); 
				for (TableItem tableItem : item) {
					tableItem.setChecked(false);
				}
				tableItem = (TableItem) e.item;
				tableItem.setChecked(true);
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u540D\u79F0");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u62A5\u8B66\u7C7B\u578B");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u62A5\u8B66\u72B6\u6001");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u542F\u52A8/\u7981\u6B62");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u7F16\u8F91");
		createTableItem();
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		cursor.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				int column = cursor.getColumn();
				if(column==4){
					TableItem tableItem = cursor.getRow();
					BusinessObject bo1 = (BusinessObject)tableItem.getData();
					String name = bo1.GetField("AlarmType").get_NativeValue().toString();
					AddEmailAlarmRule aear = new AddEmailAlarmRule(null, name, bo1);
					aear.open();
				}
			}
			public void mouseDown(MouseEvent e) {
			}
			
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		
		Label lblNewLabel_1 = new Label(sashForm, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setText("\u62A5\u8B66\u65E5\u5FD7\u5217\u8868");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(EccTreeControl.color);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_1 = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBackground(EccTreeControl.color);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u62A5\u8B66\u65F6\u95F4");
		
		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u62A5\u8B66\u540D\u79F0");
		
		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("\u62A5\u8B66\u7EC4");
		
		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("\u62A5\u8B66\u76D1\u6D4B\u5668");
		
		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("\u62A5\u8B66\u7C7B\u578B");
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("\u63A5\u6536\u4EBA");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("\u62A5\u8B66\u72B6\u6001");
		sashForm.setWeights(new int[] {25, 13, 221, 12, 191});

		String time = MonitorLogTabView.getHoursAgoTime(2);
		Map<String, Object> map = new HashMap<String, Object>();
		String[] arr = time.split("\\*");
		map.put("startTime", arr[1]);
		map.put("endTime", arr[0]);
		map.put("LastModBy", null);
		ICollection ic = FileTools.getLog2(map, "EccAlarmLog");
		IEnumerator ieable = ic.GetEnumerator();
		while(ieable.MoveNext()){
			TableItem item = new TableItem(table_1, SWT.NONE);
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
	
	//获取数据
	public static void getData(){
		list=new ArrayList();
		ICollection ic=FileTools.getBussCollection("EccAlarmRule");
		IEnumerator ien=ic.GetEnumerator();
		while(ien.MoveNext()){
			BusinessObject bo=(BusinessObject) ien.get_Current();
			AlarmRuleInfo ar=new AlarmRuleInfo(bo);
			list.add(ar);
		}
	}
	
	//创建表单数据
	public static void createTableItem(){
		Set<String> set=new HashSet<String>();
		 for(int i=0;i<list.size();i++){
			 if(i==0){				 
				 get(0);
			 }
			String alarmName = list.get(i).getBo().GetField("AlarmName").get_NativeValue().toString();
			Iterator<String> ite = set.iterator();
			for (int j=0;j<set.size();j++) {
				if(alarmName.equals(ite.next())){
					break;
				}
				if(!ite.hasNext()){
					get(i);
				}
			}
			set.add(alarmName);
		}
	}
	
	
	public static void get(int i){
		TableItem item = new TableItem(table, SWT.NONE);
		item.setText(0, list.get(i).getBo().GetField("AlarmName").get_NativeValue().toString());
		if(list.get(i).getBo().GetField("AlarmType").get_NativeValue().toString().equals("email")){				
			item.setImage(1, ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/email.jpg"));
		}else if(list.get(i).getBo().GetField("AlarmType").get_NativeValue().toString().equals("SMS")){
			item.setImage(1, ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/message.jpg"));
		}else if(list.get(i).getBo().GetField("AlarmType").get_NativeValue().toString().equals("script")){
			item.setImage(1, ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/script.jpg"));
		}else if(list.get(i).getBo().GetField("AlarmType").get_NativeValue().toString().equals("sound")){
			item.setImage(1, ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/sound.jpg"));
		}
		item.setText(1, list.get(i).getBo().GetField("AlarmType").get_NativeValue().toString());
		if(list.get(i).getBo().GetField("AlarmEvent").get_NativeValue().toString().equals("warning")){
			item.setImage(2, ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/warning.png"));
		}else if(list.get(i).getBo().GetField("AlarmEvent").get_NativeValue().toString().equals("error")){
			item.setImage(2, ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/alarmerror.png"));
		}
		item.setText(2, list.get(i).getBo().GetField("AlarmEvent").get_NativeValue().toString());
		if((Boolean)list.get(i).getBo().GetField("RuleStatus").get_NativeValue()){
			item.setImage(3,ImageHelper.LoadImage(Activator.PLUGIN_ID,"Image/promiss.bmp"));
			item.setText(3, "启动中");
		}else{
			item.setImage(3,ImageHelper.LoadImage(Activator.PLUGIN_ID,"Image/stop.bmp"));
			item.setText(3, "禁止");
		}
		item.setImage(4, ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/edit.jpg"));
		item.setData(list.get(i).getBo());
	}
	
	//销毁表单数据
	public static void disposeTableItem(){
		TableItem[] item1 = table.getItems();
		if(item1.length!=0){			
			for (TableItem tableItem : item1) {
				tableItem.dispose();
			}
		}
	}
	
	private void createAction(){
		emailAction = new Action("邮件报警") {
			public void run(){
				AddEmailAlarmRule add=new AddEmailAlarmRule(null,"email",null);
				add.open();
			}
		};
		URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),"icons/email.jpg");
		ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		emailAction.setImageDescriptor(temp);
		
		messageAction = new Action("短信报警") {
			public void run(){
				AddEmailAlarmRule add=new AddEmailAlarmRule(null,"SMS",null);
				add.open();
			}
		};
		URL url1 = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),"icons/message.jpg");
		ImageDescriptor temp1 = ImageDescriptor.createFromURL(url1);
		messageAction.setImageDescriptor(temp1);
		
		scriptAction = new Action("脚本报警") {
			public void run(){
				AddEmailAlarmRule add=new AddEmailAlarmRule(null,"script",null);
				add.open();
			}
		};
		URL url2 = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),"icons/script.jpg");
		ImageDescriptor temp2 = ImageDescriptor.createFromURL(url2);
		scriptAction.setImageDescriptor(temp2);
		
		soundAction = new Action("声音报警") {
			public void run(){
				AddEmailAlarmRule add=new AddEmailAlarmRule(null,"sound",null);
				add.open();
			}
		};
		URL url3 = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),"icons/sound.jpg");
		ImageDescriptor temp3 = ImageDescriptor.createFromURL(url3);
		soundAction.setImageDescriptor(temp3);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
