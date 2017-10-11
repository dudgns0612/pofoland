package com.hst.pofoland.common.auth;

import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.auth.MailAuthentication.java
 * 클래스 설명 : User 구글로그인 처리에 대한 빈 설정 class 
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
 * 2017. 8. 2.		김영훈			최초생성
 * </pre>
 */

public class GoogleAuthentication {
	
	GoogleConnectionFactory googleConnectionFactory;
	
	OAuth2Parameters oauth2Parameter;

	/**
	 * @return the googleConnectionFactory
	 */
	public GoogleConnectionFactory getGoogleConnectionFactory() {
		return googleConnectionFactory;
	}

	/**
	 * @param googleConnectionFactory the googleConnectionFactory to set
	 */
	public void setGoogleConnectionFactory(GoogleConnectionFactory googleConnectionFactory) {
		this.googleConnectionFactory = googleConnectionFactory;
	}

	/**
	 * @return the oauth2Parameter
	 */
	public OAuth2Parameters getOauth2Parameter() {
		return oauth2Parameter;
	}

	/**
	 * @param oauth2Parameter the oauth2Parameter to set
	 */
	public void setOauth2Parameter(OAuth2Parameters oauth2Parameter) {
		this.oauth2Parameter = oauth2Parameter;
	}
}
