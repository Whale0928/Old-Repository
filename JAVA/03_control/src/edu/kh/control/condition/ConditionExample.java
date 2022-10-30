package edu.kh.control.condition;

import java.util.Scanner;
public class ConditionExample {

		//만약에 ~ 이면 
		//if(조건식이 true일 경우 {내부 코드}를 실행한다.)
		/*
		 * 	[작성법]
		 * 	if(조건식){
		 * 		조건식이 true일 경우 수행할 코드.
		 * 	}
		 * */
		public void ex1() {
			Scanner sc = new Scanner(System.in);
			
			System.out.print("정수 입력 ");
			int input =sc.nextInt();
			
		//입력된 정수가 양수인지 검사
		if(input > 0) {
			System.out.println("양수입니다.");
		}else{
			System.out.println("양수가 아닙니다."); 
			//0인지 음수인지 정확하지 않기 때문에 음수입니다라고 표기하면 안된다.
			// else  (그렇지 못한 경우 나머지)
		}	
	}
		public void ex2() {
			//if - else문
			// 조건식의 결과가 true 이면 if 
			// false면 else으로 출력한다.
			/*
			 * if(조건식){
			 * 		true 경우 출력
			 * }else{
			 * 		false 경우 출력
			 * }
			 */
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("정수 입력 :  ");
			int input = sc.nextInt();
			
			if(input%2 != 0){
					System.out.println("홀수");
			}else{
				
			//***중첩 if문***
			//홀수인가 홀수가 아닌가 밖에 생각하지 못한다.
			if(input == 0) {
				System.out.println("0 입니다.");
					}else {
					System.out.println("짝수");
				}	
			}  
		}
		public void ex3() {
			//  if - else if - else
			//양수, 음수, 0 판별
			Scanner sc = new Scanner(System.in);
			
			System.out.print("정수 입력 : ");
			int input = sc.nextInt();
			
			
			if(input > 0){      //input이 양수일 경우
								System.out.println("양수입니다.");
			}else if(input<0) {
								//if가 만족되지 못한 경우 실행
						System.out.println("음수입니다");
			}else{				//if , else if 둘 모두 false일 경우 마지막 else  실행.
					System.out.println("0 입니다.");
			}
			
		}
		public void ex4() {
			Scanner sc = new Scanner(System.in);
			
			System.out.print("달(month)을 입력하세요 : ");
			int month = sc.nextInt();
			
			String season;
			
			//3,4,5 봄 
			if(month == 3|| month == 4 || month == 5) {
					season = "봄";
			} else if(month >= 6&& month<8) {
					season = " 여름";
			} else if (month >=9 && month < 11) {
					season = "가을";
			}else if(month == 12|| month == 1 || month == 2) {
					season = "겨울";
			}else { 
					season = "해당하는 계절이 없습니다.";	
				}

			System.out.println(season);
			
		}
		public void ex5() {
			//나이를 입력 받아 
			//13세 이하면 '어린이'
			//13세 초과 19세 이하이면 '청소년'
			//19세 초과면  '성인'
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("나이를 입력하세요 : ");
			int age = sc.nextInt();
			
			if(age<=13) {
				System.out.println("어린이 입니다. ");
			}else if(age <= 19) {	
				System.out.println("청소년입니다");
			}else {
				System.out.println("성인입니다.");
			}
		}
		public void ex6() {

			//점수(점수)를 입력 받아 
			//90점 이상 A
			//80점 이상 90점 미만 B
			//70점 이상 80점 미만 C
			//60점 이상 70점 미만 D
			//0점 미만 100점 초과 error
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("점수를 입력하세요 : ");
			int score = sc.nextInt();
			
			if(score>100 || score < 0 ) {
				System.out.println("잘못 입력 되었습니다");
			}else if(score>=90) {
				System.out.println("A");
			}else if(score>=80) {
				System.out.println("B");
			}else if(score>=70) {
				System.out.println("C");
			}else if(score>=60) {
				System.out.println("D");
			}else {
				System.out.println("F");
			}
		}
		public void ex7() {
			
			// 놀이기구 탑승 제한 검사
			// 나이가 12세 이상, 키 140.0cm 이상 일 경우에만 "탑승 가능"
			// 나이가 12미만인 경우 : "적정 연령이 아닙니다."
			// 키가 140.0cm 미만 : "적정 키가 아닙니다."
			// 나이를 0세 미만, 100세 초과 시 : "잘못 입력하셨습니다."

			
//			Scanner sc = new Scanner(System.in);
//			
//			System.out.print("나이를 입력하세요 : ");
//			int age = sc.nextInt();
//			
//			System.out.print("키를(을) 입력하세요 : ");
//			double height = sc.nextDouble();
//		
//			
//		if(age<0 || age>100) {
//			System.out.println("잘못 입력 하였습니다.");
//				}else if(age<12){
//					System.out.println("적정 연령이 아닙니다.");
//						
//					}else if{
//						System.out.println("적정 키가 아닙니다.");
//					}				
//					}else{
//				System.out.println("탑승 가능");
//			}
}
		public void ex8() {

		//// 놀이기구 탑승 제한 검사 프로그램
		// 조건 - 나이 : 12세 이상
		//-  키 : 140.0cm 이상

		// 나이를 0~100세 사이로 입력하지 않은 경우 : "나이를 잘못 입력 하셨습니다."
		// 키를 0~250.0cm 사이로 입력하지 않은 경우 : "키를 잘못 입력 하셨습니다."
		// -> 입력이 되자 마자 검사를 진행하여 잘못된 경우 프로그램 종료

		// 나이 O , 키 X : "나이는 적절하나, 키가 적절치 않음";
		// 나이 X , 키 O : "키는 적절하나, 나이는 적절치 않음";
		// 나이 X , 키 X : "나이와 키 모두 적절치 않음";
		// 나이 O , 키 O : "탑승 가능"
			Scanner sc = new Scanner(System.in);
			System.out.print("나이를 입력하세요 : ");
			int age = sc.nextInt();
			
			String result;
		
				if(age<0 || age>100) {
					result="나이를 잘못 입력하였습니다.";
					}else{
						System.out.print("키를 입력하세요 : ");
						double height = sc.nextDouble();
						if(height<0 || height>250) {
							result="키를 잘못 입력하였습니다.";
								}else{
									if(age >= 12 && height<140) {
										result="키 때문에 안됨";
									}else if (age<12 && height>=140) {
										result="나이 때문에 안됨";
									}else if (age< 12 && height<140) {
										result="키와 나이 둘다 부족.";
									}else {
										result = "탑승가능";
									}
								}				
							}
				System.out.print(result);
	}
		public void ex9() {
			Scanner sc = new Scanner(System.in);
			
			System.out.print("입력하시오");
			int input = sc.nextInt();
		}//ex9 끝 대괄호.
}