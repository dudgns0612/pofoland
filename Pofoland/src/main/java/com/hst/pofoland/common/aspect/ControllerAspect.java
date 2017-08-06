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
        
        long start = System.currentTimeMillis();
		Object resultData = joinPoint.proceed();
        long end = System.currentTimeMillis();

        StringBuffer sb = new StringBuffer("\n");
        sb.append("=================================================================================================\n");
        sb.append("# Invocation \n  ").append(joinPoint.getSignature().toString()).append("\n\n");
        
        Object[] args = joinPoint.getArgs();
        int len = args.length;
        
        sb.append("# Arguments\n");
        if (len == 0) {
            sb.append("  The method takes no arguments.\n");
        } else {
            Object temp = null;
            for (int i = 0; i < len; i++) {
                temp = args[i];
                sb.append("  args[").append(i).append("] : ").append(temp.toString()).append(" [").append(temp.getClass().getCanonicalName()).append("]").append("\n");
            }
        }

		sb.append("\n# Returns\n  ").append(resultData == null ? " - " : resultData.toString()).append(" [").append(resultData == null ? "-" : resultData.getClass().getCanonicalName()).append("]").append("\n");

		sb.append("                                                                         Elapsed time : ").append(end - start).append("(ms)\n");        
        sb.append("=================================================================================================\n");

        
        LoggerManager.info(getClass(), sb.toString());
        
		return resultData;
		
	}
	
}
