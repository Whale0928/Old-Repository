package edu.kh.poly.ex2.model.service;

import edu.kh.poly.ex2.model.vo.Animal;
import edu.kh.poly.ex2.model.vo.Fish;
import edu.kh.poly.ex2.model.vo.Person;

public class AbstractService {

	public void ex1() {
		// 추상 클래스는 객체로 만들 수 있을까?

		// Animal a1 = new Animal(); //안된다...
		// Cannot instantiate the type Animal

		// 클래스 : 객체의 속성 , 기능을 정의한 것 (일종의 설계도)
		// 추상 클래스 : 미완성 메소드를 포함한 클래스(미완성 설계도)
		// 미완성 설계도는 객체를 만든 수 없다

		// 해결방법 Animal을 상속받아 미완성 부분을 구현한 클래스를 이용해 객체 생성.

		// * 추상 클래스를 상속받은 자식 객체 생성하기 Person
		Person p1 = new Person();
		p1.setType("척추동물");
		p1.setEatType("잡식");

		// 오버라이딩한 메소드 호출

		p1.eat();
		p1.breath();

		Fish f1 = new Fish();

		System.out.println("-----------물고기\n");
		f1.eat();
		f1.breath();

	}

	public void ex2() {
		//*추상 클래스와 다형성 + 바인딩
	
		//추상 클래스는 객체로 만들 수 없다.
		//--------> 하지만 >>참조<< 변수로는 사용할 수 있다!!!
	
		//ex) 동물 = 사람? 물고기?  ->알 수 없는 상태
		// Animal a1 = new Animal();
		
		//	  사람 = 동물 			 / 물고기 = 동물
		//	Ainmal a1 =person();    Ainmal a2 =  Fish();
		Animal[] arr = new Animal[2];
		//Animal 참조변수 배열 선언및 할당
		
		arr[0]  = new Person("사람", "잡식", "김투싼"); 
		//Animal 의 자식 person을 업캐스팅
		
		arr[1] = new Fish("물고기", "잡식");
		//animal의 자식 Fish를 업캐스팅
		
		for(int i = 0; i<arr.length;i++) {
//			System.out.println(arr[i].toString());
			arr[i].eat();//정석 바인딩
			arr[i].breath();
			System.out.println(arr[i]);
			
			//Animal의 eat가 아닌 오버라이딩된 각각의 자손들의 eat가 나온다.
			
			System.out.println("-----------------------");
			
		}
	}
}
