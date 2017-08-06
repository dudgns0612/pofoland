package com.hst.pofoland.biz.user.ctrl;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hst.pofoland.biz.category.service.impl.CategoryServiceImpl;
import com.hst.pofoland.biz.category.vo.CategoryVO;
import com.hst.pofoland.biz.user.service.impl.UserServiceImpl;
import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.auth.GoogleAuthentication;
import com.hst.pofoland.common.constnat.NetworkConstant;
import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.common.vo.ResponseVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.user.UserController.java
 * 클래스 설명 : User관련 Controller
 *
 * @author 김영훈
 * @since 2017. 7. 27.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 7. 27.		김영훈			최초생성
 * </pre>
 */

@Controller
public class UserController implements InitializingBean{
	
	@Inject
	UserServiceImpl userService;
	
	@Inject
	CategoryServiceImpl categoryService;
	
	@Inject
	GoogleAuthentication googleAuth;
	
	GoogleConnectionFactory googleConnectionFactory = null;
	OAuth2Parameters googleOAuth2Parameters = null;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		googleConnectionFactory = googleAuth.getGoogleConnectionFactory();
		googleOAuth2Parameters = googleAuth.getOauth2Parameter();
	}
	
	/**
	 * 유저 회원가입
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value="/user" , method=RequestMethod.POST)
	@ResponseBody
	public ResponseVO createUser(@ModelAttribute UserVO userVO) {
		
		LoggerManager.info(getClass(), userVO.toString());
		
		int code = userService.createUser(userVO);
		
		ResponseVO responseVO = new ResponseVO();
		
		if (code > 0) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
		}
		
		return responseVO;
	}
	
	
	/**
	 * 구글 가입 화면 추출
	 * @param response
	 */
	@RequestMapping(value="/google/login", method=RequestMethod.GET)
	public void googleLogin(HttpServletResponse response) {
		
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 구글 사용자 인증 확인
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/google/user", method=RequestMethod.GET)
	public void googleLoginCheck (HttpServletRequest request , HttpServletResponse response) throws IOException {

		String code = request.getParameter("code");
		
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code , googleOAuth2Parameters.getRedirectUri(), null);
		
		String accessToken = accessGrant.getAccessToken();
		Long expireTime = accessGrant.getExpireTime();
		if (expireTime != null && expireTime < System.currentTimeMillis()) {
			accessToken = accessGrant.getRefreshToken();
			LoggerManager.info(getClass(),"accessToken is expired. refresh token = {}", accessToken);
		}
		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
		
		PlusOperations plusOperations = google.plusOperations();
		Person person = plusOperations.getGoogleProfile();
		 
		Integer userSeq = userService.seqSearchUser(person.getAccountEmail());
		
		if (userSeq == null || userSeq < 0) {
			LoggerManager.info(getClass(), "Oauth회원가입 페이지");
		} else {
			UserVO userVO = userService.searchUser(userSeq);
			userVO.setUserProfileUrl(person.getImageUrl());
			
			HttpSession session = request.getSession();
			session.setAttribute("user", userVO);
			
			response.sendRedirect("/home");
		}
	}
	
	
	/**
	 * 유저 아이디중복확인
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/user/checkid/{userId}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseVO duplicateCheckId(@PathVariable String userId) {
		
		String checkId = userService.duplicateCheckId(userId);
		
		ResponseVO responseVO = new ResponseVO();
		if (checkId != null) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
		}
		
		return responseVO;
	}
	
	/**
	 * 유저 닉네임중복확인
	 * @param userNick
	 * @return
	 */
	@RequestMapping(value="/user/checknick/{userNick}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseVO duplicateCheckNick(@PathVariable String userNick) {
		
		String checkNick = userService.duplicateCheckNick(userNick);
		
		ResponseVO responseVO = new ResponseVO();
		if (checkNick != null) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
		}
		
		return responseVO;
	}
	
	/**
	 * 유저 정보조회
	 * @param userSeq
	 * @return
	 */
	@RequestMapping(value="/user/{userSeq}" , method=RequestMethod.GET)
	@ResponseBody
	public ResponseVO searchUser(@PathVariable Integer userSeq) {
		
		UserVO userVO = userService.searchUser(userSeq);
		
		ResponseVO responseVO = new ResponseVO();
		responseVO.setData(userVO);

		if (userVO != null) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
		}
		
		return responseVO;
	}
	
	/**
	 * 유저 메일인증처리
	 * @param userAuthKey
	 * @param userSeq
	 * @return
	 */
	@RequestMapping(value="/user/{userSeq}/auth/{userAuthKey}" , method=RequestMethod.GET)
	public void authProcessUser(@PathVariable String userAuthKey ,@PathVariable Integer userSeq) {
		
		UserVO userVO = new UserVO();
		userVO.setUserAuthKey(userAuthKey);
		userVO.setUserSeq(userSeq);
		
		int code = userService.authProcessUser(userVO);
		
		if (code == NetworkConstant.COMMUNICATION_FAIL_CODE) {
			//비성공 처리 다시 보내든가 함.
		} 
	}
	
	/**
	 * 유저 인증상태 확인
	 * @param userSeq
	 * @return
	 */
	@RequestMapping(value="/user/checkauth/{userSeq}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseVO authCheckUser(@PathVariable Integer userSeq) {
		
		UserVO userVO = userService.authCheckUser(userSeq);
		char userAuthYn = userVO.getUserAuthYn();
		ResponseVO responseVO = new ResponseVO();
		if(userAuthYn == 'Y') {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
			responseVO.setData(userVO);
		} 
		
		return responseVO;
	}
	
	/**
	 * 추가정보 입력 페이지 이동
	 * @return
	 */
	@RequestMapping(value="/user/addInfo/{userSeq}", method=RequestMethod.GET)
	public ModelAndView addInfoPage(@PathVariable Integer userSeq) {
		
		List<CategoryVO> categoryList = categoryService.getJobCategoryList();
		
		ModelAndView mav = new ModelAndView("추가입력 페이지");
		
		mav.addObject("jobList", categoryList);
		mav.addObject("userSeq", userSeq);
		
		return mav;
	}
	
	@RequestMapping(value="/user/addinformaion", method=RequestMethod.POST)
	public ResponseVO addInfoUser(@ModelAttribute UserVO userVO) {
		
		Integer nickResult = userService.addInfoUser(userVO);
		
		ResponseVO responseVO = new ResponseVO();
		
		return responseVO;
	}
	
}
