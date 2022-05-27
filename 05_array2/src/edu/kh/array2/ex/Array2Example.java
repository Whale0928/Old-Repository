package edu.kh.array2.ex;

import java.util.Arrays;

public class Array2Example {
	// 2차원 배열
	//
	// -자료형이 같은 1차원 배열을 묶음으로 다루는 것.
	//

	public void ex1() {

		// 2차원 배열 선언
		int[][] arr; // stack 공간에 2차원 인트형 배열을 선언
		// int 2차원 배열을 참조하는 참조 변수 선언
		// 참조형 == 참조 변수 ==레퍼런스 변수 ==레퍼런스

		// 2차원 배열 할당
		// ->new 자료형[행 크기][열 크기]
		arr = new int[2][3];// 2차원 배열의 주소값을 대입한다.
		// heap영역에 int 2차원 배열 2행 3열 공간을 할당.
		// 2차원 배열 초기화

		// 1) 행 , 열 직접 초기화(노가다)
//		arr[0][0] = 10;
//		arr[0][1] = 20;
//		arr[0][2] = 30;
//		
//		arr[1][0] = 40;
//		arr[1][1] = 50;
//		arr[1][2] = 60;

		// 2) 2중 for문을 이용한 초기화;

		// * 배열 길이
		// - > 배열명.length 는 변수가 직접 참고하고 있는 배열의 길이를 반환한다.

		/* System.out.println(arr[0].length); */
		// arr[0] 행이 참조하고있는 1차원 배열의 길이

		int num = 10;

		for (int row = 0; row < arr.length; row++) { // arr참조하고있는 배열의 길이 : 2차원 배열
			for (int col = 0; col < arr[row].length; col++) { // row가 증가하면서 배열을 확인할수 있다.
				arr[row][col] = num;
				num += 10;
			}
		}
		// Arrays. toString(배열명) : 참조하고 있는 1차원 배열 값을 문자열로 반환 한다. '1차원만'
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.deepToString(arr));
		// depp을 추가하면 모든 배열을 들어가본후 반환한다. (몇 차원 상관없이 toSring과 같은 효과를 낸다.)

		// 실행되면
		// 행을 가리키는 부분이 먼저 만들어 진다.

	}

	// 2차원 배열 실습
	// 선언과 동시에 초기화
	public void ex2() {
		// 3행 3열짜리 정수형 2차원 배열 선언과 동시에
		// 1~9까지 초기화

		int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, // 2차원 선언과 동시에 초기화
				{ 7, 8, 9 } }; // 이런 식으로 구성해 놔도 괜찮다 ';'세미콜론을 만나기 전까지.

		// 행 별로 합 출력
		int sum = 0;

		for (int row = 0; row < arr.length; row++) { // 행 반복
			for (int col = 0; col < arr[row].length; col++) { // 열의 반복. arr[row].length == arr[row] arr참조변수에 row번째 행의 길이
				sum += arr[row][col];
			}
			System.out.printf("%d행의 합 : %d\n", row, sum);
		}

		System.out.println("-----------------------------------------");
		// 열 별로 합 출력
		// -> 열 부터 하나 지정 후 각 행의 누적한다.
		// --> 완전한 사각형의 형태를 지닌 2차원 배열은
		// ---> !!모든 열의 길이가 같다!!
		int sum2 = 0;

		for (int col = 0; col < arr[0].length; col++) { // 열 반복
			for (int row = 0; row < arr.length; row++) { // 행 반복
				sum2 += arr[row][col];
				// 0 0
				// 1 0
				// 2 0
			}
			System.out.printf("%d열의 합 : %d\n", col, sum2);
		}

		System.out.println("-----------------------------------------");

		int sum3 = 0;

		for (int row = 0; row < arr.length; row++) { // 행반복
			for (int col = 0; col < arr[row].length; col++) { // 열반복
				sum3 += arr[row][col];
			}
		}
		System.out.printf("전체합 : %d\n", sum3);
	}

	
	//가변 배열 (실제로 많이 사용되지는 않는다)
	public void ex3(){
		//행의 길이는 정해두지만 
		//열의 길이가 자유롭게 넣을 수 있다.
		
		//int arr[] [] = new int [9] [ ] ;
		
		//2차원 배열 생성 시 마지막 배열 차수(열)을 지정하지 않고
		//나중에 서로 크기가 다른 1차원 배열을 생성하여 참조하는 배열.

		char arr[][] = new char[4][];
				//char 배열 생성수 행 부분만 생성을 해놓았다.	
		
		arr[0] = new char[3]; //0행 3열짜리 1차원 배열을 생성을 해서 주소 값을 저장.
		arr[1] = new char[4]; //1행에는 4열짜리 1차원 배열을 생성한다.
		arr[2] = new char[5];
		arr[3] = new char[2];
		
		//각 배열 요소에 'a'부터 차례대로 대입
		char ch = 'A';
		for(int row =0; row<arr.length;row++) {
			for(int col=0;col<arr[row].length;col++){
				arr[row][col] = ch++;  //후위 연산이기때문에 대입 먼저 하고 증가 시킨다.
			}
		}
		System.out.println(Arrays.deepToString(arr));
	}
}
