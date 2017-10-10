package com.pai.service.quartz.impl;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.date.DateConverter;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.quartz.BaseJob;
import com.pai.service.quartz.JobPersistenceSupport;
import com.pai.service.quartz.SchedulerService;
import com.pai.service.quartz.constants.JobConstants;
import com.pai.service.quartz.constants.JobConstants.EXECUTE;
import com.pai.service.quartz.entity.IJobTaskPo;
import com.pai.service.quartz.entity.IJobTaskParamPo;
import com.pai.service.quartz.util.CronUtil;

@Service
public class SchedulerServiceImpl implements SchedulerService,InitializingBean{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());		
	
	Scheduler scheduler;

	JobPersistenceSupport jobPersistenceSupport;	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
		scheduler.start();
	}

	public boolean startJob(String jobTaskId) {
		boolean result = false;
		jobPersistenceSupport = SpringHelper.getBean(JobPersistenceSupport.class);		
		if(jobPersistenceSupport!=null){
			IJobTaskPo jobDefPo = jobPersistenceSupport.getJobTaskPo(jobTaskId);
			result = startJob(jobDefPo);
		}
		return result;
	}
	private final static String LOCK = "lock";
	public boolean startJob(IJobTaskPo jobTaskPo) {
		boolean isStart = false;
		if(jobTaskPo!=null && StringUtils.isNotEmpty(jobTaskPo.getBean()) && CronUtil.check(jobTaskPo.getExpression())){
			synchronized (StringUtils.isEmpty(jobTaskPo.getId())?LOCK:jobTaskPo.getId()) {							
				logger.info("Enter start Job method，name="+jobTaskPo.getName());
				isStart = startPlan(jobTaskPo.getId(),jobTaskPo.getBean(),jobTaskPo.getGroupName());
				if(!isStart){
					List<IJobTaskParamPo> jobTaskParamPos = jobPersistenceSupport.findTaskParams(jobTaskPo.getId());
					//如果类名为全称，算了，应该避免用全称，getJobDetail就不搞了
					JobDetail jobDetail = getJobDetail(getKey(jobTaskPo), jobTaskPo.getGroupName());;
		
					BaseJob jobBean = (BaseJob) SpringHelper.getBean(jobTaskPo.getBean());	
					if(jobBean!=null){
						jobDetail = newJob(jobBean.getClass()).withIdentity(getKey(jobTaskPo), jobTaskPo.getGroupName()).storeDurably(false).build();
					}else{
						return false;
					}
					//构造参数，取jobParamPos表的key,value属性值存入map中
					Map<String, Object> dataMap = buildDataMap(jobTaskParamPos);
					jobDetail.getJobDataMap().putAll(dataMap);
					CronTrigger	trigger = buildCronTrigger(getKey(jobTaskPo),jobTaskPo.getGroupName(),jobTaskPo.getExpression());
					try {
						isStart = startPlan(jobTaskPo.getId(),jobTaskPo.getBean(),jobTaskPo.getGroupName());
						if(!isStart){	
							//全量启动所有定时任务先删除再启动
							deleteJob(getKey(jobTaskPo), jobTaskPo.getGroupName());
							scheduler.scheduleJob(jobDetail,trigger);
							logger.info("Start Job Success:"+jobTaskPo.getName());
						}
						return true;
					} catch (SchedulerException e) {			
						e.printStackTrace();
						logger.error("Start Job Error:"+jobTaskPo.getName() + " " +e.getMessage());
						return false;
					}
				}else {
					if(!CronUtil.check(jobTaskPo.getExpression())){
						logger.info("Job Expr Error! :"+jobTaskPo.getExpression());
					}else {
						logger.info("Job has run:"+jobTaskPo.getName());	
					}				
				}
			}
		}
		return isStart;
	}

	@Override
	public boolean startExprJob(String jobTaskId, String bean, String groupName, String expression,
			List<IJobTaskParamPo> iJobTaskParamPos) {
		logger.info("startJob bean="+bean + ";group="+groupName+";expr="+expression+";jobParamPos="+iJobTaskParamPos);
		if(!CronUtil.check(expression)){
			logger.warn("expr error!!! : " + expression);
			return false;
		}
		BaseJob jobBean = (BaseJob) SpringHelper.getBean(bean);
		JobDetail job = getJobDetail(bean, groupName);
		if(job==null){
			job = newJob(jobBean.getClass()).withIdentity(getKey(bean, jobTaskId), groupName).storeDurably(false).build();
		}		
		Map<String, Object> dataMap = buildDataMap(iJobTaskParamPos);
		job.getJobDataMap().putAll(dataMap);
		CronTrigger	trigger = buildCronTrigger(getKey(bean, jobTaskId), groupName, expression);
		try {
			scheduler.scheduleJob(job,trigger);
			return true;
		} catch (SchedulerException e) {			
			e.printStackTrace();
			logger.error("startJob(String bean, String group, String expr,List<IJobParamPo> jobParamPos) error"+e.getMessage(),e);
			return false;
		}		
	}

	@Override
	public boolean startOneTime(String jobTaskId,String beanId,String groupName,List<IJobTaskParamPo> iJobTaskParamPos) {
		logger.info("Enter startOneTime method，beanId="+beanId+"; group="+groupName);
		JobDetail job = getJobDetail(beanId + "_" + jobTaskId, groupName);
		boolean jobExist = true;
		if(job==null){
			jobExist = false;
			job = buildJobDetail(jobTaskId,beanId, groupName, null,false);
		}
		if(job!=null && iJobTaskParamPos!=null && iJobTaskParamPos.size()>0){
			Map<String, Object> dataMap = buildDataMap(iJobTaskParamPos);
			job.getJobDataMap().putAll(dataMap);
		}
		try {		
			SimpleTrigger trigger = buildSimpleTrigger(job);
			if(jobExist){
				scheduler.scheduleJob(trigger);
			}else {
				scheduler.scheduleJob(job,trigger);	
			}	
			logger.info("startOneTime success!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("startOneTime error!"+e.getMessage(),e);
			return false;
		}		
	}

	@Override
	public boolean shutdownPlan(String planId,String bean, String groupName) {
		TriggerKey triggerKey = new TriggerKey(getKey(bean,planId),groupName);
		try {
			boolean isStop = false;
			Trigger.TriggerState state= scheduler.getTriggerState(triggerKey);
			if(state==TriggerState.NORMAL 
					|| state == TriggerState.BLOCKED){
				scheduler.pauseTrigger(triggerKey);
				isStop = true;
			}	
			if(!isStop){
				deleteJob(getKey(bean, planId), groupName);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	private void deleteJob(String key, String groupName){
		JobKey jobKey = new JobKey(key, groupName);
		try {
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public boolean startPlan(String planId, String bean,String groupName){
		TriggerKey triggerKey = new TriggerKey(getKey(bean,planId),groupName);
		try {
			Trigger.TriggerState state= scheduler.getTriggerState(triggerKey);
			if(state!=null){
				if(state==TriggerState.PAUSED || state==TriggerState.BLOCKED){
					scheduler.resumeTrigger(triggerKey);
					return true;
				}else if(state==TriggerState.NONE || state==TriggerState.COMPLETE || state==TriggerState.ERROR){
					JobKey jobKey = new JobKey(getKey(bean, planId), groupName);
					scheduler.deleteJob(jobKey);					
				}
			}		
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;		
	}

	@Override
	public TriggerState getTriggerState(String planId,String bean, String groupName) {
		TriggerKey triggerKey = new TriggerKey(getKey(bean,planId),groupName);
		try {
			Trigger.TriggerState state= scheduler.getTriggerState(triggerKey);
			return state;	
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void startScheduler() {
		try {
			init();
			if(!scheduler.isStarted()){
				scheduler.start();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void shutdownScheduler() {
		try {
			init();
			if(!scheduler.isShutdown()){
				scheduler.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void init(){
		if(scheduler==null ){
			scheduler = (Scheduler) SpringHelper.getBean("scheduler");
		}
		if(jobPersistenceSupport==null){
			jobPersistenceSupport = SpringHelper.getBean(JobPersistenceSupport.class);
		}
		if(scheduler==null){
			throw new RuntimeException("Can't get scheduler instance, please check service-quartz.xml file.");
		}
	}
	
	private JobDetail getJobDetail(String beanId,String groupName){
		JobKey jobKey = new JobKey(beanId, groupName);
		try {
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if(jobDetail!=null){
				return jobDetail;
			}
		} catch (SchedulerException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	private SimpleTrigger buildSimpleTrigger(JobDetail job){
		SimpleTrigger trigger = (SimpleTrigger) newTrigger().forJob(job) 
                .startAt(new Date()).withSchedule(simpleSchedule().withIntervalInSeconds(1) 
                        .withRepeatCount(0) 
                        ) 
                        .build();
		return trigger;
	}	
	
	private CronTrigger buildCronTrigger(String planId,String groupName,String expression){
		CronTrigger	trigger = TriggerBuilder.newTrigger().withIdentity(planId, groupName)  
                .withSchedule(CronScheduleBuilder.cronSchedule(expression)).build();
		return trigger;
	}
	
	private JobDetail buildJobDetail(String jobParamId,String beanId,String groupName,List<IJobTaskParamPo> jobTaskParamPos,boolean isDurable){
		BaseJob jobBean = (BaseJob) SpringHelper.getBean(beanId);
		JobDetail jobDetail = newJob(jobBean.getClass()).withIdentity(getKey(beanId, jobParamId), groupName).storeDurably(isDurable).build();
		if(jobTaskParamPos!=null && jobTaskParamPos.size()>0){
			jobDetail.getJobDataMap().putAll(buildDataMap(jobTaskParamPos));
		}
		return jobDetail;
	}
	
	private Map<String, Object> buildDataMap(List<IJobTaskParamPo> iJobTaskParamPos){		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if(iJobTaskParamPos==null){
			return dataMap;
		}
		for(IJobTaskParamPo iJobTaskParamPo:iJobTaskParamPos){
			if(JobConstants.DATA_TYPE.INT.equals(iJobTaskParamPo.getValueType())){
				dataMap.put(iJobTaskParamPo.getParamKey(), Integer.parseInt(iJobTaskParamPo.getParamValue()));
			}else if(JobConstants.DATA_TYPE.LONG.equals(iJobTaskParamPo.getValueType())){
				dataMap.put(iJobTaskParamPo.getParamKey(), Long.parseLong(iJobTaskParamPo.getParamValue()));
			}else if(JobConstants.DATA_TYPE.DOUBLE.equals(iJobTaskParamPo.getValueType())){
				dataMap.put(iJobTaskParamPo.getParamKey(), Double.parseDouble(iJobTaskParamPo.getParamValue()));
			}else if(JobConstants.DATA_TYPE.DATE.equals(iJobTaskParamPo.getValueType())){
				try {
					dataMap.put(iJobTaskParamPo.getParamKey(), DateConverter.toDate(iJobTaskParamPo.getParamValue()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				dataMap.put(iJobTaskParamPo.getParamKey(), iJobTaskParamPo.getParamValue());
			}
		}
		return dataMap;
	}
	
	private String getKey(IJobTaskPo jobTaskPo){
		return jobTaskPo.getBean() + "_" + jobTaskPo.getId();
	}
	private String getKey(String bean,String jobTaskId){
		return bean + "_" + jobTaskId;
	}
}
