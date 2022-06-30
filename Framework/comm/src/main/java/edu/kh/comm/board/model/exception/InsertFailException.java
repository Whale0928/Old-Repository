package edu.kh.comm.board.model.exception;


//사용자 정의 예외 생성 방법
//1) 기존에 존재하는 예외 클래스 하나를 상속 받는다
//   단 , RuntimeException과 상속관계인 예외를 상속받게 되면
//	 자동적으로 Unchecked Exception이 된다.

//2) 생성자 작성시 super() 생성자를 이용해서 코들 구현

public class InsertFailException extends RuntimeException{

	//따로 별건 없다 그냥 예외의 이름을 생각하는 수준
	
	public InsertFailException() {
		super("게시글 삽입 실패");
	}
	
	public InsertFailException(String msg) {
		super(msg);
	}

}
