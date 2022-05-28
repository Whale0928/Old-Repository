package edu.kh.jdbc.member.model.service;

//import Static 구문  : Static 메서드를 import하여 
//          클래스명 . static 메서드() 형태에서 클래스명을 생략 할 수있게 하는 구문

import static edu.kh.jdbc.common.JDBCTemplate.getConnection;
import static edu.kh.jdbc.common.JDBCTemplate.close;
import static edu.kh.jdbc.common.JDBCTemplate.commit;
import static edu.kh.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.vo.Member;

//Service : 데이터 가공(요청에 맞는 데이터를 만든는 것)
//         + 트랜잭션 제어 처리
//         -> 하나의 Service 메서드에서 n개의 DAO 메서드를 호출 할 수 있음
//			-> n개의 DAO에서 수행된 SQL을 한번에 commit/rollback


//Service에서 트랜잭션을 처리하기 위해서는 Connection 객체가 필요함
//   == Service에서 Connection 객체를 생성/ 반환 해야 한다.

	

/**
 * @author rlagu
 *
 */
public class MemberService {
	
	//회원 관련 SQL 수행 및 결과를 반환할 DAO 객체 생성 및 참조
	private MemberDAO dao = new MemberDAO();
	
	
	/**아이디 중복검사를 하기 위한 메서드
	 * @param memberId
	 * @return result 
	 * @throws Exception
	 * 	 */
	public int duplicateCheck(String memberId) throws Exception{
		//1. Connection 객체 생성
		// JDBCTemplate에서 작성된 getConnection () 메서드를 이용해 생성 후 얻어옴
		Connection conn = getConnection();
		
		//2. DAO 메서드 호출(SELECT) 결과 반환
		int result = dao.duplicateCheck(conn,memberId);
							//SQL을 수행 하려면 Connection 필요하니 같이 전달
		//3. SELECT는 별도의 트랜잭션 제어 필요없음
		// 사용한 Connection만 객체 반환
		close(conn);
		
		//4.중복 검사 결과 View로 반환
		return result;
		
	}


	/**회원 가입을 하기 위한 메서드
	 * @param signUpMember
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member signUpMember)throws Exception { //모든 예외는 View에서 모아서 처리
		//1) Connection 객체 생성
		Connection conn = getConnection();
		
		//2) 회원 가입 DAO 메서드를 호출하고 결과 반환
		int result = dao.signUp(conn,signUpMember);
		
		//3)DAO 수행 결과에 따라 트랜잭션 처리
		if(result > 0)commit(conn);
		else rollback(conn);
	
		//4)사용한 Connection 반환
		close(conn);
		//5)수행 결과 View 반환
		return result;
	}


	/**로그인 service
	 * @param mem
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Member mem)throws Exception {
		
		//1) Connection 생성
		Connection conn = getConnection();
		
		//2) DAO 메서드 호출	
		Member loginMember = dao.login(conn,mem);
		//select 라 트랜잭션 제어 처리 X
		
		//3) 수행후 Connection 반환
		close(conn);
		
		//4) DAO 조회 결과 return
		return loginMember;
	}


	/** 가입된 회원 목록 조회
	 * @return List<Member> memberList
	 * @throws Exception
	 */
	public List<Member> selectAll()throws Exception {
		
		//1. Connection 생성
		Connection conn = getConnection();
				
		
		//2. DAO 메서드 호출후 결과 반환
		List<Member> memberList = dao.selectAll(conn);
		
		//3. conn 반환
		close(conn);
		
		//4. 조회한 값 호출한 곳으로 반환
		return memberList;
	}


	/**내 정보 수정을 위한 메서드
	 * @param updateMember
	 * @return result
	 * @throws Exception
	 */
	public int updateMyInfo(Member updateMember)throws Exception {
		int result = 0;
		//1. 커넥션 생성
		Connection conn = getConnection();
		//2. dao에서 호출후 결과 반환
		result = dao.updateMyInfo(conn,updateMember);
		//3.커밋
		if(result >0)commit(conn);
		else rollback(conn);
		//4. 커넥션 반환
		close(conn);
		//5. 결과반환
		return result;
	}


	/**비밀번호 변경을 위한 메서드
	 * @param loginMember
	 * @param newPw
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(Member loginMember, String newPw)throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updatePw(conn,loginMember,newPw);
		
		if(result > 0)commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	/**회원 탈퇴를 위한 메서드
	 * @param loginMember
	 * @param cho
	 * @return result
	 * @throws Exception
	 */
	public int secession(Member loginMember)throws Exception{
		Connection conn =getConnection();
		
		int result = dao.secession(conn,loginMember);
		
		if(result > 0)commit(conn);
		else rollback(conn);
		
		return result;
	}


}
