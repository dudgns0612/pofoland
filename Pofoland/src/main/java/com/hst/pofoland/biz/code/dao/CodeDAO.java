/*
 * @(#) CodeDAO.java
 *
 * v1.0.0 / 2017. 10. 01. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.code.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hst.pofoland.biz.code.vo.CodeVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.code.dao.CodeDAO.java
 * 클래스 설명 : 코드관리 DAO 인터페이스
 *
 * @author 이현규
 * @since 2017. 10. 01.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 10. 01.   이현규  최초생성
 * </pre>
 */
@Repository
public interface CodeDAO {

    /**
     * 코드 목록 조회
     * 
     * @param code
     * @return
     */
    public List<CodeVO> selectCodeList(CodeVO code);
    
    /**
     * 코드 상세 조회
     * 
     * @param code
     * @return
     */
    public CodeVO selectCode(CodeVO code);
    
}
