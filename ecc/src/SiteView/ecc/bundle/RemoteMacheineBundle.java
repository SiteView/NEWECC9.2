package SiteView.ecc.bundle;

import java.util.Map;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import core.busobmaint.BusObMaintView;

import siteview.IAutoTaskExtension;
import Siteview.Api.BusinessObject;

public class RemoteMacheineBundle implements IAutoTaskExtension {

	public String run(Map<String, Object> params) throws Exception {
		BusinessObject bo = (BusinessObject) params.get("_CUROBJ_");
		Shell shell = new Shell();
		MyRunableWithProgress myrun = new MyRunableWithProgress();
		ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(shell);
		myrun.setPrincipal(system.Threading.Thread.get_CurrentPrincipal());
		myrun.setCurDisplay(Display.getCurrent());
		myrun.setBo(bo);
		progressDialog.run(false, false,myrun);
		shell.close();
		System.out.println(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()+",,,,");
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().dispose();
		return null;
	}
	public boolean hasCustomUI() {
		return false;
	}

	public void creatConfigUI(Composite parent, Map<String, String> params) {
	}

	public Map<String, String> getConfig() {
		return null;
	}
}