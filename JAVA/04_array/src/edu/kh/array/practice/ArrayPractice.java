package edu.kh.array.practice;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayPractice {

	public void practice1() {
//		길이가 9인 배열을 선언 및 할당하고, 1부터 9까지의 값을 반복문을 이용하여
//		순서대로 배열의 각 인덱스 요소에 대입하고 출력한 후
//		짝수 번째 인덱스 값의 합을 출력하세요. (0 번째 인덱스는 짝수로 취급)

		int[] arr = new int[9];
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
			System.out.print(arr[i] + "  ");
			if (i % 2 == 0) {
				sum += (i + 1);
			}
		}
		System.out.println("합계 : " + sum);

	}

	public void practice2() {
//		길이가 9인 배열을 선언 및 할당하고, 9부터 1까지의 값을 반복문을 이용하여
//		순서대로 배열의 각 인덱스 요소에 대입하고 출력한 후
//		홀수 번째 인덱스 값의 합을 출력하세요. (0 번째 인덱스는 짝수로 취급)

		int arr[] = new int[9];
		int sum = 0;
		for (int i = 8; i >= 0; i--) {
			arr[i] = i + 1;
			System.out.print(arr[i] + "  ");
			if (i % 2 != 0) {
				sum += i + 1;
			}
		}
		System.out.println("\n홀수 번호 인덱스 합계: " + sum);
	}

	public void practice3() {
//		사용자에게 입력 받은 양의 정수만큼 배열 크기를 할당하고
//		1부터 입력 받은 값까지 배열에 초기화한 후 출력하세요.

		Scanner sc = new Scanner(System.in);
		System.out.print("목표치 입력 : ");
		int num = sc.nextInt();

		int arr[] = new int[num];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
			System.out.print(arr[i] + " ");
		}

	}

	public void practice4() {
//		정수 5개를 입력 받아 배열을 초기화 하고
//		검색할 정수를 하나 입력 받아 배열에서 같은 수가 있는 인덱스를 찾아 출력.
//		배열에 같은 수가 없을 경우 “일치하는 값이 존재하지 않습니다“ 출력
		Scanner sc = new Scanner(System.in);

		int arr[] = new int[5];

		for (int i = 0; i < 5; i++) {

			System.out.print("정수 1  입력 : ");
			arr[i] = sc.nextInt();

		} // for 반복 끝부분

		// 입력받은 값을 확인하는 문 작성
		System.out.print("검색 할 값 입력 : ");
		int find = sc.nextInt();
		String result = "";
		for (int i = 0; i < 5; i++) {
			// 여기 안에서 배열 5개를 비교해 봐야함
			if (find == arr[i]) {
				result = ("인덱스 번호 : " + i);
				break;
			} else {
				result = ("일치하는 값이 없습니다.");
			}
		}
		System.out.println(result);

	}

	public void practice5() {
//		문자열을 입력 받아 문자 하나하나를 배열에 넣고 검색할 문자가 문자열에 몇 개 들어가 있는지
//		개수와 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요

//		문자열 : application
//		문자 : i
//		application에 i가 존재하는 위치(인덱스) : 4 8
//		i 개수 : 2

		Scanner sc = new Scanner(System.in);
		System.out.print("원하는 단어 입력 : ");
		String str = sc.nextLine();

		char arr[] = str.toCharArray(); // 구글에서 얻어온 함수
		// String arr[] = str.split(""); //구글에서 얻어온 함수

		System.out.print("검색할 문자 입력(한글자만) : ");
		char search = sc.next().charAt(0); // 검색할 값 입력 변스.

		int count = 0;// 곂치는 개수 저장용 변수

		System.out.printf("%s에 %c가 존재하는 위치 : ", str, search);

		// 반복하면서 검색해보고 출력하는 문구
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == search) {
				count++;
				System.out.print(i + 1 + " ");
			}
		}
		System.out.printf("\n%c의 개수: %d", search, count);
	}

	public void practice6() {
//		사용자가 배열의 길이를 직접 입력하여 그 값만큼 정수형 배열을 선언 및 할당하고
//		배열의 크기만큼 사용자가 직접 값을 입력하여 각각의 인덱스에 값을 초기화 하세요.
//		그리고 배열 전체 값을 나열하고 각 인덱스에 저장된 값들의 합을 출력하세요.
//		
//		정수 : 5
//		배열 0번째 인덱스에 넣을 값 : 4
//		배열 1번째 인덱스에 넣을 값 : -4
//		배열 2번째 인덱스에 넣을 값 : 3
//		배열 3번째 인덱스에 넣을 값 : -3
//		배열 4번째 인덱스에 넣을 값 : 2
//		4 -4 3 -3 2
//		총 합 : 2
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int array = sc.nextInt();
		int result = 0; // 총 합을 저장할 변수.

		int arr[] = new int[array]; // 배열 선언

		for (int i = 0; i < arr.length; i++) {

			System.out.print(i + "번째 인덱스에 넣을 값 : ");
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
			result += arr[i];
		}
		System.out.println("\n총합 : " + result);

	}

	public void practice7() {
//		주민등록번호 번호를 입력 받아 char 배열에 저장한 후 출력하세요.
//		단, char 배열 저장 시 성별을 나타내는 숫자 이후부터 *로 저장하세요.

//		[실행 화면]
//		주민등록번호(-포함) : 123456-1234567
//		123456-1******

		Scanner sc = new Scanner(System.in);
		System.out.print("주민등록번호(- 포함): ");
		String input = sc.next();
		char num[] = input.toCharArray();

		for (int i = 0; i < 13; i++) {
			if (i >= 8) {
				System.out.print("*");
			} else {
				System.out.print(num[i]);
			}

		}

	}

	public void practice8() {
//		3이상인 홀수를 입력 받아 배열의 중간까지는 1부터 1씩 증가하여 오름차순으로 값을 넣고,
//		중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어 출력하세요.

		// 단, 입력한 정수가 홀수가 아니거나 3 미만일 경우 “다시 입력하세요”를 출력하고
//		다시 정수를 받도록 하세요.

//		정수 : 4
//		다시 입력하세요.
//		정수 : -6
//		다시 입력하세요.
//		정수 : 5
//		1, 2, 3, 2, 1

		while (true) { // 오 입력을 반복하기 위한 한무 반복문

			Scanner sc = new Scanner(System.in);
			System.out.print("3이상의 홀수 입력 : ");

			int input = sc.nextInt(); // 음수 입력이 왜 안되지? !!배열이 소수점으로 입력 되있었기 때문에

			if (input < 3 || input % 2 == 0) {
				System.out.println("다시 입력하세요.");
				continue;
			} else {

				int[] arr = new int[input];
				int x = (arr.length / 2); // 하나의 for문안에서 값을 다시 깍아버리기 위한 값.

				for (int i = 0; i < arr.length; i++) { // 배열의 길이 만큼 반복한다.
					if (i == 0 || i < input / 2 + 1) {
						arr[i] = i + 1;
						System.out.print(arr[i] + "  ");
					} else {
						arr[i] = x;
						System.out.print(arr[i] + "  ");
						x--;
					}
				}
				break;
			}
		}
	}// while문 끝

	public void practice9() {
//		10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고,
//		1~10 사이의 난수를 발생시켜 배열에 초기화한 후 출력하세요.
//		[실행 화면]
//		발생한 난수 : 9 7 6 2 5 10 7 2 9 6 

		int[] arr = new int[10];

		// 0.0 <= x < 1.0
		// 0.0 <= x * 3 <3.0
		// 1.0 <= x *3+1 <4.0
		// 1 <= (int)(x *3+1) <4
		// ==1 이상 4미만 난수 생성

		System.out.print("발생한 난수 : ");
		for (int i = 0; i < 10; i++) {
			int randam = (int) (Math.random() * 10); // 랜덤 발생 구간

			arr[i] = randam + 1;
			System.out.print(arr[i] + "  ");
		}

	}

	public void practice10() {
//		10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고,
//		1~10 사이의 난수를 발생시켜 배열에 초기화 후
//		배열 전체 값과 그 값 중에서 최대값과 최소값을 출력하세요.
//		[실행 화면]
//		발생한 난수 : 5 3 2 7 4 8 6 10 9 10
//		최대값 : 10
//		최소값 : 2

		int min = 0; // 최소값 저장용
		int max = 0; // 최대값 저장용

		int arr[] = new int[10];
		System.out.print("발생한 난수 : ");
		for (int i = 0; i < 10; i++) {
			int random = (int) (Math.random() * 10);
			arr[i] = random + 1;
			System.out.print(arr[i] + "  ");
//			if (i == 0 || arr[i - 1] <= arr[i]) {
//				max = arr[i];
//			} else if (i == 0 || arr[i - 1] > arr[i]) {
//				min = arr[i];   수업 듣기전에 구현해둔 부분.
//			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			} else if (arr[i] < min) {
				min = arr[i];
			}
		}
		System.out.println("\n최대값 : " + max);
		System.out.println("최소값 : " + min);

	}

	public void practice11() {
//		10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고
//		1~10 사이의 난수를 발생시켜 중복된 값이 없게 배열에 초기화한 후 출력하세요.
		int[] num = new int[10];

		for (int i = 0; i < num.length; i++) {
			num[i] = (int) (Math.random() * 10 + 1); // num[i]에는 순차적으로 1~10사이의 난수가 들어가고있음.

			// 0.0 <= x(난수) < 10 ... 10 미만이기 때문에 곱한 후 1을 더해 1~10을 대입한다.

			// 아래 반복문에서 중복을 검사.
			for (int x = 0; x < i; x++) { // i의 초기 값부터 대칭해보기 시작한다.
				if (num[i] == num[x]) {
					i--;
					break;
				}
			}
		}
		Arrays.sort(num); // 확인을 편하게 하기 위한 순차 정렬 메소드

		System.out.println("난수 : " + Arrays.toString(num));

	}

	public void practice12() {
//		로또 번호 자동 생성기 프로그램을 만들기.
//		(중복 값 없이 오름차순으로 정렬하여 출력하세요.)
		int[] arr = new int[9];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 45 + 1); // 1이상 45이하를 만들기 위해 45을 곱한후 1을 만들어 0초과 46미만으로 만든다.

			for (int x = 0; x < i; x++) { // x 변수 하나를 생성 시켜 i의 0번째 값부터
				if (arr[i] == arr[x]) { // x는 난수 값중 하나를 순차적으로 넣으면서 값이 들어가있는 arr[i]를 조사한다.
					i--;
					break;
				}
			}
		}

		Arrays.sort(arr);
		System.out.println("로또 번호: " + Arrays.toString(arr));
	}

	public void practice13() {
		// 문자열을 입력 받아 문자열에 어떤 문자가 들어갔는지 배열에 저장하고
		// 문자의 개수와 함께 출력하세요.

//		[실행 화면]
//			문자열 : application
//			문자열에 있는 문자 : a, p, l, i, c, t, o, n
//			문자 개수 : 

		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 입력 : ");
		String input1 = sc.next();

		System.out.print("문자열에 있는 갯수 : ");
		int count = 0;
		char cNull = '\0';
		char[] arr = new char[input1.length()];

		for (int i = 0; i < arr.length; i++) {

			arr[i] = input1.charAt(i);
			boolean flag = true;

			for (int x = 0; x < i; x++) {
				if (arr[x] == arr[i]) {
					flag = false;
				}
			}

			if (flag) {
				System.out.print(arr[i] + ",");
				count++;
			}
		}
		System.out.println("\n문자 갯수 :" + count);
	}

	public void practice14() {
		Scanner sc = new Scanner(System.in);

		System.out.print("배열의 크기를 입력하세요 : ");
		String[] arr = new String[sc.nextInt()]; // 입력받은 정수만큼 바로 배열 할당함

		int point = 0; // 배열의 끝을 기억할 변수

		while (true) {
			for (int i = point; i < arr.length; i++) {
				System.out.print((i + 1) + "번째 문자열 : ");
				arr[i] = sc.next();
			}
			point = arr.length;

			System.out.print("값을 추가적으로 입력 하시겠습니까?  (y/n): ");
			char plus = sc.next().charAt(0);

			if (plus == 'y' || plus == 'Y') {
				System.out.print("추가 할 배열의 개수를 입력하세요 : "); // 추가 하기 원하는 값을 입력
				String[] copyArr = new String[arr.length + sc.nextInt()]; // 배열 추가 선언 할당

				System.arraycopy(arr, 0, copyArr, 0, arr.length);
				arr = copyArr; // 배열을 확장한 후에 다시 arr에 대입해서 위의 문자열 입력 반복문으로 보내 실행한다.

			} else if (plus == 'n' || plus == 'N') {
				System.out.println(Arrays.toString(arr));
				break;
			} else {
				System.out.println("잘못 입력 하셧습니다.");
				continue;
			}
		}
	}
}