/*
 * @(#) EchoOff.java
 *
 * v1.0.0 / 2017. 8. 6. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.annotation.EchoOff.java
 * 클래스 설명 : 컨트롤러 Aspect 로그를 하지 않을 때 사용하는 어노테이션
 *
 * @author 이현규
 * @since 2017. 8. 6.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 8. 6.   이현규  최초생성
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EchoOff {

}
