package com.hst.pofoland.common.auth.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.common.auth.security.Role.java
 * 클래스 설명 : 권한에 대하여 정의하는 class
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
 * 2017. 7. 31.		김영훈			최초생성
 * </pre>
 */

public class SecurityRole implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;

	private String authority;
	
	/**
	 *  생성자
	 */
	public SecurityRole() {
	}
	
	public SecurityRole(String authority) {
		this.authority = authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
	    return this.authority;
	}
}
