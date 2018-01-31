/*
 * @(#) BoardController.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.board.ctrl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.board.service.BoardService;
import com.hst.pofoland.biz.board.vo.BoardVO;
import com.hst.pofoland.common.ctrl.BaseController;
import com.hst.pofoland.common.utils.LoggerManager;

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
@Controller
@RequestMapping("/board")
public class BoardController extends BaseController {

    @Inject
    private BoardService boardService;
    
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView boardMain(@ModelAttribute("condition")BoardVO condition) {
        // 페이징처리를 위한 세팅
        condition.setTotalRecordCount(boardService.getBoardListCount(condition));
        condition.createPaginationInfo();
        
        ModelAndView mv = new ModelAndView("board/list");
        mv.addObject("boardList", boardService.getBoardList(condition));
        mv.addObject("boardCategories", codeService.getCodeList("A01"));
        mv.addObject("jobCategories", codeService.getCodeList("B01"));
        mv.addObject("currentCategory", codeService.getCode("A01", "01"));
        
        LoggerManager.info(getClass(), "{}", condition.getPaginationInfo().getTotalPageCount());
        
        return mv;
    }
    
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public ModelAndView boardWrite(@ModelAttribute("writeForm")BoardVO writeForm) {
        ModelAndView mv= new ModelAndView("board/write");
        mv.addObject("boardCategories", codeService.getCodeList("A01"));
        mv.addObject("jobCategories", codeService.getCodeList("B01"));
        
        writeForm.setUserSeq(getSessionUserSeq());
        
        return mv;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public ModelAndView boardModify(BoardVO srchVo) {
        ModelAndView mv= new ModelAndView("board/write");
        
        // 현재 게시물 조회
        BoardVO board = boardService.getBoard(srchVo);
        
        // 존재하지 않는 게시물을 수정하려 한 경우
        if(board == null) {
            mv.setViewName("redirect:error500");
            return mv;
        }
        // 조회한 게시물의 작성자와 로그인한 사용자의 시퀀스가 일치하지 않는 경우
        else if(board.getUserSeq() != getSessionUserSeq()) {
            mv.setViewName("redirect:error401");
            return mv;
        }
        
        mv.addObject("boardCategories", codeService.getCodeList("A01"));
        mv.addObject("jobCategories", codeService.getCodeList("B01"));
        mv.addObject("writeForm", board);
        
        return mv;
    }
    
    @RequestMapping(value ="", method = RequestMethod.POST)
    public String writeBoard(@ModelAttribute("writeForm")BoardVO writeForm, MultipartHttpServletRequest request) {
        LoggerManager.info(getClass(), "{}", request);
        
        for(MultipartFile mf : request.getFiles("atthFiles")) {
            LoggerManager.info(getClass(), "{}", mf.getName());            
        }
        
        LoggerManager.info(getClass(), "{}", request.getFile("a"));
        
        boardService.writeBoard(writeForm);
        
        return "redirect:/board/main";
    }
    
    @RequestMapping(value = "{boardSeq}", method = RequestMethod.GET)
    public ModelAndView boardDetail(@PathVariable("boardSeq") String boardSeq) {
        ModelAndView mv = new ModelAndView("board/detail");
        
        BoardVO srchVo = new BoardVO();
        srchVo.setBoardSeq(Integer.parseInt(boardSeq));
        
        BoardVO board = boardService.getBoard(srchVo);
        boardService.increaseHit(srchVo);
        
        mv.addObject("board", board);
        
        return mv;
    }

}