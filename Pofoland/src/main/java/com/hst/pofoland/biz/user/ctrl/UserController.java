package com.hst.pofoland.biz.user.ctrl;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hst.pofoland.biz.category.service.impl.CategoryServiceImpl;
import com.hst.pofoland.biz.category.vo.CategoryVO;
import com.hst.pofoland.biz.file.vo.FileVO;
import com.hst.pofoland.biz.user.service.impl.OAuthApiServiceImpl;
import com.hst.pofoland.biz.user.service.impl.UserServiceImpl;
import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.auth.Ase128Encrypt;
import com.hst.pofoland.common.auth.GoogleAuthentication;
import com.hst.pofoland.common.auth.security.SecurityAuthorityManager;
import com.hst.pofoland.common.constnat.NetworkConstant;
import com.hst.pofoland.common.utils.ApiUtils;
import com.hst.pofoland.common.utils.FileUtils;
import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.common.view.ImageView;
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
	OAuthApiServiceImpl oauthApiService;
	
	@Inject
	CategoryServiceImpl categoryService;

	@Inject
	Ase128Encrypt ase128Encrypt;
	
	@Inject
	GoogleAuthentication googleAuth;
	
	@Inject
    private FileUtils fileUtil;
	
    @Inject
    private Configuration config;
	
    private final String DEFUALT_FILE_NAME = "default_profile.jpg"; 
    
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
	public ResponseVO createUser(@ModelAttribute UserVO userVO , HttpServletResponse response) {
		
		userVO = userService.createUser(userVO);
		ResponseVO responseVO = new ResponseVO();
		
		if (userVO.getUserSeq() > 0) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
			responseVO.setData(userVO);
		}
		
		return responseVO;
	}
	
	/**
	 * 네이버 로그인 화면 추출
	 * @param response
	 */
	@RequestMapping(value="/naver/login", method=RequestMethod.GET)
	public void naverLogin(HttpServletResponse response) {
		try {
			String clientId = config.getString("network.naver.clientId");
			String redirectURI = URLEncoder.encode(config.getString("network.naver.redirectURI"),"UTF-8");
			String apiURI = config.getString("network.naver.api.loginURI");
			
			StringBuffer uriBuffer = new StringBuffer(apiURI);
			uriBuffer.append("&client_id=").append(clientId);
			uriBuffer.append("&redirect_uri=").append(redirectURI);
			
			System.out.println("TODO" + uriBuffer.toString());
			
			response.sendRedirect(uriBuffer.toString());
		} catch (IOException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e.getMessage());
		}
	}
	
	/**
	 * 네이버 사용자 인증 확인
	 * @param request
	 * @param response
	 * @param code
	 */
	@RequestMapping(value="/naver/user", method=RequestMethod.GET)
	public void naverCallback(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) {
		
		String clientId = config.getString("network.naver.clientId");
		String secret = config.getString("network.naver.secret");
		String apiURI = config.getString("network.naver.api.tokenURI");
		String redirectURI = "";
		
		
		try {
			StringBuffer uriBuffer = new StringBuffer(apiURI);
			redirectURI = URLEncoder.encode(redirectURI, "UTF-8");
			uriBuffer.append("&client_id=").append(clientId);
			uriBuffer.append("&client_secret=").append(secret);
			uriBuffer.append("&redirect_uri=").append(redirectURI);
			uriBuffer.append("&code=").append(code);
			
			String responseBody = ApiUtils.urlConnResponseStr(uriBuffer.toString());
		
			ObjectMapper jsonMapper = new ObjectMapper();
			Map<String, Object> map = jsonMapper.readValue(responseBody,new TypeReference<Map<String, Object>>() {});
			
			String userId = oauthApiService.getNaverUserInfo(String.valueOf(map.get("access_token")));
			Integer userSeq = userService.seqSearchUser(userId);
			
			if (userSeq == null || userSeq < 0) {
				HttpSession session = request.getSession();
				
				UserVO userVO = new UserVO();
				userVO.setUserId(userId);
				session.setAttribute("user",userVO);
				
				response.sendRedirect("/join/oAuth/step1");
			} else {
				UserVO userVO = userService.searchUser(userSeq);
				
				//security 권한설정
				SecurityAuthorityManager authManager = new SecurityAuthorityManager();
				authManager.setAuthorityList("ROLE_USER");
				userVO.setAuthorities(authManager.getAuthorityList());
				
				//security 인증처리
				Authentication authentication = new UsernamePasswordAuthenticationToken(userVO.getUserId(), userVO.getPassword(),userVO.getAuthorities());
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);
				
				HttpSession session = request.getSession();
				session.setAttribute("user", userVO);

				response.sendRedirect("/home");
			}
		} catch (IOException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e.getMessage());
		}
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
		String googleId = person.getAccountEmail();
		
		Integer userSeq = userService.seqSearchUser(googleId);
		
		if (userSeq == null || userSeq < 0) {
			HttpSession session = request.getSession();
			
			UserVO userVO = new UserVO();
			userVO.setUserId(googleId);
			session.setAttribute("user",userVO);
			
			response.sendRedirect("/join/oAuth/step1/F");
		} else {
			UserVO userVO = userService.searchUser(userSeq);
			
			//security 권한설정
			SecurityAuthorityManager authManager = new SecurityAuthorityManager();
			authManager.setAuthorityList("ROLE_USER");
			userVO.setAuthorities(authManager.getAuthorityList());
			
			//security 인증처리
			Authentication authentication = new UsernamePasswordAuthenticationToken(userVO.getUserId(), userVO.getPassword(),userVO.getAuthorities());
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			
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
		System.out.println("userId: " + userId);
		String checkId = userService.duplicateCheckId(userId);
		
		ResponseVO responseVO = new ResponseVO();
		if (checkId != null) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
		}
		
		return responseVO;
	}
	
	/**
	 * 유저 닉네임 중복확인
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
	 * 유저 이메일 중복확인
	 * @param userEmail
	 * @return
	 */
	@RequestMapping(value="/user/checkemail", method=RequestMethod.GET)
	@ResponseBody
	public ResponseVO duplicateCheckEmail(String userEmail) {
		
		String checkEmail = userService.duplicateCheckEmail(userEmail);
		
		ResponseVO responseVO = new ResponseVO();
		if (checkEmail != null) {
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
	public ModelAndView authProcessUser(@PathVariable String userAuthKey ,@PathVariable Integer userSeq) {
		
		UserVO userVO = new UserVO();
		ase128Encrypt.createEncryptKey(NetworkConstant.ENCRYPTION_MAILAUTH_KEY);
		userAuthKey = ase128Encrypt.decode(userAuthKey);
		userVO.setUserAuthKey(userAuthKey);
		userVO.setUserSeq(userSeq);
		
		int code = userService.authProcessUser(userVO);
		
		ModelAndView mav = new ModelAndView("/user/mailAuthResult");
		
		if (code == NetworkConstant.COMMUNICATION_SUCCESS_CODE) {
			mav.addObject("resultMsg", "메일 인증에 성공하셨습니다. 계속하여 가입해주세요.!");
			
		} else {
			mav.addObject("resultMsg", "메일 인증에 실패하셨씁니다. 다시 시도하여 주세요.!");
		}
		mav.addObject("code", code);
		
		return mav;
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
		
		ModelAndView mav = new ModelAndView("user/joinStep3");
		
		mav.addObject("jobList", categoryList);
		mav.addObject("userSeq", userSeq);
		
		return mav;
	}
	
	/**
	 * 추가정보 등록
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value="/user/addinfo", method=RequestMethod.POST)
	@ResponseBody
	public ResponseVO addInfoUser(@ModelAttribute UserVO userVO, @ModelAttribute MultipartFile userProfile) {
		
		if (userProfile != null) {
			FileVO fileVO = fileUtil.parseMultipartFile(userProfile, "userProfile");
			
			String filePath = fileVO.getFilepath();
			String serverPath = fileVO.getFilenameExcludeDirectory();
			
			try {
				userProfile.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				LoggerManager.error(getClass(), "{}", e.getMessage());
			} catch (IOException e) {
				LoggerManager.error(getClass(), "{}", e.getMessage());
			}
			
			userVO.setUserProfileUrl(serverPath);
		}
		
		
		Integer nickResult = userService.addInfoUser(userVO);
		 
		ResponseVO responseVO = new ResponseVO();
		if (nickResult > 0) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
		}
		
		return responseVO;
	}
	
	/**
	 * OAuth 추가정보 입력 페이지 이동
	 * @return
	 */
	@RequestMapping(value="/user/oAuth/addInfo", method=RequestMethod.POST)
	public ModelAndView OauthAddInfoPage(@ModelAttribute UserVO userVO, HttpServletRequest request) {
		
		LoggerManager.info(getClass(), "oauth! : {}", userVO.toString());
		
		List<CategoryVO> categoryList = categoryService.getJobCategoryList();
		ModelAndView mav = new ModelAndView("user/joinStep3");
		
		HttpSession session = request.getSession();
		userVO = (UserVO)session.getAttribute("user");
		String userId = userVO.getUserId();
		
		if (userId != "" && userId != null) {
			mav.addObject("userId",userId);
		} else {
			//에러처리
		}
		
		mav.addObject("jobList", categoryList);
		
		return mav;
	}
	
	/**
	 * 인증처리 후 구글 사용자 회원가입
	 * @param userVO
	 * @return
	 */
	@RequestMapping(value="/user/oAuth" , method=RequestMethod.POST)
	@ResponseBody
	public ResponseVO addOauthInfoUser(@ModelAttribute UserVO userVO) {
		ResponseVO responseVO = new ResponseVO();
		userVO = userService.createOauthUser(userVO);

		if (userVO.getUserSeq() > 0) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
			responseVO.setData(userVO);
		}
		
		return responseVO;
	}
	
	/**
	 * 유저 로그아웃
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/logout", method=RequestMethod.GET)
	public void userLogout(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("=======TODO=====");
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");
		Integer result = userService.loginStateUser(userVO);
		
		if (result > 0 && session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		
		try {
			response.sendRedirect("/home");
		}catch (Exception e) {
			LoggerManager.error(getClass(), "ERROR : {}", e.getMessage());
		}
	}
	
	/**
	 * 유저프로필 이미지 URL 조회
	 * @param userSeq
	 * @return
	 */
	@RequestMapping(value="/user/{userSeq}/image", method=RequestMethod.GET)
	public ImageView searchUserProfile(@PathVariable Integer userSeq) {
		String storedFileName = userService.searchUser(userSeq).getUserProfileUrl();
		
		String directoty = config.getString("upload.dirNames.userProfile");
		
		return new ImageView(directoty,storedFileName);
	}
	
	/**
	 * 유저 정보수정
	 * @param userVO
	 * @param userProfile
	 * @param fileCheck
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/modify", method=RequestMethod.POST)
	@ResponseBody
	public ResponseVO modifyUser(@ModelAttribute UserVO userVO,@ModelAttribute("userProfile") MultipartFile userProfile,@ModelAttribute("modifyFileCheck") String fileCheck,
			HttpServletRequest request) {
		ResponseVO responseVO = new ResponseVO();
		
		System.out.println("CHECK :" + fileCheck);
		
		if ("1".equals(fileCheck)) {
			if (userProfile != null) {
				FileVO fileVO = fileUtil.parseMultipartFile(userProfile, "userProfile");
				
				String filePath = fileVO.getFilepath();
				String serverPath = fileVO.getFilenameExcludeDirectory();
				
				try {
					userProfile.transferTo(new File(filePath));
				} catch (IllegalStateException e) {
					LoggerManager.error(getClass(), "{}", e.getMessage());
				} catch (IOException e) {
					LoggerManager.error(getClass(), "{}", e.getMessage());
				}
				
				userVO.setUserProfileUrl(serverPath);
			}
		} else if ("2".equals(fileCheck)) {
			userVO.setUserProfileUrl(DEFUALT_FILE_NAME);
		}
		
		Integer isResult = userService.modifyUser(userVO);
		
		if (isResult > 0) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
			userVO = userService.searchUser(userVO.getUserSeq());
			
			HttpSession session = request.getSession();
			session.setAttribute("user", userVO);
		}
		
		return responseVO;
	}
	
	/**
	 * 유저 비밀번호 수정
	 * @param oriPassword
	 * @param newPassword
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/modify/password" , method=RequestMethod.POST)
	@ResponseBody
	public ResponseVO modifyPwUser(@ModelAttribute("oriPassword") String oriPassword ,
			@ModelAttribute("newPassword") String newPassword, HttpServletRequest request) {
		StandardPasswordEncoder spEncoder = new StandardPasswordEncoder();
		ResponseVO responseVO = new ResponseVO();
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("user");
		String oriEncodePw = userVO.getUserPw();
		
		if (spEncoder.matches(oriPassword, oriEncodePw)) {
			String newEncodePw = spEncoder.encode(newPassword);
			userVO.setUserPw(newEncodePw);
			if(userService.modifyUserPssword(userVO) > 0) {
				session.setAttribute("user", userVO);
				responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
			}
		} 
		
		return responseVO;
	}
	
	/**
	 * 유저 탈퇴 처리
	 * @param userVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/drop" , method=RequestMethod.GET)
	@ResponseBody
	public ResponseVO dropUser(@ModelAttribute UserVO userVO, HttpServletRequest request) {
		ResponseVO responseVO = new ResponseVO();
		
		Integer result = userService.dropUser(userVO);
		
		if(result > 0) {
			responseVO.setCode(NetworkConstant.COMMUNICATION_SUCCESS_CODE);
			userService.loginStateUser(userVO);
			HttpSession session = request.getSession();
			session.removeAttribute("user");
		}
		
		return responseVO;
	}
	
}
