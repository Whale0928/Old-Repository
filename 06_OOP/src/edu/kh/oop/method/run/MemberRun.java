package edu.kh.oop.method.run;

import edu.kh.oop.method.model.service.MemberService;

public class MemberRun {

	public 			 static    void     main(String[] args) {
//  [접근제한자]	[예약어]  반환형    메소드명(매개변수){ ... }

		MemberService service = new MemberService();
		//MemberService 의 기본 생성자가 작성되어 있지 않지만
		//문제 없이 사용가능
		
		//왜?  :  컴파일러가 코드를 번역할 때
		//	클래스 내부에 생성자가 하나도 없다면. 
		//	자동으로 기본생성자를 추가해줌.
		//생성자가 하나라도 있으면 자동생성을 하지 않는다.
		
		service.displayMenu();
		
		
	}

}
