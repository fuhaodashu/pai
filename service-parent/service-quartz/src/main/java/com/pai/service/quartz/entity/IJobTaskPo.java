package com.pai.service.quartz.entity;

public interface IJobTaskPo {
	public String getId();
	
	public String getName();

	public String getBean();
	
	public String getMethod();
	
	public String getGroupName();
	
	public String getType();
	
	public String getExpression();
}
