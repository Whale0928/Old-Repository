package edu.kh.jsp.model.vo;

import java.sql.Date;

public class Member {
	
	   private int memberNo;
	   private String memberId;
	   private String memberPw;
	   private String memberName;
	   private char memberGender;
	   private Date enrollDate;
	   private char secessionFlag;
	   
	  
	 public Member() {} //기본 생성자

	 

	public Member(int memberNo, String memberId, String memberPw, String memberName, char memberGender, Date enrollDate,
			char secessionFlag) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberGender = memberGender;
		this.enrollDate = enrollDate;
		this.secessionFlag = secessionFlag;
	}

	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


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
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public char getMemberGender() {
		return memberGender;
	}


	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}


	public Date getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}


	public char getSecessionFlag() {
		return secessionFlag;
	}


	public void setSecessionFlag(char secessionFlag) {
		this.secessionFlag = secessionFlag;
	}


	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberName="
				+ memberName + ", memberGender=" + memberGender + ", enrollDate=" + enrollDate + ", secessionFlag="
				+ secessionFlag + "]";
	}
	 
	 
}
