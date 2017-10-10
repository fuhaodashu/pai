package com.pai.biz.common.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:任务调度参数表，同一个定时器不同时间段跑不同的任务就需要传参 Tbl对象
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2017-02-10 14:05:12
 */
 @ITable(name="jobTaskParam",code="pai_job_task_param")
public class JobTaskParamTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*主键id*/
	@IField(name="jobId",column="job_id_")
	protected String  jobId; 		/*任务调度id*/
	@IField(name="paramKey",column="param_key")
	protected String  paramKey; 		/*参数键*/
	@IField(name="paramValue",column="param_value")
	protected String  paramValue; 		/*参数值*/
	@IField(name="valueType",column="value_type")
	protected String  valueType; 		/*参数值类型（string=字符串；int=整型；long=长整型；double=浮点；）*/
	
	
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
	public void setJobId(String jobId) 
	{
		this.jobId = jobId;
	}
	/**
	 * 返回 任务调度id
	 * @return
	 */
	public String getJobId() 
	{
		return this.jobId;
	}
	public void setParamKey(String paramKey) 
	{
		this.paramKey = paramKey;
	}
	/**
	 * 返回 参数键
	 * @return
	 */
	public String getParamKey() 
	{
		return this.paramKey;
	}
	public void setParamValue(String paramValue) 
	{
		this.paramValue = paramValue;
	}
	/**
	 * 返回 参数值
	 * @return
	 */
	public String getParamValue() 
	{
		return this.paramValue;
	}
	public void setValueType(String valueType) 
	{
		this.valueType = valueType;
	}
	/**
	 * 返回 参数值类型（string=字符串；int=整型；long=长整型；double=浮点；）
	 * @return
	 */
	public String getValueType() 
	{
		return this.valueType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("jobId", this.jobId) 
		.append("paramKey", this.paramKey) 
		.append("paramValue", this.paramValue) 
		.append("valueType", this.valueType) 
		.toString();
	}
}