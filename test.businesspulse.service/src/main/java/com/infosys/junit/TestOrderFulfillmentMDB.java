package com.infosys.junit;

import java.util.Hashtable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestOrderFulfillmentMDB
{
    
    public static void main(String[] args)
    {
	Hashtable<String, String> env = new Hashtable<String, String>();
	env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
	env.put(Context.PROVIDER_URL, "t3://localhost:7001");
	Context ctx = null;
	Connection jmsConnection = null;
	Session jmsSession = null;
	MessageProducer jmsMessageProducer = null;
	try
	{
	    System.out.println("Started Lookup of queue");
	    ctx = new InitialContext(env);
	    Destination orderfulfillmentQueue = (Destination) ctx.lookup("OrderFulFillmentJMSDest");
	    System.out.println("Completed queue lookup. Proceeding ConnectionFactory lookup");
	    ConnectionFactory jmsConnectionFactory = (ConnectionFactory) ctx.lookup("HackathonConnectionFactory");
	    System.out.println("Completed ConnectionFactory lookup");
	    jmsConnection = jmsConnectionFactory.createConnection();
	    System.out.println("Completed jmsConnection creation");
	    jmsSession = jmsConnection.createSession(true, Session.AUTO_ACKNOWLEDGE);
	    System.out.println("Completed jmsSession creation");
	    jmsMessageProducer = jmsSession.createProducer(orderfulfillmentQueue);
	    System.out.println("Completed jmsMessageProducer creation");
	    for (;;)
	    {
		try
		{
		    Thread.sleep(10);
		    
		    TextMessage txtMsg = jmsSession.createTextMessage();
		    txtMsg.setText("Fullfill My Order");
		    jmsMessageProducer.send(txtMsg);
		    System.out.println("Completed jmsMessageProducer send");
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	    }
	    
	}
	catch (NamingException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	catch (JMSException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	finally
	{
	    try
	    {
		jmsMessageProducer.close();
	    }
	    catch (JMSException e1)
	    {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	    try
	    {
		jmsSession.close();
	    }
	    catch (JMSException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    try
	    {
		jmsConnection.close();
	    }
	    catch (JMSException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	    
	}
    }
    
}
