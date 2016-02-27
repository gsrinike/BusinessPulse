package com.infosys.tool.business.pulse.task.businessprocess.application.server.weblogic.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.tool.business.pulse.application.server.ServerEntity;
import com.infosys.tool.business.pulse.application.server.ServerProperty;
import com.infosys.tool.business.pulse.application.server.weblogic.JMXConnection;
import com.infosys.tool.business.pulse.application.server.weblogic.JMXProperties;
import com.infosys.tool.business.pulse.application.server.weblogic.WeblogicServerProperty;
import com.infosys.tool.business.pulse.datamodel.ServerType;

public class WeblogicServerPropertyTest {

	private static final Logger LOG =LoggerFactory.getLogger(WeblogicServerPropertyTest.class);
	
	private static void process(ServerProperty serverProperty,List<String> instanceNames){
		
		if(serverProperty.getServerType()==ServerType.Weblogic){
			System.out.println(true);
		}
		
		WeblogicServerProperty sProps=(WeblogicServerProperty)serverProperty;
		Set<String> instanceNameSet= new HashSet<String>();
		for(String instanceName:instanceNames){
			instanceNameSet.add(instanceName);
		}
		
		MBeanServerConnection mbsc = sProps.getConnection().getMBeanServerConnection();
		LOG.trace(mbsc.toString());
		
		try{
		for (ObjectInstance name : mbsc.queryMBeans(new ObjectName(
				"com.bea:ServerRuntime=" + sProps.getServerName() + "*,*,Type="
						+ sProps.getProperties().getMBeanName()), null)) {
			
			//LOG.trace(name.toString());
			//LOG.trace("ServerRuntime: "+getValue(name.toString(), "com.bea:ServerRuntime"));
			String instanceName=getValue(name.toString(), "Name");
			if(instanceNameSet.contains(instanceName)){
			Iterator<String> iterator = sProps.getProperties().getProperties().iterator();
			LOG.trace(instanceName);
			while (iterator.hasNext()) {
				String property = iterator.next();
				Object value = (Object) mbsc.getAttribute(name.getObjectName(),
						property);
				LOG.trace(property+": "+value);
			}
			}	
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	private static String getValue(String name,String key){
		
		String value="";
		name=name.substring(name.indexOf("[")+1, name.indexOf("]"));
		String[] values=name.split(",");
		for(String v:values){
			
			if(v.split("=")[0].equalsIgnoreCase(key)){
				value=v.split("=")[1];
				break;
			}
			
		}
		return value;
	}


	public static void main(String[] args){
		
		List<ServerEntity> serverEntities= new LinkedList<ServerEntity>();
		List<String> jmxProperties = new LinkedList<String>();
		jmxProperties.add("Name");
		jmxProperties.add("IdleBeansCount");
		JMXProperties properties= new JMXProperties("EJBPoolRuntime", jmxProperties);
		String connectionUrl="t3://localhost:7001";
		Map<String,String> connectionProperties = new HashMap<String, String>();
		connectionProperties.put("java.naming.security.principal", "weblogic");
		connectionProperties.put("java.naming.security.credentials", "weblogic1");
		connectionProperties.put("jmx.remote.protocol.provider.pkgs", "weblogic.management.remote");
		JMXConnection connection= new JMXConnection(connectionUrl, connectionProperties);
		System.out.println("Test ");
		ServerProperty serverProperty = new WeblogicServerProperty(serverEntities,connection,properties,"AdminServer");
		List<String> instanceNames= new LinkedList<String>();
		instanceNames.add("BillingServiceProvider");
		System.out.println("List Size: "+instanceNames.size());
		process(serverProperty,instanceNames);
		
	}

}
