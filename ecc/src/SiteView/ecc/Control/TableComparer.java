package SiteView.ecc.Control;

import org.eclipse.jface.viewers.IElementComparer;

import SiteView.ecc.Modle.EmailModle;
import SiteView.ecc.Modle.SMSModel;

public class TableComparer implements IElementComparer {

	public boolean equals(Object a, Object b) {
		if(a!=null && b!=null){
			if(a instanceof EmailModle &&b instanceof EmailModle){
				EmailModle a1=(EmailModle) a;
				EmailModle b1=(EmailModle) b;
				return a1.equals(b1);
			}else if(a instanceof SMSModel &&b instanceof SMSModel){
				SMSModel a1=(SMSModel) a;
				SMSModel b1=(SMSModel) b;
				return a1.equals(b1);
			}
		}
		return false;
	}

	public int hashCode(Object element) {
		if(element instanceof EmailModle){
			return ((EmailModle)element).hashCode();
		}else if(element instanceof SMSModel){
			return ((SMSModel)element).hashCode();
		}
		return 0;
	}

}
