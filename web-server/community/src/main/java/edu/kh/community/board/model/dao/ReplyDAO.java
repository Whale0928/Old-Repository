package edu.kh.community.board.model.dao;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.community.board.model.vo.Reply;
import static edu.kh.community.common.JDBCTemplate.*;

public class ReplyDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public ReplyDAO(){
		try {
			
			prop = new Properties();
			String filePath = ReplyDAO.class.getResource("/edu/kh/community/sql/reply-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<Reply> selectReplyList(Connection conn, int boardNo)throws Exception{
		List<Reply> rList = new ArrayList<Reply>();
		
		try {
			String sql = prop.getProperty("selectReplyList");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Reply r = new Reply();
				
				r.setReplyNo(rs.getInt("REPLY_NO"));
				r.setReplyContent(rs.getString("REPLY_CONTENT"));
				r.setCreateDate(rs.getString("CREATE_DT"));
				r.setBoardNo(boardNo);
				r.setMemberNo(rs.getInt("MEMBER_NO"));
				r.setMemberNickname(rs.getString("MEMBER_NICK"));
				r.setProfileImage(rs.getString("PROFILE_IMG"));
				
				rList.add(r);
			}
			
		}finally{
			close(rs);
			close(pstmt);
		}
		return rList;
	}

	/**댓글 삽입 
	 * @param conn
	 * @param reply
	 * @return reuslt
	 * @throws Exception
	 */
	public int insertReply(Connection conn, Reply reply)throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertReply");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getMemberNo());
			pstmt.setInt(3, reply.getBoardNo());
			
			result = pstmt.executeUpdate();

			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 댓글 삭제DAO
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(Connection conn, int replyNo)throws Exception{
	int result = 0;
		try {
			String sql = prop.getProperty("deleteReply");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/**댓글 수정 DAO
	 * @param conn
	 * @param replyNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(Connection conn, int replyNo, String replyContent)throws Exception{
		int result = 0;
		try {
			String sql = prop.getProperty("updateReply");
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, replyContent);
			pstmt.setInt(2, replyNo);
			result = pstmt.executeUpdate();	
		}finally {			close(pstmt);			}
		
		return result;
		}
	
	
	
}
