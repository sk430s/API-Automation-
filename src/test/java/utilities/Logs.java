package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Logs {

	public static Logger getLogger(String className) {
		System.out.println(System.getProperty("user.dir"));
		String  log4j_Prop_Path = System.getProperty("user.dir") +"/src/test/resources/properties/log4j.properties";
	    PropertyConfigurator.configure(log4j_Prop_Path); 
		return Logger.getLogger(className);
	}
}
