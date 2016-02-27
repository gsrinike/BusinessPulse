package com.infosys.tool.business.pulse.datamodel;

public class ServerEntityData {

private String name;
	
	private String property;
	
	private String value;
	
	private Threshold threshold;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Threshold getThreshold() {
		return threshold;
	}

	public void setThreshold(Threshold threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		return "ServerEntityData [name=" + name + ", property=" + property
				+ ", value=" + value + ", threshold=" + threshold + "]";
	}

}
