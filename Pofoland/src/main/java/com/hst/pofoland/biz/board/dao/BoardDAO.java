/*
 * @(#) BoardDAO.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hst.pofoland.biz.board.vo.BoardVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.board.BoardDAO.java
 * 클래스 설명 : 커뮤니티 게시판 DAO 클래스
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
@Repository
public interface BoardDAO {

    /**
     * 게시글 등록
     * 
     * @param board 등록할 게시글
     */
    public int insertBoard(BoardVO board);
    
    /**
     * 게시글 조회 
     * 
     * @param board 조회 조건
     * @return
     */
    public List<BoardVO> selectBoard(BoardVO condition);
    
}
