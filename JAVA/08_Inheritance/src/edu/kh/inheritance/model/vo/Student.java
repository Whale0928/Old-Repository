package edu.kh.inheritance.model.vo;

public class Student extends Person {

	// ********상속 ***************888
	// extends : 확장한다 , 연장한다
	// public class Student extends Person : Student클래스에 Person의 내용을 추가하여 확장한다.

	// 필드
	private int grade; // 학년
	private int classRoom;

	// 생성자
	public Student() { // 기본 생성자
		// Student ( ) 객체 생성시
		// 내부에 상속받은 person객체를 생성하기 위한
		// Person 생성자 호출 코드가 필요하다
		// super==Person();

		super(); // super()생성자

//		부모의 생성자를 호출하는 코드
//		반드시 자식생성자에 최상단에 작성되어야한다.
//		*super ( )  생성자 때문에
//		자식 객체 내부에 부모 객체가 생성된다.

//		*super 생성자 미작성 시
//		컴파일러가 컴파일 단계에서 자동으로 추가해줌.
	}

	public Student(String name, int age, String nationality, int grade, int classRoom) {
		//전달받은 값 중 부모 필드에 값 세팅하기
	
		//this.name = name;
		//부모의 필드에 private접근제한자인 객체이기 때문에 직접 접근이 불가.
		
		//setter을 이용해도 되지만 비효율적이다..
		//setName(name);
		
		super(name, age, nationality);
		
		this.grade = grade;
		this.classRoom = classRoom;
	}

	// 메소드

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(int classRoom) {
		this.classRoom = classRoom;
	}


	//toString 오버라이딩
	@Override
	public String toString() {
		return super.toString()+" / "+grade+" / "+classRoom;
					//클래스와 반이 오버라이드된다.
	}
	
}
