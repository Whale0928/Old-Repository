package edu.kh.rental.member.view;
import java.util.List;
import java.util.Scanner;

import edu.kh.rental.member.model.service.ClientService;
import edu.kh.rental.member.model.vo.Client;
import edu.kh.rental.member.model.vo.Place;
import edu.kh.rental.member.model.vo.Reservation;

public class ClientView {
	private Scanner sc = new Scanner(System.in);
	
	ClientService service = new ClientService();
	

	/**회원 가입
	 * 
	 */
	public void signUp() {
		System.out.println("[회원 가입]");

		try {
			String memberId = null;
			String memberPw = null;
			String memberPw2 = null;
			String memberName = null;
			String phone = null;
			
			while(true){ //중복아이디가 없을 경우 break;
				System.out.print("아이디 : ");
				 memberId = sc.next(); 
				 
				int result = service.duplicateCheck(memberId);
				
				if(result == 0 ) { 
					System.out.println("[사용 가능한 아이디입니다]");
					break;
				}else {
					System.out.println("[이미 사용중인 아이디입니다 다시 입력해주세요.]");
				}
			}
			

			while(true) {
				System.out.print("비밀번호 : ");
				memberPw = sc.next();
				
				System.out.print("비밀번호 확인 : ");
				memberPw2 = sc.next();
				
				if(memberPw.equals(memberPw2)) {
					break;
				}else {
					System.out.println("[비밀번호가 일치하지 않습니다, 다시 입력해주세요 ]");
				}
			}
			
			
			System.out.print("이름을 입력하세요 : ");
			memberName = sc.next();
			
			while(true) {
				System.out.print("전화번호를 입력하세요 (숫자만 입력하세요) :");
				phone = sc.next();
					if(phone.length() == 11) {
						break;
					}
			}
			Client signUpMember = new Client(memberId, memberPw, memberName,phone);
		
			int result = service.signUp(signUpMember);
			
			if(result > 0 ) System.out.println("\n[***회원가입에 성공하였습니다***]\n");
			else System.out.println("[회원가입 실패]");
			
		}catch(Exception e) {
			System.out.println("\n  << 회원 가입 중 예외 발생 >> \n");
			e.printStackTrace(); //예외 내용도 출력
		}
	}

	/**로그인
	 * @return
	 */
	public Client login() {
		Client loginMember = null;
		
		System.out.println("[로그인]");
		
		System.out.print("아이디 :");
		String clientId = sc.next();
		System.out.print("비밀번호 :");
		String clientPw = sc.next();
		//멤버 객체를 생성하여 입력 받은 값 세팅
		Client mem = new Client();
		
		mem.setClientId(clientId);
		mem.setClientPw(clientPw);
		
		try {			
			loginMember = service.login(mem);
			
			if(loginMember != null) { 
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

	/**지점 조회
	 * 
	 */
	public void select() {
		try {
			List<Place> listUp = service.select();
			
			if(listUp.isEmpty()) { 
				System.out.println("조회결과가 없습니다");
			}else {
				for(Place temp : listUp) {
					System.out.printf("%2d  . %4s\n",
							temp.getPlaceNo(),temp.getPlaceName());
					}
				System.out.print("지점을 입력하세요 : ");
				int num = sc.nextInt();
			
				List<Reservation> timeList = service.scheduleList(num);
				if(timeList.isEmpty()) { 
					System.out.println("조회결과가 없습니다");
				}else {
					System.out.println("[예약 불가 일정]");
					for(Reservation temp : timeList) {
						System.out.printf("[ %d일 ]\n",temp.getResDate());
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**예약 신청
	 * @param ctLogin
	 */
	public void resRequest(Client ctLogin) {
		int result = 0;
		int count = 0;
		int num = 0;
		int num2 = 0;
		try {
		
			Reservation res = new Reservation(); 
		
		List<Place> listUp = service.select();
			
			if(listUp.isEmpty()) { 
				System.out.println("조회결과가 없습니다");
			}else {
				for(Place temp : listUp) {
					System.out.printf("%2d  . %4s\n",
							temp.getPlaceNo(),temp.getPlaceName());
					}
				System.out.print("지점을 입력하세요 : ");
				num= sc.nextInt();
			
				List<Reservation> timeList = service.scheduleList(num);
				if(timeList.isEmpty()) { 
					System.out.println("조회결과가 없습니다");
				}else {
					System.out.println("[예약 불가 일정]");
					for(Reservation temp : timeList) {
						System.out.printf("[ %d일 ]\n",
								temp.getResDate());
						}
					}
				}
		List<Reservation> timeList = service.scheduleList(num);
		while(true) {
			System.out.print("\n예약일정을 입력하세요 :");
			num2 = sc.nextInt();
			
			for(Reservation temp : timeList) {
				if(num2==temp.getResDate()) {
					count++;		
				}
			}
			if(count == 0) {
				System.out.println("예약 가능한 일정입니다 ");
				break;
			}else {
				System.out.println("이미 예약된 날짜입니다. 다시 입력해주세요");
			}
		}
		
		res.setClientNo(ctLogin.getClientNo());
		res.setPlaceNo(num);
		res.setResDate(num2);
		result = service.resUpdate(res);
		
			if(result > 0) {
				System.out.println("\n[신청이 되었습니다]\n");
			}else {
				System.out.println("[신청중 오류 발생]");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("신청중 오류 발생.");
		}
		
		
	}

	/**예약조회
	 * @param ctLogin
	 */
	public void selectMy(Client ctLogin) {
	try {
		List<Reservation> res = service.selectMy(ctLogin);
		
		if(res.isEmpty()) {
			System.out.println("예약정보가 없습니다");
		}else {
			
		for(Reservation temp:res) {
			System.out.printf("%s님의 예약정보 [%5s : %2d일] \n"
						,temp.getClientName()
						,temp.getPlaceName()
						,temp.getResDate());			
			}
		}
			}catch(Exception e) {
				e.printStackTrace();
			}
		
	}
	
	
}
