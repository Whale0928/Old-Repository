package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.model.service.MemberService;
import edu.kh.jsp.model.vo.Member;

@WebServlet("/member/selectName")
public class SelectName extends HttpServlet{
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MemberService service = new MemberService();
			String name = req.getParameter("userName");
			
			List<Member> memberList = service.selectName(name);

			String path = "/WEB-INF/views/selectName.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			req.setAttribute("list", memberList);
			
			dispatcher.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
