package edu.kh.community.common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class EncryptWrapper extends HttpServletRequestWrapper{

	
	// HttpServletRequestWrapper 
	// - 클라이언트 요청 객체 HttpServletRequest를 '오버라이딩' 하는 방법을 제공하는 클래스;
	// - 

	//생성자가 작성되어 있으면
	//컴파일러가 기본생성자를 자동으로 추가 안한다.
	
	
	// -> Wrapper 클래스 생성 시 반드시 HttpServletRequest 객체를 매개변수로 전달해야 한다.
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	
	// getParameter() 오버라이딩
	@Override
	public String getParameter(String name) {
		//매개변수 name : input 태그의 name 속성 값
		//super . getParameter(name) : 기본으 getParameter()   ( 메서드 원래 있던 대로 )
		
		String value = null;
		
		switch(name){
		case "inputPw" :    //입력된 비밀번호 
		case "memberPw" :   //로그인된 유저의 비밀번호
			
		case "currentPw" :  // 원래 비밀번호
		case "newPw" :      //새 비밀번호
			
			value = getSha512(super.getParameter(name)); 
			break;
			
		//암호화가 필요한 비밀번호 자료가 아니면 그냥 기존에 있던 값 그대로 유지한다.
		//기본 getParameter() 메서드의 형태 유지
		default : value = super.getParameter(name); 
		}
		return value;
	}
	
	//암호화 메서드(SHA-512 해시 함수)
	//해시 함수 : 어떤 문자열이든 일정한 길이의 무작위 문자열로 변환하는 함수(중복 X).
	private String getSha512(String pw) {
		System.out.println("암호화 전 비밀번호:"+pw);
		//매개변수 pw : 암호화 되기 전의 비밀번호.
		
		//암호화 안된걸 받아서 암호화 해가지고 내보낸다
		String encryptPw = null; //암호화된 비밀번호를 저장할 변수
		
		//1) 해시 함수를 수행할 객체를 참조할 변수 선언
		MessageDigest md = null;
		
		try{
			// 2) SHA-512 방식의 해시 함수를 수행할 수 있는 객체를 얻어옴
			md = MessageDigest.getInstance("SHA-512");
			
			// 3) md를 이용해 암호화를 진행할 수 있도록 pw를 byte[] 배열 형태로 변환
			byte[] bytes = pw.getBytes(Charset.forName("UTF-8")); //UTF-8 문자열인거 감안하고 변환해라
			
			// 4) 암호화  -> 암호화 결과가 md 내부에 저장함.
			md.update(bytes);
 			
			// 5) 암호화된 비밀번호를 encryptPw에 대입
			//digest가 보관하고 있다
			//byte[]배열을 String으로 변환할 필요가 있다.
			encryptPw = Base64.getEncoder().encodeToString(md.digest());
					//Base64 : byte 코드를 문자열로 변환하는 객체
					//base64 에서 변환할수 있게 해주는 객체를 가져와 변환한다
			
		}catch(NoSuchAlgorithmException e) {
			//SHA-512 해시 함수가 존재하지 않을 때 예외 발생
			e.printStackTrace();
		}
	
		
		
		return encryptPw;
	}
}

