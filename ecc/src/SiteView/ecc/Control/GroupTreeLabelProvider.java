package SiteView.ecc.Control;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;

import siteview.windows.forms.ImageHelper;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.Modle.SiteViewEcc;
import Siteview.Api.BusinessObject;

public class GroupTreeLabelProvider extends LabelProvider{
	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
	   if(element instanceof SiteViewEcc){
		   return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/logo.jpg");
	   }else if (element instanceof GroupModle) {
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/node.jpg");
		}else if(element instanceof MachineModle&& ((MachineModle)element).getMonitors().size()>0){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/shebei.jpg");
		}else if(element instanceof MonitorModle){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/Monitor.jpg");
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
		}else if(element instanceof MachineModle && ((MachineModle)element).getMonitors().size()>0){
			MachineModle machine=(MachineModle)element;
			BusinessObject bo=machine.getBo();
			String type=bo.GetField("RemoteMachineType").get_NativeValue().toString();
			if(type.equals("RemoteNT")){
				type="(windows)";
			}else{
				type="(Unix)";
			}
			return bo.GetField("ServerAddress").get_NativeValue().toString()+type;
		}else if(element instanceof MonitorModle){
			return ((MonitorModle) element).getBo().GetField("title").get_NativeValue().toString();
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
