package com.hst.pofoland.biz.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hst.pofoland.biz.user.vo.UserVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.user.UserDAO.java
 * 클래스 설명 : User DB에 대한 작업접근 위한 DAO class
 *
 * @author 김영훈
 * @since 2017. 7. 27.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 7. 27.		김영훈			최초생성
 * </pre>
 */

@Repository
public interface UserDAO {
	/**
	 * 유저 회원가입
	 * @param userVO
	 * @return
	 */
	public Integer insertUser(UserVO userVO);
	
	/**
	 * 유저 로그인
	 * @param userVO
	 * @return
	 */
	public UserVO selectUserLogin(String userId);
	
	/**
	 * 유저 아이디 중복확인
	 * @param userId
	 * @return
	 */
	public String selectDuplicateCheckId(String userId);
	
	/**
	 * 유저 닉네임 중복확인
	 * @param userNick
	 * @return
	 */
	public String selectDuplicateCheckNick(String userNick);
	
	/**
	 * 유저 이메일 중복확인
	 * @param userEmail
	 * @return
	 */
	public String selectDuplicateCheckEmail(String userEmail);
	
	public String selectCheckIdEmail(UserVO userVO);
	
	/**
	 * 유저 정보조회
	 * @param userSeq
	 * @return
	 */
	public UserVO selectUserInfo(UserVO userVO);
	
	/**
	 * 유저 아이디/비밀번호 찾기 조회
	 * @param userVO
	 * @return
	 */
	public UserVO selectFindUserInfo(UserVO userVO);
	
	/**
	 * 유저 시퀀스 조회
	 * @param userId
	 * @return
	 */
	public Integer selectUserSeq(String userId);
	
	/**
	 * 유저 인증상태 변경
	 * @param userVO
	 * @return
	 */
	public Integer updateAuthState(UserVO userVO);
	
	/**
	 * 유저 로그인상태
	 * @param userSeq
	 * @return
	 */
	public Integer updateLoginState(UserVO userVO);
	
	/**
	 * 유저 인증상태 체크
	 * @param userSeq
	 * @return
	 */
	public UserVO selectAuthState(Integer userSeq);
	
	
	/**
	 * 유저 추가정보 입력(닉네임)
	 * @param userVO
	 * @return
	 */
	public Integer updateAddInfo(UserVO userVO);
	
	/**
	 * InterestCode 추가
	 * @param userVO
	 * @return
	 */
	public Integer insertInterestInfo(UserVO userVO);
	
	/**
	 * OAuth 유저 회원가입
	 * @param userVO
	 * @return
	 */
	public Integer insertOauthUser(UserVO userVO);
	
	/**
	 * 유저 정보 수정
	 * @param userVO
	 * @return
	 */
	public Integer updateModifyUser(UserVO userVO);
	
	/**
	 * 유저 회원 탈퇴
	 * @param userVO
	 * @return
	 */
	public Integer updateDropUser(UserVO userVO);
	
	/**
	 * 유저 프로필 파일명 조회
	 * @return
	 */
	public List<String> selectAllUserProfile();
	
	/**
	 * 유저 비밀번호 변경
	 * @param newPassword
	 * @return
	 */
	public Integer updatePasswordUser(UserVO userVO);
}
