/*
 * 
 * Created on 2005-3-7 1:00:23
 *
 * DiskSpaceMonitor.java
 *
 * History:
 *
 */
package COM.dragonflow.StandardMonitor;

/**
 * Comment for <code>DiskSpaceMonitor</code>
 * 
 * @author 
 * @version 0.0
 *
 *
 */
import COM.dragonflow.HTTP.HTTPRequest;
import COM.dragonflow.Log.LogManager;
import COM.dragonflow.Page.CGI;
import COM.dragonflow.Page.WmiService;
import COM.dragonflow.Properties.*;
import COM.dragonflow.SiteView.*;
import COM.dragonflow.SiteViewException.SiteViewException;
import java.net.URLEncoder;
import java.util.Vector;
import jgl.Array;
import jgl.HashMap;

public class DiskSpaceMonitor extends ServerMonitor
{

 static ScalarProperty pDisk;
 static ScalarProperty pFileSystem;
 static StringProperty pPercentFull;
 static StringProperty pFreeSpace;

 public DiskSpaceMonitor()
 {
 }

 StringProperty diskProperty()
 {
     return getPropertyObject("_disk");
 }

 protected boolean update()
 {
	 System.out.println("调用了 diskSpace update方法////////////////////////////////////////");
     String s = getProperty(diskProperty());
     System.out.println("......磁盘属性......"+s);
     Array array = null;
     if(monitorDebugLevel == 3)
     {
         array = new Array();
     }
     HashMap m = this.getClassProperties();
//     System.out.println("监听器中的hashMap++++++++++++++++++++"+m);
//     System.out.println("*************************LogPropertites***********----------------"+this.getLogProperties());
//     System.out.println(m.get("elements").getClass());
     
//     this.getClassProperties().get("_class").toString();
     String s1 = getProperty(pMachineName);
//     System.out.println(".....设备名称..........."+s1);
//     System.out.println("............设备............"+Machine.OS_NAME[Machine.getOS(currentStatus)]);
     long al[] = null;
     Machine machine = Machine.getMachine(s1);
     System.out.println("......Machine.getOSName('_os')......"+Machine.getOSName("_os"));
     System.out.println("..........machine........."+machine);
     System.out.println(".....设备名称..........."+s1);
     if("NT".equals(Machine.getOSName("_os"))&&machine!=null&&!s1.startsWith("remote:")){
    	 String address = machine.getSetting("_host").substring(2);
    	 String user = (String)machine.getSetting("_login");
         user = user.replaceAll("\\d", "").replaceAll("\\.", "").replaceAll("\\\\", "");
     	 String pw = (String)machine.getSetting("_password");
     	System.out.println("address................"+address);
    	System.out.println("user................"+user);
    	System.out.println("pw................"+pw);
    	 WmiService ws = new WmiService(address);
    	 ws.connect("", user, pw);
    	 al = ws.getDiskSpace(s);
     	 ws.disconnect();
//    	 al = Platform.getDiskFull(s, s1, this, array);
     }else{
    	 al = Platform.getDiskFull(s, s1, this, array);
     }
//     long al[] = Platform.getDiskFull(s, s1, this, array);
     long l = al[0];//使用率
     long l1 = al[2];//总大小(字节)
     long l2 = l1 - al[1];//剩余空间
     String s2 = "" + l2 / 0x100000L;//剩余空间(MB)
     String s3 = "" + l1 / 0x100000L;//总大小(MB)
     if(stillActive())
     {
         synchronized(this)
         {
             if(l == -1L)
             {
                 setProperty(pPercentFull, "n/a");
                 setProperty(pFreeSpace, "n/a");
                 setProperty(pMeasurement, 0);
                 setProperty(pStateString, "no data");
                 setProperty(pNoData, "n/a");
                 if(monitorDebugLevel == 3 && array != null)
                 {
                     StringBuffer stringbuffer = new StringBuffer();
                     for(int i = 0; i < array.size(); i++)
                     {
                         stringbuffer.append(array.at(i) + "\n");
                     }

                     LogManager.log("Error", "DiskSpaceMonitor: " + getFullID() + " failed, output:\n" + stringbuffer);
                 }
             } else
             {
                 setProperty(pPercentFull, l);
                 setProperty(pFreeSpace, l2 / 0x100000L);
                 setProperty(pMeasurement, getMeasurement(pPercentFull));
                 setProperty(pStateString, l + "% full, " + s2 + "MB free, " + s3 + "MB total");
             }
         }
         System.out.println("*************************LogPropertites***********----------------"+this.getLogProperties());
     }
     return true;
 }

