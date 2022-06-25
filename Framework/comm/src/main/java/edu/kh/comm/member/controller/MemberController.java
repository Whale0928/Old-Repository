package edu.kh.comm.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	//@RequestMapping(value="/login",method=RequestMethod.POST) 
	
	//로그인 기능
	@PostMapping("/login") //위와 동일한 코드
	public String login(Member member 
						,Model model
						,RedirectAttributes ra
						,HttpServletResponse resp
						,HttpServletRequest req
						,@RequestParam(value="saveId",required = false)String saveId){ //안넘어올수도 있다라는 것을 명시.
		// @ModelAttribute 생략 가능
		// -> 커맨드 객체라고 명칭함 (Model Attrbute가 생략된 상태에서 파라미터가 필드에 세팅된 객체 )
		logger.info("로그인 기능");
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
		if(loginMember != null) {
			model.addAttribute("loginMember",loginMember); //req.setAttribute와 동일
			
			//로그인 성공시 무조건 쿠키를 생성하는데
			// 단, 아이디 저장 체크 여부에 따라서 쿠키의 유지 시간을 조정
			//수명만 조절
			
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			
			if(saveId != null) { //아이디 저장이 체크 되었을 때.
				cookie.setMaxAge(60*60*24*365); // 얼마나 생존할지 초단위로 저장 (1년) 초 시 하루 년
			}else { //체크되지 않았을 때.
				cookie.setMaxAge(0); //생성 되자마자 사라짐 == 쿠키 삭제.
			}
			
			//쿠키가 적용될 범위(경로) 지정
			cookie.setPath(req.getContextPath());
			
			//쿠기를 응답시 클라이언트에게 전달
			resp.addCookie(cookie);
			
		}else {
			ra.addFlashAttribute("message","아이디 또는 비밀번호가 일치하지 않습니다.");
			
			
			//redirect시에도 request Scope로 세팅된 데이터가 유지될 수 있도록 하는 방법을
			//Spring에서 제공해준다.
			//RedirectAttributes()   :: 컨트롤러 매개변수에 작성하면 사용 가능.
		}
		
		return "redirect:/";
	}

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
	public String signUp() {
		return "member/signUp";
		}
			
	/**회원 가입
	 * @param inputMember
	 * @return
	 */
	@PostMapping("/signUp")
	public String signUp(Member inputMember,String[] memberAddress,RedirectAttributes ra){
		//커맨드 객체를 이용해서 입력된 회원 정보를 잘 받아옴
		// 단 , 같은 name을 가진 주소가 하나의 문자열로 합쳐서 세팅되어 있음
		// -> 도로명 주소에 " , "기호가 포함되는 경우가 있어 이를 구분자로 사용할 수 없음.
		
		//name이 memberAddress인 파라미터의 값을 모두 배열에 담아서 반환 
		inputMember.setMemberAddress(String.join(",,", memberAddress)); //배열을 하나의 문자열로 합치는 메서드 왼쪽에는 구분자를 지정할 수 있음.
		//만약 주소입력 안하면 null 대입
		if(inputMember.getMemberAddress().equals(",,,,")) {inputMember.setMemberAddress(null);}
		
		int result = service.signUp(inputMember);
		
		String message = null;
		String path = null;
		
		if(result>0) { //성공
			message = "회원가입 성공";
			path = "redirect:/";
		}else {
			message = "회원가입 실패";
			path = "redirect:/signUp";			
		}
		
		ra.addFlashAttribute("message",message);
		
		return path;
	}
		
	//이메일 중복 검사 Ajax
	@ResponseBody //Ajax 응답시 사용
	@GetMapping("/emailDupCheck")
	public int emailDupCheck(String memberEmail){
		// Controller 에서 반환되는 값을 forward 또는 Redirect를 위한 경로인 경우가 일반적이다.
		// -> 반환되는 값은 모드 경로로 인식되고 있다.
		//-> 비동기 통신은 위한 어노테이션이 존재
		// @ResponseBody : 반환되는 값을 ResponseBody( 응답의 몸통) 에 추가하여 이전 요청 주소로 돌아간다.
		// -> Controller에서 반환되는 값이 경로가 아니나 '값자체"로 인식된다.
		return service.emailDupCheck(memberEmail);
	}
	
	//닉네임 중복검사
	@ResponseBody //Ajax 응답시 사용
	@GetMapping("/nicknameDupCheck")
	public int nicknameDupCheck(String memberNickname){		return service.nicknameDupCheck(memberNickname);	}

}
