/*
 * @(#) BoardReplyServiceImpl.java
 *
 * v1.0.0 / 2017. 10. 15.
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.boardreply.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.boardreply.dao.BoardReplyDAO;
import com.hst.pofoland.biz.boardreply.service.BoardReplyService;
import com.hst.pofoland.biz.boardreply.vo.BoardReplyVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * com.hst.pofoland.biz.boardreply.service.impl.BoardReplyServiceImpl.java
 * 클래스 설명 : 커뮤니티 댓글 관련 서비스 구현체
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
@Service
public class BoardReplyServiceImpl implements BoardReplyService {

    @Inject
    private BoardReplyDAO boardReplyDao;
    
    @Override
    public List<BoardReplyVO> getBoardReplyList(BoardReplyVO srchVo) {
        return boardReplyDao.selectBoardReplyList(srchVo);
    }

    @Override
    public void writeBoardReply(BoardReplyVO replyVo) {
        boardReplyDao.insertBoardReply(replyVo);        
    }

    @Override
    public void modifyBoardReply(BoardReplyVO replyVo) {
        boardReplyDao.updateBoardReply(replyVo);
    }

    @Override
    public void deleteBoardReply(BoardReplyVO srchVo) {
        boardReplyDao.deleteBoardReply(srchVo);
    }

}
