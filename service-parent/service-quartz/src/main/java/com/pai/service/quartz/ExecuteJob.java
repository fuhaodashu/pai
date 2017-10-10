package com.pai.service.quartz;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pai.base.core.helper.SpringHelper;
import com.pai.service.quartz.constants.JobConstants.EXECUTE;

public class ExecuteJob extends BaseJob {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		String taskName = (String) jobDataMap.getString(EXECUTE.TASK_NAME).trim();
		String methodName = (String) jobDataMap.getString(EXECUTE.METHOD_NAME).trim();
		String className = (String) jobDataMap.getString(EXECUTE.CLASS_NAME).trim();
		List<Object> params = (List<Object>) jobDataMap.get(EXECUTE.PARAMS);		
		List<Class> paramTypes = (List<Class>) jobDataMap.get(EXECUTE.PARAM_TYPES);

		try {
			Class<?> clazz = Class.forName(className);
			Object srv = SpringHelper.getBean(clazz);
			if(srv == null) {
				srv = clazz.newInstance();
			}
			Method method = srv.getClass().getMethod(methodName, paramTypes.toArray(new Class[paramTypes.size()]));
			method.invoke(srv, params.toArray(new Object[params.size()]));
			//写日志
		} catch (Exception e) {
			String exceptionMsg = "";
			try {
				Field field = e.getClass().getDeclaredField("target");
				field.setAccessible(true);
				Exception target = (Exception) field.get(e);
				logger.error(target.getMessage(), target);
				exceptionMsg = target.getMessage();
				field.setAccessible(false);
				
				//TODO 写错误日志
			} catch (Exception ex) {
			}					
		} finally {
			
		}
	}
}
