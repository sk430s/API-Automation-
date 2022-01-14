package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import constants.RTRRequestPayLoad;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import utilities.RestAPI;

public class RouterScenariosEM_ATTNOW extends BaseTestSpecs {
	
	RTRRequestPayLoad req = new RTRRequestPayLoad();
	//String caseNumber="";
	
	
	
	
	@Test(description = "Create Router Ticket")
	public void createRouterTicket(ITestContext context) throws IOException {

          RestAPI restAPI = new RestAPI(); 
		  String fileName =req.createReqBodyRTR("caseNumber"); 
		  String EndPoint = config.getProperty("createcaseRTR"); 
		  Response response =restAPI.postRestAPIwithStringBody(EndPoint, fileName); 
		  String jsonString =response.asString(); 
		//  System.out.println(jsonString);
		//  System.out.println(response.getStatusCode());
		  Assert.assertTrue(response.getStatusCode()==201); 
		  JsonPath jsonpath=response.jsonPath();
		  
		 String caseNumber=jsonpath.get("result[0].display_value");
		  context.setAttribute("ID",caseNumber);
		  System.out.println("ID :"+ context.getAttribute("ID"));
	//	  Assert.assertEquals(caseNumber, jsonpath.get("result[0].display_value"));
	}



	@Test(description = "Log Update for RTR EM  Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.RouterScenariosEM_ATTNOW.createRouterTicket"},priority=1)
	public void logUpdateRTR(ITestContext context) throws IOException {

          System.out.println(context.getAttribute("ID"));
		  String Case = (String) context.getAttribute("ID");
		  RestAPI restAPI = new RestAPI();
		  //case = context.getCurrentTestResult().getTestContext().getAttribute("ID");
		  String fileName =req.updateLogReqBodyRTR(Case); 
		  String EndPoint = config.getProperty("createcaseRTR"); 
		  Response response =restAPI.postRestAPIwithStringBody(EndPoint, fileName); 
		//  String jsonString =response.asString(); 
		//  System.out.println(jsonString);
		//  System.out.println(response.getStatusCode());
		  Assert.assertTrue(response.getStatusCode()==201); 
		//  JsonPath jsonpath=response.jsonPath(); 
		//  String caseNumber=jsonpath.get("result[0].display_value");
		//  Assert.assertEquals(caseNumber, jsonpath.get("result[0].display_value"));
		 // Assert.assertEquals("updated", jsonpath.get("result[0].status"));
		 

	}
	
	@Test(description = "Escalation Req for RTR EM  Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.RouterScenariosEM_ATTNOW.createRouterTicket"},priority=2)
	public void escalationReqRTR(ITestContext context) throws IOException {

		String Case = (String) context.getAttribute("ID");
		  RestAPI restAPI = new RestAPI(); 
		  String fileName =req.caseEscalationReqBodyRTR(Case); 
		  String EndPoint = config.getProperty("createcaseRTR"); 
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
	

	@Test(description = "Closurecase Req for RTR EM  Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.RouterScenariosEM_ATTNOW.createRouterTicket"},priority=3)
	public void closeReqRTR(ITestContext context) throws IOException {

		String Case = (String) context.getAttribute("ID");  
		RestAPI restAPI = new RestAPI(); 
		  String fileName =req.closeReqBodyRTR(Case); 
		  String EndPoint = config.getProperty("createcaseRTR"); 
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
