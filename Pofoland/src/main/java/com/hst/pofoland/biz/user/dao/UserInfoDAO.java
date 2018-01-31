package com.hst.pofoland.biz.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hst.pofoland.biz.user.vo.UserVO;

/**
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.user.UserInfoDAO.java
 * 클래스 설명 : UserInfo DB에 대한 작업접근 위한 DAO class
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

@Repository
public interface UserInfoDAO {
	
	public List<UserVO> selectUserInfo(UserVO userVO);
	
	public Integer selectUserTotal(UserVO userVO);
	
	public List<UserVO> selectHomeUserInfo();
}
