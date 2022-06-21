package edu.kh.comm.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.dao.MemberDAO;

@Service //비지니스 로직 (데이터 가공 + DB연결 + 등등)를 처리하는 클래스임을 명시 + Bean으로 등록
		 //Bean으로 등록하게 되면 스프링에서 생성+관리하는 객체 
public class MemberServiceImpl implements MemberService {

	@Autowired	 //Bean 등록된 객체중 같은 타입이 있으면 의존성 주입 ( DI )
	private MemberDAO dao;
}
