/*
 * @(#) BoardRestController.java
 *
 * v1.0.0 / 2017. 11. 18.
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.board.ctrl;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hst.pofoland.biz.board.service.BoardService;
import com.hst.pofoland.biz.board.vo.BoardReplyVO;
import com.hst.pofoland.common.ctrl.BaseController;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * com.hst.pofoland.biz.board.ctrl.BoardRestController.java
 * 클래스 설명 : 커뮤니티 게시판 API 처리 컨트롤러
 *
 * @author 이현규
 * @since 2017. 11. 18.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 11. 18.   이현규          최초생성
 * </pre>
 */
@RestController
@RequestMapping("/api/board")
public class BoardRestController extends BaseController {
    
    private BoardService boardService;
    
    @Inject
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }
    
    
    
    @RequestMapping(value = "/reply",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> wrtieBoardReply(BoardReplyVO replyVo) {
        boardService.writeReply(replyVo);
        
        return buildResponseEntity(HttpStatus.OK, null, "댓글이 성공적으로 작성되었습니다.");
    }
    
}
