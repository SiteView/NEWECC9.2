/*
 *
 * Created on 2005-2-28 6:56:56
 *
 * FrameFile.java
 *
 * History:
 *
 */
package COM.dragonflow.Properties;

/**
 * Comment for <code>FrameFile</code>
 *
 * @author
 * @version 0.0
 *
 *
 */
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

import jgl.Array;
import jgl.HashMap;
import jgl.Sorting;
import my.util.email.MailSenderInfo;
import my.util.email.SimpleMailSender;
import my.util.sound.TestMusic;

import org.apache.commons.httpclient.HttpException;

import system.Collections.ICollection;
import system.Collections.IEnumerator;
import system.Xml.XmlElement;

import COM.dragonflow.Log.LogManager;
import COM.dragonflow.SiteView.DetectConfigurationChange;
import COM.dragonflow.SiteView.MonitorGroup;
import COM.dragonflow.SiteView.Platform;
import COM.dragonflow.SiteView.PlatformNew;
import COM.dragonflow.SiteView.SiteViewGroup;
import COM.dragonflow.Utils.FileTools;
import COM.dragonflow.Utils.FileUtils;
import COM.dragonflow.Utils.I18N;
import COM.dragonflow.Utils.TempFileManager;
import COM.dragonflow.Utils.TextUtils;
import COM.dragonflow.itsm.data.Config;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Api.ISiteviewApi;
import Siteview.Api.Relationship;

// Referenced classes of package COM.dragonflow.Properties:
// LessEqualPropertyName, StringProperty, HashMapOrdered

public class FrameFile {

	public static final boolean SORTED = true;

	public static final boolean UNSORTED = false;

	static boolean allowNestedFrames = false;

	private static boolean readMasterConfig = false;

	public static boolean forceMangleInited = false;

	public static boolean forceMangleOnRead = false;

	public static boolean forceMangleOnWrite = false;
	public static String[] MonitorCounterGroups = { "SQLServerMonitor",
			"WindowsMediaMonitor", "ADPerformanceMonitor", "ASPMonitor",
			"ColdFusionMonitor", "IISServerMonitor", "RealMonitor",
			"OracleDBMonitor", "PatrolMonitor", "TuxedoMonitor",
			"HealthUnixServerMonitor", "LogEventHealthMonitor",
			"MediaPlayerMonitorBase", "MonitorLoadMonitor",
			"RealMediaPlayerMonitor", "WindowsMediaPlayerMonitor",
			"DynamoMonitor", "CheckPointMonitor", "WebLogic5xMonitor",
			"BrowsableSNMPMonitor", "CiscoMonitor", "F5Monitor",
			"IPlanetAppServerMonitor", "IPlanetWSMonitor",
			"NetworkBandwidthMonitor", "VMWareMonitor", "AssetMonitor",
			"CPUMonitor", "NTCounterMonitor", "ScriptMonitor",
			"UnixCounterMonitor", "DB2Monitor", "SAPMonitor",
			"BrowsableNTCounterMonitor", "BrowsableWMIMonitor",
			"DatabaseCounterMonitor", "IPMIMonitor", "OracleJDBCMonitor",
			"SiebelMonitor", "WebLogic6xMonitor", "WebSphereMonitor",
			"InterfaceMonitor" };

	public FrameFile() {
	}

	public static void writeToFile(String s, HashMap hashmap)
			throws IOException {
		writeToFile(s, hashmap, false);
	}

	public static void writeToFile(String s, HashMap hashmap, boolean flag)
			throws IOException {
		Array array = new Array();
		array.add(hashmap);
		writeToFile(s, array, null, false, flag);
	}

	public static void writeToFile(String s, Array array) throws IOException {
		writeToFile(s, array, false);
	}

	public static void writeToFile(String s, Array array, boolean flag)
			throws IOException {
		writeToFile(s, array, null, false, flag);
	}

	public static void writeToFile(String s, Array array, String s1,
			boolean flag) throws IOException {
		writeToFile(s, array, s1, flag, false);
	}

	public static void removeOlderBackFiles(int i) {
		File file = new File(Platform.getRoot() + "/groups");
		String as[] = file.list();
		for (int j = 0; as != null && j < as.length; j++) {
			if (as[j].indexOf("bak.") < 0) {
				continue;
			}
			int k = TextUtils.toInt(as[j].substring(as[j].indexOf("bak.") + 4));
			if (i > 1 && k <= i) {
				continue;
			}
			File file1 = new File(Platform.getRoot() + "/groups/" + as[j]);
			if (k == 1) {
				String s = Platform.getRoot() + "/groups/" + as[j];
				s = s.substring(0, s.lastIndexOf("."));
				File file2 = new File(s);
				if (file2.exists()) {
					file2.delete();
				}
				file1.renameTo(file2);
			} else {
				file1.delete();
			}
		}

	}

