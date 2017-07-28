package com.hst.pofoland.common.auth.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hst.pofoland.biz.user.vo.UserVO;

public class UserDetail implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		System.out.println("dwdww");
		
		UserVO userVO = new UserVO();
		
		userVO.setUserId(userId);
		userVO.setUserPw("1234");
		
		return userVO;
	}

}
