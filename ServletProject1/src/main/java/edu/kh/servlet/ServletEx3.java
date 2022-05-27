package edu.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx3 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF8");
		PrintWriter out = resp.getWriter();

		String id = req.getParameter("inputId");
		String pw = req.getParameter("inputPw");
		/* String pw2 = req.getParameter("inputPw2"); */
		String name = req.getParameter("inputNM");
		String email = req.getParameter("Email");
		String[] color = req.getParameterValues("color");

		if (pw.equals(req.getParameter("inputPw2"))) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");

			out.println("<head>");
			out.println("<title>회원 정보 제출 결과</title>");
			out.println("</head>");

			out.println("<body>");

			out.println("<ul>");

			out.println("<li>아이디 : " + id + "</li>");
			out.println("<li>이름 : " + name + "</li>");
			out.println("<li>이메일 : " + email + "</li>");
			out.print("<li>좋아하는 색:");
			if (color != null) {
				for (String c : color) {
					out.println(c);
				}
			} else {
				out.print("없다");
			}
			out.print("</li>");
			out.println("</ul>");
			out.println("</body>");
			out.println("</html>");
		} else {
			out.print("<H1>비밀번호가 일치하지 않습니다</H1>");
		}
	}
}
