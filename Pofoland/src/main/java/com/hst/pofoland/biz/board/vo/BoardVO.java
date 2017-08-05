/*
 * @(#) BoardVO.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.board.vo;

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

    private int boardSeq;
    private String boardTitle;
    private String boardContent;
    private String boardRegDt;
    private String boardUdtDt;
    private String boardDelYn;
    private int boardHitCnt;
    
    private int boardCateSeq;
    private String boardCateName;
    
    private int jobCateSeq;
    private String jobCateName;
    
    private int userSeq;
    private String userNick;

    /**
     * 기본 생성자
     */
    public BoardVO() {
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
     * @return the boardCateSeq
     */
    public int getBoardCateSeq() {
        return boardCateSeq;
    }

    /**
     * @param boardCateSeq the boardCateSeq to set
     */
    public void setBoardCateSeq(int boardCateSeq) {
        this.boardCateSeq = boardCateSeq;
    }

    /**
     * @return the boardCateName
     */
    public String getBoardCateName() {
        return boardCateName;
    }

    /**
     * @param boardCateName the boardCateName to set
     */
    public void setBoardCateName(String boardCateName) {
        this.boardCateName = boardCateName;
    }

    /**
     * @return the jobCateSeq
     */
    public int getJobCateSeq() {
        return jobCateSeq;
    }

    /**
     * @param jobCateSeq the jobCateSeq to set
     */
    public void setJobCateSeq(int jobCateSeq) {
        this.jobCateSeq = jobCateSeq;
    }

    /**
     * @return the jobCateName
     */
    public String getJobCateName() {
        return jobCateName;
    }

    /**
     * @param jobCateName the jobCateName to set
     */
    public void setJobCateName(String jobCateName) {
        this.jobCateName = jobCateName;
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BoardVO [boardSeq=").append(boardSeq).append(", boardTitle=").append(boardTitle)
                .append(", boardContent=").append(boardContent).append(", boardRegDt=").append(boardRegDt)
                .append(", boardUdtDt=").append(boardUdtDt).append(", boardDelYn=").append(boardDelYn)
                .append(", boardHitCnt=").append(boardHitCnt).append(", boardCateSeq=").append(boardCateSeq)
                .append(", boardCateName=").append(boardCateName).append(", jobCateSeq=").append(jobCateSeq)
                .append(", jobCateName=").append(jobCateName).append(", userSeq=").append(userSeq).append(", userNick=")
                .append(userNick).append("]");
        return builder.toString();
    }

}