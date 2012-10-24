package SiteView.ecc.editors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.dialog.CreateFilterCondition;
import SiteView.ecc.tools.FileTools;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

public class MonitorBrowse extends EditorPart {
	public static String ID = "SiteView.ecc.editors.MonitorBrowse";
	private Table table;
	private Table table_1;
	private Button button;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	private Button btnNewButton_1;
	private Button btnNewButton_3;
	private Label lblNewLabel_1;
	private Text text;
	private TableViewer tableViewer;
	private int currentSelectIndex = -1;//标志位，记录table中当前被选中的行索引
	private double pageSize = 3;//每一页显示的记录条数
	private double currentPage = 1;//当前页码数
	private double count = 0;//总记录数
	private double pageCount = 0;
//	private double begin = (currentPage-1)*pageSize+1;
//	private double end = currentPage*pageSize;
	private List<BusinessObject> boList = new ArrayList<BusinessObject>();
	private List<List<String>> list = null;
	public MonitorBrowse() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
		this.setPartName(input.getName());
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	private CreateFilterCondition createCreateFilterCondition(String name){
		return new CreateFilterCondition(null,name,this);
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(0, 0, 82, 32);
		btnNewButton.setText("创建筛选条件");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				String name = ((Button)e.getSource()).getText();
				CreateFilterCondition cfc = createCreateFilterCondition(name);
				cfc.open();
			}
		});
		
		btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(90, 0, 72, 32);
		btnNewButton_1.setText("编辑");
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setBounds(171, 0, 72, 32);
		btnNewButton_2.setText("刷新");
		
		btnNewButton_3 = new Button(composite, SWT.NONE);
		btnNewButton_3.setBounds(252, 0, 72, 32);
		btnNewButton_3.setText("删除");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				TableItem[] arr = table.getSelection();
				int a = table.getSelectionIndex();
				if(arr.length<1){
					MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "提示", "你未选中任何行!");
					return;
				}
				Boolean flag = MessageDialog.openConfirm(Display.getCurrent().getActiveShell(), "确认框", "你真的要删除此项吗?");
				if(flag){
					String recId = arr[0].getText(8);
					for(int i=0;i<boList.size();i++){
						String rec = boList.get(i).GetField("RecId").get_NativeValue().toString();
						if(rec.equals(recId)){
							boList.get(i).DeleteObject(ConnectionBroker.get_SiteviewApi());
							boList.remove(i);
							table.remove(a);
							if(currentSelectIndex == a){
								table_1.removeAll();
							}
							currentSelectIndex = -1;
							break;
						}
					}
				}
			}
		});
		
		Button btnNewButton_4 = new Button(composite, SWT.NONE);
		btnNewButton_4.setBounds(334, 0, 72, 32);
		btnNewButton_4.setText("生成Excel");
		
		Button btnNewButton_5 = new Button(composite, SWT.NONE);
		btnNewButton_5.setBounds(415, 0, 82, 32);
		btnNewButton_5.setText("设置显示条目");
		
		tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				int a = table.getSelectionIndex();
				if(a == currentSelectIndex){
					return;
				}
				currentSelectIndex = a;
				if(a==0){
					btnNewButton_1.setEnabled(false);
					btnNewButton_3.setEnabled(false);
				}else{
					btnNewButton_1.setEnabled(true);
					btnNewButton_3.setEnabled(true);
				}
		
				String s = "";
				if(a==0){
					ICollection ic = FileTools.getBussCollection("Ecc");
					IEnumerator ieable = ic.GetEnumerator();
					while(ieable.MoveNext()){
						BusinessObject bo = (BusinessObject)ieable.get_Current();
						s = s+bo.GetField("RecId").get_NativeValue().toString();
						s = s + ";";
					}
					s = s.substring(0, s.length()-1);
				}else{
					String recId = table.getItem(a).getText(8);
					ICollection ic = FileTools.getBussCollection("RecId",recId,"EccFilter");
					IEnumerator ieable = ic.GetEnumerator();
					if(ieable.MoveNext()){
						BusinessObject bo = (BusinessObject)ieable.get_Current();
						s = bo.GetField("MonitorId").get_NativeValue().toString();
					}
				}
				list = getTable_1Items(s);
				int n = list.size();
				count = n;
				pageCount = Math.ceil(count/pageSize);//总页数
				if(pageCount==1){
					button_2.setEnabled(false);
				}
				show(table_1,list);
				text.setText(""+(int)currentPage);
				lblNewLabel_1.setText("/"+(int)pageCount);
			}
		});
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn.getColumn();
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("名称");
		
		TableViewerColumn tableViewerColumn_7 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_7 = tableViewerColumn_7.getColumn();
		tblclmnNewColumn_7.setWidth(100);
		tblclmnNewColumn_7.setText("组");
		
		TableViewerColumn tableViewerColumn_6 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_6 = tableViewerColumn_6.getColumn();
		tblclmnNewColumn_6.setWidth(100);
		tblclmnNewColumn_6.setText("设备");
		
		TableViewerColumn tableViewerColumn_5 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_5 = tableViewerColumn_5.getColumn();
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("监测器名称");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_4 = tableViewerColumn_4.getColumn();
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("监测器类型");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_3 = tableViewerColumn_3.getColumn();
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("显示/隐藏");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_2 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("描述");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_1.getColumn();
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("排序");
		
		TableViewerColumn tableViewerColumn_16 = new TableViewerColumn(tableViewer, SWT.Hide);
		TableColumn tableColumn = tableViewerColumn_16.getColumn();
		tableColumn.setWidth(0);
		tableColumn.setText("RecId");
		tableColumn.setResizable(false);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(0, 0, 1894, 27);
		lblNewLabel.setText("浏览次数最多的监测器");
		TableItem item0 = new TableItem(table,SWT.NONE);
		item0.setText(0, "浏览次数最多的监测器");
		item0.setText(4, "所有类型");
		item0.setText(5, "显示所有");
		item0.setText(7, "状态");
		initTableViewer();
		
		TableViewer tableViewer_1 = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableViewerColumn tableViewerColumn_8 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_8 = tableViewerColumn_8.getColumn();
		tblclmnNewColumn_8.setWidth(100);
		tblclmnNewColumn_8.setText("状态");
		
