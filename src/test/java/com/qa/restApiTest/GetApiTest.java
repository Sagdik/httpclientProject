package com.qa.restApiTest;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBaseClass;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBaseClass {

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
	public void getApiTest() throws ClientProtocolException, IOException, JSONException {
		
		restClient = new RestClient();

	
		closeableHttpresponse= restClient.get(url);
	
		//a.Status Code
		
				int statusCode = closeableHttpresponse.getStatusLine().getStatusCode();
				
				System.out.println(statusCode);
				
				Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Status code Not 200");
				
		//b.Json String
				
				
				//Per Page data fetch
			    String responseString =	EntityUtils.toString(closeableHttpresponse.getEntity(),"UTF-8");
				
			    JSONObject responseJson = new JSONObject(responseString);
			    System.out.println("Value of json respons :"+responseJson);
			    
			    String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
			    
			    System.out.println("Value of page " + perPageValue);
			    
			    Assert.assertEquals(Integer.parseInt(perPageValue) , 3);
			    
			    
			    //Fetch total value from the request
			    String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
			    
			    System.out.println("Total Value in page "+totalValue);
			    
			    Assert.assertEquals(Integer.parseInt(totalValue), 12);
			    
			    
			    
			    //get the value from JSON Array;
			    
			    String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");

			    String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");

			    String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");

			    String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
			    
			    
			    System.out.println("Name :- "+lastName);
			    System.out.println("ID :-"+id);
			    System.out.println("Avatar value :-"+avatar);
			    System.out.println("EMAIL VALUE :-"+email);
			    
			    
			    
		//c. All Headers
			    Header[] headersArray = closeableHttpresponse.getAllHeaders();
			    
			    HashMap<String, String> allHeader =  new HashMap<String, String>();
			    
			    for(Header header: headersArray)
			    	
			    {
			    	allHeader.put(header.getName(), header.getValue());
			    }
			    
			    System.out.println("Header Array-----"+ allHeader);
			    
	}
	
	@Test
	public void getApiTestWithHeader() throws ClientProtocolException, IOException, JSONException {
		
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		
		headerMap.put("Content-Type", "application/json");
		
		
		

		closeableHttpresponse= restClient.get(url,headerMap);
	
		//a.Status Code
		
				int statusCode = closeableHttpresponse.getStatusLine().getStatusCode();
				
				System.out.println(statusCode);
				
				Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Status code Not 200");
				
		//b.Json String
				
				
				//Per Page data fetch
			    String responseString =	EntityUtils.toString(closeableHttpresponse.getEntity(),"UTF-8");
				
			    JSONObject responseJson = new JSONObject(responseString);
			    System.out.println("Value of json respons :"+responseJson);
			    
			    String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
			    
			    System.out.println("Value of page " + perPageValue);
			    
			    Assert.assertEquals(Integer.parseInt(perPageValue) , 3);
			    
			    
		//Fetch total value from the request
			    String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
			    
			    System.out.println("Total Value in page "+totalValue);
			    
			    Assert.assertEquals(Integer.parseInt(totalValue), 12);
			    
			    
			    
	    //get the value from JSON Array;
			    
			    String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");

			    String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");

			    String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");

			    String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
			    
			    
			    System.out.println("Name :- "+lastName);
			    System.out.println("ID :-"+id);
			    System.out.println("Avatar value :-"+avatar);
			    System.out.println("EMAIL VALUE :-"+email);
			    
			    
			    
		//c. All Headers
			    Header[] headersArray = closeableHttpresponse.getAllHeaders();
			    
			    HashMap<String, String> allHeader =  new HashMap<String, String>();
			    
			    for(Header header: headersArray)
			    	
			    {
			    	allHeader.put(header.getName(), header.getValue());
			    }
			    
			    System.out.println("Header Array-----"+ allHeader);
			    
	}

}
