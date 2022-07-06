package edu.kh.comm.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
@Aspect
public class AroundAspect {
	private Logger logger = LoggerFactory.getLogger(AroundAspect.class);

	// @Around : 전처리(before) , 후처리(After)를 한 번에 작성 가능한 advice

	
	@Around("CommonPointcut.implPointcut()")
	public Object runningTime(ProceedingJoinPoint jp)throws Throwable{ // Exception과 error의 부모로 모두 처리 
		//proceed : 전 처리 + 후 처리를 모두
		//proceedingJoinPoint : 전 처리 + 후 처리 관련 기능 , 값을 얻어올 수 있는 메서드를 제공한다.
		

		// proceed() 메소드 호출 전 : @Before advice 작성
		// proceed() 메소드 호출 후 : @After advice 작성
		// 메소드 마지막에 proceed()의 반환값을 리턴해야함.
		
		
		//System.currentTimeMillis(); : 
		//	1970/01/01 오전 9시 (한국 OS 기준)
		//  지금까지 지난 시간을 ms단위로 나타낸 값
		
		
		long startMs = System.currentTimeMillis();
		
		Object obj = jp.proceed();
		
		long endMs = System.currentTimeMillis();
	
		
		logger.info("Running Time : "+(endMs-startMs)+"ms");
		
		return obj;
	}
	
	

}
