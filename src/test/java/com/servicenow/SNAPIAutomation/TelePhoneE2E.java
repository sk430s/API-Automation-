package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import constants.RequestPayLoad;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import utilities.RestAPI;

public class TelePhoneE2E  {
	
	

	@Factory(dataProvider="dp")
	public Object[] createInstances( String phno) {
		return new Object[] {new TestData(phno)};
	}
	
	@DataProvider(name="dp")
	public static Object[][] dataProvider() {
		Object[][] dataArray = {
				{ "18472581905"}
				
		};//{ "14082256502"}
		return dataArray;
	}
	
	

}

class TestData extends BaseTestSpecs{
	
private String phoneNm="";

RequestPayLoad req = new RequestPayLoad();
String caseNumber="";
 RestAPI restAPI;
	
	public TestData(String phoneNm) {
		
		this.phoneNm=phoneNm;
		
		}
	
	@Test(description = "Createcase for EM TelePhoneNumber Ticket")
	public void createCasephoneNum() throws IOException {


		
		  restAPI = new RestAPI(); 
		  String fileName =req.telePhoneNumRequestBody(phoneNm); 
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
	
	@Test(description = "Log Update for EM TelePhoneNumber Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.TNTicketE2EFlow.createCasephoneNum"})
	
	public void logUpdate() throws IOException {


		
		   restAPI = new RestAPI(); 
		  String fileName =req.logUpdateRequestBody(caseNumber,phoneNm); 
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
	
	@Test(description = "Case Escalation for EM TelePhoneNumber Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.TNTicketE2EFlow.logUpdate"})
	public void caseEscalation() throws IOException {


		
		   restAPI = new RestAPI(); 
		  String fileName =req.caseEscalationRequestBody(caseNumber,phoneNm); 
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
	@Test(description = "Case closure for EM TelePhoneNumber Ticket",dependsOnMethods= {"com.servicenow.SNAPIAutomation.TNTicketE2EFlow.caseEscalation"})
	public void caseClosure() throws IOException {
		   restAPI = new RestAPI(); 
		  String fileName =req.caseClosureRequestBody(caseNumber,phoneNm); 
		  String EndPoint = config.getProperty("caseClosure"); 
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

	
	
}
