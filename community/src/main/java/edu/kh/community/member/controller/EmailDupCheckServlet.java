package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/emailDupCheck")
public class EmailDupCheckServlet extends HttpServlet{
	
	//이메일 중복 검사
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//비동기 통신으로 전달된 파라미터 값 얻어오기.
		String memberEmail = req.getParameter("memberEmail");  
	
		
		try{
			//이메일 중복 검사. 서비스 호출 후 결과 반환 받기.
			MemberService service = new MemberService();
			
			int result = service.emailDupCheck(memberEmail);
			
			
			//보통 동기시 코드 작성시
			//forward , redirect 사용해 새로운 페이지를 동작함
			
			//*** 비동기 통신 시 응답은 화면이 아닌 데이터(String , XML , Json , int등등) 
			//응답용 스트림을 이용해 단순 데이터 전달만하면 된다.
			
			resp.getWriter().print(result);
			//응답용 스트림을 이용해 result를 출력.
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
	}
}
