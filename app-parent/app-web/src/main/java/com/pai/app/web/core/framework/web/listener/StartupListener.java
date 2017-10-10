
package com.pai.app.web.core.framework.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.util.Log4jWebConfigurer;

import com.pai.app.web.core.framework.web.context.ContextParamHelper;
import com.pai.app.web.core.framework.web.context.Log4jConfig;
import com.pai.base.core.helper.SpringHelper;
import com.pai.base.core.util.ServletContextHelper;

public class StartupListener extends ContextLoaderListener implements
		ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		SpringHelper.cleanup();

		super.contextDestroyed(event);

		Log logger = LogFactory.getLog(StartupListener.class);
		if (logger.isDebugEnabled()) {
			logger.info("Application cleanup [OK].");
		}

		Log4jConfig.shutdownLogging();
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
		
		// 进行框架的初始化工作
		// 将web.xml中的context-param值放在ContextParamHelper对象中
		ContextParamHelper.getInstance().init(servletContext);
		// 初始化Log4J
		Log4jConfig.initLogging(servletContext);
		Log4jWebConfigurer.initLogging(event.getServletContext());
	}
}
