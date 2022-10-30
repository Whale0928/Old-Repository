package edu.kh.rental.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	/*
	 * DB 연결 , JDBC 자원 반환 , 트랜잭션 제어 반복적으로 사용되는JDBC 관련 코드를 모아둔 클래스
	 * 
	 * 
	 * getConnection( ) 메소드 close(Connection | Statement | ResultSet ) 메서드 --
	 * PreparedStatement는 Statement의 자식이므로 매개변수 다형성으로 한번에 처리
	 * 
	 * 
	 * commit (Connection) rollback(Connection)
	 */

	// 필드
	// static 메서드세어
	// 해당 필드를 사용할려면
	// 필드도 static이여야 한다.
	private static Connection conn = null; // 초기값 null
	
	// 메소드

	// DB연결 정보를 담고 있느 Connection 객체 반환 메서드
	public static Connection getConnection() {

		try {
			// 계속 공용으로 사용되는 conn 변수에
			// 커넥션이 대입된적 없거나(Null)
			// 또는 이전에 커넥션이 있었으나 닫힌 경우 (isClosed())
			// 새로운 커넥션이 필요한 상황

			// 커넥션을 계속 닫고 만들고, ... 닫고 , 만들고를 반복
			// - > 프로그램 하나에 커넥션 1개만을 운용
			if (conn == null || conn.isClosed()) {

				Properties prop = new Properties();
				// K,V가 모든 String인 MAP ,XML 파일 입출력에 특화

				// driver.XML 파일 읽어오기
				prop.loadFromXML(new FileInputStream("driver.xml"));

				// 커넥션 생성준비
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String password = prop.getProperty("password");
				// 1) jdbc 드라이버 메모리 로드
				Class.forName(driver);
				// 2) DriverManager 커넥션 생성
				conn = DriverManager.getConnection(url, user, password);

				// 3)트랜잭션 제어를 위한 자동 커밋 비활성화
				conn.setAutoCommit(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	// cloes( ) 메서드 작성
	// Connection 반환 메소드
	public static void close(Connection conn) {
		try {
			// 참조 하는 Connection 이 있으면서 닫혀 있지 않은 경우
			// conn.isClosed() 닫혀 있으면 true
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Statement(부모) , PreparedStatement 반환 메소드
	public static void close(Statement stmt) {
		try {
			// 참조 하는 Statement 이 있으면서 닫혀 있지 않은 경우
			// stmt.isClosed() 닫혀 있으면 true
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ResultSet  반환 메소드
	public static void close(ResultSet rs) {
		try {
			// 참조 하는 ResultSet 이 있으면서 닫혀 있지 않은 경우
			// rs.isClosed() 닫혀 있으면 true
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 트랜잭션 제어 메소드
	// commit 메소드
	public static void commit(Connection conn) {
		try {
			// 참조하는 connection 있는 경우
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// rollbakc 메소드
	public static void rollback(Connection conn) {
		try {
			// 참조하는 connection 있는 경우
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
