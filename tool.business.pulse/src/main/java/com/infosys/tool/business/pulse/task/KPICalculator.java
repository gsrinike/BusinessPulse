package com.infosys.tool.business.pulse.task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.tool.business.pulse.datamodel.ApplicationData;
import com.infosys.tool.business.pulse.datamodel.BetweenThreshold;
import com.infosys.tool.business.pulse.datamodel.BusinessProcessData;
import com.infosys.tool.business.pulse.datamodel.MaxThreshold;
import com.infosys.tool.business.pulse.datamodel.MinThreshold;
import com.infosys.tool.business.pulse.datamodel.ServerData;
import com.infosys.tool.business.pulse.datamodel.ServerEntityData;
import com.infosys.tool.business.pulse.datamodel.ServerPropertyData;
import com.infosys.tool.business.pulse.datamodel.Threshold;

public class KPICalculator {

	private static final Logger LOG =LoggerFactory.getLogger(KPICalculator.class);
	
	private List<Double> serverEntityIndex = new LinkedList<Double>();
	
	private static KPICalculator INSTANCE = new KPICalculator();
	
	public static KPICalculator getInstance(){
		return INSTANCE;
	}
	
	public BusinessProcessData calculate(BusinessProcessData businessProcessData){
		
		//Enrich BusinessProcessData with KPI value

		for (ApplicationData applicationData : businessProcessData.getApplicationDatas()) {
			LOG.trace("Application Data: "+applicationData);
			processApplicationData(applicationData);
		}
		
		businessProcessData.setBusinessProcessIndex(Double.toString(average(serverEntityIndex)));
		
		return businessProcessData;
	}
	
	
	private void processApplicationData(ApplicationData applicationData) {
		
		for(ServerData serverData:applicationData.getServerDatas()){
			processServerData(applicationData.getName(),serverData);
		}
	}
	
	private void processServerData(String applicationName,ServerData serverData) {
		LOG.trace("Application Name: "+applicationName);
		LOG.trace("ServerData: "+serverData);
		
		for(ServerPropertyData serverPropertyData:serverData.getServerProperties()){
			processServerPropertyData(applicationName,serverData.getName(),serverData.getServerType().toString(),serverPropertyData);
		}
	}

	private void processServerPropertyData(String applicationName, String serverName,
			String serverType, ServerPropertyData serverPropertyData) {
		LOG.trace("Server Property "+serverPropertyData);
		for(ServerEntityData serverEntityData:serverPropertyData.getServerEntities()){
			processServerEntityData(applicationName,serverName,serverType,serverPropertyData.getName(),serverEntityData);
		}
	}
	
	/**
	 * Calculates the performance index based on the threshold values
	 * @param applicationName
	 * @param serverName
	 * @param serverType
	 * @param serverPropertyName
	 * @param serverEntityData
	 */
	private void processServerEntityData(String applicationName,
		String serverName, String serverType, String serverPropertyName,
		ServerEntityData serverEntityData) {
		Double entityIndex = 100.0;
		Threshold threshold = serverEntityData.getThreshold();
		HashMap<String, String> thresholdValue = getThresholdValue(threshold);
		Double value = Double.valueOf(serverEntityData.getValue());
		switch(threshold.getThresholdType()){
		case BETWEEN:
			Double dFrom = Double.valueOf((String)thresholdValue.get("from"));
			Double dTo = Double.valueOf((String)thresholdValue.get("to"));
			if(value<dFrom){
				Double diff = dFrom - value;
				entityIndex = entityIndex - diff;	
			}
			if(value>dTo){
				Double diff = value - dTo;
				entityIndex = entityIndex - diff;
			}
			break;		
		case MIN:
			Double dMin = Double.valueOf((String)thresholdValue.get("min"));
			if(value < dMin){
				Double diff = dMin - value;
				entityIndex = entityIndex - diff;
			}
			break;
		case MAX:
		default:
			Double dMax = Double.valueOf((String)thresholdValue.get("min"));
			if(value > dMax){
				Double diff = value - dMax;
				entityIndex = entityIndex - diff;
			}
		}
		serverEntityIndex.add(entityIndex);
		}
		
	/**
	 * Get the threshold value depending on the threshold type
	 * @param threshold
	 * @return
	 */
	private HashMap<String, String> getThresholdValue(Threshold threshold){
		HashMap<String, String> thresholdValue = new HashMap<String, String>();
		switch(threshold.getThresholdType()){
		case BETWEEN:
			String fromValue = ((BetweenThreshold)threshold).getFromValue();
			String toValue = ((BetweenThreshold)threshold).getToValue();
			thresholdValue.put("from",fromValue);
			thresholdValue.put("to",toValue);
			break;		
		case MIN:
			String minValue = ((MinThreshold)threshold).getValue();
			thresholdValue.put("min", minValue);
			break;
		case MAX:
		default:
			String maxValue = ((MaxThreshold)threshold).getValue();
			thresholdValue.put("max", maxValue);
		}
		
		return  thresholdValue;
		
	}
	
	/**
	 * Calculate the average of the list
	 * @param list
	 * @return
	 */
	public static double average(List<Double> list) {

	    if (list == null || list.isEmpty())
	        return 0.0;
	    long sum = 0;
	    int n = list.size();
	    for (int i = 0; i < n; i++)
	        sum += list.get(i);
	    return ((double) sum) / n;
	}
	
}
