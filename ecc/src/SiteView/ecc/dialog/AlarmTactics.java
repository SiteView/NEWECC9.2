package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import SiteView.ecc.view.EccTreeControl;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AlarmTactics extends Dialog{
	private Table table;

	public AlarmTactics(Shell parentShell) {
		super(parentShell);
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(900, 900);
		newShell.setLocation(200, 105);
		newShell.setText("±¨¾¯²ßÂÔ");
		super.configureShell(newShell);
	}
	
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		
		Label lblNewLabel = new Label(sashForm, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setText("\u544A\u8B66\u7B56\u7565");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		Button button = new Button(composite_1, SWT.NONE);//Ìí¼Ó
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddAlarmTactics addAlarm=new AddAlarmTactics(new Shell());
				addAlarm.open();
			}
		});
		button.setBounds(0, 0, 72, 22);
		button.setText("\u6DFB\u52A0");
		
		
		Button button_1 = new Button(composite_1, SWT.NONE);//±à¼­
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_1.setBounds(81, 0, 72, 22);
		button_1.setText("\u7F16\u8F91");
		
		Button button_2 = new Button(composite_1, SWT.NONE);//É¾³ý
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button_2.setBounds(159, 0, 72, 22);
		button_2.setText("\u5220\u9664");
		
		Button button_3 = new Button(composite_1, SWT.NONE);//Ë¢ÐÂ
		button_3.setBounds(241, 0, 72, 22);
		button_3.setText("\u5237\u65B0");
		
		Label lblNewLabel_1 = new Label(sashForm, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel_1.setText("\u544A\u8B66\u7B56\u7565\u5217\u8868");
		
		table = new Table(sashForm, SWT.FULL_SELECTION|SWT.CENTER);
//		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(437);
		tableColumn.setText("\u540D\u79F0");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(EccTreeControl.color);
		sashForm.setWeights(new int[] {5, 8, 5, 78, 94});
		return composite;
	}
}
