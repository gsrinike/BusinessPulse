package com.infosys.test.businesspulse.web.loader;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleOrderFulfillentTask extends TimerTask{

	private static final Logger LOG =LoggerFactory.getLogger(ScheduleOrderFulfillentTask.class);
			
	
	private Timer timer;
	public ScheduleOrderFulfillentTask(){
		
	}
	public ScheduleOrderFulfillentTask(Timer timer){
		this.timer = timer;
	}
	
	@Override
	public void run(){
		LoadOrderFulfillment loadOrderFulfillment = new LoadOrderFulfillment();
		loadOrderFulfillment.load();
		
	}
}
