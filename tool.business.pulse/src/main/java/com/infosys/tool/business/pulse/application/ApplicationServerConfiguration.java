package com.infosys.tool.business.pulse.application;

import java.util.List;

import com.infosys.tool.business.pulse.application.server.ServerProperty;

public class ApplicationServerConfiguration {

	private String serverName;
	public void setServerProperties(List<ServerProperty> serverProperties) {
		this.serverProperties = serverProperties;
	}

	private List<ServerProperty> serverProperties;

	public List<ServerProperty> getServerProperties() {
		return serverProperties;
	}

	public ApplicationServerConfiguration(String serverName,List<ServerProperty> serverProperties) {
		this.serverName=serverName;
		this.serverProperties = serverProperties;
	}
	
	public ApplicationServerConfiguration(List<ServerProperty> serverProperties){
		this.serverProperties=serverProperties;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	@Override
	public String toString() {
		return "ApplicationServerConfiguration [serverName=" + serverName
				+ ", serverProperties=" + serverProperties + "]";
	}
	
	
}
