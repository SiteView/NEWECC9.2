package SiteView.ecc.bundle;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import COM.dragonflow.Api.APIInterfaces;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.dialog.BatchAddMachine;
import SiteView.ecc.tools.TextUtils;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;

import system.Security.Principal.IPrincipal;
public class MyRunableWithProgress implements IRunnableWithProgress{

    private IPrincipal principal;
    private Display curDisplay;
    private BusinessObject bo;
    
	public IPrincipal getPrincipal() {
		return principal;
	}

	public void setPrincipal(IPrincipal principal) {
		this.principal = principal;
	}

	public Display getCurDisplay() {
		return curDisplay;
	}

	public void setCurDisplay(Display curDisplay) {
		this.curDisplay = curDisplay;
	}

	public BusinessObject getBo() {
		return bo;
	}

	public void setBo(BusinessObject bo) {
		this.bo = bo;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException {
		monitor.beginTask("执行快速操作-", 100);
		monitor.subTask("准备添加设备……");
		final IProgressMonitor mon = monitor;
		Execute(monitor);
		monitor.done();
	}

	public void  Execute(IProgressMonitor monitor){
		APIInterfaces rmiServer;
		system.Threading.Thread.set_CurrentPrincipal(principal);
		String remoteMachineInfo = "";
		String hostname = bo.GetField("ServerAddress").get_NativeValue().toString();
		String type = bo.GetField("RemoteMachineType").get_NativeValue().toString();
		if (type.equals("RemoteNT")) {
			if (!hostname.startsWith("\\\\")) {
				hostname = "\\\\" + hostname;
			}
		}
		monitor.worked(10);
		String id=bo.get_RecId();
		addMachine(bo);
		monitor.worked(15);
		remoteMachineInfo = getMachineMassage(bo);
		monitor.worked(25);
		rmiServer = EditGroupBundle.createAmiServer();
		monitor.worked(30);
		monitor.subTask("正在链接设备...");
		List<String[]> c;
		try {
			c = rmiServer.doTestMachine(remoteMachineInfo, hostname,id);
			monitor.worked(90);
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(new Shell(), "link test","不能链接到设备");
			return;
		}
		String s = c.get(0)[0].replaceAll(" ", ".");
		bo.GetField("Status").SetValue(new SiteviewValue(s));
		monitor.done();
		MessageDialog.openInformation(new Shell(), "link test", c.get(0)[0]);
		BatchAddMachine b = new BatchAddMachine(null,bo);
		
		b.s = c;
		b.hostname = hostname;
		b.group = bo.GetField("Groups").get_NativeValue().toString();
		b.machineId = bo.get_RecId();
		b.open();
	}
	static String getMachineMassage(BusinessObject bo) {
		String remoteMachineInfo = "";
		String password = TextUtils.obscure(bo.GetField("PasswordMachine")
				.get_NativeValue().toString());
		String value = bo.GetField("DisableConnectionCaching")
				.get_NativeValue().toString();

		if (value.equals("true") || value.equals("1")) {
			remoteMachineInfo += "_disableCache=" + "on";
		} else {
			remoteMachineInfo += "_disableCache= ";
		}
		value = bo.GetField("Trace").get_NativeValue().toString();
		if (value.equals("true") || value.equals("1")) {
			remoteMachineInfo += ";_trace=" + "on";
		} else {
			remoteMachineInfo += ";_trace= ";
		}
		value = bo.GetField("SSHVersion2Only").get_NativeValue().toString();
		if (value.equals("true") || value.equals("1")) {
			remoteMachineInfo += ";_version2=" + "on";
		} else {
			remoteMachineInfo += ";_version2= ";
		}
		if (bo.get_Name().contains("NT")) {
			remoteMachineInfo += ";_os=NT";
		}
		if (bo.get_Name().contains("Remote")) {
			remoteMachineInfo += "_Remote=Remote;";
		} else {
			value = bo.GetField("OS").get_NativeValue().toString();
			if (value.equals("Red Hat Enterprise Linux")) {
				remoteMachineInfo += ";_os=RHESLinux";
			}
		}
		if (bo.GetField("SSHClient").get_NativeValue().toString()
				.contains(" Java ")) {
			remoteMachineInfo += ";_sshClient=java";
		}
		if (bo.get_Name().contains("RemoteUnix")) {
			remoteMachineInfo += ";_prompt="
					+ bo.GetField("Prompt").get_NativeValue().toString()
					+ ";_passwordPrompt="
					+ bo.GetField("PasswordPrompt").get_NativeValue()
							.toString()
					+ ";_loginPrompt="
					+ bo.GetField("LoginPrompt").get_NativeValue().toString()
					+ ";_secondaryPrompt="
					+ bo.GetField("SecondaryPrompt").get_NativeValue()
							.toString();
		}
		if (bo.get_Name().contains("Remote")) {
			remoteMachineInfo += ";_status=unknown" + ";_sshPort="
					+ bo.GetField("PortNumber").get_NativeValue().toString()
					+ ";_id="
					+ bo.GetField("RecId").get_NativeValue().toString()
					+ ";_method="
					+ bo.GetField("ConnectionMethod").get_NativeValue()
							.toString()
					+ ";_sshCommand="
					+ bo.GetField("CustomCommandline").get_NativeValue()
							.toString()
					+ ";_login="
					+ bo.GetField("UserName").get_NativeValue().toString()
					+ ";_host="
					+ bo.GetField("ServerAddress").get_NativeValue().toString()
					+ ";_sshAuthMethod="
					+ bo.GetField("SSHAuthentication").get_NativeValue()
							.toString()
					+ ";_sshConnectionsLimit="
					+ bo.GetField("ConnectionLimit").get_NativeValue()
							.toString()
					+ ";_keyFile="
					+ bo.GetField("KeyFileforSSHconnections").get_NativeValue()
							.toString() + ";_password=" + password + ";_name="
					+ bo.GetField("RemoteName").get_NativeValue().toString()
					+ "\n";
		} else {
			remoteMachineInfo += ";_status=unknown" + ";_sshPort="
					+ bo.GetField("PortNumber").get_NativeValue().toString()
					+ ";_id="
					+ bo.GetField("RecId").get_NativeValue().toString()
					+ ";_method="
					+ bo.GetField("ConnectionMethod").get_NativeValue()
							.toString()
					+ ";_sshCommand="
					+ bo.GetField("CustomCommandline").get_NativeValue()
							.toString()
					+ ";_login="
					+ bo.GetField("UserName").get_NativeValue().toString()
					+ ";_host="
					+ bo.GetField("ServerAddress").get_NativeValue().toString()
					+ ";_sshAuthMethod="
					+ bo.GetField("SSHAuthentication").get_NativeValue()
							.toString()
					+ ";_sshConnectionsLimit="
					+ bo.GetField("ConnectionLimit").get_NativeValue()
							.toString()
					+ ";_keyFile="
					+ bo.GetField("KeyFileforSSHconnections").get_NativeValue()
							.toString() + ";_password=" + password + ";_name="
					+ bo.GetField("Title").get_NativeValue().toString() + "\n";
		}
		if (bo.get_Name().equals("RemoteMachine.RemoteNT")) {
			remoteMachineInfo = "_remoteNTMachine=;" + remoteMachineInfo;
		} else {
			remoteMachineInfo = "_remoteMachine=;" + remoteMachineInfo;
		}
		return remoteMachineInfo;
	}
	
	public void addMachine(BusinessObject bo) {
		String groupid = bo.GetField("Groups").get_NativeValue().toString();
		GroupModle groupModle = SiteViewData.subgroups.get(groupid);
		MachineModle machine = new MachineModle(bo, true, true, true, true,
				true);
		List<MachineModle> s = groupModle.getMachines();
		s.add(machine);
		groupModle.setMachines(s);
		SiteViewData.subgroups.put(groupid, groupModle);
		EccTreeControl.treeViewer
				.update(groupModle, new String[] { "machine" });
		EccTreeControl.treeViewer.insert(groupModle, machine, 0);
		addGroupBundle.setAuther(machine, bo.get_RecId());
	}

}
