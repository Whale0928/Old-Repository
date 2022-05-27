package edu.kh.oop.method.model.service;

import java.util.Scanner;

import edu.kh.oop.method.model.vo.Member;

public class MemberService { // 클래스
	// 속성 (필드)
	private Scanner sc = new Scanner(System.in);
//	system.in : 자바에서 기본적으로 지정해둔 입력 장치(키보드)
//	입력받은 값은 내부에서 저장시킬거니 따로 get set을 사용안해도 됨

	private Member memberInfo = null; // 가입할 회원의 정보를 저장할 변수;
	private Member loginMember = null; // 로그인한 회원의 정보를 저장할 변수.

	// 기능(생성자 , 메소드)

	public MemberService() {
	} // 이자리에 기본생성자 있다.

//	***메소드 작성법***
//	[접근제한자]   [예약어]      반환형        메소드명   ( [매개변수])
//  public         	static     기본 자료형
//	protected		final  	     참조형
//	(default)      abstract		  void
//	private		  static final

//   ***반환형 void***
//	반환(return)이란  : 메소드 수행후 호출부로 돌아가느것
//  반환 값 이란      : 돌아가면서 가져갈 결과 값
//  반환 형 이란      : 변환 값의 자료형
//  -> void는 돌려보낼 값이 없다.

	public void displayMenu() { // 메뉴 화면 출력 기능

		int menuNum = 0; // 메뉴 선택용 변수.

		do {// 한 번은 무조건 반복

			System.out.println("======= 회원 정보 관리 프로그램 v1 =======");
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.println("3.회원 정보 조회");
			System.out.println("4.회원 정보 수정");
			System.out.println("0.프로그램 종료");

			System.out.print("메뉴 입력 : ");
			menuNum = sc.nextInt();
			sc.nextLine();// 입력버퍼에 남은 개행문자 제거.

			switch (menuNum) {
			case 1:
				System.out.println(signUp());
				break;
			// 같은 클래스 내부에 있는 필드, 메소드는 이름만 불러도 호출 가능.
			// signup메소드를 호출하여 실행후 반환 받은 값을 출력.
			case 2:
				System.out.println(login());
				break;
			case 3:
				System.out.println(selectMember());
				break;
			case 4:
				int result = updateMember(); //회원 정보 수정 메소드 수행 후 결과값을 result에 대입.
				
				switch(result) {
				case -1:System.out.println("로그인후 이용해주세요");break;
				case 0:	System.out.println("회원 정보 수정 실패 (비밀번호 불일치)");	break;
				case 1:System.out.println("회원 정보 수정 성공");break;
				}
				
				break;
			case 0:
				System.out.println("프로그램을 종료합니다...");
				break;

			default:
				System.out.println("잘못 입력 하였습니다 다시 입력하세요 . ");
			}

		} while (menuNum != 0); // menuNum이 0이면 반복 종류

	}

	// 회원 가입 기능
	public String signUp() {
		// 해당 메소드가 끝나고 호출한 곳으로 돌아갈 때
		// void : 반환할 값이 없다.
		// String : String 자료형 값을 가지고 돌아간다.

		System.out.println("\n***** 회원가입 *****");

		System.out.print("아이디 : ");
		String memberId = sc.next();

		System.out.print("비밀번호 : ");
		String memberPw = sc.next();

		System.out.print("비밀번호 확인: ");
		String memberPw2 = sc.next();

		System.out.print("이름 : ");
		String memberName = sc.next();

		System.out.print("나이 : ");
		int memberAge = sc.nextInt();
		sc.nextLine();
		// 비밀번호 , 비밀번호 확인이 일치하면 회원 가입
		// 일치하지않으면 회원 가입 실패

		if (memberPw.equals(memberPw2)) {// 일치하는 경우
			// 입력 받은 정보를 이용해서 member객체를 생성한 후
			// 생성된 된 객체의 주소를 필드에 있는 memberinfo에 대입

			memberInfo = new Member(memberId, memberPw, memberName, memberAge);
			// memberInfo도 member타입으로 선언되었기 때문에 대입이 가능하다.
			return "회원가입 성공 !!";
			// return : 현재 메소드를 종료하고 호출한 곳으로 돌아감
			// return 값 ; : 호출한 곳을 돌아갈 때 값을 가지고 돌아간다.

		} else { // 일치하지 않는 경우
			return "회원가입 실패 ㅠㅠ";

		}
	}

	// 로그인 기능
	public String login() {

		// 회원 가입을 했는지 부터 확인.
		if (memberInfo == null) {
			return "가입부터 하세요";
		}
		System.out.println("\n***************로그인***************8");
		System.out.print("아이디 : ");
		String memberId = sc.next();

		System.out.print("비밀번호 : ");
		String memberPw = sc.next();

		// 가입정보 (memberInfo가 참조하는 Member 객체)에서
		// 저장된 아이디,비밀번호가 입력된 아이디 비밀번호와 같으면 로그인
		// 일치하지 않으면 실패문구 return ;

		if (memberId.equals(memberInfo.getMemberId()) && memberPw.equals(memberInfo.getMemberPw())) {
			// 입력받은 memberId와
			// memberInfo필드에서 참조중인 memberId객체의 memberId가 같은지 확인
			loginMember = memberInfo;
			// 참조형 = Member객체 주소(얕은 복사)

			// 회원 가입정보를 로그인 정보에 대입해서 어떤 회원이 로그인 중인지 구분할 수 있게된다.

			return loginMember.getMemberName() + "님 환영합니다";
		} else {
			return "아이디 또는 비밀번호가 일치하지 않습니다";
		}
	}

	// 회원 정보 조회 기능
	public String selectMember() {
		System.out.println("*************회원 정보 조회*************");
		// 로그인 여부 확인 - > 필드 loginMember가 참조하는 객체가 있는지 확인한다.
		if (loginMember == null) {
			return "로그인 후 이용해주세요";
		}

		// 로그인이 되어 있는 경우

		String str = "이름 : " + loginMember.getMemberName();
		// String edu.kh.oop.method.model.vo.Member.getMemberName()
		// 반환형 메소드의 위치

		str += "\n아이디 : " + loginMember.getMemberId();
		str += "\n나이: " + loginMember.getMemberAge() + "세";
		return str;
	}

	// 회원 정보 수정 기능(update,modify,alter..등)
	public int updateMember() {
		System.out.println("*************회원 정보 수정*************");

		// 1.로그인 여부 판별
		// 로그인이 되어 있지 않으면 -1 반환

		if (loginMember == null) {
			return -1;
		} // 로그인이 안되면 -1반환

		// 2.수정할 회원 정보 (이름,나이)
		System.out.print("수정할 이름 입력 : ");
		String inputName = sc.next();
		System.out.print("수정할 나이 입력 : ");
		int inputAge = sc.nextInt();
		sc.nextLine();

		// 3.비밀번호 입력받아서
		// 로그인한 회원의 비밀 번호와 일치한지 확인
		System.out.print("비밀번호 확인: ");
		String inputPw = sc.next();

		if (inputPw.equals(loginMember.getMemberPw())) {
			// 4.비밀번호가 같을 경우
			// 로그인한 회원의 이름, 나이 정보를 입력받은 값으로 변경.
			// 1을 반환

			loginMember.setMemberName(inputName);
			loginMember.setMemberAge(inputAge);
			// 입력받은 이름과 나이를 Member객체의 필드 memberName/ memberAge에 대입
			return 1;
		} else {
			return 0;
		}

	}
}
