package com.hst.pofoland.biz.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.user.dao.UserInfoDAO;
import com.hst.pofoland.biz.user.service.UserInfoService;
import com.hst.pofoland.biz.user.vo.UserVO;
/**
 * 시스템명 : 포트폴리오 관리 시스템
* $com.hst.pofoland.biz.user.UserInfoService.java
 * 클래스 설명 : UserInfo에 관한 Service 역활 class
 *
 * @author 김영훈
 * @since 2017. 9. 25.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 9. 25.		김영훈			최초생성
 * </pre>
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Inject
	UserInfoDAO userInfoDAO;
	
	/**
	 * 생성자
	 */
	public UserInfoServiceImpl() {
	}
	
	
	@Override
	public List<UserVO> getUserInfo(UserVO userVO) {
		return userInfoDAO.selectUserInfo(userVO);
	}

	@Override
	public Integer getUseInfoTotal(UserVO userVO) {
		return userInfoDAO.selectUserTotal(userVO);
	}
	
}
