package SiteView.ecc.editors;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import siteview.windows.forms.ImageHelper;
import system.Collections.ICollection;
import system.Collections.IEnumerator;

import SiteView.ecc.Activator;
import SiteView.ecc.Control.TableComparer;
import SiteView.ecc.Control.TableDutyContentProvider;
import SiteView.ecc.Control.TableDutyLabelProvider;
import SiteView.ecc.Modle.EmailModle;
import SiteView.ecc.Modle.SMSModel;
import SiteView.ecc.dialog.AddEmail;
import SiteView.ecc.dialog.AddSMSModleSet;
import SiteView.ecc.dialog.AddSMS;
import SiteView.ecc.tools.FileTools;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class MessageSetUp extends EditorPart {
	public static final String ID = "SiteView.ecc.editors.MessageSetUp";
	private Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	BusinessObject SMSsend;
	public static List<SMSModel> list;
	public static TableViewer tableViewer;
	TableItem tableItem;
	private Action messageAction;//短信模板设置
	private Action webMessageAction;//web短信模板设置
	public Menu popmenu;
	public MessageSetUp() {
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
		this.setSite(site);// 设置site
		this.setInput(input);// 设置输入的IEditorInput对象
		this.setPartName(input.getName());// 设置编辑器上方显示的名称

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

	public void createPartControl(Composite parent) {
		ICollection ico=FileTools.getBussCollection("SMSType", "send", "EccSMS");
		IEnumerator ien=ico.GetEnumerator();
		while(ien.MoveNext()){
			SMSsend=(BusinessObject) ien.get_Current();
		}
		if(list==null){
			list = new ArrayList<SMSModel>();
			ICollection ic=FileTools.getBussCollection("SMSType", "receive", "EccSMS");
			ien=ic.GetEnumerator();
			while(ien.MoveNext()){
				BusinessObject bo=(BusinessObject) ien.get_Current();
				SMSModel sms=new SMSModel(bo);
				list.add(sms);
			}
		}
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setText("\u77ED\u4FE1\u8BBE\u7F6E");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(null);
		
		Button addButton = new Button(composite_1, SWT.NONE);
		addButton.setBounds(10, 0, 36, 22);
		addButton.setText("添加");
		addButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				AddSMS add = new AddSMS(null);
				add.open();
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Button delButton = new Button(composite_1, SWT.NONE);
		delButton.setBounds(52, 0, 36, 22);
		delButton.setText("删除");
		delButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				SMSModel sms = (SMSModel) tableItem.getData();
				sms.getBo().DeleteObject(ConnectionBroker.get_SiteviewApi());
				tableItem.dispose();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Button allowButton = new Button(composite_1, SWT.NONE);
		allowButton.setBounds(94, 0, 36, 22);
		allowButton.setText("允许");
		allowButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				SMSModel sms = (SMSModel) tableItem.getData();
				sms.getBo().GetField("IsStop").SetValue(new SiteviewValue(true));
				sms.getBo().SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				tableItem.setImage(1,ImageHelper.LoadImage(Activator.PLUGIN_ID,"Image/promiss.bmp"));
				tableItem.setText(1, "允许");
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Button forbidButton = new Button(composite_1, SWT.NONE);
		forbidButton.setBounds(136, 0, 36, 22);
		forbidButton.setText("禁止");
		forbidButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				SMSModel sms = (SMSModel) tableItem.getData();
				sms.getBo().GetField("IsStop").SetValue(new SiteviewValue(false));
				sms.getBo().SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				tableItem.setImage(1,ImageHelper.LoadImage(Activator.PLUGIN_ID,"Image/stop.bmp"));
				tableItem.setText(1, "禁止");
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Button refreshButton = new Button(composite_1, SWT.NONE);
		refreshButton.setBounds(178, 0, 36, 22);
		refreshButton.setText("刷新");
		
		Button mouldButton = new Button(composite_1, SWT.NONE);
		mouldButton.setBounds(220, 0, 60, 22);
		mouldButton.setText("模板设置");
		final MenuManager pm = new MenuManager();
		pm.setRemoveAllWhenShown(true);
		popmenu = pm.createContextMenu(mouldButton);
		pm.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {				
				manager.add(messageAction);				
				manager.add(webMessageAction);					
			}
		});
		mouldButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				popmenu.setVisible(true);
			}
		});
		
		Button helpButton = new Button(composite_1, SWT.NONE);
		helpButton.setBounds(286, 0, 36, 22);
		helpButton.setText("帮助");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u77ED\u4FE1\u8BBE\u7F6E\u8BE6\u7EC6\u4FE1\u606F");
		
		tableViewer = new TableViewer(sashForm, SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table = tableViewer.getTable();
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				tableItem=(TableItem) e.item;
				tableItem.setChecked(true);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		cursor.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
				int column = cursor.getColumn();
				for(TableItem ta:table.getItems()){
					if(!ta.equals(tableItem)){
						ta.setChecked(false);
					}
				}
				if(column==3){
					AddSMS edit=new AddSMS(null,(SMSModel)tableItem.getData());
					edit.open();
				}
			}
			public void mouseDown(MouseEvent e) {}
			public void mouseDoubleClick(MouseEvent e) {}
		});
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(150);
		tblclmnNewColumn.setText("\u540D\u79F0");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(150);
		tableColumn.setText("\u72B6\u6001");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("\u624B\u673A\u53F7\u7801");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(150);
		tableColumn_2.setText("\u7F16\u8F91");
		
		TabFolder tabFolder = new TabFolder(sashForm, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u4EE5\u98DE\u4FE1\u65B9\u5F0F\u53D1\u9001\u77ED\u4FE1");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabItem.setControl(composite_3);
		composite_3.setLayout(null);
		
		Label lblNewLabel_1 = new Label(composite_3, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(42, 30, 92, 18);
		lblNewLabel_1.setText("\u624B\u673A\u53F7/\u98DE\u4FE1\u53F7*\uFF1A");
		
		text = new Text(composite_3, SWT.BORDER);
		text.setBounds(140, 30, 170, 18);
		text.setText(SMSsend.GetField("SMSUserName").get_NativeValue().toString());
		
		Label lblNewLabel_2 = new Label(composite_3, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(42, 60, 92, 18);
		lblNewLabel_2.setText("\u5BC6\u7801*\uFF1A");
		
		text_1 = new Text(composite_3, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(140, 60, 170, 18);
		text_1.setText(SMSsend.GetField("SMSPwd").get_NativeValue().toString());
		
		Label lblNewLabel_3 = new Label(composite_3, SWT.NONE);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_3.setBounds(42, 90, 92, 18);
		lblNewLabel_3.setText("\u4FE1\u606F\u957F\u5EA6*\uFF1A");
		
		text_2 = new Text(composite_3, SWT.BORDER);
		text_2.setBounds(140, 90, 170, 18);
		String s=SMSsend.GetField("SMSLength").get_NativeValue().toString();
		text_2.setText(s.substring(0,s.indexOf(".")));
		
		Button btnNewButton = new Button(composite_3, SWT.NONE);
		btnNewButton.setBounds(42, 120, 72, 22);
		btnNewButton.setText("\u5E94\u7528");
		btnNewButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()==null ||text.getText().equals("")){
					MessageDialog.openInformation(null, "提示", "请输入手机号或飞信号");
					return;
				}else if(text_1.getText()==null ||text_1.getText().equals("")){
					MessageDialog.openInformation(null, "提示", "请输入密码");
					return;
				}else if(text_2.getText()==null ||text_2.getText().equals("")){
					MessageDialog.openInformation(null, "提示", "请输入信息长度");
					return;
				}else if(Integer.parseInt(text_2.getText())>70){
					MessageDialog.openInformation(null, "提示", "短信最大长度为70");
					return;
				}
				SMSsend.GetField("SMSType").SetValue(new SiteviewValue("send"));
				SMSsend.GetField("SMSUserName").SetValue(new SiteviewValue(text.getText()));
				SMSsend.GetField("SMSPwd").SetValue(new SiteviewValue(text_1.getText()));
				SMSsend.GetField("SMSLength").SetValue(new SiteviewValue(text_2.getText()));
				SMSsend.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		Button btnNewButton_1 = new Button(composite_3, SWT.NONE);
		btnNewButton_1.setBounds(140, 120, 72, 22);
		btnNewButton_1.setText("\u91CD\u65B0\u83B7\u5F97");
		
		Button btnNewButton_2 = new Button(composite_3, SWT.NONE);
		btnNewButton_2.setBounds(238, 120, 72, 22);
		btnNewButton_2.setText("\u6D4B\u8BD5");
		
		Label lblNewLabel_4 = new Label(composite_3, SWT.NONE);
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setBounds(323, 90, 100, 18);
		lblNewLabel_4.setText("\u77ED\u4FE1\u6700\u5927\u957F\u5EA6\u4E3A70");
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("以串口方式发送短信");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabItem_1.setControl(composite_4);
		
		TabItem tabItem_2 = new TabItem(tabFolder, SWT.NONE);
		tabItem_2.setText("调用动态库中的函数发送短信");
		
		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabItem_2.setControl(composite_5);
		
		TabItem tabItem_3 = new TabItem(tabFolder, SWT.NONE);
		tabItem_3.setText("数据库存取");
		
		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabItem_3.setControl(composite_6);
		tableViewer.setContentProvider(new TableDutyContentProvider());
		tableViewer.setLabelProvider(new TableDutyLabelProvider());
		tableViewer.setInput(list);
		tableViewer.setComparer(new TableComparer());
		sashForm.setWeights(new int[] {1, 2, 1, 15, 15});
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	private void createAction(){
		messageAction = new Action("短信模板设置") {
			public void run(){
				AddSMSModleSet add=new AddSMSModleSet(null,"短信模板设置",null);
				add.open();
			}
		};
		URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),"icons/message.jpg");
		ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		messageAction.setImageDescriptor(temp);
		
		webMessageAction = new Action("Web短信模板设置") {
			public void run(){
				AddSMSModleSet add=new AddSMSModleSet(null,"Web短信模板设置",null);
				add.open();
			}
		};
		URL url1 = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),"Image/mail.bmp");
		ImageDescriptor temp1 = ImageDescriptor.createFromURL(url1);
		webMessageAction.setImageDescriptor(temp1);
	}
}
