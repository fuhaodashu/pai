package com.pai.biz.auth.persistence.entity;
/**
 * 对象功能:角色-授权 entity对象
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-12-28 11:56:47
 */
public class AuthRoleResourcesPo extends AuthRoleResourcesTbl{
	private String  name; 		/*角色名称*/
	private String  resourceName; 		/*资源名称*/
	private String  descript; 		/*角色描述*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
}