package edu.kh.basic;

//주석 : Compiler 해석하지 않는 부분, 주로 코드 설명을 작성
//Compiler : 코드를 2진수로 변역함.

//JAVA Code 실행순서 
// 코드 작성 > ctrl +f11 > 컴파일러가 2진수로 번역 > JVM이 2진수를 해석해 실행

/* 범위 
  		
  		주석 */

public class JavaTest {    //public == 공개적인  / class == 자바코드가 작성되는 기본적인 영역.
	public static void main(String[] args) { 	 //자바 응용프로그램의 의 가장 기본적인 main method  == 필수적인
				System.out.println("hello world!!"); 
				System.out.println("점심은 뭐 먹지");
				System.out.println("123 subway vs 김밥뭐시기");
				
				//숫자 연산
				
				System.out.println("-----------");
				System.out.println("1+2");  
				System.out.println(1+2);	
				//""안에 작성된 코드는 단순 문자열
				//작성되지 않는 코드는 int형/변수 자체로 인식된다
				
				
				System.out.println(50-122);
				System.out.println(501*122);
				System.out.println(500/122);

				//문자열 + 숫자 함께 작성

				System.out.println("14 * 19 = "+266);
				// + 기호의 역활 
				//숫자+숫자 = 덧셈연산 결과
				//문자열+숫자 혹은 문자열 + 문자열 이어쓰기의 역활이다.
				
				System.out.println("14 * 20 = " + 14*19 ); //곱하기가 먼저 실행되고 더하기가 실행된다.
				System.out.println("90+ 60+ 75 = " + 90+60+75); //사칙연산의 서순에 따른 오류 앞에서 부터 순서대로 실행된다.
				System.out.println("90+ 60+ 75 = " + (90+60+75)); //괄호로 묶어 먼저 연산 시킨다.
				
			//------------------------------------------------------------------
				
				
				
				
				
				
	}
}