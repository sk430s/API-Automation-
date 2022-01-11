package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI;

public class TestClose extends BaseTestSpecs{
	
	//String ticketnum="";

	@Test(description = "Test Close REST API ET\n")
	public void testRESTTicketClose_ET() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "ET_Close.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
	//	String jsonString = response.asString();
	//	System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
		String ID = jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
		System.out.println("\nRequest ID is : " + ID);
		
		System.out.println("\n\n**** ET Ticket Closed ****");
		
	    
	}
	
	@Test(description = "Test Close REST API EM\n")
	public void testRESTTicketClose_EM() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "EM_Close_Ticket.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
	//	String jsonString = response.asString();
	//	System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
		String ID = jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
		System.out.println("\nRequest ID is : " + ID);
		
		System.out.println("\n\n**** EM Ticket Closed ****");
		
	    
	}
	
	
}
