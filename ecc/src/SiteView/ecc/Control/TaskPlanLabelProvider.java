package SiteView.ecc.Control;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import siteview.windows.forms.ImageHelper;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.TaskModel;

public class TaskPlanLabelProvider extends LabelProvider implements ITableLabelProvider {


	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if(columnIndex==0){
			return ImageHelper.LoadImage(Activator.PLUGIN_ID, "icons/taskPlan.jpg");
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof TaskModel){
			TaskModel task=(TaskModel) element;
			if(columnIndex==0){
				return task.getName();
			}else if(columnIndex==1){
				return task.getInstruction();
			}
		}
		return null;
	}

}
