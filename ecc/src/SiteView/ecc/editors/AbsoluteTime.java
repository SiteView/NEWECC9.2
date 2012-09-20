package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.custom.SashForm;
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

import SiteView.ecc.dialog.AddAbsoluteTime;
import Siteview.Api.DefinitionLibrary;
import Siteview.Windows.Forms.ConnectionBroker;

public class AbsoluteTime extends EditorPart {
	public static final String ID="SiteView.ecc.editors.AbsoluteTime";
	private Table table;

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
		
		Label label = new Label(composite, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u7EDD\u5BF9\u65F6\u95F4\u4EFB\u52A1\u8BA1\u5212");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(null);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setBounds(10, 0, 36, 22);
		btnNewButton.setText("\u6DFB\u52A0");
		btnNewButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				AddAbsoluteTime aat=new AddAbsoluteTime(null);
				aat.open();
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
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		table = tableViewer.getTable();
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
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

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
