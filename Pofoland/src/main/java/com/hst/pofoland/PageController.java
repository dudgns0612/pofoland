package com.hst.pofoland;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.common.utils.LoggerManager;


@Controller
public class PageController {

	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		LoggerManager.info(getClass(), "로그테스트");
		
		return "login";
	}
	
	@RequestMapping(value="/home")
	public String tilesTest() {
		return "home";
	}
	
	@RequestMapping(value="/join/joinUser", method=RequestMethod.GET)
	public String joinUser() {
		
		return "join/joinUser";
	}
	
	@RequestMapping(value="/join/joinUser1", method={RequestMethod.GET, RequestMethod.POST})
	public String joinUser1() {
		return "join/joinUser1";
	}
	
	/**
	 * 유저 메일 인증 화인페이지 이동
	 * @param userSeq
	 * @return
	 */
	@RequestMapping(value = "/user/mailauth/{userSeq}", method = RequestMethod.GET)
	public ModelAndView userMailAuthPage(@PathVariable Integer userSeq) {
		
		ModelAndView mav = new ModelAndView("페이지명");
		
		mav.addObject("userSeq", userSeq);
		
		return mav;
	}

}
