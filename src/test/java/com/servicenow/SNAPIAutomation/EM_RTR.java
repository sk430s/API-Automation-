package com.servicenow.SNAPIAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI;

public class EM_RTR extends BaseTestSpecs {

	
	@Test(description = "Create EM_RTR_Nowia\n")
	public void testRESTTicketCreate_EM_RTR() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "EM_RTR_Nowia.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		String jsonString = response.asString();
		System.out.println(jsonString);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		
	
		String ID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
		System.out.println("**** EM_TN Ticket Created ***\n");
		System.out.println("EM ID is : " + ID);
		
		
		String ReqID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.RequestId");
				System.out.println("EM RequestID is : " + ReqID);
		
		
		String F_Area = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.FunctionalArea");
				System.out.println("FunctionalArea is : " + F_Area);
				System.out.println("\n\n");
		
	}
	
	
	@Test(description = "Get EM_RTR_Nowia")
	public void testRESTTicketGet_EM_RTR() throws IOException {
		
		
		
		RestAPI restAPI = new RestAPI();
		String fileName = "EM_RTR_Get.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		System.out.println(jsXpath.getNodeChildren("Envelope.Body.getTicketResponse.getTicketOutput").size());
		Assert.assertEquals(jsXpath.getNodeChildren("Envelope.Body.getTicketResponse.getTicketOutput").size(),1);
		
		String ID = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.TicketNum"),ID);
		System.out.println("\n TicketNum :" +ID);
		
		String F_Area = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[1].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[1].Value"),F_Area);
		System.out.println(" FunctionalArea :" +F_Area);
		
		String TroubleType = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[95].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[95].Value"),TroubleType);
		System.out.println(" TroubleType :" +TroubleType);
		
		String Severity = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[27].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[27].Value"),Severity);
		System.out.println(" Severity :" +Severity);
		
		String TroubleDesc = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[26].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[26].Value"),TroubleDesc);
		System.out.println(" Trouble Description :" +TroubleDesc);
		
		String State = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[2].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[2].Value"),State);
		System.out.println(" TicketState :" +State);
		
		String Type = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[3].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[3].Value"),Type);
		System.out.println(" TicketType :" +Type);
		
				
		String AssetID = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[17].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[17].Value"),AssetID);
		System.out.println(" AssetID :" +AssetID);
		
		
		System.out.println("\n***** Primary Service Assurance Contact Information *****\n");
		
		String AccountContactName = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[4].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[4].Value"),AccountContactName);
		System.out.println(" AccountContactName :" +AccountContactName);
		
		String Email = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[50].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[50].Value"),Email);
		System.out.println(" Email :" +Email);
		
		String AssuranceContact = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[5].Value");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.FieldTupleList.FieldTuple[5].Value"),AssuranceContact);
		System.out.println(" AccountPhone :" +AssuranceContact);
		
		System.out.println("\n***** Primary Local Site Contact Information *****\n");
		
		String LocationContactName = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.LocationContactName");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.LocationContactName"),LocationContactName);
		System.out.println(" LocationContactName :" +LocationContactName);
		
		String LocalEmail = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.LocationContactEmail");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.LocationContactEmail"),LocalEmail);
		System.out.println(" Email :" +LocalEmail);
		
		String LocalPhone = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.LocationContactPhone");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.LocationContactPhone"),LocalPhone);
		System.out.println(" LocalPhone :" +LocalPhone);
		
		for(int i=0;i<=6; i++)
		{
			
		
		String AccessDay = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.PremiseAccessHoursInfoList.AccessHoursInfo["+i+"].DayOfWeek");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.PremiseAccessHoursInfoList.AccessHoursInfo["+i+"].DayOfWeek"),AccessDay);
		System.out.print(" AccessDay :" +AccessDay);
		
		String AccessHoursStart = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.PremiseAccessHoursInfoList.AccessHoursInfo["+i+"].Start");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.PremiseAccessHoursInfoList.AccessHoursInfo["+i+"].Start"),AccessHoursStart);
		System.out.print(" Start Time :" +AccessHoursStart);
		
		String AccessHoursEnd = jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.PremiseAccessHoursInfoList.AccessHoursInfo["+i+"].End");
		Assert.assertEquals(jsXpath.get("Envelope.Body.getTicketResponse.getTicketOutput.LocationDetailsInfoList.LocationDetailsInfo.PremiseAccessHoursInfoList.AccessHoursInfo["+i+"].End"),AccessHoursEnd);
		System.out.print(" End Time  :" +AccessHoursEnd);
		System.out.println("\n");
		}

			
		
		System.out.println("\n\n");
		

}
	
	
	@Test(description = "Update Log REST API EM_RTR_Nowia")
	public void testRESTTicketUpdate_EM_TN() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "EM_RTR_AddLog.xml";
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
		System.out.println("\n\n");


}
	
	
	@Test(description = "Case Escalation EM_RTR_Nowia")
	public void testRESTTicketCase_EM_TN() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "EM_RTR_CaseEscalation.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		//String jsonString = response.asString();
		
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
		
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
		String ID = jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
		System.out.println("\n**** EM Ticket Updated ***\n");
		System.out.println("EM RequestID is : " + ID);
		System.out.println("\n\n");


}
	
	@Test(description = "Close REST API EM_RTR_Nowia\n")
	public void testRESTTicketClose_EM_TN() throws IOException {

		RestAPI restAPI = new RestAPI();
		String fileName = "EM_RTR_Close.xml";
		String EndPoint = config.getProperty("createEMTicket");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());
		System.out.println(
				jsXpath.getNodeChildren("Envelope.Body.updateTicketResponse.updateTicketOutput").size());
		String ID = jsXpath.get("Envelope.Body.updateTicketResponse.updateTicketOutput.RequestId");
		System.out.println("\nRequest ID is : " + ID);
		
		System.out.println("\n\n**** EM_TN Ticket Closed ****");
		

}
	
	@Test(description = "EDF REST API EM_RTR_Nowia")

	public void testEDF_TN_Request() throws IOException {
		
		RestAPI restAPI = new RestAPI();
		String fileName = "EM_RTR_EDF.xml";
		String EndPoint = config.getProperty("EDFTNRequest");
		Response response = restAPI.postRestAPI(EndPoint, fileName);
		System.out.println(response.getStatusCode());
		XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
	
		String primaryCompanyName = jsXpath.get("Envelope.Body.InquireEnterpriseCustomerSiteDetailsResponse.CustomerSiteDetail.CustomerSummary.primaryCompanyName");
		Assert.assertEquals(jsXpath.get("Envelope.Body.InquireEnterpriseCustomerSiteDetailsResponse.CustomerSiteDetail.CustomerSummary.primaryCompanyName"),primaryCompanyName);
		System.out.println("\n primaryCompanyName :" +primaryCompanyName);
	    
		for(int i=0;i<=2;i++)
		{
		String voipOption = jsXpath.get("Envelope.Body.InquireEnterpriseCustomerSiteDetailsResponse.CustomerSiteDetail.BVOIPCustomerSite.voipOption["+i+"]");
		Assert.assertEquals(jsXpath.get("Envelope.Body.InquireEnterpriseCustomerSiteDetailsResponse.CustomerSiteDetail.BVOIPCustomerSite.voipOption["+i+"]"),voipOption);
		System.out.println("\n voipOption :" +voipOption);
		}
		
		String SiteDetail = jsXpath.get("Envelope.Body.InquireEnterpriseCustomerSiteDetailsResponse.CustomerSiteDetail.BVOIPCustomerSite.CustomerSite.SiteDetail.Site.BillingMCN.mcn");
		Assert.assertEquals(jsXpath.get("Envelope.Body.InquireEnterpriseCustomerSiteDetailsResponse.CustomerSiteDetail.BVOIPCustomerSite.CustomerSite.SiteDetail.Site.BillingMCN.mcn"),SiteDetail);
		System.out.println("\n Org_ID :" +SiteDetail);
		
		System.out.println("\n\n");
		
		
	
}
	
	
}
