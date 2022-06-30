package edu.kh.comm.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.comm.board.model.vo.Board;
import edu.kh.comm.board.model.vo.BoardDetail;
import edu.kh.comm.board.model.vo.BoardImage;
import edu.kh.comm.board.model.vo.BoardType;
import edu.kh.comm.board.model.vo.Pagination;

//Componet - bean 등록
@Repository // DB랑 통신하는 클래스 + Bean등록
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * 게시판의 종류의 이름을 조회하는 DAO
	 * 
	 * @return
	 */
	public List<BoardType> selectBoardType() {
		return sqlSession.selectList("boardMapper.selectBoardType");
		// myPageMapper.updateInfo
	}

	/**
	 * 게시판의 게시글 수를 조회하는 DAO
	 * 
	 * @param boardCode
	 * @return
	 */
	public int getListCount(int boardCode) {
		return sqlSession.selectOne("boardMapper.getListCount", boardCode);
	}

	/**
	 * 게시판 목록 조회 DAO
	 * 
	 * @param pagination
	 * @param boardCode
	 * @return
	 */
	public List<Board> selectBoardList(Pagination pagination, int boardCode) {

		// RowBounds 객체 ( 마이바티스)
		// 전체 조회 결과에서 몇개의 행을 건너 뛰고 ( offset )
		// 그 다음 몇개의 행만 조회 할 것인지 지정 ( limit )
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());

		return sqlSession.selectList("boardMapper.selectBoardList", boardCode, rowBounds);
	}

	/**게시글 상세조회 DAO
	 * @param boardNo
	 * @return
	 */
	public BoardDetail selectBoardDetail(int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoardDetail",boardNo);
	}

	
	/**게시글 조회수 증가 DAO
	 * @param boardNo
	 * @return
	 */
	public int updateReadCount(int boardNo) {
		return sqlSession.update("boardMapper.updateReadCount",boardNo);
	}

	/**게시글 조회 DAO
	 * @param detail
	 * @return
	 */
	public int insertBoard(BoardDetail detail) {
	
		
		int result = sqlSession.insert("boardMapper.insertBoard",detail);
		
		if(result>0) {
			result = detail.getBoardNo();
			//게시글 삽입 성공 시 
			//<selectKey> 태그를 이용해 세팅된 boardNo 값을 반환 --> 게시글번호를 사용 가능
		}
		return result;
	}

	/** 작성된 게시글의 이미지 업로드 DAO
	 * @param boardImageList
	 * @return
	 */
	public int insertBoardImageList(List<BoardImage> boardImageList) {
		return sqlSession.insert("boardMapper.insertBoardImageList",boardImageList);
	}


	/**게시글 삭제
	 * @param boardNo
	 * @return
	 */
	public int deleteBoard(int boardNo) {
		return sqlSession.update("boardMapper.deleteBoard",boardNo);
	}

}
