package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.common.JDBCTemplate;
import edu.kh.jsp.model.service.MemberService;
import edu.kh.jsp.model.vo.Member;

@WebServlet("/member/selectAll")
public class SelectAllServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//JDBC
			//View <-> Service <-> dao <-> DB서버
			//발생하는 예외를 호출한 쪽으로 던졌었다.
			
			//View == HTML , JSP
			//어떤 요청이오면 어떤 서비스를 호출할지 제어하는 것이 Controller (Servlet)
		
			try {
			
				MemberService service = new MemberService();
				
				List<Member> memberList = service.selectAll();
			
				
				String path = "/WEB-INF/views/selectAll.jsp";
				
				RequestDispatcher dispatcher = req.getRequestDispatcher(path);
				req.setAttribute("list", memberList);
				req.setAttribute("test", "test!!");
				
				dispatcher.forward(req, resp);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		}
}
