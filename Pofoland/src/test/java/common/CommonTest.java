package common;

import org.junit.Test;

import com.hst.pofoland.common.auth.MailAuthentication;

public class CommonTest {
	
	@Test
	public void Main() {
		MailAuthentication mailAuth = new MailAuthentication("dudgns0612@gmail.com", "");
		
		mailAuth.sendAuthMail();
	}
}
