package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import common.Hooks;

public class CommonUtils {
	static String requestPath = Hooks.config_prop.getProperty("RequestFilePath");
	
	public static String readFile(String FileName) throws IOException {
		
		String fileName = requestPath+FileName;
		String content = new String(Files.readAllBytes(Paths.get(fileName)));
		return content;
		
	}

}
