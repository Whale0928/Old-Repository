package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.model.vo.Member;

import static edu.kh.jdbc.common.JDBCTemplate.close;
//close으로 오버로딩된 모든 메서드가 호출

//DATA Access Object : DAO
//데이터가 저장할 수 있는 DB 파일등에 접근하는 객체 == SQL을 수행전달 반환받을수 있다


//JAVA에서 DB에  접근 하고 결과를 반환받기 위해 프로그래밍 API를 제공한다 == JDBC
//	(Connection , Statement , PreparedStatement , ResultSet)
public class MemberDAO {
 
	//필드
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	//MAP K/V 모두 String, 외부 xml파일 입출력에 특화
	
	//MemberDAO기본 생성자
	public MemberDAO() {
		//MemberDAO 객체 생성 시
		//member-sql.xml파일의 내용을 읽어와
		//Properties 객체 생성
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**아이디의 중복 검사 DAO 메서드
	 * @param conn
	 * @param memberId
	 * @return result 
	 * @throws Exception
	 */
	public int duplicateCheck(Connection conn,String memberId)throws Exception{
		//throws : 호출한 메소드로 예외를 던짐
		//1) 결과 저장용 변수 선언
		int result = 0;

		try {
			//2) sql 작성
			String sql = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID = ? AND SECESSION_FL = 'N'";
			
			//3) Prepared Statement 객체 생성(connection , sql 필요) 
			pstmt = conn.prepareStatement(sql);
			
			//4) 위치홀더에 알맞은 값 세팅
			pstmt.setString(1,memberId);
			
			//5) sql 수행후 결과를 반환
			rs = pstmt.executeQuery(); //SELECT 수행 결과 RESULT SET을 반환 받음
			
			//6)조화 결과를 한행씩 접근하여 원하는 컬럼 값 얻어오김
			// -> 아이디 중복 검사 select 결과는 0 또는 1 1행 결과라 무조건 나옴 == if 사용 
			if(rs.next()) {
				result = rs.getInt(1);// 1은 컬럼 순서
			}
		}finally { //try - fanally (catch는 throws에 의해서 생략)
			//7) 사용한 JDBC 자원 반환
			close(rs);
			close(pstmt);
		}

		//8 sql 수행 결과 반환
		return result;
	}
	
	/**회원가입 DAO
	 * @param conn
	 * @param signUpMember
	 * @return	result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member signUpMember)throws Exception {
		int result = 0;
			
		try {
			//1) SQL 작성 (Properties에 저장된 sql 얻어오기)
			String sql = prop.getProperty("signUp");
			
			//2) Prepared Statement 객체 생성(Connection , sql 필요)
			pstmt = conn.prepareStatement(sql);
			
			//3) 위치 홀더 '?' 에 알맞은 값 세팅
			pstmt.setString(1, signUpMember.getMemberId());
			pstmt.setString(2, signUpMember.getMemberPw());
			pstmt.setString(3, signUpMember.getMemberName());
			pstmt.setString(4, signUpMember.getMemberGender()+"");
			//getMemberGender의 자료형은 char
			//setString을 String 자료형 MissMatch
			
			//char 자료형 + "" (빈문자열)
			//1 + 1 = 2
			//1 + "1" = "11"
			
			//4) sql 수행후 결과 반환 받기
			result = pstmt.executeUpdate();//수행된 행의 개수 반환.
			
			
		}finally {
			//5) 사용한 JDBC 자원 반환
			close(pstmt);
		}
	
		
		
		return result;
	}

	/** 로그인 메서드
	 * @param conn
	 * @param mem
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, Member 
			mem)throws Exception{
		Member loginMember = null;
		//왜 null으로 선언하나? 로그인 결과가 오류일 경우도 상정해야 하기 때문에
		
		try {
			//1) sql 작성
			String sql = prop.getProperty("login");
			
			//2) pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//3) 위치 홀더 매칭
			pstmt.setString(1, mem.getMemberId());
			pstmt.setString(2, mem.getMemberPw());
			
			//sql 수행 후 결과 반환 (Result Set)받기 (rs변수에)
			rs = pstmt.executeQuery();
						
			//5) if or while을 이용해 rs에 한행씩 접근하여 원하는 값 얻어오기
			if(rs.next()) {
//				int memberNo = rs.getInt("MEMBER_NO");
//				String memberId = rs.getString("MEMBER_ID");
//				String memberName =rs.getString("MEMBER_NM");
//				char memberGender = rs.getString("MEMBER_GENDER").charAt(0);
//				Date enrollDate = rs.getDate("ENROLL_DATE");
				
				//6)원하는 컬럼 값을 이용해 member객체를 생성하여 loginMember 변수에 저장
						
				loginMember = new Member();
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberId(rs.getString("MEMBER_ID"));
				loginMember.setMemberName(rs.getString("MEMBER_NM"));
				loginMember.setMemberGender(rs.getString("MEMBER_GENDER").charAt(0));
				loginMember.setEnrollDate(rs.getDate("ENROLL_DATE"));
			}
		}finally {
			//7) Connection 을 제외한 객체 자원 반환
			close(rs);
			close(pstmt);
		}
		//dao 수행 결과 반환
		return loginMember;
	}

	/**전체 회원 정보 조회를 위한 메서드
	 * @param conn
	 * @return memberList
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn)throws Exception{
		//결과 저장용 변수
		List<Member>memberList = new ArrayList<Member>();
		
		try {
			//1. sql 작성
			String sql = prop.getProperty("selectAll");
			//2. stmt객체 생성
			stmt = conn.createStatement();
			
			//3.sql(select) 수행 후 결과 반환(Result Set) 받기
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				//4.ResultSet 한행씩 접근(rs.next()) 조회된 컬럼값을 얻어와
				Member temp = new Member();
				temp.setMemberId(rs.getString("MEMBER_ID"));
				temp.setMemberName(rs.getString("MEMBER_NM"));
				temp.setEnrollDate(rs.getDate("ENROLL_DATE"));
			
				
				//5. 컬럼값이 저장된 Member 객체를 List에 추가
				memberList.add(temp);
			}
			
		}finally {
			//6. 사용한 JDBC 자원 반환 (Conn 제외) 
			close(rs);
			close(stmt);
		
		}
		
		//7. 결과 반환
		return memberList;
	}

	/**내 정보 업데이트를 위한 메서드
	 * @param updateMember
	 * @return
	 * @throws Exception
	 */
	public int updateMyInfo(Connection conn,Member updateMember)throws Exception{
		int result = 0;

		try {			
			String sql = prop.getProperty("updateMyInfo");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updateMember.getMemberName());
			pstmt.setString(2, updateMember.getMemberGender()+"");
			pstmt.setInt(3, updateMember.getMemberNo());
				
			result = pstmt.executeUpdate();
		
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**비밀번호 변경을 위한 메서드
	 * @param loginMember
	 * @param newPw
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(Connection conn,Member loginMember, String newPw)throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw);
			pstmt.setInt(2, loginMember.getMemberNo());
			pstmt.setString(3, loginMember.getMemberPw());
			
			result = pstmt.executeUpdate();
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	/**회원 탈퇴를 위한 메서드
	 * @param conn
	 * @param loginMember
	 * @param cho
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn,Member loginMember)throws Exception {
		int result =0;
		
		try {
			String sql = prop.getProperty("secession");
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,loginMember.getMemberNo());
			pstmt.setString(2,loginMember.getMemberPw());
			
			result = pstmt.executeUpdate();
							
		}finally{
			close(pstmt);
		}
			
		return result;
	}

	
	
}
