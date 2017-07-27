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

public class MailAuthentication {

	private String email = null;
	private String authKey = null;
	
	
	public MailAuthentication(String email,String authKey) {
		this.email = email;
		this.authKey = authKey;
	}
	
	public void sendAuthMail() {
		String host = "smtp.naver.com";
		final String user = "dudgns0612";
		final String password = "dudgns153795!";
		
		String receiver = email;
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		
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
