package SiteView.ecc.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;

import SiteView.ecc.Control.GroupTreeContentProvider;
import SiteView.ecc.Control.GroupTreeLabelProvider;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.view.EccTreeControl;

public class MonitorSelect extends Dialog {
	public Shell parentShell;
	private TreeViewer treeViewer;
	private String butName;
	public static Tree tree;
	
	protected MonitorSelect(Shell parentShell,String butName) {
		super(parentShell);
		this.parentShell=parentShell;
		this.butName = butName;
	}
	
	protected void configureShell(Shell newShell) {
		newShell.setSize(480, 480);
		newShell.setLocation(200, 175);
		newShell.setText("检测器选择");
		super.configureShell(newShell);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite=(Composite)super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		treeViewer = new TreeViewer(composite, SWT.BORDER | SWT.CHECK | SWT.V_SCROLL);
		tree = treeViewer.getTree();
		tree.setBackground(EccTreeControl.color);
		treeViewer.setContentProvider(new GroupTreeContentProvider());
		treeViewer.setLabelProvider(new GroupTreeLabelProvider());
		treeViewer.setInput(SiteViewData.CreatTreeData());
		treeViewer.expandAll();
		TreeItem[] treeItem = tree.getItems();
		scanAllItems(treeItem,butName);
		return composite;
	}
	
	private void scanAllItems(TreeItem[] treeItem,String butName){
		for(int i=0;i<treeItem.length;i++){
			Object obj = treeItem[i].getData();
			if(obj instanceof SiteViewEcc){
				TreeItem[] item = treeItem[i].getItems();
				scanAllItems(item,butName);
			}else if(obj instanceof GroupModle){
				TreeItem[] item = treeItem[i].getItems();
				scanAllItems(item,butName);
			}else if(obj instanceof MachineModle){
				if("所属组".equals(butName)){
					treeItem[i].dispose();
				}else{
					TreeItem[] item = treeItem[i].getItems();
					scanAllItems(item,butName);
				}
			}else{
				if("所属组".equals(butName)||"所属设备".equals(butName)){
					treeItem[i].dispose();
				}
			}
		}
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button  button1= createButton(parent, IDialogConstants.OK_ID, "确定", true);
		Button  button2= createButton(parent, IDialogConstants.CANCEL_ID, "取消", true);
		
	}
	
	protected void buttonPressed(int buttonId) {
		if(buttonId==IDialogConstants.OK_ID){
			
		}else{
			this.close();
		}
	}
}
