package edu.kh.comm.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.comm.member.model.service.MyPageService;
import edu.kh.comm.member.model.vo.Member;
import oracle.jdbc.proxy.annotation.Post;

//member/myPage/profile
//member/myPage/info
//member/myPage/changePw
//member/myPage/secession

@Controller
@RequestMapping("/member/myPage")
@SessionAttributes({ "loginMember" }) // session scope에서 loginMember를 얻어온다.
public class MyPageController {

	@Autowired // DI 요청
	private MyPageService service;
	private Logger logger = LoggerFactory.getLogger(MyPageController.class);

	/**
	 * 마이페이지 forward
	 * 
	 * @return
	 */
	@GetMapping("/*")
	public String info(HttpServletRequest req) {

		String path = req.getRequestURI();
		int point = path.lastIndexOf("/");
		String type = path.substring(point + 1);
		return "member/myPage-" + type;
	}

	/**
	 * 내 정보 변경
	 * 
	 * @param loginMember
	 * @param paramMap
	 * @param updateAddress
	 * @param rs
	 * @return
	 */
	@PostMapping("/info")
	public String updateInfo(@ModelAttribute("loginMember") Member loginMember,
			@RequestParam Map<String, Object> paramMap //
			, String[] updateAddress, RedirectAttributes rs) {
		// 필요한 값 @ModelAttribute("loginMember")
		/*
		 * - 닉네임 - 전화번호 - 주소배열 (String.join() 사용) - Session에서 가져올 회원 번호
		 * -> @SessionAttribute , @ModelAttribute 필요
		 * 
		 * @SessionAttribute 1) session scope에 올릴때 사용 Model에 세팅된 데이터의 Key와
		 * 
		 * @SessionAttribute에 작성된 Key가 같으면 Model에 세팅된 데이터를 Request에서 Session scope로 이동
		 * 
		 * 2)기존에 session scope로 이동 시킨 값을 얻어오는 역할
		 * 
		 * @ModelAttribute("loginMember") 작성 시 [ session key 명 ] --> @SessionAttribute를
		 * 통해 Session Scope에 등록된 값을 얻어와 오른쪽에 작성된 Member loginMember변수에 대입 단 , 클래스
		 * 위에 @SessionAttribute({"loginMember"})가 작성되어 있어야 가능
		 */

		// [ 매개변수를 이용해서 Session Parameter를 동시에 얻어올 때 문제점]
		// Session에서 객체를 얻어오기 위해 매개변수를 작성한 상태에서
		// Parameter의 name 속성 값이 매개변수의에 작성된 객체의 필드와 일치하면.
		// Session에서 가져온 객체의 필드에 덮어쓰기가 되어 진다.

		// [해결 방법]
		// 세션 값을 어쩔수 없다 넘어오는 파라미터의 name 속성을 변경해서 얻어오면된다.
		// ( 필드명 곂쳐서 문제니깐 겹치지 않게 하자 )

		// Parameter를 저장한 Map(paramMap)에 회원번호 , 주소 추가하기

		paramMap.put("memberNo", loginMember.getMemberNo());

		String memberAddress = String.join(",,", updateAddress);
		if (memberAddress.equals(",,,,")) { // 주소 미입력시 그냥 null이나 대입해라
			memberAddress = null;
		}

		paramMap.put("memberAddress", memberAddress);

		// 회원 정보 수정 서비스 호출
		int result = service.upadteInfo(paramMap);
		String msg = null;

		if (result > 0) {
			msg = "회원정보가 수정되었습니다";
			loginMember.setMemberNickname((String) paramMap.get("updateNickname"));
			loginMember.setMemberTel((String) paramMap.get("updateTel"));
			loginMember.setMemberAddress(memberAddress);
		} else {
			msg = "회원정보 수정이 실패하였습니다";
		}

		rs.addFlashAttribute("message", msg);

		// /member/myPage/info "redirect:/signUp";
		return "redirect:/member/myPage/info";
	}

	/**
	 * 비밀번호 업데이트
	 * 
	 * @param loginMember
	 * @param paramMap
	 * @param rs
	 * @return
	 */
	@PostMapping("/changePw")
	public String updatePw(@ModelAttribute("loginMember") Member loginMember,
			@RequestParam Map<String, Object> paramMap, RedirectAttributes rs) {

		paramMap.put("memberNo", loginMember.getMemberNo());
		int result = service.updatePw(paramMap);
		String path = null;
		String msg = null;

		if (result > 0) {
			path = "redirect:/";
			msg = "성공적으로 변경되었습니다";
		} else {
			path = "redirect:changePw";
			msg = "현재 비밀번호가 일치하지 않습니다.";
		}
		rs.addFlashAttribute("message", msg);

		return path;
	}

	/**
	 * 회원탈퇴
	 * 
	 * @param loginMember
	 * @param rs
	 * @return
	 */
	@PostMapping("/secession")
	public String secession(@ModelAttribute("loginMember") Member loginMember, RedirectAttributes rs,
			HttpServletRequest req, HttpServletResponse resp) {
		int result = service.secession(loginMember);
		String path = null;
		String msg = null;

		if (result > 0) {
			path = "redirect:/member/logout";
			msg = "탈퇴되었습니다";
			Cookie cookie = new Cookie("saveId", null);
			cookie.setMaxAge(0);
			cookie.setPath(req.getContextPath());
			resp.addCookie(cookie);

		} else {
			path = "redirect:secession";
			msg = "비밀번호 확인후 다시 입력해주세요.";
		}
		rs.addFlashAttribute("message", msg);
		return path;
	}

	//프로필 이미지 변경 서비스
	@PostMapping("/profile")
	public String updateProfile(@ModelAttribute("loginMember") Member member,
			@RequestParam("uploadImage") MultipartFile uploadImage // 업로드된 이미지
			, @RequestParam Map<String, Object> map, HttpServletRequest req // 파일 저장 경로 탐색용
			, RedirectAttributes ra) throws IOException {

		// 경로 작성 하기
		// src/main/webapp/resources/images/memberProfile

		// 1) 웹 접근 경로 (/comm/resources/images/memberProfile
		String webPath = "/resources/images/memberProfile/";

		// 2) 서버 저장 경로 ( )
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		// map에 다 저장 웹경로 ,찐 경로 , 들어온 이미지 , 회원번호
		map.put("webPath", webPath);
		map.put("folderPath", folderPath);
		map.put("uploadImage", uploadImage);
		map.put("memberNo", member.getMemberNo());

		int result = service.updateProfile(map);

		String msg = null;
		if (result > 0) {
			msg = "프로필 이미지가 변경되었습니다";
			// DB-세션 동기화
			// 서비스 호출 시 전달된 map은 실제로는 주소만 전달(얕은복사)
			// - > 서비스에서 축된 profileImage는 원본 map에 그대로 있음
			member.setProfileImage((String) map.get("profileImage"));
		} else {
			msg = "프로필 이미지가 변경 실패.....";
		}
		ra.addFlashAttribute("message", msg);

		return "redirect:profile";

	}
}
