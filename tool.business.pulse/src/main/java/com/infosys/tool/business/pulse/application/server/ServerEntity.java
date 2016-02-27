package com.infosys.tool.business.pulse.application.server;

import com.infosys.tool.business.pulse.datamodel.Threshold;

public class ServerEntity {

	private String property;
	
	private String name;
	
	private Threshold threshold;
	

	public ServerEntity(String name,String property,Threshold threshold){
		this.property=property;
		this.name=name;
		this.setThreshold(threshold);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Threshold getThreshold() {
		return threshold;
	}

	public void setThreshold(Threshold threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		return "ServerEntity [property=" + property + ", name=" + name
				+ ", threshold=" + threshold + "]";
	}

	
	
	
	
}
