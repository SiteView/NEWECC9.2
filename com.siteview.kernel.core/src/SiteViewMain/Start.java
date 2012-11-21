package SiteViewMain;
import java.io.IOException;

import COM.dragonflow.Api.ApiRmiServer;
import COM.dragonflow.Api.ECFServer;
import COM.dragonflow.Log.LogManager;
import COM.dragonflow.SiteView.Platform;
import COM.dragonflow.Utils.FileTools;
import SiteViewMain.SiteViewSupport;
public class Start {
	 
	public Start()
	 {
	 }
	public static void main(String args[])
    throws IOException
    {
        SiteViewSupport.argv = args;
        try {
        	Class.forName("COM.dragonflow.Api.ECFServer");
			//ECFServer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
        try
        {
            SiteViewSupport.InitProcess();
            SiteViewSupport.InitProcess2();
            SiteViewSupport.StartProcess();/*∆Ù∂Øº‡≤‚œﬂ≥Ã*/
            ApiRmiServer server = new ApiRmiServer();
            SiteViewSupport.WaitForProcess();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            LogManager.log("Error", Platform.productName + " unexpected shutdown: " + exception);
        }
        SiteViewSupport.ShutdownProcess();
    }

}
