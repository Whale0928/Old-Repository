package edu.kh.jdbc.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.model.vo.Employee;

//DAO (Date Access Object) : 데이터 접근 객체
// - DB에 연결되어 SQL을 수행하고 결과를 반환 받는
/**
 * @author rlagu
 *
 */
public class EmployeeDAO {

	// JDBC 객체 저장용 참조 변수 필드 선언

	private Connection conn;
	// DB 연결정보를 담은 객체(JAVA-DB사이의 통로 역할)

	private Statement stmt;
	// DB연결 정보를 가지고 값을 보내고 가져오는것
	// Connection을 통해 SQL을 수행하고 결과를 반환 받는 객체.

	private PreparedStatement pstmt;
	//statement의 자식으로 좀 더 향상된 기능을 제공
	// -? 위치 홀더를 이용하여 sql에 작성되어 지는 리터럴를 동적으로 제어한다.
	// -- 오타 위험이 감소 / 가독성상승
	
	private ResultSet rs;
	// 수행한 결과를 저장하는 객체
	// SELECT 수행 결과를 저장하는 객체.

	/**
	 * 전체사원 정보 조회 DAO
	 * 
	 * @return
	 */
	public List<Employee> selectAll() {

		// 결과 저장용 변수 준비
		List<Employee> empList = new ArrayList<Employee>();

		try {
			// 1) oracle JDBC Driver 메모리 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) DB연결 작업(Connection 얻어오기)
			String type = "jdbc:oracle:thin:@"; // 오라클 드라이버의 타입
			String ip = "localhost"; // 루프백아이피와 같은 역활
			String port = ":1521"; // 포트번호
			String sid = ":xe";// db의 이름
			String user = "khg";
			String pw = "khg1234";

			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			// DriverManager : Connection 생성 메소드를 제공

			// 3) 수행 할 SQL 작성
			String sql = "SELECT * FROM EMPLOYEE2 ORDER BY EMP_ID";
			// SQL 명령문에 ';' 세미콜론 금지

			// 4) Statment 객체 생성
			stmt = conn.createStatement(); // 커넥션을 왔다 갔다하는 셔틀버스 역활

			// 5) sql 수행 후 ResultSet반환 받기
			rs = stmt.executeQuery(sql);

			// 6) 결과를 List에 옮겨 담기
			// ResultSet을 한행씩 접근하여 컬럼 값을 얻어와
			// 한 행의 정보가 담긴 Employee 객체를 생성하고
			// 이를 empList에 추가
			while (rs.next()) {
				// 다음행이 있으면 true 반환 , 호출시 마다 다음 행으로 이동.
				int empId = rs.getInt("EMP_ID"); //
				String empName = rs.getString("EMP_NAME");
				String empNO = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary = rs.getInt("SALARY");
				double bonus = rs.getDouble("BONUS");
				int managerId = rs.getInt("MANAGER_ID");
				Date hireDate = rs.getDate("HIRE_DATE");
				Date entDate = rs.getDate("ent_DATE");
				char entYn;
				if(rs.getString("ENT_YN")==null) {
					entYn = 'X';
				}else {					
					entYn = rs.getString("ENT_YN").charAt(0);
				}

				// rs.getChar()는 존재하지 않음
				// 자바에서는 문자 하나라는 개념이 있지만
				// DB에서는 문자열만 존재.

				// 얻어온 컬럼 값으로 객체 생성 후 초기화
				Employee emp = new Employee(empId, empName, empNO, email, phone, deptCode, jobCode, salLevel, salary,
						bonus, managerId, hireDate, entDate, entYn);

				empList.add(emp);
			}

		} catch (Exception e) {
			// Exception 모든 예외의 최상위 부모
			// try에서 발생하는 모든 예외 종류 상관 없이 처리
			e.printStackTrace();
		}finally {
			// 7) 사용한 JDBC 자원 반환(close)
			try {
				if(rs != null)rs.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		// 8) List 호출부로 반환

		
		return empList;
	}

	/**사번으로 사원 정보 조회 DAO
	 * @param input
	 * @return
	 */
	public Employee selectOne(int input) {
		Employee emp = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection conn;
			Statement stmt;
			ResultSet rs;
			
			String type = "jdbc:oracle:thin:@"; // 오라클 드라이버의 타입
			String ip = "localhost"; // 루프백아이피와 같은 역활
			String port = ":1521"; // 포트번호
			String sid = ":xe";// db의 이름
			String user = "khg";
			String pw = "khg1234";

			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			
			
			String sql = "SELECT * FROM EMPLOYEE2 WHERE EMP_ID = "+input;
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql);
			
			//조회 결과가 있다면 1행밖에 나오지 않음으로 while 대신 if 사용
			if(rs.next()) {
				int empId = rs.getInt("EMP_ID"); //
				String empName = rs.getString("EMP_NAME");
				String empNO = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary = rs.getInt("SALARY");
				double bonus = rs.getDouble("BONUS");
				int managerId = rs.getInt("MANAGER_ID");
				Date hireDate = rs.getDate("HIRE_DATE");
				Date entDate = rs.getDate("ent_DATE");
				char entYn = rs.getString("ENT_YN").charAt(0);
				
				emp = new Employee(empId, empName, empNO, email, phone, deptCode, jobCode, salLevel, salary,
						bonus, managerId, hireDate, entDate, entYn);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("모든 에러");
		}finally {
			try {
				if(rs != null)rs.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return emp;
	}

	/**월급으로 직원 정보 조회
	 * @param input
	 * @return
	 */
	public List<Employee> selectSal(int input){	
		
		List<Employee> empList = new ArrayList<Employee>();

	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		String type = "jdbc:oracle:thin:@"; 
		String ip = "localhost"; 
		String port = ":1521"; 
		String sid = ":xe";
		String user = "khg";
		String pw = "khg1234";

		conn = DriverManager.getConnection(type + ip + port + sid, user, pw);

		String sql = "SELECT * FROM EMPLOYEE2 WHERE SALARY >="+input+"ORDER BY SALARY ";
		
		stmt = conn.createStatement(); 

		rs = stmt.executeQuery(sql);

	
		if (rs.next()) {
			int empId = rs.getInt("EMP_ID"); 
			String empName = rs.getString("EMP_NAME");
			String empNO = rs.getString("EMP_NO");
			String email = rs.getString("EMAIL");
			String phone = rs.getString("PHONE");
			String deptCode = rs.getString("DEPT_CODE");
			String jobCode = rs.getString("JOB_CODE");
			String salLevel = rs.getString("SAL_LEVEL");
			int salary = rs.getInt("SALARY");
			double bonus = rs.getDouble("BONUS");
			int managerId = rs.getInt("MANAGER_ID");
			Date hireDate = rs.getDate("HIRE_DATE");
			Date entDate = rs.getDate("ent_DATE");
			char entYn = rs.getString("ENT_YN").charAt(0);
			
			Employee emp = new Employee(empId, empName, empNO, email, phone, deptCode, jobCode, salLevel, salary,
					bonus, managerId, hireDate, entDate, entYn);

			empList.add(emp);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(rs != null)rs.close();
			if(stmt != null)stmt.close();
			if(conn != null)conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	return empList;
	}

	/**새로운 직원 정보 추가
	 * @param emp
	 * @return
	 */
	public int insertEmployee(Employee emp) {
		int result = 0; //결과 저장용
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String type = "jdbc:oracle:thin:@";
			String ip = "localhost"; 
			String port = ":1521"; 
			String sid = ":xe";
			String user = "khg";
			String pw = "khg1234";

			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			// - > 생성된 커넥션을 이용해 SQL을 수행하면 자동 커밋이 된다.
			// - > 자동 커밋 기능을 끄고 개발자가 직접 트랜잭션을 제어하는게 좋다.
			
			conn.setAutoCommit(false);//자동 커밋 기능을 비활성화
			
			String sql = "INSERT INTO EMPLOYEE2 VALUES(?, ?, ?, ?, ?, ?, ?, 'S5', ?, ?, 200, SYSDATE, NULL, 'N')";
											  			//? 기호 : 위치 홀더 
														//이 자리에 고정한다
			//Statement ㅣ 커넥션 생성 - sql 작성 statement 객체 생성 sql 수행후 반환
			//PreparedStatement : 커넥션 생성 >  SQL 작성(?기호 사용) > PreparedStatement 객체 생성(SQL적재) 
			// 						위치 홀더에 알맞는 값을 대입  > sql생성 후 결과 반환
			
			pstmt = conn.prepareStatement(sql);
			//위치 홀더에 알맞는 값 대입
			//pstmt.set[Type](위치 홀더 순서 , 값)
			pstmt.setInt(1,emp.getEmpId()); //입력받은 사번을 1번 ?(위치홀더)에 세팅한다.
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDeptCode());
			pstmt.setString(7, emp.getJobCode());
			pstmt.setInt(8, emp.getSalary());
			pstmt.setDouble(9, emp.getBonus());
			
//	Statement				stmt.executeQuery(sql)
//	PreparedStatemetn 		pstmt.executeQuery() : 값이 이미 있음으로 sql질의 중복 발생 시킬수 있음
			
//	********DML 사용시 executeUpdate 사용 ********
//	Statement - DML    			: stmt.executeUpdate(sql);
//	Prepared Statement - DML    : pstmt.executeUpdate();
			
		result = pstmt.executeUpdate(); //INSERT / UPDATE //DELETE 가 성공한 행의 개수를 반환.
								   //조건에 맞는 행이 없으면 0을 반환
			
		if(result > 0) conn.commit();//dml 성공 시 commit 직접 수행
		else conn.rollback(); 		 //dml 실패시 rollback 수행
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();					
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		return result;
	}

	/**사번으로 사원 정보 삭제DAO
	 * @param input
	 * @return
	 */
	public int deleteEmployee(int input) {
		int result = 0; //결과 저장용
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String type = "jdbc:oracle:thin:@";
			String ip = "localhost"; 
			String port = ":1521"; 
			String sid = ":xe";
			String user = "khg";
			String pw = "khg1234";

			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
			conn.setAutoCommit(false);

			String sql = "DELETE FROM EMPLOYEE2 WHERE EMP_ID=?";
			
  	
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,input);
			
			result = pstmt.executeUpdate(); 
			if(result > 0) conn.commit();
			else conn.rollback();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return result;
	}

	/** 사번으로 사원 정보 수정 DAO
	 * @param emp
	 * @return
	 */
	public int updateEmployee(Employee emp) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//커넥션 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","khg","khg1234");
			//자동 커밋 비활성화
			conn.setAutoCommit(false);
			//sql작성
		String sql ="UPDATE EMPLOYEE2 SET EMAIL = ?, PHONE =?,SALARY = ? WHERE EMP_ID = ? ";		
			//prepared stmt
		pstmt = conn.prepareStatement(sql);
			//위치 홀더에 알맞은 값대입
			//setString을 통해 위치 홀더에 문자열 값을 대입하면 
			//문자열 양쪽에 '' (홀따옴표)가 포함된 상태로 추가된다
		pstmt.setString(1,emp.getEmail());
		pstmt.setString(2,emp.getPhone());
		pstmt.setInt(3,emp.getSalary());
		pstmt.setInt(4,emp.getEmpId());
		
			//sql문 수행
		result = pstmt.executeUpdate();
			//트랜젝션 제어
		if(result > 0) conn.commit();
		else conn.rollback();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/** 사번으로 사원 정보 수정 DAO
	 * @param emp
	 * @return
	 */
	public int updateEmployee2(Employee emp) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//커넥션 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","khg","khg1234");
			//자동 커밋 비활성화
			conn.setAutoCommit(false);
			//sql작성
			String sql = "UPDATE EMPLOYEE2 SET "
					+ "EMAIL ='"+emp.getEmail()+"',"
					+ "PHONE = '"+emp.getPhone()+"',"
					+ "SALARY ="+emp.getPhone()+" "
					+ "WHERE EMP_ID="+emp.getEmpId();
				
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			if(result >0) conn.commit();
			else conn.rollback();
			
			//stmt 객체 생성
			
			//sql수행
			
			//트랜젝션 제어
		if(result > 0) conn.commit();
		else conn.rollback();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)stmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		return result;
	}

	/** 부서코드로 보너스율 변경.
	 * @param emp
	 * @return
	 */
	public int updateBonus(Employee emp) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//커넥션 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","khg","khg1234");
			//자동 커밋 비활성화
			conn.setAutoCommit(false);
			//sql작성
			
			String sql ="UPDATE EMPLOYEE2 SET BONUS='"+emp.getBonus()+"' WHERE DEPT_CODE = '"+emp.getDeptCode()+"'";
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
	
			if(result >0) conn.commit();
			else conn.rollback();
			
		if(result > 0) conn.commit();
		else conn.rollback();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)stmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int updateBonus2(Employee emp) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//커넥션 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","khg","khg1234");
			//자동 커밋 비활성화
			conn.setAutoCommit(false);
			//sql작성
			
			String sql ="UPDATE EMPLOYEE2 SET BONUS=?  WHERE DEPT_CODE =? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1,emp.getBonus());
			pstmt.setString(2,emp.getDeptCode());
			
			result = pstmt.executeUpdate();
	
			if(result >0) conn.commit();
			else conn.rollback();
			
		if(result > 0) conn.commit();
		else conn.rollback();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
