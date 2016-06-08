package com.infosys.test.businesspulse.web.loader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

	private ExecutorService executor=null;
	
	Loader(int min,int max){
		this.executor = Executors.newFixedThreadPool(randomWithRange(min,max));
	}
	
	protected int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}
	
	protected ExecutorService getExecutor(){
		return this.executor;
	}
	
	
}
