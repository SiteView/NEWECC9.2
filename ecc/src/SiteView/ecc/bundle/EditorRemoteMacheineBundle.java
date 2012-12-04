package SiteView.ecc.bundle;

import java.util.List;
import java.util.Map;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import COM.dragonflow.Api.APIInterfaces;
import siteview.IAutoTaskExtension;

public class EditorRemoteMacheineBundle implements IAutoTaskExtension {
	APIInterfaces rmiServer;
	public EditorRemoteMacheineBundle() {
	}

	@Override
	public String run(Map<String, Object> params) throws Exception {
		BusinessObject bo = (BusinessObject) params.get("_CUROBJ_");
		rmiServer=EditGroupBundle.createAmiServer();
		String hostname=bo.GetField("ServerAddress").get_NativeValue().toString();
		String remoteMachineInfo = MyRunableWithProgress.getMachineMassage(bo);
		List<String[]> c=rmiServer.doTestMachine(remoteMachineInfo,hostname,bo.get_RecId());
		String s=c.get(0)[0].replaceAll(" ", ".");
		bo.GetField("Status").SetValue(new SiteviewValue(s));
		bo.SaveObject(ConnectionBroker.get_SiteviewApi(), false, true);
		editMachine(bo);
		MessageDialog.openInformation(new Shell(), "link test", c.get(0)[0]);
		return null;
	}

	private void editMachine(BusinessObject bo) {
		String groupid=bo.GetField("Groups").get_NativeValue().toString();
		GroupModle groupModle=SiteViewData.subgroups.get(groupid);
		MachineModle machine=null;
		for(MachineModle ma:groupModle.getMachines()){
			if(ma.getBo().get_RecId().equals(bo.get_RecId())){
				machine=ma;
			}
		}
		machine.setBo(bo);
		groupModle.getMachines().add(machine);
		SiteViewData.subgroups.put(groupid, groupModle);
		EccTreeControl.treeViewer.update(groupModle, new String[]{"machine"});
		EccTreeControl.treeViewer.update(machine, new String[]{"bo"});
		addGroupBundle.setAuther(machine, bo.get_RecId());
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
