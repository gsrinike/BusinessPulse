package com.infosys.tool.business.pulse.datamodel;

public class MaxThreshold extends Threshold{
	
	public MaxThreshold(String value) {
		super(ThresholdType.MAX);
		this.value=value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MaxThreshold [value=" + value + ", thresholdType="
				+ thresholdType + "]";
	}
	
}
