package com.infosys.test.businesspulse.web.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "NetworkInventoryService")
public class NetworkInventoryService
{
    
    @WebMethod
    public void updateInventory(String customerNumber)
    {
    	System.out.println("Started To Update Inventory");
	try
	{
	    Thread.sleep(10);
	}
	catch (InterruptedException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	System.out.println("Completed Updating the Inventory");
    }
}
