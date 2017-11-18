/*
 * @(#) AbstractController.java
 *
 * v1.0.0 / 2017. 10. 8.
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hst.pofoland.biz.code.service.CodeService;
import com.hst.pofoland.biz.user.vo.UserVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * com.hst.pofoland.common.ctrl.AbstractController.java
 * 클래스 설명 : Controller 클래스 Format
 *
 * @author 이현규
 * @since 2017. 10. 8.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 10. 8.   이현규          최초생성
 * </pre>
 */
public class BaseController {

    @Inject
    protected CodeService codeService;
    
    /**
     * 현재 세션 반환
     * 
     * @return
     */
    protected HttpSession currentSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession();
    }
    
    /**
     * 현재 로그인 사용자 시퀀스 반환
     * 
     * @return
     */
    protected Integer getSessionUserSeq() {
        UserVO loginUser = (UserVO) currentSession().getAttribute("user");
       
       if(loginUser != null) {
           return loginUser.getUserSeq();
       }
       
       return null;
    }
    
    /**
     * viewName으로 리다이렉트
     * 
     * @param viewName
     * @return
     */
    protected String redirectTo(String viewName) {
        return "redirect:" + viewName;
    }
    
    /**
     * Ajax 요청 공통 응답 엔티티 생성
     * 
     * @param status HTTP 응답코드
     * @param data BODY 데이터
     * @param message 응답 메세지
     * @return
     */
    protected ResponseEntity<?> buildResponseEntity(HttpStatus status, Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("message", message);
        
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        return new ResponseEntity<>(map, headers, status);
    }
    
}
