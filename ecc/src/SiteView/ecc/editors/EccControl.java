package SiteView.ecc.editors;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import COM.dragonflow.Api.APIInterfaces;
import COM.dragonflow.Api.ApiRmiServer;
import COM.dragonflow.Api.SSAlertInstanceInfo;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.bundle.EditGroupBundle;
import SiteView.ecc.data.MonitorServer;
import SiteView.ecc.dialog.ParticularInfo;
import SiteView.ecc.tab.views.MonitorLogTabView;
import SiteView.ecc.tab.views.RelativelyMonitor;
import SiteView.ecc.tab.views.TotalTabView;
import SiteView.ecc.tools.Config;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.views.EccReportView;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TabFolder;

import system.Collections.ICollection;
import system.Collections.IEnumerator;

import core.busobmaint.BusObMaintView;

/**
 * 点击左边树 展开在右边的 editor 监测器列表
 * 
 * @author Administrator
 * 
 */

public class EccControl extends EditorPart {
	public EccControl() {
	}
	public static final String ID = "SiteView.ecc.editors.EccControl";
	public static TableItem item = null;
	public static BusinessObject bo1 = null;
	public static Table toptable;
	static TabFolder tabFolder;
	public Composite c_1;
	public static Composite c1;
	static Label lable;
	public Composite c;
	static Color[] color;
	
