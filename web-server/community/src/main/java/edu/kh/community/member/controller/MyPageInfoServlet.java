package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/myPage/info")
public class MyPageInfoServlet extends HttpServlet {

	// 메인 페이지에서 닉네임 클릭시 요청되는 방식(GET)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/member/myPage-info.jsp";

		req.getRequestDispatcher(path).forward(req, resp);
	}

	// 회원 정보 수정 요청.(POST)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memberNickname = req.getParameter("memberNickname");
		String memberTell = req.getParameter("memberTel");
		String[] address = req.getParameterValues("memberAddress");
		String memberAddress = null;
		if (!address[0].equals("")) {
			memberAddress = String.join(",,", address);
		}

		// 세션에서 로그인한 회원 정보 얻어오기
		HttpSession session = req.getSession();
		// 얕은 복사 (세션에 있는 회원 정보의 객체 주소!)
		// -> loginMember를 수정하면 session의 로그인정보도 수정된다,.
		Member loginMember = (Member) (session.getAttribute("loginMember"));
		int memberNo = loginMember.getMemberNo(); // 회원 번호

		// 업데이트 필요한 정보를 모아둔 Member 객체 생성
		Member mem = new Member();
		mem.setMemberNo(memberNo);
		mem.setMemberNickName(memberNickname);
		mem.setMemberTell(memberTell);
		mem.setMemberAddress(memberAddress);

		try {
			MemberService service = new MemberService();

			int result = service.updateMember(mem);

			// 수정 성공 / 실패에 따른 메세지
			if (result > 0) { // 수정 성공
				session.setAttribute("message", "회원 정보가 수정되었습니다.");
				// DB는 수정 되었지만!
				// 로그인한 회원 정보가 담겨있는 Session 의 정보는 그대로다.
				// ->동기화 작업 필요

				loginMember.setMemberNickName(memberNickname);
				loginMember.setMemberTell(memberTell);
				loginMember.setMemberAddress(memberAddress);

			} else { // 실패
				session.setAttribute("message", "회원 정보 수정 실패");
			}
			// 성공 실패 여부 관계 없이 "내 정보" 화면 재요청

			// 절대 경로
			// resp.sendRedirect(req.getContextPath()+"/member/myPage/info");

			// 상대 경로
			resp.sendRedirect("info");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
