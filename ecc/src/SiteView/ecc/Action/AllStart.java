package SiteView.ecc.Action;

import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.internal.util.BundleUtility;

import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;

public class AllStart extends Action{
public List<BusinessObject> list;
public AllStart(List<BusinessObject> list){
	this.list=list;
	URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
	"Image/AllStart.bmp");
    ImageDescriptor temp = ImageDescriptor.createFromURL(url);
    setImageDescriptor(temp);
	setText("批量启动");
}
public void run(){
	if(EccTreeControl.item instanceof GroupModle){//禁止组中监测器
		
	}else if(EccTreeControl.item instanceof MachineModle){//禁止设备下监测器
		
	}
}
}
