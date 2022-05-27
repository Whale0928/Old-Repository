package edu.kh.collection.model.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.kh.collection.model.vo.Student;

public class StudentService {
	Student std = new Student();
	// 필드
	private Scanner sc = new Scanner(System.in);

	// 학생 정보를 저장할 List를 생성(객체 배열 Upgrade 버전)

	// java.util.List 인터페이스 : List에 반드시 필요한 필수 기능들을 모아둔 인터페이스(추상메소드모음)
	// 인터페이스는 객체생성 X , 부모 참조 변수 O

	// java.utill.ArrayList : 배열 형태 List (가장 대표적인 List 자식 클래스)

	// ArrayList( ) 기본 생성자 : 기본 크기 10짜리 리스트 생성
//								 하지만 리스트는 크기 변형이 자유롭기 때문에 큰 의미가 없다.
//	 ArrayList( 용량 ) : 용량만큼의 리스트를 생성할 수 있다 * 너무 큰값을 작성하면 메모리를 많이 소모하니 주의

	private List<Student> studentList = new ArrayList<Student>();
//	private List<Student> studentList = new LinkedList<Student>();
	// Student로 저장되는 타입이 제한된다.
	// 모든게 Student이고 Student인지 검사가 불필요하게 된다.


	public StudentService() {
		studentList.add(new Student("홍길동", 25, "서울시 중구", 'M', 90));
		studentList.add(new Student("고영희", 23, "경기도 안산시", 'F', 100));
		studentList.add(new Student("김아지", 30, "서울시 강남구", 'M', 80));
		studentList.add(new Student("오미나", 27, "충북 청주시", 'F', 90));
		studentList.add(new Student("박주희", 24, "서울 서대문구", 'F', 70));
	}

	public void ex() {
		// List 사용 테스트
		// List.add(Object e) : 리스트에 객체를 추가
		// 매개변수 타입이 Object === 모든 객체를 매개변수로 전달 가능함을 의미한다.
		// object == 최상위 부모 참조 변수 == 다형성 적용 가능

		studentList.add(new Student()); // 0번
//		studentList.add(sc);			//1번
//		studentList.add("문자열");		//2번
//		studentList.add(new Object());  //3번
		// ->컬렉션 특징 : 여러 타입의 데이터를 저장 할 수 잇다

		// Object List.get(index i) : 리스트에서 i번째 인덱스에 있는 객체(object)를 반환
		// 반환형이 Object == 모든 객체를 반환 할 수있다

		System.out.println(studentList.get(0).toString());
//			실행전 : 		java.lang.Object.toString( ) == 정적 바인딩
//			실행후 : 		알고보니 Student객체이고 , toString() 오버라이딩 되어있다.
//								-> Student의 toString()이 수행된다 ==동적바인딩.

//			Student의 이름만 얻어오기

		// student객체가 맞는지 확인하고 다운 캐스팅을 해야
		// student 기능을 사용할 수 있다.

		if (studentList.get(0) instanceof Student) {
			System.out.println(((Student) studentList.get(0)).getName());
		}

		// 길고 매우 복잡
		// 컬렉션의 장점인 여러객체저장이 코딩에 방해함
		// 그래서 있는게 제네릭 ( Generics ) == 제일 중요한 역활
		// colloetion에 저장되는 객체 타입을 한가지로 제한함.

		System.out.println(studentList.get(0).getName());

	}

