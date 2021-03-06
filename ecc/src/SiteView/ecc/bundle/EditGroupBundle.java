package SiteView.ecc.bundle;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import COM.dragonflow.Api.APIInterfaces;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import siteview.IAutoTaskExtension;

public class EditGroupBundle implements IAutoTaskExtension {
	APIInterfaces rmiServer;
	public EditGroupBundle() {
	}

	public String run(Map<String, Object> params) {
		BusinessObject bo = (BusinessObject) params.get("_CUROBJ_");
		GroupModle oldGroup=SiteViewData.subgroups.get(bo.get_RecId());
		BusinessObject oldbo=oldGroup.getBo();
		String oldParntId=oldbo.GetField("ParentGroupId").get_NativeValue().toString();
		String newParntId=bo.GetField("ParentGroupId").get_NativeValue().toString();
		GroupModle oldParent;
		String groupId=bo.get_RecId();
		if(oldParntId.equals(newParntId)){
			if(SiteViewData.subgroups.get(oldParntId)!=null){
				oldParent=SiteViewData.subgroups.get(oldParntId);
				oldParent.getGroups().remove(oldGroup);
				oldGroup.setBo(bo);
				oldParent.getGroups().add(oldGroup);
				SiteViewData.subgroups.put(oldParntId, oldParent);
				SiteViewData.subgroups.put(bo.get_RecId(), oldGroup);
				EccTreeControl.treeViewer.update(oldParent, new String[]{"groups"});
				EccTreeControl.treeViewer.update(oldGroup, new String[]{"bo"});
				EccTreeControl.treeViewer.refresh();
				updateGroup("GroupId="+oldParntId);
			}else{
				List<SiteViewEcc> list=(List<SiteViewEcc>) EccTreeControl.treeViewer.getInput();
				SiteViewEcc site=list.get(0);
				site.getList().remove(oldGroup);
				oldGroup.setBo(bo);
				site.getList().add(oldGroup);
				SiteViewData.subgroups.put(bo.get_RecId(), oldGroup);
				EccTreeControl.treeViewer.update(site, new String[]{"list"});
				EccTreeControl.treeViewer.update(oldGroup, new String[]{"bo"});
				EccTreeControl.treeViewer.refresh();
				updateGroup("GroupId="+groupId);
			}
			return null;
		}
		if(oldParntId!=null && !oldParntId.equals("")){
			GroupModle oldparent=SiteViewData.subgroups.get(oldParntId);
			oldparent.getGroups().remove(oldGroup);
			if(oldparent.getGroups().size()>=0){
				BusinessObject oldParntbo=oldparent.getBo();
				oldParntbo.GetField("HasSubGroup").SetValue(new SiteviewValue(false));
				oldParntbo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			}
			SiteViewData.subgroups.put(oldParntId, oldparent);
			EccTreeControl.treeViewer.update(oldparent, new String[]{"groups"});
			EccTreeControl.treeViewer.remove(oldGroup);
			updateGroup("GroupId="+oldParntId);
		}else{
			List<SiteViewEcc> list=(List<SiteViewEcc>) EccTreeControl.treeViewer.getInput();
			SiteViewEcc site=list.get(0);
			site.getList().remove(oldGroup);
			EccTreeControl.treeViewer.update(site, new String[]{"list"});
			EccTreeControl.treeViewer.remove(oldGroup);
		}
		if(newParntId!=null && !newParntId.equals("")){
			GroupModle newparent=SiteViewData.subgroups.get(newParntId);
			BusinessObject newbo=newparent.getBo();
			newbo.GetField("HasSubGroup").SetValue(new SiteviewValue(true));
			newbo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
			newparent.getGroups().add(oldGroup);
			SiteViewData.subgroups.put(newParntId, newparent);
			EccTreeControl.treeViewer.update(newparent,  new String[]{"groups"});
			EccTreeControl.treeViewer.insert(newparent, oldGroup, 0);
			updateGroup("GroupId="+newParntId);
		}else{
			List<SiteViewEcc> list=(List<SiteViewEcc>) EccTreeControl.treeViewer.getInput();
			SiteViewEcc site=list.get(0);
			oldGroup.setBo(bo);
			site.getList().add(oldGroup);
			EccTreeControl.treeViewer.update(site, new String[]{"list"});
			EccTreeControl.treeViewer.insert(site, oldGroup, 0);
		}
		EccTreeControl.treeViewer.refresh();
		SiteViewData.subgroups.put(bo.get_RecId(),oldGroup);
		updateGroup("GroupId="+groupId);
		return null;
	}
	public void updateGroup(String id){
		if(rmiServer==null){
			rmiServer=createAmiServer();
		}
		try {
			rmiServer.adjustGroups("",id,"edit");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	public static void main(String[]args){
		String RootFilePath=System.getProperty("user.dir");
		System.out.println(RootFilePath);
	}
	public static APIInterfaces createAmiServer(){
		Registry registry;
		String serverAddress ="localhost";
		String serverPort = "3232";
		APIInterfaces rmiServer;
	
			try {
				registry = LocateRegistry.getRegistry(serverAddress, (new Integer(
						serverPort)).intValue());
				rmiServer = (APIInterfaces) (registry.lookup("kernelApiRmiServer"));
				return rmiServer;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
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
