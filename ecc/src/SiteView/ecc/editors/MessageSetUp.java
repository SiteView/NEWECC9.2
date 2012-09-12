package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;

public class MessageSetUp extends EditorPart {
	public static final String ID = "SiteView.ecc.editors.MessageSetUp";
	private Table table;

	public MessageSetUp() {
		// TODO Auto-generated constructor stub
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
		this.setSite(site);// ����site
		this.setInput(input);// ���������IEditorInput����
		this.setPartName(input.getName());// ���ñ༭���Ϸ���ʾ������

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
		composite_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Button addButton = new Button(composite_1, SWT.NONE);
		addButton.setText("���");
		
		Button delButton = new Button(composite_1, SWT.NONE);
		delButton.setText("ɾ��");
		
		Button allowButton = new Button(composite_1, SWT.NONE);
		allowButton.setText("����");
		
		Button forbidButton = new Button(composite_1, SWT.NONE);
		forbidButton.setText("��ֹ");
		
		Button refreshButton = new Button(composite_1, SWT.NONE);
		refreshButton.setText("ˢ��");
		
		Button mouldButton = new Button(composite_1, SWT.NONE);
		mouldButton.setText("ģ������");
		
		Button helpButton = new Button(composite_1, SWT.NONE);
		helpButton.setText("����");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u77ED\u4FE1\u8BBE\u7F6E\u8BE6\u7EC6\u4FE1\u606F");
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table = tableViewer.getTable();
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
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
		tabItem.setText("��WEB��ʽ���Ͷ���");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabItem.setControl(composite_3);
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("�Դ��ڷ�ʽ���Ͷ���");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		composite_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabItem_1.setControl(composite_4);
		
		TabItem tabItem_2 = new TabItem(tabFolder, SWT.NONE);
		tabItem_2.setText("���ö�̬���еĺ������Ͷ���");
		
		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabItem_2.setControl(composite_5);
		
		TabItem tabItem_3 = new TabItem(tabFolder, SWT.NONE);
		tabItem_3.setText("���ݿ��ȡ");
		
		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		composite_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tabItem_3.setControl(composite_6);
		sashForm.setWeights(new int[] {1, 2, 1, 15, 15});
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
