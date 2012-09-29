package SiteView.ecc.Action;

import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.internal.util.BundleUtility;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import system.Net.ICredentials;

import SiteView.ecc.Activator;
import SiteView.ecc.Modle.GroupModle;
import SiteView.ecc.Modle.MachineModle;
import SiteView.ecc.editors.EccControl;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.EncryptionResult;
import Siteview.IAdr;
import Siteview.IAuthenticationBundle;
import Siteview.IAuthenticationService;
import Siteview.ICounterRangeManagementService;
import Siteview.IDataStoreService;
import Siteview.IMessageService;
import Siteview.IModuleManager;
import Siteview.IMultiCurrencyService;
import Siteview.ISecurity;
import Siteview.ISettings;
import Siteview.ISystemFunctions;
import Siteview.ReportLibrary;
import Siteview.Api.AttachmentService;
import Siteview.Api.BusinessObject;
import Siteview.Api.DefinitionLibrary;
import Siteview.Api.ExportImportManager;
import Siteview.Api.IAlertNotificationService;
import Siteview.Api.IBusObEventPublisher;
import Siteview.Api.IBusinessObjectService;
import Siteview.Api.ICounterService;
import Siteview.Api.IDefinitions;
import Siteview.Api.ILinkObjects;
import Siteview.Api.INotifier;
import Siteview.Api.IPresentation;
import Siteview.Api.IResources;
import Siteview.Api.ISiteviewApi;
import Siteview.Api.ISystemInfo;
import Siteview.Api.ITokenService;
import Siteview.Api.ITopoService;
import Siteview.Api.InteractionHandler;
import Siteview.Api.LicenseService;
import Siteview.Api.LicenseType;
import Siteview.Api.MailService;
import Siteview.Api.RoleManager;
import Siteview.Api.StatisticsService;
import Siteview.Api.SystemExtensions;
import Siteview.Api.ValueResolverService;
import Siteview.Windows.Forms.ConnectionBroker;

public class AllDelete extends Action {
	public AllDelete( ) {
		URL url = BundleUtility.find(Platform.getBundle(Activator.PLUGIN_ID),
				"Image/AllDelete.bmp");
		ImageDescriptor temp = ImageDescriptor.createFromURL(url);
		setImageDescriptor(temp);
		setText("����ɾ��");
	}

	public void run() {
		EccControl.getSelete(false, "ɾ��");
	}
}
