package com.pai.biz.message.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:pai_message_record Tbl对象
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:00:10
 */
 @ITable(name="messageRecord",code="pai_message_record")
public class MessageRecordTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String id; 		/*主键ID*/
	@IField(name="status",column="status")
	protected String status; 		/*状态（waiting_confirm=等待确认，sending=发送中）*/
	@IField(name="url",column="url")
	protected String url; 		/*回调函数，每个服务一个，确保事务的一致*/
	@IField(name="messageBody",column="message_body")
	protected String messageBody; 		/*消息内容*/
	@IField(name="messageSendTimes",column="message_send_times")
	protected Short messageSendTimes; 		/*消息重发次数*/
	@IField(name="areadlyDead",column="areadly_dead")
	protected char areadlyDead; 		/*是否死亡*/
	@IField(name="msgType",column="msg_type")
	protected String msgType; 		/*监听处理类handler前缀*/
	@IField(name="remark",column="remark")
	protected String remark; 		/*备注*/
	@IField(name="createTime",column="create_time")
	protected java.util.Date createTime; 		/*创建时间*/
	@IField(name="updateTime",column="update_time")
	protected java.util.Date updateTime; 		/*最后修改时间*/
	
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态（waiting_confirm=等待确认，sending=发送中）
	 * @return
	 */
	public String getStatus() 
	{
		return this.status;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}
	/**
	 * 返回 回调函数，每个服务一个，确保事务的一致
	 * @return
	 */
	public String getUrl() 
	{
		return this.url;
	}
	public void setMessageBody(String messageBody) 
	{
		this.messageBody = messageBody;
	}
	/**
	 * 返回 消息内容
	 * @return
	 */
	public String getMessageBody() 
	{
		return this.messageBody;
	}
	public void setMessageSendTimes(Short messageSendTimes) 
	{
		this.messageSendTimes = messageSendTimes;
	}
	/**
	 * 返回 消息重发次数
	 * @return
	 */
	public Short getMessageSendTimes() 
	{
		return this.messageSendTimes;
	}
	public void setAreadlyDead(char areadlyDead) 
	{
		this.areadlyDead = areadlyDead;
	}
	/**
	 * 返回 是否死亡
	 * @return
	 */
	public char getAreadlyDead() 
	{
		return this.areadlyDead;
	}
	public void setMsgType(String msgType) 
	{
		this.msgType = msgType;
	}
	/**
	 * 返回 监听处理类handler前缀
	 * @return
	 */
	public String getMsgType() 
	{
		return this.msgType;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemark() 
	{
		return this.remark;
	}
	public void setCreateTime(java.util.Date createTime) 
	{
		this.createTime = createTime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateTime() 
	{
		return this.createTime;
	}
	public void setUpdateTime(java.util.Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	/**
	 * 返回 最后修改时间
	 * @return
	 */
	public java.util.Date getUpdateTime() 
	{
		return this.updateTime;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("status", this.status) 
		.append("url", this.url) 
		.append("messageBody", this.messageBody) 
		.append("messageSendTimes", this.messageSendTimes) 
		.append("areadlyDead", this.areadlyDead) 
		.append("msgType", this.msgType) 
		.append("remark", this.remark) 
		.append("createTime", this.createTime) 
		.append("updateTime", this.updateTime) 
		.toString();
	}
}