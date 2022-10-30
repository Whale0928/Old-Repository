package edu.kh.comm.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class AfterAspect {

	Logger logger = LoggerFactory.getLogger(AfterAspect.class);

	
	@After("CommonPointcut.implPointcut()")
	public void serviceEnd(JoinPoint jp) {
		//String str += "---------------------------------------------------------------------------------------------------------------------------------\n";

		String className = jp.getTarget().getClass().getSimpleName(); 
		String methodName = jp.getSignature().getName();
		String param = Arrays.toString(jp.getArgs());
		String str = "END : " + className + " - " + methodName + "\n";
		str += "---------------------------------------------------------------------------------------------------------------------------------\n";
		logger.info(str);
		
	}

}
