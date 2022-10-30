package edu.kh.poly.ex1.model.service;

import edu.kh.poly.ex1.model.vo.Car;
import edu.kh.poly.ex1.model.vo.Spark;
import edu.kh.poly.ex1.model.vo.Tesla;

public class PolyService {

	public void ex1() {
		// 대형성 확인 예제

		// car객체
		Car car = new Car();
		// 부모타입 참조변수로 부모 객체를 참조해서 문제가 없다.

		Tesla tesla = new Tesla();
		// 자식타입 참조변수로 자식 객체 참고

		// ********다형성 (업캐스팅) *****8

		Car car2 = new Tesla(); // 에러 안남
		// Tesla 객체를 참조하는 변수의 타입이 Car 이기 때문에
		// Tesla 객체가 Car 객체로 변화한다.

		Car car3 = new Spark();
		// 둘 모두 부모타임 참조변수를 참조하는데
		// 에러가 나지 않는다.

		// ***다형성 (업캐스팅) 사용법***

		// 1-1 car (부모 = 부모)
		car.setEngine("v6 6기통");
		car.setFuel("고급 휘발유");
		car.setWheel(4);

		// 1-2 tesla 전기 (자식=자식)
		tesla.setEngine("전기");
		tesla.setFuel("전기");
		tesla.setWheel(4);
		tesla.setBatteryCapacity(100000);

		// 1-3 car2 (부모 = 자식)
		car2.setEngine("전기");
		car2.setFuel("전기");
		car2.setWheel(4);
//		car2.setBatteryCapacity(100000);
		// The method setBatteryCapacity(int) is undefined for the type Car

		// 1-4 car3(부모 = 자식)
		car3.setEngine("나약한 엔진");
		car3.setFuel("식용유");
		car3.setWheel(4);
//		car3.setDiscountOffer(100000);

		Car[] arr = new Car[3];// 부모타입 참조변수 배열 선언및 3칸 할당
								// 각 배열의 요소가 Car타입 참조 변수다

		arr[0] = car; // Car주소 == car 객체
		arr[1] = car2; // Car주소 == Tesla 객체
		arr[2] = car3; // Car주소 == Spark 객체

		// 상속 + 다형성
		// 상속 특징 : 일련의 클래스에 대한 공통적인 규약
		for (int i = 0; i < arr.length; i++) {
			System.out.println(i + "번째 인덱스의 엔진 : " + arr[i].getEngine());
		}
	}

	public void ex2() {
		// 3) 다형성을 이용한 매개변수 사용방법

		Tesla t = new Tesla("전기모터", "전기", 4, 1000000);
		Spark s = new Spark("나약한엔진", "식용유", 4, 0.5);
		Car c = new Car("트럭엔진", "경유", 12);

//		printCar(t);
//		printCar(s);
//		printCar(c);

		System.out.println("------------------------------");
		// 4)다형성을 이용한 반환형 사용법
		Car[] arr = { createCar(1), createCar(2), createCar(3)
				// Car Tesla Spark
		};

//		arr[0]; //car
//		arr[1]; //Tesla
//		arr[2]; //Spark

//	 	********* instance of 연산자 ************
//		System.out.println(arr[1] instanceof Spark);

		for (int i = 0; i < arr.length; i++) {
			// 부모타입 검새는 제일 마지막에 진행해야한다.
			if (arr[i] instanceof Tesla) {
				System.out.println(i + "번 배열Tesla 객체입니다.");
			} else if (arr[i] instanceof Spark) {
				System.out.println(i + "번 배열 Spark 객체입니다");
			} else {
				System.out.println(i + "번 배열 Car 객체입니다.");
			}
		}

	}

	// 전달받은 Car또는 자식 객체의 엔진 , 연료 , 바퀴 개수만 출력하는 메서드.
	// 매개변수에 부모타입 참조변수를 작성하면 모든 자식 객체를 전달 받을 수 있다.
	public void printCar(Car temp) {
		// 매개변수에 작성된 참조형 변수에는 주소가 저장된다(얕은 복사 개념);
		// 매서드 내부 변수 + 매개변수 == 지역변수(Local Variable)

		System.out.println("엔진 :" + temp.getEngine());
		System.out.println("연료 :" + temp.getFuel());
		System.out.println("바퀴 개수 :" + temp.getWheel() + "\n");
	}

