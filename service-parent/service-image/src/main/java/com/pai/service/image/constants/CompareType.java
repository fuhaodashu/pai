package com.pai.service.image.constants;

public enum CompareType {
	SIZE,NAME,TYPE;
	public String lower(){
		return name().toLowerCase();
	}
}
