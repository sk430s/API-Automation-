package com.servicenow.SNAPIAutomation;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constants.RequestPayLoad;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import utilities.RestAPI;

public class TestPLcon  extends BaseTestSpecs{
	
	
	RequestPayLoad req = new RequestPayLoad();
	String caseNumber="";
	 RestAPI restAPI;


	@DataProvider(name = "DccCode")
	  public static Object[][] credentials() {

	        return new Object[][] { { "315007" }, { "315005" },{"315032"}};
	  }


	@Test(description = "Create Case ",dataProvider="DccCode")
	public void createCase(String phoneNumber) throws IOException {



		  restAPI = new RestAPI(); 
		  String fileName =req.createRequestBody(phoneNumber); 
		  String EndPoint = config.getProperty("createTNTicket"); 
		  Response response =restAPI.postRestAPIwithStringBody(EndPoint, fileName); 
		  String jsonString =response.asString(); 
		  System.out.println(response.getStatusCode());
			XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
			
			System.out.println(
					jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size());

			 caseNumber = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
			
			Assert.assertEquals(jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum"),caseNumber);
			System.out.println("****  Ticket Created ***\n");
			System.out.println(" ID is : " + caseNumber);


	}

}
