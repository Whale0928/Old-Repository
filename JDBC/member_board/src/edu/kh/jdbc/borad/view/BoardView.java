package edu.kh.jdbc.borad.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.borad.model.service.BoardService;
import edu.kh.jdbc.borad.model.vo.Board;
import edu.kh.jdbc.borad.model.vo.Reply;
import edu.kh.jdbc.member.model.vo.Member;


//게시판 메뉴 전용 화면
public class BoardView {
	
	private Scanner sc = new Scanner(System.in);
	private BoardService service = new BoardService(); 
	
	/**게시판 전용 메뉴 화면 출력
	 * @param loginMember (현재 로그인한 회원 정보)
	 */
	public void boardMenu(Member loginMember) {
		int menuNum=-1;
		
		do {
			try {

				System.out.println("\n********** 게시판 메뉴 **********\n");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회 (게시글 번호 입력)");
									//게시글 상세 조회 + 댓글 목록조회
				
									//게시글 작성자와 로그인한 회원이 같을 때
									//게시글 수정(UPDATE) , 게시글 삭제(DELETE)
									//댓글 삽입 / 수정 / 삭제
				System.out.println("3. 게시글 작성(INSERT)");
				System.out.println("4. 게시글 검색(제목/내용/제목+내용/작성자)");
				System.out.println("0. 이전 메뉴로 돌아가기.");
				
				 System.out.print("\n 메뉴를 선택해주세요. ");
				 menuNum = sc.nextInt();
				 sc.nextLine();System.out.println();//개행문자 제거와 줄바꿈
				
				switch(menuNum) {
				case 1:selectAll();break;
				case 2:selectOne(loginMember);break;
				case 3:insertBoard(loginMember.getMemberNo());break;
				case 4:searchBoard();break;
				case 0:System.out.println("회원 메뉴로 돌아갑니다....");break;
				default : System.out.println("\n 메뉴에 작성된 번호만 입력해주세요 "); 
				}
				
				
				//
				
			}catch(InputMismatchException e){
				System.out.println("\n잘못 입력되었습니다");
				sc.nextLine();
			}
			
		}while(menuNum !=0);
		
	}

	/**
	 * 전체 게시글 목록 조회 메서드
	 */
	private void selectAll() {
		//view > sevice > dao > db > dao> service > view
		System.out.println("[게시글 목록 조회]");
		try {
			//게시글 목록 조회 service 
			List<Board> boardList= service.selectAll();
			
			if(boardList.isEmpty()) {
				System.out.println("\n[조회된 목록이 없습니다]");
			}else {
				System.out.println("번호            제목[댓글수]         작성자      작성일       조회수");
				System.out.println("---------------------------------------------------------------------------------");
				for(Board b : boardList) {
					System.out.printf("%d  %20s[%d]   %5s %15s %10d \n",
								b.getBoardNo(),b.getBoardTitle(),b.getReplyCount(),
								b.getMemberNAME(),b.getCreateDate(),b.getReadCount()
								);
					
				}
			}
			
		}catch(Exception e) {
			System.out.println("\n<게시글 목록 조회중 예외 발생>\n");
			e.printStackTrace();
		}
	}

