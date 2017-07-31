package com.hst.pofoland.biz.user.service.impl;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.user.dao.UserDAO;
import com.hst.pofoland.biz.user.service.UserService;
import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.auth.security.SecurityAuthorityManager;

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
 * </pre>
 */

@Service
public class UserServiceImpl implements UserService , UserDetailsService{
	
	@Inject
	UserDAO userDAO;
	
	StandardPasswordEncoder spEncoder = new StandardPasswordEncoder();
	
	/**
	 * 유저 회원가입 서비스
	 * @param userVO
	 * @return
	 */
	@Override
	public int createUser(UserVO userVO) {
		
		
		userVO.setUserAuthKey(getAuthKey());
		
		String userPw = spEncoder.encode(userVO.getPassword());
		
		userVO.setUserPw(userPw);
		
		int result = userDAO.insertUser(userVO);
		
		return result;
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
	 * 유저 정보조회
	 * @param userSeq
	 * @return
	 */
	@Override
	public UserVO searchUser(String userSeq) {
		
		UserVO userVO = userDAO.selectUserInfo(userSeq);
		
		return userVO;
	}
	
	/**
	 * userAuthKey 발급
	 * @return
	 */
	public String getAuthKey() {
		
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		
		UserVO userVO = userDAO.selectUserLogin(userId);
		
		userVO.setUserId(userId);
		userVO.setUserPw(userVO.getPassword());
		
		//User 권한부여
		SecurityAuthorityManager authManager = new SecurityAuthorityManager();
		authManager.setAuthorityList("ROLE_USER");
		userVO.setAuthorities(authManager.getAuthorityList());
		
		return userVO;
	}
	
}
