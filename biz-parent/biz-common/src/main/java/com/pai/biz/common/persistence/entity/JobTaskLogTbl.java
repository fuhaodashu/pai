package com.pai.biz.common.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:任务调度日志 Tbl对象
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:35
 */
 @ITable(name="jobTaskLog",code="pai_job_task_log")
public class JobTaskLogTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*主键id*/
	@IField(name="jobKey",column="job_key")
	protected String  jobKey; 		/*作业任务键bean_id*/
	@IField(name="status",column="status")
	protected Integer  status; 		/*状态（1=成功；2=失败）*/
	@IField(name="jobLog",column="job_log")
	protected String  jobLog; 		/*日志*/
	@IField(name="createTime",column="create_time")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	protected java.util.Date  createTime; 		/*创建时间*/
	
	
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
	public void setJobKey(String jobKey) 
	{
		this.jobKey = jobKey;
	}
	/**
	 * 返回 作业任务键bean_id
	 * @return
	 */
	public String getJobKey() 
	{
		return this.jobKey;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态（1=成功；2=失败）
	 * @return
	 */
	public Integer getStatus() 
	{
		return this.status;
	}
	public void setJobLog(String jobLog) 
	{
		this.jobLog = jobLog;
	}
	/**
	 * 返回 日志
	 * @return
	 */
	public String getJobLog() 
	{
		return this.jobLog;
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
		.append("jobKey", this.jobKey) 
		.append("status", this.status) 
		.append("jobLog", this.jobLog) 
		.append("createTime", this.createTime) 
		.toString();
	}
}