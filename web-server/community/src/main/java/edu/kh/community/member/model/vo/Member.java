package edu.kh.community.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok 라이브 러리
//-VO(Value Object ) 또는 DTO(Data Transfer Object)
// 에 작성되는 공통 코드(getter / setter / 생성자)등을 자동 추가해주는 라이러리.

@Getter //getter 자동 생성 추가
@Setter	//setter 자동 추가
@ToString //toString 자동 추가
@NoArgsConstructor //기본 생성자
@AllArgsConstructor// 모든 필드 초기화 매개변수 생성자
public class Member {
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberNickName;
	private String memberTell;
	private String memberAddress;
	private String profileImage;
	private String enrollDate;
	private String secessionFlag;
}
