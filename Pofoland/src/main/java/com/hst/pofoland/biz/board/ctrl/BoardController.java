/*
 * @(#) BoardController.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.board.ctrl;

import javax.annotation.Resource;

import com.hst.pofoland.biz.board.service.BoardService;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.board.ctrl.BoardController.java
 * 클래스 설명 : 커뮤니티 게시판 컨트롤러 클래스
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
public class BoardController {

    @Resource(name = "boardService")
    private BoardService boardService;
    
}
