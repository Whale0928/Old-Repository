package edu.kh.comm.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //영속성 객체 Bean 등록 == DAO
public class MyPageDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;


    //파라미터가 Map일때 그냥 KEY값만 쓰면된다.

    /**
     * 회원정보 수정 DAO
     *
     * @param paramMap
     * @return
     */
    public int updateInfo(Map<String, Object> paramMap) {
        return sqlSession.update("myPageMapper.updateInfo", paramMap);
    }

    /**
     * 현재 비밀번호 조회
     *
     * @param memberNo
     * @return
     */
    public String selectPw(int memberNo) {
        return sqlSession.selectOne("myPageMapper.selectPw", memberNo);
    }

    /**
     * 비밀번호 변경
     *
     * @param paramMap
     * @return
     */
    public int updatePw(Map<String, Object> paramMap) {
        return sqlSession.update("myPageMapper.updatePw", paramMap);
    }

    /**
     * 회원탈퇴
     *
     * @param memberNo
     * @return
     */
    public int secession(int memberNo) {
        return sqlSession.update("myPageMapper.secession", memberNo);
    }

    /**
     * 프로필이미지 수정
     *
     * @param map
     * @return result
     */
    public int updateProfile(Map<String, Object> map) {
        return sqlSession.update("myPageMapper.updateProfile", map);
    }

}
