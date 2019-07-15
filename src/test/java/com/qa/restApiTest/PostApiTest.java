package com.qa.restApiTest;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBaseClass;
import com.qa.client.RestClient;
import com.qa.data.UserData;

public class PostApiTest extends TestBaseClass {


	TestBaseClass testBase;

	String url;

	String apiUrl;

	String servicesUrl;
	RestClient restClient;
	
	CloseableHttpResponse closeableHttpresponse;

	@BeforeMethod
	public void setUp() {

		testBase = new TestBaseClass();

		servicesUrl = prop.getProperty("URL");

		apiUrl = prop.getProperty("servicesUrl");

		url = servicesUrl + apiUrl;

	}	
	
	@Test
	public void newUserPostApiTest() throws JsonGenerationException, JsonMappingException, IOException, JSONException
	{
		
		restClient = new RestClient();
	
        HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API
		ObjectMapper objectMapper = new ObjectMapper();
	
		UserData userData = new UserData("morpheus", "leader");
		
		//object to json file;
		objectMapper.writeValue(new File("H:/GIthub/restapi/src/main/java/com/qa/data/data.json"), userData);
		
		//Object to json in String Using Mapper Class
		
		String userjsonString =objectMapper.writeValueAsString(userData);
		
		System.out.println(userjsonString);
		
		closeableHttpresponse = restClient.postRequest(url, userjsonString, headerMap);
		
		
		//1.Status Code
		int statusCode = closeableHttpresponse.getStatusLine().getStatusCode();
		
		System.out.println(statusCode);
		
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_201, "Wrong status code");
	
		
		
		//2.JsonString
		
		String responseString = EntityUtils.toString(closeableHttpresponse.getEntity(), "UTF-8");
		
		JSONObject jsonobject = new JSONObject(responseString);
		
		System.out.println("The resonse from API IS:-"+jsonobject);
		
		//JSOn to java object
		
		UserData userObj = objectMapper.readValue(responseString , UserData.class);
		//System.out.println(userObj);
	
		System.out.println(userData.getName().equals(userObj.getName()));
		
		
		
		
	}
	
}
