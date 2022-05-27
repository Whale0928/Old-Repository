package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/selectOne")
public class SelectOneServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터 얻어오기.
		String memberEmail = req.getParameter("memberEmail");
		//전달받은 멤버 정보 저장
		
		
		try {
			MemberService service = new MemberService();
			
			
			Member member = service.selectOne(memberEmail); 
			
			//resp.getWriter().print(member);
			//print 매개변수로 참조형 변수가 작성되면 
			//해당 참조형 변수의 toString() 메서드를 자동으로 호출해서 출력한다.
			
			// ** Java 객체를 Javascript 객체로 변환하여 응답(출력)해야한다 **
			// -> Java 객체 - >   Javascript 객체 형태 문자열(JSON)  -> Javascript 객체
			
			//-------------------------------------------------------------------------------------
			//1) JSON 직접작성 -> 개 어이없음
			//String str = "{\"memberNickname\":\""+member.getMemberNickName()+"\"}";
							//"{'memberNickname':''유저일'}"
			//resp.getWriter().print("");
			
			//-------------------------------------------------------------------------------------
			//2) JSON-Simple 라이브러리에서 제공하는 JSONObject 사용
			/*
			 * if(member != null) { JSONObject obj = new JSONObject(); //map형식의 객체.
			 * 
			 * obj.put("memberEmail",member.getMemberEmail());
			 * obj.put("memberNickname",member.getMemberNickName());
			 * obj.put("memberTel",member.getMemberTell());
			 * obj.put("memberAddress",member.getMemberAddress());
			 * obj.put("enrollDate",member.getEnrollDate());
			 * 
			 * //JSONObject의 toString() 메서드는 //JSON 형태로 출력될 수 있도록 오버라이딩이 되어있다.
			 * resp.getWriter().print(obj);
			 * 
			 * }else { //회원 정보 없는 경우 null이라도 반환 resp.getWriter().print(member); }
			 */
			//-------------------------------------------------------------------------------------
			//3) GSON 라이브러리를 이용한 자바객체 -> JSON 변환
			
			//new Gson().toJson(객채,응답스트립);
			//-> 매개변수 위치에 작성된 객체를 JSON 형태로 변환후 스트림을 통해서 출력한다.
			new Gson().toJson(member,resp.getWriter());
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
