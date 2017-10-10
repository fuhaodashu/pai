package com.pai.base.db.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractPo<PK extends java.io.Serializable> implements PO<PK>,Serializable{

	private PK pk;
	private String name;
	private String createBy;
	private Date createTime; 
	private String updateBy;
	private Date updateTime;

	public void setPk(PK id){
		this.pk = id;
		if(id instanceof String){
			setId((String)id);
		}
	}	

	public String getPk(){
		if(pk instanceof String){
			return (String)pk;
		}
		return "";
	}
	
	public abstract void setId(String id);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
