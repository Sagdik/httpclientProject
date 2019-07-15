package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class TestBaseClass {

	public  Properties prop;
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_404 = 400;
	public int RESPONSE_STATUS_CODE_204 = 204;
	
	
	
	public TestBaseClass() {
		// TODO Auto-generated constructor stub
		
		try{
			
			prop = new Properties();
			
			File file = new File("./configuration/config.properties");
			
			FileInputStream fileConnection = new FileInputStream(file);
		
			prop.load(fileConnection);
			
			
		}catch(Exception e)
		{
			System.out.println("Unable to process property files"+e);
		}
		
		
	}
	
	
	
	
}