	// 전달받은 매개변수에 따라서 Car 또는 자식 객체를 생성하고
	// 생성된 객체의 주소를 반환한다.
	public Car createCar(int num) {

		Car result = null;
		// null 아무것도 참조하고 있지 않음

		switch (num) {
		case 1:
			result = new Car();
			break;
		case 2:
			result = new Tesla();
			break;
		case 3:
			result = new Spark();
			break;
		}

		// 반환형이 Car이지만
		// case가 2 ,3 이면 Car의 자식객체 주소가 반환된다.
		return result;
	}

	public void ex3() {
		// 다형성 중 다운 캐스팅

		// 다운 캐스팅이란
		// 부모타입 참조 변수가 자식 객체를 참조하는 업캐스팅 상태에서만 진행 가능한 기술로
		// 부모 타입을 자식 타입으로 '강제 형 변환'해서
		// 자식 객체의 본래 필드 메소드를 사용 가능하게 한다.

		Car c1 = new Tesla("전기모터", "전기", 4, 2000);

		((Tesla) c1).getBatteryCapacity();
		System.out.println(((Tesla) c1).getBatteryCapacity());

		// 주의 !@ '.' 연산자가(Tesla) 강제 형변환 연산자보다 우선순위가 높다.

		// < 효율적인 다운캐스팅 방법 >
		// 얕은 복사를 이용한다

		Tesla t1 = (Tesla) c1;
		System.out.println(t1.getBatteryCapacity());
	}

	public void ex4() {
		// 다운 캐스팅 주의사항 @!!@!#@!#@!

		// 오류 발생의 케이스

		Car c1 = new Tesla();

		// 해결방법 : instanceof와 같이 사용하자
		if (c1 instanceof Spark) {
			Spark s1 = (Spark) c1;
			// 부모가 같은 점 말고는 근본없다.

		} else {
			System.out.println("실패");
		}
	}

	public void ex5() {

		// 바인딩(Binding)
		// 실제 실행할 메소드 코드와 호출하는 코드를 연결 시키는 것.

		Car c1 = new Car("경유엔진", "경유", 6); // (엔진종류/연료종류/바퀴수)

		System.out.println(c1.getEngine());
		// Car객체에 있는 getEngine메소드를 호출한다.

		// String edu.kh.poly.ex1.model.vo.Car.getEngine()

		// 프로그램 실행전
		// -컴파일러는 getEngine() 메소드와 Car에 있는 걸로 인식해서
		// c1.getEngine()호출 코드와
		// String edu.kh.poly.ex1.model.vo.Car.getEngine() 메소드 코드를 연결
		// - > [정적 바인딩] : 코드 실행 전에는 움직임이 없다

		System.out.println(c1.toString());
		// String edu.kh.poly.ex1.model.vo.Car.toString() (object의 toString을 재정의)
		// Car 참조변수 c1ㅇ을 이용해서
		// Car 객체에 있는 오버라이딩된 toString()메소드를 호출한다.

		// 다형성이 적용시 바인딩

		Car c2 = new Spark("경차용엔진", "휘발유", 4, 0.5);
		// 업캐스팅 적용 - > 부모부분만 참조 가능한 상태

		System.out.println(c2.toString());
//		String edu.kh.poly.ex1.model.vo.Car.toString()
//		참고 변수 c2 = car타입이니깐
//		toString()e도 car의 toString을 호출 --정적바인딩

//		하지만 실행 해보니 자식(Spark)의 toString()이 호출됨
//		컴파일 시에는 부모와 바인딩 -- 정적 바인딩
//		실행 시에는 자식의 오버라이딩된 메소드와 바인딩 --동적 바인딩

		// 동적 바인딩 활용 방법
		Car arr[] = { new Car("경유엔진", "경유", 12), new Tesla("전기엔진", "전기", 4, 50000), new Spark("경차엔진", "무연", 4, 0.3) };

		// arr 배열 요소가 참조하는 모든 객체의 필드 값을 출력.

		for (int i = 0; i < arr.length; i++) {
			System.out.println(i + "번 요소 : " + arr[i].toString());
			//실행전 : Car에 있는 toString이 표기되지만 
			//실행후 : 각 객체별로 오버라이딩된 toString이 호출된다 
			
		}
	}
}
