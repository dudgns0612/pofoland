package com.hst.pofoland.biz.user.service;

import com.hst.pofoland.biz.user.vo.UserVO;

public interface OAuthApiService {
	
	public UserVO getNaverUserInfo(String accessToken);
	
	public String refreshNaverUserToken(String refreshToken);
	
	public void deleteNaverUserToken(String refreshToken);
}
