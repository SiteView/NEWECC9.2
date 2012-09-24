package SiteView.ecc.Control;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import SiteView.ecc.Modle.AlarmModle;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorSetUpModel;
import SiteView.ecc.Modle.SetUpModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.Modle.TaskPlanModel;

public class GroupTreeContentProvider implements ITreeContentProvider{

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return ((List) inputElement).toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof SiteViewEcc){
			return ((SiteViewEcc) parentElement).getList().toArray();
		}else if(parentElement instanceof GroupModle) { 	
			GroupModle category = (GroupModle)parentElement;
			List<Object> list=new ArrayList();
			list.addAll(category.getGroups());
			list.addAll(category.getMachines());
			list.addAll(category.getMonitors());
			return list.toArray();
	     }else if(parentElement instanceof MachineModle){
	    	 MachineModle m=(MachineModle) parentElement;
	    	return m.getMonitors().toArray();
	     }
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasChildren(Object element) {
		if(element instanceof SiteViewEcc){
			SiteViewEcc site=(SiteViewEcc) element;
			if(site.getList().size()>0){
				return true;
			}
		}else if(element instanceof GroupModle){
			GroupModle group=(GroupModle) element;
			if(group.getGroups().size()>0||group.getMachines().size()>0||group.getMonitors().size()>0){
				return true;
			}
		}else if(element instanceof MachineModle){
			if(((MachineModle)element).getMonitors()!=null&&((MachineModle)element).getMonitors().size()>0){
				return true;
			}
		}
		return false;
	}

}
