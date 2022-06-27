package edu.kh.comm.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.comm.board.model.service.BoardService;

@Controller //controller여야지 dispatcherServlet에서 요청은 전달해줌
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	//@Autowired
	private Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	

}
