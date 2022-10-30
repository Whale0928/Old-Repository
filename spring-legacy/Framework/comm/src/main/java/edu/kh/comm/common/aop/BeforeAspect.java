package edu.kh.comm.common.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.kh.comm.member.model.vo.Member;

@Component
@Aspect
public class BeforeAspect {

	Logger logger = LoggerFactory.getLogger(BeforeAspect.class);
	
	
	//이전에 실행 됬으면 하는 advice들
	
	//JoinPoint : advice가 적용될 수 있는 후보들
	// || 동명이인 느낌
	//JoinPoint Interface : 
	//		advice가 적용되는 target Object (ServiceImple)의 
	//		각종 정보 , 전달되는 매개변수 , 메서드, 반환값 , 예외등을 얻을 수 있는 메서드를 제공
	//		function(e, event) 같은건가?
	
	// **(항상 첫번째 매개변수로 작성되어야 한다.)**
	
	//메서드 시그니쳐
	
	
	@Before("CommonPointcut.implPointcut()")
	public void serviceStart(JoinPoint jp) {
		String str = "---------------------------------------------------------------------------------------------------------------------------------\n";
		
		//jp.getTarget : AOP가 적용된 객체(각종 ServiceImple)
		String className = jp.getTarget().getClass().getSimpleName(); //간단한 클래스명 (패키지명을 제외한)

		//jp.getSignature() : 수행되는 메서드 정보.
		String methodName = jp.getSignature().getName();

		//Arrays.toString(jp.getArgs()); : 메서드에 매개변수를 문자열 상태로 받음
		String param = Arrays.toString(jp.getArgs());
		
		
		
		str += "Start : "+className+" - "+methodName+"\n";
		//	 MemberServiceImpl - login 같이 생성된다.
		
		str += "Parameter : " + param +"\n";
		
		//세션에 접근한 사람의 ip를 가져오기
		try {
			//HttpServletReqeust 얻어오기
			// 단,  스프링 스케줄러 동작 시 예외 발생 (스케줄러는 요청 객체가 존재하지 않음)
			
			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			//HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes().getRequest())
			
			Member loginMember = (Member)req.getSession().getAttribute("loginMember");
			
			// ip : xxx.xxx.xxx.xxx (email : test01@naver.com )
			// 어디서든지 IP를 얻어오는 방식
			
			str+="ip : "+ getRemoteAddr(req);
			if(loginMember != null) {
				str += "(email : "+ loginMember.getMemberEmail()+")";
				//ip : 0:0:0:0:0:0:0:1(email : test01@gmail.com) == IP V4
			}else {
				str += "비로그인 회원";
			}
			
		}catch(Exception e) {
			str += "[스케줄러 동작]";
		}
		
		
		//여러개의 logger 정보를 한번에 문자열만 내보내기 위하여 누적.
		logger.info(str);
	};
	
	//ip 접근
	 public static String getRemoteAddr(HttpServletRequest request) {

	        String ip = null;

	        ip = request.getHeader("X-Forwarded-For");

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("Proxy-Client-IP"); 
	        } 

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("WL-Proxy-Client-IP"); 
	        } 

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("HTTP_CLIENT_IP"); 
	        } 

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	        }

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("X-Real-IP"); 
	        }

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("X-RealIP"); 
	        }

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getHeader("REMOTE_ADDR");
	        }

	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	            ip = request.getRemoteAddr(); 
	        }

	      return ip;
	   }
	
		
}
