package com.hst.pofoland.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.constnat.NetworkConstant;
import com.hst.pofoland.common.utils.LoggerManager;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.interceptor.AuthInspectionInterceptor.java
 * 클래스 설명 : User 로그인과 인증여부 검사 인터셉터
 *
 * @author 김영훈
 * @since 2017. 8. 1.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 8. 1.		김영훈			최초생성
 * </pre>
*/


public class AuthInspectionInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 *  컨트롤러 메소드 실행 직후
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	/**
	 * 컨트롤러 메소드 실행 전 사용자 세션정보와 인증여부 검사
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		StringBuffer sb = new StringBuffer();
		UserVO userVO = (UserVO) session.getAttribute("user");
		
		LoggerManager.info(getClass(), "TEST {}", userVO == null);
        LoggerManager.info(getClass(), "Request URI : {}", request.getRequestURI());
        
		if (userVO == null) {
			sb.append("<script language='javascript'>");
			sb.append("		alert('로그인이 필요합니다.');");
			sb.append("		document.location.href='/';");
			sb.append("</script>");
			
			NetworkConstant.printWrite(pw, sb.toString());
			
			return false;
		} else {
			char authYn = userVO.getUserAuthYn();
			
			if (authYn == 'N') {
				sb.append("<script language='javascript'>");
				sb.append("		alert('인증이 메일을 확인하여 인증을 처리해주세요.^^');");
				sb.append("		document.location.href='/home';");
				sb.append("</script>");
				
				NetworkConstant.printWrite(pw, sb.toString());
				return false;
			}
		}
		return true;
	}
}
