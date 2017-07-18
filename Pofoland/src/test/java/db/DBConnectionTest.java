package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.common.utils.PropertyManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
		"file:src/main/resources/root-context.xml",
		"file:src/main/resources/spring/*.xml",
		"file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})

@WebAppConfiguration
public class DBConnectionTest {
	
	@Inject SqlSessionFactoryBean factoryBean;
	
	@Inject PropertyManager propertyManager;
	
	@Test
	public void propertiesTest() {
      Properties properties = propertyManager.getProperties();
      
      System.out.println("현재 운영체제 : " + System.getProperty("os.name"));
      
      System.out.println("DB URL : " + properties.get("jdbc.url"));
      System.out.println("개발 : " + properties.get("dev.image.path"));
      System.out.println("운영 : " + properties.get("operate.image.path"));
      
      System.out.println("현재 시스템 환경에 따른 이미지 경로 : " + properties.get("image.path"));
	}
	
//	@Test
//	public void dbConnectionTest() {
//		
//		PropertyManager propManager = new PropertyManager("db.server.properties");
//		
//		Properties prop = propManager.getProperties();
//		
//		String driver = prop.getProperty("jdbc.driverClassName");
//		String url = prop.getProperty("jdbc.url");
//		String username = prop.getProperty("jdbc.username");
//		String password = prop.getProperty("jdbc.password");
//		
//		try {
//			Class.forName(driver);
//			
//			Connection conn =  DriverManager.getConnection(url, username, password);
//			
//			LoggerManager.info(getClass(), "커넥션 여부 : {}" , !conn.isClosed());
//		} catch (SQLException e) {
//			LoggerManager.info(getClass(), "커넥션 실패");
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		LoggerManager.info(getClass(), "커넥션성공");
//	}
}
