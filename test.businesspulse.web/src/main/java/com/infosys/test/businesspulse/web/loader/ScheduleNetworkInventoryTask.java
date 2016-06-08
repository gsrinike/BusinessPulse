package com.infosys.test.businesspulse.web.loader;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleNetworkInventoryTask extends TimerTask{

	private static final Logger LOG =LoggerFactory.getLogger(ScheduleNetworkInventoryTask.class);
			
	
	private Timer timer;
	public ScheduleNetworkInventoryTask(){
		
	}
	public ScheduleNetworkInventoryTask(Timer timer){
		this.timer = timer;
	}
	
	@Override
	public void run(){
		LoadNetworkInventoryService loadNetworkInventoryService = new LoadNetworkInventoryService();
		loadNetworkInventoryService.load();
	}
}
