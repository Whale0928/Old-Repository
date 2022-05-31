package edu.kh.community.member.model.dao;

import static edu.kh.community.common.JDBCTemplate.*;
import edu.kh.community.member.model.vo.Member;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;//xml에서 sql 읽어오기
	
	
	public MemberDAO(){
		try {			
			
			prop=new Properties();
			String filePath = MemberDAO.class.getResource("/edu/kh/community/sql/member-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("파일 로드 실패");
		}
	}
	
	/**로그인
	 * @param conn
	 * @param mem
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Connection conn, Member mem)throws Exception{
		Member loginMember = null;
		try {
			String sql = prop.getProperty("login"); 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginMember = new Member();
				
				loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
				loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				loginMember.setMemberNickName(rs.getString("MEMBER_NICK"));
				loginMember.setMemberTell(rs.getString("MEMBER_TEL"));
				loginMember.setMemberAddress(rs.getString("MEMBER_ADDR"));
				loginMember.setProfileImage(rs.getString("PROFILE_IMG"));
				loginMember.setEnrollDate(rs.getString("ENROLL_DT"));
			}
			
			//MEMBER_NO,MEMBER_EMAIL,MEMBER_NICK,MEMBER_TEL,MEMBER_ADDR,PROFILE_IMG,
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
				
		return loginMember;
	}

	/**회원 가입하는 dao
	 * @param conn
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member mem)throws Exception{
		int result=0;
		
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getMemberEmail());
			pstmt.setString(2, mem.getMemberPw());
			pstmt.setString(3, mem.getMemberNickName());
			pstmt.setString(4, mem.getMemberTell());
			pstmt.setString(5, mem.getMemberAddress());
			
			result = pstmt.executeUpdate();
			
			
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	/**정보 업데이트
	 * @param conn
	 * @param mem
	 * @return
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member mem)throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mem.getMemberNickName());
			pstmt.setString(2, mem.getMemberTell());
			pstmt.setString(3, mem.getMemberAddress());
			pstmt.setInt(4, mem.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			
		}
	
		return result;
	}

	/**비밀번호 변경
	 * @param conn
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int changePw(Connection conn, String currentPw, String newPw, int memberNo)throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("changePw");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setInt(2, memberNo);
			pstmt.setString(3, currentPw);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			//try에서 예외가 발생해서 사용중이던 jdbc 객체 자원을 무조건 반환하기 위해서
		}
		return result;
	}

	/**회원탈퇴
	 * @param conn
	 * @param mem
	 * @return
	 * @throws Exception
	 */
	public int secession(Connection conn, Member mem)throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("secession");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, mem.getMemberNo());
			pstmt.setString(2, mem.getMemberPw());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			//try에서 예외가 발생해서 사용중이던 jdbc 객체 자원을 무조건 반환하기 위해서
		}
		return result;
	}

	public int emailDupCheck(Connection conn, String memberEmail)throws Exception{
		int result=0;
		
		try {
			String sql = prop.getProperty("emailDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	public int nicknameDupCheck(Connection conn, String memberNickname)throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("nicknameDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberNickname);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				 result = rs.getInt(1);
			}
					
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		
		return result;
	}


	/**회원 정보 조회
	 * @param conn
	 * @param memberEmail
	 * @return member
	 * @throws Exception
	 */
	public Member selcetOne(Connection conn, String memberEmail)throws Exception{
		Member member = null;
		
		try {
			String sql = prop.getProperty("selectOne");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member =  new Member();
				
				member.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				member.setMemberNickName(rs.getString("MEMBER_NICK"));
				member.setMemberTell(rs.getString("MEMBER_TEL"));
				member.setMemberAddress(rs.getString("MEMBER_ADDR"));
				member.setEnrollDate(rs.getString("ENROLL_DT"));
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return member;
	}

	/**멤버 전체조회 리스트
	 * @param conn
	 * @return MemberList
	 * @throws Exception
	 */
	public List<Member> selectAll(Connection conn)throws Exception {
		List<Member> memberList = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("selectAll");

			stmt = conn.createStatement();
			
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				Member m = new Member();
				m.setMemberNo(rs.getInt(1));
				m.setMemberEmail(rs.getString(2));
				m.setMemberNickName(rs.getString(3));
				
				memberList.add(m);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return memberList;
	}

	public int updateProfileImage(Connection conn, int memberNo, String profileImage)throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("updateProfileImage");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,profileImage);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

}
