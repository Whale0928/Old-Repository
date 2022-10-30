package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet : 웹 서비스(요청 , 응답)을 위한 자바 클래스.

@WebServlet("/signUp")
public class signUp extends HttpServlet{
	
	
		//Post 방식 요청 처리 
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			
			// *** Post 방식의 한글 깨짐 문제 ***
			
			//데이터 전달 방식 차이점
			// -  GET : 주소(URI)를 통해서 데이터를 전달 
			//			이 때, 문자 인코딩은 제출된 HTML 파일의 문자 인코딩(charset)을 따른다
			// - POST :HTTP Body를 통해서 데이터를 전달
			//			이 때, 문자 인코딩은 서버의 기본 문자 인코딩을 따른다
			
			req.setCharacterEncoding("UTF-8");
			String memberId = req.getParameter("memberId");
			
			String message = memberId + "님의 가입을 환영합니다!";
			
			//Servlet -> Request Dispatcher -> forward -> ->  JSP  
			//				요청 발송자		 전송(req,resp)
																	
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/signUpResult.jsp");
																	// JSP 경로 작성 시 webapp 폴더를 기준으로 작성한다
			
			//Http Servlet Request 객체에 message 변수를 추가 
			req.setAttribute("msg", message);
			
			dispatcher.forward(req, resp);
			
			
		}
}
