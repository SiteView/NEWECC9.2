package SiteView.ecc.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import SiteView.ecc.Control.GroupTreeContentProvider;
import SiteView.ecc.Control.GroupTreeLabelProvider;
import SiteView.ecc.Modle.MonitorModle;
import SiteView.ecc.bundle.MassageAlarmBundle;
import SiteView.ecc.data.SiteViewData;
import SiteView.ecc.reportchart.StatusCTIReport;
import SiteView.ecc.reportchart.TimeContrastReport;
import SiteView.ecc.tab.views.TotalTabView;
import SiteView.ecc.view.EccTreeControl;
import SiteView.ecc.views.ContrastReportView;
import SiteView.ecc.views.TrendReportView;

public class TrendReport extends EditorPart {
	public static String type;
	public static String ID="SiteView.ecc.editors.TrendReport";
	public Composite composite_1 ;
	public Label label;
	public TrendReport() {
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
		// TODO Auto-generated method stub
		this.setSite(site);
		this.setInput(input);
		this.setPartName(type);
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
		parent.setBackground(EccTreeControl.color);
		parent.setLayout(new FillLayout(SWT.VERTICAL));
		
		SashForm sashForm = new SashForm(parent, SWT.NONE);
		sashForm.setBackground(EccTreeControl.color);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout());
		composite.setBackground(EccTreeControl.color);
		TreeViewer treeViewer = new TreeViewer(composite,SWT.NONE);
		Tree tree = treeViewer.getTree();
		tree.setBackground(EccTreeControl.color);
		tree.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if(e.item.getData() instanceof MonitorModle){
					TotalTabView.businessObj=((MonitorModle)e.item.getData()).getBo();
					TimeContrastReport.bo=((MonitorModle)e.item.getData()).getBo();
					StatusCTIReport.setData(((MonitorModle)e.item.getData()).getBo());
				}else{
					MessageDialog.openInformation(new Shell(), "提示","请选择选择监测器！");
					TotalTabView.businessObj=null;
					TimeContrastReport.bo=null;
					StatusCTIReport.setData(null);
				}
				createComposite_1(composite_1);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackground(EccTreeControl.color);
		createComposite_1(composite_1);
		sashForm.setWeights(new int[] {123, 468});
		
		treeViewer.setContentProvider(new GroupTreeContentProvider());
		treeViewer.setLabelProvider(new GroupTreeLabelProvider());
		treeViewer.setInput(SiteViewData.CreatTreeData());
		treeViewer.expandToLevel(2);
		parent.layout();
	}
	public void setFocus() {
		if(type.equals("趋势报告")){
			this.setPartName("趋势报告");
		}else if(type.equals("时段对比报告")){
			this.setPartName("时段对比报告");
		}else if(type.equals("对比报告")){
			this.setPartName("对比报告");
		}else if(type.equals("状态统计报告")){
			this.setPartName("状态统计报告");
		}
		
	}
	public static void createComposite_1(Composite composite_1){
		for(Control c:composite_1.getChildren()){
			c.dispose();
		}
		if(type.equals("趋势报告")){
			TrendReportView  trv = new TrendReportView();
			trv.createPartControl(composite_1);
		}else if(type.equals("时段对比报告")){
			TimeContrastReport time=new TimeContrastReport();
			time.create(composite_1);
		}else if(type.equals("对比报告")){
			ContrastReportView crv = new ContrastReportView();
			crv.createPartControl(composite_1);
		}else if(type.equals("状态统计报告")){
			StatusCTIReport status=new StatusCTIReport();
			status.create(composite_1);
		}
	}
}
