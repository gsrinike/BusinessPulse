package com.infosys.test.businesspulse.service.ejb;

import javax.ejb.Stateless;

@Stateless(mappedName = "BillingServiceProvider")
public class BillingServiceProvider implements BillingService
{
    
    @Override
    public void syncBillingInfo(String customerNumber)
    {
	
	try
	{
	    System.out.println("Starting Billing Service Bean Provider for customerNumber:"+customerNumber);
	    Thread.sleep(5000);
	    System.out.println("Completed Billing service Bean Provider for customerNumber:"+customerNumber);
	}
	catch (InterruptedException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }
    
}
