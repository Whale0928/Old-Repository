package edu.kh.control.condition;

import java.util.Scanner;

public class SwitchExample {
	
	
//	- 작성법
//	- switch(식){
//	- 
//	- case 결과 값 1 : 수행 코드1; break;
//	- case 결과 값 2 : 수행 코드2; break;
//	- case 결과 값 3 : 수행 코드3; break;
//	- .....원하는 만큼 case생성 가능
//	- default : 모든 case를 만족하지 못할 경우 수행하는 코드.
//	- }
	
	public void ex1() {
		//키보드로 정수를 입력 받아
		//1이며 빨간색
		//2이면 주황색
		//3이면 노란색
		//4이면 초록색
		//1~4사이의 수가 아니면 흰색
			
		Scanner sc = new Scanner(System.in); 
		//스캐너는 입력장치를 읽어오는 것인대
		//system.in은 자바에서 '기본' 으로 정해둔 키보드라는 의미이다.
		
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		String result;
	/*	if(input == 1) {
			result = "빨강색";
		}else if(input==2) {
			result = "주황색";
		}else if(input==3) {
			result = "노란색";
		}else if(input==4) {
			result = "초록색";
		}else {
			result = "흰색";
		}*/
		
		
		/*여러 값이 나오는 식*/
		switch(input) { 
			
		case 1 : result = "빨간색";break;
		//input에 입력된 값이 1인경우( case) 
		//result 변수에 빨강색을 대입하고 swithc문을 멈춘다(break)
		
		case 2 : result = "주황색";break;
		case 3 : result = "노란색";break;
		case 4 : result = "초록색";break;
		
		default : result = "흰색"; break; 
		//어차피 마지막이라 break를 적지 않아도 되긴한다.
		}
		System.out.println(result);
	}
	public void ex2() {
		
	// 정수를 입력 받아 4 팀으로 나누기
	// 정수 입력 : 
		
	Scanner sc = new Scanner(System.in);
	System.out.print("번호 입력 : ");
	int input  = sc.nextInt();
	
	String team;
	
	switch(input % 4) { //1,2,3,0 으로 나눠진다.
	case 1 : team = "1백팀";break;
	case 2 : team = "2홍팀";break;
	case 3 : team = "3청팀";break;
	default : team = "4흑팀";break;
	}
	System.out.println(team+"입니다.");
	
	}
	public void ex3() {
		//switch문에서 berak의 역활
		//달(월) 입력시 계절 판멸(Switch버전으로 재 입력)

		Scanner sc = new Scanner(System.in);
		System.out.print("달을 입력 : ");
		int month = sc.nextInt();
		
		String season; //결과 저장용 변수
		
		switch(month){
		case 3 :// season = "봄3";  //break가 없어서 봄 4로 넘어감
		case 4 :// season = "봄4";  //break가 없어서 봄 5로 넘어간다.	
		case 5 : season = "봄5";break;
		
		case 6 :case 7 :case 8 : season = "여름";break;
		case 9 :	case 10 :	case 11 : season = "가을";break;
		case 12 :	case 1 :	case 2 : season = "겨울";break;
		//동일한 결과값을 도출해 내는 case는 한줄로 묶어버려도 괜찮다.
		
		default : season = "잘못입력";break;
		}
		System.out.println(season);
	}
	public void ex4() {
		//결과값이 정수가 아닌 경우.
		//계산기 제작
		//ex)
		//정수 1 입력 :5
		//연산자 입력 :+
		//정수 2 입력 :2
		
		int result;
		Scanner sc = new Scanner(System.in);
		System.out.print("수1 를 입력하세요:");
		int num1 = sc.nextInt();

		System.out.print("연산자를 입력하세요 : ");
		String op = sc.next();
		
		//scanner은 char를 입력 받는 기능이 없다.
		if(op != "+" || op!="-" || op!="*" || op!="/" || op!="%") {
			System.out.println("Can not used this opertor");
		}else {
		
		System.out.print("수2 를 입력하세요 : ");
		int num2 = sc.nextInt();
		
		switch(op) {
		case "+" : System.out.printf("%d + %d = %d",num1,num2,num1+num2); break;
		case "-" : System.out.printf("%d - %d = %d",num1,num2,num1-num2); break;
		case "*" : System.out.printf("%d * %d = %d",num1,num2,num1*num2); break;
		
		case "/" : 
			
			if(num1==0 || num2==0 ) {
				System.out.println("0으로 나눌 수 없습니다");
			}else {
			
			System.out.printf("%d / %d = %d",num1,num2,num1/num2); 
			}
			break;
		
		case "%" : System.out.printf("%d %% %d = %d",num1,num2,num1%num2); break;
		default :  System.out.println("Can not used this opertor");;break;
			}
		}
	}
}
