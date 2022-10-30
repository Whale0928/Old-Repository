package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/checkNumber")
public class CheckNumberServlet extends HttpServlet{			
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			//  data:{"cNumber":cNumber.value,"inputEmail":memberEmail.value},
			String  cNumber = req.getParameter("cNumber");
			String  inputEmail = req.getParameter("inputEmail");
			
			int result = new MemberService().checkNumber(inputEmail,cNumber);
			
			resp.getWriter().print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		      // ajax error 속성 활용을 위한 500에러를 응답으로 전달.
	         resp.setStatus(500);
	         resp.getWriter().print(e.getMessage());
		}
	}
}
