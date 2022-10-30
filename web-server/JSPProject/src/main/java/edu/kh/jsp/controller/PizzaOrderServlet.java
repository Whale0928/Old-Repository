package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pizzaOrder")
public class PizzaOrderServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//parameter 얻어오기
		String pizza = req.getParameter("pizza");//주문한 피자 종류
		
		String size = req.getParameter("size"); //라디오 버튼은 값이 한개만 들어올 수 있다.
		
		
		//파라미터는 모두 String이다
		// -> String을 정수로 변경하고 싶을 경우
		//  -> Integer.parseInt("문자열")을 사용하여 변경(파싱)
		int amount = Integer.parseInt(  req.getParameter("amount"));
			
		//피자 = 1판에 만원
		//사이즈 L인 경우 2천원 추가
		//수량(1~10) 
		
		int temp = 10000; //사이즈에 따른 추가 금액
		if(size.equals("L")) {
			temp += 2000; //L 사이즈면 2000원
		}
		
		int result = temp*amount;
		
		//응답 화면을 작성하는 것을 JSP로 위임
		
		//JSP경로는 webapp폴더를 기준으로 작성
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/orderResult.jsp");

		req.setAttribute("res", result);
		
		dispatcher.forward(req, resp);
		
		
	}
}
