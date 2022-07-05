package edu.kh.comm.common.scheduling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//특정 시간마다 스프링이 알아서 코드를 수행할 수 있도록
// Bean으로 등록 
@Component
public class SchedulingTest {
/*
 * @Scheduled
 * 
 * * Spring에서 제공하는 스케줄러 - 스케줄러 : 시간에 따른 특정 작업(Job)의 순서를 지정하는 방법.
 * 
 * 설정 방법 
 * 1) servlet-context.xml -> Namespaces 탭 -> task 체크 후 저장 
 * 2) * servlet-context.xml -> Source 탭 -> <task:annotation-driven/> 추가
 * 
 * 
 * 
 *
 * @Scheduled 속성 - fixedDelay : 이전 작업이 끝난 시점으로 부터 고정된 시간(ms)을 설정. - fixedRate :
 * 이전 작업이 수행되기 시작한 시점으로 부터 고정된 시간(ms)을 설정.
 * 
 * * cron 속성 : UNIX계열 잡 스케쥴러 표현식으로 작성 - cron="초 분 시 일 월 요일 [년도]" - 요일 : 1(SUN) ~
 * 7(SAT) ex) 2019년 9월 16일 월요일 10시 30분 20초 cron="20 30 10 16 9 2" // 연도 생략 가능
 * 
 * - 특수문자 * : 모든 수. - : 두 수 사이의 값. ex) 10-15 -> 10이상 15이하 , : 특정 값 지정. ex) 3,4,7
 * -> 3,4,7 지정 / : 값의 증가. ex) 0/5 -> 0부터 시작하여 5마다 ? : 특별한 값이 없음. (월, 요일만 해당) L :
 * 마지막. (월, 요일만 해당)
 * 
 * * 주의사항 - @Scheduled 어노테이션은 매개변수가 없는 메소드에만 적용 가능.
 */
	private Logger logger = LoggerFactory.getLogger(SchedulingTest.class);
	
	//fixedRate  시작하자 마자 카운트 시작 1초 시작 4초 대기 
	//fixedDelay 1초 수행 5초 카운트 
	//@Scheduled(fixedDelay = 50000)
	
	//cron="초 분 시 일 월 요일 [년도]" - 요일 : 1(SUN) ~
	//@Scheduled(cron = "0 * * * * *")
	/*
	 * @Scheduled(cron = "0 0 * * * *") public void test(){
	 * logger.info("모든 시간의 0초 마다 출력");
	 * 
	 * }
	 */
		

}
