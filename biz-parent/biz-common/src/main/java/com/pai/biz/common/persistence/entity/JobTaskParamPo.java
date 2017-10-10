package com.pai.biz.common.persistence.entity;

import com.pai.service.quartz.entity.IJobTaskParamPo;

/**
 * 对象功能:任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参 entity对象
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:12
 */
public class JobTaskParamPo extends JobTaskParamTbl implements IJobTaskParamPo{
	
	private String name; //job名称
	
	private String bean; //job类名

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}
	
}