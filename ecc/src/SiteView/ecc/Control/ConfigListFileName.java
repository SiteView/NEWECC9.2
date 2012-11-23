package SiteView.ecc.Control;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.net.telnet.TelnetClient;


import system.Collections.ICollection;
import system.Collections.IEnumerator;

import SiteView.ecc.dialog.AlarmTactics;
import SiteView.ecc.tools.FileTools;
import SiteView.ecc.view.EccTreeControl;
import Siteview.SiteviewValue;
import Siteview.Api.BusinessObject;
import Siteview.Windows.Forms.ConnectionBroker;

/**
 * ��ȡ��Ҫ���صĸ����豸�������ļ������б�
 * 
 * @author Administrator
 * 
 */
public class ConfigListFileName {
	 
	private int i = 0;
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	private FileOutputStream fout;
	private String prompt = ";";
	private String ip = "";
	private String user = "";
	private String password = "";
	private int port = 23;
	private String strlen;
	public static boolean success = false;
	private static ArrayList<String> list = null;
	private static String URL;
	static{
//		getCon();
	}
	

	public static String getURL() {
		return URL;
	}

//	public static void main(String[] args) {
//		LinkCisco("192.168.0.248", "rootroot", "en", "siteview", "dir");
//	}

	public static ArrayList<String> TelnetInfo(String host) {
	

		BusinessObject bo = EccTreeControl.CreateBo("ServerAddress", host,
				"RemoteMachine");
		String pwd = bo.GetField("EquipmentPassWord").get_NativeValue()
				.toString();
		String superName = bo.GetField("AuthorityName").get_NativeValue()
				.toString();
		String superPwd = bo.GetField("AuthorityPwd").get_NativeValue()
				.toString();
		String EquipmentTypes = bo.GetField("EpuipmentsTypes")
				.get_NativeValue().toString();
		String dir = "dir";
		if ("˼��".equals(EquipmentTypes)) {
			LinkCisco(host, pwd, superName, superPwd, dir);
		} else if ("��Ϊ".equals(EquipmentTypes)) {
			LinkHuaWei(host, pwd, superName, superPwd, dir);
		}
		
		while(true){
			ICollection ico=FileTools.getBussCollection("ConfigListFileName");//�õ�һ�������������
			IEnumerator ien=ico.GetEnumerator();
			while(ien.MoveNext()){//����ɾ��һ�������������
				BusinessObject bo3=(BusinessObject) ien.get_Current();//���һ������
				bo3.DeleteObject(ConnectionBroker.get_SiteviewApi());//ִ��ɾ��
			
			}

		for (int i = 1; i < list.size()-1; i++) {//i!=0 �� list.size() -1 ��ȥ��2�������ļ�������
			
			
			BusinessObject bo1 = ConnectionBroker.get_SiteviewApi()// �õ����ݿ��
					.get_BusObService().Create("ConfigListFileName");
			bo1.GetField("FileName").SetValue(new SiteviewValue(list.get(i)));
			bo1.GetField("FileName").get_NativeValue();
			bo1.SaveObject(ConnectionBroker.get_SiteviewApi(), true,
					true);//�����ݴ洢������	
		}
		return list;}
	}

