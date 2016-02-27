package com.infosys.test.businesspulse.service.ejb;

import javax.ejb.Remote;

@Remote
public interface BillingService {
	
	void syncBillingInfo(String customerNumber);

}
