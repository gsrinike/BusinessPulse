package com.infosys.test.businesspulse.web.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.test.businesspulse.service.test.BillingServiceInvoker;

public class LoadBillingService extends Loader{
	
	private static final Logger LOG =LoggerFactory.getLogger(LoadBillingService.class);
	
	LoadBillingService() {
		super(1,3);
	}

	public void load(){
		
		int maxLoad=randomWithRange(20, 50);
		
		LOG.trace(this.getClass().getSimpleName()+" with threads: "+maxLoad);
		
		for(int i=0;i<maxLoad;i++){
			Runnable worker = new Runnable() {
				
				@Override
				public void run() {
					BillingServiceInvoker invoker = new BillingServiceInvoker();
					invoker.invoke();
					
				}
			}; 
			getExecutor().execute(worker);
		}
	}
	
	
	
}
