/*
 * @(#) BoardController.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
package com.hst.pofoland.biz.board.ctrl;

import java.util.Enumeration;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.board.service.BoardService;
import com.hst.pofoland.biz.board.vo.BoardVO;
import com.hst.pofoland.biz.category.service.CategoryService;
import com.hst.pofoland.biz.category.vo.CategoryVO;
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
public class BoardController implements InitializingBean {

    @Inject
    private CategoryService categoryService;

    @Inject
    private BoardService boardService;
    
    // 캐싱 적용 전 임시 메모리 저장소로 활용할 객체
    private List<CategoryVO> boardCategories;
    private List<CategoryVO> jobCategories;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        boardCategories = categoryService.getBoardCategoryList();
        jobCategories = categoryService.getJobCategoryList();
    }

    
    @RequestMapping(value = "/boardMain", method = RequestMethod.GET)
    public ModelAndView boardMain(@ModelAttribute("condition")BoardVO condition) {
        ModelAndView mv = new ModelAndView("boardMain");
        mv.addObject("boardList", boardService.getBoardList(condition));
        mv.addObject("boardCategories", boardCategories);
        mv.addObject("jobCategories", jobCategories);
        mv.addObject("currentCategory", categoryService.getBoardCategory(condition.getBoardCateSeq()));
        return mv;
    }
    
    @RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
    public ModelAndView boardWrite(@ModelAttribute("writeForm")BoardVO writeForm) {
        ModelAndView mv= new ModelAndView("boardWrite");
        mv.addObject("boardCategories", boardCategories);
        mv.addObject("jobCategories", jobCategories);
        return mv;
    }

    @RequestMapping(value ="/board", method = RequestMethod.POST)
    public void writeBoard(@ModelAttribute("writeForm")BoardVO writeForm) {
        LoggerManager.info(getClass(), "{}", writeForm);
    }

}