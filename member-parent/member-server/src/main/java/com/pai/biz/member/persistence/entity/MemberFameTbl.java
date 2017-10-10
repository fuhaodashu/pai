package com.pai.biz.member.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:名人堂，为匿名用户服务 Tbl对象
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 16:45:42
 */
 @ITable(name="memberFame",code="pai_member_fame")
public class MemberFameTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*用户id*/
	@IField(name="name",column="name")
	protected String  name; 		/*名字*/
	@IField(name="organization",column="organization")
	protected String  organization; 		/*帮派*/
	@IField(name="vocation",column="vocation")
	protected String  vocation; 		/*职业*/
	@IField(name="gender",column="gender")
	protected char  gender; 		/*性别*/
	@IField(name="story",column="story")
	protected String  story; 		/*传记*/
	@IField(name="source",column="source")
	protected String  source; 		/*故事来源*/
	@IField(name="achievement",column="achievement")
	protected String  achievement; 		/*主要成就，相当于xx县委书记*/
	@IField(name="education",column="education")
	protected String  education; 		/*学历，相当于xx大学毕业 */
	@IField(name="background",column="background")
	protected String  background; 		/*出身，官二代，贫农，富二代*/
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
	 * 返回 用户id
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
	 * 返回 名字
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setOrganization(String organization) 
	{
		this.organization = organization;
	}
	/**
	 * 返回 帮派
	 * @return
	 */
	public String getOrganization() 
	{
		return this.organization;
	}
	public void setVocation(String vocation) 
	{
		this.vocation = vocation;
	}
	/**
	 * 返回 职业
	 * @return
	 */
	public String getVocation() 
	{
		return this.vocation;
	}
	public void setGender(char gender) 
	{
		this.gender = gender;
	}
	/**
	 * 返回 性别
	 * @return
	 */
	public char getGender() 
	{
		return this.gender;
	}
	public void setStory(String story) 
	{
		this.story = story;
	}
	/**
	 * 返回 传记
	 * @return
	 */
	public String getStory() 
	{
		return this.story;
	}
	public void setSource(String source) 
	{
		this.source = source;
	}
	/**
	 * 返回 故事来源
	 * @return
	 */
	public String getSource() 
	{
		return this.source;
	}
	public void setAchievement(String achievement) 
	{
		this.achievement = achievement;
	}
	/**
	 * 返回 主要成就，相当于xx县委书记
	 * @return
	 */
	public String getAchievement() 
	{
		return this.achievement;
	}
	public void setEducation(String education) 
	{
		this.education = education;
	}
	/**
	 * 返回 学历，相当于xx大学毕业 
	 * @return
	 */
	public String getEducation() 
	{
		return this.education;
	}
	public void setBackground(String background) 
	{
		this.background = background;
	}
	/**
	 * 返回 出身，官二代，贫农，富二代
	 * @return
	 */
	public String getBackground() 
	{
		return this.background;
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
		.append("organization", this.organization) 
		.append("vocation", this.vocation) 
		.append("gender", this.gender) 
		.append("story", this.story) 
		.append("source", this.source) 
		.append("achievement", this.achievement) 
		.append("education", this.education) 
		.append("background", this.background) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.toString();
	}
}