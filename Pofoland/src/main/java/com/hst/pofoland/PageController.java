package com.hst.pofoland;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PageController {

	@RequestMapping(value={"/","/home"})
	public String tilesTest() {
		return "common/home";
	}
	
	@RequestMapping(value="/join/step1", method=RequestMethod.GET)
	public ModelAndView joinUser() {
		
		ModelAndView mav = new ModelAndView("user/joinStep1");
		mav.addObject("type", "general");
		
		return mav;
	}
	
	@RequestMapping(value="/join/step2", method={RequestMethod.GET, RequestMethod.POST})
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
