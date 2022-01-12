package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.annotations.Test;

import constants.RTRRequestPayLoad;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import utilities.RestAPI;

public class RouterScenariosEM_ATTNOW extends BaseTestSpecs {
	
	RTRRequestPayLoad req = new RTRRequestPayLoad();
	String caseNumber="";
	
	@Test(description = "Create Router Ticket")
	public void createRouterTicket() throws IOException {

          RestAPI restAPI = new RestAPI(); 
		  String fileName =req.createReqBodyRTR("caseNumber"); 
		  String EndPoint = config.getProperty("createcaseRTR"); 
		  Response response =restAPI.postRestAPIwithStringBody(EndPoint, fileName); 
		  String jsonString =response.asString(); 
		  System.out.println(jsonString);
		  System.out.println(response.getStatusCode());
		  Assert.assertTrue(response.getStatusCode()==201); 
		  JsonPath jsonpath=response.jsonPath();
		  caseNumber=jsonpath.get("result[0].display_value");
		  Assert.assertEquals(caseNumber, jsonpath.get("result[0].display_value"));
	}



	@Test(description = "Log Update for RTR EM  Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.RouterScenariosEM_ATTNOW.createRouterTicket"})
	public void logUpdateRTR() throws IOException {

          RestAPI restAPI = new RestAPI(); 
		  String fileName =req.updateLogReqBodyRTR(caseNumber); 
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
	
	@Test(description = "Escalation Req for RTR EM  Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.RouterScenariosEM_ATTNOW.createRouterTicket"})
	public void escalationReqRTR() throws IOException {

          RestAPI restAPI = new RestAPI(); 
		  String fileName =req.caseEscalationReqBodyRTR(caseNumber); 
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
	

	@Test(description = "Closurecase Req for RTR EM  Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.RouterScenariosEM_ATTNOW.createRouterTicket"})
	public void closeReqRTR() throws IOException {

		  RestAPI restAPI = new RestAPI(); 
		  String fileName =req.closeReqBodyRTR(caseNumber); 
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
