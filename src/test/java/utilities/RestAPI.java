package utilities;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.params.CoreConnectionPNames;

import common.Hooks;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAPI extends BaseAPISpecs {
	
	public Response postRestAPI(String endpoint, String FileName) throws IOException {
		
		RestAssured.baseURI = endpoint;
        Response response = null;
        String jsonrequest = CommonUtils.readFile(FileName);
		RequestSpecification request = givenBaseSpec().body(jsonrequest);
        response = request.post();
		return response;
	}
	
public Response postRestAPIwithStringBody(String endpoint, String body) throws IOException {
		
		RestAssured.baseURI = endpoint;
        Response response = null;
      //  String jsonrequest = CommonUtils.readFile(FileName);
		RequestSpecification request = givenBaseSpec().body(body);
        response = request.post();
		return response;
	}
	
	
	public Response postSOAPAPI(String endpoint, String FileName) throws IOException {
		
		RestAssured.baseURI = endpoint;
        Response response = null;
        String xmlrequest = CommonUtils.readFile(FileName);
		RequestSpecification request = givenSOAPBaseSpec().body(xmlrequest);
        response = request.post();
		
		return response;
	}
	
	
public Response postSOAPAPIwithString(String endpoint, String body) throws IOException {
		
		RestAssured.baseURI = endpoint;
        Response response = null;
        //String xmlrequest = CommonUtils.readFile(FileName);
		RequestSpecification request = givenSOAPBaseSpec().body(body);
        response = request.post();
		
		return response;
	}
	public Response getRestAPI(String endpoint) {
        RestAssured.baseURI = endpoint;
        RequestSpecification httpRequest = givenBaseSpec();
        Response response = httpRequest.get();
        return response;
        
    }
	
	public Response getRestAPIWithParams(String endpoint) {
        RestAssured.baseURI = endpoint;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("limit", "1000");
        RequestSpecification httpRequest = givenBaseSpec().params(params);
        Response response = httpRequest.get();
        return response;
        
    }
	
	public Response getAPI(String endpoint) {
        RestAssured.baseURI = endpoint;
        RestAssuredConfig config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig());
        RequestSpecification httpRequest = givenBaseSpec().config(config);
        Response response = httpRequest.get();
        //HTMLResponse 
        return response;
        
    }
	public Response putRestAPI(String endpoint, String FileName) throws IOException {
        RestAssured.baseURI = endpoint;
        String jsonrequest = CommonUtils.readFile(FileName);
        RequestSpecification httpRequest = givenBaseSpec().body(jsonrequest);
        Response response = httpRequest.put();
        return response;
        
    }
	
	public Response putRestAPI(String endpoint) throws IOException {
        RestAssured.baseURI = endpoint;
       
        RequestSpecification httpRequest = givenBaseSpec();
        Response response = httpRequest.put();
        return response;
        
    }
	
	public Response headRestAPI(String endpoint) throws IOException {
        RestAssured.baseURI = endpoint;
       
        RequestSpecification httpRequest = givenBaseSpec();
        Response response = httpRequest.head();
        return response;
        
    }
	
	public Response deleteRestAPI(String endpoint) {
        RestAssured.baseURI = endpoint;
        RequestSpecification httpRequest = givenBaseSpec();
        Response response = httpRequest.delete();
        return response;
        
    }
	
	public Response deleteRestAPI(String endpoint, String param, String value) {
        RestAssured.baseURI = endpoint;
        RequestSpecification httpRequest = givenBaseSpec().param(param, value);
        Response response = httpRequest.delete();
        return response;
        
    }

}
