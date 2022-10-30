package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.community.common.MyRenamePolicy;
import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/myPage/profile")
public class MyPageProfileServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//메인페이지 -> 프로필 이미지 클릭
		//마이페이지 -> 프로필 클릭
		String path = "/WEB-INF/views/member/myPage-profile.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
				
			
			//System.out.println(req.getParameter("profileImage"));  == null이 나오는 이유
			
			//파라미터가 얻어와지지 않는 이유
			// 1. enctype="multpart/form-data" -> 인코딩이 안되어 있어 파라미터가 인지되지 않음.
			// 2. input type= "file" -> 파일형태 데이터
			
			//multpart/form-data 형식의 요청을 처리할 수 있는 전용 Request 객체가 필요
			//  --> MulitpartRequest (cos.jar 라이브러리 이용)
			//						파일 업로드 라이브러리
			
			//*** MulitpartRequest 사용을 위한 준비단계***
			//1. 업로드 되는 파일 크기의 전체 합을 지정(Byte 단위)
			int maxSize = 1024 * 1024 * 20; 
						//1kb    1MB   20MB
			
			//2. 업로드 되는 파일이 어디에 저장될지 경로 지정.
			//			-> 서버 컴퓨터 내부 폴더(절대 경로)
			//2-1 . server option 확인.
			// servers -> 서버설정 확인 -> OverView -> server options -> Serve Modules Without Publishing 체크
			// (업로드 되는 파일이 (webapp폴더 내부에 저장 될 수 있음))
			
			//2-2. memberProfile 폴더까지의 절대 경로 얻어오기.
			HttpSession session = req.getSession(); //session 얻어오는것은 상관없음 ( 사용 가능 )
			// 최상위 경로 ( " / "  == webapp 폴더 )의 컴퓨터 상의 실제 절대 경로를 얻어옴.
			String root = session.getServletContext().getRealPath("/");
			//실제 파일이 저장되는 폴더의 경로
			String folderPath = "/resources/images/memberProfile/";
	
			// memberProfile 폴더까지의 절대 경로
			String filePath = root+folderPath;
			
			
			
			
			//3. 저장되는 파일의 파일명 중복을 방지하기 위한 파일명 변경 규칙이 필요
			//-> cos.jar 라이브러리에서 제공하는 FileRenamePolicy 인터페이스 상속 클래스 생성
			//MyRenamePolicy 클래스 생성
			
			
			//4. 파일 이외의 파라미터의 문자 인코딩 지정.
			String encoding = "UTF-8";
			
			//5. MulitpartRequest 생성
			// ********************(중요)********************
			//Mult part Request객체가 생성됨과 동시에 지정된 경로에 
			//지정된 파일명 변경 정책에 맞게 이름이 바뀐 파일이 저장(upload)된다.
			
			MultipartRequest mpReq = new MultipartRequest(req, filePath, maxSize, encoding, new MyRenamePolicy());
			
			// 프로필 이미지 변경 Service 호출시 필요한 값들
			// 1) 로그인한 회원의 회원번호(PK)
			Member loginMember = (Member)session.getAttribute("loginMember"); //로그인한 회원정보를 세션에서 꺼내옴
			int memberNo = loginMember.getMemberNo();
			
			// 2) 업로드된 이미지의 웹 접근 경로(folderPath에 저장되어있다) + 변경된 파일명.
			
			// -> 원복 파일명
			//getOriginalyFileName("input type="file" 의 name속성값)
			//System.out.println(mpReq.getOriginalFileName("profileImage"));
			
			// -> 변경된 파일명
			//getFilesystemName("input type="file" 의 name속성값)
			//System.out.println(mpReq.getFilesystemName("profileImage"));

			//DB에 대입될 프로필 이미지 경로.
			//단 x버튼이 클릭된 상태면 null을 가지게 한다.
			String profileImage = folderPath+mpReq.getFilesystemName("profileImage");
			
			//프로필 이미지 삭제
			// 1) "delete" input type = "hidden" 태그의 값(파라미터 얻어오기)
			// **(주의!)** multipart/from-data 형식의 요청을 처리 중이기 때문에
			// req를 이용해 parameter를 얻어올 수 없다(Byte코드이기때문에)
			//  위에서 정의한 mpReq를 이용해 확인 가능
			int delete = Integer.parseInt(mpReq.getParameter("delete"));
			
			// 2) delete의 값이 1(눌러진경우) 프로필 이미지의 값을 Null로 반환
			if(delete ==1) {
				profileImage = null;
			}
			
			
			//프로필 이미지 변경 Service 호출후 결과 반환 받기
			MemberService service = new MemberService(); 
			
			int result = service.updateProfileImage(memberNo,profileImage);
			
			if(result>0){ //DB 업데이트 수행 성공했을때
				session.setAttribute("message", "프로필 이미지가 변경되었습니다");
				//DB의 프로필 이미지 정보는 변경 되었으나 
				//session에 저장된 로그인 정보중 프로필 이미지는 변경되지 않은
				//동기화 작업 필요
				loginMember.setProfileImage(profileImage);
			}else {
				session.setAttribute("message", "프로필 이미지가 변경 실패");
			}
			
			//성공 실패 관계 없이 프로필에 화면 재요청
			//resp.sendRedirect("GET방식");
			resp.sendRedirect("profile");
			
		}catch(Exception e) {
			e.printStackTrace();
			resp.setStatus(500); //500번 server 에러
			resp.getWriter().print(e.getMessage());//에러용을 출력
		}
	}
}
