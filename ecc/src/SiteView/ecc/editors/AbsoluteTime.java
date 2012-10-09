package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import SiteView.ecc.Control.AbsoluteTimeContentProvider;
import SiteView.ecc.Control.AbsoluteTimelabelProvider;
import SiteView.ecc.data.AbsoluteTimeInfor;
import SiteView.ecc.dialog.AddAbsoluteTime;
import SiteView.ecc.dialog.AddRelativeTime;
import SiteView.ecc.dialog.AddTimeQuantum;
import Siteview.Api.DefinitionLibrary;
import Siteview.Windows.Forms.ConnectionBroker;

public class AbsoluteTime extends EditorPart {
	public static final String absoluteID="SiteView.ecc.editors.AbsoluteTime";
	public static TableViewer tableViewer;
	private Table table;
	private TableItem tableItem;
	public static String name;
	Label lblNewLabel;

	public AbsoluteTime() {
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
		name = AbsoluteTimeInput.type;
//		System.out.println(name);
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
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		createLabel();
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(null);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setBounds(10, 0, 36, 22);
		btnNewButton.setText("\u6DFB\u52A0");
		btnNewButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if("绝对时间任务计划".equals(name)){
					AddAbsoluteTime aat=new AddAbsoluteTime(null);
					aat.open();
				}else if("时间段任务计划".equals(name)){
					AddTimeQuantum atq = new AddTimeQuantum(null);
					atq.open();
				}else if("相对时间任务计划".equals(name)){
					AddRelativeTime art = new AddRelativeTime(null);
					art.open();
				}
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setBounds(52, 0, 36, 22);
		button.setText("\u5220\u9664");
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setBounds(94, 0, 36, 22);
		button_1.setText("\u5237\u65B0");
		
		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setBounds(136, 0, 36, 22);
		button_2.setText("\u5E2E\u52A9");
		
		tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		table = tableViewer.getTable();
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				 tableItem=(TableItem)e.item;
				 tableItem.setChecked(true);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				tableItem.setChecked(false);
			}
		});
		
		final TableCursor cursor=new TableCursor(table, SWT.NONE);
		cursor.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
			for(TableItem ta:table.getItems()){
				if(!ta.equals(tableItem)){
					ta.setChecked(false);
				}
			}
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
			
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			
			}
		});
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.LEFT);
		tblclmnNewColumn_1.setWidth(150);
		tblclmnNewColumn_1.setText("\u540D\u79F0");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.LEFT);
		tblclmnNewColumn.setWidth(150);
		tblclmnNewColumn.setText("\u63CF\u8FF0");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.LEFT);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("\u7F16\u8F91");
		sashForm.setWeights(new int[] {1, 2, 34});

//		tableViewer.setContentProvider(new AbsoluteTimeContentProvider());
//		tableViewer.setLabelProvider(new AbsoluteTimelabelProvider());
//		tableViewer.setInput(AbsoluteTimeInfor.getTableDutyInfor());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	
	public void createLabel(){
		if("绝对时间任务计划".equals(name)){
			lblNewLabel.setText("绝对时间任务计划");
		}else if("时间段任务计划".equals(name)){
			lblNewLabel.setText("时间段任务计划");
		}else if("相对时间任务计划".equals(name)){
			lblNewLabel.setText("相对时间任务计划");
		}
	}
}
