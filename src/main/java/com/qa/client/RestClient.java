package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RestClient {

	//1.Get Method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException, JSONException
	{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpget= new HttpGet(url);
		
		CloseableHttpResponse closeableHttpresponse = httpClient.execute(httpget);
		
		return closeableHttpresponse;
	    
	}
	
	//2.Get
	
	public CloseableHttpResponse get(String url,HashMap<String, String> headerMap) throws ClientProtocolException, IOException, JSONException
	{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpget= new HttpGet(url);//http get request
		
		for(Map.Entry<String,String> entry: headerMap.entrySet())
		{
			httpget.addHeader(entry.getKey(), entry.getValue());
			
		}
		
		CloseableHttpResponse closeableHttpresponse = httpClient.execute(httpget);
		
		return closeableHttpresponse;
	    
	}
	
	//3.Post method for add data
	public CloseableHttpResponse postRequest(String url , String entityString , HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		
		HttpPost httppost = new HttpPost(url);//http post request
		
		httppost.setEntity(new StringEntity(entityString));//for payload
		
		//for header
		
		for(Map.Entry<String,String> entry: headerMap.entrySet())
		{
			httppost.addHeader(entry.getKey(), entry.getValue());
			
		}
		
		CloseableHttpResponse closeableHttpresponse = httpclient.execute(httppost);
		
		return closeableHttpresponse;
		
	}
	
	//4.Delete API Method for remove data
	public CloseableHttpResponse deleteData(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpDelete httpDelete = new HttpDelete(url);
		

		CloseableHttpResponse closeableHttpresponse = httpclient.execute(httpDelete);
		 
		return closeableHttpresponse;
		
	}
	
	
	//5.Update API Request for data
	public CloseableHttpResponse updateApiRequest(String url , String entityString , HashMap<String, String> headerMap ) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		HttpPut httpPut = new HttpPut(url);
		
		httpPut.setEntity(new StringEntity(entityString));
		
		for(Map.Entry<String, String> entry : headerMap.entrySet())
		{
			
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpresponse = httpclient.execute(httpPut);
		
		return closeableHttpresponse;
		
	}
	
	
	
	
}
