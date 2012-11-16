package SiteView.ecc.bundle;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.event.ConnectionAdapter;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import siteview.IAutoTaskExtension;
import siteview.windows.forms.ImageHelper;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import system.Xml.XmlElement;
import COM.dragonflow.Api.APIInterfaces;
import SiteView.ecc.Activator;
import SiteView.ecc.Control.GroupTreeContentProvider;
import SiteView.ecc.Control.GroupTreeLabelProvider;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.data.SiteViewData;
import Siteview.Operators;
import Siteview.QueryInfoToGet;
import Siteview.SiteviewQuery;
import Siteview.StringUtils;
import Siteview.Api.BusinessObject;
import Siteview.Database.DatabaseConnection;
import Siteview.Windows.Forms.ConnectionBroker;

public class RunMonitorBundle implements IAutoTaskExtension {
	private Text txt_Monitor;

	public RunMonitorBundle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String run(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		String MonitorId=params.get("MonitorId").toString();
		String GroupID=params.get("GroupID").toString();
		System.out.println(MonitorId+"~~~~~~~~"+GroupID);
		APIInterfaces rmiServer=EditGroupBundle.createAmiServer();
		try {
			rmiServer.Refresh(MonitorId,GroupID);	
			
		} catch (RemoteException e1) {
			System.out.println("监测器运行失败！");
			e1.printStackTrace();
		}
		System.out.println("监测器运行成功！");
		return "运行成功";
	}

	@Override
	public boolean hasCustomUI() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void creatConfigUI(Composite parent, Map<String, String> params) {
		// TODO Auto-generated method stub
//		parent.setLayout(new FillLayout());
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		
		TreeViewer treeViewer = new TreeViewer(composite);
		final Tree tree = treeViewer.getTree();
		FormData fd_tree = new FormData();
		fd_tree.bottom = new FormAttachment(0, 282);
		fd_tree.right = new FormAttachment(100);
		fd_tree.top = new FormAttachment(0);
		fd_tree.left = new FormAttachment(0);
		tree.setLayoutData(fd_tree);
		treeViewer.setContentProvider(new GroupTreeContentProvider());
		treeViewer.setLabelProvider(new GroupTreeLabelProvider());
		treeViewer.setInput(SiteViewData.CreatTreeData());
		treeViewer.expandToLevel(3);
		
		
//		final Tree tree = new Tree(composite, SWT.BORDER);
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem[] treeitems=tree.getSelection();
				if(treeitems.length>0){
					TreeItem treeitem=treeitems[0];
					Object object= treeitem.getData();
					if(object!=null && (object instanceof MonitorModle)){
						
						MonitorModle mm=(MonitorModle)object;
						BusinessObject bo=mm.getBo();
//						System.out.println(bo.get_Name());
						if(bo!=null){
							String name=bo.GetField("Title").get_NativeValue().toString();
							if(name!=null&&!"".equals(name.trim())){
								txt_Monitor.setText(name.trim());
								txt_Monitor.setData(bo);
								return;
							}
						}
					}
					txt_Monitor.setText("");
					txt_Monitor.setData("");
				}else{
					txt_Monitor.setText("");
					txt_Monitor.setData("");
				}
			}
		});
