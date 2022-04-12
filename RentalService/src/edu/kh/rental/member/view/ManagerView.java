package edu.kh.rental.member.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.rental.member.model.service.ManagerService;
import edu.kh.rental.member.model.vo.Client;
import edu.kh.rental.member.model.vo.Manager;

public class ManagerView {
	Scanner sc = new Scanner(System.in);
	
	ManagerService service = new ManagerService();
	
	
	/**로그인
	 * @return
	 */
	public Manager login() {
		Manager loginMember = null;
		
		System.out.println("[로그인]");
		
		System.out.print("아이디 :");
		String managerId = sc.next();
		System.out.print("비밀번호 :");
		String managerPw = sc.next();
		//멤버 객체를 생성하여 입력 받은 값 세팅
		Manager mem = new Manager();
		
		mem.setManagerId(managerId);
		mem.setManagerPw(managerPw);
		
		try {			
			loginMember = service.login(mem);
			
			if(loginMember != null) { 
				System.out.println("\n [***관리자 로그인 성공*** ]\n");
				System.out.println(loginMember);
				
			}else {
				System.out.println("\n [아이디 혹은 비밀번호가 일치하지 않습니다.]");
			}
		}catch(Exception e) {
			System.out.println("로그인 과정에서 예외 발생");
			e.printStackTrace();			
		}
		
		return loginMember;
	}

	/**관리중인 지점 조회
	 * @param managerLogin
	 */
	public void selectShop(Manager managerLogin) {
		
		try {
			
		List<Manager> shopList = service.selectShop(managerLogin);
		
		if(shopList.isEmpty()) {
			System.out.println("관리중인 지점이 없습니다");
		}else {
			
			for(Manager m : shopList) {
				System.out.printf("[%d.%s  - %20s]\n",
						m.getPlaceNo(),m.getPlaceName(),m.getPlaceAddress());
			}
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	}

	/**지점 관리메뉴
	 * @param managerLogin
	 */
	public void superintend(Manager managerLogin) {
		int menuNum = -1;
		System.out.println("\n********** 지점 관리 프로그램 **********\n");
		System.out.println("[1. 예약 고객 조회]");
		System.out.println("[2. 예약 초기화]");
		System.out.println("[9. 로그아웃]");
		System.out.print("메뉴를 입력해주세요 >> ");
		
		menuNum = sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		switch (menuNum) {
		case 1:selectClient(managerLogin.getManagerNo());break; 
		case 2:endSet(managerLogin);break;
		case 9:managerLogin = null;break;
		case 0:
			System.out.println("\n프로그램을 종료합니다.");
			managerLogin = null;
			break;
		default:
			System.out.println("메뉴에 작성된 번호를 입력해주세요.");
		}
	}

	/**관리중인 지점 예약인원 조회
	 * @param managerNo
	 */
	private void selectClient(int managerNo) {
		List<Client> ctList = null;
				
		try {
			
		
		 ctList = service.selectClient(managerNo);
		
		 if(ctList.isEmpty()) {
			 System.out.println("예약자가 없습니다");
		 }else {
			 System.out.println("일정 [고객명]");
			 for(Client c : ctList) {
				 System.out.printf("%d. %d일 [%s]  : %s\n"
					,c.getClientNo(),c.getResDate(),c.getName(),c.getPlaceName());
			 }
		 }
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예약자 조회중 오류");
		}
	}

	/**관리중인 지점 조회후 지점 조기화
	 * @param managerLogin
	 */
	private void endSet(Manager managerLogin) {
		selectShop(managerLogin);
		
		try {
			
		System.out.print("지점을 선택하세요 :");
		int placeNum = sc.nextInt();
		int result = service.endSet(placeNum);
		
		if(result > 0) {
			System.out.println(placeNum+"번 지점이 초기화 되었습니다.");
		}else {
			System.out.println("변경중 에러 발생");
		}
		
		}catch(Exception e) {
			System.out.println("지점 초회중 오류 발생");
		}
	}
		
	
		
}
;