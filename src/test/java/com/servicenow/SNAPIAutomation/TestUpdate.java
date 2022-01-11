package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI;

public class TestUpdate extends BaseTestSpecs {
	
	@Test(description = "Test Update REST API ET")
	public void testRESTTicketUpdate_ET() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "Update_Ticket.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
		String ID = jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
		System.out.println("\n**** ET Ticket Updated ***\n");
		System.out.println("ET RequestID is : " + ID);
		
	}
	
	@Test(description = "Test Update REST API EM")
	public void testRESTTicketUpdate_EM() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "Update_Ticket_EM.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
		String ID = jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
		System.out.println("\n**** EM Ticket Updated ***\n");
		System.out.println("EM RequestID is : " + ID);

	}
}
