package edu.kh.variable.ex1; //해당 패지키에 포함되어 있다.

public class VariableExample2 {

	public static void main(String[] args) {
		
		/* 자바 기본 자료형
		 * 논리형 :  blooean (1byte)
		 * 정수형 : byte , short, int, long (1,2,4,8)
		 * 실수형 : float(4byte) , double(8byte)
		 * 문자형 : char (2byte , 유니코드) */
		
		//변수 선언 : 메모리에 값을 저장한 공간을 할당 하는 것이다.
		//변수의 값을 대입 : 변수에 값을 집어넣는다(대입한다).
		
		//연결되는 첫 단어의 첫 글자를 대문자로 표현한다 카멜표기법
		
		boolean booleanData;
		//boolean이라는 논리값을 저장할 공간을  1byte 할당했다.
		//할당된 공간을 booleanData라고 통용한다.
		
		booleanData = true;
		System.out.println("booleanData: " + booleanData);
		
		
		byte byteNum = 127;
		System.out.println(byteNum);
		//byte이라는 정수값 을 저장할 공간을  1byte 할당했다.
		//할당된 공간을 byteNum라고 통용한다.
		//선언된 byteNum변수에 초기값으로 정수 127을 부여했다.
		// -->초기화 : 첫 변수에 값을 대입한다.
		
		short shortNum = 32767;
		//short라는 정수값 을 저장할 공간을  1byte 할당했다.
		//할당된 공간을 shortNum라고 통용한다.
		
		
		
		// 회사 프로젝트에 short/byte 있으면 탈출해라
		// 회사 프로젝트에 short/byte 있으면 탈출해라
		// 회사 프로젝트에 short/byte 있으면 탈출해라
		
		
		
		int intNum = 214700000;  //자료형 변수명 = 리터럴(Iteral) /상수라는 단어의 대체어
		//int라는 정수값 을 저장할 공간을  1byte 할당했다.
		//할당된 공간을 intNum라고 통용한다.
		
		//iteral : 변수에 대입되거나 작성 되어지는 값 자체.
		//+ 자료형에 따라 iteral 표기법이 다르다.
	
		
		long longNum = 10000000000000000l; 
		//L을 수의 끝에 붙임으로서 롱이라는 자료형을 인지시킨다 - iteral 표기법
		//변수선언 및 초기화;
		
		float floatNum = 0.12345f;
		//리터럴에 f를 붙여 실수를 인정시킨다
		//c언어에서만 사용 
		//모바일에서 c로 float형으로 data를 전달할 경우가 있다.
		
		double doubleNum = 3.1415;
		//double형은 기본적으로 . 소수점을 쓰면 자바에서 기본 실수형으로 인지한다.
		//float형일 경우에만 f를 첨가한다.
		
		
		char ch1 = 97; //문자열은 문자열 단 하나만 대입 가능하다.
		char ch2 = 'A';
		System.out.println(ch1);
		// ASCII CODE에 맴핑되어있는 주소로 변환되어 저장된다
		// 애초에 아스키코드로 저장이 가능하다
		
		String Str = "Lemon";
		//참조형
		//char은 '' string은 ""
		
		
		//변수 명령 규칙
		//하면 '안'되는 것 기억하기
		
		//	 1.대소문자가 구별되고 , 길이제한이 없다.
		int dsadqwqwesd; //소문자 d
		int dsadqwqwesD; //대문자 D
		// 	1. 예약어 사용금지  코드 쓰면 색 변하는 것들
		double double1;
		// syntax error == 문법의 오류이다. 
		//	2. 숫자로 시작은 '안'됨
		
		//	3.특수문자 $,\ 만 사용가능 but 사용 '안'한다.
		int $$$$; // 문제 없이 실행되지만 개발자가 직접 사용하지는 않는다.
		int int_num; //카멜표기법을 사용한다. 언더바 작성 표기법은 DB에서 사용한다.
		
		//카멜 표기법
		// - > 변수명 작성 시 여러 단어를 이어서 작성하는 경우
		//	   띄어쓰지 않고 후속 단어의 첫 글자를 '대'문자로 사용하는 것.
		
		char helloWorldBananaLemon;
	
		//6.변수명은 언어를 가리지않는다.
		int 정수1;
		double 실수1;
		char 火 = '불';
		System.out.println(火);
		//하지만 쓰지 않는다.

		//카멜 표기법만 쓰자 그냥
		//카멜 표기법만 쓰자 그냥
		//카멜 표기법만 쓰자 그냥
		
		
		
		
		
		int num = 10;
		System.out.println(num);
		
		num = 20;
		System.out.println(num);
		
		
		//상수 (항상 같은 수)
		// 변수의 한 종류이지만
		// 한번 data가 대입되면 다른 값으로 대체 불가능.
		
		final double PI_VALUE = 3.14; //더 이상 리터럴를 대체 할 수 없다.
		// pi=123.0;
		System.out.println(PI_VALUE);
		
		//상수 명령 규칙
		//상수는 모두 대문자.
		//상수는 언더바로 구별한다
		
		//상수를 사용하는 경우
		// 1) 변하면 안되는 고정된 값
		// 2) 특정한 값이 의미가 부여된 경우
		
	
		// 문자+숫자는 이어 쓰기된다.
		
		//형 변환.
		//자료의 형태를 바꾸는 것.
		
		//자동 형 변환.
		//강제 형 변환.
		
		
		// 컴퓨터의 값 처리 원칙
		// 1) 같은 자료형만 대입 가능.
		// 2) 같은 자료형만 계산 가능.
		// 3) 계산의 결과도 같은 종류의 값이 나와야함.
		
		
		System.out.println(1+1.3); //알아서 실수형으로 변한다.
		// int + int = int 
		// int + double = error
		
		//자동 형 변환 (Casting)
		//컴파일러(2진수 번역기)가 자동으로 값의 범위가 작은 자료형을 값의 범위가 큰 자료형으로 변환한다.
		
		//강제 형 변환 
		
	}

}
