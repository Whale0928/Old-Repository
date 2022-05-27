package edu.kh.community.member.model.service;

import edu.kh.community.member.model.dao.MemberDAO;
import edu.kh.community.member.model.vo.Member;
import static edu.kh.community.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

public class MemberService {
	
	MemberDAO dao = new MemberDAO(); 
	/**멤버 로그인
	 * @param mem
	 * @return memberLogin
	 * @throws Exception
	 */
	public Member loginMember(Member mem)throws Exception{
		//connection 얻어오기
		Connection conn = getConnection();
		
		
		Member loginMember = dao.loginMember(conn,mem);
		
	
		//connection 반환
		close(conn);
		
		return loginMember;
	}
	
	/**회원가입 service
	 * @param mem 
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member mem)throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn,mem);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/**멤버 정보 수정
	 * @param mem
	 * @return
	 * @throws Exception
	 */
	public int updateMember(Member mem)throws Exception{
		Connection conn = getConnection();
		int result = dao.updateMember(conn,mem);
		
		if(result>0)commit(conn);
		else 		rollback(conn);
		
		close(conn);
		
		return result;
	}

	/**비밀번호 변경
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int changePw(String currentPw, String newPw, int memberNo)throws Exception {
		Connection conn = getConnection();
		int result = dao.changePw(conn,currentPw,newPw,memberNo);
		
		if(result > 0)commit(conn);
		else rollback(conn);
		
		close(conn);
		
		
		return result;
	}

	/**회원 탈퇴
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int secession(Member mem)throws Exception{
		Connection conn = getConnection();
		int result = dao.secession(conn,mem);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/**이메일 중복 검사 Service
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int emailDupCheck(String memberEmail)throws Exception{
		Connection conn = getConnection(); //DBCP에서 만들어둔 커넥션 얻어오기 데이터베이스 커넥션 풀
		
		int result = dao.emailDupCheck(conn,memberEmail);
		
		close(conn);
		
		return result;
	}

	/**닉네임 중복확인을 위한 service
	 * @param memberNickname
	 * @return result
	 * @throws Exception
	 */
	public int nicknameDupCheck(String memberNickname)throws Exception {
		Connection conn = getConnection();
		
		int result = dao.nicknameDupCheck(conn,memberNickname);
		
		close(conn);
		
		return result;
	}

	/** 회원 정보 조회
	 * @param memberEmail
	 * @return
	 * @throws Exception
	 */
	public Member selectOne(String memberEmail)throws Exception {

		Connection conn = getConnection();
		
		Member member = dao.selcetOne(conn,memberEmail);
		
		close(conn);
		
		return member;
	}

	public List<Member> selectAll()throws Exception{
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectAll(conn);
		
		close(conn);
		
		return memberList;
	}

}
