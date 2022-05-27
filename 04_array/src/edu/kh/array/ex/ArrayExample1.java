package edu.kh.array.ex;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayExample1 {
	/*
	 * 배열(Array) -같은 자료형의 변수를 하나의 묶음으로 다루는것.(자료구조) -묶여진 변수들은 하나의 배열명으로 불려지고 구분은
	 * index를 이용한다(index는 0부터 시작하는 정수)
	 * 
	 * 
	 * 
	 * 
	 */

	public void ex1() {
		// 변수 vs 배열

		// 1. 변수 선언
		int num;
		// Stack영역에 4바이트 int를 할당했다.
		// 그 공간에 num이라는 이름을 부여했다.

		// 1-2
		num = 10;
		// Stack영역에 선언 되어 있는 num변수에 10이라는 iretal을 부여했다.

		// 이렇게 선언 할당된 변수는 호출하여 사용한다.

		// =================================================

		// 2-1 배열선언
		int[] arr; // 정수형 (참조형)배열 선언. (Stack 영역)
		// int 배열은 참조형으로 주소값을 저장한다.
		// 주소값은 4byte이지만 참조형이다.

		// 2-2 배열할당
		arr = new int[3];
		// new : "new 연산자" 라고 하며
		// Heap 메모리 영역에 새로운 공간 (배열,객체)을 할당한다.

		// int[3] : int 자료형 변수 3개를 의미한다. 하나의 묶음을 나타내는 배열.

		// new int[3] : 3칸 짜리 int 배열을 >>HEAP<<에 생성 (할당) 한다

		// heap영역에 할당된 공간은 <<절대>>비어있을 수 없다.
		// 최초 할당 시 컴파일러가 자동으로 JVM 기본값이 자동 저장된다
		// boolean은 false , 나머지는 0 , 참조형은 >>null<<
		// 주소값은 0x0300 뒤의 4자리를 기억하여 찾아간다.

		// arr = new Int[3]'
		// (int[]) (int[]) -> 같은 자료형이기 때문에 == 연산 가능하다

		// Heap 영역에 생성된 int[]의 시작 주소를
		// stack 영역에 arr 변수에 대입한다.

		// -> arr 변수가 int[]을 주소값을 보고 참조해온다
		// 그래서 참고형이라 한다.

		// 2-3 배열 요소 값 대입
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;

		// 2-4 배열 요소 값 읽거오기
		System.out.println(arr[0]); // arr 참조하고 있는 배열의 0번 주소값을 얻어옴
		System.out.println(arr[1]);
		System.out.println(arr[2]);

	}

	public void ex2() {
		// 배열 선언 및 할당

		int[] arr;
		arr = new int[4];
		// 1)int참조 변수 arr을 선언하고
		// 2) heap 영역에 int형 4개를 묶음으로 다루는 배열의 주소를
		// 3) 참조형 변수 arr에 새로 선언한 int형4칸 배열의 주소를 참조시킨다.

		// 배열 길이 (몇칸인가?)
		// System.out.println("배열의 길이 : "+arr.length+"칸\n");

		arr[0] = 100;
		arr[1] = 200;
		arr[2] = 500;
		arr[3] = 1000;

		// 배열과 for문
		// for문에서 활용하는 i의 의미는 'index'
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("arr[%d]에 저장된 값 : %d \n", i, arr[i]);

		}
	}

	public void ex3() {
		// 배열의 초기화.
		// 5명의 키를 입력 받고 평균 구하기

		// 1번키 입력 : 170.3
		// 2번키 입력 : 160.2
		// 3번키 입력 : 120.4
		// 4번키 입력 : 180.8
		// 5번키 입력 : 150.5

		// 입력받은 키 : 위의 키들
		// 평균 : 177.02cm

		Scanner sc = new Scanner(System.in);

		double[] height = new double[5];
		double sum = 0;

		// double[ ] 자료형 참조 변수 height를 stack 영역에 생성
		// height 에 heap 영역에 새로 생성된 double 5칸 짜리의 double[] 시작 주소를 대입한다.

		// 평균은 합계
		for (int i = 0; i < height.length; i++) {
			System.out.print((i + 1) + "번째 키 : ");
			height[i] = sc.nextDouble();
			sum += height[i];
		}

		System.out.print("입력 받은 키 : ");

		for (int i = 0; i < height.length; i++) {
			System.out.print(height[i] + "  ");
		}

		System.out.printf("\n평균 : %.2f\n", (sum / height.length));
	}

	public void ex4() {
		// 입력 받은 인원 수 만큼의 점수를 입력 받아 배열에 저장.

		Scanner sc = new Scanner(System.in);
		System.out.print("인원 수를 입력하세요 : ");
		int input = sc.nextInt();

		int arr[] = new int[input];

		int sum = 0; // 합계 저장용

		for (int i = 0; i < arr.length; i++) {
			System.out.print((i + 1) + "번 점수를 입력해주세요 : ");
			arr[i] = sc.nextInt();
			sum += arr[i]; // 입력받으면서 바로 합계 저장

		}
		// 최고 최저점 구하기.

		int max = arr[0]; // 최대 점수
		int min = arr[0]; // 최소 점수

		// 아래 for문을 이용해서 score 배열에 있는 모든 값과
		// max min을 비교
		// score[i] max보다 크면 max에 대입
		// score[i] min보다 크면 min에 대입
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}else if(arr[i] < min) {
				min = arr[i];
			}

		}

		System.out.println("합계 : " + sum);
		System.out.printf("평균 : %.2f", (double) sum / arr.length); // 소수점 2번째 자리까지 보고싶기 때문에 강제 형 변환

		System.out.println("최고점 : " + max);
		System.out.println("최저점 : " + min);
	}

	public void ex5() {
		// 배열 선언과 동시에 초기화 하기

		char[] arr = new char[5];
		int[] int1 = new int[1];

		// char[] arr이 참조하는 배열 요소에 abcde 대입하기.
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (char) (65 + i);

			// **Arrays 클래스
			// - Java에서 제공하는 '배열'과 관련된 기능을 모아둔 클래스
		}
		// System.out.println(int1); //주소값이 출력된다. [I@2f92e0f4 맨앞의 영어는
		System.out.println(Arrays.toString(int1));
		System.out.println(Arrays.toString(arr));

		char[] arr3 = { 'A', 'B', 'C', 'D', 'E' };

		// char[] 참조 변수 arr3를 선언하고
		// heap 영역에 char 5칸짜리 char[]q배열을 생성하고
		// 각각 'A','B','C','D','E'으로 초기화 후 주소를 arr3에 대입한다

		// iteral 표기법 L , F , '' , " "
		// 중괄호는 배열의 iteral 표기법.

		System.out.println("\narr3의 길이 : " + arr3.length);
		System.out.println(Arrays.toString(arr3));

	}

	public void ex6() {

		// 점심메뉴 뽑기 프로그램

		String[] arr = { "김밥", "서브웨이", "햄버거", "백반", "파스타", "국밥" };

		System.out.println("오늘 점심 메뉴 : " + (arr[(int) (Math.random() * 6)]));

		// random = 0.0<=x <1.0;
		// random = 0<=x*6 <6; (실수를 정수로 형 변환 하여 0 1 2 3 4 5까지의 수만 나온다.

		System.out.println();
	}

	public void ex7() {
		// 배열을 이용한 검색.

		// 입력받은 정수가 배열에 있는지 없는지 확인
		// 있는 경우 몇번 인덱스 인지.

		int[] arr = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000 }; // int는 표기법없이 입력

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력: ");
		int input = sc.nextInt();

		// 배열 요소 순차 접근(반복 접근);

		// 신호를 나타내기 위한 변수
		// flag == false : 일치하는 값이 존재하지 않음
		// flag == true : 일치하는 값이 존재한다

		boolean flag = false; // 검사 전에는 없다고 가정 하고 시작한다.

		for (int i = 0; i < arr.length; i++) {
			// arr[i]에 저장된 값과 input이 같을경우

			if (input == arr[i]) {
				System.out.println(i + "번째 인덱스에 존재");
				flag = true;
			}
		}

		// flag 상태를 검사
		if (!flag) { // 논리부정 ! 을 사용해서 조금 더 간단하게 만들 수 있다.
			System.out.print("존재하지 않음");
		}

		// !flag
		// flag가 true일 경우 true 값이 반전되서 false로 코드가 동작하지 않음
		// flag가 false일 경우 true으로 반전되서 if문이 실행된다.

	}

	public void ex8() {
		// 입력 받은 값과 일치 값이 있으면 인덱스 번호 출력
		// 없으면 존재하지 않음.

		Scanner sc = new Scanner(System.in);

		String[] arr = { "멜론", "바나나", "딸기", "레몬", "용과", "키위" };

		System.out.print("과일을 검색 하세요 : ");
		String input = sc.next();

		boolean flag = false;

		for (int i = 0; i < arr.length; i++) {
			if (input.equals(arr[i])) {
				System.out.println(i + "번째 인덱스에 존재함.");
				flag = true; // 일치하는 값이 있는데 false이면 부정적인 의미이여서 논리 부정으로 썻다.
			}
		}
		if (!flag) { // 구지 반전 시키지 않아도 실행 되기는한다. 하지만 긍정적 /부정적 의미
			System.out.println("존재하지않음");
		}
	}

	public void ex9() {

		// 1. 문자열을 입력 받아 한 글자씩 잘라내어 char 배열에 순서대로 저장.
		// 2. 문자 하나를 입력 받아 일치하는 문자가 char 배열에 몇개 존재하는지 확인
		// 3. 단 , 일치 하는 문자가 없을 경우 "존재하지 않습니다"출력

		// 사용하는 기술
		// 1) 배열 검색
		// 2)string.length(); : 문자열의 길이
		// ex) "hello".legnth() => 5

		// 3)String.charAt(index) : 문자열에서 특정 index에 위치한 문자 하나를 얻어옴.
		// ex) "hello".charAt(1) = > 'e'

		// 4) count;

		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 입력 : ");
		String input = sc.next();

		int count = 0;

		char arr[] = new char[input.length()];

		for (int i = 0; i < input.length(); i++) {
			arr[i] = input.charAt(i);
			// arr[i]에 입력받은 문자열중 i 번째 문자를 저장한다.
		}

		System.out.print("문자 한개를 입력 (여러 글자 입력 시 첫 글자로 검색함) : ");
		char ch = sc.next().charAt(0);
		// 문자열의 첫 글자만 검색
		for (int i = 0; i < input.length(); i++) {
			if (ch == arr[i]) {
				// arr[i]번째 값이 ch의 값과 같을 경우.
				// 1) 카운트
				count++;
			}
		}
		if (count == 0) {
			System.out.println("존재하지 않습니다");
		} else {
			System.out.println("개수 : " + count);
		}
	}

	public void ex10() {

	}

}
