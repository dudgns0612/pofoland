package com.hst.pofoland;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.constnat.NetworkConstant;
import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.common.vo.ResponseVO;


@Controller
public class PageController {

	@RequestMapping(value={"/","/home"})
	public String tilesTest() {
		return "common/home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
	    return "common/login";
	}
	
	@RequestMapping(value="/join/step1", method=RequestMethod.GET)
	public ModelAndView joinUser() {
		
		ModelAndView mav = new ModelAndView("user/joinStep1");
		mav.addObject("type", "general");
		
		return mav;
	}
	
	@RequestMapping(value="/join/step2", method=RequestMethod.POST)
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

}
