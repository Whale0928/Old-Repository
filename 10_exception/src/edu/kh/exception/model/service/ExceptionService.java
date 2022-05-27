package edu.kh.exception.model.service;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionService {

	// 예외 : 소스 코드의 수정으로 해결 가능한 오류들

	// 예외는 두종류로 구분된다.
	// 1) Unchecked Exception : 선택적으로 예외 처리.
	// 2) Checked Exception : 필수적으로 예외 처리.

	Scanner sc = new Scanner(System.in);

	public void ex1() {
		// try ~ catch 예외 처리

		// try { } : 괄호 내부에 예외가 가능성이 있는 코드를 작성한 후 시도.

		// catch(예외){ } : try 구문에서 발생한 예외를 잡아내서 처리하여 프로그램이 비정상 종료 되지 않도록 한다.

		System.out.println("두 정수를 입력받아 나누기한 몫을 출력 ");

		System.out.print("정수 1 입력 : ");
		int num1 = sc.nextInt();
		System.out.print("정수 2 입력 : ");
		int num2 = sc.nextInt();
		try {
			System.out.println("답 : " + num1 / num2);
		} catch (ArithmeticException e) {
			System.out.println("infinity");
		}
	}

	// 정수를 입력받아 반환하는 메서드
	public int inputNumber() {
		int input = 0;
		while (true) {

			try {
				System.out.print("정수 입력 : ");

				input = sc.nextInt();
				sc.nextLine();

				break;
			} catch (InputMismatchException e) {
				System.out.println("잘못 입력 하였습니다 정수만 입력해주세요....>>>");

				sc.nextLine();// 버퍼에 남아있는 잘못 입력된 문자열을 제거
			}
		}

		return input;
	}

	public void ex2() {
		// 정수 3개 입력후 합 구하기

		int sum = 0;

		for (int i = 0; i < 3; i++) {
			sum += inputNumber();
		}
		System.out.println("합계 : " + sum);
	}

	public void ex3() {
		// 여러개의 예외에 대한 처리 방법

		try {
			System.out.print("입력 1 : ");
			int num1 = sc.nextInt(); // InputMismatchException

			System.out.print("입력 2 : ");
			int num2 = sc.nextInt();// InputMismatchException

			System.out.println("나누기 결과 : " + num1 / num2);
			// ArithmeticeException

			// *******************************************
			// 강제로 NullPointerException 발생\
			// 참조하지 않는 참조변수를 이용해서 코드를 수행할 경우 발생
			String str = null;

			System.out.println(str.charAt(0));
			// NullPointerException이 캐치가 없을 경우 오류 발생후 종류

			// *******************************************
		} catch (InputMismatchException e) {
			// 예외 처리 + 다형성
			// Exception 클래스 : 모든 예외의 최상위 부모
			// 다형성 - 업캐스팅 : 부모 타입의 참조변수로 자식 객체를 참조
			System.out.println("정수가 아닌 문자가 입력 되었습니다.");

			// 상위 타입의 예외 클래스를 catch문에 작성하면
			// 다형성 업캐스팅에 의해 모두 잡아서 처리.

		} catch (ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다");
		} catch (Exception e) {
			System.out.println("무언가 예외 발생");
		}
	}

	public void ex4() {
		// 1) try -catch - finally
		// finally : try구문에서 예외가 발생하든 말든 무조건 실행

		try {
			System.out.println(4 / 2);
		} catch (ArithmeticException e) {
			System.out.println("예외.");
			// 2) catch문 매개변수 활용
			// 매개변수 e: 예외 관련 정보 + 예외 관련 기능
//			System.out.println(e.getClass());
//			System.out.println(e.getMessage());
//			
			System.out.println(e);
			e.printStackTrace();
		} finally {
			System.out.println("무조건 수행됨");
		}
	}
//-------------------------------------------
	
	public void ex5() {
		//Exception 처리를 호출한 메소드에게 위임
		
		//Throws : 호출한 메소드에게 예외를 던짐
		//호출한 메소드에게 예외를 처리하라고 위임하는 행위
		
		//throw : 예외를 강제 발생 구문(현재 메소드에 예외를 던짐)
		
	try {
		methodA();		
	}catch(Exception e) {
		//Exception : 모든 예외의 최상위 부모,
		//catch매개변수로 작성 됨 예외의 종류에 상관없이 모두 처리 
		System.out.println("예외 처리됨.");
	
		e.printStackTrace();
	}
	
	}
		
	public void methodA() throws IOException{

		methodB();
	}
	public void methodB() throws IOException{
		methodC();
	}
	public void methodC() throws IOException{
		//methodC() 메소드는 IOException을 발생시킬 가능성이 있음으로
		//호출하는 곳에서 예외처리를 반드시 해야햔다.
		//단 UncheckedException은 선택적으로 예외처리 할 수 있음.
		
		
		//IOException( ) ; :  예외강제 발생 
		throw new IOException();
	
		//발생한 예외를 처리하는 방법
		//1) try ~ catch 감싸서 현재 위치에서 처리
		//2 throws로 호출한 메소드로 예외를 위임하여 처리 
	}
//	public void methodD() throws IOEception{
//		throw new RuntimeException();
//	}
}
