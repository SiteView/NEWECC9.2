package SiteView.ecc.Control;

import org.apache.xml.utils.res.IntArrayWrapper;
import org.eclipse.jface.viewers.IElementComparer;

import SiteView.ecc.Modle.EmailModle;

public class TableComparer implements IElementComparer {

	@Override
	public boolean equals(Object a, Object b) {
		// TODO Auto-generated method stub
		if(a!=null && b!=null){
			if(a instanceof EmailModle &&b instanceof EmailModle){
				EmailModle a1=(EmailModle) a;
				EmailModle b1=(EmailModle) b;
				return a1.equals(b1);
			}
		}
		return false;
	}

	@Override
	public int hashCode(Object element) {
		// TODO Auto-generated method stub
		if(element instanceof EmailModle){
			return ((EmailModle)element).hashCode();
		}
		return 0;
	}

}
