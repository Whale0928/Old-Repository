package edu.kh.variable.ex1;

public class VariableExample3 {

	public static void main(String[] args) {
		/* 형변환 (Casting) : 값의 자료형을 변환하는 것. (boolean 제외)
		 * 
		 * 기본적으로 컴퓨터는 같은 자료형 끼리만 연산이 가능.
		 * 다른 자료형과 연산시 오류 발생. (이런 상황을 피하기 위해 형변환이 필요)
		 * 
		 * 자동 / 강제 형변환으로 나눠진다.
		 * 
		 * */
		
		//자동 형변환(auto Cast)
		//  - 값의 범위가 큰 자료형 
		// 	- 값의 범위가 작은 자료형의 연산시
		//	  작은 자료형이 큰자료형으로 컴파일러에 의하여 자동적으로 변환하는것.
		
		int num1 = 10;
		double num2 = 3.5;
		
		System.out.println("자동형 변환 결과 : "+ (num1+num2));
		//원래 에러가 발생하지만 자동 형 변환 떄문에 오류 없음.
		
		int i1 = 3;
		double d1 = i1;   //double은 실수만 저장할 수 있는 자료형이다. 때문에 강제적으로 변환된다. 
		
		System.out.println("i1:"+i1); //3
		System.out.println("d1:"+d1); //3.0
		
		System.out.println(d1+num2); //double + double.
		
		//int -> long 형 변환
		int i2 = 2_100_000_000;  //언더바로 자리수를 구분해도 된다.
		long l2 = 10_000_000_000l;
		long result2 = i2 + l2;
		
		System.out.println("i2+l2 ; "+result2);
		//int + long = long (int가 long으로 변경)
		

		
		//char -> int 형 변환
		
		char ch3 = 'V';
		int i3 = ch3; //대입도 연산이다.
					  //int 변수 = char값
					  //--> int 자동 형 변환.
		System.out.println(i3);
		
		char ch4 = '각';
		int i4=ch4;
		System.out.println("i4:"+i4);
		
		
		//long -> float 자동 형변환
		 long l5 = 123456789123456789l;
		 float f5 = l5;//*100; //long 의 범위를 초과함
		 //float = long * int(long으로 자동 형변환)  
		 //float = long * long
		 //float = long 결과
		 //float = float(자동 형 변환)
		 
		 System.out.println("결과:"+f5); //오버플로우(overflow) 반대되는 현상은 언더플로우(underflow)
		
		 
		 //overflow는 개발자가 미리 예측해야 하는 범위이다 컴퓨터가 예측불가한 부분.
		 //애매하다 싶으면 상위 단위로 쓰자.
	
		
		 //강제 형 변환.은 02/23일 오전에 시작.
	}
}
