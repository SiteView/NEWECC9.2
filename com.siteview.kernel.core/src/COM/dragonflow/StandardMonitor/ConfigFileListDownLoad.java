package COM.dragonflow.StandardMonitor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.net.telnet.TelnetClient;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;



public class ConfigFileListDownLoad {
	public static ConfigFileListDownLoad cfl= new ConfigFileListDownLoad();
	private static ConfigFileListDownLoad configFLD;
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
	public  boolean success=false;
	private String pwd = "";
	private String configSystem = "system-view";
	static MessageDigest MD5 = null;
	static String oldurl=null;
	public  static String description="����";
	public static ConfigFileListDownLoad getConfigFileListDownLoad(){
		if(configFLD==null){
			configFLD=new ConfigFileListDownLoad();
		}
		return configFLD;
	}
	public ConfigFileListDownLoad(){
		
	}
	private String[] devicearr={"��ݻ��","��ݺ���","���·����"};
	
	 static {
	        try {
	        MD5 = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException ne) {
	        ne.printStackTrace();
	        }
	    }
		//��¼
//����public ConfigFileListDownLoad loginTelnet(){
//			if("administrator".equals(UserName.trim()
//					)){
//				UserName="3";
//			}
//			ConfigFileListDownLoad telnet1= new ConfigFileListDownLoad(">", host,
//					23, UserName, pwd);
//			String str = telnet1.Connection();// ����
//			boolean flag = false;
//			if (!("��¼�ɹ�!".equals(str))) {
//				return null;
//			}
//			return telnet1;
//		}
	//ִ�з���
	public static boolean Link(String serviceName,String downConfigNmae,String ConfigFileName,String host, 
			String command, String UserName, String pwd, String superName,String  superPwd) {
		if("administrator".equals(UserName.trim()
				)){
			UserName="3";
		}
		ConfigFileListDownLoad telnet1 = new ConfigFileListDownLoad(">", host,
				23, UserName, pwd);
		String str = telnet1.Connection();// ����
		boolean flag = false;
		if (!("��¼�ɹ�!".equals(str))) {
			cfl.success=false;
			return false;
		}
		telnet1.write(superName);// Ȩ��
		serviceName=serviceName.substring(0, 2);
		try {
			String localhost = java.net.InetAddress.getLocalHost()
					.getHostAddress();
			if ("˼��".equals(serviceName)) {
				String str1 = telnet1.readUntil("Password:");
				telnet1.write(superPwd);// Ȩ������
				cfl.success = telnet1.determineCommand("#", "Password:");// �����Ƿ�ɹ�
				if (!cfl.success) {
					return false;
				}
				flag = telnet1.getConfigFileCisco(telnet1, ConfigFileName,
						localhost);
			} else 	if ("��Ϊ".equals(serviceName)) {
				String str1 = telnet1.readUntil("Password:");
				telnet1.write(superPwd);// Ȩ������
				cfl.success = telnet1.determineCommand(">", "Password:");// �����Ƿ�ɹ�
				if (!cfl.success) {
					return false;
				}
				flag = telnet1.getConfigFileHuaWei(telnet1, ConfigFileName,
						localhost,host);
				if(flag){
					flag = CompareFileMD5();
				}
			}
			else if("���".equals(serviceName)){
				String str1 = telnet1.readUntil("Password:");
				telnet1.write(superPwd);// Ȩ������
				cfl.success = telnet1.determineCommand("#", "Password:");// �����Ƿ�ɹ�
				if (!cfl.success) {
					return false; 
				}
				flag = telnet1.getConfigFileRuiJie( host, telnet1,command, downConfigNmae,
						localhost);
				if(flag){
					flag = CompareFileMD51();//�Ƚ��ļ�
				}
			}
			else if("����".equals(serviceName)){
				String str1 = telnet1.readUntil("Password:");
				telnet1.write(superPwd);// Ȩ������
				cfl.success = telnet1.determineCommand("#", "Password:");// �����Ƿ�ɹ�
			   // success=true;
			    if (!cfl.success) {
					return false;
				}
				flag = telnet1.getzhongxinConfigFile( host, telnet1, command,
						 downConfigNmae,  localhost);
				if(flag){
					flag = CompareFileMD51();
				}
			}else {
				System.out.println("û�и�ģ��");
				return false;
			}
			if(flag){
				File f = new File(oldurl);
				ConfigCopy.commitConifgFile(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
		//ִ�з���(�����豸�����ļ�����)
		public static boolean Link(String serviceName,String downConfigNmae,String ConfigFileName,String host, 
				String command, String UserName, String pwd, String superName,String  superPwd,String CFDirectory,String CommandText,String cfname,String hostIP) {
			if("administrator".equals(UserName.trim()
					)){
				UserName="3";
			}
			ConfigFileListDownLoad telnet1 = new ConfigFileListDownLoad(">", host,
					23, UserName, pwd);
			String str = telnet1.Connection();// ����
			boolean flag = false;
			if (!("��¼�ɹ�!".equals(str))) {
				cfl.success=false;
				return false;
			}
			telnet1.write(superName);// Ȩ��
			//serviceName=serviceName.substring(0, 2);
			try {
				String localhost = java.net.InetAddress.getLocalHost()
						.getHostAddress();
					String str1 = telnet1.readUntil("Password:");
					telnet1.write(superPwd);// Ȩ������
					cfl.success = telnet1.determineCommand("#", "Password:");// �����Ƿ�ɹ�
					// success=true;
					if (!cfl.success) {
						return false;
					}
					//�������������ļ�
					flag = telnet1.getAllConfigFile( host, telnet1, command,
							downConfigNmae,  localhost,CFDirectory,CommandText,cfname,hostIP);
					if(flag){
						flag = CompareFileMD51();
					}
				if(flag){
					File f = new File(oldurl);
					ConfigCopy.commitConifgFile(f);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return flag;
	}
	// ConfigDownLoadUploadMonitor �Ƚ��ļ�����
	public static boolean CompareFileMD5() throws IOException {
		String newurl = ConfigDownLoadUploadMonitor.ConfigURL;
		 oldurl = ConfigDownLoadUploadMonitor.ConfigAllURl;
		File newfile = new File(newurl);
	if(!newfile.exists()){
		return false;
	}
		File oldfile = new File(oldurl);
		if(!oldfile.exists()){
			String str=oldurl.substring(0,oldurl.lastIndexOf('/'));
		File tempfile=new File(str);
		tempfile.mkdirs();
			oldfile.createNewFile();
		}
		try {
			String newM5 = getFileMD5Value(newfile);
			String oldM5 = getFileMD5Value(oldfile);
			if (newM5.equals(oldM5)) {
				return false;
			} else {
				replaceFileContent(newfile, oldfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return true;
	}
	//ConfigFileMonitor �ļ��Ƚ�
	public static boolean CompareFileMD51() throws IOException {
		String newurl = ConfigFileMonitor.ConfigURL;
		 oldurl = ConfigFileMonitor.ConfigAllURl;
		File newfile = new File(newurl);
	if(!newfile.exists()){
		return false;
	}
		File oldfile = new File(oldurl);
		if(!oldfile.exists()){
			String str=oldurl.substring(0,oldurl.lastIndexOf('/'));
		File tempfile=new File(str);
		tempfile.mkdirs();
			oldfile.createNewFile();
		}
		try {
			String newM5 = getFileMD5Value(newfile);
			String oldM5 = getFileMD5Value(oldfile);
			if (newM5.equals(oldM5)) {
				return false;
			} else {
				replaceFileContent(newfile, oldfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return true;
	}

	/**
	 * �滻�ļ�����
	 */
	public static void replaceFileContent(File newfile, File oldfile) {
		FileWriter fw = null;
		FileInputStream in = null;
		try {
			in = new FileInputStream(newfile);
			fw = new FileWriter(oldfile);
			
			int line = in.read();
			while (line != -1) {
				char ch =(char)line;
				fw.write(ch);
				fw.flush();
				line = in.read();
			}
		fw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				fw.close();
				// newfile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
     * ��һ���ļ���ȡmd5ֵ
     * @return md5��
     */
	public static String getFileMD5Value(File file) throws Exception {
	        FileInputStream fileInputStream = null;
	        try {
	        fileInputStream = new FileInputStream(file);
	            byte[] buffer = new byte[1024*1024];
	            int length;
	            while ((length = fileInputStream.read(buffer)) != -1) {
	            MD5.update(buffer, 0, length);
	            }
	            return new String(Hex.encodeHex(MD5.digest()));
	        } catch (FileNotFoundException e) {
	        e.printStackTrace();
	            return null;
	        } catch (IOException e) {
	        e.printStackTrace();
	            return null;
	        } finally {
	            try {
	                if (fileInputStream != null)
	                fileInputStream.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	    }
	}
	/**
	 * ���������豸�����ļ�����
	 * 
	 * @return
	 */
	public boolean getAllConfigFile(String host,ConfigFileListDownLoad telnet1,String command,
			String downConfigNmae, String localhost,String CFDirectory,String CommandText,String cfname,String hostIP) {
		StringBuffer sb = new StringBuffer();
			telnet1.write(CFDirectory);
		    telnet1.write(command);
		    telnet1.write(hostIP);
		    telnet1.write(cfname);
		try {
			int line = this.in.read();
			while (line != -1) {
				char ch = (char) line;
				sb.append(ch);
				if (sb.toString().endsWith("success")){return true;}	
				line = this.in.read();
			}
			System.out.println("info=="+sb);
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			telnet1.write("exit");
			telnet1.write("exit");
		}
		return false;
		
	}
	/**
	 * ��������һ���豸��ϵͳ�����ļ�
	 * 
	 * @return
	 */
	public boolean getzhongxinConfigFile(String host,ConfigFileListDownLoad telnet1,String command,
			String downConfigNmae, String localhost) {
		StringBuffer sb = new StringBuffer();
		telnet1.write("cd cfg");
		telnet1.write("copy flash: startrun.dat tftp: //"+localhost+"/"+downConfigNmae);
		try {
			int line = this.in.read();
			while (line != -1) {
				char ch = (char) line;
				sb.append(ch);
				if (sb.toString().endsWith("success")){return true;}	
				line = this.in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			telnet1.write("exit");
			telnet1.write("exit");
		}
		return false;
	}
	/**
	 * �������һ���豸��ϵͳ�����ļ�
	 * @return
	 */
	public boolean getConfigFileRuiJie(String host,ConfigFileListDownLoad telnet1,String command,
			String downConfigNmae, String localhost) {
		StringBuffer sb = new StringBuffer();
		telnet1.write("copy startup-config tftp:");
		telnet1.write(localhost);
		telnet1.write(downConfigNmae);
		try {
			int line = this.in.read();
			while (line != -1) {
				char ch = (char) line;
				sb.append(ch);
				if (sb.toString().endsWith("success")){return true;}	
				line = this.in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			telnet1.write("exit");
			telnet1.write("exit");
		}
		return false;

	}

	/**
	 * ����˼�������ļ�
	 * 
	 * @return
	 */
	public boolean getConfigFileCisco(ConfigFileListDownLoad telnet1,
			String ConfigFileName, String localhost) {
		StringBuffer sb = new StringBuffer();
		telnet1.write("copy " + ConfigFileName + " tftp:");
		telnet1.write(localhost);	
		telnet1.write(ConfigFileName);
		try {
			int line = this.in.read();
			while (line != -1) {
				char ch = (char) line;
				sb.append(ch);
				if (sb.toString().endsWith("!!"))
					return true;
				if (sb.toString().endsWith("(Permission denied)")) {
					description="�����ļ�Ȩ�޲���";
					return false;
				}if(sb.toString().endsWith("(Is a directory)")){
					description="�����ļ���һ���ļ���";
					System.out.println(description);
					return false;
				}
				line = this.in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			telnet1.write("exit");
			telnet1.write("exit");
		}
		return true;

	}

	

	/**
	 * ���ػ�Ϊ�����ļ�
	 * 
	 * @return
	 */
	public boolean getConfigFileHuaWei(ConfigFileListDownLoad telnet1,
			String ConfigFileName, String localhost,String host) {
		telnet1.write("system-view");
		telnet1.write("tftp put " + ConfigFileName + " //"+localhost+"/"+host+"config.txt");
		StringBuffer sb = new StringBuffer();
		try {
			int line = this.in.read();
			while (line != -1) {
				char ch = (char) line;
				sb.append(ch);
				if (sb.toString().endsWith("Uploading succeeds!"))
					return true;
				if (sb.toString().endsWith("Uploading fails!")) {
					return  false;
				}
				line = this.in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			telnet1.write("quit");
			telnet1.write("exit");
			telnet1.write("exit");
		}
		return success;
	}

	public ConfigFileListDownLoad(String prompt, String ip, int port,
			String user, String password) {
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
		if(!"3".equals(user)){
			 write(user);
		}
		readUntil("Password:");
		write(password);
		return determineCommand(">", "Password:");
	}

	public String readUntil(String pattern) throws Exception {
		char lastChar = pattern.charAt(pattern.length() - 1);
		StringBuffer sb = new StringBuffer();
		int line = -1;
		line = this.in.read();
		char ch = (char) line;
		while (line != -1) {
			sb.append(ch);
				if (sb.toString().endsWith(pattern)) {
					return sb.toString();
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
				System.out.print(ch);
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
		return isCommadSuccess(content, errorStr, successStr) ;
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
		value = (value == null ? "" : value.trim());
		try {
			if(!("".equals(value))){
				this.out.println(value);
			}
			this.out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
