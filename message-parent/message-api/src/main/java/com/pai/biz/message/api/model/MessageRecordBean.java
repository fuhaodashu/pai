package com.pai.biz.message.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.doc.AutoDocField;
import com.pai.base.api.model.Bean;
/**
 * 对象功能:pai_message_record Tbl对象
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-10 16:13:57
 */
public class MessageRecordBean extends Bean{
	
	private static final long serialVersionUID = 2L;
	
	@AutoDocField("主键ID")
	private String  id; 
			
	@AutoDocField("状态（waiting_confirm=等待确认，sending=发送中）")
	private String  status; 
			
	@AutoDocField("回调函数，每个服务一个，确保事务的一致")
	private String  url; 
			
	@AutoDocField("消息内容")
	private String  messageBody; 
			
	@AutoDocField("消息重发次数")
	private Short  messageSendTimes; 
			
	@AutoDocField("是否死亡")
	private char  areadlyDead; 
			
	@AutoDocField("监听处理类handler前缀")
	private String  msgType; 
			
	@AutoDocField("备注")
	private String  remark; 
			
	@AutoDocField("创建时间")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private java.util.Date  createTime; 
			
	@AutoDocField("最后修改时间")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private java.util.Date  updateTime; 
			
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
}