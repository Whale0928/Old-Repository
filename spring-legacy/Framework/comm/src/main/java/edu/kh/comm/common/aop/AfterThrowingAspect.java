package edu.kh.comm.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterThrowingAspect {
	Logger logger = LoggerFactory.getLogger(AfterThrowingAspect.class);

		
	//@AfterThrowing : 기본 Atfer + 반환 값 같이 얻어오기
	
	
	//@AfterReturning(pointcut ="CommonPointcut.implPointcut()",returning="returnObj")
	@AfterThrowing(pointcut ="CommonPointcut.implPointcut()",throwing="exceptionObj")
	public void serviceThrowValue(JoinPoint jp, Exception exceptionObj) {
		//logger.info("Throws Value : " + exceptionObj);
		
		//exceptionObj.getStackTrace(); == 여러줄의 에러중 딱 0번째 줄만 가져온다.
		String str = "*** < < Exception  >  > : "+exceptionObj.getStackTrace()[0];
		str += "  -  " + exceptionObj.getMessage()+"\n";
		
		logger.error(str);
	}
	
	
}
