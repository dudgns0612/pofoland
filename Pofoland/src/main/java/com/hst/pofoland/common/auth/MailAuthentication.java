package com.hst.pofoland.common.auth;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.hst.pofoland.common.utils.LoggerManager;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.common.auth.MailAuthentication.java
 * 클래스 설명 : User 본인에 대한 메일인증처리 class 
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

public class MailAuthentication {

	private String email = null;
	private String authKey = null;
	
	
	public MailAuthentication(String email,String authKey) {
		this.email = email;
		this.authKey = authKey;
	}
	
	public void sendAuthMail() {
		// smtp host정보와 관련 보낸이 관리자 메일정보
		String host = "smtp.naver.com";
		final String user = "";
		final String password = "";
		
		String receiver = email;
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		
		//관리자 메일정보 확인
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			
			message.setSubject("[Pofoland]본인인증관련");
			
			message.setText("Simple mail test ..");
			
			StringBuffer msg = new StringBuffer("<h2>안녕하세요. Pofoland입니다.</h2><br/><br/>");
			
			msg.append("<h4>회원가입에 대하여 간단한 본인인증을 위하여 아래의 링크를 클릭하여주세요.</h4>");
			msg.append("<h4>감사합니다.</h4><br/><br/>");
			msg.append("<a href='http://localhost:8080'>본인인증</a>");
			msg.append(" 클릭 후 메인페이지로 이동합니다.");
			
			message.setContent(msg.toString(),"text/html;charset=UTF-8");
			
			Transport.send(message);
			LoggerManager.info(getClass(), "Success Auth Mail");
			
		} catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	
}