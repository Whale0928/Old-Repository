package edu.kh.oop.praction;

public class Member {

	// 속성
	private String userId;
	private String userPw;
	private String userName;
	private int userAge;
	private char userGender;
	
	public Member() {} // 기본 생성자 : 반환형이 없으면서
	 
	public Member(String userId, String userPw,String userName,int userAge,char userGender) {
		this.userId =  userId;  
		this.userPw =  userPw;  
		this.userName =  userName;  
		this.userAge =  userAge;  
		this.userGender =  userGender;  	
	}

	// 기능
	// 기본 생성자 // 매개변수 생성자.
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public char getUserGender() {
		return userGender;
	}

	public void setUserGender(char userGender) {
		this.userGender = userGender;
	}
	
	
}
