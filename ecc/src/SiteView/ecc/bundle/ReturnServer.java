package SiteView.ecc.bundle;

import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

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
		if(!address.startsWith("\\")){
			address="\\\\"+address;
		}
		rmiServer=EditGroupBundle.createAmiServer();
		String s="";
		BusinessObject machine=FileTools.CreateBo("RecId", bo.GetField("Machine").get_NativeValue().toString(), "RemoteMachine");
		if(machine.GetField("RemoteMachineType").get_NativeValue().toString().equals("RemoteUnix")){
			s="remote:"+bo.GetField("Machine").get_NativeValue().toString();
		}
		String[] service = rmiServer.getServer(address,s);
		if(service.length<=0){
			MessageDialog.openInformation(new Shell(), "提示", "未找到远程设备的服务");
			return null;
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
