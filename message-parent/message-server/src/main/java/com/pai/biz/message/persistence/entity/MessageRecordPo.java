package com.pai.biz.message.persistence.entity;
/**
 * 对象功能:pai_message_record entity对象
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:00:10
 */
public class MessageRecordPo extends MessageRecordTbl{

	public enum MessageStatusEnum {
		WAITING_CONFIRM,SENDING;
	}
}