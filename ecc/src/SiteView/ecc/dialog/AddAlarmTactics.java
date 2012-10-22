package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

import SiteView.ecc.Control.EccTreeContentProvider;
import SiteView.ecc.Control.EccTreeLabelProvider;
import SiteView.ecc.view.EccTreeControl;

public class AddAlarmTactics extends Dialog{
	private Text text;

	protected AddAlarmTactics(Shell parentShell) {
		super(parentShell);
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(500, 500);
		newShell.setLocation(200, 105);
		newShell.setText("新建策略");
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("宋体", 13, SWT.NORMAL));
		label.setBounds(0, 0, 87, 22);
		label.setText("\u62A5\u8B66\u540D\u79F0*\uFF1A");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBounds(93, 0, 341, 18);
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		
		Group group = new Group(sashForm_1, SWT.NONE);
		group.setLayout(new FillLayout());
		TreeViewer treeViewer = new TreeViewer(group);
		Tree tree = treeViewer.getTree();
		treeViewer.setContentProvider(new EccTreeContentProvider());
		treeViewer.setLabelProvider(new EccTreeLabelProvider());
		treeViewer.setInput(EccTreeControl.treeViewer.getInput());
		treeViewer.expandToLevel(2);
		Group group_1 = new Group(sashForm_1, SWT.NONE);
		group_1.setText("\u76D1\u6D4B\u5668\u7C7B\u578B");
		sashForm_1.setWeights(new int[] {1, 1});
		sashForm.setWeights(new int[] {22, 206});
		return composite;
	}
}
