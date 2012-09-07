package SiteView.ecc.editors;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

import SiteView.ecc.Control.DutyDetailContentProvider;
import SiteView.ecc.Control.DutyDetailLabelProvider;
import SiteView.ecc.Control.TableDutyContentProvider;
import SiteView.ecc.Control.TableDutyLabelProvider;
import SiteView.ecc.data.DutyDetailInfor;
import SiteView.ecc.data.TableDutyInfor;
import SiteView.ecc.dialog.AddDutyDetail;
import SiteView.ecc.dialog.AddTableDuty;



public class TableDuty extends EditorPart{
	public static String ID = "SiteView.ecc.editors.TableDuty";
	public static TableViewer TableViewer;
	public static TableViewer TableViewer1;
	public Table table;
	public static TableItem tableItem;
	private Table table_1;
	public TableDuty(){
		
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
		sashForm.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite.setLayout(new FormLayout());
		
		Button button = new Button(composite, SWT.NONE);//第一个添加按钮
		button.setAlignment(SWT.LEFT);
		FormData fd_button = new FormData();
		fd_button.top = new FormAttachment(0);
		button.setLayoutData(fd_button);
		button.setText("\u6DFB\u52A0");
		button.addSelectionListener(new SelectionAdapter(){//添加按钮事件
			public void widgetSelected(SelectionEvent e){
				AddTableDuty addTableDuty=new AddTableDuty(null);
				addTableDuty.open();
			}
		});
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setAlignment(SWT.LEFT);
		fd_button.right = new FormAttachment(button_1, -6);
		FormData fd_button_1 = new FormData();
		fd_button_1.top = new FormAttachment(button, 0, SWT.TOP);
		button_1.setLayoutData(fd_button_1);
		button_1.setText("\u5220\u9664");
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setAlignment(SWT.LEFT);

		fd_button_1.right = new FormAttachment(button_2, -6);
		FormData fd_button_2 = new FormData();
		fd_button_2.top = new FormAttachment(button, 0, SWT.TOP);
		button_2.setLayoutData(fd_button_2);
		button_2.setText("\u5237\u65B0");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setAlignment(SWT.LEFT);
		fd_button_2.right = new FormAttachment(100, -464);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(button, 0, SWT.TOP);
		fd_btnNewButton.left = new FormAttachment(button_2, 7);
		fd_btnNewButton.right = new FormAttachment(100, -417);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("\u5E2E\u52A9");
		
		Label lblNewLabel_1 = new Label(sashForm, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setText("\u503C\u73ED\u8868\u8BBE\u7F6E\u8BE6\u7EC6\u4FE1\u606F");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout());
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
	    TableViewer =  new TableViewer(composite_1, SWT.MULTI
				 |SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.CHECK);//值班表设置详细信息表
	    table = TableViewer.getTable();
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				tableItem=(TableItem) e.item;
			}
			public void widgetDefaultSelected(SelectionEvent e) {
	
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
		
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		
		Button btnNewButton_1 = new Button(composite_3, SWT.NONE);//第二个添加按钮
		btnNewButton_1.setBounds(27, 10, 47, 22);
		btnNewButton_1.setText("\u6DFB\u52A0");
		btnNewButton_1.addSelectionListener(new SelectionAdapter(){//添加按钮事件
			public void widgetSelected(SelectionEvent e){
				AddDutyDetail addDutyDetail=new AddDutyDetail(null);
				addDutyDetail.open();
			}
		});
		
		Button btnNewButton_2 = new Button(composite_3, SWT.NONE);
		btnNewButton_2.setBounds(86, 10, 47, 22);
		btnNewButton_2.setText("\u5220\u9664");
		
		Label lblNewLabel_2 = new Label(sashForm, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_2.setText("\u503C\u73ED\u4FE1\u606F:");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout());
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		TableViewer1 =  new TableViewer(composite_2, SWT.MULTI
				 |SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.CHECK);
		table_1 = TableViewer1.getTable();
		table_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("\u63A5\u6536\u544A\u8B66\u624B\u673A\u53F7\u7801");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("\u63A5\u6536\u544A\u8B66\u90AE\u7BB1");
		
		TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("\u65E5\u671F");
		
		TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_7.setWidth(100);
		tblclmnNewColumn_7.setText("\u5F00\u59CB\u65F6\u95F4");
		
		TableColumn tblclmnNewColumn_8 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("\u7ED3\u675F\u65F6\u95F4");
		
		TableColumn tblclmnNewColumn_9 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_9.setText("\u7F16\u8F91");
		tblclmnNewColumn_9.setWidth(100);
		sashForm.setWeights(new int[] {25, 17, 180, 39, 19, 179});
		
		TableViewer.setContentProvider(new TableDutyContentProvider());
		TableViewer.setLabelProvider(new TableDutyLabelProvider());
		TableViewer.setInput(TableDutyInfor.getTableDutyInfor());
		tableItem=table.getItem(0);
		
//		TableViewer1.setContentProvider(new DutyDetailContentProvider());
//		TableViewer1.setLabelProvider(new DutyDetailLabelProvider());
//		TableViewer.setInput(DutyDetailInfor.getDutyDetailInfor());
//		tableItem=table.getItem(0);
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
