package edu.kh.comm.board.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDetail {
	private String boardTitle;
	private String boardContent;
	private String createDate;
	private String updateDate;
	private int readCount;
	private String memberNickname;
	private String profileImage;
	private int memberNo;
	private int boardNo;
	private String boardName;

	private List<BoardImage> imageList;
	
	private int boardCode; // 어느 게시판인지 
}
