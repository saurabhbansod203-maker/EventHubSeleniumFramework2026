package com.eventHub.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	/**
	 * ConfigReader loads and manages configuration settings from properties files.
	 */
	
	private static Properties properties;
	private static final String CONFIG_FILE_PATH = "src/test/resources/config/config.properties";
	
	
	// Static block loads properties file into memory when class is first referenced(loaded)
	
	static {
		
		try {
			
			FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
			properties = new Properties();
			properties.load(fis);
			fis.close();
			
			
			
		}catch(FileNotFoundException e) {
			
			throw new RuntimeException("LOG [ERROR]: Config file not found at " + CONFIG_FILE_PATH, e);
			
			
		}catch(IOException e)
		{
			
			throw new RuntimeException("LOG [ERROR]: Failed to load config properties file", e);
			
		}
			
		
	}
	/**
     * Fetches property value by key. System properties (CLI) override file properties.
     * Example: Passing -Dbrowser=firefox via Maven command line takes priority.
     * 
     * @param key Key name in config.properties
	 * @return 
     */
	
	
	public static String getProperty(String key)
	
	// System property override (crucial for Jenkins CI/CD execution)
	
	{
		
		String systemProperty = System.getProperty(key);  // System is class 
		if(systemProperty !=null && !systemProperty.isEmpty())
		{
			
			return systemProperty;
			
		}
		
		String propertyValue = properties.getProperty(key);
		if(propertyValue == null)
		{
			
			throw new RuntimeException ("LOG [ERROR]: Key '" + key + "' is not specified in config.properties!");
			
		}
		
		return propertyValue.trim();  // return properties value with removing space front and end
		
	}
	
	
	
	
}
