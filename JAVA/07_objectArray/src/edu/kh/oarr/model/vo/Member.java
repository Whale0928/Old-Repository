package edu.kh.oarr.model.vo;

public class Member {

	//필드(멤버변수)
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAge;
	private String region; //어느 지역권 가는지( 경기 서울 충북)
	//생성자
	
	public Member() {//기본 생성자
	}
	
	public Member(String memberId,String memberPw,String memberName,int memberAge,String region) {
		//매개 변수 생성자
		
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberAge = memberAge;
		this.region = region;
	}
	
	//메소드(기능)
	//getter setter 
	
	
	//memberId 
	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String memberId){
		this.memberId = memberId;
		//return ; 
		
		//모든 메소드는 종료시 호출한 곳으로 돌아가는 
		//return 구분이 작성되어야만 하지만
		//단 void일 경우 생략 가능하다.
		//생략시 컴파일러가 자동으로 추가해준다.
	}
	
	public String getMemberPw() {
		return memberPw;
	}
	
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName=memberName;
	}
	
	public int getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(int memberAge) {
		this.memberAge =memberAge;
	}

	public String getRegion() {
		return region;
	}
	public void setReigon(String region) {
		this.region =region;
	}
	
}
