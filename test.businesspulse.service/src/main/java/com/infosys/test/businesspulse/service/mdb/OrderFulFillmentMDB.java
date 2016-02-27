package com.infosys.test.businesspulse.service.mdb;

import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "OrderFulFillmentJMSDest", 
name = "OrderFulFillmentMDB",
activationConfig = { 
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	@ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "HackathonConnectionFactory"),
	@ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "OrderFulFillmentJMSDest"),
})
public class OrderFulFillmentMDB implements MessageListener
{
    
    public void onMessage(Message message)
    {
	
	try
	{
	    System.out.println("Started Order fulfillment processing");
	    System.out.println(message.getJMSMessageID());
	    
	    Thread.sleep(50000);
	    System.out.println("Completed Order fulfillment processing");
	}
	catch (InterruptedException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	catch (JMSException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
}
