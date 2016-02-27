package com.infosys.tool.business.pulse.task.businessprocess.application.server.weblogic;

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
import com.infosys.tool.business.pulse.application.server.weblogic.WeblogicServerProperty;
import com.infosys.tool.business.pulse.datamodel.ServerEntityData;
import com.infosys.tool.business.pulse.datamodel.ServerPropertyData;
import com.infosys.tool.business.pulse.task.businessprocess.application.server.ServerPropertyLoader;

public class WeblogicServerPropertyLoader implements ServerPropertyLoader {
	private static final Logger LOG =LoggerFactory.getLogger(WeblogicServerPropertyLoader.class);

	@Override
	public ServerPropertyData load(ServerProperty serverProperty) {
		
		WeblogicServerProperty property = (WeblogicServerProperty)serverProperty;
		ServerPropertyData propertyData = new ServerPropertyData();
		propertyData.setName(property.getProperties().getMBeanName());
		List<ServerEntityData> serverEntityDatas = new LinkedList<ServerEntityData>();
		
		Map<String,ServerEntity> serverEntityMap= new HashMap<String, ServerEntity>();
		Set<String> instanceNameSet= new HashSet<String>();
		
		for(ServerEntity serverEntity:property.getServerEntities()){
			serverEntityMap.put(serverEntity.getProperty()+"."+serverEntity.getName(), serverEntity);
			instanceNameSet.add(serverEntity.getProperty());
		}
		MBeanServerConnection mbsc = property.getConnection().getMBeanServerConnection();
		
		try{
				for (ObjectInstance name : mbsc.queryMBeans(new ObjectName(
					"com.bea:ServerRuntime=" + property.getServerName() + "*,*,Type="
							+ property.getProperties().getMBeanName()), null)) {
					
					LOG.trace("ServerRuntime: "+getValue(name.toString(), "com.bea:ServerRuntime"));
					String instanceName=getValue(name.toString(), "Name");
					LOG.trace("Name: "+instanceName);
					
					if(instanceNameSet.contains(instanceName)){
						Iterator<String> iterator = property.getProperties().getProperties().iterator();
						while (iterator.hasNext()) {
							String prop = iterator.next();
							ServerEntity serverEntity=serverEntityMap.get(instanceName+"."+prop);
							if(serverEntity!=null){
							
								Object value = (Object) mbsc.getAttribute(name.getObjectName(),
										prop);
								ServerEntityData entityData= new ServerEntityData();
								entityData.setName(serverEntity.getName());
								entityData.setProperty(serverEntity.getProperty());
								entityData.setThreshold(serverEntity.getThreshold());
								entityData.setValue(value.toString());
								LOG.trace(prop+": "+value);
								serverEntityDatas.add(entityData);
							}
							
						}
					}
				}	
			}catch(Exception e){
				
			}
			
		propertyData.setServerEntities(serverEntityDatas);
		return propertyData;
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


}
