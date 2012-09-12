package SiteView.ecc.bundle;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import Siteview.Api.BusinessObject;


import siteview.IAutoTaskExtension;

public class AddMonitorBundle implements IAutoTaskExtension {
	public AddMonitorBundle() {
	}
	public String run(Map<String, Object> params) {
		BusinessObject bo = (BusinessObject) params.get("_CUROBJ_");
		String groupId=bo.GetFieldOrSubfield("Groups_valid").get_NativeValue().toString();
		EditGroupBundle edit=new EditGroupBundle();
		edit.updateGroup("GroupId="+groupId);
		return null;
	}
	@Override
	public boolean hasCustomUI() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void creatConfigUI(Composite parent, Map<String, String> params) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, String> getConfig() {
		// TODO Auto-generated method stub
		return null;
	}
}
