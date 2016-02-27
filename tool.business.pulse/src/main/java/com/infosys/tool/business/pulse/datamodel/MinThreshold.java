package com.infosys.tool.business.pulse.datamodel;

public class MinThreshold extends Threshold{
	public MinThreshold(String value) {
		super(ThresholdType.MIN);
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
		return "MinThreshold [value=" + value + ", thresholdType="
				+ thresholdType + "]";
	}
}
