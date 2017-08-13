/*
 * @(#) BoardService.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.board.service;

import java.util.List;

import com.hst.pofoland.biz.board.vo.BoardVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.board.ctrl.BoardService.java
 * 클래스 설명 : 커뮤니티 게시판 서비스 인터페이스
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
public interface BoardService {

    
    /**
     * 게시글 목록 반환
     * 
     * @param searchVo 검색 / 페이징 조건
     * @return 게시글 목록
     */
    public List<BoardVO> getBoardList(BoardVO searchVo);
    
    /**
     * 게시글 등록
     * 
     * @param board 등록할 게시글
     * @return 등록 여부
     */
    public int writeBoard(BoardVO board);
    
}
