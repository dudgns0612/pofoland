package com.hst.pofoland.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
@Component
public class ApiUtils {
	
	List<ExtJobsVO> extJobsList = new ArrayList<ExtJobsVO>(); // 구인구직 데이터 리스트

	
	public void methodInvoke(ExtJobsVO vo, String nodeName, String value) throws Exception {
		
		Class<?> extJobsVO = vo.getClass();
		Method tgMethod = null;
		
		try {
			tgMethod = extJobsVO.getDeclaredMethod("set" + nodeName.toUpperCase().charAt(0) + nodeName.substring(1), String.class);
		} catch(NoSuchMethodException e) {
			LoggerManager.info(getClass(), "================= No Search Field: {}", nodeName);
		}
		
		if(tgMethod != null)
			tgMethod.invoke(vo, value);
	}
	
	
	public List<ExtJobsVO> parseXml(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder domBuilder = null;
		Document doc = null;
		ExtJobsVO extJobsVO = null;
		
		try {
			domBuilder = factory.newDocumentBuilder();
			InputStream in = new ByteArrayInputStream(xml.getBytes());
			doc = domBuilder.parse(in);
			Element element = doc.getDocumentElement();
			
			/*
			 * Testing
			 * NodeList, getNode need Check
			 * To-Be : reflection 적용할?
			 */
			NodeList nodeList = element.getElementsByTagName("job");	// Length = 10, <job> 태그 10개
			LoggerManager.info(getClass(), "=============== nodeList.length: [{}]", nodeList.getLength());
			for(int i=0; i<nodeList.getLength(); i++) {
				extJobsVO = new ExtJobsVO();
				NodeList jobList = nodeList.item(i).getChildNodes();
				
				for(int j=0; j<jobList.getLength(); j++) {
					Node jobNode = jobList.item(j);
					String nodeName = jobNode.getNodeName().replaceAll("-", "");
					String value = jobNode.getTextContent();
				
					//
					if(!("#text".equals(nodeName))) {
						
						// <position> 태그 추출 작업
						if("position".equals(nodeName)) {
							NodeList postList = jobNode.getChildNodes();
							for(int idx=0; idx<postList.getLength(); idx++) {
								Node postNode = postList.item(idx);
								String postNodeNm = postNode.getNodeName().replaceAll("-", "");
								String postValue = postNode.getTextContent();
								methodInvoke(extJobsVO, postNodeNm, postValue);
							}
						}
						methodInvoke(extJobsVO, nodeName, value);
					}
				}
				extJobsList.add(extJobsVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return extJobsList;
	}
	
	
	public List<ExtJobsVO> searchJobs(String url, String keyword) {
		String xml = null;
		HttpClient client = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(url+"?keywords="+URLEncoder.encode(keyword));
		try {
			HttpResponse response = client.execute(getRequest);
			BasicResponseHandler handler = new BasicResponseHandler();
			xml = handler.handleEntity(response.getEntity());
			
			LoggerManager.info(getClass(), "res: {}", xml);
		} catch (Exception e) {
			LoggerManager.info(getClass(), "error: " + e);
		}
		return parseXml(xml);
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
