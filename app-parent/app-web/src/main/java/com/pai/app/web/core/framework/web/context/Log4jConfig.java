/**
 * 
 */

package com.pai.app.web.core.framework.web.context;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

import com.pai.app.web.core.constants.WebConstants;

public final class Log4jConfig {
	private static boolean	initialized	= false;
	private final static String LOG4J_CONFIG_LOCATION="/WEB-INF/classes/conf/log4j.properties";
	
	public static void initLogging(ServletContext servletContext) {
		if (initialized) {
			return;
		}
		initialized = true;
		String webAppName;
		String webAppRootPath;
		if (servletContext != null) {
			webAppName = servletContext.getServletContextName();
			webAppRootPath = servletContext.getRealPath("/");
		} else {
			File webRoot = new File("web");
			webAppRootPath = webRoot.getAbsolutePath();
			webAppName = webRoot.getAbsoluteFile().getParentFile().getName();
		}
		String logPath = webAppRootPath + "/WEB-INF/logs/";		
		String location = null;
		if(servletContext==null){
			location = webAppRootPath + LOG4J_CONFIG_LOCATION;
		}else{
			location = webAppRootPath + ContextParamHelper.getInstance().getParamValue(WebConstants.LOG4J_CONFIG_LOCATION);
		}

		System.setProperty("application", webAppName);
		System.setProperty("logs", logPath);
		
		// load Log4J property configurations
		try {
			PropertyConfigurator.configure(location);
			PropertyConfigurator.configureAndWatch(location, 10000);
			Log logger = LogFactory.getLog(Log4jConfig.class);
			logger.info("Initialized Log4J from [" + location
						+ "], dynamic configurations is enabled.");
		} catch (Throwable e) {
			throw new RuntimeException("Initialize Log4J error!", e);
		}
	}

	public static void shutdownLogging() {
		Log logger = LogFactory.getLog(Log4jConfig.class);
		logger.info("Shutting down Log4J ......");
		LogManager.shutdown();
	}

}
