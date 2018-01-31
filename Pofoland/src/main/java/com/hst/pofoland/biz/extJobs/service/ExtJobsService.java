package com.hst.pofoland.biz.extJobs.service;

import java.util.List;

import com.hst.pofoland.biz.extJobs.vo.ExtJobsVO;

public interface ExtJobsService {

	List<ExtJobsVO> searchJobs(ExtJobsVO params);

}
