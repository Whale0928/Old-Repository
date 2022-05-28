package edu.kh.jdbc.borad.model.dao;
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.borad.model.vo.Board;
import edu.kh.jdbc.borad.model.vo.Reply;

public class BoardDAO {

	//JDBC 객체 참조용 변수 선언
	private Statement stmt =null;
	private PreparedStatement pstmt =null;
	private ResultSet rs = null;
	
	//sql 내용을 저장한 Properties 객체 잠조 변수 선언
	private Properties prop;
	
	//기본 생성자 xml 읽어오게 
	public BoardDAO() {
		try {
			prop = new Properties();
			//XML 파일 읽어오기
			prop.loadFromXML(new FileInputStream("board-sql.xml"));
			}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	/**게시글 목록 조회 DAO
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAll(Connection conn)throws Exception{
		//결과 저장용 변수
		List<Board> boardList = new ArrayList<Board>();
		try {
			//1) sql작성
			String sql = prop.getProperty("selectAll");
			//2)Statement 생성
			stmt = conn.createStatement();
					
			//3) rs에 수행 결과 반환
			rs = stmt.executeQuery(sql);
			//4) resultSet을 한행 씩 접근하는 while 수행 (rs.next())
			while(rs.next()) {
				//5) 현재행에서 컬럼명을 이용해 컬럼 값 얻어오기.
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				Date createDate = rs.getDate("CREATE_DATE");
				int readCount = rs.getInt("READ_COUNT");
				String memberNAME = rs.getString("MEMBER_NM");
				int replyCount = rs.getInt("REPLY_COUNT");
				//6) board객체를 생성하여 컬럼 값 담기
				
				//7) board객체를 boardList에 추가
				Board board = new Board(boardNo, boardTitle, createDate, readCount, memberNAME, replyCount);
				boardList.add(board);
			
			}
		}finally{
			//JDBC 객체 반환 (conn제외)
			close(rs);
			close(stmt);
		}
		//결과 반환
		return boardList;
	}

	/**특정 게시글 상세 조회
	 * @param conn
	 * @param boardNo
	 * @return borad 
	 * @throws Exception
	 */
	public Board selectOne(Connection conn, int boardNo)throws Exception{
		Board board = null;
		
		try {
			//1) sql 작성
			String sql = prop.getProperty("selectOne");
			//2) PreparedStatement  생성
			
			pstmt = conn.prepareStatement(sql);
			//3) 위치 홀더 세팅
			pstmt.setInt(1, boardNo);
					
			//4) rs에 수행후 반환 받은 result set을 저장
			rs = pstmt.executeQuery();
				
			//5) 조회된 한 행이 있을 경우 조회된 컬럼 값 얻어오기
			if(rs.next()) {
				//6.Board 객체를 생성하여 컬럼 값 세팅
				//int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				Date createDate = rs.getDate("CREATE_DATE");
				int readCount = rs.getInt("READ_COUNT");
				String memberNAME = rs.getString("MEMBER_NM");
				
				String boardContent = rs.getString("BOARD_CONTENT");
				int memberNo = rs.getInt("MEMBER_NO");
				
				board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setCreateDate(createDate);
				board.setReadCount(readCount);
				board.setMemberNAME(memberNAME);
				board.setBoardContent(boardContent);
				board.setMemberNo(memberNo);
			}
			
		}finally {
			//7) jdbc 객체 종료
			close(rs);
			close(pstmt);
			
			
		}
		
		//결과 반환
		return board;
	}

	/**특정 게시글의 댓글 목록을 조회하는 DAO
	 * @param conn
	 * @param boardNo
	 * @return ReplyList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(Connection conn, int boardNo)throws Exception {
		List<Reply> replyList = new ArrayList<Reply>();
		
		try {
			//1) sql
			String sql = prop.getProperty("selectReplyList");
			//2) pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			//3)위치홀더 세팅
			pstmt.setInt(1, boardNo);
			
			//4) rs 반환받아서 저장하기
			rs=pstmt.executeQuery();
			
			//5) 조회된 결과를 한행씩 접근 한다
			while(rs.next()) {
				int replyNo = rs.getInt("REPLY_NO");
				String replyContent = rs.getString("REPLY_CONTENT");
				Date createDate = rs.getDate("CREATE_DATE");
				int memberNo = rs.getInt("MEMBER_NO");
				String memberNAME = rs.getString("MEMBER_NM");
				
				//6) Reply 객체 하나 생성해서 자료 담고 
				Reply reply = new Reply(replyNo, replyContent, createDate, memberNo, boardNo, memberNAME);
				
				//7)list에 추가한다.
				replyList.add(reply);
			}
			
		}finally {
			//8) JDBC반환
			close(rs);
			close(pstmt);
		}

		return replyList;
	}

	/**게시글 조회수 증가DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int boardNo)throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("increaseReadCount");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	/**게시글 삭제
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn,int boardNo)throws Exception {
		int result = 0;
		try {
			String sql = prop.getProperty("deleteBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**게시글 수정
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, Board board)throws Exception{
		int result =0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**댓글 작성
	 * @param conn
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Connection conn, Reply reply)throws Exception {
		int result =0;
		
		try {
			String sql = prop.getProperty("insertReply");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getMemberNo());
			pstmt.setInt(3, reply.getBoardNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	/**댓글 수정
	 * @param conn
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(Connection conn,Reply reply)throws Exception {
		int result =0;
		
		try {
			String sql = prop.getProperty("updateReply");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getReplyNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
		}
	
	/**댓글 삭제
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(Connection conn,int replyNo)throws Exception {
		int result =0;
		
		try {
			String sql = prop.getProperty("deleteReply");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	/**게시글 작성 dao
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, Board board)throws Exception {
		int result=0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	/**게시글 검색
	 * @param conn
	 * @param menuNum
	 * @param keyword
	 * @return board
	 * @throws Exception
	 */
	public List<Board> searchBoard(Connection conn, int menuNum, String keyword)throws Exception{
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			//sql (menuNum)에 따라서 sql 작성
			String sql = prop.getProperty("searchBoard1")+
						 prop.getProperty("condition"+menuNum)
						 +prop.getProperty("searchBoard2");
			
			pstmt = conn.prepareStatement(sql);

			//-> 주의점
			//    제목 + 내용을 검색하는 조건(3번)은 혼자만 위치홀더가 2개다!!
			pstmt.setString(1, keyword);
			if(menuNum==3) pstmt.setString(2, keyword);
						
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				Date createDate = rs.getDate("CREATE_DATE");
				int readCount = rs.getInt("READ_COUNT");
				String memberNAME = rs.getString("MEMBER_NM");
				int replyCount = rs.getInt("REPLY_COUNT");
				
				Board board = new Board(boardNo, boardTitle, createDate, readCount, memberNAME, replyCount);
				boardList.add(board);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	



}

