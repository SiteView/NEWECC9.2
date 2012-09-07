package SiteView.ecc.Control;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import siteview.windows.forms.ImageHelper;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.TableModle;



public class TableDutyLabelProvider extends LabelProvider implements ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex==3){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/edit.jpg");
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		if(element instanceof TableModle){
			TableModle tm=(TableModle) element;
			if(columnIndex==0){
				return tm.getDutyTableName();
			}else if(columnIndex==1){
				return tm.getDutyTableType();
			}else if(columnIndex==2){
				return tm.getDutyTableDec();
			}
		}
		return null;
	}

	
}
