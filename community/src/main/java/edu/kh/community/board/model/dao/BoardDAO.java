package edu.kh.community.board.model.dao;
import static edu.kh.community.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.community.board.model.vo.Board;
import edu.kh.community.board.model.vo.BoardDetail;
import edu.kh.community.board.model.vo.BoardImage;
import edu.kh.community.board.model.vo.Pagination;


public class BoardDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BoardDAO(){
		try {
			
			prop = new Properties();
			String filePath = BoardDAO.class.getResource("/edu/kh/community/sql/board-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**게시판 이름 조회 DAO
	 * @param conn
	 * @param type 
	 * @return boardName
	 * @throws Exception
	 */
	public String selectBoardName(Connection conn, int type)throws Exception{
		String boardName = null;
		
		try {
			String sql = prop.getProperty("selectBoardName");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				boardName=rs.getString(1);
			}
		}finally{
			close(rs);
			close(pstmt);
		}
		return boardName;
	}
	
	/** 전체 게시글 수 조회
	 * @param conn
	 * @param type
	 * @return listCount
	 * @throws Exception
	 */
	public int selectListCount(Connection conn, int type)throws Exception {
	int listCount = 0;
		
		try {
			String sql = prop.getProperty("selectListCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			 
			rs=pstmt.executeQuery();
			if(rs.next()) {
				listCount=rs.getInt(1);
			}
		}finally{
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	/**특정 범위에 게시글정보들 조회
	 * @param conn
	 * @param pagination
	 * @param type
	 * @return selectBoardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Connection conn, Pagination pagination, int type)throws Exception{
		List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectBoardList");
			
			int start = (pagination.getCurrentPage()-1)*pagination.getLimit()+1;
			int end = start+pagination.getLimit()-1;
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,type);
			pstmt.setInt(2,start);
			pstmt.setInt(3,end);
			
			rs =pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberNickname(rs.getString("MEMBER_NICK"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setReadCount(rs.getInt("READ_COUNT"));	
				board.setThumbnail(rs.getString("THUMBNAIL"));
				boardList.add(board);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	/**게시글 상세 조회
	 * @param conn
	 * @param boardNo
	 * @return detail
	 * @throws Exception
	 */
	public BoardDetail selectBoardDetail(Connection conn, int boardNo)throws Exception{
		BoardDetail detail = null;
		try {
			
			String sql = prop.getProperty("selectBoardDetail");
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				detail=new BoardDetail();
				
		          detail.setBoardNo(rs.getInt(1));
		            detail.setBoardTitle(rs.getString(2));
		            detail.setBoardContent(rs.getString(3));
		            detail.setCreateDate(rs.getString(4));
		            detail.setUpdateDate(rs.getString(5));
		            detail.setReadCount(rs.getInt(6));
		            detail.setMemberNickname(rs.getString(7));
		            detail.setProfileImage(rs.getString(8));
		            detail.setMemberNo(rs.getInt(9));
		            detail.setBoardName(rs.getString(10));
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}

		return detail;
	}

	/**게시글에 첨부된 이미지 리스트 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return imageList
	 * @throws Exception
	 */
	public List<BoardImage> selectImageList(Connection conn, int boardNo)throws Exception{
		List<BoardImage> imageList = new ArrayList<BoardImage>();
		
		try {
			String sql = prop.getProperty("selectImageList");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardImage image = new BoardImage();
				image.setImageNo(rs.getInt(1));
	            image.setImageReName(rs.getString(2));
	            image.setImageOriginal(rs.getString(3));
	            image.setImageLevel(rs.getInt(4));
	            image.setBoardNo(rs.getInt(5));
	            
	            imageList.add(image);
			}
					
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return imageList;
	}

	/**다음 게시글 번호 조회
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn)throws Exception{
		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");
			stmt = conn.createStatement();
			
			rs=stmt.executeQuery(sql);
			
			if(rs.next()){
				boardNo=rs.getInt(1);
			}
			
		}finally{
			close(rs);
			close(stmt);
		}
		return boardNo;
	}

	/**게시글 작성 삽입
	 * @param conn
	 * @param detail
	 * @param boardCode
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, BoardDetail detail, int boardCode)throws Exception{
		int result = 0 ;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, detail.getBoardNo());
			pstmt.setString(2, detail.getBoardTitle());
			pstmt.setString(3, detail.getBoardContent());
			pstmt.setInt(4, detail.getMemberNo());
			pstmt.setInt(5, boardCode);
			
			result = pstmt.executeUpdate();
			
		}finally{
			close(pstmt);
		}
		return result;
	}

	/**게시글의 이미지 삽입 DAO
	 * @param conn
	 * @param image
	 * @return result
	 * @throws Exception
	 */
	public int insertBoardImage(Connection conn, BoardImage image)throws Exception{
		int result =0;
		
		try {
			String sql = prop.getProperty("insertBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, image.getImageReName());
			pstmt.setString(2, image.getImageOriginal());
			pstmt.setInt(3, image.getImageLevel());
			pstmt.setInt(4,image.getBoardNo());
			
			result=pstmt.executeUpdate();
			
		}finally{
			close(pstmt);
		}
		return result;
	}

	/**게시글 수정 DAO
	 * @param conn
	 * @param detail
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, BoardDetail detail)throws Exception{
	int result = 0 ;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, detail.getBoardTitle());
			pstmt.setString(2, detail.getBoardContent());
			pstmt.setInt(3, detail.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally{
			close(pstmt);
		}
		return result;
	}
 
	/**게시글의 이미지를 수정 DAO
	 * @param conn
	 * @param image
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardImage(Connection conn, BoardImage image)throws Exception{
		int result = 0 ;
		
		try {
			String sql = prop.getProperty("updateBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,image.getImageReName());
			pstmt.setString(2,image.getImageOriginal());
			pstmt.setInt(3,image.getBoardNo());
			pstmt.setInt(4,image.getImageLevel());
			
			result = pstmt.executeUpdate();
			
		}finally{
			close(pstmt);	
		}
			
		return result;
	}

	/* 이미지 삭제 DAO
	 * @param conn
	 * @param deleteList
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoardImage(Connection conn, String deleteList, int boardNo)throws Exception{
	int result = 0 ;
		
		try {
			String sql = prop.getProperty("deleteBoardImage")+deleteList+")";			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally{
			close(pstmt);	
		}
			
		return result;
	}

	
	
	
}
