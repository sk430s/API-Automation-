package com.servicenow.SNAPIAutomation;
import utilities.RestAPI;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class TestSoapApi extends BaseTestSpecs {
	
	@Test(description = "Test SOAP API", enabled=false)
	public void testSOAPTicketCreate() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "BMP_Create_ticket.xml";
		String EndPoint = config.getProperty("createBmpTicket");
		Response response = restAPI.postSOAPAPI(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		// System.out.println("path: "+ jsXpath.get("Envelope.Body"));
		// System.out.println(jsXpath.getList("Envelope.Body"));
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.createCircuitTicketResponse.createTicketOutput").size());
		System.out.println("Node: " + jsXpath.getNodeChildren("Envelope.Body").getNodes("createTicketOutput"));
		// System.out.println("Node:
		// "+jsXpath.getNode("Envelope.Body.createCircuitTicketResponse.createTicketOutput"));

		String ID = jsXpath.get("Envelope.Body.createCircuitTicketResponse.createTicketOutput.troubleTicketId");
		// String
		// ID=jsXpath.get("createCircuitTicketResponse.createTicketOutput.troubleTicketId");

		// String ID = XmlPath.("troubleTicketID");

		System.out.println("ID is : " + ID);
	}
	
	
	@Test(description = "Test SOAP API EM")
	public void testSOAPTicketCreate_EM() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "EM_Create_ticket.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postSOAPAPI(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		// System.out.println("path: "+ jsXpath.get("Envelope.Body"));
		// System.out.println(jsXpath.getList("Envelope.Body"));
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size());
//		System.out.println("Node: " + jsXpath.getNodeChildren("Envelope.Body").getNodes("createTicketOutput"));
		// System.out.println("Node:
		// "+jsXpath.getNode("Envelope.Body.createCircuitTicketResponse.createTicketOutput"));

		String ID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
		// String
		// ID=jsXpath.get("createCircuitTicketResponse.createTicketOutput.troubleTicketId");

		// String ID = XmlPath.("troubleTicketID");

		System.out.println("EM ID is : " + ID);
	}

	
	
	@Test(description = "Test SOAP API ET")
	public void testSOAPTicketCreate_ET() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "ET_Create_ticket.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		Response response = restAPI.postSOAPAPI(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		// System.out.println("path: "+ jsXpath.get("Envelope.Body"));
		// System.out.println(jsXpath.getList("Envelope.Body"));
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size());
//		System.out.println("Node: " + jsXpath.getNodeChildren("Envelope.Body").getNodes("createTicketOutput"));
		// System.out.println("Node:
		// "+jsXpath.getNode("Envelope.Body.createCircuitTicketResponse.createTicketOutput"));

		String ID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
		// String
		// ID=jsXpath.get("createCircuitTicketResponse.createTicketOutput.troubleTicketId");

		// String ID = XmlPath.("troubleTicketID");

		System.out.println("ET ID is : " + ID);
	}


}

