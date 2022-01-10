package com.servicenow.SNAPIAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Table.Cell;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI;


public class TestExcel  extends BaseTestSpecs {
	
	String Ticket="";
	
	 		
		@Test(description = "Test Create REST API EM\n")
		public void testRESTTicketCreate_EM() throws IOException {

			RestAPI restAPI = new RestAPI();
			String fileName = "EM_Create_ticket.xml";
			String EndPoint = config.getProperty("createEMTicket");
			Response response = restAPI.postRestAPI(EndPoint, fileName);
			System.out.println("Endpoint" + EndPoint);
			String jsonString = response.asString();
			System.out.println(jsonString);
			System.out.println(response.getStatusCode());
			XmlPath jsXpath = new XmlPath(response.asString());// Converting string into xml path to assert
			
			System.out.println(
					jsXpath.getNodeChildren("Envelope.Body.createTicketResponse.createTicketOutput").size());

			String ID = jsXpath.get("Envelope.Body.createTicketResponse.createTicketOutput.TicketNum");
			
	
/*			
			FileInputStream fis = new FileInputStream("./src/test/resources/files/Ticket.xlsx");
			XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(fis);
			XSSFSheet sh = wb.getSheet("Sheet2");
			int nor = sh.getLastRowNum();
			int i=0;
		
			XSSFRow row = sh.createRow(i);
			XSSFCell cell = row.createCell(0);
			cell.setCellValue(ID);
			//System.out.println(cell.getStringCellValue());
			FileOutputStream fos = new FileOutputStream("./src/test/resources/files/Ticket.xlsx");
			wb.write(fos);
			i++;
			
			
		//	System.out.println(nor);
			
//			for(int i=0;i<=nor;i++) {
//				System.out.println(sh.getRow(i).getCell(0));
				//System.out.println(sh.getRow(i).getCell(1));
				
				
//			}
			
			XSSFRow row = sh.createRow(0);
			XSSFCell cell = row.createCell(0);
			cell.setCellValue(ID);
			System.out.println(cell.getStringCellValue());
			FileOutputStream fos = new FileOutputStream("./src/test/resources/files/Ticket.xlsx");
			wb.write(fos);
			fos.flush();
			fos.close();
		
*/			
			System.out.println("**** EM Ticket Created ***\n");
			System.out.println("EM ID is : " + ID);
			
		}
		
		
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
