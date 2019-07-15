package com.qa.restApiTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBaseClass;
import com.qa.client.RestClient;
import com.qa.data.UserData;

public class UpdateApiTest extends TestBaseClass {
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
	public void oldUserUpdateApiTest() throws JsonGenerationException, JsonMappingException, IOException, JSONException
	{
		
		restClient = new RestClient();
		
        HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API
		ObjectMapper mapper = new ObjectMapper();
		
		UserData userdata = new UserData("morpheus","zion resident");
		
		mapper.writeValue(new File("H:/GIthub/restapi/src/main/java/com/qa/data/data.json"),userdata);
		
		String userJsonString = mapper.writeValueAsString(userdata);
		
		System.out.println(userJsonString);
		
		
	}
}
