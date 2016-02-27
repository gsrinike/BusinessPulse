package com.infosys.tool.business.pulse.datamodel;

public class Threshold {
	protected ThresholdType thresholdType;
	public Threshold(ThresholdType thresholdType){
	
		this.thresholdType=thresholdType;
	}
	@Override
	public String toString() {
		return "Threshold [thresholdType=" + thresholdType + "]";
	}
	public ThresholdType getThresholdType() {
		return thresholdType;
	}
	public void setThresholdType(ThresholdType thresholdType) {
		this.thresholdType = thresholdType;
	}
	
}
