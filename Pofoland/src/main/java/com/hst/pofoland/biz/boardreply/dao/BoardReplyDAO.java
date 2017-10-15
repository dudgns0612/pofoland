/*
 * @(#) BoardReplyDao.java
 *
 * v1.0.0 / 2017. 10. 15.
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.boardreply.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hst.pofoland.biz.boardreply.vo.BoardReplyVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * com.hst.pofoland.biz.boardreply.dao.BoardReplyDao.java
 * 클래스 설명 : 커뮤니티 댓글 DAO 인터페이스 
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
@Repository
public interface BoardReplyDAO {

    /**
     * 댓글 목록 조회
     * 
     * @param srchVo
     * @return
     */
    public List<BoardReplyVO> selectBoardReplyList(BoardReplyVO srchVo);

    /**
     * 댓글 등록
     * 
     * @param replyVo
     */
    public void insertBoardReply(BoardReplyVO replyVo);
    
    /**
     * 댓글 수정
     * 
     * @param replyVo
     */
    public void updateBoardReply(BoardReplyVO replyVo);

    /**
     * 댓글 삭제
     * 
     * @param replyVo
     */
    public void deleteBoardReply(BoardReplyVO replyVo);
    
}
