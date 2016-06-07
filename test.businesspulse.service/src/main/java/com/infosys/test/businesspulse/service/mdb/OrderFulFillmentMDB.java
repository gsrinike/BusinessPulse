package com.infosys.test.businesspulse.service.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.test.businesspulse.service.ejb.BillingService;
import com.infosys.test.businesspulse.service.utils.ServerContextUtils;

@MessageDriven(mappedName = "OrderFulFillmentJMSDest",
        name = "OrderFulFillmentMDB",
        activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "OrderFulFillmentConnectionFactory"),
                @ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "OrderFulFillmentJMSDest"), })
public class OrderFulFillmentMDB implements MessageListener
{
	private static final Logger LOG =LoggerFactory.getLogger(OrderFulFillmentMDB.class);
    public void onMessage(Message message)
    {
	
	try
	{
	    LOG.trace("Started Order fulfillment processing");
	    LOG.trace(message.getJMSMessageID());
	    BillingService billingService = (BillingService)  ServerContextUtils.getInstance().getContext().lookup("BillingServiceProvider#com.infosys.test.businesspulse.service.ejb.BillingService");
	    LOG.trace("After Context Lookup of Billing service. Invoking Billing service EJB");
	    billingService.syncBillingInfo(message.getJMSMessageID());
	    LOG.trace("After Billing service EJB");
	    
	    Thread.sleep(5000);
	    LOG.trace("Completed Order fulfillment processing");
	}
	catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
}
