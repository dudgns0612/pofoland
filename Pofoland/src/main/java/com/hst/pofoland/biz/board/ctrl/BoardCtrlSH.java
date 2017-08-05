package com.hst.pofoland.biz.board.ctrl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.board.service.BoardService;
import com.hst.pofoland.biz.board.vo.BoardVO;
import com.hst.pofoland.biz.category.service.CategoryService;
import com.hst.pofoland.common.utils.LoggerManager;

@Controller
public class BoardCtrlSH {

    @Inject
    private CategoryService categoryService;

    @Inject
    private BoardService boardService;
    
    @RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
    public ModelAndView boardDetail() {
        ModelAndView mv = new ModelAndView("boardDetail");
        
        List<BoardVO> boardList = boardService.getBoardList(new BoardVO());
        
        BoardVO b = boardList.get(0);
        
        //boardService.getBoard(searchVo);
        
        mv.addObject("board", b);
        
        return mv;
    }
    
}
