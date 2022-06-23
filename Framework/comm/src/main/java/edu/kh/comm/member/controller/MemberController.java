package edu.kh.comm.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.kh.comm.member.model.service.MemberService;
import edu.kh.comm.member.model.service.MemberServiceImpl;
import edu.kh.comm.member.model.vo.Member;

//순수 자바 기반 프로그래밍
//POJO 기반 프레임 워크 : 외부 라이브러리 상속 X

//class  : 설계도 ( ? )
//-> 객체로 생성 되어야지 기능 수행이 가능하다.

//--> IOC(제어의 역전 , 객체의 생명 주기를 스프링이 관리한다) 이용하여 객체 생성
//  ** 이 때 , 스프링이 생성한 객체를 Bean이라고 명명한다.

//bean등록 == 스프링이 객체로 만들어서 가지고있어라
//@Component //해당 클래스를 Bean으로 등록하라 (WebServlet과 같은 느낌??) (Annotation : 프로그램에게 알려주는 주석)
@Controller //생성된 클래스가 Bean으로 등록하면서 Controller인것을 명시.
@RequestMapping("/member") //comm/member이하의 모든 요청을 처리하는 컨트롤러로 명시/등록

@SessionAttributes({"loginMember"}) //Model에 추가된 값의 Key를 작성하면 어노테이션의 작성된 값이 같으면 
								 	//해당 값을 sessionScope로 이동 시키는 역할 
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//스프링이 알아서 service에 필요한 객체를 대입한다.
	@Autowired // : bean으로 등록된 객체 중 타입이 같거나 or 상속관계인 Bean을  - Spring이 주입해주는 역할
	private MemberService service; // -> 의존성 주입 (DI , Dependenc Injecting)
	
	
	//@Controller : 요청/응답을 제어 역할을 하는 클래스
	
/*@RequestMapping : 클라이언트 요청 (url)에 맞는 클래스 or 메서드를 연결시켜주는 어노테이션
 * [위치에 따른 해석]
 * - 클래스 레벨 : 공통 주소를 나타내는 Front Controller 패턴 지정
 * - 메서드 레벨 : 공통 주소외 나머지 주소 (상세주소?)가 된다.
 * 
 * 단, 클래스 레벨에서 @RequestMapping이 존재하지 않는다면
 * - 메서드 레벨:단독요청 처리 주소.
 * - 
 * */

//Argument Resolver 라는 매개변수를 유연하게 처리해주는 해결사 / 스프링에 내장된 기능
//https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments 

//로그인 처리
//	@RequestMapping("/login")
//	public String login(HttpServletRequest req){
//		//요청시 파라미터 얻어오는 방법 1번

//		//-> HttpServletRequest 이용 
//		String id = req.getParameter("inputEmail");
//		String pw = req.getParameter("inputPw");
//		logger.debug("inputEmail: "+id);
//		logger.debug("inputPw:"+pw);
//		logger.info("로그인 요청 수행 완료!!!");
//		return "redirect:/";
//	}
	//----------------------------------------------------------------------------------------------------------
	//요청시 파라미터를 얻어오는 방법 2번
	//@RequestParam 어노테이션 사용
	//@RequestParam("name 속성값") 자료형 변수명
	// -클라이언트 요청 시 같이 전달된 파라미터를 변수에 저장
	//어떤 파라미터를 변수에 저장할지는 name 속성값을 이용해 저장
	

	//매개변수 파싱을 자유롭게 진행 할 수 있다.
	//Ex) String -> int 

	//[속성]
	// value        : input 태그의 name 속성 (속성을 하나도 적지 않을 경우의 기본값 == Default)
	
	// required     : 입력된 name속성 값이 필수적으로 parameter에 포함되어야 하는지를 지정
	//  				required = true / required =false 
	// Http Status 400 : 잘못된 요청(Bad Request) : 파라미터가 존재하지 않아 요청이 잘못됨
	//-> required = false일때 null로 반환됨

	// defaultValue : required = false 인 상태에서 파라미터가 존재하지 않을 경우의 값을 지정
	
	//requestParam을 생략하지만 파라미터를 얻어오는 방법
	//네임 속성값과 파라미터를 저장할 변수명을 동일하게 한다

