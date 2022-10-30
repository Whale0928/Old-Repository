package edu.kh.jdbc.borad.model.vo;

import java.sql.Date;
import java.util.List;


//VO(Value Object) : 값 저장용 객체
//꼭 테이블과 같은 모양일 필요가 없다.
//어떤 테이블을 저장하여 옮기고 싶은지에 따라 필드 구성이 달라짐.

public class Board {
	//BOARD_NO,BOARD_TITLE,CREATE_DATE,READ_COUNT,MEMBER_NO,MEMBER_NM,
	private int boardNo;
	private String boardTitle;
	private Date createDate;
	private int readCount;
	private String memberNAME;
	private int replyCount;
	
	//게시글 상세 조회 하기 위한 필드
	private String boardContent;
	private List<Reply> replyList;
	
	//게시글 수정 , 삭제를 위해 필요한 정보
	private int memberNo;
	
	//기본 생성자
	public Board() {}

	//목록조회만 생성자
	public Board(int boardNo, String boardTitle, Date createDate, int readCount, String memberNAME, int replyCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.createDate = createDate;
		this.readCount = readCount;
		this.memberNAME = memberNAME;
		this.replyCount = replyCount;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getMemberNAME() {
		return memberNAME;
	}

	public void setMemberNAME(String memberNAME) {
		this.memberNAME = memberNAME;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public List<Reply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	
	//그냥 중간 확인 할려고 
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", createDate=" + createDate
				+ ", readCount=" + readCount + ", memberNAME=" + memberNAME + ", replyCount=" + replyCount
				+ ", boardContent=" + boardContent + ", replyList=" + replyList + ", memberNo=" + memberNo + "]";
	}
	
	
}

