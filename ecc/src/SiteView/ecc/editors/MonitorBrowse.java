package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Text;

public class MonitorBrowse extends EditorPart {
	public static String ID = "SiteView.ecc.editors.MonitorBrowse";
	private Table table;
	private Table table_1;
	private Text text;
	public MonitorBrowse() {
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
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(0, 0, 82, 32);
		btnNewButton.setText("创建筛选条件");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(90, 0, 72, 32);
		btnNewButton_1.setText("编辑");
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setBounds(171, 0, 72, 32);
		btnNewButton_2.setText("刷新");
		
		Button btnNewButton_3 = new Button(composite, SWT.NONE);
		btnNewButton_3.setBounds(252, 0, 72, 32);
		btnNewButton_3.setText("删除");
		
		Button btnNewButton_4 = new Button(composite, SWT.NONE);
		btnNewButton_4.setBounds(334, 0, 72, 32);
		btnNewButton_4.setText("生成Excel");
		
		Button btnNewButton_5 = new Button(composite, SWT.NONE);
		btnNewButton_5.setBounds(415, 0, 82, 32);
		btnNewButton_5.setText("设置显示条目");
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("名称");
		
		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_7 = tableViewerColumn_7.getColumn();
		tblclmnNewColumn_7.setWidth(100);
		tblclmnNewColumn_7.setText("组");
		
		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_6 = tableViewerColumn_6.getColumn();
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("设备");
		
		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_5 = tableViewerColumn_5.getColumn();
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("名称");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_4 = tableViewerColumn_4.getColumn();
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("监测器类型");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_3 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("显示/隐藏");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_2 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("描述");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("排序");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 594, 27);
		lblNewLabel.setText("浏览次数最多的监测器");
		
		TableViewer tableViewer_1 = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableViewerColumn tableViewerColumn_8 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_8 = tableViewerColumn_8.getColumn();
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("状态");
		
		TableViewerColumn tableViewerColumn_15 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_15 = tableViewerColumn_15.getColumn();
		tblclmnNewColumn_15.setWidth(100);
		tblclmnNewColumn_15.setText("组");
		
		TableViewerColumn tableViewerColumn_14 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_14 = tableViewerColumn_14.getColumn();
		tblclmnNewColumn_14.setWidth(100);
		tblclmnNewColumn_14.setText("设备");
		
		TableViewerColumn tableViewerColumn_13 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_13 = tableViewerColumn_13.getColumn();
		tblclmnNewColumn_13.setWidth(100);
		tblclmnNewColumn_13.setText("名称");
		
		TableViewerColumn tableViewerColumn_12 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_12 = tableViewerColumn_12.getColumn();
		tblclmnNewColumn_12.setWidth(100);
		tblclmnNewColumn_12.setText("编辑");
		
		TableViewerColumn tableViewerColumn_11 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_11 = tableViewerColumn_11.getColumn();
		tblclmnNewColumn_11.setWidth(100);
		tblclmnNewColumn_11.setText("刷新");
		
		TableViewerColumn tableViewerColumn_10 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_10 = tableViewerColumn_10.getColumn();
		tblclmnNewColumn_10.setWidth(100);
		tblclmnNewColumn_10.setText("更新时间");
		
		TableViewerColumn tableViewerColumn_9 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_9 = tableViewerColumn_9.getColumn();
		tblclmnNewColumn_9.setWidth(100);
		tblclmnNewColumn_9.setText("描述");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Button button = new Button(composite_2, SWT.NONE);
		button.setBounds(0, 10, 43, 22);
		button.setText("\u9996\u9875");
		
		Button button_1 = new Button(composite_2, SWT.NONE);
		button_1.setBounds(49, 10, 57, 22);
		button_1.setText("\u4E0A\u4E00\u9875");
		
		text = new Text(composite_2, SWT.BORDER);
		text.setBounds(112, 10, 37, 22);
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.SHADOW_NONE);
		lblNewLabel_1.setBounds(150, 15, 54, 18);
		lblNewLabel_1.setText("New Label");
		
		Button button_2 = new Button(composite_2, SWT.NONE);
		button_2.setBounds(210, 10, 57, 22);
		button_2.setText("\u4E0B\u4E00\u9875");
		
		Button button_3 = new Button(composite_2, SWT.NONE);
		button_3.setBounds(273, 10, 54, 22);
		button_3.setText("\u5C3E\u9875");
		sashForm.setWeights(new int[] {33, 67, 16, 309, 37});
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