//		@RequestMapping("/login")
//		public String login(@RequestParam("inputEmail")String id,
//							@RequestParam("inputPw")String pw,
//							@RequestParam(value="inputName",required=false)String name,
//							@RequestParam(value="inputName",required=false,defaultValue="김길동")String defaultVal)
//		{
//		public String login(int inputEmail,String inputPw,@RequestParam(value="inputName",required=false,defaultValue="김길동")String defaultVal){
//			
//			logger.debug("id:"+inputEmail);
//			logger.debug("pw:"+inputPw);
//			logger.debug("name : "+defaultVal);
//			
//			logger.debug(inputEmail+100+"");
//			
//			return "redirect:/";
//		}
	
	//----------------------------------------------------------------------------------------------------------
	
	
	//[작성법에 따른 해석]
	/*
	 * 1) @RequestMapping("url")
	 * 	 요청방식이 GET이든 POST이든 모든 URL을 처리
	 * 
	 * 2) @RequestMapping(value="url",method=requestMethod.GET | POST)
	 *	요청 방식에 따라 요청 처리 (GET 인 경우 POST인 경우)
	 * 
	 * 
	 * 메서드 레벨에서 GET ? POST 방식을 구문하여 매핑할 경우
	 * GetMapping(url) 
	 * PostMapping(url)
	 *  을 사용하면 된다
	 * 
	 * */
	
	
	// 요청 시 파라미터를 얻어오는 방법 3
	// -> @ModelAttribute 어노테이션 사용
	
	// [ @ModelAttribute를 매개변수에 작성하는 경우 ]
	// @ModelAttribute Vo타입 변수명 
	//	VO에 일치하는 name속성이 있는 해당하는 VO에 대입한다 (미친 효율)
	
	// **** @ModelAttribute를 이용해서 객체에 값을 직접 담는 경우의 주의사앙 ****
	// - VO 기본 생성자.
	// - VO 필드에 대한 Setter 필요
	// 위 두개가 반드시 필요!!!
	
	// -Getter는 EL 구문을 사용할대 사용.
	
	
	//@RequestMapping(value="/login",method=RequestMethod.POST) 
	@PostMapping("/login") //위와 동일한 코드
	public String login(Member member 
									,Model model){
		// @ModelAttribute 생략 가능
		// -> 커맨드 객체라고 명칭함 (Model Attrbute가 생략된 상태에서 파라미터가 필드에 세팅된 객체 )
		logger.info("로그인 기능 수행됨");
		// id+pw가 일치하는 service호출후 결과 반환받기.
		Member loginMember = service.login(member);
		
		/* Model : 데이터를 Map(K:V) 형태로 담아 전달하는 용도의 객체.
		 * -> request , session을 대체하는 객체.
		 * 
		 * -기본 Scope : request 
		 * 
		 * -session scope 으로 변환하고 싶은 경우
		 *  클래스 레벨로 @SessionAttributes를 작성하면 된다
		 *  
		 * */
		
		//SessionAttribute 미작성시 req scope
		
		model.addAttribute("loginMember",loginMember); //req.setAttribute와 동일
		
		
		return "redirect:/";
	}
	//${contextPath}/member/logout
	//로그아웃 기능
	@GetMapping("/logout")
	public String logout(HttpSession session,SessionStatus status) {
		//로그아웃 == 세션을 없애는 것.
		
		//@SessionAttributes를 이용해 session Scope에 배치된 데이터는
		// SessionStatus라는 별도의 객체를 이용해야지 없앨수 있다.  H201603034
		logger.info("로그아웃 수행됨");
		//session.invalidate(); 스프링의 세션과 다른 세션이라고 생각해야 한다
		status.setComplete(); //세션이 할일을 다 했다 그니깐 죽어라
		
		return "redirect:/";
	}
	
	//회원가입 페이지로 이동
	@GetMapping("/signUp")
	public String signUp() {		return "member/signUp";		}
	

	
}
