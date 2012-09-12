package SiteView.ecc.bundle;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import siteview.IAutoTaskExtension;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Combo;

public class SoundAlarmBundle implements IAutoTaskExtension {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Tree tree;

	public SoundAlarmBundle() {
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
		parent.setLayout(new FillLayout());
		Composite composite=new Composite(parent, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		sashForm.SASH_WIDTH=0;
		
		Group group = new Group(sashForm, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group.setText("\u76D1\u6D4B\u5668\u9009\u62E9");
		group.setLayout(new FillLayout());
		
		tree = new Tree(group, SWT.BORDER | SWT.CHECK);
		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tree.setVisible(true);
		tree.setHeaderVisible(true);
		EmailAlarmBundle.createTreeItem(tree);
		
		Group groupSetUp = new Group(sashForm, SWT.NONE);
		groupSetUp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		groupSetUp.setText("\u8BBE\u7F6E \u58F0\u97F3\u62A5\u8B66");
		
		Label label = new Label(groupSetUp, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(10, 20, 90, 20);
		label.setText("\u62A5\u8B66\u540D\u79F0*\uFF1A");
		
		text = new Text(groupSetUp, SWT.BORDER);
		text.setBounds(105, 20, 155, 20);
		if (params.get("AlarmName") != null) {
			text.setText(params.get("AlarmName"));
		}
		
		Label lblNewLabel = new Label(groupSetUp, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 55, 90, 20);
		lblNewLabel.setText("\u670D\u52A1\u5668\u540D*\uFF1A");
		
		text_1 = new Text(groupSetUp, SWT.BORDER);
		text_1.setBounds(105, 55, 155, 20);
		if (params.get("ServerName") != null) {
			text_1.setText(params.get("ServerName"));
		}
		
		Label label_1 = new Label(groupSetUp, SWT.NONE);
		label_1.setText("\u767B\u5F55\u540D*\uFF1A");
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(10, 90, 90, 20);
		
		text_2 = new Text(groupSetUp, SWT.BORDER);
		text_2.setBounds(105, 90, 155, 20);
		if (params.get("LoginName") != null) {
			text_2.setText(params.get("LoginName"));
		}
		
		Label label_2 = new Label(groupSetUp, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(10, 125, 90, 20);
		label_2.setText("\u767B\u5F55\u5BC6\u7801\uFF1A");
		
		text_3 = new Text(groupSetUp, SWT.BORDER);
		text_3.setBounds(105, 125, 155, 20);
		if (params.get("LoginPassword") != null) {
			text_3.setText(params.get("LoginPassword"));
		}
		
		Label label_3 = new Label(groupSetUp, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(10, 160, 90, 20);
		label_3.setText("\u62A5\u8B66\u7B56\u7565\uFF1A");
		
		text_4 = new Text(groupSetUp, SWT.BORDER);
		text_4.setBounds(105, 160, 155, 20);
		if (params.get("AlarmTactics") != null) {
			text_4.setText(params.get("AlarmTactics"));
		}
		sashForm.setWeights(new int[] {2, 3});

	}

	@Override
	public Map<String, String> getConfig() {
		Map map=new HashMap<String, String>();
		map.put("AlarmName", text.getText());
		map.put("ServerName", text_1.getText());
		map.put("LoginName", text_2.getText());
		map.put("LoginPassword", text_3.getText());
		map.put("AlarmTactics", text_4.getText());
		return map;
		
	}

}
