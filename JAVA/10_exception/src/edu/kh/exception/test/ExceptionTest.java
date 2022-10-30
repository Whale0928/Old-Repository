package edu.kh.exception.test;

import java.util.Scanner;

public class ExceptionTest {
	public static void main(String[] args) {
		

	Scanner sc = new Scanner(System.in);
	while(true) {
		System.out.print("정수 입력 >0입력시 종료<: ");
		int input=sc.nextInt();
		if(input==0) {
			break;
		}
	}	
	
}
}