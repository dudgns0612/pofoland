package com.hst.pofoland.biz.user.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hst.pofoland.biz.user.dao.UserDAO;
import com.hst.pofoland.biz.user.service.UserService;
import com.hst.pofoland.biz.user.vo.UserVO;
import com.hst.pofoland.common.auth.Ase128Encrypt;
import com.hst.pofoland.common.auth.security.SecurityAuthorityManager;
import com.hst.pofoland.common.constnat.NetworkConstant;
import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.common.utils.MailSendUtils;
import com.hst.pofoland.common.utils.StringUtils;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.user.UserService.java
 * 클래스 설명 : User에 관한 Service 역활 class
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
 * 2017. 7. 31.     김영훈			메일인증추가
 * </pre>
 */

@Service
public class UserServiceImpl implements UserService , UserDetailsService{
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	MailSendUtils mailSendUtils;
	
	@Inject
	Ase128Encrypt ase128Encrypt;
	/**
	 * 생성자
	 */
	public UserServiceImpl() {
	}
	
	/**
	 * 유저 회원가입 서비스
	 * @param userVO
	 * @return
	 */
	@Override
	public UserVO createUser(UserVO userVO) {
		
		userVO.setUserAuthKey(getAuthKey());
		
		StandardPasswordEncoder spEncoder = new StandardPasswordEncoder();
		String userPw = spEncoder.encode(userVO.getPassword());
		userVO.setUserPw(userPw);
		
		Integer result = userDAO.insertUser(userVO);
		
		if (result > 0) {
			Integer userSeq = userDAO.selectUserSeq(userVO.getUserId());
			
			//등록된 유저번호 입력
			userVO.setUserSeq(userSeq);
			
			String userEmail = userVO.getUserEmail();
			String userAuthKey = userVO.getUserAuthKey();
			
			//인증 키 ase128인코딩
			ase128Encrypt.createEncryptKey(NetworkConstant.ENCRYPTION_MAILAUTH_KEY);
			userAuthKey = ase128Encrypt.encode(userAuthKey);
			
			LoggerManager.info(getClass(), "SEND AUTH KEY : {}", userAuthKey);
			
			String title = "[Pofoland]본인인증관련";
			StringBuffer content = new StringBuffer("<h2>안녕하세요. Pofoland입니다.</h2><br/><br/>");
			
			try {
				content.append("<h4>회원가입에 대하여 간단한 본인인증을 위하여 아래의 링크를 클릭하여주세요.</h4>");
				content.append("<h4>감사합니다.</h4><br/><br/>");
				content.append("<a href='http://localhost:8080/user/mail/auth?userSeq="+userSeq+"&userAuthKey="+URLEncoder.encode(userAuthKey,"UTF-8")+"'>본인인증</a>");
				content.append(" 클릭 후 메인페이지로 이동합니다.");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			mailSendUtils.sendEmail(userEmail, title, content);
		}
		
		return userVO;
	}
	
	
	
	/**
	 * 유저 아이디 중복확인
	 * @param userId
	 * @return
	 */
	@Override
	public String duplicateCheckId(String userId) {
		
		String checkId = userDAO.selectDuplicateCheckId(userId);
		
		return checkId;
	}
	
	/**
	 * 유저 시퀀스 조회
	 */
	@Override
	public Integer seqSearchUser(String userId) {
		
		Integer userSeq = userDAO.selectUserSeq(userId);
		
		return userSeq;
	}
	
	/**
	 * 유저 닉네임 중복확인
	 * @param userNick
	 * @return
	 */
	@Override
	public String duplicateCheckNick(String userNick) {
		
		String checkNick = userDAO.selectDuplicateCheckNick(userNick);
		
		return checkNick;
	}

	/**
	 * 유저 이메일 중복확인
	 * @param userEmail
	 * @return
	 */
	@Override
	public String duplicateCheckEmail(String userEmail) {
		
		String checkEmail = userDAO.selectDuplicateCheckEmail(userEmail);
		
		return checkEmail;
	}
	
	/**
	 * 유저 정보조회
	 * @param userSeq
	 * @return
	 */
	@Override
	public UserVO searchUser(Integer userSeq) {
		UserVO userVO = new UserVO();
		userVO.setUserSeq(userSeq);
		
		userVO = userDAO.selectUserInfo(userVO);
		
		return userVO;
	}
	
	/**
	 * 유저 아이디 찾기
	 */
	@Override
	public UserVO findIdUser(UserVO userVO) {
		String userEmail = userDAO.selectDuplicateCheckEmail(userVO.getUserEmail());
		
		//존재하는 이메일 확인
		if (userEmail != null && userEmail != "") {
			userVO = userDAO.selectFindUserInfo(userVO);
			
			if (userVO != null) {
				String title = "[Pofoland]아이디 찾기";
				StringBuffer content = new StringBuffer();
				content.append("<h2>안녕하세요. Pofoland입니다.</h2><br/><br/>");
				content.append("귀하의 아이디는 '"+userVO.getUserId()+"' 입니다.");
				content.append("즐거운 하루 되세요. 감사합니다.");
				
				mailSendUtils.sendEmail(userEmail, title, content);
			}
		} 
		return userVO;
	}
	
	public UserVO findPwUser(UserVO userVO) {
		String userEmail = userDAO.selectCheckIdEmail(userVO);
		
		//존재하는 유저 정보일 경우
		if (userEmail != null && userEmail != "") {
			StringBuffer tempPw = new StringBuffer();
			Random random = new Random();
			
			//8자리 숫자/영어 조합
			for(int i = 0 ; i < 8 ; i++) {
				if(random.nextBoolean()){
					tempPw.append((char)(random.nextInt(26)+65));
		        }else{
		        	tempPw.append((random.nextInt(10)));
		        }
			}
			
			//임시비밀번호 생성 및 인코딩SHA256(security인증방식)
			StandardPasswordEncoder spEncoder = new StandardPasswordEncoder();
			String userPw = spEncoder.encode(tempPw.toString());
			
			userVO.setUserPw(userPw);
			
			//임시비밀번호로 비밀번호 업데이트
			Integer isUpdatePw = userDAO.updatePasswordUser(userVO);
			
			if (isUpdatePw > 0) {
				String title = "[Pofoland]임시비밀번호 안내";
				StringBuffer content = new StringBuffer();
				content.append("<h2>안녕하세요. Pofoland입니다.</h2><br/><br/>");
				content.append("귀하의 임시비밀번호는 '"+tempPw.toString()+"' 입니다.<br/>");
				content.append("비밀번호 수정 후 사용하여 주세요. <br/>");
				content.append("즐거운 하루 되세요. 감사합니다.");
				
				mailSendUtils.sendEmail(userEmail, title, content);
			} else {
				userVO = null;
			}
		} else {
			userVO = null;
		}
		return userVO;
	}
	
	/**
	 * 유저 허가인증
	 */
	@Override
	public Integer authProcessUser(UserVO userVO) {
		
		Integer result = userDAO.updateAuthState(userVO);
		
		return result;
	}
	
	/**
	 *  유저메일인증 체크
	 */
	@Override
	public UserVO authCheckUser(Integer userSeq) {
		
		UserVO userVO = userDAO.selectAuthState(userSeq);
		
		return userVO;
	}
	
	/**
	 * 유저 추가정보 등록
	 */
	@Override
	public Integer addInfoUser(UserVO userVO) {
		
		Integer nickResult = userDAO.updateAddInfo(userVO);
		if(userVO.getInterestCode().length > 0) {
			userDAO.insertInterestInfo(userVO);
		}
		
		return nickResult;
	}
	
	/**
	 * userAuthKey 발급
	 * @return
	 */
	public String getAuthKey() {
		return StringUtils.random();
	}
	
	/**
	 * OAuth 사용자 회원가입
	 */
	@Override
	public UserVO createOauthUser(UserVO userVO) {
		
		Integer result = userDAO.insertOauthUser(userVO);
		
		if (result > 0) {
			Integer userSeq = userVO.getUserSeq();
			if(userVO.getInterestCode().length > 0 && userSeq > 0) {
				userDAO.insertInterestInfo(userVO);
			}
		}
		
		return userVO;
	}
	
	/**
	 * 유저 정보 수정
	 */
	@Override
	public Integer modifyUser(UserVO userVO) {
		return userDAO.updateModifyUser(userVO);
	}
	
	/**
	 * 유저 탈퇴
	 */
	@Override
	public Integer dropUser(UserVO userVO) {
		return userDAO.updateDropUser(userVO);
	}
	
	/**
	 * 유저 로그인 상태 수정
	 * USER_DEL_YN  :  삭제유무
	 */
	@Override
	public Integer loginStateUser(UserVO userVO) {
		return userDAO.updateLoginState(userVO);
	}
	
	/**
	 * 유저 패스워드 수정
	 */
	@Override
	public Integer modifyUserPssword(UserVO userVO) {
		return userDAO.updatePasswordUser(userVO);
	}
	
	/**
	 * Security 인증 확인
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		UserVO userVO = userDAO.selectUserLogin(userId);

		if (userVO == null) {
			throw new UsernameNotFoundException("userId");
		}
		
		userVO.setUserId(userId);
		userVO.setUserPw(userVO.getPassword());
		
		//User 권한부여
		SecurityAuthorityManager authManager = new SecurityAuthorityManager();
		authManager.setAuthorityList("ROLE_USER");
		userVO.setAuthorities(authManager.getAuthorityList());
		
		return userVO;
	}
}
