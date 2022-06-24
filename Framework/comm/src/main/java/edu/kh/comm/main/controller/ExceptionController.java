package edu.kh.comm.main.controller;

import java.sql.SQLException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	//컨트롤러에서 발생하는 예외를 모아 처리
	@ExceptionHandler(SQLException.class)
	public String sqlExceptionHandler(Exception e,Model model){
		e.printStackTrace();
		model.addAttribute("errorMessage","SQL 서비스 이용중 문제가 발생하였습니다");
		model.addAttribute("e",e);
		return "common/error";
	}

	@ExceptionHandler(Exception.class)
	public String AllexceptionHandler(Exception e,Model model){
		e.printStackTrace();
		model.addAttribute("errorMessage","서비스 이용중 알수없는 문제가 발생하였습니다");
		model.addAttribute("e",e);
		return "common/error";
	}
}
