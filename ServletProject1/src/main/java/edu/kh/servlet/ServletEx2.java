package edu.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet 클래스를 만들 때는
//'반드시' HttpServlet 을 상속받아야 한다.
public class ServletEx2 extends HttpServlet{
	
	
	//GET방식 요청을 처리(do)하는 메소드 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터 (Parameter) == 요청시 전달된 input태그의 값

		String orderer = req.getParameter("orderer"); 
		//->getParameter() 는 전달 된 input 태그의 name이 하나일 때만.
		//-->같은 name이 여러 개면 String[]배열 으로 반환하는 
		//   	getParameterValues()를 사용
		
		String[] coffee = req.getParameterValues("coffee");
		//체크박스에 체크된 모든 메뉴들이 배열에 담김
		//만약 체크가 하나도 안되면 배열에 아무것도 없음
		
		if(coffee!=null) {//체크된 메뉴가 잇는지 검사
			for(String temp:coffee){
				System.out.println(temp);
			}
		}else {
			System.out.println("주문 내역이 없습니다.");
		}
		System.out.println("주문자 : "+orderer);
		
		
		//HttpServletRequest req, : 클라이언트 정보 + 전달된 값 
		//HttpServletResponse resp : 서버가 클라이언트에게 응답할 방법을 제공.
		
		
		// writer : 서버가 클라이언트에게 ~ 쓰다 
		//resp.getWriter(); : 서버가 클라이언트에게 응답할 수 있는 
		// 						출력 전용 스트림을 얻어온다.
		resp.setContentType("text/html; charset=UTF-8"); //어떤 형식 / 인코딩인이 명시.
		//스트림을 통해서 그냥 문자열을 내보내면 정상 출력되지 않는 문제 발생.
		// 왜 ? 전달되는 응답 데이터가 어떤 형식인지 ,문자 인코딩은 어떤건지를 지정해주지 않아서. 
		PrintWriter out = resp.getWriter();
		//out.print("응답 되나..?");
		
		//********************************************************************************************
		/*Dynamic Web Project (동적 웹 프로젝트) 
		 * 
		 * - 요청에 따라서 응답 되는 '화면'을 실시간을 만들어 내서 
		 * 		클라이언트에게 응답하는 프로젝트
		 * */
		//********************************************************************************************
		
		//HTML 코드를 자바(Servlet)에서 작성 해
		//클라이언트와 연결된 응답 출력용 스트림(여기서는 out) 을 이용해 출력.
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		
		out.println("<head>");
		out.println("<title>"+orderer+"님의 주문 목록</title>");
		out.println("</head>");

		out.println("<body>");

		out.println("<p>"+orderer+"님의 주문 목록</p>");
		out.println("<ul>");		
			if(coffee!=null) {
				
				for(String c:coffee) {
					out.println("<li>"+c+"</li>");				
				}
			}else {
				out.println("<li>주문 내역이 없습니다</li>");
			}
		out.println("</ul>");		
		out.println("</body>");
		
		out.println("</html>");
		
		
	}
}
