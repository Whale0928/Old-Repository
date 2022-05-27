package edu.kh.oop.abstraction.model.vo;

//vo (Value Object): 값 저장용 객체를 만들기 위한 클래스를 모아두는 패키지
//

public class People { // 국민 클래스
	// 클래스란?? 객체의 속성 (속성/기능)을 정의한 것
	// == 객체를 만들기 위한 설계도.]

	// 캡슐화 (Encapsulation) ]
	// - 데이터와 기능을 하나로 묶어서 관리하는 기법
	// - 데이터의 직접적인 접근을 제한 하는것이 원칙이다
	// -> 직접 접근을 목하기 떄문ㅇ ㅔ
	// 클래스 내부에 간접 접근 방법을 제공하는 기능을 작성해둔다.

	// 속성 == 값 ==data <<< 여기에 직접 접근을 제한한다
	// (값을 저장하기 위한 변수 선언)
	// -> 국민이라면 '공통'적으로 가지고있는 속성만 작성(추상화(
	// 이름 , 성별 , 주민번호 , 주소, 전화번호,나이

	private String name;
	private char gender;
	private String pNum; // ' - ' 때문에 string
	private String address;
	private String phone;
	private int age;

	// **데이터 직접 접근 제한***
	// private (공공적인) - > private(사적인,개인적인) 변경

	// 기능 == 행동 ==method
	public void tax() {
		System.out.println("세금을 뻇김니다.");
	}

	public void vote() {
		System.out.println("투표를 합니다(권고)");
	}

	// 캡슐화에서 사용할 간접 접근 기능 : ( getter / setter )
	// 단방향 통신

	public void setName(String name) { // setter
		this.name = name;
		// 외부로 부터 전달 받은 name을
		// 현재 객체의 속성중 name( == this.name) 에 대입한다.
	}

	public String getName() {
		return name;
		// 현재 객체의 속성중 name을
		// 호출한 곳으로 반환(retrun)
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// alt + shift +s getter/setter
	// select  All
	// Generat

	// 속성과 기능을 하나로 묶어 관리한다. 캡슐화

}