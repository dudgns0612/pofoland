package com.hst.pofoland.biz.board.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        List<BoardVO> boardList = boardDao.selectBoard(condition);
        
        
        return boardList;
    }

    @Override
    public int insertBoard(BoardVO board) {
        int result = boardDao.insertBoard(board);
        return result;
    }
    
}
