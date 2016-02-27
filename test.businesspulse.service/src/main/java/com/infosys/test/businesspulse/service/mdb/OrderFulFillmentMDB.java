package com.infosys.test.businesspulse.service.mdb;

import java.util.Hashtable;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.infosys.test.businesspulse.service.ejb.BillingService;

@MessageDriven(mappedName = "OrderFulFillmentJMSDest",
        name = "OrderFulFillmentMDB",
        activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(propertyName = "connectionFactoryJndiName", propertyValue = "HackathonConnectionFactory"),
                @ActivationConfigProperty(propertyName = "destinationJndiName", propertyValue = "OrderFulFillmentJMSDest"), })
public class OrderFulFillmentMDB implements MessageListener
{
    
    public void onMessage(Message message)
    {
	
	try
	{
	    System.out.println("Started Order fulfillment processing");
	    System.out.println(message.getJMSMessageID());
	    
	    Hashtable<String, String> env = new Hashtable<String, String>();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
	    env.put(Context.PROVIDER_URL, "t3://localhost:7001");
	    Context ctx = new InitialContext(env);
	    //Context ctx = new InitialContext();
	    BillingService billingProvider = (BillingService) ctx.lookup("BillingServiceProvider#com.infosys.test.businesspulse.service.ejb.BillingService");
	    System.out.println("After Context Lookup of Billing service. Invoking Billing service EJB");
	    billingProvider.syncBillingInfo(message.getJMSMessageID());
	    System.out.println("After Billing service EJB");
	    
	    Thread.sleep(5000);
	    System.out.println("Completed Order fulfillment processing");
	}
	catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
}
