package com.pai.biz.auth.event;

import org.springframework.context.ApplicationEvent;

import com.pai.biz.auth.persistence.entity.AuthUserPo;

@SuppressWarnings("serial")
public class LoginEvent extends ApplicationEvent {

	private String ip;
	
	public LoginEvent(AuthUserPo source, String ip) {
		super(source);
		this.ip = ip;
	}
	
	public AuthUserPo getUser(){
		return (AuthUserPo) source;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
