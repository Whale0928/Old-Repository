package edu.kh.community.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/myPage/changePw")
public class MyPageChangePwServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/member/myPage-changePw.jsp";
		
		/*
		 * RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp); */
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	
	//비밀번호 변경
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String currentPw = req.getParameter("currentPw");
		String newPw = req.getParameter("newPw");
			
		
		/*아씨 어디가 오타인지를 모르겟네.
		 * HttpSession session = req.getSession(); 
		 * Member loginMember = (Member)(session.getAttribute("loginMember")); 
		 * int memberNo = loginMember.getMemberNo();
  		   HttpSession session = req.getSession();
		   Member loginMember = (Member) (session.getAttribute("loginMember"));
		   int memberNo = loginMember.getMemberNo();
		 */
		
		// 세션에서 로그인한 회원 정보 얻어오기
		//MEMBER_NO과 동기화를 위해 세션 로그인맴버 얻어오기
				HttpSession session = req.getSession();
				Member loginMember = (Member) (session.getAttribute("loginMember"));
				int memberNo = loginMember.getMemberNo();
				
		//**Service로 전달 값이 많은대 VO하나만 가지고 해결할 수 없는 경우**
		// 1.  매개변수로 하나하나 따로 전달함 
		//	-> 최대 4개를 넘지 않는 것으로 암묵적 함의
		
		// 2.  Map을 이용
		// 3.  목적에 맞는 VO 새로 만들기(1회성 목적으로 사용할려고 만들면 뚝뺴기 깨짐)
		
		try {
			MemberService service = new MemberService();
			
			int result = service.changePw(currentPw,newPw,memberNo);
			
			String path = null;//리다이렉트 주소.
			if(result>0){
				//Session Scope -message- 키를 이용해 비밀번호 성공 여부 Value를 세팅 
				session.setAttribute("message", "비밀번호 변경 성공");
				//path 성공 했을 경우 "내 정보 페이지 주소"				
				path = req.getContextPath()+"/member/myPage/info";
			}else {
				session.setAttribute("message", "현재 비밀번호가 일치하지 않습니다");
				path="changePw";
			}
			resp.sendRedirect(path);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
