package com.infosys.tool.business.pulse.task;

import com.infosys.tool.business.pulse.datamodel.BusinessProcessData;

public class DataUploadProcessor implements UploadProcessor{
	
	
	@Override
	public void process(BusinessProcessData businessProcessData ) {
		
		DataUploadTask task = new DataUploadTask(businessProcessData);
		task.upload();
		
	}
	

}
