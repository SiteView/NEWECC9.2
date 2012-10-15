package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import siteview.windows.forms.ImageHelper;
import system.Collections.ICollection;
import system.Collections.IEnumerator;
import SiteView.ecc.Activator;
import SiteView.ecc.Modle.AbsoluteTimeModel;
import SiteView.ecc.Modle.AbsoluteTimeProjectModel;
import SiteView.ecc.dialog.AbsoluteDetail;
import SiteView.ecc.dialog.AbsoluteTimeEditor;
import SiteView.ecc.dialog.AddAbsoluteTime;
import SiteView.ecc.dialog.AddRelativeTime;
import SiteView.ecc.dialog.AddTimeQuantum;
import SiteView.ecc.dialog.QuantumDetail;
import SiteView.ecc.dialog.QuantumTimeEditor;
import SiteView.ecc.dialog.RelativeTimeEditor;
import SiteView.ecc.tools.FileTools;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import Siteview.Windows.Forms.MessageBox;

import org.eclipse.swt.events.SelectionAdapter;

public class AbsoluteTime extends EditorPart {
	public static final String absoluteID = "SiteView.ecc.editors.AbsoluteTime";
	public static TableViewer tableViewer;
	public static Table table;
	public static TableItem tableItem;
	public static String name;
	Label lblNewLabel;

	public AbsoluteTime() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
		name = AbsoluteTimeInput.type;
		System.out.println("name:"+name);
		this.setPartName(input.getName());

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		sashForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		createLabel();

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite_1.setLayout(null);

