package com.pai.app.admin.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pai.app.web.core.framework.web.listener.StartupListener;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.ConfigHelper;
import com.pai.service.quartz.SchedulerStartupRegister;


public class AdminStartupListener extends StartupListener{

	private static Logger logger = LoggerFactory.getLogger(AdminStartupListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {		
		super.contextInitialized(event);
		
		//这里放置定时任务的调用
		String group = ConfigHelper.getInstance().getParamValue("job.group");
		
		SchedulerStartupRegister schedulerStartupRegister = SpringHelper.getBean(SchedulerStartupRegister.class);
		
		if(schedulerStartupRegister!=null){
			schedulerStartupRegister.registerJobs(group);
			logger.info("定时器启动！！！");
		}
	}

}
