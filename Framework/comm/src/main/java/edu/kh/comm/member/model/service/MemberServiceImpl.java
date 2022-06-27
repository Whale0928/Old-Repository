package edu.kh.comm.member.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.dao.MemberDAO;
import edu.kh.comm.member.model.vo.Member;

@Service //비지니스 로직 (데이터 가공 + DB연결 + 등등)를 처리하는 클래스임을 명시 + Bean으로 등록
//Bean으로 등록하게 되면 스프링에서 생성+관리하는 객체
public class MemberServiceImpl implements MemberService {

    @Autowired     //Bean 등록된 객체중 같은 타입이 있으면 의존성 주입 ( DI )
    private MemberDAO dao;

    //암호화를 위한 bcrypt객체 의존성 주입
    @Autowired //bcryptPasswordEncoder
    private BCryptPasswordEncoder bcrypt;
    Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

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

        //전달받은 비밀번호를 암호화하여
        //DB에서 조회한 비밀번호와 비교 (DB에서 비교 X)

        // Why?? Bcrypt 암호화를 진행하기 위해서 어쩔수 없이 Service에서 비교해야 함

        //sha 방식 암호화
        // A회원 / 비밀번호 1234 -> 암호화 : abcd
        // B회원 / 비밀번호 1234 -> 암호화 : abcd   ( 암호화시 변경된 내용이 같음. )

        //Bcrypt 암호화 : 암호화 하기 전에 salt를 추가하여 변형된 상태로 암호화를 진행

        // A회원 / 비밀번호 1234 -> 암호화 : a@b1cd
        // B회원 / 비밀번호 1234 -> 암호화 : ab3c1d   ( 암호화시 변경된 내용이 같음. )
        //* 매번 암호화 되는 비밀번호가 달라져서 DB에서 직접 비교 불가능
        //  대신 Bcrypt 암호화를 지원하는 객체가 이를 비교하는 기능(메서드)를 가지고 있어 이를 활용하면 된다.

        // ** Bcrypt 암호화를 사용하기 위해 이를 지원하는 Spring - security 모듈 추가

        //logger.debug(member.getMemberPw()+"/"+bcrypt.encode(member.getMemberPw()));
        //logger.debug(member.getMemberPw()+"/"+bcrypt.encode(member.getMemberPw()));
        //logger.debug(member.getMemberPw()+"/"+bcrypt.encode(member.getMemberPw()));
        logger.debug(member.getMemberPw() + "/" + bcrypt.encode(member.getMemberPw()));

        //입력받은 비밀번호 (평문) : DB에서 조회한 비밀번호 (암호화)


        Member loginMember = dao.login(member);

        //loginMember == null : 일치하는 이메일 없음
        if (loginMember != null) { // 이메일이 일치하는 회원정보가 있을 경우
            //입력된 비밀번호 , 조회된 비밀번호 비교
            //앞쪽이 평문  뒤쪽에 암호화
            if (bcrypt.matches(member.getMemberPw(), loginMember.getMemberPw())) {
                loginMember.setMemberPw(null); //비교만 하고 지워버림
            } else {
                loginMember = null;
            }

        }

        return loginMember;
        // Connection을 얻어오거나 / 반환하거나
        // 트랜잭션 처리 하는 구분을 적지 않아도
        // Spring에서 제어하기 때문에 Service 구문이 간단해진다.
    }

    //이메일 중복검사 서비스 구현
    @Override
    public int emailDupCheck(String memberEmail) {
        return dao.emailDupCheck(memberEmail);
    }

    //닉네임 중복
    @Override
    public int nicknameDupCheck(String memberNickname) {
        return dao.nicknameDupCheck(memberNickname);
    }


    //회원가입.
    @Override
    public int signUp(Member inputMember) {
        inputMember.setMemberPw(bcrypt.encode(inputMember.getMemberPw()));

        //트랜잭션 제어 처리를 하는 이유
        // 1개의 서비스에서 n개의 dao를 호출
        // -> DAO하나라도 예외 발생시 전체 rollback;


        return dao.signUp(inputMember);
    }

    //회원 한명을 조회하는 비동기 통신
    @Override
    public Member selectOne(String memberEmail) {
        return dao.selectOne(memberEmail);
    }

	/**비동기 회원 목록 조회
	 *
	 */
	@Override
	public List<Member> selectAll() {
		return dao.selectAll();
	}


}
