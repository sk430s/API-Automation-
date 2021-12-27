package com.servicenow.SNAPIAutomation;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI;

public class EMCreateTicket extends BaseTestSpecs {
      
	@Test(description = " CreateTicketEM ")
	public void createEMT() throws IOException {

			RestAPI restAPI = new RestAPI();
			String fileName = "create_Ticket_EM.xml";
			String EndPoint = config.getProperty("createEMTicket");
			Response response = restAPI.postSOAPAPI(EndPoint, fileName);
			String jsonString = response.asString();
			System.out.println(response.getStatusCode());
			XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
			System.out.println(jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size());
			Assert.assertEquals(jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size(),1);
			String ID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
			Assert.assertEquals(jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum"),ID);
		
		}
}
