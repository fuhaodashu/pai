package com.pai.service.mail.entity;

import java.io.Serializable;

import com.pai.base.core.util.string.StringUtils;

public class EmailPerson implements Serializable{
	private String email;
	private String name;	
	
	public EmailPerson(String email){
		this.email = email;
		this.name = StringUtils.getNameByEmail(email);
	}
	
	public EmailPerson(String email,String name){
		this.email = email;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EmailPerson && obj!=null){
			EmailPerson ep = (EmailPerson)obj;
			if(ep.getEmail().equals(this.getEmail())
					&& ep.getName().equals(this.getName())){
				return true;
			}
		}
		return false;
	}
	
}