	public static boolean LinkCisco(String host, String pwd, String superName,
			String superPwd, String dir) {
		ConfigListFileName telnet1 = new ConfigListFileName(">", host, 23, "3",
				pwd);
		String str = telnet1.Connection();// ����
		boolean flag = false;
		if (!("��¼�ɹ�!".equals(str))) {
			return false;
		}
		telnet1.write(superName);// Ȩ��

		try {
			String str1 = telnet1.readUntil("Password:");
			telnet1.write(superPwd);// Ȩ������
			success = telnet1.determineCommand("#", "Password:");// �����Ƿ�ɹ�
			if (!success) {
				return false;
			}
			telnet1.write(dir);
			telnet1.getCiscoFileName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * ��ȡ˼���ļ��б�
	 * 
	 * @return
	 */
	public ArrayList<String> getCiscoFileName() {
		StringBuffer sb = new StringBuffer();
		list = new ArrayList<String>();
		int line;
		try {
			line = this.telnet.getInputStream().read();
			char ch = (char) line;
			while (line != -1) {
				sb.append(ch);
				if (sb.toString().endsWith("#")) {
					break;
				}
				line = this.telnet.getInputStream().read();
				ch = (char) line;
			}

			for (String s : sb.toString().split("\r\n")) {
				if (s.lastIndexOf(" ") < 0)
					continue;
				String str = s.substring(s.lastIndexOf(" "), s.length());
//				if (str.endsWith("flash:/"))
//					continue;
//				if (str.endsWith("free)"))
//					continue;
				// System.out.println(str);//�ļ��б���
				list.add(str);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * ��Ϊ
	 * 
	 * @param host
	 * @param pwd
	 * @param superName
	 * @param superPwd
	 * @param dir
	 * @return
	 */
	public static boolean LinkHuaWei(String host, String pwd, String superName,
			String superPwd, String dir) {
		ConfigListFileName telnet1 = new ConfigListFileName(">", host, 23, "3",
				pwd);
		String str = telnet1.Connection();// ����
		boolean flag = false;
		if (!("��¼�ɹ�!".equals(str))) {
			System.out.println(str);
			return false;
		}
		telnet1.write(superName);// Ȩ��

		try {
			String str1 = telnet1.readUntil("Password:");
			telnet1.write(superPwd);// Ȩ������
			success = telnet1.determineCommand(">", "Password:");// �����Ƿ�ɹ�
			System.out.println(success);
			if (!success) {
				return false;
			}
			System.out.println(dir);
			telnet1.write(dir);
			telnet1.getHuaWeiFileName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public ArrayList<String> getHuaWeiFileName() {
		StringBuffer sb = new StringBuffer();
		list = new ArrayList<String>();
		int line;
		try {
			line = this.telnet.getInputStream().read();
			char ch = (char) line;
			while (line != -1) {
				sb.append(ch);

				if (sb.toString().endsWith("<Quidway>")) {
					break;
				}
				line = this.telnet.getInputStream().read();
				ch = (char) line;
			}

			for (String s : sb.toString().split("\r\n")) {
				if (s.lastIndexOf(" ") < 0)
					continue;
				String str = s.substring(s.lastIndexOf(" "), s.length());
//				if (str.endsWith("flash:/"))
//					continue;
//				if (str.endsWith("free)"))
//					continue;
				// System.out.println(str);//�ļ��б���
				list.add(str);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}

	public ConfigListFileName(String prompt, String ip, int port, String user,
			String password) {
		this.ip = (ip == null ? "" : ip.trim());
		this.port = port;
		this.user = (user == null ? "" : user.trim());
		this.password = (password == null ? "" : password.trim());
		if (prompt != null && !"".equals(prompt)) {
			this.prompt = prompt;
		}

	}

	public String Connection() {
		try {
			this.telnet.setConnectTimeout(20000);
			this.telnet.connect(this.ip, this.port);
			this.telnet.setSoTimeout(20000);
		} catch (SocketException e) {
			// e.printStackTrace();
			return "����ʧ��!" + e.getMessage();
		} catch (IOException e) {
			// e.printStackTrace();
			return "����ʧ��!" + e.getMessage();
		}
		this.in = this.telnet.getInputStream();
		this.out = new PrintStream(this.telnet.getOutputStream());
		try {
			if (!login(this.user, this.password))
				return "�û������������!";
		} catch (Exception e) {
			e.printStackTrace();
			return "��¼ʧ��!" + e.getMessage();
		}

		return "��¼�ɹ�!";
	}

	public boolean login(String user, String password) throws Exception {
		// write(user);
		// readUntil(">");
		readUntil("Password:");
		write(password);
		return determineCommand(">", "Password:");
		// return readUntil(">").equals("Bad passwords");
	}

	public String readUntil(String pattern) throws Exception {
		char lastChar = pattern.charAt(pattern.length() - 1);
		StringBuffer sb = new StringBuffer();
		int line = -1;
		line = this.in.read();
		char ch = (char) line;
		while (line != -1) {
			sb.append(ch);
			if (ch == lastChar) {
				if (sb.toString().endsWith(pattern)) {
					return sb.toString();
				}
			}
			line = this.in.read();
			ch = (char) line;

		}

		return sb.toString();
	}

	public boolean determineCommand(String successStr, String errorStr)
			throws Exception {

		StringBuffer sb = new StringBuffer();
		int line = -1;
		line = this.in.read();
		char ch = (char) line;
		try {
			while (line != -1) {
				sb.append(ch);
				if (sb.toString().endsWith(errorStr)) {
					break;
				}
				if (sb.toString().endsWith(successStr)) {
					break;
				}
				line = this.in.read();
				ch = (char) line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String content = sb.toString();
		strlen = content;
		return isCommadSuccess(content, errorStr, successStr);

	}

	private boolean isCommadSuccess(String content, String errorStr,
			String successStr) {
		if (content.endsWith(errorStr)) {
			return false;
		}
		if (content.endsWith(successStr)) {
			return true;
		}
		return false;
	}

	/**
	 * д������
	 * 
	 * @param value
	 */
	public void write(String value) {
		try {
			this.out.println(value);
			this.out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ�����ļ�
	 */
	public static void getCon() {

		// InputStream inputStream = this.getClass().getClassLoader()
		// .getResourceAsStream("crf.properties");

		Properties p = new Properties();
		try {
			FileReader fr = new FileReader("cfr.properties");
			p.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		ApacheGitServer = p.getProperty("ApacheGitServer");
//		Apacheport = p.getProperty("Apacheport");
		URL = p.getProperty("URL");
//		UserName = p.getProperty("UserName");
//		UserPassword = p.getProperty("UserPassword");
//		remoteGit = p.getProperty("remoteGit");
//		tempUrl=p.getProperty("tempUrl");

	}
	
}
