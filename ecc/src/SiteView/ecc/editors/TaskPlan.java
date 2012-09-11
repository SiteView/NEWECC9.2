package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import SiteView.ecc.Control.TaskPlanContentProvider;
import SiteView.ecc.Control.TaskPlanLabelProvider;
import SiteView.ecc.data.TaskInfo;

public class TaskPlan extends EditorPart {
	public static final String ID="SiteView.ecc.editors.TaskPlan";
	private Table table;
	private TableItem tableItem;
	

	public TaskPlan() {
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
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label = new Label(composite, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u4EFB\u52A1\u8BA1\u5212");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TableViewer tableViewer = new TableViewer(composite_1, SWT.MULTI | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				tableItem = (TableItem) e.item;
				if(tableItem.getText(0).equals("绝对时间任务计划")){
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new AbsoluteTimeInput(), AbsoluteTime.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				}else if(tableItem.getText(0).equals("时间段任务计划")){
					try {
						   PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new TimeQuantumInput(), TimeQuantum.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				}else if(tableItem.getText(0).equals("相对时间任务计划")){
					try {
						   PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new RelativeTimeInput(), RelativeTime.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setText("\u540D\u79F0");
		tblclmnNewColumn.setWidth(150);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setText("\u8BF4\u660E");
		tableColumn.setWidth(900);
		sashForm.setWeights(new int[] {1, 40});

		tableViewer.setContentProvider(new TaskPlanContentProvider());
		tableViewer.setLabelProvider(new TaskPlanLabelProvider());
		tableViewer.setInput(TaskInfo.getTaskInfo());
	}

	@Override
	public void setFocus() {

	}

}
