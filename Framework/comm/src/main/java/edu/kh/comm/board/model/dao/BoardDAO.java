package edu.kh.comm.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.comm.board.model.vo.BoardType;


//Componet - bean 등록
@Repository // DB랑 통신하는 클래스 + Bean등록
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<BoardType> selectBoardType() {
		return sqlSession.selectList("boardMapper.selectBoardType");
									//	myPageMapper.updateInfo
	}
	
	
}
 