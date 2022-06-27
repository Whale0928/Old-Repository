package edu.kh.comm.board.model.service;

import java.util.List;

import edu.kh.comm.board.model.vo.BoardType;

public interface BoardService {

	/**게시판 코드,이름 조회
	 * @return
	 */
	List<BoardType> selectBoardType();

}