	private static int getFileNum(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s.substring(s.lastIndexOf(".") + 1));
		} catch (NumberFormatException numberformatexception) {
		}
		return i;
	}

	private static File pushBackup(File file, String s, String s1, int i,
			boolean flag) throws IOException {
		File file1 = new File(s1);
		if (i > 1) {
			String s2 = new String(s);
			int j = s2.lastIndexOf("/");
			int k = s2.lastIndexOf("\\");
			s2 = s2.substring(0, Math.max(j, k) + 1);
			String s3 = s.substring(Math.max(j, k) + 1);
			File file2 = new File(s2);
			String as[] = file2.list();
			Array array = new Array();
			for (int l = 0; l < as.length; l++) {
				if (as[l].indexOf(s3 + ".bak.") >= 0) {
					array.add(as[l]);
				}
			}

			File file3 = new File(s2 + s3 + ".bak");
			int i1 = array == null ? 0 : array.size();
			Array array1 = new Array();
			if (file3.exists()) {
				array1.add(s3 + ".bak");
				if (i1 == 0) {
					array1.add(s3 + ".bak.1");
				}
				for (int j1 = 1; j1 < i1 + 1; j1++) {
					array1.add(array.at(j1 - 1));
				}

				if (i1 < i) {
					array1.add(s3 + ".bak." + ++i1);
				}
				array = array1;
			}
			for (int k1 = 1; k1 < array.size(); k1++) {
				int i2 = getFileNum((String) array.at(k1));
				String s4 = new String((String) array.at(k1));
				int k2;
				for (k2 = k1 - 1; k2 >= 0
						&& i2 > getFileNum((String) array.at(k2)); k2--) {
					array.put(k2 + 1, array.at(k2));
				}

				array.put(k2 + 1, s4);
			}

			for (int l1 = 0; l1 < array.size(); l1++) {
				File file4 = new File(s2 + (String) array.at(l1));
				int j2 = array.size() - l1;
				if (j2 >= i && file4.exists()) {
					file4.delete();
				}
				if (j2 < i) {
					File file5 = new File(s2 + s3 + ".bak." + (j2 + 1));
					file4.renameTo(file5);
				}
			}

			file1 = new File(s + ".bak.1");
			if (file1.exists()) {
				file1.delete();
			}
		}
		if (file.exists() && flag) {
			try {
				FileUtils.copyFileThrow(file, file1);
			} catch (IOException ioexception) {
				file1.delete();
				throw new IOException(ioexception.toString());
			}
		}
		return file1;
	}

	private static boolean popBackup(File file, File file1, String s, int i)
			throws IOException {
		boolean flag = true;
		if (file1.exists()) {
			FileUtils.copyFileThrow(file1, file);
		} else {
			flag = false;
		}
		if (i > 1) {
			String s1 = new String(s);
			int j = s1.lastIndexOf("/");
			int k = s1.lastIndexOf("\\");
			s1 = s1.substring(0, Math.max(j, k) + 1);
			String s2 = s.substring(Math.max(j, k) + 1);
			File file2 = new File(s1);
			String as[] = file2.list();
			Array array = new Array();
			for (int l = 0; l < as.length; l++) {
				if (as[l].indexOf(s2 + ".bak.") >= 0) {
					array.add(as[l]);
				}
			}

			File file3 = new File(s1 + s2 + ".bak");
			if (file3.exists()) {
				return flag;
			}
			for (int i1 = 1; i1 < array.size(); i1++) {
				int k1 = getFileNum((String) array.at(i1));
				String s3 = new String((String) array.at(i1));
				int l1;
				for (l1 = i1 - 1; l1 >= 0
						&& k1 < getFileNum((String) array.at(l1)); l1--) {
					array.put(l1 + 1, array.at(l1));
				}

				array.put(l1 + 1, s3);
			}

			for (int j1 = 0; j1 < array.size() - 1; j1++) {
				File file4 = new File(s1 + (String) array.at(j1));
				if (file4.exists()) {
					file4.delete();
				}
				File file5 = new File(s1 + (String) array.at(j1 + 1));
				file5.renameTo(file4);
			}

		}
		return flag;
	}

	/**
	 * 
	 * 
	 * @param s
	 * @param array
	 * @param s1
	 * @param flag
	 * @param flag1
	 * @throws IOException
	 */
	public static void writeToFile(String s, Array array, String s1,
			boolean flag, boolean flag1) throws IOException {
		synchronized (FileUtils.getFileLock(s)) {
			SiteViewGroup siteviewgroup = SiteViewGroup.currentSiteView();
			int i = siteviewgroup.getSettingAsLong("_backups2Keep", 1);
			File file = new File(s);
			if (!file.exists() && I18N.isI18N(s)) {
				String s2 = I18N.toDefaultEncoding(s);
				File file1 = new File(s2);
				if (file1.exists()) {
					s = s2;
					file = file1;
				}
			}
			String s3 = s + ".bak" + (i <= 1 ? "" : ".1");
			boolean flag2 = siteviewgroup.getSetting("_backupDyns").length() == 0
					|| !s.endsWith("dyn");
			File file2 = pushBackup(file, s, s3, i, flag2);
			if (s.indexOf("master.config") != -1) {
				TempFileManager.addTempByAgeFile(file,
						siteviewgroup.getSettingAsLong("_tempByAgeTTL", 168));
			}
			if (printFile(file, array, s1, flag, flag1)) {
				popBackup(file, file2, s, i);
			}
		}
		DetectConfigurationChange.getInstance().resetFileTimeStamp(s);
	}

	static StringBuffer mangle(StringBuffer stringbuffer) {
		String s = stringbuffer.toString();
		s = TextUtils.replaceChar(s, '\r', "");
		long l = PlatformNew.crc(s);
		String s1 = TextUtils.obscure(s);
		return new StringBuffer("=" + l + s1);
	}

	/**
	 * 
	 * 
	 * @param file
	 * @param array
	 * @param s
	 * @param flag
	 * @param flag1
	 * @return
	 * @throws IOException
	 */
	public static boolean printFile(File file, Array array, String s,
			boolean flag, boolean flag1) throws IOException {
		PrintWriter printwriter = null;
		FileOutputStream fileoutputstream = null;
		boolean flag2 = false;
		try {
			StringBuffer stringbuffer = new StringBuffer();
			String s1 = "";
			printFrames(stringbuffer, array, s, flag, flag1);
			if (forceMangleOnWrite) {
				stringbuffer = mangle(stringbuffer);
			}
			if (stringbuffer.indexOf("measurement") != -1) {
				savadyn(stringbuffer);
			} else if (stringbuffer.indexOf("_nextID=") != -1) {

			} else {
				fileoutputstream = new FileOutputStream(file);
				printwriter = FileUtils.MakeUTF8OutputWriter(fileoutputstream);
				StringBuffer st = stringbuffer;
				String ss = st.toString();
				String sss = "";
				String s0 = "";
				String s2 = "";
				if (st.indexOf("_remote") != -1) {
					s0 = ss.substring(0, st.indexOf("_remote"));
					s2 = ss.substring(st.lastIndexOf("_remote"));
				}
				s2 = s2.substring(s2.indexOf("\n") + 1);
				if (file.getName().contains("master.config")
						&& ss.contains("_remoteNTMachine")) {
					ss = ss.substring(ss.indexOf("_remoteNTMachine"));
					while (ss.contains("_remoteNTMachine")) {
						sss += ss.substring(0, ss.indexOf("\n") + 1);
						ss = ss.substring(ss.indexOf("\n") + 1);
					}
					st = st.delete(st.indexOf("_remoteNTMachine"),
							st.indexOf("_remoteNTMachine") + sss.length());
				}
				if((s0+s2).length()<=0){
					printwriter.print(st);
		        }else{
	        		printwriter.print(s0+s2);
		        }
				ss = st.toString();
				if (file.getName().contains("master.config")
						&& ss.contains("_remoteMachine")) {
					ss = ss.substring(ss.indexOf("_remoteMachine"));
					while (ss.contains("_remoteMachine")) {
						sss += ss.substring(0, ss.indexOf("\n") + 1);
						ss = ss.substring(ss.indexOf("\n") + 1);
					}
					st = st.delete(st.indexOf("_remoteMachine"),
							st.indexOf("_remoteMachine") + sss.length());
				}
				if ((s0 + s2).equals("")) {
					printwriter.print(st);
				} else {
					printwriter.print(s0 + s2);
				}
				flag2 = printwriter.checkError();
			}
		} catch (IOException e) {
			LogManager.log("Error",
					" IOException during write of " + file.getAbsolutePath()
							+ " " + e.getMessage());
			throw e;
		} finally {
			if (printwriter != null) {
				printwriter.close();
			}
			try {
				if (fileoutputstream != null) {
					fileoutputstream.close();
				}
			} catch (IOException e) {
				LogManager.log("Error", "Exception in FrameFile.printFile()"
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		return flag2;
	}

	public static void printFrames(PrintWriter printwriter, Array array,
			String s, boolean flag, boolean flag1) {
	}

	static void printFrames(StringBuffer stringbuffer, Array array, String s,
			boolean flag, boolean flag1) {
		Enumeration enumeration = array.elements();
		for (boolean flag2 = true; enumeration.hasMoreElements(); flag2 = false) {
			HashMap hashmap = (HashMap) enumeration.nextElement();
			if (!flag2) {
				stringbuffer.append("#" + Platform.FILE_NEWLINE);
			} else if (TextUtils.getValue(hashmap, "_fileEncoding").length() == 0) {
				hashmap.put("_fileEncoding", "UTF-8");
			}
			printFrame(stringbuffer, hashmap, s, flag, flag1);
		}

	}

	static void printFrame(StringBuffer stringbuffer, HashMap hashmap,
			String s, boolean flag, boolean flag1) {
		boolean flag2 = s != null;
		if (hashmap.get("noSlotFilter") != null) {
			flag2 = false;
		}
		Enumeration enumeration = hashmap.keys();
		if (flag1) {
			Array array = new Array();
			for (; enumeration.hasMoreElements(); array.add(enumeration
					.nextElement())) {
			}
			Sorting.sort(array, new LessEqualPropertyName());
			enumeration = array.elements();
		}
		while (enumeration.hasMoreElements()) {
			Object obj = enumeration.nextElement();
			String s1;
			if (obj instanceof StringProperty) {
				s1 = ((StringProperty) obj).getName();
			} else {
				s1 = (String) obj;
			}
			Enumeration enumeration1 = hashmap.values(obj);
			while (enumeration1.hasMoreElements()) {
				Object obj1 = enumeration1.nextElement();
				boolean flag3 = true;
				if (s1.equals("_name") || s1.equals("_class")) {
				} else if (flag2) {
					flag3 = s1.startsWith(s);
					if (!flag) {
						flag3 = !flag3;
					}
				}
				if (flag3) {
					if (allowNestedFrames && (obj1 instanceof HashMap)) {
						stringbuffer.append(s1 + "={" + Platform.FILE_NEWLINE);
						printFrame(stringbuffer, (HashMap) obj1, s, flag, flag1);
						stringbuffer.append("}" + Platform.FILE_NEWLINE);
					} else if (obj1 instanceof Array) {
						Enumeration enumeration2 = ((Array) obj1).elements();
						while (enumeration2.hasMoreElements()) {
							stringbuffer.append(s1 + "="
									+ enumeration2.nextElement()
									+ Platform.FILE_NEWLINE);
						}
					} else {
						if (containsPasswordInName(s1)) {
							obj1 = "" + TextUtils.obscure((String) obj1);
						}
						String s2 = ("" + obj1).trim();
						if (s2.length() != 0 || flag1) {
							stringbuffer.append(s1 + "=" + s2
									+ Platform.FILE_NEWLINE);
						}
					}
				}
			}
		}
	}

	private static void savadyn(StringBuffer stringbuffer) {
		stringbuffer.delete(0, stringbuffer.indexOf("#") + 1);
		String s = "";
		while (stringbuffer.length() > 0) {
			if (stringbuffer.indexOf("#") != -1) {
				s = stringbuffer.substring(0, stringbuffer.indexOf("#"));
				savadyn(s);
				stringbuffer.delete(0, stringbuffer.indexOf("#") + 1);
			} else {
				s = stringbuffer.substring(0, stringbuffer.length());
				savadyn(s);
				stringbuffer.delete(0, stringbuffer.length());
			}
		}
	}

	// 分解String
	public static String format(String s, String s1, String s0) {
		s1 = s1.trim();
		if (!s1.contains(s)) {
			return null;
		}
		String s2 = s1.substring(s1.indexOf(s));
		s1 = s1.substring(0, s1.indexOf(s)).trim();
		if (s2.contains("\n")) {
			s0 = s2.substring(s2.indexOf("=") + 1, s2.indexOf("\n"));
			s1 = s1 + "\n" + s2.substring(s2.indexOf("\n") + 1);
		} else {
			s0 = s2.substring(s2.indexOf("=") + 1);
		}
		return s0 + "*" + s1;
	}
	private static void savadyn(String s) {
		String s1;
		String category = null;// 状态 时间 父组
		String monitorid = null;//
		String groupId = null;//
		String monitorName = null;// monitor name
		String type = null;
		int statuscount = 0;
		if (s.contains("category=")) {
			s1 = format("category=", s, category);
			category = s1.substring(0, s1.indexOf("*"));
			s = s1.substring(s1.indexOf("*") + 1);
		}
		if (category == null && s.contains("lastCategory=")) {
			s1 = format("lastCategory=", s, category);
			category = s1.substring(0, s1.indexOf("*"));
			s = s1.substring(s1.indexOf("*") + 1);
		}
		if (s.contains("id=")) {
			s1 = format("id=", s, monitorid);
			monitorid = s1.substring(0, s1.indexOf("*"));
			s = s1.substring(s1.indexOf("*") + 1);
		}
		if (s.contains("_class=")) {
			s1 = format("_class=", s, monitorid);
			type = s1.substring(0, s1.indexOf("*"));
			s = s1.substring(s1.indexOf("*") + 1);
		}
		if (s.contains("group=")) {
			s1 = format("group=", s, groupId);
			groupId = s1.substring(0, s1.indexOf("*"));
			s = s1.substring(s1.indexOf("*") + 1);
		}
		if (s.contains("_name=")) {
			s1 = format("_name=", s, monitorName);
			monitorName = s1.substring(0, s1.indexOf("*"));
			s = s1.substring(s1.indexOf("*") + 1);
		}
		
		type = Config.getReturnStr("siteview9.2_itsm.properties", type);
		String need = Config.getReturnStr("MonitorLogTabView.properties",
				"Ecc." + type);
		String so = "";
		if (need != null&&!need.equals("")) {
			String[] str = need.split(",");
			for (String sss : str) {
				s1 = format(sss.substring(sss.indexOf(":") + 1) + "=", s, null);
				if (s1 == null) {
					continue;
				}
				so += sss.substring(0, sss.indexOf(":")) + "="
						+ s1.substring(0, s1.indexOf("*") + 1);
				s = s1.substring(s1.indexOf("*") + 1);
			}
		}
		String groupName =MonitorGroup.groupnameip.get(groupId);
		monitorName = groupId + " ：" + monitorName;
		s = s.trim();
		s = s.replaceAll("\n", "*");// 日志

		String department = MonitorGroup.groupnameip.get(groupId) + " "
				+ monitorid;
		BusinessObject bo = null;
		String RecId;
		ICollection ico = null;
		bo = FileTools.CreateBo("monitorid", monitorid, "EccDyn");
		ISiteviewApi api=FileTools.getApi();
		if (bo != null) {
			RecId = bo.get_RecId();
			String status = bo.GetField("category").get_NativeValue()
					.toString();
			String cc = bo.GetField("StatusConut").get_NativeValue().toString();
			cc = cc.substring(0, cc.indexOf("."));
			statuscount = Integer.parseInt(cc);
			if (status.equals(category)) {
				statuscount++;
			} else {
				statuscount = 0;
			}
			bo.GetField("category").SetValue(new SiteviewValue(category));
			bo.GetField("monitorDesc").SetValue(new SiteviewValue(so));
			bo.GetField("groupid").SetValue(new SiteviewValue(groupName));
			bo.GetField("monitorName").SetValue(new SiteviewValue(monitorName));
			bo.GetField("Department").SetValue(new SiteviewValue(department));
			bo.GetField("MonitorType").SetValue(new SiteviewValue(type));
			bo.GetField("StatusConut").SetValue(new SiteviewValue(statuscount));
		} else {
			bo = api.get_BusObService().Create("EccDyn");
			bo.GetField("category").SetValue(new SiteviewValue(category));
			bo.GetField("monitorDesc").SetValue(new SiteviewValue(so));
			bo.GetField("monitorid").SetValue(new SiteviewValue(monitorid));
			bo.GetField("groupid").SetValue(new SiteviewValue(groupName));
			bo.GetField("monitorName").SetValue(new SiteviewValue(monitorName));
			bo.GetField("Department").SetValue(new SiteviewValue(department));
			bo.GetField("MonitorType").SetValue(new SiteviewValue(type));
			bo.GetField("StatusConut").SetValue(new SiteviewValue(0));
		}
		bo.SaveObject(api, false, true);
		/**
		 * 是否报警 
		 * 
		 */
		ICollection ico_alarm = FileTools.getBussCollection("MonitorId",monitorid, "EccAlarmRule");
		IEnumerator ien_alarm = ico_alarm.GetEnumerator();
		while (ien_alarm.MoveNext()) {
			BusinessObject bo_alarm = (BusinessObject) ien_alarm.get_Current();
			Timestamp LastModDateTime = new Timestamp(
					System.currentTimeMillis());
			String alarmstatus = bo.GetField("AlarmEvent").get_NativeValue()
					.toString();
			if ((Boolean) bo.GetField("RuleStatus").get_NativeValue()
					|| !alarmstatus.equals(category)) {
				continue;
			}
			String alarmrule = bo.GetField("AlarmRule").get_NativeValue()
					.toString();
			int starttime = (Integer) bo.GetField("StatusConut")
					.get_NativeValue();
			if (alarmrule.equals("select")) {
				int repeatime = (Integer) bo.GetField("RepeatCount")
						.get_NativeValue();
				if (statuscount < starttime
						|| (statuscount - starttime) % (repeatime + 1) != 0) {
					continue;
				}
			} else if (alarmrule.equals("once")) {
				if (starttime != statuscount) {
					continue;
				}
			} else if (alarmrule.equals("continue")) {
				if (starttime < statuscount) {
					continue;
				}
			}
			System.out.println("报警条件已经符合，开始报警了");
			Alarm(bo_alarm, "", groupName, monitorName, category,
					LastModDateTime, s);
		}
	}

	private static void Alarm(BusinessObject bo, String allGroup, String group,
			String monitor, String status, Timestamp LastModDateTime,
			String logFile) {
		String alarmType = "", address = "", toAddress = "", fromAddress = "", mailServerHost = "", userName = "", password = "", modleId = "", content = "";
		String ttime = LastModDateTime.toString().split("\\.")[0];
		String mmonitor = monitor.substring(monitor.lastIndexOf("：") + 1);
		// try {
		// modleId = rule.getString("ModleId");
		// alarmType = rule.getString("AlarmType");
		// address = rule.getString("Address");
		modleId = bo.GetField("ModleId").get_NativeValue().toString();
		alarmType = bo.GetField("AlarmType").get_NativeValue().toString();
		address = bo.GetField("Address").get_NativeValue().toString();
		if (address.length() == 32 && address.matches("\\w{32}")) {
			BusinessObject bo_to = FileTools.CreateBo("RecId", address,
					"EccMail");
			toAddress = bo_to.GetField("MailAddress").get_NativeValue()
					.toString();
			/*
			 * String sql = "select MailAddress from EccMail where RecId = '" +
			 * address + "'"; ResultSet rs =
			 * JDBCForSQL.sql_ConnectExecute_Select(sql); if (rs.next()) {
			 * toAddress = rs.getString("MailAddress"); }
			 */

			BusinessObject ico_email = FileTools.CreateBo("MailType", "send",
					"EccMail");
			fromAddress = ico_email.GetField("MailAddress").get_NativeValue()
					.toString();
			mailServerHost = ico_email.GetField("SendServer").get_NativeValue()
					.toString();
			userName = ico_email.GetField("MailUserName").get_NativeValue()
					.toString();
			password = ico_email.GetField("MailPwd").get_NativeValue()
					.toString();
			/*
			 * String sql2 =
			 * "select MailAddress,SendServer,MailUserName,MailPwd from EccMail where MailType = 'send'"
			 * ; ResultSet rs2 = JDBCForSQL.sql_ConnectExecute_Select(sql2); if
			 * (rs2.next()) { fromAddress = rs2.getString("MailAddress");
			 * mailServerHost = rs2.getString("SendServer"); userName =
			 * rs2.getString("MailUserName"); password =
			 * rs2.getString("MailPwd"); }
			 */

			BusinessObject bo_modle = FileTools.CreateBo("RecId", modleId,
					"EccMailModle");
			content = bo_modle
					.GetField("MailContent")
					.get_NativeValue()
					.toString()
					.replaceAll("@AllGroup@", allGroup)
					.replaceAll("@Group@", group)
					.replaceAll("@monitor@", mmonitor)
					.replaceAll("@Status@", status)
					.replaceAll("@Time@",
							LastModDateTime.toString().split("\\.")[0])
					.replaceAll("@LogFile@", logFile);
			/*
			 * String sql3 =
			 * "select MailContent from EccMailModle where RecId = '" + modleId
			 * + "'"; ResultSet rs3 =
			 * JDBCForSQL.sql_ConnectExecute_Select(sql3); if (rs3.next()) {
			 * content = rs3 .getString("MailContent") .replaceAll("@AllGroup@",
			 * allGroup) .replaceAll("@Group@", group) .replaceAll("@monitor@",
			 * mmonitor) .replaceAll("@Status@", status) .replaceAll("@Time@",
			 * LastModDateTime.toString().split("\\.")[0])
			 * .replaceAll("@LogFile@", logFile); }
			 */
		} else if (address.length() > 2 && address.matches("\\w+")) {
			toAddress = address;
		} else {
			toAddress = bo.GetField("Other").get_NativeValue().toString();
			modleId = bo.GetField("ModleId").get_NativeValue().toString();
			BusinessObject bo_modle = FileTools.CreateBo("RecId", modleId,
					"EccMailModle");
			content = bo_modle.GetField("MailContent").get_NativeValue()
					.toString().replaceAll("@AllGroup@", allGroup)
					.replaceAll("@Group@", group)
					.replaceAll("@monitor@", mmonitor)
					.replaceAll("@Status@", status).replaceAll("@Time@", ttime)
					.replaceAll("@LogFile@", logFile);
			/*
			 * toAddress = rule.getString("Other"); modleId =
			 * rule.getString("ModleId"); String sql =
			 * "select MailContent from EccMailModle where RecId = '" + modleId
			 * + "'"; ResultSet rs = JDBCForSQL.sql_ConnectExecute_Select(sql);
			 * if (rs.next()) { content = rs.getString("MailContent")
			 * .replaceAll("@AllGroup@", allGroup) .replaceAll("@Group@", group)
			 * .replaceAll("@monitor@", mmonitor) .replaceAll("@Status@",
			 * status) .replaceAll("@Time@", ttime) .replaceAll("@LogFile@",
			 * logFile); }
			 */
		}

		// } catch (SQLException e) {
		// e.printStackTrace();
		// return;
		// }
		String alarmName = "";
		alarmName = bo.GetField("AlarmName").get_NativeValue().toString();
		// try {
		// alarmName = rule.getString("AlarmName");
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		BusinessObject bo_0 = FileTools.api.get_BusObService().Create(
				"EccAlarmLog");
		// bo_0.GetField("RecId").SetValue(new SiteviewValue(getRecId()));
		// bo_0.GetField("CreatedDateTime").SetValue(new SiteviewValue(ttime));
		bo_0.GetField("AlarmName").SetValue(new SiteviewValue(alarmName));
		bo_0.GetField("AlarmGroup").SetValue(new SiteviewValue(group));
		bo_0.GetField("AlarmMonitor").SetValue(new SiteviewValue(mmonitor));
		bo_0.GetField("AlarmType").SetValue(new SiteviewValue(alarmType));
		bo_0.GetField("ReceiverAddress").SetValue(new SiteviewValue(toAddress));
		bo_0.GetField("AlarmStatus").SetValue(new SiteviewValue(status));
		bo_0.SaveObject(FileTools.api, false, true);
		/*
		 * String insertSQL =
		 * "insert into EccAlarmLog (RecId,CreatedDateTime,AlarmName,AlarmGroup,AlarmMonitor,AlarmType,ReceiverAddress,AlarmStatus)"
		 * + " values('" + getRecId() + "','" + ttime + "','" + alarmName +
		 * "','" + group + "','" + mmonitor + "','" + alarmType + "','" +
		 * toAddress + "','" + status + "')";
		 */
		ArrayList<String> list = new ArrayList<String>();
		list.add(ttime);
		list.add(alarmName);
		list.add(group);
		list.add(mmonitor);
		list.add(alarmType);
		list.add(toAddress);
		list.add(status);
		if (alarmType.equals("email")) {
			sendEmail(mailServerHost, userName, password, fromAddress,
					toAddress, content);
			// JDBCForSQL.execute_Insert(insertSQL);
		} else if (alarmType.equals("SMS")) {
			sendSMS(toAddress, content);
			// JDBCForSQL.execute_Insert(insertSQL);
		} else if (alarmType.equals("script")) {

		} else if (alarmType.equals("sound")) {
			TestMusic sound = new TestMusic("sounds/1262.wav");
			InputStream stream = new ByteArrayInputStream(sound.getSamples());
			sound.play(stream);
			System.out.println("声音播放了");
		}
		String dutyId = "";
		dutyId = bo.GetField("DutyId").get_NativeValue().toString();
		// try {
		// dutyId = rule.getString("DutyId");
		// } catch (SQLException e1) {
		// e1.printStackTrace();
		// }
		String dutyType = "";
		BusinessObject bo_duty = FileTools.CreateBo("RecId", dutyId,
				"EccDutyTable");
		dutyType = bo_duty.GetField("DutyTableType").get_NativeValue()
				.toString();
		// String sql = "select DutyTableType from EccDutyTable where RecId = '"
		// + dutyId + "'";
		// ResultSet rss = JDBCForSQL.sql_ConnectExecute_Select(sql);
		// try {
		// if (rss.next()) {
		// dutyType = rss.getString("DutyTableType");
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		Calendar ca = Calendar.getInstance();
		String currentTime = new SimpleDateFormat("HHmmss").format(new Date());
		Map map = new java.util.HashMap<String, String>();
		ICollection ico_1 = null;
		if ("day of week".equals(dutyType)) {
			int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK) - 1;
			String[] arr = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
			String week = arr[dayOfWeek];
			map.put("DutyId", dutyId);
			map.put("Week", week);
		} else if ("day of month".equals(dutyType)) {
			int dayOfMonth = ca.get(Calendar.DAY_OF_MONTH);
			String day = "" + dayOfMonth;
			map.put("DutyId", dutyId);
			map.put("Week", day);

		} else if ("day".equals(dutyType)) {
			map.put("DutyId", dutyId);
			map.put("Week", "");
		}
		ico_1 = FileTools.getBussCollection(map, "DutyDetail");
		DutyAlarm(list, ico_1, currentTime, content, mailServerHost, userName,
				password, fromAddress);
	}

	public static String getRecId() {
		String RecId = UUID.randomUUID().toString();
		RecId = RecId.replaceAll("-", "").toUpperCase();
		return RecId;
	}

	public static void DutyAlarm(ArrayList<String> list, ICollection ico,
			String currentTime, String content, String mailServerHost,
			String userName, String password, String fromAddress) {
		IEnumerator ien = ico.GetEnumerator();
		while (ien.MoveNext()) {
			BusinessObject bo = (BusinessObject) ien.get_Current();
			String startTime = bo.GetField("StartTime").get_NativeValue()
					.toString();
			startTime = startTime.substring(startTime.indexOf(":") - 2)
					.replaceAll(":", "").split("\\.")[0];
			String endTime = bo.GetField("EndTime").get_NativeValue()
					.toString();
			endTime = endTime.substring(endTime.indexOf(":") - 2)
					.replaceAll(":", "").split("\\.")[0];
			int start = Integer.parseInt(startTime);
			int end = Integer.parseInt(endTime);
			int time = Integer.parseInt(currentTime);
			if (start <= time && time <= end) {
				String receivePhone = bo.GetField("ReceiveAlarmpPhone")
						.get_NativeValue().toString();
				String receiveEmail = bo.GetField("ReceiveAlarmEmail")
						.get_NativeValue().toString();
				BusinessObject bo1 = FileTools.api.get_BusObService().Create(
						"EccAlarmLog");
				if (receivePhone != null && receivePhone.length() > 0
						&& "SMS".equals(list.get(4))) {
					sendSMS(receivePhone, content);
					bo1.GetField("ReceiverAddress").SetValue(
							new SiteviewValue(receivePhone));
				} else if (receiveEmail != null && receiveEmail.length() > 0
						&& "email".equals(list.get(4))) {
					sendEmail(mailServerHost, userName, password, fromAddress,
							receiveEmail, content);
					bo1.GetField("ReceiverAddress").SetValue(
							new SiteviewValue(receiveEmail));
				}
				// bo1.GetField("RecId").SetValue(new SiteviewValue(
				// getRecId()));
				// bo1.GetField("CreatedDateTime").SetValue(new
				// SiteviewValue(list.get(0)));
				bo1.GetField("AlarmName").SetValue(
						new SiteviewValue(list.get(1)));
				bo1.GetField("AlarmGroup").SetValue(
						new SiteviewValue(list.get(2)));
				bo1.GetField("AlarmMonitor").SetValue(
						new SiteviewValue(list.get(3)));
				bo1.GetField("AlarmType").SetValue(
						new SiteviewValue(list.get(4)));
				bo1.GetField("AlarmStatus").SetValue(
						new SiteviewValue(list.get(6)));
				bo1.SaveObject(FileTools.api, false, true);
			}
		}
		/*
		 * try { while (rsss.next()) { String startTime =
		 * rsss.getString("StartTime"); startTime =
		 * startTime.substring(startTime.indexOf(":") - 2) .replaceAll(":",
		 * "").split("\\.")[0]; String endTime = rsss.getString("EndTime");
		 * endTime = endTime.substring(endTime.indexOf(":") - 2)
		 * .replaceAll(":", "").split("\\.")[0]; int start =
		 * Integer.parseInt(startTime); int end = Integer.parseInt(endTime); int
		 * time = Integer.parseInt(currentTime); if (start <= time && time <=
		 * end) { String receivePhone = rsss.getString("ReceiveAlarmpPhone");
		 * String receiveEmail = rsss.getString("ReceiveAlarmEmail"); if
		 * (receivePhone != null && receivePhone.length() > 0 &&
		 * "SMS".equals(list.get(4))) { sendSMS(receivePhone, content); String
		 * SQL1 =
		 * "insert into EccAlarmLog (RecId,CreatedDateTime,AlarmName,AlarmGroup,AlarmMonitor,AlarmType,ReceiverAddress,AlarmStatus)"
		 * + " values('" + getRecId() + "','" + list.get(0) + "','" +
		 * list.get(1) + "','" + list.get(2) + "','" + list.get(3) + "','" +
		 * list.get(4) + "','" + receivePhone + "','" + list.get(6) + "')";
		 * JDBCForSQL.execute_Insert(SQL1); } if (receiveEmail != null &&
		 * receiveEmail.length() > 0 && "email".equals(list.get(4))) {
		 * sendEmail(mailServerHost, userName, password, fromAddress,
		 * receiveEmail, content); String SQL2 =
		 * "insert into EccAlarmLog (RecId,CreatedDateTime,AlarmName,AlarmGroup,AlarmMonitor,AlarmType,ReceiverAddress,AlarmStatus)"
		 * + " values('" + getRecId() + "','" + list.get(0) + "','" +
		 * list.get(1) + "','" + list.get(2) + "','" + list.get(3) + "','" +
		 * list.get(4) + "','" + receiveEmail + "','" + list.get(6) + "')";
		 * JDBCForSQL.execute_Insert(SQL2); } } } } catch (SQLException e) {
		 * e.printStackTrace(); }
		 */
	}

	public static void sendEmail(String mailServerHost, String userName,
			String password, String fromAddress, String receiveEmail,
			String content) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName(userName);
		mailInfo.setPassword(password);// 您的邮箱密码
		mailInfo.setFromAddress(fromAddress);
		mailInfo.setToAddress(receiveEmail);
		mailInfo.setSubject("Ecc报警");
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		// SimpleMailSender.sendTextMail(mailInfo);//发送文体格式
		sms.sendHtmlMail(mailInfo);// 发送html格式
	}

	public static void sendSMS(String TO, String MSG) {
		ArrayList<String> list = getSendPhoneAndPWD();
		String PHONE = list.get(0);
		String PWD = list.get(1);
		// String TO = receivePhone;
		// String MSG = content;
		try {
			// Fetion.sendMsg(PHONE, PWD, TO, MSG);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<String> getSendPhoneAndPWD() {
		ArrayList<String> list = new ArrayList<String>();
		BusinessObject bo = FileTools.CreateBo("SMSType", "send", "EccSMS");
		if (bo != null) {
			list.add(bo.GetField("SMSUserName").get_NativeValue().toString());
			list.add(bo.GetField("SMSPwd").get_NativeValue().toString());
		}
		return list;
	}

	public static boolean forceMangleOnReading() throws IOException {
		if (!forceMangleInited) {
			String s = Platform.getRoot() + File.separator + "classes"
					+ File.separator + "Scrambler.class";
			if ((new File(s)).exists()) {
				forceMangleOnRead = true;
				forceMangleOnWrite = true;
			} else {
				forceMangleOnRead = false;
				forceMangleOnWrite = false;
			}
			forceMangleInited = true;
			if (forceMangleOnRead) {
				safetyChecks();
			}
		}
		return forceMangleOnRead;
	}

	public static String safetyChecks() {
		String s = "";
		try {
			String s1 = Platform.getRoot() + File.separator + "groups"
					+ File.separator + "master.config";
			File file = new File(s1);
			if (!file.exists()) {
				throwError("master.config", "missing");
			}
			Array array = readFromFile(s1);
			if (array.size() != 1) {
				throwError("master.config", "truncated");
			}
			HashMap hashmap = (HashMap) array.at(0);
			if (hashmap.get("_initialMonitorDelay") == null) {
				throwError("master.config", "corrupt");
			}
			String s2 = Platform.getRoot() + File.separator + "groups"
					+ File.separator + "users.config";
			File file1 = new File(s2);
			if (!file1.exists()) {
				throwError("users.config", "missing");
			}
			array = readFromFile(s2);
			if (array.size() < 3) {
				throwError("users.config", "truncated");
			}
			HashMap hashmap1 = (HashMap) array.at(1);
			if (hashmap1.get("_realName") == null) {
				throwError("users.config", "corrupt");
			}
		} catch (Exception exception) {
			s = "error: " + exception;
		}
		return s;
	}

	public static void setMangle(boolean flag, StringBuffer stringbuffer)
			throws IOException {
		String s = Platform.getRoot() + File.separator + "classes"
				+ File.separator + "Scramble.class";
		String s1 = Platform.getRoot() + File.separator + "classes"
				+ File.separator + "Scrambler.class";
		if (flag) {
			stringbuffer.append("Encoding\n");
			FileUtils.copyFile(s, s1);
		} else {
			stringbuffer.append("Decoding\n");
			(new File(s1)).delete();
		}
		forceMangleInited = true;
		forceMangleOnWrite = flag;
		forceMangleOnRead = false;
		File file = new File(Platform.getRoot() + File.separator + "groups");
		String as[] = file.list();
		for (int i = 0; i < as.length; i++) {
			String s2 = as[i];
			if (isConfig(s2)) {
				stringbuffer.append(s2 + '\n');
				String s3 = Platform.getRoot() + File.separator + "groups"
						+ File.separator + s2;
				Array array = readFromFile(s3);
				writeToFile(s3, array);
			}
		}

		forceMangleOnRead = flag;
		stringbuffer.append(safetyChecks());
	}

	public static void logError(String s, String s1) {
		try {
			throwError(s, s1);
		} catch (Exception exception) {
		}
	}

	public static boolean containsPasswordInName(String s) {
		return s.indexOf("assword") != -1 || s.equals("_pwd")
				|| s.equals("_senderUserPwd") || s.equals("_userPwd");
	}

	public static void throwError(String s, String s1) throws IOException {
		File file = new File(Platform.getRoot() + File.separator + "logs"
				+ File.separator + "config.log");
		RandomAccessFile randomaccessfile = null;
		try {
			randomaccessfile = new RandomAccessFile(file, "rw");
			randomaccessfile.seek(randomaccessfile.length());
			String s2 = "" + new Date();
			String s3 = s2 + '\t' + s + '\t' + s1 + Platform.FILE_NEWLINE;
			randomaccessfile.writeBytes(s3);
		} catch (IOException ioexception) {
			System.err.println("Could not open log file " + file);
		} finally {
			try {
				if (randomaccessfile != null) {
					randomaccessfile.close();
				}
			} catch (IOException ioexception1) {
			}
		}
		throw new IOException(s + ", " + s1);
	}

	public static Array readFromFile(String s) throws IOException {
		boolean flag = forceMangleOnReading();
		return readFromFile(s, flag);
	}

	/**
	 * 从数据库中获取数据
	 * 
	 * @param groupstr
	 * @param groups_valid
	 * @return
	 * @throws IOException
	 */
	public static Array readFromDataBase(String groupstr, String groups_valid)
			throws IOException {
		return readDataBase(groupstr, groups_valid);
	}

	/**
	 * 读取数据
	 * 
	 * @param groupstr
	 * @param groups_valid
	 * @return
	 * @throws IOException
	 */
	public static Array readDataBase(String groupstr, String groups_valid)
			throws IOException {
		Array array = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(groupstr);
		ICollection ico=FileTools.getBussCollection("Groups", groups_valid, "Ecc");
		IEnumerator ien=ico.GetEnumerator();
		while(ien.MoveNext()){
			Map maps = new java.util.HashMap();
			BusinessObject bo1=(BusinessObject) ien.get_Current();
			String ecctype=bo1.GetField("EccType").get_NativeValue().toString();
			BusinessObject bo=FileTools.CreateBo("RecId", bo1.get_RecId(), "Ecc."+ecctype);
			ICollection ico_colum=bo.get_FieldNames();
			IEnumerator ien_colum=ico_colum.GetEnumerator();
			while(ien_colum.MoveNext()){
				String columName=(String) ien_colum.get_Current();
				String datavalue = bo.GetField(columName).get_NativeValue().toString();
				if (datavalue != null) {
					if (!datavalue.equals(" ")) {
						String parmName = Config.getReturnStr("itsm_eccmonitorparams.properties",columName);
						if (parmName == null || parmName.equals("")) {
							// System.err.println("Can not find parms from itsm_eccmonitorparams.properties:"+columName);
							// 过滤linkCheck字段
							if (columName.equals("MaxHops")
									&& datavalue.equals("no limit")) {
								maps.put("MaxHops", "no limit");
								continue;
							} else if (columName.equals("MaxHops")
									&& datavalue.equals("main page links")) {
								maps.put("MaxHops", "main page links");
								continue;
							}
							// wpc
							if (columName.equals("Scale")) {
								if (datavalue.equals("kilobytes")) {
									maps.put("_scale", "9.765625E-4");
									continue;
								} else if (datavalue.equals("megabytes")) {
									maps.put("_scale", "9.536743E-7");
									continue;
								} else if (datavalue.equals("Other")) {
									continue;
								} else {
									maps.put("_scale", datavalue);
								}
							}
							continue;
						} else {
							// mail监测器字段过滤
							if (parmName.equals("_useIMAP")
									&& datavalue.equals("IMAP4")) {
								datavalue = "true";
							} else if (parmName.equals("_useIMAP")
									&& datavalue.equals("POP3")) {
								continue;
							}
							// mail监测器发送方式字段过滤
							if (parmName.equals("_receiveOnly")
									&& datavalue.equals("Send & Receive")) {
								continue;
							}
							// eBusiness Chain监测器字段过滤
							if (parmName.equals("_whenError")
									&& datavalue.equals("continue")) {
								continue;
							}
							// 过滤url监测器
							if (parmName.equals("_checkContent")
									&& datavalue
											.equals("no content checking")) {
								continue;
							} else if (parmName.equals("_checkContent")
									&& (datavalue
											.equals("compare to saved contents") || datavalue
											.equals("reset saved contents"))) {
								datavalue = "baseline";
								stringBuffer
										.append("_checkContentResetTime=");
								stringBuffer.append(System
										.currentTimeMillis());
								stringBuffer.append(";");
							} else if (parmName.equals("_checkContent")
									&& datavalue
											.equals("compare to last contents")) {
								datavalue = "on";
								stringBuffer
										.append("_checkContentResetTime=");
								stringBuffer.append(System
										.currentTimeMillis());
								stringBuffer.append(";");
							}
							if (parmName
									.equals("_URLDropDownEncodePostData")
									&& datavalue
											.equals("Use content-type:")) {
								datavalue = "contentTypeUrlencoded";
							} else if (parmName
									.equals("_URLDropDownEncodePostData")
									&& datavalue
											.equals("force url encoding")) {
								datavalue = "forceEncode";
							} else if (parmName
									.equals("_URLDropDownEncodePostData")
									&& datavalue
											.equals("force No url encoding")) {
								datavalue = "forceNoEncode";
							}
							if (parmName.equals("_whenToAuthenticate")
									&& datavalue
											.equals("Use Global Preference")) {
								continue;
							} else if (parmName
									.equals("_whenToAuthenticate")
									&& datavalue
											.equals("Authenticate first request")) {
								datavalue = "authOnFirst";
							} else if (parmName
									.equals("_whenToAuthenticate")
									&& datavalue
											.equals("Authenticate if requested")) {
								datavalue = "authOnSecond";
							}
							// 过滤WebServerMonitor
							if (parmName.equals("_serverName")
									&& datavalue.equals("Microsoft IIS")) {
								datavalue = "Microsoft4|";
							}
							// 过滤logfile
							if (parmName.equals("_alerting")
									&& datavalue
											.equals("for each log entry matche")) {
								datavalue = "each";
							} else if (parmName.equals("_alerting")
									&& datavalue
											.equals("once afterall log entries")) {
								datavalue = "once";
							}
							if (parmName.equals("_cacheLife")) {
								datavalue = "0";
							}
							if (parmName.equals("_remotescript")) {
								datavalue = "none";
							}

							if (parmName.equals("_resetFile")
									&& (datavalue
											.equals("Never First Time Only") || datavalue
											.equals("First Time Only"))) {
								datavalue = "once";
							}
							// 过滤 LDAPMonitor
							if (parmName.equals("_securityprincipal")) {
								datavalue = datavalue.replaceAll(",", "*");
							}
							// 对应监测器
							if (columName.equals("EccType")) {
								datavalue = Config.getReturnStr(
										"itsm_siteview9.2.properties",
										datavalue);
							}

							// Windows Performance Counter过滤
							if (parmName.equals("_pmcfile")
									&& datavalue.equals("(Custom Object)")) {
								datavalue = "none";
							}
							// 逻辑字段值为true对应ON
							if (parmName.equals("_verifyError")
									|| parmName.equals("_notLogToTopaz")
									|| parmName.equals("_disabled")
									|| parmName.equals("_externalLinks")
									|| parmName
											.equals("_challengeResponse")
									|| parmName
											.equals("_sslAcceptInvalidCerts")
									|| parmName.equals("_getImages")
									|| parmName.equals("_errorOnRedirect")
									|| parmName
											.equals("_sslAcceptAllUntrustedCerts")
									|| parmName.equals("_measureDetails")
									|| parmName.equals("_HTTPVersion10")
									|| parmName.equals("_HTTPVersion10")
									|| parmName.equals("_getFrames")
									|| parmName.equals("_noFileCheckExist")
									|| parmName.equals("_deepCheck")
									|| parmName
											.equals("_checkSequentially")
									|| parmName.equals("_singleSession")
									|| parmName.equals("_noRecurse")) {
								if (datavalue.equals("true")) {
									datavalue = "on";
								} else {
									continue;
								}
							}

							if (columName.equals("RecId")) {
								stringBuffer.append("_encoding=GBK;");
								stringBuffer.append("_id=" + datavalue
										+ ";");
							}
							// 时间转化
							if (columName.equals("frequency")|| columName.equals("verifyErrorFrequency")) {
								if (datavalue != null) {
									if(datavalue.contains(".")){
										datavalue=datavalue.substring(0,datavalue.indexOf("."));
									}else{
										datavalue=0+"";
									}
									int timehs = Integer.parseInt(datavalue);
									if (bo.GetField("timeUnitSelf").get_NativeValue().toString().equals("Minute")) {
										timehs = timehs * 60;
									}
									if (bo.GetField("timeUnitSelf").get_NativeValue().toString().equals("Hour")) {
										timehs = timehs * 3600;
									}
									if (bo.GetField("timeUnitSelf").get_NativeValue().toString().equals("Day")) {
										timehs = timehs * 86400;
									}
									datavalue = String.valueOf(timehs);
								}
							}
							stringBuffer.append(parmName + "=" + datavalue+ ";");
						}
					}
				}
			}
				// linkCheck
				if (maps.get("MaxHops") != null
						&& maps.get("MaxHops").equals("no limit")) {
					if (!stringBuffer.toString().contains("_maxSearchDepth=")) {
						stringBuffer.append("_maxSearchDepth=");
						stringBuffer.append("100");
						stringBuffer.append(";");
					}
				} else if (maps.get("MaxHops") != null
						&& maps.get("MaxHops").equals("main page links")) {
					if (!stringBuffer.toString().contains("_maxSearchDepth=")) {
						stringBuffer.append("_maxSearchDepth=");
						stringBuffer.append("1");
						stringBuffer.append(";");
					}
				}
				// Windows Performance Counter
				if (!stringBuffer.toString().contains("_scale")
						&& maps.get("_scale") != null) {
					stringBuffer.append("_scale=");
					stringBuffer.append(maps.get("_scale"));
					stringBuffer.append(";");
				}
				int i = 0;
				if(isHave(MonitorCounterGroups, Config.getReturnStr("itsm_eccmonitorparams.properties",ecctype))){
					Relationship rel=bo.GetRelationship(ecctype+"ContainsCounter");
					if(rel!=null){
						ICollection ico_count=rel.get_BusinessObjects();
						IEnumerator ien_count=ico_count.GetEnumerator();
						while(ien_count.MoveNext()){
							BusinessObject count=(BusinessObject) ien_count.get_Current();
							String counter = count.GetField("Name").get_NativeValue().toString();
							if (counter.contains("Default")) {
								counter = counter.substring(0,
										counter.lastIndexOf("--") + 2)
										+ "Total";
							}
							if (i == 0) {
								stringBuffer.append("_counters=");
								stringBuffer.append(counter);
							} else {
								stringBuffer.append("," + counter);
							}
							i++;
						}
					}
				}
				
				// 取计数器
//				String sql_count = "select * from MonitorCounter where ParentLink_RecID='"
//						+ eccrs.getString("RecId") + "'";
//				ResultSet coutrs = JDBCForSQL
//						.sql_ConnectExecute_Select(sql_count);
//				while (coutrs.next()) {
//					String counter = coutrs.getString("Name");
//					if (counter.contains("Default")) {
//						counter = counter.substring(0,
//								counter.lastIndexOf("--") + 2)
//								+ "Total";
//					}
//					if (i == 0) {
//						stringBuffer.append("_counters=");
//						stringBuffer.append(counter);
//					} else {
//						stringBuffer.append("," + counter);
//					}
//					i++;
//				}
				if (i > 0) {
					stringBuffer.append(";");
				}
				
				Relationship rel=bo.GetRelationship(ecctype+"ContainsAlarm");
				if(rel!=null){
					ICollection ico_alarm=rel.get_BusinessObjects();
					IEnumerator ien_alarm=ico_alarm.GetEnumerator();
					while(ien_alarm.MoveNext()){
						BusinessObject alarm=(BusinessObject) ien_alarm.get_Current();
						stringBuffer.append("_classifier=");
						String value = Config.getReturnStr("itsm_monitorreturnitem.properties", ecctype);
						stringBuffer.append(alarm.GetField(value).get_NativeValue().toString());
						stringBuffer.append(" ");
						stringBuffer.append(alarm.GetField("Operator").get_NativeValue().toString());
						stringBuffer.append(" ");
						stringBuffer.append(alarm.GetField("AlramValue").get_NativeValue().toString());
						stringBuffer.append("\t");
						stringBuffer.append(alarm.GetField("AlarmStatus").get_NativeValue().toString());
						stringBuffer.append(";");
					}
				}
				
				// 报警条件
//				String sql = "select * from Alarm where ParentLink_RecID='"
//						+ eccrs.getString("RecId") + "'";
//				ResultSet rsAlarm = JDBCForSQL.sql_ConnectExecute_Select(sql);
//				while (rsAlarm.next()) {
//					stringBuffer.append("_classifier=");
//					String monitorType = eccrs.getString("EccType");
//					String value = Config.getReturnStr(
//							"itsm_monitorreturnitem.properties", monitorType);
//					stringBuffer.append(rsAlarm.getString(value));
//					stringBuffer.append(" ");
//					stringBuffer.append(rsAlarm.getString("Operator"));
//					stringBuffer.append(" ");
//					stringBuffer.append(rsAlarm.getString("AlramValue"));
//					stringBuffer.append("\t");
//					stringBuffer.append(rsAlarm.getString("AlarmStatus"));
//					stringBuffer.append(";");
//				}
				stringBuffer.append("#;");
			}
		array = mangleIt(stringBuffer.toString());
		return readFrames(array.elements());
	}

	/**
	 * 
	 * 
	 * @param s
	 * @param flag
	 * @return
	 * @throws IOException
	 */
	static Array readFromFile(String s, boolean flag) throws IOException {
		synchronized (FileUtils.getFileLock(s)) {
			StringBuffer stringbuffer = null;
			Array array = null;
			try {
				stringbuffer = FileUtils.readUTF8File(s);
			} catch (IOException e) {
				if (stringbuffer == null || stringbuffer.length() == 0) {
					if (readMasterConfig && s.indexOf("master.config") >= 0) {
						LogManager.log("error", "File(" + s + ") is missing");
					}
					File file = new File(s);
					String s2 = file.getParent();
					File file1 = null;
					Object obj1 = null;
					int j = 100;
					if (readMasterConfig) {
						SiteViewGroup siteviewgroup = SiteViewGroup
								.currentSiteView();
						j = siteviewgroup.getSettingAsLong("_backups2Keep", 1);
						String s3 = s + ".bak" + (j <= 1 ? "" : ".1");
						file1 = new File(s3);
					} else {
						String s4 = s + ".bak";
						file1 = new File(s4);
						if (!file1.exists()) {
							file1 = new File(s4 + ".1");
						}
					}
					try {
						if (popBackup(file, file1, s2, j) && readMasterConfig) {
							LogManager.log("error", "File(" + s
									+ ") replacing with backup");
						} else if (readMasterConfig
								&& s.indexOf("master.config") >= 0) {
							LogManager
									.log("error",
											"File("
													+ s
													+ ") CANNOT replace backup. No backup found.");
						}
					} catch (IOException e1) {
						if (readMasterConfig && s.indexOf("master.config") >= 0) {
							LogManager.log("error", "File(" + s
									+ ") Exception(" + e1.getMessage()
									+ ") replacing backup");
						}
					}
					stringbuffer = FileUtils.readUTF8File(s);
				}
			}
			array = mangleIt(s, stringbuffer.toString(), flag);
			if (s.indexOf("master.config") >= 0) {
				readMasterConfig = true;
				for (int i = 0; i < array.size(); i++) {
					String s1 = (String) array.at(i);
					if (s1.trim().startsWith("#")) {
						array.remove(i);
						i--;
					}
				}

			}
			return readFrames(array.elements());
		}
	}

	static Array mangleIt(String s, String s1, boolean flag) throws IOException {
		boolean flag1 = s1.startsWith("=");
		if (flag1) {
			int i = s1.indexOf("(0x)");
			if (i == -1) {
				throwError(s, "format");
			}
			long l = TextUtils.toLong(s1.substring(1, i));
			s1 = s1.substring(i);
			s1 = TextUtils.enlighten(s1);
			long l1 = PlatformNew.crc(s1);
			if (l != l1) {
				logError(s, "checksum");
			}
		} else if (flag && s.indexOf("groups") != -1) {
			throwError(s, "encoding problem");
		}
		s1 = TextUtils.replaceChar(s1, '\r', "");
		Array array = Platform.split('\n', s1);
		return array;
	}

	public static Array mangleIt(String s1) throws IOException {
		s1 = TextUtils.replaceChar(s1, '\r', "");
		Array array = Platform.splitdata(s1);
		return array;
	}

	public static Array readFrames(BufferedReader bufferedreader)
			throws IOException {
		throw new IOException("unimplemented");
	}

	/**
	 * 
	 * 
	 * @param enumeration
	 * @return
	 * @throws IOException
	 */
	public static Array readFrames(Enumeration enumeration) throws IOException {
		Array array = new Array();
		while (true) {
			HashMap hashmap = readFrame(enumeration, "#");
			if (hashmap != null) {
				array.add(hashmap);
			} else {
				return array;
			}
		}
	}

	/**
	 * 
	 * 
	 * @param enumeration
	 * @param s
	 * @return
	 * @throws IOException
	 */
	private static HashMap readFrame(Enumeration enumeration, String s)
			throws IOException {
		HashMapOrdered hashmapordered = null;
		int iii = 0;
		while (enumeration.hasMoreElements()) {
			if (iii > 100)
				System.out.println(++iii);
			String s1 = (String) enumeration.nextElement();
			if (s1 == null) {
				break;
			}
			if (!s1.startsWith("//")) {
				if (hashmapordered == null) {
					hashmapordered = new HashMapOrdered(true);
				}
				if (s1.startsWith(s)) {
					return hashmapordered;
				}
				int i = s1.indexOf('=');
				if (i > 0) {
					String s2 = s1.substring(0, i);
					String s3 = s1.substring(i + 1);
					if (allowNestedFrames && s3.startsWith("{")) {
						HashMap hashmap = readFrame(enumeration, "}");
						if (hashmap != null) {
							hashmapordered.add(s2, hashmap);
						}
					} else {
						if (containsPasswordInName(s2)) {
							s3 = TextUtils.enlighten(s3);
						}
						hashmapordered.add(s2, s3);
					}
				}
			}
		}
		return hashmapordered;
	}

	public static void touchFile(String s) {
		synchronized (FileUtils.getFileLock(s)) {
			RandomAccessFile randomaccessfile = null;
			try {
				randomaccessfile = new RandomAccessFile(s, "rw");
				randomaccessfile.seek(0L);
				byte abyte0[] = new byte[1];
				randomaccessfile.read(abyte0, 0, abyte0.length);
				randomaccessfile.seek(0L);
				randomaccessfile.write(abyte0, 0, abyte0.length);
			} catch (IOException ioexception) {
				LogManager.log("Error", "Exception in FrameFile.touchFile()"
						+ ioexception.getMessage());
				ioexception.printStackTrace();
			} finally {
				try {
					if (randomaccessfile != null) {
						randomaccessfile.close();
					}
				} catch (IOException ioexception1) {
					LogManager.log(
							"Error",
							"Exception in FrameFile.touchFile()"
									+ ioexception1.getMessage());
					ioexception1.printStackTrace();
				}
			}
		}
	}

	static int gi(HashMap hashmap, String s) {
		String s1 = TextUtils.getValue(hashmap, s);
		s1 = s1.trim();
		if (s1.equals("most important")) {
			return 3;
		}
		if (s1.equals("important")) {
			return 2;
		}
		if (s1.equals("a little important")) {
			return 1;
		}
		return !s1.equals("not important") ? 0 : 0;
	}

	static String gv(HashMap hashmap, String s) {
		String s1 = TextUtils.getValue(hashmap, s);
		s1 = TextUtils.replaceString(s1, " ", "\\n");
		s1 = s1.trim();
		s1 = s1.replace(',', ' ');
		return s1;
	}

	public static boolean isConfig(String s) {
		return s.endsWith(".mg") || s.endsWith(".dyn") || s.endsWith(".config");
	}

	public static boolean isHave(String[] strs, String s) {
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].indexOf(s) != -1) {
				return true;
			}
		}
		return false;
	}



	public static void main(String[] args) {
		String path = "";
		try {
			path = Thread.currentThread().getContextClassLoader()
					.getResource("1262.wav").toURI().toString();
			String s = path.substring(path.indexOf("com."),
					path.indexOf(".core") + 5);
			System.out.println(s);
			s = s.replaceAll("\\.", "/");
			System.out.println(s);
			path = path.replaceAll("com\\.siteview\\.kernel\\.core", s);
			// path = path.substring(path.indexOf(":/")+2);
			path = path.substring(10, path.indexOf("/lib") + 1)
					+ "sounds/1262.wav";
			// path = path.replaceAll("\\.", "/");
			System.out.println(path);
			// path =
			// FrameFile.class.getResource("../../../../sounds/1262.wav").toURI().toString();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// path=path.substring(path.indexOf("/")+1,path.length());
		System.out.println(path);
		TestMusic sound = new TestMusic("sounds/1262.wav");
		InputStream stream = new ByteArrayInputStream(sound.getSamples());
		sound.play(stream);
		System.out.println("声音播放了");
	}
}