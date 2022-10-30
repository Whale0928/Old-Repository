package edu.kh.variable.practice;

public class CastiongPractice {

	public static void main(String[] args) {
		int iNum1 = 10;
		int iNum2 = 4;	
		float fNum = 3.0f;
		double dNum = 2.5;
		char ch = 'A';
		
		//System.out.println(iNum1/iNum2);
		//System.out.println((int)dNum);
		//System.out.println((double)iNum1);
		//System.out.println(dNum*4);  //변수를 더 활용했으면 iNum2도 사용했어야된다.
		
		//System.out.println((int)fNum);
		//System.out.println(iNum2-1);
		//System.out.println(iNum1/(int)fNum); 
		
		//System.out.println( (double)iNum1/(double)iNum2); //2.5 출력을 희망.
		
		//float / double 차이점.
		//System.out.println( iNum1/fNum); //3.3333.. 
		//System.out.println( iNum1/(double)fNum); //3.333...35    
		
		//float : 소수점 아래 8짜리 까지 연산 후 반올림.
		//double : 소수점 아래 16자리 까지 연산 후 반 올림.
	}
}
