package edu.kh.comm.board.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.comm.board.model.service.BoardService;

@Controller //controller여야지 dispatcherServlet에서 요청은 전달해줌
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	//@Autowired
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	
	
	//게시글 목록 조회
	
	//@PathVariable ("value") : URL 경로에 포함되어있는 값을 변수로 사용할 수 있게 하는 Annotation 
	// -> 자동으로  request scope에 등록됨 -> jsp에서 ${value} EL 작성 가능
	
	//PathVariable : 요청 자원을 식별하는 경우
	
	//QueryString : 정렬 , 검색 등의 필터링 옵션등에 사용  
	
	@GetMapping("/list/{boardCode}")
	public String boardList(@PathVariable("boardCode") int boardCode
							// 없어되 하지만 없으면 기본값을 1으로 세팅해라 
							,@RequestParam(value="cp",required=false,defaultValue ="1") int cp
							,Model model){
		
		//게시글 목록 조회 서비스
		//1.게시판 이름 조회 -> Intetcepor로 올려둔 BoardTypeList 사용 해도 될듯?
		//2.페이지네이션 객체 생성 (listCount)
		//3.게시글 목록 조
		Map<String, Object> map =service.selectBoardList(cp,boardCode);
		model.addAttribute("map",map);
		return "board/boardList";
	}
	
}
