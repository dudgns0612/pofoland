package com.hst.pofoland.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0
 * @created 2017.07.17
 * @updated 
 * @author YH
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
	public static void info(Class<?> clazz, String msg, Object[] arrObj) {
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
	public static void debug(Class<?> clazz, String msg, Object[] arrObj) {
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
	public static void error(Class<?> clazz, String msg, Object[] arrObj) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.error(msg , arrObj);
	}
}
