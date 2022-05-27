package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//post 방식 요청 시 문자 인코딩이 서버 기본값으로 지정된다
		// - > 한글 깨짐. - >문자 인코딩 변경 필요
		
		// 모든 doPost( ) 메서드에 변경 코드를 작성해야 한다( 매우 귀찮다)
		//	->모든 요청 -> 전달방식을 가리지 않고 req,resp의 문자 인코딩을 UTF-8로 고정
		// 	 - > 필터(Filter) 
		
		//전달된 파라미터 변수에 저장
		String memberEmail = req.getParameter("inputEmail");
		String memberPw = req.getParameter("inputPw");
		 
		//getParameter( ) 오버라이딩 확인
		System.out.println("이메일"+memberEmail);
		System.out.println("비밀번호"+memberPw);
		
		//파라미터를 VO에 세팅(lombok 세팅)
		Member mem = new Member();
		
		mem.setMemberEmail(memberEmail);
		mem.setMemberPw(memberPw);
		
		
		try {
			MemberService service = new MemberService(); 
			
			Member loginMember =service.loginMember(mem); //이메일 비밀번호가 일치하는 회원을 조회
			
			//로그인 성공 실패에 따른 처리 코드
			
			//*** 로그인 ***
			// ID/PW가 일치하는 회원 정보를 session scope에 저장하는 것
			
			//Session 객체 얻어오기
			HttpSession session = req.getSession();
			
			if(loginMember != null) { //성공인 경우
				//화원 정보 세팅
				session.setAttribute("loginMember", loginMember);
				
				//특정 시간 동안 요청이 없으면 세션 만료
				session.setMaxInactiveInterval(3600); //3600초 1시간.
				//초단위 작성
				
				
				//============================================================================================
				//아이디 저장(Cookie)
				
				/*Cookie : 클라이언트(브라우저)에서 관리하는 파일
				 * 
				 * -특정 주소 요청 시 마다 
				 * 	해당 주소와 연관된 쿠키 파일을 브라우저가 알아서 읽어온다.
				 *  -> 읽어온 쿠기 파일 내용을 서버에 같이 전달 
				 *  
				 * 생성 및 사용 방법
				 * 
				 * 1) 서버가 요청에 대한 응답을 할 때 
				 *     쿠키를 생성한 후 응답에 쿠키를 담아서 클라이언트에게 전달
				 *     
				 * 2) 응답담긴 쿠키가 클라이언트에게 파일형태로 저장이 되고
				 * 
				 * 3) 이후 특정 주소 요청 시 쿠키 파일을 브라우저가 찾아 자동으로 요청에 실어서 보낸다.
				 * 
				 * 4) 서버는 요청에 실려온 쿠키 파일을 사용한다.
				 * */
				
				//      ("클라이언트에 저장되는 쿠키 이름 " ,  쿠키 값 )
				Cookie c = new Cookie("saveId", memberEmail);
				
				//아이디 저장이 체크된 경우
				if(req.getParameter("saveId") != null) {
					//쿠키파일을 30일 동안 유지한다 
					c.setMaxAge(2592000);
					
					
				}else {
					//쿠키파일을 0초동안 유지한다.
					//->만약에 기존에 아이디가 존재하는 경우 쿠키파일의 유지시간을 0초로 덮어씌워서 
					//  사용자는 인지하지 못하고 삭제된다.
					c.setMaxAge(0);
				}
				
				// 이 쿠키 파일이 적용될 주소
				c.setPath( req.getContextPath() );
//				req.getContextPath() : 최상위 주소
//				/Community로 시작하는 주소에서만 쿠키를 적용하겟다.
				
				//응답 객체를 이용해서 클라이언트로 전달
				resp.addCookie(c); //이 코드가 해석 되는 순간 바로 전달된다 (Response 가 사라지기 전에 전달되어 영향권 밖이다)
				
				
				
				
				
				//============================================================================================
			}else { //실패인 경우
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다."); 
				//현재 페이지와 요청받은 페이지까지만 유지 범위가 나오니깐 되나?
				// nope
			}
			
			//클라이언트 요청 -> 서버 응답(Servlet) -> 응답화면 만들어달라고 위임(JSP)			
			
			// 1. forward (요청 위임) 
			// ->Servlet으로 응답화면 만들기 불편하기 때문에 JSP로 req,resp객체를 위임하여 
			// 	 요청에 대한 응답화면을 대신 제작함.
			
			//** 환경이 변경되도 요청 주소가 유지됨
			
			// ex) 아아 주세요 -> 주문 받음 -> 바리스타가 커피 만듬
			//     클라이언트     Servlet(직원)  응답 결과(JSP)
			
			
			
			// 2.Redirect (재 요청) 
			// -현재 Servlet에서 응답페이지(JSP)를 만들지 않고
			//  응답페이지를 만들 수 있는 다른 요청의 주소로 클라이언트를 이동 시킨다(재요청)
		
			// - 클라이언트 요청 -> HttpServletRequest / HttpServletResponse 객채 생성. -> 
			// - 클라이언트 '재요청' -> 기존에 있던 HttpServletRequest / HttpServletResponse 객채 제거 후 새로운 객체로 '재생성' -> 
			
			//리다이렉트시 request객체가 유지되지 않기 때문에
			// 특정 데이터를 전달하거나 유지하고 시픙니면
			// session 또는 application 범위에 세팅해야 한다.
			
			
			//CGV랑 같이 있는  카페
			// ex) 팝콘 주세요 -> 팝콘파는 위치를 알려줌 -> (클라이언트)팝콘 파는 곳으로 이동
			//     클라이언트    Servlet(직원)                   클라이언트의 재요청
			
			resp.sendRedirect(req.getContextPath());
			//req.getContextPath() : 최상위 주소로 이동 (/community)
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
