package edu.kh.oarr.model.service;

import java.util.Scanner;

import edu.kh.oarr.model.vo.Member;

public class MemberService {
	private Scanner sc = new Scanner(System.in);

	// Member객체 배열
	// 회원 5명인 배열
	private Member memberArr[] = new Member[5];

	// 현재 로그인중인 회원의 정보를 저장하는 변수 선엉ㄴ
	private Member loginMember = null;

	// 메뉴 출력용 메소드
	public void displayMenu() {

		int menuNum = 0; // 메뉴 선택용 변수.

		do {// 한 번은 무조건 반복

			System.out.println("\n======= 회원 정보 관리 프로그램 v2 =======");
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.println("3.회원 정보 조회");
			System.out.println("4.회원 정보 수정");
			System.out.println("5.회원 검색(지역)");
			System.out.println("0.프로그램 종료");

			System.out.print("메뉴 입력 : ");
			menuNum = sc.nextInt();
			sc.nextLine();// 입력버퍼에 남은 개행문자 제거.

			switch (menuNum) {
			case 1:System.out.println(signUp());break;
			case 2:System.out.println(login());	break;
			case 3:System.out.println(selectMember());break;
			case 4:
				switch(updateMember()) {
				case -1:System.out.println("로그인 후 이용해 주세요");break;
				case  0:System.out.println("비밀번호가 일치하지 않습니다");break;
				case  1:System.out.println("정보 수정 완료");break;
				};break;
			case 5:searchRegion();break;
			case 0:
				System.out.println("\n 프로그램을 종료합니다....");
				break;
			default:
				System.out.println("\n※※잘못 입력 하였습니다 다시 입력하세요※※ . ");
			}

		} while (menuNum != 0); // menuNum이 0이면 반복 종류
	}

	
	//회원 가입 기능
	public String signUp() {
		System.out.println("\n___________회원 가입___________");

		// 객체 배열(memberArr)에 가입한 회원 정보를 저장할 예정
		// 빈공간에 회원 정보를 저장할 수 있는지 확인하고
		// 빈공간의 인덱스 번호 얻어오기 -> 새로운 메소드로

		int index = emptyIndex();// memberarr에서 비어있는 인덱스 위치를 가져옴.

		System.out.println("현재 회원 수 : " + index);
		if (index == -1) {// 가입 못하는 경우
			return "가입할 수 없습니다(인원수 초과)";
		} else {
			System.out.print("아이디 : ");
			String memberId = sc.next();

			System.out.print("비밀번호 : ");
			String memberPw = sc.next();

			System.out.print("비밀번호 확인: ");
			String memberPw2 = sc.next();
			sc.nextLine();
			
			System.out.print("이름 : ");
			String memberName = sc.nextLine();

			System.out.print("나이 : ");
			int memberAge = sc.nextInt();
			sc.nextLine();

			System.out.print("지역 : ");
			String region = sc.next();

			if (memberPw.equals(memberPw2)) {
				memberArr[index] = new Member(memberId, memberPw2, memberName, memberAge, region);
				return "회원가입 성공";
			} else {
				return "회원가입 실패(비밀번호 불일치)";
			}
		}

	}
	//현재 가입자 수 체크 기능 
	// memberArr의 비어있는 인덱스 번호 반환하는
	// 만약 비어 있는 인덱스가 없다면 -1 반환
	public int emptyIndex() {
		// memberArr 배열의 0번인덱스부터 끝까지 검색해서
		// 참조하는 값이 null일 경우 인덱스를 반화
		for (int i = 0; i < memberArr.length; i++) {
			if (memberArr[i] == null) {
				return i;
			}
		}
		return -1;
	}

