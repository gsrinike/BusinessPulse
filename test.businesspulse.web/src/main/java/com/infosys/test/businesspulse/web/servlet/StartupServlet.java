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

@WebServlet(asyncSupported = false, name = "StartupServlet")
public class StartupServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG =LoggerFactory.getLogger(StartupServlet.class);
	
	private ScheduleOrderFulfillentTask scheduleOrderFulfillentTask;
	private ScheduleBillingTask scheduleBillingTask;
	private ScheduleNetworkInventoryTask scheduleNetworkInventoryTask;
	
	private ScheduleOrderFulfillentTask getScheduleOrderFulfillentTask() {
		return scheduleOrderFulfillentTask;
	}

	private void setScheduleOrderFulfillentTask(
			ScheduleOrderFulfillentTask scheduleOrderFulfillentTask) {
		this.scheduleOrderFulfillentTask = scheduleOrderFulfillentTask;
	}

	private ScheduleBillingTask getScheduleBillingTask() {
		return scheduleBillingTask;
	}

	private void setScheduleBillingTask(ScheduleBillingTask scheduleBillingTask) {
		this.scheduleBillingTask = scheduleBillingTask;
	}

	private ScheduleNetworkInventoryTask getScheduleNetworkInventoryTask() {
		return scheduleNetworkInventoryTask;
	}

	private void setScheduleNetworkInventoryTask(
			ScheduleNetworkInventoryTask scheduleNetworkInventoryTask) {
		this.scheduleNetworkInventoryTask = scheduleNetworkInventoryTask;
	}

	public void start()
    {
		
		 LOG.trace("Scheduling Orderfulfilment Task");
    	 Timer timer = new Timer() ;
    	 setScheduleOrderFulfillentTask(new ScheduleOrderFulfillentTask(timer));
	     int firstSartOF = 0; // after one minute of server restart.
	     int periodOF = 1000*60*1 ; // period is every 1 min
	     timer.schedule(getScheduleOrderFulfillentTask(), firstSartOF, periodOF) ;//the time specified in millisecond.
	    
	     LOG.trace("Scheduling Billing Task");
	    setScheduleBillingTask(new ScheduleBillingTask(timer));
	     int firstSartB = 0; // after one minute of server restart.
	     int periodB = 1000*30 ; // period is every 30 sec
	     timer.schedule(getScheduleBillingTask(), firstSartB, periodB) ;//the time specified in millisecond.
	     
	     LOG.trace("Scheduling NetworkInventory Task");
	     setScheduleNetworkInventoryTask(new ScheduleNetworkInventoryTask(timer));
	     int firstSartN = 0; // after one minute of server restart.
	     int periodN = 1000*30 ; // period is every 30 sec
	     timer.schedule(getScheduleNetworkInventoryTask(), firstSartN, periodN) ;//the time specified in millisecond.
	     
	     
    }
	
	 @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException
    {
        // TODO Auto-generated method stub
        super.service(arg0, arg1);
        LOG.trace("Started Startup Servlet service");
        
    }

    /**
     * doProcess method is called each time the user gets/posts an event from the user
     * inteface.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException
    {
    	if(request.getRequestURI().contains("cancel")){
    		cancel();
    	}
    	else if(request.getRequestURI().contains("start")){
    		start();
    	}
    	response.sendRedirect("index.html");
    }
    
    
    private void cancel(){
    	getScheduleOrderFulfillentTask().cancel();
    	getScheduleOrderFulfillentTask().getTimer().cancel();
    	getScheduleOrderFulfillentTask().getTimer().purge();
    	
    	
    	getScheduleBillingTask().cancel();
    	getScheduleBillingTask().getTimer().cancel();
    	getScheduleBillingTask().getTimer().purge();
    	
    	getScheduleNetworkInventoryTask().cancel();
    	getScheduleNetworkInventoryTask().getTimer().cancel();
    	getScheduleNetworkInventoryTask().getTimer().purge();
    }
}
