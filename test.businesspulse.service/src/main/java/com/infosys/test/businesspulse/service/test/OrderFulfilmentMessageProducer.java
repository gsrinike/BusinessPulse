package com.infosys.test.businesspulse.service.test;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.test.businesspulse.service.utils.ServerContextUtils;

public class OrderFulfilmentMessageProducer {
	private static final Logger LOG =LoggerFactory.getLogger(OrderFulfilmentMessageProducer.class);
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.trace("Test");
		OrderFulfilmentMessageProducer test = new OrderFulfilmentMessageProducer();
		test.pushMessage();
		LOG.trace("Message posted");

	}
	
	
	
	
	public void pushMessage(){
		try{
		
		ConnectionFactory jmsConnectionFactory = (QueueConnectionFactory) ServerContextUtils.getInstance().getContext().lookup("OrderFulFillmentConnectionFactory");
		Connection jmsConnection = jmsConnectionFactory.createConnection();
	    Session jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	    Destination orderfulfillmentQueue = (Destination)  ServerContextUtils.getInstance().getContext().lookup("OrderFulFillmentJMSDest");
	    MessageProducer jmsMessageProducer = jmsSession.createProducer(orderfulfillmentQueue);
	    
	    Message jmsMsg = jmsSession.createMessage();
	    jmsMsg.setJMSMessageID("Fullfill My Order" + (new Date().getTime()));
	    jmsMessageProducer.send(jmsMsg);
	    
	    jmsMessageProducer.close();
	    jmsSession.close();
	    jmsConnection.close();
		
		}catch(Exception e){
			LOG.error("ERROR while Pushing message", e);
		}
	}
}	
