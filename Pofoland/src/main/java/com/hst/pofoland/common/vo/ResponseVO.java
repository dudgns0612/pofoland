package com.hst.pofoland.common.vo;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.vo.ResponseVO.java
 * 클래스 설명 : 공통으로 전송하는 data VO class
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

public class ResponseVO {
	
	Object data = null;
	Integer code = 0;
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseVO [data=" + data + ", code=" + code + "]";
	}
	
}
