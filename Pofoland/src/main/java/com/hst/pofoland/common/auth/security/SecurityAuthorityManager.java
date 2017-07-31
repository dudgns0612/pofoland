package com.hst.pofoland.common.auth.security;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.auth.security.SecurityAuthorityManager.java
 * 클래스 설명 : security 권한에 대한 리스트관리
 *
 * @author 김영훈
 * @since 2017. 7. 31.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 7. 31.		김영훈			최초생성
 * </pre>
*/

public class SecurityAuthorityManager {
	
	private List<SecurityRole> authList = new ArrayList<SecurityRole>();

	public void setAuthorityList(String... authority) {
		for (String auth : authority) {
			authList.add(new SecurityRole(auth));
		}
	}
	
	public List<SecurityRole> getAuthorityList() {
		return authList;
	}
}
