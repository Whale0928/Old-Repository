package edu.kh.collection.model.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import edu.kh.collection.model.vo.Member;

public class SetService {

	// set (집합)
	// 순서를 유지하지 않음 ( == 인덱스 번호가 존재하지 않는다)
	// 중복을 허용하지 않는다 (Null 도 중복X)

	// ***set이 중복을 확인하는 방법.
	// - set도 컬렉션인대 컬렉션은 기본적으로 객체만 저장 가능하다.
	// - 객체가 가지고 있는 필드 값이 모두 같으면 중복으로 판단.
	// - 이 때 필드 값이 모두 같은지 비교하기 위해서
	// -객체에 'equals()'가 반드시 오버라이딩 되어있어야한다.

	public void ex1() {
		Set<String> set = new HashSet<String>();
		// HashSet : set의 대표적인 자식 클래스
		// 사용조건 1 : 저장되는 객체가 반드시 equals() 가 오버라이딩 되어야한다
		// 사용조건 2 : 저장되는 객체가 반드시 hashCode() 가 오버라이딩 되어야한다

		// *참고* Hash라는 단어가 붙은 컬렉션은 반드시 equals() HashCode()를 오버라이딩 해야한다/

		// Set.add(String e)

		set.add("네이버");
		set.add("카카오");
		set.add("라인");
		set.add("쿠팡");
		set.add("배달의 민족");
		set.add("배달의 민족");
		set.add("배달의 민족");
		set.add(null);
		set.add(null);

		System.out.println(set);

		// 확인 할 것 : 1.순서 /2.중복 /3.null중복
		System.out.print("저장된 데이터의 수 : " + set.size() + "\n");

		// remove(String e) : Set 에 저장된 객체중 매개변수 e와 필드값이 같은 객체를 제거
		// +Hash라는 단어가 포함된 Set이면 HashCode()도 같아아함. (String에서는 그냥 되어있다.)

		System.out.println(set.remove("라인"));
		System.out.println(set.remove("요기 어때"));

		System.out.println(set);

		// Set은 순서가 없어서 저장된 객체를 하나를 얻어올 수 있는 방법이 없다.
		// - > 대신 Set전체의 데이터를 하나씩 반복적으로 얻어올 수는 있다

		// 1) Iterator(반복자)
		// - 컬렉션에서 제공하는 컬렉션 객체에 반복 접근자
		// (컬렉션에 저장된 데이터를 임의로 하나씩 반복적으로 꺼내는 역활)

		// Iterator가 얻어온 모든 데이터 타입은 String을 알려줌
		Iterator<String> it = set.iterator();

		// set.iterator() : Set을 Iterator 하나씩 꺼내갈 수 있는 모양으로 변환한다.

		while (it.hasNext()) { // 하나씩 꺼냈을 때 다음 값이 없는 경우 == 끝이다.
			// - > 다음 값이 있으면 반복해야한다.

			// it.hasNext : 다음값을 가지고 있으면 트루 반환
			String temp = it.next();
			System.out.println(temp);
		}
		System.out.println("--------------------------------------");
		// 향상된 for문
		// for( 하나씩 꺼내서 저장할 변수 : 컬렉션 ) { }

		for (String temp : set) {
			System.out.println(temp);
		}
	}

	public void ex2() {
		// object의 equals() , hashCode() 오버라이딩

		// A.equals(B) : A와B가 가지고 있는 값이 같으면 True 아니면 False;

		// Hash 함수 : 입력된 단어를 지정된 길이의 문자열로 변환하는 함수 (중복 X)
		// ex) 입력 : 111 -> "asbfg"(5글자)
		// ex) 입력 : 1234567 -> "qegzh"(5글자)
		// 보통 짦은걸 길게 만들게 이때 중복이 발생하지 않음.

		// hashCode() : 필드값이 다르면 중복되지 않는 숫자를 만드는 메소드.
		// 왜 만드는가 : 빠른 데이터 검색을 위해서(객체가 어디에 있는지 빨리 찾기 위해서)

		// HashSet( ) : 중복 없이 데이터 저장(Set)하고 데이터 검색이 빠름(Hash)

		Member mem1 = new Member("User01", "Pass01", 20);
		Member mem2 = new Member("User01", "Pass01", 20);
		Member mem3 = new Member("User03", "Pass03", 30);
		// mem1 mem2가 같은지 비교 .

		System.out.println(mem1 == mem2); // 주소 비교 : 얕은 복사같은 경우가 아니라면 다 false

		// 매번 이렇게 비교하기 힘드니 .. 비교 코드를 작성해 재활용하다
		// == equals() 메소드 오버라이딩

		System.out.println("=================");

		System.out.println(mem1.equals(mem2));
		System.out.println(mem1.equals(mem3));
		// 서로 다른 객체지만 필드값이 같다 == 동등하다.
		// 비교하려는 것이 정말 같은 하나의 객체이다 == 동등

	}

