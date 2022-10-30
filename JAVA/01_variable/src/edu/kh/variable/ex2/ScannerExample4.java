package edu.kh.variable.ex2;

import java.util.Scanner;

public class ScannerExample4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("입력 1: ");
		String input = sc.next()+" "; //입력받은 값을 저장
		
		System.out.print("입력 2: ");
		input = input + sc.next()+" ";
			// 대입 연산자 기준으로 연산을 시작함.

		System.out.print("입력 3: ");
		input = input + sc.next()+" ";
		
		System.out.println(input);
		//누적 효과( 변수의 재사용성) 
		
	}
}
