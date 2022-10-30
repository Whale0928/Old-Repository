package edu.kh.oop.cls.model.vo;

class TestVo {

	//접근제한자 (defult) : 같은 '패키지' 내에서만 접근 가능
		 
		 //Student와 같은 패키지
		//->public , protected / (default)만 접근 가능

	public void ex() {
		
			Student std = new Student();
			
			System.out.println(std.v1);
			System.out.println(std.v2);
			System.out.println(std.v3);
			
			//private 는 자신의 클래스안에서만 사용가능하다.
	}
}
