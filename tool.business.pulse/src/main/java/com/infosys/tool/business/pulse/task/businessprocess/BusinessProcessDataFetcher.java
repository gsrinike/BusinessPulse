package com.infosys.tool.business.pulse.task.businessprocess;

import java.util.LinkedList;
import java.util.List;

import com.infosys.tool.business.pulse.utils.TimestampUtils;
import com.infosys.tool.business.pulse.application.ApplicationConfiguration;
import com.infosys.tool.business.pulse.businessprocess.BusinessProcess;
import com.infosys.tool.business.pulse.datamodel.ApplicationData;
import com.infosys.tool.business.pulse.datamodel.BusinessProcessData;

public class BusinessProcessDataFetcher {
	
	private BusinessProcess businessProcess;
	private BusinessProcessData businessProcessData= new BusinessProcessData();
	
	public BusinessProcessDataFetcher(BusinessProcess businessProcess){
		this.setBusinessProcess(businessProcess);
		for(ApplicationConfiguration applicationConfiguration:this.getBusinessProcess().getApplicationConfigurations()){
			//do application fetch
		}
		
	}
	
	public BusinessProcessData fetch(){
		
		List<ApplicationData> applicationDatas= new LinkedList<ApplicationData>();
		
		//Start Tasks 
		
		
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
