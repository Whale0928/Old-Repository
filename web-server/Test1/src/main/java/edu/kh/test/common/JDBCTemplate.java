package edu.kh.test.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {

	private static Connection conn = null;

	public static Connection getConnection() {

		try {

			// JNDI (Java Naming and Directory Interface API)
			// - 디렉토리에 접근하는데 사용하는 JAVA API
			// - 애플리케이션(프로그램 ,웹앱)은 JNDI를 이용해서 파일 또는 서버 Resource를 찾을 수 있다.

			Context initContext = new InitialContext();

			// servers - > Context.xml 파일 찾기
			Context envContext = (Context) initContext.lookup("java:/comp/env");

			// DBCP 세팅의 <Resource> 태그 찾아 DataSource 형식의 객체로 얻어오기
			// DataSource : Connection pool을 구현하는 객체(만들어둔 Connection 얻어오기 가능)

			DataSource ds = (DataSource) envContext.lookup("jdbc/oracle");

			conn = ds.getConnection();
			conn.setAutoCommit(false);

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
