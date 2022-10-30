package edu.kh.collection.model.vo;

import java.util.Objects;

public class Member {

	private String id;
	private String pw;
	private int age;

	public Member() {
	}

	public Member(String id, String pw, int age) {
		super();
		this.id = id;
		this.pw = pw;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", age=" + age + "]";
	}
/*
	// Object.equals () 오버라이딩
	// - 현재 객체와 매개변수로 전달받은 객체의 필드가 같은지 비교하는 형태로 오버라이딩.
	@Override
	public boolean equals(Object obj) {
		// 매개변수 다운캐스팅 우선
		Member other = (Member) obj;
		if (this.id.equals(other.id) && this.pw.equals(other.pw) && this.age == other.age) {
			return true;
		} else {
			return false;
		}
	}
	
	//Object.hashCode() 오버라이딩
	//
	
	@Override
	public int hashCode() {
		//필드 값이 같은 객체 같은 정수를 반환해야 한다. 중복 추출을 위해
		//필드값을 이용해서 정수값을 만들면 된다.
		final int PRIME = 31;//소수 31이 연산속도가 빠른 소수 중 하나.
		
		int result = 1;//결과 저장변수 
		
		result = result * PRIME * age ;
		result = result * PRIME * (id == null ? 0 : id.hashCode()) ;
		result = result * PRIME * (pw == null ? 0: pw.hashCode());
		
		return result;
	}
*/

	@Override
	public int hashCode() {
		return Objects.hash(age, id, pw);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return age == other.age && Objects.equals(id, other.id) && Objects.equals(pw, other.pw);
	}
	
	
}
