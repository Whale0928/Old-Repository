package edu.kh.exception.model.vo;

import java.io.IOException;

public class Parent {

	public void method() throws IOException{
		//호출한 곳으로 IOException을 던진다.
		// == 해당 메서드는 IOException 발생 시킬 가능성이 있음으로
		//호출하는 곳에서 예외 처리를 해야한다.
		System.out.println("부모 메소드");
	}
}
