package COM.dragonflow.StandardMonitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.net.telnet.TelnetClient;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.CanceledException;
import org.eclipse.jgit.api.errors.DetachedHeadException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidConfigurationException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.diff.RenameDetector;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilterGroup;

import com.mindbright.jca.security.MessageDigest;

public class ConfigCopy {
	public static ConfigCopy cc= new ConfigCopy();
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
	private static String localPath;
	private static Repository localRepo;
	private static Git git;

	public  boolean success = false;
	private static String ApacheGitServer;
	private static String Apacheport;
	private static String URL;
	private static String UserName;
	private static String UserPassword;
	private static String remotePath;
	private static String remoteGit;
	private static String tempUrl;
public ConfigCopy (){
	
}
	public static String getURL() {
		return URL;
	}

	public static String getRemoteGit() {
		return remoteGit;
	}

	public static String getTempUrl() {
		return tempUrl;
	}

	static {
		getCon();
		localPath = URL;
		try {
			localRepo = new FileRepository(localPath + "/.git");
		} catch (IOException e) {
			e.printStackTrace();
		}
		remotePath = "http://" + ApacheGitServer + ":" + Apacheport + "/"
				+ remoteGit;
		git = new Git(localRepo);
	}

	public static void main(String[] args) {

	}

	/**
	 * 读取属性文件
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
		ApacheGitServer = p.getProperty("ApacheGitServer");
		Apacheport = p.getProperty("Apacheport");
		URL = p.getProperty("URL");
		UserName = p.getProperty("UserName");
		UserPassword = p.getProperty("UserPassword");
		remoteGit = p.getProperty("remoteGit");
		tempUrl = p.getProperty("tempUrl");

	}

	public ConfigCopy(String prompt, String ip, int port, String user,
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

	public String execexreadUntil(String pattern) throws Exception {
		StringBuffer sb = new StringBuffer();
		char ch = (char) this.in.read();
		while (true) {
			sb.append(ch);
			if (sb.toString().indexOf(">>>") != -1) {
				break;
			} else if (sb.toString().indexOf("No such option in this menu") != -1) {
				throw new Exception(sb.toString());
			} else if (sb.toString().indexOf("Press any key to return") != -1) {
				break;
			}
			ch = (char) this.in.read();
		}
		return sb.toString();
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

	public String readUntil2(String pattern, StringBuffer buffer) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(this.in);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.lastIndexOf("#") > -1) {
					return buffer.toString();
				}
				buffer.append(line + "\r\n");
				if (line.lastIndexOf("--More--") > -1) {
					break;
				}
				if (buffer.indexOf(pattern) > -1) {
					return buffer.toString();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return "";

	}

	public boolean readUntil3(String successMark, String failMark) {

		Scanner scanner = null;
		try {
			scanner = new Scanner(this.in);
			String line = null;
			while ((line = scanner.nextLine()) != null) {
				if (line.toLowerCase().lastIndexOf(failMark.toLowerCase()) > -1) {
					return false;
				}
				if (line.toLowerCase().lastIndexOf(successMark.toLowerCase()) > -1) {
					return true;
				}

			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}

	/**
	 * 判断命令是否执行成功
	 * 
	 * @param successStr
	 *            成功判断符
	 * @param errorStr
	 *            失败判断符
	 * @return
	 * @throws Exception
	 */
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
		// System.out.println(content);
		return isCommadSuccess(content, errorStr, successStr);

	}

	/**
	 * 获得分页结果
	 * 
	 * @param complateStr
	 *            是否完成标识
	 * @param nextpageStr
	 *            下一页标识
	 * @param sb
	 * @return
	 * @throws Exception
	 */
	public String doCommandGetPagedList(String complateStr, String nextpageStr,
			StringBuffer sb) throws Exception {

		if (sb == null) {
			sb = new StringBuffer();
		}
		boolean isNextpage = false;// 是否存在下一页
		do {
			int line = -1;
			line = this.in.read();
			char ch = (char) line;
			try {
				while (line != -1) {

					sb.append(ch);
					// System.out.print(ch);
					if (sb.toString().endsWith(nextpageStr)) {
						break;
					}
					if (sb.toString().endsWith(complateStr)) {
						break;
					}
					line = this.in.read();
					ch = (char) line;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			String content = sb.toString();
			isNextpage = isCommadSuccess(content, complateStr, nextpageStr);
			write(" ");
		} while (isNextpage);
		return sb.toString();
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

	public void disconnect() {
		try {
			this.in.close();
			this.out.flush();
			this.out.close();
			this.telnet.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String executeCommand(String cmd) {
		try {
			this.telnet.setConnectTimeout(10000);
			this.telnet.connect(this.ip, this.port);
			this.telnet.setSoTimeout(20000);
		} catch (SocketException e) {
			e.printStackTrace();
			return "连接失败!" + e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return "连接失败!" + e.getMessage();
		}
		this.in = this.telnet.getInputStream();
		this.out = new PrintStream(this.telnet.getOutputStream());
		try {
			if (login(this.user, this.password))
				return "用户名或密码错误!";
		} catch (Exception e) {
			e.printStackTrace();
			return "登录失败!" + e.getMessage();
		}
		String mess = "";
		try {

			String[] shells = cmd.split(prompt);
			if (shells != null && shells.length > 0) {
				for (String shell : shells) {
					write(shell.trim());
					mess = execexreadUntil(this.prompt + " ");
				}
			} else {
				write(cmd.trim());
				mess = execexreadUntil(this.prompt + " ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "执行命令失败!" + e.getMessage();
		}
		System.out.println("执行命令成功!");
		return mess;
	}

	public static String testIsConnection(String endoperator, String server,
			String port, String username, String password) {
		int ports = 23;
		try {
			ports = Integer.valueOf(port).intValue();
		} catch (NumberFormatException e) {
			ports = 23;
		}
		ConfigCopy telnet = new ConfigCopy(endoperator, server, ports,
				username, password);
		String mess = telnet.Connection();
		telnet.disconnect();
		return mess;
	}

	public static String execCustomShell(String endoperator, String server,
			String port, String username, String password, String shellCommand) {
		int ports = 23;
		try {
			ports = Integer.valueOf(port).intValue();
		} catch (NumberFormatException e) {
			ports = 23;
		}
		ConfigCopy telnet = new ConfigCopy(endoperator, server, ports,
				username, password);
		String mess = telnet.executeCommand(shellCommand);
		telnet.disconnect();
		return mess;

	}

	/**
	 * 比较2个文件的内容是否一致
	 * 
	 * @param f1
	 * @param f2
	 * @return
	 */
	private static boolean CompareFile(File f1, StringBuffer sb1) {
		StringBuffer sf = new StringBuffer();
		boolean flag = false;
		if (f1 == null) {
			return true;
		} else if (sb1.length() == 0) {
			System.err.println("连接失败3");
			return false;
		} else {
			try {
				FileReader fr = new FileReader(f1);
				StringBuffer sb = new StringBuffer();
				int line = fr.read();
				while (line != -1) {
					sb.append((char) line);
					line = fr.read();
				}
				if (sb1.toString().equals(sb.toString())) {
				} else {
					flag = true;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;// 返回false为2个文件相同
	}

	/**
	 * 判断命令是否成功
	 * 
	 * @param string
	 * @param string2
	 * @return
	 * @throws IOException
	 */
	private boolean determineCommand1(String successStr, String errorStr)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		int line = -1;
		line = this.in.read();
		char ch = (char) line;
		while (line != -1) {
			sb.append(ch);
			line = this.in.read();
			ch = (char) line;
		}
		if (sb.toString().endsWith(successStr)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 得到最新版本的配置文件
	 */
	/**
	 * @param fileName
	 */
	public static File getFileName(String fileName) {
		File f = new File(fileName);
		File newFile = null;
		File[] arr = f.listFiles();
		if (arr == null) {
			f.mkdirs();
			return newFile;
		} else if (arr.length == 0) {
			return newFile;
		} else {
			String file = f + "/" + arr[arr.length - 1].getName();
			newFile = new File(file);
		}
		return newFile;
	}

	/**
	 * 命令窗口信息读取,并写入文件(思科)
	 */
	public boolean readCisco(String fileName, ConfigCopy telnet1, String host)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		int line = this.telnet.getInputStream().read();
		char ch = (char) line;
		while (line != -1) {
			sb.append(ch);
			if (sb.toString().trim().endsWith("More")) {
				telnet1.write(" ");

			} else if (sb.toString().trim().endsWith("#")) {
				break;
			}
			line = this.telnet.getInputStream().read();
			ch = (char) line;
		}
		for (int i = 0; i < sb.length(); i++) {
			char ch1 = sb.charAt(i);
			sb1.append(ch1);
			if (sb1.toString().endsWith(" --More--         ")) {
				String str = " --More--         ";
				sb1.replace(sb1.length() - str.length(), sb1.length(), "");
			}
		}

		// if(sb1.toString().endsWith("!")){
		sb1.delete(0, sb1.indexOf("!"));
		// }

		sb1.delete(sb1.lastIndexOf("end") + "end".length(), sb1.length());
		File cfile = new File(fileName + "/config.txt");
		cfile.createNewFile();
		boolean flag = CompareFile(cfile, sb1);
		if (flag) {
			fout = new FileOutputStream(cfile);
			for (int i = 0; i < sb1.length(); i++) {
				if (sb1.length() != 0) {
					fout.write(sb1.charAt(i));
				} else {
					return false;
				}
			}
			fout.flush();
			try {

				commitConifgFile(cfile);
			} catch (NoFilepatternException e) {
				e.printStackTrace();
			} catch (GitAPIException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 命令窗口信息读取,并写入文件(华为)
	 * 
	 * @param FileName
	 *            文件全路径
	 * @return
	 * @throws IOException
	 */
	public boolean readUntil6(String fileName, ConfigCopy telnet1, String host)
			throws IOException {

		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		int line = this.telnet.getInputStream().read();
		char ch = (char) line;
		while (line != -1) {
			sb.append(ch);
			if (sb.toString().trim().endsWith("More")) {
				telnet1.write(" ");
			} else if (sb.toString().trim().endsWith("<Quidway>")) {
				break;
			}
			line = this.telnet.getInputStream().read();
			ch = (char) line;
		}
		for (int i = 0; i < sb.length(); i++) {
			char ch1 = sb.charAt(i);
			sb1.append(ch1);
			if (sb1.toString()
					.endsWith(
							"---- More ----[42D                                          [42D")) {
				String str = "---- More ----[42D                                          [42D";
				sb1.replace(sb1.length() - str.length(), sb1.length(), "");

			}
		}
		File cfile = new File(fileName + "/config.txt");

		cfile.createNewFile();
		if (sb1.indexOf("#") > 0) {
			sb1.delete(0, sb1.indexOf("#"));
		}
		sb1.delete(sb1.lastIndexOf("return") + "return".length(), sb1.length());
		boolean flag = CompareFile(cfile, sb1);
		if (flag) {
			fout = new FileOutputStream(cfile);
			for (int i = 0; i < sb1.length(); i++) {
				if (sb1.length() != 0) {
					fout.write(sb1.charAt(i));
				} else {
					return false;
				}
			}
			fout.flush();
			fout.close();
			try {
				commitConifgFile(cfile);
			} catch (NoFilepatternException e) {
				e.printStackTrace();
			} catch (GitAPIException e) {
				e.printStackTrace();
			}
		}
		return flag;

	}

	/**
	 * clone 库 到本地
	 * 
	 * @throws IOException
	 * @throws InvalidRemoteException
	 * @throws TransportException
	 * @throws GitAPIException
	 */
	public static void testClone() throws IOException, InvalidRemoteException,
			TransportException, GitAPIException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		File f = new File(localPath);
		Repository repository = builder.setGitDir(f).readEnvironment()
				.findGitDir().build();
		Git git = new Git(repository);
		CloneCommand clone = git.cloneRepository();

		clone.setBare(false);
		clone.setCloneAllBranches(true);
		clone.setDirectory(f).setURI(remotePath);
		UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider(
				UserName, UserPassword);
		clone.setCredentialsProvider(user);
		// System.out.println("clone=" + clone);
		clone.call();

	}

	/**
	 * 将配置文件存到一个git库中
	 */

	public static void commitConifgFile(File cfile) throws IOException,
			NoFilepatternException, GitAPIException {
		if (!cfile.isFile()) {
			cfile.createNewFile();
		}
System.out.println(cfile);
		git.add().addFilepattern(".").call();
		RevCommit commit = git.commit().setMessage("配置文件有更新").call();
		String s = commit.getName();
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("提交到本地库成功");

	}

	/**
	 * 上传到服务器
	 * 
	 * @throws InvalidRemoteException
	 * @throws TransportException
	 * @throws GitAPIException
	 * @throws Exception
	 */
	public static void testpush() throws InvalidRemoteException,
			TransportException, GitAPIException, Exception {

		PushCommand push = git.push();
		push.setRemote(remotePath);
		UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider(
				UserName, UserPassword);
		push.setCredentialsProvider(user);
		push.call();
	}

	/**
	 * pull到本地
	 * 
	 * @throws GitAPIException
	 * @throws TransportException
	 * @throws NoHeadException
	 * @throws RefNotFoundException
	 * @throws CanceledException
	 * @throws InvalidRemoteException
	 * @throws DetachedHeadException
	 * @throws InvalidConfigurationException
	 * @throws Exception
	 */
	public static void testpull() throws Exception {
		PullCommand pull = git.pull();
		UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider(
				UserName, UserPassword);
		pull.setCredentialsProvider(user);
		pull.call();
	}

	/**
	 * 思科配置文件下载
	 */
	public static boolean ConfigCisco(String host, String configName,
			String pwd, String superName, String superPwd, String command,
			String groupname, String serverName) {
		ConfigCopy telnet1 = new ConfigCopy(">", host, 23, "3", pwd);
		String str = telnet1.Connection();// 连接
		boolean flag = false;
		if (!("登录成功!".equals(str))) {
			System.out.println("连接失败IP未连接上");
			return cc.success = false;
		}
		// System.out.println(str);
		telnet1.write(superName);// 权限
		try {
			String str1 = telnet1.readUntil("Password:");//
			telnet1.write(superPwd);// 权限密码
			cc.success = telnet1.determineCommand("#", "Password:");// 连接是否成功
			if (!cc.success) {
				// System.out.println("密码错误");
				return cc.success = false;
			}
			String f = configName + "/" + groupname + "/" + serverName + "/"
					+ host;
			File ciscofile = new File(f);
			ciscofile.mkdirs();
			telnet1.write(command);
			flag = telnet1.readCisco(f, telnet1, host);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			telnet1.write("exit");
			telnet1.write("exit");
		}
		return flag;
	}

	/**
	 * 中兴配置文件下载
	 */
	public static boolean ConfigZhongXin(String host, String configName,
			String pwd, String superName, String superPwd, String command,
			String groupname, String serverName) {
		boolean flag = false;
		ConfigCopy telnet1 = new ConfigCopy(">", host, 23, "3", pwd);
		String str = telnet1.Connection();
		// System.out.println(str);
		if (!("登录成功!".equals(str))) {
			System.out.println("连接失败IP未连接上");
			return cc.success = false;
		}
		telnet1.write(superName);
		try {
			String str1 = telnet1.readUntil("Password:");
			telnet1.write(superPwd);
			cc.success = telnet1.determineCommand(">", "Password:");
			if (!cc.success) {
				System.out.println("连接失败，权限名或密码错误");
				return cc.success = false;
			}
			String f = configName + "/" + groupname + "/" + serverName + "/"
					+ host;
			File huafile = new File(f);
			huafile.mkdirs();
			telnet1.write(command);
			//flag = telnet1.readUntil6(f, telnet1, host);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			telnet1.write("exit");
			telnet1.write("exit");
		}
		return flag;
	}
	/**
	 * 华为配置文件下载
	 */
	public static boolean ConfigHuaWei(String host, String configName,
			String pwd, String superName, String superPwd, String command,
			String groupname, String serverName) {
		boolean flag = false;
		ConfigCopy telnet1 = new ConfigCopy(">", host, 23, "3", pwd);
		String str = telnet1.Connection();
		// System.out.println(str);
		if (!("登录成功!".equals(str))) {
			System.out.println("连接失败IP未连接上");
			return cc.success = false;
		}
		telnet1.write(superName);
		try {
			String str1 = telnet1.readUntil("Password:");

			telnet1.write(superPwd);
			cc.success = telnet1.determineCommand(">", "Password:");
			if (!cc.success) {
				System.out.println("连接失败，权限名或密码错误");
				return cc.success = false;
			}
			String f = configName + "/" + groupname + "/" + serverName + "/"
					+ host;
			File huafile = new File(f);
			huafile.mkdirs();
			telnet1.write(command);
			flag = telnet1.readUntil6(f, telnet1, host);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			telnet1.write("exit");
			telnet1.write("exit");
		}
		return flag;
	}

}
