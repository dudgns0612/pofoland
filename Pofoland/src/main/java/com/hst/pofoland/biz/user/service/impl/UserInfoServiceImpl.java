package com.hst.pofoland.biz.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.user.dao.UserInfoDAO;
import com.hst.pofoland.biz.user.service.UserInfoService;
import com.hst.pofoland.biz.user.vo.UserVO;

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
