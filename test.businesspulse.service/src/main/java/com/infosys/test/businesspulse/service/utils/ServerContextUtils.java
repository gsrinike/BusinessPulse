package com.infosys.test.businesspulse.service.utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerContextUtils {
	
	private static final ServerContextUtils INSTANCE = new ServerContextUtils();
	private static final Logger LOG =LoggerFactory.getLogger(ServerContextUtils.class);
	private Context context=null;
	
	private ServerContextUtils(){
		try{
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			env.put(Context.PROVIDER_URL, "t3://localhost:7001");
			this.context= new InitialContext(env);
		}catch(Exception e){
			LOG.error("ERROR while creating Initial Context", e);
		}		
	}
	
	public static ServerContextUtils getInstance(){
		return INSTANCE;
	}

	public Context getContext() {
		return context;
	}


}
