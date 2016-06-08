package com.infosys.test.businesspulse.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.test.businesspulse.web.webservice.client.NetworkInventoryService;
import com.infosys.test.businesspulse.web.webservice.client.NetworkInventoryServiceService;

public class NetworkServiceInvoker {

	private static final Logger LOG =LoggerFactory.getLogger(NetworkServiceInvoker.class);
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NetworkServiceInvoker networkServiceInvoker = new NetworkServiceInvoker();
		networkServiceInvoker.invoke();
		
	}
	
	private NetworkInventoryService getNetworkInventoryService(){
		NetworkInventoryServiceService service = new NetworkInventoryServiceService();
		NetworkInventoryService port = service.getNetworkInventoryServicePort();
		return port;
	}
	
	public void invoke(){
		try{
			LOG.trace("Invoking NetworkInventoryService");
			getNetworkInventoryService().updateInventory(String.valueOf(Double.valueOf(Math.rint(100)).intValue()));
			LOG.trace("NetworkInventoryService updateInventory called");
		}catch(Exception e){
			LOG.error("Error while invoking Network Service",e);
		}
	}

}
