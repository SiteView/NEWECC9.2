package SiteView.ecc.editors;

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
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
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
 * µã»÷×ó±ßÊ÷ Õ¹¿ªÔÚÓÒ±ßµÄ editor ¼à²âÆ÷ÁÐ±í
 * 
 * @author Administrator
 * 
 */

public class EccControl extends EditorPart {
	public EccControl() {
	}

	public static final String ID = "SiteView.ecc.editors.EccControl";
	public static TableItem item = null;
	public BusinessObject bo1 = null;
	public static Table toptable;
	static TabFolder tabFolder;
	public List<BusinessObject> list;
	public Composite c_1;
	public static Composite c1;
	Color[] color;

	public void createPartControl(Composite parent) {
		list=new  ArrayList<BusinessObject>();
		if (parent.getChildren().length > 0) {
			for (Control control : parent.getChildren()) {
				control.dispose();
			}
		}
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		final SashForm sashForm = new SashForm(parent, SWT.BORDER);
		sashForm.setOrientation(SWT.VERTICAL);
		Label lable = new Label(sashForm, SWT.NONE);
		lable.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lable.setText("¼à²âÆ÷");
		Composite c = new Composite(sashForm, SWT.NONE);
		c.setVisible(true);
		c.setLayout(new FillLayout());
		createTable(c);
		c1 = new Composite(sashForm, SWT.NONE);
		c1.setLayout(new FillLayout());
		tab(bo1);
		toptable.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				item = toptable.getItem(new Point(e.x, e.y));
				if (e.button == 3) {
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
					BusinessObject bo = (BusinessObject) item.getData();
					item.setChecked(!item.getChecked());
					if (bo != null) {
						tab(bo);
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		sashForm.setWeights(new int[] { 10, 157, 289 });
		parent.layout();
	}

	// ¼à²âÆ÷ÁÐ±í
	public void createTable(Composite c) {
		if (toptable != null && !toptable.isDisposed()) {
			toptable.dispose();
		}
		toptable = new Table(c, SWT.FULL_SELECTION|SWT.CHECK);
		toptable.setLinesVisible(true);
		toptable.setHeaderVisible(true);
		toptable.setBackground(new Color(null, 255, 255, 255));

        
		TableColumn tblclmnNewColumn = new TableColumn(toptable, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("ÊÇ·ñ½ûÖ¹");

		TableColumn newColumnTableColumn_top = new TableColumn(toptable,
				SWT.NONE);
		newColumnTableColumn_top.setWidth(100);
		newColumnTableColumn_top.setText("×´Ì¬");
		TableColumn newColumnTableColumn_top2 = new TableColumn(toptable,
				SWT.NONE);
		newColumnTableColumn_top2.setWidth(100);
		newColumnTableColumn_top2.setText("Ãû³Æ");
		TableColumn newColumnTableColumn_top3 =new TableColumn(toptable,
				SWT.NONE);
		newColumnTableColumn_top3.setWidth(100);
		newColumnTableColumn_top3.setText("ÃèÊö");
		TableColumn newColumnTableColumn_top4 = new TableColumn(toptable,
				SWT.NONE);
		newColumnTableColumn_top4.setWidth(100);
		newColumnTableColumn_top4.setText("×îºó¸üÐÂ");
		createTableItem();
	}

	public void createTableItem() {
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
		}
		int i = 0;
		if (ienum != null) {
			while (ienum.MoveNext()) {
				BusinessObject bo = (BusinessObject) ienum.get_Current();
				if (flag) {
					if (bo.GetField("Machine").get_NativeValue().toString() != null
							&& !bo.GetField("Machine").get_NativeValue().toString().equals("")) {
						break;
					}
				}
				BusinessObject bodyn = EccTreeControl.CreateBo("monitorid",
						bo.get_RecId(), "EccDyn");
				String[] data = new String[5];
				if (bo1 == null) {
					bo1 = bo;
				}
				if (bodyn == null) {
					String disable=bo.GetField("disable").get_NativeValue()
							.toString();
							if("true".equals(disable)){
								data[0]="½ûÖ¹";
							}else{
								data[0]="ÔÊÐí";
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
								data[0]="½ûÖ¹";
							}else{
								data[0]="ÔÊÐí";
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
				if (i == 0) {
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

	// ½âÎö×Ö·û´®
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

	// ½¨Á¢ÏÂ±ßtabÒ³
	public static void tab(BusinessObject bo) {
		if (tabFolder != null && !tabFolder.isDisposed()) {
			tabFolder.dispose();
		}
		tabFolder = new TabFolder(c1, SWT.FULL_SELECTION);
		String time = MonitorLogTabView.getHoursAgoTime(2);
		TotalTabView.startTime = time.substring(time.indexOf("*") + 1);
		TotalTabView.endTime = time.substring(0, time.indexOf("*"));
		if (bo != null) {
			TotalTabView.setTotalData(bo);
			TabItem comaTabItem = new TabItem(tabFolder, SWT.NONE);
			comaTabItem.setText("¸ÅÒª");
			Composite c = new Composite(tabFolder, SWT.FULL_SELECTION);
			EccReportView erv = new EccReportView();
			erv.createPartControl(c);
			comaTabItem.setControl(c);

			TabItem comaTabItem_2 = new TabItem(tabFolder, SWT.NONE);
			comaTabItem_2.setText("Ïà¹Ø¼à²âÆ÷");
			Composite c2 = new Composite(tabFolder, SWT.FULL_SELECTION);
			RelativelyMonitor.bo = bo;
			RelativelyMonitor mo = new RelativelyMonitor(c2);
			mo.createView(c2);
			comaTabItem_2.setControl(c2);

			TabItem comaTabItem_3 = new TabItem(tabFolder, SWT.NONE);
			comaTabItem_3.setText("ÈÕÖ¾Êý¾Ý");
			Composite c3 = new Composite(tabFolder, SWT.FULL_SELECTION);
			MonitorLogTabView molog = new MonitorLogTabView(c3);
			MonitorLogTabView.SetData(bo);
			molog.createView(c3);
			comaTabItem_3.setControl(c3);
		}
		c1.layout();
	}

	public void doSave(IProgressMonitor arg0) {
	}

	public void doSaveAs() {
	}

	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);// ÉèÖÃsite
		this.setInput(input);// ÉèÖÃÊäÈëµÄIEditorInput¶ÔÏó
		this.setPartName(input.getName());// ÉèÖÃ±à¼­Æ÷ÉÏ·½ÏÔÊ¾µÄÃû³Æ
		color = new Color[5];
		color[0] = new Color(null, 0, 153, 255);
		color[1] = new Color(null, 255, 50, 10);
		color[2] = new Color(null, 255, 255, 136);
		color[3] = new Color(null, 0, 255, 0);
		color[4] = new Color(null, 255, 170, 102);
	}

	// ±íÖÐ¼à²âÆ÷ÓÒ»÷Menu
	public Menu getMenu(Table tableItem) {
		Menu menu = new Menu(tableItem);
		MenuItem m1 = new MenuItem(menu, SWT.NONE);
		m1.setText("±à¼­¼à²âÆ÷");
		MenuItem m2 = new MenuItem(menu, SWT.NONE);
		m2.setText("ÏêÏ¸ÐÅÏ¢");
		MenuItem m3 = new MenuItem(menu, SWT.NONE);
		m3.setText("Ë¢ÐÂ¼à²âÆ÷");
		MenuItem m4 = new MenuItem(menu, SWT.NONE);
		m4.setText("É¾³ý¼à²âÆ÷");
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
				BusinessObject bo = EccTreeControl.CreateBo("monitorid",
						monitorid, "EccDyn");
				String[] s = new String[4];
				if(bo!=null){
					s[0] = bo.GetField("category").get_NativeValue().toString();
					s[1] = monitor.GetField("Title").get_NativeValue().toString();
					String desc = bo.GetField("monitorDesc").get_NativeValue()
							.toString();
					s[2] = format(desc, monitor.GetField("EccType")
							.get_NativeValue().toString());
					s[3] = bo.GetField("LastModDateTime").get_NativeValue()
							.toString();
				}else{
					s[0] = "no data";
					s[1] = bo.GetField("title").get_NativeValue().toString();
					s[2] = "has no logs";
					s[3] = bo.GetField("LastModDateTime").get_NativeValue()
							.toString();
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
			if(s.equals("½ûÖ¹")){
				bo=(BusinessObject) tab.getData();
				bo.GetField("disable").SetValue(new SiteviewValue(true));
				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true, true);
				tab.setText(0, s);
				tab.setData(bo);
			}else if(s.equals("É¾³ý")){
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
						tab.setText(0, "½ûÖ¹");
					}else{
						tab.setText(0, "ÔÊÐí");
					}
					tab.setData(bo);
				}
			}
		}
	}
}
