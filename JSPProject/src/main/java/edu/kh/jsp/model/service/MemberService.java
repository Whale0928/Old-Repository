package edu.kh.jsp.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jsp.model.dao.MemberDAO;
import edu.kh.jsp.model.vo.Member;

import static edu.kh.jsp.common.JDBCTemplate.*;

public class MemberService {

	private MemberDAO dao = new MemberDAO();
		
	/**전체 회원 조회
	 * @return list
	 * @throws Exception
	 */
	public List<Member> selectAll()throws Exception{
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectAll(conn);  
		
		close(conn);
		
		return memberList;
	}

	public List<Member> selectName(String name)throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectName(conn,name);
		
		close(conn);
		
		return memberList;
	}

}
