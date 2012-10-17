package SiteView.ecc.views;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

import COM.dragonflow.Api.APIInterfaces;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.bundle.EditGroupBundle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.dialog.ParticularInfo;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.reportchart.EccReportChart;
import SiteView.ecc.tab.views.TotalTabView;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import core.busobmaint.BusObMaintView;
/**
 * 概要信息视图
 * @author zhongping.wang
 *
 */
public class EccReportView extends ViewPart {
	private Composite reportComposite;
	private ChartComposite frame;
	private TableViewer tableViewer;
	private Table table;
	public static final String ID = "SiteView.ecc.views.EccReportView";
	public static Composite composite_reportimgControl = null;
	public static JFreeChart chart = null;

	public EccReportView() {

	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		parent.setLayout(new FillLayout());
		SashForm reportForm = new SashForm(parent, SWT.BORDER);
		
		reportForm.setOrientation(SWT.VERTICAL);
		reportForm.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite_reportimgControl = new Composite(reportForm, SWT.NONE);
		composite_reportimgControl.setLayout(new FormLayout());
		Composite composite_reportdescControl = new Composite(reportForm,
				SWT.NONE);
		composite_reportdescControl.setLayout(new FillLayout(SWT.HORIZONTAL));
		reportComposite = new Composite(composite_reportimgControl, SWT.NONE);
		reportComposite.setLayoutData(new FormData());
		reportComposite.setLayout(new FillLayout());
		XYDataset dataset = EccReportChart.createDataset(TotalTabView.xyDataArrayList);
		chart = EccReportChart.createChart(dataset, TotalTabView.xname,
				TotalTabView.yname);

		Group group1 = new Group(composite_reportimgControl,
				SWT.SHADOW_ETCHED_OUT);
		FormData fd_group1 = new FormData();
		fd_group1.right = new FormAttachment(100);
		fd_group1.top = new FormAttachment(reportComposite, 0, SWT.TOP);
		fd_group1.bottom = new FormAttachment(100);
		group1.setBackground(new Color(null, 255, 255, 255));
		group1.setLayoutData(fd_group1);
		group1.setLayout(new FillLayout(SWT.VERTICAL));
		group1.setText("数据记录");
		
		Composite composite = new Composite(group1, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Button button = new Button(composite, SWT.NONE);
		button.setBounds(0, 0, 56, 22);
		button.setText("\u8BE6\u7EC6\u4FE1\u606F");
		button.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_FOREGROUND));
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setBounds(55, 0, 48, 22);
		button_1.setText("\u7F16\u8F91");
		button_1.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				BusinessObject bo=(BusinessObject) EccTreeControl.item;
				if(SiteViewData.user.get_SecurityGroupName().equals("监测管理员")||SiteViewData.user.get_SecurityGroupName().equals("Administrators")){
					BusObMaintView.open(ConnectionBroker.get_SiteviewApi(),bo);
					return;
				}
				String machineId=bo.GetField("Machine").get_NativeValue().toString();
				if(machineId==null||machineId.equals("")){
					machineId=bo.GetField("Groups").get_NativeValue().toString();
				}
				if(SiteViewData.permissions.get(machineId)!=null){
					BusinessObject b=SiteViewData.permissions.get(machineId);
					if((Boolean)b.GetField("EditMonitor").get_NativeValue()){
						BusObMaintView.open(ConnectionBroker.get_SiteviewApi(),bo);
						return;
					}
				}
				MessageDialog.openQuestion(new Shell(), "警告", "您没有编辑权限");
			}
		});
		
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setBounds(103, 0, 48, 22);
		button_2.setText("\u5220\u9664");
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.setBounds(151, 0, 51, 22);
		button_3.setText("\u5237\u65B0");
		button_3.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				TableItem itable=EccControl.item;
				BusinessObject monitor=(BusinessObject) EccControl.item.getData();
				String monitorid=monitor.get_RecId();
				APIInterfaces rmiServer=EditGroupBundle.createAmiServer();
				try {
					rmiServer.Refresh(monitorid,monitor.GetField("Groups").get_NativeValue().toString());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				BusinessObject bo=EccTreeControl.CreateBo("monitorid", monitorid, "EccDyn");
				String [] s=new String[5];
				s[0]=(Boolean)monitor.GetField("disable").get_NativeValue()?"禁止":"允许";
				s[1]=bo.GetField("category").get_NativeValue().toString();
				s[2]=monitor.GetField("Title").get_NativeValue().toString();
				String desc =bo.GetField("monitorDesc").get_NativeValue().toString();
				s[3]=EccControl.format(desc,monitor.GetField("EccType").get_NativeValue().toString());
				s[4] = bo.GetField("LastModDateTime").get_NativeValue().toString();
				itable.setText(s);
				EccControl.tab((BusinessObject)EccControl.item.getData());
			}
		});
		button_2.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				BusinessObject bo=(BusinessObject) EccTreeControl.item;
				String id=bo.GetField("Machine").get_NativeValue().toString();
				boolean flage=false;
				if(!SiteViewData.user.get_SecurityGroupName().equals("监测管理员")&&!SiteViewData.user.get_SecurityGroupName().equals("Administrators")){
					if(id!=null && !id.equals("")){
						if(SiteViewData.permissions.get(id)!=null){
							flage=(Boolean)SiteViewData.permissions.get(id).GetField("DeleteMonitor").get_NativeValue();
						}else{
							flage=true;
						}
					}else{
						id=bo.GetField("Groups").get_NativeValue().toString();
						if(SiteViewData.permissions.get(id)!=null){
							flage=(Boolean)SiteViewData.permissions.get(id).GetField("DeleteMonitor").get_NativeValue();
						}else{
							flage=true;
						}
					}
				}
				if(flage){
					MessageDialog.openQuestion(new Shell(), "警告", "您没有删除监测器的权限");
					return ;
				}
				String groupId=bo.GetFieldOrSubfield("Groups_valid").get_NativeValue().toString();
				TableItem  tablei=EccControl.item;
				Table toptable=tablei.getParent();
				if(toptable.getItemCount()>1){
					EccControl.item=(TableItem)toptable.getItem(0);
					toptable.select(0);
					EccControl.tab((BusinessObject)EccControl.item.getData());
				}else{
					EccControl.c1.dispose();
				}
				tablei.dispose();
				bo.DeleteObject(ConnectionBroker.get_SiteviewApi());
				EditGroupBundle edit=new EditGroupBundle();
				edit.updateGroup("GroupId="+groupId);
			}
		});
		
		button.addListener(SWT.MouseDown,new Listener() {
			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
				ParticularInfo par=new ParticularInfo(null);
				par.open();
			}
		});
		
		Label goodlabel = new Label(group1, SWT.NONE);
		goodlabel.setForeground(new Color(null,0,255,0));
		goodlabel.setBackground(new Color(null, 255, 255, 255));
		goodlabel.setText("   正常:   " + TotalTabView.goodcount+ "条");
		Label warninglabel = new Label(group1, SWT.NONE);
		warninglabel.setForeground(new Color(null,192,194,20));
		warninglabel.setBackground(new Color(null, 255, 255, 255));
		warninglabel.setText("   危险:   "+ TotalTabView.warningcount+ "条");
		Label errorlabel = new Label(group1, SWT.NONE);
		errorlabel.setForeground(new Color(null,255,0,0));
		errorlabel.setBackground(new Color(null, 255, 255, 255));
		errorlabel.setText("   错误:   " + TotalTabView.errorcount+ "条 ");
		Label disablelabel = new Label(group1, SWT.NONE);
		disablelabel.setForeground(new Color(null,255,170,102));
		disablelabel.setBackground(new Color(null, 255, 255, 255));
		disablelabel.setText("   禁止:   " + TotalTabView.disablecount+ "条");
		Label errorValve = new Label(group1, SWT.NONE);
		errorValve.setText("   错误阀值:   "+TotalTabView.errorAlarmCondition);
		errorValve.setBackground(new Color(null, 255, 255, 255));
		Label warningValve = new Label(group1, SWT.NONE);
		warningValve.setText("   危险阀值:   "+TotalTabView.warningAlarmCondition);
		warningValve.setBackground(new Color(null, 255, 255, 255));
		Label goodValve = new Label(group1, SWT.NONE);
		goodValve.setText("   正常阀值:   "+TotalTabView.goodAlarmCondition);
		goodValve.setBackground(new Color(null, 255, 255, 255));
		Label labelName3 = new Label(group1, SWT.NONE);
		labelName3.setText("   时间段：   ");
		labelName3.setBackground(new Color(null, 255, 255, 255));
		Label labelName4 = new Label(group1, SWT.NONE);
		labelName4.setText("   从：   "+TotalTabView.startTime+" 开始");
		labelName4.setBackground(new Color(null, 255, 255, 255));
		Label labelName5 = new Label(group1, SWT.NONE);
		labelName5.setText("   到：   "+TotalTabView.endTime+" 结束");
		labelName5.setBackground(new Color(null, 255, 255, 255));
		frame = new ChartComposite(composite_reportimgControl, SWT.NONE, chart, true);
		fd_group1.left = new FormAttachment(frame, 6);
		FormData fd_frame = new FormData();
		fd_frame.top = new FormAttachment(reportComposite, 0, SWT.TOP);
		fd_frame.right = new FormAttachment(100, -217);
		fd_frame.left = new FormAttachment(0);
		fd_frame.bottom = new FormAttachment(100);
		frame.setLayoutData(fd_frame);
		tableViewer = new TableViewer(composite_reportdescControl, SWT.MULTI
				| SWT.FULL_SELECTION | SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBackground(new Color(null, 255, 255, 255));
		TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
		newColumnTableColumn.setWidth(600);
		newColumnTableColumn.setText("返回值");
		TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_1.setWidth(150);
		newColumnTableColumn_1.setText("最大值");
		TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_2.setWidth(150);
		newColumnTableColumn_2.setText("平均值");
		TableColumn newColumnTableColumn_3 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_3.setWidth(150);
		newColumnTableColumn_3.setText("最新值");
		reportForm.setWeights(new int[] {293, 165});
		
		for (Map<String, List<String>> map : TotalTabView.reportDescList) {
			Set<Map.Entry<String, List<String>>> set = map.entrySet();
			for (Iterator<Map.Entry<String, List<String>>> it = set.iterator(); it
					.hasNext();) {
				TableItem item = new TableItem(table, SWT.NONE);
				Map.Entry<String, List<String>> entry = (Map.Entry<String, List<String>>) it
						.next();
				List<String> arrlist = entry.getValue();
				if (arrlist.size()>0) {
					String str = entry.getKey()+"&"+arrlist.get(0)+"&"+arrlist.get(1)+"&"+arrlist.get(2);
					String[] strdata = str.split("&");
					item.setText(strdata);
				}
			}
		}
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * Refresh Report Part
	 */
	public void refreshReport() {
		if (reportComposite.getChildren().length > 0) {
			for (Control control : reportComposite.getChildren()) {
				control.dispose();
			}
		}
		XYDataset dataset = EccReportChart.createDataset(TotalTabView.xyDataArrayList);
		chart = EccReportChart.createChart(dataset, TotalTabView.xname,
				TotalTabView.yname);
		frame = new ChartComposite(reportComposite, SWT.NONE, chart, true);
		reportComposite.layout();
	}
}
