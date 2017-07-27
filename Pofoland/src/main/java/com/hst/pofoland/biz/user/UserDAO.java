package com.hst.pofoland.biz.user;

import org.springframework.stereotype.Repository;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.user.UserDAO.java
 * 클래스 설명 : User DB에 대한 작업 연결을 위한 DAO class
 *
 * @author 김영훈
 * @since 2017. 7. 27.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 7. 27.		김영훈			최초생성
 * </pre>
 */

@Repository
public interface UserDAO {
	
	public int insertUser(UserVO userVO);
	
}
