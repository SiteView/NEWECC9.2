package SiteView.ecc.bundle;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import SiteView.ecc.dialog.ChooseService;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import COM.dragonflow.Api.APIInterfaces;

import siteview.IAutoTaskExtension;
import system.Windows.Forms.VisualStyles.FillType;

public class ReturnServer implements IAutoTaskExtension {
	APIInterfaces rmiServer;

	public ReturnServer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String run(Map<String, Object> params) throws Exception {
		BusinessObject bo = (BusinessObject) params.get("_CUROBJ_");
		String address=bo.GetField("Service").get_NativeValue().toString();
		rmiServer=EditGroupBundle.createAmiServer();
		String s="";
		BusinessObject machine=FileTools.CreateBo("RecId", bo.GetField("Machine").get_NativeValue().toString(), "RemoteMachine");
		if(machine.GetField("RemoteMachineType").get_NativeValue().toString().equals("RemoteUnix")){
			s="remote:"+bo.GetField("Machine").get_NativeValue().toString();
		}
		String[] service = rmiServer.getServer(address,s);
		for (int i = 0; i < service.length; i++) {
			System.out.println(service[i]);
		}
		ChooseService cs=new ChooseService(null, service, bo);
		cs.open();
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