	//로그인 기능(메소드)
	public String login() {
		System.out.println("\n===========로그인==========");
		
		System.out.print("아이디 : ");
		String memberId = sc.next();

		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		//1) memberArr 배열 내 요소를 순서대로 접근 하여 null이 아닌지 확인
		
		for(int i=0; i<memberArr.length;i++) {
			if(memberArr[i] != null) { //회원 정보가 있을 경우
				//2) 회원 정보(memberArr[i])의 아이디 비밀번호와 
				//입력받은 입력받은 아이디 , 비밀번호가 같은지 확인.
				if( memberArr[i].getMemberId().equals(memberId) &&
				    memberArr[i].getMemberPw().equals(memberPw)) { //아이디와 비밀번호를 비교
						//3) 로그인 회원 정보객체(Member)을 참조하는 변수 loginMember에
						//현재 접근중인 memberArr[i]번째 요소에 저장된 주소를 얕은 복사
					loginMember = memberArr[i];
					
					return "환영합니다 "+memberArr[i].getMemberName()+"회원님 .";
						}	
				
			}
		}//for문 끝
		//로그인 성공 실패 여부에 따라 결과값을 반환 
		
				return "로그인 실패";
		
		
		
	}
	
	public MemberService() {// 기본 생성자
		//memberArr 배열 0 , 1 , 2 인덱스 초기화
		memberArr[0] = new Member("user01", "123", "홍길동", 30, "대구");
		memberArr[1] = new Member("user02", "123", "김소용", 21, "평택");
		memberArr[2] = new Member("user03", "123", "박드래곤", 211, "평택");
	}
	
	
	//회원 정보 조회 메소드
	public String selectMember() {
		
		System.out.println("\n========== 회원 정보 조회 ==========");
		
		// 1) 로그인 여부 확인 -> 필드 loginMember가 참조하는 객체가 있는지 확인
		if(loginMember == null) { 
			return "로그인 후 이용 해주세요.";
		}
		
		// 2) 로그인이 되어있는 경우
		//    회원 정보를 출력할 문자열을 만들어서 반환(return)
		//    (단, 비밀번호는 제외)
		String str = "이름 : " + loginMember.getMemberName();
		str += "\n아이디 : " + loginMember.getMemberId();
		str += "\n나이 : " + loginMember.getMemberAge() + "세";
		str += "\n주소 : " + loginMember.getRegion();
		return str;
	}

	
	//회원정보 수정  메소드
	public int updateMember() {
		System.out.println("\n*************회원 정보 수정*************");

		if (loginMember == null) {
			return -1;
		} 
		System.out.print("수정할 이름 입력 : ");
		String inputName = sc.next();
		System.out.print("수정할 나이 입력 : ");
		int inputAge = sc.nextInt();
		System.out.print("수정할 주소 입력 : ");
		String inputRegion = sc.next();
		sc.nextLine();
		
		System.out.print("비밀번호 확인: ");
		String inputPw = sc.next();

		if (inputPw.equals(loginMember.getMemberPw())) {
			loginMember.setMemberName(inputName);
			loginMember.setMemberAge(inputAge);
			loginMember.setReigon(inputRegion);
			return 1;
		} else {
			return 0;
		}
	}


	//회원 검색(지역) 메소드
	public void searchRegion() {
		System.out.println("************회원 검색************");
		
		System.out.print("\n 검색 하고 싶은 지역을 입력하세요 : ");
		String inputRegion = sc.next();
		boolean flag = true;
		//1) memberArr배열에 모든 요소 순차 접근
		for(int i=0;i<memberArr.length;i++) {
			//2) memberArr[i]번째 요소가 null인 경우 반복 종료
			if(memberArr[i]==null){
				break;
			}
			//3) memberArr[i] 요소에 저장된 지역 값이
			//입력 받은 지역과 같은 경우
			String str="";
			if(inputRegion.equals(memberArr[i].getRegion())) {
				System.out.printf("아이디 : %s  이름 : %s",memberArr[i].getMemberId(),memberArr[i].getMemberName());
				System.out.println();
				flag =false;
			}
		}	
		//검색 결과가 없을 경우
		if(flag) {
			System.out.println("해당 지역에 인원이 없습니다.");			
		}
	}

}


