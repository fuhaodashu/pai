package com.pai.service.quartz;

import java.util.Calendar;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.ExceptionUtil;
import com.pai.base.core.util.date.DateConverter;
import com.pai.service.quartz.constants.JobConstants;

@DisallowConcurrentExecution
public abstract class BaseJob implements Job {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public abstract void executeJob(JobExecutionContext context) throws Exception;
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobPersistenceSupport jobPersistenceSupport = SpringHelper.getBean(JobPersistenceSupport.class);
		String jobName=context.getJobDetail().getKey().getName();		
		String triggerName=getTriggerName(context);
		Date now = Calendar.getInstance().getTime();
		String nowString = DateConverter.toString(now);
		long ltime = now.getTime();
		try {
			StringBuffer log = new StringBuffer("开始执行任务，任务名："+jobName+"；触发器名："+triggerName+"，开始时间 "+nowString);  
			logger.warn("开始执行任务，任务名：{}；触发器名：{}，开始时间 {}",new Object[]{jobName,triggerName,nowString});			
			executeJob(context);
			long ms = Calendar.getInstance().getTimeInMillis() - ltime;
			logger.warn("结束执行任务，执行时间是：{} 毫秒",new Object[]{String.valueOf(ms)});
			log.append("结束执行任务，执行时间是："+ms+" 毫秒");
			if(jobPersistenceSupport!=null){
				jobPersistenceSupport.saveJobTaskLog(context.getJobDetail().getKey().getName(),JobConstants.LOG_STATUS.SUCCESS, log.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(jobPersistenceSupport!=null){
				jobPersistenceSupport.saveJobTaskLog(context.getJobDetail().getKey().getName(),JobConstants.LOG_STATUS.FAILURE, ExceptionUtil.getExceptionMessage(e));
			}
			logger.error("执行任务出错，{}",new Object[]{e.getMessage()},e);			
		}
	}
	
	protected JobDataMap getJobDataMap(JobExecutionContext context) {
		return context.getJobDetail().getJobDataMap();
	}
	
	private String getTriggerName(JobExecutionContext context){
		String triggerName="defaultDirectExec";
		
		Trigger trigger=context.getTrigger();
		if(trigger!=null){
			triggerName=trigger.getKey().getName();
		}
		return triggerName;
	}
	
}