	/**
	 * 메소드 설명용 주석 alt + Shift + J 여기 적힌 내용이 클래스 마우스 온 시 표시됨
	 * 
	 * @author rlagusrl0123@naver.com
	 */
	public void displayMenu() {
		int menuNum = 0;

		do {
			System.out.println("\n ================학생관리프로그램 ver.2================\n");
			System.out.println("1 . 학생 정보 추가");
			System.out.println("2 . 학생 전체 조회");
			System.out.println("3 . 학생 정보 수정");
			System.out.println("4 . 학생 정보 제거");
			System.out.println("5 . 이름으로 검색(일치)");
			System.out.println("6 . 이름으로 검색(포함)");

			System.out.println("0 . 프로그램 종료");
			System.out.print("\n메뉴 번호 선택 >> ");

			try {
				menuNum = sc.nextInt();
				System.out.println(); // 입력받고 줄바꿈

				switch (menuNum) {
				case 1:
					System.out.println(addStudent());
					break;
				case 2:
					selectAll();
					break;
				case 3:
					System.out.println(updateStudent());
					break;
				case 4:
					System.out.println(removeStudent());
					break;
				case 5:
					searchName1();
					break;
				case 6:
					searchName2();
					break;
				case 0:
					System.out.println("--프로그램을 종료합니다--");
					break;
				default:
					System.out.println("메뉴에 있는 번호만 입력해주세요");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nErroe : 입력 형식이 유효하지 않습니다 다시 시도해주세요.");

				sc.nextLine(); // 입력 버퍼에 남아있는 잘못 입력된 문자열을 제거.
				menuNum = -1; // 첫 반복시 잘못 입력하는 경우 menuNum에 0을 가지고 있어 종료하는데
								// 이를 방지하기 위해 임의값 -1을 대입한다.
			}

		} while (menuNum != 0);
	}

	/** 1학생 정보 추가 메서드 추가 성공시 " 성공 " 실패시 "실패" 반환 */
	public String addStudent() throws InputMismatchException {
		System.out.println("\n========= 학생 정보 추가 ==========\n");
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("사는 곳: ");
		String region = sc.nextLine();
		System.out.print("성별(M / F) : ");
		char gender = sc.next().charAt(0);
		System.out.print("점수 : ");
		int score = sc.nextInt();

		// Student 객체 생성 후 list에 추가

		// add()는 무조건 true 반환하기 때문에
		// 실패하는 경우는 있음
		// 예외가 발생해 add가 수행 전 메서드가 종료될 순 없음.

		if (studentList.add(new Student(name, age, region, gender, score))) {
			return "성공";
		} else {
			return "실...패...";
		}
		// boolean java.util.List.add(Student e)
		// add는 정상 수행 시 무조건 true이다
		// 제네릭<student> 때문에 List 타입이 Student로 제한됨

	}

	/** 2. 학생 전체 조회 메서드. */
	public void selectAll() {
		// List는 인덱스가 있다. (0번 부터 시작)
		// - List에 저장된 데이터의 개수를 얻어오는 방법 : int List.size();
		// -> 배열명.length 대신 사용

		// - List가 비어있는지 확인하는 방법:
		// boolean List.isEmpty : 비어 있으면 true반환

		System.out.println("======학생 전체 조회======");

		// studentList가 비어있는 경우. "학생정보가 없습니다." 출력

//		if(studentList.size() <= 0) {

		if (studentList.isEmpty()) {
			System.out.println("학생 정보가 없습니다.");
			return;
		}

//		 일반 for문
//		for(int i=0; i<studentList.size();i++) {
//			System.out.println(studentList.get(i));
//								//studentList에서  i번째 인덱스 요소를 얻어와 바로 출력.
//		}  

//		향상된 for문(for each문)
//		-컬렉션,배열의 모든 요소를 순차적으로 반복 접근할 수 있는 for문 
//		(순차적 : 0번 인덱스 부터 마지막 요소까지 인덱스를 1씩 증가.)

		// [작성법]
		// for( 컬렉션 또는 배열에서 꺼낸 한개의 요소를 저장할 변수 : 컬렉션 혹은 배열명){ }

		int index = 0;
		for (Student std : studentList) {
			// std에는 for문 반복시마다 0 ,1 ,2 인덱스 요소들 한번씩 저장 된다.
			System.out.print((index++) + "번 : "); // 출력후 인덱스 값을 1 증가
			System.out.println(std);
		}
	}

	/** 3. 학생 정보 수정 메소드 */
	public String updateStudent() throws InputMismatchException {
		// - Student List.set(int index , Student e)
		// -> List의 i번 째 요소를 전달 받은 e로 변경.
		// -> Student == 변경전 Student객체가 담겨 있다.

		System.out.println("===== 학생 정보 수정 =====");

		System.out.print("인덱스 번호 입력 : ");
		int index = sc.nextInt();
		sc.nextLine();

		// 1) 학생 정보가 studentList에 있는가?
		if (studentList.isEmpty()) {
			return "학생정보 없음";

			// 2) 입력된 숫자가 0보다 작은가?
		} else if (index < 0) {
			return "음수는 입력 할 수 없습니다.";

			// 3) 만약 문자열을 입력한 경우가 있다. -> throws으로 입력 처리

			// 4)입력받은 숫자가 studentList 범위내 인덱스인가
		} else if (studentList.size() <= index) {
			return "범위를 넘어선 값을 입력할 수 없습니다";

		} else { // 다 통과됬으면 정보 입력 받기.
			System.out.println(index + "번째 인덱스 저장된 학생 정보");
			System.out.println(studentList.get(index));

			System.out.println("=======================");

			System.out.print("이름 : ");
			String name = sc.next();
			System.out.print("나이 : ");
			int age = sc.nextInt();
			sc.nextLine();
			System.out.print("사는 곳: ");
			String region = sc.nextLine();
			System.out.print("성별(M / F) : ");
			char gender = sc.next().charAt(0);
			System.out.print("점수 : ");
			int score = sc.nextInt();

			// 입력받은 index번째에 새로운 학생 정보를 세팅 == 수정
			// 이때 index번째 있던 기존 학생 정보가 반환된다.
			Student temp = studentList.set(index, new Student(name, age, region, gender, score));

			return temp.getName() + "의 정보가 변경 되었습니다.";
		}

	}

	/**
	 * 학생 정보 제거 메소드
	 * 
	 * @return
	 */
	public String removeStudent() throws InputMismatchException {
		// -Student List.remove(int index)
		// 리스트에서 index번째 요소를 제거
		// 이 때 제거된 요소가 반환된다.
		// *List는 중간에 비어있는 인덱스가 없게 하기 위해서
		// remove 동작시 뒤쪽 요소를 한칸 씩 당겨온다.

		System.out.println("===== 학생 정보 제거 =====");

		System.out.print("인덱스 번호 입력 : ");
		int index = sc.nextInt();
		sc.nextLine();

		// 1) 학생 정보가 studentList에 있는가?
		if (studentList.isEmpty()) {
			return "학생정보 없음";

			// 2) 입력된 숫자가 0보다 작은가?
		} else if (index < 0) {
			return "음수는 입력 할 수 없습니다.";

			// 3) 만약 문자열을 입력한 경우가 있다. -> throws으로 입력 처리

			// 4)입력받은 숫자가 studentList 범위내 인덱스인가
		} else if (studentList.size() <= index) {
			return "범위를 넘어선 값을 입력할 수 없습니다";

		} else {
			// 학생 정보 제거
			System.out.print("정말로 삭제 하시겠습니까? ( Y / N ): ");
			char ch = sc.next().toUpperCase().charAt(0);
			// toUpperCase 소문자를 대문자로 변환.
			if (ch == 'Y') {
				Student temp = studentList.remove(index);// 인덱스 번에 있는 학생 정보 삭제
				return temp.getName() + "의 정보가 삭제 되었습니다";
			} else {
				return "취소";
			}

		}
	}

	/** 일치하는 학생을 찾아서 조회 메서드 */
	public void searchName1() {
		System.out.println("===========학생 검색(이름 일치)==========");

		System.out.print("검색할 이름 입력 : ");
		String input = sc.next();

		boolean flag = true;

		// 향상된 for 문
		for (Student std : studentList) {
			if (input.equals(std.getName())) {
				System.out.println(std);
				flag = false;
			}
		}
		if (flag) { // for문에서 flag에 false가 대입된 적이 없다 == 검색 결과가 없다.
			System.out.println("검색 결과가 없습니다.");
		}
	}

	/** 이름에 특정 문자열이 포함되는 학생을 찾아서 조회하는 메서드 */
	public void searchName2() {
		System.out.println("===========학생 검색(문자열 포함)==========");

		System.out.print("이름에 포함되는 문자열 : ");
		String input = sc.next();

		boolean flag = true;

		// 향상된 for 문
		for (Student std : studentList) {
			// contanins : 포함
			// String.contanins(문자열)
			if (std.getName().contains(input)) {
				System.out.println(std);
				flag = false;
			}
		}
		if (flag) { // for문에서 flag에 false가 대입된 적이 없다 == 검색 결과가 없다.
			System.out.println("검색 결과가 없습니다.");
		}
	}

}
