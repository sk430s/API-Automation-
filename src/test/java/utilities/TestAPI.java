package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.Hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class TestAPI extends BaseAPISpecs {
	Properties config = null;
	 
	
	public TestAPI() throws Exception {
		Hooks.initialize();
		config = Hooks.config_prop;
	}
	
/*	@Test
	public void test1() throws IOException  {
		
		RestAPI restAPI = new RestAPI();
		String fileName = "createTicket.json";
		String EndPoint = config.getProperty("baseUrl")+config.getProperty("createTicket");
		Response response = restAPI.postRestAPI(EndPoint,fileName);
        String jsonString = response.asString();
        int ID = JsonPath.from(jsonString).get("ticket.id");
        System.out.println("ID is : "+ID);
	}
*/
	@Test
	public void test2() throws IOException  {
		
		RestAPI restAPI = new RestAPI();
		String EndPoint = config.getProperty("gitURL");
		Response response = restAPI.getRestAPI(EndPoint);
        String jsonString = response.asString();
        System.out.println(jsonString);
        
	}
	

}
