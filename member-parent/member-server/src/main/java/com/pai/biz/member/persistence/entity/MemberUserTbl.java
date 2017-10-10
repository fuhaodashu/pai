package com.pai.biz.member.persistence.entity;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:会员表 Tbl对象
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 20:45:31
 */
 @ITable(name="memberUser",code="pai_member_user")
public class MemberUserTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*会员Id*/
	@IField(name="name",column="name")
	protected String  name; 		/*昵称*/
	@IField(name="phone",column="phone")
	protected String  phone; 		/*手机号*/
	@IField(name="password",column="password")
	protected String  password; 		/*密码*/
	@IField(name="mail",column="mail")
	protected String  mail; 		/*邮箱*/
	@IField(name="profile",column="profile")
	protected String  profile; 		/*头像*/
	@IField(name="vocation",column="vocation")
	protected String  vocation; 		/*行业*/
	@IField(name="school",column="school")
	protected String  school; 		/*学校*/
	@IField(name="profession",column="profession")
	protected String  profession; 		/*专业*/
	@IField(name="status",column="status")
	protected Integer  status; 		/*状态（1=正常；2=冻结）*/
	@IField(name="gender",column="gender")
	protected char  gender; 		/*性别（男，女）*/
	@IField(name="description",column="description")
	protected String  description; 		/*个人简介*/
	@IField(name="createBy",column="create_by")
	protected String  createBy; 		/*创建人*/
	@IField(name="createTime",column="create_time")
	protected java.util.Date  createTime; 		/*创建时间*/
	@IField(name="updateBy",column="update_by")
	protected String  updateBy; 		/*修改人*/
	@IField(name="updateTime",column="update_time")
	protected java.util.Date  updateTime; 		/*修改时间*/
	
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 会员Id
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
	public void setMail(String mail) 
	{
		this.mail = mail;
	}
	/**
	 * 返回 邮箱
	 * @return
	 */
	public String getMail() 
	{
		return this.mail;
	}
	public void setProfile(String profile) 
	{
		this.profile = profile;
	}
	/**
	 * 返回 头像
	 * @return
	 */
	public String getProfile() 
	{
		return this.profile;
	}
	public void setVocation(String vocation) 
	{
		this.vocation = vocation;
	}
	/**
	 * 返回 行业
	 * @return
	 */
	public String getVocation() 
	{
		return this.vocation;
	}
	public void setSchool(String school) 
	{
		this.school = school;
	}
	/**
	 * 返回 学校
	 * @return
	 */
	public String getSchool() 
	{
		return this.school;
	}
	public void setProfession(String profession) 
	{
		this.profession = profession;
	}
	/**
	 * 返回 专业
	 * @return
	 */
	public String getProfession() 
	{
		return this.profession;
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
	public void setGender(char gender) 
	{
		this.gender = gender;
	}
	/**
	 * 返回 性别（男，女）
	 * @return
	 */
	public char getGender() 
	{
		return this.gender;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	/**
	 * 返回 个人简介
	 * @return
	 */
	public String getDescription() 
	{
		return this.description;
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
		.append("mail", this.mail) 
		.append("profile", this.profile) 
		.append("vocation", this.vocation) 
		.append("school", this.school) 
		.append("profession", this.profession) 
		.append("status", this.status) 
		.append("gender", this.gender) 
		.append("description", this.description) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.toString();
	}
}