package edu.kh.variable.ex2;

import java.util.Scanner;

public class ScannerExample2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//사칙연산 계산기
		
		System.out.print("실수 1 입력 : ");
		double input1 = sc.nextDouble();		
		//실수를 입력받은 후 실수형 input1에 저장시킨다.
		

		System.out.print("실수 2 입력 : ");
		double input2 = sc.nextDouble();	
		
		System.out.printf("%.2f + %.2f = %.2f\n",input1,input2,input1+input2);
		System.out.printf("%.2f - %.2f = %.2f\n",input1,input2,input1-input2);
		System.out.printf("%.2f / %.2f = %.2f\n",input1,input2,input1/input2);
		System.out.printf("%.2f * %.2f = %.2f\n",input1,input2,input1*input2);
	}	
}

