package com.hst.pofoland.common.utils;

import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.InitializingBean;

public class MailSendUtils implements InitializingBean{
	
	private Session session = null;
	private String user = "";
	
	@Inject
	Configuration config;

	/**
	 * 기본생성자
	 */
	public MailSendUtils() {
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		session = mailSenderSetting();
	}
	
	public Session mailSenderSetting(){
		// smtp host정보와 관련 보낸이 관리자 메일정보
		// smtp.gmail.com 구글
		// smtp.naver.com 네이버
		String host = config.getString("network.email.host");
		user = config.getString("network.email.administratorMail");
		final String password = config.getString("network.email.administratorMailPw");
		
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		
		//관리자 메일정보 확인
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		return session;
	}
	
	/**
	 * 메일 전송
	 * @param userEmail
	 * @param title
	 * @param content
	 */
	public void sendEmail(String userEmail, String title, StringBuffer content) {
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			message.setSubject(title);
			message.setContent(content.toString(),"text/html;charset=UTF-8");
			Transport.send(message);
			
		} catch (AddressException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e);
		} catch (MessagingException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e);
		}
		
		LoggerManager.info(getClass(), "User Mail : "+userEmail+" Success Mail");
	}
}
