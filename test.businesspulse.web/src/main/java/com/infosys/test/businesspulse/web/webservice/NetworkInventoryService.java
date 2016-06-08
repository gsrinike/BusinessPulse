package com.infosys.test.businesspulse.web.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebService(name = "NetworkInventoryService")
public class NetworkInventoryService
{
	private static final Logger LOG =LoggerFactory.getLogger(NetworkInventoryService.class);
    
    @WebMethod
    public void updateInventory(String customerNumber)
    {
    	LOG.trace("Started To Update Inventory");
	try
	{
	    Thread.sleep(200);
	}
	catch (InterruptedException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    LOG.error("Error while updating NetworkService"+e);
	}
		LOG.trace("Completed Updating the Inventory");
    }
}
