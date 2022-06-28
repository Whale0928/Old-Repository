package edu.kh.comm.board.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.comm.board.model.vo.BoardDetail;
import edu.kh.comm.board.model.vo.BoardType;

public interface BoardService {

	/**게시판 코드,이름 조회 서비스
	 * @return
	 */
	List<BoardType> selectBoardType();

	/**게시글 목록 조회 서비스
	 * @param cp
	 * @param boardCode
	 * @return list
	 */
	Map<String, Object> selectBoardList(int cp, int boardCode);

	/**게시글 상세조회 서비스
	 * @param boardNo
	 * @return detail
	 */
	BoardDetail selectBoardDetail(int boardNo);
	
	
	/**조회수 증가
	 * @param boardNo
	 * @return
	 */
	int updateReadCount(int boardNo);

}
