package edu.kh.oop.abstraction.model.service;

import edu.kh.oop.abstraction.model.vo.People;

//Service : 특정기능(비지니스 로직)을 제공하는 클래스

public class AbstractionService {

	// 해당 클래스가 객체가 되면 아래 예제 기능을 수행 할 수 있다.

	public void ex1() {
		// 국민 객체 만들기.

		People p1 = new People();
		// People p1: 객체의 주소를 저장하여 참조하는 변수 p1
		// new People( : 새로운 People 객체를 Heap영역에 생성

		// *** 클래스 이름이 자료형 처럼 사용되어짐 !
		// == 그래서 클래스를 "사용자 정의 자료형"이라고도 한다 ***

		People p2 = new People();
		// 만들어진 객체는 추상화가 적용되어 있어 누군지는 정확하게는 모른다.
		// ->속성(데이터)를 추가해서 누구인지 알 수 있게 한다(구체화);

		//p1.name = "홍길동";
		p1.setName("홍길동");
		
		//p1.age = 25;
		p1.setAge(25);
		
		//p1.gender = '남';
		p1.setGender('남');
		
		//p1.phone = "010-1234-5678";
		p1.setPhone("010-1234-1234");

		//p1.pNum = "980303-1234567";
		p1.setpNum("980928-1234567");
		
		//p1.address = "서울시 중구 남대문로 120 대일빌딩 2층 A강의장 맨 앞자리.";
		p1.setAddress("서울시 중구 남대문로 120 대일빌딩 2층 A강의장 맨 앞자리.");
		
		
		// p1은 홍길동을 지칭한다.

		System.out.println("p1의 name : " + p1.getName());
		System.out.println("p1의 age : " + p1.getAge());
		System.out.println("p1의 gender : " + p1.getGender());
		System.out.println("p1의 phone : " + p1.getPhone());
		System.out.println("p1의 pNo : " + p1.getpNum());
		System.out.println("p1의 address : " + p1.getAddress());
		p1.tax();
		p1.vote();
		
		System.out.println("========================");
	

//		p2.setName("김현기");
//		p2.age = 25;
//		p2.gender = '남';
//		p2.phone = "010-9916-1042";
//		p2.pNum = "9800928-1234567";
//		p2.address = "경기 용인 처인 *** .";
//		
//
//		System.out.println("p2의 name : " + p2.name);
//		System.out.println("p2의 age : " + p2.age);
//		System.out.println("p2의 gender : " + p2.gender);
//		System.out.println("p2의 phone : " + p2.phone);
//		System.out.println("p2의 pNo : " + p2.pNum);
//		System.out.println("p2의 address : " + p2.address);
//
//		p2.tax();
//		p2.vote();
		

		
		
	}

}

