package com.pai.app.web.core.framework.web.context;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

public class ContextParamHelper {
	private static ContextParamHelper helper = new ContextParamHelper();
	private Map<String,String> contextParams = null;
	
	private ContextParamHelper(){}
	
	public static ContextParamHelper getInstance(){		
		return helper;
	}
	
	public void init(ServletContext sc) {	
		contextParams=new HashMap();
		Enumeration<String> parameters = sc.getInitParameterNames();
		while (parameters.hasMoreElements()) {
			String parameter = (String) parameters.nextElement();
			contextParams.put(parameter, sc.getInitParameter(parameter));
		}			
	}
	public String getParamValue(String key){
		return contextParams.get(key);
	}
}
