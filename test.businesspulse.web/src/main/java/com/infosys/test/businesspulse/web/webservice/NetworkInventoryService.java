package com.infosys.test.businesspulse.web.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "NetworkInventoryService")
public class NetworkInventoryService
{
    
    @WebMethod
    public void updateInventory(String customerNumber)
    {
	System.out.println("update Inventory");
    }
}
