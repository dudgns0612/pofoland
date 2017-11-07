package com.hst.pofoland.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

	
	public Class methodInvoke(ExtJobsVO vo, String nodeName, String value) throws Exception {
		Method[] methodList = null;
		String methodNm = null;
		
		Class extJobsVO = ExtJobsVO.class;
		extJobsVO.newInstance();
		methodList = extJobsVO.getMethods();
		
		for(Method method : methodList) {
			methodNm = method.getName().substring(3);
			LoggerManager.info(getClass(), "============= methodNm: {}", methodNm);

			if(methodNm.equals(nodeName) && "set".equals(method.getName().substring(0, 3))) {
				method.invoke(value);
			}
			
		}
		return extJobsVO;
	}
	@SuppressWarnings("null")
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
					String nodeName = jobNode.getNodeName();
					String value = jobNode.getTextContent();
				
					//
					if(!("#text".equals(nodeName))) {
						
						// <position> 태그 추출 작업
						if("position".equals(nodeName)) {
							NodeList postList = jobNode.getChildNodes();
						}
						methodInvoke(extJobsVO, nodeName, value);
					}
					//
				}
				extJobsList.add(extJobsVO);
			}
		} catch (ParserConfigurationException e) {
			LoggerManager.info(getClass(), "=================== domBuilder: {}", domBuilder);
			e.printStackTrace();
		} catch (SAXException e) {
			LoggerManager.info(getClass(), "=================== doc: {} ", doc);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			LoggerManager.info(getClass(), "=================== Method Not find: {}");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return extJobsList;
	}
	public List<ExtJobsVO> searchJobs(String url, String keyword) {
		String xml = null;
		HttpClient client = HttpClients.createDefault();
		HttpGet getRequest = new HttpGet(url+"?keywords="+keyword);
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
