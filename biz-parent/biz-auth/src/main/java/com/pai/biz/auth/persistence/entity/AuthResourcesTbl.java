package com.pai.biz.auth.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

/**
 * 对象功能:资源 Tbl对象
 * 开发公司:PAI.COM
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 10:37:44
 */
 @ITable(name="authResources",code="pai_auth_resources")
public class AuthResourcesTbl extends AbstractPo<String>{
	@IField(name="id",column="id_")
	protected String  id; 		/*资源Id*/
	@IField(name="name",column="name")
	protected String  name; 		/*名称*/
	@IField(name="type",column="type")
	protected Integer  type; 		/*资源类型（1=菜单；2=功能按钮）*/
	@IField(name="url",column="url")
	protected String  url; 		/*资源链接*/
	@IField(name="parentId",column="parent_id_")
	protected String  parentId; 		/*父资源Id*/
	@IField(name="path",column="path")
	protected String  path; 		/*树路劲*/
	@IField(name="depth",column="depth")
	protected Integer  depth; 		/*层次*/
	@IField(name="icon",column="icon")
	protected String  icon; 		/*图标*/
	@IField(name="sort",column="sort")
	protected Integer  sort; 		/*排序*/
	@IField(name="status",column="status")
	protected Integer  status; 		/*状态*/
	
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 资源Id
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
	public void setType(Integer type) 
	{
		this.type = type;
	}
	/**
	 * 返回 资源类型（1=菜单；2=功能按钮）
	 * @return
	 */
	public Integer getType() 
	{
		return this.type;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}
	/**
	 * 返回 资源链接
	 * @return
	 */
	public String getUrl() 
	{
		return this.url;
	}
	public void setParentId(String parentId) 
	{
		this.parentId = parentId;
	}
	/**
	 * 返回 父资源Id
	 * @return
	 */
	public String getParentId() 
	{
		return this.parentId;
	}
	public void setPath(String path) 
	{
		this.path = path;
	}
	/**
	 * 返回 树路劲
	 * @return
	 */
	public String getPath() 
	{
		return this.path;
	}
	public void setDepth(Integer depth) 
	{
		this.depth = depth;
	}
	/**
	 * 返回 层次
	 * @return
	 */
	public Integer getDepth() 
	{
		return this.depth;
	}
	public void setIcon(String icon) 
	{
		this.icon = icon;
	}
	/**
	 * 返回 图标
	 * @return
	 */
	public String getIcon() 
	{
		return this.icon;
	}
	public void setSort(Integer sort) 
	{
		this.sort = sort;
	}
	/**
	 * 返回 排序
	 * @return
	 */
	public Integer getSort() 
	{
		return this.sort;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态
	 * @return
	 */
	public Integer getStatus() 
	{
		return this.status;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("type", this.type) 
		.append("url", this.url) 
		.append("parentId", this.parentId) 
		.append("path", this.path) 
		.append("depth", this.depth) 
		.append("icon", this.icon) 
		.append("sort", this.sort) 
		.append("status", this.status) 
		.toString();
	}
}