package com.hst.pofoland.common.auth.security;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hst.pofoland.biz.user.dao.UserDAO;
import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.utils.LoggerManager;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.auth.security.SecurityLoginSuccessHandler.java
 * 클래스 설명 : User Login 성공에 대한 처리
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

public class SecurityLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	@Inject
	UserDAO userDao;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		
		UserVO userVO = (UserVO) auth.getPrincipal();
		@SuppressWarnings("unchecked")
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
		sb.append("		UserPublicYn = " + userVO.getUserPublicYn() + "\n");
		sb.append("		UserProfileName = " + userVO.getUserProfileUrl() + "\n");
		sb.append("\n");
		sb.append("# User Authority \n");
		for(SecurityRole role : roleList) {
			sb.append("	*	" + role.getAuthority() + "\n");
		}
		sb.append("=================================================================================================\n");
		
		LoggerManager.info(getClass(), sb.toString());
		
		//유저 로그인 상태 변경
		if (userDao.updateLoginState(userVO) > 0) {
			userVO.setUserLoginYn('Y');
		}
		// Session Process
		HttpSession session = request.getSession();
		session.setAttribute("user", userVO);

		response.sendRedirect("/home");
	}

}
