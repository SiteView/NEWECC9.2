package SiteView.ecc;

import org.eclipse.team.ui.history.IHistoryView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import SiteView.ecc.view.EccTreeControl;

public class perspective implements IPerspectiveFactory {
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.addView(EccTreeControl.ID, IPageLayout.LEFT, 0.3f, layout.getEditorArea());

	}

}
