package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.jsp.model.vo.Person;

@WebServlet("/EL/result")
public class ELResultServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//문자 인코딩 세팅 (POST방식의 문제점)
		req.setCharacterEncoding("UTF-8");
		
		//임의의 값
		String menu = "철판치즈볶음밥";
		
		//파라미터 얻어오기.
		String inputName = req.getParameter("inputName");
		int inputAge = Integer.parseInt(req.getParameter("inputAge"));
		String inputAdress = req.getParameter("inputAddress");
		
		//Person 객체에 파라미터를 변경한 값을 대입.
		Person p = new Person();
		p.setName(inputName+"님");
		p.setAge(inputAge+100);
		p.setAddress("Republe of Korea - "+inputAdress);
		
		//1) 요청 발송자
		String path="/WEB-INF/views/el/result.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		//2) 요청 위임할때 추가하고 싶은 값 세팅
		req.setAttribute("menu", menu);
		req.setAttribute("person", p);
		//3) 요청 위임.
		dispatcher.forward(req, resp);
		
		
	}
}
