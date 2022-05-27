package edu.kh.variable.ex2;

import java.util.Scanner; 
//다른 패키지에 존재하는 패키지를 얻어오는 부분.
//스캐너를 import한다. (import ==수입)

public class ScannerExample {
	
	public static void main(String[] args) {
		//Scanner : 클라이언트에 각종 입력 정보를 받는 역활을 한다. 
		//(키보드 입력을 받을수 있게 하는.)
		
		//Scanner 생성
		// - > 프로그램 안에 스캐너라는 기계를 만드는 것.
		
		Scanner sc = new Scanner(System.in); //스캐너의 이름은 sr
		System.out.print("1번째 정수 입력:");
		int input1 = sc.nextInt(); 
		//다음 입력된 정수를 읽어온 후 
		//정수형 input1에 저장한다.
		
		
		System.out.print("2번째 정수 입력:");
		int input2 = sc.nextInt();
		
		System.out.printf("입력한 값의 결과는 : %d\n",input1+input2);
		
	}
}
