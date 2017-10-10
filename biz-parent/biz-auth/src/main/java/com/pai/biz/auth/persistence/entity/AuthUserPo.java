package com.pai.biz.auth.persistence.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象功能:用户 entity对象
 * 开发公司:π
 * 开发人员:FUHAO
 * 创建时间:2016-09-29 18:00:11
 */
public class AuthUserPo extends AuthUserTbl{
	
	private List<AuthResourcesPo> authResourcesPos = new ArrayList<AuthResourcesPo>();
	
	private String roleNames;
	
	private String roleIds;
	
	public List<AuthResourcesPo> getAuthResourcesPos() {
		return authResourcesPos;
	}

	public void setAuthResourcesPos(List<AuthResourcesPo> authResourcesPos) {
		this.authResourcesPos = authResourcesPos;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
}