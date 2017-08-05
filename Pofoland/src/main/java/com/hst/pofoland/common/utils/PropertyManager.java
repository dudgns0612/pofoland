/*
 * @(#) PropertyManager.java 
 *
 * v1.0.0 / 2017. 7. 25. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */

package com.hst.pofoland.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.utils.PropertyManager.java
 * 클래스 설명 : 시스템 property 관리 클래스
 *
 * @author 이현규
 * @since 2017. 7. 25.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 7. 25.     이현규         최초생성
 * </pre>
 */
public class PropertyManager {
    
    private static final String PROPERY_ROOT_PATH = "properties/";
    
    private Properties properties;
    
    public PropertyManager(List<String> locations) {
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
            properties.put("file.root", properties.get("dev.file.root"));
        } else if(osName.indexOf("linux") >= 0) {
            properties.put("file.root", properties.get("operate.file.root"));
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