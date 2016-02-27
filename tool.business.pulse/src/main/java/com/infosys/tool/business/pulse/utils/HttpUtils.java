package com.infosys.tool.business.pulse.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class HttpUtils
{
	public static String fetchGetResult(String url) throws HttpException,
			IOException
	{
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);
		String body = new String(method.getResponseBody());
		method.releaseConnection();
		return body;

	}
	/**
	 * 
	 * @param url
	 * @param data
	 * @param type can be "application/xml" or "application/json"
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static String post(String url, String data,String type) throws HttpException,
			IOException
	{
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		  
		if (data != null)
		{
			method.setRequestEntity(new StringRequestEntity(data, type,null));
		}
		client.executeMethod(method);
		String response = method.getResponseBodyAsString();
		method.releaseConnection();
		return response;

	}
	
}