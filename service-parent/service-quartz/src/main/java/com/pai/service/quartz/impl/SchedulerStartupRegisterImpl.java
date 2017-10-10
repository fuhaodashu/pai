package com.pai.service.quartz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.quartz.JobPersistenceSupport;
import com.pai.service.quartz.SchedulerService;
import com.pai.service.quartz.SchedulerStartupRegister;
import com.pai.service.quartz.entity.IJobTaskPo;
import com.pai.service.quartz.util.CronUtil;

public class SchedulerStartupRegisterImpl implements SchedulerStartupRegister{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());		
	
	public void registerJobs(String group) {
		logger.info("============================================");
		logger.info("Enter SchedulerStartupRegisterImpl, register all jobs");
		JobPersistenceSupport jobPersistenceSupport = SpringHelper.getBean(JobPersistenceSupport.class);
		SchedulerService schedulerService = SpringHelper.getBean(SchedulerService.class);
		
		int registerJobCount = 0;
		if(schedulerService!=null && jobPersistenceSupport!=null){		
			List<IJobTaskPo> iJobTaskPos = jobPersistenceSupport.findActivedJobTaskPos(group);
			if(iJobTaskPos!=null){
				for(IJobTaskPo iJobTaskPo:iJobTaskPos){
					if(StringUtils.isNotEmpty(iJobTaskPo.getExpression())){
						if(CronUtil.check(iJobTaskPo.getExpression())){
							schedulerService.startJob(iJobTaskPo);
							logger.info("#### register "+ iJobTaskPo.getName());
							registerJobCount++;	
						}else {
							logger.warn("$$$$ error expr + " + iJobTaskPo.getExpression());
						}
					}
				}
			}
		}
		logger.info("Complete SchedulerStartupRegisterImpl, register: " + registerJobCount + " Jobs");
		logger.info("============================================");
	}	

}
