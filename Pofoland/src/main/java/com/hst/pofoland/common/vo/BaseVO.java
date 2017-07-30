/*
 * @(#) BaseVO.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.common.vo;

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

    private String search;
    private String searchType;
    
    private int startRecord;
    private int endRecord;
    
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
     * @return the startRecord
     */
    public int getStartRecord() {
        return startRecord;
    }

    /**
     * @param startRecord the startRecord to set
     */
    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    /**
     * @return the endRecord
     */
    public int getEndRecord() {
        return endRecord;
    }

    /**
     * @param endRecord the endRecord to set
     */
    public void setEndRecord(int endRecord) {
        this.endRecord = endRecord;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseVO [search=").append(search).append(", searchType=").append(searchType)
                .append(", startRecord=").append(startRecord).append(", endRecord=").append(endRecord).append("]");
        return builder.toString();
    }
    
    
}
