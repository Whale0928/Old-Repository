package edu.kh.comm.board.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.comm.board.model.service.BoardService;
import edu.kh.comm.board.model.vo.BoardDetail;
import edu.kh.comm.member.model.vo.Member;

@Controller // controller여야지 dispatcherServlet에서 요청은 전달해줌
@RequestMapping("/board")
@SessionAttributes({ "loginMember" })
public class BoardController {

	@Autowired
	private BoardService service;
	// @Autowired
	private Logger logger = LoggerFactory.getLogger(BoardController.class);

	// 게시글 목록 조회

	// @PathVariable ("value") : URL 경로에 포함되어있는 값을 변수로 사용할 수 있게 하는 Annotation
	// -> 자동으로 request scope에 등록됨 -> jsp에서 ${value} EL 작성 가능

	// PathVariable : 요청 자원을 식별하는 경우

	// QueryString : 정렬 , 검색 등의 필터링 옵션등에 사용

	// 게시글 목록 조회
	@GetMapping("/list/{boardCode}")
	public String boardList(@PathVariable("boardCode") int boardCode
	// 없어되 하지만 없으면 기본값을 1으로 세팅해라
			, @RequestParam(value = "cp", required = false, defaultValue = "1") int cp, Model model) {

		// 게시글 목록 조회 서비스
		// 1.게시판 이름 조회 -> Intetcepor로 올려둔 BoardTypeList 사용 해도 될듯?
		// 2.페이지네이션 객체 생성 (listCount)
		// 3.게시글 목록 조
		Map<String, Object> map = service.selectBoardList(cp, boardCode);
		model.addAttribute("map", map);
		return "board/boardList";
	}

	// 게시글 상세 조회
	@GetMapping("/detail/{boardCode}/{boardNo}")
	public String board(@PathVariable("boardCode") int boardCode, @PathVariable("boardNo") int boardNo,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp, Model model,
			HttpServletRequest req, HttpServletResponse resp, HttpSession session) {

		// 게시글 상세조회 서비스 호출
		BoardDetail detail = service.selectBoardDetail(boardNo);

		// 게시글 조회수 증가
		// 쿠키를 이용한 조회수 중복 증가 방지 코드 + 본인의 글은 조회수 증가 X
		// int memberNo = loginMember.getMemberNo();
		// ModelAttribute는 별도의 별도의 required 속성이 없다
		// 세션에 올라가있는 LoginMember가 없으면 예외 발생

		if (detail != null) {
			Member loginMember = (Member) session.getAttribute("loginMember");

			int memberNo = 0;
			if (loginMember != null) {
				memberNo = loginMember.getMemberNo();
			}

			// 글쓴이와 현재 로그인한 회원이 같지 않은 경우에 수행 ->> 조회수 증가
			if (detail.getMemberNo() != memberNo) {
				Cookie cookie = null;
				Cookie[] cArr = req.getCookies(); // 쿠키 '들' 얻어오기

				if (cArr != null && cArr.length > 0) { // 얻오온 쿠키가 있을 경우
														// null인지 얻어왔는지 비어있는게 아닌지 둘다 비교
					// 만약 여기서 '이 게시글'에 대한 쿠키가 있을 경우
					for (Cookie c : cArr) { // 쿠키에서 얻어온 쿠키중 이름이 ( readBoardNo )가 있으면 얻어오기
						if (c.getName().equals("readBoardNo")) {
							cookie = c; // readBoardNo
						}
					}
				}

				int result = 0;

				if (cookie == null) { // 기존에 readBoardNo라는 쿠키가 없는 경우 -상세조회를 한번도 안하거나 오랜만에 들어온 경우
					cookie = new Cookie("readBoardNo", boardNo + "");
					result = service.updateReadCount(boardNo); // 조회수 증가 서비스
				} else {
					// 기존에 "readBoardNo"이라는 쿠기가 있을경우
					// -> 쿠키 저장된 값 뒤쪽에 현재 조회 게시글의 번호
					// 단 기존 쿠키 값에 중복되는 번호가 없어야 함.
					String temp[] = cookie.getValue().split("/");
					List<String> list = Arrays.asList(temp);
					// List의 indexOf(Object) :
					// - List에서 Object와 일치하는 부분의 인덱스를 반화
					// - 일치하는 부분이 없으면 -1 반환

					if (list.indexOf(boardNo + "") == -1) { // 같은 값에 같은 글번호가 없다면 쿠키에 값 추가
						cookie.setValue(cookie.getValue() + "/" + boardNo);

						// 조회수 증가
						result = service.updateReadCount(boardNo); // 조회수 증가 서비스
					}
				}

				if (result > 0) {
					// 동기화 작업 진행
					detail.setReadCount(detail.getReadCount() + 1);
					// 여기서 '이 게시글에 대한' 기존 쿠키에 게시글번호를 누적하는 동작으로 변경

					cookie.setPath(req.getContextPath());
					cookie.setMaxAge(60 * 60 * 1); // 1시간을 의미
					resp.addCookie(cookie);

				}
			}

		}

		model.addAttribute("detail", detail);

		return "board/boardDetail";
	}

}
