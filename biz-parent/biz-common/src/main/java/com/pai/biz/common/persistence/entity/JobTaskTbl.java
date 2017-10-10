package com.pai.biz.common.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:任务调度 Tbl对象
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:39:48
 */
 @ITable(name="jobTask",code="pai_job_task")
public class JobTaskTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*主键id*/
	@IField(name="name",column="name")
	protected String  name; 		/*名称*/
	@IField(name="description",column="description")
	protected String  description; 		/*描述*/
	@IField(name="bean",column="bean")
	protected String  bean; 		/*类名*/
	@IField(name="groupName",column="group_name")
	protected String  groupName; 		/*所属组*/
	@IField(name="type",column="type")
	protected String  type; 		/*类型（one_time=执行一次；expression=按表达式执行）*/
	@IField(name="expression",column="expression")
	protected String  expression; 		/*cron表达式*/
	@IField(name="status",column="status")
	protected Integer  status; 		/*状态（1=运行中；2=停止中）*/
	@IField(name="createTime",column="create_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	protected java.util.Date  createTime; 		/*创建时间*/
	@IField(name="createBy",column="create_by")
	protected String  createBy; 		/*创建人*/
	@IField(name="updateTime",column="update_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	protected java.util.Date  updateTime; 		/*修改时间*/
	@IField(name="updateBy",column="update_by")
	protected String  updateBy; 		/*修改人*/
	
	
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
	public void setDescription(String description) 
	{
		this.description = description;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getDescription() 
	{
		return this.description;
	}
	public void setBean(String bean) 
	{
		this.bean = bean;
	}
	/**
	 * 返回 类名
	 * @return
	 */
	public String getBean() 
	{
		return this.bean;
	}
	public void setGroupName(String groupName) 
	{
		this.groupName = groupName;
	}
	/**
	 * 返回 所属组
	 * @return
	 */
	public String getGroupName() 
	{
		return this.groupName;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	/**
	 * 返回 类型（one_time=执行一次；expression=按表达式执行）
	 * @return
	 */
	public String getType() 
	{
		return this.type;
	}
	public void setExpression(String expression) 
	{
		this.expression = expression;
	}
	/**
	 * 返回 cron表达式
	 * @return
	 */
	public String getExpression() 
	{
		return this.expression;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态（1=运行中；2=停止中）
	 * @return
	 */
	public Integer getStatus() 
	{
		return this.status;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("description", this.description) 
		.append("bean", this.bean) 
		.append("groupName", this.groupName) 
		.append("type", this.type) 
		.append("expression", this.expression) 
		.append("status", this.status) 
		.append("createTime", this.createTime) 
		.append("createBy", this.createBy) 
		.append("updateTime", this.updateTime) 
		.append("updateBy", this.updateBy) 
		.toString();
	}
}