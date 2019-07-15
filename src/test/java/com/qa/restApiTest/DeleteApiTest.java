package com.qa.restApiTest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBaseClass;
import com.qa.client.RestClient;

public class DeleteApiTest extends TestBaseClass{
	
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
	public void DeleteTestApi() throws ClientProtocolException, IOException
	{
		restClient = new RestClient();
		
		closeableHttpresponse = restClient.deleteData(url+"/2");
		
		 int statuscode = closeableHttpresponse.getStatusLine().getStatusCode();
		 
		 Assert.assertEquals(statuscode, testBase.RESPONSE_STATUS_CODE_204,"Wrong status code ");
		 
		 System.out.println(statuscode);
		
		
		
	}
}
