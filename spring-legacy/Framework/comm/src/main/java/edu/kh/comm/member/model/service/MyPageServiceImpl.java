package edu.kh.comm.member.model.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.comm.common.Util;
import edu.kh.comm.member.model.dao.MyPageDAO;
import edu.kh.comm.member.model.vo.Member;

@Service
public class MyPageServiceImpl implements MyPageService {

	Logger logger = LoggerFactory.getLogger(MyPageServiceImpl.class);
	@Autowired
	private MyPageDAO dao;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	/**
	 * 내정보 수정
	 *
	 */
	@Override
	public int upadteInfo(Map<String, Object> paramMap) {
		return dao.updateInfo(paramMap);
	}

	/**
	 * 비밀번호 수정
	 *
	 */
	@Override
	public int updatePw(Map<String, Object> paramMap) {
		int result = 0;

		// 비밀번호 변경 서비스 호출

		// 1) DB에서 현재 회원의 비밀번호를 조회
		String memberPw = dao.selectPw((int) paramMap.get("memberNo"));

		// 2) 입력된 현재 비밀번호와 조회된 비밀번호 비교 (bcrypt.matches() 이용 )
		if (bcrypt.matches((String) paramMap.get("currentPw"), memberPw)) {
			logger.info("비밀번호 일치");
			// 3) 비교 결과가 일치하면 새로 입력된 비밀번호를 암호화 해서 db Upadte수행
			paramMap.put("newPw", bcrypt.encode((String) paramMap.get("newPw")));
			result = dao.updatePw(paramMap);
		} else {
			;
			logger.info("비밀번호 불일치 이벤트 발생");
			result = 0;
		}

		return result;
	}

	/**
	 * 탈퇴신청
	 *
	 */
	@Override
	public int secession(Member loginMember) {

		String memberPw = dao.selectPw(loginMember.getMemberNo());
		int result = 0;

		if (bcrypt.matches(loginMember.getMemberPw(), memberPw)) {
			logger.info("비밀번호 일치 성공 이벤트 발생");
			result = dao.secession(loginMember.getMemberNo());
		} else {
			;
			logger.info("비밀번호 불일치 이벤트 발생");
			result = 0;
		}

		return result;
	}

	// 프로필 이미지 업로드 서비스 구현
	@Override
	public int updateProfile(Map<String, Object> map) throws IllegalStateException, IOException {
		// 현재 map에 들어있는 거
		// webPath , folderPath , uploadImage , delete ,emberNo

		MultipartFile file = (MultipartFile) map.get("uploadImage");
		String delete = (String) map.get("delete");

		// 프로필 이미지 삭제 여부를 확인해서
		// 삭제가 아닌 경우(==새로운 이미지로 변경된 경우) -> 업로드된 파일명은 Rename 수행
		// 삭제된 경우 Null값을 준비한다.(DB에 업데이트)

		// 변경된 파일명 저장
		String renameImage = null;

		// 0이 변경 1이 삭제
		if (delete.equals("0")) {
			// 파일명 변경
			// 로직을 만들어야 됨
			renameImage = Util.fileRename(file.getOriginalFilename());
			map.put("profileImage", map.get("webPath") + renameImage);
			// resources/images/memberProfile/ 변경된 이름
		} else {
			map.put("profileImage", null); // 삭제인 경우 null을 넣어줘야함
		}

		// DAO 호출해서 프로필 이미지 UPDATE 수행
		int result = dao.updateProfile(map);

		// DB수정 성공시 메모리에 임시 저장되어있는 이미지를 서버에 저장
		if (result > 0 && map.get("profileImage")!= null) {
			logger.info("이미지 저장됨.");
			logger.debug(map.get("folderPath") + renameImage);

			file.transferTo(new File(map.get("folderPath") + renameImage));
			// transferTo : 해당 파일을 지정된 경로 + 이름으로 저장

		}

		return result;
	}

}
