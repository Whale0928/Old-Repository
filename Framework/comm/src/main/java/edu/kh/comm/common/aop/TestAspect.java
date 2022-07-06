package edu.kh.comm.common.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component // 런타임 시 필요한 위치에 코드를 알아서 동적 참여 시킬 수 있도록 Bean으로 등록'
@Aspect //공통 관심사  ( 특정한 흐름 사이에 끼어서 수행할 코드 )가 작성된 클래스임을 명시한다.
		//@Aspect 어노테이션이 작성되어 있는 클래스에는
		//advice ( 끼어서 수행할 메서드)
		//point cut ( advice 가 끼어들어 수행될 부분)이 작성되어있어야 한다.
public class TestAspect {
	
	private Logger log = LoggerFactory.getLogger(TestAspect.class);
	
	
	//joinpoint : advice가 적용될 수 있는 지점(후보)
	//Pointcut  : joinpoint 중에서 실제로 advice를 적용할 지점 선택
	
	//***Pointcut 작성방법***
	// @Pointcut(" execution([접근제한자]반환형 패키지+클래스명.메서드명([매개변수]) ")==execution: 실행
	
	// * : 모든 | 아무 값이나
	// .. : 하위 | 아래 (하위 패키지) | 매개변수 자리에서는 0개 이상의 매개변수(*처럼 동작ㅋㅋ)
	
	
	//advice  === 끼워들어 수행될 메서드
	//여기서는 끼어 들어 log만 출력하는 메서드가 된다
	
	//@Before : Pointcut에 지정된 메서드가 수행되기 전 advice를 수행을 명시하는 어노테이션
	// edu.kh.comm 패키지 아래의 impl 로 끈나는 모든 클래스들 중 모든 메서드
	//@Before("execution(*  edu.kh.comm.member..*Impl.*(..))")
	//@Before("CommonPointcut.implPointcut()") // CommonPointcut에서 가져온다
	
	public void start() {//서비스 코드가 수행되기 전에 수행되는 메서드(advice)
		log.info("-------------------------------Service Impl Start-------------------------------");
	}
	
	//@After : Pointcut에 지정된 메서드가 수행 된 후 advice를 수행을 명시하는 어노테이션
	//@After("execution(*  edu.kh.comm.member..*Impl.*(..))")
	//@After("CommonPointcut.implPointcut()") // CommonPointcut에서 가져온다
	
	public void end() {//서비스 코드가 수행되기 전에 수행되는 메서드(advice)
		log.info("-------------------------------Service END-------------------------------");
	}
	
	
	//Pointcut 지점을 작성해둔 메서드를 구현
	// -> Pointcut의 패턴이 작성되는 부분에 memberPointcut 메서드 이름을 작성하면
	// @Pointcut 함수를 수행한다.
	@Pointcut("execution(*  edu.kh.comm.member..*Impl.*(..))")
	public void memberPointcut() { } //내용 작성X
	
	
}
