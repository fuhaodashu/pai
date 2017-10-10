package com.pai.biz.member.api.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.doc.AutoDocField;
import com.pai.base.api.model.Bean;
/**
 * 对象功能:会员表 Tbl对象
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 20:45:31
 */
public class MemberUserBean extends Bean{
	
	private static final long serialVersionUID = 2L;
	
	@AutoDocField("会员Id")
	private String  id; 
			
	@AutoDocField("昵称")
	private String  name; 
			
	@AutoDocField("手机号")
	private String  phone; 
			
	@AutoDocField("密码")
	private String  password; 
			
	@AutoDocField("邮箱")
	private String  mail; 
			
	@AutoDocField("头像")
	private String  profile; 
			
	@AutoDocField("行业")
	private String  vocation; 
			
	@AutoDocField("学校")
	private String  school; 
			
	@AutoDocField("专业")
	private String  profession; 
			
	@AutoDocField("状态（1=正常；2=冻结）")
	private Integer  status; 
			
	@AutoDocField("性别（男，女）")
	private char  gender; 
			
	@AutoDocField("个人简介")
	private String  description; 
			
	@AutoDocField("创建人")
	private String  createBy; 
			
	@AutoDocField("创建时间")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private java.util.Date  createTime; 
			
	@AutoDocField("修改人")
	private String  updateBy; 
			
	@AutoDocField("修改时间")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private java.util.Date  updateTime; 
	
	@AutoDocField("花名")
	private String fameName; 
	
	@AutoDocField("花名到期时间")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private java.util.Date dueTime; 
	
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