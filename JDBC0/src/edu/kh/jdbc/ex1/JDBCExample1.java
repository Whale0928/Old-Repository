package edu.kh.jdbc.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {
	public static void main(String[] args) {
		// JDBC : java가 DB와 연결할 수 있게 해주는 java API
		// JDBC 객체 참조 변수 (JAVA.SQL 패키지.)

// [1단계]JDBC 객체 참조 변수 선언 (JAVA.SQL 패키지(

		Connection conn = null;
		// DB 연결 정보를 담은 객체
		// JAVA와 DB사이를 연결해 주는 일종의 통로(스트림).

		Statement stmt = null;
		// Connection 객체를 통해
		// JAVA에서 작성된 sql을 db로 전달하여 수행한 후.
		// 결과를 반환 받아 다시 JAVA로 돌아오는 역활의 객체

		ResultSet rs = null;
		// SELECT 질의문 성공시 반환되는
		// 결과 행의 집함 (ResultSet)을 참조하는 변수.

		try {
// [2단계] 참조 변수에 알맞은 객체 대입하기.

			// 2.1) DB연결에 필요한 oracle JDBC Driver 메모리 로드하기.
			// - > Oracle JDBC Driver가 어디에 있는지 알려주면 알아서 메모리에 로드됨
			// - >생략 가능

			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ClassNotFoundException 발생 가능성이 높다

			// 2.2) DB연결에 필요한건 준비 했으니 이제 연결.
			// 연결 정보를 담은 Connection을 생성 (이때 DriverManaget 객체가 필요함)
			// DriverManager JDBC드라이버를 통해 Connection 객체를 만드는 역활

			String type = "jdbc:oracle:thin:@"; // JDBC드라이버가 thin이라는 타입이다.
			String ip = "192.168.45.7"; // loop back ip(127.0.0.1) [DB 서버 컴퓨터 IP]
			String port = ":1521"; // 포트번호.
			String sid = ":xe"; // DB이름
			String user = "khg"; // 사용자명
			String pw = "khg1234";// 비밀번호.

//			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.45.1:1521:xe", "khg", "khg1234");

			// 중간 확인
			System.out.println(conn);

			// 2.3) Statement 객체에 적재할 SQL 작성하기.
			// ************JAVA에서 작성된 SQL문은 마지막에 ;(세미콜론)찍지 않아야한다.************
			// - > '유효하지 않은 문자 오류'를 발생시킨다.
			String sql = "SELECT EMP_ID,EMP_NAME,SALARY,DEPT_CODE FROM EMPLOYEE";

			// 2.4) Statement 객체를 생성
			stmt = conn.createStatement();

			// 2.5) SQL을 Statement에 적재후 DB로 전달하여 수행후
			// 결과를 반환받아와 rs 변수에 저장
			rs = stmt.executeQuery(sql);

			// DB에서 SELECT 수행한 결과 (Result Set) 객체를 얻어와 rs가 참조하게 한다.

//[3단계] : SELECT의 수행 결과를 '한 행씩' 접근하여 원하는 컬럼값 얻어 오기.
			int count = 0;
			while(rs.next()) {
				//rs.next() : 참조하고 있는 ResultSet 객체의 첫번째 컬럼부터 순서대로 한 행씩 이동하면서
				//			   다음 행이 있을 경우 True를 반환.
				
				//rs.get[Type](컬럼명) : 현재 가리키고 있는 행의 특정 컬럼 값을 얻어옴
				//[Type]은 DB에서 얻어와서 Java에 저장할 자료형(Java 쪽 자료형)
				
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				String deptCode = rs.getString("DEPT_CODE");
				count++;
				//조회 결과 출력
				System.out.printf("아이디 : %s , 이름 : %s, 월급 : %7d, 부서번호 : %s  \n"
											,empId,empName,salary,deptCode);
				
			}System.out.println("행의 개수 : "+ count);
			
		} catch (SQLException e) {
			// SQLException : DB연결 관련 예외의 최상위 부모
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("OJDBC 라이브러리 미등록 또는 경로 오타.");
			e.printStackTrace();
		}finally {
//[4단계] : 사용한 JDBC 객체 자원 반환 (close)
			
			//자원 반환 순서는 객체 생성 순서의 역순.
			//생성 순서 : Connection , Statement , ResultSet 
			//반환 순서 : ResultSet , Statement , Connection
			
			try {
				//NullPointException을 방지하기 위한 if문
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}
}
