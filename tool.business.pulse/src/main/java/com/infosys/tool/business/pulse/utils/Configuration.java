package com.infosys.tool.business.pulse.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration {
	private static final Logger LOG =LoggerFactory.getLogger(Configuration.class);
	private static final String PROPERTYFILE_NAME="config.properties";
	private static Configuration INSTANCE = new Configuration();
    private Properties properties = new Properties();
	
    
    public static Configuration getInstance(){
		return INSTANCE;
	}
	
	private Configuration(){
		loadConfiguration();
		
	}



	private void loadConfiguration() {
	
		try {
			File f = new File(PROPERTYFILE_NAME);
			InputStream stream=null;
			if(f.exists()){
				stream = new FileInputStream(f);
			}else{
				LOG.trace(PROPERTYFILE_NAME+ " loading from classpath");
				stream=getClass().getClassLoader().getResourceAsStream(PROPERTYFILE_NAME);
			}
		
			properties.load(stream);
		} catch (IOException e) {
			LOG.error(""+properties.toString());
			LOG.error("Error Loading Property: ", e);
		} 
	}
	
	public String getValue(String key){
		return (String)properties.get(key);
	}
}