package com.infosys.tool.business.pulse.task;

import java.util.LinkedList;
import java.util.List;

import org.springframework.core.task.TaskExecutor;

import com.infosys.tool.business.pulse.businessprocess.BusinessProcess;
import com.infosys.tool.business.pulse.businessprocess.BusinessProcessConfiguration;



public class DataFetchProcessor {
	List<DataFetchTask> dataFetchTasks;
	TaskExecutor taskExecutor;

	public DataFetchProcessor(BusinessProcessConfiguration configuration,TaskExecutor taskExecutor){
		this.dataFetchTasks = new LinkedList<DataFetchTask>();
		this.taskExecutor=taskExecutor;
		
		for(BusinessProcess businessProcess:configuration.getBusinessProcesses()){
			dataFetchTasks.add(new DataFetchTask(businessProcess,null,taskExecutor));
		}
		
	}
	public void fetch() {
		
		for (DataFetchTask dataFetchTask : dataFetchTasks) {
			dataFetchTask.run();
		}
	}
}
