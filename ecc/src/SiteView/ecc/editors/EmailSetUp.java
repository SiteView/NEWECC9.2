package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class EmailSetUp extends EditorPart {
	public static final String ID = "SiteView.ecc.editors.EmailSetUp";
	private Table table;
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
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setText("\u90AE\u4EF6\u8BBE\u7F6E");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button addButton = new Button(composite_1, SWT.NONE);
		addButton.setSize(55, 25);
		addButton.setText("添加");
		
		Button delButton = new Button(composite_1, SWT.NONE);
		delButton.setLocation(65, 0);
		delButton.setSize(55, 25);
		delButton.setText("删除");
		
		Button allowButton = new Button(composite_1, SWT.NONE);
		allowButton.setLocation(130, 0);
		allowButton.setSize(55, 25);
		allowButton.setText("允许");
		
		Button forbidButton = new Button(composite_1, SWT.NONE);
		forbidButton.setLocation(195, 0);
		forbidButton.setSize(55, 25);
		forbidButton.setText("禁止");
		
		Button refreshButton = new Button(composite_1, SWT.NONE);
		refreshButton.setLocation(260, 0);
		refreshButton.setSize(55, 25);
		refreshButton.setText("刷新");
		
		Button mouldButton = new Button(composite_1, SWT.NONE);
		mouldButton.setLocation(325, 0);
		mouldButton.setSize(55, 25);
		mouldButton.setText("模板设置");
		
		Button helpButton = new Button(composite_1, SWT.NONE);
		helpButton.setLocation(390, 0);
		helpButton.setSize(55, 25);
		helpButton.setText("帮助");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u90AE\u4EF6\u8BBE\u7F6E\u8BE6\u7EC6\u4FE1\u606F");
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION |SWT.CHECK |SWT.V_SCROLL |SWT.H_SCROLL);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(260);
		tblclmnNewColumn.setText("\u540D\u79F0");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(260);
		tableColumn.setText("\u72B6\u6001");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(260);
		tblclmnNewColumn_1.setText("\u7535\u5B50\u90AE\u4EF6\u5730\u5740");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(260);
		tableColumn_1.setText("\u7F16\u8F91");
		
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
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
		
		text_1 = new Text(composite_4, SWT.BORDER);
		text_1.setBounds(142, 45, 176, 20);
		
		text_2 = new Text(composite_4, SWT.BORDER);
		text_2.setBounds(142, 70, 176, 20);
		
		text_3 = new Text(composite_4, SWT.BORDER);
		text_3.setBounds(142, 95, 176, 20);
		
		text_4 = new Text(composite_4, SWT.BORDER);
		text_4.setBounds(142, 120, 176, 20);
		
		Button btnNewButton = new Button(composite_4, SWT.NONE);
		btnNewButton.setBounds(65, 150, 72, 22);
		btnNewButton.setText("\u5E94\u7528");
		
		Button btnNewButton_1 = new Button(composite_4, SWT.NONE);
		btnNewButton_1.setBounds(155, 150, 72, 22);
		btnNewButton_1.setText("\u91CD\u65B0\u83B7\u5F97");
		
		Button btnNewButton_2 = new Button(composite_4, SWT.NONE);
		btnNewButton_2.setBounds(245, 150, 72, 22);
		btnNewButton_2.setText("\u6D4B\u8BD5");
		sashForm.setWeights(new int[] {1, 2, 1, 20, 1, 15});

	}

	public void setFocus() {

	}
}
