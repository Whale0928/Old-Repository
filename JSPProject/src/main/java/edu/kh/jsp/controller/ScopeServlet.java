package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EL/scope")
public class ScopeServlet extends HttpServlet{
	
	
	//a 태그 요청은 모두 Get 방식
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*Servlet / JSP 내장 객체와범위
		 * (공통) 특정 값을 
		 * 
		 * page: 현재 페이지(Servlet / JSP)에서만 사용 가능.
		 * 
		 * request : 현재 Servlet + 요청 위임한 JSP에서만 사용 가능. (2개)
		 * 
		 * session : 현재 켜져있는 브라우저가 종료되기 전까지 어디서든 사용 가능.
		 * 			(ex.로그인.)
		 * 
		 * application :배포한 웹 애플리케이션이 '종료'되기 전가지 어디서든 사용 가능.
		 * 			(서버가 꺼질 때 까지)
		 * */
		//1.)page(스킵)
		
		//2.)request scope
		req.setAttribute("message", "Request scope에 저장된 메세지");
			
		//3.) Session scope
		// 1) HttpSession 내장 객체 얻어오기
		HttpSession session = req.getSession();
		// 2) session 범위로 값 세팅(request와 방법이 동일)
		session.setAttribute("sessionValue", "999");

		//범위가 다른데 key값이 같으면?
		session.setAttribute("message", "Session scope에 저장된 메세지");
		
		
		//4.) application scope
		// 1)ServletContext  application 내장 객체 얻어오기
		ServletContext application = req.getServletContext();
		// 2)application 범위로 값 세팅(Request와 방법 동일)
		application.setAttribute("appValue","Application Scope 범위로 지정된 메세지" );
		
		//범위가 다른데 key값이 같으면?
		application.setAttribute("message", "appliction scope에 저장된 메세지");
				
		
		
		//요청 위힘
		String path = "/WEB-INF/views/el/scopeResult.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		
		dispatcher.forward(req, resp);
		
	}
}
