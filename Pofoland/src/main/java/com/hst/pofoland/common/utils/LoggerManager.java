/*
 * @(#) LoggerManager.java 
 *
 * v1.0.0 / 2017. 7. 25. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */

package com.hst.pofoland.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 시스템명 : 포트폴리오 관리 클래스
 * $com.hst.pofoland.common.utils.LoggerManager.java
 * 클래스 설명 : Logger 관리 클래스
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
public class LoggerManager {
    
    /**
     * 생성자
     */
    public LoggerManager() {
    }
    
    /**
     * INFOLevel
     * logger사용 class와 message
     * @param clazz
     * @param msg
     */
    public static void info(Class<?> clazz, String msg) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(msg);
    }
    
    /**
     * {} 포함한 String msg
     * {} 안에  Object obj
     * @param clazz
     * @param msg
     * @param obj
     */
    public static void info(Class<?> clazz, String msg, Object obj) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(msg , obj);
    }
    
    /**
     * {} 포함한 String msg
     * {} 안에  Object[] arrObj
     * @param clazz
     * @param msg
     * @param arrObj
     */
    public static void info(Class<?> clazz, String msg, Object... arrObj) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info(msg , arrObj);
    }
    
    /**
     * DEBUGLevel
     * logger사용 class와 message
     * @param clazz
     * @param msg
     */
    public static void debug(Class<?> clazz, String msg) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.debug(msg);
    }
    
    /**
     * {} 포함한 String msg
     * {} 안에  Object obj
     * @param clazz
     * @param msg
     * @param obj
     */
    public static void debug(Class<?> clazz, String msg, Object obj) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.debug(msg , obj);
    }
    
    /**
     * {} 포함한 String msg
     * {} 안에  Object[] arrObj
     * @param clazz
     * @param msg
     * @param arrObj
     */
    public static void debug(Class<?> clazz, String msg, Object... arrObj) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.debug(msg , arrObj);
    }
    
    /**
     * ERRORLevel
     * logger사용 class와 message
     * @param clazz
     * @param msg
     */
    public static void error(Class<?> clazz, String msg) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(msg);
    }
    
    /**
     * {} 포함한 String msg
     * {} 안에  Object obj
     * @param clazz
     * @param msg
     * @param obj
     */
    public static void error(Class<?> clazz, String msg, Object obj) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(msg , obj);
    }
    
    /**
     * {} 포함한 String msg
     * {} 안에  Object[] arrObj
     * @param clazz
     * @param msg
     * @param arrObj
     */
    public static void error(Class<?> clazz, String msg, Object... arrObj) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(msg , arrObj);
    }
}

