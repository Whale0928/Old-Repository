package edu.kh.community.board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.community.board.model.service.BoardService;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//QueryString 얻어오기 (파라미터 얻어오기)
			int type = Integer.parseInt(req.getParameter("type"));
			
			//  /board/list?type=1 & cp=2
			
			//nav 메뉴선택시 queryString에 cp가 없으면 1으로 고정
			int cp = 1;
			//페이지네이션의 번호 선택 시 
			//쿼리스트링에 cp가 있음 --> cp = 쿼리스트링의 cp 값
			if(req.getParameter("cp") != null){
				cp =Integer.parseInt(req.getParameter("cp")); 
			}			
			
			BoardService service = new BoardService();
		
			//게시판 이름, 페이지네이션 객체, 게시글 리스트 3개를 한번에 반환하는 service 호출
			Map<String, Object> map = service.selectBoardList(type,cp);
			
			//request범위로 map을 세팅
			req.setAttribute("map", map);
			
			String path = "/WEB-INF/views/board/boardList.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
