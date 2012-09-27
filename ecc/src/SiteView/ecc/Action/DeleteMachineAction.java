package SiteView.ecc.Action;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.util.BundleUtility;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class DeleteMachineAction extends Action {
	
	
	
	public DeleteMachineAction() {
			URL  url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),"Image/Delete.bmp");
			ImageDescriptor temp = ImageDescriptor.createFromURL(url);
			setImageDescriptor(temp);
			setText("删除设备");
	}

	public void run() {
		MachineModle machine=(MachineModle) EccTreeControl.item;
		BusinessObject bo =machine.getBo();
		String groupId=bo.GetFieldOrSubfield("Groups_valid").get_NativeValue().toString();
		GroupModle parentGroup=SiteViewData.subgroups.get(groupId);
		parentGroup.getMachines().remove(machine);
		SiteViewData.subgroups.put(groupId, parentGroup);
		EccTreeControl.treeViewer.remove(machine);
		EccTreeControl.treeViewer.update(parentGroup, new String[]{"machines"});
		EccTreeControl.treeViewer.refresh();
		String id=bo.get_RecId();
		//树上节点选中修改，monitor列表修改
		EccTreeControl.item=SiteViewData.subgroups.get(groupId);
		IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
		 IEditorPart editor = page.findEditor(EccTreeControl.eee); 
		 if(editor==null){
			 try {
				page.openEditor(EccTreeControl.eee, EccControl.ID);
			} catch (PartInitException e1) {
				// TODO Auto-generated catch block
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
		if (bo != null) {
			bo.DeleteObject(ConnectionBroker.get_SiteviewApi());//删除数据库数据
		}
		ICollection ico=FileTools.getBussCollection("Machine", id, "Ecc");
		IEnumerator ien=ico.GetEnumerator();
		while(ien.MoveNext()){
			BusinessObject monitor=(BusinessObject) ien.get_Current();
			BusinessObject mdyn=FileTools.CreateBo("monitorid", monitor.get_RecId(), "EccDyn");
			if(mdyn!=null){
				mdyn.DeleteObject(ConnectionBroker.get_SiteviewApi());
			}
			monitor.DeleteObject(ConnectionBroker.get_SiteviewApi());
		}
		
	}
	
}
