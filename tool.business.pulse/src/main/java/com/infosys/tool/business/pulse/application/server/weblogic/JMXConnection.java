package com.infosys.tool.business.pulse.application.server.weblogic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMXConnection {
	private static final Logger LOG =LoggerFactory.getLogger(JMXConnection.class);
	
	private String connectionUrl;
	private Hashtable<String,String> env;
	private MBeanServerConnection connection=null;
	public JMXConnection(String connectionUrl,Map<String,String> connectionProperties){
		this.connectionUrl=connectionUrl;
		this.env=new Hashtable<String, String>(connectionProperties);
	}
	 
	public MBeanServerConnection getMBeanServerConnection(){
		
		
		if(this.connection==null){
			
			String jmxURL = "service:jmx:"+this.connectionUrl+ "/jndi/weblogic.management.mbeanservers.domainruntime";
			LOG.trace("JMX URL: "+jmxURL);
				JMXServiceURL address =null;
				JMXConnector connector=null;
		    	try {
					address = new JMXServiceURL(jmxURL);
					connector=JMXConnectorFactory.connect(address, env);
					this.connection=connector.getMBeanServerConnection();
					return this.connection;
				} catch (MalformedURLException e) {
					LOG.error("JMX Connection Error: "+e);
				} catch (IOException e) {
					LOG.error("JMX Connection Error: "+e);
				}

		}
		LOG.trace("Using Cached MBeanServerConnection");
		return this.connection;
	}

}
