package SiteView.ecc.dialog;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import SiteView.ecc.Control.GroupTreeContentProvider;
import SiteView.ecc.Control.GroupTreeLabelProvider;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.view.EccTreeControl;
import Siteview.Api.BusinessObject;

public class MonitorSelect extends Dialog {
	public Shell parentShell;
	private TreeViewer treeViewer;
	private String butName;
	public static Tree tree;
	private Set<BusinessObject> monitorSet = new HashSet<BusinessObject>();
	private Set<BusinessObject> machineSet = new HashSet<BusinessObject>();
	private Set<BusinessObject> groupSet = new HashSet<BusinessObject>();
	private CreateFilterCondition cfc;
	
	protected MonitorSelect(Shell parentShell,String butName,CreateFilterCondition cfc) {
		super(parentShell);
		this.parentShell=parentShell;
		this.butName = butName;
		this.cfc = cfc;
	}
	
	protected void configureShell(Shell newShell) {
		newShell.setSize(480, 480);
		newShell.setLocation(200, 175);
		newShell.setText(butName);
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
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				TreeItem item = tree.getItem(new Point(e.x,e.y));
				if(item.getChecked()){
					if("监测器选择器".equals(butName)){
						if(item.getData() instanceof MonitorModle){
							monitorSet.add(((MonitorModle)item.getData()).getBo());
						}
					}else if("设备选择器".equals(butName)){
                        if(item.getData() instanceof MachineModle){
                        	machineSet.add(((MachineModle)item.getData()).getBo());
						}
					}else if("组选择器".equals(butName)){
                        if(item.getData() instanceof GroupModle){
                        	groupSet.add(((GroupModle)item.getData()).getBo());
						}
					}
				}
			}
		});
		return composite;
	}
	/**
	 * 遍历整个树，去除不需要的节点
	 * */
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
				if("组选择器".equals(butName)){
					treeItem[i].dispose();
				}else{
					TreeItem[] item = treeItem[i].getItems();
					scanAllItems(item,butName);
				}
			}else{
				if("组选择器".equals(butName)||"设备选择器".equals(butName)){
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
			if("监测器选择器".equals(butName)){
				StringBuffer nameSb = new StringBuffer();
				StringBuffer recIdSb = new StringBuffer();
				Iterator<BusinessObject> it = monitorSet.iterator();
				while(it.hasNext()){
					BusinessObject bo = it.next();
					nameSb.append(bo.GetField("title").get_NativeValue().toString());
					nameSb.append(";");
					recIdSb.append(bo.GetField("RecId").get_NativeValue().toString());
					recIdSb.append(";");
				}
				String s1 = nameSb.toString();
				cfc.getText_1().setText(s1.substring(0,s1.length()-1));
				String s2 = recIdSb.toString();
				cfc.setMonitorId(s2.substring(0,s2.length()-1));
				this.close();
			}else if("设备选择器".equals(butName)){
				StringBuffer nameSb = new StringBuffer();
				StringBuffer recIdSb = new StringBuffer();
				Iterator<BusinessObject> it = machineSet.iterator();
				while(it.hasNext()){
					BusinessObject bo = it.next();
					nameSb.append(bo.GetField("ServerAddress").get_NativeValue().toString());
					nameSb.append(";");
					recIdSb.append(bo.GetField("RecId").get_NativeValue().toString());
					recIdSb.append(";");
				}
				String s1 = nameSb.toString();
				cfc.getText_2().setText(s1.substring(0,s1.length()-1));
				String s2 = recIdSb.toString();
				cfc.setMachineId(s2.substring(0,s2.length()-1));
				this.close();
			}else if("组选择器".equals(butName)){
				StringBuffer nameSb = new StringBuffer();
				StringBuffer recIdSb = new StringBuffer();
				Iterator<BusinessObject> it = groupSet.iterator();
				while(it.hasNext()){
					BusinessObject bo = it.next();
					nameSb.append(bo.GetField("GroupName").get_NativeValue().toString());
					nameSb.append(";");
					recIdSb.append(bo.GetField("RecId").get_NativeValue().toString());
					recIdSb.append(";");
				}
				String s1 = nameSb.toString();
				cfc.getText_3().setText(s1.substring(0,s1.length()-1));
				String s2 = recIdSb.toString();
				cfc.setGroupId(s2.substring(0,s2.length()-1));
				this.close();
			}
		}else{
			this.close();
		}
	}
}
