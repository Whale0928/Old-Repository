package edu.kh.oop.praction;

import java.util.Scanner;

public class MemberService {
	Scanner sc = new Scanner(System.in);
	// 속성

	private Member MemberInfo = null; // 회원 정보를 저장중인 필드
	private Member MemberLogin = null; // 로그인 중인 회원의 정보를 기억하는 필드
	// 기능

	public void mainDisplay() {
		int input = 0;

		do {
			System.out.println("============회원 정보 관리 프로그램 beta 0.1ver=============");
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.println("3.회원정보 조회");
			System.out.println("4.회원정보 수정");
			System.out.println("5.회원탈퇴");
			System.out.println("6.프로그램 종료");
			System.out.print("원하는 메뉴를 입력해주세요 : ");
			input = sc.nextInt();
			sc.nextLine();

			switch (input) {
			case 1:
				System.out.println(signUp());
				break;
			case 2:
				System.out.println(login());
				break;
			case 3:
				System.out.println(check());
				break;
			case 4:
				System.out.println(modify());
				break;
			case 5:
				System.out.println(delete());
				break;
			case 6:
				input = 0;
				break;
			default:
				System.out.println("다시 입력해주세요");
			}
		} while (input != 0);
	}// 화면을 출력하는 메소드

	public String signUp(){// 회원 가입을 하는 메소드

		System.out.print("아이디 입력  :");
		String userId = sc.next();

		System.out.print("비밀번호 입력  :");
		String userPw = sc.next();

		System.out.print("비밀번호 입력  :");
		String userPw2 = sc.next();

		System.out.print("이름 입력  :");
		String userName = sc.next();

		System.out.print("나이 입력  :");
		int userAge = sc.nextInt();

		System.out.print("성별 입력(남/여)  :");
		char userGender = sc.next().charAt(0);

		if (userPw.equals(userPw2)) {

			MemberInfo = new Member(userId, userPw, userName, userAge, userGender);
			// MemberInfo는 위에서 이미 Member클래스를 참조하여 선언 되었음으로
			// 추가적인 생성자가 필요없다.
			return "가입에 성공하였습니다";
		} else {
			return "가입에 실패하였습니다(비밀번호 불일치)";
		}
	}// 로그인 기능의 끝

	public String login() {// 로그인 하는 메소드
		if (MemberInfo == null) {
			return "회원정보가 없습니다";
		}

		System.out.println("****로그인****");

		System.out.print("아이디 입력 : ");
		String userId = sc.next();

		System.out.print("비밀번호 입력 : ");
		String userPw = sc.next();

		if (userId.equals(MemberInfo.getUserId()) && userPw.equals(MemberInfo.getUserPw())) {
			MemberLogin = MemberInfo;
			return "로그인 되었습니다 \n 환영합니다  " + MemberInfo.getUserName() + "회원님";

		} else {
			return "아이디 혹은 비밀번호가 일치하지 않습니다.";
		}

	}

	public String check() {// 회원정보 메소드
		if (MemberLogin == null) {
			return "로그인을 해주세요 .";
		}
		System.out.print("비밀번호를 입력해주세요 : ");
		String pwCheck = sc.next();
		if (pwCheck.equals(MemberInfo.getUserPw())) {

			System.out.println(" \n정보 ");
			System.out.println("__________________");
			System.out.println("ID : " + MemberInfo.getUserId());
			System.out.println("PassWord : " + MemberInfo.getUserPw());
			System.out.println("이름 : " + MemberInfo.getUserName());
			System.out.println("나이 : " + MemberInfo.getUserAge());
			System.out.println("성별 : " + MemberInfo.getUserGender());
			System.out.println("__________________");
			return "정보가 확인 되었습니다.";
		} else {
			return "비밀번호가 틀렸습니다";
		}
	}

	public String modify() {// 회원정보 수정 메소드

		if (MemberLogin == null) {
			return "로그인 후 이용가능합니다";
		}

		System.out.print("비밀번호를 입력해주세요 : ");
		String pwCheck = sc.next();

		if (pwCheck.equals(MemberInfo.getUserPw())) {

			System.out.println("\n============정보 변경============\n");
			System.out.println("1. 비밀번호 ");
			System.out.println("2. 이름 ");
			System.out.println("3. 나이 ");
			System.out.println("4. 성별 ");

			System.out.print("\n변경할 정보를 입력해주세요 : ");
			int change = sc.nextInt();

			System.out.println("\n========================\n");
			System.out.print("변경할 수치를 입력해주세요");
			switch (change) {
			case 1:
				MemberInfo.setUserPw(sc.next());
				break;
			case 2:
				MemberInfo.setUserName(sc.next());
				break;
			case 3:
				MemberInfo.setUserAge(sc.nextInt());
				break;
			case 4:
				MemberInfo.setUserGender(sc.next().charAt(0));
				break;
			default:
				System.out.println("올바르지 않은 수입니다.");
				break;
			}

			return "변경이 완료되었습니다.";
		} else {
			return "비밀번호가 올바르지 않습니다.";
		}

	}

	public String delete() {// 회원 데이터 삭제
		if (MemberLogin == null) {
			return "로그인 후 이용가능합니다";
		}
		System.out.print("정말로 탈퇴 하시겠습니까? (y/n): ");
		char input = sc.next().charAt(0);

		if (input == 'y' || input == 'Y') {
			MemberInfo = null;
			return "삭제 완료 되었습니다.";
		} else {
			return "잘생각하셨습니다.";
		}

	}
}
