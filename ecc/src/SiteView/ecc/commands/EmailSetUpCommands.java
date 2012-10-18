package SiteView.ecc.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import SiteView.ecc.Activator;
import SiteView.ecc.editors.EmailSetUp;
import SiteView.ecc.editors.EmailSetUpInput;

public class EmailSetUpCommands implements IHandler {
	public static EmailSetUpInput e=new EmailSetUpInput();
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
		 IEditorPart editor = page.findEditor(e); 
		try {
			if(editor==null){
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(e, EmailSetUp.ID);
			}else{
				page.activate(editor);
			}
		} catch (PartInitException e) {
			e.printStackTrace();
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
