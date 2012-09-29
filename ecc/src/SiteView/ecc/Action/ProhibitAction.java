package SiteView.ecc.Action;

import org.eclipse.jface.action.Action;
import java.net.URL;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.internal.util.BundleUtility;
import SiteView.ecc.Activator;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.view.EccTreeControl;

public class ProhibitAction extends Action {
	public ProhibitAction() {
		URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
				"Image/ProhibitAction.bmp");
		ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		setImageDescriptor(temp);
		setText("½ûÖ¹");
	}

	public void run() {
		EccControl.getSelete(true,"½ûÖ¹");
	}
}
