package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI;

public class AotsTm extends BaseTestSpecs {
	
	@Test(description = "Create AOTS-TM_RTR_Nowia\n")
	public void Create_RTR() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "AOTS-TM_Create.xml";
		String EndPoint = config.getProperty("createAOTSTMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		String jsonString = response.asString();
//		System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		
	
		String ID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
		System.out.println("**** AOTS-TM Ticket Created ***\n");
		System.out.println(" ID is : " + ID);
		
		System.out.println(" \n");
			
	}
	
	@Test(description = "Update Log REST API AOTS-TM_RTR_Nowia")
	public void testRESTTicketUpdate_EM_TN() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "AOTS-TM_AddLog.xml";
		String EndPoint = config.getProperty("createAOTSTMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		String jsonString = response.asString();
		//System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		
	//	System.out.println(
	//			jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
		String ID = jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
		System.out.println("\n**** AOTS-TM Ticket Updated ***\n");
		System.out.println(" RequestID is : " + ID);
		System.out.println("\n\n");


}
	
	@Test(description = "Get AOTS-TM_RTR_Nowia")
	public void testRestGetAOTSTM_EM_RTR() throws IOException {
		
		
		
		RestAPI restAPI = new RestAPI();
		String fileName = "AOTS-TM_Get.xml";
		String EndPoint = config.getProperty("createAOTSTMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		
		System.out.println(jsXpath.getNodeChildren("Envelope.Body.getTicketResponse.getTicketOutput").size());
				
		String ID = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum"),ID);
		System.out.println("\n AOTS-TM TicketNum :" +ID);
		
		String F_Area = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FunctionalArea");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FunctionalArea"),F_Area);
		System.out.println(" FunctionalArea :" +F_Area);
		
		String Req_ID = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.RequestId");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.RequestId"),Req_ID);
		System.out.println(" Request ID :" +Req_ID);
		
						
		String State = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketState");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketState"),State);
		System.out.println(" TicketState :" +State);
		
		String Type = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketType");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketType"),Type);
		System.out.println(" TicketType :" +Type);
		
				
		String AssetID = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.AssetID");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.AssetID"),AssetID);
		System.out.println(" AssetID :" +AssetID);
		
				
		System.out.println("\n");
	}

		
		

	@Test(description = "Close REST API AOTS-TM RTR\n")
	public void testClose_AOTSTM_RTR() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "AOTS-TM_Close.xml";
		String EndPoint = config.getProperty("createAOTSTMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());
		System.out.println(
			jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
//	String ID = jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
//	System.out.println("\nRequest ID is : " + ID);
		
		System.out.println("\n\n**** AOTS-TM Ticket Closed ****");
		

}

	

}
