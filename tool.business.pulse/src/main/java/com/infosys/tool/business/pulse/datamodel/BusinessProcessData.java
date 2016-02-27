package com.infosys.tool.business.pulse.datamodel;

import java.util.List;

public class BusinessProcessData {
	private String timeStamp;
	
	private String businessProcess;
	
	private List<ApplicationData> applicationDatas;
	
	private String businessProcessIndex;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(String businessProcess) {
		this.businessProcess = businessProcess;
	}

	public List<ApplicationData> getApplicationDatas() {
		return applicationDatas;
	}

	public void setApplicationDatas(List<ApplicationData> applicationDatas) {
		this.applicationDatas = applicationDatas;
	}
	
	

	@Override
	public String toString() {
		return "BusinessProcessData [timeStamp=" + timeStamp
				+ ", businessProcess=" + businessProcess
				+ ", applicationDatas=" + applicationDatas
				+ ", businessProcessIndex=" + businessProcessIndex + "]";
	}

	public String getBusinessProcessIndex() {
		return businessProcessIndex;
	}

	public void setBusinessProcessIndex(String businessProcessIndex) {
		this.businessProcessIndex = businessProcessIndex;
	}
	
	
}
