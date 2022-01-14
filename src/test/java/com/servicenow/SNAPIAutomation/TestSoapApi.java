package com.servicenow.SNAPIAutomation;
import utilities.CommonUtils;
import utilities.RestAPI;
import java.io.IOException;
import java.util.Map;

import org.testng.ITestContext;
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
		System.out.println("Endpoint"+EndPoint);
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
		//Response response = restAPI.postSOAPAPI(EndPoint, fileName);
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
	
	
	@Test(description = "JSON Update")
	public void json_create_request(ITestContext context) throws IOException {
//		RestAPI restAPI = new RestAPI();
		String fileName = "createCase.json";
//		String EndPoint = config.getProperty("createEMTicket");

		String jsonrequest = CommonUtils.readFile(fileName);
		
		context.setAttribute("jsonrequest", jsonrequest);
		String jsonrequest1 = (String) context.getAttribute("jsonrequest");

		String valuestobeupdated = "contact_phone:9492588888, category:Fault";
		if (!valuestobeupdated.isEmpty()) {
			Map<String, Object> keyValuesMap = CommonUtils.getKVPairFromString(valuestobeupdated);
			
			System.out.println("Values to be updated in json request are: \n" + keyValuesMap);
			String updatedJsonRequest = CommonUtils.updateJson(jsonrequest, keyValuesMap);
			System.out.println("Updated Json Request is: \n" + updatedJsonRequest);
			context.setAttribute("jsonrequest", updatedJsonRequest);
			
		} else {
			System.out.println("No values to be updated in Request Json so using Request Json from file");
		}
		
		
		
	}


}

