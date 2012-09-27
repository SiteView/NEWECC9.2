package SiteView.ecc.Action;

import org.eclipse.jface.action.Action;
import java.net.URL;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.ui.internal.util.BundleUtility;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class ProhibitAction extends Action{
	public ProhibitAction(){
		URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
				"Image/ProhibitAction.bmp");
			ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		    setImageDescriptor(temp);
		    setText("禁止");
	}
	public void run(){
		ICollection ico = null;
		IEnumerator ie=null;
				if(EccTreeControl.item instanceof GroupModle){//禁止组中监测器
					String GroupId=((GroupModle) EccTreeControl.item).getBo().get_RecId();//得到选中组的id
					ico=FileTools.getBussCollection("Groups", GroupId, "Ecc");//通过得到选中组的id找到组中所有的监测器
					ie=ico.GetEnumerator();
					while(ie.MoveNext()){
						BusinessObject bo=(BusinessObject)ie.get_Current();
						if(bo!=null){
							String disable=bo.GetField("disable").get_NativeValue().toString();
							if("false".equals(disable)){
								bo.GetField("disable").SetValue(new SiteviewValue("true"));//true表示禁止
								bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
										true);
								String a=bo.GetField("disable").get_NativeValue().toString();
							}
						}
					}
				}else if(EccTreeControl.item instanceof MachineModle){//禁止设备下监测器
					String RecId=((MachineModle) EccTreeControl.item).getBo().get_RecId();//得到选中设备的id
					ico=FileTools.getBussCollection("Machine", RecId, "Ecc");//得到设备下的监测器
					ie=ico.GetEnumerator();
					while(ie.MoveNext()){
						BusinessObject bo=(BusinessObject)ie.get_Current();
						if(bo!=null){
							String disable=bo.GetField("disable").get_NativeValue().toString();
							if("false".equals(disable)){
								bo.GetField("disable").SetValue(new SiteviewValue("true"));
								bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
										true);
							}
						}
					}
				}
			
	}
}
