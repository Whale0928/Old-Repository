package edu.kh.control.partice;

import java.util.Scanner;

public class LoopPractice {

	public void practice1() {
//		사용자로부터 한 개의 값을 입력 받아 1부터 그 숫자까지의 숫자들을 모두 출력하세요.
//		단, 입력한 수는 1보다 크거나 같아야 합니다.
//		만일 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요“를 출력하세요.

		Scanner sc = new Scanner(System.in);
		System.out.print("1이상의 수를 입력하세요. : ");
		int input = sc.nextInt();
		if (input <= 0) {
			System.out.println("1 이상의 숫자를 입력해주세요.");
		} else {
			for (int i = 1; i <= input; i++) {
				System.out.print(i + "  ");
			}
		}
	}

	public void practice2() {
//		사용자로부터 한 개의 값을 입력 받아 1부터 그 숫자까지의 모든 숫자를 거꾸로 출력하세요.
//		단, 입력한 수는 1보다 크거나 같아야 합니다.

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		if (input <= 0) {
			System.out.println("1이상의 수를 입력해주세요");
		} else {
			for (int i = input; i >= 1; i--) {
				System.out.print(i + "  ");

			}
		}

	}

	public void practice3() {

//		1부터 사용자에게 입력 받은 수까지의 정수들의 합을 for문을 이용하여 출력하세요.
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력: ");
		int input = sc.nextInt();
		int sum = 0;
		if (input <= 0) {
			System.out.println("1이상의 수를 입력해주세요.");
		} else {
			for (int i = 1; i <= input; i++) {
				sum += i;
				if (i >= input) {
					System.out.print(i + "=" + sum);
				} else {
					System.out.print(i + "+");
				}
			}
		}
	}

	public void practice4() {
//		사용자로부터 두 개의 값을 입력 받아 그 사이의 숫자를 모두 출력하세요.
//		만일 1 미만의 숫자가 입력됐다면 “1 이상의 숫자를 입력해주세요“를 출력하세요.

		Scanner sc = new Scanner(System.in);
		System.out.print("1번째 정수 입력 : ");
		int input1 = sc.nextInt();

		if (input1 <= 0) {
			System.out.println("1 이상의 수를 입력.");
		} else {
			System.out.print("2번째 정수 입력 : ");
			int input2 = sc.nextInt();
			if (input1 == input2) {
				System.out.print(input1 + "사이의 값이 없습니다.");
			} else if (input1 > input2) {
				// 1번 입력이 2번 입력보다 클 경우
				for (int i = input2; i <= input1; i++) {
					System.out.print(i + "  ");
				}
			} else {
				for (int i = input1; i <= input2; i++) {
					System.out.print(i + "  ");
				}
			}
		}
	}

	public void practice5() {
		// 사용자로부터 입력 받은 숫자의 단을 출력하세요.
		Scanner sc = new Scanner(System.in);
		System.out.print("원하는 구구단 입력 : ");
		int dan = sc.nextInt();
		System.out.println("======" + dan + "단======");
		for (int i = 1; i <= 9; i++) {
			System.out.printf("%2d X %2d = %3d\n", dan, i, dan * i);
		}

	}

	public void practice6() {
//		사용자로부터 입력 받은 숫자의 단부터 9단까지 출력하세요.
//		단, 2~9를 사이가 아닌 수를 입력하면 “2~9 사이 숫자만 입력해주세요”를 출력하세요.
		Scanner sc = new Scanner(System.in);
		System.out.print("원하는 구구단 입력 : ");
		int dan = sc.nextInt();

		if (1 >= dan || dan >= 10) {
			System.out.println("2~9사이의 값을 입력해주세요.");
		} else {
			for (int x = dan; x <= 9; x++) {
				System.out.println("======" + x + "단======");
				for (int i = 1; i <= 9; i++) {
					System.out.printf("%2d X %2d = %3d\n", x, i, x * i);
				}
			}
		}
	}

	public void practice7() {
//		ex.
//		정수 입력 : 4
//		*
//		**
//		***
//		****
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		if (input <= 0) {
			System.out.println("1이상의 수를 입력하세요");
		} else {
			for (int x = 1; x <= input; x++) {
				for (int i = 1; i <= x; i++) {
					System.out.print("*");
				}
				System.out.println();
			}

		}

	}

	public void practice8() {
//		ex.
//		정수 입력 : 4
//		****
//		***
//		**
//		*

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		if (input <= 0) {
			System.out.println("1이상의 수를 입력하세요");
		} else {
			for (int x = input; x >= 1; x--) {
				for (int i = 1; i <= x; i++) {
					System.out.print("*");
				}
				System.out.println();
			}

		}

	}

	public void practice9() {

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		if (input <= 0) {
			System.out.println("1이상의 수를 입력하세요");
		} else {
			for (int i = 1; i <= input; i++) {
				for (int ii = input; ii >= 1; ii--) {
					if (i < ii) {
						System.out.print(" ");
					} else {
						System.out.print("*");
					}
				}
				System.out.println();
			}
		}
	}

	public void practice10() {
//		*
//		**
//		***
//		**
//		*
// 구현하기.
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();

		if (input <= 0) {
			System.out.println("1이상의 수를 입력하세요");
		} else {
			for (int x = 1; x < input; x++) {
				for (int y = 1; y <= x; y++) {
					System.out.print("*");
				}
				System.out.println();
			}
			for (int i = input; i >= 1; i--) {
				for (int iI = i; iI >= 1; iI--) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
	}

	public void practice11() {
		// 나무 그리기

		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int num = sc.nextInt();
		if (num <= 0) {
			System.out.println("1이상의 수를 입력하세요");
		} else {

			for (int i = 1; i <= num; i++) {
				for (int ii = num; ii >= 1; ii--) {
					if (i < ii) {
						System.out.print(" ");
					} else {
						System.out.print("*");
						if (i > ii) {
							System.out.print("*");
						}
					}
				}
				System.out.println();
			}

		}

	}

	public void practice12() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수입력 : ");
		int num = sc.nextInt();

		if (num < 0) {
			System.out.println("1이상의 수를 입력하세요.");
		} else {

			for (int i = 1; i <= num; i++) {
				// 입력한 만큼 반복 시키는 반복문
				if (i == num || i == 1) {
					for (int x = 1; x <= num; x++) {
						System.out.print("*");
					}
					System.out.println(); // 줄 띄우기.
						} else {
							for (int x = 1; x <= num; x++) {
								if (x == 1 || x == num) {
								System.out.print("*");
								} else {
								System.out.print(" ");
						}
					}
					System.out.println();
				}
			}
		}
	}

	public void practice13() {

//		1부터 사용자에게 입력 받은 수까지 중에서
//		1) 2와 3의 배수를 모두 출력하고
//		2) 2와 3의 공배수의 개수를 출력하세요.
//		* ‘공배수’는 둘 이상의 수의 공통인 배수라는 뜻으로 어떤 수를 해당 수들로 나눴을 때
//		모두 나머지가 0이 나오는 수

		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력하세요 : ");
		int input = sc.nextInt();
		int count = 0;
		for (int i = 1; i <= input; i++) { // 입력된 값까지 나눠보기 위한 변수
			if (i % 2 == 0 || i % 3 == 0) {
				System.out.print(i + "  ");
				if (i % 2 == 0 && i % 3 == 0) {
					count++;
				}
			}
		}
		System.out.print("\ncount : " + count);
	}

}// 메인메소드 괄호
