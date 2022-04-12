package edu.kh.rental.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.rental.member.model.vo.Client;
import edu.kh.rental.member.model.vo.Manager;
import edu.kh.rental.member.view.ClientView;
import edu.kh.rental.member.view.ManagerView;

public class MainView {

	Scanner sc =new Scanner(System.in);
	private Client ctLogin =  null;
	private Manager managerLogin =  null;
	private ClientView clientView = new ClientView(); 
	private ManagerView managerView = new ManagerView(); 
	
	/** 시작 프로그램  
	 * 
	 */
	public void mainDisplay() {
		int menuNum = -1; // 선택값 저장용
		do {
			try {
				if (ctLogin == null&&managerLogin ==null) {// 로그인이 안되어 있는 경우

					System.out.println("\n********** 예약 프로그램 **********\n");
					System.out.println("[1. 회원가입]");
					System.out.println("[2. 로그인]");
					System.out.println("[3. 관리자 로그인]");
					System.out.println("[0. 프로그램 종료]\n");
					System.out.print("메뉴를 입력해주세요 >> ");
					menuNum = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (menuNum) {
					case 1:clientView.signUp(); break;
					case 2:ctLogin = clientView.login();break;
					case 3:managerLogin = managerView.login();break;
					case 0:
						System.out.println("\n프로그램을 종료합니다.");
						break;
					default:
						System.out.println("메뉴에 작성된 번호를 입력해주세요.");
					}
				} else if (ctLogin != null&&managerLogin ==null){ // 클라이언트가 로그인 되어 있는 경우
				
					System.out.println("\n********** 회원제 게시판 프로그램 **********\n");
					System.out.println("[1. 예약 가능 지점]");
					System.out.println("[2. 예약 신청]");
					System.out.println("[3. 예약 조회]");
					System.out.println("[9. 로그아웃]");
					System.out.println("[0. 프로그램 종료]\n");
					System.out.print("메뉴를 입력해주세요 >> ");
					
					menuNum = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (menuNum) {
					case 1:clientView.select();break; 
					case 2:clientView.resRequest(ctLogin);break;
					case 3:clientView.selectMy(ctLogin);break;
					case 9:ctLogin = null;break;
					case 0:
						System.out.println("\n프로그램을 종료합니다.");
						ctLogin = null;
						break;
					default:
						System.out.println("메뉴에 작성된 번호를 입력해주세요.");
					}
					
				}else if (ctLogin == null&&managerLogin !=null) {
					System.out.println("\n********** 회원제 게시판 프로그램 **********\n");
					System.out.println("[1. 관리중인 지점]");
					System.out.println("[2. 예약 관리]");
					System.out.println("[9. 로그아웃]");
					System.out.println("[0. 프로그램 종료]\n");
					System.out.print("메뉴를 입력해주세요 >> ");
					
					menuNum = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (menuNum) {
					case 1:managerView.selectShop(managerLogin);break; 
					case 2:managerView.superintend(managerLogin);break;
					case 9:managerLogin = null;break;
					case 0:
						System.out.println("\n프로그램을 종료합니다.");
						managerLogin = null;
						break;
					default:
						System.out.println("메뉴에 작성된 번호를 입력해주세요.");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\n입력 형식이 올바르지 않습니다 , 다시 시도해주세요");
				sc.nextLine();//입력 버퍼에 남아있는 잘못된 문자열들 제외
			}
		} while (menuNum != 0);
		
	}

}
