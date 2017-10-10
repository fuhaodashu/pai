package com.pai.biz.message.api.enums;

public enum MessageStatusEnum {
	
	WAITING_CONFIRM("待确认"),

	SENDING("发送中");
	
	private String desc;

	private MessageStatusEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
