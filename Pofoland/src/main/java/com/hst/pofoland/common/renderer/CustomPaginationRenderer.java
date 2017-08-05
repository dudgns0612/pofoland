/*
 * @(#) CustomPaginationRenderer.java
 *
 * v1.0.0 / 2017. 8. 1. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.renderer;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.renderer.CustomPaginationRenderer.java
 * 클래스 설명 : 페이지네이션 랜더러 클래스
 *
 * @author 이현규
 * @since 2017. 8. 1.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 8. 1.   이현규  최초생성
 * </pre>
 */
public class CustomPaginationRenderer extends AbstractPaginationRenderer {
    
    /**
     * Default 생성자
     */
    public CustomPaginationRenderer() {
        firstPageLabel = "<li><a href=\"#\" onclick=\"{0}({1}); return false;\">&laquo;</a></li>";
        previousPageLabel = "<li><a href=\"#\" onclick=\"{0}({1}); return false;\">&lt;</a></li>";
        currentPageLabel = "<li class=\"active\"><a href=\"#\">{0}</a></li>";
        otherPageLabel = "<li><a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
        nextPageLabel = "<li><a href=\"#\" onclick=\"{0}({1}); return false;\">&gt;</a></li>";
        lastPageLabel = "<li><a href=\"#\" onclick=\"{0}({1}); return false;\">&raquo;</a></li>";
    }
    
}
