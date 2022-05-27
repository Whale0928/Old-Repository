package edu.kh.variable.ex2;

import java.util.Scanner;

public class ScannerExample3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//문자열 String 입력 
		
		
		//next() : 다음 입력된 한 단어를 잃어온다.
		//nextLine() : 다음 한 줄을 잃어온다..?
		
		
		System.out.print("입력:");
		String input1 = sc.next();
		System.out.print("입력:");
		input1 = input1 + sc.next();
//		System.out.print("입력:");
//		String input1 = sc.next();
//		
		System.out.printf("%s_%s",input1,input1);
	}

}