	public void ex3() {
		// equals()가 오버라이딩된 Member를 Set에 저장
		// [Key Point] : 중복이 제거가 되는가.
		Set<Member> memberSet = new HashSet<Member>();

		memberSet.add(new Member("user1", "pw1", 30));
		memberSet.add(new Member("user2", "pw2", 33));
		memberSet.add(new Member("user3", "pw3", 26));
		memberSet.add(new Member("user4", "pw4", 10));
		memberSet.add(new Member("user4", "pw4", 10));

		for (Member mem : memberSet) {
			System.out.println(mem);
		}
		// hashCode() 오버라이딩 전
		// equals()는 오버라이딩되어 중복 검사는 했지만 중복 제거가 되어있지 않음
		// HashSet은 hashCode()도 오버라이딩 해야한다.
		Member mem1 = new Member("user01", "pass01", 30);
		Member mem2 = new Member("user01", "pass01", 30);
		Member mem3 = new Member("user01", "pass01", 31);

		System.out.println(mem1.hashCode());
		System.out.println(mem2.hashCode());
		System.out.println(mem3.hashCode());

	}

	public void ex4() {
		// Wraaper 클래스 : 기본자료형 - > 객체로 포장하는 클래스

		// 컬렉션에 기본 자료형 값을 저장할 때 사용
		// - 기본 자료형에 없던 추가 기능 . 값을 이용하고 싶을 때 사용한다.

		// int - > Integer
		// double -> Double

		int iNum = 10;
		double dNum = 3.14;

		Integer w1 = new Integer(iNum); // int가 Integer로 포장
		Double w2 = new Double(dNum); // double을 Double로 포장

		// Wrapper 클래스 활용
		System.out.println("int 최대값 : " + w1.MAX_VALUE);
		System.out.println("double 최소값 : " + w2.MIN_VALUE);

		System.out.println("w1 : " + w1);
		System.out.println("w2 : " + w2);
	
		System.out.println("====================================");
		System.out.println("static방식으로 Wrapper 클래스 사용하기");
		
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Double.MAX_VALUE);

		System.out.println("====================================");
		
		
		//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		//	parsting : 데이터의 형식을 변환
		//  String 데이터를 기본 자료형으로 변환! 
			int num1 = Integer.parseInt("220"); //문자열 100을 int 형식으로 변환
			double num2= Double.parseDouble("1.23121"); //문자열 1.23121을 double형식으로 변환
		
			System.out.println(num1+num2);
		//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	}
	
	public void ex5() {
		//Wrapper Class / Auto Boxing , Auto unBoxing
		Integer w1 = new Integer(10);
		//삭제선 == Depraecated ==해당 구문은 삭제될 예정이다.
		// = > 사용을 권장하지 않는다
		
		Integer w2 = 100;
		Integer w3 = 200;
	//	(Interger)   (Int)
	//   w2 와 100은 원래 연산이 안되어야 되지만
	//	Integer은 int의 포장형식이라는 걸 JAVA가 인지하고있어서
	//	위와 같은 경우 int Integer으로 자동 포장해준다.
		
		System.out.println("w2+w3 : "+(w2+w3));
	// w2 + w3 는 객체이여서 +연산이 안되지만
	// 자바가 integer가 int인걸 알고 있어서 int+int연산으로 Auto Unboxing을 함/
		
	}
	

	public void lotto() {
		// 로또번호 생성기 ver.2

		//Set<int> lotto = new HashSet<int>();
		// intsms rlqhs wkfyguddlrl EOansdp
		// 객체만 저장하는 컬렉션은 저장할 수 없다.

		// 해결방법 : Wrapper Class를 이용해 기본자료형을 객체로 포장한다.
		
//		Set<Integer> lotto = new HashSet<Integer>();
//		Set<Integer> lotto = new LinkedHashSet<Integer>(); //순서가 유지되는 set
		Set<Integer> lotto = new TreeSet<Integer>(); //자동 정렬 set
		//Integer은 기본적으로 오버라이딩되어있다

		//로또라는 Set 6개 미만일 경우 반복
		while(lotto.size() < 6) {
			int random =(int)(Math.random()*45+1); //1에서 45 사이의 난수.			
			System.out.println(random);
			lotto.add(random); //랜덤 int 값이 자동으로 Integer으로 포장(unBoxing)되어 lotto에 포장된다. 			
		}
		System.out.println("로또 번호 : " + lotto);

	}
}
