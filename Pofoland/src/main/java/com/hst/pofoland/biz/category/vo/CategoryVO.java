/*
 * @(#) BoardCategoryVO.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.category.vo;

import com.hst.pofoland.common.vo.BaseVO;

/**
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.board.vo.BoardCategoryVO.java
 * 클래스 설명 : 커뮤니티 게시판 카테고리 VO 클래스
 *
 * @author 이현규
 * @since 2017. 7. 28.
 * @version 1.0.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 7. 28.   이현규  최초생성
 *      </pre>
 */
public class CategoryVO extends BaseVO {

    private int cateSeq;
    private String cateName;

    public CategoryVO() {
    }

    /**
     * @param cateSeq
     * @param cateName
     */
    public CategoryVO(int cateSeq, String cateName) {
        super();
        this.cateSeq = cateSeq;
        this.cateName = cateName;
    }

    /**
     * @return the cateSeq
     */
    public int getCateSeq() {
        return cateSeq;
    }

    /**
     * @param cateSeq
     *            the cateSeq to set
     */
    public void setCateSeq(int cateSeq) {
        this.cateSeq = cateSeq;
    }

    /**
     * @return the cateName
     */
    public String getCateName() {
        return cateName;
    }

    /**
     * @param cateName
     *            the cateName to set
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CategoryVO [cateSeq=").append(cateSeq).append(", cateName=").append(cateName).append("]");
        return builder.toString();
    }

}
