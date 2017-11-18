package com.hst.pofoland.biz.board.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.board.dao.BoardDAO;
import com.hst.pofoland.biz.board.dao.BoardReplyDAO;
import com.hst.pofoland.biz.board.service.BoardService;
import com.hst.pofoland.biz.board.vo.BoardReplyVO;
import com.hst.pofoland.biz.board.vo.BoardVO;
import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.common.utils.StringUtils;

@Service
public class BoardServiceImpl implements BoardService {

    @Inject
    private BoardDAO boardDao;
    
    @Inject
    private BoardReplyDAO boardReplyDao;
    
    @Override
    public List<BoardVO> getBoardList(BoardVO condition) {
        List<BoardVO> boardList = boardDao.selectBoardList(condition);
        return boardList;
    }

    @Override
    public int writeBoard(BoardVO board) {
        // 게시글 본문 Summary생성 시작
        String oridinal = board.getBoardContent();
        StringBuffer summary = new StringBuffer();
        
        for(String img : StringUtils.HTMLProcessing.findImgTag(oridinal)) {
            summary.append("<img ").append(img).append(" style=\"witdh:144px; height:108px;\"> &nbsp;&nbsp;&nbsp;");
        }
        summary.append("<br><br>");
        
        String extractedText = StringUtils.HTMLProcessing.text(oridinal);
        
        if(extractedText.length() > 50) {
            summary.append(extractedText.substring(0, 50));
        } else {
            summary.append(extractedText);
        }
        summary.append("...");
        // 게시글 본문 Summary 생성 완료
        
        LoggerManager.debug(getClass(), "Summary : {}", summary.toString());
        
        // 게시글 summary 지정
        board.setBoardContentSummary(summary.toString());
        
        int result = boardDao.insertBoard(board);
        return result;
    }
    
    @Override
    public BoardVO getBoard(BoardVO condition){
        BoardVO board = boardDao.selectBoard(condition);
        
        BoardReplyVO srchVo = new BoardReplyVO();
        
        // 게시글 댓글 조회 및 SET
        srchVo.setBoardSeq(board.getBoardSeq());
        board.setBoardReplyList(boardReplyDao.selectBoardReplyList(srchVo));
        
        return board;
    }

    @Override
    public int getBoardListCount(BoardVO srchVo) {
        return boardDao.selectTotalRecordCount(srchVo);
    }

    @Override
    public void increaseHit(BoardVO board) {
        boardDao.increaseHit(board);
    }

    @Override
    public void writeReply(BoardReplyVO boardReplyVO) {
        boardReplyDao.insertBoardReply(boardReplyVO);
    }

    @Override
    public void deleteReply(BoardReplyVO boardReplyVO) {
        boardReplyDao.deleteBoardReply(boardReplyVO);
    }
    
}
