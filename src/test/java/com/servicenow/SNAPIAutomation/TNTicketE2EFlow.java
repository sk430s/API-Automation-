package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.RequestPayLoad;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import utilities.RestAPI;

public class TNTicketE2EFlow extends BaseTestSpecs {
	
	RequestPayLoad req = new RequestPayLoad();
	String caseNumber="";
	 RestAPI restAPI;
	

	@DataProvider(name = "TelePhoneNumbers")
	  public static Object[][] credentials() {
	        
	        return new Object[][] { { "18472581905" }, { "14082256502" },{"12168447367"},{"18472581795"}};
	  }


	@Test(description = "Createcase for EM TelePhoneNumber Ticket",dataProvider="TelePhoneNumbers")
	public void createCasephoneNum(String phoneNumber) throws IOException {


		
		  restAPI = new RestAPI(); 
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
	
	@Test(description = "Log Update for EM TelePhoneNumber Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.TNTicketE2EFlow.createCasephoneNum"},dataProvider="TelePhoneNumbers")
	
	public void logUpdate(String phoneNumber) throws IOException {


		
		   restAPI = new RestAPI(); 
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
	
	@Test(description = "Case Escalation for EM TelePhoneNumber Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.TNTicketE2EFlow.logUpdate"},dataProvider="TelePhoneNumbers")
	public void caseEscalation(String phoneNumber) throws IOException {


		
		   restAPI = new RestAPI(); 
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
	@Test(description = "Case closure for EM TelePhoneNumber Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.TNTicketE2EFlow.caseEscalation"},dataProvider="TelePhoneNumbers")
	public void caseClosure(String phoneNumber) throws IOException {
		   restAPI = new RestAPI(); 
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
