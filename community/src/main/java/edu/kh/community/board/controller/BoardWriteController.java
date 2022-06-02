package edu.kh.community.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.community.board.model.service.BoardService;
import edu.kh.community.board.model.vo.BoardDetail;
import edu.kh.community.board.model.vo.BoardImage;
import edu.kh.community.common.MyRenamePolicy;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet{

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			try {
				
			
			String mode = req.getParameter("mode"); // insert - / update 로 구분
			
			//insert는 별로의 추가 처리가 필요없음
			
			//update는 기존 게시글 내용을 조회하는 처리가 필요함
			if(mode.equals("update")){
				
			}
			 
			}catch(Exception e){
				
			}
			String path = "/WEB-INF/views/board/boardWriteForm.jsp";
			req.getRequestDispatcher(path).forward(req, resp);;
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			try {
				// insert / update 구분 없이 전달받은 파라미터 모두 꺼내서 정리하기
				
				//*** enctype ="multipart/form-data" 인코딩 미지정 형식의 요청 ***
				//->HttpServlet로 파라미터 얻어오기가 불가능한 상태.
				//-->MulitpartRequest를 이용(cos 라이브러리에서 제공)
				//---> 업로드 최대 용량 , 저장 실제 경로 , 파일명 변경 정책 , 문자열파라미터 인코딩 -- 총 4개 설정 필요 
				
				//최대 업로드 용량
				int maxSize = 1024*1024*100; //100MB
				
				//저장할 실제 경로
				HttpSession session = req.getSession();
				// 최상위 경로 ( " / "  == webapp 폴더 )의 컴퓨터 상의 실제 절대 경로를 얻어옴.
				String root = session.getServletContext().getRealPath("/"); //webapp폴더까지의 경로
				//실제 파일이 저장되는 폴더의 경로	
				String folderPath = "/resources/images/board/"; //저장될 폴더 경로 webapp 아래부터
				// 위에 두개 합쳐서 하나의 경로로 만들기
				// memberProfile 폴더까지의 절대 경로
				String filePath = root+folderPath;
				
				//이름 변경 클래스 만들어둠 MyRenamePolicy
				
				String encoding = "UTF-8"; //파라미터중 파일 외 파라미터(문자열) 인코딩 지정
				
				//MultipartRequest 객체 생성
				//-> 객체가 생성됨과 동시에 파라미터로 전달된 이미지파일들은(input type="files") 지정된 경로에 저장(업로드)된다 
				MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding,new MyRenamePolicy());
				
				//MulitpartRequest . getFileNames();
				// - 요청 파라미터 중! 모드 file 타입 태그의 name 속성 값을 얻어와 
				//	 Enumeration 형태로 반환  (Iterator의 과거 버전) 
				//	 --> 해당 객체에 여러 값이 담겨 있고 이를 순서대로 얻어오는 방법을 제공
				//	 --> 보통 순서가 없는 모듬(set과 같은 경우)에서 하나씩 꺼낼때 사용
				
				Enumeration<String> files = mpReq.getFileNames(); 
				//file 타입 태그의 name 속성 0 , 1 , 2, , 3 , 4가 순서가 섞인 상태로 저장됨
				
//				업로드된 이미지의 정보를 모아둘 List 생성
				List<BoardImage> imageList = new ArrayList<BoardImage>();
				
				while(files.hasMoreElements()) {//다음 요소가 있는가? 있으면true
					String name = files.nextElement(); // 다음 (name 속성 값)를 얻어옴
//					System.out.println("name은 : "+name);
					
					//files타입 태그들의 name속성값을 모두 얻어옴
					//업로드가 안된 file타입 태그의 name오 얻어와짐
					String rename =mpReq.getFilesystemName(name); //변경된 파일명
					String original =mpReq.getOriginalFileName(name); //원본 파일명
					
//					System.out.println("reaname:"+rename);
//					System.out.println("original:"+original);
					
					if(rename != null){ //업로드된 파일이 있는 경우						
										//현재 files에서 얻어온 name속성을 이용해
										//원복 또는 변경을 얻어왔을 때 그 값이 null이 아닌 경우
						
						// 이미지정보를 담은 객체(BoardImage)를 생성
						BoardImage image = new BoardImage();
						image.setImageOriginal(original); //원본명(다운로드할때 사용)
						image.setImageReName(folderPath+rename);  //저장되는 폴더 경로+ 파일명 (그냥 파일만 저장하면 안된다.)
						image.setImageLevel(Integer.parseInt(name)); // 이미지 위치도 저장 (0은 썸네일)
						imageList.add(image); //리스트에 추가
					} //if문 끝
					
					
				} //while 끝
				
				// ** 이미지를 제외한 게시글 관련 정보들 저장
				// 그냥 req으로 하면null로 나옴
				String boardTitle = mpReq.getParameter("boardTitle");
				String boardContent = mpReq.getParameter("boardContent");
				int boardCode = Integer.parseInt(mpReq.getParameter("type"));
				
				Member loginMember = (Member)session.getAttribute("loginMember"); //세션에서 로그인 정보 얻어오기
				int memberNo = loginMember.getMemberNo(); //회원 번호
				
				//게시글 관련 정보를 하나의 객체(BoardDetail) 에 저장
				BoardDetail detail = new BoardDetail();
				detail.setBoardTitle(boardTitle);
				detail.setBoardContent(boardContent);
				detail.setMemberNo(memberNo);
				
				//boardCode는 별도 매개변수로 전달
				//만들면되지만 구지 안만들어도 괜찮아서
				
				
				//---------------------------게시글 작성에 필요한 기본 파라미터 얻어오기 끝-------------------
				
				BoardService service = new BoardService();
			
				
				//모드 (insert / update) 에 따라서 추가 파라미터 얻어오기 및 서비스 호출
				String mode = mpReq.getParameter("mode");
				
				if(mode.equals("insert")){ //삽입
					//게시글 삽입 서비스 호출 후 결과(삽입된 게시글의 번호) 반환
					//반환된 게시글 번호를 이용해 상세조회 화면으로 redirect해 보여준다
					int boardNo = service.insertBoard(detail,imageList,boardCode);
					
			
					String path = null;
					
					if(boardNo>0) { //성공
						session.setAttribute("message", "게시글이 등록되었습니다");
						//detail?no=숫자&type=숫자;
						path="detail?no="+boardNo+"&type="+boardCode;
//						detail?no=1000&cp=1&type=2
						
					}else {//작성 실패
						session.setAttribute("message", "게시글 등록에 실패하였습니다.");
						//실패 했을때
						path="write?mode="+mode+"&type="+boardCode;
					}
					resp.sendRedirect(path); //리다이렉트
				}
				
				
				if(mode.equals("update")){ //수정
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
}
