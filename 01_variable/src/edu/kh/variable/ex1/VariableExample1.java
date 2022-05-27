package edu.kh.variable.ex1;

public class VariableExample1 {
	//생성된 .java파일의 이름과 class옆의 이름은 === 이여야 한다

	public static void main(String[] args) {

		System.out.println(2 * 3.141592653589793 * 10);
		System.out.println(3.141592653589793 * 10 * 10);
		System.out.println(3.141592653589793 * 10 * 10 * 20);
		System.out.println(4 * 3.141592653589793 * 10 * 10);
		
		
		/*변수(Variable)
		값을 기록하는 공간
		값(data)가 변할 수 있어 변할수 있는 값을 의미한다.
		
		변수의 여러종류가 있다
		종류마다 형태와 크기가 다르다
		 */
		
		double pi = 3.141592653589793;
		int r = 10; // 반지름(radius)
		int h = 20; // 높이 (height)

		
		System.out.println("---------------경계선-------------");
		System.out.println(2*pi*r); //원의 둘레
		System.out.println(pi*r*r); //원의 높이
		System.out.println(pi*r*r*h); //원기둥의 부피 계산식
		System.out.println(4*pi*r*r); //구의 겉넒이
		
		
		//변수의 장점 
		//가독성의 증가
		//재 사용성 증가 (반복사용이 쉬워짐)
		//코드의 길이가 감소한다
		//유지보수성이 상승한다 (코드 수정이 간단해진다)
		//
		}
}
