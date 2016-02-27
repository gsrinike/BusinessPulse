package com.infosys.tool.business.pulse.task;

import org.springframework.core.task.TaskExecutor;

import com.infosys.tool.business.pulse.businessprocess.BusinessProcess;


public class DataFetchTask {
	
	private BusinessProcess businessProcess; 
	private UploadProcessor processor;
	private TaskExecutor taskExecutor;
	
	
	public DataFetchTask(BusinessProcess businessProcess,UploadProcessor processor,TaskExecutor taskExecutor){
		this.businessProcess=businessProcess;
		this.processor=processor;
		this.taskExecutor=taskExecutor;
	}

	public void run() {
	
		DataFetcher task = new DataFetcher(businessProcess,processor);
		taskExecutor.execute(task);
		
	}

	
}
