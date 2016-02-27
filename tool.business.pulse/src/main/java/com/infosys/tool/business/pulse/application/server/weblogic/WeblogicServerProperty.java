package com.infosys.tool.business.pulse.application.server.weblogic;

import java.util.List;

import com.infosys.tool.business.pulse.application.server.ServerEntity;
import com.infosys.tool.business.pulse.application.server.ServerProperty;
import com.infosys.tool.business.pulse.datamodel.ServerType;

public class WeblogicServerProperty extends ServerProperty{

	private JMXConnection connection;
	
	private JMXProperties properties;
	
	private String serverName;
	
	public WeblogicServerProperty(List<ServerEntity> serverEntities,JMXConnection connection,JMXProperties properties,String serverName) {
		super(ServerType.Weblogic,serverEntities);
		this.setConnection(connection);
		this.setProperties(properties);
		this.serverName=serverName;
	}


	public JMXConnection getConnection() {
		return connection;
	}


	public void setConnection(JMXConnection connection) {
		this.connection = connection;
	}


	public JMXProperties getProperties() {
		return properties;
	}


	public void setProperties(JMXProperties properties) {
		this.properties = properties;
	}


	public String getServerName() {
		return serverName;
	}


	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Override
	public String toString() {
		return "WeblogicServerProperty [serverName=" + serverName
				+ ", serverEntities=" + serverEntities + "]";
	}
	
	

}
