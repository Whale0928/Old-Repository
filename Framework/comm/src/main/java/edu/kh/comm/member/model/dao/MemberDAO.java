package edu.kh.comm.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.comm.member.model.vo.Member;

@Repository //영속성을 가지는 DB 파일과 연결되는 명시 + Bean으로 등록
			//영속성  == DB연결 관련된 모든 것들
public class MemberDAO {

	//DAO는 DB랑 연결하기 위한 Connection이 공통적으로 필요하다
	// -> 필드에 선언
	// + Mybatis 영속성 프레임워크를 이용하려면 Connection을 이용해 만들어진 객체
	//	SqlSessionTemplate을 사용
	
	@Autowired //root-context.xml에서 생성된 sqlSessionTemplate Bean을 의존성 주입 ( DI )
	private SqlSessionTemplate sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(MemberDAO.class); 
	
	//로그인 DAO
	public Member login(Member member) {
		//1행 조회시 (파라미터 X) 방법
		//int count = sqlSession.selectOne("memberMapper.test1");
		
		//int count = sqlSession.selectOne("memberMapper.test1");
		//logger.debug(count+"");
		
		//1행 조회 (파라미터) 조회
		//String memberNickname = sqlSession.selectOne("memberMapper.test2",member.getMemberEmail());
		//logger.debug(memberNickname);
		
		//1행 조회 파라미터가 VO일때
		//String memberTel = sqlSession.selectOne("memberMapper.test3",member);
		//logger.debug(memberTel);
		
		//1행 조회 파라미터와 반환값 둘다 VO
		Member loginMember = sqlSession.selectOne("memberMapper.loginMember",member);
		//logger.debug(loginMember.toString());
		
		return loginMember;
	}

	
	//이메일 중복 검사 DAO
	public int emailDupCheck(String memberEmail) {
		return sqlSession.selectOne("memberMapper.emailDupCheck",memberEmail);
	}

	//닉네임 조회
	public int nicknameDupCheck(String memberNickname) {
	return sqlSession.selectOne("memberMapper.nicknameDupCheck",memberNickname);
	}

	//회원가입
	public int signUp(Member inputMember) {
	return sqlSession.insert("memberMapper.signUp",inputMember);
	}
	
	
}
