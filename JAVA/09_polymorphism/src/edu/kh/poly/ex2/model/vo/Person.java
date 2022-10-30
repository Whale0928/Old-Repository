package edu.kh.poly.ex2.model.vo;

public class Person extends Animal{

	private String name;
	
	
	//생성자
	public Person() {
		super(); //Animal 기본 생성자.
	}
	public Person(String type, String eatType,String name) {//매개변수 생성자.
		super(type,eatType);
		this.name = name;
	}
	
	//getter  , setter 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public void eat() {
			System.out.println("식기를 활용해서 먹는다.");
	}
	@Override
	public void breath() {
			System.out.println("코와 입으로 호흡한다.");
	}
	
	//toString()
	@Override
	public String toString(){
		
		return "Person : " + super.toString()+ " / "+name;
	}
}
