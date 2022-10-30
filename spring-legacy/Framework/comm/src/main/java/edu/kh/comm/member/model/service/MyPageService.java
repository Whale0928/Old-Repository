package edu.kh.comm.member.model.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.vo.Member;


public interface MyPageService {

	/**회원정보 수정 서비스
	 * @param paramMap
	 * @return
	 */
	int upadteInfo(Map<String, Object> paramMap);

	/**비밀번호 변경 서비스
	 * @param paramMap
	 * @return
	 */
	int updatePw(Map<String, Object> paramMap);

	/**회원탈퇴 서비스
	 * @param loginMember
	 * @return
	 */
	int secession(Member loginMember);

	/**프로필 이미지 등록
	 * @param map
	 * @return
	 */
	int updateProfile(Map<String, Object> map) throws IOException;

}
