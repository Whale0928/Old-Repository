package edu.kh.comm.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.comm.board.model.vo.Reply;

@Repository
public class ReplyDAO {
	
	//등록된 BEAN 중에서 같은 타입을 찾아 의존성을 주입받는다.
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Reply> selectReplyList(int boardNo) {
		return sqlSession.selectList("replyMapper.selectReplyList",boardNo);
	}

	public int insertReply(Reply reply) {
		return sqlSession.insert("replyMapper.insertReply",reply);
	}

	public int deleteReply(int replyNo) {
		return sqlSession.update("replyMapper.deleteReply",replyNo);
	}

	public int updateReply(Reply reply) {
		return sqlSession.update("replyMapper.updateReply",reply);
	}

}
