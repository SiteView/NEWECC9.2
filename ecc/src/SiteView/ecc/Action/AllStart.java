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
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

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
	if(EccTreeControl.item instanceof GroupModle){//启动组中监测器
		for(BusinessObject bb:list){
			System.out.println("bb:"+bb);
			String disable=bb.GetField("disable").get_NativeValue().toString();
			System.out.println("disable:"+disable);
			if("true".equals(disable)){
				bb.GetField("disable").SetValue(new SiteviewValue("false"));//false表示启动
				bb.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
						true);
				String a=bb.GetField("disable").get_NativeValue().toString();
				System.out.println("a:"+a);
			}
		}
	}else if(EccTreeControl.item instanceof MachineModle){//启动设备下监测器
		for(BusinessObject bb:list){
			System.out.println("bb:"+bb);
			String disable=bb.GetField("disable").get_NativeValue().toString();
			System.out.println("disable:"+disable);
			if("true".equals(disable)){
				bb.GetField("disable").SetValue(new SiteviewValue("false"));//false表示启动
				bb.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
						true);
				String a=bb.GetField("disable").get_NativeValue().toString();
				System.out.println("a:"+a);
			}
		}
	}
}
}
