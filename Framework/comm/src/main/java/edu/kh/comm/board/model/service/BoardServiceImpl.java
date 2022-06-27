package edu.kh.comm.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.comm.board.model.dao.BoardDAO;
import edu.kh.comm.board.model.vo.BoardType;

@Service // 비지니스 로직을 처리한다는 클래스 명시 + bean 등록
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO dao;

	@Override
	public List<BoardType> selectBoardType() {
		return dao.selectBoardType();
	}

	
}
