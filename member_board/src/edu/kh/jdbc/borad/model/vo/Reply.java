package edu.kh.jdbc.borad.model.vo;

import java.sql.Date;

public class Reply {
	int replyNo;
	String replyContent;
	Date createDate;
	int memberNo;
	int boardNo;
	String memberNAME;
	
	public Reply() {}

	public Reply(int replyNo, String replyContent, Date createDate, int memberNo, int boardNo, String memberNAME) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.memberNo = memberNo;
		this.boardNo = boardNo;
		this.memberNAME = memberNAME;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getMemberNAME() {
		return memberNAME;
	}

	public void setMemberNAME(String memberNAME) {
		this.memberNAME = memberNAME;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", createDate=" + createDate
				+ ", memberNo=" + memberNo + ", boardNo=" + boardNo + ", memberNAME=" + memberNAME + "]";
	}
	
	
}
