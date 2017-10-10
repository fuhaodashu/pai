package com.pai.biz.member.persistence.entity;


/**
 * 对象功能:会员表 entity对象
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-02 18:04:24
 */
public class MemberUserPo extends MemberUserTbl{

	private String fameName; //花名
	
	private java.util.Date dueTime; //花名到期时间

	public String getFameName() {
		return fameName;
	}

	public void setFameName(String fameName) {
		this.fameName = fameName;
	}

	public java.util.Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(java.util.Date dueTime) {
		this.dueTime = dueTime;
	}
	
	
}