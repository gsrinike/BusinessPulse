package com.infosys.tool.business.pulse.datamodel;

import java.util.List;

public class ServerPropertyData {

private String name;
	
	private List<ServerEntityData> serverEntities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ServerEntityData> getServerEntities() {
		return serverEntities;
	}

	public void setServerEntities(List<ServerEntityData> serverEntities) {
		this.serverEntities = serverEntities;
	}

	@Override
	public String toString() {
		return "ServerPropertyData [name=" + name + ", serverEntities="
				+ serverEntities + "]";
	}
	
}
