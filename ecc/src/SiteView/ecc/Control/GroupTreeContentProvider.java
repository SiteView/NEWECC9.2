package SiteView.ecc.Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import SiteView.ecc.Modle.AlarmModle;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.Modle.MonitorSetUpModel;
import SiteView.ecc.Modle.SetUpModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.Modle.TaskPlanModel;

public class GroupTreeContentProvider implements ITreeContentProvider{
	private Map<String,List<Object>> map=new HashMap<String,List<Object>>();
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
		}else if(parentElement instanceof GroupModle ) { 	
			GroupModle category = (GroupModle)parentElement;
			List<Object> list=new ArrayList();
			if(getMonitorCount_1(category.getGroups(), new ArrayList<Object>()).size()>0){
				list.addAll(category.getGroups());
			}
			list.addAll(category.getMonitors());
			if(getMonitorCount((List)category.getMachines()).size()>0){
				list.addAll(getMonitorCount((List)category.getMachines()));
			}
			
			return list.toArray();
	     }else if(parentElement instanceof MachineModle &&((MachineModle)parentElement).getMonitors().size()>0){
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
	public List<MachineModle> getMonitorCount(List<MachineModle> o){
		List<MachineModle> list=new ArrayList<MachineModle>();
		for(int i=0;i<o.size();i++){
			if(o.get(i) instanceof MachineModle){
				if(((MachineModle)o.get(i)).getMonitors().size()>0){
					list.add(o.get(i));
				}
			}
		}
		return list;
	}
	public List<Object> getMonitorCount_1(List<GroupModle> group,List<Object> o){
		List list=new ArrayList<GroupModle>();
		for(int i=0;i<group.size();i++){
			GroupModle groupm=group.get(i);
			o.add(getMonitorCount(groupm.getMachines()));
			o.add(groupm.getMonitors());
			getMonitorCount_1(groupm.getGroups(), o);
			if(o.size()<=0){
				list.add(groupm);
			}
		}
		return o;
	}
}
