package edu.kh.comm.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//공통으로 사용될 포인트 컷

@Component
@Aspect
public class CommonPointcut {

	private Logger log = LoggerFactory.getLogger(CommonPointcut.class);
	
	
	
	//회원 서비스용 PointCut
	@Pointcut("execution(*  edu.kh.comm.member..*Impl.*(..))")
	public void memberPointcut() { } //내용 작성X

	//모든 ServiceImple 용 Pointcut
	@Pointcut("execution(*  edu.kh.comm..*Impl.*(..))")
	public void implPointcut() { } //내용 작성X
	
	
}
