package com.infosys.tool.business.pulse.task;

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

public class JsonDataBuilder {
	private static final Logger LOG =LoggerFactory.getLogger(JsonDataBuilder.class);
	// JSON Conversion Constants
	private final static String COMMA = ",";
	private final static String DOUBLE_QUOTE = "\"";
	private final static String COLON = ":";

	private final static String dataPushStart = "{";
	private final static String dataPushEnd = "}";

	private List<String> jsonDatas = new LinkedList<String>();

	private BusinessProcessData businessProcessData;

	public JsonDataBuilder(BusinessProcessData businessProcessData) {
		this.businessProcessData = businessProcessData;
	}

	public List<String> buildJson() {

		for (ApplicationData applicationData : getBusinessProcessData()
				.getApplicationDatas()) {
			LOG.trace("Application Data: "+applicationData);
			processApplicationData(applicationData);
		}

		return getJsonDatas();
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

	private void processServerEntityData(String applicationName,
			String serverName, String serverType, String serverPropertyName,
			ServerEntityData serverEntityData) {
		
		StringBuilder data = new StringBuilder(dataPushStart);
		data = data.append(DOUBLE_QUOTE).append("Timestamp").append(
				DOUBLE_QUOTE).append(COLON).append(DOUBLE_QUOTE).append(getBusinessProcessData().getTimeStamp()).append(DOUBLE_QUOTE).append(COMMA);
		data = data.append(DOUBLE_QUOTE).append("BusinessProcess").append(
				DOUBLE_QUOTE).append(COLON).append(DOUBLE_QUOTE).append(getBusinessProcessData().getBusinessProcess()).append(DOUBLE_QUOTE).append(COMMA);
		data = data.append(DOUBLE_QUOTE).append("BusinessProcess").append(
				DOUBLE_QUOTE).append(COLON).append(getBusinessProcessData().getBusinessProcessIndex()).append(COMMA);
		
		data = data.append(DOUBLE_QUOTE).append("Application").append(
				DOUBLE_QUOTE).append(COLON).append(DOUBLE_QUOTE).append(applicationName).append(DOUBLE_QUOTE).append(COMMA);
		data = data.append(DOUBLE_QUOTE).append("Server").append(
				DOUBLE_QUOTE).append(COLON).append(DOUBLE_QUOTE).append(serverName).append(DOUBLE_QUOTE).append(COMMA);
		data = data.append(DOUBLE_QUOTE).append("ServerType").append(
				DOUBLE_QUOTE).append(COLON).append(DOUBLE_QUOTE).append(serverType).append(DOUBLE_QUOTE).append(COMMA);
		data = data.append(DOUBLE_QUOTE).append("ServerProperty").append(
				DOUBLE_QUOTE).append(COLON).append(DOUBLE_QUOTE).append(serverPropertyName).append(DOUBLE_QUOTE).append(COMMA);
		data = data.append(DOUBLE_QUOTE).append("ServerEntity").append(
				DOUBLE_QUOTE).append(COLON).append(DOUBLE_QUOTE).append(serverEntityData.getName()).append(DOUBLE_QUOTE).append(COMMA);
		data = data.append(DOUBLE_QUOTE).append("ServerEntityFor").append(
				DOUBLE_QUOTE).append(COLON).append(DOUBLE_QUOTE).append(serverEntityData.getProperty()).append(DOUBLE_QUOTE).append(COMMA);
		data = data.append(DOUBLE_QUOTE).append("ServerEntityValue").append(
				DOUBLE_QUOTE).append(COLON).append(serverEntityData.getValue()).append(COMMA);

		
		data= processThresholdData(data,serverEntityData.getThreshold());
		data = data.append(dataPushEnd);
		
		getJsonDatas().add(data.toString());
				
	}

	private StringBuilder processThresholdData(StringBuilder data,
			Threshold threshold) {
		
		data = data.append(DOUBLE_QUOTE).append("ThresholdType").append(
				DOUBLE_QUOTE).append(COLON).append(DOUBLE_QUOTE).append(threshold.getThresholdType()).append(DOUBLE_QUOTE).append(COMMA);
		
		switch(threshold.getThresholdType()){
		case BETWEEN:
			data = data.append(DOUBLE_QUOTE).append("ThresholdFromValue").append(
					DOUBLE_QUOTE).append(COLON).append(((BetweenThreshold)threshold).getFromValue()).append(COMMA);
			data = data.append(DOUBLE_QUOTE).append("ThresholdToValue").append(
					DOUBLE_QUOTE).append(COLON).append(((BetweenThreshold)threshold).getToValue());
			break;		
		case MIN:
			data = data.append(DOUBLE_QUOTE).append("ThresholdValue").append(
					DOUBLE_QUOTE).append(COLON).append(((MinThreshold)threshold).getValue());
			break;
		case MAX:
		default:
			data = data.append(DOUBLE_QUOTE).append("ThresholdValue").append(
					DOUBLE_QUOTE).append(COLON).append(((MaxThreshold)threshold).getValue());
		
		}
		
		return data;
	}

	private BusinessProcessData getBusinessProcessData() {
		return businessProcessData;
	}

	public List<String> getJsonDatas() {
		return jsonDatas;
	}

	public void setJsonDatas(List<String> jsonDatas) {
		this.jsonDatas = jsonDatas;
	}

}
