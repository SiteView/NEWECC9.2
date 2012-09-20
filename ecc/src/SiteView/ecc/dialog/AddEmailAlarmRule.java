package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Group;

import SiteView.ecc.bundle.EmailAlarmBundle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

public class AddEmailAlarmRule extends Dialog{
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	public AddEmailAlarmRule(Shell parentShell) {
		super(parentShell);
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(700, 450);
		newShell.setLocation(200, 175);
		newShell.setText("添加Email报警");
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setBackground(SWTResourceManager.getColor(255, 255, 255));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Group group = new Group(sashForm, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		group.setText("\u9009\u62E9\u62A5\u8B66\u8303\u56F4");
		group.setLayout(new FillLayout());
		Tree tree = new Tree(group, SWT.CHECK);
		tree.setVisible(true);
		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		EmailAlarmBundle.createTreeItem(tree);
		
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
		sashForm_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Group grpEmail = new Group(sashForm_1, SWT.NONE);
		grpEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		grpEmail.setText("Email\u8BBE\u7F6E");
		
		Group group_1 = new Group(sashForm_1, SWT.NONE);
		group_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		group_1.setText("\u53D1\u9001\u6761\u4EF6");
		
		Label label = new Label(group_1, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label.setBounds(10, 24, 54, 12);
		label.setText("\u62A5\u8B66\u4E8B\u4EF6");
		
		Combo combo = new Combo(group_1, SWT.NONE);
		combo.setBounds(70, 21, 86, 20);
		
		Button button = new Button(group_1, SWT.RADIO);
		button.setBounds(10, 52, 160, 16);
		button.setText("\u8FDE\u7EED\u4E0D\u65AD\u53D1\u9001\u8B66\u544A");
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Label label_1 = new Label(group_1, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_1.setBounds(10, 74, 78, 12);
		label_1.setText("\u603B\u662F\u53D1\u9001,\u4ECE\u7B2C");
		
		text = new Text(group_1, SWT.BORDER);
		text.setBounds(90, 71, 43, 18);
		
		Label lblNewLabel = new Label(group_1, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel.setBounds(136, 74, 218, 12);
		lblNewLabel.setText("\u6B21\u7B26\u5408\u62A5\u8B66\u53D1\u9001\u6761\u4EF6\u5F00\u59CB\u53D1\u9001\u62A5\u8B66 ");
		
		Button button_1 = new Button(group_1, SWT.RADIO);
		button_1.setBounds(10, 117, 213, 16);
		button_1.setText("\u62A5\u8B66\u53EA\u53D1\u9001\u4E00\u6B21");
		button_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Label label_2 = new Label(group_1, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_2.setBounds(10, 139, 86, 12);
		label_2.setText("\u53D1\u9001\u4E00\u6B21\uFF0C\u5F53\u7B2C");
		
		text_1 = new Text(group_1, SWT.BORDER);
		text_1.setBounds(97, 136, 36, 18);
		
		Label label_3 = new Label(group_1, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_3.setBounds(136, 139, 218, 12);
		label_3.setText("\u6B21\u7B26\u5408\u62A5\u8B66\u53D1\u9001\u6761\u4EF6\u65F6\u53D1\u9001\u62A5\u8B66");
		
		Button btnRadioButton = new Button(group_1, SWT.RADIO);
		btnRadioButton.setBounds(10, 175, 213, 16);
		btnRadioButton.setText("\u9009\u62E9\u6027\u53D1\u9001\u8B66\u62A5");
		btnRadioButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Label lblNewLabel_1 = new Label(group_1, SWT.NONE);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		lblNewLabel_1.setBounds(10, 197, 30, 12);
		lblNewLabel_1.setText("\u5F53\u7B2C ");
		
		text_2 = new Text(group_1, SWT.BORDER);
		text_2.setBounds(40, 194, 48, 18);
		
		Label label_4 = new Label(group_1, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_4.setBounds(90, 197, 210, 12);
		label_4.setText("\u6B21\u7B26\u5408\u62A5\u8B66\u53D1\u9001\u6761\u4EF6\u65F6\u53CA\u5176\u4EE5\u540E\u6BCF\u91CD\u590D ");
		
		text_3 = new Text(group_1, SWT.BORDER);
		text_3.setBounds(307, 194, 43, 18);
		
		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		label_5.setBounds(354, 197, 127, 12);
		label_5.setText("\u6B21\u65F6\u53D1\u9001\u62A5\u8B66");
		sashForm_1.setWeights(new int[] {73, 155});
		sashForm.setWeights(new int[] {153, 288});
		return composite;
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "保存",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", true);
	}
}
