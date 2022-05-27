package edu.kh.control.loop;

import java.util.Scanner;

public class WhileExample {
/*	
 * 	while문 
 * 	-별도의 표기식 증감식이 존재하지 않고
 * 	반복 종료 조건을 자유롭게 설정하는 반복문.
 *  
 *  **확실히 언제 반복이 끈날지는 모르지만.
 *    언젠가 반복 조건이 false가 되는 경우 반복 종류 .
 *    
 *    
 *    [작성법[
 *    
 *    while(조건식){
 *     조건식이 true일 때 반복 수행 할 구문.    
 *    }
 *    
 * */
		public void ex1() {
			Scanner sc = new Scanner(System.in);
			//System.out.print("");
			
			
			int input = 0;
			
			
		while(input !=9) {
				//input에 저장된 값이 9가 아닐 경우 반복을 하겟다.
				
				System.out.println("================");
				System.out.println("---메뉴 선택---");
				System.out.println("1번  떡볶이");
				System.out.println("2번  김밥");
				System.out.println("3번  라면");
				System.out.println("9번  종료");
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
			
				
				
				
				//input 값에 따라서 케이스를 선택
				switch(input) {
				case 1: System.out.println("[ 떡볶이를 주문하였습니다 ]"); break;
				case 2: System.out.println("김밥 주문하였습니다"); break;
				case 3: System.out.println("라면를 주문하였습니다"); break;
				
				case 9: System.out.println("메뉴 선택을 종료합니다."); break;
				
				default:System.out.println("잘못 입력 하셨습니다.");
				}
			}
		}
		public void ex2() {
			Scanner sc = new Scanner(System.in);
			
			int input = -1;
			//0이 아닌 값을 대입하여 while문이 처음에 동작 할 수 있도록.
			int sum = 0;
			
			//입력되는 모든 정수 구하기.
			//0이 입력되지 않으면 계속 반복하겟다
			
			//0일 때 종료
			while(input != 0) {

				System.out.print("정수 입력 :");
				input = sc.nextInt();
				
				sum+=input;//입력 받는 값을 sum에 누적
			}
			System.out.println("합계"+(sum));
		}
		
		public void ex3() {
			Scanner sc = new Scanner(System.in);
			
			int input = 0;
			//0이 아닌 값을 대입하여 while문이 처음에 동작 할 수 있도록.
			int sum = 0;
			
			//입력되는 모든 정수 구하기.
			//0이 입력되지 않으면 계속 반복하겟다
			
			
			// 2번째 방법으로 while문을 최소 한 번은 수행 하는 반복문
			// 	do ~ while문
			
			//0일 때 종료
			do{  //do == 일단은 실행해 봐라.
				System.out.print("정수 입력 :");
				input = sc.nextInt();
				
				sum+=input;//입력 받는 값을 sum에 누적
			}while(input != 0);
			
			System.out.println("합계 : "+sum);
		} 
}
