package com.hst.pofoland.biz.user.service;

import com.hst.pofoland.biz.user.vo.UserVO;

public interface UserService {
	
	public int createUser(UserVO userVO);
	
	public String duplicateCheckId(String userId);
	
	public String duplicateCheckNick(String userNick);
	
	public UserVO searchUser(String userSeq);
	
}
