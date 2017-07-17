package com.hst.pofoland.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @version 1.0
 * @created 2017.07.17
 * @updated 
 * @author YH 
 */
public class PropertyManager {
	
	static String PROPERY_ROOT_PATH = "properties/";
	
	InputStream inputsteam = null;
	
	Properties prop = null;
	
	/**
	 * 생성자
	 * @param fileName
	 */
	public PropertyManager(String fileName) {
		prop = new Properties();
		
		inputsteam = getClass().getClassLoader().getResourceAsStream(PROPERY_ROOT_PATH+fileName);
		
		try {
			prop.load(inputsteam);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 설정한 프로퍼티 값 전달
	 * @return
	 */
	public Properties getProperties() {
		return prop;
	}
	
}
