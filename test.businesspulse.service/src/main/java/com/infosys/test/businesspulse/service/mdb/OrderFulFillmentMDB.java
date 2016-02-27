package com.infosys.test.businesspulse.service.mdb;

import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "OrderFulFillmentJMSDest", activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class OrderFulFillmentMDB implements MessageListener
{
    
    public void onMessage(Message message)
    {
	
	try
	{
	    System.out.println("Started Order fulfillment processing");
	    Thread.sleep(5000);
	    System.out.println("Completed Order fulfillment processing");
	}
	catch (InterruptedException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
}
