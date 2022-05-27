package edu.kh.community.board.model.service;
import static edu.kh.community.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.community.board.model.dao.ReplyDAO;
import edu.kh.community.board.model.vo.Reply;

public class ReplyService {
	ReplyDAO dao = new ReplyDAO();

	/**댓글 목록 조회
	 * @param boardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(int boardNo)throws Exception{
		Connection conn = getConnection();
		
		List<Reply> rList = dao.selectReplyList(conn,boardNo);

		close(conn);
		
		return rList;
	} 
}