	/**게시글 상세 조회
	 * @param LoginMember
	 */
	private void selectOne(Member loginMember) {
		
		System.out.print("조회할 게시글 번호 입력 :" );
		int boardNo = sc.nextInt();
		sc.nextLine();
	
		//게시글 상세 조회 service 호출후 결과 반환 (게시글 1개의 정보 == board)
		try {
			Board board = service.selectOne(boardNo);

			if(board != null) { //조회된 게시글이 있는 경우
				System.out.println("--------------------------------------------------------");
				System.out.printf("<%2d> [%10s]\n",board.getBoardNo(),board.getBoardTitle());
				System.out.printf("조회수 : %d \n작성일 :%s\n작성자: [%s]\n"
						,board.getReadCount(),board.getCreateDate(),board.getMemberNAME());
				System.out.println("=======================================================\n[내용]");
				System.out.println(board.getBoardContent());
				System.out.println("=======================================================\n[댓글]");
				for(Reply temp : board.getReplyList()) {
					System.out.println("--------------------------------------------------------");
					System.out.printf("<%d> %s : %8s\n"
						,temp.getReplyNo(),temp.getMemberNAME(),temp.getReplyContent());
				}
				
				//-----------------------------------------------------------------------
				//상세 조회용 메뉴 출력
				System.out.println("\n********** 상세 조회 메뉴 **********\n");
				
				//누구나 가능 
				System.out.println("1. 댓글 삽입");
	
				//댓글 번호를 입력 받아 
				//댓글을 작성한 회원번호 ==로그인한 회원 번호
				System.out.println("2. 댓글 수정");
				System.out.println("3. 댓글 삭제");
				
				//상세 조회된 게시글의 회원 번호가== 로그인한 회원 번호와 같을 때
				if(board.getMemberNo() == loginMember.getMemberNo() ) {
					System.out.println("4. 게시글 수정");
					System.out.println("5. 게시글 삭제");
				}
				System.out.println("0. 게시판 메뉴로 돌아가기");
				
				System.out.print("메뉴 선택 >>");
				int menuNum = sc.nextInt();
				sc.nextLine();
			
				switch(menuNum) {
				case 0:System.out.println("\n게시판 메뉴로 돌아갑니다\n"); break;
				case 1:insertReply(loginMember,boardNo); break;
				
				case 2:	case 3:   
				//댓글 번호 입력 -> 댓글이 있는지 확인 > 해당 댓글이 로그인한 회원께 맞는지 검사
				String temp = menuNum == 2 ? "\n[댓글 수정]\n" : "\n[댓글 삭제]\n";
				System.out.println(temp);
				
				System.out.print("댓글 번호를 입력:");
				int inputNo =sc.nextInt();
				sc.nextLine();
				
				//입력 받은 댓글 번호가 댓글목록에 있는지 확인.
				Reply reply = null; //확인된 댓글을 참조할 변수
				for(Reply r : board.getReplyList()) {
					if(inputNo==r.getReplyNo()) {//입력받은 번호화 일치하는 댓글이 있을 경우에~
						reply = r;
						break;
					}
				}
				
				if(reply ==null) {
					System.out.println("입력 된 번호의 댓글이 없습니다");
				}else {
					if(loginMember.getMemberNo()==reply.getMemberNo()) {
						if(menuNum == 2) {
							updateReply(inputNo);
						}else {
							deleteReply(inputNo);
						}
					}else {
						System.out.println("작성자만 변경할 수 있습니다");
					}
				}
					
					
				break;
				case 4: case 5:
					//게시글 작성자 번호와 로그인한 회원의 번호가 같은 경우에만
					if(board.getMemberNo() == loginMember.getMemberNo()) {
						if(menuNum==4) {//4번 입력됬을때.
							updateBoard(boardNo); //수정용
						}else {//아니면 5번
							//삭제 의사 재확인(Y/N)
							//y 일 경우 보안문자 생성
							//보안문자가 일치하는 경우에 삭제 진행
							deleteBoard(boardNo);//삭제용
						}
					}else {
						System.out.println("\n 메뉴에 있는 내용만 입력해주세요");
					}
					break;
				
				default:System.out.println("\n 메뉴에 있는 내용만 입력해주세요");
				}
			}//ifboard ! = null
			else {//board==null 조회된게시글이 없는 경우
				System.out.println("\n[존재하지 않는 게시글 번호입니다.]\n");
			}
		} catch (Exception e) {
			System.out.println("\nxx 게시글 상세 조회중 오류 발생 xx\n");
			e.printStackTrace();
		}
	}

	/**삭제용 메서드
	 * @param boardNo
	 */
	private void deleteBoard(int boardNo)throws Exception{
		//y n만 입력되도록 무한 반복
		
		char ch = ' ';
		
		System.out.println("\n [게시글 삭제]");
	
		while(true){
			System.out.print("정말 삭제 하시겠습니까?(Y/N) :");
			ch = sc.next().toUpperCase().charAt(0);
			
			if(ch =='Y'|| ch=='N')break;
			else System.out.println("Y / N 중 하나만 입력주세요");
			}
		
		if(ch=='Y') { //삭제 의사가 확실한 경우
			//보안 문자 생성
			String cap = captcha();
			System.out.println("보안문자를 입력해주세요 :"+cap);

			System.out.print("보안 문자 입력:");
			String input = sc.next();
			
			if(input.equals(cap)) {//문자열이 같을 때
				//삭제 서비스 호출
				int result = service.deleteBoard(boardNo);
				if(result>0) {
					System.out.println(boardNo+"번 게시글이 삭제되었습니다");
				}else {
					System.out.println("삭제 실패");
				}
			}else {
				//다를때
				System.out.println("\n보안 문자가 일치하지 않습니다\n");
			}
				
		}else {//삭제 취소하는 경우
			System.out.println("\n삭제를 취소합니다\n");
		}
	}
	
	/**보안문자 생성 메서드
	 * @return cap
	 */
	private String captcha(){
		String cap = "";
		
		for(int i=0; i<5;i++) {//5바퀴
			cap += (char)(Math.random()*26+'a');
				//난수로 숫자를 생성하고 그걸 다시 문자로 바꿔서 문자열에 집어넣는다
		}
		return cap;
	}
	
