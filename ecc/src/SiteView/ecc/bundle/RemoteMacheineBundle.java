package SiteView.ecc.bundle;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import siteview.IAutoTaskExtension;
import COM.dragonflow.Api.APIInterfaces;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.dialog.BatchAddMachine;
import SiteView.ecc.tools.TextUtils;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;

public class RemoteMacheineBundle implements IAutoTaskExtension {
	String[] ss=null;
	APIInterfaces rmiServer;
	public String run(Map<String, Object> params) throws Exception {
		BusinessObject bo = (BusinessObject) params.get("_CUROBJ_");
		String remoteMachineInfo = "";
		List<String[]> c=null;
		String hostname=bo.GetField("ServerAddress").get_NativeValue().toString();
		addMachine(bo);	
		rmiServer=EditGroupBundle.createAmiServer();
		remoteMachineInfo=getMachineMassage(bo);	
		c=rmiServer.doTestMachine(remoteMachineInfo,hostname,bo.get_RecId());
		String s=c.get(0)[0].replaceAll(" ", ".");
		bo.GetField("Status").SetValue(new SiteviewValue(s));
		//bo.SaveObject(ConnectionBroker.get_SiteviewApi(), false, true);
		MessageDialog.openInformation(new Shell(), "link test", c.get(0)[0]);
		BatchAddMachine b=new BatchAddMachine(null);
		b.s=c;
		b.hostname=hostname;
		b.group=bo.GetField("Groups").get_NativeValue().toString();
		b.machineId=bo.get_RecId();
		b.open();
		return null;
	}
	 static String getMachineMassage(BusinessObject bo) {
		String remoteMachineInfo="";
		String password = TextUtils.obscure(bo
				.GetField("PasswordMachine").get_NativeValue()
				.toString());
		String value=bo
				.GetField("DisableConnectionCaching")
				.get_NativeValue().toString();
		
		if(value.equals("true")||value.equals("1")){
			remoteMachineInfo+="_disableCache="+"on";
		}else{
			remoteMachineInfo+="_disableCache= ";
		}
		value=bo.GetField("Trace").get_NativeValue().toString();
		if(value.equals("true")||value.equals("1")){
			remoteMachineInfo+=";_trace="+"on";
		}else{
			remoteMachineInfo+=";_trace= ";
		}
		value=bo.GetField("SSHVersion2Only").get_NativeValue().toString();
		if(value.equals("true")||value.equals("1")){
			remoteMachineInfo+=";_version2="+"on";
		}else{
			remoteMachineInfo+=";_version2= ";
		} 
		if(bo.get_Name().contains("NT")){
			remoteMachineInfo+=";_os=NT";
		}
		if(bo.get_Name().contains("Remote")){
			remoteMachineInfo+="_Remote=Remote;";
		}
		else{
			value=bo.GetField("OS").get_NativeValue().toString();
			if(value.equals("Red Hat Enterprise Linux")){
				remoteMachineInfo+=";_os=RHESLinux";
			}
		}
		if(bo.GetField("SSHClient").get_NativeValue().toString().contains(" Java ")){
			remoteMachineInfo+=";_sshClient=java";
		}
		if(bo.get_Name().contains("RemoteUnix")){
			remoteMachineInfo+=";_prompt="
					+ bo.GetField("Prompt").get_NativeValue().toString()
					+ ";_passwordPrompt="
					+ bo.GetField("PasswordPrompt").get_NativeValue().toString()
					+ ";_loginPrompt="
					+ bo.GetField("LoginPrompt").get_NativeValue().toString()
					+ ";_secondaryPrompt="
					+ bo.GetField("SecondaryPrompt").get_NativeValue().toString();
		}
		if(bo.get_Name().contains("Remote")){
			remoteMachineInfo+=";_status=unknown"
					+ ";_sshPort="
					+ bo.GetField("PortNumber").get_NativeValue().toString()
					+";_id="
					+ bo.GetField("RecId").get_NativeValue().toString()
					+";_method="
					+ bo.GetField("ConnectionMethod").get_NativeValue().toString()
					+ ";_sshCommand="
					+ bo.GetField("CustomCommandline").get_NativeValue().toString()
					+ ";_login="
					+ bo.GetField("UserName").get_NativeValue().toString()
					+ ";_host="
					+ bo.GetField("ServerAddress").get_NativeValue().toString()
					+ ";_sshAuthMethod="
					+ bo.GetField("SSHAuthentication").get_NativeValue().toString()
					+ ";_sshConnectionsLimit="
					+ bo.GetField("ConnectionLimit").get_NativeValue().toString()
					+ ";_keyFile="
					+ bo.GetField("KeyFileforSSHconnections").get_NativeValue().toString()
					+ ";_password="+ password
					+ ";_name="
					+ bo.GetField("RemoteName").get_NativeValue().toString()+"\n";
		}else{
		remoteMachineInfo+=";_status=unknown"
				+ ";_sshPort="
				+ bo.GetField("PortNumber").get_NativeValue().toString()
				+";_id="
				+ bo.GetField("RecId").get_NativeValue().toString()
				+";_method="
				+ bo.GetField("ConnectionMethod").get_NativeValue().toString()
				+ ";_sshCommand="
				+ bo.GetField("CustomCommandline").get_NativeValue().toString()
				+ ";_login="
				+ bo.GetField("UserName").get_NativeValue().toString()
				+ ";_host="
				+ bo.GetField("ServerAddress").get_NativeValue().toString()
				+ ";_sshAuthMethod="
				+ bo.GetField("SSHAuthentication").get_NativeValue().toString()
				+ ";_sshConnectionsLimit="
				+ bo.GetField("ConnectionLimit").get_NativeValue().toString()
				+ ";_keyFile="
				+ bo.GetField("KeyFileforSSHconnections").get_NativeValue().toString()
				+ ";_password="+ password
				+ ";_name="
				+ bo.GetField("Title").get_NativeValue().toString()+"\n";
		}
		if(bo.get_Name().equals("RemoteMachine.RemoteNT")){
			remoteMachineInfo="_remoteNTMachine=;"+remoteMachineInfo;
		}else{
			remoteMachineInfo="_remoteMachine=;"+remoteMachineInfo;
		}
		return remoteMachineInfo;
	}
	
	public void addMachine(BusinessObject bo){
		String groupid=bo.GetField("Groups").get_NativeValue().toString();
		GroupModle groupModle=SiteViewData.subgroups.get(groupid);
		MachineModle machine=new MachineModle(bo, true, true, true, true,true);
		List<MachineModle> s=groupModle.getMachines();
		s.add(machine);
		groupModle.setMachines(s);
		SiteViewData.subgroups.put(groupid, groupModle);
		EccTreeControl.treeViewer.update(groupModle, new String[]{"machine"});
		EccTreeControl.treeViewer.insert(groupModle, machine, 0);
		addGroupBundle.setAuther(machine, bo.get_RecId());
	}
	
	public void addMonitor(BusinessObject bo){
		String groupid=bo.GetField("Groups").get_NativeValue().toString();
		GroupModle groupModle=SiteViewData.subgroups.get(groupid);
		GroupModle group=new GroupModle(bo, true, true, true, true,true,true,true,true);
	}
	public boolean hasCustomUI() {
		return false;
	}
	public void creatConfigUI(Composite parent, Map<String, String> params) {
	}
	public Map<String, String> getConfig() {
		return null;
	}
}
