/*
 * @(#) BoardCategoryService.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.category.service;

import java.util.List;

import com.hst.pofoland.biz.category.vo.CategoryVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.board.service.BoardCategoryService.java
 * 클래스 설명 : 시스템 카테고리 서비스(Board, Job 공용)
 *
 * @author 이현규
 * @since 2017. 7. 28.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 7. 28.   이현규  최초생성
 * </pre>
 */
public interface CategoryService {
    
    /**
     * 커뮤니티 게시판 카테고리 목록 반환
     * 
     * @return 카테고리 목록
     */
    public List<CategoryVO> getBoardCategoryList();
    
    /**
     * Job 카테고리 목록 반환
     * 
     * @return 카테고리 목록
     */
    public List<CategoryVO> getJobCategoryList();
    
    /**
     * 커뮤니티 게시판 카테고리 상세 반환
     * 
     * @param boardCateSeq
     * @return
     */
    public CategoryVO getBoardCategory(int boardCateSeq);
    
    /**
     * Job 카테고리 상세 반환
     * 
     * @param jobCateSeq
     * @return
     */
    public CategoryVO getJobCategory(int jobCateSeq);
    
}
