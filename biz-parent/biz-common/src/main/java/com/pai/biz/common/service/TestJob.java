package com.pai.biz.common.service;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import com.pai.service.quartz.BaseJob;

@Component
public class TestJob extends BaseJob {

	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		JobDataMap map = context.getJobDetail().getJobDataMap();
		String val = map.getString("a");
		System.out.println("=======================>"+val+"<=====================");
	}

}
