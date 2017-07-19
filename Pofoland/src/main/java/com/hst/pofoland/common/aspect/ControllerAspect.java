package com.hst.pofoland.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import com.hst.pofoland.common.utils.LoggerManager;

/**
 * @version 1.0
 * @created 2017.07.17
 * @updated 
 * @author YH
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
