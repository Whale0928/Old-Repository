package edu.kh.community.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="loginFilter",
		urlPatterns = {"/board/write","/board/delete","/reply/insert","/reply/update","/reply/delete",
						"/member/myPage/*"}		)
public class LoginFilter extends HttpFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("로그인 필터 생성");
	}
	
	public void destroy() {	
		System.out.println("로그인 필터가 변경되어 파괴 -> 이후 재생성");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//클라이언트 요청 시 로그인 여부를 확인 후
		// -> 로그인 되어있지 않으면 메인페이지로 강제 이동 (Redirect)
		
		//1. ServletRequest , ServletResponse 다운 캐슽ㅇ
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//2.session 얻어오기.
		HttpSession session = req.getSession();
		
		//3. 로그인 여부 확인
		if(session.getAttribute("loginMember")!=null) { //로그인 된 경우.
			//정상 동작
			//다음 필터 호출 , 없으면 servlet으로 이동
			chain.doFilter(request, response);
		}else { //로그인 값이 없는 경우
			session.setAttribute("message", "비정상적인 접근입니다. 로그인 후 이용해주세요");
			resp.sendRedirect(req.getContextPath()); //메인페이지(최상위 페이지)
		}
		
	}
}
