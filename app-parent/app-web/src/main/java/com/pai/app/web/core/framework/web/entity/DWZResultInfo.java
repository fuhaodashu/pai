package com.pai.app.web.core.framework.web.entity;


public class DWZResultInfo implements Info{
	
	public class STATUS{
		public final static String OK="200";
		public final static String ERROR="300";
		public final static String TIMEOUT="301";
	}	
	
	private String statusCode = "";
	private String message = "";
	private String navTabId = "";
	private String callbackType ="";
	private String forwardUrl = "";
	
	private DWZResultInfo(String statusCode_){
		this.statusCode = statusCode_;
	}
	
	public static DWZResultInfo newInstance(String statusCode_){
		DWZResultInfo info = new DWZResultInfo(statusCode_);
		return info;		
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	
	
	
}
