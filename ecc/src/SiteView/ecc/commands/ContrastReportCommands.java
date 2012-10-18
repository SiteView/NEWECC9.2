package SiteView.ecc.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import SiteView.ecc.Activator;
import SiteView.ecc.editors.TrendReport;
import SiteView.ecc.editors.TrendReportInput;
import SiteView.ecc.reportchart.StatusCTIReport;
import SiteView.ecc.reportchart.TimeContrastReport;
import SiteView.ecc.tab.views.TotalTabView;

public class ContrastReportCommands implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage(); 
		if(TimeContrastReportCommands.tren==null){
			TimeContrastReportCommands.tren=new TrendReportInput();
		}
		IEditorPart editor = page.findEditor(TimeContrastReportCommands.tren);
		TrendReport.type="对比报告";
		TotalTabView.businessObj=null;
		TimeContrastReport.bo=null;
		StatusCTIReport.setData(null);
		if(editor==null){
			 try {
					page.openEditor(TimeContrastReportCommands.tren, TrendReport.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				} 
		}else{
			page.activate(editor);
			editor.setFocus();
			TrendReport.createComposite_1(((TrendReport)editor).composite_1);
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
