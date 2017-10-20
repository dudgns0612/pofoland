package com.hst.pofoland.viewer.vo;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.viewer.vo.ChannelVO.java
 * 클래스 설명 : 클라이언트 각 ChannelVO
 *
 * @author 김영훈
 * @since 2017. 10. 19.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 10. 19.	김영훈			최초생성
 * </pre>
*/


/**
 * ctx : 각 channel handler
 * userId : 관리자 아이디
 * userPw : 관리자 비밀번호
 * @author User
 *
 */
public class ChannelVO {
	public static List<ChannelVO> channelList = new ArrayList<ChannelVO>();
	ChannelHandlerContext ctx;
	String userId;
	String userPw;
	String workStateYn = "Y";
	/**
	 * @return the ctx
	 */
	public ChannelHandlerContext getCtx() {
		return ctx;
	}
	/**
	 * @param ctx the ctx to set
	 */
	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
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
	 * @return the workState
	 */
	public String getWorkStateYn() {
		return workStateYn;
	}
	/**
	 * @param workState the workState to set
	 */
	public void setWorkStateYn(String workStateYn) {
		this.workStateYn = workStateYn;
	}
	
	public static ChannelVO getChannelVO(ChannelHandlerContext ctx) {
		for (ChannelVO channelVO : channelList) {
			if (channelVO.getCtx() == ctx) {
				return channelVO;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChannelVO [ctx=" + ctx + ", userId=" + userId + ", userPw=" + userPw + ", workState=" + workStateYn + "]";
	}
	
}
