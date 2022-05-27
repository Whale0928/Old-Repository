package edu.kh.jsp.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@Webservlet("요청 주소") 어노테이션
// - 해당 클래스를 servlet 클래스로  등록(<servlet>태그)하고.
// - 어떤 요청 주소를 처리할지 지정(<servlet-mapping>태그)
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청시 전달된 input태그의 값(==Parameter) 얻어오기.
		//Parameter는 모두 String 타입이다
		String inputId = req.getParameter("inputId");
		String inputPw = req.getParameter("inputPw");
		
		//피라미터 확인
		System.out.println("ID:"+inputId);
		System.out.println("PW:"+inputPw);
		
		String result = null;
		//간이 로그인 확인
		if(inputId.equals("user01")&&inputPw.equals("pass01!")){
			result = inputId+"님이 로그인 하셨습니다.";
		}else {
			result = "아이디 혹은 비밀번호가 일치하지 않습니다.";
		}
		 
		//out.print("<html>") ; - > jsp로 교체
		//JSP란? 
		// - servlet에 코드를 쓰는게 불편하기 때문에
		// 반대로 HTML코드를 Java 코드를 쓸 수 있게 하는 문서.
		
		// JSP 생성 폴더 위치.
		// -> webapp 폴더. 
		
		//-------------------------------------------------------------------------
		
		//     << JSP로 응답하기 >>
		
		// Dispatcher : 정보를 제공하는 자 == 발송자   
		
		// 위임       : 넘겨주나
		
		// forward    : 전송하다
		
		//* 응답화면을 만드는 Servlet의 일을
		//  좀더 효율적으로 처리할 수 있는 JSP에게 넘겨준다 
		
		//RequestDispatcher : Servlet -> JSP로 
		//  		HttpServletRequest 객체 / HttpServletResponse 객체를 발송(위임)하는 역할의 객체.  
		
		
		//req.getRequestDispatcher("JSP 경로");
		// HttpServletRequest 객체가 생성될 때
		// 내부에 자동으로 요청 발송자(RequestDispatcher)가 같이 생성됨.
		
		// --"JSP경로" : 전달할 JSP 경로의 작성
		
		//*** JSP 경로 작성 규칙 ***
		// -webapp 폴더를 '기준'으로 해서 JSP파일까지의 '모든' 경로를 작성
	
		
		RequestDispatcher dispatcher =  req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp");
		//요청발송자													목적지
	
		
		//JSP에게 전달될 예정인 req 객체에 result 변수 값을 담아 같이 전달
		
		//req.setAttribute (String Key , Object value);
		// - key 는 String(문자열)
		// - value는 모든 객체(타입 상관 없음)
		
		// - Attribute : 속성(데이터,값)
		
		req.setAttribute("res", result);
		
		//요청 발송자를 이용하여 req , resp객체를 전송(forward)함
		dispatcher.forward(req, resp);
		//        		현재 서블릿이 가지고있는 객체를 전달
		
		
		
		
		
			}
}
