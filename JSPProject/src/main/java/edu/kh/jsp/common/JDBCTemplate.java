package edu.kh.jsp.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	private static Connection conn = null;

	public static Connection getConnection() {

		try {

			if (conn == null || conn.isClosed()) {

				Properties prop = new Properties();

				String filePath = JDBCTemplate.class.getResource("/edu/kh/jsp/sql/driver.xml").getPath();
// JDBCTemplate.class의 파일을 기준으로 특정 리소스를 얻어오겟다 /edu/kh/jsp/sql/driver.xml

				prop.loadFromXML(new FileInputStream(filePath));

				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);

				conn.setAutoCommit(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
