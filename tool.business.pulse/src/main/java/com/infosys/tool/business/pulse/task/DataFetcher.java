package com.infosys.tool.business.pulse.task;

import com.infosys.tool.business.pulse.businessprocess.BusinessProcess;
import com.infosys.tool.business.pulse.datamodel.BusinessProcessData;
import com.infosys.tool.business.pulse.task.businessprocess.BusinessProcessDataFetcher;

public class DataFetcher implements Runnable{
	
	private BusinessProcess businessProcess;
	
	private BusinessProcessData businesProcessData;
	
	private UploadProcessor processor;
	
	public DataFetcher(BusinessProcess businessProcess,UploadProcessor processor){
		this.businessProcess=businessProcess;
		this.processor=processor;
	}

	public BusinessProcessData fetchBusinessProcessData(BusinessProcess businessProcess){
		
		BusinessProcessDataFetcher businessProcessDataFetcher = new BusinessProcessDataFetcher(getBusinessProcess());
		return businessProcessDataFetcher.fetch();
		
	}

	@Override
	public void run() {
		setBusinesProcessData(fetchBusinessProcessData(getBusinessProcess()));
		getProcessor().process(getBusinesProcessData());
	}
	

	public BusinessProcess getBusinessProcess() {
		return businessProcess;
	}

	public void setBusinessProcess(BusinessProcess businessProcess) {
		this.businessProcess = businessProcess;
	}

	public BusinessProcessData getBusinesProcessData() {
		return businesProcessData;
	}

	public void setBusinesProcessData(BusinessProcessData businesProcessData) {
		this.businesProcessData = businesProcessData;
	}

	public UploadProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(UploadProcessor processor) {
		this.processor = processor;
	}
}
