package com.infosys.tool.business.pulse.task.businessprocess.application;

import java.util.LinkedList;
import java.util.List;

import com.infosys.tool.business.pulse.application.ApplicationConfiguration;
import com.infosys.tool.business.pulse.application.ApplicationServerConfiguration;
import com.infosys.tool.business.pulse.datamodel.ApplicationData;
import com.infosys.tool.business.pulse.datamodel.ServerData;
import com.infosys.tool.business.pulse.task.businessprocess.application.server.ServerDataFetcher;

public class ApplicationDataFetcher {
	
	private ApplicationData applicationData;
	private List<ServerDataFetcher>  serverDataFetchTasks= new LinkedList<ServerDataFetcher>();
	
	private ApplicationConfiguration  applicationConfiguration;

	public ApplicationDataFetcher(ApplicationConfiguration  applicationConfiguration){
		this.setApplicationConfiguration(applicationConfiguration);
		
		for(ApplicationServerConfiguration configuration:getApplicationConfiguration().getApplicationServerConfigurations()){
			serverDataFetchTasks.add(new ServerDataFetcher(configuration));
		}
		this.applicationData=new ApplicationData();
	}
	
	public ApplicationData run() {
		
		List<ServerData> serverDatas= new LinkedList<ServerData>();
		//start tasks
		for(ServerDataFetcher task:serverDataFetchTasks){
			serverDatas.add(task.run());
		}
		
		enrich(serverDatas);
		return getApplicationData();
	}

	public ApplicationData getApplicationData() {
		return applicationData;
	}

	public void setApplicationData(ApplicationData applicationData) {
		this.applicationData = applicationData;
	}

	public ApplicationConfiguration getApplicationConfiguration() {
		return applicationConfiguration;
	}

	public void setApplicationConfiguration(ApplicationConfiguration applicationConfiguration) {
		this.applicationConfiguration = applicationConfiguration;
	}
	
	
	private void enrich(List<ServerData> serverDatas){
		getApplicationData().setName(applicationConfiguration.getApplicationName());
		getApplicationData().setServerDatas(serverDatas);
	}
	

}
