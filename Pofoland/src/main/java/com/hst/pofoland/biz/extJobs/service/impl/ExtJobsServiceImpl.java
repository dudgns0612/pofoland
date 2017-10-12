package com.hst.pofoland.biz.extJobs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.extJobs.service.ExtJobsService;
import com.hst.pofoland.biz.extJobs.vo.ExtJobsVO;
import com.hst.pofoland.common.utils.ApiUtils;

@Service
public class ExtJobsServiceImpl implements ExtJobsService {

	private ApiUtils apiUtils;
	@Override
	public List<ExtJobsVO> searchJobs(ExtJobsVO params) {
		String url = params.getUrl();
		String keyword = params.getKeyword();
		List<ExtJobsVO> extJobsList = apiUtils.searchJobs(url, keyword);
		return extJobsList;
	}

}
