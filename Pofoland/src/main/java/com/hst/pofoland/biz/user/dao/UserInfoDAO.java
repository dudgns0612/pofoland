package com.hst.pofoland.biz.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hst.pofoland.biz.user.vo.UserVO;

@Repository
public interface UserInfoDAO {
	
	public List<UserVO> selectUserInfo(UserVO userVO);
	
	public Integer selectUserTotal(UserVO userVO);
	
	public List<UserVO> selectHomeUserInfo();
}
