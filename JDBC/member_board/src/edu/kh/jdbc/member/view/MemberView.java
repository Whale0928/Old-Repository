 package edu.kh.jdbc.member.view;


import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.member.model.service.MemberService;
import edu.kh.jdbc.member.model.vo.Member;

/**
 * @author LivingWhale
 *
 */
public class MemberView {//회원 관련 화면 입 / 출력

	private Scanner sc = new Scanner(System.in);
	
	//회원관련 서비스 제공 객체 선언 및 참조
	private MemberService service = new MemberService();
	
	/**
	 * 회원가입을 위한 메서드
	 */
	public void signUp() {
		System.out.println("[회원 가입]");

		try {
			String memberId = null;
			String memberPw = null;
			String memberPw2 = null;
			String memberName = null;
			char memberGender = ' ';
			
			while(true){ //중복아이디가 없을 경우 break;
				System.out.print("아이디 : ");
				 memberId = sc.next();
				/*아이디 중복검사(DB에 일치하는 아이디가 있으면 중복- > 다시 입력)*/
				int result = service.duplicateCheck(memberId);
				//result 는 중복이면 1 , 아니면 0 반환 예정
				
				if(result == 0 ) { //중복이 아닐 경우 아이디 생성 가능하다는 의미임으로 
					System.out.println("[사용 가능한 아이디입니다]");
					break;
				}else {
					System.out.println("[이미 사용중인 아이디입니다 다시 입력해주세요.]");
				}
			}
			
			//비밀번호 .비밀번호 확인
			while(true) {
				System.out.print("비밀번호 : ");
				memberPw = sc.next();
				
				System.out.print("비밀번호 확인 : ");
				memberPw2 = sc.next();
				
				if(memberPw.equals(memberPw2)) {
					break;
				}else {
					System.out.println("\n [비밀번호가 일치하지 않습니다, 다시 입력해주세요 ]\n");
				}
			}//비밀번호 입력 종료
			//이름을 입력 
			System.out.print("\n이름을 입력하세요 : ");
			memberName = sc.next();
			
			while(true) {
				System.out.print("\n성별(M / F) : ");
				memberGender = sc.next().toUpperCase().charAt(0);
				//String.toUpperCase();
				
					if(memberGender != 'M' && memberGender != 'F') {
							System.out.println("M 또는 F 만 입력해주세요");
					}else {
						break;
					}
			}
		
			//입력 받은 값을 하나의 객체에 저장
			Member signUpMember = new Member(memberId, memberPw, memberName, memberGender); 
			
			//회원 가입 서비스 호출 후 결과 반환
			// - 회원 가입은 == DB에 회원 정보를 삽입 ==INSERT (DML)
			// DML 구문 수행시 성공한 행의 개수가 반환됨 == int 형 변수로 결과 저장 
			int result = service.signUp(signUpMember);
			
			if(result > 0 ) System.out.println("\n[***회원가입에 성공하였습니다***]\n");
			else System.out.println("[회원가입 실패]");
			
		}catch(Exception e) {
			System.out.println("\n  << 회원 가입 중 예외 발생 >> \n");
			e.printStackTrace(); //예외 내용도 출력
		}
	}

	/**로그인을 위한 메서드
	 * @return loginMember (실패시 null 성공시 null X)
	 */
	public Member login(){
		Member loginMember = null;
		
		System.out.println("[로그인]");
		
		System.out.print("아이디 :");
		String memberId = sc.next();
		System.out.print("비밀번호 :");
		String memberPw = sc.next();
		//멤버 객체를 생성하여 입력 받은 값 세팅
		Member mem = new Member();
		mem.setMemberId(memberId); //setter을 이용해 초기화
		mem.setMemberPw(memberPw); //setter을 이용해 초기화
		
		try {			
			//로그인 서비스 수행후 Member타입의 객체를 반환 받는다.
			loginMember = service.login(mem);
			
			if(loginMember != null) { //참조하는 객체가 있는 경우 ==로그인 성공
				System.out.println("\n [***로그인 성공*** ]\n");
			}else {
				System.out.println("\n [아이디 혹은 비밀번호가 일치하지 않습니다.]");
			}
		}catch(Exception e) {
			System.out.println("로그인 과정에서 예외 발생");
			e.printStackTrace();			
		}
		
		return loginMember;
	}

	/**게스트 로그인을 위한 메서드
	 * 
	 */
	public Member GuestSignUp() {
		int ran = (int)(Math.random()*10000+1); 
		int result =0;
		String guestId = "guest"+ran ;
		String guestPw = "guest"+ran;
		String guestName = "guest"+ran;
		char guestGender = 'M';
		
		Member guest = new Member(guestId, guestPw, guestName, guestGender);
		
		try {
			result = service.signUp(guest);
			if (result > 0) {
				Member loginMember = service.login(guest);
				if(loginMember != null) return loginMember;
			} else 	{return null;}

		} catch (Exception e) {
			System.out.println("게스트 계정 로그인 실패");
			e.printStackTrace();
		}
		
		return  guest;
	}

