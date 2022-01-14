package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import constants.RequestPayLoad;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI;

public class CreateTicket extends BaseTestSpecs {

	RequestPayLoad request=new RequestPayLoad();
	String ticketNum="";
	@Test(description = "EM CreateTicket ")
	public void createEMTicket() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "create_Ticket_EM.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postSOAPAPI(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(response.getStatusCode());
	//	logger.info("hello");
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		System.out.println(jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size());
		Assert.assertEquals(jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size(),1);
		String ID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
	    Assert.assertEquals(jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum"),ID);
	
	}
		
	@Test(description = "ET CreateTicket ")
	public void createETTicket() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "ET_Create_Ticket.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postSOAPAPI(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		System.out.println(jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size());
		Assert.assertEquals(jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size(),1);
	   ticketNum = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
		Assert.assertEquals(jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum"),ticketNum);
	
	}
	
	@Test(description = "ET getTicket ", dependsOnMethods= {"com.servicenow.SNAPIAutomation.CreateTicket.createETTicket"})
	public void getETTicketPostCreate() throws IOException {

		RestAPI restAPI = new RestAPI();
		System.out.println(ticketNum);
		String fileName = request.getRequestBody(ticketNum);
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postSOAPAPIwithString(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(response.getStatusCode());
		System.out.println(jsonString);
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		System.out.println(jsXpath.getNodeChildren("Envelope.Body.getTicketResponse.getTicketOutput").size());
	//	Assert.assertEquals(jsXpath.getNodeChildren("Envelope.Body.getTicketResponse.getTicketOutput").size(),1);
		 String ID = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum"),ID);
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[4].Value"),"Queued");
	} 

  @Test(description = "ET UpdateTicket " ,dependsOnMethods= {"com.servicenow.SNAPIAutomation.CreateTicket.createETTicket"})
	public void updateETTicket() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = request.updateRequestBody(ticketNum);
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postSOAPAPIwithString(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		System.out.println(jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
		Assert.assertEquals(jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size(),1);
		String ID = (String)jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
		Assert.assertEquals((String)jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId"),ID);
		
		
}

	
	@Test(description = "ET closeTicket ", dependsOnMethods= {"com.servicenow.SNAPIAutomation.CreateTicket.updateETTicket"})
	public void closeETTicket() throws IOException, InterruptedException {

		RestAPI restAPI = new RestAPI();
		String fileName = request.closeRequestBody(ticketNum);
		String EndPoint = config.getProperty("createEMTicket");
		Thread.sleep(5000);
		Response response = restAPI.postSOAPAPIwithString(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		System.out.println(jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
		Assert.assertEquals(jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size(),1);
		String ID = jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
		Assert.assertEquals(jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId"),ID);
	
}
	@Test(description = "ET getTicket after closed", dependsOnMethods= {"com.servicenow.SNAPIAutomation.CreateTicket.closeETTicket"})
	public void getETTicketPostClose() throws IOException {

		RestAPI restAPI = new RestAPI();
		System.out.println(ticketNum);
		String fileName = request.getRequestBody(ticketNum);
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postSOAPAPIwithString(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(response.getStatusCode());
		System.out.println(jsonString);
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		System.out.println(jsXpath.getNodeChildren("Envelope.Body.getTicketResponse.getTicketOutput").size());
	//	Assert.assertEquals(jsXpath.getNodeChildren("Envelope.Body.getTicketResponse.getTicketOutput").size(),1);
		 String ID = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum"),ID);
		//Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[4].Value"),"Closed");
	
		
		
	} 
	

	
	
}

	