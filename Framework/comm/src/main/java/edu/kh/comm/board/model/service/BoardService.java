package edu.kh.comm.board.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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
	
	/**게시글 작성 + image
	 * @param detail
	 * @param imageList
	 * @param webPath
	 * @param folderPath
	 * @return
	 * @throws Exception
	 */
	int insertBoard(BoardDetail detail, List<MultipartFile> imageList, String webPath, String folderPath)throws Exception;

	/**게시글 삭제 
	 * @param boardNo
	 * @return
	 */
	int deleteBoard(int boardNo);

	/** 게시글 수정
	 * @param detail
	 * @param imageList
	 * @param webPath
	 * @param folderPath
	 * @param deleteList
	 * @return
	 */
	int updateBoard(BoardDetail detail, List<MultipartFile> imageList, String webPath, String folderPath,
			String deleteList)throws IOException;

	/**게시글 검색
	 * @param paramMap
	 * @return
	 */
	Map<String, Object> searchBoardList(Map<String, Object> paramMap);

	/**이미지 목록들 조회
	 * @return
	 */
	List<String> selectDbList();
	
	
}
