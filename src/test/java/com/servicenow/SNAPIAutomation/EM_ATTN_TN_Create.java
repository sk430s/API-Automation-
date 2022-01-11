package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI;

public class EM_ATTN_TN_Create extends BaseTestSpecs{
	
	
	@Test(description = "Test Create REST API EM\n")
	public void testRESTTicketCreate_EM() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "ATT_EM_TN_Create.json";
		String EndPoint = config.getProperty("createEMTicketATTNOW");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		JsonPath jspath = response.jsonPath();
		
		//System.out.println("path: "+ jspath.get("Envelope.Body"));
		//System.out.println(jspath.getList("Envelope.Body"));
		
		
		String ID = jspath.get("result[3].display_value");
		
		System.out.println("**** EM Ticket Created ***\n");
		System.out.println("ATTNOW EM_TN  ID is : " + ID);
	}

}
