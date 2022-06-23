package edu.kh.comm.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.dao.MemberDAO;
import edu.kh.comm.member.model.vo.Member;

@Service //비지니스 로직 (데이터 가공 + DB연결 + 등등)를 처리하는 클래스임을 명시 + Bean으로 등록
		 //Bean으로 등록하게 되면 스프링에서 생성+관리하는 객체 
public class MemberServiceImpl implements MemberService {

	@Autowired	 //Bean 등록된 객체중 같은 타입이 있으면 의존성 주입 ( DI )
	private MemberDAO dao;
	//Service interface에 이미 설명이 작성되어 있어 별도로 설명을 작성하지 않는다
	
	
	// [ Connection 을 Service에서 얻어왔던 이유 ]
	// - Service의 메서드 하나는 요청을 처리하는 업무 단위 
	// -> 해당 업무가 끈난 후 트랜젝션을 한번에 처리하기 위해서 
	//	  어쩔 수 없이 Connection을 Service에서 얻어올 수 밖에 없었다.
	
	// AOP( 관점 지향 프로그래밍 )
	// Service에서 수행이 완료후 return이 수행후 자동으로 트랜잭션을 처리해준다.
	
	
	
	
	
	
	// 로그인 서비스 상속 받아 구현
	@Override
	public Member login(Member member) {
		Member loginMember = dao.login(member);
		return loginMember;
		// Connection을 얻어오거나 / 반환하거나
		// 트랜잭션 처리 하는 구분을 적지 않아도
		// Spring에서 제어하기 때문에 Service 구문이 간단해진다.
	}
	
}
