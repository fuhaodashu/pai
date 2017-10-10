package com.pai.biz.auth.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:角色 Tbl对象
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-29 09:36:49
 */
 @ITable(name="authRole",code="pai_auth_role")
public class AuthRoleTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*角色Id*/
	@IField(name="name",column="name")
	protected String  name; 		/*名称*/
	@IField(name="descript",column="descript")
	protected String  descript; 		/*描述*/
	@IField(name="status",column="status")
	protected Integer  status; 		/*状态*/
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
	 * 返回 角色Id
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
	 * 返回 名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setDescript(String descript) 
	{
		this.descript = descript;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getDescript() 
	{
		return this.descript;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态
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
		.append("descript", this.descript) 
		.append("status", this.status) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.toString();
	}
}