		Button btnNewButton = new Button(composite_1, SWT.NONE);//添加
		btnNewButton.setBounds(10, 5, 36, 22);
		btnNewButton.setText("\u6DFB\u52A0");
		btnNewButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if ("absolute".equals(name)) {
					AddAbsoluteTime aat = new AddAbsoluteTime(null);
					aat.open();
				} else if ("quantum".equals(name)) {
					AddTimeQuantum atq = new AddTimeQuantum(null);
					atq.open();
				} else if ("ralative".equals(name)) {
					AddRelativeTime art = new AddRelativeTime(null);
					art.open();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		
		Button button = new Button(composite_1, SWT.NONE);//删除
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(tableItem==null){
					MessageBox messageBox=new MessageBox();
					messageBox.Show("你还没有选定想删除的项!", "提示", SWT.OK);
				}else{
					ICollection ico=FileTools.getBussCollection("TaskName",tableItem.getText(0), "EccTaskPlan");
					IEnumerator ien=ico.GetEnumerator();
					while(ien.MoveNext()){
						((BusinessObject)ien.get_Current()).DeleteObject(ConnectionBroker.get_SiteviewApi());//删除数据库数据
					}
					tableItem.dispose();//删除表单数据
				}
			}
		});
		button.setBounds(52, 5, 36, 22);
		button.setText("\u5220\u9664");
		
		Button button_1 = new Button(composite_1, SWT.NONE);//刷新
		button_1.setBounds(94, 5, 36, 22);
		button_1.setText("\u5237\u65B0");
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				tableViewer.refresh();
			}
		});

		Button button_2 = new Button(composite_1, SWT.NONE);//帮助
		button_2.setBounds(136, 5, 36, 22);
		button_2.setText("\u5E2E\u52A9");
        
			tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION
					| SWT.CHECK);
			createTable();
			
		sashForm.setWeights(new int[] { 1, 2, 34 });
	}

	public void setFocus() {
	}

	public void createLabel() {
		if ("absolute".equals(name)) {
			lblNewLabel.setText("绝对时间任务计划");
		} else if ("quantum".equals(name)) {
			lblNewLabel.setText("时间段任务计划");
		} else if ("ralative".equals(name)) {
			lblNewLabel.setText("相对时间任务计划");
		}
	}
	public void createTable(){
		if("absolute".equals(name)){
			table = tableViewer.getTable();
			table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			table.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					tableItem = (TableItem) e.item;
					tableItem.setChecked(true);
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tableItem.setChecked(false);
				}
			});

			final TableCursor cursor = new TableCursor(table, SWT.NONE);
			cursor.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent e) {
					for (TableItem ta : table.getItems()) {
						if (!ta.equals(tableItem)) {
							ta.setChecked(false);
						}
					}
					int column=cursor.getColumn();
					if(column==2){//编辑
							String name=tableItem.getText();
							AbsoluteTimeEditor editor = new AbsoluteTimeEditor(null,name,tableItem);
							editor.open();
							
					}
					if(column==0||column==1){
						String name=tableItem.getText();
						AbsoluteDetail absoluteDetail=new AbsoluteDetail(null,name);
						absoluteDetail.open();
					}
				}
				public void mouseDown(MouseEvent e) {

				}
				public void mouseDoubleClick(MouseEvent e) {

				}
			});
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.LEFT);
			tblclmnNewColumn_1.setWidth(150);
			tblclmnNewColumn_1.setText("\u540D\u79F0");

			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.LEFT);
			tblclmnNewColumn.setWidth(150);
			tblclmnNewColumn.setText("\u63CF\u8FF0");

			TableColumn tableColumn_1 = new TableColumn(table, SWT.LEFT);
			tableColumn_1.setWidth(150);
			tableColumn_1.setText("\u7F16\u8F91");
			
				ICollection icoll = FileTools.getBussCollection("EccTaskPlan");
				IEnumerator ienum = icoll.GetEnumerator();
				if (ienum != null) {
					while (ienum.MoveNext()) {
						BusinessObject bo = (BusinessObject) ienum.get_Current();
						if (bo != null&&bo.GetField("Model").get_NativeValue().toString().equals("绝对时间任务计划")) {
							String TaskName = bo.GetField("TaskName").get_NativeValue()
									.toString();
							String Instruction = bo.GetField("Instruction")
									.get_NativeValue().toString();

							TableItem tableItem_1 = new TableItem(table, SWT.NONE);
							tableItem_1.setText(0, TaskName);
							tableItem_1.setText(1, Instruction);
							tableItem_1.setImage(2, ImageHelper.LoadImage(
									Activator.PLUGIN_ID, "icons/edit.jpg"));
						}
					}
				}
		}else if("quantum".equals(name)){
			table = tableViewer.getTable();
			table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			table.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					tableItem = (TableItem) e.item;
					tableItem.setChecked(true);
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tableItem.setChecked(false);
				}
			});

			final TableCursor cursor = new TableCursor(table, SWT.NONE);
			cursor.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent e) {
					for (TableItem ta : table.getItems()) {
						if (!ta.equals(tableItem)) {
							ta.setChecked(false);
						}
					}
					int column=cursor.getColumn();
					if(column==2){//编辑
						String name=tableItem.getText();
						QuantumTimeEditor editor=new QuantumTimeEditor(null,name,tableItem);
						editor.open();
					}
					if(column==0||column==1){
						String name=tableItem.getText();
						QuantumDetail quantumDetail=new QuantumDetail(null,name);
						quantumDetail.open();
					}
				}
				public void mouseDown(MouseEvent e) {

				}
				public void mouseDoubleClick(MouseEvent e) {

				}
			});
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.LEFT);
			tblclmnNewColumn_1.setWidth(150);
			tblclmnNewColumn_1.setText("\u540D\u79F0");

			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.LEFT);
			tblclmnNewColumn.setWidth(150);
			tblclmnNewColumn.setText("\u63CF\u8FF0");

			TableColumn tableColumn_1 = new TableColumn(table, SWT.LEFT);
			tableColumn_1.setWidth(150);
			tableColumn_1.setText("\u7F16\u8F91");
			
				ICollection icoll = FileTools.getBussCollection("EccTaskPlan");
				IEnumerator ienum = icoll.GetEnumerator();
				if (ienum != null) {
					while (ienum.MoveNext()) {
						BusinessObject bo = (BusinessObject) ienum.get_Current();
						if (bo != null&&bo.GetField("Model").get_NativeValue().toString().equals("时间段计划")) {
							String TaskName = bo.GetField("TaskName").get_NativeValue()
									.toString();
							String Instruction = bo.GetField("Instruction")
									.get_NativeValue().toString();

							TableItem tableItem_1 = new TableItem(table, SWT.NONE);
							tableItem_1.setText(0, TaskName);
							tableItem_1.setText(1, Instruction);
							tableItem_1.setImage(2, ImageHelper.LoadImage(
									Activator.PLUGIN_ID, "icons/edit.jpg"));
						}
					}
				}
		}else if("ralative".equals(name)){
			table = tableViewer.getTable();
			table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			table.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					tableItem = (TableItem) e.item;
					tableItem.setChecked(true);
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					tableItem.setChecked(false);
				}
			});

			final TableCursor cursor = new TableCursor(table, SWT.NONE);
			cursor.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent e) {
					for (TableItem ta : table.getItems()) {
						if (!ta.equals(tableItem)) {
							ta.setChecked(false);
						}
					}
					int column=cursor.getColumn();
					if(column==2){//编辑
						String name=tableItem.getText();
                        RelativeTimeEditor relativeEditor=new RelativeTimeEditor(null,name);
                        relativeEditor.open();
					}
				}
				public void mouseDown(MouseEvent e) {

				}
				public void mouseDoubleClick(MouseEvent e) {

				}
			});
			
			TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.LEFT);
			tblclmnNewColumn_1.setWidth(150);
			tblclmnNewColumn_1.setText("\u540D\u79F0");

			TableColumn tblclmnNewColumn = new TableColumn(table, SWT.LEFT);
			tblclmnNewColumn.setWidth(150);
			tblclmnNewColumn.setText("\u63CF\u8FF0");

			TableColumn tableColumn_1 = new TableColumn(table, SWT.LEFT);
			tableColumn_1.setWidth(150);
			tableColumn_1.setText("\u7F16\u8F91");
			
				ICollection icoll = FileTools.getBussCollection("EccTaskPlan");
				IEnumerator ienum = icoll.GetEnumerator();
				if (ienum != null) {
					while (ienum.MoveNext()) {
						BusinessObject bo = (BusinessObject) ienum.get_Current();
						if (bo != null&&bo.GetField("Model").get_NativeValue().toString().equals("相对时间计划")) {
							String TaskName = bo.GetField("TaskName").get_NativeValue()
									.toString();
							String Instruction = bo.GetField("Instruction")
									.get_NativeValue().toString();

							TableItem tableItem_1 = new TableItem(table, SWT.NONE);
							tableItem_1.setText(0, TaskName);
							tableItem_1.setText(1, Instruction);
							tableItem_1.setImage(2, ImageHelper.LoadImage(
									Activator.PLUGIN_ID, "icons/edit.jpg"));
						}
					}
				}
		}
	}
