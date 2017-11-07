package com.hst.pofoland.biz.extJobs.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.extJobs.service.ExtJobsService;
import com.hst.pofoland.biz.extJobs.vo.ExtJobsVO;
import com.hst.pofoland.common.utils.ApiUtils;
import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.common.utils.StatusUtils;

@Service
public class ExtJobsServiceImpl implements ExtJobsService {
	
	@Inject
	private ApiUtils apiUtils;
	
	@Override
	public List<ExtJobsVO> searchJobs(ExtJobsVO params) {
		String url = StatusUtils.SARAMIN_API_URL.getFirstValue();
		String keyword = params.getKeyword();
		List<ExtJobsVO> extJobsList = apiUtils.searchJobs(url, keyword);
		return extJobsList;
	}

}
