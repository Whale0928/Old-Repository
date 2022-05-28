package edu.kh.jdbc.borad.model.service;

import java.sql.Connection;
import java.util.List;
import edu.kh.jdbc.borad.model.dao.BoardDAO;
import edu.kh.jdbc.borad.model.vo.Board;
import edu.kh.jdbc.borad.model.vo.Reply;

//import static : 스태틱 필드 /메서드 호출시 클래스명 생략
import static edu.kh.jdbc.common.JDBCTemplate.*;
// * : 모든 전부(ALL) 

public class BoardService {
	
	BoardDAO dao = new BoardDAO(); 
	
	/** 게시글 목록 조회를 위한 Service
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAll()throws Exception{
		Connection conn = getConnection();
				
		List<Board> boardList = dao.selectAll(conn); 
		
		close(conn);
		
		return boardList;
	}
	
	/**게시글 상세 조회
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectOne(int boardNo)throws Exception {
		//1) connetion 생성
		Connection conn = getConnection();
		
		//2) 특정 게시글 상세 조회 dao메서드(select) 호출 후 결과 반환 받기
		Board board = dao.selectOne(conn,boardNo);
		
		if(board != null) { //검색한 게시글 상세 내용이 있을 경우에만 
			//3-1) 특정 게시글으 댓글 목록 조회 dao메서드(select) 호출후 결과 반환 받기
			List<Reply> replyList = dao.selectReplyList(conn,boardNo);
			board.setReplyList(replyList);
			
			//3-1) 게시글 조회수 증가 DAO메서드 호출후 결과 반환 받기(update)
			int result = dao.increaseReadCount(conn,boardNo);
			
			if(result > 0) {
				commit(conn);
				//update전에 게시글 정보를 조회 해 상승한 조회수가 대입이 안된 상태
				//조회된 게시글 조회수보다 1 낮다
				board.setReadCount(board.getReadCount()+1);
			}else {
				rollback(conn);
			}
		}
		
		//4) Connection 반환
		close(conn);
		
		return board; //게시글 상세 조회 댓글 목록
	}

	/**게시글 삭제 
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo)throws Exception{
	
		Connection conn =  getConnection();
				
		int result = dao.deleteBoard(conn,boardNo);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		close(conn);

		return result;
	}

	/**게시글 수정
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Board board)throws Exception{
		Connection conn = getConnection();
				
		int result = dao.updateBoard(conn,board);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/**댓글 작성
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Reply reply)throws Exception {
		Connection conn = getConnection();
		
		int result = dao.insertReply(conn,reply);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	/**댓글 수정
	 * @param reply
	 * @return result 
	 * @throws Exception
	 */
	public int updateReply(Reply reply)throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateReply(conn,reply);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
	
		return result;
	}

	/**댓글 삭제
	 * @param replyNo 
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(int replyNo)throws Exception {
		Connection conn = getConnection();
		
		int result = dao.deleteReply(conn,replyNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
	
		return result;
	}


	/**게시글 작성
	 * @param board
	 * @return result 
	 * @throws Exception
	 */
	public int insertBoard(Board board)throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertBoard(conn,board);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	/**게시글 검색 리스트
	 * @param menuNum
	 * @param keyword
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoard(int menuNum, String keyword)throws Exception{
		Connection conn = getConnection();
		
		List<Board> boardList = dao.searchBoard(conn,menuNum,keyword);
		
		close(conn);
		
		return boardList;
	}
}
