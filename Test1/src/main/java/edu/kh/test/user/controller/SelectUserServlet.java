package edu.kh.test.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.test.user.model.service.UserService;
import edu.kh.test.user.model.vo.User;

@WebServlet("/selectUser")
public class SelectUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int userNo = Integer.parseInt(req.getParameter("userNo"));
	
		try {
			User user = new UserService().selectUser(userNo); 
			String path ="";
			
			if(user != null) {
				req.setAttribute("user", user);
				path = "/WEB-INF/views/searchSuccess.jsp";  //성공시  
			}else {				
				path = "/WEB-INF/views/searchFail.jsp";  //실패시
			}
		
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
