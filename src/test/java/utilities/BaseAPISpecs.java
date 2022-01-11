package utilities;

import java.util.HashMap;
import java.util.Map;

import common.Hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseAPISpecs {
	
	public RequestSpecification givenBaseSpec() { 
		Map<String, String> headers = new HashMap<String, String>();
//		Map<String, String> body = new HashMap<>(); 
				return RestAssured.given() 
							.relaxedHTTPSValidation() 
							.accept(ContentType.JSON) 
							.contentType(ContentType.JSON) 
							.auth().basic(Hooks.config_prop.getProperty("usrName"), 
									Hooks.config_prop.getProperty("passWord")) 
							.headers(headers); 
	}
	
	public RequestSpecification givenBaseSpecHeader() { 
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "");
				return RestAssured.given() 
							.relaxedHTTPSValidation() 
							.accept(ContentType.JSON) 
							.contentType(ContentType.JSON) 
							.headers(headers); 
	}
	
	public RequestSpecification givenSOAPBaseSpec() { 
		Map<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "text/xml");
				return RestAssured.given() 
							.relaxedHTTPSValidation() 
							.accept(ContentType.XML) 
							.contentType(ContentType.XML) 
							.headers(headers); 
	}

}
