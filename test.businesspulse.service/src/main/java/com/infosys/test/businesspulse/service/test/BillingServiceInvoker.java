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
		BillingServiceInvoker invoker = new BillingServiceInvoker();
		invoker.invoke();
	}
	
	public void invoke(){
		try{
		BillingService billingService = (BillingService)  ServerContextUtils.getInstance().getContext().lookup("BillingServiceProvider#com.infosys.test.businesspulse.service.ejb.BillingService");
        LOG.trace("After Context Lookup of Billing service. Invoking Billing service EJB");
        billingService.syncBillingInfo(String.valueOf(Double.valueOf(Math.rint(100)).intValue()));
        LOG.trace("After Billing service EJB");
				
		}catch(Exception exception){
			LOG.error("Error while invoking BillingService", exception);
		}
	}	
}
