package edu.kh.inheritance.model.vo;

public class Person extends Object {
	// class명에 상속 구문이 없다면 object
	// 컴파일러가 자동으로 extends Object 구문을 추가

	// 필드
	private String name;
	private int age;
	private String nationality;

	// 기본 생성자
	public Person() {
	} // 기본 생성자

	// 매개변수 생성자
	public Person(String name, int age, String nationality) {
		this.name = name;
		this.age = age;
		this.nationality = nationality;
	}

	// 메서드
//		private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//		private int age;
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

//		private String nationality;
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void breath() {
		System.out.println("호흡한다!.");
	}

	public void move() {
		System.out.println("움직인다");
		// 상속받은 자식들에 대한 공통적인 규약 정의할 수 있다
		// 상속 받은 자식들은 모두 부모와 같은 필드 메소드르 가지고 있음으로 클래스들이 일부 공통된 형태를 띈다
	}
	
	//Object . toString( ) 메소드 오버라이딩
	
	//-toString(); 메소드는 객체가 가지고있는 모든 값 (필드)을 하나의 문자열로 반환하는 메소드.

	@Override
	public String toString() {
		return name+" / "+age+"세 / "+nationality;
				//이름/나이/국적이 반환된다.
	}
}
