package edu.kh.comm.common.interceptor;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.comm.board.model.service.BoardService;
import edu.kh.comm.board.model.vo.BoardType;

public class BoardTypeInterceptor implements HandlerInterceptor {

	/*
	 * 인터셉터가 요청을 가로채는 시기
	 * 
	 * 1. preHandle ( 전처리 ) : Controller 수행 '전'
	 * 
	 * 2. postHandle ( 후처리 ) : Controller 수행 '후' ( 컨트롤러의 수행 결과를 참고 할 수 있다. )
	 * 
	 * 3. afterCompletion ( 모든 요청 처리가 완료된 후 == View 단 처리 후 ) : 보통 자원반환(close)를 수행.
	 * 
	 * -- 단 , 비동기 요청은 가로채지 않는다. ( 자바 내부의 별도 쓰레드 요청 )
	 * 
	 */

	private Logger logger = LoggerFactory.getLogger(BoardTypeInterceptor.class);

	// DI
	@Autowired
	private BoardService boardService;

	// Controller 접근 전 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		logger.info("전 처리 수행");

		// aplication scope에 " boardTypeList " 가 없을 경우
		// 이를 조회하는 Service 호출 후 결과를 세팅

		// application 가져오기
		ServletContext application = request.getServletContext();

		if (application.getAttribute("boardTypeList") == null) {
			List<BoardType> boardTypeList = boardService.selectBoardType();
			application.setAttribute("boardTypeList", boardTypeList);
			logger.debug(boardTypeList.toString());
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	// Controller 접근 후 처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	// Controller 처리 후 View까지 처리후 . *보통 close등 자원 반환을 수행한다.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
