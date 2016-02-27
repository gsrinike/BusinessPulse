package com.infosys.tool.business.pulse.datamodel;

import java.util.List;

public class ServerData {

private String name;
	
	private ServerType serverType;
	
	private List<ServerPropertyData> serverProperties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ServerType getServerType() {
		return serverType;
	}

	public void setServerType(ServerType serverType) {
		this.serverType = serverType;
	}

	public List<ServerPropertyData> getServerProperties() {
		return serverProperties;
	}

	public void setServerProperties(List<ServerPropertyData> serverProperties) {
		this.serverProperties = serverProperties;
	}

	@Override
	public String toString() {
		return "ServerData [name=" + name + ", serverType=" + serverType
				+ ", serverProperties=" + serverProperties + "]";
	}
}
