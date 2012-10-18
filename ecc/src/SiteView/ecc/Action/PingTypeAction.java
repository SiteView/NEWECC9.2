package SiteView.ecc.Action;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.dialog.MonitorSetUp;
import SiteView.ecc.view.EccTreeControl;

public class PingTypeAction extends Action {
	public TreeItem item;
	public Table table=MonitorSetUp.table;
	public Tree tree=MonitorSetUp.tree;
	public Combo combo=MonitorSetUp.combo_2;
	public PingTypeAction(String name,TreeItem item){
		this.setText(name);
		this.item = item;
	}
	
	public void run() {
		Set<String> set = new HashSet<String>();
		Set<String> set1 = MonitorSetUp.selectAllId(item, set);
		Set<String> setId = new HashSet<String>();
		String eccType="";
		for (String string : set1) {
			if(EccTreeControl.CreateBo("RecId", string, "Ecc").GetField("EccType").get_NativeValue().toString().equals(this.getText())){
				setId.add(string);
				eccType=EccTreeControl.CreateBo("RecId", string, "Ecc").GetField("EccType").get_NativeValue().toString();
				select(item.getItems(),string);
			}
		}
		combo.add(eccType);
		combo.select(0);
		MonitorSetUp.createTableItem(setId);
	}
	
	public void select(TreeItem[] item,String string){
		for (TreeItem item1 : item) {
			if(item1.getData() instanceof MonitorModle){
				if(((MonitorModle)item1.getData()).getBo().get_RecId().equals(string)){
					item1.setChecked(true);
					MonitorSetUp.SelectParent(item1);
				}
			}
			if(item1.getItemCount()>0){
				select(item1.getItems(),string);
			}
		}
	}
}
