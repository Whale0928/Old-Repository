package edu.kh.jdbc.ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample2 {
	public static void main(String[] args) {
		// 예제 1번 다시 써보면서 분석하기.

		// JDBC 객체를 참조 변수 선언.

		Connection conn = null; // DB와 연결할 통로(일종의 스트림)
		Statement stmt = null; // conn을 통해 DB에 sql문을 보내고 가져올 셔틀 역활
		ResultSet rs = null; // stmt로 가져온 값 resultSet을 저장한 역활

		try {

			// DB와 연결될 변수들을 만들었으니간 이제 연결
			// 이때 conn이 DB와 연결 할 수 있도록 DriverManager JDBC드라이버를 이용해 생성 해준다
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 여기서 ClassNotFound 예외가 발생할 수 있음.

			// 이제 conn에 DB를 연결하기 위한 통로를 생성한다.

			String type = "jdbc:oracle:thin:@"; // 드라이버가 thin 타입임을 확인.
			String ip = "127.0.0.1"; // DB의 ip 주소
			String port = ":1521"; // ip 주소와 port가 있어야지 접근할 수있다
			String sid = ":xe"; // DB의 이름.

			String user = "khg"; // 사용자의 이름
			String pw = "khg1234"; // 사용자 비밀번호

			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);

			// 통로를 열었으니 sql을 우선 만들고 그 질의문을 담을 셔틀을 만든다

			String sql = "SELECT EMP_NAME,EMP_ID,SALARY,DEPT_CODE,JOB_NAME FROM EMPLOYEE JOIN JOB USING(JOB_CODE) ORDER BY JOB_CODE,SALARY ";

			// 셔틀을 만들고
			stmt = conn.createStatement();
			// 셔틀에 sql을 담는다dad
			rs = stmt.executeQuery(sql);

			// 급여의 합계를 구할 변수
			int others = 0;
			int ceo = 0;
			int subCeo = 0;

			int sum = 0;

			// sql 테이블에 반복 접근하면서 한 행씩 출력
			while (rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String name = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				String deptCode = rs.getString("DEPT_CODE");
				String jobTitle = rs.getString("JOB_NAME");

				if (jobTitle.equals("대표")) {
					ceo += salary;
				} else if (jobTitle.equals("부사장")) {
					subCeo += salary;
				} else {
					others += salary;
				}
				sum += salary;
				System.out.printf("아이디 : %d  이름 : %s  월급 : %7d  부서코드 : %s  직급명 : %s \n", empId, name, salary, deptCode,
						jobTitle);
			}
			System.out.printf("\n대표의 급여 : %8d\n부사장의 급여 : %8d\n사원들의 급여 : %8d\n총 인건비 : %8d ", ceo, subCeo, others, sum);

		} catch (SQLException e) {
			// DB 연결 관련 최상위 예외
			e.printStackTrace();// 오류 내용 출력
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close(); // 먼저 resultSet의 값을 닫고
				if (stmt != null)
					stmt.close(); // 2번째로 열린 셔틀을 닫고
				if (conn != null)
					conn.close(); // db와 연결을 끈는다.
			} catch (SQLException e) {
				System.out.println("메모리 정리중 오류");
				e.printStackTrace();
			}
		}
	}
}
