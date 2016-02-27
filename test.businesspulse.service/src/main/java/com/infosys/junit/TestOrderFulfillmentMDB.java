package com.infosys.junit;

import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestOrderFulfillmentMDB implements Runnable
{
    
    public static void main(String[] args)
    {
	
	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(50);
	for(int i=0; i<500; i++)
	{
	    TestOrderFulfillmentMDB testMDBObj = new TestOrderFulfillmentMDB();
	    try
	    {
		Thread.sleep(5);
	    }
	    catch (InterruptedException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    executor.submit(testMDBObj);
	}
	executor.shutdown();
    }

    @Override
    public void run()
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
	    ConnectionFactory jmsConnectionFactory = (QueueConnectionFactory) ctx.lookup("HackathonConnectionFactory");
	    System.out.println("Completed ConnectionFactory lookup");
	    
	    
	    {
		try
		{
		    
		    jmsConnection = jmsConnectionFactory.createConnection();
		    System.out.println("Completed jmsConnection creation");
		    jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		    System.out.println("Completed jmsSession creation");
		    jmsMessageProducer = jmsSession.createProducer(orderfulfillmentQueue);
		    System.out.println("Completed jmsMessageProducer creation");
		    
		    Message jmsMsg = jmsSession.createMessage();
		    jmsMsg.setJMSMessageID("Fullfill My Order" + (new Date().getTime()));
		    jmsMessageProducer.send(jmsMsg);
		    System.out.println("Completed jmsMessageProducer send" );
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		finally
		{
		    try
		    {
			jmsMessageProducer.close();
			System.out.println("Closed Producer");
		    }
		    catch (JMSException e1)
		    {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		    }
		    try
		    {
			jmsSession.close();
			System.out.println("Closed Session");
		    }
		    catch (JMSException e)
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    try
		    {
			jmsConnection.close();
			System.out.println("Closed Connection");
		    }
		    catch (JMSException e)
		    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	    }
	    
	}
	catch (NamingException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }
    
}
