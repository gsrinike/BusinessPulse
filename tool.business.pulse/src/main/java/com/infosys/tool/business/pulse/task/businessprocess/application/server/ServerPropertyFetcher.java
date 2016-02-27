package com.infosys.tool.business.pulse.task.businessprocess.application.server;

import com.infosys.tool.business.pulse.application.server.ServerProperty;
import com.infosys.tool.business.pulse.datamodel.ServerPropertyData;

public class ServerPropertyFetcher {

	
	private ServerProperty serverProperty;
	
	public ServerPropertyFetcher(ServerProperty serverProperty){
		this.setServerProperty(serverProperty);
	}
	
	public ServerPropertyData run() {
	
		return ServerPropertyLoaderFactory.getInstance().getServerPropertyLoader(serverProperty.getServerType()).load(serverProperty);
		
	}

	public ServerProperty getServerProperty() {
		return serverProperty;
	}

	public void setServerProperty(ServerProperty serverProperty) {
		this.serverProperty = serverProperty;
	}

}
