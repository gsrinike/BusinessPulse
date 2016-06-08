package com.infosys.test.businesspulse.web.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.test.businesspulse.service.test.NetworkServiceInvoker;

public class LoadNetworkInventoryService extends Loader{
	
	private static final Logger LOG =LoggerFactory.getLogger(LoadNetworkInventoryService.class);
	
	LoadNetworkInventoryService() {
		super(1, 5);
	}
	
	public void load(){
		
		int maxLoad=randomWithRange(75, 150);
		LOG.trace(this.getClass().getSimpleName()+" with threads: "+maxLoad);
		for(int i=0;i<maxLoad;i++){
			Runnable worker = new Runnable() {
				
				@Override
				public void run() {
					NetworkServiceInvoker invoker = new NetworkServiceInvoker();
					invoker.invoke();
					
				}
			}; 
			getExecutor().execute(worker);
		}
	}


}
