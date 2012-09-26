package SiteView.ecc.Action;

import java.net.URL;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.internal.util.BundleUtility;
import SiteView.ecc.Activator;

public class AllProhibitAction extends Action{
public AllProhibitAction(){
	URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
	"Image/AllProhibitAction.bmp");
    ImageDescriptor temp = ImageDescriptor.createFromURL(url);
    setImageDescriptor(temp);
	setText("ÅúÁ¿½ûÖ¹");
}
public void run(){
	
}
}
