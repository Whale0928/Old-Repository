package edu.kh.oop.praction;

public class MemberRun {

	public static void main(String[] args) {
			MemberService service = new MemberService();
		//  MemberService를 참조하는 service를 선언한다.
			service.mainDisplay(); //메인 디스플레이를 실행
		// 참조한 service에서 mainDisplay라고 하는 메소드를 참조해와 실행한다.
	}

}
