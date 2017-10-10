package com.pai.biz.auth.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:用户 Tbl对象
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-01-01 18:41:40
 */
 @ITable(name="authUser",code="pai_auth_user")
public class AuthUserTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*用户Id*/
	@IField(name="name",column="name")
	protected String  name; 		/*昵称*/
	@IField(name="phone",column="phone")
	protected String  phone; 		/*手机号*/
	@IField(name="password",column="password")
	protected String  password; 		/*密码*/
	@IField(name="status",column="status")
	protected Integer  status; 		/*状态（1=正常；2=冻结）*/
	@IField(name="createBy",column="create_by")
	protected String  createBy; 		/*创建人*/
	@IField(name="createTime",column="create_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	protected java.util.Date  createTime; 		/*创建时间*/
	@IField(name="updateBy",column="update_by")
	protected String  updateBy; 		/*修改人*/
	@IField(name="updateTime",column="update_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	protected java.util.Date  updateTime; 		/*修改时间*/
	
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 用户Id
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 昵称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	/**
	 * 返回 手机号
	 * @return
	 */
	public String getPhone() 
	{
		return this.phone;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	/**
	 * 返回 密码
	 * @return
	 */
	public String getPassword() 
	{
		return this.password;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态（1=正常；2=冻结）
	 * @return
	 */
	public Integer getStatus() 
	{
		return this.status;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreateBy() 
	{
		return this.createBy;
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
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}
	/**
	 * 返回 修改人
	 * @return
	 */
	public String getUpdateBy() 
	{
		return this.updateBy;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("phone", this.phone) 
		.append("password", this.password) 
		.append("status", this.status) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.toString();
	}
}