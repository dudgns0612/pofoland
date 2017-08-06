/*
 * @(#) CategoryServiceImpl.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.category.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.category.dao.CategoryDAO;
import com.hst.pofoland.biz.category.service.CategoryService;
import com.hst.pofoland.biz.category.vo.CategoryVO;

/**
 * 
 * 시스템명 : 
 * $com.hst.pofoland.biz.category.service.impl.CategoryServiceImpl.java
 * 클래스 설명 : 
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
@Service
public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryDAO categoryDao;

    @Override
    public List<CategoryVO> getBoardCategoryList() {
        return categoryDao.selectBoardCategory();
    }

    @Override
    public List<CategoryVO> getJobCategoryList() {
        return categoryDao.selectJobCategory();
    }

    @Override
    public CategoryVO getBoardCategory(int boardCateSeq) {
        if(boardCateSeq == 0) {
            return null;
        }
        
        return categoryDao.selectBoardCategoryDetail(boardCateSeq);
    }

    @Override
    public CategoryVO getJobCategory(int jobCateSeq) {
        return categoryDao.selectJobCategoryDetail(jobCateSeq);
    }
    
}
