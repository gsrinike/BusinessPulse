package com.infosys.test.businesspulse.web.servlet;

import java.io.IOException;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.test.businesspulse.web.loader.ScheduleBillingTask;
import com.infosys.test.businesspulse.web.loader.ScheduleNetworkInventoryTask;
import com.infosys.test.businesspulse.web.loader.ScheduleOrderFulfillentTask;

@WebServlet(asyncSupported = false, name = "StartupServlet", urlPatterns = { "/startup" }, loadOnStartup=1)
public class StartupServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG =LoggerFactory.getLogger(StartupServlet.class);
	
	public void init()
    {
		
		 LOG.trace("Scheduling Orderfulfilment Task");
    	 Timer timer = new Timer() ;
	     ScheduleOrderFulfillentTask scheduleOrderFulfillentTask = new ScheduleOrderFulfillentTask(timer);
	     int firstSartOF = 1000*60*1 ; // after one minute of server restart.
	     int periodOF = 1000*60*1 ; // period is every 1 min
	     timer.schedule(scheduleOrderFulfillentTask, firstSartOF, periodOF) ;//the time specified in millisecond.
	    
	     LOG.trace("Scheduling Billing Task");
	     ScheduleBillingTask scheduleBillingTask = new ScheduleBillingTask(timer);
	     int firstSartB = 1000*60*1 ; // after one minute of server restart.
	     int periodB = 1000*30 ; // period is every 30 sec
	     timer.schedule(scheduleBillingTask, firstSartB, periodB) ;//the time specified in millisecond.
	     
	     LOG.trace("Scheduling NetworkInventory Task");
	     ScheduleNetworkInventoryTask scheduleNetworkInventoryTask = new ScheduleNetworkInventoryTask(timer);
	     int firstSartN = 1000*60*1 ; // after one minute of server restart.
	     int periodN = 1000*30 ; // period is every 30 sec
	     timer.schedule(scheduleNetworkInventoryTask, firstSartN, periodN) ;//the time specified in millisecond.
	     
	     
    }

    /**
     * doProcess method is called each time the user gets/posts an event from the user
     * inteface.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @throws ServletException
     */
    public void doProcess(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
    	init();
    	response.sendRedirect("index.html");
    }
}
