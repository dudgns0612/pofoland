/*
 * @(#) BoardVO.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.board.vo;

import java.util.List;

import com.hst.pofoland.common.vo.BaseVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.board.vo.BoardVO.java
 * 클래스 설명 : 커뮤니티 게시판 Vo 클래스
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
public class BoardVO extends BaseVO {
    
    private String boardGbnCode;
    
    private int boardSeq;
    private String boardTitle;
    private String boardContent;
    private String boardContentSummary;
    private String boardRegDt;
    private String boardUdtDt;
    private String boardDelYn;
    private int boardHitCnt;

    private String boardCategory;
    private String boardCategoryName;
    
    private String jobCategory;
    private String jobCategoryName;
    
    private int userSeq;
    private String userId;
    private String userNick;
    
    private List<BoardReplyVO> boardReplyList;
    
    /**
     * 기본 생성자
     */
    public BoardVO() {
    }

    /**
     * @return boardContentSummary 반환
     */
    public String getBoardContentSummary() {
        return boardContentSummary;
    }

    /**
     * @param boardContentSummary 설정할 boardContentSummary
     */
    public void setBoardContentSummary(String boardContentSummary) {
        this.boardContentSummary = boardContentSummary;
    }

    /**
     * @return the boardSeq
     */
    public int getBoardSeq() {
        return boardSeq;
    }

    /**
     * @param boardSeq the boardSeq to set
     */
    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    /**
     * @return the boardTitle
     */
    public String getBoardTitle() {
        return boardTitle;
    }

    /**
     * @param boardTitle the boardTitle to set
     */
    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    /**
     * @return the boardContent
     */
    public String getBoardContent() {
        return boardContent;
    }

    /**
     * @param boardContent the boardContent to set
     */
    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    /**
     * @return the boardRegDt
     */
    public String getBoardRegDt() {
        return boardRegDt;
    }

    /**
     * @param boardRegDt the boardRegDt to set
     */
    public void setBoardRegDt(String boardRegDt) {
        this.boardRegDt = boardRegDt;
    }

    /**
     * @return the boardUdtDt
     */
    public String getBoardUdtDt() {
        return boardUdtDt;
    }

    /**
     * @param boardUdtDt the boardUdtDt to set
     */
    public void setBoardUdtDt(String boardUdtDt) {
        this.boardUdtDt = boardUdtDt;
    }

    /**
     * @return the boardDelYn
     */
    public String getBoardDelYn() {
        return boardDelYn;
    }

    /**
     * @param boardDelYn the boardDelYn to set
     */
    public void setBoardDelYn(String boardDelYn) {
        this.boardDelYn = boardDelYn;
    }

    /**
     * @return the boardHitCnt
     */
    public int getBoardHitCnt() {
        return boardHitCnt;
    }

    /**
     * @param boardHitCnt the boardHitCnt to set
     */
    public void setBoardHitCnt(int boardHitCnt) {
        this.boardHitCnt = boardHitCnt;
    }

    /**
     * @return boardCategory 반환
     */
    public String getBoardCategory() {
        return boardCategory;
    }

    /**
     * @param boardCategory 설정할 boardCategory
     */
    public void setBoardCategory(String boardCategory) {
        this.boardCategory = boardCategory;
    }

    /**
     * @return boardCategoryName 반환
     */
    public String getBoardCategoryName() {
        return boardCategoryName;
    }

    /**
     * @param boardCategoryName 설정할 boardCategoryName
     */
    public void setBoardCategoryName(String boardCategoryName) {
        this.boardCategoryName = boardCategoryName;
    }

    /**
     * @return jobCategory 반환
     */
    public String getJobCategory() {
        return jobCategory;
    }

    /**
     * @param jobCategory 설정할 jobCategory
     */
    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    /**
     * @return jobCategoryName 반환
     */
    public String getJobCategoryName() {
        return jobCategoryName;
    }

    /**
     * @param jobCategoryName 설정할 jobCategoryName
     */
    public void setJobCategoryName(String jobCategoryName) {
        this.jobCategoryName = jobCategoryName;
    }

    /**
     * @return the userSeq
     */
    public int getUserSeq() {
        return userSeq;
    }

    /**
     * @param userSeq the userSeq to set
     */
    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    /**
     * @return the userNick
     */
    public String getUserNick() {
        return userNick;
    }

    /**
     * @param userNick the userNick to set
     */
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return boardGbnCode 반환
     */
    public String getBoardGbnCode() {
        return boardGbnCode;
    }

    /**
     * @param boardGbnCode 설정할 boardGbnCode
     */
    public void setBoardGbnCode(String boardGbnCode) {
        this.boardGbnCode = boardGbnCode;
    }

    /**
     * @return boardReplyList 반환
     */
    public List<BoardReplyVO> getBoardReplyList() {
        return boardReplyList;
    }

    /**
     * @param boardReplyList 설정할 boardReplyList
     */
    public void setBoardReplyList(List<BoardReplyVO> boardReplyList) {
        this.boardReplyList = boardReplyList;
    }

}