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
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

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
			String disable=bb.GetField("disable").get_NativeValue().toString();
			System.out.println("disable:"+disable);
			if("false".equals(disable)){
				bb.GetField("disable").SetValue(new SiteviewValue("true"));//true��ʾ��ֹ
				bb.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
						true);
				String a=bb.GetField("disable").get_NativeValue().toString();
				System.out.println("a:"+a);
			}
		}
		
		
	}else if(EccTreeControl.item instanceof MachineModle){//��ֹ�豸�¼����
		for(BusinessObject bb:list){
			System.out.println(bb);
			String disable=bb.GetField("disable").get_NativeValue().toString();
			System.out.println("disable:"+disable);
			if("false".equals(disable)){
				bb.GetField("disable").SetValue(new SiteviewValue("true"));//true��ʾ��ֹ
				bb.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
						true);
				String a=bb.GetField("disable").get_NativeValue().toString();
				System.out.println("a:"+a);
			}
		}
	}
}
}