	public void createPartControl(Composite parent) {
		bo1=null;
		if (parent.getChildren().length > 0) {
			for (Control control : parent.getChildren()) {
				control.dispose();
			}
		}
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		final SashForm sashForm = new SashForm(parent, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		lable = new Label(sashForm, SWT.NONE);
		lable.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lable.setText("子组");
		c = new Composite(sashForm, SWT.NONE);
		c.setVisible(true);
		c.setLayout(new FillLayout());
		createTable(c);
		c1 = new Composite(sashForm, SWT.NONE);
		c1.setLayout(new FillLayout());
		tab(bo1);
		sashForm.setWeights(new int[] { 10, 157, 289 });
		parent.layout();
	}

	// 监测器列表
	public static void createTable(final Composite c) {
		if(toptable != null && !toptable.isDisposed()) {
			toptable.dispose();
		}
		toptable = new Table(c, SWT.CHECK | SWT.FULL_SELECTION);
		toptable.setLinesVisible(true);
		toptable.setHeaderVisible(true);
		toptable.setBackground(new Color(null, 255, 255, 255));

		toptable.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (e.button == 3) {
					item = toptable.getItem(new Point(e.x, e.y));
					final Menu menu = getMenu(toptable);
					menu.setLocation(toptable.toDisplay(e.x, e.y));
					menu.setVisible(true);
				}
			}
		});
		toptable.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (item != e.item) {
					item = (TableItem) e.item;
					if(item.getData() instanceof BusinessObject){
						bo1= (BusinessObject) item.getData();
						item.setChecked(!item.getChecked());
						EccTreeControl.item=item.getData();
						if (bo1 != null) {
							tab(bo1);
						}
					}else if(item.getData() instanceof MachineModle || item.getData() instanceof GroupModle){
						EccTreeControl.item=item.getData();
						lable.setText("监测器");
						createTable(c);
						tab(bo1);
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		TableColumn tblclmnNewColumn = new TableColumn(toptable, SWT.NONE);
		tblclmnNewColumn.setWidth(120);
		tblclmnNewColumn.setText("是否禁止");
		
		if(EccTreeControl.item instanceof MachineModle || EccTreeControl.item instanceof GroupModle){
			TableColumn newColumnTableColumn_top = new TableColumn(toptable,
					SWT.NONE);
			newColumnTableColumn_top.setWidth(120);
			newColumnTableColumn_top.setText("状态");
		}
		
		TableColumn newColumnTableColumn_top2 = new TableColumn(toptable,
				SWT.NONE);
		newColumnTableColumn_top2.setWidth(120);
		newColumnTableColumn_top2.setText("名称");
		
		if(EccTreeControl.item instanceof SiteViewEcc){
			TableColumn newColumnTableColumn_1 = new TableColumn(toptable,
					SWT.NONE);
			newColumnTableColumn_1.setWidth(80);
			newColumnTableColumn_1.setText("设备数");
			TableColumn newColumnTableColumn_2 = new TableColumn(toptable,
					SWT.NONE);
			newColumnTableColumn_2.setWidth(80);
			newColumnTableColumn_2.setText("监测器数");
			
			lable.setText("子组");
		}
		TableColumn newColumnTableColumn_top3 =new TableColumn(toptable,
				SWT.NONE);
		newColumnTableColumn_top3.setWidth(150);
		newColumnTableColumn_top3.setText("描述");
		TableColumn newColumnTableColumn_top4 = new TableColumn(toptable,
				SWT.NONE);
		newColumnTableColumn_top4.setWidth(120);
		newColumnTableColumn_top4.setText("最后更新");
		createTableItem();
		c.layout();
	}

	public static void createTableItem() {
		bo1=null;
		item = null;
		if (toptable.getItemCount() > 0) {
			for (TableItem tableItem : toptable.getItems()) {
				tableItem.dispose();
			}
		}
		boolean flag = false;
		String groupid = "";
		ICollection icoll = null;
		IEnumerator ienum = null;
		if (EccTreeControl.item instanceof GroupModle) {
			groupid = ((GroupModle) EccTreeControl.item).getBo().get_RecId();
			icoll = FileTools.getBussCollection("Groups", groupid, "Ecc");
			ienum = icoll.GetEnumerator();
			flag = true;
		} else if (EccTreeControl.item instanceof MachineModle) {
			BusinessObject machienbo = ((MachineModle) EccTreeControl.item)
					.getBo();
			groupid = machienbo.GetField("Groups").get_NativeValue().toString();
			String machineId = machienbo.get_RecId();
			Map<String, String> map = new HashMap<String, String>();
			map.put("Groups", groupid);
			map.put("Machine", machineId);
			icoll = FileTools.getBussCollection(map, "Ecc");
			ienum = icoll.GetEnumerator();
		}else if(EccTreeControl.item instanceof SiteViewEcc){
			List<GroupModle> groups=((SiteViewEcc)EccTreeControl.item).getList();
			for(GroupModle group:groups){
				String[] data=new String[6];
				TableItem tableItem = new TableItem(toptable, SWT.NONE);
				tableItem.setData(group);
				data[0]="";
				data[1]=group.getBo().GetField("GroupName").get_NativeValue().toString();
				data[2]=group.getMachines().size()+"";
				data[3]=group.getMonitors().size()+"";
				data[4]=group.getBo().GetField("Description").get_NativeValue().toString();
				data[5]=group.getBo().GetField("LastModDateTime").get_NativeValue().toString();
				tableItem.setText(data);
			}
			return ;
		}
		int i = 0;
		if (ienum != null) {
			while (ienum.MoveNext()) {
				BusinessObject bo = (BusinessObject) ienum.get_Current();
				if (flag) {
					if (bo.GetField("Machine").get_NativeValue().toString() != null
							&& !bo.GetField("Machine").get_NativeValue().toString().equals("")) {
						continue;
					}
				}
				BusinessObject bodyn = EccTreeControl.CreateBo("monitorid",
						bo.get_RecId(), "EccDyn");
				String[] data = new String[5];
				if (bo1 == null&&EccTreeControl.item instanceof MachineModle) {
					bo1 = bo;
				}
				if (bodyn == null) {
					String disable=bo.GetField("disable").get_NativeValue()
							.toString();
							if("true".equals(disable)){
								data[0]="禁止";
							}else{
								data[0]="允许";
							}
					data[1] = "no data";
					data[2] = bo.GetField("title").get_NativeValue().toString();
					data[3] = "has no logs";
					data[4] = bo.GetField("LastModDateTime").get_NativeValue()
							.toString();
				} else {
					String disable=bo.GetField("disable").get_NativeValue()
							.toString();
							if("true".equals(disable)){
								data[0]="禁止";
							}else{
								data[0]="允许";
							}
					data[1] = bodyn.GetField("category").get_NativeValue()
							.toString();
					data[2] = bo.GetField("title").get_NativeValue().toString();
					String desc = bodyn.GetField("monitorDesc")
							.get_NativeValue().toString();
					data[3] = format(desc, bo.GetField("EccType")
							.get_NativeValue().toString());
					data[4] = bodyn.GetField("LastModDateTime")
							.get_NativeValue().toString();
				}
				TableItem tableItem = new TableItem(toptable, SWT.NONE);
				tableItem.setData(bo);
				tableItem.setText(data);
				if (i == 0&&EccTreeControl.item instanceof MachineModle) {
					item = tableItem;
				}
				i++;
				if (data[1].equals("good")) {
					tableItem.setBackground(color[3]);
				} else if (data[1].equals("error")) {
					tableItem.setBackground(color[1]);
				} else if (data[1].equals("warning")) {
					tableItem.setBackground(color[2]);
				} else if (data[1].equals("disable")) {
					tableItem.setBackground(color[4]);
				} else {
					tableItem.setBackground(color[0]);
				}
			}
		}
	}

	// 解析字符串
	public static String format(String desc2, String type) {
		desc2 += "*";
		String filePath = FileTools
				.getRealPath("\\files\\siteview9.2_itsm.properties");
		String s1 = Config.getReturnStr(filePath, "Ecc." + type);
		if (s1 == null) {
			return desc2;
		}
		String[] s2 = s1.split(",");
		s1 = "";
		for (int i = 0; i < s2.length; i++) {
			String s0 = s2[i].substring(s2[i].indexOf(":") + 1);
			if (desc2.contains(s0 + "=")) {
				String s = desc2.substring(desc2.indexOf(s0 + "="));
				s = s.substring(s.indexOf("=") + 1, s.indexOf("*"));
				s1 += s2[i].substring(0, s2[i].indexOf(":")) + "=" + s + " ";
			}
		}
		return s1;
	}

	// 建立下边tab页
	public static void tab(BusinessObject bo) {
		for(Control c:c1.getChildren()){
			c.dispose();
		}
		if(EccTreeControl.item instanceof GroupModle){
			createTable_1();
			return;
		}
		if (bo != null) {
			tabFolder = new TabFolder(c1, SWT.FULL_SELECTION);
			String time = MonitorLogTabView.getHoursAgoTime(2);
			TotalTabView.startTime = time.substring(time.indexOf("*") + 1);
			TotalTabView.endTime = time.substring(0, time.indexOf("*"));
			TotalTabView.setTotalData(bo);
			TabItem comaTabItem = new TabItem(tabFolder, SWT.NONE);
			comaTabItem.setText("概要");
			Composite c = new Composite(tabFolder, SWT.FULL_SELECTION);
			EccReportView erv = new EccReportView();
			erv.createPartControl(c);
			comaTabItem.setControl(c);

			TabItem comaTabItem_2 = new TabItem(tabFolder, SWT.NONE);
			comaTabItem_2.setText("相关监测器");
			Composite c2 = new Composite(tabFolder, SWT.FULL_SELECTION);
			RelativelyMonitor.bo = bo;
			RelativelyMonitor mo = new RelativelyMonitor(c2);
			mo.createView(c2);
			comaTabItem_2.setControl(c2);

			TabItem comaTabItem_3 = new TabItem(tabFolder, SWT.NONE);
			comaTabItem_3.setText("日志数据");
			Composite c3 = new Composite(tabFolder, SWT.FULL_SELECTION);
			MonitorLogTabView molog = new MonitorLogTabView(c3);
			MonitorLogTabView.SetData(bo);
			molog.createView(c3);
			comaTabItem_3.setControl(c3);
		}else{
			c1.setBackground(EccTreeControl.color);
		}
		c1.layout();
	}

	private static  void createTable_1() {
		c1.setBackground(EccTreeControl.color);
		c1.setLayout(new FillLayout());
		SashForm sa=new SashForm(c1, SWT.VERTICAL);
		sa.setLayout(new FillLayout());
		
		Label label_1=new Label(sa, SWT.VERTICAL);
		label_1.setText("子组");
		label_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		Table table_1=new Table(sa,SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBackground(EccTreeControl.color);
		
		TableColumn tableColumn_1 = new TableColumn(table_1,
				SWT.NONE);
		tableColumn_1.setWidth(120);
		tableColumn_1.setText("组名");
		
		TableColumn tableColumn_2 = new TableColumn(table_1,
				SWT.NONE);
		tableColumn_2.setWidth(120);
		tableColumn_2.setText("设备数");
		
		TableColumn tableColumn_3 = new TableColumn(table_1,
				SWT.NONE);
		tableColumn_3.setWidth(120);
		tableColumn_3.setText("子组数");
		
		TableColumn tableColumn_4 = new TableColumn(table_1,
				SWT.NONE);
		tableColumn_4.setWidth(120);
		tableColumn_4.setText("描述信息");
		
		TableColumn tableColumn_5 = new TableColumn(table_1,
				SWT.NONE);
		tableColumn_5.setWidth(120);
		tableColumn_5.setText("上次修改时间");
		
		table_1.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				item = (TableItem) e.item;
				if(item.getData() instanceof GroupModle){
					EccTreeControl.item=item.getData();
					lable.setText("监测器");
					IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
					IEditorPart editor = page.findEditor(EccTreeControl.eee);
					((EccControl)editor).createTable(((EccControl)editor).c);
					tab(((EccControl)editor).bo1);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		for(GroupModle group:((GroupModle)EccTreeControl.item).getGroups()){
			TableItem t=new TableItem(table_1, SWT.NONE);
			String [] data=new String[5];
			data[0]=group.getBo().GetField("GroupName").get_NativeValue().toString();
			data[1]=group.getMachines().size()+"";
			data[2]=group.getGroups().size()+"";
			data[3]=group.getBo().GetField("Description").get_NativeValue().toString();
			data[4]=group.getBo().GetField("LastModDateTime").get_NativeValue().toString();
			t.setData(group);
			t.setText(data);
		}
		
		Label label=new Label(sa, SWT.VERTICAL);
		label.setText("设备");
		label.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		Table table=new Table(sa,SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBackground(EccTreeControl.color);
		
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				item = (TableItem) e.item;
				if(item.getData() instanceof MachineModle){
					EccTreeControl.item=item.getData();
					IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
					IEditorPart editor = page.findEditor(EccTreeControl.eee);
					((EccControl)editor).createTable(((EccControl)editor).c);
					tab(((EccControl)editor).bo1);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		TableColumn tableItem_1 = new TableColumn(table,SWT.NONE);
		tableItem_1.setWidth(120);
		tableItem_1.setText("状态");
		
		TableColumn tableItem_2 = new TableColumn(table,SWT.NONE);
		tableItem_2.setWidth(120);
		tableItem_2.setText("名称");
		
		TableColumn tableItem_3 = new TableColumn(table,SWT.NONE);
		tableItem_3.setWidth(120);
		tableItem_3.setText("ip地址");
		
		TableColumn tableItem_4 = new TableColumn(table,SWT.NONE);
		tableItem_4.setWidth(120);
		tableItem_4.setText("设备类型");
		
		TableColumn tableItem_5 = new TableColumn(table,SWT.NONE);
		tableItem_5.setWidth(120);
		tableItem_5.setText("监测器");
		
		createItem_1(table);
		sa.setWeights(new int[]{1,10,1,10});
		c1.layout();
	}

	private static void createItem_1(Table table) {
		List<MachineModle> machines=((GroupModle) EccTreeControl.item).getMachines();
		for(MachineModle machine:machines){
			String[] data=new String[5];
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setData(machine);
			data[0]=machine.getBo().GetField("Status").get_NativeValue().toString().contains("successful")?"good":"error";
			data[1]=machine.getBo().GetField("title").get_NativeValue().toString()==null?machine.getBo().GetField("ServerAddress").get_NativeValue().toString():machine.getBo().GetField("title").get_NativeValue().toString();
			data[2]=machine.getBo().GetField("ServerAddress").get_NativeValue().toString();
			data[3]=machine.getBo().GetField("RemoteMachineType").get_NativeValue().toString().equals("RemoteNT")?"windows设备":machine.getBo().GetField("RemoteMachineType").get_NativeValue().toString().equals("RemoteEquipment")?"网络设备":"linux设备";
			data[4]=machine.getMonitors().size()+"";
			tableItem.setText(data);
		}
	}

	public void doSave(IProgressMonitor arg0) {
	}

	public void doSaveAs() {
	}

	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);// 设置site
		this.setInput(input);// 设置输入的IEditorInput对象
		this.setPartName(input.getName());// 设置编辑器上方显示的名称
		color = new Color[5];
		color[0] = new Color(null, 0, 153, 255);
		color[1] = new Color(null, 255, 50, 10);
		color[2] = new Color(null, 255, 255, 136);
		color[3] = new Color(null, 0, 255, 0);
		color[4] = new Color(null, 255, 170, 102);
	}

	// 表中监测器右击Menu
	public static Menu getMenu(Table tableItem) {
		Menu menu = new Menu(tableItem);
		MenuItem m1 = new MenuItem(menu, SWT.NONE);
		m1.setText("编辑监测器");
		MenuItem m2 = new MenuItem(menu, SWT.NONE);
		m2.setText("详细信息");
		MenuItem m3 = new MenuItem(menu, SWT.NONE);
		m3.setText("刷新监测器");
		MenuItem m4 = new MenuItem(menu, SWT.NONE);
		m4.setText("删除监测器");
		if(EccTreeControl.item instanceof GroupModle){
			m1.setEnabled(((GroupModle) EccTreeControl.item).isEditmonitor());
			m4.setEnabled(((GroupModle) EccTreeControl.item).isDeleteMonitor());
		}else if(EccTreeControl.item instanceof MachineModle){
			m1.setEnabled(((MachineModle) EccTreeControl.item).isEditMonitor());
			m4.setEnabled(((MachineModle) EccTreeControl.item).isDeleteMonitor());
		}
		m1.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				BusinessObject bo=(BusinessObject) item.getData();
//				bo.GetField("Groups").get_Definition().set_ReadOnlyRule(new );
				BusObMaintView.open(ConnectionBroker.get_SiteviewApi(),
						(BusinessObject) item.getData());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		m2.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				ParticularInfo par = new ParticularInfo(null);
				par.open();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		m3.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				TableItem itable = item;
				BusinessObject monitor = (BusinessObject) item.getData();
				String monitorid = monitor.get_RecId();
				APIInterfaces rmiServer=EditGroupBundle.createAmiServer();
				try {
					rmiServer.Refresh(monitorid,monitor.GetField("Groups").get_NativeValue().toString());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				BusinessObject bo = EccTreeControl.CreateBo("monitorid",
						monitorid, "EccDyn");
				String[] s = new String[5];
				if(bo!=null){
					s[1] = bo.GetField("category").get_NativeValue().toString();
					String desc = bo.GetField("monitorDesc").get_NativeValue().toString();
					s[3] = format(desc, monitor.GetField("EccType").get_NativeValue().toString());
					s[4] = bo.GetField("LastModDateTime").get_NativeValue().toString();
				}else{
					s[1] = "no data";
					s[3] = "has no logs";
					s[4] = monitor.GetField("LastModDateTime").get_NativeValue().toString();
				}
				s[0] = (Boolean)monitor.GetField("disable").get_NativeValue()?"禁止":"允许";
				s[2] = monitor.GetField("title").get_NativeValue().toString();
				if (s[1].equals("good")) {
					itable.setBackground(color[3]);
				} else if (s[1].equals("error")) {
					itable.setBackground(color[1]);
				} else if (s[1].equals("warning")) {
					itable.setBackground(color[2]);
				} else if (s[1].equals("disable")) {
					itable.setBackground(color[4]);
				} else {
					itable.setBackground(color[0]);
				}
				itable.setText(s);
				tab((BusinessObject) item.getData());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		m4.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				BusinessObject bo = (BusinessObject) EccControl.item.getData();
				bo.DeleteObject(ConnectionBroker.get_SiteviewApi());
				String groupId = bo.GetFieldOrSubfield("Groups_valid")
						.get_NativeValue().toString();
				EditGroupBundle edit = new EditGroupBundle();
				edit.updateGroup("GroupId=" + groupId);
				TableItem tablei = EccControl.item;
				Table toptable = tablei.getParent();
				if (toptable.getItemCount() > 1) {
					EccControl.item = (TableItem) toptable.getItem(0);
					toptable.select(0);
					EccControl.tab((BusinessObject) EccControl.item.getData());
				} else {
					EccControl.c1.dispose();
				}
				tablei.dispose();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		return menu;
	}

	public boolean isDirty() {
		return false;
	}

	public boolean isSaveAsAllowed() {
		return false;
	}

	public void setFocus() {
	}
	public static void getSelete(boolean flg,String s){
		for(TableItem tab:toptable.getItems()){
			BusinessObject bo;
			if(s.equals("禁止")){
				bo=(BusinessObject) tab.getData();
				bo.GetField("disable").SetValue(new SiteviewValue(true));
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				tab.setText(0, s);
				tab.setData(bo);
			}else if(s.equals("删除")){
				if(tab.getChecked()){
					bo=(BusinessObject) tab.getData();
					bo.DeleteObject(ConnectionBroker.get_SiteviewApi());
					tab.dispose();
					tab(null);
				}
			}else if(s.equals("")){
				if(tab.getChecked()){
					bo=(BusinessObject) tab.getData();
					bo.GetField("disable").SetValue(new SiteviewValue(flg));
					bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
					if(flg){
						tab.setText(0, "禁止");
					}else{
						tab.setText(0, "允许");
					}
					tab.setData(bo);
				}
			}
		}
	}
}
