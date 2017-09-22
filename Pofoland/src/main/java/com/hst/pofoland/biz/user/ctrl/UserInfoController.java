package com.hst.pofoland.biz.user.ctrl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.user.service.UserInfoService;
import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.utils.LoggerManager;

@Controller
public class UserInfoController {
	
	@Inject
	UserInfoService userInfoService;
	
	@RequestMapping(value="/user/info")
	public ModelAndView getUserInfo(@ModelAttribute("condition")UserVO userVO) {
		
		LoggerManager.info(getClass(), "userInfo {}", userVO);
		
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
