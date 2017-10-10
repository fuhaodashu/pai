package com.pai.app.web.core.framework.web.event;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationEvent;

public class SessionDestroyedEvent  extends ApplicationEvent{
	private HttpSession httpSession;
	public SessionDestroyedEvent(Object source,HttpSession httpSession){
		super(source);
		this.httpSession = httpSession;
	}
	public HttpSession getHttpSession() {
		return httpSession;
	}
	
}
