package com.infosys.tool.business.pulse.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimestampUtils {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	public static String getCurrentTimestamp(){
		String timestamp= sdf.format(Calendar.getInstance().getTime());
		return timestamp;
		
	}
}
