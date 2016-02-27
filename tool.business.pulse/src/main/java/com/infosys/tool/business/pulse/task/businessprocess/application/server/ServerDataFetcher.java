package com.infosys.tool.business.pulse.task.businessprocess.application.server;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.tool.business.pulse.application.ApplicationServerConfiguration;
import com.infosys.tool.business.pulse.application.server.ServerProperty;
import com.infosys.tool.business.pulse.datamodel.ServerData;
import com.infosys.tool.business.pulse.datamodel.ServerPropertyData;

public class ServerDataFetcher {
	private static final Logger LOG =LoggerFactory.getLogger(ServerDataFetcher.class);
	private ApplicationServerConfiguration serverConfiguration;
	
	private ServerData serverData;
	
	private List<ServerPropertyFetcher>  serverPropFetchTasks= new LinkedList<ServerPropertyFetcher>();
	
	public ServerDataFetcher(ApplicationServerConfiguration serverConfiguration){
		this.setServerConfiguration(serverConfiguration);
		
		for(ServerProperty serverProperty:serverConfiguration.getServerProperties()){
			this.serverPropFetchTasks.add(new ServerPropertyFetcher(serverProperty));
		}
		this.serverData=new ServerData();
	}

	public ServerData run() {
		
		List<ServerPropertyData> serverPropertyDatas= new LinkedList<ServerPropertyData>();
		//start tasks
		for(ServerPropertyFetcher task:serverPropFetchTasks){
			serverPropertyDatas.add(task.run());
		}
	
		enrich(serverPropertyDatas);
		return getServerData();
	}

	private void enrich(List<ServerPropertyData> serverPropertyDatas) {
		if(getServerConfiguration().getServerProperties().size()>0){
			getServerData().setServerType(getServerConfiguration().getServerProperties().get(0).getServerType());
		}
		getServerData().setServerProperties(serverPropertyDatas);
		getServerData().setName(getServerConfiguration().getServerName());
	}

	public ApplicationServerConfiguration getServerConfiguration() {
		return serverConfiguration;
	}

	public void setServerConfiguration(ApplicationServerConfiguration serverConfiguration) {
		this.serverConfiguration = serverConfiguration;
	}

	public ServerData getServerData() {
		return serverData;
	}

	public void setServerData(ServerData serverData) {
		this.serverData = serverData;
	}
	

}
