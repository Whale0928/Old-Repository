package edu.kh.variable.ex1;

public class VariableExample4 {
	public static void main(String[] args) {
		
		//강제 형 변환
		//강제 형 변환
		/* - 자료형을 원하는 자료형으로 '강제'로 변환 시키는 것. 
		 * 
		 * 	1) 값의 범위가 큰 자료형을 작은 자료형으로 변환할 떄 사용. ( 주로 사용)
		 *  2) 출력 되는 데이터의 표기법을 변환 하고 싶을 때.
		 *  
		 *  
		 *   -강제 형 변환 방법-
		 *	 [자료형을 변환 시키고 싶은 값 또는 변수 앞에 ( 자료형)을 작성]
		 * 		(temp==임시라는 의미)
		 * 			ex)  double temp = 3.14
		 * 				 int num = (int)temp;    double형 temp를 인트형으로 강제 형변환 시킨다
		 * */
		
		double temp = 3.14;
		int num = (int)temp; //Type mismatch : cannot convert from double to int.
		
		System.out.println(num);
		System.err.println(temp);
		// 실수 -> 정수 변환 시 소수점 버림 처리 ( 데이터 손실)
		
		//int to byte 강제 형 변환
		int iNum = 290;
		byte bNum = (byte)iNum;  //(byte) 형변환 명령이 없을 시 타입 미스매치 제한이 일어남.
		
		System.out.println("int to byte 강제 형 변환");
		System.out.println("before:"+iNum);
		System.out.println("after:"+bNum);
	
	
		// 2)방법 데이터의 표기법을 바꾸고 싶을 때에도 사용
		// char = > int 강제 형변환
		// int = > char 강제 형변환
		
		char ch = 'A';
		//int iNum2 = ch; //자동 형변환.
		
		System.out.println((int)ch);//강제 형 변환을 이용. 2줄이 1줄로 변경.
		int iNum3 = 65;
		System.out.println(iNum3 + "번째 문자: " + (char)iNum3);
		
		// 소문자 ' a' 보다 10칸 뒤에 있는 문자는 무엇일까?
		

		char ch4 = 'a';
		int iNum4 = ch4+10;
		System.out.println("10칸 이후의 문자"+ (char)iNum4);
		// 이런 방법도 있다 System.out.println((char)(int)ch4+10);
		// System.out.println((char)(ch4+10);
		
		//qustion
		
	}
}
