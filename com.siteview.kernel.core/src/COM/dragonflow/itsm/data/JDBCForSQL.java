package COM.dragonflow.itsm.data;

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

public class JDBCForSQL {
	private static Connection conn = getConnection();
	/**
	 * get Connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();
			String URL = "jdbc:sqlserver://192.168.9.33:1433;DatabaseName=EccSiteView";
			String USER = "sa";
			String PASSWORD = "siteview";
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
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
}
