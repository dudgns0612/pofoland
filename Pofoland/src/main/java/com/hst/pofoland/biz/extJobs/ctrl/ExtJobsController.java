package com.hst.pofoland.biz.extJobs.ctrl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.extJobs.service.ExtJobsService;
import com.hst.pofoland.biz.extJobs.vo.ExtJobsVO;
import com.hst.pofoland.common.utils.LoggerManager;

/**
* 
*
* 클래스에 클래스에 대한 설명
*
* @author ypLim
* @since 2017. 9. 9.
* @version 1.0
* @see
*
* <pre>
* << 개정이력 (Modification Information) >>
*
* 수정일	수정자		수정내용
* ------------------------------------------
* 2017. 9. 9.	임유표		최초생성
*
* </pre>
 */


@Controller
@RequestMapping("/extJobs")
public class ExtJobsController {

	@Inject
	private ExtJobsService extJobsService;

	@RequestMapping(value="/main", method = RequestMethod.GET) 
	public ModelAndView jobsMain(HttpServletRequest request) {
		LoggerManager.info(getClass(), "extJobs Chk");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("extJobs/main");

		/*
		 *	사람인_API 검색 페이징 가져오기
		 * 
		 */
		
		return mv;
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public List<ExtJobsVO> jobsSearch(ExtJobsVO params) {
		List<ExtJobsVO> extJobsList = null;
		
		/* Saramin Api 요청 */
		extJobsList = extJobsService.searchJobs(params);
		return extJobsList;
	}
}