 public Array getLogProperties()
 {
     Array array = super.getLogProperties();
     array.add(pPercentFull);
     array.add(pFreeSpace);
     return array;
 }

 public Vector getScalarValues(ScalarProperty scalarproperty, HTTPRequest httprequest, CGI cgi)
     throws SiteViewException
 {
     if(scalarproperty == diskProperty())
     {
         return Platform.getDisks(Machine.getFullMachineID(getProperty(pMachineName), httprequest));
     } else
     {
         return super.getScalarValues(scalarproperty, httprequest, cgi);
     }
 }

 public String verify(StringProperty stringproperty, String s, HTTPRequest httprequest, HashMap hashmap)
 {
     if(stringproperty == diskProperty())
     {
         return s;
     } else
     {
         return super.verify(stringproperty, s, httprequest, hashmap);
     }
 }

 public String getTestURL()
 {
     HashMap hashmap = MasterConfig.getMasterConfig();
     int i = Machine.getOS(getProperty(pMachineName));
     if(Platform.isWindows(i))
     {
         String s = URLEncoder.encode(getProperty(pMachineName));
         String s1 = "/SiteView/cgi/go.exe/SiteView?page=perfCounter&counterObject=LogicalDisk&machineName=" + s;
         return s1;
     } else
     {
         return null;
     }
 }

 static 
 {
     pDisk = new ScalarProperty("_disk", true);
     pDisk.setWindowsPlatforms();
     pDisk.setDisplayText("Disk", "the local disk drive to be monitored<br><b>NOTE: If this field is blank, click <a href=\"/SiteView/docs/DiskMon.htm#disk\" TARGET=\"Help\">here</a> to trouble shoot the problem.");
     pDisk.setParameterOptions(true, 2, false);
     pFileSystem = new ScalarProperty("_disk", true);
     pFileSystem.setUnixPlatforms();
     pFileSystem.setDisplayText("Filesystem", "the filesystem to be monitored");
     pFileSystem.setParameterOptions(true, 2, false);
     pPercentFull = new PercentProperty("percentFull");
     pPercentFull.setLabel("percent full");
     pPercentFull.setStateOptions(1);
     pFreeSpace = new NumericProperty("freeSpace", "0", "MB");
     pFreeSpace.setLabel("MB free");
     pFreeSpace.setStateOptions(2);
     StringProperty astringproperty[] = {
         pPercentFull, pFreeSpace, pDisk, pFileSystem
     };
     addProperties("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", astringproperty);
     addClassElement("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", Rule.stringToClassifier("percentFull > 98\terror", true));
     addClassElement("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", Rule.stringToClassifier("percentFull > 95\twarning", true));
     addClassElement("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", Rule.stringToClassifier("percentFull == n/a\terror"));
     addClassElement("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", Rule.stringToClassifier("always\tgood"));
     setClassProperty("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", "description", "Determines the amount of space used on a disk.");
     setClassProperty("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", "help", "DiskMon.htm");
     setClassProperty("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", "title", "Disk Space");
     setClassProperty("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", "class", "DiskSpaceMonitor");
     setClassProperty("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", "target", "_disk");
     setClassProperty("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", "topazName", "Disk Space");
     setClassProperty("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", "topazType", "System Resources");
     setClassProperty("COM.dragonflow.StandardMonitor.DiskSpaceMonitor", "classType", "server");
 }
}
