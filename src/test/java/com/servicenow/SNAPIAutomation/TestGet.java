package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.RestAssured.*;
import utilities.RestAPI;


public class TestGet extends BaseTestSpecs {
	
	@Test(description = "Test Get REST API ET")
	public void testRESTTicketGet_ET() throws IOException {
		
		
		RestAPI restAPI = new RestAPI();
		String fileName = "Get_Ticket.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
	//	String jsonString = response.asString();
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		System.out.println(jsXpath.getNodeChildren("Envelope.Body.getTicketResponse.getTicketOutput").size());
		Assert.assertEquals(jsXpath.getNodeChildren("Envelope.Body.getTicketResponse.getTicketOutput").size(),1);
		
		String ID = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum"),ID);
		System.out.println("\n TicketNum :" +ID);
		
		String F_Area = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[2].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[2].Value"),F_Area);
		System.out.println(" FunctionalArea :" +F_Area);
		
		String T_Open = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[3].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[3].Value"),T_Open);
		System.out.println(" TicketOpened :" +T_Open);
		
		String State = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[4].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[4].Value"),State);
		System.out.println(" TicketState :" +State);
		
		String Role = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[5].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[5].Value"),Role);
		System.out.println(" TicketRole :" +Role);
		
		String Location = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[6].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[6].Value"),Location);
		System.out.println(" LocationStateProvince :" +Location);
		
		System.out.println("\n\n");
		
			
	}

}
