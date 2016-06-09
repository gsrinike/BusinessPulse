package com.infosys.test.businesspulse.web.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.test.businesspulse.service.test.OrderFulfilmentMessageProducer;

public class LoadOrderFulfillment extends Loader{
	
	private static final Logger LOG =LoggerFactory.getLogger(LoadOrderFulfillment.class);
	
	LoadOrderFulfillment() {
		super(5, 15);
	}
	
	public void load(){
		
		int maxLoad=randomWithRange(50, 100);
		LOG.trace(this.getClass().getSimpleName()+" with threads: "+maxLoad);
		for(int i=0;i<maxLoad;i++){
			Runnable worker = new Runnable() {
				
				@Override
				public void run() {
					OrderFulfilmentMessageProducer producer = new OrderFulfilmentMessageProducer();
					producer.pushMessage();
					
				}
			}; 
			getExecutor().execute(worker);
		}
	}

}
