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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import SiteView.ecc.dialog.AddEmailAlarmRule;

public class AlarmRule extends EditorPart {
	private Table table;
	private Table table_1;
	public static final String ID="SiteView.ecc.editors.AlarmRule";
	public AlarmRule() {
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
		// TODO Auto-generated method stub
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
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddEmailAlarmRule add=new AddEmailAlarmRule(null);
				add.open();
			}
		});
		button.setBounds(0, 10, 59, 22);
		button.setText("\u6DFB\u52A0");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setBounds(75, 10, 59, 22);
		button_1.setText("\u5220\u9664");
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setBounds(152, 10, 72, 22);
		button_2.setText("\u5141\u8BB8");
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.setBounds(242, 10, 72, 22);
		button_3.setText("\u7981\u6B62");
		
		Button button_4 = new Button(composite, SWT.NONE);
		button_4.setBounds(330, 10, 72, 22);
		button_4.setText("\u5237\u65B0");
		
		Label lblNewLabel = new Label(sashForm, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setText("\u62A5\u8B66\u89C4\u5219\u5217\u8868");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u540D\u79F0");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u62A5\u8B66\u7C7B\u578B");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u62A5\u8B66\u72B6\u6001");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u542F\u52A8/\u7981\u6B62");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u7F16\u8F91");
		
		Label lblNewLabel_1 = new Label(sashForm, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setText("\u62A5\u8B66\u65E5\u5FD7\u5217\u8868");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table_1 = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u62A5\u8B66\u65F6\u95F4");
		
		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u62A5\u8B66\u540D\u79F0");
		
		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("\u62A5\u8B66\u7EC4");
		
		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("\u62A5\u8B66\u76D1\u6D4B\u5668");
		
		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("\u62A5\u8B66\u7C7B\u578B");
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("\u63A5\u6536\u4EBA");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("\u62A5\u8B66\u72B6\u6001");
		sashForm.setWeights(new int[] {25, 13, 221, 12, 191});
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
