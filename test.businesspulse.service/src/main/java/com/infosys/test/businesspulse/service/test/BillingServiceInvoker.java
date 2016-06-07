package com.infosys.test.businesspulse.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.test.businesspulse.service.ejb.BillingService;
import com.infosys.test.businesspulse.service.utils.ServerContextUtils;

public class BillingServiceInvoker {
	
	private static final Logger LOG =LoggerFactory.getLogger(BillingServiceInvoker.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void invoke(){
		try{
		BillingService billingService = (BillingService)  ServerContextUtils.getInstance().getContext().lookup("BillingServiceProvider#com.infosys.test.businesspulse.service.ejb.BillingService");
		billingService.syncBillingInfo(String.valueOf(Math.random()));
				
		}catch(Exception exception){
			LOG.error("Error while invoking BillingService", exception);
		}
	}	
}
