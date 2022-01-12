package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.RequestPayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import utilities.RestAPI;

public class TelePhoneEM extends BaseTestSpecs {

	RequestPayLoad req = new RequestPayLoad();
	String caseNumber="";
	String phoneNumber="18472581905";
	@DataProvider(name = "TelePhoneNumbers")
	  public static Object[][] credentials() {
	        // The number of times data is repeated, test will be executed the same no. of times
	        // Here it will execute two times
	        return new Object[][] { { "+18472581905" }, { "+14082256502" }};
	  }

	@Test(description = "Create EM TelePhoneNumber Ticket",dataProvider="TelePhoneNumbers")
	public void createCasephoneNum(String phoneNumber) throws IOException {


		
		  RestAPI restAPI = new RestAPI(); 
		  String fileName =req.telePhoneNumRequestBody(phoneNumber); 
		  String EndPoint = config.getProperty("createTNTicket"); 
		  Response response =restAPI.postRestAPIwithStringBody(EndPoint, fileName); 
		  String jsonString =response.asString(); 
		  System.out.println(jsonString);
		  System.out.println(response.getStatusCode());
		  Assert.assertTrue(response.getStatusCode()==201); 
		  JsonPath jsonpath=response.jsonPath();
		  caseNumber=jsonpath.get("result[0].display_value");
		  Assert.assertEquals(caseNumber, jsonpath.get("result[0].display_value"));
		 

	}


	@Test(description = "Log Update for EM TelePhoneNumber Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.TelePhoneEM.createCasephoneNum"})
	public void logUpdate() throws IOException {


		
		  RestAPI restAPI = new RestAPI(); 
		  String fileName =req.logUpdateRequestBody(caseNumber,phoneNumber); 
		  String EndPoint = config.getProperty("createTNTicket"); 
		  Response response =restAPI.postRestAPIwithStringBody(EndPoint, fileName); 
		  String jsonString =response.asString(); 
		  System.out.println(jsonString);
		  System.out.println(response.getStatusCode());
		  Assert.assertTrue(response.getStatusCode()==201); 
		  JsonPath jsonpath=response.jsonPath(); 
		  String caseNumber=jsonpath.get("result[0].display_value");
		  Assert.assertEquals(caseNumber, jsonpath.get("result[0].display_value"));
		  Assert.assertEquals("updated", jsonpath.get("result[0].status"));
		 

	}
	@Test(description = "case Escalation for EM TelePhoneNumber Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.TelePhoneEM.createCasephoneNum"})
	public void caseEscalation() throws IOException {


		
		  RestAPI restAPI = new RestAPI(); 
		  String fileName =req.caseEscalationRequestBody(caseNumber,phoneNumber); 
		  String EndPoint = config.getProperty("creatCaseEscalation"); 
		  Response response =restAPI.postRestAPIwithStringBody(EndPoint, fileName); 
		  String jsonString =response.asString(); 
		  System.out.println(jsonString);
		  System.out.println(response.getStatusCode());
		  Assert.assertTrue(response.getStatusCode()==201); 
		  JsonPath jsonpath=response.jsonPath(); 
		  String caseNumber=jsonpath.get("result[0].display_value");
		  Assert.assertEquals(caseNumber, jsonpath.get("result[0].display_value"));
		  Assert.assertEquals("inserted", jsonpath.get("result[0].status"));
		 

	}
	
	@Test(description = "case closure for EM TelePhoneNumber Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.TelePhoneEM.createCasephoneNum"})
	public void caseClosure() throws IOException {
		  RestAPI restAPI = new RestAPI(); 
		  String fileName =req.caseClosureRequestBody(caseNumber,phoneNumber); 
		  String EndPoint = config.getProperty("caseClosure"); 
		  Response response =restAPI.postRestAPIwithStringBody(EndPoint, fileName); 
		  String jsonString =response.asString(); 
		  System.out.println(jsonString);
		  System.out.println(response.getStatusCode());
		  Assert.assertTrue(response.getStatusCode()==201); 
		  JsonPath jsonpath=response.jsonPath(); 
		  String caseNumber=jsonpath.get("result[0].display_value");
		  Assert.assertEquals(caseNumber, jsonpath.get("result[0].display_value"));
		  Assert.assertEquals("updated", jsonpath.get("result[0].status"));
	}
}
