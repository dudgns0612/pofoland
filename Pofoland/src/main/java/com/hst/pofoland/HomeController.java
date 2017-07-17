package com.hst.pofoland;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hst.pofoland.common.utils.LoggerManager;


@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		LoggerManager.info(getClass(), "로그테스트");
		
		return "home";
	}
	
	@RequestMapping(value="/tiles")
	public String tilesTest() {
		return "tiles";
	}
	
}
