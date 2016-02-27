package com.infosys.tool.business.pulse.task;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.tool.business.pulse.datamodel.BusinessProcessData;
import com.infosys.tool.business.pulse.utils.Configuration;
import com.infosys.tool.business.pulse.utils.HttpUtils;

public class DataUploadTask  {
	
	private static final Logger LOG =LoggerFactory.getLogger(DataUploadTask.class);
	private final static String TARGET_URL=Configuration.getInstance().getValue("TARGET_URL");
	
	private BusinessProcessData businessProcessData;
	
	public DataUploadTask(BusinessProcessData businessProcessData){
		this.businessProcessData=businessProcessData;
	}
	
	public void upload() {
		
		setBusinessProcessData(KPICalculator.getInstance().calculate(getBusinessProcessData()));
		List<String>data=getJsonData();
		Iterator<String> iterator= data.iterator();
		String postUrl=TARGET_URL+"/"+getBusinessProcessData().getBusinessProcess()+"/?pretty";
		LOG.trace("postUrl: "+postUrl);
		
		while(iterator.hasNext()){
			
			String postData=iterator.next();
			try {
				HttpUtils.post(postUrl, postData, "application/json");
			} catch (HttpException e) {
				LOG.error("Error while publishing data: "+e);
			} catch (IOException e) {
				LOG.error("Error while publishing data: "+e);
			}
			LOG.trace("postData: "+postData);
			
		}
		
	}
	
	public List<String> getJsonData(){

		return new JsonDataBuilder(getBusinessProcessData()).buildJson();
	}
	

	public BusinessProcessData getBusinessProcessData() {
		return businessProcessData;
	}

	public void setBusinessProcessData(BusinessProcessData businessProcessData) {
		this.businessProcessData = businessProcessData;
	}

}