//		FormData fd_tree = new FormData();
//		fd_tree.left = new FormAttachment(0);
//		fd_tree.top = new FormAttachment(0);
//		fd_tree.right = new FormAttachment(0, 450);
//		tree.setLayoutData(fd_tree);
//		tree.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
//		tree.setVisible(true);
//		createTreeItem(tree);
//		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		fd_tree.bottom = new FormAttachment(lblNewLabel, -6);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(100, -5);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		fd_lblNewLabel.right = new FormAttachment(0, 100);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("\u9009\u62E9\u7684\u76D1\u6D4B\u5668:");
		
		txt_Monitor = new Text(composite, SWT.BORDER);
		if(!StringUtils.IsEmpty(params.get("MonitorName"))){
			txt_Monitor.setText(params.get("MonitorName").toString());
			txt_Monitor.setData(getSelectedMonitorBO(params.get("MonitorId")));
		}
		txt_Monitor.setEditable(false);
		txt_Monitor.setEnabled(false);
		FormData fd_txt_Monitor = new FormData();
		fd_txt_Monitor.right = new FormAttachment(100, -10);
		fd_txt_Monitor.left = new FormAttachment(lblNewLabel, 6);
		fd_txt_Monitor.bottom = new FormAttachment(100, -2);
		txt_Monitor.setLayoutData(fd_txt_Monitor);
	}
	
	
	private BusinessObject getSelectedMonitorBO(String recid){
		
		SiteviewQuery query=new SiteviewQuery();
		query.AddBusObQuery("ECC", QueryInfoToGet.All);
		XmlElement element=query.get_CriteriaBuilder().FieldAndValueExpression("RecId", Operators.Equals, recid.trim());
		query.set_BusObSearchCriteria(element);
		ICollection boColl=ConnectionBroker.get_SiteviewApi().get_BusObService().get_SimpleQueryResolver().ResolveQueryToBusObList(query);
		if(boColl!=null&&boColl.get_Count()>0){
			IEnumerator enums=boColl.GetEnumerator();
			if(enums.MoveNext()){
				return (BusinessObject) enums.get_Current();
			}
		}
		return null;
	}
	

	public static void createTreeItem(Tree tree) {
		tree.removeAll();
		
		SiteViewData si=new SiteViewData();
		si.getData();
		
		TreeItem treeItem = new TreeItem(tree, SWT.NONE | SWT.CHECK);
		treeItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		treeItem.setText("Ecc9.2");
		treeItem.setImage(ImageHelper.LoadImage(Activator.PLUGIN_ID,
				"icons/logo.jpg"));
		for(int i=0;i<SiteViewData.groups_0.size();i++){
			if(SiteViewData.groups_0.get(0) instanceof GroupModle){
				GroupModle group=SiteViewData.groups_0.get(i);
				BusinessObject bo=group.getBo();
				String s=bo.GetField("GroupName").get_NativeValue().toString();
				TreeItem treeItem1 = new TreeItem(treeItem, SWT.NONE | SWT.CHECK);
				treeItem1.setText(s);
				treeItem1.setData(bo.get_RecId());
//				String id=bo.get_RecId();
				treeItem1.setImage(ImageHelper.LoadImage(Activator.PLUGIN_ID,"icons/node.jpg"));
				createItem(group, treeItem1);
				treeItem1.setExpanded(true);
			}
		}
		treeItem.setExpanded(true);
	}
	public static void createItem(GroupModle group, TreeItem treeItem12) {
		List<GroupModle> subgroup=group.getGroups();
		for(int i=0;i<subgroup.size();i++){
			GroupModle g=subgroup.get(i);
			BusinessObject bo=g.getBo();
			String subid=bo.get_RecId();
			TreeItem treeItem2 = new TreeItem(treeItem12, SWT.NONE
					| SWT.CHECK);
			treeItem2.setText(bo.GetField("GroupName").get_NativeValue().toString());
			treeItem2.setData(subid);
			treeItem2.setImage(ImageHelper.LoadImage(Activator.PLUGIN_ID,
					"icons/node.jpg"));
			createItem(g, treeItem2);
		}
		List<MachineModle> machines=group.getMachines();
		for(int i=0;i<machines.size();i++){
			MachineModle machine=machines.get(i);
			BusinessObject bo=machine.getBo();
			TreeItem treeItem3 = new TreeItem(treeItem12, SWT.NONE
					| SWT.CHECK);
			treeItem3.setText(bo.GetField("ServerAddress")
					.get_NativeValue().toString());
			treeItem3.setData(bo.get_RecId()+"~"+group.getBo().get_RecId());
			treeItem3.setImage(ImageHelper.LoadImage(Activator.PLUGIN_ID,
					"icons/shebei.jpg"));
		}
	}

	@Override
	public Map<String, String> getConfig() {
		BusinessObject bo=(BusinessObject) txt_Monitor.getData();
		if(bo==null){
			MessageDialog.openInformation(new Shell(), "提示", "保存失败.");
			txt_Monitor.forceFocus();
			return null;
		}
		Map<String,String> map=new HashMap<String,String>();
		map.put("MonitorId",bo.GetField("RecId").get_NativeValue().toString());
		map.put("GroupID",bo.GetField("Groups").get_NativeValue().toString());
		map.put("MonitorName", txt_Monitor.getText().toString());
		return map;
	}
}
