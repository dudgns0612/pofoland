package com.hst.pofoland.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hst.pofoland.common.utils.LoggerManager;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.listener.ContextLoadListener.java
 * 클래스 설명 : 웹서버 시작 / 중지에 따른 설정을 위한 리스너
 *
 * @author 김영훈
 * @since 2017. 10. 18.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 10. 18.	김영훈			최초생성
 * </pre>
*/

public class ContextLoadListener implements ServletContextListener{
	
	/**
	 * 웹 컨테이너 시작
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//TODO NETTY 이용한 LOGVIEWER 서버시작 개발
		LoggerManager.info(getClass(), "TCP LOGVIEWER START");
	}
	
	/**
	 * 웹 컨테이너 종료
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
