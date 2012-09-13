package SiteView.ecc.bundle;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import siteview.IAutoTaskExtension;

public class ScriptAlarmBundle implements IAutoTaskExtension {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Tree tree;

	public ScriptAlarmBundle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String run(Map<String, Object> params) throws Exception {
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
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		sashForm.SASH_WIDTH = 0;

		Group groupMonitor = new Group(sashForm, SWT.NONE);
		groupMonitor.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		groupMonitor.setText("\u76D1\u6D4B\u5668\u9009\u62E9");
		groupMonitor.setLayout(new FillLayout(SWT.HORIZONTAL));

		tree = new Tree(groupMonitor, SWT.BORDER | SWT.CHECK);
		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		tree.setVisible(true);
		tree.setHeaderVisible(true);
		tree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem treeItem = (TreeItem) e.item;
				if(treeItem.getChecked()){
					SoundAlarmBundle.SelectParent(treeItem);
					SoundAlarmBundle.SelectChild(treeItem);
				}else{
					SoundAlarmBundle.DeletChild(treeItem);
					TreeItem item = treeItem.getParentItem();
					TreeItem[] item1 = item.getItems();
					for(int i=0;i<item1.length;i++){
						if(item1[i].getChecked()){
							treeItem.setChecked(false);
							break;
						}
						if(i==item1.length-1){							
							SoundAlarmBundle.DeletParent(treeItem);
						}
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		EmailAlarmBundle.createTreeItem(tree);

		Group groupSetUp = new Group(sashForm, SWT.NONE);
		groupSetUp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		groupSetUp.setText("\u8BBE\u7F6E \u811A\u672C\u62A5\u8B66");

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
		lblNewLabel.setText("\u9009\u62E9\u670D\u52A1\u5668*\uFF1A");

		text_1 = new Text(groupSetUp, SWT.BORDER);
		text_1.setBounds(105, 55, 155, 20);
		if (params.get("SelectServer") != null) {
			text_1.setText(params.get("SelectServer"));
		}

		Label label_1 = new Label(groupSetUp, SWT.NONE);
		label_1.setText("\u9009\u62E9\u811A\u672C*\uFF1A");
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(10, 90, 90, 20);

		text_2 = new Text(groupSetUp, SWT.BORDER);
		text_2.setBounds(105, 90, 155, 20);
		if (params.get("SelectScript") != null) {
			text_2.setText(params.get("SelectScript"));
		}

		Label label_2 = new Label(groupSetUp, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(10, 125, 90, 20);
		label_2.setText("\u9644\u52A0\u53C2\u6570\uFF1A");

		text_3 = new Text(groupSetUp, SWT.BORDER);
		text_3.setBounds(105, 125, 155, 20);
		if (params.get("AddParameter") != null) {
			text_3.setText(params.get("AddParameter"));
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
		sashForm.setWeights(new int[] { 2, 3 });

	}

	@Override
	public Map<String, String> getConfig() {
		Map map = new HashMap<String, String>();
		map.put("AlarmName", text.getText());
		map.put("SelectServer", text_1.getText());
		map.put("SelectScript", text_2.getText());
		map.put("AddParameter", text_3.getText());
		map.put("AlarmTactics", text_4.getText());
		return map;
	}
}
