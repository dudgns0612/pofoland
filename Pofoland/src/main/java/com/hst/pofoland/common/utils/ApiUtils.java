package com.hst.pofoland.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;

import com.hst.pofoland.biz.extJobs.vo.ExtJobsVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.utils.ApiUtils.java
 * 클래스 설명 : api관련 Utill
 *
 * @author 임유표
 * @since 2017. 9. 28.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 9. 28.		임유표			최초생성
 * </pre>
 */

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
	
	/**
	 * 공통 httpConnection
	 * @param requestURI
	 * @return
	 */
	public static String urlConnResponseStr(String requestURI , String... keyword) {
		StringBuffer response = null;

		try {
			URL url = new URL(requestURI);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

		} catch (UnsupportedEncodingException e) {
			LoggerManager.error(ApiUtils.class, "ERROR : {}", e.getMessage());
		} catch (MalformedURLException e) {
			LoggerManager.error(ApiUtils.class, "ERROR : {}", e.getMessage());
		} catch (IOException e) {
			LoggerManager.error(ApiUtils.class, "ERROR : {}", e.getMessage());
		}

		return response.toString();
	}
	
	/**
	 * 헤더 추가 HttpConnection
	 * @param requestURI
	 * @param httpHeaderMap
	 * @return
	 */
	public static String urlConnResponseStr(String requestURI , Map<String,String> httpHeaderMap, String... keyword) {
		StringBuffer response = null;

		try {
			URL url = new URL(requestURI);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			Iterator<String> keys = httpHeaderMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				con.setRequestProperty(key, httpHeaderMap.get(key));
			}
			
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

		} catch (UnsupportedEncodingException e) {
			LoggerManager.error(ApiUtils.class, "ERROR : {}", e.getMessage());
		} catch (MalformedURLException e) {
			LoggerManager.error(ApiUtils.class, "ERROR : {}", e.getMessage());
		} catch (IOException e) {
			LoggerManager.error(ApiUtils.class, "ERROR : {}", e.getMessage());
		}

		return response.toString();
	}
}
