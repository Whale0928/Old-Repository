package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/myPage/secession")
public class MyPageSecessionServlet extends HttpServlet {
		
	
	//회원탈퇴 페이지로 전환하기 위한 doget
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/myPage-secession.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String memberPw = req.getParameter("memberPw");
		
		HttpSession session = req.getSession();
		Member loginMember = (Member) (session.getAttribute("loginMember"));
		int memberNo = loginMember.getMemberNo();
		
		try {
			MemberService service = new MemberService(); 
			Member mem = new Member();
			mem.setMemberPw(memberPw);
			mem.setMemberNo(memberNo);
			
int result = service.secession(mem);
String path =null;
if(result>0) {
	//1 . 로그아웃 서블릿으로 보내 로그아웃 시키는 방법
	//2 . 이 IF 내부에서 SESSION 무효화 후 진행
	session.invalidate();

	//session을 무효화 해서 메세지를 무효화 하게 되버려 세션에 set을 무효화 한 다음 
	// 새로 생성된 세션을 얻어온다.
	session=req.getSession();
	
	session.setAttribute("message", "탈퇴 되었습니다.");
	path=req.getContextPath();
	
	Cookie c = new Cookie("saveId","");//쿠키생성
	c.setMaxAge(0); //쿠키 수명
	c.setPath(req.getContextPath());//쿠키가 적용될 주소
	resp.addCookie(c); //쿠키 전달.

}else {
	session.setAttribute("message", "비밀번호가 일치하지 않습니다.");		
	path="secession";
}


resp.sendRedirect(path);
			
		}catch(Exception e) {
			
		}
	}
	
	
	
}
