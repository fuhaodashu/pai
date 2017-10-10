package com.pai.biz.member.api.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.doc.AutoDocField;
import com.pai.base.api.model.Bean;
/**
 * 对象功能:名人堂，为匿名用户服务 Tbl对象
 * 开发公司:π
 * 开发人员:FU_HAO
 * 创建时间:2017-07-15 16:45:43
 */
public class MemberFameBean extends Bean{
	
	private static final long serialVersionUID = 2L;
	
	@AutoDocField("用户id")
	private String  id; 
			
	@AutoDocField("名字")
	private String  name; 
			
	@AutoDocField("帮派")
	private String  organization; 
			
	@AutoDocField("职业")
	private String  vocation; 
			
	@AutoDocField("性别")
	private char  gender; 
			
	@AutoDocField("传记")
	private String  story; 
			
	@AutoDocField("故事来源")
	private String  source; 
			
	@AutoDocField("主要成就，相当于xx县委书记")
	private String  achievement; 
			
	@AutoDocField("学历，相当于xx大学毕业 ")
	private String  education; 
			
	@AutoDocField("出身，官二代，贫农，富二代")
	private String  background; 
			
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
}