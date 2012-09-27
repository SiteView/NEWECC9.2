package SiteView.ecc.bundle;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Operators;
import Siteview.QueryInfoToGet;
import Siteview.SiteviewQuery;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import COM.dragonflow.Api.APIInterfaces;
import COM.dragonflow.SiteViewException.SiteViewException;

import siteview.IAutoTaskExtension;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import system.Xml.XmlElement;

public class DeleteGroupBundle implements IAutoTaskExtension {
	APIInterfaces rmiServer;
	public DeleteGroupBundle() {
	}
	public String run(Map<String, Object> params) {
		BusinessObject bo = (BusinessObject) params.get("_CUROBJ_");
		deleteGroup(bo);
		return null;
	}
	public void deleteGroup(String id){
		if(rmiServer==null){
			rmiServer=EditGroupBundle.createAmiServer();
		}
		try {
			rmiServer.adjustGroups("", id, "delete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteGroup(BusinessObject bo){
		String groupId=bo.GetField("RecId").get_NativeValue().toString();
		String parentId=bo.GetField("ParentGroupId").get_NativeValue().toString();
		
		ICollection monitors=FileTools.getBussCollection("Groups_Valid",groupId,"Ecc");
		IEnumerator interfaceTableIEnum = monitors.GetEnumerator();
		while(interfaceTableIEnum.MoveNext()){
			BusinessObject monitor=(BusinessObject) interfaceTableIEnum.get_Current();
			String monitorId=monitor.get_RecId();
			monitor.DeleteObject(ConnectionBroker.get_SiteviewApi());
			BusinessObject bo_1=FileTools.CreateBo("monitorid", monitorId, "EccDyn");
			if(bo_1!=null){
				bo_1.DeleteObject(ConnectionBroker.get_SiteviewApi());
			}
		}
		GroupModle group=(GroupModle) EccTreeControl.item;
		if(parentId==null||parentId.equals("")){
			SiteViewEcc site=(SiteViewEcc) ((List)EccTreeControl.treeViewer.getInput()).get(0);
			site.getList().remove(group);
			EccTreeControl.treeViewer.update(site, new String[] {"list"});
			if(EccTreeControl.item instanceof GroupModle&&((GroupModle)EccTreeControl.item).getBo().get_RecId().equals(groupId)){
				EccTreeControl.item=site;
				updateEdit();
			}
		}else{
			if(group.getGroups().size()<=0){
				BusinessObject parentbo=group.getBo();
				if(parentbo!=null){
					parentbo.GetField("HasSubGroup").SetValue(new SiteviewValue("false"));
					parentbo.SaveObject(ConnectionBroker.get_SiteviewApi(), false, true);
				}
			}
			GroupModle parent=SiteViewData.subgroups.get(parentId);
			parent.getGroups().remove(group);
			EccTreeControl.treeViewer.update(parent, new String[]{"groups"});
			EditGroupBundle edit=new EditGroupBundle();
			if(EccTreeControl.item instanceof GroupModle&&((GroupModle)EccTreeControl.item).getBo().get_RecId().equals(groupId)){
				EccTreeControl.item=parent;
				updateEdit();
			}
			edit.updateGroup("GroupId="+parentId);
		}
		EccTreeControl.treeViewer.remove(group);
		EccTreeControl.treeViewer.refresh();
		//deleteGroup("GroupId="+groupId);
		EccTreeControl.treeViewer.refresh();
	}
	//ÐÞ¸ÄMonitorEdit
	private void updateEdit() {
		// TODO Auto-generated method stub
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
	}
	@Override
	public boolean hasCustomUI() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void creatConfigUI(Composite parent, Map<String, String> params) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, String> getConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
