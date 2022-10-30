package edu.kh.comm.member.model.service;

import java.util.List;

import edu.kh.comm.member.model.vo.Member;

/*Service Interface를 사용하는 이유
 * 
 * 1. 프로젝트에 규칙성을 부여하기 위해서
 * 
 * 2. Spring AOP 관점지향 프로그래밍을 위해서 필요하다
 * 		-AOP 
 * 3. 클래스간의 결합도를 낮추기 약화 시키기 위해서  == 유지보수성이 향상
 * 
 * 
 * */
public interface MemberService {

	//[ 인터페이스 특징 ]
	// 1. 모든 메서드가 추상 메서드 ( public abstract ) 묵시적
	// 2. 모든 필드는 상수  (public static final ) 묵시적
	
	/**로그인 서비스
	 * @param member
	 * @return loginMember
	 */
	Member login(Member member);

	
	/**이메일 중복 검사
	 * @param memberEmail
	 * @return
	 */
	int emailDupCheck(String memberEmail);


	/**닉네임 중복검사
	 * @param memberNickname
	 * @return
	 */
	int nicknameDupCheck(String memberNickname);


	/**회원가입
	 * @param member
	 * @return
	 */
	int signUp(Member member);

    Member selectOne(String memberEmail);


	/**비동기 회원들 조회
	 * @return
	 */
	List<Member> selectAll();
}
