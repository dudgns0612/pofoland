package com.hst.pofoland.biz.board.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.board.dao.BoardDAO;
import com.hst.pofoland.biz.board.service.BoardService;
import com.hst.pofoland.biz.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

    @Inject
    private BoardDAO boardDao;
    
    @Override
    public List<BoardVO> getBoardList(BoardVO condition) {
        condition.setTotalRecordCount(boardDao.selectTotalRecordCount(condition));
        condition.createPaginationInfo();
        
        List<BoardVO> boardList = boardDao.selectBoards(condition);
        return boardList;
    }

    @Override
    public int writeBoard(BoardVO board) {
        int result = boardDao.insertBoard(board);
        return result;
    }
    
    @Override
    public BoardVO getBoard(BoardVO boardSeq){
        BoardVO board = boardDao.selectBoard(boardSeq);
        return board;
    }
    
}
