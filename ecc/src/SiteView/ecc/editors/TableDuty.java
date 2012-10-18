package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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

import SiteView.ecc.Control.DutyDetailContentProvider;
import SiteView.ecc.Control.DutyDetailLabelProvider;
import SiteView.ecc.Control.TableDutyContentProvider;
import SiteView.ecc.Control.TableDutyLabelProvider;
import SiteView.ecc.Modle.DetailModel;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.data.DutyDetailInfor;
import SiteView.ecc.data.TableDutyInfor;
import SiteView.ecc.dialog.AddDutyDetail;
import SiteView.ecc.dialog.AddTableDuty;
import SiteView.ecc.dialog.DutyEditor;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import Siteview.Windows.Forms.MessageBox;



public class TableDuty extends EditorPart{
	public static String ID = "SiteView.ecc.editors.TableDuty";
	public static TableViewer TableViewer;//��һ����
	public Table table;
	public  TableItem tableItem;//��һ�����ĵ�һ��
	public static TableViewer TableViewer1;//�ڶ�����
	public TableItem tableItem1;//�ڶ������ĵ�һ��
	public Table table_1;
	public Button btnNewButton_1;
	public Button btnNewButton_2;
	public String type;
	public BusinessObject bo1; 
	public TableDuty() {
		super();
	}
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);// ����site
		this.setInput(input);// ���������IEditorInput����
		this.setPartName(input.getName());// ���ñ༭���Ϸ���ʾ������
		
	}
	@Override
	public boolean isDirty() {
		return false;
	}
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setFont(SWTResourceManager.getFont("����", 12, SWT.NORMAL));
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite.setLayout(new FormLayout());
		
		Button button = new Button(composite, SWT.NONE);//���Ƶ�һ��������Ӱ�ť
		button.setAlignment(SWT.LEFT);
		FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(0);
		fd_button.left = new FormAttachment(0);
		button.setLayoutData(fd_button);
		button.setText("\u6DFB\u52A0");
		button.addSelectionListener(new SelectionAdapter(){//��Ӱ�ť�¼�
			public void widgetSelected(SelectionEvent e){
				AddTableDuty addTableDuty=new AddTableDuty(null);
				addTableDuty.open();
			}
		});
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setAlignment(SWT.LEFT);
		FormData fd_button_1 = new FormData();
		fd_button_1.top = new FormAttachment(button, 0, SWT.TOP);
		fd_button_1.left = new FormAttachment(button, 6);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("\u5220\u9664");
		button_1.addSelectionListener(new SelectionAdapter(){//���Ƶ�һ������ɾ����ť
			public void widgetSelected(SelectionEvent e){//ɾ����ť�����¼�
				if(tableItem==null){
					MessageBox messageBox=new MessageBox();
					messageBox.Show("�㻹û��ѡ����ɾ������!", "��ʾ", SWT.OK);
				}else{
					if(!(tableItem.isDisposed())){
						TableModle tm=(TableModle) tableItem.getData();
						BusinessObject bo=tm.getBo();
						ICollection ico=FileTools.getBussCollection("DutyId", bo.get_RecId(), "DutyDetail");
						IEnumerator ien=ico.GetEnumerator();
						while(ien.MoveNext()){
							((BusinessObject)ien.get_Current()).DeleteObject(ConnectionBroker.get_SiteviewApi());
						}
						bo.DeleteObject(ConnectionBroker.get_SiteviewApi());//�����ݿ������ɾ��
						TableDutyInfor.list.remove(tm);//�����������ɾ��
						TableViewer.setInput(TableDutyInfor.list);
						TableViewer.refresh();
						DutyDetailInfor.list.clear();//�����������ɾ��
						TableViewer1.setInput(DutyDetailInfor.list);
						TableViewer1.refresh();
						btnNewButton_1.setEnabled(false);
					}
				}
			}
		});
		
		Button button_2 = new Button(composite, SWT.NONE);//��������ťˢ��
		button_2.setAlignment(SWT.LEFT);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableViewer.refresh();
			}
		});
		FormData fd_button_2 = new FormData();
		fd_button_2.top = new FormAttachment(button, 0, SWT.TOP);
		fd_button_2.left = new FormAttachment(button_1, 6);
		button_2.setLayoutData(fd_button_2);
		button_2.setText("\u5237\u65B0");
		
		Button btnNewButton = new Button(composite, SWT.NONE);//���ĸ�������ť
		btnNewButton.setAlignment(SWT.LEFT);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(button, 0, SWT.TOP);
		fd_btnNewButton.left = new FormAttachment(button_2, 6);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("\u5E2E\u52A9");
		
		Label lblNewLabel_1 = new Label(sashForm, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setText("\u503C\u73ED\u8868\u8BBE\u7F6E\u8BE6\u7EC6\u4FE1\u606F");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout());
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		
        Composite composite_3 = new Composite(sashForm, SWT.NONE);
        composite_3.setBackground(EccTreeControl.color);
		
	    btnNewButton_1 = new Button(composite_3, SWT.NONE);//���Ƶڶ���������Ӱ�ť
		btnNewButton_1.setBounds(27, 10, 47, 22);
		btnNewButton_1.setText("\u6DFB\u52A0");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addSelectionListener(new SelectionAdapter(){//��Ӱ�ť�¼�
			public void widgetSelected(SelectionEvent e){
				String s="";
				if(type.equals("day of month")){
					s="��";
				}else if(type.equals("day of week")){
					s="����";
				}
				AddDutyDetail addDutyDetail=new AddDutyDetail(null,s,null,null,((TableModle) tableItem.getData()).getBo().get_RecId());
				addDutyDetail.open();
			}
		});
		
		btnNewButton_2 = new Button(composite_3, SWT.NONE);//���Ƶڶ�������ɾ����ť
		btnNewButton_2.setBounds(86, 10, 47, 22);
		btnNewButton_2.setText("\u5220\u9664");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addSelectionListener(new SelectionAdapter(){//ɾ����ť�����¼�
			public void widgetSelected(SelectionEvent e){
				DetailModel dm=(DetailModel) tableItem1.getData();
				BusinessObject bo=dm.getBo();
				bo.DeleteObject(ConnectionBroker.get_SiteviewApi());//�����ݿ������ɾ��
				DutyDetailInfor.list.remove(dm);//�����������ɾ��
				TableViewer1.setInput(DutyDetailInfor.list);
				TableViewer1.refresh();
				if(table_1.getItemCount()>0){
					tableItem1=table_1.getItem(0);
				}else{
					btnNewButton_2.setEnabled(false);
				}
			}
		});
		
	    TableViewer =  new TableViewer(composite_1, SWT.MULTI
				 |SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.CHECK);//ֵ���������ϸ��Ϣ��
	    table = TableViewer.getTable();
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				tableItem=(TableItem) e.item;
				tableItem.setChecked(true);//��ѡ��ѡ��
				btnNewButton_1.setEnabled(true);//��Ӱ�ť����ʹ��
				btnNewButton_2.setEnabled(false);
				TableModle tm=(TableModle) tableItem.getData();
				bo1=tm.getBo();
				type=bo1.GetField("DutyTableType").get_NativeValue().toString();//�õ�ѡ�еĶ��������
				TableViewer1.setContentProvider(new DutyDetailContentProvider());
				TableViewer1.setLabelProvider(new DutyDetailLabelProvider());
				TableViewer1.setInput(DutyDetailInfor.getDutyDetaildayInfor(bo1.get_RecId()));
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				tableItem.setChecked(false);
			}
		});
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		cursor.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
			@Override
			public void mouseDown(MouseEvent e) {
			}
			@Override
			public void mouseUp(MouseEvent e) {
				int column = cursor.getColumn();
				for(TableItem ta:table.getItems()){
					if(!ta.equals(tableItem)){
						ta.setChecked(false);
					}
				}
				if(column==3){
					TableModle tm=(TableModle) tableItem.getData();
					BusinessObject bb=tm.getBo();
					DutyEditor editor=new DutyEditor(null,bb,tm);
					editor.open();
				}
			}
		});
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u503C\u73ED\u8868");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("\u7C7B\u578B");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_2.setWidth(96);
		tblclmnNewColumn_2.setText("\u63CF\u8FF0");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("\u7F16\u8F91");
		
		Label lblNewLabel_2 = new Label(sashForm, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_2.setText("\u503C\u73ED\u4FE1\u606F:");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout());
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		TableViewer1 =  new TableViewer(composite_2, SWT.MULTI//ֵ����Ϣ��
				 |SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table_1 = TableViewer1.getTable();
		table_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		table_1.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				tableItem1=(TableItem) e.item;
				btnNewButton_2.setEnabled(true);//ɾ����ť����ʹ��
			}
			public void widgetDefaultSelected(SelectionEvent e) {
	
			}
		});
		final TableCursor cursor1 = new TableCursor(table_1, SWT.NONE);
		cursor1.addMouseListener(new MouseListener(){
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
			}
			@Override
			public void mouseDown(MouseEvent e) {
				
			}
			@Override
			public void mouseUp(MouseEvent e) {
				int column1 = cursor1.getColumn();
				if(column1==5){
					DetailModel dm=(DetailModel)tableItem1.getData();
					BusinessObject bv=dm.getBo();
					AddDutyDetail editDutyDetail=new AddDutyDetail(null, bv.GetField("Week").get_NativeValue().toString(), bv,dm,null);
					editDutyDetail.open();
				}
			}
			
		});
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("\u63A5\u6536\u544A\u8B66\u624B\u673A\u53F7\u7801");
		
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("\u63A5\u6536\u544A\u8B66\u90AE\u7BB1");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("\u65E5\u671F");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_7.setWidth(100);
		tblclmnNewColumn_7.setText("\u5F00\u59CB\u65F6\u95F4");
		
		TableColumn tblclmnNewColumn_8 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("\u7ED3\u675F\u65F6\u95F4");
		
		TableColumn tblclmnNewColumn_9 = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn_9.setText("\u7F16\u8F91");
		tblclmnNewColumn_9.setWidth(100);
		sashForm.setWeights(new int[] {28, 14, 178, 47, 11, 181});
		
		TableViewer.setContentProvider(new TableDutyContentProvider());
		TableViewer.setLabelProvider(new TableDutyLabelProvider());
		TableViewer.setInput(TableDutyInfor.getTableDutyInfor());
		

	
		
		
	}
	
	@Override
	public void setFocus() {
		
		
	}
	@Override
	public void doSave(IProgressMonitor monitor) {
		
	}

	@Override
	public void doSaveAs() {
		
	}
}
