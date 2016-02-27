package com.infosys.tool.business.pulse.application;

import java.util.List;


public class ApplicationConfiguration {
	
	private String applicationName;
	
	private List<ApplicationServerConfiguration> applicationServerConfigurations;
	
	public ApplicationConfiguration(String applicationName,List<ApplicationServerConfiguration> applicationServerConfigurations){
		this.setApplicationName(applicationName);
		this.setApplicationServerConfigurations(applicationServerConfigurations);
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public List<ApplicationServerConfiguration> getApplicationServerConfigurations() {
		return applicationServerConfigurations;
	}

	public void setApplicationServerConfigurations(
			List<ApplicationServerConfiguration> applicationServerConfigurations) {
		this.applicationServerConfigurations = applicationServerConfigurations;
	}

	@Override
	public String toString() {
		return "ApplicationConfiguration [applicationName=" + applicationName
				+ ", applicationServerConfigurations="
				+ applicationServerConfigurations + "]";
	}
	

}
