package SiteView.ecc.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.part.ViewPart;


import system.Collections.ICollection;
import system.Collections.IEnumerator;
import system.Xml.XmlElement;
import SiteView.ecc.Activator;
import SiteView.ecc.Action.AddGroupAction;
import SiteView.ecc.Action.AllDelete;
import SiteView.ecc.Action.AllProhibitAction;
import SiteView.ecc.Action.AllStart;
import SiteView.ecc.Action.DNSAction;
import SiteView.ecc.Action.DeleteGroupAction;
import SiteView.ecc.Action.DeleteMachineAction;
import SiteView.ecc.Action.EditorGroupAction;
import SiteView.ecc.Action.EditorMachineAction;
import SiteView.ecc.Action.ProhibitAction;
import SiteView.ecc.Action.RemoteAction;
import SiteView.ecc.Action.SNMPAction;
import SiteView.ecc.Action.UnixAction;
import SiteView.ecc.Action.WindowsAction;
import SiteView.ecc.Action.pingAction;
import SiteView.ecc.Control.EccTreeComparer;
import SiteView.ecc.Control.EccTreeContentProvider;
import SiteView.ecc.Control.EccTreeLabelProvider;
import SiteView.ecc.Modle.AbsoluteTimeModel;
import SiteView.ecc.Modle.AlarmLogModle;
import SiteView.ecc.Modle.AlarmRuleModle;
import SiteView.ecc.Modle.EmailSetUpModel;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.MessageSetUpModel;
import SiteView.ecc.Modle.MonitorBrowseModle;
import SiteView.ecc.Modle.MonitorSetUpModel;
import SiteView.ecc.Modle.RelativeTimeModel;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.Modle.TableDutyModle;
import SiteView.ecc.Modle.TaskPlanModel;
import SiteView.ecc.Modle.TimeQuantumModel;
import SiteView.ecc.Modle.TrendReportModle;
import SiteView.ecc.Modle.UserManageModle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.dialog.MonitorSetUp;
import SiteView.ecc.editors.AbsoluteTime;
import SiteView.ecc.editors.AbsoluteTimeInput;
import SiteView.ecc.editors.AlarmLog;
import SiteView.ecc.editors.AlarmLogInput;
import SiteView.ecc.editors.AlarmRule;
import SiteView.ecc.editors.AlarmRuleInput;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.editors.EccControlInput;
import SiteView.ecc.editors.EmailSetUp;
import SiteView.ecc.editors.EmailSetUpInput;
import SiteView.ecc.editors.MessageSetUp;
import SiteView.ecc.editors.MessageSetUpInput;
import SiteView.ecc.editors.MonitorBrowse;
import SiteView.ecc.editors.MonitorBrowseInput;
import SiteView.ecc.editors.TableDuty;
import SiteView.ecc.editors.TableDutyInput;
import SiteView.ecc.editors.TaskPlan;
import SiteView.ecc.editors.TaskPlanInput;
import SiteView.ecc.editors.TrendReport;
import SiteView.ecc.editors.TrendReportInput;
import SiteView.ecc.editors.UserManager;
import SiteView.ecc.editors.UserManagerInput;
import SiteView.ecc.reportchart.StatusCTIReport;
import SiteView.ecc.reportchart.TimeContrastReport;
import SiteView.ecc.tab.views.TotalTabView;
import Siteview.Operators;
import Siteview.QueryInfoToGet;
import Siteview.SiteviewQuery;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class EccTreeControl extends ViewPart{			
	public static EccControlInput eee = new EccControlInput();
	public static AbsoluteTimeInput ati = new AbsoluteTimeInput();
	TrendReportInput tren = new TrendReportInput();
	public static Color color = new Color(null, 255, 255, 255);
	public static Object item;
	public static TreeViewer treeViewer;
	public static List<String> list;// 组下面的监测器
	public static List<String> list_1;// windows设备下的监测器
	public static List<String> list_2;// Unix设备下的监测器
	public static List<String> list_4;// 网络设备下的监测器

	static {
		list = new ArrayList<String>();
		list_1 = new ArrayList<String>();
		list_2 = new ArrayList<String>();
		list_4 = new ArrayList<String>();
		list.add("Ecc.ping");
		list.add("Ecc.DNS");
		list.add("Ecc.LinkCheck");
		list.add("Ecc.URL");
		list.add("Ecc.URLContent");
		list.add("Ecc.Mail");
		list.add("Ecc.NetWorkMonitor");

		list.add("Ecc.ScaningDevice");
		
		list_1.add("Ecc.DiskSpace");
		list_1.add("Ecc.Port");
		list_1.add("Ecc.Memory");
		list_1.add("Ecc.Service");
		list_1.add("Ecc.IISServer");
		list_1.add("Ecc.RealMediaPlayer");
		list_1.add("Ecc.WinPerformanceCounter");
		list_1.add("Ecc.WinResources");
		list_1.add("Ecc.WinMediaServer");
		list_1.add("Ecc.WindowsDialup");
		list_1.add("Ecc.WindowsEventLog");
		list_1.add("Ecc.SQLServerMonitor");
		list_1.add("Ecc.CPUUtilization");
		list_2.add("Ecc.DiskSpace");
		list_2.add("Ecc.Memory");
		list_2.add("Ecc.Port");
		list_2.add("Ecc.Memory");
		list_2.add("Ecc.Service");
		list_4.add("Ecc.ConfigFileReadMonitor");
		list_4.add("Ecc.ConfigDownLoadUploadMonitor");
	}

	public static TreeViewer getTreeViewer() {
		return treeViewer;
	}

	public EccTreeControl() {
	}
	
	public static String ID = "SiteView.ecc.view";

	@Override
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent);
		Tree tree = treeViewer.getTree();
		treeViewer.setContentProvider(new EccTreeContentProvider());
		treeViewer.setLabelProvider(new EccTreeLabelProvider());
		SiteViewData s = new SiteViewData();
		treeViewer.setInput(s.getData());
		treeViewer.expandToLevel(2);
		treeViewer.setComparer(new EccTreeComparer());
		treeViewer.getTree().addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				item = e.item.getData();
				if (item instanceof SiteViewData) {
					return;
				} else if (item instanceof UserManageModle) {
					try {
						PlatformUI
								.getWorkbench()
								.getActiveWorkbenchWindow()
								.getActivePage()
								.openEditor(new UserManagerInput(),
										UserManager.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				} else if (item instanceof EmailSetUpModel) {
					try {
						PlatformUI
								.getWorkbench()
								.getActiveWorkbenchWindow()
								.getActivePage()
								.openEditor(new EmailSetUpInput(),
										EmailSetUp.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				} else if (item instanceof MonitorBrowseModle) {
					try {
						PlatformUI
								.getWorkbench()
								.getActiveWorkbenchWindow()
								.getActivePage()
								.openEditor(new MonitorBrowseInput(),
										MonitorBrowse.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				} else if (item instanceof MessageSetUpModel) {
					try {
						PlatformUI
								.getWorkbench()
								.getActiveWorkbenchWindow()
								.getActivePage()
								.openEditor(new MessageSetUpInput(),
										MessageSetUp.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				} else if (item instanceof TableDutyModle) {
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage()
								.openEditor(new TableDutyInput(), TableDuty.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				} else if (item instanceof TaskPlanModel) {
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage()
								.openEditor(new TaskPlanInput(), TaskPlan.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				} else if (item instanceof AlarmLogModle) {
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage()
								.openEditor(new AlarmLogInput(), AlarmLog.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				} else if (item instanceof AbsoluteTimeModel
						|| item instanceof TimeQuantumModel
						|| item instanceof RelativeTimeModel) {
					IWorkbenchPage page = Activator.getDefault().getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();
					IEditorPart editor = page.findEditor(ati);
					if (editor == null) {
						if (item instanceof AbsoluteTimeModel) {
							try {
								page.openEditor(new AbsoluteTimeInput(
										"absolute"), AbsoluteTime.absoluteID);
							} catch (PartInitException e1) {
								e1.printStackTrace();
							}
						} else if (item instanceof TimeQuantumModel) {
							try {
								page.openEditor(
										new AbsoluteTimeInput("quantum"),
										AbsoluteTime.absoluteID);
							} catch (PartInitException e1) {
								e1.printStackTrace();
							}
						} else if (item instanceof RelativeTimeModel) {
							try {
								page.openEditor(new AbsoluteTimeInput(
										"ralative"), AbsoluteTime.absoluteID);
							} catch (PartInitException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						page.activate(editor);
						if (item instanceof AbsoluteTimeModel) {
							AbsoluteTime.name = "absolute";
							((AbsoluteTime) editor).createTable();
							((AbsoluteTime) editor).createLabel();
						} else if (item instanceof TimeQuantumModel) {
							AbsoluteTime.name = "quantum";
							((AbsoluteTime) editor).createTable();
							((AbsoluteTime) editor).createLabel();
						} else if (item instanceof RelativeTimeModel) {
							AbsoluteTime.name = "ralative";
							((AbsoluteTime) editor).createTable();
							((AbsoluteTime) editor).createLabel();
						}
					}

				} else if (item instanceof MonitorSetUpModel) {
					MonitorSetUp msu = new MonitorSetUp(null);
					msu.open();
				} else if (item instanceof GroupModle
						|| item instanceof MachineModle
						|| item instanceof SiteViewEcc) {
					IWorkbenchPage page = Activator.getDefault().getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();
					IEditorPart editor = page.findEditor(eee);
					if (editor == null) {
						try {
							page.openEditor(eee, EccControl.ID);
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					} else {
						page.activate(editor);
						((EccControl) editor)
								.createTable(((EccControl) editor).c);
						if (EccControl.item == null) {
							((EccControl) editor).tab(null);
						} else {
							((EccControl) editor)
									.tab((BusinessObject) EccControl.item
											.getData());
						}
					}
				} else if (item instanceof AlarmRuleModle) {
					try {
						PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getActivePage()
								.openEditor(new AlarmRuleInput(), AlarmRule.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				} else if (item instanceof TrendReportModle) {
					IWorkbenchPage page = Activator.getDefault().getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();
					IEditorPart editor = page.findEditor(tren);
					TrendReport.type = ((TrendReportModle) item).getName();
					TotalTabView.businessObj = null;
					TimeContrastReport.bo = null;
					StatusCTIReport.setData(null);
					if (editor == null) {
						try {
							page.openEditor(tren, TrendReport.ID);
						} catch (PartInitException e1) {
							e1.printStackTrace();
						}
					} else {
						page.activate(editor);
						editor.setFocus();
						TrendReport
								.createComposite_1(((TrendReport) editor).composite_1);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		createContextMenu(parent);
		item = tree.getItem(0).getData();
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().openEditor(eee, EccControl.ID);
		} catch (PartInitException e1) {
			e1.printStackTrace();
		}
	}

	public void setFocus() {
	}

	private void createContextMenu(Composite parent) {// 添加菜单
		MenuManager mgr = new MenuManager();
		mgr.setRemoveAllWhenShown(true);
		mgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer
						.getSelection();
				Object element = selection.getFirstElement();
				item = element;
				if (element instanceof GroupModle) {
					GroupModle gm = (GroupModle) element;
					fillContextMenu1(manager, gm);
				} else if (element instanceof MachineModle) {
					MachineModle machine = (MachineModle) element;
					fillContextMenu2(manager, machine);
				} else if (element instanceof SiteViewEcc) {
					fillContextMenu2(manager, (SiteViewEcc) element);
				}
			}
		});
		Menu menu = mgr.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(mgr, treeViewer);

	}

	protected void fillContextMenu2(IMenuManager manager, SiteViewEcc siteview) {
		AddGroupAction addGroupAction = new AddGroupAction();
		manager.add(addGroupAction);
		if (!siteview.isAddGroup()) {
			manager.setVisible(false);
		}
	}

	public void fillContextMenu1(IMenuManager manager, GroupModle gm) {// 菜单具体项目
		URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
				"Image/AddMachine.bmp");
		ImageDescriptor temp1 = ImageDescriptor.createFromURL(url);
		MenuManager machineMenu = new MenuManager("增加设备", temp1, "1");
		manager.add(machineMenu);
		machineMenu.add(new WindowsAction());
		machineMenu.add(new UnixAction());
		machineMenu.add(new SNMPAction());
		machineMenu.add(new RemoteAction());

		URL url2 = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
				"Image/AddMonitor.bmp");
		ImageDescriptor temp2 = ImageDescriptor.createFromURL(url2);
		MenuManager monitorMenu = new MenuManager("增加监测器", temp2, "2");
		manager.add(monitorMenu);
		for (int i = 0; i < list.size(); i++) {
			monitorMenu.add(new pingAction(list.get(i)));
		}
		EditorGroupAction editorGroupAction = new EditorGroupAction();// 编辑组
		AddGroupAction addGroupAction = new AddGroupAction();// 增加子组
		DeleteGroupAction deleteGroupAction = new DeleteGroupAction();// 删除组
		ProhibitAction prohibitAction = new ProhibitAction();// 禁止
		AllProhibitAction allProhibitAction = new AllProhibitAction();// 批量禁止
		AllStart allStart = new AllStart();// 批量启动
		AllDelete allDelete = new AllDelete();// 批量删除
		manager.add(editorGroupAction);
		manager.add(addGroupAction);
		manager.add(deleteGroupAction);
		manager.add(prohibitAction);
		manager.add(allProhibitAction);
		manager.add(allStart);
		manager.add(allDelete);

		if (!gm.isAddMachine()) {
			machineMenu.setVisible(false);
		}
		if (!gm.isAddMonitor()) {
			monitorMenu.setVisible(false);
		}
		if (!gm.isAddSubGroup()) {
			addGroupAction.setEnabled(false);
		}
		if (!gm.isDeleteGroup()) {
			deleteGroupAction.setEnabled(false);
		}
		if (!gm.isEditGroup()) {
			editorGroupAction.setEnabled(false);
		}

	}

	public void fillContextMenu2(IMenuManager manager, MachineModle machine) {// 菜单具体项目
		URL url;
		url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
				"Image/AddMonitor.bmp");
		ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		MenuManager monitorMenu = new MenuManager("增加监测器", temp, "1");
		manager.add(monitorMenu);
		for (int i = 0; i < list.size(); i++) {
			monitorMenu.add(new DNSAction(list.get(i)));
		}
		if (machine.getBo().GetField("RemoteMachineType").get_NativeValue()
				.toString().equals("RemoteNT")) {
			for (int i = 0; i < list_1.size(); i++) {
				monitorMenu.add(new DNSAction(list_1.get(i)));
			}
		} else if (machine.getBo().GetField("RemoteMachineType")
				.get_NativeValue().toString().equals("RemoteUnix")) {
			for (int i = 0; i < list_2.size(); i++) {
				monitorMenu.add(new DNSAction(list_2.get(i)));
			}
		} else if (machine.getBo().GetField("RemoteMachineType")
				.get_NativeValue().toString().equals("RemoteEquipment")) {
			for (int i = 0; i < list_4.size(); i++) {
				monitorMenu.add(new DNSAction(list_4.get(i)));
			}
		}

		EditorMachineAction editorMachineAction = new EditorMachineAction();// 编辑设备
		DeleteMachineAction deleteMachineAction = new DeleteMachineAction();// 删除设备
		DeleteGroupAction deleteMonitorAction = new DeleteGroupAction();// 删除监测器
		ProhibitAction prohibitAction = new ProhibitAction();// 禁止
		AllProhibitAction allProhibitAction = new AllProhibitAction();// 批量禁止
		AllStart allStart = new AllStart();// 批量启动
		AllDelete allDelete = new AllDelete();// 批量删除
		manager.add(editorMachineAction);
		manager.add(deleteMachineAction);
		manager.add(prohibitAction);
		manager.add(allProhibitAction);
		manager.add(allStart);
		manager.add(allDelete);
		if (!machine.isDeleteMchine()) {

			deleteMachineAction.setEnabled(false);
		}
		if (!machine.isAddMonitor()) {

			monitorMenu.setVisible(false);
		}
		if (!machine.isDeleteMonitor()) {

			deleteMonitorAction.setEnabled(false);
		}
		if (!machine.isEditMachine()) {

			editorMachineAction.setEnabled(false);
		}
	}

	public static BusinessObject CreateBo(String key, String s, String s1) {
		SiteviewQuery query = new SiteviewQuery();
		query.AddBusObQuery(s1, QueryInfoToGet.All);
		XmlElement xml;
		xml = query.get_CriteriaBuilder().FieldAndValueExpression(key,
				Operators.Equals, s);
		query.set_BusObSearchCriteria(xml);
		ICollection iCollenction = ConnectionBroker.get_SiteviewApi()
				.get_BusObService().get_SimpleQueryResolver()
				.ResolveQueryToBusObList(query);
		BusinessObject bo = null;
		IEnumerator interfaceTableIEnum = iCollenction.GetEnumerator();
		if (interfaceTableIEnum.MoveNext()) {
			bo = (BusinessObject) interfaceTableIEnum.get_Current();
		}
		return bo;
	}
	public static List<BusinessObject> CreateConfigBo(String key, String s, String s1) {
		SiteviewQuery query = new SiteviewQuery();
		query.AddBusObQuery(s1, QueryInfoToGet.All);
		XmlElement xml;
		xml = query.get_CriteriaBuilder().FieldAndValueExpression(key,
				Operators.Equals, s);
		query.set_BusObSearchCriteria(xml);
		ICollection iCollenction = ConnectionBroker.get_SiteviewApi()
				.get_BusObService().get_SimpleQueryResolver()
				.ResolveQueryToBusObList(query);
		List<BusinessObject> list = new ArrayList<BusinessObject>();
		BusinessObject bo=null;
		IEnumerator interfaceTableIEnum = iCollenction.GetEnumerator();
		
		while (interfaceTableIEnum.MoveNext()) {
			bo = (BusinessObject) interfaceTableIEnum.get_Current();
			list.add(bo);
		}
		return list;
	}
	
}
