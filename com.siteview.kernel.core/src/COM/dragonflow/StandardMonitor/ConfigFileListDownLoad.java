package COM.dragonflow.StandardMonitor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.net.telnet.TelnetClient;

import sun.misc.BASE64Encoder;
import system.Convert;
import system.IO.FileStream;
import system.Security.Cryptography.MD5CryptoServiceProvider;

import Siteview.Api.BusinessObject;

public class ConfigFileListDownLoad {
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
	private String pwd = "";
	private String configSystem = "system-view";
	static MessageDigest MD5 = null;
	 static {
	        try {
	        MD5 = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException ne) {
	        ne.printStackTrace();
	        }
	    }
//	public static void main(String[] args) {
//		 Link("192.168.9.254", "root", "super", "rootroot", "vrpcfg.txt", "华为");
//	}

	/**
	 * 测试连接
	 * 
	 * @param host
	 * @param pwd
	 * @param superName
	 * @param superPwd
	 * @param dir
	 * @return
	 */

	public static boolean Link(String host, String pwd, String superName,
			String superPwd, String ConfigFileName, String serviceName) {

		ConfigFileListDownLoad telnet1 = new ConfigFileListDownLoad(">", host,
				23, "3", pwd);
		String str = telnet1.Connection();// 连接
		boolean flag = false;
		if (!("登录成功!".equals(str))) {
			return false;
		}
		telnet1.write(superName);// 权限

		try {
			String localhost = java.net.InetAddress.getLocalHost()
					.getHostAddress();
			if ("思科".equals(serviceName)) {

				String str1 = telnet1.readUntil("Password:");
				telnet1.write(superPwd);// 权限密码
				success = telnet1.determineCommand("#", "Password:");// 连接是否成功
//				System.out.println(success+"sike1");
				if (!success) {
					return false;
				}
				flag = telnet1.getConfigFileCisco(telnet1, ConfigFileName,
						localhost);
			} else if ("华为".equals(serviceName)) {
				String str1 = telnet1.readUntil("Password:");
				telnet1.write(superPwd);// 权限密码
				success = telnet1.determineCommand(">", "Password:");// 连接是否成功
				if (!success) {
					return false;
				}
				flag = telnet1.getConfigFileHuaWei(telnet1, ConfigFileName,
						localhost);
			}
			if(flag){
				flag = CompareFileMD5();
			}
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// 比较文件内容
	public static boolean CompareFileMD5() throws IOException {
		String newurl = ConfigDownLoadUploadMonitor.ConfigURL;
		String oldurl = ConfigDownLoadUploadMonitor.ConfigAllURl;
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
	 * 替换文件内容
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
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				fw.close();
				 newfile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//	public static void main(String[] args) {
//		File f1= new File("C:/download/vrpcfg.txt");
//		File f2= new File("C:/CfrConfigFile/jonee/华为/192.168.9.254/vrpcfg.txt");
//		
//		try {
//			System.out.println(getFileMD5Value(f1));
//			System.out.println(getFileMD5Value(f2));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	/**
     * 对一个文件获取md5值
     * @return md5串
     */
	public static String getFileMD5Value(File file) throws Exception {

		
	        FileInputStream fileInputStream = null;
	        try {
	        fileInputStream = new FileInputStream(file);
	            byte[] buffer = new byte[8192];
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
	 * 下载思科配置文件
	 * 
	 * @return
	 */
	public boolean getConfigFileCisco(ConfigFileListDownLoad telnet1,
			String ConfigFileName, String localhost) {
		telnet1.write("copy " + ConfigFileName + " tftp");
		telnet1.write(localhost);
		telnet1.write(ConfigFileName);
		StringBuffer sb = new StringBuffer();
		try {
			int line = this.in.read();
			while (line != -1) {
				char ch = (char) line;
				sb.append(ch);
				if (sb.toString().endsWith("!!"))
					return true;
				if (sb.toString().endsWith("%Error")) {
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
	 * 下载华为配置文件
	 * 
	 * @return
	 */
	public boolean getConfigFileHuaWei(ConfigFileListDownLoad telnet1,
			String ConfigFileName, String localhost) {
		telnet1.write("system-view");
		telnet1.write("tftp put " + ConfigFileName + " //"+localhost);
//		telnet1.write(localhost);
//		telnet1.write(ConfigFileName);

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
			return "连接失败!" + e.getMessage();
		} catch (IOException e) {
			// e.printStackTrace();
			return "连接失败!" + e.getMessage();
		}
		this.in = this.telnet.getInputStream();
		this.out = new PrintStream(this.telnet.getOutputStream());
		try {
			if (!login(this.user, this.password))
				return "用户名或密码错误!";
		} catch (Exception e) {
			e.printStackTrace();
			return "登录失败!" + e.getMessage();
		}

		return "登录成功!";
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
	 * 写入命令
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
}
