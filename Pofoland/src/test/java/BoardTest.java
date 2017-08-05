
/*
 * @(#) BoardTest.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */

import java.util.Random;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hst.pofoland.biz.board.service.BoardService;
import com.hst.pofoland.biz.board.vo.BoardVO;
import com.hst.pofoland.biz.category.service.CategoryService;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $.BoardTest.java
 * 클래스 설명 : 커뮤니티 게시판 기능 단위테스트 클래스
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
        "classpath:/root-context.xml"
})
public class BoardTest {

    private static final Logger logger = LoggerFactory.getLogger(BoardTest.class);
    
    @Inject
    private BoardService boardService;
    
    @Inject
    private CategoryService categoryService;
    
    @Test
    public void insert() {
        BoardVO temp = null;
        
        int bCateSeq, jCateSeq;
        Random r = new Random();
        
        for (int i = 0; i < 100; i++) {
            temp = new BoardVO();
            
            bCateSeq = r.nextInt(5) + 1;
            jCateSeq = r.nextInt(5) + 1;
            
            temp.setBoardCateSeq(bCateSeq);
            temp.setJobCateSeq(jCateSeq);
            
            temp.setBoardTitle("[TEST] 게시글 " + (i+1));
            temp.setBoardContent("안녕하세요! 테스트 게시글입니다.<br> Hello World!");
            temp.setUserSeq(31);
            
            boardService.insertBoard(temp);
        }
    }

}
