package edu.kh.poly.ex2.model.vo;

public abstract class Animal {

	//추상클래스 (abstract class)
	
	//1) 미완성 메소드(추상메소드)를 보유하고 있는클래스
	//2) (추상메소드는 없지만) 객체로 만들면안되는 클래스
	
	// 필드
	private String type; // ~종 ~과 ~류
	private String eatType;// 식성(육식 , 초식 , 잡식등)

	// 생성자
	public Animal() {}
	//추상 클래스는 new연산자를 이용해서 직접적인 객체생성은 불가 하지만
	// 하지만 >상속 시< 내부에 생성 될 때에는 사용되기 때문에 생성자가 필요하다. 
	// super생성자
	
	
	public Animal(String type, String eatType) {
		this.type = type;
		this.eatType = eatType;

	}

	// 메소드

	public String getType() { // 외부에서 얻어간다
		return type;
	}

	public void setType(String type) { // 외부에서 받아와 대입한다..
		this.type = type;
	}

	public String getEatType() {
		return eatType;
	}

	public void setEatType(String eatType) {
		this.eatType = eatType;
	}

	// toString overriding
	@Override // 오버라이드 되었음을 명시
	public String toString() {

		return type + " / " + eatType;
	}

	// 동물의 공통 기능추출(추상화)
	//모두 먹고 호흡하지만 
	//각각 방식이 모두 다름. 
	//이 메소드들에서 구제척으로 명시 불가.
	
	// 추상 메소드로 작성후 자식 메소드들이 해당 메소드를 구체적으로 정의하도록 강제함
	
	
	public abstract void eat();

	public abstract void breath();

}
