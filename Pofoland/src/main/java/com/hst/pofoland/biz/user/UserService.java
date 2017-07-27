package com.hst.pofoland.biz.user;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

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
public class UserService {
	
	@Inject
	UserDAO userDAO;
	
	/**
	 * 유저 회원가입 서비스
	 * @param userVO
	 * @return
	 */
	public int crateUser(UserVO userVO) {
		
		userVO.setUserAuthKey(getAuthKey());
		
		int result = userDAO.insertUser(userVO);
		
		return result;
	}
	
	/**
	 * userAuthKey 발급
	 * @return
	 */
	public String getAuthKey() {
		
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}

}
