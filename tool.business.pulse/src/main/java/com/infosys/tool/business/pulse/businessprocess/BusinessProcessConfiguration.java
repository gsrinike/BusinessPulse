package com.infosys.tool.business.pulse.businessprocess;

import java.util.List;

public class BusinessProcessConfiguration {
	
	private List<BusinessProcess> businessProcesses;
	public BusinessProcessConfiguration(List<BusinessProcess> businessProcesses){
		this.setBusinessProcesses(businessProcesses);
	}
	public List<BusinessProcess> getBusinessProcesses() {
		return businessProcesses;
	}
	
	public void setBusinessProcesses(List<BusinessProcess> businessProcesses) {
		this.businessProcesses = businessProcesses;
	}
	
	@Override
	public String toString() {
		return "BusinessProcessConfiguration [businessProcesses="
				+ businessProcesses + "]";
	}
	
	

}
