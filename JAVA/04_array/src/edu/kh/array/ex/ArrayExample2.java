package edu.kh.array.ex;

import java.util.Arrays;

public class ArrayExample2 {

	// 얕은 복사 (shallow : 얕은)
	public void shallowCopy() {

		int[] arr = { 1, 2, 3, 4, 5 };

		// 얕은 복사 진행
		int[] copyArr = arr; // 주소만 복사.

		// System.out.println(Arrays.toString(copyArr));
		System.out.println("주소 확인");
		System.out.println("arr : " + arr);
		System.out.println("copyArr : " + copyArr);

		// 배열 값 변경.
		System.out.println("변경전");
		System.out.println("arr : " + Arrays.toString(arr));
		System.out.println("copyArr : " + Arrays.toString(copyArr));

		// 얕은 복사한 배열의 값을 변경.
		copyArr[2] = 999;

		System.out.println("변경후");
		System.out.println("arr : " + Arrays.toString(arr));
		System.out.println("copyArr : " + Arrays.toString(copyArr));

	}

	// 깊은 복사
	public void deepCopy() {
		// 같은 자료형의 새로운배열을 만들어서
		// 기존 배열의 데이터를 모두 복사하는 방법.

		int[] org = { 1, 2, 3, 4, 5 }; // 원본

		// 1.)For문을 이용한 깊은 복사
		int[] copyArr1 = new int[org.length];
		for (int i = 0; i < org.length; i++) {
			copyArr1[i] = org[i];
		}

		// 2.) System.arraycopy
		// (원본배열, 원본 복사 시작 인덱스, 복사배열, 복사배열의 삽입 시작 인덱스, 복사길이);
		int[] copyArr2 = new int[org.length];
		System.arraycopy(org, 0, copyArr2, 0, org.length);

		// 3.)볷할 배열 참조 변수 = Arrays.copyOf(원본 배열 , 복사할 길이);
		int[] copyArr3 = Arrays.copyOf(org, org.length);

		copyArr1[4] = 0;
		copyArr2[4] = 99;
		copyArr3[4] = 99999;
		System.out.println("arr 배열 : " + Arrays.toString(org));
		System.out.println("copyArr1 배열 : " + Arrays.toString(copyArr1));
		System.out.println("copyArr2 배열 : " + Arrays.toString(copyArr2));
		System.out.println("copyArr3 배열 : " + Arrays.toString(copyArr3));

	}

	// 배열을 이용한 중복 데이터 제거 + 정렬

	public void createLottoNumber() {
		// 로또 번호 생성기.
		// 1. 1~45 사이 중복 되지 않은 난수 6개를 생성
		// 2. 생성된 난수가 오름차 순으로 정렬

		int[] lotto = new int[6]; // 로또 번호 여섯개 선언 및 할당.

		// 3) 생성된 배열을 처음부터 끝까지 순차 접근하는 for문 작성
		for (int i = 0; i < lotto.length; i++) {
			// 4) 생성된 난수를 순서대로 배열 요소에 대입
			int random = (int) (Math.random() * 45 + 1);
			lotto[i] = random;

			// 5)중복검사를 위한for문 작성
			for (int x = 0; i > x; x++) {
				// 6)생성된 난수와 같은 수가
				// 앞쪽 요소에 있는지 검사
				if (random == lotto[x]) {
					i--;
					// i 가 1씩 증가할 때 마다 난수가 하나 생성된다.
					// 이 for 문을 멈추고 다시 난수를 생성한다.
					// i는 기본적으로 0~5까지 6번만 반복하지만
					// i 값을 강제로 1 감소 시켜 7회 반복되게하는 모양을 만든다.

					break;
					// 가장 인접한 for문법을 멈춰버리는 분기문 : break;
					// 남은 값은 비교할 필요성이 없음
				}
			}
		} // for문 끝

		// Arrays .sort (배열명) : 배열 내 값들이 오름차순으로 정렬됨.

		Arrays.sort(lotto); // 배열 오름차순 정렬 유틸
		System.out.println(Arrays.toString(lotto));
	}
	
	public void simple() {
		int[][] arr = new int[1][2];
		int[] arr1 = new int[2];
		System.out.println(arr);
	}
}
