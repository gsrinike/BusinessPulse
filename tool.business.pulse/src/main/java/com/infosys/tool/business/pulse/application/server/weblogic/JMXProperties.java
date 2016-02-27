package com.infosys.tool.business.pulse.application.server.weblogic;

import java.util.Iterator;
import java.util.List;

public class JMXProperties {
	
	private String mBeanName;
	
	private List<String> properties;
	
	public JMXProperties(String mBeanName,List<String> properties){
		this.mBeanName=mBeanName;
		this.properties=properties;
	}

	public String getMBeanName() {
		return mBeanName;
	}

	public void setMBeanName(String beanName) {
		mBeanName = beanName;
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(List<String> properties) {
		this.properties = properties;
	}
	
	public String toString(){
		String toString="";
		toString="mBeanName : "+mBeanName;
		Iterator<String> iterator= properties.iterator();
		while(iterator.hasNext()){
			
			toString=toString+", "+iterator.next();
			
		}
		return toString;

	}
	

}
