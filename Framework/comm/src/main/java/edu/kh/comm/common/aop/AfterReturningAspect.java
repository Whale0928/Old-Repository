package edu.kh.comm.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(5) // advie 실행 순서 클 수록 먼저 실행.
@Component
@Aspect
public class AfterReturningAspect {
	Logger logger = LoggerFactory.getLogger(AfterReturningAspect.class);

	
	//@AfterReturning : 기본 Atfer + 반환 값 같이 얻어오기
	
	
	//returning="returnObj" : 반환값을 저장할 매개 변수를 지정
	
	@AfterReturning(pointcut ="CommonPointcut.implPointcut()",returning="returnObj")
	public void serviceReturValue(JoinPoint jp, Object returnObj) {
		logger.info("Return Value : " + returnObj);
	}
	
}
