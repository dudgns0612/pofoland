/*
 * @(#) ControllerAspect.java 
 *
 * v1.0.0 / 2017. 7. 25. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */

package com.hst.pofoland.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import com.hst.pofoland.common.utils.LoggerManager;


/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.aspect.ControllerAspect.java
 * 클래스 설명 : 컨트롤러 IN / OUT 로깅 Aspect 클래스
 *
 * @author 이현규
 * @since 2017. 7. 25.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 7. 25.		이현규			최초생성
 * </pre>
 */
public class ControllerAspect {

	public Object aroundAop(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodNm = joinPoint.getSignature().toShortString();
		LoggerManager.info(getClass(), "{} 시작",methodNm);
		long start = System.currentTimeMillis();
		
		try {
			
			Object resultData = joinPoint.proceed();
			return resultData;
			
		} finally {
			
			long end = System.currentTimeMillis();
			LoggerManager.info(getClass(), "{} 종료", methodNm);
			LoggerManager.info(getClass(), "실행시간 : {}ms",(end-start));
			
		}
		
	}
}
