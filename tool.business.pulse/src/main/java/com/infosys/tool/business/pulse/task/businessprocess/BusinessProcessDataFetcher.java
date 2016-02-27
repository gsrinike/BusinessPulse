package com.infosys.tool.business.pulse.task.businessprocess;

import java.util.LinkedList;
import java.util.List;

import com.infosys.tool.business.pulse.application.ApplicationConfiguration;
import com.infosys.tool.business.pulse.businessprocess.BusinessProcess;
import com.infosys.tool.business.pulse.datamodel.ApplicationData;
import com.infosys.tool.business.pulse.datamodel.BusinessProcessData;
import com.infosys.tool.business.pulse.task.businessprocess.application.ApplicationDataFetcher;
import com.infosys.tool.business.pulse.utils.TimestampUtils;

public class BusinessProcessDataFetcher {
	
	private BusinessProcess businessProcess;
	private List<ApplicationDataFetcher>  appDataFetchTasks= new LinkedList<ApplicationDataFetcher>();
	private BusinessProcessData businessProcessData= new BusinessProcessData();
	
	public BusinessProcessDataFetcher(BusinessProcess businessProcess){
		this.setBusinessProcess(businessProcess);
		for(ApplicationConfiguration applicationConfiguration:this.getBusinessProcess().getApplicationConfigurations()){
			appDataFetchTasks.add(new ApplicationDataFetcher(applicationConfiguration));
		}
		
	}
	
	public BusinessProcessData fetch(){
		
		List<ApplicationData> applicationDatas= new LinkedList<ApplicationData>();
		
		//Start Tasks 
		for(ApplicationDataFetcher task:appDataFetchTasks){
			applicationDatas.add(task.run());
		}
		
		enrich(applicationDatas);
		return getBusinessProcessData();
		
	}
	
	
	private BusinessProcess getBusinessProcess() {
		return businessProcess;
	}

	private void setBusinessProcess(BusinessProcess businessProcess) {
		this.businessProcess = businessProcess;
	}

	private void enrich(List<ApplicationData> applicationDatas){
		
		getBusinessProcessData().setBusinessProcess(getBusinessProcess().getBusinessProcessName());
		getBusinessProcessData().setTimeStamp(TimestampUtils.getCurrentTimestamp());
		businessProcessData.setApplicationDatas(applicationDatas);
		
	}

	public BusinessProcessData getBusinessProcessData() {
		return businessProcessData;
	}

	public void setBusinessProcessData(BusinessProcessData businessProcessData) {
		this.businessProcessData = businessProcessData;
	}
	
	
}
