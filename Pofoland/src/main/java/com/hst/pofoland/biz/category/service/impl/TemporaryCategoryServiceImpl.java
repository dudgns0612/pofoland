/*
 * @(#) BoardCategoryServiceImpl.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.category.service.CategoryService;
import com.hst.pofoland.biz.category.vo.CategoryVO;

/**
 * 임시 카테고리 서비스 구현체 / 추후에 CategoryServiceImpl로 교체할 것.
 */
@Service
public class TemporaryCategoryServiceImpl implements CategoryService {

    // DB 한글 처리 전까지 사용할 임시 카테고리 목록
    private String[] bCategories = { "공지사항", "사는얘기", "포럼", "정기모임/스터디", "학원/홍보" };
    
    // DB 한글 처리 전까지 사용할 임시 카테고리 목록
    private String[] jCategories = { "경영·사무·인사", "IT·정보통신", "연예·예술", "디자인", "음악·미디어" };
    
    @Override
    public List<CategoryVO> getBoardCategoryList() {
        List<CategoryVO> categoryList = new ArrayList<CategoryVO>();
        
        for (int i = 0; i < 5; i++) {
            categoryList.add(new CategoryVO((i + 1), bCategories[i]));
        }
        
        return categoryList;
    }

    @Override
    public List<CategoryVO> getJobCategoryList() {
        List<CategoryVO> categoryList = new ArrayList<CategoryVO>();
        
        for (int i = 0; i < 5; i++) {
            categoryList.add(new CategoryVO((i + 1), jCategories[i]));
        }
        
        return categoryList;
    }

}
