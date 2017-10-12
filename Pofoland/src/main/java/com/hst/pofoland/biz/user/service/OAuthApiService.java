package com.hst.pofoland.biz.user.service;

public interface OAuthApiService {
	
	public String getNaverUserInfo(String accessToken);
	
	public String refreshNaverUserToken(String refreshToken);
	
	public void deleteNaverUserToken(String refreshToken);
}
