package com.hst.pofoland.common.constant;

import java.io.PrintWriter;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.constant.NetworkConstant.java
 * 클래스 설명 : 네트워크 관련 공통 변수 및 메소드
 *
 * @author 김영훈
 * @since 2017. 8. 1.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 8. 1.		김영훈			최초생성
 * </pre>
*/


public class NetworkConstant {
	public static final Integer COMMUNICATION_SUCCESS_CODE = 1;
	public static final Integer COMMUNICATION_FAIL_CODE = 0;
	
	public static final String ENCRYPTION_MAILAUTH_KEY = "ASE128_MAIL_AUTH";
	
	public static void printWrite(PrintWriter pw ,String httpStr) {
		pw.write(httpStr.toString());
		pw.flush();
		pw.close();
	}
}
