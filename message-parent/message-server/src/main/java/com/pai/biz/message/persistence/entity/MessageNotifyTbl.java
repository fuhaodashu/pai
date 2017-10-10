package com.pai.biz.message.persistence.entity;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:最大通知记录表 Tbl对象
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-09-07 23:37:41
 */
 @ITable(name="messageNotify",code="pai_message_notify")
public class MessageNotifyTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String id; 		/*主键id*/
	@IField(name="createTime",column="create_time")
	protected java.util.Date createTime; 		/*创建时间*/
	@IField(name="updateTime",column="update_time")
	protected java.util.Date updateTime; 		/*修改时间*/
	@IField(name="latestNotifyTime",column="latest_notify_time")
	protected java.util.Date latestNotifyTime; 		/*最近一次通知时间*/
	@IField(name="notifyTimes",column="notify_times")
	protected Integer notifyTimes; 		/*通知次数*/
	@IField(name="url",column="url")
	protected String url; 		/*url链接*/
	@IField(name="paramBody",column="param_body")
	protected String paramBody; 		/*json参数*/
	@IField(name="status",column="status")
	protected String status; 		/*success=成功 failure=失败*/
	@IField(name="notifyType",column="notify_type")
	protected String notifyType; 		/*通知类型post/get*/
	@IField(name="successVal",column="success_val")
	protected String successVal; 	  /*返回值中包含successVal表示请求成功*/
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键id
	 * @return
	 */
	public String getId() 
	{
		return this.id;
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
	 * 返回 修改时间
	 * @return
	 */
	public java.util.Date getUpdateTime() 
	{
		return this.updateTime;
	}
	public java.util.Date getLatestNotifyTime() {
		return latestNotifyTime;
	}
	public void setLatestNotifyTime(java.util.Date latestNotifyTime) {
		this.latestNotifyTime = latestNotifyTime;
	}
	public void setNotifyTimes(Integer notifyTimes) 
	{
		this.notifyTimes = notifyTimes;
	}
	/**
	 * 返回 通知次数
	 * @return
	 */
	public Integer getNotifyTimes() 
	{
		return this.notifyTimes;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}
	/**
	 * 返回 url链接
	 * @return
	 */
	public String getUrl() 
	{
		return this.url;
	}
	public void setParamBody(String paramBody) 
	{
		this.paramBody = paramBody;
	}
	/**
	 * 返回 json参数
	 * @return
	 */
	public String getParamBody() 
	{
		return this.paramBody;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	/**
	 * 返回 success=成功 failure=失败
	 * @return
	 */
	public String getStatus() 
	{
		return this.status;
	}
	public void setNotifyType(String notifyType) 
	{
		this.notifyType = notifyType;
	}
	/**
	 * 返回 通知类型post/get
	 * @return
	 */
	public String getNotifyType() 
	{
		return this.notifyType;
	}
	public String getSuccessVal() {
		return successVal;
	}
	public void setSuccessVal(String successVal) {
		this.successVal = successVal;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("createTime", this.createTime) 
		.append("updateTime", this.updateTime) 
		.append("notifyTimes", this.notifyTimes) 
		.append("url", this.url) 
		.append("paramBody", this.paramBody) 
		.append("status", this.status) 
		.append("notifyType", this.notifyType) 
		.toString();
	}
}