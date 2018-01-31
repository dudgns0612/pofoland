package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.inject.Inject;

import org.apache.commons.configuration.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hst.pofoland.common.utils.LoggerManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
        "classpath:/root-context.xml"
})
public class DBConnectionTest {
	
	@Inject
	private Configuration config;
	
	private static final Logger logger = LoggerFactory.getLogger(DBConnectionTest.class);
	
	@Test
	public void configurationTest() {
	    
	    String oneDepthConfig = config.getString("database.use-setting");
	    String twoDepthConfig = config.getString("database.settings.mysql-db.driverClassName");
	    String useVariableConfig = config.getString("database.driverClassName");
	    //${database.settings.${database.use-setting}.driverClassName}
	    logger.info("USE SETTING : {}", oneDepthConfig);
	    logger.info("DRIVER CLASS NAME : {}", twoDepthConfig);
	    logger.info("USEVARIABLE : {}", useVariableConfig);
	}
	
	//@Test
	public void dbConnectionTest() {
		
		String driver = config.getString("database.driverClassName");
		String url = config.getString("database.url");
		String username = config.getString("database.username");
		String password = config.getString("database.password");
		
		LoggerManager.info(getClass(), "{}", driver);
		LoggerManager.info(getClass(), "{}", url);
		LoggerManager.info(getClass(), "{}", username);
		LoggerManager.info(getClass(), "{}", password);
		
		try {
			Class.forName(driver);
			
			Connection conn =  DriverManager.getConnection(url, username, password);
			
			LoggerManager.info(getClass(), "커넥션 여부 : {}" , !conn.isClosed());
		} catch (SQLException e) {
			LoggerManager.info(getClass(), "커넥션 실패");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LoggerManager.info(getClass(), "커넥션성공");
	}
}
