package SiteView.ecc.bundle;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import siteview.IAutoTaskExtension;
import COM.dragonflow.Api.APIInterfaces;
import SiteView.ecc.dialog.ChooseDiskSpace;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;

public class ReturnDiskSpace implements IAutoTaskExtension {
	APIInterfaces rmiServer;
	public ReturnDiskSpace() {
		
	}

	@Override
	public String run(Map<String, Object> params) throws Exception {
		BusinessObject bo = (BusinessObject) params.get("_CUROBJ_");
		String address=bo.GetField("HostNameDiskSpace").get_NativeValue().toString();
		rmiServer=EditGroupBundle.createAmiServer();
		String s="";
		BusinessObject machine=FileTools.CreateBo("RecId", bo.GetField("Machine").get_NativeValue().toString(), "RemoteMachine");
		if(machine.GetField("RemoteMachineType").get_NativeValue().toString().equals("RemoteUnix")){
			s="remote:"+bo.GetField("Machine").get_NativeValue().toString();
		}
		String[] disk=rmiServer.getDiskSpace(address,s);
		if(disk.length>0){
			ChooseDiskSpace space=new ChooseDiskSpace(null,disk,bo);
			space.open();	
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
