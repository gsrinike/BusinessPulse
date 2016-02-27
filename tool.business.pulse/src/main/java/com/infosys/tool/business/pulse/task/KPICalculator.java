package com.infosys.tool.business.pulse.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infosys.tool.business.pulse.datamodel.BusinessProcessData;

public class KPICalculator {

	private static final Logger LOG =LoggerFactory.getLogger(KPICalculator.class);
	
	private static KPICalculator INSTANCE = new KPICalculator();
	
	public static KPICalculator getInstance(){
		return INSTANCE;
	}
	
	public BusinessProcessData calculate(BusinessProcessData businessProcessData){
		
		//Enrich BusinessProcessData with KPI value
		
		return businessProcessData;
	}

}
