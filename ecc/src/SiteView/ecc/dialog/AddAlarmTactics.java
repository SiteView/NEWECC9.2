package SiteView.ecc.dialog;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import SiteView.ecc.Control.EccTreeContentProvider;
import SiteView.ecc.Control.EccTreeLabelProvider;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.Modle.SiteViewEcc;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;
import Siteview.Windows.Forms.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import system.Collections.ICollection;
import system.Collections.IEnumerator;

public class AddAlarmTactics extends Dialog{
	private Text text;
	private static Table table;
    public static Set<String> set=new HashSet<String>();
    public static Set<String> set1=new HashSet<String>();
    public static List<String> list;
    public static List<List> list2=new ArrayList<List>();
    public BusinessObject bo;
    public static TableItem item;
	protected AddAlarmTactics(Shell parentShell) {
		super(parentShell);
	}
	protected void configureShell(Shell newShell) {
		newShell.setSize(500, 500);
		newShell.setLocation(200, 105);
		newShell.setText("新建策略");
		super.configureShell(newShell);
	}
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("宋体", 13, SWT.NORMAL));
		label.setBounds(0, 0, 87, 22);
		label.setText("\u62A5\u8B66\u540D\u79F0*\uFF1A");
		
		text = new Text(composite_1, SWT.BORDER);//名称
		text.setBounds(93, 0, 341, 18);
		
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		
		Group group = new Group(sashForm_1, SWT.NONE);
		group.setLayout(new FillLayout());
		final TreeViewer treeViewer = new TreeViewer(group);
		Tree tree = treeViewer.getTree();
		treeViewer.setContentProvider(new EccTreeContentProvider());
		treeViewer.setLabelProvider(new EccTreeLabelProvider());
		treeViewer.setInput(EccTreeControl.treeViewer.getInput());
		treeViewer.expandToLevel(2);
		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer
						.getSelection();
				Object element = selection.getFirstElement();
				if (element instanceof GroupModle) {
					list=new ArrayList<String>();
					GroupModle gm = (GroupModle) element;
					BusinessObject object=gm.getBo();
					String groupId=object.get_RecId();
					System.out.println(groupId);
					list.add(groupId);
					list.add("group");
					SiteViewEccMonitorType(set);
				} else if (element instanceof MachineModle) {
					list=new ArrayList<String>();
					MachineModle machine = (MachineModle) element;
					BusinessObject bb=machine.getBo();
					String machineId=bb.get_RecId();
					System.out.println(machineId);
					MachineModleMonitorType(machineId,set1);
				}else if(element instanceof SiteViewEcc){
					SiteViewEccMonitorType(set);
				}
			}
		});
		
		Group group_1 = new Group(sashForm_1, SWT.NONE);//监测器类型
		group_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		group_1.setText("\u76D1\u6D4B\u5668\u7C7B\u578B");
		
		table = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION|SWT.CHECK);
		table.setBounds(0, 10, 245, 371);
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				item=(TableItem)e.item;
				if(item.getChecked()){
					String MonitorType=item.getText();
					System.out.println(MonitorType);
					list.add(MonitorType);
					System.out.println("list:"+list);
					list2.add(list);
					System.out.println("list2:"+list2);
				}
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		sashForm_1.setWeights(new int[] {1, 1});
		sashForm.setWeights(new int[] {22, 206});
		return composite;
	}
	public static void  SiteViewEccMonitorType(Set<String> set){
		for(TableItem tableItem:table.getItems()){
			tableItem.dispose();
		}
		ICollection icoll = FileTools.getBussCollection("Ecc");
		IEnumerator ienum = icoll.GetEnumerator();
		if(ienum!=null){
			while(ienum.MoveNext()){
				BusinessObject bo = (BusinessObject) ienum.get_Current();
				if(bo!=null){
					String Type = bo.GetField("EccType").get_NativeValue()
							.toString();
					set.add(Type);
				}
			}
		}
		for(String string:set){
			TableItem tableItem = new TableItem(table, SWT.NONE);
			for (int i = 0; i < set.size(); i++) {
				tableItem.setText(i, string);
			}
		}
	}
	public static void MachineModleMonitorType(String machineId,Set<String> set1){
		for(TableItem tableItem:table.getItems()){
			tableItem.dispose();
		}
		ICollection icoll = FileTools.getBussCollection("Machine", machineId, "Ecc");
		IEnumerator ienum = icoll.GetEnumerator();
		if(ienum!=null){
			while(ienum.MoveNext()){
				BusinessObject bo1 = (BusinessObject) ienum.get_Current();
				if(bo1!=null){
					String Type = bo1.GetField("EccType").get_NativeValue()
							.toString();
					set1.add(Type);
				}
			}
		}
		for(String string:set1){
			TableItem tableItem = new TableItem(table, SWT.NONE);
			for (int i = 0; i < set1.size(); i++) {
				tableItem.setText(i, string);
			}
			
		}
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.setBackground(EccTreeControl.color);
		Button subButton = createButton(parent, IDialogConstants.OK_ID, "保存",
				true);
		Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
				"取消", true);
	}
	protected void buttonPressed(int buttonId){
		if(buttonId==IDialogConstants.OK_ID){
			ICollection ico=FileTools.getBussCollection("EccAlarmTactic");
			IEnumerator ienum=ico.GetEnumerator();
			while(ienum.MoveNext()){
				BusinessObject businessObject=(BusinessObject)ienum.get_Current();
				if(businessObject!=null){
					String TacticName=businessObject.GetField("TacticName").get_NativeValue().toString();
					if(text.getText().equals(TacticName)){
						MessageBox messageBox=new MessageBox();
						messageBox.Show("该告警名称已经存在!", "提示", SWT.OK);
						return;
					}
				}
			}
			if(text.getText().isEmpty()){
				MessageBox messageBox=new MessageBox();
				messageBox.Show("请输入告警策略的名称!", "提示", SWT.OK);
				return;
			}
			
			for(List a:list2){
				System.out.println(a);
				List<String> list3=a;
				for(String b:list3){
					System.out.println(b);
				}
//				bo=ConnectionBroker.get_SiteviewApi()//得到数据库表
//				.get_BusObService().Create("EccAlarmTactic");
//				bo.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
//						true);//将数据存储到数据
			}
			
		}
		this.close();
	}
}
