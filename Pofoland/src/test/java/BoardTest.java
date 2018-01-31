
/*
 * @(#) BoardTest.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hst.pofoland.biz.board.dao.BoardDAO;
import com.hst.pofoland.biz.board.service.BoardService;
import com.hst.pofoland.biz.board.vo.BoardVO;
import com.hst.pofoland.biz.category.service.CategoryService;
import com.hst.pofoland.common.utils.StringUtils;

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
    private BoardDAO boardDao;
    
    @Inject
    private CategoryService categoryService;
    
    @Test
    public void insert() {
        BoardVO board = new BoardVO();
        board.setBoardGbnCode("A01");
        board.setBoardTitle("테스트");
        board.setBoardContent("테스트 게시글입니다.");
        board.setBoardContentSummary("테스트 게시...");
        board.setUserSeq(94);
        board.setBoardCategory("02");
        board.setJobCategory("01");
        
        boardDao.insertBoard(board);
    }
    
    //@Test
    public void deleteAll() throws ClientProtocolException, IOException {
        HttpClient client = HttpClients.createDefault();
        
        HttpGet get = new HttpGet("http://api.saramin.co.kr/job-search?");
        
        HttpResponse response = client.execute(get);
        BasicResponseHandler handler = new BasicResponseHandler();
        String body = handler.handleEntity(response.getEntity());
        
        System.out.println(body);
        
    }
    
}
