package edu.kh.comm.board.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import edu.kh.comm.board.model.service.ReplyService;
import edu.kh.comm.board.model.vo.Reply;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// REST API 
// 자원의 이름을 구분 (자원의 표현) 하여 
// 자원의 상태를 주고 받는 것

//특정한 이름 ( 주소 ) 로 요청이 오면 값으로 응답.

// Rest Controller : 요청에 따른 응답이 모두 데이터(값) 자체인 컨트롤러
// ->  Controller + @ResponseBody

@RestController //모든 요청이 비동기 요청
@RequestMapping("/reply")
@SessionAttributes({ "loginMember" })
public class ReplyController {

	//Service Bean 연결
	@Autowired
	private ReplyService service;
	
	//Logger 찍기
	private Logger log = LoggerFactory.getLogger(ReplyController.class);
	
	
	//댓글 목록을 조회
	@GetMapping("/selectReplyList")
	public String selectReplyList(int boardNo) {
		
		List<Reply> rList = service.selectReplyList(boardNo);
		
		return new Gson().toJson(rList);
	}
	
	//댓글 작성
	@PostMapping("/insert")
	public int insertReply(Reply reply) {
		return service.insertReply(reply);
	}
	
	//댓글 삭제
	@GetMapping("/delete")
	public int deleteReply(int replyNo) {
		return service.deleteReply(replyNo);
	}
	
	//댓글 수정
	@PostMapping("/update")
	public int updateReply(Reply reply) {
		return service.updateReply(reply);
	}
	
}
