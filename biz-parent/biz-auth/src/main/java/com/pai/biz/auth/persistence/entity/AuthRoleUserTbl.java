package com.pai.biz.auth.persistence.entity;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:角色-用户 Tbl对象
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 20:59:27
 */
 @ITable(name="authRoleUser",code="pai_auth_role_user")
public class AuthRoleUserTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*主键Id*/
	@IField(name="roleId",column="role_id_")
	protected String  roleId; 		/*角色Id*/
	@IField(name="userId",column="user_id_")
	protected String  userId; 		/*用户Id*/
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
	 * 返回 主键Id
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setRoleId(String roleId) 
	{
		this.roleId = roleId;
	}
	/**
	 * 返回 角色Id
	 * @return
	 */
	public String getRoleId() 
	{
		return this.roleId;
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
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("roleId", this.roleId) 
		.append("userId", this.userId) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("updateTime", this.updateTime) 
		.append("updateBy", this.updateBy) 
		.toString();
	}
}