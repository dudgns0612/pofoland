/*
 * @(#) StringUtils.java
 *
 * v1.0.0 / 2017. 8. 6. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 시스템명 : 
 * $com.hst.pofoland.common.utils.StringUtils.java
 * 클래스 설명 : 
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
public class StringUtils {

    /**
     * @return
     */
    public static String random() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    /**
     * 문자열 null 또는 empty 체크
     * @param src
     * @return
     */
    public static boolean isEmpty(String src) {
        return src == null || src.length() == 0;
    }
    
    /**
     * 문자열 null처리 함수, defaultValue를  ""로 지정
     * 
     * @param src
     * @param defaultValue
     * @return
     */
    public static String nvl(String src) {
        return nvl(src, "");
    }
    
    /**
     * 문자열 null처리 함수
     * 
     * @param src
     * @param defaultValue
     * @return
     */
    public static String nvl(String src, String defaultValue) {
        return isEmpty(src) ? defaultValue : src;
    }
    
    /**
     * HTML 문자열 처리 메소드 집합 클래스
     * 
     * @author 이현규
     * @version 1.0
     * @since 2017. 9. 3. 
     */
    public static class HTMLProcessing {

        public static String text(String content) {
            return nvl(content).replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        }
        
        public static String[] findImgTag(String content) {
            //Matcher matcher = getMatcher("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>", content);
            Matcher matcher = getMatcher("src=\"(.*?)\"", content);
            
            List<String> finded = new ArrayList<String>();
            
            while (matcher.find()) {
                finded.add(matcher.group());
            }
            
            return finded.toArray(new String[finded.size()]);
        }
        
        private static Matcher getMatcher(String regExp, String content) {
            return Pattern.compile(regExp).matcher(content);
        }
        
    }
    
}
