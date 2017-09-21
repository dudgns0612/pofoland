package com.hst.pofoland.biz.user.service.impl;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.user.dao.UserDAO;
import com.hst.pofoland.biz.user.service.UserService;
import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.auth.MailAuthentication;
import com.hst.pofoland.common.auth.security.SecurityAuthorityManager;
import com.hst.pofoland.common.utils.StringUtils;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.user.UserService.java
 * 클래스 설명 : User에 관한 Service 역활 class
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
 * 2017. 7. 31.     김영훈			메일인증추가
 * </pre>
 */

@Service
public class UserServiceImpl implements UserService , UserDetailsService{
	
	@Inject
	UserDAO userDAO;
	
	public UserServiceImpl() {
	}
	
	/**
	 * 유저 회원가입 서비스
	 * @param userVO
	 * @return
	 */
	@Override
	public UserVO createUser(UserVO userVO) {
		
		userVO.setUserAuthKey(getAuthKey());
		
		StandardPasswordEncoder spEncoder = new StandardPasswordEncoder();
		String userPw = spEncoder.encode(userVO.getPassword());
		userVO.setUserPw(userPw);
		
		Integer result = userDAO.insertUser(userVO);
		
		if (result > 0) {
			Integer userSeq = userDAO.selectUserSeq(userVO.getUserId());
			
			//등록된 유저번호 입력
			userVO.setUserSeq(userSeq);
			
			String userEmail = userVO.getUserEmail();
			String userAuthKey = userVO.getUserAuthKey();
			
			//인증메일 전송
			MailAuthentication mailAuth = new MailAuthentication(userEmail, userAuthKey, userSeq);
			mailAuth.sendAuthMail();
		}
		
		return userVO;
	}
	
	
	
	/**
	 * 유저 아이디 중복확인
	 * @param userId
	 * @return
	 */
	@Override
	public String duplicateCheckId(String userId) {
		
		String checkId = userDAO.selectDuplicateCheckId(userId);
		
		return checkId;
	}
	
	/**
	 * 유저 시퀀스 조회
	 */
	@Override
	public Integer seqSearchUser(String userId) {
		
		Integer userSeq = userDAO.selectUserSeq(userId);
		
		return userSeq;
	}
	
	/**
	 * 유저 닉네임 중복확인
	 * @param userNick
	 * @return
	 */
	@Override
	public String duplicateCheckNick(String userNick) {
		
		String checkNick = userDAO.selectDuplicateCheckNick(userNick);
		
		return checkNick;
	}

	/**
	 * 유저 이메일 중복확인
	 * @param userEmail
	 * @return
	 */
	@Override
	public String duplicateCheckEmail(String userEmail) {
		
		String checkEmail = userDAO.selectDuplicateCheckEmail(userEmail);
		
		return checkEmail;
	}
	
	/**
	 * 유저 정보조회
	 * @param userSeq
	 * @return
	 */
	@Override
	public UserVO searchUser(Integer userSeq) {
		
		UserVO userVO = userDAO.selectUserInfo(userSeq);
		
		return userVO;
	}
	
	/**
	 * 유저 허가인증
	 */
	@Override
	public Integer authProcessUser(UserVO userVO) {
		
		Integer result = userDAO.updateAuthState(userVO);
		
		return result;
	}
	
	/**
	 *  유저메일인증 체크
	 */
	@Override
	public UserVO authCheckUser(Integer userSeq) {
		
		UserVO userVO = userDAO.selectAuthState(userSeq);
		
		return userVO;
	}
	
	/**
	 * 유저 추가정보 등록
	 */
	@Override
	public Integer addInfoUser(UserVO userVO) {
		
		Integer nickResult = userDAO.updateAddInfo(userVO);
		if(userVO.getJobCate().length > 0) {
			userDAO.insertJobCate(userVO);
		}
		
		return nickResult;
	}
	
	/**
	 * userAuthKey 발급
	 * @return
	 */
	public String getAuthKey() {
		return StringUtils.random();
	}
	
	/**
	 * 구글 사용자 회원가입
	 */
	@Override
	public UserVO createOauthUser(UserVO userVO) {
		
		Integer result = userDAO.insertOauthUser(userVO);
		
		if (result > 0) {
			Integer userSeq = userVO.getUserSeq();
			if(userVO.getJobCate().length > 0 && userSeq > 0) {
				userDAO.insertJobCate(userVO);
			}
		}
		
		return userVO;
	}
	
	/**
	 * 유저 정보 수정
	 */
	@Override
	public Integer modifyUser(UserVO userVO) {
		return userDAO.updateModifyUser(userVO);
	}
	
	@Override
	public Integer dropUser(UserVO userVO) {
		return userDAO.updateDropUser(userVO);
	}
	
	@Override
	public Integer loginStateUser(UserVO userVO) {
		return userDAO.updateLoginState(userVO);
	}
	
	@Override
	public Integer modifyUserPssword(UserVO userVO) {
		return userDAO.updatePasswordUser(userVO);
	}
	
	/**
	 * Security 인증 확인
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		UserVO userVO = userDAO.selectUserLogin(userId);

		if (userVO == null) {
			throw new UsernameNotFoundException("userId");
		}
		
		userVO.setUserId(userId);
		userVO.setUserPw(userVO.getPassword());
		
		//User 권한부여
		SecurityAuthorityManager authManager = new SecurityAuthorityManager();
		authManager.setAuthorityList("ROLE_USER");
		userVO.setAuthorities(authManager.getAuthorityList());
		
		return userVO;
	}
}
