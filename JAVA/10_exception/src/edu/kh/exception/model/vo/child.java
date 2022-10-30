package edu.kh.exception.model.vo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class child extends Parent{
	
	@Override
	public void method() throws FileNotFoundException{
								//FileNotFoundException
		//오버라이딩시 예외는 같거나 더 좁은 범위
		// * 좁은 범위 : 구체적인 예외 사항
		
		System.out.println("오버라이딩된 자식 메소드");
	}
}
