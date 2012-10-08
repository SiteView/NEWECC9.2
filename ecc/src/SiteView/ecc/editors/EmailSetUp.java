package SiteView.ecc.editors;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import siteview.windows.forms.ImageHelper;
import system.Collections.ICollection;
import system.Collections.IEnumerator;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import SiteView.ecc.Activator;
import SiteView.ecc.Control.TableComparer;
import SiteView.ecc.Control.TableDutyContentProvider;
import SiteView.ecc.Control.TableDutyLabelProvider;
import SiteView.ecc.Modle.EmailModle;
import SiteView.ecc.Modle.SMSModel;
import SiteView.ecc.dialog.AddEmail;
import SiteView.ecc.dialog.ConnectMail;
import SiteView.ecc.dialog.MailModleSetUp;
import SiteView.ecc.tools.FileTools;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class EmailSetUp extends EditorPart {
	public static final String ID = "SiteView.ecc.editors.EmailSetUp";
	public static List<EmailModle> list;
	BusinessObject sendMailbo=null;
	public static TableViewer tableViewer;
	TableItem tableItem;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	public EmailSetUp() {
	}

	public void doSave(IProgressMonitor monitor) {

	}

	public void doSaveAs() {

	}

	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);// 设置site
		this.setInput(input);// 设置输入的IEditorInput对象
		this.setPartName(input.getName());// 设置编辑器上方显示的名称
	}

	public boolean isDirty() {
		return false;
	}

	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		ICollection ico=FileTools.getBussCollection("MailType", "send", "EccMail");
		IEnumerator ien=ico.GetEnumerator();
		while(ien.MoveNext()){
			sendMailbo=(BusinessObject) ien.get_Current();
		}
		if(list==null){
			list=new ArrayList();
			ICollection ic=FileTools.getBussCollection("MailType", "receiver", "EccMail");
			ien=ic.GetEnumerator();
			while(ien.MoveNext()){
				BusinessObject bo=(BusinessObject) ien.get_Current();
				EmailModle m=new EmailModle(bo);
				list.add(m);
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
		lblNewLabel.setText("\u90AE\u4EF6\u8BBE\u7F6E");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(null);
		
		Button addButton = new Button(composite_1, SWT.NONE);
		addButton.setBounds(10, 0, 36, 22);
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddEmail addmail=new AddEmail(null);
				addmail.open();
			}
		});
		addButton.setText("添加");
		
		Button delButton = new Button(composite_1, SWT.NONE);
		delButton.setBounds(52, 0, 36, 22);
		delButton.setText("删除");
		delButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				EmailModle email = (EmailModle) tableItem.getData();
				email.getBo().DeleteObject(ConnectionBroker.get_SiteviewApi());
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
				EmailModle email = (EmailModle) tableItem.getData();
				email.getBo().GetField("IsStop").SetValue(new SiteviewValue(true));
				email.getBo().SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
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
				EmailModle email = (EmailModle) tableItem.getData();
				email.getBo().GetField("IsStop").SetValue(new SiteviewValue(false));
				email.getBo().SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
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
		mouldButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MailModleSetUp m=new MailModleSetUp(null);
				m.open();
			}
		});
		mouldButton.setText("模板设置");
		
		Button helpButton = new Button(composite_1, SWT.NONE);
		helpButton.setBounds(286, 0, 36, 22);
		helpButton.setText("帮助");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u90AE\u4EF6\u8BBE\u7F6E\u8BE6\u7EC6\u4FE1\u606F");
		
		tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION |SWT.CHECK |SWT.V_SCROLL |SWT.H_SCROLL);
		final Table table = tableViewer.getTable();
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
					AddEmail edit=new AddEmail(null,(EmailModle)tableItem.getData());
					edit.open();
				}
			}
			public void mouseDown(MouseEvent e) {}
			public void mouseDoubleClick(MouseEvent e) {}
		});
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(104);
		tblclmnNewColumn.setText("\u540D\u79F0");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(120);
		tableColumn.setText("\u72B6\u6001");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(160);
		tblclmnNewColumn_1.setText("\u7535\u5B50\u90AE\u4EF6\u5730\u5740");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(112);
		tableColumn_1.setText("\u7F16\u8F91");
		
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label_1 = new Label(composite_3, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setText("\u57FA\u7840\u8BBE\u7F6E");
		
		Composite composite_4 = new Composite(sashForm, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel_1 = new Label(composite_4, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(40, 20, 96, 20);
		lblNewLabel_1.setText("\u53D1\u9001\u670D\u52A1\u5668SMTP \uFF1A");
		
		Label lblNewLabel_2 = new Label(composite_4, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(40, 45, 96, 20);
		lblNewLabel_2.setText("\u53D1\u9001\u65B9Email\u5730\u5740\uFF1A");
		
		Label lblNewLabel_3 = new Label(composite_4, SWT.NONE);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_3.setBounds(40, 70, 96, 20);
		lblNewLabel_3.setText("\u5907\u4EFD\u53D1\u9001\u670D\u52A1\u5668 \uFF1A");
		
		Label lblNewLabel_4 = new Label(composite_4, SWT.NONE);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setBounds(40, 95, 96, 20);
		lblNewLabel_4.setText("\u8EAB\u4EFD\u9A8C\u8BC1\u7528\u6237\u540D \uFF1A");
		
		Label lblNewLabel_5 = new Label(composite_4, SWT.NONE);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_5.setBounds(40, 120, 96, 20);
		lblNewLabel_5.setText("\u8EAB\u4EFD\u9A8C\u8BC1\u5BC6\u7801   \uFF1A");
		
		text = new Text(composite_4, SWT.BORDER);
		text.setBounds(142, 20, 176, 20);
		text.setText(sendMailbo.GetField("SendServer").get_NativeValue().toString());
		
		text_1 = new Text(composite_4, SWT.BORDER);
		text_1.setBounds(142, 45, 176, 20);
		text_1.setText(sendMailbo.GetField("MailAddress").get_NativeValue().toString());
		
		text_2 = new Text(composite_4, SWT.BORDER);
		text_2.setBounds(142, 70, 176, 20);
		text_2.setText(sendMailbo.GetField("BackupSendServer").get_NativeValue().toString());
		
		text_3 = new Text(composite_4, SWT.BORDER);
		text_3.setBounds(142, 95, 176, 20);
		text_3.setText(sendMailbo.GetField("MailUserName").get_NativeValue().toString());
		
		text_4 = new Text(composite_4, SWT.BORDER|SWT.PASSWORD);
		text_4.setBounds(142, 120, 176, 20);
		text_4.setText(sendMailbo.GetField("MailPwd").get_NativeValue().toString());
		Button btnNewButton = new Button(composite_4, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				 String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
				 Pattern regex = Pattern.compile(check); 
				 if(text.getText()==null ||text.getText().equals("")){
					 MessageDialog.openInformation(new Shell(), "提示", "请填写发送服务器");
					 return;
				 }else if(text_1.getText()==null ||text_1.getText().equals("")){
					 MessageDialog.openInformation(new Shell(), "提示", "请填写Email地址");
					 return;
				 }else if(!regex.matcher(text_1.getText()).matches()){
					 MessageDialog.openInformation(new Shell(), "提示", "Email格式不对");
					 return;
				 }else if(text_2.getText()==null ||text_2.getText().equals("")){
					 MessageDialog.openInformation(new Shell(), "提示", "请填写备份发送服务器");
					 return;
				 }else if(text_3.getText()==null ||text_3.getText().equals("")){
					 MessageDialog.openInformation(new Shell(), "提示", "请填写用户名");
					 return;
				 }else if(text_4.getText()==null ||text_4.getText().equals("")){
					 MessageDialog.openInformation(new Shell(), "提示", "请填密码");
					 return;
				 }
				sendMailbo.GetField("MailType").SetValue(new SiteviewValue("send"));
				sendMailbo.GetField("SendServer").SetValue(new SiteviewValue(text.getText()));
				sendMailbo.GetField("MailAddress").SetValue(new SiteviewValue(text_1.getText()));
				sendMailbo.GetField("BackupSendServer").SetValue(new SiteviewValue(text_2.getText()));
				sendMailbo.GetField("MailUserName").SetValue(new SiteviewValue(text_3.getText()));
				sendMailbo.GetField("MailPwd").SetValue(new SiteviewValue(text_4.getText()));
				sendMailbo.SaveObject(ConnectionBroker.get_SiteviewApi(), false, true);
			}
		});
		btnNewButton.setBounds(65, 150, 72, 22);
		btnNewButton.setText("\u5E94\u7528");
		
		Button btnNewButton_1 = new Button(composite_4, SWT.NONE);
		btnNewButton_1.setBounds(155, 150, 72, 22);
		btnNewButton_1.setText("\u91CD\u65B0\u83B7\u5F97");
		
		Button btnNewButton_2 = new Button(composite_4, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ConnectMail con=new ConnectMail(null,sendMailbo);
				con.open();
			}
		});
		btnNewButton_2.setBounds(245, 150, 72, 22);
		btnNewButton_2.setText("\u6D4B\u8BD5");
		
		tableViewer.setContentProvider(new TableDutyContentProvider());
		tableViewer.setLabelProvider(new TableDutyLabelProvider());
		tableViewer.setInput(list);
		tableViewer.setComparer(new TableComparer());
		sashForm.setWeights(new int[] {1, 2, 1, 20, 1, 15});
		
	}

	public void setFocus() {

	}
}
