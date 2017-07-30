/*
 * @(#) CategoryDAO.java
 *
 * v1.0.0 / 2017. 7. 30. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.category.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hst.pofoland.biz.category.vo.CategoryVO;

/**
 * 
 * 시스템명 : 
 * $com.hst.pofoland.biz.category.dao.CategoryDAO.java
 * 클래스 설명 : 
 *
 * @author 이현규
 * @since 2017. 7. 30.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 7. 30.   이현규  최초생성
 * </pre>
 */
@Repository
public interface CategoryDAO {
    
    /**
     * 커뮤니티 게시판 카테고리 목록 반환
     * @return
     */
    public List<CategoryVO> selectBoardCategory();
    
    /**
     * 카테고리 목록 반환
     * @return
     */
    public List<CategoryVO> selectJobCategory();
    
}
