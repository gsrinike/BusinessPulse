package com.infosys.tool.business.pulse.datamodel;

public class BetweenThreshold extends Threshold {
	private String fromValue;
	private String toValue;
	
	public BetweenThreshold(String fromValue, String toValue) {
		super(ThresholdType.BETWEEN);
		this.fromValue=fromValue;
		this.toValue=toValue;
		
	}

	public String getToValue() {
		return toValue;
	}

	public void setToValue(String toValue) {
		this.toValue = toValue;
	}

	public String getFromValue() {
		return fromValue;
	}

	public void setFromValue(String fromValue) {
		this.fromValue = fromValue;
	}

	@Override
	public String toString() {
		return "BetweenThreshold [fromValue=" + fromValue + ", toValue="
				+ toValue + ", thresholdType=" + thresholdType + "]";
	}
}
