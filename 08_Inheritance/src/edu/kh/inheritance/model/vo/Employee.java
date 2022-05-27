package edu.kh.inheritance.model.vo;

public class Employee extends Person{
	// 필드
	private String company; // 소속 회사

	// 생성자
	public Employee() { // 기본생성자

	}

//매개변수 생성자
	public Employee(String name, int age, String nationality, String company) {
		super(name,age,nationality);
		this.company = company;
	}

	//person으로 부터 상속받은 메서드중 
	//move 메서드를 person에 맞게 재정의(오버라이딩)
	
	//@Override  어노테이션 : 해당 메소드가 오버라이딩 되었음을 컴파일러에게 인지시킨다
	
	//어노테이션(Annotation) : 컴파일러에게 알려주기 위한 코드(컴파일러 인식용 주석)
	
	@Override //오버라이딩시 작성해주는 것이 좋다.
	public void move() {
		//기존 부모코드 삭제후 자식이 새롭게 재정의  
		System.out.println("오버라이딩된 move( )메서드");
		System.out.println("효율적으로 빨리 일한다");
	
	}
	
	// 메소드

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String toString() {
		return super.toString() +" / " + company;
	}
}
