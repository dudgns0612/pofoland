package com.hst.pofoland.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @version 1.0
 * @created 2017.07.17
 * @updated 
 * @author YH 
 */
public class PropertyManager {
	
	private static final String PROPERY_ROOT_PATH = "properties/";
	
	private List<String> locations;
	
//	InputStream inputsteam = null;
//	
	private Properties properties;
	
//	/**
//	 * 생성자
//	 * @param fileName
//	 */
//	public PropertyManager(String fileName) {
//		prop = new Properties();
//		
//		inputsteam = getClass().getClassLoader().getResourceAsStream(PROPERY_ROOT_PATH+fileName);
//		
//		try {
//			prop.load(inputsteam);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public PropertyManager(List<String> locations) {
	    this.locations = locations;
	    
	    properties = new Properties();
	    
	    Properties property = new Properties();
	    InputStream in =null;
	    
	    try {
	        for(String location : locations) {
	            in = getClass().getClassLoader().getResourceAsStream(PROPERY_ROOT_PATH + location);
	            
	            property.load(in);
	            properties.putAll(property);
	            property.clear();
	        }
	    } catch(IOException e) {
	        e.printStackTrace();
	    }
	    
	    String osName = System.getProperty("os.name").toLowerCase();
	    
	    if(osName.indexOf("win") >= 0) {
	        properties.put("image.path", properties.get("dev.image.path"));
	    } else if(osName.indexOf("linux") >= 0) {
	        properties.put("image.path", properties.get("operate.image.path"));
	    }
	}
	
	/**
	 * 설정한 프로퍼티 값 전달
	 * @return
	 */
	public Properties getProperties() {
		return properties;
	}
	
	
	/**
	 * 특정 프로퍼티 value 반환
	 * 
	 * @param key 프로퍼티 키
	 * @return 프로퍼티 값
	 */
	public String getProperty(String key) {
	    return properties.getProperty(key);
	}
	
}