//		TableViewerColumn tableViewerColumn_15 = new TableViewerColumn(tableViewer_1, SWT.NONE);
//		TableColumn tblclmnNewColumn_15 = tableViewerColumn_15.getColumn();
//		tblclmnNewColumn_15.setWidth(100);
//		tblclmnNewColumn_15.setText("组");
		
		TableViewerColumn tableViewerColumn_14 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_14 = tableViewerColumn_14.getColumn();
		tblclmnNewColumn_14.setWidth(100);
		tblclmnNewColumn_14.setText("设备");
		
		TableViewerColumn tableViewerColumn_13 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_13 = tableViewerColumn_13.getColumn();
		tblclmnNewColumn_13.setWidth(100);
		tblclmnNewColumn_13.setText("监测器名称");
		
		TableViewerColumn tableViewerColumn_12 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_12 = tableViewerColumn_12.getColumn();
		tblclmnNewColumn_12.setWidth(100);
		tblclmnNewColumn_12.setText("编辑");
		
		TableViewerColumn tableViewerColumn_11 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_11 = tableViewerColumn_11.getColumn();
		tblclmnNewColumn_11.setWidth(100);
		tblclmnNewColumn_11.setText("刷新");
		
		TableViewerColumn tableViewerColumn_10 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_10 = tableViewerColumn_10.getColumn();
		tblclmnNewColumn_10.setWidth(100);
		tblclmnNewColumn_10.setText("更新时间");
		
		TableViewerColumn tableViewerColumn_9 = new TableViewerColumn(tableViewer_1, SWT.NONE);
		TableColumn tblclmnNewColumn_9 = tableViewerColumn_9.getColumn();
		tblclmnNewColumn_9.setWidth(100);
		tblclmnNewColumn_9.setText("描述");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		button = new Button(composite_2, SWT.NONE);
		button.setBounds(0, 10, 43, 22);
		button.setText("\u9996\u9875");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				showFirst();
			}
		});
		
		button_1 = new Button(composite_2, SWT.NONE);
		button_1.setBounds(49, 10, 57, 22);
		button_1.setText("\u4E0A\u4E00\u9875");
		button_1.setEnabled(false);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				showUp();
			}
		});
		
		text = new Text(composite_2, SWT.BORDER);
		text.setBounds(112, 10, 37, 22);
		
		lblNewLabel_1 = new Label(composite_2, SWT.SHADOW_NONE);
		lblNewLabel_1.setBounds(150, 15, 54, 18);
		lblNewLabel_1.setText("New Label");
		
		button_2 = new Button(composite_2, SWT.NONE);
		button_2.setBounds(210, 10, 57, 22);
		button_2.setText("\u4E0B\u4E00\u9875");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				showNext();
			}
		});
		
		button_3 = new Button(composite_2, SWT.NONE);
		button_3.setBounds(273, 10, 54, 22);
		button_3.setText("\u5C3E\u9875");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				showLast();
			}
		});
		sashForm.setWeights(new int[] {33, 67, 16, 309, 37});

	}
	private void show(Table table,List<List<String>> list){
		clearItems(0,table);//table 赋值之前清空
		double begin = (currentPage-1)*pageSize+1;
		double end = currentPage*pageSize;
		for(double i=begin-1;i<=end-1;i++){
			int j = (int)i;
			TableItem item = new TableItem(table_1, SWT.NONE);
			if(j>list.size()-1)break;
			List<String> ll =list.get(j);
			item.setText(0, ll.get(0));
			item.setText(1, ll.get(1));
			item.setText(2, ll.get(2));
			item.setText(3, ll.get(3));
			item.setText(4, ll.get(4));
			item.setText(5, ll.get(5));
			item.setText(6, ll.get(6));
//			item.setText(7, ll.get(7));
		}
	}
	private void showFirst(){
		currentPage = 1;
		button_2.setEnabled(true);
		button_1.setEnabled(false);
		show(table_1,list);
		text.setText(""+(int)currentPage);
	}
	private void showNext(){
		currentPage++;
		if(currentPage==pageCount){
			button_2.setEnabled(false);
		}else{
			button_1.setEnabled(true);
		}
		show(table_1,list);
		text.setText(""+(int)currentPage);
	}
	private void showUp(){
		currentPage--;
		if(currentPage==1){
			button_1.setEnabled(false);
		}else{
			button_2.setEnabled(true);
		}
		show(table_1,list);
		text.setText(""+(int)currentPage);
	}
	private void showLast(){
		currentPage = pageCount;
		button_1.setEnabled(true);
		button_2.setEnabled(false);
		show(table_1,list);
		text.setText(""+(int)currentPage);
	}
	
	private List<List<String>>getTable_1Items(String s){
		List<List<String>> list1 = new ArrayList<List<String>>();
		String[] monitorId = s.split(";");
		for(int i=0;i<monitorId.length;i++){
			List<String> list = new ArrayList<String>();
			String status = "";
//			String groupName = "";
			String machineName = "";
			String monitorName = "";
			String edit = "编辑";
			String refresh = "刷新";
			String updateTime = "";
			String description = "";
			
			ICollection ic1 = FileTools.getBussCollection("RecId",monitorId[i].trim(),"Ecc");
			IEnumerator ieable1 = ic1.GetEnumerator();
			String machineId = "";
			if(ieable1.MoveNext()){
				machineId = ((BusinessObject)ieable1.get_Current()).GetField("Machine").get_NativeValue().toString();
				monitorName = ((BusinessObject)ieable1.get_Current()).GetField("title").get_NativeValue().toString();
				ICollection ic3 = FileTools.getBussCollection("RecId",machineId,"RemoteMachine");
				IEnumerator ieable3 = ic3.GetEnumerator();
				if(ieable3.MoveNext()){
					machineName = ((BusinessObject)ieable3.get_Current()).GetField("ServerAddress").get_NativeValue().toString();
				}
			}
			ICollection ic2 = FileTools.getBussCollection("monitorid",monitorId[i].trim(),"EccDyn");
			IEnumerator ieable2 = ic2.GetEnumerator();
			if(ieable2.MoveNext()){
				BusinessObject bo = (BusinessObject)ieable2.get_Current();
				status = bo.GetField("category").get_NativeValue().toString();
//				String groupId = bo.GetField("groupid").get_NativeValue().toString();
//				ICollection ic4 = FileTools.getBussCollection("RecId",groupId,"EccGroup");
//				IEnumerator ieable4 = ic4.GetEnumerator();
//				if(ieable4.MoveNext()){
//					groupName = ((BusinessObject)ieable4.get_Current()).GetField("GroupName").get_NativeValue().toString();
//				}
				updateTime = bo.GetField("LastModDateTime").get_NativeValue().toString();
				description = bo.GetField("monitorDesc").get_NativeValue().toString();
			}
			list.add(status);
//			list.add(groupName);
			list.add(machineName);
			list.add(monitorName);
			list.add(edit);
			list.add(refresh);
			list.add(updateTime);
			list.add(description);
			list1.add(list);
		}
		return list1;
	}
	
	private void tableViewerRefresh(){
		clearItems(1,table);
		for(int i=0;i<boList.size();i++){
			TableItem item = new TableItem(table, SWT.NONE);
			BusinessObject bo = boList.get(i);
			item.setText(0, bo.GetField("ConditionName").get_NativeValue().toString());
			String grid = bo.GetField("GroupId").get_NativeValue().toString();
			String grname = getGroupNameById(grid);
			item.setText(1, grname);
			String maid = bo.GetField("MachineId").get_NativeValue().toString();
			String maname = getMachineNameById(maid);
			item.setText(2, maname);
			String moid = bo.GetField("MonitorId").get_NativeValue().toString();
			String moname = getMonitorNameById(moid);
			item.setText(3, moname);
			item.setText(4, bo.GetField("MonitorType").get_NativeValue().toString());
			item.setText(5, bo.GetField("IsVisivble").get_NativeValue().toString());
			item.setText(6, bo.GetField("Description").get_NativeValue().toString());
			item.setText(7, bo.GetField("SortMethod").get_NativeValue().toString());
			item.setText(8, bo.GetField("RecId").get_NativeValue().toString());
		}
	}
	private void clearItems(int a,Table table){
		TableItem[] itemArr = table.getItems();
		for(int i=a;i<itemArr.length;i++){
			itemArr[i].dispose();
		}
	}
	
	public void initTableViewer(){
		clearItems(1,table);
		ICollection ic = FileTools.getBussCollection("EccFilter");
		IEnumerator ieable = ic.GetEnumerator();
		while(ieable.MoveNext()){
			TableItem item = new TableItem(table, SWT.NONE);
			BusinessObject bo = (BusinessObject)ieable.get_Current();
			item.setText(0, bo.GetField("ConditionName").get_NativeValue().toString());
			String grid = bo.GetField("GroupId").get_NativeValue().toString();
			String grname = getGroupNameById(grid);
			item.setText(1, grname);
			String maid = bo.GetField("MachineId").get_NativeValue().toString();
			String maname = getMachineNameById(maid);
			item.setText(2, maname);
			String moid = bo.GetField("MonitorId").get_NativeValue().toString();
			String moname = getMonitorNameById(moid);
			item.setText(3, moname);
			item.setText(4, bo.GetField("MonitorType").get_NativeValue().toString());
			item.setText(5, bo.GetField("IsVisivble").get_NativeValue().toString());
			item.setText(6, bo.GetField("Description").get_NativeValue().toString());
			item.setText(7, bo.GetField("SortMethod").get_NativeValue().toString());
			item.setText(8, bo.GetField("RecId").get_NativeValue().toString());
			boList.add(bo);
		}
	}
	
	private String getMonitorNameById(String moid) {
		List<String> set = new ArrayList<String>();
		String[] gd = moid.split(";");
		for(int i=0;i<gd.length;i++){
			ICollection ico=FileTools.getBussCollection("RecId",gd[i],"Ecc");
			IEnumerator ien=ico.GetEnumerator();
			while(ien.MoveNext()){
				BusinessObject bo = (BusinessObject)ien.get_Current();
				set.add(bo.GetField("title").get_NativeValue().toString());
			}
		}
		String[] a = new String[set.size()];
		String s = Arrays.toString(set.toArray(a)).replaceAll(",", ";");
		s = s.substring(1, s.length()-1);
		return s;
	}

	private String getMachineNameById(String maid) {
		List<String> set = new ArrayList<String>();
		String[] gd = maid.split(";");
		for(int i=0;i<gd.length;i++){
			ICollection ico=FileTools.getBussCollection("RecId",gd[i],"RemoteMachine");
			IEnumerator ien=ico.GetEnumerator();
			while(ien.MoveNext()){
				BusinessObject bo = (BusinessObject)ien.get_Current();
				set.add(bo.GetField("ServerAddress").get_NativeValue().toString());
			}
		}
		String[] a = new String[set.size()];
		String s = Arrays.toString(set.toArray(a)).replaceAll(",", ";");
		s = s.substring(1, s.length()-1);
		return s;
	}

	private String getGroupNameById(String grid) {
		List<String> set = new ArrayList<String>();
		String[] gd = grid.split(";");
		for(int i=0;i<gd.length;i++){
			ICollection ico=FileTools.getBussCollection("RecId",gd[i],"EccGroup");
			IEnumerator ien=ico.GetEnumerator();
			while(ien.MoveNext()){
				BusinessObject bo = (BusinessObject)ien.get_Current();
				set.add(bo.GetField("GroupName").get_NativeValue().toString());
			}
		}
		String[] a = new String[set.size()];
		String s = Arrays.toString(set.toArray(a)).replaceAll(",", ";");
		s = s.substring(1, s.length()-1);
		return s;
	}

	@Override
	public void setFocus() {

	}
}

