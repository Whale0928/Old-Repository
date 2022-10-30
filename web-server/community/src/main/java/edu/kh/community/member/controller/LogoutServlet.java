package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.vo.Member;


@WebServlet("/member/logout")
public class LogoutServlet extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			//로그아웃
			//session Scope에 세팅된 회원 정보를 없애는 거.

			
			//Session 얻어오기
			HttpSession session = req.getSession();
			
			// 1) Session 에서 회원 정보만 없애기
			//session.removeAttribute("loginMember"); //session에 있는 loginMember만 제거
			
			// 2) Session 전체를 없애고 새로운 Session 만들기. (주로 사용됨 . 로그아웃만 하는게 아니라 남긴 흔적도 지워야하기 때문에.)
			session.invalidate();//invalidate : 무효화  == session 무효화 
														//현재 세션을 없어지면 새로운 새션이 자동으로 재생성
			
			
			//메인페이지로 돌아가기 	resp.sendRedirect(req.getContextPath());
			//메인페이지는 최상위 주소(/community)로 요청햇을 때 화면
			//req.getContextPath() 는 최상위 주소를 의미한다. 
			resp.sendRedirect(req.getContextPath()); 
		}
}
