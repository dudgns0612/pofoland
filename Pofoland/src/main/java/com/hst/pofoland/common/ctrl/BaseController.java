/*
 * @(#) AbstractController.java
 *
 * v1.0.0 / 2017. 10. 8.
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.ctrl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
    
}
