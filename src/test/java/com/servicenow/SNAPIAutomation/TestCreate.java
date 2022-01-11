package com.servicenow.SNAPIAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI;

public class TestCreate extends BaseTestSpecs {
	
	
	@Test
		public void testRESTTicketCreate_EM() throws IOException {

		RestAPI restAPI = new RestAPI();
		Assert.assertEquals(false,true);
		String fileName = "EM_Create_ticket.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		
		String jsonString = response.asString();
		//System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size());

		String ID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
		
	
		System.out.println("**** EM Ticket Created ***\n");
		System.out.println("EM ID is : " + ID);
	}

	
	@Test
	//(retryAnalyzer= Analyser.RetryAnalyser.class)
	public void testRESTTicketCreate_ET() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "ET_Create_ticket.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		String jsonString = response.asString();
	//	System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size());
		String ID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
		System.out.println("\n**** ET Ticket Created ***\n");
		System.out.println("ET ID is : " + ID);
	}




	
	
}
