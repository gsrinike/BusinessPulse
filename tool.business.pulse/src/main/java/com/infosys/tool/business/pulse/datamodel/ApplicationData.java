package com.infosys.tool.business.pulse.datamodel;

import java.util.List;

public class ApplicationData {

	private String name;
	private List<ServerData> serverDatas;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ServerData> getServerDatas() {
		return serverDatas;
	}
	public void setServerDatas(List<ServerData> serverDatas) {
		this.serverDatas = serverDatas;
	}
	@Override
	public String toString() {
		return "ApplicationData [name=" + name + ", serverDatas=" + serverDatas
				+ "]";
	}
}
