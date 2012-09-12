package SiteView.ecc.bundle;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import siteview.IAutoTaskExtension;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.wb.swt.SWTResourceManager;

public class MassageAlarmBundle implements IAutoTaskExtension {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Tree tree;

	public MassageAlarmBundle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String run(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomUI() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void creatConfigUI(Composite parent, Map<String, String> params) {
		// TODO Auto-generated method stub
		parent.setLayout(new FillLayout());
		Composite con=new Composite(parent, SWT.NONE);
		con.setLayout(new FillLayout(SWT.HORIZONTAL));
		SashForm sashForm = new SashForm(con, SWT.NONE);
		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(new FillLayout());
		tree = new Tree(composite, SWT.BORDER | SWT.CHECK);
		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tree.setVisible(true);
		tree.setHeaderVisible(true);
		EmailAlarmBundle.createTreeItem(tree);
		
		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setBounds(10, 21, 54, 12);
		label.setText("\u62A5\u8B66\u540D\u79F0\uFF1A");
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBounds(10, 49, 72, 12);
		label_1.setText("\u63A5\u53D7\u624B\u673A\u53F7\uFF1A");
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(10, 73, 72, 12);
		lblNewLabel.setText("\u5176\u4ED6\u624B\u673A\u53F7\uFF1A");
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBounds(10, 97, 54, 12);
		label_2.setText("\u53D1\u9001\u65B9\u5F0F\uFF1A");
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBounds(10, 123, 60, 12);
		label_3.setText("\u77ED\u4FE1\u6A21\u7248\uFF1A");
		
		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setBounds(10, 147, 54, 12);
		label_4.setText("\u5347\u7EA7\u6B21\u6570\uFF1A");
		
		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setBounds(10, 170, 84, 12);
		label_5.setText("\u5347\u7EA7\u63A5\u6536\u5730\u5740\uFF1A");
		
		Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setBounds(10, 195, 54, 12);
		label_6.setText("\u505C\u6B62\u6B21\u6570\uFF1A");
		
		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setBounds(10, 222, 84, 12);
		label_7.setText("\u503C\u73ED\u62A5\u8B66\u5217\u8868\uFF1A");
		
		Label label_8 = new Label(composite_1, SWT.NONE);
		label_8.setBounds(10, 252, 54, 12);
		label_8.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setBounds(115, 18, 156, 18);
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(115, 43, 156, 18);
		
		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setBounds(115, 67, 156, 18);
		
		text_3 = new Text(composite_1, SWT.BORDER);
		text_3.setBounds(115, 91, 156, 18);
		
		text_4 = new Text(composite_1, SWT.BORDER);
		text_4.setBounds(115, 117, 156, 18);
		
		text_5 = new Text(composite_1, SWT.BORDER);
		text_5.setBounds(115, 141, 156, 18);
		
		text_6 = new Text(composite_1, SWT.BORDER);
		text_6.setBounds(115, 164, 156, 18);
		
		text_7 = new Text(composite_1, SWT.BORDER);
		text_7.setBounds(115, 189, 156, 18);
		
		text_8 = new Text(composite_1, SWT.BORDER);
		text_8.setBounds(115, 216, 156, 18);
		
		text_9 = new Text(composite_1, SWT.BORDER);
		text_9.setBounds(115, 246, 156, 18);
		sashForm.setWeights(new int[] {163, 284});
	}

	@Override
	public Map<String, String> getConfig() {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String,String>();
		return null;
	}
}
