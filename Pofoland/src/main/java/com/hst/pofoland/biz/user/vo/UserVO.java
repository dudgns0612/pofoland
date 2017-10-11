package com.hst.pofoland.biz.user.vo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hst.pofoland.common.auth.security.SecurityRole;
import com.hst.pofoland.common.vo.BaseVO;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.biz.user.UserVO.java
 * 클래스 설명 : User정보를 담고 있는 VO class
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
 * 2017. 7. 31.     김영훈			인증검사에 따른 수정
 * </pre>
 */

public class UserVO extends BaseVO implements UserDetails {
	private static final long serialVersionUID = 1L;
	Integer userSeq = null;
	String userId = "";
	String userPw = "";
	String userNick = "";
	String userEmail = "";
	String userAuthKey = "";
	char userAuthYn = 'N';
	Integer userScore = 0;
	String userRegDt = "";
	String userProfileUrl = "";
	char userDelYn = 'N';
	char userPublicYn = 'Y';
	char userLoginYn = 'N';
	Integer[] jobCate = {};
	
	//생성자
	public UserVO() {
	}
	
	//Security
	private List<SecurityRole> authorities;
	
	/**
	 * @return the userSeq
	 */
	public Integer getUserSeq() {
		return userSeq;
	}
	/**
	 * @param userSeq the userSeq to set
	 */
	public void setUserSeq(Integer userSeq) {
		this.userSeq = userSeq;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userPw
	 */
	public String getUserPw() {
		return userPw;
	}
	/**
	 * @param userPw the userPw to set
	 */
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	/**
	 * @return the userNick
	 */
	public String getUserNick() {
		return userNick;
	}
	/**
	 * @param userNick the userNick to set
	 */
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the userAuthKey
	 */
	public String getUserAuthKey() {
		return userAuthKey;
	}
	/**
	 * @param userAuthKey the userAuthKey to set
	 */
	public void setUserAuthKey(String userAuthKey) {
		this.userAuthKey = userAuthKey;
	}
	/**
	 * @return the userAuthYn
	 */
	public char getUserAuthYn() {
		return userAuthYn;
	}
	/**
	 * @param userAuthYn the userAuthYn to set
	 */
	public void setUserAuthYn(char userAuthYn) {
		this.userAuthYn = userAuthYn;
	}
	/**
	 * @return the userScore
	 */
	public Integer getUserScore() {
		return userScore;
	}
	/**
	 * @param userScore the userScore to set
	 */
	public void setUserScore(Integer userScore) {
		this.userScore = userScore;
	}
	/**
	 * @return the userRegDt
	 */
	public String getUserRegDt() {
		return userRegDt;
	}
	/**
	 * @param userRegDt the userRegDt to set
	 */
	public void setUserRegDt(String userRegDt) {
		this.userRegDt = userRegDt;
	}
	/**
	 * @return the userDelYn
	 */
	public char getUserDelYn() {
		return userDelYn;
	}
	/**
	 * @param userDelYn the userDelYn to set
	 */
	public void setUserDelYn(char userDelYn) {
		this.userDelYn = userDelYn;
	}
	/**
	 * @return the userPublicYn
	 */
	public char getUserPublicYn() {
		return userPublicYn;
	}
	/**
	 * @param userPublicYn the userPublicYn to set
	 */
	public void setUserPublicYn(char userPublicYn) {
		this.userPublicYn = userPublicYn;
	}
	/**
	 * @return the userLoginYn
	 */
	public char getUserLoginYn() {
		return userLoginYn;
	}
	/**
	 * @param userLoginYn the userLoginYn to set
	 */
	public void setUserLoginYn(char userLoginYn) {
		this.userLoginYn = userLoginYn;
	}
	
	public String getUserProfileUrl() {
		return userProfileUrl;
	}
	
	public void setUserProfileUrl(String userProfileUrl) {
		this.userProfileUrl = userProfileUrl;
	}
	
	public Integer[] getJobCate() {
		return jobCate;
	}
	
	public void setJobCate(Integer[] jobCate) {
		this.jobCate = jobCate;
	}

	@Override
	public String toString() {
		return "UserVO [userSeq=" + userSeq + ", userId=" + userId + ", userPw=" + userPw + ", userNick=" + userNick
				+ ", userEmail=" + userEmail + ", userAuthKey=" + userAuthKey + ", userAuthYn=" + userAuthYn
				+ ", userScore=" + userScore + ", userRegDt=" + userRegDt + ", userProfileUrl=" + userProfileUrl
				+ ", userDelYn=" + userDelYn + ", userPublicYn=" + userPublicYn + ", userLoginYn=" + userLoginYn
				+ ", jobCate=" + Arrays.toString(jobCate) + "]";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return this.authorities;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userPw;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<SecurityRole> authorities) {
		this.authorities = authorities;
	}
}
