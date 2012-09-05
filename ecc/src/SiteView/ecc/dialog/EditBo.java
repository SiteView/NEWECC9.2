package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import Siteview.IVirtualKeyList;
import Siteview.Api.BusinessObject;
import Siteview.Api.ISiteviewApi;
import Siteview.Api.VirtualKeyList;
import Siteview.Windows.Forms.ConnectionBroker;
import core.busobmaint.BusObMaintInput;
import core.busobmaint.BusObMaintView;

public class EditBo extends Dialog {
	public BusinessObject bo;
	public EditBo(Shell parent) {
		super(parent);
	}
	/*
	 * ≥ı ºªØ≈‰÷√
	 */
	protected void configureShell(Shell newShell) {
		newShell.setSize(500,500);
		newShell.setLocation(250,150);
		newShell.setText("±‡º≠º‡≤‚∆˜");
		super.configureShell(newShell);
	}
	
	/*
	 * ¥¥Ω®√Ê∞Â‘™Àÿ
	 */
	protected Control createDialogArea(Composite parent) {
		ISiteviewApi api=ConnectionBroker.get_SiteviewApi();
		IVirtualKeyList keyList = new VirtualKeyList(api);
		int nPos = keyList.AddKey(bo.get_Id(), bo.get_Name());
		keyList.Jump(nPos);
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new BusObMaintInput(api,bo.get_Definition().get_Name(),keyList), BusObMaintView.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parent;
	}
}