public static void addAbsoluteData(){//添加数据后表单刷新
	for(TableItem item:table.getItems()){
		item.dispose();
	}
	ICollection icoll = FileTools.getBussCollection("EccTaskPlan");
	IEnumerator ienum = icoll.GetEnumerator();
	if (ienum != null) {
		while (ienum.MoveNext()) {
			BusinessObject bo = (BusinessObject) ienum.get_Current();
			if (bo != null&& bo.GetField("Model").get_NativeValue().toString().equals("绝对时间任务计划")) {
				String TaskName = bo.GetField("TaskName").get_NativeValue()
						.toString();
				String Instruction = bo.GetField("Instruction")
						.get_NativeValue().toString();
                	TableItem tableItem_2 = new TableItem(table, SWT.NONE);
    				tableItem_2.setText(0, TaskName);
    				tableItem_2.setText(1, Instruction);
    				tableItem_2.setImage(2, ImageHelper.LoadImage(
    						Activator.PLUGIN_ID, "icons/edit.jpg"));
			}
		}
	}
}
public static void addQuantumData(){//添加数据后表单刷新
	for(TableItem item:table.getItems()){
		item.dispose();
	}
	ICollection icoll = FileTools.getBussCollection("EccTaskPlan");
	IEnumerator ienum = icoll.GetEnumerator();
	if (ienum != null) {
		while (ienum.MoveNext()) {
			BusinessObject bo = (BusinessObject) ienum.get_Current();
			if (bo != null&& bo.GetField("Model").get_NativeValue().toString().equals("时间段计划")) {
				String TaskName = bo.GetField("TaskName").get_NativeValue()
						.toString();
				String Instruction = bo.GetField("Instruction")
						.get_NativeValue().toString();
                	TableItem tableItem_2 = new TableItem(table, SWT.NONE);
    				tableItem_2.setText(0, TaskName);
    				tableItem_2.setText(1, Instruction);
    				tableItem_2.setImage(2, ImageHelper.LoadImage(
    						Activator.PLUGIN_ID, "icons/edit.jpg"));
			}
		}
	}
}
public static void addRelativeData(){//添加数据后表单刷新
	for(TableItem item:table.getItems()){
		item.dispose();
	}
	ICollection icoll = FileTools.getBussCollection("EccTaskPlan");
	IEnumerator ienum = icoll.GetEnumerator();
	if (ienum != null) {
		while (ienum.MoveNext()) {
			BusinessObject bo = (BusinessObject) ienum.get_Current();
			if (bo != null&& bo.GetField("Model").get_NativeValue().toString().equals("相对时间计划")) {
				String TaskName = bo.GetField("TaskName").get_NativeValue()
						.toString();
				String Instruction = bo.GetField("Instruction")
						.get_NativeValue().toString();
                	TableItem tableItem_2 = new TableItem(table, SWT.NONE);
    				tableItem_2.setText(0, TaskName);
    				tableItem_2.setText(1, Instruction);
    				tableItem_2.setImage(2, ImageHelper.LoadImage(
    						Activator.PLUGIN_ID, "icons/edit.jpg"));
			}
		}
	}
}
}
