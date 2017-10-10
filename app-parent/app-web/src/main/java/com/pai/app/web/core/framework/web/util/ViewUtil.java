package com.pai.app.web.core.framework.web.util;

import com.pai.base.core.util.string.StringUtils;

public class ViewUtil {
	public static String getByURI(String requestURI,String contextPath,String actionPostfix){
		requestURI = requestURI.replace(actionPostfix, "");
		int cxtIndex = requestURI.indexOf(contextPath);
		if (cxtIndex != -1) {
			requestURI = requestURI.substring(cxtIndex + contextPath.length());
		}
		String viewPath=null;
		String[] paths = requestURI.split("[/]");
		
		if (paths != null && paths.length == 6) {
			viewPath = "/" + paths[1] + "/" + paths[2] + "/" + paths[3] + "/" + paths[4] + "/" + paths[4] + StringUtils.upperFirst(paths[5]);
		} else if (paths != null && paths.length == 5) {
			viewPath = "/" + paths[1] + "/" + paths[2] + "/" + paths[3] + "/" + paths[3] + StringUtils.upperFirst(paths[4]);
		} else {
			viewPath=requestURI;
		}		
		return viewPath;
	}
	
	public static String getFrontTmplByURI(String requestURI,String contextPath,String actionPostfix){
		requestURI = requestURI.replace(actionPostfix, "");
		int cxtIndex = requestURI.indexOf(contextPath);
		if (cxtIndex != -1) {
			requestURI = requestURI.substring(cxtIndex + contextPath.length());
		}
		String viewPath=requestURI;	
		return viewPath;
	}
}
