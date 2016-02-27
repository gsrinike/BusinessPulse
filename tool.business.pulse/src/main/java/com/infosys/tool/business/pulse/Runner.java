package com.infosys.tool.business.pulse;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Runner {
	private static final Logger LOG =LoggerFactory.getLogger(Runner.class);
	private static final String[] APPLICATION_CONTEXTS={"spring-appcontext.xml"};

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		
		File f = new File(APPLICATION_CONTEXTS[0]);
		
		if(f.exists()){
			new FileSystemXmlApplicationContext(APPLICATION_CONTEXTS);
		}else{
			LOG.debug("Configuration loaded from Classpath..");
			new ClassPathXmlApplicationContext(APPLICATION_CONTEXTS);
			
		}	
		
	}

}