	/**게시글 수정용 메소드
	 * @param boardNo
	 */
	private void updateBoard(int boardNo) {
		System.out.println("\n[게시글 수정]\n");
		
		System.out.print("\n수정할 제목 :");
		String boardTitle =sc.nextLine();
		
		System.out.print("\n수정할 내용(입력 종료 시 @exit 입력)\n");
		String boardContent = inputContent();
		
		//게시글 번호 , 수정한 제목 ,내용 한번에 옴기기
		Board board = new Board();
		board.setBoardNo(boardNo);
		board.setBoardTitle(boardTitle);
		board.setBoardContent(boardContent);
		
		try {		
			int result = service.updateBoard(board);
			
			if(result > 0 ) {
				System.out.println("\n["+boardNo+"번 게시글 수정 완료]\n");
			}else {
				System.out.println("\n[수정 실패]\n");
			}
			
		}catch(Exception e) {
			System.out.println("\n게시글 수정중 예외 발생\n");
			e.printStackTrace();
		}
		
	}
	
	/*String (객체)에 대하여
	 * - 불변성(immutable) <-> 가변성(mutable)
	 * 
	 * ex) String str = "Abc"; // heap 형역에 String 객체가 생성되고 생성된 객체에 abc문자열 저장
	 * 
	 * 		str ="123" //heap 영엽에 새로운 string 객체가 생성되고 
	 * 				   //생성된 객체에 123문자열 저장 후 
	 * 				   //객체주소를 str에 대입하다.
	 *   
	 * 	str은 이제 123 문자열의 주소를 가지고 있지만 Abc문자열은 변하지 않는다
	 
	 * 
	 * ex) String str = "abc";
	 * 
	 * 		str+= "123"; //123이 저장된 String객체 생성후 
	 * 					 //abc 와 123이 합쳐진 String객체가 추가로 별도 생성된다
	 *					 //그 후 "abc123" 객체 주소가  str에 저장된다.
	 * 
	 * **String의 문제점
	 * 	-- String에 저장된 값을 바꾸거나 누적하려 할때마다
	 * 	-- String객체가 무분별하게 생성됨 - > 메모리 낭지
	 * 
	 * **해결 방법
	 * -StringBuffer / StringBuilder
	 * 	클래스를 자바에서 제공함
	 * (StringBuffer / StringBuilder 사용방법은 똑같음)
	 * -> 동기 비동기의 차이
	 *  
	 * */
		
	/** 게시글, 댓글 내용 입력메서드
	 * @return
	 */
	private String inputContent() {
		String input = null;
//		String content = " ";
		StringBuffer content = new StringBuffer();
	
		while(true) { //게시글을 무한이 입력받아 하나의 변수에 누적을 한다.
			
			input = sc.nextLine();
			
			if(input.equals("@exit")){ //@exit가입력되면 누적 종료
				break;
			}else {
//				content +=input+"\n";
				content.append(input);
				content.append("\n");
				//StringBuffer에 저장된 문자열 제일 끝에 input을 추가(누적)
				//append : (제일뒤에) 덧붙이다 ,추가하다
				
				// - > 하나의 StrinfBuffer 객체에 문자열이 계속 누적이 되서 
				// (추가적인 String 객체 생성이 없다) == 메모리에 효율적이다.
				
			}
		}
		return content.toString();//String Buffer에 오버라이딩된 toString()
								  //저장된 문자열을 String 자료형 형태로 반환
	}

