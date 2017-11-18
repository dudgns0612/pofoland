import javax.inject.Inject;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hst.pofoland.biz.board.service.BoardService;
import com.hst.pofoland.biz.board.vo.BoardReplyVO;
import com.hst.pofoland.biz.board.vo.BoardVO;

/*
 * @(#) BoardReplyTest.java
 *
 * v1.0.0 / 2017. 11. 12.
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * .BoardReplyTest.java
 * 클래스 설명 : 
 *
 * @author 이현규
 * @since 2017. 11. 12.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일          수정자         수정내용
 * -------------------------------------------------
 * 2017. 11. 12.   이현규          최초생성
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
        "classpath:/root-context.xml"
})
public class BoardReplyTest {
    
    @Inject
    private BoardService boardService;
    
    private BoardVO srchVo;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Before
    public void setUp() {
        srchVo = new BoardVO();
        srchVo.setBoardSeq(211);
    }
    
    @Test
    @Ignore
    public void 게시판서비스_댓글등록_테스트() {
        BoardReplyVO vo = new BoardReplyVO();
        vo.setBoardSeq(211);
        vo.setBoardReplyContent("천제당당 길드 오세요.");
        vo.setUserSeq(134);
        
        boardService.writeReply(vo);
    }
    
    @Test
    public void 게시판서비스_게시글상세조회_테스트() {
        BoardVO detail = boardService.getBoard(srchVo);
        
        for(BoardReplyVO reply : detail.getBoardReplyList()) {
            logger.info("{} [{}]", reply.getUserNick(), reply.getBoardReplyContent());
        }
        
    }
    
}