	/**내 정보 조회
	 * @param loginMember
	 */
	public void myInfo(Member loginMember) {
		System.out.println("[내 정보 조회]");
		System.out.println("회원 번호:"+loginMember.getMemberNo());
		System.out.println("아이디:"+loginMember.getMemberId());
		System.out.println("이름:"+loginMember.getMemberName());
				if(loginMember.getMemberGender()=='M') {
					System.out.println("성별: 남성");
				}else {
					System.out.println("성별: 여성");
				}
	
		System.out.println("가입일:"+loginMember.getEnrollDate());
		
	}

	/**모든 회원 정보 조회를 위한 메서드
	 * 
	 */
	public void selectAll() {
		//DB에서 회원 정보를 조회(아이디 , 이름, 가입)
		//탈퇴한 회원은 제외,아이디 오름차순
		try {
		List<Member> memberList = service.selectAll();
		
		if(memberList.isEmpty()) {//조회 결과가 없을 경우 
			System.out.println("조회결과가 없습니다");
		}else {
			for(Member mem : memberList) {
				System.out.printf("%12s    %12s   %s\n",
					mem.getMemberId(),
					mem.getMemberName(),
					mem.getEnrollDate().toString());//date 자료형을 String으로 변환하기 위해 toString사용
				
			}
		}
		}catch(Exception e) {
			System.out.println("\n회원 정보 조회 과정중 예외 발생\n");
			e.printStackTrace();
		}
		
	}

	/**내 정보 수정을 위한 메서드 
	 * @param loginMember
	 */
	public void updateMyInfo(Member loginMember) {
		
		System.out.println("[내 정보 수정]");
		System.out.print("변경할 이름 :");
		String memberName = sc.next();
		
		System.out.print("변경할 성별(M/F) :");
		char memberGender = sc.next().toUpperCase().charAt(0);
		
		//입력 받은 값 + 로그인한 회원 번호를 하나의 Member 객체에 저장
		Member updateMember = new Member();
		updateMember.setMemberName(memberName);
		updateMember.setMemberGender(memberGender);
		updateMember.setMemberNo(loginMember.getMemberNo());
		
		try {
			//DML 수행 성공한 행의 결과를 반환 한다.
			int result = service.updateMyInfo(updateMember);
			
			if(result>0) {//수정 성공
				System.out.println("\n[회원 정보가 수정되었습니다.]\n");
				
				//DB에 수정된 내용과 현재 로그인한 회원의 정보를 일치시킨다
				//얕은 복사 : 참조 주소만 복사하여 같은 객체를 참조
				//- > 특징 :  복사된 주소만 참조 하여 수정하면 원본 객체가 수정된다
				loginMember.setMemberName(updateMember.getMemberName());
				loginMember.setMemberGender(updateMember.getMemberGender());
				
			}else {
				System.out.println("\n[회원 정보 수정 실패 하였습니다]\n");
			}
						
		}catch(Exception e) {
			System.out.println("정보 수정중 오류 발생");
			e.printStackTrace();
		}
	}


	/**비밀번호 수정을 위한 메서드
	 * @param loginMember
	 */
	public void updatePw(Member loginMember) {
		
		System.out.print("현재 비밀번호를 입력해주세요 :");
		String currntPw = sc.next();
		String newPw = null;
		
		loginMember.setMemberPw(currntPw);
		
		while(true) {
			System.out.print("변경할 비밀번호를 입력해주세요 :");
			newPw = sc.next();
			System.out.print("새 비밀번호 확인 :");
			String newPw2 = sc.next();
				if(newPw.equals(newPw2)) break;
				else System.out.println("\n [비밀번호가 일치하지 않습니다, 다시 입력해주세요 ]\n");
			}
		
		try {
			int result = service.updatePw(loginMember,newPw);
			
			if(result > 0)System.out.println("\n[비밀번호 변경이 완료되었습니다.]\n");
			else System.out.println("\n[비밀번호 변경 실패]\n");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("\n비밀번호 변경중 에러 발생\n");
		}
		
		
		
	}

	
	/**회원 탈퇴를 위한 메서드
	 * @param loginMember
	 */
	public Member secession(Member loginMember) {
		int result =0;
		System.out.println("[회원 탈퇴]");
		char cho =' ';
		System.out.print("비밀번호를 입력해주세요 :");
		loginMember.setMemberPw(sc.next());
		
		while(true) {
			System.out.print("\n정말로 탈퇴하시겠습니까?(Y/N) : ");
			cho = sc.next().toUpperCase().charAt(0);
					if(cho =='Y' ||   cho=='N') break;
					else System.out.println("올바른 값만 입력하세요");
		}
		
				try {
			if(cho=='Y') {
				result = service.secession(loginMember);
	
				if(result>0) {
					System.out.println("\n[탈퇴 되었습니다.]\n");
					loginMember =null;
				
				}else {
					System.out.println("\n[비밀번호가 일치하지 않습니다.]\n");
				}
			}else{
				System.out.println("탈퇴를 취소합니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("\n[탈퇴중 에러 발생]\n");
		}		
				return loginMember;
	}
}
