package edu.kh.poly.ex2.model.run;

import edu.kh.poly.ex2.model.service.AbstractService;
import edu.kh.poly.ex2.model.service.BDHCalculator;
import edu.kh.poly.ex2.model.service.Calculator;
import edu.kh.poly.ex2.model.service.KHGCalculator;
import edu.kh.poly.ex2.model.service.LCSCalculator;

public class AbstractRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractService service = new AbstractService();

//		service.ex1();
//		service.ex2();

		// Calculator 임폴트
		// import edu.kh.poly.ex2.model.service.Calculator;

		// 각자 만든 xxxCalculator 임폴트
		// import edu.kh.poly.ex2.model.service.BDHCalculator;

		// Calculator cal = new KHGCalculator();
		// Calculator cal = new LCSCalculator();
		Calculator cal = new BDHCalculator();
		// 인터페이스 == 미완성 설계도 == 객체 생성 불가능
		// 추상 클래스 처럼 참조 변수로는 사용가능

		// 코드의 큰 수정 없이
		// 객체생성 코드만 바꾸면 새로운 클래스 코드를 수행 할 수 있다.

		System.out.println("합 : " + cal.plus(20, 15));
		System.out.println("차 : " + cal.minus(20, 15));
		System.out.println("곱 : " + cal.multiple(20, 15));
		System.out.println("몫 : " + cal.divide(20, 15));
	}
}
