package com.hst.pofoland.common.auth.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.utils.LoggerManager;

public class SecurityLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		
		UserVO userVO = (UserVO) auth.getPrincipal();
		List<SecurityRole> roleList = (List<SecurityRole>) auth.getAuthorities();
		
		StringBuffer sb = new StringBuffer("\n");
		
		sb.append("=================================================================================================\n");
		sb.append("# Login Success UserInfo \n");
		sb.append("		UserSeq = " + userVO.getUserSeq() + "\n");
		sb.append("		UserId = " + userVO.getUserId() + "\n");
		sb.append("		UserNickname = " + userVO.getUserNick() + "\n");
		sb.append("		UserEmail = " + userVO.getUserEmail() + "\n");
		sb.append("		UserRegDate = " + userVO.getUserRegDt() + "\n");
		sb.append("		UserAuthYn = " + userVO.getUserAuthYn() + "\n");
		sb.append("\n");
		sb.append("# User Authority \n");
		for(SecurityRole role : roleList) {
			sb.append("	*	" + role.getAuthority() + "\n");
		}
		sb.append("=================================================================================================\n");
		
		LoggerManager.info(getClass(), sb.toString());
	}

}
