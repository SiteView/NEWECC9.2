package SiteView.ecc.Action;

import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.util.BundleUtility;

import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.editors.EccControlInput;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class AllStart extends Action{
public List<BusinessObject> list;
public static EccControlInput eee=new EccControlInput();
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
			String disable=bb.GetField("disable").get_NativeValue().toString();
			if("true".equals(disable)){
				bb.GetField("disable").SetValue(new SiteviewValue("false"));//false表示启动
				bb.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
						true);
				IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
				 IEditorPart editor = page.findEditor(eee); 
				 if(editor==null){
					 try {
						page.openEditor(eee, EccControl.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}  
				 }else{
					((EccControl)editor).createTableItem();
					if(EccControl.item==null){
						((EccControl)editor).tab(null);
					}else{
						((EccControl)editor).tab((BusinessObject)EccControl.item.getData());
					}
				 }
			}
		}
	}else if(EccTreeControl.item instanceof MachineModle){//启动设备下监测器
		for(BusinessObject bb:list){
			String disable=bb.GetField("disable").get_NativeValue().toString();
			if("true".equals(disable)){
				bb.GetField("disable").SetValue(new SiteviewValue("false"));//false表示启动
				bb.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
						true);
				IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
				 IEditorPart editor = page.findEditor(eee); 
				 if(editor==null){
					 try {
						page.openEditor(eee, EccControl.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}  
				 }else{
					((EccControl)editor).createTableItem();
					if(EccControl.item==null){
						((EccControl)editor).tab(null);
					}else{
						((EccControl)editor).tab((BusinessObject)EccControl.item.getData());
					}
				 }
			}
		}
	}
}
}
