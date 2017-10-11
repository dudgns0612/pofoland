/*
 * @(#) BaseVO.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.vo.BaseVO.java
 * 클래스 설명 : 기본 VO 클래스
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
public class BaseVO {
    // 기본 페이지 크기
    public static final int DEFAULT_PAGE_SIZE = 5;
    
    // 기본 페이지 당 레코드 수
    public static final int DEFAULT_RECORD_PER_PAGE = 10;

    // 검색어
    private String search;
    
    // 검색 타입
    private String searchType;
    
    /* ===========        페이징 관련 멤버변수      ============== */
    private PaginationInfo paginationInfo;  // eGov 페이징 변수
    private int firstRecordIndex;     // 시작 레코드 인덱스
    private int lastRecordIndex;      // 마지막 레코드 인덱스
    private int currentPageNo;        // 현재 페이지 번호
    private int totalRecordCount;     // 총 레코드 갯수
    private int recordCountPerPage;   // 페이지당 레코드 갯수
    private int pageSize;             // 페이지 크기
    /* ================================================== */
    
    public BaseVO() {
    }

    /**
     * @param search
     * @param searchType
     */
    public BaseVO(String search, String searchType) {
        super();
        this.search = search;
        this.searchType = searchType;
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }
    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }
    /**
     * @return the searchType
     */
    public String getSearchType() {
        return searchType;
    }
    /**
     * @param searchType the searchType to set
     */
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    /**
     * @return the firstRecordIndex
     */
    public int getFirstRecordIndex() {
        return firstRecordIndex;
    }

    /**
     * @param firstRecordIndex the firstRecordIndex to set
     */
    public void setFirstRecordIndex(int firstRecordIndex) {
        this.firstRecordIndex = firstRecordIndex;
    }

    /**
     * @return the lastRecordIndex
     */
    public int getLastRecordIndex() {
        return lastRecordIndex;
    }

    /**
     * @param lastRecordIndex the lastRecordIndex to set
     */
    public void setLastRecordIndex(int lastRecordIndex) {
        this.lastRecordIndex = lastRecordIndex;
    }

    /**
     * @return the currentPageNo
     */
    public int getCurrentPageNo() {
        return currentPageNo;
    }

    /**
     * @param currentPageNo the currentPageNo to set
     */
    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    /**
     * @return the totalRecordCount
     */
    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    /**
     * @param totalRecordCount the totalRecordCount to set
     */
    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the recordCountPerPage
     */
    public int getRecordCountPerPage() {
        return recordCountPerPage;
    }

    /**
     * @param recordCountPerPage the recordCountPerPage to set
     */
    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    /**
     * @return the paginationInfo
     */
    public PaginationInfo getPaginationInfo() {
        return paginationInfo;
    }

    /**
     * @param paginationInfo the paginationInfo to set
     */
    public void setPaginationInfo(PaginationInfo paginationInfo) {
        this.paginationInfo = paginationInfo;
    }
    
    public void createPaginationInfo() {
        paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(currentPageNo == 0 ? 1 : currentPageNo);
        paginationInfo.setPageSize(pageSize == 0 ? DEFAULT_PAGE_SIZE : pageSize);
        paginationInfo.setRecordCountPerPage(recordCountPerPage == 0 ? DEFAULT_RECORD_PER_PAGE : recordCountPerPage);
        paginationInfo.setTotalRecordCount(totalRecordCount);

        currentPageNo = paginationInfo.getCurrentPageNo();
        recordCountPerPage = paginationInfo.getRecordCountPerPage();
        lastRecordIndex = paginationInfo.getLastRecordIndex();
        firstRecordIndex = paginationInfo.getFirstRecordIndex();
    }
    
}
