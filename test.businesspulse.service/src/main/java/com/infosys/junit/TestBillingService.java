package com.infosys.junit;

import java.util.Hashtable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.infosys.test.businesspulse.service.ejb.BillingService;
import com.infosys.test.businesspulse.service.ejb.BillingServiceProvider;

public class TestBillingService implements Runnable
{
    
    public static void main(String[] args)
    {
	
	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(50);
	for(int i=0; i<50; i++)
	{
	    TestBillingService testEJBObj = new TestBillingService();
	    try
	    {
		Thread.sleep(5);
	    }
	    catch (InterruptedException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    executor.submit(testEJBObj);
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
	try
	{
	    
	    ctx = new InitialContext(env);
	    System.out.println("Initial Context created");
	    BillingService billingProvider = (BillingService) ctx.lookup("BillingServiceProvider#com.infosys.test.businesspulse.service.ejb.BillingService");
	    System.out.println("lookup successful");
	    System.out.println("Calling EJB method . . .");
	    billingProvider.syncBillingInfo("123456789");
	}
	catch (NamingException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	finally
	{
	    try
	    {
		ctx.close();
	    }
	    catch (NamingException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	
    }
    
}
