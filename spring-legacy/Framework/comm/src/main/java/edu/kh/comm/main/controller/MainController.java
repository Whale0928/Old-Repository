package edu.kh.comm.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //Controller이면서 Bean인 것을 등록( * 컴포넌트 스캔 시 bean으로 생성됨)
public class MainController {
	
	@RequestMapping("/main")
	public String mainForward() {
		//index.jsp의 forward를 처리하는 mainForward()에서
		//또 다시 한번 forward /common/main.jsp로 
		
		
		return "common/main";
	}
}
