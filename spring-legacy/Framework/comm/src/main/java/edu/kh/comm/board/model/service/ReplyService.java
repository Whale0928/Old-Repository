package edu.kh.comm.board.model.service;

import java.util.List;

import edu.kh.comm.board.model.vo.Reply;

public interface ReplyService {

	/** 댓글 목록 조회
	 * @param boardNo
	 * @return result
	 */
	List<Reply> selectReplyList(int boardNo);

	/** 댓글 작성
	 * @param reply
	 * @return result
	 */
	int insertReply(Reply reply);

	/** 댓글 삭제
	 * @param replyNo
	 * @return
	 */
	int deleteReply(int replyNo);

	/**게시글 수정
	 * @param reply
	 * @return
	 */
	int updateReply(Reply reply);
	

}
