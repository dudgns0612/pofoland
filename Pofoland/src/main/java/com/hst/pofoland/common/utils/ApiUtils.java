package com.hst.pofoland.common.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;

import com.hst.pofoland.biz.extJobs.vo.ExtJobsVO;

public class ApiUtils {

	public List<ExtJobsVO> searchJobs(String url, String keyword) {
		List<ExtJobsVO> extJobsList = new ArrayList<ExtJobsVO>();
		HttpClient client = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(url);
		getRequest.getParams().setParameter("keyword", keyword);
		try {
			HttpResponse response = client.execute(getRequest);
			BasicResponseHandler handler = new BasicResponseHandler();
			String var = handler.handleEntity(response.getEntity());
			
			LoggerManager.info(getClass(), "res: {}", var);
		} catch (Exception e) {
			LoggerManager.info(getClass(), "error: " + e);
		}
		return extJobsList;
	}

	
}
