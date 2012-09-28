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
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.util.BundleUtility;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.TableModle;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.editors.EccControlInput;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class AllProhibitAction extends Action{
public List<BusinessObject> list;
public static EccControlInput eee=new EccControlInput();
public AllProhibitAction(List<BusinessObject> list){
	this.list=list;
	URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
			"Image/AllProhibitAction.bmp");
		    ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		    setImageDescriptor(temp);
			setText("批量禁止");
}

public AllProhibitAction() {
	URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
			"Image/AllProhibitAction.bmp");
		    ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		    setImageDescriptor(temp);
			setText("批量禁止");
}

public void run(){

	if(EccTreeControl.item instanceof GroupModle){//禁止组中监测器
		for(BusinessObject bb:list){
			String disable=bb.GetField("disable").get_NativeValue().toString();
			if("false".equals(disable)){
				bb.GetField("disable").SetValue(new SiteviewValue("true"));//true表示禁止
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
		
		
	}else if(EccTreeControl.item instanceof MachineModle){//禁止设备下监测器
		for(BusinessObject bb:list){
			String disable=bb.GetField("disable").get_NativeValue().toString();
			if("false".equals(disable)){
				bb.GetField("disable").SetValue(new SiteviewValue("true"));//true表示禁止
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
