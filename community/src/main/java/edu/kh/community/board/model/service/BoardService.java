package edu.kh.community.board.model.service;
import static edu.kh.community.common.JDBCTemplate.*;
import static edu.kh.community.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.community.board.model.dao.BoardDAO;
import edu.kh.community.board.model.vo.Board;
import edu.kh.community.board.model.vo.BoardDetail;
import edu.kh.community.board.model.vo.BoardImage;
import edu.kh.community.board.model.vo.Pagination;
import edu.kh.community.common.Util;

public class BoardService {
	private BoardDAO dao = new BoardDAO();

	/**게시글 목록 조회
	 * @param type
	 * @param cp
	 * @return selectBoardList
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardList(int type, int cp)throws Exception{
		Connection conn = getConnection();
		
		//1. 게시판 이름 조회 DAO 호출
		String boardName = dao.selectBoardName(conn,type);
		
		//2 - 1. 특정 게시판의 전체 게시글 수 조회
		int listCount = dao.selectListCount(conn,type);
		
		//2 - 2. 전체 게시글수 + 현재 페이지(CP)를 이용해 페이지네이션 객체 생성
		Pagination pagination = new Pagination(cp, listCount);
		
		//3 게시글 목록 조회
		List<Board> boardList = dao.selectBoardList(conn,pagination,type);
		
		//4.map 객체를 생성하여 1 , 2 , 3의 결과 객체를 모두 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		close(conn);
		
		return map; 
	}

	/**게시글 상세조회 service
	 * @param boardNo
	 * @return detail
	 * @throws Exception
	 */
	public BoardDetail selectBoardDetail(int boardNo)throws Exception {
		Connection conn = getConnection();
		
		//1) 게시글 ( BOARD 테이블 ) 내용만 조회
		BoardDetail detail = dao.selectBoardDetail(conn,boardNo);
		
		//2) 게시글에 첨부된 이미지(BOARD_IMG 테이블)
		if(detail !=null) {			
			List<BoardImage> imageList = dao.selectImageList(conn,boardNo);
			detail.setImageList(imageList);
		}
		
		/*
		 * Map<String, Object> map = new HashMap<String, Object>;
		 */
		
		close(conn);
		
		return detail;
	}

	/**게시글 등록 serviec
	 * @param detail
	 * @param imageList
	 * @param boardCode
	 * @return boardNo
	 * @throws Exception
	 */
public int insertBoard(BoardDetail detail, List<BoardImage> imageList, int boardCode)throws Exception{
	Connection conn = getConnection();
	
	//1.다음 작성할 게시글 번호 얻어오기
	//Board 테이블 INSERT 할때 / BOARD_IMG 테이블에 INSERT할때/ 반환 값(상세조회번호)
	int boardNo=dao.nextBoardNo(conn);
	
	//2. 게시'글' 부분만 삽입(detail,boardCode 사용)
	detail.setBoardNo(boardNo);
	//2-1) xss 방지 처리 (제목 내용)
	detail.setBoardTitle(Util.XssHandling(detail.getBoardTitle()));
	detail.setBoardContent(Util.XssHandling(detail.getBoardContent()));
	//2-2) 개행 문자 처리(내용만 해줘도 됨)
	detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));
	
	int result = dao.insertBoard(conn,detail,boardCode);
	
	if(result>0) { //게시글 삽입에 성공했다면
		//3 이미지 정보만 삽입(imageList)
		for(BoardImage image : imageList){ //하나씩 꺼내에서 삽입 수행.
			image.setBoardNo(boardNo); // 게시글 번호 세팅
			result=dao.insertBoardImage(conn,image);
			
			if(result == 0) {//이미지 삽입이 실패한 경우.
				break; //하나라도 실패하면 프로그램이 오류인 부분
			}
			
		} //for 끝
	} //if 끝
	
	//트랜잭션 처리
	
	if(result >0) {
		commit(conn);
	}else { //2,3번에서 한번이라도 실패한 경우
		rollback(conn);
		boardNo = 0; // 게시글 번호를 0으로 바꿔서 실패했음을 컨트롤러로 전달
	}
	
	close(conn);
		
		return boardNo;
	}
	
}
