package edu.kh.control.branch;

import java.util.Scanner;

public class BranchExample {

	public void ex1() {
		// 1부터 10까지 1씩 증가하며 출력하는 반목문

		for (int i = 1; i <= 10; i++) {
			System.out.print(i + "  ");
			if (i == 5) {
				break; // 반복문을 멈춤. 만약 i = 5일때;
			}
		}
	}

	public void ex2() {
		// 0이 입력될때 까지의 모든 정수의합 구하기
		// 1) 인풋의 초기값을 다른 값을 지정
		// while(input != 0)

		// 2)do ~ while()을 사용

		// 3)무한 루프의 while문을 만들고
		// 내부에 break 조건 장성
		Scanner sc = new Scanner(System.in);
		int sum = 0;
		int input = 0;

		while (true) {// 한무 루프
			System.out.print("정수 입력 : ");
			input = sc.nextInt();
			// 입력받은 수가 0인지 검사
			if (input == 0) {
				break;
			} // 입력받은 값이 0일때 break;
			sum += input; // 누적

		}
		System.out.println("종료" + sum);

	}

	public void ex3() {
		Scanner sc = new Scanner(System.in);
		// 입력받는 문자열을 누적
		// 단 exit 입력시 문자열 누적을 종료하고 결과 출력

		String str = ""; // 공백없이 쌍따옴표를 이용해 String iteral 빈 문자열을 의미한다.
							// ""는 무조건 String을 가르킨다.

		while (true) { // 한무 반복.
			System.out.print("문자열 입력 _ (exit@ 입력시 종료): ");
			String input = sc.nextLine();
			// next(); 는 다음 한 단어. (띄어쓰기로 구분하기 때문에 띄어쓰기를 사용할 수 없다)
			// nextLine(): 은 다음 한 줄을 기억한다 (enter를 만나는 지점 띄어쓰기를 사용 가능)

			if (input.equals("exit@")) {
				// String자료형은 비교연산자로(==) 같은문자열인지 판별 할 수 없다.

				// 비교연산자 보통 기본 자료형끼리의 연산에만 사용 가능하다.
				// String은 참조형

				// ***해결 방법 : 문자열1.equals(문자열2) 으로 비교 가능***
				// 입력값 .equals(비교할 문자)

				break;
			}

			str += input + "\n"; // 누적 할 때마다 개행문자도 추가한다 (개행문자 : 줄바꿈 문자)
		}
		System.out.println("----------------------------------------------");
		System.out.println(str);

	}

	public void ex4() {
		// 중첩 반복문 내부에서 break 사용하기
		// 2에서 9단까지 모두 출력하는 문

		for (int dan = 2; dan <= 9; dan++) {
			for (int i = 1; i <= 9; i++) {
				System.out.printf("%d x %d = %2d  ", dan, i, dan * i);
				if (i == dan) {
					break;
					// 분기문은 중첩 반복문 내에서 사용되면
					// 가장가까운 반복문에 작용한다!

					// go to라는 분기문은 어디서 든지 원하는 for문은 제어할 수 있다.
				}
			}
			System.out.println();
		}
	}

	public void ex5() {
		// break : 반복문을 바로 멈추는 역활
		// continue : 다음 반복으로 넘어감.

		// 1~10까지 1씩 증가하며 출력

		for (int i = 1; i <= 10; i++) {
			if (i % 3 == 0) {
				continue;
			}
			System.out.println(i + " ");
		}
	}

	public void ex6() {
		//1에서 100까지 1씩 증가하며 출력하는 반복문
		//단 5의 배수는 스킵
		//증가하는 값이 40이 되었을 때 멈춤
		
		for(int i = 1; i<=100;i++) {
			if(i==40){
				break;
			}
			if(i%5==0) {
				continue;
			}
			System.out.println(i);
		
		}
	}
	
	public void RPSGame() {
		//가위 바위 보 게임만들기
		
		//1) 판수입력 : 3 입력
		//2) 가위/바위/보 중 하나를 입력 해주세요 :가위
		//컴퓨터가 [보]를 선택했습니다.
		// 플레이어 승
		//현재 기록 : 1승 0무 0패
		System.out.println("[가위 바위 보 게임]");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("몇판을 진행 하시겠습니까? : ");
		int games = sc.nextInt();
		
		//승패기록용 변수
		int win = 0;
		int draw = 0;
		int lose = 0;
		
		for(int i =1; i<=games;i++) {
			System.out.println("\n"+i+"번째 게임입니다.");
			System.out.print("가위 바위 보 중 하나를 입력해주세요 : ");
			String choie = sc.next(); //플레이어가 가위/바위/보 선택
			
				//컴퓨터가 가위바위보를 정하기(랜덤)
				//1~3 사이의 난수 생성
				// 1이면 가위 2이면 바위 3이면 보를 지정(switch문을 활용)

				//난수 생성 방법 : Math.random();
				//0.0이상 1.0미만의 난수가 생성된다.
				
			int randam = (int)(Math.random() * 3 +1);
				//0.0 <= x < 1.0
				//0.0 <= x * 3 <3.0
				//1.0 <= x *3+1 <4.0
				//1 <= (int)(x *3+1) <4
				//==1 이상 4미만 난수 생성
			
			String com=null; //컴퓨터가 선택한 가위/바위/보를 저장하는 변수.
							 //null : 아무것도 참조하고 있지 않음
			
			switch(randam) {
			case 1:com="가위";break;
			case 2:com="바위";break;
			case 3:com="보";break;
			}
			
			System.out.printf("컴퓨터는 %s 를(을)선택 했습니다\n",com);
			//The local variable com may not have been initialized
			//컴퓨터는 실행 전까지 1~3 사이의 값이 나온다고 신뢰 못함.
			String end= null;
			
	
				if(choie.equals(com)){
					System.out.println("비겼습니다.");
					draw++;
				}else {
					if(choie.equals("가위")&&com.equals("보")) {
						System.out.println("플레이어 승");
						win++;
					}else if(choie.equals("바위") && com.equals("가위")){
						System.out.println("플레이어 승");
						win++;
					}else if(choie.equals("보") && com.equals("바위")){
						System.out.println("플레이어 승");
						win++;
					}else {
						System.out.println("졌습니다");
						lose++;
					}
				}//else 끝부분
	
		System.out.printf("%d승 %d무 %d패 \n",win,draw,lose);

			
		}
	}
	

}
