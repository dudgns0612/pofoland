
/*
 * @(#) UserTest.java
 *
 * v1.0.0 / 2017. 7. 28. 
 *
 * Copyright ((c) 2017 by HST, Inc. All Rights Reserved.
 */
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hst.pofoland.biz.user.UserService;
import com.hst.pofoland.biz.user.UserVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $.UserTest.java
 * 클래스 설명 : User 기능 단위테스트 클래스
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
public class UserTest {

    @Inject
    private UserService userService;
    
    @Test
    public void createUserTest() {
        UserVO user = new UserVO();
        user.setUserId("gusrb0808");
        user.setUserPw("cjsrn1992");
        user.setUserNick("나하나쯤이야");
        user.setUserEmail("gusrb0808@naver.com");
        userService.crateUser(user);
    }
    
}
