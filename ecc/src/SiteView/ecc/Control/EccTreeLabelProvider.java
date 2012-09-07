package SiteView.ecc.Control;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;

import Siteview.Api.BusinessObject;

import SiteView.ecc.Activator;
import SiteView.ecc.Modle.AlarmLogModle;
import SiteView.ecc.Modle.AlarmModle;
import SiteView.ecc.Modle.AlarmRuleModle;
import SiteView.ecc.Modle.AlarmTacticsModle;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorSetUpModel;
import SiteView.ecc.Modle.SetUpModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.Modle.TableDutyModle;
import SiteView.ecc.Modle.UserManageModle;
import siteview.windows.forms.ImageHelper;

public class EccTreeLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
	   if(element instanceof SiteViewEcc){
		   return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/logo.jpg");
	   }else if (element instanceof GroupModle) {
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/node.jpg");
		}else if(element instanceof MachineModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/shebei.jpg");
		}else if(element instanceof SetUpModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/Setup.jpg");
		}else if(element instanceof UserManageModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/user.bmp");
		}else if(element instanceof AlarmModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/Alarm.jpg");
		}else if(element instanceof AlarmRuleModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/Alarm.jpg");
		}else if(element instanceof AlarmTacticsModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/AlarmTactics.jpg");
		}else if(element instanceof AlarmLogModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/AlarmLog.jpg");
		}else if(element instanceof MonitorSetUpModel){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/monitorsetup.bmp");
		}else if(element instanceof TableDutyModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/TableDuty.jpg");
		}
		return null;
	}

		@Override
	    public String getText(Object element) { 
		// TODO Auto-generated method stub
		if(element instanceof SiteViewEcc ){
			return ((SiteViewEcc) element).getName();
		}else if (element instanceof GroupModle) {
			GroupModle m = (GroupModle) element;
			BusinessObject bo=m.getBo();
			return bo.GetField("GroupName").get_NativeValue().toString();
		}else if(element instanceof MachineModle){
			MachineModle machine=(MachineModle)element;
			BusinessObject bo=machine.getBo();
			return bo.GetField("ServerAddress").get_NativeValue().toString();
		}else if(element instanceof SetUpModle){
			return ((SetUpModle) element).getName();
		}else if(element instanceof UserManageModle){
			return ((UserManageModle) element).getName();
		}else if(element instanceof AlarmModle){
			return ((AlarmModle) element).getName();
		}else if(element instanceof AlarmRuleModle){
			return ((AlarmRuleModle) element).getName();
		}else if(element instanceof AlarmTacticsModle){
			return ((AlarmTacticsModle) element).getName();
		}else if(element instanceof AlarmLogModle){
			return ((AlarmLogModle) element).getName();
		}else if(element instanceof MonitorSetUpModel){
			return ((MonitorSetUpModel) element).getName();
		}else if(element instanceof TableDutyModle){
			return ((TableDutyModle) element).getName();
		}
		return null;
	}

		@Override
		public void addListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			super.addListener(listener);
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			super.dispose();
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			// TODO Auto-generated method stub
			return super.isLabelProperty(element, property);
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			super.removeListener(listener);
		}

		@Override
		protected void fireLabelProviderChanged(LabelProviderChangedEvent event) {
			// TODO Auto-generated method stub
			super.fireLabelProviderChanged(event);
		}
		
}
