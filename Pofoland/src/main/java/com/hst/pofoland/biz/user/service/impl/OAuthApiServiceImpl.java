package com.hst.pofoland.biz.user.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.configuration.Configuration;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hst.pofoland.biz.user.service.OAuthApiService;
import com.hst.pofoland.common.utils.ApiUtils;
import com.hst.pofoland.common.utils.LoggerManager;


/**
 * 시스템명 : 포트폴리오 관리 시스템
* $com.hst.pofoland.biz.user.OAuthApiServiceImpl.java
 * 클래스 설명 : OAuthLoginApi에 관한 Service 역활 class
 *
 * @author 김영훈
 * @since 2017. 9. 258
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 9. 28.		김영훈			최초생성
 * </pre>
 */

@Service
public class OAuthApiServiceImpl implements OAuthApiService{
	
	@Inject
	private Configuration config;
	
	/**
	 * 네이버 유저 프로필 조회
	 * @param accessToken
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getNaverUserInfo(String accessToken) {
		String header = "Bearer " + accessToken; // Bearer 다음에 공백 추가
		String apiURI = config.getString("network.naver.api.profileURI");
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", header);

		String responseBody = ApiUtils.urlConnResponseStr(apiURI, headerMap);
		ObjectMapper jsonMapper = new ObjectMapper();
		Map<String, Object> map = null;
		String userId = "";
		
		try {
			map = jsonMapper.readValue(responseBody,new TypeReference<Map<String, Object>>() {});
			LoggerManager.info(getClass(), "네이버 전체 유저 정보 {}", map.toString());
			map = (Map<String, Object>) map.get("response");
			
			LoggerManager.info(getClass(), "네이버로그인 유저 프로필 {}", map.toString());
			
			userId = String.valueOf(map.get("id"));
		} catch (JsonParseException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e.getMessage());
		} catch (JsonMappingException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e.getMessage());
		} catch (IOException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e.getMessage());
		}
		
		return userId;
	}
	
	/**
	 * 네이버 토근 갱신
	 * @param refreshToken
	 * @return
	 */
	@Override
	public String refreshNaverUserToken(String refreshToken) {
		String clientId = config.getString("network.naver.clientId");
		String secret = config.getString("network.naver.secret");
		String refreshTokenURI = config.getString("network.naver.api.refreshTokenURI");
		String accessToken = "";
		
		StringBuffer uriBuffer = new StringBuffer(refreshTokenURI);
		uriBuffer.append("&client_id=").append(clientId);
		uriBuffer.append("&client_secret=").append(secret);
		try {
			uriBuffer.append("&refresh_token=").append(URLEncoder.encode(refreshToken,"UTF-8"));
			
			String responseStr = ApiUtils.urlConnResponseStr(uriBuffer.toString());
			
			ObjectMapper jsonMapper = new ObjectMapper();
			Map<String, Object> map = jsonMapper.readValue(responseStr,new TypeReference<Map<String, Object>>() {});
			
			//새로 생성 받은 accessToken
			accessToken = String.valueOf(map.get("access_token"));
			
		} catch (IOException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e.getMessage());
		}
		
		return accessToken;
	}
	
	/**
	 * 네이버 토큰 삭제
	 * @param refreshToken
	 */
	@Override
	public void deleteNaverUserToken(String refreshToken) {
		String accessToken = refreshNaverUserToken(refreshToken);
		
		String clientId = config.getString("network.naver.clientId");
		String secret = config.getString("network.naver.secret");
		String deleteTokenURI = config.getString("network.naver.api.deleteTokenURI");
		
		StringBuffer uriBuffer = new StringBuffer(deleteTokenURI);
		uriBuffer.append("&client_id=").append(clientId);
		uriBuffer.append("&client_secret=").append(secret);
		try {
			uriBuffer.append("&access_token=").append(URLEncoder.encode(accessToken,"UTF-8"));
			uriBuffer.append("&service_provider=").append("NAVER");
			
			String responseStr = ApiUtils.urlConnResponseStr(uriBuffer.toString());
			
			ObjectMapper jsonMapper = new ObjectMapper();
			Map<String, Object> map = jsonMapper.readValue(responseStr,new TypeReference<Map<String, Object>>() {});
		} catch (IOException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e.getMessage());
		}
	}
}
