package com.infosys.tool.business.pulse.application.server;

import java.util.List;

import com.infosys.tool.business.pulse.datamodel.ServerType;

public class ServerProperty {
	protected ServerType serverType;
	protected ServerProperty(ServerType serverType,List<ServerEntity> serverEntities){
		this.serverEntities=serverEntities;
		this.serverType=serverType;
	}
	public ServerType getServerType() {
		return serverType;
	}
	public void setServerType(ServerType serverType) {
		this.serverType = serverType;
	}
	public List<ServerEntity> getServerEntities() {
		return serverEntities;
	}
	public void setServerEntities(List<ServerEntity> serverEntities) {
		this.serverEntities = serverEntities;
	}
	protected List<ServerEntity> serverEntities;
	
	
}
