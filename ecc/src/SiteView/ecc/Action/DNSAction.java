package SiteView.ecc.Action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import SiteView.ecc.Control.ConfigListFileName;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.tools.Config;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.LegalUtils;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import core.busobmaint.BusObMaintView;
import core.busobmaint.BusObNewInput;

public class DNSAction extends Action {

	public DNSAction(String name) {
		this.setText(name);
	}

	public void run() {
		MachineModle machine = (MachineModle) EccTreeControl.item;
		BusinessObject machinebo = machine.getBo();
		String groupId = machinebo.GetField("Groups").get_NativeValue()
				.toString();
		Siteview.Api.BusinessObject busOb = ConnectionBroker.get_SiteviewApi()
				.get_BusObService().Create(this.getText());
		busOb.GetField("Groups").SetValue(new SiteviewValue(groupId));
		String filePath = FileTools.getRealPath("\\files\\HostName.properties");
		String s = Config.getReturnStr(filePath, this.getText());
		String EccType = busOb.GetField("EccType").get_NativeValue().toString();
		String m = machinebo.GetField("ServerAddress").get_NativeValue()
				.toString();
		if ("ConfigDownLoadUploadMonitor".equals(EccType)) {
			ConfigListFileName.TelnetInfo(m);
			
		}
		if(!m.startsWith("\\\\")&&!(EccType.equals("ping")||EccType.equals("ConfigFileReadMonitor")||EccType.equals("ConfigDownLoadUploadMonitor"))){
			m="\\\\"+m;
		}
		if (s != null) {
			busOb.GetField(s).SetValue(new SiteviewValue(m));
			busOb.GetField("Machine").SetValue(
					new SiteviewValue(machinebo.get_RecId()));
		}
		if ("ConfigFileReadMonitor".equals(EccType)) {
			String Title = machinebo.GetField("RemoteName").get_NativeValue()
					.toString();
			if ("��Ϊ".equals(Title)) {
				busOb.GetField("title").SetValue(
						new SiteviewValue(Title + "�����ļ�"));
			} else if ("˼��".equals(Title)) {
				busOb.GetField("title").SetValue(
						new SiteviewValue(Title + "�����ļ�"));
			} else {
				busOb.GetField("title").SetValue(new SiteviewValue("�����ļ�"));
			}
		}
		try {
			PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.openEditor(
							new BusObNewInput(
									ConnectionBroker.get_SiteviewApi(),
									this.getText(), busOb), BusObMaintView.ID);
		} catch (PartInitException e1) {
			MessageDialog.openError(null, LegalUtils.get_MessageBoxCaption(),
					e1.getMessage());
			e1.printStackTrace();
		}
	}

}