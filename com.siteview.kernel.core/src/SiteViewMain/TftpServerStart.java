package SiteViewMain;

public class TftpServerStart {
  public static void StartServer() throws InterruptedException{
	  TftpServerAgent tftp_srv = new TftpServerAgent();
      tftp_srv.setDaemon(true);
      tftp_srv.start();            
//      tftp_srv.join();  
  }
}
