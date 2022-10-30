package edu.kh.array2.practice;

import java.util.Arrays;
import java.util.Scanner;

public class Array2Practice {

	public void ex1() {
//		3행 3열짜리 문자열 배열을 선언 및 할당하고
//		인덱스 0행 0열부터 2행 2열까지 차례대로 접근하여 “(0, 0)”과 같은 형식으로 저장 후 출력하세요

		String[][] arr = new String[3][3];

		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				System.out.printf("(%d , %d)", row, col);
			}
			System.out.println();
		}
	}

	public void ex2() {
//	4행 4열짜리 정수형 배열을 선언 및 할당하고
//	1) 1 ~ 16까지 값을 차례대로 저장하세요.
//	2) 저장된 값들을 차례대로 출력하세요

		int[][] arr = new int[4][4];
		int up = 1; // count 역활

		for (int row = 0; row < arr.length; row++) { // row 행
			for (int col = 0; col < arr[row].length; col++) {
				arr[row][col] = up++; // up변수를 후위 연산 시켜서 코드를 한줄로 줄임
				System.out.print(arr[row][col] + " ");
			}
			System.out.println();
		}

	}

	public void ex3() {
//		4행 4열짜리 정수형 배열을 선언 및 할당하고
//		1) 16 ~ 1과 같이 값을 거꾸로 저장하세요.
//		2) 저장된 값들을 차례대로 출력하세요.

		int[][] arr = new int[4][4];
		int count = 16;

		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				arr[row][col] = count--;
				System.out.printf("%3d", arr[row][col]); // printf을 이용해 가시성을 높혔다
															// arr배열 변수의 row번 1차원배열에 col을 호출한다.
			}
			System.out.println();

		}

	}

	public void ex4() {
		int arr[][] = new int[4][4];

		int rowEnd = arr.length - 1;
		int colEnd = arr[0].length - 1;

		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				if (row != rowEnd && col != colEnd) {
					int random = (int) (Math.random() * 10 + 1);
					arr[row][col] = random;

					arr[row][colEnd] += arr[row][col];
					arr[rowEnd][col] += arr[row][col];
					arr[rowEnd][colEnd] += arr[row][col];
				}
				System.out.print(arr[row][col] + "  ");
			}
			System.out.println();
		}
	}

	public void ex5() {
//		2차원 배열의 행과 열의 크기를 사용자에게 직접 입력받되, 1~10사이 숫자가 아니면
//		“반드시 1~10 사이의 정수를 입력해야 합니다.” 출력 후 다시 정수를 받게 하세요.
//		크기가 정해진 이차원 배열 안에는 영어 대문자가 랜덤으로 들어가게 한 뒤 출력하세요.
//		(char형은 숫자를 더해서 문자를 표현할 수 있고 65는 A를 나타냄, 알파벳은 총 26글자)
		Scanner sc = new Scanner(System.in);

		int row = 10;
		int col = 10;

		while (true) {
			System.out.print("행 크기 입력: ");
			row = sc.nextInt();
			if (1 > row || row > 10) {
				System.out.println("1~10 사이의 수를 입력하세요");
				continue;
			} else {
				break;
			}
		}
		while (true) {
			System.out.print("열 크기 입력: ");
			col = sc.nextInt();
			if (1 > col || col > 10) {
				System.out.println("1~10 사이의 수를 입력하세요");
				continue;
			} else {
				break;
			}
		}
		char arr[][] = new char[row][col];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				char ch = (char) (Math.random() * 26 + 65);
				arr[i][j] += ch;
			}
			System.out.println(Arrays.toString(arr[i]));
		}

	}

	public void ex6() {
//      사용자에게 행의 크기를 입력 받고 그 수만큼의 반복을 통해 열의 크기도 받아
//		문자형 가변 배열을 선언 및 할당하세요.
//		그리고 각 인덱스에 ‘a’부터 총 인덱스의 개수만큼 하나씩 늘려 저장하고 출력하세요.
		Scanner sc = new Scanner(System.in);

		int row = 0;
		int col = 0;
		char num = 'a';

		while (true) {
			System.out.print("행 크기 입력: ");
			row = sc.nextInt();
			if (1 > row || row > 10) {
				System.out.println("1~10 사이의 수를 입력하세요");
				continue;
			} else {
				break;
			}
		} // 행 크기 받은 while문의 끝

		char[][] arr = new char[row][];

		for (int i = 0; i < arr.length; i++) {
			while (true) {
				System.out.print(i + "번열 크기는 ? : ");
				col = sc.nextInt();
				arr[i] = new char[col];

				if (1 > col || col > 10) {
					System.out.println("1~10 사이의 수를 입력하세요");
					continue;
				} else {
					break;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = num++;
				System.out.print(arr[i][j] + "  ");
			}

			System.out.println();
		}

	}

	public void ex7() {
//		1차원 문자열 배열에 학생 이름 초기화되어 있다.
//		3행 2열 짜리 2차원 문자열 배열 2개를 새로 선언 및 할당하여
//		학생 이름을 2차원 배열에 순서대로 저장하고 아래와 같이 출력하시오.
//		(첫 번째 2차원 배열이 모두 저장된 경우 두 번째 2차원 배열에 저장 진행)

		String[] students = { "강건강", "남나나", "도대담", "류라라", "문미미", "박보배", "송성실", "윤예의", "진재주", "차천축", "피풍표", "홍하하" };

		String[][] arr1 = new String[3][2];
		String[][] arr2 = new String[3][2];

		int num = 0;

		System.out.println("===========1분단===========");
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[i].length; j++) {
				arr1[i][j] = students[num];
				num++;
				System.out.print(arr1[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("===========2분단===========");
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[i].length; j++) {
				arr2[i][j] = students[num];
				num++;
				System.out.print(arr2[i][j] + " ");
			}
			System.out.println();
		}

	}

	public void ex8() {

		String[][] arr1 = { { "강건강", "남나나" }, { "도대담", "류라라" }, { "문미미", "박보배" } };
		String[][] arr2 = { { "송성실", "윤예의" }, { "진재주", "차천축" }, { "피풍표", "홍하하" } };

		System.out.println("===========1분단===========");
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[i].length; j++) {
				System.out.print(arr1[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("===========2분단===========");
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[i].length; j++) {
				System.out.print(arr2[i][j] + " ");
			}
			System.out.println();
		}

		Scanner sc = new Scanner(System.in);
		System.out.print("검색 할 이름을 입력해주세요 : ");
		String name = sc.next();

		for (int i = 0; i < arr1.length; i++) { // 두 배열의 길이 만큼 검색
			for (int j = 0; j < arr1[i].length; j++) {
				if (arr1[i][j].equals(name)) {
					String seat = "";
					if (j == 0) {
						seat = "왼쪽";
					} else {
						seat = "오른쪽";
					}
					System.out.printf("입력하신 %s 학생은 1분단 %d째줄 %s에 있습니다.", name, i + 1, seat);
				} else if (arr2[i][j].equals(name)) {
					String seat = "";
					if (j == 0) {
						seat = "왼쪽";
					} else {
						seat = "오른쪽";
					}
					System.out.printf("입력하신 %s 학생은 2분단 %d째줄 %s에 있습니다.", name, i + 1, seat);
				}
			}
		}
	}

	public void ex9() {
//		String 2차원 배열 6행 6열을 만들고 행의 맨 위와 제일 앞 열은 각 인덱스를 저장하세요.
//		그리고 사용자에게 행과 열을 입력 받아 해당 좌표의 값을 “X”로 변환해 2차원 배열을 출력하세요.\
		Scanner sc = new Scanner(System.in);
		System.out.print("행의 좌표 입력 (0~5): ");
		int rowPoint = sc.nextInt();
		System.out.print("열의 좌표 입력 (0~5): ");
		int colPoint = +sc.nextInt();

		String arr[][] = new String[6][6];

		int row = arr.length - 1; // 행의 고정 길이
		int col = (arr[0].length - 1); // 열의 고정 길이

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (j == 0 && i == 0) {
					System.out.print(" ");
				} else if (i == 0) {
					System.out.print(j);

				} else if (j == 0) {
					System.out.print(i);
				}
			}
			for (int j = 0; j < arr[i].length; j++) {
				if (i == rowPoint && j == colPoint) {
					for (int jum = 0; jum < colPoint - 1; jum++) {
						System.out.print(" ");
					}
					System.out.print("X");
				}
			}
			System.out.println();
		}
	}

	public void ex10() {

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.print("행의 좌표 입력 (0~5): ");
			int rowPoint = sc.nextInt();
			if (rowPoint == 99) {
				System.out.println("프로그램 종료");
				break;
			}
			System.out.print("열의 좌표 입력 (0~5): ");
			int colPoint = +sc.nextInt();

			String arr[][] = new String[6][6];
			arr[rowPoint][colPoint] = "X";

			System.out.println("  0 1 2 3 4");
			for (int row = 0; row < arr.length - 1; row++) {
				System.out.print(row + " ");
				for (int col = 0; col < arr[col].length - 1; col++) {
					if (arr[row][col] == arr[rowPoint][colPoint])
						arr[row][col] = "X";
					else
						arr[row][col] = " ";
					System.out.print(arr[row][col] + " ");
				}
				System.out.println();
			}
		}
	}

	public void binGo() {
//		1. 빙고판 크기를 입력 받아, 그 크기 만큼의 행과 열을 가지는 2차원 배열(빙고판)을 생성하고
//		1부터 크기*크기 사이의 정수 난수를 무작위 배치.
//		2. 정수를 입력 받아 빙고판에서 일치하는 부분을 찾아 해당 부분의 숫자를 “★“로 변경하고
//		현재 빙고 카운트가 몇인지 출력.
//		단, 빙고판에 없는 정수를 입력한 경우 “다시 입력해주세요.“ 출력
//		3. 가로, 세로, 대각선 한 줄이 모두 “★“로 변경되어 있을 경우 빙고 카운트를 1 증가
//		4. 빙고카운트가 3이상이 되면 “***Bingo!***” 를 출력하고 프로그램 종료.

		Scanner sc = new Scanner(System.in);
		System.out.print("빙고판 크기 입력 : ");
		int size = sc.nextInt();
		int star = 0; // 빙고의 위치를 저장
		int arr[][] = new int[size][size];
		int temp1 = 0;
		int temp2 = 0;
		int num = 1;

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				arr[row][col] = num++;
			}
		} // 빙고판 그리는 구문 끝 처음 빙고는 한번만 그려져야 한다.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int ran1 = (int) (Math.random() * size);
				int ran2 = (int) (Math.random() * size);

				temp1 = arr[i][j];
				temp2 = arr[ran1][ran2];

				arr[i][j] = temp2;
				arr[ran1][ran2] = temp1;
			}
		} // 임의의 값과 항상 섞이게하는 난수.

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				System.out.printf(" %2d ", arr[row][col]);
			}
			System.out.println();
		}

		System.out.println("===== 빙고 게임 시작=======");

		while (true) { // 빙고의대입
			System.out.print("\n정수 입력 : ");
			star = sc.nextInt();

			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					if (star == arr[row][col]) {
						System.out.printf(" %2c ", '★');
						arr[row][col] = 99;
					} else if (arr[row][col] == 99) {
						System.out.printf(" %2c ", '★');
					} else {
						System.out.printf(" %2d ", arr[row][col]);
					}
				}

				System.out.println();
			}

			int bingo = 0;
			int left = 0;
			int right = 0;

			for (int i = 0; i < size; i++) {// 행을 반복

				int row = 0;// 행 초기화
				int col = 0;// 열 초기화

				for (int j = 0; j < size; j++) { // 열을 반복
					if (arr[i][j] == 99)
						row++; // arr i행 j열이 별 일때 row증가
					if (arr[j][i] == 99)
						col++; // arr j행 i열이 별일 때 col증가 i 한번에 j가 한바퀴 도는걸 참조해 새로를 비교하는것
					if (i == j && arr[i][j] == 99)
						left++; // 왼쪽부터 내려오는 대각선
					if ((i + j) == size - 1 && arr[i][j] == 99)
						right++; // 오른쪽 부터 내려오는 대각선
				}
				if (row == size)
					bingo++; // 세로 빙고 카운트
				if (col == size)
					bingo++; // 가로 빙고 카운트
			}
			if (left == size)
				bingo++; // 대각선은 하나의 반복안에서 조회해야 함으로 밖에서 검사함.
			if (right == size)
				bingo++;
			if (bingo >= 3)
				break;
			System.out.printf("%d 빙고 입니다!\n", bingo);
			System.out.println("******************************");
		} // while문 반복끝

		System.out.println("************3 빙고**************");
	}

}