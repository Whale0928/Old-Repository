package edu.kh.oop.method.model.vo;

public class Member {

	// member 클래스의 필드(속성)
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAge;

	// 기본 생성자
	// 생성자 작성 규칙
	// 1 ) 반환형 없이
	// 2 ) 클래스의 이름과 똑같이
	public Member() {
	}

	// 매개변수 생성자 (필드 모두 초기화 용도)
	public Member(String memberId, String memberPw, String memberName, int memberAge) {
		// 오버로딩이 적용되어있다(매개변수의 개수가 다름)

		// 매개변수로 받아온 값을 옴겨 담아준다.
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberAge = memberAge;
	}

	// 기능 (간접 접근할수 있는 기능 getter / setter만들기)

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
				//get+돌려보내고 싶은 필드
		return memberName;
	}

	//매개변수로 전달받은 값을 MemberName 필드에 대입
	//매개변수 == 전달받은 값을 지니고 있는 변수
	public void setMemberName(String memberName) {
		//		set+ 필드명   (전달 받아올 값)
		this.memberName = memberName;
		
	}

	public int getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}

}
