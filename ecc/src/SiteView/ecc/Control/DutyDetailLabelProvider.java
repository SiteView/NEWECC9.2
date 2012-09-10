package SiteView.ecc.Control;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import siteview.windows.forms.ImageHelper;

import SiteView.ecc.Activator;
import SiteView.ecc.Modle.DetailModel;


public class DutyDetailLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex==5){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/edit.jpg");
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof DetailModel){
			DetailModel dm=(DetailModel) element;
			if(columnIndex==0){
				return dm.getReceiveAlarmpPhone();
			}else if(columnIndex==1){
				return dm.getReceiveAlarmEmail();
			}else if(columnIndex==2){
				return dm.getCreatedDateTime();
			}else if(columnIndex==3){
				return dm.getStartTime();
			}else if(columnIndex==4){
				return dm.getEndTime();
			}
		}
		return null;
	}
}
