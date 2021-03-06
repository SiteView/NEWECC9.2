package SiteView.ecc.tab.views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import siteview.windows.forms.LayoutViewBase;
import swing2swt.layout.FlowLayout;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import system.Xml.XmlElement;
import SiteView.ecc.tools.Config;
import SiteView.ecc.tools.FileTools;
import Siteview.Operators;
import Siteview.QueryInfoToGet;
import Siteview.SiteviewQuery;
import Siteview.Api.BusinessObject;
import Siteview.Api.ISiteviewApi;
import Siteview.Windows.Forms.ConnectionBroker;

/**
 * 日志数据
 * @author：lihua.zhong
 */
public class MonitorLogTabView extends LayoutViewBase {
	// 数据
	public static BusinessObject bo;
	private BusinessObject bo1;
	private static ICollection iCollenction;//日志数据
	Map<String, Object> map;//查询日志条件（列:值）
	List<String> cloumns;//表的列
	List<String> cloumnsEn;//列所对应的日志属性
	// 控件
	SashForm sashForm;
	private Button good;
	private Button error;
	private Button warning;
	private Button disable;
	private Button all;
	private Table table_1;
	private static Color [] color;//nodata下标0 error下标1 warning下标2 good下标3 disable下标4

	public MonitorLogTabView(Composite parent) {
		super(parent);
	}

	public void SetDataFromBusOb(BusinessObject bo) {
		bo1 = bo;
	}

	// 赋初始值
	public static void SetData(BusinessObject  bo) {
		MonitorLogTabView.bo = bo;
		color=new Color[5];
		color[0]=new Color(null, 0,153,255);
		color[1]=new Color(null,255,50,10);
		color[2]=new Color(null,255,255,136);
		color[3]=new Color(null, 0,255,0);
		color[4]=new Color(null,255,170,102);
	}
	
