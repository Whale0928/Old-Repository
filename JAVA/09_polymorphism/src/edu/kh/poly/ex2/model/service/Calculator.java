package edu.kh.poly.ex2.model.service;


//계산기 인터페이스
// 모든 계산기에 대한 공통 필드 , 기능명 제공하는 접점 용도
public interface Calculator {

	//interface : 추상 클래스의 변형체(추상 클래스보다 추상도가 높다)
	//			-추상클래스  : 일부만 추상 메소드(0개이상)
	//			-인터페이스  : 모두 추상 메소드
	
	//필드 : 묵시적으로 public static final
	public static final double PI = 3.14;
	static final int MAX_NUM = 10000;
	public int MIN_NUM=-100;
	int zero = 0;
	
	//메서드 : 묵시적으로 public abstract 
	public abstract int plus(int num1,int num2);
	public abstract int minus(int num1,int num2);
	public abstract int multiple(int num1,int num2);
	public abstract double divide(int num1,int num2);
}
