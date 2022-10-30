package edu.kh.community.member.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/signUp")
public class SignUpServlet extends HttpServlet {
	
	MemberService service = new MemberService(); 
	
	//Get 방식 요청시 JSP로 바로 응답할수 있도록 요청
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/signUp.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memberEmail = req.getParameter("memberEmail");
		String memberPw = req.getParameter("memberPw");
		String memberNickname = req.getParameter("memberNickname");
		String memberTell = req.getParameter("memberTel");
		
		//주소는 3개의 input으로 이루어져 있음으로 배열로 전달받음.
		//DB컬럼은 한개라서 배열을 하나의 문자열로 합칠 예정
		String[] address = req.getParameterValues("memberAddress");
		
		String memberAddress = null;
		//주소가 입력되지 않으면 null이 아니라 빈칸3개
		if(!address[0].equals("")) {//우편번호가 빈칸 이라면 == 주소 미작성으로 판단.  / !을 붙여 주소를 붙이는 동작을 함
			memberAddress = String.join(",,",address);
			
			// String.join("구분자",배열)
			//배열 요소를 하나의 문자열로 반환 
			//요소 사이에 "구분자"추가
		}
		
		//파라미터를 하나의 Member객체에 저장
		
		Member member = new Member();
		
		member.setMemberEmail(memberEmail);
		member.setMemberPw(memberPw);
		member.setMemberNickName(memberNickname);
		member.setMemberTell(memberTell);
		member.setMemberAddress(memberAddress);
		
		
		try {
			int result = service.signUp(member);
			
			//서비스 결과에 따라서 meesage를 다르게 하며 메인 페이지 재 요청
			HttpSession session = req.getSession();
			
			if(result>0) {
				session.setAttribute("message", "회원 가입 성공");
			}else {
				session.setAttribute("message", "회원 가입 실패");				
			}
			
			resp.sendRedirect( req.getContextPath());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	
	}
}
