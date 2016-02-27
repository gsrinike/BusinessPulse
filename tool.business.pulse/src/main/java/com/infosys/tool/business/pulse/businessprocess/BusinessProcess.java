package com.infosys.tool.business.pulse.businessprocess;

import java.util.List;

import com.infosys.tool.business.pulse.application.ApplicationConfiguration;

public class BusinessProcess {
	
	private String businessProcessName;
	
	private List<ApplicationConfiguration> applicationConfigurations;
	
	public String getBusinessProcessName() {
		return businessProcessName;
	}

	public void setBusinessProcessName(String businessProcessName) {
		this.businessProcessName = businessProcessName;
	}

	public BusinessProcess(String businessProcessName,List<ApplicationConfiguration> applicationConfigurations){
		this.businessProcessName=businessProcessName;
		this.setApplicationConfigurations(applicationConfigurations);
	}

	public List<ApplicationConfiguration> getApplicationConfigurations() {
		return applicationConfigurations;
	}

	public void setApplicationConfigurations(
			List<ApplicationConfiguration> applicationConfigurations) {
		this.applicationConfigurations = applicationConfigurations;
	}

	@Override
	public String toString() {
		return "BusinessProcess [businessProcessName=" + businessProcessName
				+ ", applicationConfigurations=" + applicationConfigurations
				+ "]";
	}
	
	
}
