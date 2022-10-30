package edu.kh.poly.ex2.model.vo;

public class Fish extends Animal{

	//생성자
	public Fish() {
		super();
	}
	public Fish(String type, String eatType) {
		super(type , eatType);
	}
	
	
	//추상 메소드는 상속받으면 오버라이딩이 강제된다.
	@Override
	public void eat() {
		System.out.println("본인보다 작은걸 왠만하면 다 먹는다.");
	}

	@Override
	public void breath() {
		System.out.println("아가미로 호흡한다.");
	}
	

	@Override
	public String toString(){
		
		return "Fish : " + super.toString();
	}

	
}
