/*
 * @(#) BoardController.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.board.ctrl;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * 2017. 11. 18.  이현규  REST컨트롤러와 분리
 * </pre>
 */
@Controller
@RequestMapping("/board")
public class BoardController extends BaseController {

    private BoardService boardService;
    
    @Inject
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    
    /**
     * 게시글 목록화면으로 이동
     * 
     * @param condition 조회조건
     * @return
     */
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
    
    /**
     * 게시글 등록화면으로 이동
     * 
     * @param writeForm 게시글 작성 Form
     * @return
     */
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public ModelAndView boardWrite(@ModelAttribute("writeForm")BoardVO writeForm) {
        ModelAndView mv= new ModelAndView("board/write");
        mv.addObject("boardCategories", codeService.getCodeList("A01"));
        mv.addObject("jobCategories", codeService.getCodeList("B01"));
        
        writeForm.setUserSeq(getSessionUserSeq());
        
        return mv;
    }

    /**
     * 게시글 수정화면으로 이동
     * 
     * @param srchVo 수정게시글정보Vo
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public ModelAndView boardModify(BoardVO srchVo) {
        ModelAndView mv= new ModelAndView("board/write");
        
        // 현재 게시물 조회
        BoardVO board = boardService.getBoard(srchVo);
        
        // 존재하지 않는 게시물을 수정하려 한 경우
        if(board == null) {
            mv.setViewName(redirectTo("error500"));
            return mv;
        }
        // 조회한 게시물의 작성자와 로그인한 사용자의 시퀀스가 일치하지 않는 경우
        else if(board.getUserSeq() != getSessionUserSeq()) {
            mv.setViewName(redirectTo("error401"));
            return mv;
        }
        
        mv.addObject("boardCategories", codeService.getCodeList("A01"));
        mv.addObject("jobCategories", codeService.getCodeList("B01"));
        mv.addObject("writeForm", board);
        
        return mv;
    }
    
    /**
     * 게시글 상세조회 화면으로 이동
     * 
     * @param boardSeq 게시글번호
     * @return
     */
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
    
    /**
     * 게시글 등록 API
     * 
     * @param writeForm 게시글작성Form
     * @param request Multipart 요청정보 객체
     * @return
     */
    @RequestMapping(value ="", method = RequestMethod.POST)
    public String writeBoard(@ModelAttribute("writeForm")BoardVO writeForm, MultipartHttpServletRequest request) {
        boardService.writeBoard(writeForm);
        return redirectTo("/board/main");
    }
    
}