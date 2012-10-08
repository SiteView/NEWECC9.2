package SiteView.ecc.Control;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import siteview.windows.forms.ImageHelper;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.AbsoluteTimeProjectModel;
import SiteView.ecc.Modle.TableModle;


public class AbsoluteTimelabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if(columnIndex==2&&element instanceof AbsoluteTimeProjectModel){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/edit.jpg");
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof AbsoluteTimeProjectModel){
			AbsoluteTimeProjectModel atpm=(AbsoluteTimeProjectModel) element;
			if(columnIndex==0){
				//return atpm
			}else if(columnIndex==1){
				//return atpm
			}
		}
		return null;
	}


}
