package SiteView.ecc.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class AbsoluteTimeInput implements IEditorInput {
	private String name="";
	String type;

	public AbsoluteTimeInput(String type) {
		this.type = type;
	}
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
		if("absolute".equals(type)){
			return "����ʱ������ƻ�";			
		}else if("quantum".equals(type)){
			return "ʱ�������ƻ�";	
		}else if("ralative".equals(type)){
			return "���ʱ������ƻ�";	
		}
		return null;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return "";
	}
	
	@Override  
	 public boolean equals(Object obj) {  
	    if(null == obj) return false;  
	              
	    if(!(obj instanceof AbsoluteTimeInput)) return false;  
	              
	    if(!getName().equals(((AbsoluteTimeInput)obj).getName())) return false;  
	              
	    return true;  
	  } 

}
