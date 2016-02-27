package com.infosys.tool.business.pulse.task.businessprocess.application.server;

import com.infosys.tool.business.pulse.datamodel.ServerType;
import com.infosys.tool.business.pulse.task.businessprocess.application.server.weblogic.WeblogicServerPropertyLoader;

public class ServerPropertyLoaderFactory {
	
	private static ServerPropertyLoaderFactory INSTANCE= new ServerPropertyLoaderFactory();
	private ServerPropertyLoaderFactory(){}
	
	public static ServerPropertyLoaderFactory getInstance(){
		return INSTANCE;
	}
	
	public ServerPropertyLoader getServerPropertyLoader(ServerType serverType){
		switch(serverType){
			
		case Weblogic:
		default:
				return new WeblogicServerPropertyLoader();
		}
		
	}

}