	/**댓글 삽입
	 * @param loginMember
	 * @param boardNo
	 */
	private void insertReply(Member loginMember,int boardNo) {
		System.out.println("[댓글 작성]");
		
		System.out.println("댓글 내용 입력(종료시 @exit 입력)");
		String replyContent = inputContent();
		
		// 회원 번호 , 게시글 번호 ,  댓글 내용 reply 객체에 저장
		
		Reply reply = new Reply();
	
		reply.setMemberNo(loginMember.getMemberNo());
		reply.setBoardNo(boardNo);
		reply.setReplyContent(replyContent);
		
		try {
			
			int result = service.insertReply(reply);
			
			if(result > 0) {
				System.out.println("\n[댓글이 작성 되었습니다.]");
				}else {
					System.out.println("[작성 실패]");
				}
		}catch(Exception e) {
			System.out.println("댓글 삽입중 오류 발생");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	/**댓글 수정
	 * @param loginMember
	 * @param boardNo
	 */
	private void updateReply(int replyNo) {
		System.out.println("\n[수정할 내용 입력 (종료시 @exit 입력)]\n");
		String replyContent = inputContent();
		
		Reply reply = new Reply();
		reply.setReplyContent(replyContent);
		reply.setReplyNo(replyNo);
		
		try {
			int result = service.updateReply(reply);			
			
			if(result > 0 )System.out.println(reply.getReplyNo()+"댓글이 수정되었습니다\n");
			else System.out.println("\n[댓글 수정 실패!]\n");
		}catch(Exception e) {
			System.out.println("[수정중 오류 발생]");
			e.printStackTrace();
		}
		
	}
		
	/**댓글 삭제
	 * @param loginMember
	 * @param boardNo
	 */
	private void deleteReply(int replyNo)throws Exception {
		char ch = ' ';
		
	while(true) {
		System.out.print("정말 삭제하시겠습니까? (Y/N) :");
		ch = sc.next().toUpperCase().charAt(0);
			if(ch == 'Y'||ch =='N') {
				break;
			}else {
				System.out.println("Y 또는 N을 입력해주세요");
			}
		}
	if(ch == 'Y') {
		String cap = captcha();
		
		System.out.println("다음 보안 문자를 입력해주세요 :"+cap );
		
		System.out.print("보안 문자를 입력해주세요 : ");
		if(sc.next().equals(cap)){
			int result = service.deleteReply(replyNo);
			
			if(result > 0)System.out.println("\n["+replyNo+"번 댓글 삭제 완료!]\n");
			else		  System.out.println("\n[삭제 실패!]\n");
		}else {
			System.out.println("보안 문자가 일치하지 않습니다.");
		}
		
		
		
	}else {
		System.out.println("\n댓글 삭제를 취소합니다.\n");
	}
		
	
		
	}
	
	/**게시글 작성
	 * @param memberNo
	 */
	private void insertBoard(int memberNo) {
		System.out.println("\n[ 게시글 작성 ]\n");
		
		System.out.print("게시글 제목 : ");
		String boardTitlle = sc.nextLine();
		
		System.out.println("\n게시글 내용 입력 (@exit입력시 종료)\n");
		String boardContent = inputContent(); 
		
		Board board = new Board();
		board.setMemberNo(memberNo);
		board.setBoardTitle(boardTitlle);
		board.setBoardContent(boardContent);
		
		try {
		
		int result = service.insertBoard(board);
		
			if(result>0) {
				System.out.println("\n[작성 완료]\n");
			}else {
				System.out.println("\n[작성 실패]\n");
			}
		}catch(Exception e) {
			System.out.println("\n게시글 작성중 예외 발생\n");
		}
	}

	/**
	 * 게시글 검색
	 */
	private void searchBoard() {
		System.out.println("\n[ 게시글 검색]\n");
		
		int menuNum=-1;
		do {
			try {
			System.out.println("--검색 조건을 선택해주세요--");
			System.out.println("1.제목");
			System.out.println("2.내용");
			System.out.println("3.제목+내용");
			System.out.println("4.작성자");
			System.out.println("0.돌아가기");
			
			System.out.print("선택 :");
			menuNum=sc.nextInt();
			sc.nextLine();
			
			switch(menuNum) {
			case 0: System.out.println("\n게시판 메뉴로 돌아갑니다\n");break;
			case 1:	case 2:	case 3:	case 4:
				//검색어 입력 - > Service 호출
				System.out.print("검색어 입력:");
				String keyword = sc.nextLine();
				
				List<Board> boardList = service.searchBoard(menuNum,keyword);
				
				if(boardList.isEmpty()) {
					System.out.println("\n검색된 값이 없습니다\n");
				}else {
					System.out.println("------------------------------------------------------------------------");
		            System.out.printf("%3s  %13s%12s   %7s%3s %7s%2s %s\n",
		                     "글번호", "제목", "", "작성자", "", "작성일", "" , "조회수");
		            System.out.println("------------------------------------------------------------------------");
		            
		            // 향상된 for문
		            for(Board b : boardList) {
		               
		               System.out.printf("%3d  %20s [%d]  %10s  %s %3d\n",
		                     b.getBoardNo(), b.getBoardTitle(), b.getReplyCount(),
		                     b.getMemberNAME(), b.getCreateDate().toString(), b.getReadCount());
		            }
//					for(Board b:boardList) {
//						System.out.printf("%d.%s      %s\n",
//								b.getBoardNo(),b.getBoardTitle(),b.getMemberNAME());
//					}
				}
			break;
			default : System.out.println("\n메뉴에 있는 번호만 입력해주세요.\n");
			}
			
			}catch(InputMismatchException e) {
				System.out.println("\n[입력 형식이 올바르지 않습니다.]\n");
				e.printStackTrace();
			}catch(Exception e) {
				System.out.println("\n[검색중 오류발생.]\n");
				e.printStackTrace();
				break; //검색 조건중 오류가 났음으로 일단 반복문 종료한다
			}
		
		}while(menuNum != 0);
		
	}

};//마지막 