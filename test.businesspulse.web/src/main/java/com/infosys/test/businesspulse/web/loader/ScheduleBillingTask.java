package com.infosys.test.businesspulse.web.loader;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleBillingTask extends TimerTask{

	private static final Logger LOG =LoggerFactory.getLogger(ScheduleBillingTask.class);
			
	
	private Timer timer;
	public ScheduleBillingTask(){
		
	}
	public ScheduleBillingTask(Timer timer){
		this.setTimer(timer);
	}
	
	@Override
	public void run(){
		LoadBillingService loadBillingService = new LoadBillingService();
		loadBillingService.load();
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}
