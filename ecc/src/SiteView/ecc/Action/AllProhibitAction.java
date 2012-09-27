package SiteView.ecc.Action;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.internal.util.BundleUtility;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;

public class AllProhibitAction extends Action{
public List<BusinessObject> list;
public AllProhibitAction(List<BusinessObject> list){
	this.list=list;
	URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
			"Image/AllProhibitAction.bmp");
		    ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		    setImageDescriptor(temp);
			setText("������ֹ");
}

public AllProhibitAction() {
	URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
			"Image/AllProhibitAction.bmp");
		    ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		    setImageDescriptor(temp);
			setText("������ֹ");
}

public void run(){

	if(EccTreeControl.item instanceof GroupModle){//��ֹ���м����
		for(BusinessObject bb:list){
			System.out.println(bb);
		}
		
		
	}else if(EccTreeControl.item instanceof MachineModle){//��ֹ�豸�¼����
		
	}
}
}
