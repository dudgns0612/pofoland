package com.hst.pofoland.biz.user.ctrl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.user.service.UserInfoService;
import com.hst.pofoland.biz.user.vo.UserVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.user.UserInfoController.java
 * 클래스 설명 : UserInfo관련 Controller
 *
 * @author 김영훈
 * @since 2017. 9. 25.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 9. 25.		김영훈			최초생성
 * </pre>
 */

@Controller
public class UserInfoController {
	
	@Inject
	UserInfoService userInfoService;
	
	/**
	 * 회원정보 조회(페이징처리)
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value="/user/info")
	public ModelAndView getUserInfo(@ModelAttribute("condition")UserVO userVO) {
		userVO.setTotalRecordCount(userInfoService.getUseInfoTotal(userVO));
		userVO.createPaginationInfo();
		
		ModelAndView mav = new ModelAndView("user/userInfo");
		List<UserVO> userInfoList =  userInfoService.getUserInfo(userVO);
		
		if (userInfoList.size() > 0) {
			mav.addObject("userInfoList", userInfoList);
		}
		
		return mav;
	}
}
