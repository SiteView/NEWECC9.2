package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;

import SiteView.ecc.view.EccTreeControl;

public class AlarmTactics extends EditorPart{
	public AlarmTactics() {
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
		this.setInput(input);
		this.setSite(site);
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
		parent.setBackground(EccTreeControl.color);
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setBackground(EccTreeControl.color);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(EccTreeControl.color);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label label = new Label(composite, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setText("\u67E5\u8BE2\u6761\u4EF6");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBackground(EccTreeControl.color);
		lblNewLabel_1.setBounds(0, 10, 66, 12);
		lblNewLabel_1.setText(" \u62A5\u8B66\u540D\u79F0\uFF1A");
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setBackground(EccTreeControl.color);
		lblNewLabel_2.setBounds(0, 33, 78, 12);
		lblNewLabel_2.setText(" \u62A5\u8B66\u63A5\u6536\u4EBA\uFF1A");
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBackground(EccTreeControl.color);
		label_1.setBounds(0, 51, 90, 12);
		label_1.setText(" \u62A5\u8B66\u5F00\u59CB\u65F6\u95F4\uFF1A");
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBackground(EccTreeControl.color);
		label_2.setBounds(0, 69, 104, 12);
		label_2.setText(" \u62A5\u8B66\u7ED3\u675F\u65F6\u95F4\uFF1A");
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBackground(EccTreeControl.color);
		label_3.setBounds(0, 96, 78, 12);
		label_3.setText(" \u62A5\u8B66\u7C7B\u578B\uFF1A");
		
		Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setBounds(195, 2, 181, 20);
		combo.add("全部");
		combo.select(0);
		
		Combo combo_1 = new Combo(composite_1, SWT.NONE);
		combo_1.setBounds(195, 25, 181, 20);
		combo_1.add("全部");
		combo_1.select(0);
		
		DateTime dateTime = new DateTime(composite_1, SWT.BORDER);
		dateTime.setBounds(195, 51, 90, 20);
		
		DateTime dateTime_1 = new DateTime(composite_1, SWT.BORDER);
		dateTime_1.setBounds(195, 69, 90, 20);
		
		Combo combo_2 = new Combo(composite_1, SWT.READ_ONLY);
		combo_2.setBounds(195, 93, 181, 20);
		combo_2.add("全部");
		combo_2.add("邮件报警");
		combo_2.add("短信报警");
		combo_2.add("脚本报警");
		combo_2.add("声音报警");
		combo_2.select(0);
		
		DateTime dateTime_2 = new DateTime(composite_1, SWT.TIME);
		dateTime_2.setBounds(286, 51, 90, 20);
		
		DateTime dateTime_3 = new DateTime(composite_1, SWT.TIME);
		dateTime_3.setBounds(286, 69, 90, 20);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(EccTreeControl.color);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setText("\u62A5\u8B66\u65E5\u5FD7\u5217\u8868");
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		composite_3.setBackground(EccTreeControl.color);
		sashForm.setWeights(new int[] {11, 128, 18, 308});
		// TODO Auto-generated method stub
		
	}
	public void setFocus() {
	}
}
