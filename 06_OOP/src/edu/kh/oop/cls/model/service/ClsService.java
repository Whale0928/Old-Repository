package edu.kh.oop.cls.model.service;

import edu.kh.oop.cls.model.vo.Student;
import edu.kh.oop.cls.model.vo.User;

public class ClsService {

	public void ex1() {
		// 클래스 접근 제한자 확인하기.
		Student std = new Student();
		// public class인 Student는 import 가능

//		TestVo test = new TestVo();
//		(default) class인 TestVo는 import가 불가함.

		System.out.println("다른 패키지");
		System.out.println(std.v1);
//		System.out.println(std.v2); 상속 받으면 사용가능함
//		System.out.println(std.v3);

	}

	public void ex2() {
		Student std1 = new Student();
		Student std2 = new Student();

		// 학생 객체에 name 세팅
		std1.setName("홍길동");
		std2.setName("김영희");

		System.out.println(Student.schoolName);
		System.out.println(std1.getName());

		System.out.println(Student.schoolName);
		System.out.println(std2.getName());

		std1.schoolName = "KH정보교육원";

		System.out.println("변경 후 std1 : " + Student.schoolName); // static을 보는중
		System.out.println("std2 : " + Student.schoolName); // static을 같이 보는중
		// schoolName에 노란줄이 뜨는 이유 ->제대로 작성하지 않아서.
		// static이 붙은 필드(변수)는 클래스명.변수로 사용한다.

		Student std3 = new Student();
		System.out.println("std3 :  " + Student.schoolName);

		System.out.println(std3.getName());
	}

	public void ex3() {
		// 생성자 확인 예제

		// 1) Student 객체를 기본 생성자를 이용해 생성하고
		// 2) 이를 참조하는 참조변수 s1에 대입
		Student s1 = new Student();
		// 기본 생성자

		// 1) new Student : Student 클래스를 보고 Student객체를 만들겟다
		// 2) new Student( ): Student 객체가 생성된 후 기본 생성자에 작성된 코드를 수행 "괄호"

		// User 기본 생성자를 이용해 객체 생성

		User u1 = new User(); // User()가 기본생성자를 의미한다.

		// user 객체 필드 초기화 확인

		System.out.println(u1.getUserId());
		System.out.println(u1.getUserPw());
		System.out.println(u1.getUserName());
		System.out.println(u1.getUserAge());
		System.out.println(u1.getUserGender());

		System.out.println("================================");
		// user 기본 생성자를 이용해 객체 생성
		User u2 = new User();
		System.out.println(u2.getUserId());
		System.out.println(u2.getUserPw());
		System.out.println(u2.getUserName());
		System.out.println(u2.getUserAge());
		System.out.println(u2.getUserGender());

		// 현재 문제점 : u1이 참조하고 있는 User 객체와
		// u2가 참조하고 있는 User 객체의 필드값이 모두 동일하다
		// 왜? 똑같은 기본 생성자로 User객체를 생성했기 떄문에..

		System.out.println("================================");
		// 해결 방법 1) setter 을 이용해서 새로운 값을 대입
		u2.setUserId("Lemon123");
		u2.setUserPw("!12345");
		u2.setUserName("김영희");
		u2.setUserAge(20);
		u2.setUserGender('여');

		System.out.println(u2.getUserId());
		System.out.println(u2.getUserPw());
		System.out.println(u2.getUserName());
		System.out.println(u2.getUserAge());
		System.out.println(u2.getUserGender());

		System.out.println("================================");
		// 해결 방법 2) 매개 변수 생성자를 이용해 객체가 생성될 때 부터 다른 값을 부여하는것.

		User u3 = new User("king", "pass333");// 매개변수 생성자.
		// 생성자 수행시 전달 값을 작성 순서를 꼭 지켜야 한다.

		// 생성된 User 객체의 필드에 king,pass333이 초기화 됨.

		System.out.println(u3.getUserId());
		System.out.println(u3.getUserPw());
	}

	public void ex4() { //매개변수 생성자 예제
		
		User u1 = new User();//기본 생성자.
		User u2 = new User("User2","pass02");//매개변수 2개짜리 생성자
		User u3 = new User("User3", "pass03", "김현기", 25, '남');
		User u4 = new User("User4","pass04","계보린",25,'여');//매개변수 2개짜리 생성자
		User u5 = new User("User5", "pass05", "김카누", 40, '남');
		
		
		System.out.printf("u1: : %s /  %s / %s / %d / %c\n",u1.getUserId(),u1.getUserPw(),u1.getUserName(),u1.getUserAge(),u1.getUserGender());
		System.out.printf("u2: : %s /  %s / %s / %d / %c\n",u2.getUserId(),u2.getUserPw(),u2.getUserName(),u2.getUserAge(),u2.getUserGender());
		System.out.printf("u3: : %s /  %s / %s / %d / %c\n",u3.getUserId(),u3.getUserPw(),u3.getUserName(),u3.getUserAge(),u3.getUserGender());
		System.out.printf("u4: : %s /  %s / %s / %d / %c\n",u4.getUserId(),u4.getUserPw(),u4.getUserName(),u4.getUserAge(),u4.getUserGender());
		System.out.printf("u5: : %s /  %s / %s / %d / %c\n",u5.getUserId(),u5.getUserPw(),u5.getUserName(),u5.getUserAge(),u5.getUserGender());
	}
}
