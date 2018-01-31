package com.hst.pofoland.biz.user.service;

import java.util.List;

import com.hst.pofoland.biz.user.vo.UserVO;

public interface UserInfoService {
	
	public List<UserVO> getUserInfo(UserVO userVO);
	
	public Integer getUseInfoTotal(UserVO userVO);
}
