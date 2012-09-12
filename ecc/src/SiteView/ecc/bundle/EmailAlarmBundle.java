package SiteView.ecc.bundle;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import siteview.IAutoTaskExtension;

public class EmailAlarmBundle implements IAutoTaskExtension {

	public EmailAlarmBundle() {
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
		Label label=new Label(parent, SWT.NONE);
		label.setText("±¨¾¯Ãû³Æ");
		
	}

	@Override
	public Map<String, String> getConfig() {
		// TODO Auto-generated method stub
		return null;
	}

}
