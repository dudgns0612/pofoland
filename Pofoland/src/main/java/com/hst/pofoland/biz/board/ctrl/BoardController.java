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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.category.service.CategoryService;

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
public class BoardController {

    @Inject
    private CategoryService categoryService;

    @RequestMapping(value = "/boardMain", method = RequestMethod.GET)
    public ModelAndView boardMain() {
        ModelAndView mv = new ModelAndView("boardMain");
        mv.addObject("boardCategories", categoryService.getBoardCategoryList());
        return mv;
    }

}
