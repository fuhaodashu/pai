package com.pai.biz.auth.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:用户登录日志 Tbl对象
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-10-15 23:19:03
 */
 @ITable(name="authUserLoginLog",code="pai_auth_user_login_log")
public class AuthUserLoginLogTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*主键*/
	@IField(name="userId",column="user_id_")
	protected String  userId; 		/*用户Id*/
	@IField(name="ip",column="ip")
	protected String  ip; 		/*来源ip*/
	@IField(name="createTime",column="create_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	protected java.util.Date  createTime; 		/*创建时间*/
	
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 用户Id
	 * @return
	 */
	public String getUserId() 
	{
		return this.userId;
	}
	public void setIp(String ip) 
	{
		this.ip = ip;
	}
	/**
	 * 返回 来源ip
	 * @return
	 */
	public String getIp() 
	{
		return this.ip;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("userId", this.userId) 
		.append("ip", this.ip) 
		.append("createTime", this.createTime) 
		.toString();
	}
}