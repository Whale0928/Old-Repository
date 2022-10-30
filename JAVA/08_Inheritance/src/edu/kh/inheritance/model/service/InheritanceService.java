package edu.kh.inheritance.model.service;

import java.util.Scanner;

import edu.kh.inheritance.model.vo.Employee;
import edu.kh.inheritance.model.vo.Person;
import edu.kh.inheritance.model.vo.Student;

public class InheritanceService {

	public void ex1() {
		// 상속 확인
		// person을 상속 받은 Student가 person의 필드 메소드를 사용할 수 있는가?

		Person p = new Person();
		p.setName("홍길동");
		p.setAge(25);
		p.setNationality("대한민국");

		System.out.println(p.getName());
		System.out.println(p.getAge());
		System.out.println(p.getNationality());

		System.out.println("==============================================");

		Student std = new Student();
		/// student만의 고유한 메서드
		std.setGrade(2);
		std.setClassRoom(5);

		// preson에서 물려받은 코드
		std.setAge(25);
		std.setName("홍길동");
		std.setNationality("대한민국");
		
		
		System.out.println(std.getGrade());
		System.out.println(std.getClassRoom());
		
		//student가 person에 메소드 뿐 아니라 필드도 상속 받았는지 확인하는 부분
		System.out.println(std.getAge());
		System.out.println(std.getName());
		System.out.println(std.getNationality());

		System.out.println("==============================================");

		Employee emp = new Employee();
		// Employee만의 고유한 메소드
		emp.setCompany("kh교육원");

		// Person에서 상속받은 메소드
		emp.setAge(33);
		emp.setName("김종현");
		emp.setNationality("영국");
		
		System.out.println(emp.getCompany());
		System.out.println(emp.getName());
		System.out.println(emp.getNationality());


		System.out.println("==============================================");

		// Person에 추가된 메소드 확인하기
		p.breath();
		std.breath();
		emp.breath();

	}

	public void ex2() {
		//super() 생성자 사용방법
		
		Student std = new Student("김철수",17,"한국",1,3);
		
		System.out.println(std.getName());
		System.out.println(std.getAge());
		System.out.println(std.getNationality());
		System.out.println(std.getGrade());
		System.out.println(std.getClassRoom());
	}

	public void ex3() {
		//오버라이딩 확인 예제
		Employee emp = new Employee();
		Student std = new Student();
		
		std.move();//오버라이딩을 안함 Person에 있는 move가 실행됨
		System.out.println(); 
		emp.move();//overriding함.  Employee에서 오버라이딩한 move가 실행됨
	}

	public void ex4() {
		//모든 클래스는 object의 후손
		//모든 클래스의 최상의 부모는 Object
	
		//1)Objcet 상속 여부 확인 
	
		Person p = new Person();  //Object를 상속 받은 person객체 생성
		
		Student std = new Student();
		Scanner sc = new Scanner(System.in);
		String str = "Abe";
		
		System.out.println(p.hashCode());//Object에서 물려받은 hashCode( );
		
		System.out.println(std.getAge());//Person에서 물려받은 getAge( );
		System.out.println(std.hashCode()); //Person이 Object에게서 물려받은hashCode를 Student가 또 물려받아서 사용
		//상속의 상속의 상속 ....상속 가능
	
		
		//*Object가 모든 클래스의 최상위 부모인지.
		System.out.println(sc.hashCode());
		//object - hashCode
		
		System.out.println(str.hashCode());
		//String - hashCode
		//  String이 object에게 물려받은hashCode를 오버라이딩함.
		
	
	}

	public void ex5() {
		//toString( ) 오버라이딩과 Super참조변수
		Person p = new Person("김드래곤", 2, "네덜란드");
		System.out.println(p.toString());

		System.out.println(p);
		System.out.println("---------------------");
		
		Student std = new Student("이백점", 17, "미국", 2, 6);
		
		System.out.println(std.toString());
		//1)Student 클래스 toString() 오버라이딩 전
		//  Person으로 부터 상속받은 오버라이딩된toString()이 실행된다.
		
		//2)Student 클래스 toString() 오버라이딩 후
		System.out.println();
		
		Employee emp = new Employee("김근로",24, "한국", "grdcompany");
		System.out.println(emp);
	}
}
