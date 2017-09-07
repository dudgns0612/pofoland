package com.hst.pofoland.biz.user.service;

import com.hst.pofoland.biz.user.vo.UserVO;

public interface UserService {
	
	public UserVO createUser(UserVO userVO);
	
	public String duplicateCheckId(String userId);
	
	public String duplicateCheckNick(String userNick);
	
	public String duplicateCheckEmail(String userEmail);
	
	public UserVO searchUser(Integer userSeq);
	
	public Integer authProcessUser(UserVO userVO);
	
	public UserVO authCheckUser(Integer userSeq);
	
	public Integer seqSearchUser(String userId);
	
	public boolean addInfoUser(UserVO userVO);
}
