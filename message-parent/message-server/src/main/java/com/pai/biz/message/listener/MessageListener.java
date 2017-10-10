
package com.pai.biz.message.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.util.Log4jWebConfigurer;

import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.ServletContextHelper;
import com.pai.biz.message.api.service.MessageNotifyService;

public class MessageListener extends ContextLoaderListener implements
		ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		SpringHelper.cleanup();

		super.contextDestroyed(event);

		Log logger = LogFactory.getLog(MessageListener.class);
		if (logger.isDebugEnabled()) {
			logger.info("Application cleanup [OK].");
		}
		LogManager.shutdown();
		super.contextDestroyed(event);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			super.contextInitialized(event);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException("Spring context failed to startup.", e);
		}
		ServletContext servletContext = event.getServletContext();
		ServletContextHelper.init(servletContext);
		
		Log4jWebConfigurer.initLogging(event.getServletContext());
		
		MessageNotifyService messageNotifyService = SpringHelper.getBean(MessageNotifyService.class);
		messageNotifyService.startNoyifyThread();
	}
}
