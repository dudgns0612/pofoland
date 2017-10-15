package com.hst.pofoland;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.user.dao.UserInfoDAO;
import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.utils.LoggerManager;


@Controller
public class PageController {
	
	@Inject
	UserInfoDAO userInfoDAO;
	
	@RequestMapping(value = {"/","/home"} )
	public ModelAndView home() {
		
		ModelAndView mav = new ModelAndView("common/home");
		List<UserVO> user = userInfoDAO.selectHomeUserInfo();
		mav.addObject("userList", user);
		
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
	    return "common/login";
	}
	
	@RequestMapping(value = "/join/step1", method=RequestMethod.GET)
	public ModelAndView joinUser() {
		
		ModelAndView mav = new ModelAndView("user/joinStep1");
		mav.addObject("userJoinType", "P");
		
		return mav;
	}
	
	@RequestMapping(value = "/join/oAuth/step1/{joinType}", method=RequestMethod.GET)
	public ModelAndView joinOauthUser(@PathVariable("joinType") char type) {
		ModelAndView mav = new ModelAndView("user/joinStep1");
		mav.addObject("userJoinType", type);
		
		return mav;
	}
	
	@RequestMapping(value = "/join/step2", method=RequestMethod.POST)
	public String joinUser1() {
		return "user/joinStep2";
	}
	
	/**
	 * 유저 메일 인증 화인페이지 이동
	 * @param userSeq
	 * @return
	 */
	@RequestMapping(value = "/user/mailauth/{userSeq}", method = RequestMethod.GET)
	public ModelAndView userMailAuthPage(@PathVariable Integer userSeq) {
		
		ModelAndView mav = new ModelAndView("user/mailAuth");
		mav.addObject("userSeq", userSeq);
		
		return mav;
	}
	
	@RequestMapping(value = "/pofoland/info")
	public String pofolandInfo() {
		return "common/pofolandInfo";
	}
	
	@RequestMapping(value = "/user/modify")
	public String userModify() {
		return "user/userModify";
	}
	
	@RequestMapping(value = "/user/modify/password")
	public String userModifyPassword() {
		return "user/userModifyPw";
	}
	
	@RequestMapping(value = "/user/find/info")
	public String userFindPw() {
		return "user/userFindInfo";
	}
	
	
	// =========================ERROR PAGE =============================
	
	// ERROR PAGE 처리
	@RequestMapping(value = "/error404")
	public ModelAndView error404Page(HttpServletResponse response) {
		//ERROR 성공처리
		response.setStatus(HttpServletResponse.SC_OK);
		ModelAndView mav = new ModelAndView("error/404page");
		LoggerManager.info(getClass(),"ERROR PAGE = {}", "404");
		
		
		return mav;
	}
	
	@RequestMapping(value = "/error500")
	public ModelAndView error500Page(HttpServletResponse response) {
		//ERROR 성공처리
		response.setStatus(HttpServletResponse.SC_OK);
		ModelAndView mav = new ModelAndView("error/500page");
		LoggerManager.info(getClass(),"ERROR PAGE = {}", "500");
		
		return mav;
	}
	
	@RequestMapping(value = "/error501")
	public ModelAndView error501Page(HttpServletResponse response) {
		//ERROR 성공처리
		response.setStatus(HttpServletResponse.SC_OK);
		ModelAndView mav = new ModelAndView("error/501page");
		LoggerManager.info(getClass(),"ERROR PAGE = {}", "501");
		
		return mav;
	}
}