	private  Map<String, Object> setMap(BusinessObject bo) {
		map = new java.util.HashMap<String, Object>();
		map.put("monitorId", bo.get_RecId());
//		String time = getHoursAgoTime(2);
		
		String time="";
		if(bo.GetField("EccType").get_NativeValue().toString().equals("ScaningDevice")){
			time=getHoursAgoTime(24);
		}else{
			time = getHoursAgoTime(2);
		}
		
		map.put("endTime", time.substring(0, time.indexOf("*")));
		map.put("startTime", time.substring(time.indexOf("*") + 1));
		return map;
	}
	//创建tab
	public  void createView(final Composite parent) {
		parent.addControlListener(new ControlListener() {
			public void controlResized(ControlEvent e) {
				if (parent.getChildren().length > 0) {
					for (Control control : parent.getChildren()) {
						control.dispose();
					}
				}
				if(bo!=null){
					bo1=bo;
					setCloumns(bo.get_Name());
					setMap(bo);
					parent.setLayout(new FillLayout(SWT.VERTICAL));
					sashForm = new SashForm(parent, SWT.VERTICAL);
					Composite group = new Composite(sashForm, SWT.NONE);
					group.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					createToolbar(group);
					iCollenction = getLog(map);
					Composite com=new Composite(sashForm, SWT.NONE);
					com.setLayout(new FillLayout());
					createTable(com, iCollenction);
					sashForm.setWeights(new int[] {30, 400});
					parent.layout();
				}
			}
			public void controlMoved(ControlEvent e) {
			}
		});
	}
	//创建状态单	选按钮
	private void createToolbar(final Composite group) {
		all = new Button(group, SWT.RADIO);
		all.setText("\u5168\u90E8");
		all.setSelection(true);
		all.setBackground(color[0]);
		
		error = new Button(group, SWT.RADIO);
		error.setText("\u9519\u8BEF");
		error.setBackground(color[1]);
		
		good = new Button(group, SWT.RADIO);
		good.setText("\u6B63\u5E38");
		good.setBackground(color[3]);
		
		warning = new Button(group, SWT.RADIO);
		warning.setText("\u5371\u9669");
		warning.setBackground(color[2]);
		
		disable = new Button(group, SWT.RADIO);
		disable.setText("\u7981\u7528");
		disable.setBackground(color[4]);
		
		all.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (all.getSelection()) {
					setMap(bo1);
					iCollenction = getLog(map);
					createItem(iCollenction);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			
			}
		});
		warning.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (warning.getSelection()) {
					setMap(bo1);
					map.put("MonitorStatus", "warning");
					iCollenction = getLog(map);
					createItem(iCollenction);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		disable.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (disable.getSelection()) {
					setMap(bo1);
					map.put("MonitorStatus", "disable");
					iCollenction = getLog(map);
					createItem(iCollenction);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		good.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (good.getSelection()) {
					setMap(bo1);
					map.put("MonitorStatus", "good");
					iCollenction = getLog(map);
					createItem(iCollenction);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		error.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (error.getSelection()) {
					setMap(bo1);
					map.put("MonitorStatus", "error");
					iCollenction = getLog(map);
					createItem(iCollenction);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
	}
	// 日志数据表
	public void createTable(Composite com, ICollection iCollection) {
		if (table_1 != null && !table_1.isDisposed()) {
			table_1.dispose();
		}
		table_1= new Table(com, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		for (int i = 0; i < cloumns.size(); i++) {
			TableColumn tblclmnNewColumn = new TableColumn(table_1, SWT.NONE);
			if (i == (cloumns.size() - 1)) {
				tblclmnNewColumn.setWidth(250);
			} else {
				tblclmnNewColumn.setWidth(100);
			}
			tblclmnNewColumn.setText(cloumns.get(i).toString());
		}
		createItem(iCollection);
//		IEnumerator interfaceTableIEnum = iCollection.GetEnumerator();
//		while (interfaceTableIEnum.MoveNext()) {
//			String[] data = new String[cloumns.size()];
//			int j = 0;
//			BusinessObject bo = (BusinessObject) interfaceTableIEnum
//					.get_Current();
//			TableItem item = new TableItem(table_1, SWT.NONE);
//			data[j++] = bo.GetField("CreatedDateTime").get_NativeValue()
//					.toString();
//			data[j++] = bo.GetField("monitorName").get_NativeValue().toString();
//			String s = bo.GetField("MonitorMassage").get_NativeValue()
//					.toString();
//			String status=bo.GetField("MonitorStatus").get_NativeValue().toString();
//				List<String> massage = formatItem(s);
//				for (int n = 0; n < massage.size(); n++) {
//					data[j++] = massage.get(n).toString();
//				}
//				item.setText(data);
//			Color  c=c=new Color(null,255,170,102);
//			if(status.equals("good")){
//				item.setBackground(color[3]);
//			}else if(status.equals("error")){
//				item.setBackground(color[1]);
//			}else if(status.equals("warning")){
//				item.setBackground(color[2]);
//			}else if(status.equals("disable")){
//				item.setBackground(color[4]);
//			}else {
//				item.setBackground(color[0]);
//			}
//		}
		table_1.layout();
	}
	public void createItem(ICollection iCollection){
		if(table_1.getItems().length>0){
			for(TableItem tableItem:table_1.getItems()){
				tableItem.dispose();
			}
		}
		IEnumerator interfaceTableIEnum = iCollection.GetEnumerator();
		while (interfaceTableIEnum.MoveNext()) {
			String[] data = new String[cloumns.size()];
			int j = 0;
			BusinessObject bo = (BusinessObject) interfaceTableIEnum
					.get_Current();
			TableItem item = new TableItem(table_1, SWT.NONE);
			data[j++] = bo.GetField("CreatedDateTime").get_NativeValue()
					.toString();
			data[j++] = bo.GetField("monitorName").get_NativeValue().toString();
			String s = bo.GetField("MonitorMassage").get_NativeValue()
					.toString();
			String status=bo.GetField("MonitorStatus").get_NativeValue().toString();
				List<String> massage = formatItem(s);
				for (int n = 0; n < massage.size(); n++) {
					data[j++] = massage.get(n).toString();
				}
				item.setText(data);
			Color  c=c=new Color(null,255,170,102);
			if(status.equals("good")){
				item.setBackground(color[3]);
			}else if(status.equals("error")){
				item.setBackground(color[1]);
			}else if(status.equals("warning")){
				item.setBackground(color[2]);
			}else if(status.equals("disable")){
				item.setBackground(color[4]);
			}else {
				item.setBackground(color[0]);
			}
		}	
	}
	//获取表的列
	private  void setCloumns(String s) {
		cloumns= new ArrayList<String>();
		cloumnsEn=new ArrayList<String>();
		cloumns.add("时间");
		cloumns.add("名称");
		String filePath = FileTools
				.getRealPath("\\files\\MonitorLogTabView.properties");
		s = Config.getReturnStr(filePath, s);
		if (s!=null) {
			String[] column = s.split(",");
			for (String s1 : column) {
				cloumns.add(s1.substring(0,s1.indexOf(":")));
				cloumnsEn.add(s1.substring(s1.indexOf(":")+1));
			}
		}
		cloumns.add("描述");
		
	}
	//销毁颜色
//	public void dispose() {
//		for(Color c: color){
//			c.dispose();
//		}
//		super.dispose();
//	}
	// 赋时间值（得到当前及N小时前时间）公用方法
	public static String getHoursAgoTime(int i) {
		Calendar cal = Calendar.getInstance();
		String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(cal.getTime());
		cal.add(Calendar.HOUR, -i);
		String twoHoursAgoTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(cal.getTime());
		return currentTime + "*" + twoHoursAgoTime;
	}
	// 查日志 （公用方法）
	public static ICollection getLog(Map<String, Object> map) {
		ISiteviewApi siteviewApi = ConnectionBroker.get_SiteviewApi();
		SiteviewQuery query = new SiteviewQuery();
		query.AddBusObQuery("MonitorLog", QueryInfoToGet.All);
		XmlElement[] xmls = new XmlElement[map.size() - 1];
		XmlElement xml;
		int i = 0;
		if (map.get("startTime") != null) {
			xml = query.get_CriteriaBuilder().QueryListExpression(
					"CreatedDateTime", Operators.Between.name());
			query.get_CriteriaBuilder().AddLiteralValue(xml,
					map.get("startTime").toString());
			query.get_CriteriaBuilder().AddLiteralValue(xml,
					map.get("endTime").toString());
			xmls[i++] = xml;
			map.remove("startTime");
			map.remove("endTime");
		}
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			key = key.substring(0, key.indexOf("="));
			xml = query.get_CriteriaBuilder().FieldAndValueExpression(key,
					Operators.Equals, map.get(key).toString());
			xmls[i++] = xml;
		}
		query.AddOrderByDesc("CreatedDateTime");
		query.set_BusObSearchCriteria(query.get_CriteriaBuilder()
				.AndExpressions(xmls));

		ICollection iCollenction = siteviewApi.get_BusObService()
				.get_SimpleQueryResolver().ResolveQueryToBusObList(query);
		return iCollenction;
	}
	//解析日志文件monitorMassage字段（公用方法）
	private List<String> formatItem(String s) {
		List<String> massage = new ArrayList<String>();
		for (String s0 : cloumnsEn) {
			if(s.contains(s0)){
				String s1=s.substring(s.indexOf(s0));
				if(s1.contains("\t")){
					s1=s1.substring(s1.indexOf("=")+1,s1.indexOf("\t"));
				}else{
					s1=s1.substring(s1.indexOf("=")+1);
				}
				massage.add(s1);
			}
		}
		s=s.replaceAll("\t", ",");
		massage.add(s);
		return massage;
	}
}