package com.hst.pofoland.common.auth.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.utils.LoggerManager;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.auth.security.SecurityLoginFailHandler.java
 * 클래스 설명 : User Login 실패에 대한 처리
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

public class SecurityLoginFailHandler implements AuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		UserVO userVO = (UserVO) authException.getExtraInformation();
		
		StringBuffer sb = new StringBuffer("\n");
		sb.append("=================================================================================================\n");
		sb.append("# Login Fail UserInfo \n");
		sb.append("		UserId = " + userVO.getUserId() + "\n");
		sb.append("# Login Fail Message \n");
		sb.append("		Error Message = " + authException.getMessage() + "\n");
		sb.append("=================================================================================================\n");
		
		LoggerManager.info(getClass(), sb.toString());
	}

}