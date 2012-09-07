package SiteView.ecc.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class TableDutyInput implements IEditorInput{
	private String name="";
	@Override
	public Object getAdapter(Class adapter) {

		return null;
	}

	@Override
	public boolean exists() {
		
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		
		return null;
	}

	@Override
	public String getName() {
		
		return "÷µ∞‡±Ì…Ë÷√";
	}

	@Override
	public IPersistableElement getPersistable() {
		
		return null;
	}

	@Override
	public String getToolTipText() {
		
		return "";
	}
	public boolean equals(Object obj){
		if(null == obj) return false;
		
		if(!(obj instanceof TableDutyInput)) return false;
		
		if(!getName().equals(((TableDutyInput)obj).getName())) return false;
		return true;
	}
}
