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
import com.hst.pofoland.biz.code.service.CodeService;
import com.hst.pofoland.biz.user.vo.UserVO;
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
public class BoardController {

    @Inject
    private BoardService boardService;
    
    @Inject
    private CodeService codeService;
    
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
    public ModelAndView boardWrite(@ModelAttribute("writeForm")BoardVO writeForm, HttpSession session) {
        ModelAndView mv= new ModelAndView("board/write");
        mv.addObject("boardCategories", codeService.getCodeList("A01"));
        mv.addObject("jobCategories", codeService.getCodeList("B01"));
        
        UserVO loginUser = (UserVO) session.getAttribute("user");
        writeForm.setUserSeq(loginUser.getUserSeq());
        
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