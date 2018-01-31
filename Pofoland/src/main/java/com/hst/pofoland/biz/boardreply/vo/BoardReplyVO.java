/*
 * @(#) BoardReplyVO.java
 *
 * v1.0.0 / 2017. 10. 15.
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.boardreply.vo;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * com.hst.pofoland.biz.boardreply.vo.BoardReplyVO.java
 * 클래스 설명 : 커뮤니티 댓글 VO 클래스
 *
 * @author 이현규
 * @since 2017. 10. 15.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 10. 15.   이현규          최초생성
 * </pre>
 */
public class BoardReplyVO {

    private int boardReplySeq;
    private String boardReplyContent;
    private String boardReplyRegDt;
    private String boardReplyUdtDt;

    private int boardSeq;

    private int userSeq;
    private String userNick;

    public BoardReplyVO() {
    }
    
    public BoardReplyVO(int boardReplySeq, String boardReplyContent, String boardReplyRegDt, String boardReplyUdtDt,
            int boardSeq, int userSeq, String userNick) {
        super();
        this.boardReplySeq = boardReplySeq;
        this.boardReplyContent = boardReplyContent;
        this.boardReplyRegDt = boardReplyRegDt;
        this.boardReplyUdtDt = boardReplyUdtDt;
        this.boardSeq = boardSeq;
        this.userSeq = userSeq;
        this.userNick = userNick;
    }

    /**
     * @return boardReplySeq 반환
     */
    public int getBoardReplySeq() {
        return boardReplySeq;
    }

    /**
     * @param boardReplySeq
     *            설정할 boardReplySeq
     */
    public void setBoardReplySeq(int boardReplySeq) {
        this.boardReplySeq = boardReplySeq;
    }

    /**
     * @return boardReplyContent 반환
     */
    public String getBoardReplyContent() {
        return boardReplyContent;
    }

    /**
     * @param boardReplyContent
     *            설정할 boardReplyContent
     */
    public void setBoardReplyContent(String boardReplyContent) {
        this.boardReplyContent = boardReplyContent;
    }

    /**
     * @return boardReplyRegDt 반환
     */
    public String getBoardReplyRegDt() {
        return boardReplyRegDt;
    }

    /**
     * @param boardReplyRegDt
     *            설정할 boardReplyRegDt
     */
    public void setBoardReplyRegDt(String boardReplyRegDt) {
        this.boardReplyRegDt = boardReplyRegDt;
    }

    /**
     * @return boardReplyUdtDt 반환
     */
    public String getBoardReplyUdtDt() {
        return boardReplyUdtDt;
    }

    /**
     * @param boardReplyUdtDt
     *            설정할 boardReplyUdtDt
     */
    public void setBoardReplyUdtDt(String boardReplyUdtDt) {
        this.boardReplyUdtDt = boardReplyUdtDt;
    }

    /**
     * @return boardSeq 반환
     */
    public int getBoardSeq() {
        return boardSeq;
    }

    /**
     * @param boardSeq
     *            설정할 boardSeq
     */
    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    /**
     * @return userSeq 반환
     */
    public int getUserSeq() {
        return userSeq;
    }

    /**
     * @param userSeq
     *            설정할 userSeq
     */
    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

    /**
     * @return userNick 반환
     */
    public String getUserNick() {
        return userNick;
    }

    /**
     * @param userNick
     *            설정할 userNick
     */
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

}
