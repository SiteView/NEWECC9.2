package COM.dragonflow.itsm.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class JDBCForSQL {
	private static Connection conn = getConnection();

	private static String driverName;
	private static String url;
	private static String user;
	private static String password;

	/**
	 * get Connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Properties p = new Properties();
		FileReader fr = null;
		try {
			fr = new FileReader(new File("db.properties"));
			p.load(fr);
			url = p.getProperty("url");
			user = p.getProperty("user");
			password = p.getProperty("password");
			driverName = p.getProperty("driverName");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Class.forName(driverName)
					.newInstance();
			conn = DriverManager.getConnection(url, user, password);
		} catch (java.lang.ClassNotFoundException ce) {
			System.out.println("Get Connection error:");
			ce.printStackTrace();
		} catch (java.sql.SQLException se) {
			System.out.println("Get Connection error:");
			se.printStackTrace();
		} catch (Exception e) {
			System.out.println("Get Connection error:");
			e.printStackTrace();
		}
		return conn;
	}


	/*
	 * the Program is to Select the database!!!
	 */
	public static ResultSet sql_ConnectExecute_Select(String query_sql) {
		ResultSet rs = null;
		try {
			if (conn.isClosed()) {
				conn = getConnection();
			}
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(query_sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void execute_Insert(String sql) {

		try {
			if (conn.isClosed()) {
				conn = getConnection();
			}
			Statement statement = conn.createStatement();
			statement.execute(sql);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testConnection() {
		if (conn == null)
			conn=getConnection();
		try {
			String sql = "SELECT * FROM Ecc";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("RecId"));
			}
		} catch (SQLException e) {
		} 
	}

	public static void main(String[] args) {
		JDBCForSQL jdbc = new JDBCForSQL();
		// SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Timestamp d2=new Timestamp(System.currentTimeMillis());
		// jdbc.savaLog("insert into MonitorLog(RecId,ownerID,MonitorStatus,MonitorName,MonitorId,MonitorMassage,CreatedDateTime)values('ad26c8ee090d47dfaa9a3ee477c1ba90','lili','good','ping131','1','0.01 sec*39**','"+Timestamp.valueOf(f.format(d2))+"')");
		String query_sql = "select * from Ecc where Groups_Valid ='5042ACCD47B2495A97D814DCB4D3E2B9'";
		ResultSet eccrs = JDBCForSQL.sql_ConnectExecute_Select(query_sql);
		ResultSetMetaData metaData;
		try {
			metaData = eccrs.getMetaData();
			int colum = metaData.getColumnCount();
			while (eccrs.next()) {
				for (int i = 1; i < colum; i++) {
					// Get colum name
					String columName = metaData.getColumnName(i);
					String datavalue = eccrs.getString(columName);
					System.out.println("ÁÐ£º" + columName + " Öµ£º" + datavalue);
					if (datavalue == null )
						System.out.println("¿ÕÁÐ£º" + columName);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
