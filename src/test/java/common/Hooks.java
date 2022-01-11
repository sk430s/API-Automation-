package common;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.util.PropertiesPropertySource;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.extentReports;

public class Hooks {
	
	public static Properties config_prop = null;



	
	public static void initialize() throws Exception {

		config_prop = new Properties();
		String configPath = "src/test/resources/properties/config.properties";
		config_prop.load(new FileInputStream(configPath));
	

}
	}
